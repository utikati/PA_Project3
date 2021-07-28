package InterfaceIU;

import AcessoBD.DadosProdutos;
import Aviso.Aviso;
import Produtos.Categoria;
import Produtos.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe responsavel pela insercao do stock de um produto já existente
 * @author Jorge Martins e Rodrigo Duro
 */
public class InsertStock extends JFrame implements ActionListener {
    private Container cont;
    private JPanel panelTop, panelMenu, panelInsertCat;
    private JTextField skuField, stockField;
    private JButton insertCat;

    /**
     * Construtor responsavel pela construcao grafica da interface.
     */
    public InsertStock(){
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        cont = getContentPane();
        cont.setLayout(new BorderLayout());

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER)); //para preencher e ficar no meio
        panelTop.add(new JLabel("Inserir Stock"));

        JPanel tagSku = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagSku.add(new JLabel("Sku do Produto"));

        JPanel tagStock = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagStock.add(new JLabel("Stock a Inserir"));
        // fim tag
        JPanel stockBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        stockField = new JTextField(15);
        skuField.setToolTipText("Insira o stock a adicionar ao produto");
        stockBox.add(stockField);

        JPanel skuBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        skuField = new JTextField(15);
        skuField.setToolTipText("Insira o SKU do produto para adicionar stock");
        skuBox.add(skuField);

        panelMenu = new JPanel(new GridLayout(2,2));
        panelMenu.add(tagSku);
        panelMenu.add(skuBox);

        panelMenu.add(tagStock);
        panelMenu.add(stockBox);

        panelInsertCat = new JPanel(new FlowLayout(FlowLayout.CENTER));
        insertCat = new JButton(" Inserir Stock");
        insertCat.setPreferredSize(new Dimension(180,30));
        insertCat.setActionCommand("inserir");
        insertCat.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        insertCat.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelInsertCat.add(insertCat); //adiciona o botao ao painel


        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(panelMenu, BorderLayout.CENTER);
        cont.add(panelInsertCat, BorderLayout.SOUTH);

        this.setSize(450,290);
        this.setLocationRelativeTo(null);
        this.setTitle("Produto");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * Metodo responsavel pela gestao dos eventos da classe
     * @param e evento gerado dentro da classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        switch (op){
            case "inserir": if(stockField.getText().equals("") || skuField.getText().equals("")){
                Aviso.showMessage("Campo Vazio", "Aviso", "error");
            }else{
                if(DadosProdutos.pesquisaProduto(Integer.parseInt(skuField.getText())).getSku() != Integer.parseInt(skuField.getText())){
                    Aviso.showMessage("Sku nao existente", "Aviso", "error");
                }else {
                    Produto product = DadosProdutos.pesquisaProduto(Integer.parseInt(skuField.getText()));
                    int qtd = product.getQuantidade();
                    product.setQuantidade(Integer.parseInt(stockField.getText()) + qtd);
                    DadosProdutos.updateProduto(product);
                    cleanerFields();
                    Aviso.showMessage("Stock actualizado com sucesso!", "Aviso", "error");
                }
            }
        }

    }

    /**
     * Metodo de limpeza dos campos do formulario
     */
    public void cleanerFields() {
        stockField.setText("");
        skuField.setText("");
    }
}
