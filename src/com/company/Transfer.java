package src.com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Transfer{
    private int idTransfer;
    private UUID codeSecurityIn;
    private UUID codeSecurityOut;
    private int amount;
    private List<User> validated;
    private Status status;

    public Transfer(int idTransfer, UUID codeSecurityIn, UUID codeSecurityOut, int amount) {
        this.idTransfer = idTransfer;
        this.codeSecurityIn = codeSecurityIn;
        this.codeSecurityOut = codeSecurityOut;
        this.amount = amount;
        this.validated=new ArrayList<>();
        this.status= new Status(3, TransferStatus.Pending);
    }

    public Transfer() {

    }

    public int getIdTransfer() {
        return idTransfer;
    }

    public void setIdTransfer(int idTransfer) {
        this.idTransfer = idTransfer;
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
            this.status= new Status(1, TransferStatus.Validated);
        }
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "idTransfer=" + idTransfer +
                ", codeSecurityIn=" + codeSecurityIn +
                ", codeSecurityOut=" + codeSecurityOut +
                ", amount=" + amount +
                ", validated=" + validated +
                ", status=" + status +
                '}';
    }

    public Status getStatus() {
        return status;
    }


}
