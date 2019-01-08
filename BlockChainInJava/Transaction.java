import java.security.*;
import java.util.ArrayList;

public class Transaction {

    public String transactionId; //hash of Transaction
    public PublicKey sender; // senders address/public key
    public PublicKey recipient; // Recipients address
    public float value;
    public byte[] signature; //prevent other people from using wallet

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    private static int sequence = 0; //how many transactions have been generated

    //transaction constructor
    public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.recipient = to;
        this.value = value;
        this.inputs = inputs; 
    }

    //hash will be transactionId
    public String calculateHash() {
        sequence++;
        return StringUtil.applySha256(StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value) + sequence);
    }

    //Signs all data we don't want tampered with
    public void generateSignature(PrivateKey privateKey) {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value);
        signature = StringUtil.applyECDSASig(privateKey,data);
    }

    //Verifies Data hasn't been tampered with
    public void verifySignature(PrivateKey privateKey) {
            String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value);
            signature = StringUtil.verifyECDSASig(privateKey,data);
   }

}