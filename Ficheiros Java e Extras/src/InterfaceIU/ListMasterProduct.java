package InterfaceIU;

import Produtos.Produto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe responsavel pela Listagem dos Produtos existentes na base de Dados
 * @author Jorge Martins e Rodrigo Duro
 */
public class ListMasterProduct extends JPanel{

    private JPanel panelTop, userPanelTable;
    private JTable userTable;
    private JScrollPane scroll;

    /**
     * Construtor da classe que contem os argumentos necessarios para construir o JPanel para a interface grafica
     * @param listProd arrayList produto
     */
    public ListMasterProduct(ArrayList <Produto> listProd){
        String [][] prodlist = stringConverterUser(listProd);
        setLayout(new BorderLayout());

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop.add(new JLabel("Lista de Produtos"));

        userPanelTable = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] colname = {"Categoria", "Designacao", "Fabricante", "Quantidade", "Preco", "SKU", "Lote"};
        userTable = new JTable(prodlist, colname);
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
    private static String[][] stringConverterUser(ArrayList<Produto> listProducts){ // como a JTable só aceita objectos [][] para ficar parecido ao meu listador master do primeiro projecto converti em string bidimensional
        String [][] sendList = new String[listProducts.size()][7];
        for (int i = 0; i < listProducts.size(); ++i) {
            sendList[i][0] = " " + listProducts.get(i).getCategoria().getDesignacao();
            sendList[i][1] = " " + listProducts.get(i).getDesignacao();
            sendList[i][2] = " " + listProducts.get(i).getFabricante();
            sendList[i][3] = " " + listProducts.get(i).getQuantidade();
            sendList[i][4] = " " + listProducts.get(i).getPreco();
            sendList[i][5] = " " + listProducts.get(i).getSku();
            sendList[i][6] = " " + listProducts.get(i).getLote();

        }
        return sendList;
    }

    /**
     * Metodo de criacao de JFrame com os dados dos JPanel construido no construtor
     * @param list arraylist Produto
     * @return JFrame do resultado da insercao com JPanel do construtor
     */
    public static JFrame createFrame(ArrayList<Produto> list){
        JFrame jframe = new JFrame();
        ListMasterProduct menuListMaster = new ListMasterProduct(list);

        jframe.setSize(950,400);
        jframe.setLocationRelativeTo(null);
        jframe.setTitle("Lista de Produtos do Sistema");
        jframe.setResizable(false);
        jframe.setVisible(true);

        jframe.add(menuListMaster);
        return jframe;
    }
}
