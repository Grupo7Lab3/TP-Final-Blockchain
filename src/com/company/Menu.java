package src.com.company;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static FilesJson file = new FilesJson<>();

    public void userMenu(User user, Scanner scanner,List<User> userList){
        int option = 0;

        System.out.println("Bienvenido " + user.getUsername() + ".");

        do{
            List<Transfer> transfers = file.readJsonTransfer("transfer.json");
            List<Wallet> walletList = file.readJsonNode("wallets.json");
            Wallet userWallet = checkWallet(user, walletList);
            System.out.println("1 - Agregar saldo");
            System.out.println("2 - Consultar saldo");
            System.out.println("3 - Realizar una transaccion");
            System.out.println("4 - Consultar transacciones pendientes");
            System.out.println("5 - Ver historial de transacciones");
            System.out.println("6 - Validar transferencia");
            System.out.println("7 - Salir");

            System.out.println("Ingrese una opcion: ");
            option = scanner.nextInt();
            switch (option){
                case 1:
                    addAmount(scanner, user, walletList);
                    break;
                case 2:
                    System.out.println("El monto es: " + userWallet.getAmount());
                    break;
                case 3:
                    NewTransfer(scanner,user,walletList,userList, transfers);
                    break;
                case 4:
                    SeeNonValidatedTransfers(user,userWallet, transfers);
                    break;
                case 5:
                    SeeHistorial(user,userWallet, transfers);
                    break;
                case 6:
                    CheckTransfersNonValidated(scanner,user,userWallet,transfers);
                    break;
                case 7:
                    System.out.println("Cerrando sesion.. ");
                    // return to login stage
                    break;
                default:
                    System.out.println("Ha ingresado una opcion incorrecta. Intente denuevo ");
            }

        }while (option != 7);
    }

    public Wallet createWallet(User user, List<Wallet> walletList){
        Wallet wallet = new Wallet(user.getCodeSecurity());
        walletList.add(wallet);
        file.writeToJson("wallets.json", walletList);
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
    public void CheckTransfersNonValidated(Scanner scanner, User user,Wallet wallet, List<Transfer> transfers){
        scanner.nextLine();
        int trans=0;
        for(String str:wallet.getTransferenciasNoValidadas(transfers)){
            System.out.println(str+"\n");
        }
        System.out.println("Ingrese la transferencia a validar: ");
        trans=scanner.nextInt();
        wallet.ValidarTransferencia(transfers,trans,user);
    }
    public void SeeHistorial(User user,Wallet wallet,List<Transfer> transfers){
        for(String str:wallet.getTransferenciasValidadas(transfers)){
            System.out.println(str+"\n");
        }
        for(String str:wallet.getTransferenciasNoValidadas(transfers)){
            System.out.println(str+"\n");
        }
    }
    public  void SeeNonValidatedTransfers(User user,Wallet wallet,List<Transfer> transfers){
        for(String str:wallet.getTransferenciasNoValidadas(transfers)){
            System.out.println(str+"\n");
        }
    }
    public void NewTransfer(Scanner scanner, User user,List<Wallet> walletList,List<User> userList,List<Transfer> transfers){
        scanner.nextLine();
        User aux=null;
        String str;
        String aux2= "";
        do {
            System.out.println("Ingrese el nombre del usuario que recibira la transferencia: ");
            str=scanner.nextLine();
            for (User users : userList) {
                if (users.getUsername().equals(str)) {
                    aux = users;
                    System.out.println(aux);
                }
            }
            if(aux == null){
                System.out.println("El usuario no existe");
                System.out.println("Presione cualquier tecla para seguir, si no desea continuar presione 4");
                aux2=scanner.nextLine();
            }
        }while((aux == null) && (!aux2.equals("4")));
        if(aux!= null) {
            Wallet wallet = checkWallet(user, walletList);
            Transfer transfer = wallet.newTransfer(scanner, aux.getCodeSecurity(), transfers.size() + 1);
            if (transfer != null) {
                transfers.add(transfer);
                file.writeToJson("transfer.json", transfers);
                file.writeToJson("wallets.json", walletList);
            }
        }
    }

    public void addAmount(Scanner scanner, User user, List<Wallet> walletList){
        Wallet wallet=checkWallet(user, walletList);
        Double amount = 0.0;
        scanner.nextLine();
        do {
            System.out.println("Ingrese el monto que desea ingresar: ");
            amount = scanner.nextDouble();
            if (amount < 0){
                System.out.println("Por favor, ingrese un numero positivo. ");
            }
        }while (amount < 0);

        wallet.setAmount(wallet.getAmount() + amount);
        System.out.println("Saldo agregado exitosamente. Su nuevo saldo es: " + wallet.getAmount());
        file.writeToJson("wallets.json", walletList);
    }

}
