package skit.project.bill_payment.model;

public class JwtRequest {
    String name;
    String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "JwtRequest [name=" + name + ", password=" + password + "]";
    }

    public JwtRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

}
