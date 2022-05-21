public class Wallet {
    private int amount;
    private String CodeSecurity;
    private int Trasfer;

    public Wallet(int amount, String codeSecurity, int trasfer) {
        this.amount = amount;
        this.CodeSecurity = codeSecurity;
        this.Trasfer = trasfer;
    }

    public Wallet() {
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCodeSecurity() {
        return this.CodeSecurity;
    }

    public void setCodeSecurity(String codeSecurity) {
        this.CodeSecurity = codeSecurity;
    }

    public int getTrasfer() {
        return this.Trasfer;
    }

    public void setTrasfer(int trasfer) {
        Trasfer = trasfer;
    }

}
