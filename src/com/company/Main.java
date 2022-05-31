package src.com.company;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // leer archos

        Node node = new Node();
        node.addWallet(new Wallet("abc"));
        User user = new User();
        user.setCodeSecurity("abc");
        menu(user, node);
  }

  public static void menu(User user, Node node){
      int option = 0;
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
                  Wallet aux = node.searchWallet(user.getCodeSecurity());
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




}
