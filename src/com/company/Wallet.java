package src.com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Wallet {
    private int amount;
    private UUID CodeSecurity;


    public Wallet(UUID codeSecurity) {
        this.amount = 100;
        this.CodeSecurity = codeSecurity;
    }

    public Wallet() {
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public UUID getCodeSecurity() {
        return this.CodeSecurity;
    }

    public void setCodeSecurity(UUID codeSecurity) {
        this.CodeSecurity = codeSecurity;
    }

    public Transfer newtransfer(UUID codeSecurity,int transfer){
        int amount=0;
        Scanner scanInt=new Scanner(System.in);
        do{
            System.out.println("\nIngrese cuanto quiere transferir:");
            amount=scanInt.nextInt();
            if (amount>this.amount){
                System.out.println("\nEl monto ingresado es mayor a lo que tiene en la wallet");
            }
        }while(amount>this.amount);
        System.out.println("La transferencia pasa a validarse");//Lo pense como que el int trasfer es un codigo para rastrear transferencias
        this.amount -= amount;
        Transfer trans=new Transfer(transfer,this.CodeSecurity,codeSecurity,amount);
        return trans;
    }


    public ArrayList<String> getTransferenciasValidadas(List<Transfer> transfers){
        ArrayList<String> aux=new ArrayList<>();
        int contador=transfers.size();
        while(contador<transfers.size()){
            if(transfers.get(contador).getStatus().getId() == 1 && transfers.get(contador).getCodeSecurityIn() == this.CodeSecurity){
                aux.add(transfers.get(contador).toString());
            }
            contador++;
        }
        return aux;
    }
    public List<String> getTransferenciasNoValidadas(List<Transfer> transfers){
        List<String> aux=new ArrayList<>();
        int contador=transfers.size();
        while(contador<transfers.size()){
            if(transfers.get(contador).getStatus().getId() == 3 && transfers.get(contador).getCodeSecurityIn() != this.CodeSecurity && this.CodeSecurity != transfers.get(contador).getCodeSecurityOut()){
                aux.add(transfers.get(contador).toString());//guardarlo en un ArrayList o mostrarlo directamente??
            }
            contador++;
        }
        return aux;
    }
    public void ValidarTransferencia(List<Transfer> transfers,int id, User user){
        if(transfers.get(id).getStatus().getId() == 1 || transfers.get(id).getCodeSecurityOut().equals(user.getCodeSecurity()) || transfers.get(id).getCodeSecurityIn().equals(user.getCodeSecurity()))
            System.out.println("No se puede validar esta transferencia");
        else
            transfers.get(id).setValidated(user);
    }

}
