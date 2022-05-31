package src.com.company;

public class User {
        private String username;
        private String mail;
        private String pass;
        private String codeSecurity;

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

        public String getCodeSecurity() {
            return codeSecurity;
        }

        public void setCodeSecurity(String codeSecurity) {
            this.codeSecurity = codeSecurity;
        }

        public void viewPending(String codeSecurity, String status){}

        public void validate(){}

}

