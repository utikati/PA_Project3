package InterfaceIU;

import AcessoBD.DadosEncomendas;
import AcessoBD.DadosUtilizadores;
import Aviso.Aviso;
import DadosEstaticos.DadosStatic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Metodo responsavel pela listagem das encomendas
 * @author Jorge Martins e Rodrigo Duro
 */
public class ListSearchOrders extends JFrame implements ActionListener, ItemListener {

    private Container cont;
    private JPanel mainPanel, panelTop, bottonSerchPanel, southPanel, bottonCreatDatePanel, bottonStatePanel, p1, p2, p3;
    private JTextField orderField, CreatDateField;
    private JButton searchOrd, searchCreatDate, searchState;
    private JComboBox stateField;
    private String state = "iniciada";
    private String type;
    private int idUser;

    /**
     * Construtor com elementos para construir interface grafica
     */
    public ListSearchOrders() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        cont = getContentPane();
        cont.setLayout(new BorderLayout());
        mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop.add(new JLabel("Lista de Encomendas"));

        JPanel tagOrder = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagOrder.add(new JLabel("Identificador"));

        JPanel orderBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        orderField = new JTextField(15);
        orderField.setToolTipText("Insira Identificador");
        orderBox.add(orderField);


        bottonSerchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchOrd = new JButton("Pesquisar");
        searchOrd.setActionCommand("pesquisaIdentificador");
        searchOrd.addActionListener(this);
        searchOrd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonSerchPanel.add(searchOrd);
//---------------------------------------------------------
        JPanel tagCreatDate = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagCreatDate.add(new JLabel("Data Criacao dd/MM/yyyy"));

        JPanel CreatDateBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        CreatDateField = new JTextField(15);
        CreatDateField.setToolTipText("Insira data no formato dia/mes/ano - 01/01/1970");
        CreatDateBox.add(CreatDateField);


        bottonCreatDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchCreatDate = new JButton("Pesquisar");
        searchCreatDate.setActionCommand("pesquisaData");
        searchCreatDate.addActionListener(this);
        searchCreatDate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonCreatDatePanel.add(searchCreatDate);
//---------------------------------------------------------------------------------
        JPanel tagState = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagState.add(new JLabel("Estado"));

        JPanel stateBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        String[] varTypes = {"iniciada", "aceite", "preparada", "aguarda entrega", "em transporte", "entregue", "confirmada", "rejeitada"};
        stateField = new JComboBox<String>(varTypes);
        stateField.setToolTipText("Escolha o estado da encomenda a pesquisar");
        stateField.addItemListener(this);
        stateBox.add(stateField);

        bottonStatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchState = new JButton("Pesquisar");
        searchState.setActionCommand("pesquisaEstado");
        searchState.addActionListener(this);
        searchState.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonStatePanel.add(searchState);
//-----------------------------------------------------------------------

        type = DadosStatic.Tipo;
        idUser = DadosUtilizadores.getIdUser(DadosStatic.Login);

        switch (type){
            case "Cliente" : mainPanel.add(new ListMasterOrder(DadosEncomendas.listarEncomendasCondicao("WHERE CLI_ID_UTILIZADOR = "+ idUser +" ;"))); break;
            case "Funcionario": if(DadosStatic.especializacao.equals("Armazenista")){
                                    mainPanel.add(new ListMasterOrder(DadosEncomendas.listarEncomendasCondicao("WHERE FUN_ID_UTILIZADOR = "+ idUser +" ;")));
                                }else{
                                    mainPanel.add(new ListMasterOrder(DadosEncomendas.listarEncomendasCondicao("WHERE FUN_ID_UTILIZADOR2 = "+ idUser +" ;")));
                                }
        }

        p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        p1.add(tagOrder);
        p1.add(orderBox);
        p1.add(bottonSerchPanel);

        p2.add(tagCreatDate);
        p2.add(CreatDateBox);
        p2.add(bottonCreatDatePanel);

        p3.add(tagState);
        p3.add(stateBox);
        p3.add(bottonStatePanel);

        southPanel = new JPanel(new GridLayout(3, 1));
        southPanel.add(p1);
        southPanel.add(p2);
        southPanel.add(p3);



        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(mainPanel, BorderLayout.CENTER);
        cont.add(southPanel, BorderLayout.SOUTH);

        this.setSize(950, 490);
        this.setLocationRelativeTo(null);
        this.setTitle("Encomendas");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * Metodo para verificar se a data esta no formato correcto
     * @param dat string com a data inserida pelo utilizador
     * @return booleano com resultado da verificacao
     */
    private boolean checkDate(String dat) {
        Date dataInicio = new Date();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        formatoData.setLenient(false); //para verificar se o formato da data é valido tipo 30/02/2021
        try {
            dataInicio = formatoData.parse(dat);
        } catch (ParseException e) {
            try {
                dataInicio = formatoData.parse("01/01/1970");
            } catch (ParseException e2) {
                e.printStackTrace();
            }
            return false; //data nao aceite
        }
        try {
            Date antes = formatoData.parse("01/01/1970");
            Date agora = new Date();
            if (dataInicio.after(antes) && dataInicio.before(agora)) {
                return true; //unica forma de ser aceite
            } else {
                return false; //nao aceite
            }
        } catch (ParseException e3) {
            e3.printStackTrace();
        }
        return false;
    }

    /**
     * Serve para transformar a escrita de data europeia para a da Base de Dados americana. Apenas é usada apos o uso do check date.
     *
     * @param date
     * @return String com a data no formato americano
     */
    private String transformDate(String date) {
        String sendDate = "";
        String[] split = date.split("/");
        sendDate = split[2] + "-" + split[1] + "-" + split[0];
        return sendDate;
    }

    /**
     * Metodo responsavel por verificar se a ComboBox muda o seu estado e assim vai mudando o valor da variavel state
     * @param e evento da de mudanca do estado da comboBox
     */
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            state = e.getItem().toString();
        }
    }

    /**
     * Metodo de gestao de eventos da classe
     * @param e eventos gerados pelas accoes do utilizador
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        switch (op) {
            case "pesquisaIdentificador":
                if (orderField.getText().equals("")) {
                    Aviso.showMessage("Campo Vazio", "Aviso", "error");
                } else {
                    switch (type){
                        case "Cliente" : ListMasterOrder.createFrame(DadosEncomendas.listarEncomendasCondicao("WHERE IDENTIFICADOR_ENCOMENDA = " + orderField.getText() + " AND CLI_ID_UTILIZADOR = "+ idUser +" ;")); break;
                        case "Funcionario": if(DadosStatic.especializacao.equals("Armazenista")){
                                                ListMasterOrder.createFrame(DadosEncomendas.listarEncomendasCondicao("WHERE IDENTIFICADOR_ENCOMENDA = " + orderField.getText() + " AND FUN_ID_UTILIZADOR = "+ idUser +" ;"));
                                            }else{
                                                ListMasterOrder.createFrame(DadosEncomendas.listarEncomendasCondicao("WHERE IDENTIFICADOR_ENCOMENDA = " + orderField.getText() + " AND FUN_ID_UTILIZADOR2 = "+ idUser +" ;"));
                        }break;
                    }
                }
                break;

            case "pesquisaData":
                if (CreatDateField.getText().equals("")) {
                    Aviso.showMessage("Campo Vazio", "Aviso", "error");
                } else {
                    if (checkDate(CreatDateField.getText())) {
                        String send = transformDate(CreatDateField.getText());
                        switch (type){
                            case "Cliente": ListMasterOrder.createFrame(DadosEncomendas.listarEncomendasCondicao("WHERE DATACRIACAO_ENCOMENDA = '" + send + "' AND CLI_ID_UTILIZADOR = "+ idUser +" ;")); break; //tem de se usar as plicas porque e data enviada em string
                            case "Funcionario": if(DadosStatic.especializacao.equals("Armazenista")){
                                ListMasterOrder.createFrame(DadosEncomendas.listarEncomendasCondicao("WHERE DATACRIACAO_ENCOMENDA = '" + send + "' AND FUN_ID_UTILIZADOR = "+ idUser +" ;")); //tem de se usar as plicas porque e data enviada em string
                            }else{
                                ListMasterOrder.createFrame(DadosEncomendas.listarEncomendasCondicao("WHERE DATACRIACAO_ENCOMENDA = '" + send + "' AND FUN_ID_UTILIZADOR2 = "+ idUser +" ;")); //tem de se usar as plicas porque e data enviada em string
                            }break;
                        }
                    } else {
                        Aviso.showMessage("Data nao valida", "Aviso", "error");
                    }
                }
                break;

            case "pesquisaEstado":
                switch (type){
                    case "Cliente": ListMasterOrder.createFrame(DadosEncomendas.listarEncomendasCondicao("WHERE ESTADO_ENCOMENDA = '" + state + "' AND CLI_ID_UTILIZADOR = "+ idUser +" ;")); break;
                    case "Funcionario": if(DadosStatic.especializacao.equals("Armazenista")){
                        ListMasterOrder.createFrame(DadosEncomendas.listarEncomendasCondicao("WHERE ESTADO_ENCOMENDA = '" + state + "' AND FUN_ID_UTILIZADOR = "+ idUser +" ;"));
                    }else{
                        ListMasterOrder.createFrame(DadosEncomendas.listarEncomendasCondicao("WHERE ESTADO_ENCOMENDA = '" + state + "' AND FUN_ID_UTILIZADOR2 = "+ idUser +" ;"));
                    } break;
                }
                break;


                }

        }

    }


