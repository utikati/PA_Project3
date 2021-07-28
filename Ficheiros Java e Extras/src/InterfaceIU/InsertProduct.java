package InterfaceIU;

import AcessoBD.DadosProdutos;
import Aviso.Aviso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import Produtos.*;

/**
 * Class responsavel pela insercao do produto na base de dados
 * @author Jorge Martins e Rodrigo Duro
 */
public class InsertProduct extends JFrame implements ActionListener {
    private Container cont;
    private JPanel panelTop, panelMenu, panelInsertProd;
    private JTextField categoryField, prodDescField, manufacterField, amountField, priceField, skuField, batchField /*lote */, dateProdutionField, obsField;
    private JButton insertProd;
    private Date productionDate;

    /**
     * Construtor responsavel pela construcao do interface grafico
     */
    public InsertProduct() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        cont = getContentPane();
        cont.setLayout(new BorderLayout());
        panelMenu = new JPanel(new GridLayout(8, 2));

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER)); //para preencher e ficar no meio
        panelTop.add(new JLabel("Criar Produto"));
        //tags dos produtos ------------------------------------------------------------------------

        JPanel tagDesc = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagDesc.add(new JLabel("Descricao Produto"));

        JPanel tagManufacter = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tagManufacter.add(new JLabel("Fabricante do Produto"));

        JPanel tagAmount = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tagAmount.add(new JLabel("Quantidade de Produto"));

        JPanel tagPrice = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tagPrice.add(new JLabel("Preco"));

        JPanel tagBatch = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tagBatch.add(new JLabel("Lote"));

        JPanel tagDataProdution = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tagDataProdution.add(new JLabel("Data Producao do Produto dd/mm/yyyy"));

        JPanel tagDesCategory = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tagDesCategory.add(new JLabel("Designacao da Categoria"));

        JPanel tagObs = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tagObs.add(new JLabel("Observacoes"));

        //------------------------------------------------------------------------------------------------------

        //BOX OU CAMPOS DE PREENCHIMENTO DOS PRODUTOS-------------------------------------------------------------------------------------
        JPanel prodDesBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        prodDescField = new JTextField(15);
        prodDescField.setToolTipText("Descricao do Produto");
        prodDesBox.add(prodDescField);

        JPanel manufacterBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        manufacterField = new JTextField(15);
        manufacterField.setToolTipText("Insira Fabricante do Produto");
        manufacterBox.add(manufacterField);

        JPanel amountBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        amountField = new JTextField(15);
        amountField.setToolTipText("Insira a quantidade de Stock inicial");
        amountBox.add(amountField);

        JPanel priceBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        priceField = new JTextField(15);
        priceField.setToolTipText("Insira o preco por unidade do Produto");
        priceBox.add(priceField);

        JPanel batchBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        batchField = new JTextField(15);
        batchField.setToolTipText("Insira o Lote do Produto");
        batchBox.add(batchField);

        JPanel dateProdBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        dateProdutionField = new JTextField(15);
        dateProdutionField.setToolTipText("Insira a Data de Producao do Produto");
        dateProdBox.add(dateProdutionField);

        JPanel desCategoryBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        categoryField = new JTextField(15);
        categoryField.setToolTipText("Insira a Categoria(designacao)");
        desCategoryBox.add(categoryField);

        JPanel obsBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        obsField = new JTextField(15);
        obsField.setToolTipText("Insira Observacoes Sobre o produto");
        obsBox.add(obsField);

        //FINAL DA INSERCAO DOS CAMPOS---------------------------------------------------------------------------------

        //INICIO DE INSERIR NO PAINEL PRINCIPAL

        panelMenu.add(tagDesc);
        panelMenu.add(prodDesBox);

        panelMenu.add(tagManufacter);
        panelMenu.add(manufacterBox);

        panelMenu.add(tagAmount);
        panelMenu.add(amountBox);

        panelMenu.add(tagPrice);
        panelMenu.add(priceBox);

        panelMenu.add(tagBatch); //lote
        panelMenu.add(batchBox);

        panelMenu.add(tagDataProdution);
        panelMenu.add(dateProdBox);

        panelMenu.add(tagDesCategory);
        panelMenu.add(desCategoryBox);

        panelMenu.add(tagObs);
        panelMenu.add(obsBox);

        panelInsertProd = new JPanel(new FlowLayout(FlowLayout.CENTER));
        insertProd = new JButton(" Inserir Produto");
        insertProd.setPreferredSize(new Dimension(180, 30));
        insertProd.setActionCommand("inserir");
        insertProd.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        insertProd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelInsertProd.add(insertProd); //adiciona o botao ao painel


        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(panelMenu, BorderLayout.CENTER);
        cont.add(panelInsertProd, BorderLayout.SOUTH);

        this.setSize(470, 490);
        this.setLocationRelativeTo(null);
        this.setTitle("Produto");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * Metodo responsavel pela gestao do eventos da classe
     * @param e evento gerado pela classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        switch (op) {
            case "inserir": {
                if (categoryField.getText().equals("") || prodDescField.getText().equals("") || manufacterField.getText().equals("")
                        || amountField.getText().equals("") || priceField.getText().equals("") || batchField.getText().equals("")
                        || dateProdutionField.getText().equals("") || obsField.getText().equals("")) {
                    Aviso.showMessage("Erro - Algum Campo Vazio", "Aviso", "error");
                } else {
                    if (!DadosProdutos.pesquisaCategoria(categoryField.getText()).getDesignacao().equals(categoryField.getText())) {
                        Aviso.showMessage("Categoria não existe", "Aviso", "error");
                    } else {
                        if (!checkData(dateProdutionField.getText())) {
                            Aviso.showMessage("Formato de data nao aceite", "Aviso", "error");
                        } else {

                            Random rnd = new Random();
                            int sku = 0;
                            boolean state = false;
                            do { //criar um random para o sku
                                state = false;
                                sku = rnd.nextInt(1000001);
                                if (sku == 0) {
                                    sku = 1;
                                }
                                if (DadosProdutos.idProduto(sku) > 0) { //se for maior que 0 quer dizer que já existe
                                    state = true;
                                }
                            } while (state);

                            SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
                            formatData.setLenient(false);

                            try {

                                productionDate = formatData.parse(dateProdutionField.getText());

                            } catch (ParseException e3) {
                                try {

                                    productionDate = formatData.parse("01/01/1970");

                                } catch (ParseException e2) {
                                    e2.printStackTrace();
                                }
                            }

                                Produto product = new Produto(prodDescField.getText(), manufacterField.getText(), Integer.parseInt(amountField.getText()), Float.parseFloat(priceField.getText()), sku, batchField.getText(), productionDate, DadosProdutos.pesquisaCategoria(categoryField.getText()));
                                product.setObs(obsField.getText());
                                DadosProdutos.adicionarProduto(product);
                                Aviso.showMessage("Produto Adicionado com Sucesso", "Aviso", "info");
                                cleanerFields();
                            }
                        }
                    }
                }
            }

        }

    /**
     * Metodo responsavel pela verificacao da Data se esta no formato aceitavel do software
     * @param dataInicioString string com o data de inicio da string
     * @return booleano com resultado da verificacao
     */
    private boolean checkData (String dataInicioString){
            Date dataInicio = new Date();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            formatoData.setLenient(false); //para verificar se o formato da data é valido tipo 30/02/2021
            try {
                dataInicio = formatoData.parse(dataInicioString);
            } catch (ParseException e) {
                try {
                    dataInicio = formatoData.parse("01/01/1970");
                } catch (ParseException e2) {
                    e.printStackTrace();
                }
                return false; //data nao aceite
            }
            try {
                Date antes = formatoData.parse("01/01/1970");
                Date agora = new Date();
                if (dataInicio.after(antes) && dataInicio.before(agora)) {
                    return true; //unica forma de ser aceite
                } else {
                    return false; //nao aceite
                }
            } catch (ParseException e3) {
                e3.printStackTrace();
            }
            return false;
        }

    /**
     * Metodo de limpeza dos campos do formulario
     */
    private void cleanerFields(){
        categoryField.setText("");
        prodDescField.setText("");
        manufacterField.setText("");
        amountField.setText("");
        priceField.setText("");
        batchField.setText("");
        dateProdutionField.setText("");
        obsField.setText("");
        }
    }


