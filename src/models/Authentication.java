package src.models;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Authentication {

    public Authentication(){

    }
    // @param char[] password from registerForm.
    // @return String encryptedPassword
    // Returns encrypted String password when password is given
    public static String encryptPassword(char[] password){
        return BCrypt.withDefaults().hashToString(12, password);
    }
    // @param char[] password from loginForm.
    // @param String bcryptHashString from database
    // @return BCrypt.Result
    // Returns boolean true when char[] password is same as bcryptHashString
    // result.verified == true
    public static BCrypt.Result decryptPassword(char[] password, String bcryptHashString){
        return BCrypt.verifyer().verify(password, bcryptHashString);
    }

//    String password = "1234";
//    String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
//// $2a$12$US00g/uMhoSBm.HiuieBjeMtoN69SN.GE25fCpldebzkryUyopws6
//    BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
//// result.verified == true
}
