package InterfaceIU;

import AcessoBD.DadosUtilizadores;
import Aviso.Aviso;
import Utilizadores.Utilizadores;
import Utilizadores.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe responsavel pela inativacao de pedidos realizados pelos utilizadores
 * @author Jorge Martins e Rodrigo Duro
 */
public class MenuRequestDelete extends JFrame implements ActionListener {
    private Container cont;
    private JPanel mainPanel, panelTop, bottomNoAcceptPanel, southPanel, bottomAcceptPanel;
    private JTextField loginField;
    private JButton searchLogAcept, serchLogNoAcept;

    /**
     * Construtos com os elementos graficos para a construcao da interface grafica
     */
    public MenuRequestDelete() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        cont = getContentPane();
        cont.setLayout(new BorderLayout());

        mainPanel = new JPanel(new GridLayout(1, 1));

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop.add(new JLabel("Lista de Utilizadores pedido de anular conta"));

        JPanel tagLogin = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagLogin.add(new JLabel("Login da Conta para Decisao"));

        JPanel loginBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        loginField = new JTextField(15);
        loginField.setToolTipText("Login de Utilizador");
        loginBox.add(loginField);

        bottomAcceptPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchLogAcept = new JButton("Aceitar Pedido");
        searchLogAcept.setActionCommand("aceitarPedido");
        searchLogAcept.addActionListener(this);
        searchLogAcept.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomAcceptPanel.add(searchLogAcept);

        bottomNoAcceptPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        serchLogNoAcept = new JButton("Negar Pedido");
        serchLogNoAcept.setActionCommand("negarPedido");
        serchLogNoAcept.addActionListener(this);
        serchLogNoAcept.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomNoAcceptPanel.add(serchLogNoAcept);

        mainPanel.add(new MenuListMaster(DadosUtilizadores.listarUtilizadoresCondicao("ESTADO_UTILIZADOR = 'pedido'")));

        southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.add(tagLogin);
        southPanel.add(loginBox);
        southPanel.add(bottomAcceptPanel);
        southPanel.add(bottomNoAcceptPanel);


        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(mainPanel, BorderLayout.CENTER);
        cont.add(southPanel, BorderLayout.SOUTH);

        this.setSize(950, 490);
        this.setLocationRelativeTo(null);
        this.setTitle("Pedidos Inactivacao Conta");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * Metodo de gestao de eventos da classe
     * @param e evento criado pela classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        switch (op) {
            case "aceitarPedido":
                if (loginField.getText().equals("")) {
                    Aviso.showMessage("Campo Vazio", "Aviso", "error");
                } else {
                    if (DadosUtilizadores.verificaLogin(loginField.getText()).equals("")) {
                        Aviso.showMessage("Login nao Existe", "Aviso", "error");
                    } else {
                        Utilizadores user = DadosUtilizadores.pesquisaLogin(loginField.getText());
                        user.setEstado("inactivo");
                        if(user.getTipo().equals("Gestor")){
                            user.setNome("anonimo"); user.setEmail("anonimo@anonimo.com"); user.setFoto("./Img/placeholderFoto.jpg");
                            DadosUtilizadores.updateUtilizador(user);
                        }else{
                            user.setFoto("./Img/placeholderFoto.jpg");
                            DadosUtilizadores.updateUtilizador(user); //apenas para dar "delete" na foto e não mexer no update dos clientes de acesso a base de dados
                            Clientes client = DadosUtilizadores.pesquisaClientes(loginField.getText());
                            client.setNome("anonimo"); client.setEmail("anonimo@anonimo.com"); client.setMorada("anonima"); client.setContacto(0); client.setEstado("inactivo");
                            DadosUtilizadores.updateCliente(client);
                        }
                        Aviso.showMessage("Conta desactivada com sucesso", "Aviso", "info");
                        this.setVisible(false);
                        MenuRequestDelete menuRequestDelete = new MenuRequestDelete();
                    }
                }
                break;
            case "negarPedido":
                if (loginField.getText().equals("")) {
                    Aviso.showMessage("Campo Vazio", "Aviso", "error");
                } else {
                    if (DadosUtilizadores.verificaLogin(loginField.getText()).equals("")) {
                        Aviso.showMessage("Login nao Existe", "Aviso", "error");
                    } else {
                        Utilizadores user = DadosUtilizadores.pesquisaLogin(loginField.getText());
                        user.setEstado("activo");
                        DadosUtilizadores.updateUtilizador(user);
                        Aviso.showMessage("Conta voltou a estar activa com sucesso", "Aviso", "info");
                        this.setVisible(false);
                        MenuRequestDelete menuRequestDelete = new MenuRequestDelete();
                    }
                }
        }
    }
}
