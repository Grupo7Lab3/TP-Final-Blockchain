package src.com.company;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Wallet {
    private double amount;
    private UUID CodeSecurity;


    public Wallet(UUID codeSecurity) {
        this.amount = 100;
        this.CodeSecurity = codeSecurity;
    }

    public Wallet() {
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public UUID getCodeSecurity() {
        return this.CodeSecurity;
    }

    public void setCodeSecurity(UUID codeSecurity) {
        this.CodeSecurity = codeSecurity;
    }

    public Transfer newTransfer(Scanner scanner, UUID codeSecurity, int idTransfer) {
        double amountTransfer = 0;
        LocalDate today = LocalDate.now();
        if (this.amount == 0) {
            System.out.println("Saldo insuficiente. ");
            return null;
        }
        do {
            System.out.println("\nIngrese cuanto quiere transferir:");
            amountTransfer = scanner.nextDouble();
            if (amountTransfer > this.amount) {
                System.out.println("\nEl monto ingresado es mayor a lo que tiene en la wallet");
            }
        } while (amountTransfer > this.amount);
        System.out.println("La transferencia pasa a validarse");
        this.amount = this.amount - amountTransfer;
        Transfer trans = new Transfer(idTransfer, codeSecurity, this.CodeSecurity, amountTransfer, today.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.LONG)));
        System.out.println(trans);
        return trans;
    }


    public List<String> getTransferenciasValidadas(List<Transfer> transfers) {
        List<String> aux = new ArrayList<>();
        int contador = 0;
        while (contador != transfers.size()) {
            if (transfers.get(contador).getStatus().getId() == 1 && transfers.get(contador).getCodeSecurityOut().equals(this.CodeSecurity)) {
                aux.add(transfers.get(contador).toString());
            }
            contador++;
        }
        return aux;
    }

    public List<Transfer> getTransferenciasNoValidadas(List<Transfer> transfers, Wallet wallet, boolean option) {
        List<Transfer> aux = new ArrayList<>();
        for (Transfer transfer : transfers) {
            if (transfer.getStatus().getId() == 2 && option) {
                if (wallet.getCodeSecurity().equals(transfer.getCodeSecurityOut()))
                aux.add(transfer);
            }
            if (transfer.getStatus().getId() == 2 && !option){
                if (!wallet.getCodeSecurity().equals(transfer.getCodeSecurityOut()) && !wallet.getCodeSecurity().equals(transfer.getCodeSecurityIn())) {
                    boolean found = false;
                    for (User user : transfer.getValidated().getUserList()) {
                        if (user.getCodeSecurity().equals(wallet.CodeSecurity)){
                            found = true;
                        }
                    }
                    if (!found)
                    aux.add(transfer);
                }
            }
        }
        return aux;
    }

    public boolean ValidarTransferencia(List<Transfer> transfers, int id, User user, FilesJson file) {
        boolean found = false;
        boolean validated = false;
        for (int i = 0; i < transfers.size(); i++) {
            if (i == id-1) {
                List<User> aux = transfers.get(i).getValidated().getUserList();
                for (User userAux : aux) {
                    if (transfers.get(i).getStatus().getId() == 1 && user.getCodeSecurity().equals(userAux.getCodeSecurity())) {
                        System.out.println("No se puede validar esta transferencia");
                        found = true;
                    }
                }
                if (!found) {
                    validated = transfers.get(i).setValidatedUser(user);
                    file.writeToJson("transfer.json", transfers);
                }
            }
        }
        return validated;
    }
}
