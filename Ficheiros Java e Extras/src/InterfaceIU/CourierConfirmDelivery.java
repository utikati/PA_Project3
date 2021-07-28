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
import java.util.Date;

/**
 * Classe responsavel grafica onde o estafeta confirma a entrega da encomenda
 * @author Jorge Martins e Rodrigo Duro
 */
public class CourierConfirmDelivery extends JFrame implements ActionListener {

    private Container cont;
    private JPanel mainPanel, panelTop, bottonSerchPanel, southPanel, bottonDeniedPanel;
    private JTextField orderField;
    private JButton searchOrd, searchDenied;

    /**
     * Construtor que impulsiona a construção grafica do interface com os seus eventos(possibilidades)
     */
    public CourierConfirmDelivery() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        cont = getContentPane();
        cont.setLayout(new BorderLayout());
        mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop.add(new JLabel("Lista de Entregas"));

        JPanel tagOrder = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagOrder.add(new JLabel("Identificador"));


        JPanel orderBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        orderField = new JTextField(15);
        orderField.setToolTipText("Insira o Identificador");
        orderBox.add(orderField);


        bottonSerchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchOrd = new JButton("Confirmar Entrega");
        searchOrd.setActionCommand("confirmarEntrega");
        searchOrd.addActionListener(this);
        searchOrd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonSerchPanel.add(searchOrd);

        mainPanel.add(new ListMasterOrder(DadosEncomendas.listarEncomendasCondicao(" WHERE FUN_ID_UTILIZADOR2 = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+" AND ESTADO_ENCOMENDA = 'em transporte' ")));


        southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.add(tagOrder);
        southPanel.add(orderBox);
        southPanel.add(bottonSerchPanel);



        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(mainPanel, BorderLayout.CENTER);
        cont.add(southPanel, BorderLayout.SOUTH);

        this.setSize(950, 490);
        this.setLocationRelativeTo(null);
        this.setTitle("Entrega a Decorrer");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * Metodo que gerencia os eventos da classe
     * @param e evento criado dentro da classe
     */
        @Override
    public void actionPerformed(ActionEvent e) {
            String op = e.getActionCommand();
        switch (op){
            case "confirmarEntrega":
                if(orderField.getText().equals("")){
                    Aviso.showMessage("Campo Vazio", "Aviso", "error");
                }else{
                    String identityOrder = orderField.getText();
                    Encomenda eOrder = DadosEncomendas.objEncomenda(identityOrder);
                    if(DadosEncomendas.idEncomenda(identityOrder) > 0) {
                        if(eOrder.getEstado().equals("em transporte") && eOrder.getIdEstafeta() == Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login)) ) {
                            eOrder.setEstado("entregue");
                            eOrder.setDataEntrega(new Date());
                            DadosEncomendas.updateEncomenda(eOrder);
                            DadosNotificacao.addNotification("A sua encomenda com o identificador: "+eOrder.getIdentificadorEncomenda()+" foi entregue!!!",DadosUtilizadores.listarUtilizadoresCondicao(" ID_UTILIZADOR = '"+eOrder.getIdCliente()+"'"));
                            Aviso.showMessage("Estado de Encomenda actualizado para Entregue", "Aviso", "info");
                            this.dispose();
                            CourierConfirmDelivery courierConfirmDelivery = new CourierConfirmDelivery();
                        }else {
                            Aviso.showMessage("Erro na insercao do Identificador, observe se é o identificador presente na Tabela", "Aviso", "error");
                        }
                    }else {
                        Aviso.showMessage("Identificador não existe", "Aviso", "error");
                    }
                }
            }
        }

    }

