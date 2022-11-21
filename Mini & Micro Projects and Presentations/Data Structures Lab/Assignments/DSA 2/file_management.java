//	GAYATRI WALKE (ROLL NO. 2466)

package dsa_temp;

import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import java.util.Iterator;
import java.util.Scanner;

class student implements Serializable
{
	static Scanner input =new Scanner(System.in); //for input
	int roll_number;
	String name;
	int marks;
	
	student(int r,String n,int m) //constructor
	{
		marks=m;
		name=n;
		roll_number=r;
	}
	
	public String toString()
	{
		return "  Student{"+" Roll number : "+roll_number+"  Name : "+name+"  Marks : "+marks+" }";//display sequence
	}
}


public class file_management
{
	static Scanner input =new Scanner(System.in); //for input
	static int first;
	
	file_management() 
	{
		first=0;
	}
	
	void write_student_records_in_file()
	{
		
		Vector<student> vector = new Vector<student>();	//to store data
		int user_input=1;
		if(first!=0)
		{
			//read all the data from previous entries
			try
			{
				 File file_name = new File("Student_data.txt");
				 FileInputStream file_input = new FileInputStream(file_name);
				 ObjectInputStream object_input = new ObjectInputStream(file_input);
				 
				 Vector<student> v = (Vector<student>)object_input.readObject();
				 Iterator<student> iter = v.iterator();
				 while(iter.hasNext())
				 {
					 student s = iter.next();
					 vector.add(s);
			     }
				 file_input.close();
				 object_input.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
		}
		//new entries
		
		{
			while(user_input==1)		//loop 
			{
				first++;
				//		user inputs
				System.out.println();
				System.out.print("	Enter the student's roll : ");
				int roll_number = input.nextInt();
				System.out.print("	Enter the student's name : ");
				String name = input.next();
				System.out.print("	Enter the student's marks : ");
				int marks = input.nextInt();
				student student_obj = new student(roll_number,name,marks);
				//System.out.println(student_obj.toString());
				vector.add(student_obj);
				System.out.print("		To add another record press 1 else press 0 : ");
				user_input=input.nextInt();
			}
			
			try
			{
				File file_name = new File("Student_data.txt");	//create file
				
				FileOutputStream file_output = new FileOutputStream(file_name,false);
				ObjectOutputStream obj_output = new ObjectOutputStream(file_output);			
				
				obj_output.writeObject(vector);	//write into file
				
				obj_output.close();
				file_output.close();	//close files
				System.out.println();
				
				System.out.println("		Data written successfully.");
				
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}
			

	}
	
	void read_student_records_from_file()
	{
		System.out.println();
		int number=0;
		if(first==0)
			System.out.println("					THE RECORD IS EMPTY!");	//no entries
		else
		{
			try
			{
				 File file_name = new File("Student_data.txt");
				 FileInputStream file_input = new FileInputStream(file_name);
				 ObjectInputStream object_input = new ObjectInputStream(file_input);
				 
				 Vector<student> deserializeStudent =(Vector<student>) object_input.readObject();//read data from file and store into vector
				 
				 Iterator<student> iter = deserializeStudent.iterator();
				 
				 while(iter.hasNext())	//iterate through vector
				 {
					number++;
					student s = iter.next();
					System.out.println(s.toString());
				 }
				 
				 if(number==0)
					 System.out.println("					THE RECORD IS EMPTY!");	//no entries
				 file_input.close();
				 object_input.close();
				 
			 }
			//catch exceptions
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			} 
			catch (IOException e)
			{
				
				e.printStackTrace();
			} 
			catch (ClassNotFoundException e) 
			{
				
				e.printStackTrace();
			}			
		}
		
		
	}
	
	void search_record()
	{
		if(first==0)
			System.out.println("					THE RECORD IS EMPTY!");	//no entries
		else
		{
			int rn;
			int found=0;
			int number=0;
			student s=null;
			
			
			System.out.print("Enter the student's roll whose data is to be searched : ");//input key value
			rn = input.nextInt();
			
			try
			{
				 File file_name = new File("Student_data.txt");
				 FileInputStream file_input = new FileInputStream(file_name);
				 ObjectInputStream object_input = new ObjectInputStream(file_input);
				 
				 Vector<student> v = (Vector<student>) object_input.readObject();	//read data from file and store into vector
				 
				 Iterator<student> iter = v.iterator();
				 while(iter.hasNext())
				 {
					 number++;
					s = iter.next();
					if(s.roll_number==rn)	//check one by one
					{
						found=1;
						break;
					}
				 }
				 
				 file_input.close();
				 object_input.close();		//close objects
				 
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			System.out.println();
			if(number==0)
				System.out.println("					THE RECORD IS EMPTY!");		//no entries
			
			else if(found==1)
				System.out.println("	FOUND! : "+s.toString());
			else
				System.out.println("	SORRY! NOT FOUND.");
			
		}
		
		
	}
	
	
	void delete()
	{
		if(first==0)
			System.out.println("					THE RECORD IS EMPTY!");	//no entries
		else	
		{
			int rn;
			int found=0;
			int number=0;
			student s=null;
			System.out.print("Enter the student's roll whose data is to be deleted : ");
			rn = input.nextInt();
			try
			{
				 File file_name = new File("Student_data.txt");
				 FileInputStream file_input = new FileInputStream(file_name);
				 ObjectInputStream object_input = new ObjectInputStream(file_input);
				 
				 Vector<student> v = (Vector<student>) object_input.readObject();	//read data from file and store into vector
				
				 Iterator<student> iter = v.iterator();
				 while(iter.hasNext())	//iterate all the data 
				 {
					s = iter.next();
					number++;
					if(s.roll_number==rn)
					{					
						found=1;
						break;
					}
				 }
				 file_input.close();
				 object_input.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			System.out.println();
			if(number==0)
				System.out.println("					THE RECORD IS EMPTY!");	//no entries
			else if(found!=1)
				System.out.println("	SORRY! NOT FOUND.");
			else
			{
				System.out.println("	FOUND! : "+s.toString()+" deleted!");
				try
				{
					 File file_name = new File("Student_data.txt");
					 
					 FileInputStream file_input = new FileInputStream(file_name);
					 ObjectInputStream object_input = new ObjectInputStream(file_input);
					 
					 Vector<student> v = (Vector<student>) object_input.readObject();	//read data from file and store into vector
					 Vector<student> v_temp =  new Vector<student>();					//store new vector after deletion
					 
					 Iterator<student> iter = v.iterator();
					 while(iter.hasNext())	//iterate 
					 {
						s = iter.next();
						if(s.roll_number!=rn)
						{
							v_temp.add(s);
						}
					 }
					 file_input.close();
					object_input.close();

					FileOutputStream file_output = new FileOutputStream(file_name,false);
					ObjectOutputStream obj_output = new ObjectOutputStream(file_output);			
					
					obj_output.writeObject(v_temp);	//write into file
					
					obj_output.close();
					file_output.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					
				}
			}
			
		}
		
		
	}
	
	
	public static void main(String args[])
	{
		file_management call= new file_management();	//object to call functions
		
		int choice;	//for user input	
		do 
		{
			
			System.out.println("---------------------------------------------------------------------------------------");
			//display menu
			System.out.println("ENTER YOUR CHOICE : ");
			System.out.println("1. READ FROM FILE");
			System.out.println("2. WRITE TO FILE");
			System.out.println("3. SEARCH A RECORD IN FILE");
			System.out.println("4. DELETE A RECORD IN FILE");
			System.out.println("0. EXIT");
			System.out.print("enter : ");
			choice=input.nextInt();
			//call function based on choice 
			switch(choice)
			{
				case 1:
					//read from file
					call.read_student_records_from_file();
					break;
					
				case 2:
					//write to file
					call.write_student_records_in_file();
					break;
					
				case 3:
					//search a record in file
					call.search_record();
					break;
				
				case 4:
					//delete a record from file
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


//			OUTPUT
/*---------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. READ FROM FILE
2. WRITE TO FILE
3. SEARCH A RECORD IN FILE
4. DELETE A RECORD IN FILE
0. EXIT
enter : 1

					THE RECORD IS EMPTY!

---------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. READ FROM FILE
2. WRITE TO FILE
3. SEARCH A RECORD IN FILE
4. DELETE A RECORD IN FILE
0. EXIT
enter : 2

	Enter the student's roll : 1
	Enter the student's name : Abc 
	Enter the student's marks : 98
		To add another record press 1 else press 0 : 1

	Enter the student's roll : 2
	Enter the student's name : Bcd
	Enter the student's marks : 93
		To add another record press 1 else press 0 : 1

	Enter the student's roll : 3
	Enter the student's name : Efg
	Enter the student's marks : 96
		To add another record press 1 else press 0 : 1

	Enter the student's roll : 4
	Enter the student's name : Hij
	Enter the student's marks : 99
		To add another record press 1 else press 0 : 0

		Data written successfully.

---------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. READ FROM FILE
2. WRITE TO FILE
3. SEARCH A RECORD IN FILE
4. DELETE A RECORD IN FILE
0. EXIT
enter : 1

  Student{ Roll number : 1  Name : Abc  Marks : 98 }
  Student{ Roll number : 2  Name : Bcd  Marks : 93 }
  Student{ Roll number : 3  Name : Efg  Marks : 96 }
  Student{ Roll number : 4  Name : Hij  Marks : 99 }

---------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. READ FROM FILE
2. WRITE TO FILE
3. SEARCH A RECORD IN FILE
4. DELETE A RECORD IN FILE
0. EXIT
enter : 3
Enter the student's roll whose data is to be searched : 5

	SORRY! NOT FOUND.

---------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. READ FROM FILE
2. WRITE TO FILE
3. SEARCH A RECORD IN FILE
4. DELETE A RECORD IN FILE
0. EXIT
enter : 3
Enter the student's roll whose data is to be searched : 2

	FOUND! :   Student{ Roll number : 2  Name : Bcd  Marks : 93 }

---------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. READ FROM FILE
2. WRITE TO FILE
3. SEARCH A RECORD IN FILE
4. DELETE A RECORD IN FILE
0. EXIT
enter : 4
Enter the student's roll whose data is to be deleted : 6

	SORRY! NOT FOUND.

---------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. READ FROM FILE
2. WRITE TO FILE
3. SEARCH A RECORD IN FILE
4. DELETE A RECORD IN FILE
0. EXIT
enter : 4
Enter the student's roll whose data is to be deleted : 1

	FOUND! :   Student{ Roll number : 1  Name : Abc  Marks : 98 } deleted!

---------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. READ FROM FILE
2. WRITE TO FILE
3. SEARCH A RECORD IN FILE
4. DELETE A RECORD IN FILE
0. EXIT
enter : 1

  Student{ Roll number : 2  Name : Bcd  Marks : 93 }
  Student{ Roll number : 3  Name : Efg  Marks : 96 }
  Student{ Roll number : 4  Name : Hij  Marks : 99 }

---------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. READ FROM FILE
2. WRITE TO FILE
3. SEARCH A RECORD IN FILE
4. DELETE A RECORD IN FILE
0. EXIT
enter : 1

  Student{ Roll number : 2  Name : Bcd  Marks : 93 }
  Student{ Roll number : 3  Name : Efg  Marks : 96 }
  Student{ Roll number : 4  Name : Hij  Marks : 99 }

---------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. READ FROM FILE
2. WRITE TO FILE
3. SEARCH A RECORD IN FILE
4. DELETE A RECORD IN FILE
0. EXIT
enter : 4
Enter the student's roll whose data is to be deleted : 2

	FOUND! :   Student{ Roll number : 2  Name : Bcd  Marks : 93 } deleted!

---------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. READ FROM FILE
2. WRITE TO FILE
3. SEARCH A RECORD IN FILE
4. DELETE A RECORD IN FILE
0. EXIT
enter : 4
Enter the student's roll whose data is to be deleted : 3

	FOUND! :   Student{ Roll number : 3  Name : Efg  Marks : 96 } deleted!

---------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. READ FROM FILE
2. WRITE TO FILE
3. SEARCH A RECORD IN FILE
4. DELETE A RECORD IN FILE
0. EXIT
enter : 4
Enter the student's roll whose data is to be deleted : 4

	FOUND! :   Student{ Roll number : 4  Name : Hij  Marks : 99 } deleted!

---------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. READ FROM FILE
2. WRITE TO FILE
3. SEARCH A RECORD IN FILE
4. DELETE A RECORD IN FILE
0. EXIT
enter : 1

					THE RECORD IS EMPTY!

---------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. READ FROM FILE
2. WRITE TO FILE
3. SEARCH A RECORD IN FILE
4. DELETE A RECORD IN FILE
0. EXIT
enter : 4
Enter the student's roll whose data is to be deleted : 1

					THE RECORD IS EMPTY!

---------------------------------------------------------------------------------------
ENTER YOUR CHOICE : 
1. READ FROM FILE
2. WRITE TO FILE
3. SEARCH A RECORD IN FILE
4. DELETE A RECORD IN FILE
0. EXIT
enter : 0

			THANK YOU

*/
