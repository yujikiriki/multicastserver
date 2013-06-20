package kernel.workerpool;

import java.util.LinkedList;

import kernel.workerpool.workers.Worker;
import kernel.workerpool.workertasks.ITask;

/**
 * @author Yuji
 */
public class WorkerPool
{
	
//--------------------
//	    Attibutes
//--------------------
	
	/**
	 * The unique instance
	 */
	private static WorkerPool instance = new WorkerPool( 10 );

	/**
	 * Number of workers
	 */
	private int numWorkers;
	
	/**
	 * The available workers
	 */
	private Worker workers[ ];
	
	/**
	 * The queue of works to be done
	 */
	private LinkedList < Runnable > queue;
	
//--------------------
//	Constructors	
//--------------------
	
	/**
	 * Constructor
	 */
	private WorkerPool( int aNumWorkers )
	{
		super ( );
		numWorkers = aNumWorkers;
		workers = new Worker[ numWorkers ];
		queue = new LinkedList < Runnable >( );
		
		for ( int i = 0 ; i<workers.length ; i++ )
		{
			workers[ i ] = new Worker( queue );
			workers[ i ].start ( );
		}
	}
	
	/**
	 * Returns the unique instance
	 * @return WorkerPool
	 */
	public static WorkerPool getInstance( )
	{
		return instance;
	}
	
//--------------------
//	Methods
//--------------------
	
	/**
	 * Enqueues a task and then executes it when a worker is avaible
	 * @param task
	 */
	public void executeTask( ITask task )
	{
		synchronized ( queue )
		{
			/* Enqueues a task */
			queue.addLast (  task );
			/* Notifies the task is done */
			queue.notify ( );
		}
	}
	
}
