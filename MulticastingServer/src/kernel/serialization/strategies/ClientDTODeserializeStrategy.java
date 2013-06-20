package kernel.serialization.strategies;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import kernel.dto.ClientDTO;
import kernel.serialization.IDeserializeStrategy;

/**
 * @author Yuji
 */
public class ClientDTODeserializeStrategy implements IDeserializeStrategy
{
	/**
	 * Constructor
	 */
	public ClientDTODeserializeStrategy( )
	{
		super ( );
	}

	/*
	 * (non-Javadoc)
	 * @see kernel.serialization.IDeserializeStrategy#deserializeClientDTO(java.net.Socket)
	 */
	public ClientDTO deserializeClientDTO( Socket socket ) throws IOException, ClassNotFoundException
	{
		ObjectInputStream ois = new ObjectInputStream( socket.getInputStream ( ) );
		return ( ClientDTO ) ois.readObject( );	
	}

	/*
	 * (non-Javadoc)
	 * @see kernel.serialization.IDeserializeStrategy#deserializeClientDTO(byte[])
	 */
	public ClientDTO deserializeClientDTO( byte [ ] byteArray )
	{
	       Object object = null;
		try
		{
			object = new ObjectInputStream ( new ByteArrayInputStream ( byteArray ) ).readObject ( );
		}
		catch ( IOException e )
		{
			e.printStackTrace ( );
		}
		catch ( ClassNotFoundException e )
		{
			e.printStackTrace ( );
		}
		return ( ClientDTO ) object;
	}
}
