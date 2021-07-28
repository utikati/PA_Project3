package InterfaceIU;

import AcessoBD.DadosNotificacao;
import AcessoBD.DadosUtilizadores;
import Aviso.*;
import Utilizadores.Utilizadores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe responsavel pela aceitacao ou rejeicao de novos utilizadores registados no sistema
 * @author Jorge Martins e Rodrigo Duro
 */
public class MenuNewAcc extends JFrame implements ActionListener {
    private Container cont;
    private JPanel mainPanel, panelTop, bottomSerchPanel, southPanel, bottomRejPanel;
    private JTextField loginField;
    private JButton searchLog, searchRej;

    /**
     * Construtor da classe com os elementos graficos da mesma para a invocacao da interface grafica
     */
    public MenuNewAcc(){
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        cont = getContentPane();
        cont.setLayout(new BorderLayout());

        mainPanel = new JPanel(new GridLayout(1, 1));

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop.add(new JLabel("Lista de Pedidos de Utilizadores"));

        JPanel tagLogin = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagLogin.add(new JLabel("Login"));

        JPanel loginBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        loginField = new JTextField(15);
        loginField.setToolTipText("Login para Aceitar/Rejeitar");
        loginBox.add(loginField);

        bottomSerchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchLog = new JButton("Activar Conta");
        searchLog.setActionCommand("activarConta");
        searchLog.addActionListener(this);
        searchLog.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomSerchPanel.add(searchLog);

        bottomRejPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchRej = new JButton("Rejeitar Conta");
        searchRej.setActionCommand("rejeitarConta");
        searchRej.addActionListener(this);
        searchRej.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomRejPanel.add(searchRej);

        mainPanel.add(new MenuListMaster(DadosUtilizadores.listarUtilizadoresCondicao("ESTADO_UTILIZADOR = 'espera'")));

        southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.add(tagLogin);
        southPanel.add(loginBox);
        southPanel.add(bottomSerchPanel);
        southPanel.add(bottomRejPanel);


        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(mainPanel, BorderLayout.CENTER);
        cont.add(southPanel, BorderLayout.SOUTH);

        this.setSize(950, 490);
        this.setLocationRelativeTo(null);
        this.setTitle("Novas Contas");
        this.setResizable(true);
        this.setVisible(true);

    }

    /**
     * Metodo de gestao de eventos da classe
     *
     * @param e evento gerado pela classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        switch (op){
            case "activarConta": if(loginField.getText().equals("")){
                Aviso.showMessage("Campo Vazio", "Aviso", "error");
            }else{
                if(DadosUtilizadores.verificaLogin(loginField.getText()).equals("")){
                    Aviso.showMessage("Login nao Existe", "Aviso", "error");
                }else{
                    Utilizadores user = DadosUtilizadores.pesquisaLogin(loginField.getText());
                    user.setEstado("activo");
                    DadosUtilizadores.updateUtilizador(user);
                    switch (user.getTipo()){
                        case "Cliente":{
                            DadosNotificacao.deleteNotByDescription("O Cliente "+user.getLogin()+" foi criado e aguarda ser activo!!!");
                            break;
                        }
                        case "Funcionario":{
                            DadosNotificacao.deleteNotByDescription("O funcionario "+user.getLogin()+" foi criado e aguarda ser activo!!!");
                            break;
                        }
                        case "Gestor":{
                            DadosNotificacao.deleteNotByDescription("O Gestor "+user.getLogin()+" foi criado e aguarda ser activo!!!");
                            break;
                        }
                    }
                    Aviso.showMessage("Conta activada com sucesso", "Aviso", "info");
                    this.dispose();
                    MenuNewAcc menuNewAcc = new MenuNewAcc();
                }
            } break;
            case "rejeitarConta": if(loginField.getText().equals("")){
                                        Aviso.showMessage("Campo Vazio", "Aviso", "error");
                                    }else{
                                        if(DadosUtilizadores.verificaLogin(loginField.getText()).equals("")){
                                            Aviso.showMessage("Login nao Existe", "Aviso", "error");
                                        }else{
                                            Utilizadores user = DadosUtilizadores.pesquisaLogin(loginField.getText());
                                            user.setEstado("rejeitado");
                                            DadosUtilizadores.updateUtilizador(user);
                                            switch (user.getTipo()){
                                                case "Cliente":{
                                                    DadosNotificacao.deleteNotByDescription("O Cliente "+user.getLogin()+" foi criado e aguarda ser activo!!!");
                                                    break;
                                                }
                                                case "Funcionario":{
                                                    DadosNotificacao.deleteNotByDescription("O funcionario "+user.getLogin()+" foi criado e aguarda ser activo!!!");
                                                    break;
                                                }
                                                case "Gestor":{
                                                    DadosNotificacao.deleteNotByDescription("O Gestor "+user.getLogin()+" foi criado e aguarda ser activo!!!");
                                                    break;
                                                }
                                            }
                                            Aviso.showMessage("Conta rejeitada com sucesso", "Aviso", "info");
                                            this.dispose();
                                            MenuNewAcc menuNewAcc = new MenuNewAcc();
                                        }
                                    }
                                    break;
        }

    }
}
