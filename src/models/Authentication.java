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
    // Result{details=HashData{cost=12, version=$2a$, rawSalt=d63f56f9a7ddcd562a9b4d05f775a482, rawHash=dbf6f4d1311531d1f4822d2d6f1b53bebc4258544901ab}, validFormat=true, verified=true, formatErrorMessage='null'}
    // To check result: result.verified (that returns true or false)
    public static BCrypt.Result decryptPassword(char[] password, String bcryptHashString){
        return BCrypt.verifyer().verify(password, bcryptHashString);
    }

}
