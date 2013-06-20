package kernel.workerpool.workertasks.tasks;

import java.io.IOException;
import java.net.Socket;

import kernel.MulticastServerRepository;
import kernel.dto.ClientDTO;
import kernel.serialization.IDeserializeStrategy;
import kernel.serialization.strategies.ClientDTODeserializeStrategy;
import kernel.workerpool.WorkerPool;
import kernel.workerpool.workertasks.ITask;

public class AttendClientTask implements ITask
{
//--------------------
//    Attibutes
//--------------------
	
	/**
	 * The open socket where the client requests are received 
	 */
	private Socket socket; 
	
	/**
	 * The strategy to b used to deserialize the sended object
	 */
	private IDeserializeStrategy deserializeStrategy;
	
//--------------------
//    Constructors	
//--------------------
	
	/**
	 * Constructor 
	 */
	public AttendClientTask( Socket aSocket )
	{
		super ( );
		socket = aSocket;
		deserializeStrategy = new ClientDTODeserializeStrategy( );
	}
	
//--------------------
//	Methods
//--------------------

	/**
	 * Implements the task solution
	 */
	public void run( )
	{
		ClientDTO receivedDTO;
		try
		{
			receivedDTO = deserializeStrategy.deserializeClientDTO ( socket );
			saveReceivedIDataTransferObject ( receivedDTO );
			replicateReceivedData( receivedDTO );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		catch ( ClassNotFoundException e )
		{
			e.printStackTrace();
		}
	}

	/**
	 * @return the socket
	 */
	public Socket getSocket( )
	{
		return socket;
	}

	/**
	 * @param socket the socket to set
	 */
	public void setSocket( Socket socket )
	{
		this.socket = socket;
	}

//--------------------
//	Private Methods
//--------------------
	
	/**
	 * Saves the receved object from the socket
	 * @param dto
	 */
	private void saveReceivedIDataTransferObject( ClientDTO dto )
	{
		MulticastServerRepository.getInstance ( ).saveClientDTO ( dto );
	}
	
	/**
	 * Sends the received client through the muticasting ip 
	 * @param receivedDTO
	 */
	private void replicateReceivedData( ClientDTO receivedDTO )
	{
		ReplicateDataTask replicateDataTask = new ReplicateDataTask( receivedDTO );
		WorkerPool.getInstance ( ).executeTask (  replicateDataTask );		
	}
	
}