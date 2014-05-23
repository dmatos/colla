package Utilities;

import java.util.Set;
import java.util.TreeSet;

public class Contem 
{
	public static Set<String> execute(Set<String> keys, String key)
	{
		Set<String> which = new TreeSet<String>();
		for(String aux:keys)
		{
			if(aux.contains(key.subSequence(0, key.length())))
				which.add(aux);
		}
		
		return which;
	}

}
