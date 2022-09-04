"""

Name : Gayatri Walke
C NO. : C22018221418
Roll No.: 3474
Assignment 3 : BFS algorithm using priority queue

"""
# A simple implementation of Priority Queue
# using Queue.


class PriorityQueue(object):
    def __init__(self):
        self.queue = []

    def __str__(self):
        return ' '.join([str(i) for i in self.queue])

    # for checking if the queue is empty
    def isEmpty(self):
        return len(self.queue) == 0

    # for inserting an element in the queue
    def insert(self, data):
        self.queue.append(data)

    # for popping an element based on Priority
    def delete(self):
        try:
            min = 0
            for i in range(len(self.queue)):
                if self.queue[i][1] < self.queue[min][1]:
                    min = i
            item = self.queue[min]
            del self.queue[min]
            return item
        except IndexError:
            print()
            exit()


def input_cost_matrix(number_nodes, number_edges):
    cost_matrix = []
    for i in range(number_nodes):  # initialize cost matrix
        a = [-1] * number_nodes
        cost_matrix.append(a)

    # For user input
    for i in range(number_edges):  # number of edges
        i = int(input("Enter the from node : "))  # from node
        while 0 > i or i >= number_nodes:
            print('Node value can be from %d to %d ' % (0, number_nodes - 1))
            i = int(input("Enter the from node : "))
        j = int(input("            to node : "))  # to node
        while 0 > j or j >= number_nodes:
            print('Node value can be from %d to %d ' % (0, number_nodes - 1))
            j = int(input("            to node : "))
        c = int(input("               cost : "))  # cost
        while c < 0:
            print('Sorry cost cannot be negative!')
            c = int(input("               cost : "))

        cost_matrix[i][j] = c

    # For printing the matrix

    h = [0] * number_nodes  # heuristic cost
    for i in range(number_nodes):
        h[i] = int(input("Enter the heuristic value of %d node : " % i))

    return cost_matrix, h  # return cost matrix, heuristic cost


def graph_traversal_by_bfs(number_nodes, cost_matrix, hcost):
    open = PriorityQueue()
    close = []
    path = []
    not_goal = True

    start = int(input("Enter the start vertex : "))  # start

    goal = int(input("Enter the goal vertex : "))   # goal
    while start == goal:
        print("Start and goal vertex cannot be same !")
        goal = int(input("Enter the goal vertex : "))   # goal
    path.append(start)
    close.append(start)
    while not_goal:
        current_node = path[-1]
        if current_node == goal:
            cost = 0
            for j in range(len(path)-1):
                cost = cost + cost_matrix[path[j]][path[j+1]]
            return path, cost
        for i in range(number_nodes):   # check neighbours
            if cost_matrix[current_node][i] > 0:
                if not (close.__contains__(i)):
                    open.insert((i, hcost[i]))
        
        node, cost__ = open.delete()
        path.append(node)
        close.append(start)


if __name__ == '__main__':
    print('\n                      BREADTH FIRST SEARCH                    \n')
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
        path, cost = graph_traversal_by_bfs(number_of_nodes, cost_matrix, hcost)
        print("         path :  ", end="")
        for i in path:
            print("%d --->" % i, end="")
        print("goal")
        print("Total cost : ", cost)
        # loop
        print("\n \n Do you want to solve another graph traversal problem ? ")
        choice = 4
        choice = int(input("Enter 0-No/1-Yes :  "))
        while choice < 0 or choice > 1:
            choice = int(input("            Enter 0-No/1-Yes :  "))

    print('             THANK YOU')

'''                     OUTPUT


                      BREADTH FIRST SEARCH                    

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
Enter the goal vertex : 0
Start and goal vertex cannot be same !
Enter the goal vertex : 7
         path :  0 --->3 --->4 --->6 --->7 --->goal
Total cost :  25

 
 Do you want to solve another graph traversal problem ? 
Enter 0-No/1-Yes :  0
             THANK YOU

Process finished with exit code 0

'''
