// GAYATRI WALKE (ROLL NO. 2466)

package dsa_temp;

import java.util.concurrent.Semaphore;

class reader_writers_problems 
{

	//Semaphore gives number of permits 
    static Semaphore readLock = new Semaphore(1);
    static Semaphore writeLock = new Semaphore(1);
    
    static int number_of_readers = 0;

    static class Read implements Runnable 
    {
        @Override
        public void run() {
            try {
                //Acquire Section
                readLock.acquire();		//lock reader lock so that no other reader enters	
                number_of_readers++;			//increase the count of reader
                if (number_of_readers == 1)		//if it is first reader
                {
                    writeLock.acquire();		//then lock writer so that no writer enters critical section
                }
                readLock.release();		//unlock reader lock so that other readers can enter

                //Reading section-----thread execution
                System.out.println("Thread "+Thread.currentThread().getName() + " is READING");	//start
                Thread.sleep(10000);
                System.out.println("Thread "+Thread.currentThread().getName() + " has FINISHED READING");	//end

                //Releasing section
                readLock.acquire();		//lock reader lock so that no other reader enters while the thread exit
                
                number_of_readers--;	//decrease the count of reader
               
                if(number_of_readers == 0) //if all the readers exit
                {
                    writeLock.release();		//release the writer lock so writer thread can execute
                }
                readLock.release();		//unlock reader lock so that other readers can enter or exit
                
                
            } 
            catch (InterruptedException e) 	//exception
            {
                System.out.println(e.getMessage());
            }
        }
    }

    static class Write implements Runnable 
    {
        @Override
        public void run() 
        {
            try 
            {
                writeLock.acquire();	//lock writer so that other writer does not write
                      
                System.out.println("Thread "+Thread.currentThread().getName() + " is WRITING");
                Thread.sleep(2500);
                System.out.println("Thread "+Thread.currentThread().getName() + " has finished WRITING");
                                
                writeLock.release();	//release the writer lock so other writer thread can execute
                
            } 
            catch (InterruptedException e) 
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception 
    {
        //OJECTS
    	Read read = new Read();
        Write write = new Write();
       
        //threads
        Thread t1 = new Thread(read);
        t1.setName("READER 1");
       
        Thread t2 = new Thread(read);
        t2.setName("READER 2");
        
        Thread t3 = new Thread(read);
        t3.setName("READER 3");
        
        Thread t4 = new Thread(read);
        t4.setName("READER 4");
        
        Thread t5 = new Thread(write);
        t5.setName("WRITER 1");
        
        Thread t6 = new Thread(read);
        t6.setName("READER 5");
        
        Thread t7 = new Thread(write);
        t7.setName("WRITER 2");
       
        Thread t8 = new Thread(write);
        t8.setName("WRITER 3");
        
        //call threads
        t5.start();
        t1.start();
        t3.start();
        t7.start();
        t2.start();
        t4.start();
        t6.start();
        t8.start();
    }
}

// 			OUTPUT
/*   
 
Thread WRITER 1 has finished WRITING
Thread READER 5 is READING
Thread READER 3 is READING
Thread READER 4 is READING
Thread READER 2 is READING
Thread READER 1 is READING
Thread READER 1 has FINISHED READING
Thread READER 2 has FINISHED READING
Thread READER 4 has FINISHED READING
Thread READER 5 has FINISHED READING
Thread READER 3 has FINISHED READING
Thread WRITER 3 is WRITING
Thread WRITER 3 has finished WRITING
Thread WRITER 2 is WRITING
Thread WRITER 2 has finished WRITING
*/