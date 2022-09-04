"""

Name : Gayatri Walke
C NO. : C22018221418
Roll No.: 3474
Assignment 3 : A star algorithm

"""


def input_cost_matrix(number_nodes, number_edges):
    cost_matrix = []
    for i in range(number_nodes):  # initialize cost matrix
        a = [-1] * number_nodes
        cost_matrix.append(a)

    # For user input
    for i in range(number_edges):  #number of edges
        i = int(input("Enter the from node : "))#from node
        while 0 > i or i >= number_nodes:
            print('Node value can be from %d to %d ' % (0, number_nodes - 1))
            i = int(input("Enter the from node : "))
        j = int(input("            to node : "))#to node
        while 0 > j or j >= number_nodes:
            print('Node value can be from %d to %d ' % (0, number_nodes - 1))
            j = int(input("            to node : "))
        c = int(input("               cost : "))#cost
        while c < 0:
            print('Sorry cost cannot be negative!')
            c = int(input("               cost : "))

        cost_matrix[i][j] = c

    # For printing the matrix

    h = [0] * number_nodes  # heuristic cost
    for i in range(number_nodes):
        h[i] = int(input("Enter the heuristic value of %d node : " % i))

    return cost_matrix, h  # return cost matrix, heuristic cost


def graph_traversal_by_astar(number_nodes, cost_matrix, hcost):
    print(cost_matrix, hcost)
    history = []    # maintains path, cost, total cost of the paths
    not_goal = True
    start = int(input("Enter the start vertex : "))  # start
    goal = int(input("Enter the goal vertex : "))   # goal
    path_current = [start]
    for i in range(number_nodes):   # for start node
        if cost_matrix[start][i] > 0:
            new_path = path_current.copy()
            new_path.append(i)
            cost_current = 0 + cost_matrix[start][i] + hcost[i]
            prev_cost = 0 + cost_matrix[start][i]
            history.append((new_path, prev_cost, cost_current))
    #print(history)
    global path
    global cost
    while not_goal:
        history.sort(key=lambda tup: tup[2])  # sort
        pathlist = history[0][0]    # check for shortest path
        currentcost = history[0][2]
        prev_cost=history[0][1]
        history.pop(0)
        current_node = pathlist[-1]
        if current_node == goal:
            path = pathlist
            cost = prev_cost
            return path, cost
            break
        for i in range(number_nodes):   # check neighbours
            if cost_matrix[current_node][i] > 0:
                new_path = pathlist.copy()
                new_path.append(i)
                currentcost = prev_cost + cost_matrix[current_node][i] + hcost[i]
                prev_costt = prev_cost+ cost_matrix[current_node][i]
                history.append((new_path, prev_costt, currentcost))     # append
                #print(history)


if __name__ == '__main__':
    print('\n                      A-star Graph Traversal                    \n')
    choice = 1

    while choice != 0:
        number_of_nodes = -1
        number_of_edges = -1
        # input number of nodes in graph
        while number_of_nodes < 0:  # validation

            number_of_nodes = int(input('Enter number of nodes in the Graph : '))

            if number_of_nodes < 0:
                print('Sorry number of nodes cannot be negative!')

        # input number of edges in graph
        while number_of_edges < 0:  # validation

            number_of_edges = int(input('Enter number of edges in the Graph : '))

            if number_of_edges < 0:
                print('Sorry number of nodes cannot be negative!')

        # input cost matrix
        cost_matrix, hcost = input_cost_matrix(number_of_nodes, number_of_edges)
        path, cost = graph_traversal_by_astar(number_of_nodes, cost_matrix, hcost)
        print("         path :  ", end="")
        for i in path:
            print("%d --->" %i, end="")
        print("goal")
        print("Total cost : ", cost)
        # loop
        print("\n \n Do you want to solve another graph traversal problem ? ")
        choice = 4
        choice = int(input("Enter 0-No/1-Yes :  "))
        while choice < 0 or choice > 1:
            choice = int(input("            Enter 0-No/1-Yes :  "))

    print('             THANK YOU')


'''
                                OUTPUT
        

                      A-star Graph Traversal                    

Enter number of nodes in the Graph : -2
Sorry number of nodes cannot be negative!
Enter number of nodes in the Graph : 8
Enter number of edges in the Graph : -4
Sorry number of nodes cannot be negative!
Enter number of edges in the Graph : 10
Enter the from node : 9
Node value can be from 0 to 7 
Enter the from node : 0
            to node : 1
               cost : -1
Sorry cost cannot be negative!
               cost : 6
Enter the from node : 0
            to node : 2
               cost : 5
Enter the from node : 0
            to node : 3
               cost : 10
Enter the from node : 1
            to node : 5
               cost : 6
Enter the from node : 2
            to node : 4
               cost : 7
Enter the from node : 2
            to node : 5
               cost : 6
Enter the from node : 3
            to node : 4
               cost : 6
Enter the from node : 4
            to node : 6
               cost : 6
Enter the from node : 5
            to node : 6
               cost : 4
Enter the from node : 6
            to node : 7
               cost : 3
Enter the heuristic value of 0 node : 17
Enter the heuristic value of 1 node : 10
Enter the heuristic value of 2 node : 13
Enter the heuristic value of 3 node : 4
Enter the heuristic value of 4 node : 2
Enter the heuristic value of 5 node : 4
Enter the heuristic value of 6 node : 1
Enter the heuristic value of 7 node : 0
Enter the start vertex : 0
Enter the goal vertex : 7

         path :  0 --->2 --->5 --->6 --->7 --->goal
Total cost :  18

 
 Do you want to solve another graph traversal problem ? 
Enter 0-No/1-Yes :  0
 
             THANK YOU

'''
