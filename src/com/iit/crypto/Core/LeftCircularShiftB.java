package com.iit.crypto.Core;



public class LeftCircularShiftB {
	
	
	
	
	public LeftCircularShiftB(){
		
				
		
	}
	
	public String encryptLCSB(String pText){
		
		String s = pText;
	
		String[] blocks = s.split("(?<=\\G....)");
		
		String cyperText = "";
	
		for (int i = blocks.length-1; i >= 0; i--) {
			
			
			cyperText += blocks[i];
		}
		
		return cyperText;
	}

	
	public String decryptLCSB(String cText){
		
		String s = cText;
	
		String[] blocks = s.split("(?<=\\G.)");
		
		String[] temp =new String[blocks.length];
		
		String tempPlainText ="";
		String plainText ="";
		int x =0;

		for (int i = blocks.length-1; i >= 0; i--) {
			
			
			temp[x] = blocks[i];
			x++;
		}
		
		for (int i = 0; i <temp.length; i++) {
			
			
			tempPlainText += temp[i];
	
		}
		
		
		String[] tempBlocks = tempPlainText.split("(?<=\\G....)");
		
		for (int i = 0; i < tempBlocks.length; i++) {
			
			String[] charSplit = tempBlocks[i].split("(?<=\\G.)"); 
			
			for (int j = charSplit.length-1; j >=0 ; j--) {
				plainText += charSplit[j];
			}
	
		}
		
		return plainText;
	}

	
	
    
 
	
	
}
