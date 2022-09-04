//============================================================================
// Name        : sorting.cpp
// Author      :
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================
//2466


#include <iostream>
using namespace std;
class sort_
{
        //attributes of sort class
       public:
       int size_;
       float cgpa_array[100];
       void take_students_detail(int number_of_students);
       void selection_sort();
       void insertion_sort();
       void merge_sort(float array_[],int start,int last);
       void display();
       void merge_(float ar[],int start,int mid,int last);
};
//merge sort
void sort_ ::merge_sort(float array_[],int start,int last)
{

    if(start<last) //till start is less than
    {
        int middle=(start+last)/2;
        merge_sort(cgpa_array,start,middle); //divide into two arrays
        merge_sort(cgpa_array,middle+1,last);
        merge_(cgpa_array,start,middle,last); //after all the arrays are sorted merge them
    }

}

//merge two arrays
void sort_::merge_(float ar[],int start,int mid,int last)
{
    int p=start;
    int q=mid+1;
    float arr[last-start+1];
    int k=0;
    for(int i=start;i<=last;i++)
    {
        //fill array arr comparing the values
        if(p>mid)
            arr[k++]=ar[q++];
        else if(q>last)
            arr[k++]=ar[p++];
        else if(ar[p]<ar[q])
            arr[k++]=ar[p++];
        else
            arr[k++]=ar[q++];
    }

    for(int p=0;p<k;p++)
        ar[start++]=arr[p]; //new cgpa array formed
}

//insertion sort
void sort_ ::insertion_sort()
{
    cout<<"insertion sort : "<<endl;
    int i;
    int j;
    float key;
    for(i=1;i<size_;i++)
    {
        key=cgpa_array[i]; //take a key
        j=i-1;

        while(j>=0&&cgpa_array[j]>key) //check condition
        {
            cgpa_array[j+1]=cgpa_array[j];   //shift if it is not in order
            j=j-1;
        }
        cgpa_array[j+1]=key;  //place key at proper position
    }
}


//selection sort
void sort_ ::selection_sort()
{
    cout<<"selection sort : "<<endl;
    int i;
    int j;
    int minimum_index;
    float temp;
    for(i=0;i<size_-1;i++) //loop for n-1 passes
    {
        minimum_index=i;
        for(j=i+1;j<size_;j++)
        {
            if(cgpa_array[j]<cgpa_array[minimum_index])  //compare
               minimum_index=j;
        }
        temp=cgpa_array[minimum_index]; //swap if it is minimum index s different
        cgpa_array[minimum_index]=cgpa_array[i];
        cgpa_array[i]=temp;
    }
}

//input array
void sort_ ::take_students_detail(int number_of_students)
{
    size_=number_of_students;
    cout<<"enter the cgpa of "<<number_of_students<<" students :"<<endl;
    for(int i=0;i<number_of_students;i++)
        cin>>cgpa_array[i];  //take input

}

//display
void sort_ ::display()
{
    for(int i=0;i<size_;i++)
        cout<<cgpa_array[i]<<"\t";  //display the array
    cout<<endl<<endl<<endl;

}



int main()
{
    int choice=0;
    sort_ call;
    int number;

    //input number of students
    cout<<"Enter the number of students in class : "<<endl;
    cin>>number;
    call.take_students_detail(number); //fill the array
    do
    {
        cout<<"ENTER YOUR CHOICE : "<<endl;
        cout<<"1.selection sort "<<endl;
        cout<<"2.insertion sort "<<endl;
        cout<<"3.merge sort "<<endl;
        cout<<"0.exit "<<endl;
        cout<<"enter  : ";
        cin>>choice;

        switch(choice)
        {
        case 1:
            //call selection sort
            call.selection_sort();
            call.display();
            break;
        case 2:
            //call insertion sort
            call.insertion_sort();
            call.display();
            break;
        case 3:
            //call merge sort
            cout<<"merge sort : "<<endl;
            call.merge_sort(call.cgpa_array,0,number-1);
            call.display();
            break;
        case 0:
            //exit
            cout<<"THANK YOU!!"<<endl;
            break;
        default:
            //invalid choice
            cout<<"enter the valid choice "<<endl<<endl;
            break;

        }

    }while(choice!=0);
}

/*  OUTPUT

Enter the number of students in class :
5
enter the cgpa of 5 students :
7.8
8.6
7.9
9.8
6.9
ENTER YOUR CHOICE :
1.selection sort
2.insertion sort
3.merge sort
0.exit
enter  : 3
merge sort :
6.9     7.8     7.9     8.6     9.8


ENTER YOUR CHOICE :
1.selection sort
2.insertion sort
3.merge sort
0.exit
enter  : 2
insertion sort :
6.9     7.8     7.9     8.6     9.8


ENTER YOUR CHOICE :
1.selection sort
2.insertion sort
3.merge sort
0.exit
enter  : 1
selection sort :
6.9     7.8     7.9     8.6     9.8


ENTER YOUR CHOICE :
1.selection sort
2.insertion sort
3.merge sort
0.exit
enter  : 0
THANK YOU!!

*/
