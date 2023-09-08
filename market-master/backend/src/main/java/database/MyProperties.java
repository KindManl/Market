package database;

import java.math.BigInteger;

public class MyProperties { 
    private String      host = "jdbc:mysql://51.250.16.106:3306/upprpo";
    private String      login = "root"; 
    private BigInteger  password = new BigInteger("670675573819"); 
    private int         hash = 40;
    private BigInteger  privateKey = new BigInteger("747501761153");
    private BigInteger  modulus = new BigInteger("820975969069"); 

    public String getHost(){
        return host;
    }

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return getEncryptedPass();
    }

    private String getEncryptedPass() {
        RSA rsa = new RSA();
        rsa.init(hash);
        rsa.setModulus(modulus);
        rsa.setPrivateKey(privateKey);
        BigInteger dq = rsa.decrypt(password);
        return new String(dq.toByteArray());
    }
} 