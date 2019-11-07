package dsn.cmon.secure;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

public class SecurityMD5Hash {

	public SecurityMD5Hash() {
		// TODO Auto-generated constructor stub
	}

	public static String md5(String input) {
        
        String md5 = null;
         
        if(null == input) return null;
         
        try {
             
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
         
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
 
        //Converts message digest value in base 16 (hex) 
        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        return md5;
    }
	
	public static boolean validateSecurePassword(String password, String encryptedPassword){
		boolean result = false;
		
		if (DigestUtils.md5Hex(password).equals(encryptedPassword))
			result = true;

		return result;		
	}
	
	public static void main(String[] args){
		String str = "adminwelcome1";
		System.out.println(DigestUtils.md5Hex(str));
		
		System.out.println(validateSecurePassword("adminwelcome1", "bb0f7e021d52a4e31613d463fc0525d8"));
		
		System.out.println(DigestUtils.md5Hex( "A" ));
		System.out.println(DigestUtils.md5Hex( "a" ));
		
	}

}