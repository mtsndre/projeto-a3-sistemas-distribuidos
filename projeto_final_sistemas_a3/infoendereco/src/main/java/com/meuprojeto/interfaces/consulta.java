package com.meuprojeto.interfaces;

// Importações necessárias
import com.meuprojeto.api.buscarclima; // Importa a classe buscarclima do pacote com.meuprojeto.api
import com.meuprojeto.classes.Endereco; // Importa a classe Endereco do pacote com.meuprojeto.classes
import com.meuprojeto.api.buscarcep; // Importa a classe buscarcep do pacote com.meuprojeto.api

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

// Classe que representa a interface de consulta de CEP
public class consulta extends JFrame {
    // Componentes da interface
    private JTextField cepField; // Campo de texto para inserção do CEP
    private JEditorPane resultPane; // Painel de exibição dos resultados
    private JButton searchButton; // Botão de busca
    private JButton clearButton; // Botão de limpar
    private JButton backButton; // Botão de voltar
    private JButton historyButton; // Botão de histórico
    private JButton showMap;

    // Lista para armazenar o histórico de buscas
    private static ArrayList<Endereco> historicoBuscas;

    // Construtor da classe
    public consulta() {
        super("consulta de CEP");

        // Inicializa o histórico de buscas, se necessário
        if (historicoBuscas == null) {
            historicoBuscas = new ArrayList<>();
        }

        // Configuração do layout da interface
        setLayout(new BorderLayout());

        // Painel superior para os componentes de entrada
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setBackground(new Color(90, 110, 130));

        // Criação e configuração dos componentes de entrada
        JLabel cepLabel = new JLabel("Informe seu CEP:");
        cepLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cepLabel.setForeground(Color.WHITE);
        topPanel.add(cepLabel);

        cepField = new JTextField(10);
        cepField.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(cepField);

        searchButton = new JButton("Buscar");
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setBackground(new Color(27, 88, 130));
        searchButton.setForeground(Color.WHITE);
        topPanel.add(searchButton);

        clearButton = new JButton("Limpar");
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setBackground(new Color(255, 69, 0));
        clearButton.setForeground(Color.WHITE);
        topPanel.add(clearButton);

        showMap = new JButton("Ver Mapa");
        showMap.setFont(new Font("Arial", Font.BOLD, 14));
        showMap.setBackground(new Color(17, 157, 0));
        showMap.setForeground(Color.WHITE);
        topPanel.add(showMap);

        backButton = new JButton("Voltar");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(128, 128, 128));
        backButton.setForeground(Color.WHITE);
        topPanel.add(backButton);

        add(topPanel, BorderLayout.NORTH);

        // Painel central para exibição dos resultados
        resultPane = new JEditorPane();
        resultPane.setContentType("text/html");
        resultPane.setEditable(false);
        resultPane.setFont(new Font("Arial", Font.PLAIN, 12));
        resultPane.setBackground(new Color(255, 244, 253));
        resultPane.setForeground(Color.BLACK);

        add(new JScrollPane(resultPane), BorderLayout.CENTER);

        // Botão para exibir o histórico de buscas
        historyButton = new JButton("Histórico");
        historyButton.setFont(new Font("Arial", Font.BOLD, 14));
        historyButton.setBackground((new Color(90, 110, 130)));
        historyButton.setForeground(Color.WHITE);

        add(historyButton, BorderLayout.SOUTH);

        // Ações dos botões
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCep();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        showMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mycep = cepField.getText().trim();

                try {
                    Desktop.getDesktop().browse(new URI("https://www.google.com.br/maps/place/" + mycep + "/"));
                } catch (IOException | URISyntaxException ex){
                    ex.printStackTrace();
                }
            }
        });
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirInfohistorico();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarSessao();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(730, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para buscar o CEP
    private void buscarCep() {
        String cep = cepField.getText().trim();
        if (cep.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe um CEP válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Busca o endereço correspondente ao CEP
            Endereco endereco = buscarcep.buscaEnderecoPelo(cep);
            if (endereco == null || endereco.getCep() == null || endereco.getLogradouro() == null ||
                    endereco.getBairro() == null || endereco.getLocalidade() == null ||
                    endereco.getUf() == null || endereco.getIbge() == null ||
                    endereco.getDdd() == null) {

                JOptionPane.showMessageDialog(this, "CEP não existe ou possui dados incompletos.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // Obtém a temperatura associada ao endereço
                endereco.setTemperatura(obterTemperatura(endereco.getLocalidade()));
                // Adiciona o endereço ao histórico de buscas e exibe na interface
                historicoBuscas.add(endereco);
                exibirEndereco(endereco);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar o CEP: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Método para obter a temperatura associada a uma cidade
    private String obterTemperatura(String cidade) {
        Endereco tempo = buscarclima.getTempo(cidade);
        if (tempo != null) {
            return tempo.getTemperatura();
        } else {
            return "Não foi possível obter a temperatura.";
        }
    }

    // Método para exibir o endereço na interface
    private void exibirEndereco(Endereco endereco) {
        StringBuilder sb = new StringBuilder();

        sb.append("<html>");
        sb.append("<body style='color:black; font-family:Arial; font-size:12px;'>");
        sb.append("<b>CEP:</b> <i>").append(endereco.getCep()).append("</i><br>");
        sb.append("<b>Logradouro:</b> <i>").append(endereco.getLogradouro()).append("</i><br>");
        sb.append("<b>Bairro:</b> <i>").append(endereco.getBairro()).append("</i><br>");
        sb.append("<b>Localidade:</b> <i>").append(endereco.getLocalidade()).append("</i><br>");
        sb.append("<b>UF:</b> <i>").append(endereco.getUf()).append("</i><br>");
        sb.append("<b>IBGE:</b> <i>").append(endereco.getIbge()).append("</i><br>");
        sb.append("<b>DDD (Contato):</b> <i>").append(endereco.getDdd()).append("</i><br>");
        sb.append("<b>Temperatura:</b> <i>").append(endereco.getTemperatura()).append("° C</i>");
        sb.append("</body>");
        sb.append("</html>");

        resultPane.setText(sb.toString());
    }

    // Método para abrir o histórico de buscas
    private void abrirInfohistorico() {
        new historico(historicoBuscas);
    }

    // Método para limpar os campos da interface
    private void limparCampos() {
        cepField.setText("");
        resultPane.setText("");
    }

    // Método para reiniciar a sessão
    private void reiniciarSessao() {
        Object[] options = { "Encerrar Sessão", "Cancelar" };
        int resposta = JOptionPane.showOptionDialog(
                null,
                "Deseja reiniciar a sessão?",
                "Encerrar Sessão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if (resposta == JOptionPane.YES_OPTION) {
            new inicio();
            dispose();
        }
    }

    // Método principal para iniciar a interface
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new consulta();
            }
        });
    }
}
