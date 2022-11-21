//============================================================================
// Name        : set_operations.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================
// ROLL NO. 2466
#include <iostream>
using namespace std;
class set
{
	int set_array[10];
	int cardinality;
public:
	//functions of set
	void accept_set();
	void display_set();
	void union_of_sets(set one , set two_);
	void intersection_of_sets(set one , set two_);
	void setdiff_of_sets(set one , set two_);


}call,set_a,set_b,set_union,set_intersection,set_setdiff;  //declare objects of class set

void set :: accept_set()
{
	cout<<"ENTER THE CARDINALITY OF THE SET : ";
	cin>>cardinality;     //input number of elements from user
	cout<<endl<<"ENTER THE ELEMENTS OF THE SET : ";
	for(int i=0;i<cardinality;i++)
	{
		cin>>set_array[i];   //take inputs
	}
}
void set :: display_set()
{
	cout<<endl<<"THE ELEMENTS OF THE SET : {";
	for(int i=0;i<cardinality;i++)
		{
			if(i!=0)
				cout<<",";
		    cout<<set_array[i];       //display set
		}
	cout<<"}";

}
void set :: union_of_sets(set one , set two )
{
	cout<<endl<<"THE UNION OF THE GIVEN SETS :";
	int j=0,i=0,pass=1;
	for(j=0;j < one.cardinality;j++)
			this->set_array[j]=one.set_array[j];   //copy set one elements
	this->cardinality=one.cardinality;
	for(i=0;i<two.cardinality;i++)    //copy set two elements
		{
		int element =two.set_array[i];
		for(j=0;j< one.cardinality;j++)
		{
			if(element==one.set_array[j])
				pass=pass*0;                     //set pass as flag
		}
		if(pass!=0)
		{
			this->set_array[this->cardinality]=element;  //depending on pass value append elements to set
			this->cardinality++;
		}
		pass=1;
		}
}
void set :: setdiff_of_sets(set one , set two )
{
	cout<<endl<<"THE SETDIFF OF THE GIVEN SETS :";
	int i=0,j=0,pass=1;
	this->cardinality=0;
	for(i=0;i<one.cardinality;i++)     //take one element from set one
	{
		int element = one.set_array[i];
		for(j=0;j<two.cardinality;j++)
		{
			if(element==two.set_array[j])     //check if that element is present in set two
				pass=pass*0;                  //set pass value as flag
		}
		if(pass!=0)
		{
			this->set_array[this->cardinality]=element;   //append element depending on pass value
			this->cardinality++;
		}
		pass=1;

	}

}
void set :: intersection_of_sets(set one , set two )
{
	cout<<endl<<"THE INTERSECTION OF THE GIVEN SETS :";
	int i=0,j=0;
	this->cardinality=0;

	for(i=0;i<one.cardinality;i++)   //take one element from set one
	{
		int element = one.set_array[i];
		for(j=0;j<two.cardinality;j++)
		{
			if(element==two.set_array[j])  //check if that element is present in set two
			{
				this->set_array[this->cardinality]=element; //append element depending
				this->cardinality++;
			}
		}
	}

}
int main()
{
	cout<<endl<<"ENTER THE INFO OF THE ENGLISH SET "<<endl;
	set_a.accept_set();
	set_a.display_set();
	cout<<endl<<"ENTER THE INFO OF THE FRENCH SET   "<<endl;
	set_b.accept_set();
	set_b.display_set();
	cout<<endl<<"PEOPLE WHO SPEAK EITHER ENGLISH OR FRENCH  ";
	set_union.union_of_sets(set_a, set_b);
	set_union.display_set();
	cout<<endl<<"PEOPLE WHO SPEAK ENGLISH AND FRENCH   ";
	set_intersection.intersection_of_sets(set_a, set_b);
	set_intersection.display_set();
	cout<<endl<<"PEOPLE WHO SPEAK ONLY ENGLISH NOT FRENCH  ";
	set_setdiff.setdiff_of_sets(set_a, set_b);
	set_setdiff.display_set();
	cout<<endl<<"PEOPLE WHO SPEAK ONLY FRENCH NOT FRENCH   ";
	set_setdiff.setdiff_of_sets(set_b, set_a);
	set_setdiff.display_set();
	return 0;
}

/*          OUTPUT
 * ENTER THE INFO OF THE ENGLISH SET
ENTER THE CARDINALITY OF THE SET : 3

ENTER THE ELEMENTS OF THE SET : 1
2
3

THE ELEMENTS OF THE SET : {1,2,3}
ENTER THE INFO OF THE FRENCH SET
ENTER THE CARDINALITY OF THE SET : 5

ENTER THE ELEMENTS OF THE SET : 2
3
4
5
6

THE ELEMENTS OF THE SET : {2,3,4,5,6}
PEOPLE WHO SPEAK EITHER ENGLISH OR FRENCH
THE UNION OF THE GIVEN SETS :
THE ELEMENTS OF THE SET : {1,2,3,4,5,6}
PEOPLE WHO SPEAK ENGLISH AND FRENCH
THE INTERSECTION OF THE GIVEN SETS :
THE ELEMENTS OF THE SET : {2,3}
PEOPLE WHO SPEAK ONLY ENGLISH NOT FRENCH
THE SETDIFF OF THE GIVEN SETS :
THE ELEMENTS OF THE SET : {1}
PEOPLE WHO SPEAK ONLY FRENCH NOT FRENCH
THE SETDIFF OF THE GIVEN SETS :
THE ELEMENTS OF THE SET : {4,5,6}
 * */
