package kernel.serialization;

import java.io.IOException;
import java.net.Socket;

import kernel.dto.ClientDTO;


/**.
 * @author Yuji
 */
public interface IDeserializeStrategy
{
	/**
	 * Deserialize an object which was send throught a Socket.
	 * @param aSocket
	 * @return
	 */
	public ClientDTO deserializeClientDTO( Socket socket ) throws IOException, ClassNotFoundException;
	
	/**
	 * Deserialize an object when is sended through multicast ip
	 * @param byteArray
	 * @return
	 */
	public ClientDTO deserializeClientDTO( byte[ ] byteArray );
}
