/*
 * Lab3.asm
 *
 *  Created: 2013-11-07 23:13:46
 *   Author: gauee
 */ 


 .include "m32def.inc"

 .dseg
	;starting at 0x60
	rslt_space:	.byte 100	; Reserve 100 bytes in SRAM for string

.cseg
.org 0x0280			;all in all it will be in 2*0x280 = 0x500
	example_txt: .db "Damian Galka",0,0		;example_txt should be shorter than 159 character because of overflow when adding to XL len of this word. X starts with 0x60
.org 0


.macro WAIT
		;InitWait
		;adding registers to stack
		push R16				
		push R17
        ldi R16,@0					
		rjmp LOOP_DESC_PARAM		
		;EndInitWait takes 15 cycles
		TO_INIT_LOOP:	; no operation to equalize with initWait take the same duration
			nop
			nop
			push R16	;no special operation only to equalize with push and pop in init and exit wait
			pop R16
			push R16
			pop R16
		LOOP_DESC_PARAM:
			;setting value of milisec amount decounter loop
			push R18
			push R19
			push R20
			push R21
			ldi R17,0					
			ldi R18,1					
			ldi R19,0
			ldi R20,0xA					 
			ldi R21,0x64			;0x0A64 = 2660
			;to this line it takes 5 cycles
			LOOP_MS_SLEEPER:		;loop during 1ms, 1 course take 6 cycles
				sub R21,R18			
				sbc R20,R17			
				adc R19,R17			
				cpse R18,R19		;double carry time to go out
				rjmp LOOP_MS_SLEEPER
			;decrementing amount of ms to wait
			sub R16,R18
			pop R21
			pop R20
			pop R19
			pop R18
			cpse R16,R17
			rjmp TO_INIT_LOOP
			;cleaning stack and setting register to state before calling wait
			pop R17
			pop R16
.endmacro

rjmp INIT
;zamiana ³ancucha znaków
reverseString:
	;defined helpful names
	.def length = R16
	.def first_char = R17
	.def last_char = R18
	;pushing registers on stack to save state of registers
	push ZL
	push ZH
	push XL
	push XH
	push length
	push first_char
	push last_char

	;Load adress to read from FLASH
	ldi ZL,low(example_txt*2)
	ldi ZH,high(example_txt*2)
	;load adress to write in SRAM
	ldi XL,low(rslt_space)
	ldi XH,high(rslt_space)

	;getting string length
	;saving pointer to Flash by pushing Z Register
	push ZL
	push ZH
	
	;init len to zero
	ldi length,0

	COUNTING_CHAR:
		lpm first_char, Z+
		cpi first_char,0		;equals with character at the end of string '0'
		breq COMPLETE_COUNTING
		inc length
		rjmp COUNTING_CHAR
	COMPLETE_COUNTING:	;having current length of string in R16
	
	;restoring pointer to Flash
	pop ZH
	pop ZL

	;having current len and pointer for start of word
	;store pointer to Flash
	push ZL
	push ZH
	;store pointer to SRAM
	push XL
	push XH
	;store length of word 
	push length
	
	COPING_CHAR_FROM_FLASH_TO_SRAM:
		lpm first_char,Z+		;reading to R17
		st X,first_char			;writing to X
		inc XL					
		dec length
		cpi length,0
		brne COPING_CHAR_FROM_FLASH_TO_SRAM

	;restoring params
	pop length
	pop XH
	pop XL
	pop ZH
	pop ZL

	;reversing string
	REVERSING_CHARACTERS_IN_STRING:
		ld first_char,X		;writing character at the adress of X Register
		dec length
		add XL,length		;moving pointer forward to last character in no-reversed character sequence
		ld last_char,X		;writing character at the adress of X Register
		st X,first_char		;overwrite the last_char with the first_char
		sub XL,length		;moving pointer backward to first character in no-reversed character sequence
		st X,last_char		;overwrite the first_char with the last_char
		dec length			
		inc XL				;moving adress in X Register forward
		cpi length,1		;true(Z=1) when the last character left,whole word is reversed
		breq REVERSED_ALL_CHARACTERS
		cpi length,0		;true(Z=1) when no character left, whole word is reversed
		breq REVERSED_ALL_CHARACTERS
		rjmp REVERSING_CHARACTERS_IN_STRING	;some characters are not reversed in string

	REVERSED_ALL_CHARACTERS:
	
	;poping values from stack to register in oposite direction and restoring staste of register before calling procedure
	pop last_char
	pop first_char
	pop length
	pop XH
	pop XL
	pop ZH
	pop ZL
	ret

INIT:
	;stack initialize
	ldi R16, LOW(RAMEND)
	out SPL, R16
	ldi R16, HIGH(RAMEND)
	out SPH, R16
	;stack initialized
	rjmp START

START:

	WAIT 10
	call reverseString

	rjmp START
