package src.com.company;

import java.util.UUID;

public class User {
        private String mail;
        private String pass;
        private UUID codeSecurity = UUID.randomUUID();
        private String username;


     public User(String mail, String pass, String username) {
        this.mail = mail;
        this.pass = pass;
        this.username = username;
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
        public double checkBalance(String mail, String codeSecurity){
            double test= 0;
            return test;
        }

        public void viewPending(String codeSecurity, String status){


        }

        public void validate(){

        }

    }

