package com.meuprojeto.interfacesistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The TelaInicio class represents the initial screen of the application, welcoming users and offering them the option to start using the system.
 */
public class inicio extends JFrame {
    /**
     * Constructs a new TelaInicio object.
     */
    public inicio() {
        super("Bem-vindo!"); // Sets the title of the frame to "Bem-vindo!" which means "Welcome!" in Portuguese.

        // Configures the layout manager for the frame.
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(64, 82, 100)); // Sets a dark blue background color.

        // Creates a central panel containing the title and description.
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Sets the layout to vertical BoxLayout.
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Adds padding to the panel.
        centerPanel.setBackground(new Color(64, 82, 100)); // Sets the same background color as the frame.

        // Creates and configures the title label.
        JLabel titleLabel = new JLabel("Sistema de Consulta de CEP");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Sets the font size and style.
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Aligns the title horizontally.
        titleLabel.setForeground(Color.WHITE); // Sets the text color to white.
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centers the text horizontally.
        centerPanel.add(titleLabel); // Adds the title label to the center panel.

        // Creates and configures the description label.
        JLabel descriptionLabel = new JLabel("<html>Este sistema permite que você consulte informações<br>sobre endereços a partir do CEP informado.</html>");
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Aligns the description horizontally.
        descriptionLabel.setForeground(Color.WHITE); // Sets the text color to white.
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centers the text horizontally.
        centerPanel.add(descriptionLabel); // Adds the description label to the center panel.

        add(centerPanel, BorderLayout.CENTER); // Adds the center panel to the frame's center.

        // Creates the "Iniciar" button.
        JButton startButton = new JButton("Iniciar");
        startButton.setFont(new Font("Arial", Font.BOLD, 18)); // Sets the font size and style.
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Aligns the button horizontally.
        startButton.setBackground(new Color(0, 123, 255)); // Sets the background color to blue.
        startButton.setForeground(Color.WHITE); // Sets the text color to white.
        startButton.setFocusPainted(false); // Removes the focus border.
        startButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30)); // Adds padding to the button.
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaConsulta(); // Calls the method to open the consulta screen when the button is clicked.
            }
        });
        add(startButton, BorderLayout.SOUTH); // Adds the button to the bottom of the frame.

        // Configures the frame settings.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets the default close operation.
        setSize(600, 300); // Sets the size of the frame.
        setResizable(false); // Disables frame resizing.
        setLocationRelativeTo(null); // Centers the frame on the screen.
        setVisible(true); // Makes the frame visible.
    }

    /**
     * Opens the consulta screen and closes the current screen.
     */
    private void abrirTelaConsulta() {
        new consulta(); // Creates a new instance of the CepConsultaApp class.
        dispose(); // Disposes of the current frame.
    }

    /**
     * The entry point of the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new inicio(); // Creates a new instance of the TelaInicio class.
            }
        });
    }
}
