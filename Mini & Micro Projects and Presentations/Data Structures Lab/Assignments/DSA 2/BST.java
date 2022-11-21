// GAYATRI WALKE (ROLL NO 2466)
package dsa_temp;

import java.util.*;


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
public class tree
{
	
	// binary tree class and its attributes
		static private node root = new node();
		int left_height;
		int right_height;
		static int height ;
		
		node ptr = new node();//temporary node
		public tree() //constructor  
		{
			root=null;
			ptr=null;
			left_height =0;
			right_height =0;
			height =0;
			
		}
		static Scanner input =new Scanner(System.in); //for input
		

		public void add_node()
		{
			
			node temp= new node();
			System.out.print("enter the data value : ");
			temp.data=input.nextInt();
			System.out.println();
		
			int flag=0;
			
			if(root==null)   //for root node
				root=temp;
			else
			{
				ptr=root;
				while(flag==0)   //while the node is not added
				{
				if(temp.data < ptr.data)  
				{ 
					if(ptr.left_link!=null)
						ptr=ptr.left_link; //traverse left
					else
					{
						ptr.left_link=temp;
						flag=1;
					}
				}
					
				else if(temp.data >= ptr.data)
				{
				if(ptr.right_link!=null)
					ptr=ptr.right_link; //traverse right
				else
					{
					ptr.right_link=temp;
					flag=1;
					}
				}
				
				if(flag==1) //for adding
					System.out.println("added !!!");
				}		
			}
			
		}

		public void display_tree_level_wise()
		{
			node temp = root;
			Queue<node> q = new LinkedList<node>();//declare queue
			q.add(root);//add root
			q.add(null);//add null to differentiate level
			while(!q.isEmpty())
			{
				temp= q.poll(); //take ptr from queue
				if(temp!=null)
				{
					
					
					if(temp.left_link!=null)
						q.add(temp.left_link);//add left child
					
					if(temp.right_link!=null)
						q.add(temp.right_link);//add right child
					
					
					System.out.print(temp.data+" ");//print data
				}
				else
				{
					if (!q.isEmpty()) //if not end of queue add null to start another level
						q.add(null);				
					System.out.println();//new line to differentiate level
				}					
				
			}	
			
		}
		
		public void maximum_minimum()
		{
			//for maximum
			
			ptr = root;
			while(ptr.right_link!=null)
				ptr=ptr.right_link;
			System.out.println("MAXIMUM : "+ ptr.data);
			
			// for minimum
			 ptr=root;
			 while(ptr.left_link!= null)
				 ptr=ptr.left_link;
			 System.out.println("MINIMUM : "+ ptr.data);			

		}
		
		
		public int height(node local_root)
		{
			if(local_root==null)
				return 0;//null
			else
			{
				left_height=height(local_root.left_link);
				right_height=height(local_root.right_link);//add
				if(left_height>right_height)//return greater
					return (left_height+1);
				else
					return (right_height+1);
				
			}
			
						
						
		}
		
		
		public void descending_order(node local_root)//print in descending order
		{
			if(local_root!=null) 
			{
				descending_order(local_root.right_link);
				System.out.print(local_root.data+" "); //traverse right to left
				descending_order(local_root.left_link);
			}
			
		}
		
		
		public int count_leaf_node(node local_root)//count leaf nodes
		{
			
			if(local_root==null)
				return 0;
			if(local_root.left_link==null&&local_root.right_link==null)
				return 1;
			else 
				return( count_leaf_node(local_root.left_link)+count_leaf_node(local_root.right_link));
				
			
		}
		
		public void parent(int child)//find parent
		{
			node prev_ptr = new node();
			prev_ptr=null; //keep track of previous node
			ptr=root;
			
			
			while(ptr!=null)
			{
				if(ptr.data==child) //if node is fount
					break;
				else if(ptr.data>child) //traverse left
				{
					prev_ptr = ptr;
					ptr=ptr.left_link;
					
				}
				else if(ptr.data<child) //traverse right
				{
					prev_ptr = ptr;
					ptr=ptr.right_link;
					
				}
			}
			
			if(prev_ptr==null) //when no parent found
				System.out.println("no parent present!");
			else //display parent
				System.out.println("Parent is : "+ prev_ptr.data);			
			
		}
		
		 

public static void main(String args[])
{
	int count_ln;
	tree tree = new tree(); //tree creation
	int choice,child;
	do 
	{
		//display menu
		System.out.println("ENTER YOUR CHOICE : ");
		System.out.println("1. ADD NODE");
		System.out.println("2. FIND MAXIMUM AND MINIMUM NODE");
		System.out.println("3. DISPLAY TREE LEVEL-WISE");
		System.out.println("4. FIND HEIGHT");
		System.out.println("5. DISPLAY TREE IN DESCENDING ORDER");
		System.out.println("6. FIND PARENT OF THE NODE");
		System.out.println("7. COUNT NUMBER OF LEAF NODES (RECURSIVE)");
		System.out.println("0. EXIT");
		System.out.print("enter : ");
		choice=input.nextInt();
		//call function based on choice 
		switch(choice)
		{
			case 1://add
				tree.add_node();
				break;
				
			case 2://maximum_minimum
				tree.maximum_minimum();
				System.out.println();
				break;
				
			case 3:// level wise display
				tree.display_tree_level_wise();
				System.out.println();
				break;
				
			case 4://height of tree
				height=tree.height(root);
				System.out.println("HEIGHT : "+height);
				System.out.println();
				break;
				
			case 5://print in descending order
				tree.descending_order(root);
				break;
				
			case 6://find parent
				System.out.print("enter the node whose parent is to be found : ");
				child=input.nextInt();
				tree.parent(child);
				break;
				
			case 7://count number of leaf nodes
				count_ln=tree.count_leaf_node(root);
				System.out.println("COUNT OF LEAF NODES "+count_ln);
				break;
				
			case 0://exit
				System.out.println("thank you!!!");
				break;
				
				
			default:
				System.out.println("enter valid choice!");
				break;
		}
		System.out.println();
		System.out.println();
	}while(choice!=0);
	
}


}


//					OUTPUT
/*ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 1
enter the data value : 6



ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 1
enter the data value : 5

added !!!


ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 1
enter the data value : 4

added !!!


ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 1
enter the data value : 2

added !!!


ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 1
enter the data value : 3

added !!!


ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 1
enter the data value : 9

added !!!


ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 1
enter the data value : 8

added !!!


ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 1
enter the data value : 7

added !!!


ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 2
MAXIMUM : 9
MINIMUM : 2



ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 1
enter the data value : 1

added !!!


ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 3
6 
5 9 
4 8 
2 7 
1 3 



ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 4
HEIGHT : 4



ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 5
9 8 7 6 5 4 3 2 1 

ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 6
enter the node whose parent is to be found : 5
Parent is : 6

ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 6
enter the node whose parent is to be found : 6
no parent present!

ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 7
COUNT OF LEAF NODES 3


ENTER YOUR CHOICE : 
1. ADD NODE
2. FIND MAXIMUM AND MINIMUM NODE
3. DISPLAY TREE LEVEL-WISE
4. FIND HEIGHT
5. DISPLAY TREE IN DESCENDING ORDER
6. FIND PARENT OF THE NODE
7. COUNT NUMBER OF LEAF NODES (RECURSIVE)
0. EXIT
enter : 0
thank you!!!


*/
