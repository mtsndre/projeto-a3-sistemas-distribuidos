package com.meuprojeto.classe;

/**
 * Classe que representa um endereço com vários atributos, como CEP, logradouro, bairro, localidade, etc.
 */
public class Endereco {
    // Atributos privados que armazenam os detalhes do endereço
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String ddd;

    // Construtor da classe Endereco que inicializa todos os atributos.
    public Endereco(String cep, String logradouro, String bairro, String localidade, String uf, String ibge, String ddd) {
        this.cep = cep; // inicializa o atributo cep
        this.logradouro = logradouro; // inicializa o atributo logradouro
        this.bairro = bairro; // inicializa o atributo bairro
        this.localidade = localidade; // inicializa o atributo localidade
        this.uf = uf; // inicializa o atributo uf
        this.ibge = ibge; // inicializa o atributo ibge
        this.ddd = ddd; // inicializa o atributo ddd
    }

    // Métodos getters e setters

    // Método getter para o atributo cep
    public String getCep() {
        return cep;
    }

    // Método setter para o atributo cep
    public void setCep(String cep) {
        this.cep = cep;
    }

    // Método getter para o atributo logradouro
    public String getLogradouro() {
        return logradouro;
    }

    // Método setter para o atributo logradouro
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    // Método getter para o atributo bairro
    public String getBairro() {
        return bairro;
    }

    // Método setter para o atributo bairro
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    // Método getter para o atributo localidade
    public String getLocalidade() {
        return localidade;
    }

    // Método setter para o atributo localidade
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    // Método getter para o atributo uf
    public String getUf() {
        return uf;
    }

    // Método setter para o atributo uf
    public void setUf(String uf) {
        this.uf = uf;
    }

    // Método getter para o atributo ibge
    public String getIbge() {
        return ibge;
    }

    // Método setter para o atributo ibge
    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    // Método getter para o atributo ddd
    public String getDdd() {
        return ddd;
    }

    // Método setter para o atributo ddd
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    // Método toString para exibir informações para o usuário
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
                '}';
    }
}
