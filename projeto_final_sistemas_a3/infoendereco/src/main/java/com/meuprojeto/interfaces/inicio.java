package com.meuprojeto.interfaces;

// Importações necessárias
import javax.swing.*; // Importa classes para construção de interfaces gráficas
import java.awt.*; // Importa classes para manipulação de elementos gráficos
import java.awt.event.ActionEvent; // Importa classes para tratamento de eventos
import java.awt.event.ActionListener; // Importa classes para tratamento de eventos

// Classe que representa a tela inicial do sistema
public class inicio extends JFrame {

    // Construtor da classe
    public inicio() {
        super("Iniciar Sessão"); // Define o título da janela

        setLayout(new BorderLayout()); // Define o layout da janela como BorderLayout
        getContentPane().setBackground(new Color(64, 82, 100)); // Define a cor de fundo da janela

        // Painel central para o título e a descrição
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Define o layout como BoxLayout vertical
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Define as margens internas
        centerPanel.setBackground(new Color(64, 82, 100)); // Define a cor de fundo

        // Título
        JLabel titleLabel = new JLabel("Consulta para Transportadoras");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Define a fonte e o tamanho do título
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinha o título horizontalmente ao centro
        titleLabel.setForeground(Color.WHITE); // Define a cor do texto
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto horizontalmente
        centerPanel.add(titleLabel); // Adiciona o título ao painel central

        // Descrição
        JLabel descriptionLabel = new JLabel("<html><br>Este sistema permite que você consulte informações sobre endereços a partir do CEP informado. Facilite seu serviço e tenha informações gerais sobre seu destino.</html>");
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinha a descrição horizontalmente ao centro
        descriptionLabel.setForeground(Color.WHITE); // Define a cor do texto
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto horizontalmente
        centerPanel.add(descriptionLabel); // Adiciona a descrição ao painel central

        add(centerPanel, BorderLayout.CENTER); // Adiciona o painel central à região central da janela

        // Botão para iniciar
        JButton startButton = new JButton("Iniciar");
        startButton.setFont(new Font("Arial", Font.BOLD, 18)); // Define a fonte e o tamanho do texto do botão
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinha o botão horizontalmente ao centro
        startButton.setBackground(new Color(90, 110, 130)); // Define a cor de fundo do botão
        startButton.setForeground(Color.WHITE); // Define a cor do texto do botão
        startButton.setFocusPainted(false); // Remove a pintura do foco
        startButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30)); // Define as margens internas do botão
        startButton.addActionListener(new ActionListener() { // Adiciona um ouvinte de ação ao botão
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaConsulta(); // Chama o método para abrir a tela de consulta ao clicar no botão
            }
        });
        add(startButton, BorderLayout.SOUTH); // Adiciona o botão à região sul da janela

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento padrão ao fechar a janela
        setSize(600, 300); // Define o tamanho da janela
        setResizable(false); // Impede o redimensionamento da janela
        setLocationRelativeTo(null); // Define a posição da janela como centralizada na tela
        setVisible(true); // Torna a janela visível
    }

    // Método para abrir a tela de consulta de CEP
    private void abrirTelaConsulta() {
        new consulta(); // Cria uma nova instância da tela de consulta de CEP
        dispose(); // Fecha a tela inicial
    }

    // Método principal para inicialização do programa
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new inicio(); // Cria uma nova instância da tela inicial ao iniciar o programa
            }
        });
    }
}
