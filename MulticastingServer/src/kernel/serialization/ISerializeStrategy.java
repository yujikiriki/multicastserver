package kernel.serialization;

import java.io.IOException;
import java.net.Socket;

import kernel.dto.ClientDTO;

/**
 * @author Yuji
 */
public interface ISerializeStrategy
{
	/**
	 * Serialize an object and sends it through a socket
	 * @param socket
	 * @param dto
	 */
	public void serializeClientDTO( Socket socket, ClientDTO dto );
	
	/**
	 * Serialize a ClientDTO to a byte array
	 * @param dto
	 * @return byte[ ]
	 */
	public byte [ ] serializeClientDTO( ClientDTO dto ) throws IOException;
}
