//============================================================================
// Name        : college_library.cpp
// Author      :
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================
//2466
#include <iostream>
using namespace std;


class book
{
     //book and its attributes
	string name;
	string author;
	int isbn_number;

public:

	void add_book()
	{
	    //accept attributes of book class
	    cout<<"ENTER NAME : ";
        cin>>name;
        cout<<"ENTER AUTHOR NAME : ";
        cin>>author;
        cout<<"ENTER ISBN NUMBER : ";
        cin>>isbn_number;
        cout<<endl<<endl;

	}
	static void search_book(string book_name,book b[]) //check book of particular name
	{
        int flag=0;//set flag for no search found
        for(int i=0; i<10;i++)
        {
                if(b[i].name== book_name)//check and display
                {
                    flag++;
                    b[i].display();
                    cout<<endl;
                }
        }
        if(flag==0)
        {
            cout<<" NO SUCH BOOK FOUND"<<endl;
        }


	}
    static void sort_book(book b[],int n)
    {
        //sort using bubble sort
        cout<<endl<<"Sorted list : "<<endl;
        book temp;
        for(int i=0;i<n;i++) //n-1 passes
        {
            for(int j=0;j<n-1-i;j++) //each time the checking should be done for one less value
            {
                if(b[j].isbn_number>b[j+1].isbn_number) //swap if greater
                {
                  temp=b[j];
                  b[j]=b[j+1];
                  b[j+1]=temp;
                }
            }
        }
        for(int i=0; i<n;i++)
            b[i].display();

	}
	void display()
	{
	    cout<<"NAME : "<<name;
        cout<<endl<<"AUTHOR NAME : "<<author;
        cout<<endl<<"ISBN NUMBER : "<<isbn_number;
        cout<<endl;

	}
	static void list_(string author_name,book b[]) //check book of particular author
	{
            int flag=0; //set flag for no search found
                for(int i=0; i<10;i++)
                {
                        if(b[i].author== author_name) //check and display
                        {
                            flag++;
                            b[i].display();
                            cout<<endl;
                        }
                }
                if(flag==0)
                {
                    cout<<" NO SUCH BOOK FOUND"<<endl;
                }


	}
	book() //constructors
	{
		name='\0';
		author='\0';
		isbn_number=0;
	}
};


int main()
{
	book call[10];
	int total_books=0; //for total number of books
	int choice=0;
	string book_name;
	string author_name;
	do
	{

		//display menu
		cout<<"\n ENTER YOUR CHOICE";
		cout<<"\n 1. ADD NEW BOOK IN LIBRARY";
		cout<<"\n 2. SEARCH A BOOK IN LIBRARY ";
		cout<<"\n 3. SORT ALL BOOKS BASED ON ISBN NO.";
		cout<<"\n 4. LIST ALL BOOKS OF SPECIFIC AUTHOR";
		cout<<"\n 0. EXIT";
		cout<<endl<<"enter : ";
		cin>>choice;


		//input choice and select case
		switch(choice)
		{
			case 1:
			    //add new book
			    cout<<"ENTER THE NUMBER OF BOOKS YOU WANT TO ADD :"<<endl;
                cin>>total_books;
				for(int i=0;i<total_books;i++)
                {
                    cout<<endl;
                    cout<<"FOR BOOK "<<i+1<<endl;
                    call[i].add_book();

                }
				break;
			case 2:
			    //search book
				cout<<"ENTER THE NAME OF BOOK TO BE SEARCHED"<<endl;
				cin>>book_name;
                book::search_book(book_name,call);
				break;
			case 3:
			    //sort all books
			    book::sort_book(call,total_books);
				break;
			case 4:
			    //list the names of authors
				cout<<"ENTER THE AUTHOR OF BOOK TO BE SEARCHED"<<endl;
				cin>>author_name;
				book::list_(author_name,call);
				break;
			case 0:
			    //to exit
				cout<<"THANK YOU !!"<<endl;
				break;
			default:
			    //default case
				cout<<" enter valid choice !"<<endl;
		}

	}while(choice!=0);

	return 0;
}


/*  OUTPUT



 ENTER YOUR CHOICE
 1. ADD NEW BOOK IN LIBRARY
 2. SEARCH A BOOK IN LIBRARY
 3. SORT ALL BOOKS BASED ON ISBN NO.
 4. LIST ALL BOOKS OF SPECIFIC AUTHOR
 0. EXIT
enter : 1
ENTER THE NUMBER OF BOOKS YOU WANT TO ADD :
3

FOR BOOK 1
ENTER NAME : abc
ENTER AUTHOR NAME : def
ENTER ISBN NUMBER : 123



FOR BOOK 2
ENTER NAME : def
ENTER AUTHOR NAME : hij
ENTER ISBN NUMBER : 564



FOR BOOK 3
ENTER NAME : lmn
ENTER AUTHOR NAME : opq
ENTER ISBN NUMBER : 335



 ENTER YOUR CHOICE
 1. ADD NEW BOOK IN LIBRARY
 2. SEARCH A BOOK IN LIBRARY
 3. SORT ALL BOOKS BASED ON ISBN NO.
 4. LIST ALL BOOKS OF SPECIFIC AUTHOR
 0. EXIT
enter : 2
ENTER THE NAME OF BOOK TO BE SEARCHED
def
NAME : def
AUTHOR NAME : hij
ISBN NUMBER : 564


 ENTER YOUR CHOICE
 1. ADD NEW BOOK IN LIBRARY
 2. SEARCH A BOOK IN LIBRARY
 3. SORT ALL BOOKS BASED ON ISBN NO.
 4. LIST ALL BOOKS OF SPECIFIC AUTHOR
 0. EXIT
enter : 2
ENTER THE NAME OF BOOK TO BE SEARCHED
nbm
 NO SUCH BOOK FOUND

 ENTER YOUR CHOICE
 1. ADD NEW BOOK IN LIBRARY
 2. SEARCH A BOOK IN LIBRARY
 3. SORT ALL BOOKS BASED ON ISBN NO.
 4. LIST ALL BOOKS OF SPECIFIC AUTHOR
 0. EXIT
enter : 3

Sorted list :
NAME : def
AUTHOR NAME : hij
ISBN NUMBER : 564
NAME : lmn
AUTHOR NAME : opq
ISBN NUMBER : 335
NAME : abc
AUTHOR NAME : def
ISBN NUMBER : 123


 ENTER YOUR CHOICE
 1. ADD NEW BOOK IN LIBRARY
 2. SEARCH A BOOK IN LIBRARY
 3. SORT ALL BOOKS BASED ON ISBN NO.
 4. LIST ALL BOOKS OF SPECIFIC AUTHOR
 0. EXIT
enter : 4
ENTER THE AUTHOR OF BOOK TO BE SEARCHED
opq
NAME : lmn
AUTHOR NAME : opq
ISBN NUMBER : 335


 ENTER YOUR CHOICE
 1. ADD NEW BOOK IN LIBRARY
 2. SEARCH A BOOK IN LIBRARY
 3. SORT ALL BOOKS BASED ON ISBN NO.
 4. LIST ALL BOOKS OF SPECIFIC AUTHOR
 0. EXIT
enter : 4
ENTER THE AUTHOR OF BOOK TO BE SEARCHED
cvb
 NO SUCH BOOK FOUND

 ENTER YOUR CHOICE
 1. ADD NEW BOOK IN LIBRARY
 2. SEARCH A BOOK IN LIBRARY
 3. SORT ALL BOOKS BASED ON ISBN NO.
 4. LIST ALL BOOKS OF SPECIFIC AUTHOR
 0. EXIT
enter : 4
ENTER THE AUTHOR OF BOOK TO BE SEARCHED
def
NAME : abc
AUTHOR NAME : def
ISBN NUMBER : 123


 ENTER YOUR CHOICE
 1. ADD NEW BOOK IN LIBRARY
 2. SEARCH A BOOK IN LIBRARY
 3. SORT ALL BOOKS BASED ON ISBN NO.
 4. LIST ALL BOOKS OF SPECIFIC AUTHOR
 0. EXIT
enter : 0
THANK YOU !!


*/
