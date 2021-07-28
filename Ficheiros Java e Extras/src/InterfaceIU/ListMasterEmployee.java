package InterfaceIU;

import Produtos.Produto;
import Utilizadores.Funcionarios;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Metodo responsavel pela listagem geral de funcionarios
 * @author Jorge Martins e Rodrigo Duro
 */
public class ListMasterEmployee extends JPanel {
    private JPanel panelTop, userPanelTable;
    private JTable userTable;
    private JScrollPane scroll;

    /**
     * Construtor que cria apenas um JPanel com os metodos necessarios para a criacao de uma interface Grafica
     * @param listEmp arraylist de funcionarios
     */
    public ListMasterEmployee(ArrayList<Funcionarios> listEmp){

            String [][] emplist = stringConverterUser(listEmp);
            setLayout(new BorderLayout());

            panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panelTop.add(new JLabel("Lista de Funcionarios"));

            userPanelTable = new JPanel(new FlowLayout(FlowLayout.CENTER));
            String[] colname = {"Nome", "Login", "Tipo", "Especializacao"};
            userTable = new JTable(emplist, colname);
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
        private static String[][] stringConverterUser(ArrayList<Funcionarios> listProducts){ // como a JTable só aceita objectos [][] para ficar parecido ao meu listador master do primeiro projecto converti em string bidimensional
            String [][] sendList = new String[listProducts.size()][4];
            for (int i = 0; i < listProducts.size(); ++i) {
                sendList[i][0] = " " + listProducts.get(i).getNome();
                sendList[i][1] = " " + listProducts.get(i).getLogin();
                sendList[i][2] = " " + listProducts.get(i).getTipo();
                sendList[i][3] = " " + listProducts.get(i).getEspecializacao();
            }
            return sendList;
        }

    /**
     * Metodo de Criacao de JFrame com um arraylist de Funcionarios
     * @param list recebe um arrayList de funcionarios
     * @return JFrame com o JPanel do construtor
     */
    public static JFrame createFrame(ArrayList<Funcionarios> list){
            JFrame jframe = new JFrame();
            ListMasterEmployee menuListMaster = new ListMasterEmployee(list);

            jframe.setSize(950,400);
            jframe.setLocationRelativeTo(null);
            jframe.setTitle("Lista Funcionarios");
            jframe.setResizable(false);
            jframe.setVisible(true);

            jframe.add(menuListMaster);
            return jframe;
        }
    }


