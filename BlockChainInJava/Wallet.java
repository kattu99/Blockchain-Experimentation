package blockchain;
import java.security.*;

//Wallets store the addresses and have the ability to perform transactions 
public class Wallet {
    public PrivateKey privateKey;
    public PublicKey publicKey;

    public Wallet() {
        generateKeyPair();
    }

    public void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            keyGen.initialize(ecSpec, random);
            KeyPair keyPair = keyGen.generateKeyPair(); //you generate a key pair based on the elliptic curve
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
