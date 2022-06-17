package src.com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static FilesJson file = new FilesJson<>();

    public void userMenu(User user, Scanner scanner){
        int option = 0;
        List<Wallet> walletList = file.readJsonNode("nodes.json");
        Wallet aux = checkWallet(user, walletList);
        System.out.println("Bienvenido " + user.getUsername() + ".");

        do{
            System.out.println("1 - Consultar saldo");
            System.out.println("2 - Realizar una transaccion");
            System.out.println("3 - Consultar transacciones pendientes");
            System.out.println("4 - Ver historial de transacciones");
            System.out.println("5 - Validar transferencia");
            System.out.println("6 - Salir");

            System.out.println("Ingrese una opcion: ");
            option = scanner.nextInt();
            switch (option){
                case 1:
                    System.out.println("El monto es: " + aux.getAmount());
                    break;
                case 2:
                    NewTransfer(user,walletList);
                    break;
                case 3:
                    SeeNonValidatedTransfers(user,walletList);
                    break;
                case 4:
                    SeeHistorial(user,walletList);
                    break;
                case 5:
                    CheckTransfersNonValidated(user,walletList);
                    break;
                case 6:
                    System.out.println("Cerrando sesion.. ");
                    // return to login stage
                    break;
                default:
                    System.out.println("Ha ingresado una opcion incorrecta. Intente denuevo ");
            }

        }while (option != 6);
    }

    public Wallet createWallet(User user, List<Wallet> walletList){
        Wallet wallet = new Wallet(user.getCodeSecurity());
        walletList.add(wallet);
        file.writeToJson("nodes.json", walletList);
        return wallet;
    }

    public Wallet checkWallet(User user,List<Wallet> walletList ){
        Wallet aux = null;
        for (Wallet wallet: walletList) {
            if (user.getCodeSecurity().equals(wallet.getCodeSecurity())){
                aux = wallet;
            }
        }
        if (aux == null){
           aux = createWallet(user, walletList);
        }
        return aux;
    }
    public void SeeTransfersValidated(User user,List<Wallet> walletList){
        Wallet wallet=checkWallet(user, walletList);
        List<Transfer> transfers=file.readJsonTransfer("transfer.json");
        for(String str:wallet.getTransferenciasValidadas(transfers)){
            System.out.println(str+"\n");
        }
    }
    public void CheckTransfersNonValidated(User user,List<Wallet> walletList){
        Wallet wallet=checkWallet(user, walletList);
        int trans=0;
        Scanner scan=new Scanner(System.in);
        List<Transfer> transfers=file.readJsonTransfer("transfer.json");
        for(String str:wallet.getTransferenciasNoValidadas(transfers)){
            System.out.println(str+"\n");
        }
        System.out.println("Ingrese la transferencia a validar: ");
        trans=scan.nextInt();
        wallet.ValidarTransferencia(transfers,trans,user);
    }
    public void SeeHistorial(User user,List<Wallet> walletList){
        Wallet wallet=checkWallet(user, walletList);
        List<Transfer> transfers=file.readJsonTransfer("transfer.json");
        for(String str:wallet.getTransferenciasValidadas(transfers)){
            System.out.println(str+"\n");
        }
        for(String str:wallet.getTransferenciasNoValidadas(transfers)){
            System.out.println(str+"\n");
        }
    }
    public  void SeeNonValidatedTransfers(User user,List<Wallet> walletList){
        Wallet wallet=checkWallet(user, walletList);
        List<Transfer> transfers=file.readJsonTransfer("transfer.json");
        for(String str:wallet.getTransferenciasNoValidadas(transfers)){
            System.out.println(str+"\n");
        }
    }
    public void NewTransfer(User user,List<Wallet> walletList){
        Wallet wallet=checkWallet(user, walletList);
        List<Transfer> transfers=file.readJsonTransfer("transfer.json");
        transfers.add(wallet.newtransfer(user.getCodeSecurity(),(transfers.size()+1)));
        file.writeToJson("transfer.json", transfers);
    }

}
