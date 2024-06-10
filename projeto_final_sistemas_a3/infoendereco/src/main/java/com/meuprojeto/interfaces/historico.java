package com.meuprojeto.interfaces;

// Importações necessárias
import javax.swing.*; // Importa classes para construção de interfaces gráficas
import com.meuprojeto.classes.Endereco; // Importa a classe Endereco do pacote com.meuprojeto.classes
import java.awt.*; // Importa classes para manipulação de elementos gráficos
import java.awt.event.ActionEvent; // Importa classes para tratamento de eventos
import java.awt.event.ActionListener; // Importa classes para tratamento de eventos
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList; // Importa classe para utilização de ArrayList

// Classe que representa a interface de histórico de pesquisas
public class historico extends JFrame {
    // Componentes da interface
    private JEditorPane historyPane; // Painel de exibição do histórico
    private JButton backButton; // Botão de voltar
    private JTextField filtroField; // Campo de texto para inserção do filtro
    private JTextField cepCliente1;
    private JTextField cepCliente2;
    private JButton filtrarButton; // Botão para filtrar o histórico
    private JButton redefinirButton; // Botão para limpar os filtros
    private JButton showRote; // Botã para limpar os filtros
    private JComboBox<String> filtroComboBox; // ComboBox para seleção do tipo de filtro
    private ArrayList<Endereco> historicoBuscas; // Lista de endereços do histórico
    private ArrayList<Endereco> historicoCompleto; // Histórico completo sem filtros
    

    // Construtor da classe
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
        filtroComboBox = new JComboBox<>(new String[]{"-- Selecione --","Localidade", "Bairro", "UF"});
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

        // Botão para redefinir a busca
        redefinirButton = new JButton("Redefinir Busca"); // Novo botão
        redefinirButton.setFont(new Font("Arial", Font.PLAIN, 14)); // Novo botão
        redefinirButton.setBackground(new Color(255, 69, 0)); // Novo botão
        redefinirButton.setForeground(Color.WHITE); // Novo botão
        filterPanel.add(redefinirButton); // Novo botão

        titleFilterPanel.add(filterPanel, BorderLayout.EAST); // Atualizado
        add(titleFilterPanel, BorderLayout.NORTH);

        cepCliente1 = new JTextField(15);
        filtroField.setFont(new Font("Arial", Font.PLAIN, 14));
        filterPanel.add(cepCliente1);

        cepCliente2 = new JTextField(15);
        filtroField.setFont(new Font("Arial", Font.PLAIN, 14));
        filterPanel.add(cepCliente2);

        // Botão para ver rota entre dois endereços
        showRote = new JButton("Ver Rota");
        showRote.setFont(new Font("Arial", Font.PLAIN, 14));
        showRote.setBackground(new Color(17, 157, 0));
        showRote.setForeground(Color.WHITE);
        filterPanel.add(showRote);

        titleFilterPanel.add(filterPanel, BorderLayout.EAST);
        add(titleFilterPanel, BorderLayout.NORTH);
        
        // Painel central para exibição do histórico
        historyPane = new JEditorPane();
        historyPane.setContentType("text/html");
        historyPane.setEditable(false);
        historyPane.setFont(new Font("Arial", Font.PLAIN, 12));
        historyPane.setBackground(new Color(255, 244, 253));
        historyPane.setForeground(Color.BLACK);

        add(new JScrollPane(historyPane), BorderLayout.CENTER);

        exibirHistorico(historicoBuscas); // Exibe o histórico completo

        // Botão de voltar
        backButton = new JButton("Voltar");
        backButton.setFont(new Font("Arial", Font.BOLD, 12));
        backButton.setBackground(new Color(90, 110, 130));
        backButton.setForeground(Color.WHITE);
        add(backButton, BorderLayout.SOUTH);

        // Ação do botão de voltar
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaCepConsulta();
            }
        });

        // Ação do botão de filtrar
        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarHistorico();
            }
        });

        // Ação do botão de redefinir busca
        redefinirButton.addActionListener(new ActionListener() { // Novo listener
            @Override
            public void actionPerformed(ActionEvent e) {
                redefinirBusca();
            }
        });

        showRote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cep1 = cepCliente1.getText().trim();
                String cep2 = cepCliente2.getText().trim();

                try {
                    Desktop.getDesktop().browse(new URI("https://www.google.com.br/maps/dir/" + cep1 + "/" + cep2 + "/"));
                } catch (IOException | URISyntaxException ex){
                    ex.printStackTrace();
                }
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para exibir o histórico na interface
    private void exibirHistorico(ArrayList<Endereco> historicoBuscas) {
        StringBuilder sb = new StringBuilder();

        sb.append("<html>");
        sb.append("<body style='color:black; font-family:Arial; font-size:12px;'>");

        // Itera sobre os endereços do histórico e os exibe no formato HTML
        for (Endereco endereco : historicoBuscas) {
            sb.append("<b>CEP:</b> <i>").append(endereco.getCep()).append("</i><br>");
            sb.append("<b>Logradouro:</b> <i>").append(endereco.getLogradouro()).append("</i><br>");
            sb.append("<b>Bairro:</b> <i>").append(endereco.getBairro()).append("</i><br>");
            sb.append("<b>Localidade:</b> <i>").append(endereco.getLocalidade()).append("</i><br>");
            sb.append("<b>UF:</b> <i>").append(endereco.getUf()).append("</i><br>");
            sb.append("<b>IBGE:</b> <i>").append(endereco.getIbge()).append("</i><br>");
            sb.append("<b>DDD (Contato):</b> <i>").append(endereco.getDdd()).append("</i><br>");
            sb.append("<b>Temperatura:</b> <i>").append(endereco.getTemperatura()).append("° C</i><hr>");
        }

        sb.append("</body>");
        sb.append("</html>");

        historyPane.setText(sb.toString()); // Define o texto no painel de histórico
    }

    // Método para voltar para a tela de consulta de CEP
    private void voltarParaCepConsulta() {
        new consulta(); // Cria uma nova instância da tela de consulta de CEP
        dispose(); // Fecha a tela de histórico
    }

    // Método para filtrar o histórico com base no filtro selecionado
    private void filtrarHistorico() {
        String filtro = filtroField.getText(); // Obtém o valor do filtro inserido no campo de texto
        String tipoFiltro = (String) filtroComboBox.getSelectedItem(); // Obtém o tipo de filtro selecionado

        ArrayList<Endereco> historicoFiltrado = new ArrayList<>();

        // Itera sobre os endereços do histórico buscando pelo filtro
        for (Endereco endereco : historicoBuscas) {
            // Adiciona o endereço ao histórico filtrado se corresponder ao filtro
            if (tipoFiltro.equals("Localidade") && endereco.getLocalidade().equalsIgnoreCase(filtro)) {
                historicoFiltrado.add(endereco);
            } else if (tipoFiltro.equals("Bairro") && endereco.getBairro().equalsIgnoreCase(filtro)) {
                historicoFiltrado.add(endereco);
            } else if (tipoFiltro.equals("UF") && endereco.getUf().equalsIgnoreCase(filtro)) {
                historicoFiltrado.add(endereco);
            }
        }

        exibirHistorico(historicoFiltrado); // Exibe o histórico filtrado na interface
    }

    // Método para redefinir a busca e exibir o histórico completo
    private void redefinirBusca() {
        filtroField.setText(""); // Limpa o campo de texto do filtro
        exibirHistorico(historicoBuscas); // Exibe o histórico completo na interface
    }
}

