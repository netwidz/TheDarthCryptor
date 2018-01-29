package com.iit.crypto.Core;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import com.iit.crypto.HashAlgo.MD5Algo;
import com.iit.crypto.products.DESEncryption;

public class KeyTextMerger {
	
	private static final String PRIVATE_KEY = "FMCDJHED";

	public KeyTextMerger() {
	
	}
	
	
	public String keyMerger(String textToMerge, String key){
		String mergedString = "";
		
		String[] splitOrigText = textToMerge.split("(?<=\\G.{8})");
		String[] splitKey = key.split("(?<=\\G.{8})");
		
		for (int j = 0; j < splitOrigText.length; j++) {
				
					
					if(j%4==0)
						mergedString += splitKey[0]+splitOrigText[j];
					else if(j%4==1)
						mergedString += splitKey[1]+splitOrigText[j];
					else if(j%4==2)
						mergedString += splitKey[2]+splitOrigText[j];
					else if(j%4==3)
						mergedString += splitKey[3]+splitOrigText[j];
					
			}
		
		
		return mergedString;
	}
	
	public String keyRemover(String mergedText){
		String[] splitMergedText = mergedText.split("(?<=\\G.{16})");
		String origText = "";
		
		for (int j = 0; j < splitMergedText.length; j++) {
			
			
			
							origText += splitMergedText[j].substring(8);
			
		}
		
		return origText;
		
		
	}
	
	public String initMerger(String pText, String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		MD5Algo md5 = new MD5Algo();
		String cText = "";
		
		String privKey = md5.encrypt(PRIVATE_KEY);
		
		int lenth = pText.toCharArray().length;
		int keyLen = key.toCharArray().length;
		String pattern = "(?<=\\G.{"+lenth/4+"})";
		String patternKey = "(?<=\\G.{"+keyLen/4+"})";
		
		
		
		String[] splittedPrivateKey = privKey.split(patternKey);
		String[] splittedpText = pText.split(pattern);
		String[] splittedKey = key.split(patternKey);		
		

		
		String keyMerged = splittedKey[0]+splittedPrivateKey[3]+splittedKey[2]+splittedPrivateKey[1]; 
		
		String[] splitKey = keyMerged.split("(?<=\\G.{"+keyLen/4+"})");
		System.err.println(Arrays.toString(splittedpText));
		if(lenth%4 == 0){
			cText =  splitKey[3]+splittedpText[0]+splitKey[2]+splittedpText[1]+splitKey[1]+splittedpText[2]+splitKey[0]+splittedpText[3];
		}else{
			
			
			String strToMrg = "";

			
			if(lenth > 4 &&  lenth < 15){
				System.err.println(splittedpText.length - lenth%4);
				for (int i = 4; i < splittedpText.length; i++) {
					strToMrg += splittedpText[i];
				}
			}else
				strToMrg = splittedpText[4];
			
			cText =  splitKey[3]+splittedpText[0]+splitKey[2]+splittedpText[1]+splitKey[1]+splittedpText[2]+splitKey[0]+splittedpText[3]+strToMrg;
		}
		
		return cText;
		
	}
	
	public String initRemover(String cText, String key) throws Exception {
		MD5Algo md5 = new MD5Algo();
		
		String privKey = md5.encrypt(PRIVATE_KEY);
		
		int lenth = cText.toCharArray().length;
		int keyLen = key.toCharArray().length;
		int pTextCount = lenth - keyLen;
		String patternKey = "(?<=\\G.{"+keyLen/4+"})";
		
		
		
		String[] splittedPrivateKey = privKey.split(patternKey);
		String[] splittedKey = key.split(patternKey);
		
		String keyMerged = splittedKey[0]+splittedPrivateKey[3]+splittedKey[2]+splittedPrivateKey[1]; 
		

		String keyFirst8 = cText.substring(0, 8);
		String keySecond8 = cText.substring(pTextCount/4 + 8, pTextCount/4 + 16);
		String keyThird8 = cText.substring((pTextCount/4 + 8)*2 , ((pTextCount/4 + 8) * 2) + 8);
		String keyFourth8 = cText.substring((pTextCount/4 + 8) * 3 ,((pTextCount/4 + 8) * 3) + 8);

		String keyFromCText = keyFourth8+keyThird8+keySecond8+keyFirst8;
		String pText = "";

		
		if(keyMerged.equals(keyFromCText) ){
			
			String firstPText = "";
			String secondPText = "";
			String thirdPText = "";
			String fourthPText = "";
			
			
			if(lenth % 4 == 0){
				firstPText = cText.substring(8, 8 + pTextCount/4);
				 secondPText = cText.substring(pTextCount/4 + 16, (pTextCount/4) * 2 + 16);
				 thirdPText = cText.substring((pTextCount/4) * 2 + 24, (pTextCount/4) * 3 + 24);
				 fourthPText = cText.substring((pTextCount/4) * 3 + 32,(pTextCount/4) * 4 + 32);
			}else{
				firstPText = cText.substring(8, 8 + pTextCount/4);
				secondPText = cText.substring(pTextCount/4 + 16, (pTextCount/4) * 2 + 16);
				thirdPText = cText.substring((pTextCount/4) * 2 + 24, (pTextCount/4) * 3 + 24);
				fourthPText = cText.substring((pTextCount/4) * 3 + 32,(pTextCount/4) * 4 + 32 + (lenth % 4));
				
			}
	
			pText = firstPText+secondPText+thirdPText+fourthPText;
		}else{
			LeftCircularShiftB lSSB = new LeftCircularShiftB();
			RightCircularShiftCWise rSSC = new RightCircularShiftCWise();
			RightCircularShiftBWise rSSB = new RightCircularShiftBWise();
			DESEncryption des = new DESEncryption();
			Substitution sub = new Substitution();
			String lSSBString = lSSB.encryptLCSB(cText);
			String subStr = sub.encrypt(lSSBString);
			String rSSCString = rSSC.encryptRCSC(subStr);
			String rSSBString = rSSB.encryptRCSB(rSSCString);
			String desStr = des.encrypt(rSSBString);
			String[] ss = desStr.split("\n");
			String ret = "";
			for (int i = 0; i < ss.length; i++) {
				ret += ss[i];
			}
			pText  = ret;
		}
			
		
		
		return pText;
		
		
		
	}
	
	

}
