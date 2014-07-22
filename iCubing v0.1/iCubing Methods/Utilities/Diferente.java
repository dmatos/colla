package Utilities;

import java.util.LinkedList;
import java.util.List;

public class Diferente
{
	public static List<String> execute(List<String> keys, String key)
	{
		List<String> which = new LinkedList<String>();
		for(String aux:keys)
		{
			if(!aux.equalsIgnoreCase(key))
				which.add(aux);
		}
		
		return which;
	}
}

