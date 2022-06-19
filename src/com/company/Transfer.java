package src.com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Transfer {
    private int idTransfer;
    private UUID codeSecurityIn;
    private UUID codeSecurityOut;
    private double amount;
    private List<User> validated;
    private Status status;
    private String date;

    public Transfer(int idTransfer, UUID codeSecurityIn, UUID codeSecurityOut, double amount, String date) {
        this.idTransfer = idTransfer;
        this.codeSecurityIn = codeSecurityIn;
        this.codeSecurityOut = codeSecurityOut;
        this.amount = amount;
        this.validated = new ArrayList<>();
        this.status = new Status(3, TransferStatus.Pending);
        this.date = date;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setValidated(User user) {
        this.validated.add(user);
        if (this.validated.size() == 3) {
            this.status = new Status(1, TransferStatus.Validated);
        }
    }

    @Override
    public String toString() {
        return  "id de la transferencia = " + idTransfer +
                ", UUID del cliente emisor= " + codeSecurityOut +
                ", UUID del cliente receptor = " + codeSecurityIn +
                ", Monto = " + amount +
                ", Validacion = " + validated +
                ", Estado = " + status +
                ", Fecha de la transferencia  = " + date;
    }

    public Status getStatus() {
        return status;
    }


}
