package com.meuprojeto.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.meuprojeto.classes.Endereco;

public class buscarclima {
    // Chave de API do OpenWeatherMap para acessar os dados meteorológicos
    private static final String OPEN_WEATHER_API_KEY = "5e4d941d62c645b9323a8ab9914a73be";

    // Método para obter os dados meteorológicos de uma cidade específica
    public static Endereco getTempo(String city) {
        try {
            // Constrói a URL de solicitação dos dados meteorológicos para a cidade
            // especificada
            String urlString = String.format(
                    "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s",
                    URLEncoder.encode(city, StandardCharsets.UTF_8.toString()), // Codifica o nome da cidade para evitar
                                                                                // problemas com caracteres especiais na
                                                                                // URL
                    OPEN_WEATHER_API_KEY);

            // Cria um objeto URL com a URL de solicitação
            URL url = new URL(urlString);
            // Abre uma conexão HTTP com a URL especificada
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Define o método da requisição como GET
            connection.setRequestMethod("GET");

            // Obtém o código de resposta da requisição
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // Se a requisição for bem-sucedida
                // Cria um BufferedReader para ler a resposta da requisição
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                // Lê a resposta linha por linha e a armazena em um StringBuilder
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Analisa a resposta JSON para extrair a temperatura e a descrição do clima
                return parseTempo(response.toString());
            } else { // Se a requisição não for bem-sucedida
                System.out.println("Erro na solicitação do tempo. Código de resposta: " + responseCode);
            }
        } catch (Exception e) { // Captura qualquer exceção que ocorra durante a execução do código
            e.printStackTrace();
        }
        return null; // Retorna null se ocorrer algum erro durante a execução
    }

    // Método para analisar a resposta JSON e extrair os dados de temperatura
    private static Endereco parseTempo(String json) {
        // Converte a string JSON em um objeto JsonObject usando o Gson
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        // Extrai a temperatura da seção "main" do objeto JSON
        double temperature = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();

        // Cria um novo objeto Endereco para armazenar a temperatura
        Endereco endereco = new Endereco();
        // Define a temperatura no objeto Endereco
        endereco.setTemperatura(String.valueOf(temperature));

        // Retorna o objeto Endereco com os dados de temperatura
        return endereco;
    }
}
