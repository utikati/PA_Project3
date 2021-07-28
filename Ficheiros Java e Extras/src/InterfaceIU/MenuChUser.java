package InterfaceIU;

import AcessoBD.DadosUtilizadores;
import Aviso.Aviso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe responsavel pela alteracao de dados de Utilizador
 * @author Jorge Martins e Rodrigo Duro
 */
public class MenuChUser extends JFrame implements ActionListener {

    private Container container;
    private JPanel titlePanel, geralPanel, nameField, panelOut, searchPanel;
    private JTextField LoginSSField;
    private JButton bottomSerchLogin, outBottom;

    /**
     * Construtor da classe com os elementos para construcao grafica da classe
     */
    public MenuChUser(){
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        container = getContentPane();
        container.setLayout(new BorderLayout());

        titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(new JLabel("Alterar Dados de um Utilizador"));
        //grids que vao ser usadas
        GridLayout grids = new GridLayout(1, 3);
        grids.setHgap(2); //espaçamento horizontal entre as "linhas"
        grids.setVgap(2);

        geralPanel = new JPanel(grids);

        //FINAL DA CONSTRUÇÃO DAS GRIDS QUE DEPOIS VAO SER PREENCHIDAS COM OS PAINEIS

        //criar labels e fields
        //ETIQUETAS PARA OS CAMPOS
        JPanel tagName = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagName.add(new JLabel("Login para Alterar"));

        //CAMPOS A PREENCHER

        JPanel nameBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        LoginSSField = new JTextField(15);
        LoginSSField.setToolTipText("Insira o Login de Utilizador");
        nameBox.add(LoginSSField);

        //BOTOES DE UPDATE DOS DADOS
        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomSerchLogin = new JButton("Pesquisa Login");
        bottomSerchLogin.setActionCommand("pesquisaLogin");
        bottomSerchLogin.addActionListener(this);
        bottomSerchLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchPanel.add(bottomSerchLogin);

        geralPanel.add(tagName);
        geralPanel.add(nameBox);
        geralPanel.add(searchPanel);

        container.add(titlePanel, BorderLayout.NORTH);
        container.add(geralPanel, BorderLayout.CENTER);
        container.add(new JPanel(), BorderLayout.SOUTH);


        this.setSize(590, 190);
        this.setLocationRelativeTo(null);
        this.setTitle("Alterar Dados");
        this.setResizable(true);
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Metodo de gestao dos eventos da classe
     * @param e evento gerado pela classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        switch (op){
            case "pesquisaLogin": if(LoginSSField.getText().equals("")){
                                        Aviso.showMessage("Campo Vazio", "Aviso", "error");
                                    }else{
                                        if(DadosUtilizadores.verificaLogin(LoginSSField.getText()).equals("")){
                                            Aviso.showMessage("Login não existe", "Aviso", "error");
                                        }else{
                                            FormChUser formChUser = new FormChUser(LoginSSField.getText()); this.dispose();
                                        }
                                    } break;
            default :Aviso.showMessage("Nao implementado", "Aviso", "error"); break;
        }

    }
}
