package com.meuprojeto.interfacesistema;

import javax.swing.*;
import com.meuprojeto.api.buscarcep;
import com.meuprojeto.classe.Endereco;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class consulta extends JFrame {
    private JTextField cepField;
    private JEditorPane resultPane;
    private JButton searchButton;
    private JButton clearButton;
    private JButton backButton;
    private JButton historyButton;
    private static ArrayList<Endereco> historicoBuscas; // Alterado para estático

    public consulta() {
        super("Consulta de CEP");
        if (historicoBuscas == null) { // Verifica se o histórico está inicializado
            historicoBuscas = new ArrayList<>();
        }
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setBackground(new Color(90, 110, 130));

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

        backButton = new JButton("Voltar");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(128, 128, 128));
        backButton.setForeground(Color.WHITE);
        topPanel.add(backButton);

        add(topPanel, BorderLayout.NORTH);

        resultPane = new JEditorPane();
        resultPane.setContentType("text/html");
        resultPane.setEditable(false);
        resultPane.setFont(new Font("Arial", Font.PLAIN, 12));
        resultPane.setBackground(new Color(255, 244, 253));
        resultPane.setForeground(Color.BLACK); // Alterado para preto
        resultPane.setText(""); // Definindo o texto inicial como vazio

        add(new JScrollPane(resultPane), BorderLayout.CENTER);

        historyButton = new JButton("Histórico");
        historyButton.setFont(new Font("Arial", Font.BOLD, 14));
        historyButton.setBackground((new Color(90, 110, 130)));
        historyButton.setForeground(Color.WHITE);

        add(historyButton, BorderLayout.SOUTH);

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

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirInfoHistorico();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Encerrar Sessão", "Cancelar"};
                int resposta = JOptionPane.showOptionDialog(
                    null, 
                    "Deseja reiniciar a sessão?", 
                    "Encerrar Sessão", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    options, 
                    options[0]
                );
                if (resposta == JOptionPane.YES_OPTION) {
                    // Volta para a tela inicial
                    new inicio(); // Assumindo que "inicio.java" é a classe da tela inicial
                    dispose(); // Fecha a tela atual
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buscarCep() {
        String cep = cepField.getText().trim();
        if (cep.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe um CEP válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            Endereco endereco = buscarcep.buscaEnderecoPelo(cep);
            if (endereco == null || endereco.getCep() == null || endereco.getLogradouro() == null || 
                endereco.getBairro() == null || endereco.getLocalidade() == null || 
                endereco.getUf() == null || endereco.getIbge() == null || 
                endereco.getDdd() == null) {
                
                JOptionPane.showMessageDialog(this, "CEP não existe ou possui dados incompletos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                historicoBuscas.add(endereco);
                exibirEndereco(endereco);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar o CEP: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

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
        sb.append("<b>DDD (Contato):</b> <i>").append(endereco.getDdd()).append("</i>");
        sb.append("</body>");
        sb.append("</html>");

        resultPane.setText(sb.toString());
        cepField.setText("");
    }

    private void abrirInfoHistorico() {
        new historico(historicoBuscas);
    }

    private void limparCampos() {
        cepField.setText("");
        resultPane.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new consulta();
            }
        });
    }
}
