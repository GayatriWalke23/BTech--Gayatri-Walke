// Gayatri Walke (Roll no. - 2466) 

package dsa_temp;
//importing 
import java.util.Scanner;
import java.util.Stack;

//node class and its attributes
class node
{
	node left_link;
	node right_link;
	int data;
	
	node() //constructor
	{
		left_link=null;
		right_link=null;
		data=0;
	}
	
}

public class binary_tree
{
	// binary tree class and its attributes
	static private node root = new node();
	node ptr = new node();//temporary node
	public binary_tree() //constructor  
	{
		root=null;
		ptr=null;
	}
	static Scanner input =new Scanner(System.in); //for input
	

	public void add_node()
	{
		
		node temp  = new node();
		System.out.print("enter the data value : ");//accept input
		temp.data=input.nextInt();
		System.out.println();
		int direction;
		int flag=0;
		
		if(root==null)   //for root node
			root=temp;
		else
		{
			ptr=root;
			while(flag==0)   //while all the nodes are not added
			{
			
			System.out.println("current node : "+ ptr.data);
			System.out.print("enter direction for the next node (1-left , 0-right) :  ");
			direction=input.nextInt();//take direction from user
			if(direction==1)  
			{
				if(ptr.left_link!=null)
					ptr=ptr.left_link; //traverse left
				else
				{
					ptr.left_link=temp;
					flag=1;
				}
			}
				
			else if(direction==0)
			{
			if(ptr.right_link!=null)
				ptr=ptr.right_link; //traverse right
			else
				{
				ptr.right_link=temp; 
				flag=1;
				}
			}
			else
				System.out.println("enter valid number for direction");
			
			if(flag==1) //for adding
				System.out.println("added !!!");
			}		
		}
		
	}
	
	
	private  void inorder_traversal(node local_root)
	{
		if(local_root!=null)
		{
			inorder_traversal(local_root.left_link); //traverse left
			System.out.print(local_root.data + " "); //print root
			inorder_traversal(local_root.right_link); //traverse right 
		}
		
	}
	
	private void preorder_traversal(node local_root)
	{
		if(local_root!=null)
		{
			System.out.print(local_root.data + " ");//print root
			inorder_traversal(local_root.left_link);//traverse left
			inorder_traversal(local_root.right_link);//traverse right
		}
	}
	
	private void postorder_traversal(node local_root)
	{
		if(local_root!=null)
		{
			inorder_traversal(local_root.left_link);//traverse left
			inorder_traversal(local_root.right_link);//traverse right
			System.out.print(local_root.data + " ");//print root
		}
		
	}
	
	
	private  void postorder_traversal_nonrec()
	{
		Stack<node> s1 = new Stack<node>();  //stack 1
		Stack<Integer> s2 = new Stack<Integer>();  //stack 2
		ptr=root;
		int flag=0;
		do
		{
		while(ptr!=null)
		{
			s1.push(ptr);
			s2.push(1);
			ptr=ptr.left_link;   //traverse left
		}
		if(!s1.isEmpty())
		{
			ptr=s1.pop();
			flag = s2.pop();   //get ptr
			if(flag==1)
			{
				s1.push(ptr);
				s2.push(0);
				ptr=ptr.right_link;   //traverse right
			}
			else
			{
				System.out.print(ptr.data +"  ");
				ptr=null;
			}
			
		}
		}while(ptr!=null||(!s1.isEmpty())); //check loop
		
	}
	
	
	private  void preorder_traversal_nonrec()
	{
		Stack<node> s = new Stack<node>();  //stack 
		ptr=root;
		do
		{
			while(ptr!=null)
			{
				System.out.print(ptr.data +"  ");				
				s.push(ptr);
				ptr=ptr.left_link;
			}
			if(!s.isEmpty())
			{
				ptr=s.pop();
				ptr=ptr.right_link;
				
			}		
			
		}while(ptr!=null||(!s.isEmpty())); //check loop
		
		
		System.out.println();
			
	}

	public static void main(String args[])
	{
		binary_tree tree = new binary_tree();
		int choice;
		do 
		{
			System.out.println();
			System.out.println();
			//display menu
			System.out.println("ENTER YOUR CHOICE : ");
			System.out.println("1. ADD NODE TO TREE");
			System.out.println("2. INORDER TRAVERSAL");
			System.out.println("3. PREORDER TRAVERSAL");
			System.out.println("4. POSTORDER TRAVERSAL");
			System.out.println("5. PREORDER TRAVERSAL REC");
			System.out.println("6. POSTORDER TRAVERSAL REC");
			System.out.println("0. EXIT");
			System.out.print("enter : ");
			choice=input.nextInt();
			//call function based on choice 
			switch(choice)
			{
				case 1:
					tree.add_node();
					break;
				case 2:
					System.out.print(" INORDER TRAVERSAL  :  ");
					tree.inorder_traversal(root);
					System.out.println();
					break;
				case 3:
					System.out.print(" PREORDER TRAVERSAL  :  ");
					tree.preorder_traversal(root);
					System.out.println();
					break;
				case 4:
					System.out.print(" POSTORDER TRAVERSAL  :  ");
					tree.postorder_traversal(root);
					System.out.println();
					break;
				case 5:
					System.out.print(" INORDER TRAVERSAL REC  :  ");
					tree.preorder_traversal_nonrec();
					break;
				case 6:
					System.out.print(" POSTORDER TRAVERSAL REC  :  ");
					tree.postorder_traversal_nonrec();
					break;
				case 0:
					System.out.println("thank you!!!");
					break;
				default:
					System.out.println("enter valid choice!");
						break;
			}
		}while(choice!=0);
		
	}

	
}


//						OUTPUT
/*

ENTER YOUR CHOICE : 
1. ADD NODE TO TREE
2. INORDER TRAVERSAL
3. PREORDER TRAVERSAL
4. POSTORDER TRAVERSAL
5. PREORDER TRAVERSAL REC
6. POSTORDER TRAVERSAL REC
0. EXIT
enter : 1
enter the data value : 1



ENTER YOUR CHOICE : 
1. ADD NODE TO TREE
2. INORDER TRAVERSAL
3. PREORDER TRAVERSAL
4. POSTORDER TRAVERSAL
5. PREORDER TRAVERSAL REC
6. POSTORDER TRAVERSAL REC
0. EXIT
enter : 1
enter the data value : 2

current node : 1
enter direction for the next node (1-left , 0-right) :  1
added !!!


ENTER YOUR CHOICE : 
1. ADD NODE TO TREE
2. INORDER TRAVERSAL
3. PREORDER TRAVERSAL
4. POSTORDER TRAVERSAL
5. PREORDER TRAVERSAL REC
6. POSTORDER TRAVERSAL REC
0. EXIT
enter : 1
enter the data value : 3

current node : 1
enter direction for the next node (1-left , 0-right) :  0
added !!!


ENTER YOUR CHOICE : 
1. ADD NODE TO TREE
2. INORDER TRAVERSAL
3. PREORDER TRAVERSAL
4. POSTORDER TRAVERSAL
5. PREORDER TRAVERSAL REC
6. POSTORDER TRAVERSAL REC
0. EXIT
enter : 1
enter the data value : 5

current node : 1
enter direction for the next node (1-left , 0-right) :  1
current node : 2
enter direction for the next node (1-left , 0-right) :  0
added !!!


ENTER YOUR CHOICE : 
1. ADD NODE TO TREE
2. INORDER TRAVERSAL
3. PREORDER TRAVERSAL
4. POSTORDER TRAVERSAL
5. PREORDER TRAVERSAL REC
6. POSTORDER TRAVERSAL REC
0. EXIT
enter : 2
 INORDER TRAVERSAL  :  2 5 1 3 


ENTER YOUR CHOICE : 
1. ADD NODE TO TREE
2. INORDER TRAVERSAL
3. PREORDER TRAVERSAL
4. POSTORDER TRAVERSAL
5. PREORDER TRAVERSAL REC
6. POSTORDER TRAVERSAL REC
0. EXIT
enter : 3
 PREORDER TRAVERSAL  :  1 2 5 3 


ENTER YOUR CHOICE : 
1. ADD NODE TO TREE
2. INORDER TRAVERSAL
3. PREORDER TRAVERSAL
4. POSTORDER TRAVERSAL
5. PREORDER TRAVERSAL REC
6. POSTORDER TRAVERSAL REC
0. EXIT
enter : 4
 POSTORDER TRAVERSAL  :  2 5 3 1 


ENTER YOUR CHOICE : 
1. ADD NODE TO TREE
2. INORDER TRAVERSAL
3. PREORDER TRAVERSAL
4. POSTORDER TRAVERSAL
5. PREORDER TRAVERSAL REC
6. POSTORDER TRAVERSAL REC
0. EXIT
enter : 5
 INORDER TRAVERSAL REC  :  1  2  5  3  


ENTER YOUR CHOICE : 
1. ADD NODE TO TREE
2. INORDER TRAVERSAL
3. PREORDER TRAVERSAL
4. POSTORDER TRAVERSAL
5. PREORDER TRAVERSAL REC
6. POSTORDER TRAVERSAL REC
0. EXIT
enter : 6
 POSTORDER TRAVERSAL REC  :  5  2  3  1  

ENTER YOUR CHOICE : 
1. ADD NODE TO TREE
2. INORDER TRAVERSAL
3. PREORDER TRAVERSAL
4. POSTORDER TRAVERSAL
5. PREORDER TRAVERSAL REC
6. POSTORDER TRAVERSAL REC
0. EXIT
enter : 7
enter valid choice!


ENTER YOUR CHOICE : 
1. ADD NODE TO TREE
2. INORDER TRAVERSAL
3. PREORDER TRAVERSAL
4. POSTORDER TRAVERSAL
5. PREORDER TRAVERSAL REC
6. POSTORDER TRAVERSAL REC
0. EXIT
enter : 0
thank you!!!
*/


