package eudu.craptocraft.enzinium;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class TokenContract {

    private PublicKey ownerPK = null;
    private Address owner = null;
    private String name = "";
    private String symbol = "";
    private double totalSupply = 0d;
    private double tokenPrice = 0d;

    private Map<PublicKey, Double> balances = new HashMap<>();

    public TokenContract(Address owner) {
        this.owner = owner;
        this.ownerPK = owner.getPK();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setTotalSupply(int totalSupply) {
        this.totalSupply = totalSupply;
    }

    public void setTokenPrice(double tokenPrice) {
        this.tokenPrice = tokenPrice;
    }

    public Address owner() {
        return this.owner;
    }

    public String name() {
        return this.name;
    }

    public String symbol() {
        return this.symbol;
    }

    public double totalSupply() {
        return this.totalSupply;
    }

    public double tokenPrice() {
        return this.tokenPrice;
    }

    public Map<PublicKey, Double> getBalances() {
        return this.balances;
    }

    @Override
    public String toString() {
        return ("\nOwner PK: " + this.ownerPK.hashCode() + "\nName: " + this.name + "\nSymbol: " + this.symbol
                + "\nTotal Supply: "
                + this.totalSupply);
    }

    public void addOwner(PublicKey PK, Double unidades) {

        getBalances().putIfAbsent(PK, unidades);

    }

    public int numOwners() {
        return getBalances().size();
    }

    public Double balanceOf(PublicKey ownerPK) {
        return this.getBalances().getOrDefault(ownerPK, 0d);
    }

    // No reventa
    public void transfer(PublicKey destinatario, Double unidades) {
        try {
            require(balanceOf(ownerPK) >= unidades);
            this.getBalances().compute(ownerPK, (pk, tokens) -> tokens - unidades);
            this.getBalances().put(destinatario, balanceOf(destinatario) + unidades);

        } catch (Exception exception) {
        }
    }

    // Reventa
    public void transfer(PublicKey destinatario, PublicKey segundoDestinatario, Double unidades) {
        try {
            require(balanceOf(destinatario) >= unidades);
            this.getBalances().put(destinatario, balanceOf(destinatario) - unidades);

            this.getBalances().put(segundoDestinatario, balanceOf(segundoDestinatario) + unidades);
        } catch (Exception exception) {
        }
    }

    private void require(Boolean holds) throws Exception {

        if (!holds) {
            throw new Exception();
        }

    }
}
