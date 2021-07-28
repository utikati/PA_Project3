package InterfaceIU;

import AcessoBD.DadosEncomendas;
import AcessoBD.DadosNotificacao;
import AcessoBD.DadosUtilizadores;
import Aviso.Aviso;

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
 * Classe responsavel pela listagem e pesquisa do Admin/Gestor das encomendas no sistema
 * @author Jorge Martins e Rodrigo Duro
 */
public class AdminListSearchOrders extends JFrame implements ActionListener, ItemListener {

    private Container cont;
    private JPanel mainPanel, panelTop, bottonSerchPanel, southPanel, bottonCreatDatePanel, bottonStatePanel, bottonClientPanel, bottonTimePanel, p1, p2, p3, p4, p5;
    private JTextField orderField, CreatDateField, clientField, startField, endField;
    private JButton searchOrd, searchCreatDate, searchState, searchClient, searchTime;
    private JComboBox stateField;
    private String state = "iniciada";

    /**
     * Construtor da classe que impulsiona que a mesma seja grafica.
     */
    public AdminListSearchOrders() {
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
        orderField.setToolTipText("Insira Identificador da Encomenda");
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
        CreatDateField.setToolTipText("Insira Data de Criacao por dia/mes/ano - 01/01/1970");
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
        stateField.setToolTipText("Escolha um estado a pesquisar");
        stateField.addItemListener(this);
        stateBox.add(stateField);

        bottonStatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchState = new JButton("Pesquisar");
        searchState.setActionCommand("pesquisaEstado");
        searchState.addActionListener(this);
        searchState.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonStatePanel.add(searchState);
//-----------------------------------------------------------------------

        JPanel tagClient = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagClient.add(new JLabel("Login Cliente"));

        JPanel clientBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        clientField = new JTextField(15);
        clientField.setToolTipText("Insira o Login do Cliente");
        clientBox.add(clientField);


        bottonClientPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchClient = new JButton("Pesquisar");
        searchClient.setActionCommand("pesquisaCliente");
        searchClient.addActionListener(this);
        searchClient.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonClientPanel.add(searchClient);
//-----------------------------------------------------------------------------------------

        JPanel tagIntervalTime = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagIntervalTime.add(new JLabel("Data de Inicio"));

        JPanel startTimeBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        startField = new JTextField(15);
        startField.setToolTipText("Insira a Data de Inicio do Intervalo a pesquisar");
        startTimeBox.add(startField);

        JPanel tagIntTime = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagIntTime.add(new JLabel("Data de Fim"));

        JPanel endTimeBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        endField = new JTextField(15);
        endField.setToolTipText("nsira a Data do Fim do Intervalo a pesquisar");
        endTimeBox.add(endField);


        bottonTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchTime = new JButton("Pesquisar Intervalo");
        searchTime.setActionCommand("pesquisaTempo");
        searchTime.addActionListener(this);
        searchTime.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonTimePanel.add(searchTime);
//-----------------------------------------------------------------------------------------

        mainPanel.add(new ListMasterOrder(DadosEncomendas.listarEncomendasCondicao("")));
        p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p5 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        p1.add(tagOrder);
        p1.add(orderBox);
        p1.add(bottonSerchPanel);

        p2.add(tagCreatDate);
        p2.add(CreatDateBox);
        p2.add(bottonCreatDatePanel);

        p3.add(tagState);
        p3.add(stateBox);
        p3.add(bottonStatePanel);

        p4.add(tagClient);
        p4.add(clientBox);
        p4.add(bottonClientPanel);

        p5.add(tagIntervalTime);
        p5.add(startTimeBox);
        p5.add(tagIntTime);
        p5.add(endTimeBox);
        p5.add(bottonTimePanel);


        southPanel = new JPanel(new GridLayout(5, 1));
        southPanel.add(p1);
        southPanel.add(p2);
        southPanel.add(p3);
        southPanel.add(p4);
        southPanel.add(p5);


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
     * Metodo responsavel por verificar se a data foi inserida da forma correcta para que não haja problemas na inserção na base de dados
     * @param dat parametro string data
     * @return booleano com verificacao
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
     * Metodo responsavel por estar sempre a escuta a verificas as modificaçoes da comboBox, assim dando o seu valor para a variavel state
     * @param e evento gerado pela mudança na comboBox
     */
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            state = e.getItem().toString();
        }
    }

    /**
     * Metodo responsavel pela gestao de eventos
     * @param e evento gerado pela classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        switch (op) {
            case "pesquisaIdentificador":
                if (orderField.getText().equals("")) {
                    Aviso.showMessage("Campo Vazio", "Aviso", "error");
                } else {
                    ListMasterOrder.createFrame(DadosEncomendas.listarEncomendasCondicao("WHERE IDENTIFICADOR_ENCOMENDA = " + orderField.getText() + " ;"));
                }
                break;

            case "pesquisaData":
                if (CreatDateField.getText().equals("")) {
                    Aviso.showMessage("Campo Vazio", "Aviso", "error");
                } else {
                    if (checkDate(CreatDateField.getText())) {
                        String send = transformDate(CreatDateField.getText());
                        ListMasterOrder.createFrame(DadosEncomendas.listarEncomendasCondicao("WHERE DATACRIACAO_ENCOMENDA = '" + send + "' ;")); //tem de se usar as plicas porque e data enviada em string
                    } else {
                        Aviso.showMessage("Data nao valida", "Aviso", "error");
                    }
                }
                break;
            case "pesquisaEstado":
                ListMasterOrder.createFrame(DadosEncomendas.listarEncomendasCondicao("WHERE ESTADO_ENCOMENDA = '" + state + "' ;"));
                break;

            case "pesquisaCliente":
                if (clientField.getText().equals("")) {
                    Aviso.showMessage("Campo Vazio", "Aviso", "error");
                } else {
                    if (DadosUtilizadores.verificaLogin(clientField.getText()).equals("")) {
                        Aviso.showMessage("Login nao existe", "Aviso", "error");
                    } else {
                        int id = DadosUtilizadores.getIdUser(clientField.getText());
                        ListMasterOrder.createFrame(DadosEncomendas.listarEncomendasCondicao("WHERE CLI_ID_UTILIZADOR = " + id + " ;"));
                    }

                }
                break;
            case "pesquisaTempo":
                if (startField.getText().equals("") || endField.getText().equals("")) {
                    Aviso.showMessage("Campo Vazio", "Aviso", "error");
                } else {
                    if (checkDate(startField.getText()) && checkDate(endField.getText())) {
                        String date1 = transformDate(startField.getText());
                        String date2 = transformDate(endField.getText());
                        String sendDate = "'" + date1 + "' AND '" + date2 + "' ;";
                        ListMasterOrder.createFrame(DadosEncomendas.listarEncomendasCondicao("WHERE DATACRIACAO_ENCOMENDA BETWEEN " + sendDate + " ;"));
                    } else {
                        Aviso.showMessage("Data Invalida insirda dd/MM/yyyy", "Aviso", "error");
                    }
                    break;


                }

        }

    }
}

