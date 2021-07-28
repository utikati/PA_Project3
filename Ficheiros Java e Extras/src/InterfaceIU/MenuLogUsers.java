package InterfaceIU;

import AcessoBD.DadosLogs;
import AcessoBD.DadosUtilizadores;
import Aviso.Aviso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe responsavel pelo sub Menu de Logs, esta apresenta logo uma listagem com a totalidade de logs do sistema com possbilidade de pesquisa por login
 * @author Jorge Martins e Rodrigo Duro
 */
public class MenuLogUsers extends JFrame implements ActionListener {
    private Container cont;
    private JPanel mainPanel, panelTop, bottomAcceptPanel, southPanel;
    private JTextField loginField;
    private JButton searchLogAcept;

    /**
     * Construtor da classe com os elementos graficos para invocacao da interface grafica
     */
    public MenuLogUsers() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        cont = getContentPane();
        cont.setLayout(new BorderLayout());

        mainPanel = new JPanel(new GridLayout(1, 1));

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop.add(new JLabel("Logs"));

        JPanel tagLogin = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagLogin.add(new JLabel("Pesquisa por Login"));

        JPanel loginBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        loginField = new JTextField(15);
        loginField.setToolTipText("Inserir Login para pesquisa de Logs");
        loginBox.add(loginField);

        bottomAcceptPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchLogAcept = new JButton("Pesquisar Logs");
        searchLogAcept.setActionCommand("pesquisar");
        searchLogAcept.addActionListener(this);
        searchLogAcept.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomAcceptPanel.add(searchLogAcept);

        mainPanel.add(new ListLogs(DadosLogs.listarTodoslogs("")));

        southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.add(tagLogin);
        southPanel.add(loginBox);
        southPanel.add(bottomAcceptPanel);

        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(mainPanel, BorderLayout.CENTER);
        cont.add(southPanel, BorderLayout.SOUTH);

        this.setSize(950, 490);
        this.setLocationRelativeTo(null);
        this.setTitle("Logs");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * Metodo responsavel pela gestao de eventos da classe
     * @param e evento gerado pela classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        switch (op) {
            case "pesquisar":
                if (loginField.getText().equals("")) {
                    Aviso.showMessage("Campo Vazio", "Aviso", "error");
                } else {
                    if (DadosUtilizadores.verificaLogin(loginField.getText()).equals("")) {
                        Aviso.showMessage("Login nao Existe", "Aviso", "error");
                    } else {
                        ListLogs.createFrame(DadosLogs.listarTodoslogs("WHERE ID_UTILIZADOR = "+DadosUtilizadores.verificaLogin(loginField.getText())+" "));
                    }
                }
        }
    }
}
