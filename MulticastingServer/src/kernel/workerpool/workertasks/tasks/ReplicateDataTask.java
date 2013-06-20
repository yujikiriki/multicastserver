package kernel.workerpool.workertasks.tasks;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import kernel.dto.ClientDTO;
import kernel.serialization.ISerializeStrategy;
import kernel.serialization.strategies.ClientDTOSerializeStrategy;
import kernel.workerpool.workertasks.ITask;

public class ReplicateDataTask implements ITask
{
//--------------------
//    Attibutes
//--------------------
	
	/**
	 * The client to be replicated
	 */
	private ClientDTO client;
	
	/**
	 * The serialization strategy
	 */
	private ISerializeStrategy serializeStrategy = new ClientDTOSerializeStrategy( );
	
//--------------------
//    Constructors	
//--------------------

	/**
	 * Constructor
	 */
	public ReplicateDataTask( ClientDTO dto )
	{
		super ( );
		client = dto;
	}

	
//--------------------
//	Methods
//--------------------
	
	public void run( )
	{
		try
		{
			InetAddress ipMulticastGroup = InetAddress.getByName ( "224.1.2.3" );
			MulticastSocket socket = new MulticastSocket ( 666 );
			byte[ ] byteArray = serializeStrategy.serializeClientDTO ( client );
			DatagramPacket packet = new DatagramPacket ( byteArray , byteArray.length, ipMulticastGroup , 666 );
			socket.joinGroup ( ipMulticastGroup );
			socket.send ( packet );
		}
		catch ( Exception e )
		{
			e.printStackTrace ( );
		}
	}
}
