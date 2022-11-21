//		 KRUSKAL ASSIGNMENT
// GAYATRI WALKE(ROLL NO - 2466)


package dsa_temp;

import java.util.Scanner;

class node			//node class with the details of each edge
{
	int vertex_1;
	int vertex_2;
	int weight;
	
	node(int u,int v, int w)
	{
		vertex_1=u;
		vertex_2=v;
	    weight=w;
		
	}
}
public class kruskal 
{
	private int number_edges;		//details of graph
	private int number_vertex;
	private int adjacency_matrix[][]=new int[100][100];	//store the details in matrix form
	private node nodes[]=new node[100];		//to store each edge details
	private int parent[]=new int[100];
	kruskal(int v, int e)
	{
		number_edges=e;
		number_vertex=v;
		
	}
	
	
	static Scanner input=new Scanner(System.in);
	public void create_graph()
	{
        int node1,node2,weight;
        System.out.println("creating graph using adjacency matrix : ");
      
      
        for(int j=0;j<number_vertex;j++)
        {
              for(int i=0;i<number_vertex;i++)
              {
            	  adjacency_matrix[j][i]=new Integer(0); //initialize to zero
              }
        }

       
        System.out.println("Enter the nodes which are adjacent to each other and their resp. weights : ");
       
        for(int j=0;j<number_edges;j++)
        {
              System.out.print("    node first number   :"); //accept vertex
              node1=input.nextInt();
              System.out.print("    node second number  :");
              node2=input.nextInt();
              System.out.print("    enter the weight of the edge :");	//accept weight
              weight=input.nextInt();
              adjacency_matrix[node1-1][node2-1]=weight;
              adjacency_matrix[node2-1][node1-1]=weight;
              nodes[j]= new node(node1,node2,weight);
              System.out.println();
                                                   

        }

        System.out.println("The adjacency matrix is :");
        for(int j=0;j<number_vertex;j++)
        {
              for(int i=0;i<number_vertex;i++)
              {
                     System.out.print(adjacency_matrix[j][i]+ " "); //print matrix
              }
             System.out.println();

        }
		
	}
	
	public void kruskals()
	{
		int temp,first=0;
		int minimum_cost=0;
		/* System.out.println("u \tv \tw ");
		 for(int j=0;j<number_edges;j++)
		 {
			 System.out.println(nodes[j].vertex_1+"\t"+nodes[j].vertex_2+"\t"+nodes[j].weight);
		 }/*/
		 
		 for(int j=0; j<number_edges-1;j++)//sort sorted_aray w.r.t size
			{
				for(int i=0; i<number_edges-1-j;i++)
				{
					if(nodes[i].weight>nodes[i+1].weight)  //bubble sort the list according to the weight
					{
						temp=nodes[i].vertex_1;
						nodes[i].vertex_1=nodes[i+1].vertex_1;	//swap u1 and u2
						nodes[i+1].vertex_1=temp;
						temp=nodes[i].vertex_2;
						nodes[i].vertex_2=nodes[i+1].vertex_2;		//swap v1 and v2
						nodes[i+1].vertex_2=temp;
						temp=nodes[i].weight;
						nodes[i].weight=nodes[i+1].weight;			//swap w1 and w2
						nodes[i+1].weight=temp;
					}
				}	
			}
		 
		 /*System.out.println("sorted");
		 for(int j=0;j<number_edges;j++)
		 {
			 System.out.println(nodes[j].vertex_1+"\t"+nodes[j].vertex_2+"\t"+nodes[j].weight);
		 }/*/
		 
		 for(int j=0;j<=number_vertex;j++)
			 parent[j]= new Integer(j);
		 
		 /*System.out.print("parent : ");
		 for(int j=0;j<=number_vertex;j++)
			 System.out.print(parent[j]+" ");*/
		 System.out.println();
		 
		  for(int i=0; i<number_edges;i++)
		 {
			 int u = nodes[i].vertex_1;	//process each edge from sorted list
			 int v = nodes[i].vertex_2;
			 int w = nodes[i].weight;
			 //System.out.println("find_parent(u):"+find_parent(u)+"  find_parent(v)"+find_parent(v));
			 if(find_parent(u)!=find_parent(v))		//add to graph if it does not form a cycle
			 {
				 parent[find_parent(v)]=find_parent(u);		//update parent after adding		 
				 minimum_cost=minimum_cost+w;				//add weight 
				 //System.out.println("find_parent(u):"+find_parent(u)+"  find_parent(v)"+find_parent(v));
				 //System.out.println("first"+first);
				 
			 }
		 }
		 
		 System.out.println("Minimum cost = "+minimum_cost);	//print total minimum cost
		
	}
	
	int find_parent(int vertex)		//returns parent
	{
		int parent_=parent[vertex];
		while(parent[parent_]!=parent_)		//to find ultimate parent
		{
			parent_=parent[parent_];
		}
		return parent_;
		
	}
	
	public static void main(String args[])
	{
		  int number_nodes;
		  int number_edges;
          System.out.print("ENTER THE NUMBER OF NODES IN THE GRAPH : ");
          number_nodes=input.nextInt();
          System.out.print("ENTER THE NUMBER OF EDGES IN THE GRAPH : ");
          number_edges=input.nextInt();
		  kruskal call= new kruskal(number_nodes,number_edges);
		  call.create_graph();
		  call.kruskals();
		
	}
}

//				OUTPUT
/*ENTER THE NUMBER OF NODES IN THE GRAPH : 7
ENTER THE NUMBER OF EDGES IN THE GRAPH : 9
creating graph using adjacency matrix : 
Enter the nodes which are adjacent to each other and their resp. weights : 
    node first number   :1
    node second number  :2
    enter the weight of the edge :28

    node first number   :2
    node second number  :3
    enter the weight of the edge :16

    node first number   :3
    node second number  :4
    enter the weight of the edge :12

    node first number   :4
    node second number  :5
    enter the weight of the edge :22

    node first number   :6
    node second number  :5
    enter the weight of the edge :25

    node first number   :1
    node second number  :6
    enter the weight of the edge :10

    node first number   :5
    node second number  :7
    enter the weight of the edge :24

    node first number   :2
    node second number  :7
    enter the weight of the edge :14

    node first number   :7
    node second number  :4
    enter the weight of the edge :18

The adjacency matrix is :
0 28 0 0 0 10 0 
28 0 16 0 0 0 14 
0 16 0 12 0 0 0 
0 0 12 0 22 0 18 
0 0 0 22 0 25 24 
10 0 0 0 25 0 0 
0 14 0 18 24 0 0 

Minimum cost = 99
*/
