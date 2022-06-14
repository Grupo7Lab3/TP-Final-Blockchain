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






}
