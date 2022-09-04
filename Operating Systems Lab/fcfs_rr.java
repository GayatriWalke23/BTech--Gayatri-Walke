//GAYATRI WALKE (ROLL NO : 2466)
package scheduling_algorithms;

import java.util.Scanner;
class queue
{
	//queue for round robin 
	int front;
	int rear;
	int number;	
	
	
		
	queue(int n)
	{
		front=-1;
		rear=-1;
		number=n;
	}
	int[] q = new int[10];

	
	void enqueue(int temp)
	{
		//enqueue
		
		if(!is_full())
		{
			rear = (rear+1)%number;		//circular queue	
			q[rear]=temp;
			
			if(front==-1)
				front=rear;
		}
	}
	
	int dequeue()
	{
		//dequeue
		int pid;
		if(!is_empty())
		{
			pid=q[front];
			if(front==rear)
			{
				front=-1;
				rear=-1;
			}
			else
			{				
				front=(front+1)%number;
				return pid;
			}
			return pid;
		}
		
		
		return -1;
		
	}
	
	boolean is_full()
	{
		
		if(front==((rear+1)%number))
			return true;
		else
			return false;
		
	}
	
	boolean is_empty()
	{
		if(front==-1)
			return true;
		else
			return false;
		
	}
	
	
	
	
}

class process
{
	//process class and all its attributes
	Scanner input = new Scanner(System.in);
	float arrival_time;
	float burst_time;
	float completion_time;
	float turnover_time;
	float waiting_time;
	int number;
	int quantum;
	float remaining_time;
	
	
	process p[]=new process[100];//for processes
	
	
	process() //constructor
	{
		number=0;
		arrival_time=0;
		burst_time=0;
		completion_time=0;
		turnover_time=0;
		waiting_time=0;
		remaining_time=0;
	}
	
	
	void accept_process_details()
	{
		System.out.print("Enter the number of processes : ");
		number=input.nextInt();
		
		
		//accept details
		
		for(int i=0; i < number ;i++)
		{
			System.out.println("enter");
			p[i]= new process();
			System.out.println("For the "+(i+1)+" process ");
			System.out.print(" enter the arrival time : ");
			p[i].arrival_time=input.nextFloat();
			System.out.print(" enter the burst time : ");
			p[i].burst_time=input.nextFloat();
			p[i].remaining_time=p[i].burst_time;
			System.out.println();
			
		}
		
	}
	

	void accept_process_details_()
	{
		System.out.print("Enter the number of processes : ");
		number=input.nextInt();
		
		
		//accept details
		
		for(int i=0; i < number ;i++)
		{
			System.out.println("enter");
			p[i]= new process();
			System.out.println("For the "+(i+1)+" process ");
			//System.out.print(" enter the arrival time : ");
			//p[i].arrival_time=input.nextFloat();
			System.out.print(" enter the burst time : ");
			p[i].burst_time=input.nextFloat();
			p[i].remaining_time=p[i].burst_time;
			System.out.println();
			
		}
		
	}
	
	void calculation_rr()
	{
		int gantt_id[]= new int[100];
		float gantt_time[]= new float[100];
		int gantt_number=0;
		int pid=0;
		float time=0;
		queue call=new queue(number);
		System.out.print("ENTER QUANTUM : ");
		quantum=input.nextInt();
		
		System.out.print(" Ready queue : ");//for ready queue
		for(int i=0; i < number ;i++)
			call.enqueue(i);
			
		pid=call.dequeue();
		while(pid!=-1)
		{
			System.out.print("|P"+(pid+1)+"("+p[pid].remaining_time+")");
			
			//calculate completion time
			if(p[pid].remaining_time>quantum) //serve and again again put in queue
			{
				p[pid].completion_time=time+quantum;
				time=time+quantum;
				p[pid].remaining_time=p[pid].remaining_time-quantum;
				call.enqueue(pid);
				
			}
			else if(p[pid].remaining_time<=quantum)
			{
				p[pid].completion_time=time+p[pid].remaining_time;
				time=time+p[pid].remaining_time;
				p[pid].remaining_time=p[pid].remaining_time-p[pid].remaining_time;
				
			}
			gantt_id[gantt_number]=pid;
			gantt_time[gantt_number]=time; //fill gantt chart for later 
			gantt_number++;
			
			pid=call.dequeue();
				
				
		}
		
		
		System.out.println("|");
		System.out.println("");
		System.out.print(" gantt chart : ");
		for(int i=0; i < gantt_number ;i++) //for gantt chart
		{
			System.out.print("|P"+(gantt_id[i]+1)+"("+gantt_time[i]+")");
		}
		System.out.println("|");
		System.out.println("");
		for(int i=0; i < number ;i++)//calculate turn-over time and waiting time
		{
			p[i].turnover_time = p[i].completion_time - p[i].arrival_time;
			p[i].waiting_time=p[i].turnover_time-p[i].burst_time;
			
		}
		
		
		
		
	}
	
	void calculation_fcfs()
	{
		float total_time=0;
		System.out.print("Ready Queue : "); //for ready queue
		for(int i=0; i < number ;i++)
		{
			System.out.print("|P"+(i+1)+"("+p[i].burst_time+")");
		}
		System.out.println("|");
		System.out.println("");
		System.out.print("Gantt Chart : "); //for gantt chart
		for(int i=0; i < number ;i++)
		{
			
			//calculate time 
			p[i].completion_time=p[i].burst_time+total_time;
			total_time=p[i].completion_time;
			p[i].turnover_time = p[i].completion_time - p[i].arrival_time;
			p[i].waiting_time=p[i].turnover_time-p[i].burst_time;
			System.out.print("|P"+(i+1)+"("+total_time+")");
			
		}
		System.out.println("|");
		System.out.println("");
		
	}
	
	void display()
	{
		System.out.println();
		System.out.println("TABLE :");
		System.out.println();
		//display table
		System.out.println("Process \tarrival time \tburst time \tcompletion time \tturnover time \twaiting time");
		System.out.println("");
		for(int i=0; i < number ;i++)
		{
			System.out.println("   P"+(i+1)+"      \t   "+p[i].arrival_time+" \t           "+p[i].burst_time+" \t             "+p[i].completion_time+" \t             "+p[i].turnover_time+" \t     "+p[i].waiting_time);
		}
		
		System.out.println();
	}

	void display_average_time()
	{
		float average_turnover=0;
		float average_waiting=0;
		for(int i=0; i < number ;i++)
		{
			average_turnover=average_turnover+p[i].turnover_time;
			average_waiting=average_waiting+p[i].waiting_time;
			
		}
		//calculate average
		average_turnover=average_turnover/number;
		average_waiting=average_waiting/number;
		
		System.out.println("Average turn-over time : "+average_turnover+" units");
		System.out.println("Average waiting time : "+average_waiting+" units");
		
		
	}
}



public class algo 
{
	
	//main method
	public static void main(String args[])
	{
		Scanner input = new Scanner(System.in);
		int choice;//input user
		process call = new process(); //for calling process class
		
		
		do
		{
			    //display menu
				
				System.out.println("      MENU   ");
				System.out.println("1. FIRST COME FIRST SERVE (FCFS)");
				System.out.println("2. ROUND ROBIN (RR)");
				System.out.println("0. EXIT");
				System.out.print("enter your choice : ");
				
				choice = input.nextInt();
				System.out.println();
				switch(choice)
				{
					//call
					case 1:
						call.accept_process_details();
						System.out.println("      FIRST COME FIRST SERVE     ");
						call.calculation_fcfs();
						call.display();
						call.display_average_time();
						System.out.println();
						System.out.println();
						break;
						
						
					case 2:
						call.accept_process_details_();
						System.out.println("      ROUND ROBIN     ");
						call.calculation_rr();
						call.display();
						call.display_average_time();
						System.out.println();
						System.out.println();
						break;
						
						
					case 0:
						System.out.println();
						System.out.println("     THANK YOU");
						break;
						
						
					default:
						System.out.println("enter valid choice!");
						break;
				}
		
		}while(choice!=0);
		input.close();
	}

}

//		OUTPUT
/*
       MENU   
1. FIRST COME FIRST SERVE (FCFS)
2. ROUND ROBIN (RR)
0. EXIT
enter your choice : 1

Enter the number of processes : 4
enter
For the 1 process 
 enter the arrival time : 0
 enter the burst time : 6

enter
For the 2 process 
 enter the arrival time : 2
 enter the burst time : 4

enter
For the 3 process 
 enter the arrival time : 4
 enter the burst time : 8

enter
For the 4 process 
 enter the arrival time : 6
 enter the burst time : 4

      FIRST COME FIRST SERVE     
Ready Queue : |P1(6.0)|P2(4.0)|P3(8.0)|P4(4.0)|

Gantt Chart : |P1(6.0)|P2(10.0)|P3(18.0)|P4(22.0)|


TABLE :

Process 	arrival time 	burst time 	completion time 	turnover time 	waiting time

   P1      	   0.0 	           6.0 	             6.0 	             6.0 	     0.0
   P2      	   2.0 	           4.0 	             10.0 	             8.0 	     4.0
   P3      	   4.0 	           8.0 	             18.0 	             14.0 	     6.0
   P4      	   6.0 	           4.0 	             22.0 	             16.0 	     12.0

Average turn-over time : 11.0 units
Average waiting time : 5.5 units


      MENU   
1. FIRST COME FIRST SERVE (FCFS)
2. ROUND ROBIN (RR)
0. EXIT
enter your choice : 2

Enter the number of processes : 4
enter
For the 1 process 
 enter the burst time : 6

enter
For the 2 process 
 enter the burst time : 5

enter
For the 3 process 
 enter the burst time : 8

enter
For the 4 process 
 enter the burst time : 4

      ROUND ROBIN     
ENTER QUANTUM : 2
 Ready queue : |P1(6.0)|P2(5.0)|P3(8.0)|P4(4.0)|P1(4.0)|P2(3.0)|P3(6.0)|P4(2.0)|P1(2.0)|P2(1.0)|P3(4.0)|P3(2.0)|

 Gantt chart : |P1(2.0)|P2(4.0)|P3(6.0)|P4(8.0)|P1(10.0)|P2(12.0)|P3(14.0)|P4(16.0)|P1(18.0)|P2(19.0)|P3(21.0)|P3(23.0)|


TABLE :

Process 	arrival time 	burst time 	completion time 	turnover time 	waiting time

   P1      	   0.0 	           6.0 	             18.0 	             18.0 	     12.0
   P2      	   0.0 	           5.0 	             19.0 	             19.0 	     14.0
   P3      	   0.0 	           8.0 	             23.0 	             23.0 	     15.0
   P4      	   0.0 	           4.0 	             16.0 	             16.0 	     12.0

Average turn-over time : 19.0 units
Average waiting time : 13.25 units


      MENU   
1. FIRST COME FIRST SERVE (FCFS)
2. ROUND ROBIN (RR)
0. EXIT
enter your choice : 0


     THANK YOU

 */
