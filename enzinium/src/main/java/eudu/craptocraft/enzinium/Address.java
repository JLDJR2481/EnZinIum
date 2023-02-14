package eudu.craptocraft.enzinium;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Address {

    private PublicKey PK = null;
    private PrivateKey SK = null;
    private double balance = 0d;
    private final String symbol = "EZI";

    public Address() {
    };

    private void setSK(PrivateKey sKey) {
        this.SK = sKey;
    }

    private PrivateKey getSK() {
        return this.SK;
    }

    private void setPK(PublicKey pKey) {
        this.PK = pKey;
    }

    public PublicKey getPK() {
        return this.PK;
    }

    public void generateKeyPair() {
        KeyPair pair = GenSig.generateKeyPair();

        this.setPK(pair.getPublic());
        this.setSK(pair.getPrivate());
    }

    @Override
    public String toString() {
        return "Public key: " + getPK().hashCode() + "\nBalance= " + this.balance + " " + this.symbol;
    }

}
