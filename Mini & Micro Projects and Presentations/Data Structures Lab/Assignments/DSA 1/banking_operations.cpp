//============================================================================
// Name        : banking_operations.cpp
// Author      :
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================
// ROLL NO. 2466
#include <iostream>
using namespace std;
class node
{
    //declaring attributes of node
    friend class list;
    int account_number;
    string name;
    float balance;
    node *next;
    public:
    node()  //constructor
    {
        account_number=0;
        name='\0';
        balance=0;
        next=NULL;
    }
};


class list
{
    node* start;
    //attributes of list

    public:
    list()
    {
        start=NULL;
    }

    //various banking operations
    void add_customer();
    void display();
    void delete_customer();
    void search_customer();
    void update_balance();
};


void list ::add_customer()
{
    int pass=1,an,bal;
    string n;

    do
    {
        //take details of customer
        cout<<endl<<"ENTER BANK DETAILS : ";
        cout<<endl<<"ACCOUNT NUMBER :";
        cin>>an;
        cout<<endl<<"NAME : ";
        cin>>n;
        cout<<endl<<"BALANCE : ";
        cin>>bal;
        //Ask whether the customer want to add more customers
        cout<<"press 1 to add new customer or 0 to exit : ";
        cin>>pass;
        //a temporary node is created
        node* temp=new node();
        temp->account_number=an;
        temp->balance=bal;
        temp->name=n;
        if(start==NULL)
            start=temp;//append first node
        else
        {
            node *ptr = start;
            while(ptr->next!=NULL)
            {
                ptr = ptr->next; //set pointer to next node
            }
            ptr->next = temp;
        }

    }while(pass==1);//runs loop till user wants to add customers
}

void list ::display()
{
    node *ptr = start;
    while(ptr->next!=NULL)
    {
        //display all nodes till pointer is not null
        cout<<endl<<"ACCOUNT NUMBER : "<<ptr->account_number;
        cout<<endl<<"NAME : "<<ptr->name;
        cout<<endl<<"BALANCE : "<<ptr->balance;
        cout<<endl;
        ptr = ptr->next;
    }
    //display last node
    cout<<endl<<"ACCOUNT NUMBER : "<<ptr->account_number;
    cout<<endl<<"NAME : "<<ptr->name;
    cout<<endl<<"BALANCE : "<<ptr->balance;
    cout<<endl;
}

void list::update_balance()
{
    int an;
    cout<<endl<<" ENTER ACCOUNT NUMBER WHOSE ACCOUNT IS TO BE UPDATED : ";
    cin>>an;
    node* ptr = start;
    while(ptr->account_number!=an&&ptr->next!=NULL)
        ptr = ptr->next;//find the node to be updated

    if(ptr->account_number==an)
    {
        float bal;
        //get updates
        cout<<"ENTER BALANCE : ";
        cin>>bal;
        cout<<endl;
        ptr->balance = bal;  //update

    }
    else
        cout<<"Enter valid account number !"<<endl<<endl; //if account number is not present
}


void list::delete_customer()
{
    int an;
    cout<<"\n ENTER ACCOUNT NUMBER WHOSE ACCOUNT IS TO BE DELETED : ";
    cin>>an;
    node* ptr = start;
    node* prev = start;
    int i = 0;
    if(ptr->account_number==an)//if the account is first account
    {
    	start=ptr->next; //change head
    }
    else
    {
    	while(ptr->next!=NULL)
    	    {
    	        if(ptr->account_number==an)//find the node
    	        {
    	            prev->next = ptr->next;
    	            delete ptr; //delete the node
    	            break;
    	        }
    	        if(i!=0) //correction for first time
    	            prev= prev->next;
    	        ptr = ptr->next;
    	        i++;
    	    }
            if(ptr->account_number==an)//if it is last node
			{
				prev->next=NULL;
			}
			else
                cout<<"Account does not exists"<<endl<<endl;//if the user enters wrong account number

    }


}


void list::search_customer()
{
    int an;
    cout<<"\n ENTER ACCNO TO BE SEARCHED : ";
    cin>>an;
    node* ptr = start;
    while(ptr->account_number!=an&&ptr->next!=NULL)
    {
        ptr = ptr->next;//find the node by account number

    }
    //print the node
    if(ptr->account_number==an)
    {
        cout<<endl<<"ACCOUNT NUMBER : "<<ptr->account_number;
        cout<<endl<<"NAME : "<<ptr->name;
        cout<<endl<<"BALANCE : "<<ptr->balance;
        cout<<endl;
    }
    else
        cout<<"Account number not present !"<<endl<<endl;//if account is not present

}


int main()
{
    int choice;
    list call;
    do
    {
        //display menu
        cout<<"\n ENTER YOUR CHOICE";
        cout<<"\n 1. ADD NEW CUSTOMER";
        cout<<"\n 2. DELETE CUSTOMER";
        cout<<"\n 3. SEARCH CUSTOMER ";
        cout<<"\n 4. DISPLAY ";
        cout<<"\n 5. UPDATE BALANCE";
        cout<<"\n 0. EXIT";
        cout<<endl<<"enter : ";
        cin>>choice;


        //input choice and select case
        switch(choice)
        {
            case 1:
                call.add_customer();
                break;
            case 2:
                call.delete_customer();
                break;
            case 3:
                call.search_customer();
                break;
            case 4:
                call.display();
                break;
            case 5:
                call.update_balance();
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

/*        OUTPUT
 ENTER YOUR CHOICE
 1. ADD NEW CUSTOMER
 2. DELETE CUSTOMER
 3. SEARCH CUSTOMER
 4. DISPLAY
 5. UPDATE BALANCE
 0. EXIT
enter : 1

ENTER BANK DETAILS :
ACCOUNT NUMBER :1

NAME : ABC

BALANCE : 23000
press 1 to add new customer or 0 to exit : 1

ENTER BANK DETAILS :
ACCOUNT NUMBER :2

NAME : DEF

BALANCE : 4500
press 1 to add new customer or 0 to exit : 1

ENTER BANK DETAILS :
ACCOUNT NUMBER :3

NAME : MNL

BALANCE : 67000
press 1 to add new customer or 0 to exit : 0

 ENTER YOUR CHOICE
 1. ADD NEW CUSTOMER
 2. DELETE CUSTOMER
 3. SEARCH CUSTOMER
 4. DISPLAY
 5. UPDATE BALANCE
 0. EXIT
enter : 2

 ENTER ACCOUNT NUMBER WHOSE ACCOUNT IS TO BE DELETED : 3

 ENTER YOUR CHOICE
 1. ADD NEW CUSTOMER
 2. DELETE CUSTOMER
 3. SEARCH CUSTOMER
 4. DISPLAY
 5. UPDATE BALANCE
 0. EXIT
enter : 4

ACCOUNT NUMBER : 1
NAME : ABC
BALANCE : 23000

ACCOUNT NUMBER : 2
NAME : DEF
BALANCE : 4500

 ENTER YOUR CHOICE
 1. ADD NEW CUSTOMER
 2. DELETE CUSTOMER
 3. SEARCH CUSTOMER
 4. DISPLAY
 5. UPDATE BALANCE
 0. EXIT
enter : 3

 ENTER ACCNO TO BE SEARCHED : 1

ACCOUNT NUMBER : 1
NAME : ABC
BALANCE : 23000

 ENTER YOUR CHOICE
 1. ADD NEW CUSTOMER
 2. DELETE CUSTOMER
 3. SEARCH CUSTOMER
 4. DISPLAY
 5. UPDATE BALANCE
 0. EXIT
enter : 3

 ENTER ACCNO TO BE SEARCHED : 7
Account number not present !


 ENTER YOUR CHOICE
 1. ADD NEW CUSTOMER
 2. DELETE CUSTOMER
 3. SEARCH CUSTOMER
 4. DISPLAY
 5. UPDATE BALANCE
 0. EXIT
enter : 5

 ENTER ACCOUNT NUMBER WHOSE ACCOUNT IS TO BE UPDATED : 2
ENTER BALANCE : 90000


 ENTER YOUR CHOICE
 1. ADD NEW CUSTOMER
 2. DELETE CUSTOMER
 3. SEARCH CUSTOMER
 4. DISPLAY
 5. UPDATE BALANCE
 0. EXIT
enter : 4

ACCOUNT NUMBER : 1
NAME : ABC
BALANCE : 23000

ACCOUNT NUMBER : 2
NAME : DEF
BALANCE : 90000

 ENTER YOUR CHOICE
 1. ADD NEW CUSTOMER
 2. DELETE CUSTOMER
 3. SEARCH CUSTOMER
 4. DISPLAY
 5. UPDATE BALANCE
 0. EXIT
enter : 0
THANK YOU !!
 *
 */
 */
