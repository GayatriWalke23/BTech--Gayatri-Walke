//============================================================================
// Name        : Queue.cpp
// Author      :
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================
//2466
#include <iostream>
using namespace std;
class waiting_list
{
    //attributes of waiting_list class
public:
	string Name;
	int Age;
	string Gender;

};
class Queue
{
	int front_; //Front Pointer
	int rear_; //Rear Pointer
public:
    Queue()
    {
      front_=-1;
      rear_=-1;
    }
	void display(waiting_list wl[],int n);
	int is_empty_();
	void is_full(int n);
	void enqueue(waiting_list wl[],int n);
	void dequeue(waiting_list wl[],int n);
};

//display list
void Queue::display(waiting_list wl[],int n)
{
    if(front_<=rear_)
    {
	cout<<"Unconfirmed List:"<<endl;

	for(int i=front_;i<n;i++)
	{
		cout<<"Name :"<<wl[i].Name<<endl;
		cout<<"Age :"<<wl[i].Age<<endl;
		cout<<"Gender :"<<wl[i].Gender<<endl;
		cout<<endl;
	}
    }
    else
        cout<<"list is empty"<<endl;
}

//is queue
int Queue:: is_empty_()
{
	if(front_>rear_)
		return 1;
	else
		return 0;
}

// is full
void Queue::is_full(int n)
{
	if(rear_>n-1)
	{
		cout<<"Queue Full"<<endl;
	}
}

// enqueue the list
void Queue::enqueue(waiting_list wl[],int n)
{
	 front_++;
	 for(int i=0;i<n;i++)
	 {
      rear_++;
	  cout<<"For Passenger "<<i+1<<endl;
	  cout<<"Enter the Name of Passenger :"<<endl;
	  cin>>wl[rear_].Name;
	  cout<<"Enter the Age of Passenger :"<<endl;
	  cin>>wl[rear_].Age;
	  cout<<"Enter the Gender of Passenger :"<<endl;
	  cin>>wl[rear_].Gender;
	}
}

//dequeue
void Queue::dequeue(waiting_list wl[],int n)
{
	if(is_empty_()==1)
	{
	    front_++;
		cout<<"All Seats are already Confirmed"<<endl;
	}
	else
	{

	cout<<"Seat Confirmed :"<<endl<<endl;

	cout<<"Name :"<<wl[front_].Name<<endl;
	cout<<"Age :"<<wl[front_].Age<<endl;
	cout<<"Gender :"<<wl[front_].Gender<<endl;

	 front_++;
	}
}


int main()
{
	Queue call;
	int number;
	int choice;
	cout<<"Enter Number Of Passengers In the Seat-Uncomfirmed List :"<<endl;
	cin>>number;
     waiting_list list_[number];   //objects
	 call.enqueue(list_, number);  //enqueue
	 do
	 {
     //display the menu
	 cout<<"Enter a Choice:  "<<endl;
	 cout<<"1.Confirm a Seat"<<endl;
	 cout<<"2.Display Waiting List"<<endl;
	 cout<<"0.Exit"<<endl;
	 cin>>choice;
	 switch(choice)
	 {
	 case 1:
	     //Dequeue
		 call.dequeue(list_, number);
		 break;
	 case 2:
	     //display
		call.display(list_,number);
		 break;
	 case 0:
		 cout<<"ThankYou!"<<endl;
		 break;
	 }
	 }while(choice!=0);
}


/*  OUTPUT

Enter Number Of Passengers In the Seat-Uncomfirmed List :
3
For Passenger 1
Enter the Name of Passenger :
gayatri
Enter the Age of Passenger :
18
Enter the Gender of Passenger :
f
For Passenger 2
Enter the Name of Passenger :
stuti
Enter the Age of Passenger :
20
Enter the Gender of Passenger :
f
For Passenger 3
Enter the Name of Passenger :
shuchi
Enter the Age of Passenger :
19
Enter the Gender of Passenger :
f
Enter a Choice:
1.Confirm a Seat
2.Display Waiting List
0.Exit
1
Seat Confirmed :

Name :gayatri
Age :18
Gender :f
Enter a Choice:
1.Confirm a Seat
2.Display Waiting List
0.Exit
2
Unconfirmed List:
Name :stuti
Age :20
Gender :f

Name :shuchi
Age :19
Gender :f

Enter a Choice:
1.Confirm a Seat
2.Display Waiting List
0.Exit
1
Seat Confirmed :

Name :stuti
Age :20
Gender :f
Enter a Choice:
1.Confirm a Seat
2.Display Waiting List
0.Exit
2
Unconfirmed List:
Name :shuchi
Age :19
Gender :f

Enter a Choice:
1.Confirm a Seat
2.Display Waiting List
0.Exit
1
Seat Confirmed :

Name :shuchi
Age :19
Gender :f
Enter a Choice:
1.Confirm a Seat
2.Display Waiting List
0.Exit
2
list is empty
Enter a Choice:
1.Confirm a Seat
2.Display Waiting List
0.Exit
1
All Seats are already Confirmed
Enter a Choice:
1.Confirm a Seat
2.Display Waiting List
0.Exit
0
ThankYou!

*/
