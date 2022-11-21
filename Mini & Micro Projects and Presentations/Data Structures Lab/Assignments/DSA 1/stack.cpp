//============================================================================
// Name        : stack.cpp
// Author      :
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================
//2466
#include <iostream>
using namespace std;

class stack
{
    //attributes of sack
	string stack_;
	int top;
public:
	stack()//constructor
	{
		stack_='\0';
		top=-1;
	}
	void evaluate_expression(string element);
	float pop();
	bool is_empty();
	bool is_full();
	void display_result();
};

//to display result
void stack::display_result()
{
    cout<<endl<<"Result : "<<pop(); //display result
}

//find the value of expression
void stack::evaluate_expression(string element_)
{
    for(int i=0;element_[i]!='\0';i++)
    {

        if(isdigit(element_[i])) // check if the element is digit
        {
            top++;
            stack_[top]=element_[i]-48;   //save element at the top of stack
        }
        else
        {

            float op2=pop(); //pop operand one
            float op1=pop();  //pop operand two
            float result;
            switch(element_[i])  //check type of operator and do the operation
            {
                case '+':
                    result=op1+op2;
                    break;
                case '-':
                    result=op1-op2;
                    break;
                case '*':
                    result=op1*op2;
                    break;
                case '/':
                    result=op1/op2;
                    break;
                default:
                    break;
            }
            top++;
            stack_[top]=result; //push the result on the top of stack

        }
    }
}


float stack::pop()
{
	top--; //decrement top
	return(stack_[top+1]); //pop out last element
}


bool stack::is_empty()
{
    if(top==-1)
        return true;   //return true if top is -1 i.e. if stack is empty
    else
        return false;  //return false if top is not -1 i.e. if stack is not empty
}



int main()
{
	stack call;

	string expression;
	cout<<"ENTER THE EXPRESSION"<<endl;//take input string
	std::getline(std::cin,expression);
	cout<<"expression : "<<expression<<endl;
	call.evaluate_expression(expression);  //evaluate expression
	call.display_result(); //display result
	cout<<endl<<endl;

	return 0;
}

/*   OUTPUT

ENTER THE EXPRESSION
532+*
expression : 532+*

Result : 25

*/