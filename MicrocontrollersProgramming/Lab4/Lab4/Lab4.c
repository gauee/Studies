/*
 * Lab4.c
 *
 * Created: 2013-11-13 18:31:30
 *  Author: gauee
 */
#define F_CPU 16000000UL

#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>


int main(void)
{
	init_for_all_lamps();
	int i=1;
	sei();
	while(1){
		if(OCR0 == 255){
			i=-1;
		}else if(OCR0 == 0){
			i=1;
		}
		OCR0+=i;
		_delay_ms(2);
    }
	
	return 0;
}

void init(){
	DDRB  |= (1<<PB3);
	TCCR0 |= (1<<WGM01) | (1<<WGM00) | (1<<COM01) | (1<<COM00) | (1<<CS00);
}

void init_for_all_lamps(){
	DDRA  |= 0xFF;
	TCCR0 |= (1<<WGM01) | (1<<WGM00) | (1<<CS00) | (1<<CS02);
	TIMSK |= (1<<OCIE0) | (1<<TOIE0);
}

ISR(TIMER0_COMP_vect){
	PORTA = 0b01010101;
}

ISR(TIMER0_OVF_vect){
	PORTA = 0b10101010;
}
