//ROLL NO 2466

#include <iostream>
using namespace std;

class Item
{
	int item_no; //item id
	char item_name[20];
	int available_quantity; //stock in the shop
	int quantity=0; //quantity purchased from available stock
	float price; //per kilogram amount in rupees

public:
friend class Purchase;
	void accept_item();
	void display_item();
};

class Purchase
{
	int no_of_items;
	int no_of_customers;
	float bill[20]={0};
	Item stock[20];
	int customer[20][20]; //array for storing purchase details of each customer

public:
	void get_stock();
	void display_stock();
	void get_purchase();
	void display_purchase();
	void display_bill();
	void top_customer();
	void top_item();

Purchase()//default constructor
{
	no_of_items =0 ;
	no_of_customers = 0 ;
}
};

void Item::accept_item()
{
	//Taking item details for individual item
	cout<<"\nEnter item number: ";
	cin>>item_no;
	cout<<"Enter name of the item: ";
	cin>>item_name;
	cout<<"Enter available quantity of the item in kilograms: ";
	cin>>available_quantity;
	cout<<"Enter the price per kilogram of the item: ";
	cin>>price;
}

void Item::display_item()
{
	//Displaying item details
	cout<<"\n\nFollowing are the details of item: \nItem number: "<<item_no<<"\nName: "<<item_name;
	cout<<"\nAvailable quantity: "<<available_quantity<<" kilograms.\nPrice: Rs."<<price<<"\n\n";
}

void Purchase::get_stock()
{
	//Taking item details for all items
	cout<<"\n\nEnter number of items:";
	cin>>no_of_items;

	for(int i=0;i<no_of_items;i++)
	{
		cout<<"\n\nFor item "<<i+1;
		stock[i].accept_item() ;
	}
}

void Purchase::display_stock()
{
	//Displaying item details for all items
	for(int i=0;i<no_of_items;i++)
	{
		stock[i].display_item();
	}
}

void Purchase::get_purchase()
{
	//Taking purchase details

	cout<<"\n\nEnter number of customers:";
	cin>>no_of_customers;

	int qty=0;

	for(int i=0;i<no_of_customers;i++)
		//this loop covers all customers
	{
		cout<<"\nEnter quantity in kilograms of the following items to be purchased by customer "<<i+1<<"\n";
		for(int j=0;j<no_of_items;j++)
		//this loop covers all items for every customer
		{
			cout<<stock[j].item_no<<". "<<stock[j].item_name<<": ";
			cin>>qty;
			if(qty>stock[j].available_quantity)
			//checking if quantity desired to be purchased exceeds existing quantity
			{
				cout<<stock[j].item_name<<"is out of stock.";
				cout<<"\nAvailable quantity is "<<stock[j].available_quantity<<" kilograms.";
				customer[i][j] = 0;
			}
			else
				//item is in stock, purchase details updated
			{
				customer[i][j] = qty;
				stock[j].available_quantity = stock[j].available_quantity - qty;
			}
		}
	}
}

void Purchase::display_purchase()
{
	for(int i=0;i<no_of_customers;i++)
	//covers all customers
	{
		cout<<"\n\nCustomer number: "<<i+1;
		for(int j=0;j<no_of_items;j++)
		//covers all items for every customer
		{
			cout<<"\nItem: "<<stock[j].item_no<<". "<<stock[j].item_name;
			cout<<"\nPurchased quantity: "<<customer[i][j];
		}
	}
}

void Purchase::display_bill()
{
	int c_no=0;

	cout<<"\n\nEnter the serial number of a customer to see his bill: ";
	cin>>c_no;

	for(int i=0; i<no_of_customers; i++)
	//covers all customers
	{
		for(int j=0; j<no_of_items; j++)
		//covers all items for every customer
		{
			bill[i] = bill[i] + (customer[i][j] * stock[j].price);


			if(c_no==i+1)
			//checks for the customer whose bill is to be displayed
			{
				for(int j=0;j<no_of_items;j++)
				{
					cout<<"Item number purchased: \tQuantity purchased: \tPrice:"<<endl;
					cout<<j<<"\t\t\t\t";
					cout<<customer[i][j]<<"\t\t";
					cout<<stock[j].price<<"\t\t";
					cout<<endl;

				}
				cout<<"\n\nBill for customer "<<c_no<<" is: Rs. "<<bill[i]<<"\n";
			}
		}
	}
}
void Purchase::top_customer()
{
	int temp = 0;
	int n = 0;
	for(int i=0; i<no_of_customers; i++)
	//covers all customers
	{
		while(temp<bill[i])
		//checking for maximum among all bills
		{
			temp = bill[i];
			//storing maximum bill in temp
			n = i+1;
		}
	}
	//displaying top customer based on maximum bill
	cout<<"\n\nTop customer is customer "<<n<<" with bill Rs. "<<temp<<"\n\n";
}

void Purchase::top_item()
{
	int temp = 0;
	int n = 0;
	for(int j=0; j<no_of_items; j++)
	//covers all items
	{
		for(int i=0; i<no_of_customers; i++)
		//checks all customers' purchase for every item
		{
			//storing purchased quantity in "stock->quantity"
			stock[j].quantity = stock[j].quantity + customer[i][j];
		}
		if(temp<stock[j].quantity)
		//checking for maximum quantity purchased among all items
		{
			temp = stock[j].quantity;
			//storing maximum quantity in temp
			n = j;
		}
	}

	//displaying top item based on maximum quantity purchased
	cout<<"\n\nTop item with sale of "<<temp<<" kilograms is: \n";
	stock[n].display_item();
	cout<<"\n\n";
}

int main()
{
int choice;
Purchase p1;

do
{
	cout<<"\n1. Enter details of all items. \n2. Display details of all items. \n3. Enter customer and purchase details.";
	cout<<"\n4. Display purchase details. \n5. Display bill.\n6. Display top customer details.\n7. Display top item with details.";
	cout<<"\n8. Quit. \nEnter your choice: ";
	cin>>choice;

	switch(choice)
	{
		case 1: p1.get_stock();
		break;

		case 2: p1.display_stock();
		break;

		case 3: p1.get_purchase();
		break;

		case 4: p1.display_purchase();
		break;

		case 5: p1.display_bill();
		break;

		case 6: p1.top_customer();
		break;

		case 7: p1.top_item();
		break;

		case 8: cout<<"Thank you";
		break;

		default: cout<<"\nInvalid choice.";
		break;
	}
}while(choice<8);


return 0;
}

/* OUTPUT

1. Enter details of all items.
2. Display details of all items.
3. Enter customer and purchase details.
4. Display purchase details.
5. Display bill.
6. Display top customer details.
7. Display top item with details.
8. Quit.
Enter your choice: 1


Enter number of items:2


For item 1
Enter item number: 1
Enter name of the item: rice
Enter available quantity of the item in kilograms: 20
Enter the price per kilogram of the item: 40


For item 2
Enter item number: 2
Enter name of the item: wheat
Enter available quantity of the item in kilograms: 20
Enter the price per kilogram of the item: 50

1. Enter details of all items.
2. Display details of all items.
3. Enter customer and purchase details.
4. Display purchase details.
5. Display bill.
6. Display top customer details.
7. Display top item with details.
8. Quit.
Enter your choice: 2


Following are the details of item:
Item number: 1
Name: rice
Available quantity: 20 kilograms.
Price: Rs.40



Following are the details of item:
Item number: 2
Name: wheat
Available quantity: 20 kilograms.
Price: Rs.50


1. Enter details of all items.
2. Display details of all items.
3. Enter customer and purchase details.
4. Display purchase details.
5. Display bill.
6. Display top customer details.
7. Display top item with details.
8. Quit.
Enter your choice: 3


Enter number of customers:2

Enter quantity in kilograms of the following items to be purchased by customer 1
1. rice: 8
2. wheat: 6

Enter quantity in kilograms of the following items to be purchased by customer 2
1. rice: 4
2. wheat: 2

1. Enter details of all items.
2. Display details of all items.
3. Enter customer and purchase details.
4. Display purchase details.
5. Display bill.
6. Display top customer details.
7. Display top item with details.
8. Quit.
Enter your choice: 4


Customer number: 1
Item: 1. rice
Purchased quantity: 8
Item: 2. wheat
Purchased quantity: 6

Customer number: 2
Item: 1. rice
Purchased quantity: 4
Item: 2. wheat
Purchased quantity: 2
1. Enter details of all items.
2. Display details of all items.
3. Enter customer and purchase details.
4. Display purchase details.
5. Display bill.
6. Display top customer details.
7. Display top item with details.
8. Quit.
Enter your choice: 5


Enter the serial number of a customer to see his bill: 1
Item number purchased: 	Quantity purchased: 	Price:
0				8		40
Item number purchased: 	Quantity purchased: 	Price:
1				6		50


Bill for customer 1 is: Rs. 320
Item number purchased: 	Quantity purchased: 	Price:
0				8		40
Item number purchased: 	Quantity purchased: 	Price:
1				6		50


Bill for customer 1 is: Rs. 620

1. Enter details of all items.
2. Display details of all items.
3. Enter customer and purchase details.
4. Display purchase details.
5. Display bill.
6. Display top customer details.
7. Display top item with details.
8. Quit.
Enter your choice: 6


Top customer is customer 1 with bill Rs. 620


1. Enter details of all items.
2. Display details of all items.
3. Enter customer and purchase details.
4. Display purchase details.
5. Display bill.
6. Display top customer details.
7. Display top item with details.
8. Quit.
Enter your choice: 7


Top item with sale of 12 kilograms is:


Following are the details of item:
Item number: 1
Name: rice
Available quantity: 8 kilograms.
Price: Rs.40




1. Enter details of all items.
2. Display details of all items.
3. Enter customer and purchase details.
4. Display purchase details.
5. Display bill.
6. Display top customer details.
7. Display top item with details.
8. Quit.
Enter your choice: 8
Thank you
 */









