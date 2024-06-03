package com.meuprojeto.interfacesistema;

import com.meuprojeto.classe.Endereco;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class historico extends JFrame {
    private JEditorPane historyPane;
    private JButton backButton;
    private JTextField filtroField;
    private JButton filtrarButton;
    private JButton redefinirButton; // Botão para limpar os filtros
    private JComboBox<String> filtroComboBox;
    private ArrayList<Endereco> historicoBuscas;
    private ArrayList<Endereco> historicoCompleto; // Histórico completo sem filtros

    public historico(ArrayList<Endereco> historicoBuscas) {
        super("Histórico de Pesquisas");
        this.historicoBuscas = historicoBuscas;
        this.historicoCompleto = new ArrayList<>(historicoBuscas); // Copia do histórico completo
        setLayout(new BorderLayout());

        // Painel para o título e filtro
        JPanel titleFilterPanel = new JPanel(new BorderLayout());
        titleFilterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        titleFilterPanel.setBackground(new Color(90, 110, 130));

        // Título à esquerda
        JLabel titleLabel = new JLabel("Histórico de Pesquisas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleFilterPanel.add(titleLabel, BorderLayout.WEST);

        // JComboBox para seleção do tipo de filtro
        filtroComboBox = new JComboBox<>(new String[]{"Localidade", "Bairro", "UF"});
        filtroComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        titleFilterPanel.add(filtroComboBox, BorderLayout.CENTER);

        // Painel para o campo de texto e botão de filtrar
        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.setBackground(new Color(90, 110, 130));

        // Campo de texto para inserção de valor do filtro
        filtroField = new JTextField(15);
        filtroField.setFont(new Font("Arial", Font.PLAIN, 14));
        filterPanel.add(filtroField);

        // Botão para aplicar o filtro
        filtrarButton = new JButton("Filtrar");
        filtrarButton.setFont(new Font("Arial", Font.PLAIN, 14));
        filtrarButton.setBackground(new Color(27, 88, 130));
        filtrarButton.setForeground(Color.WHITE);
        filterPanel.add(filtrarButton);

        titleFilterPanel.add(filterPanel, BorderLayout.EAST);
        add(titleFilterPanel, BorderLayout.NORTH);

        // Botão para redefinir a busca
        redefinirButton = new JButton("Redefinir Busca"); // Novo botão
        redefinirButton.setFont(new Font("Arial", Font.PLAIN, 14)); // Novo botão
        redefinirButton.setBackground(new Color(255, 69, 0)); // Novo botão
        redefinirButton.setForeground(Color.WHITE); // Novo botão
        filterPanel.add(redefinirButton); // Novo botão

        titleFilterPanel.add(filterPanel, BorderLayout.EAST); // Atualizado
        add(titleFilterPanel, BorderLayout.NORTH);

        
        historyPane = new JEditorPane();
        historyPane.setContentType("text/html");
        historyPane.setEditable(false);
        historyPane.setFont(new Font("Arial", Font.PLAIN, 12));
        historyPane.setBackground(new Color(255, 244, 253));
        historyPane.setForeground(Color.BLACK);

        add(new JScrollPane(historyPane), BorderLayout.CENTER);

        exibirHistorico(historicoBuscas);

        backButton = new JButton("Voltar");
        backButton.setFont(new Font("Arial", Font.BOLD, 12));
        backButton.setBackground(new Color(90, 110, 130));
        backButton.setForeground(Color.WHITE);
        add(backButton, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaCepConsulta();
            }
        });

        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarHistorico();
            }
        });

        redefinirButton.addActionListener(new ActionListener() { // Novo listener
            @Override
            public void actionPerformed(ActionEvent e) {
                redefinirBusca();
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(780, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void exibirHistorico(ArrayList<Endereco> historicoBuscas) {
        StringBuilder sb = new StringBuilder();

        sb.append("<html>");
        sb.append("<body style='color:black; font-family:Arial; font-size:12px;'>");

        for (Endereco endereco : historicoBuscas) {
            sb.append("<b>CEP:</b> ").append(endereco.getCep()).append("<br>");
            sb.append("<b>Logradouro:</b> ").append(endereco.getLogradouro()).append("<br>");
            sb.append("<b>Bairro:</b> ").append(endereco.getBairro()).append("<br>");
            sb.append("<b>Localidade:</b> ").append(endereco.getLocalidade()).append("<br>");
            sb.append("<b>UF:</b> ").append(endereco.getUf()).append("<br>");
            sb.append("<b>IBGE:</b> ").append(endereco.getIbge()).append("<br>");
            sb.append("<b>DDD (Contato):</b> ").append(endereco.getDdd()).append("<br><br>");
            sb.append("<b><hr></b> ");
        }

        sb.append("</body>");
        sb.append("</html>");

        historyPane.setText(sb.toString());
    }

    private void voltarParaCepConsulta() {
        new consulta();
        dispose();
    }

    private void filtrarHistorico() {
        String filtro = filtroField.getText();
        String tipoFiltro = (String) filtroComboBox.getSelectedItem();

        ArrayList<Endereco> historicoFiltrado = new ArrayList<>();

        for (Endereco endereco : historicoBuscas) {
            if (tipoFiltro.equals("Localidade") && endereco.getLocalidade().equalsIgnoreCase(filtro)) {
                historicoFiltrado.add(endereco);
            } else if (tipoFiltro.equals("Bairro") && endereco.getBairro().equalsIgnoreCase(filtro)) {
                historicoFiltrado.add(endereco);
            } else if (tipoFiltro.equals("UF") && endereco.getUf().equalsIgnoreCase(filtro)) {
                historicoFiltrado.add(endereco);
            }
        }

        exibirHistorico(historicoFiltrado);
    }

    private void redefinirBusca() {
        filtroField.setText(""); // Limpa o campo de texto
        exibirHistorico(historicoBuscas); // Exibe todos os CEPs no histórico
    }
}
