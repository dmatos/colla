package Utilities;

import java.util.LinkedList;
import java.util.List;

public class Contem 
{
	public static List<String> execute(List<String> keys, String key)
	{
		List<String> which = new LinkedList<String>();
		for(String aux:keys)
		{
			if(aux.contains(key.subSequence(0, key.length())))
				which.add(aux);
		}
		
		return which;
	}

}
