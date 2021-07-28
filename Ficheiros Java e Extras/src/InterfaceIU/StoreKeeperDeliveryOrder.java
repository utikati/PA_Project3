package InterfaceIU;

import AcessoBD.DadosEncomendas;
import AcessoBD.DadosNotificacao;
import AcessoBD.DadosPedido;
import AcessoBD.DadosUtilizadores;
import DadosEstaticos.DadosStatic;
import PedidosNotificacoes.Pedido;
import Produtos.Encomenda;
import Aviso.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe responsavel pelo armazenista realizar um pedido de entrega a um estafeta
 * @author Jorge Martins e Rodrigo Duro
 */
public class StoreKeeperDeliveryOrder extends JFrame implements ActionListener {

    private Container cont;
    private JPanel mainPanel, panelTop, bottonSerchPanel, southPanel, bottonVPanel;
    private JTextField orderField, courierField;
    private JButton searchOrd, searchFreeCourier;

    // TODO: 25/06/2021 Necessário criar uma notificacao para o estafeta que recebe o pedido de entrega por parte do armazenista

    /**
     * Construtor da classe com os elementos para a construcao da interface grafica
     */
    public StoreKeeperDeliveryOrder(){
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        cont = getContentPane();
        cont.setLayout(new BorderLayout());
        mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop.add(new JLabel("Lista de Encomendas Preparadas"));

        JPanel tagOrder = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagOrder.add(new JLabel("Identificador de Encomenda"));

        JPanel tagStore = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagStore.add(new JLabel("Login Estafeta"));

        JPanel orderBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        orderField = new JTextField(15);
        orderField.setToolTipText("Identificador de Encomenda");
        orderBox.add(orderField);

        JPanel storeBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        courierField = new JTextField(15);
        courierField.setToolTipText("Login do Estafeta");
        storeBox.add(courierField);

        bottonSerchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchOrd = new JButton("Pedido de Entrega");
        searchOrd.setActionCommand("delegarEncomenda");
        searchOrd.addActionListener(this);
        searchOrd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonSerchPanel.add(searchOrd);

        bottonVPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchFreeCourier = new JButton("Verificar Estafetas Disponiveis");
        searchFreeCourier.setActionCommand("verificarEstafetas");
        searchFreeCourier.addActionListener(this);
        searchFreeCourier.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonVPanel.add(searchFreeCourier);


        mainPanel.add(new ListMasterOrder(DadosEncomendas.listarEncomendasCondicao("WHERE ESTADO_ENCOMENDA = 'preparada'")));

        southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.add(tagOrder);
        southPanel.add(orderBox);
        southPanel.add(tagStore);
        southPanel.add(storeBox);
        southPanel.add(bottonSerchPanel);
        southPanel.add(bottonVPanel);



        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(mainPanel, BorderLayout.CENTER);
        cont.add(southPanel, BorderLayout.SOUTH);

        this.setSize(1050, 490);
        this.setLocationRelativeTo(null);
        this.setTitle("Encomendas Preparadas");
        this.setResizable(true);
        this.setVisible(true);

    }

    /**
     * Metodo responsavel pela gestao de eventos
     * @param e evento gerado pela classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        switch (op){
            case "delegarEncomenda": String id = orderField.getText(); //identificador da encomenda não é o ID da base de dados!!!!
                if(DadosEncomendas.idEncomenda(id) > 0) {
                    Encomenda encomenda = DadosEncomendas.objEncomenda(id);
                    String login = courierField.getText();
                    if(!DadosUtilizadores.verificaLogin(login).equals("") &&
                            DadosUtilizadores.pesquisaLogin(login).getTipo().equals("Funcionario") &&
                            DadosUtilizadores.pesquisaFuncionarios(login).getEspecializacao().equals("Estafeta")) {
                        Pedido pedido = new Pedido(Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login)), encomenda.getIdEncomenda(), encomenda.getIdCliente(), Integer.parseInt(DadosUtilizadores.verificaLogin(login)), "Pedido de Encomenda para Entrega", "activo");
                        DadosPedido.adicionaPedido(pedido);
                        encomenda.setEstado("aguarda entrega");
                        DadosEncomendas.updateEncomenda(encomenda);
                        Aviso.showMessage("Pedido realizado com sucesso", "Aviso", "info");
                        DadosNotificacao.addNotification("Tem um novo pedido de Entrega", DadosUtilizadores.listarUtilizadoresCondicao(" LOGIN_UTILIZADOR = '"+login+"'"));
                        this.dispose();
                        StoreKeeperDeliveryOrder storeKeeperDeliveryOrder = new StoreKeeperDeliveryOrder();
                    }
                    else {
                        Aviso.showMessage("Erro - Login de Estafeta nao existe", "Aviso", "error");
                    }
                }else {
                    Aviso.showMessage("Identificador nao existe", "Aviso", "error");
                } break;
            case "verificarEstafetas": if(orderField.getText().equals("")){
                                            Aviso.showMessage("Insira o Identificador de encomenda para verificar Estafetas Disponiveis para a mesma", "Aviso", "error");
                                        }else{
                                            String identificator = orderField.getText();
                                            if(DadosEncomendas.idEncomenda(identificator) > 0){
                                                int idOrder = DadosEncomendas.idEncomenda(identificator);
                                                ListMasterEmployee.createFrame(DadosPedido.listarEstafetasDisponiveis(idOrder));
                                            }else {
                                                Aviso.showMessage("Identificador nao existe", "Aviso", "error");
                                            } break;
                                        }
        }

    }
}
