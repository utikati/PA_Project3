package InterfaceIU;

import AcessoBD.DadosEncomendas;
import AcessoBD.DadosNotificacao;
import AcessoBD.DadosPedido;
import AcessoBD.DadosUtilizadores;
import Aviso.Aviso;
import DadosEstaticos.DadosStatic;
import PedidosNotificacoes.Pedido;
import Produtos.Encomenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe responsavel pelo Estafeta aceitar ou rejeitar o pedido de entrega de encomenda
 * @author Jorge Martins e Rodrigo Duro
 */
public class CourierAceptRequest extends JFrame implements ActionListener {

    private Container cont;
    private JPanel mainPanel;
    private JPanel bottonSerchPanel;
    private JPanel southPanel;
    private JPanel bottonDeniedPanel;
    private JTextField orderField;
    private JButton searchOrd, searchDenied;


    /**
     * Construtor da classe grafica que impulsiona a construcao grafica da interface
     */
    public CourierAceptRequest() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        cont = getContentPane();
        cont.setLayout(new BorderLayout());
        mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop.add(new JLabel("Lista de Pedidos"));

        JPanel tagOrder = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagOrder.add(new JLabel("ID Pedido"));


        JPanel orderBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        orderField = new JTextField(15);
        orderField.setToolTipText("Insira o ID do Pedido");
        orderBox.add(orderField);


        bottonSerchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchOrd = new JButton("Aceitar Pedido");
        searchOrd.setActionCommand("aceitarPedido");
        searchOrd.addActionListener(this);
        searchOrd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonSerchPanel.add(searchOrd);

        bottonDeniedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchDenied = new JButton("Rejeitar Pedido");
        searchDenied.setActionCommand("rejeitarPedido");
        searchDenied.addActionListener(this);
        searchDenied.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonDeniedPanel.add(searchDenied);


        mainPanel.add(new ListMasterResquestOrder(DadosPedido.listarPedidosCondicao(" WHERE FUN_ID_UTILIZADOR = "+ DadosUtilizadores.verificaLogin(DadosStatic.Login)+" AND ESTADO_PEDIDO = 'activo' ")));


        southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.add(tagOrder);
        southPanel.add(orderBox);
        southPanel.add(bottonSerchPanel);
        southPanel.add(bottonDeniedPanel);


        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(mainPanel, BorderLayout.CENTER);
        cont.add(southPanel, BorderLayout.SOUTH);

        this.setSize(950, 490);
        this.setLocationRelativeTo(null);
        this.setTitle("Pedidos de Entrega");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * Classe responsavel por estar a escuta de accoes do utilizador
     * @param e evento gerado pelo utilizador
     */
        @Override
    public void actionPerformed(ActionEvent e) {
            int id = 0;
            boolean state = true;
            String op = e.getActionCommand();
        switch (op){
            case "aceitarPedido":
                try{
                   id = Integer.parseInt(orderField.getText());
                }catch(NumberFormatException nfe){
                    Aviso.showMessage("Nao e Inteiro", "Aviso", "error");
                    state = false;
                }
                if(state) {
                    if (DadosPedido.verificaPedido(id) > 0 && DadosPedido.getPedidoBd(id).getEstado().equals("activo")) {
                        if (DadosPedido.getPedidoBd(id).getIdFuncionario() == Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login))) {
                            if (DadosPedido.verificarDisponibilidadeEstafeta(Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login))) > 0) {
                                Aviso.showMessage("Só pode aceitar uma encomenda quando entregar a que está a decorrer.", "Aviso", "error");
                            } else {
                                Pedido request = DadosPedido.getPedidoBd(id);
                                Encomenda eOrder = DadosEncomendas.objEncomendaID(request.getIdEncomenda());
                                eOrder.setIdEstafeta(Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login)));
                                eOrder.setEstado("em transporte");
                                DadosEncomendas.updateEncomenda(eOrder);
                                request.setEstado("inactivo");
                                DadosPedido.updateEstadoPedido(request);
                                DadosNotificacao.addNotification("A encomenda com o identificador: "+eOrder.getIdentificadorEncomenda()+" foi aceite pelo estafeta!!!",DadosUtilizadores.listarUtilizadoresCondicao(" ID_UTILIZADOR = '"+eOrder.getIdArmazenista()+"'"));

                                Aviso.showMessage("Pedido Aceite, Encomenda esta na sua responsabilidade de Entrega", "Aviso", "info");
                                this.dispose();
                                CourierAceptRequest courierAceptRequest = new CourierAceptRequest();
                            }
                        }
                    } else {
                        Aviso.showMessage("Erro - Pedido nao consta em lista de pedidos activos", "Aviso", "error");
                    }
                }break;

            case "rejeitarPedido":
                try{
                    id = Integer.parseInt(orderField.getText());
                }catch(NumberFormatException nfe) {
                    Aviso.showMessage("Nao e Inteiro", "Aviso", "error");
                    state = false;
                }
                    if(state) {
                        if(DadosPedido.verificaPedido(id) > 0 && DadosPedido.getPedidoBd(id).getEstado().equals("activo")) {
                            if(DadosPedido.getPedidoBd(id).getIdFuncionario() == Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login))) {
                                Pedido resquest = DadosPedido.getPedidoBd(id);
                                Encomenda eOrder = DadosEncomendas.objEncomendaID(resquest.getIdEncomenda());
                                eOrder.setEstado("preparada");
                                DadosEncomendas.updateEncomenda(eOrder);
                                resquest.setEstado("rejeitado");
                                DadosPedido.updateEstadoPedido(resquest);
                                DadosNotificacao.addNotification("A encomenda com o identificador: "+eOrder.getIdentificadorEncomenda()+" foi rejeitada pelo estafeta!!!",DadosUtilizadores.listarUtilizadoresCondicao(" ID_UTILIZADOR = '"+eOrder.getIdArmazenista()+"'"));
                                Aviso.showMessage("Pedido foi Rejeitado, Armazenista será notificado", "Aviso", "info");
                                this.dispose();
                                CourierAceptRequest courierAceptRequest = new CourierAceptRequest();
                            }
                        }else {
                            Aviso.showMessage("Erro - Pedido nao consta em lista de pedidos activos", "Aviso", "error");
                        }
                    } break;
                }
        }

    }

