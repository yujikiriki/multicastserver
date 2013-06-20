package kernel.workerpool.workers;

import java.util.LinkedList;


/**
 * 
 */
public class Worker extends Thread
{
	private LinkedList < Runnable > queue;
	
	/**
	 * Constructor
	 */
	public Worker( LinkedList < Runnable > aQueue )
	{
		super ( );
		queue = aQueue;
	}
	
	/**
	 * Implements the run method at @see Thread#run( )
	 */
	public void run( )
	{
		Runnable r;

		while ( true )
		{
			synchronized ( queue )
			{
				/* Wait while the queue is empty */
				while ( queue.isEmpty ( ) )
				{
					try
					{
						queue.wait ( );
					}
					catch ( InterruptedException ignored )
					{
					}
				}
				
				/* Get the first task to be done */
				r = queue.removeFirst ( );
			}

			/* Execute the task */
	                try
			{
				r.run ( );
			}
			catch ( RuntimeException e )
			{
				e.printStackTrace ( );
			}
		}
	}
}
