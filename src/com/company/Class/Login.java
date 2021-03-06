package src.com.company.Class;
import src.com.company.Utilities.FilesJson;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Login {
    private static Scanner scanner = new Scanner(System.in);
    private static FilesJson file = new FilesJson<>();

    public void loggin() {

        List<User> userList = file.readJsonUser("src/com/company/Utilities/users.json");
        int option = 0;
        Menu menu = new Menu();

        System.out.println("\nBienvenido a la Wallet UTN");
        do {
            System.out.println("\t1- Ingreso ");
            System.out.println("\t2- Registrarse");
            System.out.println("\t3- Salir");

            option = optionEntry(4);

            switch (option) {
                case 1:
                    User useraux = null;
                    useraux = loginUser(userList);
                    if (useraux != null) {
                        System.out.println("Ingreso correcto");
                        menu.userMenu(useraux, scanner,userList);
                    }
                    break;
                case 2:
                    loginRegister(userList);
                    break;
                case 3:
                    System.out.println("Gracias por utilizar el sistema, Vuelva prontos.");

                    break;
            }
        } while (option != 3);
    }

    public static int optionEntry(int number) {
        int op = 0;

        try {
            op = scanner.nextInt();
        } catch (InputMismatchException var3) {
            System.out.println("Dato ingresado no valido");
        }

        while (number < op || op <= 0) {
            scanner.reset();
            System.out.print("Ingrese una opcion valida: ");

            try {
                op = scanner.nextInt();
            } catch (InputMismatchException var4) {
                System.out.println("Datos incorrectos");
                op = 0;
                break;
            }
        }
        return op;
    }

    public User loginUser(List<User> userList) {
        String client;
        String password;
        User useraux = null;
        scanner.nextLine();
        System.out.println("ingrese Username o email");
        client = scanner.nextLine();
        scanner.reset();
        System.out.println("ingrese password");
        password = scanner.nextLine();
        for (User user : userList) {
            if (user.getUsername().equals(client) || user.getMail().equals(client)) {
                if (user.getPass().equals(password)) {
                    useraux = user;
                    return useraux;
                }
            }
        }
        System.out.println("ingreso incorrecto");
        return useraux;
    }

    public User loginRegister(List<User> userList) {
        String username;
        String password;
        String email;
        boolean aux;
        scanner.nextLine();
        do {
            aux = false;
            System.out.println("ingrese Username");
            username = scanner.nextLine();
            System.out.println("ingrese password");
            password = scanner.nextLine();

            do{
                System.out.println("ingrese email");
                email = scanner.nextLine();
                if (!User.valEmail(email)) System.out.println("Ingrese un formato de email valido");
            }while (!User.valEmail(email));
            for (User user : userList) {
                if (user.getUsername().equals(username) || user.getMail().equals(email)) {
                    System.out.println("ese username o email ya esta siendo usado");
                    aux = true;
                }
            }
        } while (aux == true);
        User useraux = new User(username, password, email);
        userList.add(useraux);
        file.writeToJson("src/com/company/Utilities/users.json", userList);
        System.out.println("Usuario registrado con exito");
        return useraux;
    }
}
