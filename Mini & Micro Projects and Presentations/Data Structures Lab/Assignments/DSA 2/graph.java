//	GAYATRI WALKE (ROLL NO. 2466)
package dsa_temp;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class graph
{
	//graph class and its attributes
      static int number_of_nodes;            

      graph(int no)
      {
             number_of_nodes=no; //number of nodes
      }

     

      static Scanner input=new Scanner(System.in);            
      void create__graph_using_matrix()
      {
           int choice = 1;
           int node1,node2;
           System.out.println("creating graph using adjacency matrix : ");
           Integer mat[][]= new Integer [number_of_nodes][number_of_nodes];
         
           for(int j=0;j<number_of_nodes;j++)
           {
                 for(int i=0;i<number_of_nodes;i++)
                 {
                               mat[j][i]=new Integer(0); //initialize to zero
                 }
           }

          
           System.out.println("Enter the node number which are adjacent to each other : ");
           System.out.print("    press 1 to continue :");
           choice=input.nextInt();
           while(choice == 1)
           {
                 System.out.print("    node first number   :"); //accept vertex
                 node1=input.nextInt();
                 System.out.print("    node second number  :");
                 node2=input.nextInt();
                 mat[node1-1][node2-1]=1;
                 mat[node2-1][node1-1]=1;
                 System.out.println();
                 System.out.print("    press 1 to continue :");
                 choice=input.nextInt();                                      

           }

           System.out.println("The adjacency matrix is :");
           for(int j=0;j<number_of_nodes;j++)
           {
                 for(int i=0;i<number_of_nodes;i++)
                 {
                        System.out.print(mat[j][i]+ " "); //print matrix
                 }
                System.out.println();

           }
      
           System.out.println("DFS traversal : ");
           int start_node,flag=0;
           Stack<Integer> s = new Stack<Integer>();
           System.out.print("enter start node : "); //accept start node
           start_node=input.nextInt();

           Integer visited_array[]= new Integer [number_of_nodes];
           for(int i=0;i<number_of_nodes;i++)
           {
                 visited_array[i]=new Integer(0); //initialize array to 0
           }

           System.out.println();

          

           visited_array[start_node-1]=1;
           s.push(start_node);
           System.out.print(start_node);
           int insert = 0;

           while(!s.isEmpty())
           {
                 flag=0;
                 int row=s.peek();
                 //System.out.println("checking : "+row);
                 for(int i=1;i<=number_of_nodes&&flag!=1;i++)
                 {                                                  
                       //System.out.println("row ="+row+" i="+i);
                       if(mat[row-1][i-1]==1 && visited_array[i-1]==0)
                       {
                            insert=i;
                            //System.out.println("adjacent got "+(i));                                                            
                            flag=1;
                       }                                                
                             
                 }

                
                 if(flag!=1)
                       s.pop();
                 else
                 {
                       visited_array[insert-1]=1;
                       System.out.print("---->"+insert);  //print path
                       s.push(insert);
                 }                                                    

           }                   
      }

    

     void create__graph_using_list()
     {
           int choice = 1,node;
           LinkedList<Integer> list[]=new LinkedList[number_of_nodes];
           System.out.println("creating graph using adjacency list : ");
           for(int i=0;i<number_of_nodes;i++)
             list[i]=new LinkedList();
 
           System.out.println("Enter the nodes number which are adjacent to : ");                 

           for(int i=0;i<number_of_nodes;i++)
           {

             System.out.println("    node "+(i+1)+" :");
             System.out.print("        press 1 to continue :");
             choice=input.nextInt();
             while(choice == 1)
             {

                   System.out.print("        node number   :"); //accept adjacent nodes of each vertex
                   node=input.nextInt();
                   list[i].add(node);
                   System.out.println();
                   System.out.print("        press 1 to continue :");
                   choice=input.nextInt();
  
             }

           }

           System.out.println();
           System.out.println("The adjacency list is :");
           for(int i=0;i<number_of_nodes;i++)
           {
             System.out.print((i+1)+" : ");
             for(int j=0;j<list[i].size();j++)
        	 System.out.print(list[i].get(j)+" "); //print
             System.out.println();   
           }

 
           System.out.println("bfs list traversal :");
           int start_node,insert;
           System.out.print("enter start node :");
           start_node=input.nextInt();
           Queue<Integer> q = new LinkedList<Integer>();//declare queue
           Integer visited_array[]= new Integer [number_of_nodes];

           for(int i=0;i<number_of_nodes;i++)
           {
                 visited_array[i]=new Integer(0); //initialize to 0
           }

          
           for(int i=0;i<number_of_nodes;i++)
           {
                 System.out.print(visited_array[i]+"  ");
           }

           System.out.println();
           q.add(start_node);
           visited_array[start_node-1]=1;

           //System.out.print("start");

           while(!q.isEmpty())
           {

                         node=q.poll();
                         //System.out.println("poll - "+node);
                         System.out.print("---->"+node);
                         for(int j=0;j<list[node-1].size();j++)
                           {
                        	 insert=list[node-1].get(j);

                              //System.out.println("check - "+insert)
                              if(visited_array[insert-1]!=1)
                              {
                                   q.add(insert);
                                    //System.out.println("inserted - "+insert);
                                    visited_array[insert-1]=1;
                              }
                           }                                       

           }
          /* for(int i=0;i<number_of_nodes;i++)
           {
                 System.out.print(visited_array[i]+"  ");
           }*/

           System.out.println();
      }           

     

      public static void main(String args[])

      {

           int number_nodes;
           System.out.print("ENTER THE NUMBER OF NODES IN THE GRAPH : ");
           number_nodes=input.nextInt();
           graph call=new graph(number_nodes);
           int choice;

           do
           {

             System.out.println();
             System.out.println();
             //display menu
             System.out.println("ENTER YOUR CHOICE : ");
             System.out.println("1. CREATE GRAPH USING ADJACENCY MATRIX & DEAPTH FIRST SEARCH (DFS - MATRIX)");
             System.out.println("2. CREATE GRAPH USING ADJACENCY LIST & BREADTH FIRST SEARCH (BFS - LIST)");
             System.out.println("0. EXIT");
             System.out.print("enter : ");
             choice=input.nextInt();
             //call function based on choice
             switch(choice)

             {

                           case 1:
                                    //create graph - matrix & dfs
                                    call.create__graph_using_matrix();
                                    break;                                       

                           case 2:
                                    //create graph - list & bfs
                                    call.create__graph_using_list();;
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
/*ENTER THE NUMBER OF NODES IN THE GRAPH : 6


ENTER YOUR CHOICE : 
1. CREATE GRAPH USING ADJACENCY MATRIX & DEAPTH FIRST SEARCH (DFS - MATRIX)
2. CREATE GRAPH USING ADJACENCY LIST & BREADTH FIRST SEARCH (BFS - LIST)
0. EXIT
enter : 1
creating graph using adjacency matrix : 
Enter the node number which are adjacent to each other : 
    press 1 to continue :1
    node first number   :1
    node second number  :2

    press 1 to continue :1
    node first number   :2
    node second number  :5

    press 1 to continue :1
    node first number   :3
    node second number  :4

    press 1 to continue :1
    node first number   :6
    node second number  :4

    press 1 to continue :1
    node first number   :2
    node second number  :6

    press 1 to continue :1
    node first number   :2
    node second number  :5

    press 1 to continue :0
The adjacency matrix is :
0 1 0 0 0 0 
1 0 0 0 1 1 
0 0 0 1 0 0 
0 0 1 0 0 1 
0 1 0 0 0 0 
0 1 0 1 0 0 
DFS traversal : 
enter start node : 1

1---->2---->5---->6---->4---->3

ENTER YOUR CHOICE : 
1. CREATE GRAPH USING ADJACENCY MATRIX & DEAPTH FIRST SEARCH (DFS - MATRIX)
2. CREATE GRAPH USING ADJACENCY LIST & BREADTH FIRST SEARCH (BFS - LIST)
0. EXIT
enter : 2
creating graph using adjacency list : 
Enter the nodes number which are adjacent to : 
    node 1 :
        press 1 to continue :1
        node number   :2

        press 1 to continue :0
    node 2 :
        press 1 to continue :1
        node number   :6

        press 1 to continue :1
        node number   :5

        press 1 to continue :0
    node 3 :
        press 1 to continue :1
        node number   :4

        press 1 to continue :0
    node 4 :
        press 1 to continue :1
        node number   :5

        press 1 to continue :1
        node number   :3

        press 1 to continue :0
    node 5 :
        press 1 to continue :1
        node number   :2

        press 1 to continue :0
    node 6 :
        press 1 to continue :1
        node number   :2

        press 1 to continue :1
        node number   :4

        press 1 to continue :0

The adjacency list is :
1 : 2 
2 : 6 5 
3 : 4 
4 : 5 3 
5 : 2 
6 : 2 4 
bfs list traversal :
enter start node :1
0  0  0  0  0  0  
---->1---->2---->6---->5---->4---->3


ENTER YOUR CHOICE : 
1. CREATE GRAPH USING ADJACENCY MATRIX & DEAPTH FIRST SEARCH (DFS - MATRIX)
2. CREATE GRAPH USING ADJACENCY LIST & BREADTH FIRST SEARCH (BFS - LIST)
0. EXIT
enter : 0
thank you!!!

*/