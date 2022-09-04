//GAYATRI WALKE (ROLL NO : 2466)
package sjf_srtf;

import java.util.Scanner;

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
	
	
	void accept_process_details()//for sjf where arrival time is 0
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
	
	void accept_process_details_() //for srtf to consider arrival time
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
	int get_process_id(float time)//returns a process whose arrival time is less than execution time and has least remaining time to srtf
	{
		int p_id=0;
		float min_time=1000;
		int flag=0;
		for(int i=0;i<number;i++)
		{
			if(min_time>p[i].remaining_time&&p[i].remaining_time!=0&&p[i].arrival_time<=time)//checks condition
			{
				min_time=p[i].remaining_time;
				p_id=i;
				flag=1;
			}
		}
		if(flag==1)
			return p_id;
		else
			return -1;//when no process is returned
	}
	
	void calculation_srtf()
	{
		
		int pid=0;
		float time=0;
		
		System.out.print("Gantt chart : ");
			
		pid=get_process_id(time); //gets process id to start execution of least remaining time
		while(pid!=-1)
		{
			
			
			//calculate completion time
			time++;
			p[pid].completion_time=time; //calculates of 1 unit time
			p[pid].remaining_time=p[pid].remaining_time-1;
			System.out.print("|P"+(pid+1)+"("+time+")");
			pid=get_process_id(time);//again checks for the process
			
				
		}
		
		
		System.out.println("|");
		System.out.println("");
		for(int i=0; i < number ;i++)//calculate turn-over time and waiting time
		{
			p[i].turnover_time = p[i].completion_time - p[i].arrival_time;	
			p[i].waiting_time=p[i].turnover_time-p[i].burst_time;
			
		}
		
		
		
		
	}
	
	void calculation_sjf()
	{
		float total_time=0;
		int temp;
		int ready_queue[] = new int[number];
		
		for(int i=0; i<number;i++)
		{
			ready_queue[i]=i; //fill ready queue
		}
		System.out.print("Ready Queue : ");
		for(int i=0; i<number;i++)
		{
			System.out.print("|P"+(ready_queue[i]+1)+"("+p[ready_queue[i]].burst_time+")");
		}
		System.out.println("|");
		System.out.println("");
		for(int j=0; j<number-1;j++)//sort ready queue in order of the process burst time
		{
			for(int i=0; i<number-1-j;i++)
			{
				if(p[ready_queue[i]].burst_time>p[ready_queue[i+1]].burst_time) 
				{
					temp=ready_queue[i];
					ready_queue[i]=ready_queue[i+1];
					ready_queue[i+1]=temp;
				}
			}
			
			
		}
		
		temp=0;
		System.out.print("Gantt chart : ");
		for(int i1=ready_queue[temp]; temp < number ;temp++) //calculate other parameters
		{
			i1=ready_queue[temp];
			
			//calculate time 
			p[i1].completion_time=p[i1].burst_time+total_time;
			total_time=p[i1].completion_time;
			p[i1].turnover_time = p[i1].completion_time - p[i1].arrival_time;
			p[i1].waiting_time=p[i1].turnover_time-p[i1].burst_time;
			System.out.print("|P"+(i1+1)+"("+total_time+")");
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
		System.out.println("Average waiting time   : "+average_waiting+" units");
		
		
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
				System.out.println("1. SHORTEST JOB FIRST (SJF)");
				System.out.println("2. SHORTEST REMAINING TIME FIRST (SRTF)");
				System.out.println("0. EXIT");
				System.out.print("enter your choice : ");
				
				choice = input.nextInt();
				System.out.println();
				switch(choice)
				{
					//call
					case 1:
						call.accept_process_details();
						System.out.println("      SHORTEST JOB FIRST (SJF)     ");
						System.out.println();
						call.calculation_sjf();
						call.display();
						call.display_average_time();
						System.out.println();
						System.out.println();
						break;
						
						
					case 2:
						call.accept_process_details_();
						System.out.println("      SHORTEST REMAINING TIME FIRST (SRTF)     ");
						System.out.println();
						call.calculation_srtf();
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

//     		OUTPUT

/*
 *       MENU   
1. SHORTEST JOB FIRST (SJF)
2. SHORTEST REMAINING TIME FIRST (SRTF)
0. EXIT
enter your choice : 1

Enter the number of processes : 4
enter
For the 1 process 
 enter the burst time : 6

enter
For the 2 process 
 enter the burst time : 2

enter
For the 3 process 
 enter the burst time : 5

enter
For the 4 process 
 enter the burst time : 3

      SHORTEST JOB FIRST (SJF)     

Ready Queue : |P1(6.0)|P2(2.0)|P3(5.0)|P4(3.0)|

Gantt chart : |P2(2.0)|P4(5.0)|P3(10.0)|P1(16.0)|


TABLE :

Process 	arrival time 	burst time 	completion time 	turnover time 	waiting time

   P1      	   0.0 	           6.0 	             16.0 	             16.0 	     10.0
   P2      	   0.0 	           2.0 	             2.0 	             2.0 	     0.0
   P3      	   0.0 	           5.0 	             10.0 	             10.0 	     5.0
   P4      	   0.0 	           3.0 	             5.0 	             5.0 	     2.0

Average turn-over time : 8.25 units
Average waiting time   : 4.25 units


      MENU   
1. SHORTEST JOB FIRST (SJF)
2. SHORTEST REMAINING TIME FIRST (SRTF)
0. EXIT
enter your choice : 2

Enter the number of processes : 4
enter
For the 1 process 
 enter the arrival time : 0
 enter the burst time : 6

enter
For the 2 process 
 enter the arrival time : 2
 enter the burst time : 2

enter
For the 3 process 
 enter the arrival time : 4
 enter the burst time : 5

enter
For the 4 process 
 enter the arrival time : 6
 enter the burst time : 3

      SHORTEST REMAINING TIME FIRST (SRTF)     

Gantt chart : |P1(1.0)|P1(2.0)|P2(3.0)|P2(4.0)|P1(5.0)|P1(6.0)|P1(7.0)|P1(8.0)|P4(9.0)|P4(10.0)|P4(11.0)|P3(12.0)|P3(13.0)|P3(14.0)|P3(15.0)|P3(16.0)|


TABLE :

Process 	arrival time 	burst time 	completion time 	turnover time 	waiting time

   P1      	   0.0 	           6.0 	             8.0 	             8.0 	     2.0
   P2      	   2.0 	           2.0 	             4.0 	             2.0 	     0.0
   P3      	   4.0 	           5.0 	             16.0 	             12.0 	     7.0
   P4      	   6.0 	           3.0 	             11.0 	             5.0 	     2.0

Average turn-over time : 6.75 units
Average waiting time   : 2.75 units


      MENU   
1. SHORTEST JOB FIRST (SJF)
2. SHORTEST REMAINING TIME FIRST (SRTF)
0. EXIT
enter your choice : 0


     THANK YOU

 
*/
