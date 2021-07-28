package InterfaceIU;

import DadosEstaticos.DadosStatic;
import Utilizadores.Logs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe responsavel pelo menu de encomendas demonstrado ao utilizador, divido para que cada utilizador apenas tenha acesso ao que é autorizado
 * @author Jorge Martins e Rodrigo Duro
 */
public class MenuOrders extends JFrame implements ActionListener {
    Container cont;
    JPanel panelTop, panelMenu, panelCreateOrder, panelListYourOrder, panelConfirmOrder, panelOut, panelrequestCourier, panelrequestStoreKeeper;
    JButton createOrderBot, listYourOrderBot, confirmOrderBot, outBottom, requestCourierBot, requestStoreKeeperBot;

    /**
     * Construtor de menu que verifica o tipo de utilizador online e assim chama o metodo de construcao da interface grafica para o mesmo
     */
    public MenuOrders() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        String op = DadosStatic.Tipo;
       switch (op){
           case "Gestor": menuAdmin(); break;
           case "Funcionario": if(DadosStatic.especializacao.equals("Armazenista")){
                                    menuStoreKeeper();
                               }else{
                                    menuCourier();
                               } break;
           case "Cliente": menuClient(); break;
       }

    }

    /**
     * Metodo de construcao grafica para o cliente
     */
    private void menuClient(){
        cont = getContentPane();
        cont.setLayout(new BorderLayout());

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER)); //para preencher e ficar no meio
        panelTop.add(new JLabel("Menu Encomendas"));

        panelMenu = new JPanel(new GridLayout(4,1));

        panelCreateOrder = new JPanel(new FlowLayout(FlowLayout.CENTER));
        createOrderBot = new JButton(" Criar Encomenda ");
        createOrderBot.setPreferredSize(new Dimension(180,30));
        createOrderBot.setActionCommand("criarEncomenda");
        createOrderBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        createOrderBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelCreateOrder.add(createOrderBot); //adiciona o botao ao painel

        panelListYourOrder = new JPanel(new FlowLayout(FlowLayout.CENTER));
        listYourOrderBot = new JButton(" Lista/Pesquisa ");
        listYourOrderBot.setPreferredSize(new Dimension(180,30));
        listYourOrderBot.setActionCommand("listaSuaEncomenda");
        listYourOrderBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        listYourOrderBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelListYourOrder.add(listYourOrderBot); //adiciona o botao ao painel

        panelConfirmOrder = new JPanel(new FlowLayout(FlowLayout.CENTER));
        confirmOrderBot = new JButton(" Confirmar Entrega ");
        confirmOrderBot .setPreferredSize(new Dimension(180,30));
        confirmOrderBot.setActionCommand("confirmarEntrega");
        confirmOrderBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        confirmOrderBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelConfirmOrder.add(confirmOrderBot); //adiciona o botao ao painel

        panelOut = new JPanel(new FlowLayout(FlowLayout.CENTER));
        outBottom = new JButton(" Voltar");
        outBottom.setPreferredSize(new Dimension(180,30));
        outBottom.setActionCommand("voltar");
        outBottom.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        outBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelOut.add(outBottom); //adiciona o botao ao painel

        //adicionar os paineis ao painel principal
        panelMenu.add(panelCreateOrder);
        panelMenu.add(panelListYourOrder);
        panelMenu.add(panelConfirmOrder);
        panelMenu.add(panelOut);

        //adicionar os paineis principais ao contentor
        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(panelMenu, BorderLayout.CENTER);
        cont.add(new JPanel(), BorderLayout.SOUTH);

        this.setSize(450,390);
        this.setLocationRelativeTo(null);
        this.setTitle("Encomendas");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * metodo de construcao grafica para o armazenista
     */
    private void menuStoreKeeper(){
        cont = getContentPane();
        cont.setLayout(new BorderLayout());

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER)); //para preencher e ficar no meio
        panelTop.add(new JLabel("Menu Encomendas"));

        panelMenu = new JPanel(new GridLayout(3,1));

        panelrequestCourier = new JPanel(new FlowLayout(FlowLayout.CENTER)); //pedido ao estafeta especificio do armazenista
        requestCourierBot = new JButton(" Pedido de Entrega ");
        requestCourierBot.setPreferredSize(new Dimension(180,30));
        requestCourierBot.setActionCommand("pedidoEntregaEncomenda");
        requestCourierBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        requestCourierBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelrequestCourier.add(requestCourierBot); //adiciona o botao ao painel

        panelListYourOrder = new JPanel(new FlowLayout(FlowLayout.CENTER));
        listYourOrderBot = new JButton(" Lista/Pesquisa ");
        listYourOrderBot.setPreferredSize(new Dimension(180,30));
        listYourOrderBot.setActionCommand("listaSuaEncomenda");
        listYourOrderBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        listYourOrderBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelListYourOrder.add(listYourOrderBot); //adiciona o botao ao painel

        panelOut = new JPanel(new FlowLayout(FlowLayout.CENTER));
        outBottom = new JButton(" Voltar");
        outBottom.setPreferredSize(new Dimension(180,30));
        outBottom.setActionCommand("voltar");
        outBottom.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        outBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelOut.add(outBottom); //adiciona o botao ao painel

        //adicionar os paineis ao painel principal
        panelMenu.add(panelrequestCourier);
        panelMenu.add(panelListYourOrder);
        panelMenu.add(panelOut);

        //adicionar os paineis principais ao contentor
        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(panelMenu, BorderLayout.CENTER);
        cont.add(new JPanel(), BorderLayout.SOUTH);

        this.setSize(450,390);
        this.setLocationRelativeTo(null);
        this.setTitle("Encomendas");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * metodo de construcao grafica para o estafeta
     */
    private void menuCourier(){
        cont = getContentPane();
        cont.setLayout(new BorderLayout());

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER)); //para preencher e ficar no meio
        panelTop.add(new JLabel("Menu Encomendas"));

        panelMenu = new JPanel(new GridLayout(4,1));

        panelrequestCourier = new JPanel(new FlowLayout(FlowLayout.CENTER)); //utilizo as mesmas variaveis mas aqui a accao do botao será diferente
        requestCourierBot = new JButton(" Pedidos de Entrega ");
        requestCourierBot.setPreferredSize(new Dimension(180,30));
        requestCourierBot.setActionCommand("aceitarPedidosEncomenda");
        requestCourierBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        requestCourierBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelrequestCourier.add(requestCourierBot); //adiciona o botao ao painel

        panelListYourOrder = new JPanel(new FlowLayout(FlowLayout.CENTER));
        listYourOrderBot = new JButton(" Lista/Pesquisa ");
        listYourOrderBot.setPreferredSize(new Dimension(180,30));
        listYourOrderBot.setActionCommand("listaSuaEncomenda");
        listYourOrderBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        listYourOrderBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelListYourOrder.add(listYourOrderBot); //adiciona o botao ao painel

        panelConfirmOrder = new JPanel(new FlowLayout(FlowLayout.CENTER));
        confirmOrderBot = new JButton(" Entregar Encomenda ");
        confirmOrderBot .setPreferredSize(new Dimension(180,30));
        confirmOrderBot.setActionCommand("entregarEncomenda");
        confirmOrderBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        confirmOrderBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelConfirmOrder.add(confirmOrderBot); //adiciona o botao ao painel

        panelOut = new JPanel(new FlowLayout(FlowLayout.CENTER));
        outBottom = new JButton(" Voltar");
        outBottom.setPreferredSize(new Dimension(180,30));
        outBottom.setActionCommand("voltar");
        outBottom.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        outBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelOut.add(outBottom); //adiciona o botao ao painel

        //adicionar os paineis ao painel principal
        panelMenu.add(panelrequestCourier);
        panelMenu.add(panelListYourOrder);
        panelMenu.add(panelConfirmOrder);
        panelMenu.add(panelOut);

        //adicionar os paineis principais ao contentor
        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(panelMenu, BorderLayout.CENTER);
        cont.add(new JPanel(), BorderLayout.SOUTH);

        this.setSize(450,390);
        this.setLocationRelativeTo(null);
        this.setTitle("Encomendas");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * metodo de construcao grafica do menu para Gestor
     */
    private void menuAdmin(){
        cont = getContentPane();
        cont.setLayout(new BorderLayout());

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER)); //para preencher e ficar no meio
        panelTop.add(new JLabel("Menu Encomendas"));

        panelMenu = new JPanel(new GridLayout(4,1));

        panelrequestStoreKeeper = new JPanel(new FlowLayout(FlowLayout.CENTER)); //utilizo as mesmas variaveis mas aqui a accao do botao será diferente
        requestStoreKeeperBot = new JButton(" Delegar Encomenda ");
        requestStoreKeeperBot.setPreferredSize(new Dimension(180,30));
        requestStoreKeeperBot.setActionCommand("delegarEncomenda");
        requestStoreKeeperBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        requestStoreKeeperBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelrequestStoreKeeper.add(requestStoreKeeperBot); //adiciona o botao ao painel

        panelListYourOrder = new JPanel(new FlowLayout(FlowLayout.CENTER));
        listYourOrderBot = new JButton(" Lista/Pesquisa ");
        listYourOrderBot.setPreferredSize(new Dimension(180,30));
        listYourOrderBot.setActionCommand("listaTodasEncomenda");
        listYourOrderBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        listYourOrderBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelListYourOrder.add(listYourOrderBot); //adiciona o botao ao painel

        panelConfirmOrder = new JPanel(new FlowLayout(FlowLayout.CENTER));
        confirmOrderBot = new JButton(" Aceitar/Rejeitar Enc. ");
        confirmOrderBot .setPreferredSize(new Dimension(180,30));
        confirmOrderBot.setActionCommand("aceitarEncomenda");
        confirmOrderBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        confirmOrderBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelConfirmOrder.add(confirmOrderBot); //adiciona o botao ao painel

        panelOut = new JPanel(new FlowLayout(FlowLayout.CENTER));
        outBottom = new JButton(" Voltar");
        outBottom.setPreferredSize(new Dimension(180,30));
        outBottom.setActionCommand("voltar");
        outBottom.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        outBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelOut.add(outBottom); //adiciona o botao ao painel

        //adicionar os paineis ao painel principal
        panelMenu.add(panelConfirmOrder);
        panelMenu.add(panelrequestStoreKeeper);
        panelMenu.add(panelListYourOrder);
        panelMenu.add(panelOut);

        //adicionar os paineis principais ao contentor
        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(panelMenu, BorderLayout.CENTER);
        cont.add(new JPanel(), BorderLayout.SOUTH);

        this.setSize(450,390);
        this.setLocationRelativeTo(null);
        this.setTitle("Encomendas");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * metodo de gestao de eventos da classe
     * @param e evento gerado pela classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        switch (op){
            case "listaSuaEncomenda": ListSearchOrders listSearchOrders = new ListSearchOrders(); Logs.addMov("Listar/pesquisar Suas Encomendas"); break;
            case "listaTodasEncomenda": AdminListSearchOrders adminListSearchOrders = new AdminListSearchOrders(); Logs.addMov("Listar/Pesquisar todas encomendas"); break;
            case "confirmarEntrega": ClientConfirmDelivery clientConfirmDelivery = new ClientConfirmDelivery(); Logs.addMov("Confirmar Entrega"); break;
            case "entregarEncomenda": CourierConfirmDelivery courierConfirmDelivery = new CourierConfirmDelivery(); Logs.addMov("Dar Encomenda como Entregue"); break;
            case "aceitarPedidosEncomenda": CourierAceptRequest courierAceptRequest = new CourierAceptRequest(); Logs.addMov("Aceitar pedidos de Encomenda"); break;
            case "pedidoEntregaEncomenda": StoreKeeperDeliveryOrder storeKeeperDeliveryOrder = new StoreKeeperDeliveryOrder(); Logs.addMov("Pedido de Entrega a estafeta"); break;
            case "delegarEncomenda": AdminDelegateOrder adminDelegateOrder = new AdminDelegateOrder(); Logs.addMov("Delegar Encomenda a armazenista"); break;
            case "aceitarEncomenda": AdminAcceptOrder adminAcceptOrder = new AdminAcceptOrder(); Logs.addMov("Aceitar Encomenda de Cliente"); break;
            case "criarEncomenda": FormCreateOrder formCreateOrder = new FormCreateOrder(); Logs.addMov("Criar Encomenda"); break;
            case "voltar": MainMenu mainMenu = new MainMenu(DadosStatic.Tipo); this.dispose(); Logs.addMov("Voltar Atras"); break;
        }

    }
}
