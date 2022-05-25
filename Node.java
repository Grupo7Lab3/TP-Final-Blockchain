import java.util.List;

public class Node {
    public List<Wallet> node;

    public Node(){

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
    
    @Override
    public String toString() {
        return super.toString();
    }
}
