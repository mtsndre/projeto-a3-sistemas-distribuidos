package com.meuprojeto;

import java.io.BufferedReader;
import java.io.IOException;

public class conversor {
    
    // Método estático que converte o conteúdo de um BufferedReader em uma String
    public static String converteJsonEmString(BufferedReader bufferedReader) throws IOException {
        String textoFinal; // Variável para armazenar cada linha lida do BufferedReader
        StringBuilder jsonEmString = new StringBuilder(); // StringBuilder para acumular as linhas lidas

        // Lê cada linha do BufferedReader até o final (quando readLine() retorna null)
        while ((textoFinal = bufferedReader.readLine()) != null) {
            jsonEmString.append(textoFinal); // Adiciona a linha lida ao StringBuilder
        }

        return jsonEmString.toString(); // Converte o StringBuilder em uma única string e a retorna
    }
}
