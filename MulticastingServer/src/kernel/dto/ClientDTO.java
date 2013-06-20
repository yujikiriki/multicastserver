package kernel.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Hashtable;


public class ClientDTO extends Hashtable implements Serializable
{
//--------------------
//    Attibutes
//--------------------
	
	/**
	 * Serialization issues 
	 */
	private static final long serialVersionUID = -1146565800464004480L;
	
	/**
	 * Client id
	 */
	private Integer id;
	
	/**
	 * Client complete name 
	 */
	private String name;
	
	/**
	 * Client born date
	 */
	private Date date;
	
	/**
	 * Client general description
	 */
	private String desc;
	
//--------------------
//    Constructors	
//--------------------
	
	/**
	 * Constructor
	 */
	public ClientDTO( Integer id, String name, Date date, String desc )
	{
		super ( );
		this.id = id;
		this.name = name;
		this.date = date;
		this.desc = desc;
	}
	
//--------------------
//	Methods
//--------------------

	/**
	 * @return the date
	 */
	public Date getDate( )
	{
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate( Date date )
	{
		this.date = date;
	}

	/**
	 * @return the desc
	 */
	public String getDesc( )
	{
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc( String desc )
	{
		this.desc = desc;
	}

	/**
	 * @return the id
	 */
	public Integer getId( )
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId( Integer id )
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName( )
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName( String name )
	{
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Hashtable#toString()
	 */
	public String toString( )
	{
		String buffer = "Id: " +String.valueOf ( id.intValue ( ) ) + '\n' + "Name: " + name + '\n' + "Date: " + date + '\n' + "Description: " + desc;
		return buffer;
	}
}	
