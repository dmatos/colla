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
	  
    public void execute(ArrayList<byte[]> input, String pathFile, int linha) throws IOException {
		TreeMap<String, TreeSet<String>> partialR = new TreeMap<String, TreeSet<String>>();  
		TreeMap<String,TreeMap<String, TreeSet<String>>> WordsIndex = new TreeMap<String, TreeMap<String, TreeSet<String>>>();
	    TreeMap<String,TreeSet<String>> aproxWords = new TreeMap<String, TreeSet<String>>();   
	    structOut();
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		File tempFile = File.createTempFile("auxFile", ".tmp", tempDir);
		FileOutputStream finalTempFile = new FileOutputStream(tempFile);
	        
	        for(int i = 0; i < input.size(); i++)
	        {
                    finalTempFile.write(input.get(i));
                    String newLine = new String("\n");
                    finalTempFile.write(newLine.getBytes());
	        }
	    
        BufferedReader input2 =  new BufferedReader(new FileReader(tempFile)); 
		int contLine = linha;
		String oneLine = null;
		while((oneLine = input2.readLine())!= null){
			contLine++;
			if (!oneLine.equals("")) {
				String[] words = oneLine.split(" ");
				for (int j = 0; j < words.length; j++) {
					partialR.clear();
					words[j] = cleanWord(words[j]);
					words[j] = words[j].toLowerCase();
					if (words[j].length() > 2 && words[j].length() < 20 && didntSearch(words[j])) {
						int colunm = 1 + j;
						String LC = contLine + "," + colunm;
						TreeSet<String> LinCol = new TreeSet<String>();
						LinCol.add(LC);
						partialR.put(pathFile, LinCol);
						
						if (WordsIndex.containsKey(words[j])) {
							TreeMap<String, TreeSet<String>> freq = WordsIndex.get(words[j]);
							if (freq.containsKey(pathFile)) {
								LinCol.addAll(freq.get(pathFile));
								freq.put(pathFile, LinCol);
							}
							partialR.putAll(freq);

						}
                        WordsIndex.put(words[j], partialR);
						LinCol.clear();

                                       
						
						String fistLetter = words[j].substring(0, 1);
						TreeSet<String> AproxWords = new TreeSet<String>();
						if(letter.contains(fistLetter)){
							AproxWords.add(words[j]);
							if (aproxWords.containsKey(fistLetter)) {
								AproxWords.addAll(aproxWords.get(fistLetter));
							}                                              
							aproxWords.put(fistLetter, AproxWords);
						}	
					}
				}
				
			}
		}
		
		 JCL_facade javaCaLa = JCL_FacadeImpl.getInstance();
         
         for(String word: WordsIndex.keySet()){
        	 
             JCL_result jclr = javaCaLa.getValueLocking(word);
             TreeMap<String, TreeSet<String>> freqWF = WordsIndex.get(word);  
             if(jclr.getCorrectResult()!=null){
                 TreeMap<String, TreeSet<String>> freqGV = (TreeMap<String, TreeSet<String>>) jclr.getCorrectResult();
                     if (freqGV.containsKey(pathFile)) {
                         TreeSet<String> LinCol = new TreeSet<String>();
                         if(freqWF.get(pathFile)!=null)
                        	 LinCol.addAll(freqWF.get(pathFile));
                         if(freqGV.get(pathFile)!=null)
                        	 LinCol.addAll(freqGV.get(pathFile));                      
                         freqGV.put(pathFile, LinCol);
                     }else{
                         freqGV.putAll(freqWF);
                     }
                     
                     javaCaLa.setValue(word, freqGV);

             }else
                 javaCaLa.instantiateGlobalVar(word, WordsIndex.get(word));
             
         }
         
         for(String FistLetter: aproxWords.keySet()){
             JCL_result jclr = javaCaLa.getValueLocking(FistLetter);
             TreeSet<String> Aprox =aproxWords.get(FistLetter) ;
             if(jclr.getCorrectResult() != null){
                  Aprox.addAll( (TreeSet<String>) jclr.getCorrectResult());
                  javaCaLa.setValue(FistLetter, Aprox);
             }else
                 javaCaLa.instantiateGlobalVar(FistLetter, Aprox);                         
         }              
}

        public static String cleanWord(String words){
        	words = words.replaceAll("\\.", "");
        	words = words.replaceAll("\\,", "");
        	words = words.replaceAll("\\;", "");
        	words = words.replaceAll("\\:", "");
        	words = words.replaceAll("\\!", "");
        	words = words.replaceAll("\\?", "");
        	words = words.replaceAll("\\)", "");
        	words = words.replaceAll("\\(", "");
        	words = words.replaceAll("\\-", "");
        	words = words.replaceAll("\\+", "");
        	words = words.replaceAll("\\*", "");
        	words = words.replaceAll("\\#", "");
        	words = words.replaceAll("\\{", "");
        	words = words.replaceAll("\\}", "");
        	words = words.replaceAll("\\[", "");
        	words = words.replaceAll("\\]", "");
        	words = words.replaceAll("\"", "");
        	words = words.replaceAll("/", "");
        	words = words.replaceAll(" ", "");
        	words = words.replaceAll("0", "");
        	words = words.replaceAll("1", "");
        	words = words.replaceAll("2", "");
        	words = words.replaceAll("3", "");
        	words = words.replaceAll("4", "");
        	words = words.replaceAll("5", "");
        	words = words.replaceAll("6", "");
        	words = words.replaceAll("7", "");
        	words = words.replaceAll("8", "");
        	words = words.replaceAll("9", "");
        	
        	return words;
        }
        public static void structOut(){
        	
        	wordsout.add("que");   wordsout.add("tras");
        	wordsout.add("sem");   wordsout.add("por");
        	wordsout.add("você");  wordsout.add("nos");
        	wordsout.add("tudo");  wordsout.add("todos");
        	wordsout.add("todas"); wordsout.add("todas");
        	wordsout.add("par");   wordsout.add("para");
        	wordsout.add("podem"); wordsout.add("posso");
        	wordsout.add("mas");   wordsout.add("mais");
        	wordsout.add("tipo");  wordsout.add("tenha");
        	wordsout.add("ter");   wordsout.add("são");
        	wordsout.add("sua");   wordsout.add("ser");
        	wordsout.add("seja");  wordsout.add("qual");
        	wordsout.add("quais"); wordsout.add("quanto");
        	wordsout.add("pelo");  wordsout.add("pelas");
        	wordsout.add("não");   wordsout.add("numa");
        	wordsout.add("num");   wordsout.add("neste");
        	wordsout.add("nas");   wordsout.add("nos");
        	wordsout.add("menos"); wordsout.add("quantos");
        	wordsout.add("ate");   wordsout.add("então");
        	wordsout.add("than");  wordsout.add("for");
        	wordsout.add("neste"); wordsout.add("nova");
        	wordsout.add("numa");  wordsout.add("passar");
        	wordsout.add("passa"); wordsout.add("passam");
        	wordsout.add("obter"); wordsout.add("será");
        	wordsout.add("serão"); wordsout.add("seus");
        	wordsout.add("tal");   wordsout.add("tendo");
        	wordsout.add("tipo");  wordsout.add("that");
        	wordsout.add("uma");   wordsout.add("umas");
        	wordsout.add("como");  wordsout.add("assim");
        	wordsout.add("talvez");  wordsout.add("sendo");
        	wordsout.add("assim");   wordsout.add("pode-se");
        	wordsout.add("desta");   wordsout.add("forma");
        	wordsout.add("vista");   wordsout.add("dois");
        	wordsout.add("três");    wordsout.add("quatro");
        	wordsout.add("cinco");   wordsout.add("seis");
        	wordsout.add("sete");    wordsout.add("oito");
        	wordsout.add("nove");    wordsout.add("dez");
        	wordsout.add("ouve");    wordsout.add("coisa");
        	wordsout.add("também");  wordsout.add("bem");
        	wordsout.add("mal");     wordsout.add("jamais");
        	wordsout.add("grande");  wordsout.add("pequeno");
        	wordsout.add("the");  	wordsout.add("than");
        	wordsout.add("case");  wordsout.add("and");
        	wordsout.add("Time");  wordsout.add("with");
        	wordsout.add("small"); wordsout.add("what");
        	wordsout.add("how");  	wordsout.add("you");
        	wordsout.add("she");  	wordsout.add("than");
        	wordsout.add("city");  wordsout.add("yours");
        	wordsout.add("mine");  wordsout.add("our");
        	wordsout.add("ours");  wordsout.add("big");
        	wordsout.add("will");  wordsout.add("won");
        	wordsout.add("would"); wordsout.add("did");
        	wordsout.add("not");  	wordsout.add("yes");
        	wordsout.add("puts");  wordsout.add("deal");
        	letter.add("a");   letter.add("n");
        	letter.add("b");   letter.add("o");
        	letter.add("c");   letter.add("p");
        	letter.add("d");   letter.add("q");
        	letter.add("e");   letter.add("r");
        	letter.add("f");   letter.add("s");
        	letter.add("g");   letter.add("t");
        	letter.add("i");   letter.add("u");
        	letter.add("j");   letter.add("v");
        	letter.add("k");   letter.add("x");
        	letter.add("l");   letter.add("z");
        	letter.add("m");   letter.add("w");
        	letter.add("y");   
        	
        	
        }
        public static boolean didntSearch(String word){
        	
        	if(wordsout.contains(word)){
        		return false;
        	}
        	
         	return true;
        }
        public static boolean CreateGlobalVar(String word){
        	
        	if(letter.contains(word)){
        		return true;
        	}
         	return false;
        }
        
        public static Set<String> wordsout = new TreeSet<String>();
        public static Set<String> letter = new TreeSet<String>();
}