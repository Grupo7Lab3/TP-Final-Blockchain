package src.com.company.Class;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String mail;
    private String pass;
    private UUID codeSecurity = UUID.randomUUID();
    private String username;

    public User(String username, String pass, String mail) {
        this.username = username;
        this.pass = pass;
        this.mail = mail;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public UUID getCodeSecurity() {
        return codeSecurity;
    }

    public void setCodeSecurity(UUID codeSecurity) {
        this.codeSecurity = codeSecurity;
    }

    public static boolean valEmail(String string){
        String emailRegex= "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPatt = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPatt.matcher(string);
        return matcher.find();
    }

    @Override
    public String toString() {
        return "User " +
                "mail='" + mail + '\'' +
                ", pass='" + pass + '\'' +
                ", codeSecurity=" + codeSecurity +
                ", username='" + username + '\'';
    }
}

