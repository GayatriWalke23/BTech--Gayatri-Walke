//GAYATRI WALKE
//ROLL NO 2466

package page_replacement;

import java.util.Scanner;
class frame
{
	int page_number;
	int frame_number;
	int flag;
	
	frame(int pgno,int fmno)	//para. constructor
	{
		page_number=pgno;			
		frame_number=fmno;
		flag=0;
	}
	
}


public class algo 
{
	static Scanner input = new Scanner(System.in);
	static Scanner input_ = new Scanner(System.in);
	int length_string;
	int number_frames;
	String rf_string;		//input
	Integer reference_string[]=new Integer[100];
	Integer frame[][]=new Integer[100][100];
	Integer page_fault[]=new Integer[100];
	int index=0,ptr=0;
	int page_hit;
	int page__fault;
	
		void accept_reference_string()		//accept string from user
		{
			char comma=44;
			int number=0;
			System.out.println("ENTER");
			System.out.print(  "     length of process : ");
			length_string=input.nextInt();
			System.out.print(  "     reference string : ");
			rf_string=input_.next();
			System.out.print(  "     frame number : ");
			number_frames=input_.nextInt();
			System.out.println();
			
			for(int i=0;i<length_string;i++)
				reference_string[i]= new Integer(0);
			
			while(index<length_string&&ptr<rf_string.length())
			{
				//System.out.println(rf_string.charAt(ptr));
				if(rf_string.charAt(ptr)==comma)
				{
					reference_string[index]=number;
					index++;
					number =0;
				}
				else
				{
					number=number*10+(rf_string.charAt(ptr)-48);
					//System.out.println("number "+number);
					
				}
				ptr++;
			}
			
			System.out.print("reference string : ");
			for(int i=0;i<length_string;i++)
				System.out.print(reference_string[i]+" ");
			System.out.println();
			
			}

		
			void optimal_page_replacement()
			{
				System.out.println();
				System.out.println("        			PAGE REPLACEMENT BY OPTIMAL PAGE REPLACEMENT");
				System.out.println();				
				page_hit=0;
				page__fault=0;
				int new_page;
				int frames=-1;
				
				int frame_number=0;
				
				int queue_base=0;
				int queue_pointer=0;
				
				
				
				for(int i=0;i<length_string;i++)
					page_fault[i]= new Integer(0);	//initialize page fault with 0
				
				for(int j=0;j<number_frames;j++)
				{
					for(int i=0;i<length_string;i++)
						frame[j][i]=new Integer(-1);	//initialize frame with -1 for empty
				}
				
				for(int i=0;i<length_string;i++)
				{
					new_page=reference_string[i];		//read new page no. from the string
					
					if(page_in_frame(new_page,i))		//if page is already present in the string
					{
						page_hit++;
						
					}
					else								//if not
					{
						frames++;
						if(frames<number_frames)
						{
							frame_number=frames;
							frame[frame_number][i]=new_page;

						}
						else
						{
							int optimal_future_pred[]=new int[number_frames];
							int optimal_past[]=new int[number_frames];
							
							for(int n=0;n<number_frames;n++)
								optimal_future_pred[n]=new Integer(999);
							
							for(int n=0;n<number_frames;n++)
								optimal_future_pred[n]=new Integer(999);
							
							for(int n=0;n<number_frames;n++)//keep track of who will come in future
							{
								int page=frame[n][i];
								int index=0;
								for(index=i+1;index<length_string;index++)
								{
									if(page==reference_string[index])
									{
										optimal_future_pred[n]=index;
										break;
									}
									
								}
								
							}
							for(int n=0;n<number_frames;n++)//keep track of who came first
							{
								int page=frame[n][i];
								int index=0;
								for(index=i-1;index>=0;index--)
								{
									if(page==reference_string[index])
									{
										optimal_past[n]=index;
										break;
									}
									
								}
								
							}
							for(int n=0;n<number_frames-1;n++)
							{
								if(optimal_future_pred[n]==999)//distinguish which came first
									optimal_future_pred[n]=optimal_future_pred[n]-optimal_past[n];
							}
							int maximum=0;
							for(int n=0;n<number_frames-1;n++)//find the maximum i.e. who is least likely to come
							{
								if(optimal_future_pred[maximum]<optimal_future_pred[maximum+1])
								{
									maximum++;
								}
							}
							
							frame_number=maximum;
							frame[frame_number][i]=new_page;	//update the particular frame
						}
						page__fault++;
									
					}
					if(i<length_string)
					{
						for(int j=0;j<number_frames;j++)	//copy the frame to to the next frame for next computations					
							frame[j][i+1]=frame[j][i];
					}
					page_fault[i]=page__fault;		//update page fault
				}
				
			}

			void least_recently_used()
			{
				System.out.println();
				System.out.println("        			PAGE REPLACEMENT BY LEAST RECENTLY USED(LRU)");
				System.out.println();
				
				page_hit=0;
				page__fault=0;
				int new_page;
				int frames=-1;
				
				int frame_number=0;
				
				int queue_base=0;
				int queue_pointer=0;
				
				frame lru_queue[] = new frame[100];
				
				for(int i=0;i<length_string;i++)
					lru_queue[i]= new frame(-1,-1);	
				
				
				for(int i=0;i<length_string;i++)
					page_fault[i]= new Integer(0);	//initialize page fault with 0
				
				for(int j=0;j<number_frames;j++)
				{
					for(int i=0;i<length_string;i++)
						frame[j][i]=new Integer(-1);	//initialize frame with -1 for empty
				}
				for(int i=0;i<length_string;i++)
				{
					new_page=reference_string[i];
					
					if(page_in_frame(new_page,i))//if already in frame
					{
						page_hit++;
						int p = queue_base;
						while(p<queue_pointer)
						{
							if(lru_queue[p].page_number==new_page)
							{
								lru_queue[p].flag=1;
								break;
							}
							p++;
						}
						lru_queue[queue_pointer++]=new frame(new_page,lru_queue[p].frame_number);
					}
							
					else
					{
						frames++;
						if(frames<number_frames)//for first allotments
						{
							frame_number=frames;
							frame[frame_number][i]=new_page;
							lru_queue[queue_pointer++]=new frame(new_page,frame_number);
						}
						
						else
						{
							while(lru_queue[queue_base].flag==1)
								queue_base++;
							lru_queue[queue_base].flag=1;
							frame_number=lru_queue[queue_base].frame_number;	//replacement
							frame[frame_number][i]=new_page;
							lru_queue[queue_pointer++]=new frame(new_page,frame_number);
							
						}
						page__fault++;
					}
					
					if(i<length_string)
					{
						for(int j=0;j<number_frames;j++)	//copy the frame to to the next frame for next computations					
							frame[j][i+1]=frame[j][i];
					}
					
					page_fault[i]=page__fault;
				}
				
			}
		
			void first_in_first_out()
			{
				System.out.println();
				System.out.println("        			PAGE REPLACEMENT BY FIRST IN FIRST OUT(FIFO)");
				System.out.println();
				page_hit=0;
				page__fault=0;
				int new_page;
				int frame_number=0;
				
				
				for(int i=0;i<length_string;i++)
					page_fault[i]= new Integer(0);		//initialize page fault with 0
				
				for(int j=0;j<number_frames;j++)
				{
					for(int i=0;i<length_string;i++)
						frame[j][i]=new Integer(-1);	//initialize frame with -1 for empty 
				}
				
				for(int i=0;i<length_string;i++)
				{
					new_page=reference_string[i];		//read new page no. from the string
					
					if(page_in_frame(new_page,i))		//if page is already present in the string
						{
							page_hit++;
						}
					else								//if not
					{
						frame[frame_number][i]=new_page;		//replace the frame acc to fifo
						page__fault++;
						
						frame_number++;
						if(!(frame_number<number_frames))		//increase frame number
							frame_number=0;					
					}
					if(i<length_string-1)
					{
						for(int j=0;j<number_frames;j++)	//copy the frame to to the next frame for next computations					
							frame[j][i+1]=frame[j][i];
					}
					page_fault[i]=page__fault;		//update page fault
				}
			
		          }
		
		boolean page_in_frame(int new_page,int index)
		{
			for(int j=0;j<number_frames;j++)
			{
				if(frame[j][index]==new_page)
					return true;			//if present return true
			}
			return false;
		}
		
		void display()
		{
			
			System.out.println();
			
			System.out.print("           index : ");
			for(int i=0;i<length_string;i++)
				System.out.print(i+"\t ");
			System.out.println();
			
			System.out.print("reference string : ");
			for(int i=0;i<length_string;i++)
				System.out.print(reference_string[i]+"\t ");		//print reference string
			
			System.out.println();
			System.out.println();
			
			for(int j=0;j<number_frames;j++)
			{
				System.out.print("             F"+j+"  : ");
				for(int i=0;i<length_string;i++)
					{
						if(frame[j][i]==-1)
							System.out.print("-"+"\t ");		//if the frame is not used
						else
							System.out.print(frame[j][i]+"\t ");	//the page present in that frame
					}
				System.out.println();
			}
			System.out.println();
			
			System.out.print("      page fault : ");
			for(int i=0;i<length_string;i++)
				System.out.print(page_fault[i]+"\t ");		//page fault
			
			System.out.println();
			System.out.println();
			System.out.println("Page fault : "+page__fault);	//total page fault
			System.out.println("Page hit   : "+page_hit);		//total page hit

			System.out.println();
			System.out.println();
		}
	
		//main method
		public static void main(String args[])
		{
			
			int choice;//input user
			//get details
			
			
			do
			{
				    //display menu
					
					System.out.println("      MENU - PAGE REPLACEMENT ALGORITHM   ");
					System.out.println("1. FIRST IN FIRST OUT");
					System.out.println("2. LEAST RECENTLY USED");
					System.out.println("3. OPTIMAL PAGE REPLACEMENT");
					System.out.println("0. EXIT");
					System.out.print("enter your choice : ");
					
					choice = input.nextInt();
					System.out.println();
					switch(choice)
					{
						//call
						case 1:
							//fifo
							algo call=new algo();
							call.accept_reference_string();
							call.first_in_first_out();
							call.display();
							break;
							
							
						case 2:
							//lru
							algo call1=new algo();
							call1.accept_reference_string();
							call1.least_recently_used();
							call1.display();
							break;
							
						case 3:
							//optimal
							algo call2=new algo();
							call2.accept_reference_string();
							call2.optimal_page_replacement();
							call2.display();
							break;
							
							
						case 0:
							//exit
							System.out.println();
							System.out.println("     THANK YOU");
							break;
							
							
						default:
							//default
							System.out.println("enter valid choice!");
							break;
					}
			
				}while(choice!=0);
				input.close();
			}
}


//				OUTPUT

/*      MENU - PAGE REPLACEMENT ALGORITHM   
1. FIRST IN FIRST OUT
2. LEAST RECENTLY USED
3. OPTIMAL PAGE REPLACEMENT
0. EXIT
enter your choice : 1

ENTER
     length of process : 12
     reference string : 1,2,3,4,1,2,5,1,2,3,4,5,
     frame number : 3

reference string : 1 2 3 4 1 2 5 1 2 3 4 5 

        			PAGE REPLACEMENT BY FIRST IN FIRST OUT(FIFO)


           index : 0	 1	 2	 3	 4	 5	 6	 7	 8	 9	 10	 11	 
reference string : 1	 2	 3	 4	 1	 2	 5	 1	 2	 3	 4	 5	 

             F0  : 1	 1	 1	 4	 4	 4	 5	 5	 5	 5	 5	 5	 
             F1  : -	 2	 2	 2	 1	 1	 1	 1	 1	 3	 3	 3	 
             F2  : -	 -	 3	 3	 3	 2	 2	 2	 2	 2	 4	 4	 

      page fault : 1	 2	 3	 4	 5	 6	 7	 7	 7	 8	 9	 9	 

Page fault : 9
Page hit   : 3


      MENU - PAGE REPLACEMENT ALGORITHM   
1. FIRST IN FIRST OUT
2. LEAST RECENTLY USED
3. OPTIMAL PAGE REPLACEMENT
0. EXIT
enter your choice : 2

ENTER
     length of process : 12
     reference string : 1,2,3,4,1,2,5,1,2,3,4,5,
     frame number : 4

reference string : 1 2 3 4 1 2 5 1 2 3 4 5 

        			PAGE REPLACEMENT BY LEAST RECENTLY USED(LRU)


           index : 0	 1	 2	 3	 4	 5	 6	 7	 8	 9	 10	 11	 
reference string : 1	 2	 3	 4	 1	 2	 5	 1	 2	 3	 4	 5	 

             F0  : 1	 1	 1	 1	 1	 1	 1	 1	 1	 1	 1	 5	 
             F1  : -	 2	 2	 2	 2	 2	 2	 2	 2	 2	 2	 2	 
             F2  : -	 -	 3	 3	 3	 3	 5	 5	 5	 5	 4	 4	 
             F3  : -	 -	 -	 4	 4	 4	 4	 4	 4	 3	 3	 3	 

      page fault : 1	 2	 3	 4	 4	 4	 5	 5	 5	 6	 7	 8	 

Page fault : 8
Page hit   : 4


      MENU - PAGE REPLACEMENT ALGORITHM   
1. FIRST IN FIRST OUT
2. LEAST RECENTLY USED
3. OPTIMAL PAGE REPLACEMENT
0. EXIT
enter your choice : 3

ENTER
     length of process : 12
     reference string : 1,2,3,4,1,2,5,1,2,3,4,5,
     frame number : 3

reference string : 1 2 3 4 1 2 5 1 2 3 4 5 

        			PAGE REPLACEMENT BY OPTIMAL PAGE REPLACEMENT


           index : 0	 1	 2	 3	 4	 5	 6	 7	 8	 9	 10	 11	 
reference string : 1	 2	 3	 4	 1	 2	 5	 1	 2	 3	 4	 5	 

             F0  : 1	 1	 1	 1	 1	 1	 1	 1	 1	 3	 3	 3	 
             F1  : -	 2	 2	 2	 2	 2	 2	 2	 2	 2	 4	 4	 
             F2  : -	 -	 3	 4	 4	 4	 5	 5	 5	 5	 5	 5	 

      page fault : 1	 2	 3	 4	 4	 4	 5	 5	 5	 6	 7	 7	 

Page fault : 7
Page hit   : 5


      MENU - PAGE REPLACEMENT ALGORITHM   
1. FIRST IN FIRST OUT
2. LEAST RECENTLY USED
3. OPTIMAL PAGE REPLACEMENT
0. EXIT
enter your choice : 0


     THANK YOU
*/
