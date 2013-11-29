/*
 * Lab6.c
 *
 * Created: 2013-11-27 18:32:54
 *  Author: gauee
 */ 


#define F_CPU 16000000UL

#include <avr/io.h>
#include "TaskScheduler.h"

#define MAX_COL 4

uint8_t COLS[MAX_COL] = {
	0b11111110,
	0b11111101,
	0b11111011,
	0b11110111
};

uint8_t numberAt4Cols[MAX_COL] = {0,0,0,0};

int cur_idx = 0;
int currentSecond =0;

uint8_t Digits[10] = {
	0b11000000,		//0
	0b11111001,		//1
	0b10100100,		//2
	0b10110000,		//3
	0b10011001,		//4
	0b10010010,		//5
	0b10000010,		//6
	0b11111000,		//7
	0b10000000,		//8
	0b10010000
};

void showNextDigit(void);
void countSeconds(void);

void init_portA(void);
void init_portB(void);


int main(void)
{
	
	init();
	init_portA();
	init_portB();
	add_task(showNextDigit,4);
	add_task(countSeconds,10);
	start_timer();
	
    while(1)
    {
		execute();   
    }
}

void init_portA(){
	DDRA = 0xFF;
}

void init_portB(){
	DDRB = 0xFF;
}

void showNextDigit(void){
	PORTA = Digits[numberAt4Cols[cur_idx]];
	if(cur_idx==1){
		PORTA <<= 1;
		PORTA >>= 1;
	}
	PORTB = COLS[cur_idx]; 
	cur_idx++;
	cur_idx%=MAX_COL;
}

void countSeconds(void){
	++currentSecond;
	currentSecond%=10000;
	numberAt4Cols[0] = currentSecond/1000;
	numberAt4Cols[1] = (currentSecond%1000)/100;
	numberAt4Cols[2] = (currentSecond%100)/10;
	numberAt4Cols[3] = currentSecond%10;
}