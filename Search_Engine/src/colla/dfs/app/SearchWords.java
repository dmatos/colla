package colla.dfs.app;
import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.tika.*;
import org.apache.tika.exception.TikaException;
//import org.apache.pdfbox.pdfparser.PDFObjectStreamParser;
//import org.apache.log4j.*;



import colla.dfs.util.EditDistance;

import javax.swing.JOptionPane;

//import src.colla.dfs.util.*;

public class SearchWords extends EditDistance {
	
	public SearchWords() {

	}

	public String SearchWord(String texto) throws TikaException {	
		
		JCL_facade javaCaLa = JCL_FacadeImpl.getInstance();
		String CurLine = texto;
		String r = null;
		try {
			boolean testAspas = CurLine.contains("\"");
			CurLine = CurLine.replaceAll("[\"\']", "");
			String[] words = CurLine.split(" ");
			words = CurLine.split(" ");
			String[] auxwords = new String[words.length];// variavel que vai auxiliar na aproximação de string
			for (int i = 0; i < words.length; i++) {
				
				JCL_result jclr = javaCaLa.getValue(words[0].substring(0, 1));
				Set<String> aux = new HashSet<String>();
				if (jclr.getCorrectResult() != null) {
					aux.addAll((HashSet<String>) jclr.getCorrectResult());
					String name = Levenshtein(words[i], aux);
					if (name != "") {
						auxwords[i] = name;
					} else {
						auxwords[i] = words[i];
					}
				}
			}
			for (int j = 0; j < auxwords.length; j++) {
				if(!auxwords[j].equals(words[j])){
					diferente = true;
				}	
			}
			
			if (diferente) {
				r = searchWords(auxwords, testAspas, texto);

			} else {
				r =  searchWords(words, testAspas, texto);
			}
				
		} catch (Exception e) {
			System.err.println("problem in query");
			e.printStackTrace();
		}
		return r;
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
					mainFile.putAll((HashMap<String, Set<String>>) jclr.getCorrectResult());
					result = ResultOneWord(mainFile);
				}
			}

		} else {

			JCL_result jclr = javaCaLa.getValue(words[0]);
			if (jclr.getCorrectResult() != null) {
				mainFile.putAll((HashMap<String, Set<String>>) jclr.getCorrectResult());
				Map<String, Set<String>> File = new HashMap<String, Set<String>>();

				for (int i = 1; i < words.length; i++) {
					jclr = javaCaLa.getValue(words[i]);
					if (jclr.getCorrectResult() != null) {
						File.putAll((HashMap<String, Set<String>>) jclr.getCorrectResult());
						interMaps(mainFile, File, mainFileResult);
					}
					File.clear();
				}
				result = ResultSomeWords(mainFileResult, aspas);
			}
		}
		
		if (!mainFile.isEmpty()) {
			return "The word(s) \"" + text + "\" found in the file(s):" + result;
		} else {
			return  "The word(s) \"" + text + "\" not found in the file(s)!";
		}
	}

	// Fução que gera a resposta para uma palavra
	public String ResultOneWord(Map<String, Set<String>> map) {
		String result = "\n";
		for (String aux : map.keySet()) {
			result = result + aux + "\n";

		}
		return result;
	}

	// Função que gera a resposta para mais de uma palavra
	public String ResultSomeWords(HashMap<String, LinkedList<Integer>> mapResult, boolean aspas) {
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

	// Calcula a distância entre as palavra
	public void interMaps(Map<String, Set<String>> mapFistWord, Map<String, Set<String>> mapNextWord, HashMap<String, LinkedList<Integer>> mapResult) {

		for (String nameFile : mapFistWord.keySet()) {
			if (mapNextWord.containsKey(nameFile)) {

				Set<String> setM2 = mapNextWord.get(nameFile);
				Set<String> setM1 = mapFistWord.get(nameFile);

				for (String cord : setM2) {
					String v[] = cord.split(",");
					for (String cord2 : setM1) {
						String v2[] = cord2.split(",");
					
						Integer disL = Math.abs(Integer.parseInt(v[0]) - Integer.parseInt(v2[0]));	
						Integer disC = Math.abs(Integer.parseInt(v[1]) - Integer.parseInt(v2[1]));
						Integer dis = disL + disC;
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


	public void index(String dir, Set<String> s) throws TikaException {

		File f = new File(dir);

		if (!textIndexer.contains(dir)) {
			textIndexer.add(dir);
			if (f.isDirectory()) {
				String[] files = f.list();
				for (String oneFile : files)
					index(dir + oneFile, s);

			} else {
				try {
					if (f.getAbsolutePath().endsWith(".pdf")
							|| f.getAbsolutePath().endsWith(".odt")
							|| f.getAbsolutePath().endsWith(".doc")
							|| f.getAbsolutePath().endsWith(".docx")
							|| f.getAbsolutePath().endsWith(".ppt")
							|| f.getAbsolutePath().endsWith(".pptx")
							|| f.getAbsolutePath().endsWith(".xls")
							|| f.getAbsolutePath().endsWith(".txt")
							|| f.getAbsolutePath().endsWith(".rtf")) {
					    text = null;
						text = getTika().parseToString(f);
						indexFile(f.getAbsolutePath(), s);

					} else {
						System.err.println("cannot read the file!");
					}
				} catch (IOException e) {
					System.err.println("cannot open the file!");
				}
			}
		}
	}

	public Tika getTika() {
		if (tika == null) {
			tika = new Tika();
		}
		return tika;
	}

	public void indexFile(String FilePath, Set<String> s) throws IOException {

		JCL_facade javaCaLa = JCL_FacadeImpl.getInstance();
		String readAux = null;
		ArrayList<byte[]> allText = new ArrayList<byte[]>();
		BufferedReader input = null;
		
		try {
			File f2 = new File("txtIndexer.txt");
			f2.createNewFile();
			FileOutputStream resul = new FileOutputStream(f2);
			resul.flush();
			resul.write(text.getBytes());
			input = new BufferedReader(new FileReader(f2));
			while ((readAux = input.readLine()) != null) {
				allText.add(readAux.getBytes());
			}
			f2.delete();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (!allText.isEmpty()) {

			Object[] args = { allText, FilePath };
			s.add(javaCaLa.execute("WordFilesIndexer", args));
			allText.clear();
		}
		input.close();
		
	}

	public static TreeSet<String> textIndexer = new TreeSet<String>();
	private Tika tika;
	private String text;
	private boolean diferente = false;
}
