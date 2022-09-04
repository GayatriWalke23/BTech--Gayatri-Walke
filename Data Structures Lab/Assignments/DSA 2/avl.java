package dsa_temp;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



//node class and its attributes
class node
{
	node left_link;
	node right_link;
	String customer_name;
	long telephone_number;
	int height;
	
	node() //constructor
	{
		left_link=null;
		right_link=null;
		customer_name=null;
		telephone_number=0;
		height=0;
		
	}
	node(String n,long t) //parameterized constructor
	{
		customer_name=n;
		telephone_number=t;
		
	}
	
}


public class avl 
{
	//avl class and its attributes
	static Scanner input = new Scanner(System.in);	
	static Scanner input_ = new Scanner(System.in);
	private node root ;
    avl() 
    {
	   root=null;
	}
	int height(node N)
	{
		int left,right;
        if (N != null)
        {
        	if(N.left_link==null)  //left height
        		left=0;
        	else
        		left=height(N.left_link);
        	
        	if(N.right_link==null) //right height
        		right=0;
        	else
        		right=height(N.right_link);
        	
        	N.height=1+max(left,right); //return max
        }
        else
        	return 0;
        
  
        return N.height; 
    }
	
	int max(int a,int b)  //find maximum
	{
		if(a>b)
			return a;
		else
			return b;
	}
	
	int balance_factor(node local_root) //get balance factor
	{
		if(local_root==null)
			return 0;
		else
		 return height(local_root.left_link) - height(local_root.right_link); 	//left height - right height	
	}
	
	
	public node ll_rotation(node local_root)
	{
		System.out.println("ll_rotation");
		node temp = new node();
		temp=local_root.left_link;
		local_root.left_link=temp.right_link;
		temp.right_link=local_root;
		temp.height=height(temp);
		local_root.height=height(local_root);
		return temp;
		
		
	}
	public node rr_rotation(node local_root)
	{
		System.out.println("rr_rotation");
		node temp = new node();
		temp=local_root.right_link;
		local_root.right_link=temp.left_link;
		temp.left_link=local_root;
		temp.height=height(temp);
		local_root.height=height(local_root);
		return temp;
	}
	public node lr_rotation(node local_root)
	{
		System.out.println("lr_rotation");
		local_root.left_link=rr_rotation(local_root.left_link);
		local_root=ll_rotation(local_root);
		return local_root;
		
	}
	public node rl_rotation(node local_root)
	{
		System.out.println("rl_rotation");
		local_root.right_link=ll_rotation(local_root.right_link);
		local_root=rr_rotation(local_root);
		return local_root;
	}
	
	public void add_user(long tn,String cn)
	{
		root=add_user_(root,tn,cn);
	}
	
	public node add_user_(node local_root,long tn,String cn)
	{
		
		   if(local_root==null)   //for root node
		   {
		   
			  // System.out.println("enter");
			   root = new node(cn,tn);		//add new node
			   local_root=root;
			  // System.out.println("exit");
		   }
		   else 
		   {
			   
			   if(tn<local_root.telephone_number)//left side
				{	
				   local_root.left_link=add_user_(local_root.left_link, tn,cn);
				   if(balance_factor(local_root)==2)  //check for bf
				   {
					   if(tn<local_root.left_link.telephone_number)
						   local_root=ll_rotation(local_root);
					   else
						   local_root=lr_rotation(local_root);
					   
				   }
				}
			   else //right side
			   {
				   local_root.right_link=add_user_(local_root.right_link,tn,cn);
				   if(balance_factor(local_root)== -2) //check for bf
				   {
					   if(tn>local_root.right_link.telephone_number)
						   local_root=rr_rotation(local_root);
					   else
						   local_root=rl_rotation(local_root);
				   }
			   }		
			  
		   }
		   //System.out.println("added"+ local_root.telephone_number);
		   //System.out.println("root"+ root.telephone_number);
		   return local_root;
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
				
				
				System.out.print(temp.telephone_number+" ");//print data
			}
			else
			{
				if (!q.isEmpty()) //if not end of queue add null to start another level
					q.add(null);				
				System.out.println();//new line to differentiate level
			}					
			
		}	
		
	}
	
	public void display(node local_root)
	{
		
		if(local_root!=null)
		{
			
			display(local_root.left_link); //traverse left
			System.out.println(local_root.telephone_number + " : "+local_root.customer_name); //print root
			display(local_root.right_link); //traverse right 
		}
		
	}
	
	
	public void search_user()
	{
		  int tn;
		  System.out.print("enter telephone to be searched : ");
		  tn = input_.nextInt();
		  int flag = 0;
		  int found = 0;
		  node ptr = root;	
		  if(ptr==null)   //for root node
				System.out.println("Sorry, empty directory !");
		   else// node except root
		   {
						
				while(flag==0)
				{
					if(tn==ptr.telephone_number)
					{
						flag=1;
						found=1;//set found flag
					}
					
					else if(tn<ptr.telephone_number)
					{
						if(ptr.left_link!=null)
							ptr=ptr.left_link; //traverse left
						else
							{
								System.out.println("NOT PRESENT !");
								flag=1;
							}
						
					}
					
					else if(tn<ptr.telephone_number)
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
			  System.out.println(ptr.telephone_number + " : "+ptr.customer_name);//display
			  
		  }
		
	}	
	
	public static void main(String args[])
	{	
		
		int choice;
		String cn;
		long tn;
		avl call=new avl();
		
		do 
		{
			System.out.println();
			System.out.println("---------------------------------------------------------------------------------------------");
			//display menu
			System.out.println("ENTER YOUR CHOICE : ");
			System.out.println("1. ADD USER");
			System.out.println("2. SEARCH USER");
			System.out.println("3. DISPLAY DIRECTORY");
			System.out.println("0. EXIT");
			System.out.print("enter : ");
			choice=input.nextInt();
			//call function based on choice 
			switch(choice)
			{
				case 1:
					//add user
					System.out.print("ENTER NAME : ");//accepting  node
				    cn = input.next();
				    System.out.print("ENTER TELEPHONE NUMBER : ");	   
				    tn = input_.nextLong();
				    call.add_user(tn, cn);
				   // System.out.println("main root"+call.root.telephone_number);
					break;
					
				case 2:
					//search user 
					call.search_user();
					break;
					
				case 3:
					//display
					call.display_tree_level_wise();
					call.display(call.root);			
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

//					OUTPUT
/*
---------------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. ADD USER
2. SEARCH USER
3. DISPLAY DIRECTORY
0. EXIT
enter : 1
ENTER NAME : a
ENTER TELEPHONE NUMBER : 65

---------------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. ADD USER
2. SEARCH USER
3. DISPLAY DIRECTORY
0. EXIT
enter : 1
ENTER NAME : b
ENTER TELEPHONE NUMBER : 85

---------------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. ADD USER
2. SEARCH USER
3. DISPLAY DIRECTORY
0. EXIT
enter : 1
ENTER NAME : c
ENTER TELEPHONE NUMBER : 95
rr_rotation

---------------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. ADD USER
2. SEARCH USER
3. DISPLAY DIRECTORY
0. EXIT
enter : 1
ENTER NAME : d
ENTER TELEPHONE NUMBER : 30

---------------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. ADD USER
2. SEARCH USER
3. DISPLAY DIRECTORY
0. EXIT
enter : 1
ENTER NAME : e
ENTER TELEPHONE NUMBER : 6
ll_rotation

---------------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. ADD USER
2. SEARCH USER
3. DISPLAY DIRECTORY
0. EXIT
enter : 1
ENTER NAME : f
ENTER TELEPHONE NUMBER : 71
lr_rotation
rr_rotation
ll_rotation

---------------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. ADD USER
2. SEARCH USER
3. DISPLAY DIRECTORY
0. EXIT
enter : 1
ENTER NAME : g
ENTER TELEPHONE NUMBER : 23
lr_rotation
rr_rotation
ll_rotation

---------------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. ADD USER
2. SEARCH USER
3. DISPLAY DIRECTORY
0. EXIT
enter : 1
ENTER NAME : h
ENTER TELEPHONE NUMBER : 99

---------------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. ADD USER
2. SEARCH USER
3. DISPLAY DIRECTORY
0. EXIT
enter : 1
ENTER NAME : i
ENTER TELEPHONE NUMBER : 44

---------------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. ADD USER
2. SEARCH USER
3. DISPLAY DIRECTORY
0. EXIT
enter : 1
ENTER NAME : j
ENTER TELEPHONE NUMBER : 31
rl_rotation
ll_rotation
rr_rotation

---------------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. ADD USER
2. SEARCH USER
3. DISPLAY DIRECTORY
0. EXIT
enter : 3
65 
23 85 
6 31 71 95 
30 44 99 
6 : e
23 : g
30 : d
31 : j
44 : i
65 : a
71 : f
85 : b
95 : c
99 : h

---------------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. ADD USER
2. SEARCH USER
3. DISPLAY DIRECTORY
0. EXIT
enter : 2
enter telephone to be searched : 1
NOT PRESENT !

---------------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. ADD USER
2. SEARCH USER
3. DISPLAY DIRECTORY
0. EXIT
enter : 2
enter telephone to be searched : 44
found !!
44 : i

---------------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. ADD USER
2. SEARCH USER
3. DISPLAY DIRECTORY
0. EXIT
enter : 0
thank you!!!
*/