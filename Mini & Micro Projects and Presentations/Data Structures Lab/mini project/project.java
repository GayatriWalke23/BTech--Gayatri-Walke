import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;
import java.io.Serializable;

class vector_obj implements Serializable//object going in file
{
	int sector_number;
	int domain_number;
	int add;
	int number_obj_list;
	int list_number;
	protected String sector[];
	protected String domain[];
	protected String job_internship[];
	protected String place[];
	protected String time[];
	protected String education[];
	protected String year_of_study[];
	protected String experience[];   
	protected String salary[];
	protected String Company_name[];
	protected String Location[];
	protected String job_des[];
	
	
	public vector_obj() //constructor
	{
		add=-1;//default values
		list_number=-1;
		number_obj_list=0;		
	}
	
	void initialize(int i)//initializing values as per sector number
	{
		number_obj_list=i;
		sector=new String[i];
		domain=new String[i];
		job_internship=new String[i];
		place=new String[i];
		time=new String[i];
		education=new String[i];
		year_of_study=new String[i];
		experience=new String[i];
		salary=new String[i];
		Company_name=new String[i];
		Location=new String[i];
		job_des=new String[i];
	}
	
	void assign(int s,int d,int a, int list_no,String sec,String dom,String ji,String pl,String t,String e,String ys,String ex,String sal,String cn,String loc,String jd)
	{//assigning respective values
		domain_number=d;//similar to setter methods
		sector_number=s;
		add=a;
		list_number=list_no;
		int i=list_no;
		sector[i]=sec;
		domain[i]=dom;
		job_internship[i]=ji;
		place[i]=pl;
		time[i]=t;
		education[i]=e;
		year_of_study[i]=ys;
		experience[i]=ex;   
		salary[i]=sal;
		Company_name[i]=cn;
		Location[i]=loc;
		job_des[i]=jd;
		
	}
	
	
	
}
class node //node for our graph
{
	String name;
	int number_nodes;
	String message;
	node next_link[];
	int address;//for calculating hash address

	node()//constructor
	{
		name=null;
		message=null;
		number_nodes=0; 
		address = -1;//default value
	}

	void assign(String n,String m, int no,int add)
	{
		name=n;//assigninf - similar to setter methods
		message=m;
		number_nodes=no;
		next_link=new node[no];
		address = add;
	}
}
class hash 
{// class with all hash and file functions
	protected String sector;
	protected node domain;
	protected node job_internship;
	protected node place;
	protected node time;
	protected node education;
	protected String year_of_study;
	protected node experience;   
	protected String salary;
	protected String Company_name;
	protected String Location;
	String jd;//job description
	int domain_num;
	int sector_num;//address for hash table
	//getter setters 
	public String getCompany_name() {
		return Company_name;
	}
	public void setCompany_name(String company_name) {
		Company_name = company_name;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getJd() {
		return jd;
	}
	public void setJd(String jd) {
		this.jd = jd;
	}
	@SuppressWarnings("unchecked")
	private LinkedList<hash> hash_table_it[] = new LinkedList[768];//array of linked lists of size 768 for It
	@SuppressWarnings("unchecked")
	private LinkedList<hash> hash_table_engg[] = new LinkedList[448];//array of linked lists for engineering
	@SuppressWarnings("unchecked")
	private LinkedList<hash> hash_table_business[] = new LinkedList[384];//array of linked lists  for business
	@SuppressWarnings("unchecked")
	private LinkedList<hash> hash_table_sales[] = new LinkedList[448];//array of linked lists for sales
	@SuppressWarnings("unchecked")
	private LinkedList<hash> hash_table_management[] = new LinkedList[576];//array of linked lists for management

	hash()//constructor - creates linked list objects
	{
		for(int i = 0;i<768;i++)
		{
			hash_table_it[i] = new LinkedList<hash>();
		}
		for(int i = 0;i<448;i++)
		{
			hash_table_engg[i] = new LinkedList<hash>();
		}
		for(int i = 0;i<384;i++)
		{
			hash_table_business[i] = new LinkedList<hash>();
		}
		for(int i = 0;i<448;i++)
		{
			hash_table_sales[i] = new LinkedList<hash>();
		}
		for(int i = 0;i<576;i++)
		{
			hash_table_management[i] = new LinkedList<hash>();
		}
	}
	
	void print_all_jobs()
	{//displaying
		LinkedList<hash> hash_table[] = null;
		for(int i=1;i<6;i++)
		{
			if(i == 1)
			{
				hash_table=this.hash_table_it;
			}
			else if(i == 2)
			{
				hash_table=this.hash_table_engg;
			}
			else if(i == 3)
			{
				hash_table=this.hash_table_business;
			}
			else if(i == 4)
			{
				hash_table=this.hash_table_sales;
			}
			else if(i == 5)
			{
				hash_table=this.hash_table_management;
			}	
			for(int j=0;j<hash_table.length;j++)
			{
				Iterator<hash> it;
				it = hash_table[j].iterator();
				
				while(it.hasNext())//display
				{
					hash h = it.next();
					if(h!=null)
						Graph.display_search_hash(h);
				}
				
			}
		}
	}

	private int calc_add(hash h)//hash function
	{
		int generated_address;
		if(h.experience==null)//internship
		{
			generated_address = h.domain.address+h.job_internship.address+h.time.address+h.place.address+h.education.address;
		}
		else//job
		{
			generated_address = h.domain.address+h.job_internship.address+h.time.address+h.place.address+h.experience.address+h.education.address;
		}
		return generated_address;
	}

	

	public void add(hash h)//adds records in respective hash table
	{
		int add = calc_add(h);//calculates address
		if(h.sector_num == 1)
		{//it
			this.hash_table_it[add].add(h);
		}
		if(h.sector_num == 2)
		{//engineering
			this.hash_table_engg[add].add(h);
		}
		if(h.sector_num == 3)
		{//business
			this.hash_table_business[add].add(h);
		}
		if(h.sector_num == 4)
		{//sales
			this.hash_table_sales[add].add(h);
		}
		if(h.sector_num == 5)
		{//management
			this.hash_table_management[add].add(h);;
		}

	}
	
	void copy_previous_data()
	{//reading from file
		String file_name[]=new String[6];

		file_name[1]="it.txt";
		file_name[2]="engg.txt";
		file_name[3]="business.txt";
		file_name[4]="sales.txt";
		file_name[5]="management.txt";
		for(int i=1;i<6;i++)//for all 6 files of all sectors
		{
			try
			{
				File file_name_ = new File(file_name[i]);
				if(!file_name_.exists())
				{//if file doesnt exist create file
				    file_name_.createNewFile();
				    
				}
				FileInputStream file_input = new FileInputStream(file_name_);
				int pass = file_input.available();
				if(pass != 0)//to prevent EOF exception
				{
					ObjectInputStream object_input = new ObjectInputStream(file_input);
					@SuppressWarnings("unchecked")
					Vector<vector_obj> prev = (Vector)object_input.readObject();
					Iterator<vector_obj> it;
					it = prev.iterator();
					Graph g = new Graph();
					while(it.hasNext())
					{
						vector_obj v=it.next();
						hash add_obj= new hash();
						{
							int  add = v.add;
							int sector = v.sector_number;
							add_obj= g.set(v.sector_number, v.domain_number, v.job_internship[v.list_number], v.place[v.list_number], v.time[v.list_number], v.education[v.list_number], v.year_of_study[v.list_number], v.experience[v.list_number], v.salary[v.list_number], v.Company_name[v.list_number], v.Location[v.list_number],v.job_des[v.list_number]);
							{                   									
								//After reading add obj in sector ";
								if(sector==1)
									this.hash_table_it[add].add(add_obj);

								else if(sector==2)
									this.hash_table_engg[add].add(add_obj);

								else if(sector==3)
									this.hash_table_business[add].add(add_obj);

								else if(sector==4)
									this.hash_table_sales[add].add(add_obj);
								
								else if(sector==5)
									this.hash_table_management[add].add(add_obj);
							}
						}
					}
					file_input.close();//closing all streams
					object_input.close();
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();

			}
		}

	}

	void write_new_updates()//stores record in file 
	{
		
		String file_name[]=new String[6];

		file_name[1]="it.txt";
		file_name[2]="engg.txt";
		file_name[3]="business.txt";
		file_name[4]="sales.txt";
		file_name[5]="management.txt";
		for(int i=1;i<6;i++)
		{
			try
			{
				File file_name_ = new File(file_name[i]);
				Vector<vector_obj> vector = new Vector<vector_obj>(); 
				FileOutputStream file_output = new FileOutputStream(file_name_);
				ObjectOutputStream obj_output = new ObjectOutputStream(file_output);            
				obj_output.reset();  
				LinkedList<hash> hash_table[] = null;
				if(i==1)
					hash_table=hash_table_it;//obj_output.writeObject(hash_table_it);
				else if(i==2)
					hash_table=hash_table_engg;//obj_output.writeObject(hash_table_engg); 
				else if(i==3)
					hash_table=hash_table_business;//obj_output.writeObject(hash_table_business);                
				else if(i==4)
					hash_table=hash_table_sales;//obj_output.writeObject(hash_table_sales);               
				else if(i==5)
					hash_table=hash_table_management;//obj_output.writeObject(hash_table_management);              
				
				
				for(int j=0;j<hash_table.length;j++)
				{
					Iterator<hash> it;
					it = hash_table[j].iterator();
					
					int n_ele=0;
					
					while(it.hasNext())//display
					{
						vector_obj o= new vector_obj();
						o.initialize(hash_table[j].size());
						hash h = it.next();
						
						if(h.year_of_study==null)
							o.assign(h.sector_num,h.domain_num,j,n_ele , h.sector , h.domain.name, h.job_internship.name,h.place.name, h.time.name, h.education.name,null,h.experience.name, h.salary, h.Company_name, h.Location,h.jd);
						else
							o.assign(h.sector_num,h.domain_num,j,n_ele , h.sector , h.domain.name, h.job_internship.name,h.place.name, h.time.name, h.education.name, h.year_of_study,null, h.salary, h.Company_name, h.Location,h.jd);
						
						vector.add(o);
					}
					
				}
					

				obj_output.writeObject(vector);
				obj_output.close();
				file_output.close();    //close files
			
			}
			catch(Exception e)
			{
				e.printStackTrace();

			}
			
		}
			
	}
	
	public void search(hash h)//searches for the right hash table and displays all records
	{
		int add = calc_add(h);
		
		//System.out.println(" search at"+add);
		Iterator<hash> it ;
		if(h.sector_num == 1)//it
		{
			 it = hash_table_it[add].iterator();
		}
		else if(h.sector_num == 2)//engineering
		{
		   it = hash_table_engg[add].iterator();
		}
		else if(h.sector_num == 3)//business
		{
			 it = hash_table_business[add].iterator();
		}
		else if(h.sector_num == 4)//sales
		{
			 it = hash_table_sales[add].iterator();
		}
		else
		{//management
			 it = hash_table_management[add].iterator();
		}
	
		if(!it.hasNext())//no records - validation
		{
			System.out.println(" \n \n   Sorry there is no job posted yet! Keep checking :) ");//no jobs yet for those preferences
			System.out.println(" Try some different search!");
		}
		while(it.hasNext())//display
		{
			hash n = it.next();
			Graph.display_search_hash(n);
		}

	}
}

class Graph extends hash
{
	hash o=new hash();//object of hash
	static int flag=0;//tells whether job is selected or internship
	node root = new node();
	node job_sectors ;

	node Information_Technology;//sectors nodes
	node Engineering ;
	node Bussiness ;
	node Sales;
	node Management ;

	node Job = new node();
	node Internship = new node();

	node Time= new node();
	node Site= new node();
	node Education= new node();
	node Graduation_year=new node();
	node Post_Graduation_year=new node();

	node  Experience= new node();
	node  Year= new node();

	Graph()//sets all nodes to their value 
	{
		root.assign("recruiter",null,1,-1);
		for(int i=0;i<root.number_nodes;i++)
			root.next_link[i]=new node();

		job_sectors=root.next_link[0];


		job_sectors.assign(" Job ","Please select the sector in which you want to post your next job : ",5,-1 );

		for(int i=0;i<job_sectors.number_nodes;i++)
			job_sectors.next_link[i]=new node();//creating objects


		Information_Technology = job_sectors.next_link[0];
		Engineering = job_sectors.next_link[1];
		Bussiness=job_sectors.next_link[2];
		Sales=job_sectors.next_link[3];
		Management=job_sectors.next_link[4];

		String domain_msg = "Please select the domain you want to proceed with : ";  
		Information_Technology.assign("Information Technology",domain_msg,11,-1);
		Engineering.assign("Engineering",domain_msg, 6,-1);
		Bussiness.assign("Bussiness",domain_msg, 5,-1);
		Sales.assign("Sales",domain_msg,6,-1);
		Management.assign("Management",domain_msg, 8,-1);

		for(int i=0;i<job_sectors.number_nodes;i++)
		{//creating objects
			for(int i1=0;i1<job_sectors.next_link[i].number_nodes;i1++)
				job_sectors.next_link[i].next_link[i1]=new node();
		}

		String Job_msg = "Are you looking for a 'Employee' or an 'Intern'? : ";
		Information_Technology.next_link[0].assign("Android Development",Job_msg,2,0);//all domains of IT
		Information_Technology.next_link[1].assign("Cloud domain",Job_msg,2,64);
		Information_Technology.next_link[2].assign("Big Data",Job_msg,2,128);
		Information_Technology.next_link[3].assign("SAP",Job_msg,2,192);
		Information_Technology.next_link[4].assign("Network sequrity",Job_msg,2,256);
		Information_Technology.next_link[5].assign("AI/ML",Job_msg,2,320);
		Information_Technology.next_link[6].assign("Web tooling",Job_msg,2,384);
		Information_Technology.next_link[7].assign("Data Science",Job_msg,2,448);
		Information_Technology.next_link[8].assign("Internet of Things",Job_msg,2,512);
		Information_Technology.next_link[9].assign("Biomedical",Job_msg,2,576);
		Information_Technology.next_link[10].assign("Robotics",Job_msg,2,640);

		Engineering.next_link[0].assign("Manufacturing",Job_msg,2,0);//all domains of engineering
		Engineering.next_link[1].assign("Mechanical Design",Job_msg,2,64);
		Engineering.next_link[2].assign("Transportation Systems",Job_msg,2,128);
		Engineering.next_link[3].assign("SAS",Job_msg,2,192);
		Engineering.next_link[4].assign("Construction Management",Job_msg,2,256);
		Engineering.next_link[5].assign("Geotechnical",Job_msg,2,320);

		Bussiness.next_link[0].assign("Health care",Job_msg ,2,0);//all domains of business
		Bussiness.next_link[1].assign("Aviation",Job_msg ,2,64);
		Bussiness.next_link[2].assign("Finance/Banking",Job_msg ,2,128);
		Bussiness.next_link[3].assign("Military",Job_msg ,02,192);
		Bussiness.next_link[4].assign("Retail",Job_msg, 02,256);


		Sales.next_link[0].assign("Executive",Job_msg,02,0);//all domains of sales
		Sales.next_link[1].assign("Development",Job_msg,02,64);
		Sales.next_link[2].assign("Inside sales",Job_msg,02,128);
		Sales.next_link[3].assign("Outside sales",Job_msg,02,192);
		Sales.next_link[4].assign("Management",Job_msg,02,256);
		Sales.next_link[5].assign("Consulative",Job_msg,02,320);

		Management.next_link[0].assign("Event Management",Job_msg ,02,0);//all domains of management
		Management.next_link[1].assign("Hotel Management",Job_msg,02,64);
		Management.next_link[2].assign("Office Management",Job_msg, 02,128);
		Management.next_link[3].assign("Administrative", Job_msg,02,192);
		Management.next_link[4].assign("Health Services", Job_msg,02,256);
		Management.next_link[5].assign("Marketing", Job_msg,02,320);
		Management.next_link[6].assign("Advertising", Job_msg,02,384);
		Management.next_link[7].assign("Human Resource",Job_msg ,02,448);


		for(int i=0;i<job_sectors.number_nodes;i++)
		{
			for(int i1=0;i1<job_sectors.next_link[i].number_nodes;i1++)
			{//setting connections
				job_sectors.next_link[i].next_link[i1].next_link[0]=Job;
				job_sectors.next_link[i].next_link[i1].next_link[1]=Internship;
				job_sectors.next_link[i].next_link[i1].next_link[0].assign("Job",null,4,0);
				job_sectors.next_link[i].next_link[i1].next_link[1].assign("Internship",null, 4,32);
			}
		}
		for(int i=0;i<job_sectors.next_link[0].next_link[0].next_link[0].number_nodes;i++){
			Job.next_link[i]=new node();//creating new objects
		}
		for(int i=0;i<job_sectors.next_link[0].next_link[0].next_link[1].number_nodes;i++){
			Internship.next_link[i]=new node();
		}
		for(int i=0;i<job_sectors.number_nodes;i++)
		{
			for(int i1=0;i1<job_sectors.next_link[i].number_nodes;i1++)
			{//setting edges of graph
				job_sectors.next_link[i].next_link[i1].next_link[0].next_link[0]=Time;
				job_sectors.next_link[i].next_link[i1].next_link[0].next_link[1]=Site;
				job_sectors.next_link[i].next_link[i1].next_link[0].next_link[2]=Education;
				job_sectors.next_link[i].next_link[i1].next_link[1].next_link[0]=Time;
				job_sectors.next_link[i].next_link[i1].next_link[1].next_link[1]=Site;
				job_sectors.next_link[i].next_link[i1].next_link[1].next_link[2]=Education;
				job_sectors.next_link[i].next_link[i1].next_link[0].next_link[0].assign("Time","Please post the working hours as per your need : ", 2,-1);
				job_sectors.next_link[i].next_link[i1].next_link[0].next_link[1].assign("Site", "  Please select working site of your choice : ",2,-1);
				job_sectors.next_link[i].next_link[i1].next_link[0].next_link[2].assign("Education", "Please enter basic education details for qualification : ",2,-1);
				job_sectors.next_link[i].next_link[i1].next_link[1].next_link[0].assign("Time","Please post the working hours as per your need : ", 2,-1);
				job_sectors.next_link[i].next_link[i1].next_link[1].next_link[1].assign("Site","  Please select working site of your choice : ", 2,-1);
				job_sectors.next_link[i].next_link[i1].next_link[1].next_link[2].assign("Education", "Please enter basic details for qualification : ",2,-1);

			}
		}
		for(int i=0;i<job_sectors.next_link[0].next_link[0].next_link[0].next_link[0].number_nodes;i++)
		{//creating new nodes
			Time.next_link[i]=new node();
		}
		Time.next_link[0].assign("Full Time",null, 0,0);
		Time.next_link[1].assign("Part Time",null, 0,16);

		for(int i=0;i<job_sectors.next_link[0].next_link[0].next_link[0].next_link[1].number_nodes;i++)
		{
			Site.next_link[i]=new node();
		}
		Site.next_link[0].assign("Site",null, 0,0);
		Site.next_link[1].assign("Home",null, 0,8);


		for(int i=0;i<job_sectors.next_link[0].next_link[0].next_link[0].next_link[2].number_nodes;i++)
		{
			Education.next_link[i]=new node();
		}
		for(int i=0;i<job_sectors.number_nodes;i++)
		{
			for(int i1=0;i1<job_sectors.next_link[i].number_nodes;i1++)
			{//connections

				job_sectors.next_link[i].next_link[i1].next_link[0].next_link[2].next_link[0]=Graduation_year;
				job_sectors.next_link[i].next_link[i1].next_link[1].next_link[2].next_link[1]=Post_Graduation_year;
				job_sectors.next_link[i].next_link[i1].next_link[0].next_link[2].next_link[0].assign("Graduation","Select the present year of studying : ", 4,0);
				job_sectors.next_link[i].next_link[i1].next_link[1].next_link[2].next_link[1].assign("Post Graduation","Select the present year studying : ", 2,2);

			}
		}

		for(int i=0;i<Education.next_link[0].number_nodes;i++)
		{
			Graduation_year.next_link[i]=new node();
		}
		for(int i=0;i<Education.next_link[1].number_nodes;i++)
		{
			Post_Graduation_year.next_link[i]=new node();
		}

		Graduation_year.next_link[0].assign("First year",null,0,-1);//this will not act as a search filter will be present for recruiters ease
		Graduation_year.next_link[1].assign("Second year",null,0,-1);
		Graduation_year.next_link[2].assign("Third year",null,0,-1);
		Graduation_year.next_link[3].assign("Fourth year",null,0,-1);

		Post_Graduation_year.next_link[0].assign("FY",null,0,-1);
		Post_Graduation_year.next_link[1].assign("SY",null,0,-1);


		Job.next_link[3].assign("Experience","Choose your experience level",4,-1);
		Experience=Job.next_link[3];
		for(int i=0;i<job_sectors.next_link[0].next_link[0].next_link[0].next_link[3].number_nodes;i++)
		{
			Experience.next_link[i]=new node();
		}
		Experience.next_link[0].assign("Fresher",null, 0,0);//experience 
		Experience.next_link[1].assign("1-5 years",null, 0,2);
		Experience.next_link[2].assign("5-10 years",null, 0,4);
		Experience.next_link[3].assign("Greater than 10 years",null,0, 6); 
	}
	Scanner sc=new Scanner(System.in);//scanner for input
	
	hash set(int sec,int dom,String ji,String pl,String t,String e,String ys,String ex,String sal,String cn,String loc,String j)
	{//setting details
		hash o= new hash();
		if(sec>=1) {//validations
			int d_no=dom;//for recruiter
			int sec_no=sec;
			o.domain_num=dom;
			o.sector_num=sec;
			o.sector=job_sectors.next_link[sec_no-1].name;
			o.domain=job_sectors.next_link[sec_no-1].next_link[d_no-1];
			
			if(ji.compareToIgnoreCase("Job")==0)
				o.job_internship=job_sectors.next_link[sec_no-1].next_link[d_no-1].next_link[0];
			else if(ji.compareToIgnoreCase("Internship")==0)
				o.job_internship=job_sectors.next_link[sec_no-1].next_link[d_no-1].next_link[1];
			
			if(pl.compareToIgnoreCase("Site")==0)
				o.place=Site.next_link[0];
			else
				o.place=Site.next_link[1];
			
			if(t.compareToIgnoreCase("Full Time")==0)
				o.time=Time.next_link[0];
			else
				o.time=Time.next_link[1];
			
			
			if(e.compareToIgnoreCase("Graduation")==0)
				o.education=Education.next_link[0];
			else
				o.education=Education.next_link[1];
			
			o.year_of_study=ys;
			if(ex!=null)
			{
				if(ex.compareToIgnoreCase("Fresher")==0)
					o.experience=Experience.next_link[0];
				else if(ex.compareToIgnoreCase("1-5 years")==0)
					o.experience=Experience.next_link[1];
				else if(ex.compareToIgnoreCase("5-10 years")==0)
					o.experience=Experience.next_link[2];
				else if(ex.compareToIgnoreCase("Greater than 10 years")==0)
					o.experience=Experience.next_link[3];
			}
			else
				o.experience=null;
			
			o.salary=sal;
			o.Company_name=cn;
			o.Location=loc;
			o.jd=j;
			return o;//returning hash object
		}
		else
			return null;
		
	}
	
	hash recruiter_accept()//accept for recruiter
	{
		
		System.out.println("\n \n                                           ENTER SOME BASIC DETAILS FOR YOUR NEXT HIRE ");
		//display all sectors
		System.out.println("\n          Sectors in Job and Internship are : ");
		System.out.println();
		for(int i=0;i<job_sectors.number_nodes;i++)
		{
			System.out.println("        "+(i+1)+")."+job_sectors.next_link[i].name);
		}
		System.out.println();
		System.out.println();
		//accept sector
		node sector_name=new node();
		System.out.print(job_sectors.message);
		String sector=validation_graph(job_sectors);
		System.out.println(sector);
		System.out.println();
		for(int i=0;i<job_sectors.number_nodes;i++)
		{
			if((job_sectors.next_link[i].name).compareToIgnoreCase(sector)==0)
			{
				sector_name=job_sectors.next_link[i];
				o.sector_num=(i+1);
				break;
			}
		}
		System.out.println(o.sector_num+" for the sector "+sector_name.name);
		o.sector=sector_name.name;
		//display all domains of selected sector
		System.out.println("\n \n          Great! Domains in Job and Internship for "+sector_name.name.toUpperCase()+" are :");
		System.out.println();
		for(int i=0;i<(sector_name).number_nodes;i++)
		{
			System.out.format("%10d. %-32s",(i+1),sector_name.next_link[i].name);
			System.out.println();
		}
		System.out.println();       
		//accept the domain of selected sector
		System.out.print(sector_name.message);
		String domain=validation_graph(sector_name);
		for(int i=0;i<sector_name.number_nodes;i++)
		{
			if((sector_name.next_link[i].name).compareToIgnoreCase(domain)==0)
			{
				o.domain=sector_name.next_link[i];
				o.domain_num = (i+1);//setting domain number used for hash function
			}
		}
		//looking for job or internship
		System.out.print("\n               Thanks! "+o.domain.message);
		String select;
		boolean valid=false;
		do
		{
			select=sc.nextLine();
			if(select.compareToIgnoreCase("employee")==0||select.compareToIgnoreCase("intern")==0&&select!=null)//validation
			{
				valid=true;
			}
			else
			{
				System.out.println();
				System.out.println("           Oops!!You have entered something wrong.  Please enter a valid answer:");
				System.out.print("           ");
				valid=false;
			}
		}while(!valid);//validation
		if(select.compareToIgnoreCase("Employee")==0)
			select="job";
		else
			select="internship";

		for(int i=0;i<o.domain.number_nodes;i++)
		{
			if((o.domain.next_link[i].name).compareToIgnoreCase(select)==0)
			{
				o.job_internship=o.domain.next_link[i];//setting job/internship
				break;
			}
		}
		//select the work hours
		System.out.print("\n              1."+o.job_internship.next_link[0].next_link[0].name);
		System.out.print("\n              2."+o.job_internship.next_link[0].next_link[1].name);
		System.out.println();
		System.out.println("\n              "+o.job_internship.next_link[0].message);
		System.out.print("              ");
		String work_hour=validation_graph(o.job_internship.next_link[0]);
		for(int j=0;j<o.job_internship.next_link[0].number_nodes;j++)
		{
			if(((o.job_internship.next_link[0].next_link[j]).name).compareToIgnoreCase(work_hour)==0)
			{
				o.time=o.job_internship.next_link[0].next_link[j];//setting full time or part time
				break;
			}
		}
		//select work place
		System.out.print("\n \n              ");
		for(int j=0;j<o.job_internship.next_link[1].number_nodes;j++)
		{//printing all options
			System.out.println((j+1)+"."+o.job_internship.next_link[1].next_link[j].name+" ");
			System.out.print("              ");
		}
		
		System.out.print("\n            ");
		System.out.print(o.job_internship.next_link[1].message);
		String work_place=validation_graph(o.job_internship.next_link[1]);
		for(int i=0;i<o.job_internship.next_link[1].number_nodes;i++)
		{
			if(((o.job_internship.next_link[1].next_link[i]).name).compareToIgnoreCase(work_place)==0)
			{
				o.place=o.job_internship.next_link[1].next_link[i];//setting place
				break;
			}
		}
		//if job is chosen enter the experience level
		//else enter the year of study for internship
		System.out.println("\n");
		if((o.job_internship.name).compareToIgnoreCase("job")==0)
		{
			flag=1;
			System.out.println("              "+o.job_internship.next_link[3].name+":");
			System.out.println();

			for(int j=0;j<o.job_internship.next_link[3].number_nodes;j++)
			{
				System.out.println("              "+(j+1)+"."+o.job_internship.next_link[3].next_link[j].name);
			}
			System.out.print("\n              "+o.job_internship.next_link[2].message);
			String exp=validation_graph(o.job_internship.next_link[3]);//validation
			for(int j=0;j<o.job_internship.next_link[3].number_nodes;j++)
			{
				if(((o.job_internship.next_link[3].next_link[j]).name).compareToIgnoreCase(exp)==0)
				{
					o.experience=o.job_internship.next_link[3].next_link[j];//setting experience
					break;
				}

			}
		}
		//select degree
		System.out.println();
		System.out.println("              "+o.job_internship.next_link[2].name+":");
		for(int j=0;j<o.job_internship.next_link[2].number_nodes;j++)
		{//printing all options
			System.out.println("              "+(j+1)+"."+o.job_internship.next_link[2].next_link[j].name);
		}
		System.out.print("\n              ");
		System.out.print(o.job_internship.next_link[2].message);
		String degree=validation_graph(o.job_internship.next_link[2]);//validation
		for(int j=0;j<o.job_internship.next_link[2].number_nodes;j++)
		{
			if(((o.job_internship.next_link[2].next_link[j]).name).compareToIgnoreCase(degree)==0)
			{
				o.education=o.job_internship.next_link[2].next_link[j];//setting education
				break;
			} 
		}

		//internship
		if((o.job_internship.name).compareToIgnoreCase("internship")==0)
		{
			//select year of study
			if(o.education.name.compareToIgnoreCase(o.job_internship.next_link[2].next_link[0].name)==0)//if graduation
			{
				//display years of graduation
				System.out.println();
				System.out.println("              "+o.job_internship.next_link[2].next_link[0].name+":");
				System.out.println();
				for(int i=0;i<o.job_internship.next_link[2].next_link[0].number_nodes;i++)
				{
					System.out.println("              "+(i+1)+"."+o.job_internship.next_link[2].next_link[0].next_link[i].name+":");
				}
				System.out.println();
				System.out.print("              "+o.job_internship.next_link[2].next_link[0].message);
				o.year_of_study=validation_graph(o.job_internship.next_link[2].next_link[0]);
			}
			else
			{
				//display years of pg
				System.out.println();
				System.out.println("              "+o.job_internship.next_link[2].next_link[1].name+":");
				System.out.println();
				for(int i=0;i<o.job_internship.next_link[2].next_link[1].number_nodes;i++)
				{//printing all options
					System.out.println("       "+(i+1)+"."+o.job_internship.next_link[2].next_link[1].next_link[i].name+":");
				}
				System.out.println();
				System.out.print("              "+o.job_internship.next_link[2].next_link[1].message);
				o.year_of_study=validation_graph(o.job_internship.next_link[2].next_link[1]);//validation
			}
		}
		return o;//o tells us all options selected by user
	}

	hash applicant_accept()//accepts the preferences of applicant and stores in hash object ovalidation_graph
	{

		System.out.println("\n \n                                           ENTER SOME BASIC DETAILS TO EASE YOUR SEARCH ");
		//display all sectors
		System.out.println("\n          Sectors in Job and Internship are : ");
		System.out.println();
		for(int i=0;i<job_sectors.number_nodes;i++)
		{
			System.out.println("        "+(i+1)+")."+job_sectors.next_link[i].name);
		}
		System.out.println();
		System.out.println();
		//accept sector
		node sector_name=new node();
		System.out.print(job_sectors.message);
		String sector=validation_graph(job_sectors);
		System.out.println();
		for(int i=0;i<job_sectors.number_nodes;i++)
		{
			if((job_sectors.next_link[i].name).compareToIgnoreCase(sector)==0)
			{
				sector_name=job_sectors.next_link[i];
				o.sector_num=(i+1);
				System.out.println((i+1)+" for "+job_sectors.next_link[i]);
				break;
			}
		}
		o.sector=sector_name.name;//setting sector
		//display all domains of selected sector
		System.out.println("\n \n          Great! Domains in Job and Internship for "+sector_name.name.toUpperCase()+" are :");
		System.out.println();
		for(int i=0;i<(sector_name).number_nodes;i++)
		{
			System.out.format("%10d. %-32s",(i+1),sector_name.next_link[i].name);
			System.out.println();
		}     
		//accept the domain of selected sector
		System.out.print("\nPlease select the domain of interest : ");
		String domain=validation_graph(sector_name);
		for(int i=0;i<sector_name.number_nodes;i++)
		{
			if((sector_name.next_link[i].name).compareToIgnoreCase(domain)==0)
			{
				o.domain=sector_name.next_link[i];//setting the domain field
				o.domain_num = (i+1);//no of domain - used in hash func
			}
		}
		System.out.println();
		//looking for job or internship
		System.out.print("\n              Thanks! Are you searching for 'Job' or 'Internship' : ");
		String select=validation_graph(o.domain);//validation func called
		for(int i=0;i<o.domain.number_nodes;i++)
		{//printing all options
			if((o.domain.next_link[i].name).compareToIgnoreCase(select)==0)
			{
				o.job_internship=o.domain.next_link[i];//setting the job/internship parameter
				break;
			}
		}
		//select the work hours
		System.out.print("\n              1."+o.job_internship.next_link[0].next_link[0].name);
		System.out.print("\n              2."+o.job_internship.next_link[0].next_link[1].name);
		System.out.println();
		System.out.println("\n              Do you want a 'part time' or a 'full time' job/internship : ");
		System.out.print("              ");
		String work_hour=validation_graph(o.job_internship.next_link[0]);
		for(int j=0;j<o.job_internship.next_link[0].number_nodes;j++)
		{
			if(((o.job_internship.next_link[0].next_link[j]).name).compareToIgnoreCase(work_hour)==0)
			{
				o.time=o.job_internship.next_link[0].next_link[j];
				break;
			}
		}
		//select work place
		System.out.print("\n \n              ");
		for(int j=0;j<o.job_internship.next_link[1].number_nodes;j++)
		{//printing all options
			System.out.println((j+1)+"."+o.job_internship.next_link[1].next_link[j].name+" ");
			System.out.print("              ");
		}
		System.out.print("\n              ");
		System.out.println("Select the work-place 'home' OR 'site' : ");
		System.out.print("              ");
		String work_place=validation_graph(o.job_internship.next_link[1]);
		for(int i=0;i<o.job_internship.next_link[1].number_nodes;i++)
		{
			if(((o.job_internship.next_link[1].next_link[i]).name).compareToIgnoreCase(work_place)==0)
			{
				o.place=o.job_internship.next_link[1].next_link[i];//setting place
				break;
			}
		}
		//if job is chosen enter the experience level
		//else enter the year of study for internship
		if((o.job_internship.name).compareToIgnoreCase("job")==0)
		{
			flag=1;
			System.out.println("\n              "+o.job_internship.next_link[3].name+":");
			System.out.println();
			for(int j=0;j<o.job_internship.next_link[3].number_nodes;j++)
			{//printing all options
				System.out.println("              "+(j+1)+"."+o.job_internship.next_link[3].next_link[j].name);
			}
			System.out.print("\n              Please enter your experience as a search filter : ");
			String exp=validation_graph(o.job_internship.next_link[3]);//validation
			for(int j=0;j<o.job_internship.next_link[3].number_nodes;j++)
			{
				if(((o.job_internship.next_link[3].next_link[j]).name).compareToIgnoreCase(exp)==0)
				{
					o.experience=o.job_internship.next_link[3].next_link[j];//setting experience
					break;
				}
			}
		}
		//select degree
		System.out.println();
		System.out.println("              "+o.job_internship.next_link[2].name+":");
		for(int j=0;j<o.job_internship.next_link[2].number_nodes;j++)
		{//printing all options
			System.out.println("              "+(j+1)+"."+o.job_internship.next_link[2].next_link[j].name);
		}
		System.out.print("\n              ");
		System.out.print("PLease enter your educational qualification : ");
		String degree=validation_graph(o.job_internship.next_link[2]);//setting the degree field
        //education field
		for(int j=0;j<o.job_internship.next_link[2].number_nodes;j++)
		{
			if(((o.job_internship.next_link[2].next_link[j]).name).compareToIgnoreCase(degree)==0)
			{
				o.education=o.job_internship.next_link[2].next_link[j];//setting the education field
				break;
			} 
		}
		//internship 
		if((o.job_internship.name).compareToIgnoreCase("internship")==0)
		{

			//select year of study
			if(o.education.name.compareToIgnoreCase(o.job_internship.next_link[2].next_link[0].name)==0)//if graduation
			{
				//display years for graduation
				System.out.println();
				System.out.println("             "+o.job_internship.next_link[2].next_link[0].name+":");
				System.out.println();
				for(int i=0;i<o.job_internship.next_link[2].next_link[0].number_nodes;i++)
				{
					System.out.println("              "+(i+1)+"."+o.job_internship.next_link[2].next_link[0].next_link[i].name+":");
				}
				System.out.println();
				System.out.print("              Please enter your year of study for better search results  : ");
				o.year_of_study=validation_graph(o.job_internship.next_link[2].next_link[0]);//validation
			}
			else
			{
				//display years for PG
				System.out.println();
				System.out.println("              "+o.job_internship.next_link[2].next_link[1].name+":");
				System.out.println();
				for(int i=0;i<o.job_internship.next_link[2].next_link[1].number_nodes;i++)
				{
					System.out.println("       "+(i+1)+"."+o.job_internship.next_link[2].next_link[1].next_link[i].name+":");
				}
				System.out.print("\n              Please enter your year of study for better search results  : ");
				o.year_of_study=validation_graph(o.job_internship.next_link[2].next_link[1]);//validation
			}
		}
		return o;//o tells us all options selected by user
	}

	static public hash display_hash(hash o)//to display the criteria selected by a recruiter
	{

		System.out.print("For the "+ o.sector.toUpperCase()+" sector");
		System.out.print(" in "+ o.domain.name.toUpperCase()+" domain");
		System.out.print(" for a/an "+ o.job_internship.name.toUpperCase()+".");
		System.out.print(" As a "+ o.place.name.toUpperCase()+" job/internship");
		System.out.print(" for "+ o.time.name.toUpperCase()+" work.");
		System.out.print("Eligible for "+ o.education.name.toUpperCase()+" with experience in accordance to ");
		if(flag==0)//if an intern is selected
		{
			System.out.print(o.year_of_study);//display the year of study

		}
		else//if employee is selected then display the experience criteria
		{
			System.out.print(o.experience.name);

		}
		flag=0;
		System.out.println(".");
		return o;
	}
	static public void display_search_hash(hash o)//display the details of a job finder
	{//displaying node
			System.out.println("\n \n \n               We got your search results : \n");
			System.out.println("Sector : "+o.sector);
			System.out.println("Domain : "+o.domain.name);
			System.out.println("We are looking for a "+o.job_internship.name);
			System.out.println("Work from : "+o.place.name);
			System.out.println("As a "+o.time.name);
			System.out.println("Your degree "+o.education.name);
			if( o.experience==null)
			{
				System.out.println("Currently studying in "+o.year_of_study);

			}
			else
			{
				System.out.println("With experience :"+o.experience.name);

			}
			flag=0;
			System.out.println("Salary offered : "+o.getSalary());//using getter methods
			System.out.println("Job Description : "+o.jd);
			System.out.println("Company : "+o.getCompany_name());
			System.out.println("Location  : "+o.getLocation());
	}
	
	void display_all()//to display all the sectors and their respective domains
	{//an option to user
		System.out.println(" \n\n           Sectors in Job and Internship are : \n");
		for(int i=0;i<job_sectors.number_nodes;i++)//all sectors
			System.out.println("        "+(i+1)+")."+job_sectors.next_link[i].name);
		System.out.println();
		System.out.println();
		System.out.println("            Domains in Job and Internship are : \n");
		for(int i=0;i<job_sectors.number_nodes;i++)//all domains
			System.out.format("%10d.)%-32s",(i+1),job_sectors.next_link[i].name);
		System.out.println();
		System.out.println();
		for(int i1=0;i1<12;i1++)
		{
			for(int i=0;i<job_sectors.number_nodes;i++)
			{
				if((i1)<job_sectors.next_link[i].number_nodes)
					System.out.format("%10d. %-32s",(i1+1),job_sectors.next_link[i].next_link[i1].name);//for formatting
				else
					System.out.format("%10s  %-32s","","");
			}
			System.out.println();
		}
	}
	
	String validation_graph(node o)//validations for all inputs of graph class
	{//for all inputs
		String ans=null;
		boolean valid=false;
		do
		{
			ans=sc.nextLine();
			for(int i=0;i<o.number_nodes;i++)
			{
				if(ans.compareToIgnoreCase(o.next_link[i].name)==0&&ans!=null)
				{
					valid=true;
					break;
				}
				else
				{
					valid=false;
				}
			}
			if(valid==false)
			{
				System.out.println();
				System.out.println("           Oops!!You have entered something wrong.  Please enter a valid answer:");
				System.out.print("           ");
			}
		}while(!valid);
		return ans;
	}
	String validation_main(String s1,String s2)//validations for all inputs in main() function
	{//exception handling
		String ans=null;
		boolean valid=false;//set to True when input is as desired
		do
		{
			try
			{
				ans=sc.nextLine();
				if((ans.compareToIgnoreCase(s1)==0)||(ans.compareToIgnoreCase(s2)==0))
				{
					valid=true;
				}
				else//if input not as expected
				{
					System.out.println();
					System.out.println("           Oops!!You have entered something wrong.  Please enter a valid answer:");
					System.out.print("           ");
					valid=false;
				}
			}catch(Exception e)
			{//if java throws an exception
				System.out.println();
				System.out.println("           Oops!!You have entered something wrong.  Please enter a valid answer:");
				System.out.print("           ");
				sc= new Scanner(System.in);
				valid=false;
			}
		}while(!valid);
		return ans;
	}

}



public class project
{
	static Scanner input = new Scanner(System.in);//scanner for input

	public static void main(String args[])
	{
		int user_loop=0;//for re - running the application i.e adding/searching more jobs
		String user;//stores whether you are an applicant or emloyer
		System.out.println();
		hash o = new hash();//object of hash
		
		Graph call = new Graph();//object of recruiter tree
		System.out.println("-----------------------------------------------------WELCOME TO OUR MINI JOB FINDER:P---------------------------------------------------------");
		do
		{
			hash hash_table = new hash();//object of hash
			//copied data;
			hash_table.copy_previous_data();//all previous records of file
			//all jobs after coping ;
			System.out.println();
			System.out.print("           Are you a looking for a job? Or are you a Recruiter? : "); 
			user=call.validation_main("job","Recruiter");//stores whether you are an applicant or employer
			System.out.println("\n");
			
			
			if(user.compareToIgnoreCase("job")==0)//applicant
			{
				System.out.println("                               Hey! We are glad to help you! Search Jobs on the Go !!");
				System.out.print("\n \n                         Do you want to take a quick look for all the sectors and its domains! (Y/N) :  ");
				String look =call.validation_main("y","n");//if user wants to see all domains
				if(look.compareToIgnoreCase("Y")==0)//if yes
					call.display_all(); 
				if(look.compareToIgnoreCase("Y")==0)//if yes
					hash_table.print_all_jobs();
				//else
				o= call.applicant_accept();//accept preferences
				hash_table.search(o);//search the record as per selected preferences
				System.out.println("\n \n           Thank you for choosing us. Hope you are satisfied!\n");
				//all jobs after search : ");
				
			}
			else
			{
				
				System.out.println("         Hello! Reach your next hire faster. Get relevant responses to your job ads.");
				System.out.print("\n \n                         Do you want to take a quick look for all the sectors and its domains! (Y/N) :  ");
				String look =call.validation_main("y","n");//if user wants to see all domains
				if(look.compareToIgnoreCase("Y")==0)//if yes
					call.display_all(); 
				o= call.recruiter_accept();//accepting preferences
                // additional details
				System.out.println("\n\n\n          Thank you. Your preferences have been recorded successfully. Kindly enter the following details for the applicants : ");
				System.out.print("              Enter salary offered : ");
				o.setSalary(input.next());//salary
				System.out.print("              Enter job description and contact deatils : ");
				input.nextLine();//jd
				o.setJd(input.nextLine()); 
				System.out.print("              Enter Company you represent : ");
				o.setCompany_name(input.nextLine());
				System.out.print("              Enter Location of job : ");
				o.setLocation(input.nextLine());
				hash_table.add(o);//add new record
				Graph.display_hash(o);//to Show the record entered by recruiter
				System.out.println("\n \n           Your job has been added! Thank you for choosing us. Soon your next hire will conatct you!\n");//success msg
				hash_table.write_new_updates();//add new  along with old ones -record in file
			}
			System.out.print("\n \n      					Do you want to explore more? Press 0 to exit & 1 to continue:  ");
			boolean valid=true;//whether user entered a valid choice or not
			do//validations
			{
				try//validation
				{
					user_loop = input.nextInt(); 
					if(user_loop==0||user_loop==1)
					{
						valid=true;
					}
					else
					{
						System.out.println("\n \n                         Please enter a valid input: 0 or 1:");
						System.out.print("                         ");
					}
				}catch(Exception  e)
				{
					System.out.println("\n \n                         Please enter a valid input: 0 or 1:");
					System.out.print("                         ");
					input = new Scanner(System.in);
					valid=false;
				}
			}while(!valid&&(user_loop!=0||user_loop!=1));

		}while(user_loop!=0);
		System.out.println("\n\n\n 			THANK YOU ");//exit message
	}
}
/*
 * Sample output
-----------------------------------------------------WELCOME TO OUR MINI JOB FINDER:P---------------------------------------------------------

           Are you a looking for a job? Or are you a Recruiter? : recruiter


         Hello! Reach your next hire faster. Get relevant responses to your job ads.

 
                         Do you want to take a quick look for all the sectors and its domains! (Y/N) :  n

 
                                           ENTER SOME BASIC DETAILS FOR YOUR NEXT HIRE 

          Sectors in Job and Internship are : 

        1).Information Technology
        2).Engineering
        3).Bussiness
        4).Sales
        5).Management


Please select the sector in which you want to post your next job : sales
sales

4 for the sector Sales

 
          Great! Domains in Job and Internship for SALES are :

         1. Executive                       
         2. Development                     
         3. Inside sales                    
         4. Outside sales                   
         5. Management                      
         6. Consulative                     

Please select the domain you want to proceed with : development

               Thanks! Are you looking for a 'Employee' or an 'Intern'? : employee

              1.Full Time
              2.Part Time

              Please post the working hours as per your need : 
              full time

 
              1.Site 
              2.Home 
              
              Please select working site of your choice : site


              Experience:

              1.Fresher
              2.1-5 years
              3.5-10 years
              4.Greater than 10 years

              Please enter basic details for qualification : fresher

              Education:
              1.Graduation
              2.Post Graduation

              Please enter basic details for qualification : graduation



          Thank you. Your preferences have been recorded successfully. Kindly enter the following details for the applicants : 
              Enter salary offered : 6Lakh
              Enter job description and contact deatils : Need a candidate with strong fundamentals
              Enter Company you represent : Rocket
              Enter Location of job : Pune
For the SALES sector in DEVELOPMENT domain for a/an JOB. As a SITE job/internship for FULL TIME work.Eligible for GRADUATION with experience in accordance to Fresher.

 
           Your job has been added! Thank you for choosing us. Soon your next hire will conatct you!


 
      					Do you want to explore more? Press 0 to exit & 1 to continue:  1

           Are you a looking for a job? Or are you a Recruiter? : recruiter


         Hello! Reach your next hire faster. Get relevant responses to your job ads.

 
                         Do you want to take a quick look for all the sectors and its domains! (Y/N) :  y
 

           Sectors in Job and Internship are : 

        1).Information Technology
        2).Engineering
        3).Bussiness
        4).Sales
        5).Management


            Domains in Job and Internship are : 

         1.)Information Technology                   2.)Engineering                              3.)Bussiness                                4.)Sales                                    5.)Management                      

         1. Android Development                      1. Manufacturing                            1. Health care                              1. Executive                                1. Event Management                
         2. Cloud domain                             2. Mechanical Design                        2. Aviation                                 2. Development                              2. Hotel Management                
         3. Big Data                                 3. Transportation Systems                   3. Finance/Banking                          3. Inside sales                             3. Office Management               
         4. SAP                                      4. SAS                                      4. Military                                 4. Outside sales                            4. Administrative                  
         5. Network sequrity                         5. Construction Management                  5. Retail                                   5. Management                               5. Health Services                 
         6. AI/ML                                    6. Geotechnical                                                                         6. Consulative                              6. Marketing                       
         7. Web tooling                                                                                                                                                                  7. Advertising                     
         8. Data Science                                                                                                                                                                 8. Human Resource                  
         9. Internet of Things                                                                                                                                                                                              
        10. Biomedical                                                                                                                                                                                                      
        11. Robotics                                                                                                                                                                                                        
                                                                                                                                                                                                                            

 
                                           ENTER SOME BASIC DETAILS FOR YOUR NEXT HIRE 

          Sectors in Job and Internship are : 

        1).Information Technology
        2).Engineering
        3).Bussiness
        4).Sales
        5).Management


Please select the sector in which you want to post your next job : Information Technology
Information Technology

1 for the sector Information Technology

 
          Great! Domains in Job and Internship for INFORMATION TECHNOLOGY are :

         1. Android Development             
         2. Cloud domain                    
         3. Big Data                        
         4. SAP                             
         5. Network sequrity                
         6. AI/ML                           
         7. Web tooling                     
         8. Data Science                    
         9. Internet of Things              
        10. Biomedical                      
        11. Robotics                        

Please select the domain you want to proceed with : web tooling

               Thanks! Are you looking for a 'Employee' or an 'Intern'? : intern

              1.Full Time
              2.Part Time

              Please post the working hours as per your need : 
              full time

 
              1.Site 
              2.Home 
              
              Please select working site of your choice : site



              Education:
              1.Graduation
              2.Post Graduation

              Please enter basic details for qualification : graduation

              Graduation:

              1.First year:
              2.Second year:
              3.Third year:
              4.Fourth year:

              Select the present year of studying : second year



          Thank you. Your preferences have been recorded successfully. Kindly enter the following details for the applicants : 
              Enter salary offered : 50,000
              Enter job description and contact deatils : dilligent candidate required
              Enter Company you represent : Amazon
              Enter Location of job : Hyderabad
For the INFORMATION TECHNOLOGY sector in WEB TOOLING domain for a/an INTERNSHIP. As a SITE job/internship for FULL TIME work.Eligible for GRADUATION with experience in accordance to second year.

 
           Your job has been added! Thank you for choosing us. Soon your next hire will conatct you!


 
      					Do you want to explore more? Press 0 to exit & 1 to continue:  1

           Are you a looking for a job? Or are you a Recruiter? : job


                               Hey! We are glad to help you! Search Jobs on the Go !!

 
                         Do you want to take a quick look for all the sectors and its domains! (Y/N) :  n

 

                                           ENTER SOME BASIC DETAILS TO EASE YOUR SEARCH 

          Sectors in Job and Internship are : 

        1).Information Technology
        2).Engineering
        3).Bussiness
        4).Sales
        5).Management


Please select the sector in which you want to post your next job : Information Technology



 
          Great! Domains in Job and Internship for INFORMATION TECHNOLOGY are :

         1. Android Development             
         2. Cloud domain                    
         3. Big Data                        
         4. SAP                             
         5. Network sequrity                
         6. AI/ML                           
         7. Web tooling                     
         8. Data Science                    
         9. Internet of Things              
        10. Biomedical                      
        11. Robotics                        

Please select the domain of interest : web tooling


              Thanks! Are you searching for 'Job' or 'Internship' : hj

           Oops!!You have entered something wrong.  Please enter a valid answer:
           Internship

              1.Full Time
              2.Part Time

              Do you want a 'part time' or a 'full time' job/internship : 
              full time

 
              1.Site 
              2.Home 
              
              Select the work-place 'home' OR 'site' : 
              site

              Education:
              1.Graduation
              2.Post Graduation

              PLease enter your educational qualification : graduation

             Graduation:

              1.First year:
              2.Second year:
              3.Third year:
              4.Fourth year:

              Please enter your year of study for better search results  : second year

 
 
               We got your search results : 

Sector : Information Technology
Domain : Web tooling
We are looking for a Internship
Work from : Site
As a Full Time
Your degree Graduation
Currently studying in second year
Salary offered : 50,000
Job Description : dilligent candidate required
Company : Amazon
Location  : Hyderabad

 
           Thank you for choosing us. Hope you are satisfied!


 
      					Do you want to explore more? Press 0 to exit & 1 to continue:  1

           Are you a looking for a job? Or are you a Recruiter? : job


                               Hey! We are glad to help you! Search Jobs on the Go !!

 
                         Do you want to take a quick look for all the sectors and its domains! (Y/N) :  n


 
                                           ENTER SOME BASIC DETAILS TO EASE YOUR SEARCH 

          Sectors in Job and Internship are : 

        1).Information Technology
        2).Engineering
        3).Bussiness
        4).Sales
        5).Management


Please select the sector in which you want to post your next job : business

           Oops!!You have entered something wrong.  Please enter a valid answer:
           Bussiness

3 for node@5e265ba4

 
          Great! Domains in Job and Internship for BUSSINESS are :

         1. Health care                     
         2. Aviation                        
         3. Finance/Banking                 
         4. Military                        
         5. Retail                          

Please select the domain of interest : military


              Thanks! Are you searching for 'Job' or 'Internship' : job

              1.Full Time
              2.Part Time

              Do you want a 'part time' or a 'full time' job/internship : 
              full time

 
              1.Site 
              2.Home 
              
              Select the work-place 'home' OR 'site' : 
              site

              Experience:

              1.Fresher
              2.1-5 years
              3.5-10 years
              4.Greater than 10 years

              Please enter your experience as a search filter : fresher

              Education:
              1.Graduation
              2.Post Graduation

              PLease enter your educational qualification : graduation
 
 
   Sorry there is no job posted yet! Keep checking :) 
 Try some different search!

 
           Thank you for choosing us. Hope you are satisfied!


 
      					Do you want to explore more? Press 0 to exit & 1 to continue:  o

 
                         Please enter a valid input: 0 or 1:
                         0



 													THANK YOU 
*/
 