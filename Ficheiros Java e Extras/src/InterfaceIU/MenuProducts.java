package InterfaceIU;

import AcessoBD.DadosProdutos;
import Aviso.Aviso;
import DadosEstaticos.DadosStatic;
import Utilizadores.Logs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe responsavel pelo menu de produtos
 * @author Jorge Martins e Rodrigo Duro
 */
public class MenuProducts  extends JFrame implements ActionListener {

    private Container cont;
    private JPanel panelTop, panelMenu, panelCreateProd, panelCreateCat, panelListProd, panelListCat, panelInsertStock, panelOut;
    private JButton createProdBot, createCatBot, listProdBot, listCatBot, insertStockBot, outBottom;

    /**
     * Construtor da classe que chama o metodo de construcao grafica conforme o tipo de utilizador online
     */
    public MenuProducts() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        if(DadosStatic.Tipo.equals("Funcionario") && DadosStatic.especializacao.equals("Armazenista")) {
            menuArm(); //menu do armazenista que tem o criar produtos e categorias, inserir stocks etc etc
        }
        else {
            menuAnothers();
        }
    }

    /**
     * Menu de Produtos do armazenista, com os elementos graficos para a construcao grafica
     */
    private void menuArm(){
        cont = getContentPane();
        cont.setLayout(new BorderLayout());

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER)); //para preencher e ficar no meio
        panelTop.add(new JLabel("Menu Produtos"));

        panelMenu = new JPanel(new GridLayout(6,1));

        panelCreateProd = new JPanel(new FlowLayout(FlowLayout.CENTER));
        createProdBot = new JButton(" Criar Produto ");
        createProdBot.setPreferredSize(new Dimension(180,30));
        createProdBot.setActionCommand("criarProduto");
        createProdBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        createProdBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelCreateProd.add(createProdBot); //adiciona o botao ao painel

        panelCreateCat = new JPanel(new FlowLayout(FlowLayout.CENTER));
        createCatBot = new JButton(" Criar Categoria ");
        createCatBot.setPreferredSize(new Dimension(180,30));
        createCatBot.setActionCommand("criarCategoria");
        createCatBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        createCatBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelCreateCat.add(createCatBot); //adiciona o botao ao painel

        panelInsertStock = new JPanel(new FlowLayout(FlowLayout.CENTER));
        insertStockBot = new JButton(" Inserir Stock Produto ");
        insertStockBot .setPreferredSize(new Dimension(180,30));
        insertStockBot.setActionCommand("inserirStock");
        insertStockBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        insertStockBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelInsertStock.add(insertStockBot); //adiciona o botao ao painel

        panelListProd = new JPanel(new FlowLayout(FlowLayout.CENTER));
        listProdBot = new JButton(" Listagens/Pesquisas");
        listProdBot.setPreferredSize(new Dimension(180,30));
        listProdBot.setActionCommand("listarProdutos");
        listProdBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        listProdBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelListProd.add(listProdBot); //adiciona o botao ao painel

        panelListCat = new JPanel(new FlowLayout(FlowLayout.CENTER));
        listCatBot = new JButton(" Listar Categorias ");
        listCatBot.setPreferredSize(new Dimension(180,30));
        listCatBot.setActionCommand("listarCategorias");
        listCatBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        listCatBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelListCat.add(listCatBot); //adiciona o botao ao painel

        panelOut = new JPanel(new FlowLayout(FlowLayout.CENTER));
        outBottom = new JButton(" Voltar");
        outBottom.setPreferredSize(new Dimension(180,30));
        outBottom.setActionCommand("voltar");
        outBottom.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        outBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelOut.add(outBottom); //adiciona o botao ao painel

        //adicionar os paineis ao painel principal
        panelMenu.add(panelCreateProd);
        panelMenu.add(panelCreateCat);
        panelMenu.add(panelInsertStock);
        panelMenu.add(panelListProd);
        panelMenu.add(panelListCat);
        panelMenu.add(panelOut);

        //adicionar os paineis principais ao contentor
        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(panelMenu, BorderLayout.CENTER);
        cont.add(new JPanel(), BorderLayout.SOUTH);

        this.setSize(450,390);
        this.setLocationRelativeTo(null);
        this.setTitle("Produtos");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * Menu de produtos para clientes e para gestores com os elementos graficos para construcao grafica
     */
    private void menuAnothers(){
        cont = getContentPane();
        cont.setLayout(new BorderLayout());

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER)); //para preencher e ficar no meio
        panelTop.add(new JLabel("Menu Produtos"));

        panelMenu = new JPanel(new GridLayout(3,1));

        panelListProd = new JPanel(new FlowLayout(FlowLayout.CENTER));
        listProdBot = new JButton(" Listagens/Pesquisas");
        listProdBot.setPreferredSize(new Dimension(180,30));
        listProdBot.setActionCommand("listarProdutos");
        listProdBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        listProdBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelListProd.add(listProdBot); //adiciona o botao ao painel

        panelListCat = new JPanel(new FlowLayout(FlowLayout.CENTER));
        listCatBot = new JButton(" Listar Categorias ");
        listCatBot.setPreferredSize(new Dimension(180,30));
        listCatBot.setActionCommand("listarCategorias");
        listCatBot.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        listCatBot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelListCat.add(listCatBot); //adiciona o botao ao painel

        panelOut = new JPanel(new FlowLayout(FlowLayout.CENTER));
        outBottom = new JButton(" Voltar");
        outBottom.setPreferredSize(new Dimension(180,30));
        outBottom.setActionCommand("voltar");
        outBottom.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        outBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelOut.add(outBottom); //adiciona o botao ao painel

        //adicionar os paineis ao painel principal
        panelMenu.add(panelListProd);
        panelMenu.add(panelListCat);
        panelMenu.add(panelOut);

        //adicionar os paineis principais ao contentor
        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(panelMenu, BorderLayout.CENTER);
        cont.add(new JPanel(), BorderLayout.SOUTH);

        this.setSize(450,290);
        this.setLocationRelativeTo(null);
        this.setTitle("Produtos");
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
            case "listarProdutos": ListProduct listProduct = new ListProduct(); Logs.addMov("Listar Produtos"); break;
            case "listarCategorias": ListCategory.createFrame(DadosProdutos.listarCategorias()); Logs.addMov("Listar Categorias"); break;
            case "inserirStock": InsertStock insertStock = new InsertStock(); Logs.addMov("Inserir Stock"); break;
            case "criarProduto": InsertProduct insertProduct = new InsertProduct(); Logs.addMov("Criar Produto"); break;
            case "criarCategoria": InsertCategory insertCategory = new InsertCategory(); Logs.addMov("Criar Categoria"); break;
            case "voltar": this.setVisible(false); MainMenu mainMenu = new MainMenu(DadosStatic.Tipo); Logs.addMov("Voltar"); break;
            default:
                Aviso.showMessage("Nao implementado", "Aviso", "error"); break;
        }

    }
}
