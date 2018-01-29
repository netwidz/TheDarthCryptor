import javax.swing.JOptionPane;

import com.iit.crypto.Core.KeyTextMerger;
import com.iit.crypto.Core.LeftCircularShiftB;
import com.iit.crypto.Core.RightCircularShiftBWise;
import com.iit.crypto.Core.RightCircularShiftCWise;
import com.iit.crypto.Core.Substitution;
import com.iit.crypto.Core.TranspositionAlgo;
import com.iit.crypto.HashAlgo.MD5Algo;
import com.iit.crypto.HashAlgo.ShaAlgo;
import com.iit.crypto.products.DESEncryption;


public class Bridge {
	
	
	
	public String encryptText(String pText, String key) {
		String finalCyperText = "";
		
		try{
			String keyMD5 =  this.keyMaker(key);
			
			KeyTextMerger kTMerger = new KeyTextMerger();
			
			String initialMerge = kTMerger.initMerger(pText.trim(), keyMD5);
			
			TranspositionAlgo transAlgo = new TranspositionAlgo();
			String transEncryptText = transAlgo.encrypt(initialMerge);
		
			LeftCircularShiftB ls = new LeftCircularShiftB();
			String cypertext = ls.encryptLCSB(transEncryptText);
			
			Substitution sub = new Substitution();
			String substitution1 = sub.encrypt(cypertext);
	
			String keyTextMerger = kTMerger.keyMerger(substitution1, keyMD5);
			String keyInitMerger = kTMerger.initMerger(keyTextMerger, keyMD5);
			
			DESEncryption des = new DESEncryption();
			String retDes = des.encrypt(keyInitMerger);
			String[] spStr =  retDes.split("\n"); 
			String ret = "";
		
			for (int i = 0; i < spStr.length; i++) {
				ret += spStr[i];
			}
			finalCyperText  = ret;
			
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return finalCyperText;
		
	}
	
	
	public String decryptText(String pText, String key) {
	
		String finalPlainText = "";
		
		try {
			String keyMD5 =  this.keyMaker(key);
			
			DESEncryption des = new DESEncryption();
			String desRemoved = des.decrypt(pText);

			
			KeyTextMerger kTMerger = new KeyTextMerger();
			String keyInitRemover = kTMerger.initRemover(desRemoved, keyMD5);
			String keyTextRemover = kTMerger.keyRemover(keyInitRemover);
		
			Substitution sub = new Substitution();
			String subRemover = sub.decrypt(keyTextRemover);
		
			LeftCircularShiftB ls = new LeftCircularShiftB();
			String lsRemover = ls.decryptLCSB(subRemover);

			TranspositionAlgo transAlgo = new TranspositionAlgo();
			String transRemover = transAlgo.decrypt(lsRemover);
			
			finalPlainText = kTMerger.initRemover(transRemover, keyMD5);
		
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid Encrypted Text");
		}
		
		
		return finalPlainText;
		
	}
	
	private String keyMaker(String key) {
		String keyMD5 = "";
		
		try {
			RightCircularShiftCWise rSSC = new RightCircularShiftCWise();
			String keyRSSC = rSSC.encryptRCSC(key);
			
			KeyTextMerger kTMerger = new KeyTextMerger();
			String keyKTMerger = kTMerger.keyMerger(key, keyRSSC);
			
			ShaAlgo shaAlgo = new ShaAlgo();
			String KeySHA = shaAlgo.encrypt(keyKTMerger);
			
			RightCircularShiftBWise rSSB = new RightCircularShiftBWise();
			String keyRSSB = rSSB.encryptRCSB(KeySHA);
			
			String ktMer = kTMerger.keyMerger(key, keyRSSB);
			
			MD5Algo md5Algo = new MD5Algo();
			keyMD5 = md5Algo.encrypt(ktMer);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Something went wrong");
		}
		
	
		
		return keyMD5;
		
	}
}
