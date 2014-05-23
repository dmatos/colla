package Utilities;

import java.util.Set;
import java.util.TreeSet;

public class Menor 
{
	public static Set<String> execute(Set<String> keys, String key)
	{
		Set<String> which = new TreeSet<String>();
		for(String aux:keys)
		{
			if(Double.parseDouble(aux)>Double.parseDouble(key))
				which.add(aux);
		}
		
		return which;
	}

}

