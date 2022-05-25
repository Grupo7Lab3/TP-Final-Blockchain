package src.com.company;

import jdk.jshell.Snippet;

import java.util.ArrayList;
import java.util.List;

public class Transfer {
    private int transfer;
    private String codeSecurityIn;
    private String codeSecurityOut;
    private int amount;
    private List<User> validated;
    private Enum<Status> status;

    public Transfer(int transfer, String codeSecurityIn, String codeSecurityOut, int amount) {
        this.transfer = transfer;
        this.codeSecurityIn = codeSecurityIn;
        this.codeSecurityOut = codeSecurityOut;
        this.amount = amount;
        this.validated=new ArrayList<>();
        this.status=Status.Pendiente;
    }

    public int getTransfer() {
        return transfer;
    }

    public void setTransfer(int transfer) {
        this.transfer = transfer;
    }

    public String getCodeSecurityIn() {
        return codeSecurityIn;
    }

    public void setCodeSecurityIn(String codeSecurityIn) {
        this.codeSecurityIn = codeSecurityIn;
    }

    public String getCodeSecurityOut() {
        return codeSecurityOut;
    }

    public void setCodeSecurityOut(String codeSecurityOut) {
        this.codeSecurityOut = codeSecurityOut;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setValidated(User user) {
        this.validated.add(user);
        if(this.validated.size()==3){
            this.status=Status.Validado;
        }
    }

    public Enum<Status> getStatus() {
        return status;
    }


}
