/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author USER
 */
class SCryptoUtils {
    private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES";

    static void decrypt(byte[] key, File inputFile, File outputFile) throws CryptoException {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }

    private static void doCrypto(int cipherMode, byte[] key, File inputFile, File outputFile) throws CryptoException {
       try {
			Key secretKey = new SecretKeySpec(key, ALGORITHM);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(cipherMode, secretKey);
			System.out.println("LINE 1");
                        
			FileInputStream inputStream = new FileInputStream(inputFile);
			//BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
                        byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);
			System.out.println("LINE 2");
			
                        byte[] outputBytes = cipher.doFinal(inputBytes);
			
                        System.out.println("LINE 3");
			FileOutputStream outputStream = new FileOutputStream(outputFile);
			//BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile),"UTF-8"));
                        outputStream.write(outputBytes);
			//outputStream.write(outputBytes);
                        System.out.println("LINE 4");
			inputStream.close();
			outputStream.close();
			
                        System.out.println("LINE 5");
		} catch (NoSuchPaddingException | NoSuchAlgorithmException
				| InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException ex) {
			throw new CryptoException("Error encrypting/decrypting file", ex);
		}

    }
    
}
