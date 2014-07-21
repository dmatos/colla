package Utilities;

import java.util.LinkedList;
import java.util.List;

public class Menor 
{
	public static List<String> execute(List<String> keys, String key)
	{
		List<String> which = new LinkedList<String>();
		for(String aux:keys)
		{
			if(Double.parseDouble(aux)<Double.parseDouble(key))
				which.add(aux);
		}
		
		return which;
	}

}

