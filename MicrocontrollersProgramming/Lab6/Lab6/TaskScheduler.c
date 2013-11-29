/*
 * Lab5.c
 *
 * Created: 2013-11-20 18:25:32
 *  Author: gauee
 */ 


#define F_CPU 16000000UL

#include <avr/io.h>
#include <avr/interrupt.h>

#include "TaskScheduler.h"

int new_task_idx = 0;

unsigned int toInc = 0;

void increment(void){
	++toInc;
}

void incrementOneShot(void){
	toInc+=10;
}

void init(){
	
	for(int i=0;i<SCHEDULER_SIZE;++i){
		scheduled_task[i].task_ptr = 0;
		scheduled_task[i].inteval = 0;
		scheduled_task[i].ready = 0;
		scheduled_task[i].to_go = 0;
	}
}

void start_timer(){
	OCR0 = 250;
	TCCR0 = (1 << WGM01) | (0 << WGM00) | (0 << CS02) | (1 << CS01) | (1 << CS00);
	TIMSK |= (1<<OCIE0);		//Enabling interrupt Timer/Counter0 Compare Match.
	sei();
}

void add_task(TASK_PTR t,int ms){
	
	if(new_task_idx == SCHEDULER_SIZE){
		return;
	}
	
	scheduled_task[new_task_idx].inteval = ms;
	scheduled_task[new_task_idx].to_go = ms;
	scheduled_task[new_task_idx].ready = 0;
	scheduled_task[new_task_idx].task_ptr = t;
	++new_task_idx;
	
}

void add_one_shot(TASK_PTR t,int ms){
	if(new_task_idx == SCHEDULER_SIZE){
		return;
	}
	
	scheduled_task[new_task_idx].inteval = 0;
	scheduled_task[new_task_idx].to_go = ms;
	scheduled_task[new_task_idx].ready =0;
	scheduled_task[new_task_idx].task_ptr = t;
	
	++new_task_idx;
}

void schedule(){
	for(int i=0;i<new_task_idx;++i){
		if(scheduled_task[i].to_go){
			scheduled_task[i].to_go--;
			if(!scheduled_task[i].to_go){
				scheduled_task[i].ready++;
				scheduled_task[i].to_go = scheduled_task[i].inteval;
			}
		}
	}
	
}

void execute(){
		int i =0;
		while(i < new_task_idx){
			if(scheduled_task[i].ready){
				scheduled_task[i].task_ptr();
				scheduled_task[i].ready--;	
				i=-1;
			}
			i++;
		}
}

ISR(TIMER0_COMP_vect){
	schedule();
}
