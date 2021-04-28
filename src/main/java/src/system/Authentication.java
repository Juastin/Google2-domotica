package src.system;


import org.mindrot.jbcrypt.BCrypt;

public class Authentication {

    public Authentication(){

    }
    // @param char[] password from registerForm.
    // @return String encryptedPassword
    // Returns encrypted String password when password is given
    public static String encryptPassword(char[] password){
        return BCrypt.hashpw(String.valueOf(password), BCrypt.gensalt());
    }
    // @param String password from loginForm.
    // @param String bcryptHashString from database
    // @return boolean
    // To check result: return function and check if true
    public static boolean checkPassword(String password, String bcryptHashString){
        return BCrypt.checkpw(password,bcryptHashString);
    }

}
