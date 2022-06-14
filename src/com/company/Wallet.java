package src.com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Wallet {
    private int amount;
    private UUID CodeSecurity;
    private int Trasfer;

    public Wallet(UUID codeSecurity, int trasfer) {
        this.amount = 100;
        this.CodeSecurity = codeSecurity;
        this.Trasfer = trasfer;
    }

    public Wallet(UUID codeSecurity){
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

    /*public int getTrasfer() {
        return this.Trasfer;
    }

    public void setTrasfer(int trasfer) {
        this.Trasfer = trasfer;
    }*/

    /*public void checkTranser(ArrayList<Transfer> transferArray, User src.com.company.user){
        int index=0;
        while (transferArray.size()<index){
            if (transferArray.get(index).getTransfer()!=this.Trasfer&&transferArray.get(index).getStatus()==Status.Pendiente){
                transferArray.get(index).setValidated(src.com.company.user);
            }
            index++;
        }
    }

    //no estoy seguro si deberia estar aca
    public Transfer newtransfer(String codeSecurity){
        int amount=0;
        Scanner scanInt=new Scanner(System.in);
        do{
            System.out.println("\nIngrese cuanto quiere transferir:");
            amount=scanInt.nextInt();
            if (amount>this.amount){
                System.out.println("\nEl monto ingresado es mayor a lo que tiene en la wallet");
            }
        }while(amount<this.amount);
        System.out.println("La transferencia pasa a validarse");//Lo pense como que el int trasfer es un codigo para rastrear transferencias
        Transfer trans=new Transfer(this.Trasfer,codeSecurity,this.CodeSecurity,amount);
        return trans;
    }


    public ArrayList<String> getTransferenciasValidadas(ArrayList<Transfer> transfers){
        ArrayList<String> aux=new ArrayList<>();
        int contador=transfers.size();
        while(contador<transfers.size()){
            if(transfers.get(contador).getTransfer()==this.Trasfer&&transfers.get(contador).getStatus()==Status.Validado){
                aux.add(transfers.get(contador).toString());
            }
            contador++;
        }
        return aux;
    }

*/

}
