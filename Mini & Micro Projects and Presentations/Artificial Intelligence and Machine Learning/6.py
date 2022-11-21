sub = []
class algo:
    def unification(self,l1,l2):
        if(type(l1)==str and type(l2)==str):
            if(l1==l2):
                return True
            else:
                return [l2,l1]
        elif(type(l1)== str):
            if l1 not in l2 :
                return [l2,l1]
            else:
                return False
        elif(type(l2)== str):
            if l2 not in l1 :
                return [l1,l2]
            else:
                return False
        elif(len(l1)!=len(l2)):
            return False
        elif(l1[0]!=l2[0]):
            return False
        for i in range(1,len(l1)):
            val = self.unification(l1[i],l2[i])
            if(val==False):
                return False
            elif(val!=True):
                if(val not in sub and val!=sub):
                    sub.append(val)
                for j in range(i,len(l1)):

                    if(l1[j]==val[1]):
                        l1[j]=val[0]
                    if(l2[j]==val[1]):
                        l2[j]=val[0]

        return sub

print("The two expressions are")
print("Exp1 : P(x,f(y)))")
print("Exp2 : P(a,f(g(z)))")
L1 = ['P','x',['f','y']]
L2 = ['P','a',['f',['g','z']]]
obj = algo()
res = obj.unification(L1,L2)
if(res==False):
    print("CANNOT UNIFY")
else:
    print("SUBSTITUTION LIST : ", res)
'''
OUTPUT 1 :
The two expressions are
Exp1 : P(x,f(y))
Exp2 : P(a,f(g(z)))
SUBSTITUTION LIST : [['a', 'x'], [['g', 'z'], 'y']]
OUTPUT 2 :
The two expressions are
Exp1 : P(f(a),g(y))
Exp2 : P(X,X)
CANNOT UNIFY

'''
