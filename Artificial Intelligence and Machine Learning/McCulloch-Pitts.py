def gates(gate):
    threshold = 0
    if gate == 1:
        print("         For AND gate  ")
        threshold = 2
    elif gate == 2:
        print("         For OR gate  ")
        threshold = 1
    w1 = 1
    w2 = 1
    x1 = int(input("         Enter value (0/1) of X1 : "))
    while x1 < 0 or x1 > 1:
        x1 = int(input("            Enter 0/1 :  "))
    x2 = int(input("         Enter value (0/1) of X2 : "))
    while x2 < 0 or x2 > 1:
        x2 = int(input("            Enter 0/1 :  "))

    result = x1 * w1 + x2 * w2

    if result >= threshold:
        return 1
    else:
        return 0


if __name__ == '__main__':
    print(
        '\n                      Logic gates using artificial neural network McCulloch-Pitts model                    \n')
    choice = 1
    while choice != 0:

        print("             ENTER YOUR CHOICE : ")
        print("                               1--> SOLVE USING AND GATE")
        print("                               2--> SOLVE USING OR GATE")
        print("                               3--> EXIT\n")
        gate = int(input("                               ENTER : "))

        if gate == 1 or gate == 2:
            result = gates(gate)
            print("The result is : ", result)
        elif gate == 3:
            break
        else:
            print("                               Enter valid choice!")
            continue

        print("\n \n Do you want to solve another problem ? ")

        choice = 4
        choice = int(input("Enter 0-No/1-Yes :  "))
        while choice < 0 or choice > 1:
            choice = int(input("            Enter 0-No/1-Yes :  "))

    print('             THANK YOU')

'''     OUTPUT

                      Logic gates using artificial neural network McCulloch-Pitts model                    

             ENTER YOUR CHOICE : 
                               1--> SOLVE USING AND GATE
                               2--> SOLVE USING OR GATE
                               3--> EXIT

                               ENTER : 1
         For AND gate  
         Enter value (0/1) of X1 : 1
         Enter value (0/1) of X2 : 3
            Enter 0/1 :  1
The result is :  1

 
 Do you want to solve another problem ? 
Enter 0-No/1-Yes :  1
             ENTER YOUR CHOICE : 
                               1--> SOLVE USING AND GATE
                               2--> SOLVE USING OR GATE
                               3--> EXIT

                               ENTER : 2
         For OR gate  
         Enter value (0/1) of X1 : 1
         Enter value (0/1) of X2 : 0
The result is :  1

 
 Do you want to solve another problem ? 
Enter 0-No/1-Yes :  0
             THANK YOU

Process finished with exit code 0




'''
