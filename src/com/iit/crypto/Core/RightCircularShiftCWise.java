package com.iit.crypto.Core;

public class RightCircularShiftCWise {

	
	public RightCircularShiftCWise() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * Right Circular Shift Charactor wise
	 * @param {@link String} pText
	 * @return {@link String} cyperText
	 */
	
	public String encryptRCSC(String pText){
		
		String s = pText;
	
//		String[] blocks = s.split("(?<=\\G.)");
		char[] blocks = s.toCharArray();
		
		int count = blocks.length;
		
		char[] cyperText = new char[count];
	
		for (int i = 0;  i<blocks.length;  i++) {
			
			count--;			
			cyperText[count] = blocks[i];
			

		}
		
		

		
		
		return new String(cyperText);
	}
	
	
	public String decryptRCSC(String cText) {
		
		char[] blocks = cText.toCharArray();
		int count = 0;
		char[] pText = new char[blocks.length];
		
		for (int i = blocks.length-1; i >=0 ; i--) {
			
			pText[count] = blocks[i];
			count++;
		}
		
		return new String(pText);
	}
	
	
	
	
	
	
	
}
