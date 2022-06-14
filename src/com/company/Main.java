package src.com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Wallet wallet = new Wallet();
        List<Wallet> listaWallets = new ArrayList<>();
        listaWallets.add(wallet);

        FilesJson file = new FilesJson();
        file.writeToJson("nodes.json", listaWallets);

        Login login = new Login();
        login.loggin();
  }






}
