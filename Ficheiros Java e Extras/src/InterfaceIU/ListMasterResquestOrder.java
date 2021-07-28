package InterfaceIU;

import PedidosNotificacoes.Pedido;
import Produtos.Produto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Metodo de Listar os Pedidos de Encomenda
 * @author Jorge Martins e Rodrigo Duro
 */
public class ListMasterResquestOrder extends JPanel {

    private JPanel panelTop, userPanelTable;
    private JTable userTable;
    private JScrollPane scroll;

    /**
     * Construtor da classe que cria um JPanel com os elementos para construir uma interface grafica
     * @param listRe arraylist Pedido
     */
    public ListMasterResquestOrder(ArrayList<Pedido> listRe){
        String [][] reqlist = stringConverterUser(listRe);
        setLayout(new BorderLayout());

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop.add(new JLabel("Lista de Pedidos"));

        userPanelTable = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] colname = {"ID Pedido", "ID Cliente", "ID Gestor", "ID Encomenda", "Estado Pedido"};
        userTable = new JTable(reqlist, colname);
        userTable.setEnabled(false);
        userTable.setAutoCreateRowSorter(true);
        userTable.setPreferredScrollableViewportSize(new Dimension(900,250));
        scroll = new JScrollPane(userTable); //inserir dentro do scroll e não o contrario --'
        userPanelTable.add(scroll);

        add(panelTop, BorderLayout.NORTH);
        add(userPanelTable, BorderLayout.CENTER);

    }

    /**
     * Conversor de arrayList para String bidimensional, para imitar o que foi usado no projecto 1 do Jorge como listador Master
     * @param listReq
     * @return String [][]
     */
    private static String[][] stringConverterUser(ArrayList<Pedido> listReq){ // como a JTable só aceita objectos [][] para ficar parecido ao meu listador master do primeiro projecto converti em string bidimensional
        String [][] sendList = new String[listReq.size()][5];
        for (int i = 0; i < listReq.size(); ++i) {
            sendList[i][0] = " " + listReq.get(i).getIdPedido();
            sendList[i][1] = " " + listReq.get(i).getIdCliente();
            sendList[i][2] = " " + listReq.get(i).getIdUtilizador();
            sendList[i][3] = " " + listReq.get(i).getIdEncomenda();
            sendList[i][4] = " " + listReq.get(i).getEstado();

        }
        return sendList;
    }

    /**
     * Metodo de criacao de JFrame com os elementos do Jpanel do Construtor da classe
     * @param list arraylist de pedido
     * @return JFrame com a insercao do JPanel do construtor
     */
    public static JFrame createFrame(ArrayList<Pedido> list){
        JFrame jframe = new JFrame();
        ListMasterResquestOrder menuListMaster = new ListMasterResquestOrder(list);

        jframe.setSize(950,400);
        jframe.setLocationRelativeTo(null);
        jframe.setTitle("Lista de Pedidos");
        jframe.setResizable(false);
        jframe.setVisible(true);

        jframe.add(menuListMaster);
        return jframe;
    }
}
