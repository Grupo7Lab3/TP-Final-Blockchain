package src.com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Transfer {
    private int transfer;
    private UUID codeSecurityIn;
    private UUID codeSecurityOut;
    private int amount;
    private List<User> validated;
    private Enum<Status> status;

    public Transfer(int transfer, UUID codeSecurityIn, UUID codeSecurityOut, int amount) {
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

    public UUID getCodeSecurityIn() {
        return codeSecurityIn;
    }

    public void setCodeSecurityIn(UUID codeSecurityIn) {
        this.codeSecurityIn = codeSecurityIn;
    }

    public UUID getCodeSecurityOut() {
        return codeSecurityOut;
    }

    public void setCodeSecurityOut(UUID codeSecurityOut) {
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

    @Override
    public String toString() {
        return "Transfer" +
                "transfer=" + transfer +
                ", codeSecurityIn='" + codeSecurityIn + '\'' +
                ", codeSecurityOut='" + codeSecurityOut + '\'' +
                ", amount=" + amount +
                ", status=" + status;
    }

    public Enum<Status> getStatus() {
        return status;
    }


}
