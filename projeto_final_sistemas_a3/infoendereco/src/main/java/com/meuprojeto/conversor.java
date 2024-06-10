package com.meuprojeto;

import java.io.BufferedReader; // Importa classe para leitura de texto
import java.io.IOException; // Importa classe para tratamento de exceções de entrada/saída

// Classe utilitária para conversão de JSON para String
public class conversor {
    
    // Método estático para converter um objeto BufferedReader contendo JSON em uma String
    public static String converteJsonEmString(BufferedReader bufferedReader) throws IOException {
        String textoFinal; // Variável para armazenar cada linha lida do BufferedReader
        StringBuilder jsonEmString = new StringBuilder(); // StringBuilder para construir a String final

        // Lê cada linha do BufferedReader até o final do arquivo
        while ((textoFinal = bufferedReader.readLine()) != null) {
            jsonEmString.append(textoFinal); // Adiciona a linha ao StringBuilder
        }

        return jsonEmString.toString(); // Retorna a String final contendo o JSON
    }
}
