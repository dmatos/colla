package Kernel;

public class Separate 
{
	public static String separate(String sentence,String separation)
	{
		String[] slice = sentence.split("\\" + separation);
		
		String rightResult = slice[0];
		
		for(int i = 1; i < slice.length; i++)
			rightResult += (separation + slice[i]);
		
		return rightResult;
	}
}
