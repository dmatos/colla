package colla.dfs.app;
import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import colla.dfs.util.EditDistance;

public class SearchWords extends EditDistance {

	public SearchWords(){}
	
	public SearchWords(String filePath, String texto) {

		long time1 = System.nanoTime();

		JCL_facade javaCaLa = JCL_FacadeImpl.getInstance();
		File f = new File("../usefull_jars/WordFilesIndexer.jar");
		File[] args = { f };
		javaCaLa.register(args, "WordFilesIndexer");

		Set<String> s = new HashSet<String>();

		index(filePath, s);
		for (String aux : s) {
			javaCaLa.getResultBlocking(aux);
		}

		long time2 = System.nanoTime();
		System.err.println("indexing time: " + (time2 - time1));
		String CurLine = texto;

		try {

			if (!(CurLine.equals("quit"))) {

				boolean testAspas = false;
				if (CurLine.contains("\"")) {
					CurLine = CurLine.replaceAll("[\"\']", "");
					testAspas = true;
				}

				String[] words = CurLine.split("\"");
				words = CurLine.split(" ");
				String[] auxWordStrings = new String[words.length];
				for (int i = 0; i < words.length; i++) {
					JCL_result jclr = javaCaLa.getValue(words[0]
							.substring(0, 1));
					Set<String> aux = new HashSet<String>();
					if (jclr.getCorrectResult() != null) {
						aux.addAll((HashSet<String>) jclr.getCorrectResult());
						String name = Levenshtein(words[i], aux);
						if (name != "") {
							auxWordStrings[i] = name;
						} else {
							auxWordStrings[i] = words[i];
						}
					} else
						auxWordStrings = null;
				}
				if (auxWordStrings != null) {
					boolean diferente = false;
					String resul = "";
					for (int i = 0; i < auxWordStrings.length; i++) {
						resul = resul + auxWordStrings[i] + " ";
						if (!auxWordStrings[i].equals(words[i])) {
							diferente = true;
						}
					}
					int valor = -1;
					if (diferente) {
						String[] values = new String[2];
						values[0] = "Yes";
						values[1] = "No";

						valor = JOptionPane.showOptionDialog(null, "You meant "
								+ resul, "Search", JOptionPane.YES_NO_OPTION,
								JOptionPane.PLAIN_MESSAGE, null, values, "");
					}
					if (valor == 0) {
						searchWords(auxWordStrings, testAspas, texto);

					} else {
						searchWords(words, testAspas, texto);
					}
				} else {
					searchWords(words, testAspas, texto);
				}

			} else {
				javaCaLa.destroy();
			}

		} catch (Exception e) {
			System.err.println("problem in query");
			e.printStackTrace();
		}

	}

	public String executeSearch(String filePath, String texto) throws Exception{
		String result = "";
		long time1 = System.nanoTime();

		JCL_facade javaCaLa = JCL_FacadeImpl.getInstance();
		File f = new File("../usefull_jars/WordFilesIndexer.jar");
		File[] args = { f };
		javaCaLa.register(args, "WordFilesIndexer");

		Set<String> s = new HashSet<String>();

		index(filePath, s);
		for (String aux : s) {
			javaCaLa.getResultBlocking(aux);
		}

		long time2 = System.nanoTime();
		System.err.println("indexing time: " + (time2 - time1));
		String CurLine = texto;

		boolean testAspas = false;
		if (CurLine.contains("\"")) {
			CurLine = CurLine.replaceAll("[\"\']", "");
			testAspas = true;
		}

		String[] words = CurLine.split("\"");
		words = CurLine.split(" ");
		String[] auxWordStrings = new String[words.length];
		for (int i = 0; i < words.length; i++) {
			JCL_result jclr = javaCaLa.getValue(words[0].substring(0, 1));
			Set<String> aux = new HashSet<String>();
			if (jclr.getCorrectResult() != null) {
				aux.addAll((HashSet<String>) jclr.getCorrectResult());
				String name = Levenshtein(words[i], aux);
				if (name != "") {
					auxWordStrings[i] = name;
				} else {
					auxWordStrings[i] = words[i];
				}
			} else
				auxWordStrings = null;
		}
		if (auxWordStrings != null) {
			boolean diferente = false;
			String resul = "";
			for (int i = 0; i < auxWordStrings.length; i++) {
				resul = resul + auxWordStrings[i] + " ";
				if (!auxWordStrings[i].equals(words[i])) {
					diferente = true;
				}
			}
			int valor = -1;
			if (diferente) {
				String[] values = new String[2];
				values[0] = "Yes";
				values[1] = "No";
				//pesquisa aproximada
				valor = JOptionPane.showOptionDialog(null,
						"You meant " + resul, "Search",
						JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, values, "");
			}
			if (valor == 0) {
				result = searchWords(auxWordStrings, testAspas, texto);

			} else {
				result = searchWords(words, testAspas, texto);
			}
		} else {
			result = searchWords(words, testAspas, texto);
		}

		return result;

	}

	public String searchWords(String[] words, boolean aspas, String text) {
		JCL_facade javaCaLa = JCL_FacadeImpl.getInstance();
		Map<String, Set<String>> mainFile = new HashMap<String, Set<String>>();
		HashMap<String, LinkedList<Integer>> mainFileResult = new HashMap<String, LinkedList<Integer>>();

		String result = "";
		if (words.length < 2) {
			JCL_result jclr = javaCaLa.getValue(words[0]);
			if (jclr.getCorrectResult() != null) {
				if (jclr.getCorrectResult() != null) {
					mainFile.putAll((HashMap<String, Set<String>>) jclr
							.getCorrectResult());
					result = ResultOneWord(mainFile);
				}
			}

		} else {

			JCL_result jclr = javaCaLa.getValue(words[0]);
			if (jclr.getCorrectResult() != null) {
				mainFile.putAll((HashMap<String, Set<String>>) jclr
						.getCorrectResult());
				Map<String, Set<String>> File = new HashMap<String, Set<String>>();

				for (int i = 1; i < words.length; i++) {
					jclr = javaCaLa.getValue(words[i]);
					if (jclr.getCorrectResult() != null) {
						File.putAll((HashMap<String, Set<String>>) jclr
								.getCorrectResult());
						interMaps(mainFile, File, mainFileResult);

					}
					File.clear();
				}
				result = ResultSomeWords(mainFileResult, aspas);
			}
		}

		if (mainFile.isEmpty()) {
			result = "\"" + text + "\" not found.";
		}
		return result;
	}

	// Funcao que gera a resposta para uma palavra
	public String ResultOneWord(Map<String, Set<String>> map) {
		String result = "\n";
		for (String aux : map.keySet()) {
			result = result + aux + "\n";

		}
		return result;
	}

	// Funcao que gera a resposta para mais de uma palavra
	public String ResultSomeWords(
			HashMap<String, LinkedList<Integer>> mapResult, boolean aspas) {
		Set<String> result = new HashSet<String>();
		LinkedList<Integer> distWords = new LinkedList<Integer>();
		for (String nameArq : mapResult.keySet()) {
			distWords.clear();
			distWords = mapResult.get(nameArq);

			if (aspas == true) {
				Collections.sort(distWords);
				for (Integer aux : distWords) {
					if (aux == 1) {
						result.add(nameArq);
					}
				}
			} else {
				result.add(nameArq);
			}
		}
		String r = "\n";
		for (String a : result) {
			r = r + a + "\n";
		}
		return r;
	}

	// Calcula a dist�ncia entre as palavra
	public void interMaps(Map<String, Set<String>> mapFistWord,
			Map<String, Set<String>> mapNextWord,
			HashMap<String, LinkedList<Integer>> mapResult) {

		for (String nameFile : mapFistWord.keySet()) {
			if (mapNextWord.containsKey(nameFile)) {

				Set<String> setM2 = mapNextWord.get(nameFile);
				Set<String> setM1 = mapFistWord.get(nameFile);

				for (String cord : setM2) {
					String v[] = cord.split(",");
					for (String cord2 : setM1) {
						String v2[] = cord2.split(",");

						int a = Integer.parseInt(v[0]);
						int b = Integer.parseInt(v2[0]);
						int disL = Math.abs(a - b);
						a = Integer.parseInt(v[1]);
						b = Integer.parseInt(v2[1]);
						int disC = Math.abs(a - b);
						int dis = disL + disC;
						LinkedList<Integer> dist = new LinkedList<Integer>();
						dist.add(dis);
						if (mapResult.containsKey(nameFile)) {
							dist.addAll(mapResult.get(nameFile));
						}
						mapResult.put(nameFile, dist);

					}

				}

			}

		}
	}

	public void index(String dir, Set<String> s) {

		File f = new File(dir);
		LinkedList<String> allText = new LinkedList<String>();
		String readAux = null;

		if (!textIndexer.contains(dir)) {
			textIndexer.add(dir);
			if (f.isDirectory()) {
				String[] files = f.list();
				for (String oneFile : files)
					index(dir + oneFile, s);

			} else {
				try {

					if (f.getAbsolutePath().endsWith("txt")) {

						JCL_facade javaCaLa = JCL_FacadeImpl.getInstance();
						BufferedReader input = new BufferedReader(
								new FileReader(f));
						while ((readAux = input.readLine()) != null) {
							allText.add(readAux);
						}
						input.close();

						Object[] args = { allText,
								f.getAbsoluteFile().toString() };
						s.add(javaCaLa.execute("WordFilesIndexer", args));
					} else
						System.err.println("cannot index "
								+ f.getAbsolutePath());
				} catch (IOException e) {

					System.err.println("cannot open the file!");

				}
			}
		}
	}

	public static TreeSet<String> textIndexer = new TreeSet<String>();
}
