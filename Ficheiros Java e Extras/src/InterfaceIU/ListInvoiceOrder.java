package InterfaceIU;

import AcessoBD.DadosEncomendas;
import AcessoBD.DadosProdutos;
import Produtos.Encomenda;
import Produtos.Encomenda_Produto;

import javax.mail.Message;
import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * Classes responsavel pela demonstração em tabela da fatura que vai ser imprimida
 * @author Jorge Martins e Rodrigo Duro
 */
public class ListInvoiceOrder extends JPanel implements ActionListener {

    private JPanel panelTop, userPanelTable, invPanel;
    private JTable userTable;
    private JScrollPane scroll;
    private JButton invBotton;
    private static String numberInv = "", startDate = "", endDate = "";

    /**
     * Construtor com os elementos graficos para a construcao do interface grafico
     *  Apenas gera o JPANEL
     * @param listOrder arralist de encomendas condicionado a pesquisa
     */
    public ListInvoiceOrder(ArrayList<Encomenda> listOrder){
        String [][] orderlist = stringConverterUser(listOrder);
        setLayout(new BorderLayout());

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop.add(new JLabel("Lista de Encomendas"));

        userPanelTable = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] colname = {"Produto", "Quantidade", "Custo"};
        userTable = new JTable(orderlist, colname);
        userTable.setEnabled(false);
        userTable.setAutoCreateRowSorter(true);
        userTable.setPreferredScrollableViewportSize(new Dimension(450,250));
        scroll = new JScrollPane(userTable); //inserir dentro do scroll e não o contrario --'
        userPanelTable.add(scroll);

        invPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        invBotton = new JButton("Criar Fatura");
        invBotton.setActionCommand("criarFatura");
        invBotton.addActionListener(this);
        invPanel.add(invBotton);



        add(panelTop, BorderLayout.NORTH);
        add(userPanelTable, BorderLayout.CENTER);
        add(invPanel, BorderLayout.SOUTH);

    }

    /**
     * Conversor de arrayList para String bidimensional, para imitar o que foi usado no projecto 1 do Jorge como listador Master
     * @param listProducts
     * @return String [][]
     */
    private static String[][] stringConverterUser(ArrayList<Encomenda> listProducts){ // como a JTable só aceita objectos [][] para ficar parecido ao meu listador master do primeiro projecto converti em string bidimensional
        int idOrd = DadosEncomendas.idEncomenda(listProducts.get(0).getIdentificadorEncomenda());
        startDate += listProducts.get(0).getDataCriacao();
        endDate += listProducts.get(0).getDataEntrega();
        numberInv += listProducts.get(0).getIdentificadorEncomenda();
        ArrayList <Encomenda_Produto> listProdOrd = DadosEncomendas.listaEncomenda_Produto("WHERE ID_ENCOMENDA = "+ idOrd);
        String [][] sendList = new String[listProdOrd.size() + 1][3];
        for (int i = 0; i < listProdOrd.size(); ++i) {
            sendList[i][0] = " " + DadosProdutos.pesquisaProdutoPorID(listProdOrd.get(i).getIdProduto()).getDesignacao();
            sendList[i][1] = " " + listProdOrd.get(i).getQuantidadeProduto();
            sendList[i][2] = " " + DadosProdutos.pesquisaProdutoPorID(listProdOrd.get(i).getIdProduto()).getPreco() * listProdOrd.get(i).getQuantidadeProduto();
            if(i+1 == listProdOrd.size()){
                sendList[i+1][0] = " Total";
                sendList[i+1][1] = "  ";
                sendList[i+1][2] = " "+ listProducts.get(0).getCusto();
            }
        }
        return sendList;
    }

    /**
     * Metodo responsavel pela criação de um JFrame do panel criado pelo construtor da classe
     * @param list arraylist de Encomendas condicionadas
     * @return JFrame com o JPanel desta classe já inserido
     */
    public static JFrame createFrame(ArrayList<Encomenda> list){
        JFrame jframe = new JFrame();
        ListInvoiceOrder menuListMaster = new ListInvoiceOrder(list);

        jframe.setSize(460,250);
        jframe.setLocationRelativeTo(null);
        jframe.setTitle("Lista");
        jframe.setResizable(false);
        jframe.setVisible(true);

        jframe.add(menuListMaster);
        return jframe;
    }

    /**
     * Metodo de gestao de eventos da classe
     * @param e evento gerado pela classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("criarFatura")){
            MessageFormat msg = new MessageFormat("Fatura nº"+numberInv);
            MessageFormat msg2 = new MessageFormat("Inicio da Encomenda: "+startDate+" Fim da Encomenda: "+endDate+"\n");
            try {
                userTable.print(JTable.PrintMode.NORMAL, msg, msg2);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }

}



