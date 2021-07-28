package InterfaceIU;

import AcessoBD.DadosUtilizadores;
import DadosEstaticos.DadosStatic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import Aviso.*;

/**
 * Metodo responsavel pela listagem e pesquisa de Utilizadores do sistema, listagens de forma condicionada ao nome, login..
 * @author Jorge Martins e Rodrigo Duro
 */
public class MenuListSearchUser extends JFrame implements ActionListener, ItemListener {

    private Container container;
    private JPanel titlePanel, geralPanel, bottomPanelSerchName, bottomPanelSerchLogin, bottomPanelSerchType, panelOut;
    private JButton nameBottom, loginBottom, typeBottom, outBottom;
    private JTextField nameField, loginField;
    private JComboBox typeUser;
    private String type = "Funcionario";

    /**
     * Construtor que cria uma interface grafica com os seus elementos
     */
    public MenuListSearchUser(){
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        container = getContentPane();
        container.setLayout(new BorderLayout());

        titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(new JLabel("Pesquisa e Listagens Condicionadas"));
        //grids que vao ser usadas
        GridLayout grids = new GridLayout(3, 1);
        grids.setHgap(2); //espaçamento horizontal entre as "linhas"
        grids.setVgap(2);

        geralPanel = new JPanel(grids);

        //FINAL DA CONSTRUÇÃO DAS GRIDS QUE DEPOIS VAO SER PREENCHIDAS COM OS PAINEIS

        //criar labels e fields
        //ETIQUETAS PARA OS CAMPOS
        JPanel tagName = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagName.add(new JLabel("Nome"));

        JPanel tagLogin = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagLogin.add(new JLabel("Login"));

        JPanel tagType = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tagType.add(new JLabel("Tipo de Conta"));

        //CAMPOS A PREENCHER

        JPanel nameBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        nameField = new JTextField(15);
        nameField.setToolTipText("Pesquisa avancada por Nome");
        nameBox.add(nameField);

        JPanel loginBox = new JPanel((new FlowLayout(FlowLayout.LEFT)));
        loginField = new JTextField(15);
        loginField.setToolTipText("Pesquisa por Login");
        loginBox.add(loginField);

        JPanel typeBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String [] varTypes = {"Funcionario", "Gestor", "Cliente"};
        typeUser = new JComboBox<String>(varTypes);
        typeUser.setToolTipText("Escolha o Tipo de Utilizador");
        typeUser.addItemListener(this);
        typeBox.add(typeUser);


        //BOTOES DE UPDATE DOS DADOS
        bottomPanelSerchName = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nameBottom = new JButton("Pesquisa por Nome");
        nameBottom.setActionCommand("pesquisaNome");
        nameBottom.addActionListener(this);
        nameBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanelSerchName.add(nameBottom);

        bottomPanelSerchLogin = new JPanel(new FlowLayout(FlowLayout.LEFT));
        loginBottom = new JButton("Pesquisa Login");
        loginBottom.setActionCommand("pesquisaLogin");
        loginBottom.addActionListener(this);
        loginBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanelSerchLogin.add(loginBottom);

        bottomPanelSerchType = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typeBottom = new JButton("Pesquisa Tipo Conta");
        typeBottom.setActionCommand("pesquisaTipo");
        typeBottom.addActionListener(this);
        typeBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanelSerchType.add(typeBottom);

        JPanel panel1, panel2, panel3;
        panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //adicionar ao painel com a info geral
        panel1.add(tagName);
        panel1.add(nameBox);
        panel1.add(bottomPanelSerchName);

        panel2.add(tagLogin);
        panel2.add(loginBox);
        panel2.add(bottomPanelSerchLogin);

        panel3.add(tagType);
        panel3.add(typeBox);
        panel3.add(bottomPanelSerchType);

        geralPanel.add(panel1);
        geralPanel.add(panel2);
        geralPanel.add(panel3);



        panelOut = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        outBottom = new JButton("Voltar");
        outBottom.setActionCommand("voltar");
        outBottom.addActionListener(this);
        outBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelOut.add(outBottom);

        JPanel list = new MenuListMaster(DadosUtilizadores.listarTodosUtilizadores());
        JPanel geralPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        geralPanelLeft.add(geralPanel);

        container.add(titlePanel, BorderLayout.NORTH);
        container.add(list, BorderLayout.CENTER);
        container.add(geralPanelLeft, BorderLayout.SOUTH);


        this.setSize(990, 490);
        this.setLocationRelativeTo(null);
        this.setTitle("Listagens e Pesquisas");
        this.setResizable(true);
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Metodo responsavel pela gestao de eventos da classe
     * @param e eventos gerado pela classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        switch (op){

            case "pesquisaNome": if(nameField.getText().equals("")){
                                    Aviso.showMessage("Campo Vazio", "Aviso", "error");
                                }else{
                                    MenuListMaster.createFrame(DadosUtilizadores.listarUtilizadoresCondicao("NOME_UTILIZADOR LIKE '%"+nameField.getText()+"%'"));
                                }break;
            case "pesquisaLogin": if(loginField.getText().equals("")){
                                         Aviso.showMessage("Campo Vazio", "Aviso", "error");
                                  }else{
                                        MenuListMaster.createFrame(DadosUtilizadores.listarUtilizadoresCondicao("LOGIN_UTILIZADOR = '"+loginField.getText()+"'"));
                                    }break;
            case "pesquisaTipo": MenuListMaster.createFrame(DadosUtilizadores.listarUtilizadoresCondicao("TIPO_UTILIZADOR = '"+type+"'"));
                                    break;
            default: Aviso.showMessage("Errado", "Aviso", "error"); break;
        }

    }

    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
            if(typeUser.getSelectedIndex() == 0) {
                type = "Funcionario";
            }else if(typeUser.getSelectedIndex() == 1){
                type = "Gestor";
            }else {
                type = "Cliente";
            }
        }
    }
}
