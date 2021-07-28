package InterfaceIU;

import AcessoBD.DadosEncomendas;
import AcessoBD.DadosNotificacao;
import AcessoBD.DadosUtilizadores;
import Aviso.Aviso;
import DadosEstaticos.DadosStatic;
import Produtos.Encomenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe grafica responsavel pelo cliente confirmar a entrega da sua encomenda
 * @author Jorge Martins e Rodrigo Duro
 */
public class ClientConfirmDelivery extends JFrame implements ActionListener {

    private Container cont;
    private JPanel mainPanel, panelTop, bottonSerchPanel, southPanel, bottonDeniedPanel;
    private JTextField orderField;
    private JButton searchOrd, searchDenied;


    /**
     * Construtor que imupulsiona a construcao da interface grafica
     */
    public ClientConfirmDelivery() {
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
        orderField.setToolTipText("Insira o Identificador da Encomenda");
        orderBox.add(orderField);


        bottonSerchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchOrd = new JButton("Confirmar Entrega");
        searchOrd.setActionCommand("confirmarEntrega");
        searchOrd.addActionListener(this);
        searchOrd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonSerchPanel.add(searchOrd);

        mainPanel.add(new ListMasterOrder(DadosEncomendas.listarEncomendasCondicao(" WHERE CLI_ID_UTILIZADOR = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+" AND ESTADO_ENCOMENDA = 'entregue' ")));


        southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.add(tagOrder);
        southPanel.add(orderBox);
        southPanel.add(bottonSerchPanel);



        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(mainPanel, BorderLayout.CENTER);
        cont.add(southPanel, BorderLayout.SOUTH);

        this.setSize(950, 490);
        this.setLocationRelativeTo(null);
        this.setTitle("Encomendas Entregues");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * Metodo responsavel por escutar os eventos da classe
     * @param e evento gerado por uma accao
     */
        @Override
    public void actionPerformed(ActionEvent e) {
            String op = e.getActionCommand();
        switch (op){
            case "confirmarEntrega":
                if(orderField.getText().equals("")){
                    Aviso.showMessage("Campo Vazio", "Aviso", "error");
                }else{
                    String identyOrder = orderField.getText();
                    Encomenda eOrder = DadosEncomendas.objEncomenda(identyOrder);
                    if(DadosEncomendas.idEncomenda(identyOrder) > 0) {
                        if(eOrder.getEstado().equals("entregue") && eOrder.getIdCliente() == Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login)) ) {
                            eOrder.setEstado("confirmada");
                            DadosEncomendas.updateEncomenda(eOrder);
                            Aviso.showMessage("Confirmou a entrega da Encomenda", "Aviso", "info");
                            DadosNotificacao.addNotification("O cliente "+DadosUtilizadores.getLogin(eOrder.getIdCliente())+" comfirmou a encomenda com o identificador " +eOrder.getIdentificadorEncomenda() + " !!!", DadosUtilizadores.listarUtilizadoresCondicao(" ESTADO_UTILIZADOR = 'Gestor' "));
                            this.dispose();
                            ClientConfirmDelivery clientConfirmDelivery = new ClientConfirmDelivery();
                        }else {
                            Aviso.showMessage("Erro - Encomenda não se encontra em estado para Confirmacao", "Aviso", "error");
                        }
                    }else {
                        Aviso.showMessage("Erro - Identificador não existe", "Aviso", "error");
                    }
                }
            }
        }

    }

