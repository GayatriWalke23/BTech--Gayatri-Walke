class NN:
    def __init__(self):
        self.w = []
        self.b = 0
    def NN_fun(self,X):
        value = 0
        for i in range(len(X)):
            value+=self.w[i]*X[i]
            value+=self.b
        return value
    def and_gate(self,X):
        self.w = [1,1]
        self.b = -1
        output = 1 if (self.NN_fun(X)>0) else 0
        return output
    def or_gate(self,X):
        self.w = [2,2]
        self.b = -1
        output =1 if (self.NN_fun(X)>0) else 0
        return output
    def not_gate(self,X):
        self.w = [-1]
        self.b = 1
        output = 1 if (self.NN_fun(X)>0) else 0
        return output
    def nor_gate(self,X):
        self.w = [-1,-1]
        self.b = 1
        output = 1 if (self.NN_fun(X)>0) else 0
        return output
    def nand_gate(self,X):
        self.w = [-1,-1]
        self.b = 2
        output = 1 if (self.NN_fun(X)>0) else 0
        return output

obj = NN()
table =[]
for _ in range(4):
    print("Enter Table inputs")
in1 = int(input())
in2 = int(input())
table.append([in1,in2])
print("AND","\t\t","OR","\t\t","NOT","\t\t","NOR","\t\t","NAND")
for X in table:
    x1 = X[0]
    x2 = X[1]
print(x1,x2,obj.and_gate(X),"\t\t",x1,x2,obj.or_gate(X),"\t\t",x1,obj.not_gate([x1]),"\t\t",x1,x2,obj.nor_gate(X),"\t\t",x1,x2,obj.nand_gate(X))

'''
OUTPUT :
Enter Table inputs
0
0
Enter Table inputs
1
0
Enter Table inputs
0
1
Enter Table inputs
1
1
AND OR NOT NOR NAND
0 0 0 0 0 0 0 1 0 0 1 0 0 1
1 0 0 1 0 1 1 0 1 0 0 1 0 1
0 1 0 0 1 1 0 1 0 1 0 0 1 1
1 1 1 1 1 1 1 0 1 1 0 1 1 0
'''
