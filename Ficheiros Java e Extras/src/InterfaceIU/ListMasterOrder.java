package InterfaceIU;

import Produtos.Encomenda;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Metodo de listagem das encomendas do sistema
 * @author Jorge Martins e Rodrigo Duro
 */
public class ListMasterOrder extends JPanel {

    private JPanel panelTop, userPanelTable;
    private JTable userTable;
    private JScrollPane scroll;

    /**
     * Construtor responsavel pela criacao do JPanel com os metodos necessario graficos
     * @param listOrder arraylist de encomenda
     */
    public ListMasterOrder(ArrayList<Encomenda> listOrder){
        String [][] orderlist = stringConverterUser(listOrder);
        setLayout(new BorderLayout());

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop.add(new JLabel("Lista de Encomendas"));

        userPanelTable = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] colname = {"Identificador", "Custo", "Data Criacao", "Estado"};
        userTable = new JTable(orderlist, colname);
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
     * @param listProducts
     * @return String [][]
     */
    private static String[][] stringConverterUser(ArrayList<Encomenda> listProducts){ // como a JTable só aceita objectos [][] para ficar parecido ao meu listador master do primeiro projecto converti em string bidimensional
        String [][] sendList = new String[listProducts.size()][4];
        for (int i = 0; i < listProducts.size(); ++i) {
            sendList[i][0] = " " + listProducts.get(i).getIdentificadorEncomenda();
            sendList[i][1] = " " + listProducts.get(i).getCusto();
            sendList[i][2] = " " + listProducts.get(i).getDataCriacao();
            sendList[i][3] = " " + listProducts.get(i).getEstado();

        }
        return sendList;
    }

    /**
     * Metodo de criacao de um JFrame atraves do JPanel recebido do construtor
     * @param list arraylist de encomenda
     * @return Jframe criado com o Jpanel do construtor da classe
     */
    public static JFrame createFrame(ArrayList<Encomenda> list){
        JFrame jframe = new JFrame();
        ListMasterOrder menuListMaster = new ListMasterOrder(list);

        jframe.setSize(950,400);
        jframe.setLocationRelativeTo(null);
        jframe.setTitle("Lista de Utilizadores do Sistema");
        jframe.setResizable(false);
        jframe.setVisible(true);

        jframe.add(menuListMaster);
        return jframe;
    }
}



