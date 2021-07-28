package InterfaceIU;

import AcessoBD.DadosProdutos;
import AcessoBD.DadosUtilizadores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Aviso.*;

/**
 * Classe Responsavel pela listagem de Produtos
 * @author Jorge Martins e Rodrigo Duro
 */
public class ListProduct extends JFrame implements ActionListener {

    Container cont;
    JPanel mainPanel, panelTop, desProdSearch, southPanel, catProdSearch, stockProdSearch, fatherPanel;
    JTextField descField, catField, stockField;
    JButton searchDes, searchCat, searchStock;

    /**
     * Construtor com os elementos para a construcao da interface grafica
     */
    public ListProduct(){
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        cont = getContentPane();
        cont.setLayout(new BorderLayout());

        mainPanel = new JPanel(new GridLayout(1, 1));

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop.add(new JLabel("Lista Produtos"));

        //tags e Box e botao estao 3 a 3 <----------------------------------------------
        JPanel tagDescProd = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagDescProd.add(new JLabel("Designacao Produto"));

        JPanel descBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        descField = new JTextField(15);
        descField.setToolTipText("Descricao do Produto");
        descBox.add(descField);

        desProdSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchDes = new JButton("Pesquisar");
        searchDes.setActionCommand("pesquisarDescricao");
        searchDes.addActionListener(this);
        searchDes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        desProdSearch.add(searchDes);
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        JPanel tagCatProd = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagCatProd.add(new JLabel("Categoria Produto"));

        JPanel catBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        catField = new JTextField(15);
        catField.setToolTipText("Categoria do Produto");
        catBox.add(catField);

        catProdSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchCat = new JButton("Pesquisar");
        searchCat.setActionCommand("pesquisarCategoria");
        searchCat.addActionListener(this);
        searchCat.setCursor(new Cursor(Cursor.HAND_CURSOR));
        catProdSearch.add(searchCat);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        JPanel tagStockProd = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagStockProd.add(new JLabel("Stock Superior a"));

        JPanel stockBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        stockField = new JTextField(15);
        stockField.setToolTipText("Insira o numero de stock pelo qual quer ver produtos com stock superior");
        stockBox.add(stockField);

        stockProdSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchStock = new JButton("Pesquisar");
        searchStock.setActionCommand("pesquisarStock");
        searchStock.addActionListener(this);
        searchStock.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stockProdSearch.add(searchStock);
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //---------------------------------------------------------------------------------------------
        mainPanel.add(new ListMasterProduct(DadosProdutos.listarProdutosCondicao("")));

        fatherPanel = new JPanel(new GridLayout(3, 3));
        fatherPanel.add(tagDescProd);
        fatherPanel.add(descBox);
        fatherPanel.add(desProdSearch);

        fatherPanel.add(tagCatProd);
        fatherPanel.add(catBox);
        fatherPanel.add(catProdSearch);

        fatherPanel.add(tagStockProd);
        fatherPanel.add(stockBox);
        fatherPanel.add(stockProdSearch);


        southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.add(fatherPanel);



        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(mainPanel, BorderLayout.CENTER);
        cont.add(southPanel, BorderLayout.SOUTH);

        this.setSize(950, 490);
        this.setLocationRelativeTo(null);
        this.setTitle("Novas Contas");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * Metodo responsavel por gestao dos eventos da classe
     * @param e evento gerado pela classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        switch (op){
            case "pesquisarDescricao": if(descField.getText().equals("")){
                                            Aviso.showMessage("Campo Vazio", "Aviso", "error");
                                        }else{
                                            ListMasterProduct.createFrame(DadosProdutos.listarProdutosCondicao("WHERE DESIGNACAO_PRODUTO LIKE \"%"+descField.getText()+"%\" ;"));
                                        } break;
            case "pesquisarCategoria": if(catField.getText().equals("")){
                                            Aviso.showMessage("Campo Vazio", "Aviso", "error");
                                        }else{
                                            ListMasterProduct.createFrame(DadosProdutos.listarProdutosCondicao("WHERE ID_CATEGORIA = "+DadosProdutos.idCategoria(catField.getText())+" ;"));
                                        } break;
            case "pesquisarStock": if(stockField.getText().equals("")){
                                        Aviso.showMessage("Campo Vazio", "Aviso", "error");
                                    }else{
                                        ListMasterProduct.createFrame(DadosProdutos.listarProdutosCondicao("WHERE QUANTIDADE_PRODUTO > "+stockField.getText()+" ;"));
                                    }break;
        }

    }
}
