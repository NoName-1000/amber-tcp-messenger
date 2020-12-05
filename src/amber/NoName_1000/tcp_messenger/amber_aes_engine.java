package amber.NoName_1000.tcp_messenger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class amber_aes_engine {
	
	private static SecretKeySpec kluczPrywatny;
	private static byte[] klucz;
	public static String returnVal;
	
	public static void keyCalc(String keyDump ) {
		MessageDigest sha = null;
		try {
			klucz = keyDump.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			klucz = sha.digest(klucz);
			klucz = Arrays.copyOf(klucz, 16); 
			kluczPrywatny = new SecretKeySpec(klucz, "AES");
		} catch (NoSuchAlgorithmException e) {
			
		} catch (UnsupportedEncodingException e) {
			
		}
		
	}
	
	public static String encrypt(String doZaszyfrowania, String klucz_Prywatny) {
		try {
			keyCalc(klucz_Prywatny);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, kluczPrywatny);
			return Base64.getEncoder().encodeToString(cipher.doFinal(doZaszyfrowania.getBytes("UTF-8")));
		} catch (Exception e)  {
			return returnVal = "EncryptERR";
		}
	}
	
	public static String decrypt(String doDeszyfrowania, String klucz_Prywatny) {
		try {
			keyCalc(klucz_Prywatny);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, kluczPrywatny);
			return new String(cipher.doFinal(Base64.getDecoder().decode(doDeszyfrowania)), "UTF-8");
		} catch (Exception e)  {
			return returnVal = "EncryptERR";
		}
	}
	
}