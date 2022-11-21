// 			HASHING
// Gayatri Walke (Roll no. - 2466) 


package dsa_temp;

import java.util.Scanner;

class client_details
{
	//details of client
	int client_id;
	long telephone_number;
	client_details next_link;//next link
	
	client_details() //constructor
	{
		client_id=0;
		telephone_number=0;
		next_link=null;
	}
	
	client_details(int id,long tn) //Parameterized constructor
	{
		client_id=id;
		telephone_number=tn;
	}
	
}
public class hashing 
{
	client_details hashtable[] = new client_details[10];//hash table keys 
	
	static Scanner input =new Scanner(System.in); //for input
	
	int hash_function(int clientid)	//hash function for the hash table
	{
		return (clientid%10);
	}
	
	void insert_client()
	{
		long tn;
		int c_id;
		int hash_add;
		client_details ptr=null;
		
		System.out.print("	enter the client id : ");
		c_id=input.nextInt();
		System.out.print("	enter the phone number of client : ");		//accept inputs
		tn=input.nextLong();
		
		int found=0;
		
		for(int hash_point=0;hash_point<10;hash_point++)	//iterate all key values
		{
		    ptr = hashtable[hash_point];
			while(ptr!=null&&found==0)//traverse through list
			{
				if(ptr.client_id==c_id)
				{
					found=1;
					break;
				}
				ptr=ptr.next_link;
				
			}
			if(found==1)
				break;
		}
		
		if(found==1)	//if the entry is already present
			System.out.println(" Sorry! Entry already present.");
		else
		{
			client_details temp = new client_details(c_id,tn);
			hash_add=hash_function(c_id);		//get key value
			
			//add to the list
			if(hashtable[hash_add]==null)	//if list of the key is empty
			{
				hashtable[hash_add]=temp;		//add
			}
			else
			{
				client_details ptr1=hashtable[hash_add];
				
				while(ptr1.next_link!=null)		//traverse to end 
					ptr1=ptr1.next_link;
				
				ptr1.next_link=temp;		//add
			}
		}	
		
	}
	
	void display(client_details ptr)
	{
		//display details
		System.out.println("  CLIENT ID : "+ptr.client_id);
		System.out.println("  TELEPHONE NUMBER : "+ ptr.telephone_number);
	}
	
	void display_client_list()
	{
		int found=0;
		for(int hash_point=0;hash_point<10;hash_point++) //iterate all key values
		{
			client_details ptr = hashtable[hash_point];
			while(ptr!=null)	//traverse through list
			{
				found++;
				display(ptr);
				ptr=ptr.next_link;
			}
		}
		
		if(found==0)	//if list is empty
			System.out.println("The list is empty!");
	}
	
	void search()
	{
		client_details ptr=null;
		int c_id;
		System.out.print("	enter the client id : ");		//get input from user
		c_id = input.nextInt();
		
		int found=0;
		
		for(int hash_point=0;hash_point<10;hash_point++)	//iterate all key values
		{
		    ptr = hashtable[hash_point];
			while(ptr!=null&&found==0)	//traverse through list
			{
				if(ptr.client_id==c_id)
				{
					found=1;
					break;
				}
				ptr=ptr.next_link;
				
			}
			if(found==1)
				break;
		}
		
		if(found==0)// if not found
			System.out.println(" Sorry! Entry not found.");
		else
		{
			System.out.println("Entry found!");
			display(ptr);
		}
	}
	
	
	void delete()
	{
		client_details ptr=null;
		client_details parent=null;
		int hash_point=0;
		int c_id;
		System.out.print("	enter the client id whose entry is to be deleted : ");	//get input from user
		c_id = input.nextInt();
		int found=0;
		for(hash_point=0;hash_point<10;hash_point++)//iterate all key values
		{
		    ptr = hashtable[hash_point];
		    parent=null;
			while(ptr!=null&&found==0)
			{
				if(ptr.client_id==c_id)	//traverse through list
				{
					found=1;
					break;
				}
				parent=ptr;
				ptr=ptr.next_link;
				
			}
			if(found==1)
				break;
		}
		
		if(found==0)
			System.out.println(" Sorry! Entry not found.");
		else
		{
			System.out.println("Entry found!");	//delete
			display(ptr);
			if(parent==null)		//if parent is null
				{
				  if(hashtable[hash_point].next_link==null)
					  hashtable[hash_point]=null;
				  else									//change the head
				  {
					  hashtable[hash_point]=hashtable[hash_point].next_link;
				  }
				}
			else
			{
				parent.next_link=ptr.next_link;			//delete
				ptr=null;
			}
			
			System.out.println("Deleted!");
		}
		
	}
	
	public static void main(String args[])
	{
		hashing call= new hashing();	//object to call functions
		
		int choice;	//for user input	
		do 
		{
			
			System.out.println("-------------------------------------------------");
			//display menu
			System.out.println("ENTER YOUR CHOICE : ");
			System.out.println("1. INSERT CLIENT");
			System.out.println("2. DISPLAY CLIENT LIST");
			System.out.println("3. SEARCH CLIENT");
			System.out.println("4. DELETE CLIENT");
			System.out.println("0. EXIT");
			System.out.print("enter : ");
			choice=input.nextInt();
			//call function based on choice 
			switch(choice)
			{
				case 1:
					//insert client
					call.insert_client();
					break;
					
				case 2:
					//display client list
					call.display_client_list();
					break;
					
				case 3:
					//search client
					call.search();
					break;
				
				case 4:
					//delete client
					call.delete();
					break;
				
				case 0:
					//exit
					System.out.println("\n			THANK YOU");
					break;
					
				default:
					System.out.println("\n		enter valid choice!");
						break;
			}
			System.out.println();
		}while(choice!=0);
		
	}
	
	
}
	
//				OUTPUT
/*-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 1
	enter the client id : 12
	enter the phone number of client : 9168425534

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 44

		enter valid choice!

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 1
	enter the client id : 44
	enter the phone number of client : 9689889168

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 1
	enter the client id : 44
	enter the phone number of client : 9686775436
 Sorry! Entry already present.

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 1
	enter the client id : 13
	enter the phone number of client : 9689889167

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 88

		enter valid choice!

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 1
	enter the client id : 88
	enter the phone number of client : 9689889166

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 2
  CLIENT ID : 12
  TELEPHONE NUMBER : 9168425534
  CLIENT ID : 13
  TELEPHONE NUMBER : 9689889167
  CLIENT ID : 44
  TELEPHONE NUMBER : 9689889168
  CLIENT ID : 88
  TELEPHONE NUMBER : 9689889166

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 1
	enter the client id : 22
	enter the phone number of client : 9689889165

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 2
  CLIENT ID : 12
  TELEPHONE NUMBER : 9168425534
  CLIENT ID : 22
  TELEPHONE NUMBER : 9689889165
  CLIENT ID : 13
  TELEPHONE NUMBER : 9689889167
  CLIENT ID : 44
  TELEPHONE NUMBER : 9689889168
  CLIENT ID : 88
  TELEPHONE NUMBER : 9689889166

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 3
	enter the client id : 99
 Sorry! Entry not found.

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 3
	enter the client id : 44
Entry found!
  CLIENT ID : 44
  TELEPHONE NUMBER : 9689889168

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 1
	enter the client id : 99
	enter the phone number of client : 9689889161

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 4
	enter the client id whose entry is to be deleted : 48
 Sorry! Entry not found.

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 4
	enter the client id whose entry is to be deleted : 99
Entry found!
  CLIENT ID : 99
  TELEPHONE NUMBER : 9689889161
Deleted!

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 2
  CLIENT ID : 12
  TELEPHONE NUMBER : 9168425534
  CLIENT ID : 22
  TELEPHONE NUMBER : 9689889165
  CLIENT ID : 13
  TELEPHONE NUMBER : 9689889167
  CLIENT ID : 44
  TELEPHONE NUMBER : 9689889168
  CLIENT ID : 88
  TELEPHONE NUMBER : 9689889166

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 4
	enter the client id whose entry is to be deleted : 12
Entry found!
  CLIENT ID : 12
  TELEPHONE NUMBER : 9168425534
Deleted!

-------------------------------------------------
ENTER YOUR CHOICE : 
1. INSERT CLIENT
2. DISPLAY CLIENT LIST
3. SEARCH CLIENT
4. DELETE CLIENT
0. EXIT
enter : 0

			THANK YOU

*/
