package colla.dfs.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class EditDistance {

	public static List<String> Levenshtein(String s, Set<String> textos, float threshold){
		List<String> aux = new LinkedList<String>();
		float size2 = s.length();
		
		for(String umTexto:textos){
		
			String primeiraParte = umTexto.substring(0, 3);
			System.out.println("primeiraParte:"+primeiraParte);
			
			float size1 = primeiraParte.length();    		
    		float porcentagem =0;
    		
    		if(size1>size2) porcentagem = size1/size2;
    		else porcentagem = size2/size1;
    		
    		//palavras com menos do que 50% de diferenca nos tamanhos
    		if(porcentagem<1.5){
    			float r = StringUtils.getLevenshteinDistance(s.toLowerCase(), primeiraParte.toLowerCase());
    			//X% de diferenca ou menos 
    			if(((r/size1)>=0)&&((r/size1)<threshold)) aux.add(umTexto);
    		}	
    		
    		String[] palavras = primeiraParte.split(" ");
			String novoNome = "";
			for(int i = 0; i<palavras.length; i++){
				if(palavras[i].length()>2){
					if(i<(palavras.length*0.6))
						novoNome+= palavras[i].substring(0, 1) + " ";
					else novoNome+= palavras[i] + " ";
				}
			}
			
			size1 = novoNome.length();
    		
			if(size1>size2) porcentagem = size1/size2;
    		else porcentagem = size2/size1;
    		
    		//palavras com menos do que 50% de diferenca nos tamanhos
    		if(porcentagem<1.5){
    			float r = StringUtils.getLevenshteinDistance(s.toLowerCase(), novoNome.toLowerCase());
    			//X% de diferen�a ou menos 
    			if(((r/size1)>=0)&&((r/size1)<threshold)) aux.add(umTexto);
    		}	
		}
		
		return aux;
	}
	
	public static String Levenshtein(String s, Set<String> textos){
		String aux = "";
		float diff=0;
			
		float size2 = s.length();
		
		for(String umTexto:textos){
			
			String primeiraParte = umTexto;
			float size1 = primeiraParte.length();    		
    		float porcentagem =0;
    		
    		
    		if(size1>size2) porcentagem = size1/size2;
    		else porcentagem = size2/size1;
    		
    		//palavras com menos do que 50% de diferenca nos tamanhos
    		if(porcentagem<1.5){
    			   			
    			float r = StringUtils.getLevenshteinDistance(s.toLowerCase(), primeiraParte.toLowerCase());
    			//X% de diferen�a ou menos 
    			if(((r/size1)>=0)&&((r/size1)<=0.2)) 
    				if(diff==0){
    					diff = r/size1;
    					aux=umTexto;
    				}else
	    				if((r/size1)<diff){
	    					aux=umTexto;
	    					diff = r/size1;
	    				}
    						
    		}	
    		
    		
    		String[] palavras = primeiraParte.split(" ");
    		
			String novoNome = "";
			for(int i = 0; i<palavras.length; i++){
				if(palavras[i].length()>2){
					if(i<(palavras.length*0.6))
						novoNome+= palavras[i].substring(0, 1) + " ";
					else novoNome+= palavras[i] + " ";
				}
			}
			
			size1 = novoNome.length();
			
			if(size1>size2) porcentagem = size1/size2;
    		else porcentagem = size2/size1;
    		
    		//palavras com menos do que 50% de diferenca nos tamanhos
    		if(porcentagem<1.5){
    			   			
    			float r = StringUtils.getLevenshteinDistance(s.toLowerCase(), novoNome.toLowerCase());
    			//X% de diferen�a ou menos 
    			if(((r/size1)>=0)&&((r/size1)<=0.2)) 
    				if(diff==0){
    					diff = r/size1;
    					aux=umTexto;
    				}else
	    				if((r/size1)<diff){
	    					aux=umTexto;
	    					diff = r/size1;
	    				}				
    		}	
    		
		}
			
		return aux;
	}
}
