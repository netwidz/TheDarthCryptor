/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iit.crypto.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.util.Collections;

/**
 *
 * @author Acer
 */
public class TranspositionAlgo {
    
    /**
     * Converts a String to a character array
     */
    public String encrypt(String text){
        char[] arr = text.toCharArray();
        int noOfCharacters=0;
        int lengthOfString=arr.length;
        char sprcialChar = '~';
        int count = 0;
        
        Vector<Character> vec = new Vector<Character>();
        for (int i = 0; i < arr.length; i++) {
			vec.add(arr[i]);
		}
        
        if (lengthOfString % 16 != 0) {
			noOfCharacters = 16 - (lengthOfString % 16);
		}
		if (lengthOfString % 16 != 0) {

			for (int i = lengthOfString; i < noOfCharacters + lengthOfString; i++) {
				vec.add(sprcialChar);
			}
		}

                
                
                String tempStr = new String();
		Vector<String> blocks = new Vector<String>();
		for (Iterator<Character> iterator = vec.iterator(); iterator.hasNext();) {

			Character character = (Character) iterator.next();
			tempStr += character;
			count++;
			if (count % 16 == 0) {
				blocks.add(tempStr);
				tempStr = "";
			}

		}
                
                Vector<HashMap<Integer, Character>> mapVec = new Vector<HashMap<Integer, Character>>();
		for (String vector : blocks) {

			char[] temp = vector.toCharArray();
			HashMap<Integer, Character> map = new HashMap<Integer, Character>();
			for (int i = 0; i < temp.length; i++) {
				char c = temp[i];
				map.put(i, c);

			}
			mapVec.add(map);

		}
                Vector<HashMap<Integer, Character>> vecshifted = new Vector<HashMap<Integer, Character>>();
                int t = 0;
		for (HashMap<Integer, Character> hashMap : mapVec) {
			 hashMap = circularshft(mapVec.get(t));
			vecshifted.add(hashMap);

			t++;
		}
                StringBuffer sb = new StringBuffer();
		for (HashMap<Integer, Character> hashMap : vecshifted) {
			Set<Integer> keyset = hashMap.keySet();
			for (Iterator<Integer> iterator = keyset.iterator(); iterator.hasNext();) {
				Integer integer = (Integer) iterator.next();
				sb.append(hashMap.get(integer));
			}
		}
                return sb.toString();
              
    }
    
    
    
    
    public String decrypt(String str){
        
        char[] arr = str.toCharArray();
    		Vector<Character> vec = new Vector<Character>();

    		for (int i = 0; i < arr.length; i++) {
    			char c = arr[i];
    			vec.add(c);

    		}

    		String tempStr = new String();
    		Vector<String> blocks = new Vector<String>();
    		int count = 0;
    		for (Iterator<Character> iterator = vec.iterator(); iterator.hasNext();) {

    			Character character = (Character) iterator.next();
    			tempStr += character;
    			count++;
    			if (count % 16 == 0) {
    				blocks.add(tempStr);
    				tempStr = "";
    			}

    		}

    		Vector<HashMap<Integer, Character>> mapVec = new Vector<HashMap<Integer, Character>>();
    		for (String vector : blocks) {

    		
    			char[] temp = vector.toCharArray();
    			HashMap<Integer, Character> map = new HashMap<Integer, Character>();
    			for (int i = 0; i < temp.length; i++) {
    				char c = temp[i];
    				map.put(i, c);

    			}
    			mapVec.add(map);

    		}

    		Vector<HashMap<Integer, Character>> mapVecFinal = new Vector<HashMap<Integer, Character>>();
    		int t = 0;
    		for (HashMap<Integer, Character> hashMap : mapVec) {
    			 hashMap = circularshft(mapVec.get(t));
    			mapVecFinal.add(hashMap);

    			t++;
    		}
    		StringBuffer sb = new StringBuffer();
    		for (HashMap<Integer, Character> hashMap : mapVecFinal) {
    			Set<Integer> keyset = hashMap.keySet();
    			for (Iterator<Integer> iterator = keyset.iterator(); iterator.hasNext();) {
    				Integer integer = (Integer) iterator.next();
    				sb.append(hashMap.get(integer));
    			}
    		}
    		
    		String finalStr =sb.toString().replace("~", "");
                    return finalStr;
    	}

    
      private  HashMap<Integer, Character> circularshft(HashMap<Integer, Character> map) {
                    
                   HashMap<Integer, Character> temp = new HashMap<Integer, Character>();
          
                   Set<Integer> keySet = map.keySet();
                   List<Integer> list = new ArrayList<Integer>(keySet);
                   Collections.reverse(list);
          
                   int j=0;
          
                    for (Integer integer : list) {
			
				temp.put(j, map.get(integer));
                                j++;
                    }
                    return temp;
        
      }
      
                    
}
