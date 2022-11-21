//============================================================================
// Name        : string_operations.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
using namespace std;

class string_operations
{
  string a;
  int l;
  public:
  //functions of string
  int length();
  void copy_();
  void reverse_();
  void concat ();
  int occurrence();
  void accept();
  void display();

} s1,s2,s,concat_,_reverse_,_copy_;   //string objects for different purpose
void string_operations::accept ()
{
std::getline(std::cin,a);   //accept input from user
}
int string_operations::length ()
{
int i=0,count=0;
for (i=0;a[i]!='\0';i++)
{
count++;          //increase count by one for each element in string
}
l=count;
return l ;
}
void string_operations::copy_ ()
{
int i=0;
for (i=0;i<l;i++)
_copy_.a.push_back(a[i]);     //append elements of string one in the string
}
void string_operations::reverse_ ()
{
 int i=0;
 for (i=l-1;i>=0;i--)
{
	_reverse_.a.push_back(a[i]);   //append elements of string in reverse order by using length of string

}

}
void string_operations::concat ()
{
int i=0;
for (i=0;s1.a[i]!='\0';i++)
{
concat_.a.push_back(s1.a[i]);    //append string one elements to concat string
}
for (i=0;s2.a[i]!='\0';i++)
{
concat_.a.push_back(s2.a[i]);   //append string two elements to concat string
}
}
void string_operations::display ()
{
cout<<a<<endl;   //display the string
}
int string_operations::occurrence()
{
    char character;
    int count=0;
   cout<<"enter the character whose occurrence needs to be checked in string "<<a<<endl;
   cin>>character;
    for(int i=0;a[i]!='\0';i++)
    {
        if(character==a[i])     //check character and increase count
            count++;
    }
    return count;
}
int main()
{
    int case_ =0;
    //take input from user
    cout<<"enter string one"<<endl;
    s1.accept();
    cout<<"enter string two"<<endl;
    s2.accept();
    cout<<"string one : ";
    s1.display();
    cout<<"string two : ";
    s2.display();

    do
    {
    	//take input from user
        cout<<"STRING OPERATIONS : "<<endl;
        cout<<"1. length"<<endl;
        cout<<"2. reverse"<<endl;
        cout<<"3. copy"<<endl;
        cout<<"4. concat"<<endl;
        cout<<"5. occurrence"<<endl;
        cout<<"0. exit"<<endl;
        cout<<"enter your choice ";
        cin>>case_;
        //check case acc.
        switch(case_)
        {
        case 0:
            cout<<"thank you!!"<<endl;
            break;
        case 1:
            cout<<"length of string one : "<<s1.length()<<endl;
            cout<<"length of string two : "<<s2.length()<<endl;
            break;
        case 2:
        	s1.length();
            s1.reverse_();
            cout<<"reverse of string one : ";
            _reverse_.display();
            break;
        case 3:
            s1.copy_();
            cout<<"copied string is : ";
            _copy_.display();
            break;
        case 4:
            s.concat();
            cout<<"concatenated string : ";
            concat_.display();
            break;
        case 5:
            int occ = s1.occurrence();
            cout<<"occurrence is : "<<occ<<endl;
            break;

        }

    }while(case_!=0);
return 0;
}
