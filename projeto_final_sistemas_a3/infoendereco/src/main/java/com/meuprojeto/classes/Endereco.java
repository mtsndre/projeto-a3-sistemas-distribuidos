package com.meuprojeto.classes;

// Classe que representa um endereço
public class Endereco {
    // Atributos do endereço
    private String cep; // CEP do endereço
    private String logradouro; // Logradouro do endereço
    private String bairro; // Bairro do endereço
    private String localidade; // Cidade/localidade do endereço
    private String uf; // Estado/UF do endereço
    private String ibge; // Código IBGE do endereço
    private String ddd; // Código DDD do endereço
    private String latitude; // Latitude do endereço
    private String longitude; // Longitude do endereço
    private String temperatura; // Temperatura associada ao endereço

    // Construtor vazio
    public Endereco() {
    }

    // Getters e Setters para os atributos do endereço
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    // Método toString para representar o objeto Endereco como uma String formatada
    @Override
    public String toString() {
        return "Endereco{" +
                "cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                ", ibge='" + ibge + '\'' +
                ", ddd='" + ddd + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", temperatura='" + temperatura + '\'' +
                '}';
    }
}
