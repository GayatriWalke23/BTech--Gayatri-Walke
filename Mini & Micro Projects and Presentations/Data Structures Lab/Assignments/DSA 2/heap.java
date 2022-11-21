// PERFORM OPERATION ON MAX HEAP	(ROLL NO. - 2466)

package dsa_temp;

import java.util.Scanner;

public class heap 
{
	int heap_[]= new int[100];	//array for heap 
	int number=0;				//size of heap
	
	static Scanner input =new Scanner(System.in); //for input
	
	public void build_heap()		
	{
		int choice=1;
		System.out.print("		To add element press 1 else press 0 : ");
		choice=input.nextInt();
		while(choice==1)
		{
			System.out.print("		Enter the element : ");		//accept element from user
			number++;
			heap_[number]=input.nextInt();
			System.out.print("		To add element press 1 else press 0 : ");
			choice=input.nextInt();
		}
		//display_heap();
		int i=number/2;
		while(i>=1)
		{
			down_adjustment(i);		//down adjust all the elements from middle of heap
			i--;
		}
		
	}
	
	void display_heap()
	{
		System.out.println();
		if(number==0)
			System.out.println("   HEAP IS EMPTY!");	//for empty heap
		else
		{
			System.out.print("   HEAP IS : ");
			for(int i=1;i<=number;i++)
				System.out.print(heap_[i]+" ");		//print elements
		}
	}
	
	
	public void up_adjustment(int temp)
	{
		int parent=temp;
		while(parent>1)		//till root is processed
		{
			 parent =temp/2;
			if(heap_[parent]<heap_[temp])
			{
				System.out.println("swap");		//exchange if the parent is smaller than child
				int t =heap_[parent];
				heap_[parent]=heap_[temp];
				heap_[temp]=t;
			}
			temp=temp/2;
		}
	}
	
	
	public void down_adjustment(int temp)
	{
		//System.out.println("downadj:"+heap_[temp]);
		int largest;
		int left = 2*temp;			//find left and right child of the element
		int right = 2*temp + 1;
		
		if(left<=number&&heap_[left]>heap_[temp])	//find the largest 
			largest=left;
		else
			largest=temp;
		if(right<=number&&heap_[right]>heap_[largest])
			largest=right;		
		if(largest!=temp)
		{
			//System.out.println("swap");
			int t=heap_[temp];			//exchange if the child is greater than parnt
			heap_[temp]=heap_[largest];
			heap_[largest]=t;
			down_adjustment(largest);
		}
	
	}
	
	void insert_element()
	{
		System.out.print("		Enter the element : ");		//accept element
		number++;
		heap_[number]=input.nextInt();	//add to heap 
		up_adjustment(number);		//do up adjustment on the inserted element
		
	}
	
	void delete_element()
	{
		if(number==0)
			System.out.println("Sorry the heap is empty!");//for empty heap
		else
		{
			System.out.println("deleted element : "+heap_[1]);
			heap_[1]=heap_[number];		//shift last element of heap to first position
			number--;
			down_adjustment(1);		//down adjust first element
			
		}
		
	}
	
	void delete_heap()
	{
		number=0;	//initialize index to 0
	}
	
	void sort_heap()
	{
		if(number==0)
			System.out.println(" HEAP IS EMPTY ");
		else
		{
			int sort_index=number;
			while(number>0)			//repeat for all numbers
			{
				int t=heap_[number];	//exchange first and last element 
				heap_[number]=heap_[1];
				heap_[1]=t;
				number--;
				down_adjustment(1);		// down adjust first element
			}
			number=sort_index;			
		}
		
	}
	
	
	public static void main(String args[])
	{
		
		int choice;
		heap call= new heap();
		do 
		{
			
			System.out.println("-------------------------------");
			//display menu
			System.out.println("ENTER YOUR CHOICE : ");
			System.out.println("1. BUILD HEAP");
			System.out.println("2. INSERT AN ELEMENT IN HEAP");
			System.out.println("3. DELETE AN ELEMENT FROM HEAP");
			System.out.println("4. SORT HEAP");
			System.out.println("5. DELETE HEAP");
			System.out.println("0. EXIT");
			System.out.print("enter : ");
			choice=input.nextInt();
			//call function based on choice 
			switch(choice)
			{
				case 1:
					//build heap
					call.build_heap();
					call.display_heap();
					break;
					
				case 2:
					//insert element
					call.insert_element();
					call.display_heap();
					break;
					
				case 3:
					//delete element
					call.delete_element();
					call.display_heap();
					break;
				
				case 4:
					//sort heap
					call.sort_heap();
					call.display_heap();
					break;
					
				case 5:
					//delete heap
					call.delete_heap();
					call.display_heap();
					break;
				
				case 0:
					System.out.println("THANK YOU");
					break;
					
				default:
					System.out.println("enter valid choice!");
						break;
			}
			System.out.println();
		}while(choice!=0);
	}

}

//  			OUTPUT
/*-------------------------------
ENTER YOUR CHOICE : 
1. BUILD HEAP
2. INSERT AN ELEMENT IN HEAP
3. DELETE AN ELEMENT FROM HEAP
4. SORT HEAP
5. DELETE HEAP
0. EXIT
enter : 1
		To add element press 1 else press 0 : 1
		Enter the element : 10
		To add element press 1 else press 0 : 1
		Enter the element : 12
		To add element press 1 else press 0 : 1
		Enter the element : 1
		To add element press 1 else press 0 : 1
		Enter the element : 14
		To add element press 1 else press 0 : 1
		Enter the element : 6
		To add element press 1 else press 0 : 1
		Enter the element : 5
		To add element press 1 else press 0 : 1
		Enter the element : 8
		To add element press 1 else press 0 : 1
		Enter the element : 15
		To add element press 1 else press 0 : 0

   HEAP IS : 15 14 8 12 6 5 1 10 
-------------------------------
ENTER YOUR CHOICE : 
1. BUILD HEAP
2. INSERT AN ELEMENT IN HEAP
3. DELETE AN ELEMENT FROM HEAP
4. SORT HEAP
5. DELETE HEAP
0. EXIT
enter : 2
		Enter the element : 3

   HEAP IS : 15 14 8 12 6 5 1 10 3 
-------------------------------
ENTER YOUR CHOICE : 
1. BUILD HEAP
2. INSERT AN ELEMENT IN HEAP
3. DELETE AN ELEMENT FROM HEAP
4. SORT HEAP
5. DELETE HEAP
0. EXIT
enter : 2
		Enter the element : 9
swap

   HEAP IS : 15 14 8 12 9 5 1 10 3 6 
-------------------------------
ENTER YOUR CHOICE : 
1. BUILD HEAP
2. INSERT AN ELEMENT IN HEAP
3. DELETE AN ELEMENT FROM HEAP
4. SORT HEAP
5. DELETE HEAP
0. EXIT
enter : 3
deleted element : 15

   HEAP IS : 14 12 8 10 9 5 1 6 3 
-------------------------------
ENTER YOUR CHOICE : 
1. BUILD HEAP
2. INSERT AN ELEMENT IN HEAP
3. DELETE AN ELEMENT FROM HEAP
4. SORT HEAP
5. DELETE HEAP
0. EXIT
enter : 4

   HEAP IS : 1 3 5 6 8 9 10 12 14 
-------------------------------
ENTER YOUR CHOICE : 
1. BUILD HEAP
2. INSERT AN ELEMENT IN HEAP
3. DELETE AN ELEMENT FROM HEAP
4. SORT HEAP
5. DELETE HEAP
0. EXIT
enter : 5

   HEAP IS EMPTY!

-------------------------------
ENTER YOUR CHOICE : 
1. BUILD HEAP
2. INSERT AN ELEMENT IN HEAP
3. DELETE AN ELEMENT FROM HEAP
4. SORT HEAP
5. DELETE HEAP
0. EXIT
enter : 0
THANK YOU

*/
