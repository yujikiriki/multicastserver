package kernel.workerpool.workertasks.tasks;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import kernel.MulticastServerRepository;
import kernel.dto.ClientDTO;
import kernel.serialization.IDeserializeStrategy;
import kernel.serialization.strategies.ClientDTODeserializeStrategy;
import kernel.workerpool.workertasks.ITask;

/**
 * @author Yuji
 */
public class MulticastIPListenerTask implements ITask
{
//--------------------
//    Attibutes
//--------------------
	
	/**
	 * The deserialize strategy
	 */
	private IDeserializeStrategy deserializeStrategy;
//--------------------
//    Constructors	
//--------------------

	/**
	 * Constructor
	 */
	public MulticastIPListenerTask( )
	{
		super ( );
		deserializeStrategy = new ClientDTODeserializeStrategy( );
	}

//--------------------
//	Methods
//--------------------
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run( )
	{
		try
		{
			// Defining the connection to the Multicast group and socket
			InetAddress ipMulticastGroup = InetAddress.getByName ( "224.1.2.3" );
			MulticastSocket socket = new MulticastSocket ( 666 );
			socket.joinGroup ( ipMulticastGroup );
			DatagramPacket packet = null;
			byte [ ] buffer = null;

			// Listen forever messages published on the multicast socket
			while ( true )
			{
				buffer = new byte [ 1000 ];
				packet = new DatagramPacket ( buffer , buffer.length );
				socket.receive ( packet );
				ClientDTO dto = deserializeStrategy.deserializeClientDTO ( buffer );
				saveReceivedClientoDTO ( dto );
			}

		}
		catch ( Exception e )
		{
			e.printStackTrace ( );

		}
	}
	
	/**
	 * Saves the recevied client received through the ip multicasting
	 * @param dto
	 */
	private void saveReceivedClientoDTO( ClientDTO dto )
	{
		MulticastServerRepository.getInstance ( ).saveClientDTO( dto );
	}
}

