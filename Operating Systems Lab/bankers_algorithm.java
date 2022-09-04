//				BANKER'S ALGORITHM
//GAYATRI WALKE (ROLL NO. 2466)
package dsa_temp;

import java.util.Scanner;

public class bankers_algorithm 
{
	int number_processes;
	int number_resources;
	
	static Scanner input =new Scanner(System.in); //for input
	
	int number_instances[]=new int[100];	//input
	
	int safe_sequence[]=new int[100];
	
	int maximum_matrix[][]=new int[100][100];
	int allocation_matrix[][]=new int[100][100];		
	int available_matrix[][]=new int[100][100];
	int need_matrix[][]=new int[100][100];
	
	int safe_sequence_flag;		//for safe sequence present or not
	
	void accept_details()
	{
		System.out.println("ENTER THE ");
		System.out.println();
		System.out.print("           Number of processes :");
		number_processes=input.nextInt();
		System.out.println();
		System.out.print("           Number of resources :");
		number_resources=input.nextInt();
		
		System.out.println();	
		
		//number of instances
		for(int i=0;i<number_resources;i++)
		{
			System.out.print("           	Total number of instances for resource "+(i+1)+" : ");
			number_instances[i]=input.nextInt();
			
		}
		
		//maximum matrix
		System.out.println();
		System.out.println("           Maximum matrix :");
		for(int j=0;j<number_processes;j++)
		{
			System.out.println("           	For Process P"+j);
			for(int i=0;i<number_resources;i++)
			{
				
				System.out.print("                         Number of instances for resource "+(i+1)+" : ");
				maximum_matrix[j][i]=input.nextInt();
			}
			System.out.println();
			
		}
		
		//allocation matrix
		System.out.println();
		System.out.println("           Allocation matrix :");
		for(int j=0;j<number_processes;j++)
		{
			System.out.println("           	For Process P"+j);
			for(int i=0;i<number_resources;i++)
			{
				System.out.print("                         Number of instances for resource "+(i+1)+" : ");
				allocation_matrix[j][i]=input.nextInt();
			}
			System.out.println();
			
		}
	}
	
	
	void calculation()
	{
		//calculate available matrix
		for(int j=0;j<number_resources;j++)
			available_matrix[0][j]=0;
		for(int j=0;j<number_resources;j++)
		{
			for(int k=0;k<number_processes;k++)
			{
				available_matrix[0][j]=available_matrix[0][j]+allocation_matrix[k][j];
			}
			available_matrix[0][j]=number_instances[j]-available_matrix[0][j];
			
		}
		
		//calculate need matrix
		for(int k=0;k<number_processes;k++)
		{
			for(int j=0;j<number_resources;j++)
			{
				need_matrix[k][j]=maximum_matrix[k][j]-allocation_matrix[k][j];
			}			
		}
		
		
		//calculate seek sequence
		int free_matrix[][]=new int[10][number_resources];
		int flag_taken_process[]=new int[number_processes];
		
		for(int j=0;j<number_resources;j++)
			free_matrix[0][j]=available_matrix[0][j];
		
		for(int k=0;k<number_processes;k++)
			flag_taken_process[k]=-1;					//for process not taken
		
		int number=0;
		safe_sequence_flag=0;
		int process=0;
		int number_times=0;
		while(safe_sequence_flag==0&&number_times<30)
		{
			//System.out.println("number_times"+number_times);
			number_times++;
			int flag=1;
			
			/*System.out.print("flag_taken_process ");
			for(int k=0;k<number_processes;k++)
				System.out.print(" "+flag_taken_process[k]);
			System.out.println();
			
			System.out.print("maximum_matrix ");
			for(int k=0;k<number_resources;k++)
				System.out.print(" "+maximum_matrix[process][k]);
			System.out.println();
			
			System.out.print("free_matrix ");
			for(int k=0;k<number_resources;k++)
				System.out.print(" "+free_matrix[0][k]);
			System.out.println();*/
			
			if(flag_taken_process[process]==-1)
			{
				//System.out.println(process);
				int j=0;
				for(j=0;j<number_resources;j++)
				{
					if(need_matrix[process][j]<=free_matrix[0][j])	// check need matrix
						flag=flag*1;
					else
						flag=flag*0;
				}
				
				if(flag==1)
				{
					//System.out.println(process+" taken");
					for(j=0;j<number_resources;j++)
						free_matrix[0][j]=free_matrix[0][j]+allocation_matrix[process][j];		//add it into free matrix
					safe_sequence[number++]=process;
					flag_taken_process[process]=1;		//set flag
					
				}
				
				safe_sequence_flag=1;
				for(int i=0;i<number_processes;i++)		//check all processes
				{
					if(flag_taken_process[i]==1)
						safe_sequence_flag=safe_sequence_flag*1;
					else
						safe_sequence_flag=safe_sequence_flag*0;
				}
			}
			process=(process+1)%number_processes;		//increment process number
			
			
		}
		
		if(number<number_processes)
			safe_sequence_flag=-1;
		else if(number==number_processes)	//check for all sequences
			safe_sequence_flag=1;	
	
	}
	
	
	
	void display()
	{
		
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.print("Process");
		for(int j=0;j<number_resources;j++)
			System.out.print("\t");
		System.out.print("Allocation");
		for(int j=0;j<number_resources-1;j++)
			System.out.print("\t");
		System.out.print("Max");
		for(int j=0;j<number_resources;j++)
			System.out.print("\t");							//layout
		System.out.print("Available");
		for(int j=0;j<number_resources-1;j++)
			System.out.print("\t");
		System.out.println("Need");
		//System.out.print(" 		");
		for(int j=0;j<number_resources;j++)
			System.out.print("\t");
		for(int k=0;k<4;k++)
		{
			for(int j=0;j<number_resources;j++)
				System.out.print("R"+(j+1)+"  ");
			for(int j=0;j<number_resources-1;j++)
				System.out.print("\t");
			
		}
		System.out.println();
		for(int k=0;k<number_processes;k++)
		{
			System.out.print("  P"+k);
			for(int j=0;j<number_resources;j++)
				System.out.print("\t");
			for(int j=0;j<number_resources;j++)
				System.out.print(" "+(allocation_matrix[k][j])+"  ");		//allocation
			for(int j=0;j<number_resources-1;j++)
				System.out.print("\t");
			for(int j=0;j<number_resources;j++)
				System.out.print(" "+(maximum_matrix[k][j])+"  ");			//maximum
			for(int j=0;j<number_resources-1;j++)
				System.out.print("\t");
			for(int j=0;j<number_resources;j++)
			{
				if(k==0)
					System.out.print(" "+(available_matrix[k][j])+"  ");		//available
				else
					System.out.print("   ");
			}
				
			for(int j=0;j<number_resources-1;j++)
				System.out.print("\t");
			for(int j=0;j<number_resources;j++)
				System.out.print(" "+(need_matrix[k][j])+"  ");				//need
			for(int j=0;j<number_resources-1;j++)
				System.out.print("\t");
			System.out.println();
			
		}
		System.out.println("-----------------------------------------------------------------------------------------------------");
		
		//safe sequence
		
		System.out.println();
		if(safe_sequence_flag==1)			//if safe sequence is present
		{
			System.out.print("   SAFE SEQUENCE : ");
			for(int j=0;j<number_processes;j++)
			{
				if(j!=number_processes-1)
					System.out.print("P"+safe_sequence[j]+"----->");		//print
				else
					System.out.println("P"+safe_sequence[j]);
			}
		}
		else
			System.out.println("Safe sequence not present!");		//if safe sequence is not present
		
	
	
	}
	
	
	
	
	public static void main(String args[])
	{
		bankers_algorithm call = new bankers_algorithm();
		
		System.out.println("----------------------BANKER'S ALGORITHM-------------------");
		System.out.println();
		call.accept_details();
		call.calculation();				//call functions
		call.display();
		
	}

}

//				OUTPUT
/*----------------------BANKER'S ALGORITHM-------------------

ENTER THE 

           Number of processes :5

           Number of resources :3

           	Total number of instances for resource 1 : 10
           	Total number of instances for resource 2 : 5
           	Total number of instances for resource 3 : 7

           Maximum matrix :
           	For Process P0
                         Number of instances for resource 1 : 7
                         Number of instances for resource 2 : 5
                         Number of instances for resource 3 : 3

           	For Process P1
                         Number of instances for resource 1 : 3
                         Number of instances for resource 2 : 2
                         Number of instances for resource 3 : 2

           	For Process P2
                         Number of instances for resource 1 : 9
                         Number of instances for resource 2 : 0
                         Number of instances for resource 3 : 2

           	For Process P3
                         Number of instances for resource 1 : 2
                         Number of instances for resource 2 : 2
                         Number of instances for resource 3 : 2

           	For Process P4
                         Number of instances for resource 1 : 4
                         Number of instances for resource 2 : 3
                         Number of instances for resource 3 : 3


           Allocation matrix :
           	For Process P0
                         Number of instances for resource 1 : 0
                         Number of instances for resource 2 : 1
                         Number of instances for resource 3 : 0

           	For Process P1
                         Number of instances for resource 1 : 3
                         Number of instances for resource 2 : 0
                         Number of instances for resource 3 : 2

           	For Process P2
                         Number of instances for resource 1 : 3
                         Number of instances for resource 2 : 0
                         Number of instances for resource 3 : 2

           	For Process P3
                         Number of instances for resource 1 : 2
                         Number of instances for resource 2 : 1
                         Number of instances for resource 3 : 1

           	For Process P4
                         Number of instances for resource 1 : 0
                         Number of instances for resource 2 : 0
                         Number of instances for resource 3 : 2


-----------------------------------------------------------------------------------------------------
Process		 Allocation		      Max			Available		 Need
			 R1  R2  R3  		R1  R2  R3  	R1  R2  R3     R1  R2  R3  		
  P0		 0   1   0  		 7   5   3       2   3   0     7   4   3  		
  P1		 3   0   2  		 3   2   2  		           0   2   0  		
  P2		 3   0   2  		 9   0   2  		           6   0   0  		
  P3		 2   1   1  		 2   2   2  		           0   1   1  		
  P4		 0   0   2  		 4   3   3  		           4   3   1  		
-----------------------------------------------------------------------------------------------------

   SAFE SEQUENCE : P1----->P3----->P4----->P0----->P2
*/


//			OUTPUT

/*----------------------BANKER'S ALGORITHM-------------------

ENTER THE 

           Number of processes :5

           Number of resources :3

           	Total number of instances for resource 1 : 10
           	Total number of instances for resource 2 : 5
           	Total number of instances for resource 3 : 7

           Maximum matrix :
           	For Process P0
                         Number of instances for resource 1 : 7
                         Number of instances for resource 2 : 5
                         Number of instances for resource 3 : 3

           	For Process P1
                         Number of instances for resource 1 : 3
                         Number of instances for resource 2 : 2
                         Number of instances for resource 3 : 2

           	For Process P2
                         Number of instances for resource 1 : 9
                         Number of instances for resource 2 : 0
                         Number of instances for resource 3 : 2

           	For Process P3
                         Number of instances for resource 1 : 2
                         Number of instances for resource 2 : 2
                         Number of instances for resource 3 : 2

           	For Process P4
                         Number of instances for resource 1 : 4
                         Number of instances for resource 2 : 4
                         Number of instances for resource 3 : 3


           Allocation matrix :
           	For Process P0
                         Number of instances for resource 1 : 0
                         Number of instances for resource 2 : 1
                         Number of instances for resource 3 : 0

           	For Process P1
                         Number of instances for resource 1 : 2
                         Number of instances for resource 2 : 0
                         Number of instances for resource 3 : 0

           	For Process P2
                         Number of instances for resource 1 : 3
                         Number of instances for resource 2 : 0
                         Number of instances for resource 3 : 2

           	For Process P3
                         Number of instances for resource 1 : 2
                         Number of instances for resource 2 : 1
                         Number of instances for resource 3 : 1

           	For Process P4
                         Number of instances for resource 1 : 3
                         Number of instances for resource 2 : 3
                         Number of instances for resource 3 : 2


-----------------------------------------------------------------------------------------------------
Process		Allocation		   Max			   Available		   Need
			R1  R2  R3  	 R1  R2  R3  	  R1  R2  R3  		R1  R2  R3  		
  P0		0   1   0  		 7   5   3  	  0   0   2  		 7   4   3  		
  P1	    2   0   0  		 3   2   2  		         		 1   2   2  		
  P2	    3   0   2  		 9   0   2  		         		 6   0   0  		
  P3		2   1   1  		 2   2   2  		         		 0   1   1  		
  P4		3   3   2  		 4   4   3  		         	     1   1   1  		
-----------------------------------------------------------------------------------------------------

Safe sequence not present!
*/