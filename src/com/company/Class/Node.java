package src.com.company.Class;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public List<Wallet> node;

    public Node(){
        this.node = new ArrayList<>();
    }

    public Node(List<Wallet> node){
        this.node = node;
    }

    public List<Wallet> getNode() {
        return node;
    }

    public void setNode(List<Wallet> node) {
        this.node = node;
    }

    public void addWallet(Wallet wallet){
        node.add(wallet);
    }

    public Wallet searchWallet(String code){
        Wallet aux = null;
        for (Wallet wallet : node) {
            if (wallet.getCodeSecurity().equals(code)){
                aux = wallet;
            }
        }
        return aux;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
