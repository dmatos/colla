package colla.dfs.indexer;

import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class WordFilesIndexer {
	

	public void execute(LinkedList<String> input, String pathFile) {

			// efficient parallel counter
			JCL_facade javaCaLa = JCL_FacadeImpl.getInstance();
			Map<String, Set<String>> partialR = new HashMap<String, Set<String>>();
			int contLine = 0;
			for(String oneLine:input) {
				contLine++;
				if (!oneLine.equals("")) {
					String[] words = oneLine.split(" ");
					
					for (int j = 0; j < words.length; j++) {
						words[j] =words[j].replaceAll("[^a-zA-Z1-9 ]", "");
						if (words[j].length() > 2) {
							int colunm = 1 + j;
							String LC = contLine + "," + colunm;
							Set<String> LinCol = partialR.get(pathFile);
							if (LinCol == null) {
								LinCol = new HashSet<String>();
							}
							LinCol.add(LC);
							
							partialR.put(pathFile, LinCol);
							
							// begin of a critical section
							JCL_result jclr = javaCaLa.getValueLocking(words[j]);

							if (jclr.getCorrectResult() != null) {
								Map<String, Set<String>> freq = (HashMap<String, Set<String>>) jclr.getCorrectResult();
								if (freq.containsKey(pathFile)) {
									Set<String> lincol2 = freq.get(pathFile);
									LinCol.addAll(lincol2);
									freq.put(pathFile, LinCol);
								}
								partialR.putAll(freq);

							} else
								javaCaLa.instantiateGlobalVar(words[j],new HashMap<String, Set<String>>());

							javaCaLa.setValue(words[j], partialR);

							LinCol.clear();

							//GlobalVar para a aproximacao de string
							String fistLetter = words[j].substring(0, 1);
							Set<String> AproxWords = new HashSet<String>();
							AproxWords.add(words[j]);
							jclr = javaCaLa.getValueLocking(fistLetter);
							
							if (jclr.getCorrectResult()!= null) {
								AproxWords.addAll((Set<String>) jclr.getCorrectResult());

							} else {
								javaCaLa.instantiateGlobalVar(fistLetter,new HashSet<String>());
							}
							
							javaCaLa.setValue(fistLetter, AproxWords);
							
						}
					}
					partialR.clear();
				}
			}
			contLine = 0;
	}

}
