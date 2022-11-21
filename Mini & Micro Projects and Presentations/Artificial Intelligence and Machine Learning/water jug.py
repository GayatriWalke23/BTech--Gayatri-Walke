
"""

Name : Gayatri Walke
C NO. : C22018221418
Roll No.: 3474
Assignment 2 : Water Jug Problem

"""


visited_find={} #to store evaluated states

#evaluates the solution if solution is present prints solution and returns true else returns false
def Find(jug1_capacity,jug2_capacity,jug1_current_state,jug2_current_state,jug1_goal_state,jug2_goal_state,path):
   
    #check if the current state is valid to evaluate again
    if(not isvalid(jug1_capacity, jug2_capacity, jug1_current_state, jug2_current_state)):
        return False
    else:
        #append state to path and then mark it as visited
        path.append((jug1_current_state,jug2_current_state))
        visited_find[jug1_current_state,jug2_current_state]=1
      
    #check if the goal state is already reached
    if(jug1_current_state==jug1_goal_state and jug2_current_state==jug2_goal_state):
        print("       Transition steps : ",path)
        return True
    
    
    #fill jug1 from jug2
    x1=jug1_current_state
    y1=jug2_current_state
    if(jug1_current_state<jug1_capacity):
        add=jug1_capacity-jug1_current_state
        if(add>jug2_current_state):
            x1=x1+y1
            y1=0
        else:
            x1=x1+add
            y1=y1-add
    
    
    #fill jug2 from jug1
    y2=jug1_current_state
    x2=jug2_current_state
    if(jug2_current_state<jug2_capacity):
        add=jug2_capacity-jug2_current_state
        if(add>jug1_current_state):
            x2=x2+y2
            y2=0
        else:
            x2=x2+add
            y2=y2-add  
    
    
    #apply production rules: 
    #Fill jug1        
    if(Find(jug1_capacity,jug2_capacity,jug1_capacity,jug2_current_state,jug1_goal_state,jug2_goal_state,path) ):
        return True
    #fill jug 2
    if Find(jug1_capacity,jug2_capacity,jug1_current_state,jug2_capacity,jug1_goal_state,jug2_goal_state,path):
         return True
    #empty jug 1
    if Find(jug1_capacity,jug2_capacity,0,jug2_current_state,jug1_goal_state,jug2_goal_state,path):
        return True
    #empty jug 2
    if Find(jug1_capacity,jug2_capacity,jug1_current_state,0,jug1_goal_state,jug2_goal_state,path) :
        return True
    #fill jug 1 from jug 2
    if Find(jug1_capacity,jug2_capacity,x1,y1,jug1_goal_state,jug2_goal_state,path):
        return True    
    #fill jug 2 from jug 1
    if Find(jug1_capacity,jug2_capacity,y2,x2,jug1_goal_state,jug2_goal_state,path):
        return True
    
    return False
 
#makes function calls to find solution
def water_jug(jug1_capacity,jug2_capacity,jug1_goal_state,jug2_goal_state):
    print(" \n\n        TO ACHIEVE ( %d , %d) FINAL STATE FROM ( %d , %d)    \n"%(jug1_goal_state,jug2_goal_state,0,0))
    #clear visited map and path to find a new solution
    visited_find.clear()
    path=[(0,0)]
    visited_find[0,0]=1
    
    
    #check for solution 1
    sol1=Find(jug1_capacity, jug2_capacity, jug1_capacity, 0, jug1_goal_state, jug2_goal_state, path)
    if(sol1):
        print(" \n \n    OR  \n\n")
    
    
    #clear visited map and path to find a new solution
    visited_find.clear()
    path=[(0,0)]
    visited_find[0,0]=1
    
    #check for solution 2
    if(not Find(jug1_capacity, jug2_capacity, 0,jug2_capacity , jug1_goal_state, jug2_goal_state, path) or not sol1):
        print("Sorry no solution available for the given transition")



#returns true if the state is yet to be evaluated and is valid  state   
def isvalid(jug1_capacity,jug2_capacity,jug1_current_state,jug2_current_state):
    if((((jug1_current_state,jug2_current_state) in visited_find))
       or(jug1_current_state>jug1_capacity)
       or(jug2_capacity<jug2_current_state)
       or (jug1_current_state<0)
       or(jug2_current_state<0)):
        return False
    else:
        return True




if __name__ == '__main__':
    print('\n                      WATER JUG PROBLEM                    \n')
    choice = 1
    while(choice==1):
        #initialize variables
        j1c=-1
        j2c=-1
        j1f=-1
        j2f=-1
   
        #accept input fro, user:
        #jug 1 capacity
        while(j1c<0):  #validate
            j1c=int(input("Enter the total capacity of jug 1 : "))
            if(j1c<0):
                print("Sorry, capacity cannot be negative!")
                
        #jug 2 capacity
        while(j2c<0):  #validate
            j2c=int(input("Enter the total capacity of jug 2 : "))
            if(j2c<0):
                print("Sorry, capacity cannot be negative!")
                
        #jug 1 final state
        while(j1f<0 or j1f>j1c):  #validate
            j1f=int(input("Enter the final state of jug 1 : "))
            if(j1c<0):
                print("Sorry, final state cannot be negative!")
            if(j1f>j1c):
                print('Sorry, final state cannot be greater than capacity! ')
                
        #jug 2 final state
        while(j2f<0 or j2f>j2c):  #validate
            j2f=int(input("Enter the final state of jug 2 : "))
            if(j1c<0):
                print("Sorry, Final state cannot be negative!")
            if(j2f>j2c):
                print('Sorry, final state cannot be greater than capacity! ')
        
        #solve the given problem
        water_jug(j1c,j2c,j1f,j2f)
        
        #loop for another problem
        print("\n \n Do you want to try another problem ? ")
        choice=int(input("Enter 0-No/1-Yes :  "))
    
    print('             THANK YOU')
    
    


'''
                 ----------------------OUTPUT-------------------------
                 
                 
 
                      WATER JUG PROBLEM                    


Enter the total capacity of jug 1 : 4

Enter the total capacity of jug 2 : -3
Sorry, capacity cannot be negative!

Enter the total capacity of jug 2 : 3

Enter the final state of jug 1 : 6
Sorry, final state cannot be greater than capacity! 

Enter the final state of jug 1 : 2

Enter the final state of jug 2 : 0
 

        TO ACHIEVE ( 2 , 0) FINAL STATE FROM ( 0 , 0)    

       Transition steps :  [(0, 0), (4, 0), (4, 3), (0, 3), (3, 0), (3, 3), (4, 2), (0, 2), (2, 0)]
 
 
    OR  


       Transition steps :  [(0, 0), (0, 3), (4, 3), (4, 0), (1, 3), (1, 0), (0, 1), (4, 1), (2, 3), (2, 0)]

 
 Do you want to try another problem ? 

Enter 0-No/1-Yes :  1

Enter the total capacity of jug 1 : 3

Enter the total capacity of jug 2 : 4

Enter the final state of jug 1 : 2

Enter the final state of jug 2 : 3
 

        TO ACHIEVE ( 2 , 3) FINAL STATE FROM ( 0 , 0)    

Sorry no solution available for the given transition

 
 Do you want to try another problem ? 

Enter 0-No/1-Yes :  1

Enter the total capacity of jug 1 : 4

Enter the total capacity of jug 2 : 3

Enter the final state of jug 1 : 2

Enter the final state of jug 2 : 3
 

        TO ACHIEVE ( 2 , 3) FINAL STATE FROM ( 0 , 0)    

       Transition steps :  [(0, 0), (4, 0), (4, 3), (0, 3), (3, 0), (3, 3), (4, 2), (0, 2), (2, 0), (2, 3)]
 
 
    OR  


       Transition steps :  [(0, 0), (0, 3), (4, 3), (4, 0), (1, 3), (1, 0), (0, 1), (4, 1), (2, 3)]

 
 Do you want to try another problem ? 

Enter 0-No/1-Yes :  0
             THANK YOU





'''