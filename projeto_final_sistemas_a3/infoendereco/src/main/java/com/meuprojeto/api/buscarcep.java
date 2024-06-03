package com.meuprojeto.api;

// Importações de bibliotecas necessárias para a execução do código
import com.meuprojeto.conversor; // Classe utilitária personalizada para conversão de JSON para String
import com.meuprojeto.classe.Endereco; // Classe personalizada que representa o modelo de dados do Endereço

import java.io.BufferedReader; // Classe para leitura de dados de entrada
import java.io.InputStreamReader; // Classe para converter fluxos de bytes em fluxos de caracteres
import java.net.HttpURLConnection; // Classe para gerenciar conexões HTTP
import java.net.URL; // Classe para manipulação de URLs

import com.google.gson.Gson; // Biblioteca Gson para conversão de JSON para objetos Java


// Classe responsável por buscar o endereço a partir de um CEP utilizando a API ViaCEP.
public class buscarcep {
    // URL base do serviço ViaCEP
    private static final String WEB_SERVICE = "http://viacep.com.br/ws/";
    // Código de sucesso HTTP
    private static final int CODIGO_SUCESSO = 200;


    // Busca o endereço correspondente ao CEP fornecido. 
    public static Endereco buscaEnderecoPelo(String cep) throws Exception {
        // Construção da URL completa para a chamada do serviço
        String chamada = WEB_SERVICE + cep + "/json";
        HttpURLConnection conexao = null;
        BufferedReader resposta = null;

        try {
            // Criação da URL e abertura da conexão HTTP
            URL url = new URL(chamada);
            conexao = (HttpURLConnection) url.openConnection();
            // Configuração de tempo limite para conexão e leitura
            conexao.setConnectTimeout(5000);
            conexao.setReadTimeout(5000);

            // Verificação do código de resposta HTTP
            if (conexao.getResponseCode() != CODIGO_SUCESSO) {
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());
            }

            // Leitura da resposta da conexão
            resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            // Conversão da resposta JSON para String
            String jsonString = conversor.converteJsonEmString(resposta);

            // Utilização do Gson para converter o JSON em objeto Endereco
            Gson gson = new Gson();
            return gson.fromJson(jsonString, Endereco.class);
        } catch (Exception e) {
            // Lançamento de uma nova exceção com a mensagem de erro
            throw new Exception("ERRO: " + e.getMessage(), e);
        } finally {
            // Fechamento dos recursos utilizados
            if (resposta != null) try { resposta.close(); } catch (Exception e) { /* Ignorado */ }
            if (conexao != null) conexao.disconnect();
        }
    }
}

