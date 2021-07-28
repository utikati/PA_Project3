package InterfaceIU;

import AcessoBD.DadosEncomendas;
import AcessoBD.DadosNotificacao;
import AcessoBD.DadosProdutos;
import AcessoBD.DadosUtilizadores;
import Aviso.Aviso;
import DadosEstaticos.DadosStatic;
import Produtos.Encomenda;
import Produtos.Encomenda_Produto;
import Produtos.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Classe grafica responsavel pelo Admin/Gestor aceitar os pedidos de encomendas dos clientes
 * @author Jorge Martins e Rodrigo Duro
 */
public class AdminAcceptOrder extends JFrame implements ActionListener {

    private Container cont;
    private JPanel mainPanel, panelTop, bottonSerchPanel, southPanel, bottonrejectPanel;
    private JTextField orderField;
    private JButton searchOrd, rejOrd;

    /**
     * Construtor da classe usado como trigger da mesma.
     */
    public AdminAcceptOrder(){
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        cont = getContentPane();
        cont.setLayout(new BorderLayout());

        mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop.add(new JLabel("Lista de Pedidos de Encomendas"));

        JPanel tagOrder = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagOrder.add(new JLabel("Identificador de Encomenda"));

        JPanel orderBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        orderField = new JTextField(15);
        orderField.setToolTipText("Insira Indentificador da Encomenda");
        orderBox.add(orderField);

        bottonSerchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchOrd = new JButton("Aceitar Encomenda");
        searchOrd.setActionCommand("aceitarEncomenda");
        searchOrd.addActionListener(this);
        searchOrd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonSerchPanel.add(searchOrd);

        bottonrejectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rejOrd = new JButton("Rejeitar Encomenda");
        rejOrd.setActionCommand("rejeitarEncomenda");
        rejOrd.addActionListener(this);
        rejOrd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonrejectPanel.add(rejOrd);

        mainPanel.add(new ListMasterOrder(DadosEncomendas.listarEncomendasCondicao("WHERE ESTADO_ENCOMENDA = 'iniciada'")));

        southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.add(tagOrder);
        southPanel.add(orderBox);
        southPanel.add(bottonSerchPanel);
        southPanel.add(bottonrejectPanel);


        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(mainPanel, BorderLayout.CENTER);
        cont.add(southPanel, BorderLayout.SOUTH);

        this.setSize(950, 490);
        this.setLocationRelativeTo(null);
        this.setTitle("Pedidos de Encomendas");
        this.setResizable(true);
        this.setVisible(true);

    }

    /**
     * Classe que recebe os eventos que sao tratados dentro de si propria
     * @param e evento gerado por algum botao
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        switch (op){
            case "aceitarEncomenda": if(orderField.getText().equals("")){
                                        Aviso.showMessage("Erro - Campo Vazio", "Aviso", "error");
                                    }else{
                                        if(DadosEncomendas.idEncomenda(orderField.getText()) > 0){
                                            Encomenda order = DadosEncomendas.objEncomenda(orderField.getText());
                                            if(order.getEstado().equals("iniciada")){
                                                order.setEstado("aceite");
                                                order.setDataAceitacao(new Date());
                                                order.setIdUtilizador(DadosUtilizadores.getIdUser(DadosStatic.Login));
                                                DadosEncomendas.updateEncomenda(order);
                                                DadosNotificacao.deleteNotByDescription("A encomenda com Identificador: "+order.getIdentificadorEncomenda()+" foi criada e espera ser aceite!!!");
                                                DadosNotificacao.addNotification("A sua encomenda com Identificador: "+order.getIdentificadorEncomenda()+" foi aceite!!!",DadosUtilizadores.listarUtilizadoresCondicao(" ID_UTILIZADOR = '"+order.getIdCliente()+"'"));
                                                Aviso.showMessage("Encomenda foi aceite", "Aviso", "info");
                                                this.dispose(); // para dar o "refresh" na tabela
                                                AdminAcceptOrder adminAcceptOrder = new AdminAcceptOrder();
                                            }else{
                                                Aviso.showMessage("Encomenda não esta no estado iniciada","Aviso", "error");
                                            }

                                        }else{
                                            Aviso.showMessage("Identificador não existe","Aviso", "error");
                                        }
                                    } break;
            case "rejeitarEncomenda":  if(orderField.getText().equals("")){
                                            Aviso.showMessage("Erro - Campo Vazio", "Aviso", "error");
                                        }else{
                                            if(DadosEncomendas.idEncomenda(orderField.getText()) > 0){
                                                Encomenda order = DadosEncomendas.objEncomenda(orderField.getText());
                                                if(order.getEstado().equals("iniciada")){
                                                    order.setEstado("rejeitada");
                                                    order.setIdUtilizador(DadosUtilizadores.getIdUser(DadosStatic.Login));
                                                    DadosEncomendas.updateEncomenda(order);
                                                    DadosNotificacao.addNotification("A sua encomenda com Identificador: "+order.getIdentificadorEncomenda()+" foi Rejeitada!!!",DadosUtilizadores.listarUtilizadoresCondicao(" ID_UTILIZADOR = '"+order.getIdCliente()+"'"));
                                                    autoActStockRejct(DadosEncomendas.listaEncomenda_Produto("WHERE ID_ENCOMENDA = "+DadosEncomendas.idEncomenda(order.getIdentificadorEncomenda())+" "));
                                                    Aviso.showMessage("Encomenda foi rejeitada", "Aviso", "info");
                                                    this.dispose();
                                                    AdminAcceptOrder adminAcceptOrder = new AdminAcceptOrder();
                                                }else{
                                                    Aviso.showMessage("Encomenda não esta no estado iniciada","Aviso", "error");
                                                }

                                            }else{
                                                Aviso.showMessage("Identificador não existe","Aviso", "error");
                                            }
                                        } break;
        }

    }

    /**
     * Metodo de atualizacao do stock quando a encomenda é rejeitada, assim o stock fica novamente disponivel para uso
     * @param encomendaProduto listagem das tabelas de encomenda_produto que conte os varios produtos de uma encomenda
     */
    public static void autoActStockRejct(ArrayList<Encomenda_Produto> encomendaProduto) {
        Iterator<Encomenda_Produto> table = encomendaProduto.iterator();
        Encomenda_Produto auxiliar;
        while(table.hasNext()) {
            auxiliar = table.next();
            Produto product = DadosProdutos.pesquisaProdutoPorID(auxiliar.getIdProduto());
            product.setQuantidade(product.getQuantidade() + auxiliar.getQuantidadeProduto());
            DadosProdutos.updateProduto(product);
        }

    }
}
