package dsa_temp;

import java.util.Scanner;


public class disk_scheduling 
{
	//attributes 
	int total_cylinders;
	int head_positon;
	int number_cylinder_request_sequence;
	String request_sequence;
	static Scanner input =new Scanner(System.in); //for input
	int rq_sequence[]=new int[100];		//the final request sequence
	int seek_sequence[]=new int[100];	//the actual seek sequence 
	int real_seek_sequence[]=new int[100]; //the actual seek sequence except for the algo added one
	int seek_time;		
	int index=0;		//for actual seek sequence count
	
	
	disk_scheduling()			//constructor
	{
		head_positon=0;
		number_cylinder_request_sequence=0;
		total_cylinders=0;
		request_sequence=null;
		seek_time=0;

	}
	
	void clear() 		//clear function to reset values
	{
		seek_time=0;
		index=0;
	}
	
	void first_come_first_serve()
	{
		System.out.println("			FIRST COME FIRST SERVE(FCFS)");
		seek_sequence[0]=head_positon;
		
		for(int i=1;i<=number_cylinder_request_sequence;i++)
			seek_sequence[i]=rq_sequence[i-1];			//fill seek sequence
		
		for(int i=0;i<number_cylinder_request_sequence;i++)
			real_seek_sequence[i]=rq_sequence[i];		//fill real seek sequence
		
		index=number_cylinder_request_sequence;
		
		
	}
	
	void scan()
	{
		System.out.println("						SCAN");
		int flag[]=new int[total_cylinders];
		
		
		for(int i=0;i<total_cylinders;i++)
			flag[i]=-1;				//set as -1 for the disks which are not in sequence
		
		flag[head_positon]=2;		//insert head
		flag[0]=2;					//insert 0
		
		for(int i=0;i<number_cylinder_request_sequence;i++)
			flag[rq_sequence[i]]=0;		//insert sequence in flag
		
		int j=0;
		
		
		for(int i=head_positon;i>=0;i--)	//process in left direction from head position
		{
			
			if(flag[i]!=-1)
			{
				//System.out.println(i);
				if(flag[i]==0)			//to ignore head and 0 position 
				{
					real_seek_sequence[j]=i;
					j++;
				}
				seek_sequence[index]=i;
				flag[i]=-1;
				index++;
			}				
		}
		
		for(int i=0;i<total_cylinders;i++)		//traverse right from 0
		{
			
			if(flag[i]!=-1)
			{
				//System.out.println(i);
				if(flag[i]==0)
				{
					real_seek_sequence[j]=i;
					j++;
				}
				seek_sequence[index]=i;
				flag[i]=-1;
				index++;
			}				
		}
		index--;
		
	}
	
	void csan()
	{
		System.out.println("					CIRCULAR-SCAN(C-SCAN)");
		int flag[]=new int[total_cylinders];
		for(int i=0;i<total_cylinders;i++)	//set as -1 for the disks which are not in sequence
			flag[i]=-1;
		
		flag[head_positon]=2;	
		flag[0]=2;				//insert first
		flag[total_cylinders-1]=2;		//insert last
		
		for(int i=0;i<number_cylinder_request_sequence;i++)
			flag[rq_sequence[i]]=0;		//insert sequence in flag
		
		int j=0;
		
		for(int i=head_positon;i>=0;i--)	//process in left direction from head position
		{
			
			if(flag[i]!=-1)
			{
				//System.out.println(i);
				if(flag[i]==0)		//to ignore head, last and 0 position
				{
					real_seek_sequence[j]=i;
					j++;
				}
				seek_sequence[index]=i;
				flag[i]=-1;
				index++;
			}				
		}
		
		for(int i=(total_cylinders-1);i>0;i--)	//process in left direction from last position
		{
			
			if(flag[i]!=-1)
			{
				//System.out.println(i);
				if(flag[i]==0)	//to ignore head, last and 0 position
				{
					real_seek_sequence[j]=i;
					j++;
				}
				seek_sequence[index]=i;
				flag[i]=-1;
				index++;
			}				
		}
		index--;
		
	}
	
	void shortest_seek_time_first()
	{
		System.out.println("					SHORTEST SEEK TIME FIRST(SSTF)");
		int flag[]=new int[total_cylinders];
		for(int i=0;i<total_cylinders;i++)		//set as -1 for the disks which are not in sequence
			flag[i]=-1;
		
		flag[head_positon]=2;	//insert head
		
		for(int i=0;i<number_cylinder_request_sequence;i++)
			flag[rq_sequence[i]]=0;		//insert sequence in flag
		
		int next=head_positon;
		int j=0;
		while(next!=-1)		//loop till nearest neighbor is found
		{
			//System.out.println(next);
			seek_sequence[index]=next;
			index++;
			if(flag[next]==0)
			{
				real_seek_sequence[j]=next;
				j++;
			}
			int left=next-1;
			int right=next+1;
			
			while((left>0&&left<total_cylinders) && (flag[left]==-1) )//get left neighbor
			{
				left--;
				if(left==0&&flag[left]==-1)
				{
					left=100000;
					break;
				}
				else if(left==0&&flag[left]!=-1)
				{
					left=0;
					break;
				}
			}
			while((flag[right]==-1) && (right>0&&right<total_cylinders))//get right neighbor
			{
				right++;
				if(right==199&&flag[right]==-1)
				{
					right=100000;
					break;
				}
				else if(left==199&&flag[left]!=-1)
				{
					left=199;
					break;
				}
			}
			
			//get the nearest neighbor
			if(Math.abs(left-next)>Math.abs(right-next))	//update value
			{
				flag[next]=-1;		//mark it as processed
				next=right;
			}
			else if(Math.abs(left-next)<Math.abs(right-next))
			{
				flag[next]=-1;		//mark it as processed
				next=left;
			}
			else			//if all elements are processed
				next=-1;
			
		}
		index--;
		
	}
	
	void display_output()
	{
		//seek sequence calculations
		System.out.println();
		System.out.println();
		System.out.println("	Seek Sequence Calculations");
		System.out.println();
		for(int i=1;i<=index;i++)
		{
			seek_time=seek_time+Math.abs(seek_sequence[i]-seek_sequence[i-1]);
			if(seek_sequence[i]>seek_sequence[i-1])
				System.out.println("		Head movement:  "+seek_sequence[i]+"-"+seek_sequence[i-1]+"  = "+Math.abs(seek_sequence[i]-seek_sequence[i-1]));
			else
				System.out.println("		Head movement:  "+seek_sequence[i-1]+"-"+seek_sequence[i]+"  = "+Math.abs(seek_sequence[i-1]-seek_sequence[i]));
		}
		System.out.println();
		System.out.println();
		
		//seek sequence
		System.out.print("	Seek Sequence : ");
		for(int i=0;i<number_cylinder_request_sequence;i++)
			System.out.print(real_seek_sequence[i]+", ");
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		//total seek time
		System.out.println("	Total Seek Time = "+seek_time);
		System.out.println();
		clear();
	}
	
	void accept_details()
	{
		System.out.print("	Enter the number of total cylinders : ");
		total_cylinders=input.nextInt();
		System.out.print("	Enter the initial head position : ");
		head_positon=input.nextInt();
		System.out.print("	Enter the total nummber of cylinder in request sequence : ");
		number_cylinder_request_sequence=input.nextInt();
		System.out.print("	Enter the request sequence : ");
		request_sequence=input.next();
		
		int index=0;
		int number=0;
		int ptr=0;
		char comma=44;
		
		//break the string into array for further processing
		while(index<number_cylinder_request_sequence&&ptr<request_sequence.length())
		{
			
			if(request_sequence.charAt(ptr)==comma)
			{
				rq_sequence[index]=number;
				index++;
				number =0;
			}
			else
			{
				number=number*10+(request_sequence.charAt(ptr)-48);				
			}
			ptr++;
		}
		
	}
	
	
	public static void main(String args[])
	{
		disk_scheduling call=new disk_scheduling();		// object to call methods
		call.accept_details();			//get details
		
		int choice;
		do 
		{
			
			System.out.println("------------------------------------------");
			//display menu
			System.out.println("ENTER YOUR CHOICE : ");
			System.out.println("1. FIRST COME FIRST SERVE(FCFS)");
			System.out.println("2. SCAN");
			System.out.println("3. CIRCULAR-SCAN(C-SCAN)");
			System.out.println("4. SHORTEST SEEK TIME FIRST(SSTF)");
			System.out.println("0. EXIT");
			System.out.print("enter : ");
			choice=input.nextInt();
			//call function based on choice 
			switch(choice)
			{
				case 1:
					//fcfs
					call.first_come_first_serve();
					call.display_output();
					break;
					
				case 2:
					//scan
					call.scan();
					call.display_output();
					break;
					
				case 3:
					//c-scan
					call.csan();
					call.display_output();
					break;
				
				case 4:
					//sstf
					call.shortest_seek_time_first();
					call.display_output();
					break;
					
				case 0:
					//exit
					System.out.println("\n	THANK YOU");
					break;
					
				default:
					System.out.println("Please enter valid choice!");
						break;
			}
			System.out.println();
		}while(choice!=0);
	}

}


//  OUTPUT

/*
 * 	Enter the number of total cylinders : 200
	Enter the initial head position : 53
	Enter the total number of cylinder in request sequence : 8
	Enter the request sequence : 98,183,37,122,14,124,65,67,
------------------------------------------
ENTER YOUR CHOICE : 
1. FIRST COME FIRST SERVE(FCFS)
2. SCAN
3. CIRCULAR-SCAN(C-SCAN)
4. SHORTEST SEEK TIME FIRST(SSTF)
0. EXIT
enter : 1
			FIRST COME FIRST SERVE(FCFS)


	Seek Sequence Calculations

		Head movement:  98-53  = 45
		Head movement:  183-98  = 85
		Head movement:  183-37  = 146
		Head movement:  122-37  = 85
		Head movement:  122-14  = 108
		Head movement:  124-14  = 110
		Head movement:  124-65  = 59
		Head movement:  67-65  = 2


	Seek Sequence : 98, 183, 37, 122, 14, 124, 65, 67, 


	Total Seek Time = 640


------------------------------------------
ENTER YOUR CHOICE : 
1. FIRST COME FIRST SERVE(FCFS)
2. SCAN
3. CIRCULAR-SCAN(C-SCAN)
4. SHORTEST SEEK TIME FIRST(SSTF)
0. EXIT
enter : 2
						SCAN


	Seek Sequence Calculations

		Head movement:  53-37  = 16
		Head movement:  37-14  = 23
		Head movement:  14-0  = 14
		Head movement:  65-0  = 65
		Head movement:  67-65  = 2
		Head movement:  98-67  = 31
		Head movement:  122-98  = 24
		Head movement:  124-122  = 2
		Head movement:  183-124  = 59


	Seek Sequence : 37, 14, 65, 67, 98, 122, 124, 183, 


	Total Seek Time = 236


------------------------------------------
ENTER YOUR CHOICE : 
1. FIRST COME FIRST SERVE(FCFS)
2. SCAN
3. CIRCULAR-SCAN(C-SCAN)
4. SHORTEST SEEK TIME FIRST(SSTF)
0. EXIT
enter : 3
					CIRCULAR-SCAN(C-SCAN)


	Seek Sequence Calculations

		Head movement:  53-37  = 16
		Head movement:  37-14  = 23
		Head movement:  14-0  = 14
		Head movement:  199-0  = 199
		Head movement:  199-183  = 16
		Head movement:  183-124  = 59
		Head movement:  124-122  = 2
		Head movement:  122-98  = 24
		Head movement:  98-67  = 31
		Head movement:  67-65  = 2


	Seek Sequence : 37, 14, 183, 124, 122, 98, 67, 65, 


	Total Seek Time = 386


------------------------------------------
ENTER YOUR CHOICE : 
1. FIRST COME FIRST SERVE(FCFS)
2. SCAN
3. CIRCULAR-SCAN(C-SCAN)
4. SHORTEST SEEK TIME FIRST(SSTF)
0. EXIT
enter : 4
					SHORTEST SEEK TIME FIRST(SSTF)


	Seek Sequence Calculations

		Head movement:  65-53  = 12
		Head movement:  67-65  = 2
		Head movement:  67-37  = 30
		Head movement:  37-14  = 23
		Head movement:  98-14  = 84
		Head movement:  122-98  = 24
		Head movement:  124-122  = 2
		Head movement:  183-124  = 59


	Seek Sequence : 65, 67, 37, 14, 98, 122, 124, 183, 


	Total Seek Time = 236


------------------------------------------
ENTER YOUR CHOICE : 
1. FIRST COME FIRST SERVE(FCFS)
2. SCAN
3. CIRCULAR-SCAN(C-SCAN)
4. SHORTEST SEEK TIME FIRST(SSTF)
0. EXIT
enter : 0

	THANK YOU

*/
