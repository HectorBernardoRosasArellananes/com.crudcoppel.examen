package com.crudcoppel.com.crudcoppel.examen.Security;

public class Tokenmodel {
    private String tokenAcceso;
    private String tipoToken="Bearer";
    public Tokenmodel(String tokenAcceso) {
        this.tokenAcceso = tokenAcceso;
    }
    public Tokenmodel(String tokenAcceso, String tipoToken) {
        this.tokenAcceso = tokenAcceso;
        this.tipoToken = tipoToken;
    }
    public String getTokenAcceso() {
        return tokenAcceso;
    }
    public void setTokenAcceso(String tokenAcceso) {
        this.tokenAcceso = tokenAcceso;
    }
    public String getTipoToken() {
        return tipoToken;
    }
    public void setTipoToken(String tipoToken) {
        this.tipoToken = tipoToken;
    }
}
