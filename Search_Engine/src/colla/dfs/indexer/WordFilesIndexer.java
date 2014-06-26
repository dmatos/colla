package colla.dfs.indexer;

import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordFilesIndexer {

	public void execute(ArrayList<byte[]> input, String pathFile, int linha)throws IOException {

		JCL_facade javaCaLa = JCL_FacadeImpl.getInstance();
		Map<String, Set<String>> partialR = new TreeMap<String, Set<String>>();

		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		File tempFile = File.createTempFile("auxFile", ".tmp", tempDir);
		FileOutputStream finalTempFile = new FileOutputStream(tempFile);

		for (int i = 0; i < input.size(); i++) {
			finalTempFile.write(input.get(i));
			String newLine = new String("\n");
			finalTempFile.write(newLine.getBytes());
		}

		BufferedReader input2 = new BufferedReader(new FileReader(tempFile));
		int contLine = linha;
		String oneLine = null;
		while ((oneLine = input2.readLine()) != null) {
			contLine++;
			if (!oneLine.equals("")) {
				String[] words = oneLine.split(" ");
				for (int j = 0; j < words.length; j++) {
					partialR.clear();
					words[j] = words[j].toLowerCase();
					if (words[j].length() > 2) {
						int colunm = 1 + j;
						String LC = contLine + "," + colunm;
						Set<String> LinCol = new TreeSet<String>();
						LinCol.add(LC);
						partialR.put(pathFile, LinCol);

						JCL_result jclr = javaCaLa.getValueLocking(words[j]);

						if (jclr.getCorrectResult() != null) {
							Map<String, Set<String>> freq = (Map<String, Set<String>>) jclr.getCorrectResult();
							if (freq.containsKey(pathFile)) {
								LinCol.addAll(freq.get(pathFile));
								freq.put(pathFile, LinCol);
							}
							partialR.putAll(freq);

						} else
							javaCaLa.instantiateGlobalVar(words[j],
									new TreeMap<String, Set<String>>());

						javaCaLa.setValue(words[j], partialR);
						LinCol.clear();

						String fistLetter = words[j].substring(0, 1);
						Set<String> AproxWords = new TreeSet<String>();
						AproxWords.add(words[j]);
						jclr = javaCaLa.getValueLocking(fistLetter);

						if (jclr.getCorrectResult() != null) {
							AproxWords.addAll((Set<String>) jclr.getCorrectResult());

						} else {
							javaCaLa.instantiateGlobalVar(fistLetter,new TreeSet<String>());
						}
						javaCaLa.setValue(fistLetter, AproxWords);

					}
				}

			}
		}
		contLine = 0;
	}

}
