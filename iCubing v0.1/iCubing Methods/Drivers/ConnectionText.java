package Drivers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConnectionText implements Connection
{
	private BufferedReader in;
	private String text;
	
	public void configure(String text)
	{
		//devemos ler de um arquivo XML ou TXT as configuracoes necessarias
		//nesta versao estamos fazendo hard coded
		this.text = text;
	}

	public void connect()
	{
		try 
		{
			//in = new BufferedReader(new FileReader(text));	 
	        in = new BufferedReader(new InputStreamReader(new FileInputStream(text), "ISO-8859-1"));
	        
	    } 
		
		catch (IOException e) 
		{
	    	System.out.println("No Connection");
	    	e.printStackTrace();
	    }
		
	}
	
	public String[] next()
	{
		String str = null;
		
		try 
		{
			str = in.readLine();
						
			if(str == null) 
			{
				in.close();
				return null;
			}
			
		} catch (IOException e)
		{
			try 
			{
				in.close();
			} 
			
			catch (IOException e1)
			{
				
			}
			return null;
		}
		
		String[] s = str.split(" ");
		   	
    	return s;	
		    		
	}

}
