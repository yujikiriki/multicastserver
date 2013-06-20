package kernel.serialization.strategies;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

import kernel.dto.ClientDTO;


/**
 * @author Yuji
 */
public class ClientDTOSerializeStrategy implements kernel.serialization.ISerializeStrategy
{
	/**
	 * Constructor
	 */
	public ClientDTOSerializeStrategy( )
	{
		super ( );
	}

	/*
	 * (non-Javadoc)
	 * @see kernel.serialization.ISerializeStrategy#serializeClientDTO(java.net.Socket, kernel.dto.ClientDTO)
	 */
	public void serializeClientDTO( Socket socket, ClientDTO dto )
	{
	}

	/*
	 * (non-Javadoc)
	 * @see kernel.serialization.ISerializeStrategy#serializeClientDTO(kernel.dto.ClientDTO)
	 */
	public byte [ ] serializeClientDTO( ClientDTO dto ) throws IOException
	{
	        ByteArrayOutputStream bos = new ByteArrayOutputStream ( );
	        ObjectOutput out = new ObjectOutputStream ( bos );
		out.writeObject ( dto );
		out.close ( );
		return bos.toByteArray ( );
	}
}
