# -*- coding: utf-8 -*-
"""

Name : Gayatri Walke
C NO. : C22018221418
Roll No.: 3474
Assignment 1 : Travelling Salesman Problem

"""


# get graph
def input_cost_matrix(number_of_nodes):
    cost_matrix = []
    print("Enter the entries rowwise:")

    # For user input
    for i in range(number_of_nodes):  # row entries
        a = []
        for j in range(number_of_nodes):  # column entries
            if i == j:
                a.append(-1)  # to disable self loop
                continue

            loop = True

            while loop:  # validation
                cost = (int(input("Enter value for node ( %d , %d) : " % (i + 1, j + 1))))
                if cost >= 0:
                    a.append(cost)
                    loop = False
                else:
                    print('Sorry cost cannot be negative!')

        cost_matrix.append(a)  # append value

    # For printing the matrix
    print("\n\n   You inputed : \n")

    for i in range(number_of_nodes):
        for j in range(number_of_nodes):
            print("\t", cost_matrix[i][j], end=" ")
        print()

    return cost_matrix  # return cost matrix


# accept start node from user
def accept_start(number_nodes):
    start = -1
    while start < 1 or start > number_nodes:  # validation
        start = int(input('Enter start node value between 1 and %d : ' % number_nodes))
    return start - 1;

    # Function to print a BFS of graph


'''
# Perform BFS on the graph starting from vertex source
def BFS(graph, source, dest, N):
    # stores vertex is discovered in BFS traversal or not
    discovered = [False] * 3 * N

    # mark the source vertex as discovered
    discovered[source] = True

    # `predecessor` stores predecessor information. It is used to trace
    # the least-cost path from the destination back to the source.
    predecessor = [-1] * 3 * N

    # create a queue for doing BFS and enqueue source vertex
    q = deque()
    q.append(source)

    # loop till queue is empty
    while q:

        # dequeue front node and print it
        curr = q.popleft()

        # if destination vertex is reached
        if curr == dest:
            print("The least-cost path between", source, "and", dest, "is ", end='')
            print("having cost", printPath(predecessor, dest, -1, N))

        # do for every adjacent edge of the current vertex
        for v in graph.adjList[curr]:
            if not discovered[v]:
                # mark it as discovered and enqueue it
                discovered[v] = True
                q.append(v)

                # set `curr` as the predecessor of vertex `v`
                predecessor[v] = curr


'''

'''
for(int i=0;i<e;i++)
  {
   vis[i]=0;
  }
  Queue<Integer> q=new LinkedList<Integer>();
  System.out.println("Enter starting house : ");
  int start=co.nextInt();
  System.out.println("\nBFS : ");
  q.add(start);
  vis[start]=1;
  while(q.isEmpty()==false)
  {
   int d=q.remove();
   System.out.print(d+" ");
   for(int i=1;i<=no+1;i++)
   {
    if(ma[d][i]==1 && vis[i]!=1)
    {
     q.add(i);
     vis[i]=1;
    }
   }
  }
  '''


def BFS(graph, number_nodes):
    # Mark all the vertices as not visited
    visited = [False] * number_nodes
    cost = 0  # cost of traversal
    # Create a queue for BFS
    queue = []
    start = accept_start(number_nodes)  # get start node
    # Mark the source node as
    # visited and enqueue it
    queue.append(start)
    visited[start] = True
    print("\n\n BFS   PATH : ", end="")

    while queue:

        # Dequeue a vertex from
        # queue and print it
        s = queue.pop(0)
        print(s, end=" ")

        # Get all adjacent vertices of the
        # dequeued vertex s. If a adjacent
        # has not been visited, then mark it
        # visited and enqueue it
        for i in self.graph[s]:
            if graph[s][i] > 0 and visited[i] == False:
                cost = cost + graph[s][i]  # calculate cost
                queue.append(i)
                visited[i] = True

    cost = cost + graph[start][min_vertex]  # get back to the start node
    print(' ---> %d' % (start + 1))  # update path
    print('\n    TOTAL COST = %d \n\n' % cost)


# solve using depth first search
def graph_traversal(graph, number_nodes):
    print("\n\n              DFS TRAVERSAL \n\n")

    cost = 0  # cost of traversal
    visited = [0] * number_nodes()  # keep track of visited nodes
    stack = []  # stack to track order

    start = accept_start(number_nodes)  # get start node
    visited[start] = 1  # mark start node as visited

    stack.append(start)  # push start node into the stack

    print("\n\n DFS   PATH : %d" % (start + 1), end="")

    min_vertex = 0  # minimum vertex for traversal

    while len(stack) > 0:
        flag = 0
        d = stack[len(stack) - 1]

        min_value = 10000  # value for comparing costs

        for i in range(number_nodes):
            if graph[d][i] < min_value and d != i and visited[i] == 0:
                # loop through neighbour nodes to find out minimum cost
                min_value = graph[d][i]
                min_vertex = i

        if visited[min_vertex] == 0:  # if the node is not visited
            flag = 1
            print(' ---> %d' % (min_vertex + 1), end="")  # append it to path
            stack.append(min_vertex)  # push it to the stack
            cost = cost + graph[d][min_vertex]  # calculate cost
            visited[min_vertex] = 1  # mark it as visited
        else:
            flag = 0

        if flag == 0:
            stack.pop()  # if not pop it out

    cost = cost + graph[start][min_vertex]  # get back to the start node
    print(' ---> %d' % (start + 1))  # update path
    print('\n    TOTAL COST = %d \n\n' % cost)


if __name__ == '__main__':
    print('\n                      Travelling Salesman Problem                    \n')
    choice = 1

    while choice == 1:
        number_of_nodes = -1
        # input number of nodes in graph
        while number_of_nodes < 0:  # validation

            number_of_nodes = int(input('Enter number of nodes in the Graph : '))

            if number_of_nodes < 0:
                print('Sorry number of nodes cannot be negative!')

        # input cost matrix
        cost_matrix = input_cost_matrix(number_of_nodes)

        # get the traversal
        BFS(cost_matrix, number_of_nodes)
        graph_traversal(cost_matrix, number_of_nodes)

        # loop
        print("\n \n Do you want to solve another graph traversal problem ? ")
        choice = 4
        while choice != 0 or choice != 1:
            choice = int(input("Enter 0-No/1-Yes :  "))

    print('             THANK YOU')

'''
                 ----------------------OUTPUT-------------------------
                      Travelling Salesman Problem                    


Enter number of nodes in the Graph : -3
Sorry number of nodes cannot be negative!

Enter number of nodes in the Graph : 5
Enter the entries rowwise:

Enter value for node ( 1 , 2) : -4
Sorry cost cannot be negative!

Enter value for node ( 1 , 2) : 7

Enter value for node ( 1 , 3) : 6

Enter value for node ( 1 , 4) : 10

Enter value for node ( 1 , 5) : 13

Enter value for node ( 2 , 1) : 7

Enter value for node ( 2 , 3) : 7

Enter value for node ( 2 , 4) : 10

Enter value for node ( 2 , 5) : 10

Enter value for node ( 3 , 1) : 6

Enter value for node ( 3 , 2) : 7

Enter value for node ( 3 , 4) : 5

Enter value for node ( 3 , 5) : 9

Enter value for node ( 4 , 1) : 10

Enter value for node ( 4 , 2) : 10

Enter value for node ( 4 , 3) : 5

Enter value for node ( 4 , 5) : 6

Enter value for node ( 5 , 1) : 13

Enter value for node ( 5 , 2) : 10

Enter value for node ( 5 , 3) : 9

Enter value for node ( 5 , 4) : 6


   You inputed : 

         -1      7       6       10      13 
         7       -1      7       10      10 
         6       7       -1      5       9 
         10      10      5       -1      6 
         13      10      9       6       -1 


              DFS TRAVERSAL 



Enter start node value between 1 and 5 : 1
    

    PATH : 1 ---> 3 ---> 4 ---> 5 ---> 2 ---> 1

    TOTAL COST = 34 



 
 Do you want to solve another graph traversal problem ?

 Enter 0-No/1-Yes :  0
             THANK YOU
             






'''
