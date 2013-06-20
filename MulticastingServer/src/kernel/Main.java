package kernel;

/**
 * @author Yuji
 */
public class Main
{
	/**
	 * @param args
	 */
	public static void main( String [ ] args )
	{
		MulticastServer server = new MulticastServer( 7778, 10 );
		System.out.println ( "The server is up and running" );
		server.attendClient ( );
	}
}

