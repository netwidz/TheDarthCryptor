package com.iit.crypto.Core;

public class Substitution {
	
	
	private int KEY_1 = 7;
	private int KEY_2 = 4;	
	
	
	public Substitution(){
		
	}
	
	
	private String encryptMessage(String msg, int k, Boolean isEncrypt) {
    	String result = "";
    

    	for (int i = 0; i < msg.length(); i++)
    		result += encryptChar(msg.charAt(i), k,isEncrypt);
    	
    	return result;
    }
 
    private char encryptChar(char c, int k,  Boolean isEncrypt) {
    	int key=0;
    	
    	if(!isEncrypt && Character.isLetter(c) && Character.isLowerCase(c))
			key =  18-k;
		else if(!isEncrypt && Character.isDigit(c))
			key = 10-k;
		else if(!isEncrypt && Character.isLetter(c) && Character.isUpperCase(c))
			key = 22-k;
		else
			key = k;
		
    	
    	if (Character.isLetter(c) && Character.isUpperCase(c) )
    		return (char) ('A' + (c - 'A' + (key+2)) % 26);
    	if (Character.isLetter(c) && Character.isLowerCase(c))
    		return (char) ('a' + (c - 'a' + (key+4)) % 26);
    	if (Character.isDigit(c) )
    		return (char) ('0' + (c - '0' + key) % 10);
    	else
    		return c;
    }
    
    
    public String encrypt(String pText){
    	
    	String c1 = encryptMessage(pText, KEY_1, true);
    	String c2 = encryptMessage(c1, KEY_2, true);
 //   	String c3 = encryptMessage(c2, KEY_3, true);
    	
    	return c2;
    	
    }
    
	
	public String decrypt(String cText){
	//	String p1 = encryptMessage(cText, KEY_3, false);
		String p2 = encryptMessage(cText, KEY_2, false);
		String p3 = encryptMessage(p2, KEY_1, false);
		
		
		return p3;
	}
	
	

}
