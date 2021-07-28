package InterfaceIU;

import Produtos.Categoria;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe responsavel pela listagem das Categorias existentes na base de Dados
 * @author Jorge Martins e Rodrigo Duro
 * Classe responsavel pela listagem das Categorias existentes na base de Dados
 */
public class ListCategory extends JPanel{

    private JPanel panelTop, userPanelTable;
    private JTable catTable;
    private JScrollPane scroll;

    /**
     * Construtor da classe grafica, contem os metodos para criacao grafica, gera um JPanel que pode ser inserido dentro de frames de outras classes
     * @param listCategory lista de categorias
     */
    public ListCategory(ArrayList<Categoria> listCategory) {
        String [][] catlist = stringConverter(listCategory);
        setLayout(new BorderLayout());

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop.add(new JLabel("Categorias"));

        userPanelTable = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] colname = {"Designacao", "Classificacao"};
        catTable = new JTable(catlist, colname);
        catTable.setEnabled(false);
        catTable.setAutoCreateRowSorter(true);
        catTable.setPreferredScrollableViewportSize(new Dimension(900,250));
        scroll = new JScrollPane(catTable); //inserir dentro do scroll e não o contrario --'
        userPanelTable.add(scroll);

        add(panelTop, BorderLayout.NORTH);
        add(userPanelTable, BorderLayout.CENTER);

    }

    /**
     * Conversor de arrayList para String bidimensional, para imitar o que foi usado no projecto 1 do Jorge como listador Master
     * @param listCategory
     * @return String [][]
     */
    private static String[][] stringConverter(ArrayList <Categoria> listCategory){ // como a JTable só aceita objectos [][] para ficar parecido ao meu listador master do primeiro projecto converti em string bidimensional
        String [][] sendList = new String[listCategory.size()][2];
        for (int i = 0; i < listCategory.size(); ++i) {
            sendList[i][0] = " " + listCategory.get(i).getDesignacao();
            sendList[i][1] = " " + listCategory.get(i).getClassificacao();
        }
        return sendList;
    }

    /**
     * Metodo para criar um frame pois o construtor apenas cria um JPanel com a info
     * @param listCategory arraylist de categoria
     * @return JFrame com o frame construido com o JPanel do construtor
     */
    public static JFrame createFrame(ArrayList <Categoria> listCategory){
        JFrame jframe = new JFrame();
        ListCategory menuListMaster = new ListCategory(listCategory);

        jframe.setSize(950,400);
        jframe.setLocationRelativeTo(null);
        jframe.setTitle("Listagem Categorias");
        jframe.setResizable(false);
        jframe.setVisible(true);

        jframe.add(menuListMaster);
        return jframe;
    }

}
