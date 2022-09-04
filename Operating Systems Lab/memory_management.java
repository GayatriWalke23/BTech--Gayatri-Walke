//Gayatri Walke (Roll no. - 2466) 
package dsa_temp;

import java.util.Scanner;

class process
{
	//process class and all its attributes
	Scanner input = new Scanner(System.in);
	
	int size; //pocess/block size
	
	int number_process;   //total number of processes
	int number_block;	//total number of blocks
	int allocated_block[] = new int[100]; //flag for block allocation
	int allocated_process[] = new int[100];		//flag for process allocation
	int min_diff[]= new int[100];	//for storing difference in size of block and process
	process p[]=new process[100];//for process
	process b[]=new process[100];//for block
	
	process() //constructor
	{
		number_process=0;
		number_block=0;
	}
	
	
	void accept_block_details() //to accept process number and their size 
	{
		System.out.print("Enter the number of blocks : ");
		number_block=input.nextInt();
		
		System.out.println("enter : ");
		
		for(int i=0; i < number_block ;i++)
			b[i]=new process();
		//accept details
		for(int i=0; i < number_block ;i++)
		{
			
			System.out.print("  For the "+(i+1)+" block");
			System.out.print(" enter the size : ");
			b[i].size=input.nextInt();
			System.out.println();
			
		}
		
	}
	
	void accept_process_details() //to accept process number and their size 
	{
		System.out.print("Enter the number of processes : ");
		number_process=input.nextInt();
		
		
		
		System.out.println("enter : ");
		//accept details
		for(int i=0; i < number_process ;i++)
			p[i]=new process();
		
		for(int i=0; i < number_process ;i++)
		{
			
			System.out.print("For the "+(i+1)+" process");
			System.out.print(" enter the size : ");
			p[i].size=input.nextInt();
			System.out.println();
			
		}
		
	}
	
	void calculation_first_fit()
	{
		
		for(int i=0; i < number_process ;i++)
			allocated_process[i]=new Integer(-1); 	//initialze with -1 for not allocated
		
		for(int i=0; i < number_block ;i++)
			allocated_block[i]=new Integer(0);	//initialze with 0 for not allocated
			
			
		for(int i=0;i<number_process;i++) //for each process
		{
			for(int j =0;j<number_block;j++) 
			{
				if(p[i].size<=b[j].size && allocated_block[j]==0) 	//check the block which can be allocated
				{
					//System.out.println(i+" allocated");
					allocated_process[i]=j;
					allocated_block[j]=1;
					break;
				}
			}
		}
		
		
	}
	
	void calculation__best_fit()
	{
	
		for(int i=0; i < number_process ;i++)
			allocated_process[i]=new Integer(-1); //initialze with -1 for not allocated
		
		for(int i=0; i < number_block ;i++)
			allocated_block[i]=new Integer(0);	//initialze with 0 for not allocated
		
		int min=100000; 
		int block=0; 
		int flag=0;
		
		
		for(int i=0;i<number_process;i++)  //for each process
		{
			flag=0;
			for(int j=0; j < number_block ;j++)
				min_diff[j]=new Integer(100000);
		
			for(int j =0;j<number_block;j++)
			{
				if(p[i].size<=b[j].size  &&  allocated_block[j]==0)
				{
					flag=1;
					min_diff[j]=b[j].size-p[i].size; //find the block which has minimum memory wastage
				}
			}
		
			if(flag==1)
			{
				min=100000;
				for(int j =0;j<number_block;j++)
				{
					if(min>min_diff[j])
					{
						block=j;
						min=min_diff[j];
					}
				}
				//System.out.println("for "+(i+1)+"block "+(block+1));
				allocated_process[i]=block;		//allocate
				allocated_block[block]=1;
				
				
			}
		}
		
		
	}
	void calculation_worst_fit()
	{
		for(int i=0; i < number_process ;i++)
			allocated_process[i]=new Integer(-1);	//initialze with -1 for not allocated
		
		for(int i=0; i < number_block ;i++)
			allocated_block[i]=new Integer(0);		//initialze with 0 for not allocated
		
		int sorted_block[]= new int[100] ;		//for blocks with sorted memory
		int temp;
		
		for(int i=0; i < number_block ;i++)
			sorted_block[i]=new Integer(i);		//initialize with the block numbers
		
		for(int j=0; j<number_block-1;j++)//sort sorted_aray w.r.t size
		{
			for(int i=0; i<number_block-1-j;i++)
			{
				if(b[sorted_block[i]].size<b[sorted_block[i+1]].size)  //bubble sort
				{
					temp=sorted_block[i];
					sorted_block[i]=sorted_block[i+1];
					sorted_block[i+1]=temp;
				}
			}	
		}
		
		
		//for(int j=0; j<number_block-1;j++)
			//System.out.println(sorted_block[j]);
		
		
		
		int block=0;
		for(int i=0;i<number_process;i++)
		{
			for(int j =0;j<number_block;j++)
			{
				block=sorted_block[j];
				if(p[i].size<=b[block].size && allocated_block[block]==0) //allocate the block
				{
					//System.out.println(i+" allocated");
					allocated_process[i]=block;
					allocated_block[block]=1;
					break;
				}
			}
		}		
			
		
	}
	
	void display()
	{
		for(int i=0;i<number_process;i++)
		{
			if(allocated_process[i]!=(-1)) //display if allocated
				System.out.println("Process P"+(i+1)+" allocated to block B"+(allocated_process[i]+1)+" of size "+b[allocated_process[i]].size+"KB");
			else
				System.out.println("Process P"+(i+1)+" is not allocated to any block. ");
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
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
		
		//get details
		call.accept_block_details();
		call.accept_process_details();
		
		do
		{
			    //display menu
				
				System.out.println("      MENU   ");
				System.out.println("1. FIRST FIT");
				System.out.println("2. BEST FIT");
				System.out.println("3. WORST FIT");
				System.out.println("0. EXIT");
				System.out.print("enter your choice : ");
				
				choice = input.nextInt();
				System.out.println();
				switch(choice)
				{
					//call
					case 1:
						//first fit
						call.calculation_first_fit();
						call.display();
						break;
						
						
					case 2:
						//best fit
						call.calculation__best_fit();
						call.display();
						break;
						
					case 3:
						//worst fit
						call.calculation_worst_fit();
						call.display();
						break;
						
						
					case 0:
						//exit
						System.out.println();
						System.out.println("     THANK YOU");
						break;
						
						
					default:
						//default
						System.out.println("enter valid choice!");
						break;
				}
		
		}while(choice!=0);
		input.close();
	}

}

//			OUTPUT
/*Enter the number of blocks : 5
enter : 
  For the 1 block enter the size : 100

  For the 2 block enter the size : 500

  For the 3 block enter the size : 200

  For the 4 block enter the size : 300

  For the 5 block enter the size : 600

Enter the number of processes : 4
enter : 
For the 1 process enter the size : 212

For the 2 process enter the size : 417

For the 3 process enter the size : 112

For the 4 process enter the size : 426

      MENU   
1. FIRST FIT
2. BEST FIT
3. WORST FIT
0. EXIT
enter your choice : 1

Process P1 allocated to block B2 of size 500KB

Process P2 allocated to block B5 of size 600KB

Process P3 allocated to block B3 of size 200KB

Process P4 is not allocated to any block. 



      MENU   
1. FIRST FIT
2. BEST FIT
3. WORST FIT
0. EXIT
enter your choice : 2

Process P1 allocated to block B4 of size 300KB

Process P2 allocated to block B2 of size 500KB

Process P3 allocated to block B3 of size 200KB

Process P4 allocated to block B5 of size 600KB



      MENU   
1. FIRST FIT
2. BEST FIT
3. WORST FIT
0. EXIT
enter your choice : 3

Process P1 allocated to block B5 of size 600KB

Process P2 allocated to block B2 of size 500KB

Process P3 allocated to block B4 of size 300KB

Process P4 is not allocated to any block. 



      MENU   
1. FIRST FIT
2. BEST FIT
3. WORST FIT
0. EXIT
enter your choice : 4

enter valid choice!
      MENU   
1. FIRST FIT
2. BEST FIT
3. WORST FIT
0. EXIT
enter your choice : 0


     THANK YOU
*/
