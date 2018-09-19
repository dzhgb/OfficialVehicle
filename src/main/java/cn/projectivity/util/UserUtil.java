package cn.projectivity.util;

import cn.projectivity.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class UserUtil {
    public static User getLoginUser(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return (User) session.getAttribute("user");
    }

    public static String generateSalt(){
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        return salt;
    }

    public static String encodePasswordWithSalt(String username, String password, String salt){
        String algorithmName = "md5";
        int hashIterations = 2;

        SimpleHash hash = new SimpleHash(algorithmName, password, username + salt, hashIterations);
        String encodedPassword = hash.toHex();
        return encodedPassword;
    }

    public static void main(String[] args) {
        String algorithmName = "md5";
        String username = "admin";
        String password = "123456";
        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        System.out.println("salt2: "+salt2);
        int hashIterations = 2;

        salt2 = "8d78869f470951332959580424d4bf4f";
        SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
        String encodedPassword = hash.toHex();
        System.out.println("encodedPassword: "+encodedPassword);
    }

}
