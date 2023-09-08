package database;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

public class testRSA {

    static RSA rsa;

    @BeforeAll
    public static void init(){
        rsa = new RSA();
    }

    @Test
    public void testPrivateKey(){
        BigInteger key = new BigInteger("1234567");
        rsa.setPrivateKey(key);
        assertEquals(rsa.getPrivateKey(), key);
    }

    @Test
    public void testPublicKey(){
        BigInteger key = new BigInteger("1234567");
        rsa.setPublicKey(key);
        assertEquals(rsa.getPublicKey(), key);
    }

    @Test
    public void testModulus(){
        BigInteger modulus = new BigInteger("1234567");
        rsa.setModulus(modulus);
        assertEquals(rsa.getModulus(), modulus);
    }

    @Test
    public void testToString(){
        BigInteger value = new BigInteger("12");
        rsa.setPrivateKey(value);
        rsa.setPublicKey(value);
        rsa.setModulus(value);

        String s = "";
        s += "public  = " + value  + "\n";
        s += "private = " + value + "\n";
        s += "modulus = " + value;

        assertEquals(rsa.toString(), s);
    }

    @Test
    public void testCoder(){
        BigInteger publicKey = new BigInteger("1234567");
        rsa.setPublicKey(publicKey);
        BigInteger privateKey = new BigInteger("257");
        rsa.setPrivateKey(privateKey);
        BigInteger value = new BigInteger("12345");
        BigInteger encrypted = rsa.encrypt(value);
        BigInteger decrypted = rsa.decrypt(encrypted);
        assertEquals(decrypted, value);
    }

}
