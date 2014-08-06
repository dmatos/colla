package InterfaceViewKernel;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import Kernel.Separate;

public class ProcessMeasure
{
	public static Set<String> execute(List<Set<String>> allResults)
	{
		Set<String> finalResult = new TreeSet<String>();
		Map<String, String> auxMapResult = new TreeMap<String,String>();
						
		for(Set<String> resulteachHost : allResults)
		{
			for(String eachLineResult : resulteachHost)
			{
				System.out.println(eachLineResult);

				String [] slicedQuery = eachLineResult.split("\\$");
								
				if(!auxMapResult.containsKey(slicedQuery[0]))
					auxMapResult.put(slicedQuery[0], Separate.separate(eachLineResult,"#"));
				
				else
				{
					String [] slicedfinalResult = auxMapResult.get(slicedQuery[0]).split("\\$");
					String [] slicedSaveMeasure = slicedfinalResult[1].split("\\#");
					String [] slicedOriginalMeasure = slicedQuery[1].split("\\#");
															
					String finalLine = new String("");
					
					for(int i = 0; i < slicedOriginalMeasure.length; i++)
					{
						if(slicedOriginalMeasure[i].split("\\:")[1].equals("min"))
						{
							if(Double.parseDouble(slicedOriginalMeasure[i].split("\\:")[2]) < Double.parseDouble(slicedSaveMeasure[i].split("\\:")[2]))
								finalLine += (slicedOriginalMeasure[i].split("\\:")[0] + ":" + slicedOriginalMeasure[i].split("\\:")[1] + ":" + slicedOriginalMeasure[i].split("\\:")[2] + "#");
							else
								finalLine += (slicedSaveMeasure[i].split("\\:")[0] + ":" + slicedSaveMeasure[i].split("\\:")[1] + ":" + slicedSaveMeasure[i].split("\\:")[2] + "#");
						}
						
						if(slicedOriginalMeasure[i].split("\\:")[1].equals("max"))
						{												
							if(Double.parseDouble(slicedOriginalMeasure[i].split("\\:")[2]) >= Double.parseDouble(slicedSaveMeasure[i].split("\\:")[2]))
								finalLine += (slicedOriginalMeasure[i].split("\\:")[0] + ":" + slicedOriginalMeasure[i].split("\\:")[1] + ":" + slicedOriginalMeasure[i].split("\\:")[2] + "#");
							else
								finalLine += (slicedSaveMeasure[i].split("\\:")[0] + ":" + slicedSaveMeasure[i].split("\\:")[1] + ":" + slicedSaveMeasure[i].split("\\:")[2] + "#");
						}
						
						if(slicedOriginalMeasure[i].split("\\:")[1].equals("sum"))
						{
							String [] avgParOne = slicedOriginalMeasure[i].split("\\:");
							String [] avgParTwo = slicedSaveMeasure[i].split("\\:");
							
							Float sum = (Float.parseFloat(avgParOne[2]) + Float.parseFloat(avgParTwo[2]));
							finalLine += (slicedSaveMeasure[i].split("\\:")[0] + ":" + slicedSaveMeasure[i].split("\\:")[1] + ":" + sum + "" + "#");
						}
						
						if(slicedOriginalMeasure[i].split("\\:")[1].equals("count"))
						{
							String [] avgParOne = slicedOriginalMeasure[i].split("\\:");
							String [] avgParTwo = slicedSaveMeasure[i].split("\\:");
							
							Integer count = (Integer.parseInt(avgParOne[2]) + Integer.parseInt(avgParTwo[2]));
							finalLine += (slicedSaveMeasure[i].split("\\:")[0] + ":" + slicedSaveMeasure[i].split("\\:")[1] + ":" + count + "" + "#");
						}
					}
					
					auxMapResult.put(slicedQuery[0],slicedQuery[0] + "$" + Separate.separate(finalLine, "#"));	
					finalLine = "";
				} 
			}
			
			System.out.println();
		}
		System.out.println();
		
		for(String auxKey : auxMapResult.keySet())
			finalResult.add(auxMapResult.get(auxKey));
		
		return finalResult;
	}

	public static Set<String> executeWithAverage(List<Set<String>> allResults)
	{
		Set<String> finalResult = new TreeSet<String>();
		Map<String, String> auxMapResult = new TreeMap<String,String>();
						
		for(Set<String> resulteachHost : allResults)
		{
			for(String eachLineResult : resulteachHost)
			{
				System.out.println(eachLineResult);
				
				String [] slicedQuery = eachLineResult.split("\\$");
								
				if(!auxMapResult.containsKey(slicedQuery[0]))
					auxMapResult.put(slicedQuery[0], Separate.separate(eachLineResult,"#"));
				
				else
				{
					String [] slicedfinalResult = auxMapResult.get(slicedQuery[0]).split("\\$");
					String [] slicedSaveMeasure = slicedfinalResult[1].split("\\#");
					String [] slicedOriginalMeasure = slicedQuery[1].split("\\#");
										
					String finalLine = new String("");
					
					for(int i = 0; i < slicedOriginalMeasure.length; i++)
					{
						if(slicedOriginalMeasure[i].split("\\:")[1].equals("min"))
						{							
							if(Double.parseDouble(slicedOriginalMeasure[i].split("\\:")[2]) < Double.parseDouble(slicedSaveMeasure[i].split("\\:")[2]))
								finalLine += (slicedOriginalMeasure[i].split("\\:")[0] + ":" + slicedOriginalMeasure[i].split("\\:")[1] + ":" + slicedOriginalMeasure[i].split("\\:")[2] + "#");
							else
								finalLine += (slicedSaveMeasure[i].split("\\:")[0] + ":" + slicedSaveMeasure[i].split("\\:")[1] + ":" + slicedSaveMeasure[i].split("\\:")[2] + "#");
						}
						
						if(slicedOriginalMeasure[i].split("\\:")[1].equals("max"))
						{							
							if(Double.parseDouble(slicedOriginalMeasure[i].split("\\:")[2]) > Double.parseDouble(slicedSaveMeasure[i].split("\\:")[2]))
								finalLine += (slicedOriginalMeasure[i].split("\\:")[0] + ":" + slicedOriginalMeasure[i].split("\\:")[1] + ":" + slicedOriginalMeasure[i].split("\\:")[2] + "#");
							else
								finalLine += (slicedSaveMeasure[i].split("\\:")[0] + ":" + slicedSaveMeasure[i].split("\\:")[1] + ":" + slicedSaveMeasure[i].split("\\:")[2] + "#");
						}
						
						if(slicedOriginalMeasure[i].split("\\:")[1].equals("sum"))
						{
							String [] avgParOne = slicedOriginalMeasure[i].split("\\:");
							String [] avgParTwo = slicedSaveMeasure[i].split("\\:");
							
							Float sum = (Float.parseFloat(avgParOne[2]) + Float.parseFloat(avgParTwo[2]));
							finalLine += (slicedSaveMeasure[i].split("\\:")[0] + ":" + slicedSaveMeasure[i].split("\\:")[1] + ":" + sum + "" + "#");
						}
						
						if(slicedSaveMeasure[i].split("\\:")[1].equals("avg"))
						{
							String [] avgParOne = slicedOriginalMeasure[i].split("\\:")[2].split("\\;");
							String [] avgParTwo = slicedSaveMeasure[i].split("\\:")[2].split("\\;");
							
							Float sum = (Float.parseFloat(avgParOne[0]) + Float.parseFloat(avgParTwo[0]));
							Integer count = (Integer.parseInt(avgParOne[1]) + Integer.parseInt(avgParTwo[1]));
							
							finalLine += (slicedSaveMeasure[i].split("\\:")[0] + ":" + slicedSaveMeasure[i].split("\\:")[1] + ":" + sum + ";" + count + "" + "#");
						}
						
						if(slicedOriginalMeasure[i].split("\\:")[1].equals("count"))
						{
							String [] avgParOne = slicedOriginalMeasure[i].split("\\:");
							String [] avgParTwo = slicedSaveMeasure[i].split("\\:");
							
							Integer count = (Integer.parseInt(avgParOne[2]) + Integer.parseInt(avgParTwo[2]));
							finalLine += (slicedSaveMeasure[i].split("\\:")[0] + ":" + slicedSaveMeasure[i].split("\\:")[1] + ":" + count + "" + "#");
						}
					}

					auxMapResult.put(slicedQuery[0],slicedQuery[0] + "$" + finalLine);	
					finalLine = "";
				} 
			}
		}
		
		for(String auxKey : auxMapResult.keySet())
		{
			String finalResultLine = new String("");
			
			String [] slicedQuery = auxMapResult.get(auxKey).split("\\$");
			String [] slicedMeasure = slicedQuery[1].split("#");
						
			Float avg = (float) 0.0;
			
			for(String aux : slicedMeasure)
			{
				String [] slicedOneMeasure = aux.split("\\:");
				if(slicedOneMeasure[1].equals("avg"))
				{
					String [] values = slicedOneMeasure[2].split("\\;");
					Float sum = Float.parseFloat(values[0]);
					Integer count = Integer.parseInt(values[1]);
					avg = sum/count;
					
					finalResultLine += (slicedOneMeasure[0] + ":" + slicedOneMeasure[1] + "" + ":" + avg + "#");
				}
				
				else
				{
					finalResultLine += (aux + "#");
				}
			}
			
			finalResult.add(slicedQuery[0] + "$" + Separate.separate(finalResultLine,"#"));
		}
		
		return finalResult;
	}
}
