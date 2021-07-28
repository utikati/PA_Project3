package InterfaceIU;

import AcessoBD.DadosEncomendas;
import AcessoBD.DadosNotificacao;
import AcessoBD.DadosProdutos;
import AcessoBD.DadosUtilizadores;
import DadosEstaticos.DadosStatic;
import Produtos.Encomenda;
import Produtos.Encomenda_Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import Aviso.*;
import Produtos.Produto;


/**
 * Classe responsavel por criacao grafica do formulario de criacao de Encomenda
 * @author Jorge Martins e Rodrigo Duro
 */
public class FormCreateOrder extends JFrame implements ActionListener {
    Container container;
    JPanel titlePanel, geralPanel, insertOrderPanel, insertQtdPanel, southPanel, listProdPanel, obsPanel, p1, p2, p3;
    JTextField obsField, skuField, qtdField;
    JButton insertQtdBot, insertOrderBot;
    ArrayList <Encomenda_Produto> orderTable = new ArrayList<Encomenda_Produto>(); //tabela com os produtos a inserir

    /**
     * Metodo construtor da interface grafica
     */
    public FormCreateOrder(){
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        orderTable = new ArrayList<Encomenda_Produto>();
        container = getContentPane();
        container.setLayout(new BorderLayout());
        southPanel = new JPanel(new GridLayout(3, 1));
        p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(new JLabel("Criar Encomenda"));
        obsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        geralPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //FINAL DA CONSTRUÇÃO DAS GRIDS QUE DEPOIS VAO SER PREENCHIDAS COM OS PAINEIS

        //criar labels e fields
        //ETIQUETAS PARA OS CAMPOS
        JPanel tagSKU = new JPanel(new FlowLayout(FlowLayout.LEFT)); //como no login no janela de LoginMenu
        tagSKU.add(new JLabel("SKU"));

        JPanel tagQtd = new JPanel(new FlowLayout(FlowLayout.LEFT)); //como no login no janela de LoginMenu
        tagQtd.add(new JLabel("Quantidade"));


        //observacoes fields

        JPanel tagObs = new JPanel(new FlowLayout(FlowLayout.LEFT)); //como no login no janela de LoginMenu
        tagObs.add(new JLabel("Observacoes Encomenda"));

        JPanel obsBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        obsField = new JTextField(15);
        obsField.setToolTipText("Insira observacoes");
        obsBox.add(obsField);

        //---------------------------------------------------------------------------------


        //CAMPOS A PREENCHER


        skuField = new JTextField(15);
        skuField.setToolTipText("Insira SKU do Produto");
        tagSKU.add(skuField);


        qtdField = new JTextField(15);
        qtdField.setToolTipText("Insira a quantidade do produto desejada");
        tagQtd.add(qtdField);

        //BOTOES DE UPDATE DOS DADOS

        insertQtdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        insertQtdBot = new JButton("Inserir Produto");
        insertQtdBot.setActionCommand("inserirProduto");
        insertQtdBot.addActionListener(this);
        insertQtdBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        insertQtdPanel.add(insertQtdBot);


        //adicionar ao painel com a info geral
        geralPanel.add(tagSKU);


        geralPanel.add(tagQtd);


        geralPanel.add(insertQtdPanel);

        obsPanel.add(tagObs);
        obsPanel.add(obsBox);

        listProdPanel = new ListMasterProduct(DadosProdutos.listarProdutosCondicao(""));


        insertOrderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        insertOrderBot = new JButton("Submeter Encomenda");
        insertOrderBot.setActionCommand("subEncomenda");
        insertOrderBot.addActionListener(this);
        insertOrderBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        insertOrderPanel.add(insertOrderBot);

        p1.add(geralPanel);
        p2.add(obsPanel);
        p3.add(insertOrderPanel);

        southPanel.add(p1);
        southPanel.add(p2);
        southPanel.add(p3);

        container.add(titlePanel, BorderLayout.NORTH);
        container.add(listProdPanel, BorderLayout.CENTER);
        container.add(southPanel, BorderLayout.SOUTH);


        this.setSize(990, 590);
        this.setLocationRelativeTo(null);
        this.setTitle("Encomenda");
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    /**
     * Metodo de insercao do produto na encomenda verifica a quantidade de stock disponivel
     */
    private void insertProduct(){
        int sku = Integer.parseInt(skuField.getText());
        if(DadosProdutos.idProduto(sku) > 0) {
            int qtd = Integer.parseInt(qtdField.getText());
            if(DadosProdutos.pesquisaProdutoPorID(DadosProdutos.idProduto(sku)).getQuantidade() < qtd){
                Aviso.showMessage("Produto sem stock suficiente para encomenda", "Aviso", "error");
            }else{
                if(checkBase(DadosProdutos.idProduto(sku), qtd)) {
                    orderTable.add(new Encomenda_Produto(DadosProdutos.idProduto(sku), qtd));
                    cleanFields();
                }
            }
        }else {
            Aviso.showMessage("Produto não existe", "Aviso", "error");
        }
    }

    /**
     * Metodo que verifica a quantidade de stock disponivel armazenada
     * @param idProduct id do Produto
     * @param qtd quantidade do Produto
     * @return booleano com resultado da verificacao
     */
    private boolean checkBase(int idProduct, int qtd) {
        if (orderTable != null) {
            Iterator<Encomenda_Produto> table = orderTable.iterator();
            while (table.hasNext()) {
                Encomenda_Produto aux = table.next();
                if(aux.getIdProduto() == idProduct){
                    if(aux.getQuantidadeProduto() + qtd > DadosProdutos.pesquisaProdutoPorID(aux.getIdProduto()).getQuantidade()){
                        Aviso.showMessage("Ultrapassa quantidade em Stock", "Aviso", "error");
                        cleanFields();
                        return false;
                    }else{
                        return true;
                    }

                }return true;

            }return true;

        }
        return true;
    }

    /**
     * Metodo de inserir a encomenda no sistema
     */
    private void insertOrder(){
        if(orderTable.size() > 0) {
            int price = createPriceOrder(orderTable);
            Date dateCreate = new Date();
            String state = "iniciada";
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmss"); //criar o formato para o identificador
            String finalPartIdentificador = formatDate.format(dateCreate.getTime()); //cria logo a parte final do forma do identificador

            String id = String.valueOf(DadosEncomendas.maxIdEncomenda() + 1) + finalPartIdentificador; //como é auto increment o pedido será o maximo mais 1 para juntar no identificador
            Encomenda order = new Encomenda(id, price, dateCreate, state, Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login)));
            order.setObs(obsField.getText());
            DadosEncomendas.adicionarEncomenda(order);

            makeTableOrderProduct(orderTable, DadosEncomendas.maxIdEncomenda()); // apos adicionar a encomenda o id dela será o maximo existente na tabela pois ela é auto increment e já foi adicionada

            autoActStock(orderTable); //este metodo vai actualizar todos o stock retirando a quantidade em relação à encomenda
            DadosNotificacao.addNotification("A encomenda com Identificador: "+order.getIdentificadorEncomenda()+" foi criada e espera ser aceite!!!",DadosUtilizadores.listarUtilizadoresCondicao("TIPO_UTILIZADOR = 'Gestor'"));
            Aviso.showMessage("Encomenda Gerada com Sucesso", "Aviso", "info");
        }else {
            Aviso.showMessage("Erro - Encomenda não gerada", "Aviso", "error");
        }
    }

    /**
     * Metodo de criar o preco total da encomenda
     * @param orderProduct arraylist com todos os produtos dentro da encomenda
     * @return inteiro com o preco da encomenda
     */
    public static int createPriceOrder(ArrayList<Encomenda_Produto> orderProduct) {

        int price = 0;

        Iterator <Encomenda_Produto> table = orderProduct.iterator();
        Encomenda_Produto aux;
        while(table.hasNext()) {
            aux = table.next();
            price += DadosProdutos.pesquisaProdutoPorID(aux.getIdProduto()).getPreco() * aux.getQuantidadeProduto();
        }

        return price;
    }

    /**
     * Metodo de criacao das tabelas com os produtos da encomenda
     * @param orderProduct lista com produtos da encomenda
     * @param idOrder id da encomenda
     * @return booleano com resultado do metodo
     */
    public static boolean makeTableOrderProduct(ArrayList<Encomenda_Produto> orderProduct, int idOrder) {

        Iterator <Encomenda_Produto> table = orderProduct.iterator();
        Encomenda_Produto auxiliar;
        while(table.hasNext()) {
            auxiliar = table.next();
            DadosEncomendas.adicionaTabelaEncomendaProduto(auxiliar, idOrder);
        }
        return true;
    }

    /**
     * Metodo para actualizar o stock de encomenda quando esta é criada
     * @param orderProduct lista de produtos com suas quantidades para actualizacao
     */
    public static void autoActStock(ArrayList <Encomenda_Produto> orderProduct) {

        Iterator <Encomenda_Produto> table = orderProduct.iterator();
        Encomenda_Produto aux;
        while(table.hasNext()) {
            aux = table.next();
            Produto product = DadosProdutos.pesquisaProdutoPorID(aux.getIdProduto());
            product.setQuantidade(product.getQuantidade() - aux.getQuantidadeProduto());
            DadosProdutos.updateProduto(product);
        }
    }

    /**
     * Metodo para limpar os campos
     */
    private void cleanFields(){
        skuField.setText("");
        qtdField.setText("");
        obsField.setText("");
    }

    /**
     * Metodo responsavel por escutar os eventos criados na classe
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        switch (op){
            case "subEncomenda": if(orderTable != null && orderTable.size() > 0 && !obsField.getText().equals("")){
                                    insertOrder();
                                    this.dispose();
                                    FormCreateOrder formCreateOrder = new FormCreateOrder();
                                }else{
                                    Aviso.showMessage("Algum elemento vazio", "Aviso", "error");
                                }
                                    break;
            case "inserirProduto": if(skuField.getText().equals("") || qtdField.getText().equals("")){
                                        Aviso.showMessage("Algum campo vazio", "Aviso", "info");
                                    }else{
                                        insertProduct();
                                        Aviso.showMessage("Produto adicionado na encomenda", "Aviso", "info");
                                    }
                                    break;
            default: Aviso.showMessage("Nao implementado", "Aviso", "error"); break;
        }

    }
    
}
