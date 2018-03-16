package TestJDBC;

public class User {

    private int id;
    private String userName;
    private  String psssword;
    private  char ident;
    private char telephone;
    private  String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPsssword() {
        return psssword;
    }

    public void setPsssword(String psssword) {
        this.psssword = psssword;
    }

    public char getIdent() {
        return ident;
    }

    public void setIdent(char ident) {
        this.ident = ident;
    }

    public char getTelephone() {
        return telephone;
    }

    public void setTelephone(char telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
