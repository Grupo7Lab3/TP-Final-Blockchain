package src.com.company.Class;
import src.com.company.Utilities.FilesJson;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static FilesJson file = new FilesJson<>();

    public void userMenu(User user, Scanner scanner, List<User> userList){
        int option = 0;

        System.out.println("Bienvenido " + user.getUsername() + ".");

        do{
            List<Transfer> transfers = file.readJsonTransfer("src/com/company/Utilities/transfer.json");
            List<Wallet> walletList = file.readJsonNode("src/com/company/Utilities/wallets.json");
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
                    newTransfer(scanner,user,walletList,userList, transfers);
                    break;
                case 4:
                    seeNonValidatedTransfers(userWallet, transfers);
                    break;
                case 5:
                    viewHistory(userWallet, transfers);
                    break;
                case 6:
                    checkTransfersNonValidated(scanner,user,userWallet,transfers, walletList);
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
        file.writeToJson("src/com/company/Utilities/wallets.json", walletList);
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

    public void checkTransfersNonValidated(Scanner scanner, User user,Wallet wallet, List<Transfer> transfers, List<Wallet> wallets){
        scanner.nextLine();
        int trans=0;
        List<Transfer> transferList = wallet.getTransferenciasNoValidadas(transfers, wallet, false);
        for(Transfer transfer : transferList){
            System.out.println(transfer.toString()+"\n");
        }
        if (!transferList.isEmpty()) {
            System.out.println("Ingrese la transferencia a validar o presione el 0 para salir: ");
            trans = scanner.nextInt();
            if (trans != 0) {
                boolean validated = wallet.ValidarTransferencia(transfers, trans, user, file);
                if (validated) {
                    Transfer aux = transfers.get(trans - 1);
                    for (Wallet wal : wallets) {
                        if (wal.getCodeSecurity().equals(aux.getCodeSecurityIn())) {
                            wal.setAmount(wal.getAmount() + aux.getAmount());
                        }
                    }
                    file.writeToJson("src/com/company/Utilities/wallets.json", wallets);
                    System.out.println("Se ha logrado el requerimiento para validar la transaccion, gracias");
                }
                else{
                    System.out.println("Gracias por validar la transferencia.");
                }
            }
            else if(trans== 4){
                System.out.println("Ha salido correctamente");
                return;
            }
       }else{
            System.out.println("No se encuentra tranferencias a validar");
        }
    }

    public void viewHistory(Wallet wallet,List<Transfer> transfers){
        for(String str:wallet.getTransferenciasValidadas(transfers)){
            System.out.println(str+"\n");
        }
        if(wallet.getTransferenciasValidadas(transfers).isEmpty()){
            System.out.println("No hay transferencias realizadas");
        }
    }

    public  void seeNonValidatedTransfers(Wallet wallet,List<Transfer> transfers){
        for(Transfer transfer:wallet.getTransferenciasNoValidadas(transfers, wallet, true)){
            System.out.println(transfer.toString() + "\n");
        }
        if(wallet.getTransferenciasNoValidadas(transfers,wallet,true).isEmpty()){
            System.out.println("No hay transacciones pendientes");
        }
    }

    public void newTransfer(Scanner scanner, User user,List<Wallet> walletList,List<User> userList,List<Transfer> transfers){
        scanner.nextLine();
        User aux=null;
        String str;
        String aux2= "";
        do {
            scanner.reset();
            System.out.println("Ingrese el nombre del usuario que recibira la transferencia: ");
            str=scanner.nextLine();
            for (User users : userList) {
                if (users.getUsername().equals(str)) {
                    aux = users;
                }
            }
            if(aux == null){
                System.out.println("El usuario no existe");
                System.out.println("Presione cualquier tecla para seguir, si no desea continuar presione 0");
                aux2=scanner.nextLine();
            }
        }while((aux == null) && (!aux2.equals("0")));
        if(aux!= null) {
            Wallet wallet = checkWallet(user, walletList);
            Transfer transfer = wallet.newTransfer(scanner, aux.getCodeSecurity(), transfers.size() + 1);
            if (transfer != null) {
                transfers.add(transfer);
                file.writeToJson("src/com/company/Utilities/transfer.json", transfers);
                file.writeToJson("src/com/company/Utilities/wallets.json", walletList);
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
        file.writeToJson("src/com/company/Utilities/wallets.json", walletList);
    }
}
