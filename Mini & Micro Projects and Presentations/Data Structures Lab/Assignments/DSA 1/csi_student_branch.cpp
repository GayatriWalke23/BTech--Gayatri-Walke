//============================================================================
// Name        : csi_student_branch.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================
//ROLL NO 2466
#include <iostream>
using namespace std;
class student_info
{
    //declaring attributes of student_info
    friend class membership_list;
    int roll_number;
    string name;
    string department;
    student_info *next;
    public:

    student_info()  //constructor
    {
    	roll_number=0;
        name='\0';
        next=NULL;
    }
};


class membership_list
{
	student_info* start;
    //attributes of membership_list

    public:
	membership_list()
    {
        start=NULL;
    }

    void add_members();
    void total_members();
    void display_member_info();
    void remove_member_info();
};

void membership_list::add_members()
{
	int pass=1,rn;
	string n,d;

	do
	{
		//take details of student
		cout<<endl<<"ENTER STUDENT DETAILS : ";
		cout<<endl<<"ROLL NUMBER :";
		cin>>rn;
		cout<<"NAME : ";
		cin>>n;
		cout<<"DEPARTMENT : ";
		cin>>d;
		//Ask whether the user want to add more members
		cout<<"press 1 to add new student or 0 to exit ";
		cin>>pass;
		//a temporary node is created
		student_info* temp=new student_info();
		temp->roll_number=rn;
		temp->department=d;
		temp->name=n;
		if(start==NULL)
			start=temp;//append first node
		else
		{
			student_info *ptr = start;
			while(ptr->next!=NULL)
			{
				ptr = ptr->next; //set pointer to next node
			}
			ptr->next = temp;
		}

	}while(pass==1);

}

void membership_list::display_member_info()
{
	student_info* ptr = start;
	while(ptr->next!=NULL)
	{
		//display all nodes till pointer is not null
		cout<<endl<<"ROLL NUMBER : "<<ptr->roll_number;
		cout<<endl<<"NAME : "<<ptr->name;
		cout<<endl<<"DEPARTMENT : "<<ptr->department;
		cout<<endl;
		ptr = ptr->next;
	}
	//display last node
	cout<<endl<<"ROLL NUMBER : "<<ptr->roll_number;
	cout<<endl<<"NAME : "<<ptr->name;
	cout<<endl<<"DEPARTMENT : "<<ptr->department;
	cout<<endl;

}

void membership_list::remove_member_info()
{
	int rn;
	cout<<"\n ENTER ROLL NUMBER WHOSE MEMBERSHIP IS TO BE DELETED : ";
	cin>>rn;
	student_info* ptr = start;
	student_info* prev = start;
	int i = 0;
	if(ptr->roll_number==rn)//if the student is first student in list
	{
		start=ptr->next; //change head
	}
	else
	{
		while(ptr->next!=NULL)
			{
				if(ptr->roll_number==rn)//find the node
				{
					prev->next = ptr->next;
					delete ptr; //delete the node
					break;
				}
				if(i!=0) //when head is traversed then do not change previous pointer
					prev= prev->next;
				ptr = ptr->next;
				i++;
			}
		if(ptr->roll_number==rn) //if it is last node
		{
			prev->next=NULL;
		}
		else
			cout<<"Student Roll no. not present !"<<endl<<endl;

	}

}

void membership_list::total_members()
{
	int count=0;
	student_info* ptr = start;
	while(ptr->next!=NULL)
	{
		//increase count till pointer is not null
		count++;
		ptr = ptr->next;
	}
	count++; //for last node
	cout<<endl<<"TOTAL NUMBER OF MEMBERS ARE : "<<count;
	cout<<endl;


}


int main()
{
	int choice;
	membership_list call;
	do
	{
		//display menu
		cout<<"\n ENTER YOUR CHOICE";
		cout<<"\n 1. ADD NEW MEMBERS";
		cout<<"\n 2. COMPUTE TOTAL NUMBER OF MEMBERS";
		cout<<"\n 3. DISPLAY MEMBER INFORMATION";
		cout<<"\n 4. REMOVE MEMBER DETAILS ";
		cout<<"\n 0. EXIT";
		cout<<endl<<"enter : ";
		cin>>choice;


		//input choice and select case
		switch(choice)
		{
			case 1:
				call.add_members();
				break;
			case 2:
				call.total_members();
				break;
			case 3:
				call.display_member_info();
				break;
			case 4:
				call.remove_member_info();
				break;
			case 0:
				cout<<"THANK YOU !!"<<endl;
				break;
			default:
				cout<<" enter valid choice !"<<endl;
		}

	}while(choice!=0);
	return 0;
}
/*    OUTPUT
ENTER YOUR CHOICE
1. ADD NEW MEMBERS
2. COMPUTE TOTAL NUMBER OF MEMBERS
3. DISPLAY MEMBER INFORMATION
4. REMOVE MEMBER DETAILS
0. EXIT
enter : 1

ENTER STUDENT DETAILS :
ROLL NUMBER :1
NAME : GAYATRI
DEPARTMENT : COMP
press 1 to add new student or 0 to exit 1

ENTER STUDENT DETAILS :
ROLL NUMBER :2
NAME : SURBHI
DEPARTMENT : COMP
press 1 to add new student or 0 to exit 1

ENTER STUDENT DETAILS :
ROLL NUMBER :3
NAME : SHUCHI
DEPARTMENT : COMP
press 1 to add new student or 0 to exit 1

ENTER STUDENT DETAILS :
ROLL NUMBER :4
NAME : STUTI
DEPARTMENT : COMP
press 1 to add new student or 0 to exit 0

ENTER YOUR CHOICE
1. ADD NEW MEMBERS
2. COMPUTE TOTAL NUMBER OF MEMBERS
3. DISPLAY MEMBER INFORMATION
4. REMOVE MEMBER DETAILS
0. EXIT
enter : 2

TOTAL NUMBER OF MEMBERS ARE : 4

ENTER YOUR CHOICE
1. ADD NEW MEMBERS
2. COMPUTE TOTAL NUMBER OF MEMBERS
3. DISPLAY MEMBER INFORMATION
4. REMOVE MEMBER DETAILS
0. EXIT
enter : 3

ROLL NUMBER : 1
NAME : GAYATRI
DEPARTMENT : COMP

ROLL NUMBER : 2
NAME : SURBHI
DEPARTMENT : COMP

ROLL NUMBER : 3
NAME : SHUCHI
DEPARTMENT : COMP

ROLL NUMBER : 4
NAME : STUTI
DEPARTMENT : COMP

ENTER YOUR CHOICE
1. ADD NEW MEMBERS
2. COMPUTE TOTAL NUMBER OF MEMBERS
3. DISPLAY MEMBER INFORMATION
4. REMOVE MEMBER DETAILS
0. EXIT
enter : 4

ENTER ROLL NUMBER WHOSE MEMBERSHIP IS TO BE DELETED : 8
Student Roll no. not present !


ENTER YOUR CHOICE
1. ADD NEW MEMBERS
2. COMPUTE TOTAL NUMBER OF MEMBERS
3. DISPLAY MEMBER INFORMATION
4. REMOVE MEMBER DETAILS
0. EXIT
enter : 0
THANK YOU !!
*/
