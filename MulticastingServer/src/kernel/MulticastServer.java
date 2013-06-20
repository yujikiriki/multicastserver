package kernel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import kernel.workerpool.WorkerPool;
import kernel.workerpool.workertasks.tasks.AttendClientTask;
import kernel.workerpool.workertasks.tasks.MulticastIPListenerTask;


/**
 * @uml.dependency   supplier="kernel.MulticastDataReplicationExpert"
 * @uml.dependency   supplier="kernel.workerpool.WorkerPool"
 */
public class MulticastServer
{
//--------------------
//	Attrinutes
//--------------------
		
	/**
	 * The worker pool 
	 */
	private WorkerPool workerPool;
	
	/**
	 * The server socket
	 */
	private ServerSocket serverSocket;
	
//--------------------
//	Constructor
//--------------------
	
	/**
	 * Constructor
	 */
	public MulticastServer( int port, int maxWorkers )
	{
		super ( );
		
		System.out.println ( "Im the server " + port );
		
		/* Create the server socket listrening in some port */
		try
		{
			serverSocket = new ServerSocket( port );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}

		/* Initialize the worker pool */
		workerPool = WorkerPool.getInstance ( );
		
		/* Listen to all the messages in the multicast ip */
		workerPool.executeTask ( new MulticastIPListenerTask( ) );
	}
	
//--------------------
//	Methods
//--------------------
	
	/**
	 * Attends a client. Loops infinitely.
	 */
	public void attendClient( )
	{
		while( true )
		{
			/* Get the client socket and run a new task */
			try
			{
				Socket clientSocket = serverSocket.accept ( );
				
				/* Attend a client in multithreaded mode */
				AttendClientTask attendClientTask = new AttendClientTask( clientSocket );
				workerPool.executeTask (  attendClientTask );
			}
			catch ( IOException e )
			{
				e.printStackTrace( );
			}
		}
	}
	
	/**
	 * Closes all the connections
	 */
	public void closeServer( )
	{
		try
		{
			serverSocket.close ( );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
}
