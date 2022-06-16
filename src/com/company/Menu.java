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
            System.out.println("5 - Salir");

            System.out.println("Ingrese una opcion: ");
            option = scanner.nextInt();
            switch (option){
                case 1:
                    System.out.println("El monto es: " + aux.getAmount());
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Cerrando sesion.. ");
                    // return to login stage
                    break;
                default:
                    System.out.println("Ha ingresado una opcion incorrecta. Intente denuevo ");
            }

        }while (option != 5);
    }

    public Wallet createWallet(User user, List<Wallet> walletList){
        Wallet wallet = new Wallet(user.getCodeSecurity(), 1);
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
}
