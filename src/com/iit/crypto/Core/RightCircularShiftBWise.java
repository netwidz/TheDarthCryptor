package com.iit.crypto.Core;

public class RightCircularShiftBWise {
	
	public RightCircularShiftBWise() {
		// TODO Auto-generated constructor stub
	}
	
	public String encryptRCSB(String pText) {
		
		String[] blocks = pText.split("(?<=\\G....)");

		
		String cText = "";
		
		for (int i =  blocks.length-1; i >=0; i--) {
			
			
			cText += blocks[i];
			
		}
		
		return cText;
		
	}
	
	public String decryptRCSB(String cText) {
		
		char[] blocks = cText.toCharArray();
		String tempPlainText = "";
		int count = 3;
		char[] tempReverseBlock = new char[4];
		int rem = blocks.length%4 - 1;
		
		for (int i = blocks.length-1; i >=0; i--) {
			//tempPlainText  += blocks[i];
		
			
			if(i == rem ){
				
				count=rem;
				tempReverseBlock = new char[rem+1];
			}
				
			tempReverseBlock[count] = blocks[i];
				
			if (count == 0 ){
			
				tempPlainText += new String(tempReverseBlock);
				count=3;
			} else
				count--;
				
			
			
		}
		
		return tempPlainText;
		
	}
	

	
	

}
