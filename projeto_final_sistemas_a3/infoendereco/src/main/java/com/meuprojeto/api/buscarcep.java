package com.meuprojeto.api;

import com.meuprojeto.conversor; // Importa a classe conversor
import com.meuprojeto.classes.Endereco;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson; // Importa a classe Gson do pacote com.google.gson

public class buscarcep {
    private static final String WEB_SERVICE = "http://viacep.com.br/ws/"; // URL base do serviço de busca de CEPs
    private static final int CODIGO_SUCESSO = 200; // Código HTTP 200 que indica uma resposta bem-sucedida

    public static Endereco buscaEnderecoPelo(String cep) throws Exception {
        String chamada = WEB_SERVICE + cep + "/json"; // Constrói a URL de chamada ao serviço com o CEP fornecido
        HttpURLConnection conexao = null; // Inicializa a conexão como nula
        BufferedReader resposta = null; // Inicializa a resposta como nula

        try {
            URL url = new URL(chamada); // Cria um objeto URL com a URL de chamada
            conexao = (HttpURLConnection) url.openConnection(); // Abre uma conexão HTTP
            conexao.setConnectTimeout(5000); // Define um timeout de conexão de 5 segundos
            conexao.setReadTimeout(5000); // Define um timeout de leitura de 5 segundos

            if (conexao.getResponseCode() != CODIGO_SUCESSO) { // Verifica se o código de resposta não é igual ao código de sucesso
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode()); // Lança uma exceção caso o código de resposta seja diferente do esperado
            }

            resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream())); // Inicializa o objeto resposta para ler a resposta do servidor
            String jsonString = conversor.converteJsonEmString(resposta); // Converte a resposta JSON em uma string

            Gson gson = new Gson(); // Inicializa um objeto Gson para manipulação de JSON
            return gson.fromJson(jsonString, Endereco.class); // Converte a string JSON em um objeto Endereco utilizando a classe Gson
        } catch (Exception e) { // Captura qualquer exceção que ocorra durante a execução do bloco try
            throw new Exception("ERRO: " + e.getMessage(), e); // Lança uma exceção informando o erro ocorrido
        } finally {
            if (resposta != null) try { resposta.close(); } catch (Exception e) { /* Ignorado */ } // Fecha o BufferedReader de resposta
            if (conexao != null) conexao.disconnect(); // Desconecta a conexão HTTP
        }
    }
}