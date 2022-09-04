//GAYATRI WALKE (2466)
package dsa_temp;

import java.util.Scanner;




//node class and its attributes
class node
{
	node left_link;
	node right_link;
	String word;
	String meaning;
	
	node() //constructor
	{
		left_link=null;
		right_link=null;
		word=null;
		meaning=null;
	}
	node(String w,String m)
	{
		word=w;
		meaning=m;
	}
	
}

//dictionary class
class dictionary
{
	static Scanner input = new Scanner(System.in);
	
	static Scanner input_ = new Scanner(System.in);
	private static node root ;
	String w,m;
	node ptr = new node();//temporary node
	
	
	public void create() // create tree
  {
	   
	   System.out.print("ENTER WORD : ");//accepting  node
	   w = input_.next();
	   System.out.print("ENTER MEANING : ");	   
	   m = input_.next();	   
	   node temp = new node(w,m);
	   int flag = 0;//1 means node added
	
	   if(root==null)   //for root node
			root=temp;
	   else// node except root
	   {
			ptr = root;			
			while(flag==0)
			{
				
				if(temp.word.compareToIgnoreCase(ptr.word)<0)//left side
				{
					if(ptr.left_link!=null)
						ptr=ptr.left_link; //traverse left
					else
					{
						ptr.left_link=temp;
						flag=1;
					}			
					
				}
				
				else if(temp.word.compareToIgnoreCase(ptr.word)>=0)
				{
				if(ptr.right_link!=null)
					ptr=ptr.right_link; //traverse right
				else
					{
					ptr.right_link=temp;
					flag=1;
					}
				}
				
				
			}
			
		 }
	}
	
	public void search()
	{
		  System.out.print("enter word to be searched : ");
		  w = input_.next();
		  int flag = 0;
		  int found = 0;
		  ptr = root;	
		  if(ptr==null)   //for root node
				System.out.println("Sorry, empty dictionary !");
		   else// node except root
		   {
						
				while(flag==0)
				{
					if(w.compareToIgnoreCase(ptr.word)==0)
					{
						flag=1;
						found=1;//set found flag
					}
					
					else if(w.compareToIgnoreCase(ptr.word)<0)
					{
						if(ptr.left_link!=null)
							ptr=ptr.left_link; //traverse left
						else
							{
								System.out.println("NOT PRESENT !");
								flag=1;
							}
						
					}
					
					else if(w.compareToIgnoreCase(ptr.word)>0)
					{
					if(ptr.right_link!=null)
						ptr=ptr.right_link; //traverse right
						else
						{
							System.out.println("NOT PRESENT !");
							flag=1;
						}
					}
					
					
				}
				
			 }
		  if(found==1)
		  {
			  System.out.println("found !!");
			  System.out.println(ptr.word + " : "+ptr.meaning);//display
			  
		  }
	}
	
	public void update()
	{
		 System.out.print("enter word to be updated : ");
		  w = input_.next();
		  int flag = 0;
		  int found = 0;
		  ptr = root;	
		  if(ptr==null)   //for root node
				System.out.println("Sorry, empty dictionary !");
		   else// node except root
		   {
						
				while(flag==0)
				{
					if(w.compareToIgnoreCase(ptr.word)==0)
					{
						flag=1;
						found=1;
					}
					
					else if(w.compareToIgnoreCase(ptr.word)<0)//left side
					{
						if(ptr.left_link!=null)
							ptr=ptr.left_link; //traverse left
						else
							{
								System.out.println("NOT PRESENT !");
								flag=1;
							}
						
					}
					
					else if(w.compareToIgnoreCase(ptr.word)>0)
					{
					if(ptr.right_link!=null)
						ptr=ptr.right_link; //traverse right
						else
						{
							System.out.println("NOT PRESENT !");
							flag=1;
						}
					}
					
					
				}
				
			 }
		  if(found==1)
		  {
			  System.out.println("found !!");
			  System.out.print("ENTER THE UPDATE : ");//accept update
			  ptr.meaning=input_.next();
			  System.out.println("Updated ! - "+ptr.word + " : "+ptr.meaning);
			  
		  }
		 
	}
	
	public void display(node local_root)
	{
		if(local_root!=null)
		{
			display(local_root.left_link); //traverse left
			System.out.println(local_root.word + " : "+local_root.meaning); //print root
			display(local_root.right_link); //traverse right 
		}
		
	}
	
	
	public void delete()
	{
		System.out.print("enter word to be deleted : ");
		  w = input_.next();
		 
		  int flag = 0;
		  int found = 0;
		  ptr = root;
		  node parent = new node();
		  node temp = new node();
		  if(ptr==null)   //for root node
				System.out.println("Sorry, empty dictionary !");
		   else// node except root
		   {
						
				while(flag==0)
				{
					if(w.compareToIgnoreCase(ptr.word)==0)
					{
						
						flag=1;
						found=1;
					}
					
					else if(w.compareToIgnoreCase(ptr.word)<0)//left side
					{
						if(ptr.left_link!=null)
							{
								parent=ptr;
							 	ptr=ptr.left_link; //traverse left
							}
						else
							{
								System.out.println("NOT PRESENT !");
								flag=1;
							}
						
					}
					
					else if(w.compareToIgnoreCase(ptr.word)>0)
					{
					if(ptr.right_link!=null)
						{
							parent=ptr;
							ptr=ptr.right_link; //traverse right
							
						}
						else
						{
							System.out.println("NOT PRESENT !");
							flag=1;
						}
					}
					
					
				}
				
			 }
		  if(found==1)
		  {
			  System.out.println("Deleted !!");
			  if(ptr.left_link!=null && ptr.right_link!=null)//for 2 children
			  {
				  System.out.println("2 children");
				  System.out.println("ptr-"+ptr.word);
				  temp=ptr.left_link;
				  System.out.println("temp-"+temp.word);
				  node parent_temp=ptr;
				  while(temp.right_link!=null)
				  {
					  parent_temp=temp;
					  temp=temp.right_link;
					  System.out.print("temp-"+temp.word);
					  System.out.println("parent_temp-"+parent_temp.word);
				  }
				  
				  ptr.word=temp.word;
				  ptr.meaning=temp.meaning;
				  System.out.println("ptr-"+ptr.word);
				  
				  if(parent_temp!=ptr) 
					  parent_temp.right_link=temp.left_link;
				  else if(parent_temp==ptr)
					  parent_temp.left_link=temp.left_link;
				  
				  temp=null;			  
					  
				 
			  }
			  else if((ptr.left_link != null && ptr.right_link==null)||(ptr.left_link == null && ptr.right_link!=null))//for one child
			  {
				  	
				    if(parent.left_link==ptr)//if ptr is left child of parent
					 {
				    	if(ptr.right_link!=null)//if ptr has right child only
					  	{
				    		parent.left_link=ptr.right_link;
				    		ptr=null;
					  		
					  	}
				    	else if(ptr.left_link!=null)//if ptr has left child only
					  	{
				    		parent.left_link=ptr.left_link;
				    		ptr=null;
					  		
					  	}
						
					 }
				    else if(parent.right_link==ptr)//if ptr is right child of parent
					 {
				    	if(ptr.right_link!=null)//if ptr has right child only
					  	{
				    		parent.right_link=ptr.right_link;
				    		ptr=null;
					  		
					  	}
				    	else if(ptr.left_link!=null)//if ptr has left child only
					  	{
				    		parent.right_link=ptr.left_link;
				    		ptr=null;
					  		
					  	}
						 
					 }
			  }
			  else if(ptr.left_link==null && ptr.right_link==null)//for no child
			  {
				  	 if(parent.left_link==ptr)//if ptr is left child of parent
					 {
						 parent.left_link=null;
						 ptr=null;
					 }
				  	 else if(parent.right_link==ptr)//if ptr is right child of parent
					 {
						 parent.right_link=null;
						 ptr=null;
					 }
			  }
			  
		  }
		
	}
	
	
	
	
	
	
	public static void main(String args[])
	{
		dictionary tree = new dictionary();
		int choice;
		do 
		{
			System.out.println();
			System.out.println();
			//display menu
			System.out.println("ENTER YOUR CHOICE : ");
			System.out.println("1. ADD WORD");
			System.out.println("2. DELETE WORD");
			System.out.println("3. DISPLAY DICTIONARY");
			System.out.println("4. SEARCH WORD");
			System.out.println("5. UPDATE MEANING OF WORD");
			System.out.println("0. EXIT");
			System.out.print("enter : ");
			choice=input.nextInt();
			//call function based on choice 
			switch(choice)
			{
				case 1:
					//add node
					tree.create();
					break;
					
				case 2:
					//delete node
					tree.delete();
					break;
					
				case 3:
					//display
					tree.display(root);
					System.out.println();
					break;
					
				case 4:
					//search
					tree.search();
					break;
					
				case 5:
					//update
					tree.update();
					break;
					
				case 0:
					//exit
					System.out.println("thank you!!!");
					break;
					
				default:
					System.out.println("enter valid choice!");
						break;
			}
		}while(choice!=0);
		
	}

}


// 			OUTPUT
/*ENTER YOUR CHOICE : 
1. ADD WORD
2. DELETE WORD
3. DISPLAY DICTIONARY
4. SEARCH WORD
5. UPDATE MEANING OF WORD
0. EXIT
enter : 1
ENTER WORD : mango
ENTER MEANING : fruit


ENTER YOUR CHOICE : 
1. ADD WORD
2. DELETE WORD
3. DISPLAY DICTIONARY
4. SEARCH WORD
5. UPDATE MEANING OF WORD
0. EXIT
enter : 1
ENTER WORD : banana
ENTER MEANING : fruit


ENTER YOUR CHOICE : 
1. ADD WORD
2. DELETE WORD
3. DISPLAY DICTIONARY
4. SEARCH WORD
5. UPDATE MEANING OF WORD
0. EXIT
enter : 1
ENTER WORD : watermellon
ENTER MEANING : fruit


ENTER YOUR CHOICE : 
1. ADD WORD
2. DELETE WORD
3. DISPLAY DICTIONARY
4. SEARCH WORD
5. UPDATE MEANING OF WORD
0. EXIT
enter : 1
ENTER WORD : apple
ENTER MEANING : fruit


ENTER YOUR CHOICE : 
1. ADD WORD
2. DELETE WORD
3. DISPLAY DICTIONARY
4. SEARCH WORD
5. UPDATE MEANING OF WORD
0. EXIT
enter : 1
ENTER WORD : orange
ENTER MEANING : fruit


ENTER YOUR CHOICE : 
1. ADD WORD
2. DELETE WORD
3. DISPLAY DICTIONARY
4. SEARCH WORD
5. UPDATE MEANING OF WORD
0. EXIT
enter : 3
apple : fruit
banana : fruit
mango : fruit
orange : fruit
watermellon : fruit



ENTER YOUR CHOICE : 
1. ADD WORD
2. DELETE WORD
3. DISPLAY DICTIONARY
4. SEARCH WORD
5. UPDATE MEANING OF WORD
0. EXIT
enter : 2
enter word to be deleted : banana
Deleted !!


ENTER YOUR CHOICE : 
1. ADD WORD
2. DELETE WORD
3. DISPLAY DICTIONARY
4. SEARCH WORD
5. UPDATE MEANING OF WORD
0. EXIT
enter : 3
apple : fruit
mango : fruit
orange : fruit
watermellon : fruit



ENTER YOUR CHOICE : 
1. ADD WORD
2. DELETE WORD
3. DISPLAY DICTIONARY
4. SEARCH WORD
5. UPDATE MEANING OF WORD
0. EXIT
enter : 3
apple : fruit
mango : fruit
orange : fruit
watermellon : fruit

ENTER YOUR CHOICE : 
1. ADD WORD
2. DELETE WORD
3. DISPLAY DICTIONARY
4. SEARCH WORD
5. UPDATE MEANING OF WORD
0. EXIT
enter : 4
enter word to be searched : mango
found !!
mango : fruit


ENTER YOUR CHOICE : 
1. ADD WORD
2. DELETE WORD
3. DISPLAY DICTIONARY
4. SEARCH WORD
5. UPDATE MEANING OF WORD
0. EXIT
enter : 4
enter word to be searched : pear
NOT PRESENT !



ENTER YOUR CHOICE : 
1. ADD WORD
2. DELETE WORD
3. DISPLAY DICTIONARY
4. SEARCH WORD
5. UPDATE MEANING OF WORD
0. EXIT
enter : 5
enter word to be updated : mango
found !!
ENTER THE UPDATE : new
Updated ! - mango : new


ENTER YOUR CHOICE : 
1. ADD WORD
2. DELETE WORD
3. DISPLAY DICTIONARY
4. SEARCH WORD
5. UPDATE MEANING OF WORD
0. EXIT
enter : 0
thank you!!!

*/