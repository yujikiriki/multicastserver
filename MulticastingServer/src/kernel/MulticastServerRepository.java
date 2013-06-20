/**
 * 
 */
package kernel;

import java.util.Enumeration;
import java.util.Hashtable;

import kernel.dto.ClientDTO;

/**
 * @author Yuji
 */
public class MulticastServerRepository extends Hashtable < Integer , ClientDTO >
{
//--------------------
//    Attibutes
//--------------------
	
	/**
	 * Serialization issues 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The unique instance
	 */
	private static MulticastServerRepository instance = new MulticastServerRepository( );
	
//--------------------
//    Constructors	
//--------------------

	/**
	 * Constructor
	 */
	private MulticastServerRepository( )
	{
		super( );
	}

//--------------------
//	Methods
//--------------------

	/**
	 * Returns the unique instance
	 */
	public static MulticastServerRepository getInstance( )
	{
		return instance;
	}
	
	/**
	 * Saves into the hash table the client 
	 * @param dto
	 */
	synchronized public void saveClientDTO( ClientDTO dto )
	{
		if ( !this.containsKey ( dto.getId ( ) ) )
		{
			this.put ( dto.getId ( ) , dto );
			printRepositoryContent ( );
		}
		else
		{
			System.out.println ( "[ WARNING ] The server already had save this client" );
		}
	}
	
	/**
	 * Returns a saved client 
	 * @param clientId
	 * @return
	 */
	synchronized public ClientDTO getClientDTO( Integer clientId )
	{
		if ( this.containsKey ( clientId ) )
		{
			return this.get ( clientId );
		}
		else
		{
			System.out.println ( "[ WARNING ] Client not found" );
			return null;
		}
	}
	
	/**
	 * Prints all the repository content
	 */
	synchronized public void printRepositoryContent( )
	{
		Enumeration < Integer > keys = this.keys ( );
		ClientDTO iterator = null;
		
		while ( keys.hasMoreElements ( ) )
		{
			System.out.println ( "==========  Saved clients  ========== " );
			Integer key = ( Integer ) keys.nextElement ( );
			iterator = this.getClientDTO ( key );
			System.out.println ( iterator );
			System.out.println ( "" );
		}		
	}
}

