package colla.dfs.app;

import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.tika.*;
import org.apache.tika.exception.TikaException;

import colla.dfs.util.*;

public class SearchWords extends EditDistance {

    public SearchWords() {

    }

    public Set<String> SearchWord(String texto) throws TikaException {

        JCL_facade javaCaLa = JCL_FacadeImpl.getInstance();
        String curLine = texto;
        Set<String> result = new TreeSet<String>();
        String r = null;
        try {
            testAspas = curLine.contains("\"");
            curLine = curLine.replaceAll("[\"\']", "");
            curLine = curLine.toLowerCase();
            String[] words = curLine.split(" ");
            words = curLine.split(" ");
            // Verifica se a palavra ou setenca esta escrita corretamente ou
            // existe no arquivos indexado

            String[] auxwords = new String[words.length];
            for (int i = 0; i < words.length; i++) {
                JCL_result jclr = javaCaLa.getValue(words[i].substring(0, 1));
                Set<String> aux = new TreeSet<String>();

                if (jclr.getCorrectResult() != null) {
                    aux.addAll((Set<String>) jclr.getCorrectResult());
                    String name = Levenshtein(words[i], aux);
                    if (name != "") {
                        auxwords[i] = name;
                    } else {
                        auxwords[i] = words[i];
                    }
                } else {
                    System.out.println("No result");
                }
            }            
            for (int j = 0; j < auxwords.length && j < words.length; j++) {
                //System.err.println(auxwords[j] + " AND " + words[j]);
                if (auxwords[j] != null && !auxwords[j].equals(words[j])) {
                    words = auxwords;
                    break;
                }
            }
            texto = "";
            for (String a : words) {
                texto = texto + " " + a;
            }

            Map<String, Map<String, Set<String>>> resp = new TreeMap<String, Map<String, Set<String>>>();

            for (String word : words) {
                if (word.length() > 2) {
                    JCL_result jclr = javaCaLa.getValue(word);
                    if (jclr.getCorrectResult() != null) {
                        resp.put(word, (Map<String, Set<String>>) jclr.getCorrectResult());
                    } else {
                        control = false;
                    }
                }
            }
            if (resp == null) {
                return null;
            }
            if (words.length == 1) {
                result = ResultSearchOne(resp, words[0]);
            } else {
                result = ResultSearch(resp, words);
            }

        } catch (Exception e) {
            System.err.println("problem in query");
            e.printStackTrace();
        }

        return result;
    }

    public Set<String> ResultSearchOne(Map<String, Map<String, Set<String>>> resp, String word) {
        if (resp.size() > 0) {
            Set<String> result = new TreeSet<String>();
            Map<String, Set<String>> aux = resp.get(word);
            for (String path : aux.keySet()) {
                result.add(path);
            }
            return result;
        }
        return null;
    }

    public Set<String> ResultSearch(Map<String, Map<String, Set<String>>> resp,
            String[] words) {
        if (resp.size() > 0) {
            Set<String> result = new TreeSet<String>();
            Map<String, Set<String>> R = new TreeMap<String, Set<String>>();
            Map<String, Set<String>> aux = new TreeMap<String, Set<String>>();
            for (int i = 0; i < words.length; i++) {
                if (words[i].length() > 2) {
                    R = resp.get(words[i]);
                    break;
                }
            }
            // Seleciona os arquivos comuns entre as palavras
            if (control) {

                for (int i = 1; i < words.length; i++) {

                    if (words[i].length() > 2) {

                        aux = resp.get(words[i]);
                        for (String path : aux.keySet()) {
                            if (R.containsKey(path)) {
                                result.add(path);
                            } else {
                                R.remove(path);
                                result.remove(path);
                            }

                        }
                    }
                    aux.clear();
                }
            }
            // seleciona somente os arquivos onde as palavras est�o em sequencia
            if (testAspas && result.isEmpty()) {
                Set<String> result2 = new TreeSet<String>();
                Set<String> LC1 = new TreeSet<String>();
                Set<String> LC2 = new TreeSet<String>();
                for (String path : result) {
                    for (int i = 1; i < words.length; i++) {
                        if (words[i].length() > 2) {
                            aux = resp.get(words[i]);
                            LC1.addAll(R.get(path));
                            LC2.addAll(aux.get(path));
                            for (String cord1 : LC1) {
                                String c1[] = cord1.split(",");
                                boolean seq = true; // verifica se a palavra �
                                // sequencia da outra
                                for (String cord2 : LC2) {
                                    String c2[] = cord2.split(",");
                                    Integer disL = Math.abs(Integer
                                            .parseInt(c1[0])
                                            - Integer.parseInt(c2[0]));
                                    if (disL == i - 1) {
                                        result2.add(path);
                                        seq = false;
                                        break;
                                    }

                                }
                                if (seq) {
                                    result.remove(path);
                                }
                            }
                            aux.clear();
                        }
                    }
                }
                return result2;

            }
            return result;
        }
        return null;
    }

    public void index(String dir, Set<String> s) throws TikaException {
        File f = new File(dir);
        if (f.isDirectory()) {
            String[] files = f.list();
            for (String oneFile : files) {
                dir = VerificaBarra(dir);
                index(dir + oneFile, s);
            }
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
        int linha = 0;
        try {
            File f2 = new File("txtIndexer.txt");
            f2.createNewFile();
            FileOutputStream resul = new FileOutputStream(f2);
            resul.flush();
            resul.write(text.getBytes());
            input = new BufferedReader(new FileReader(f2));
            int cont = 0;

            while ((readAux = input.readLine()) != null) {
                //System.out.println(readAux + " cont " + cont);
                allText.add(readAux.getBytes());
                cont++;
                if (cont == 1000) {
                    cont = 0;
                    Object[] args = {allText, FilePath, linha};
                    s.add(javaCaLa.execute("WordFilesIndexer", args));
                    linha = linha + 1000;
                    allText.clear();
                }

            }
            f2.delete();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!allText.isEmpty()) {
            Object[] args = {allText, FilePath, linha};
            s.add(javaCaLa.execute("WordFilesIndexer", args));
            allText.clear();
        }
        input.close();

    }

    public static String VerificaBarra(String pf) {
        String f = "\\";
        if (pf.contains("/")) {
            f = "/";
        }
        if (!pf.endsWith(f)) {
            pf = pf + f;
        }
        return pf;
    }
    private static TreeSet<String> textIndexer = new TreeSet<String>();
    private boolean testAspas;
    private boolean control = true;
    private Tika tika;
    private String text;
}
