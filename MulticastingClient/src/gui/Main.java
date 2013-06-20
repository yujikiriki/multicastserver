package gui;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;

import kernel.dto.ClientDTO;

public class Main
{
	/**
	 * @param args
	 */
	public static void main( String [ ] args )
	{
		try
		{
			// This constructor will block until the connection succeeds
			Socket socket = new Socket ( "127.0.0.1", 7777 );
			ClientDTO dto = new ClientDTO( new Integer( 1 ), "Yuji Kiriki R.", Calendar.getInstance ().getTime ( ), "Me estoy serializando!" );
			ObjectOutputStream out = new ObjectOutputStream( socket.getOutputStream ( ) );
			out.writeObject ( dto );
			out.close( );
		}
		catch ( UnknownHostException e )
		{
		}
		catch ( IOException e )
		{
		}
	}

}
