package skit.project.bill_payment.model;

public class JwtResponse {

    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
    public JwtResponse(String token) {
        this.token = token;
    }

    

    
    
}
