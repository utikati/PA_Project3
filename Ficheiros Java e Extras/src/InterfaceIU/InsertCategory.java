package InterfaceIU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import AcessoBD.DadosProdutos;
import Aviso.*;
import Produtos.Categoria;

/**
 * Classe responsavel por insercao da Categoria
 * @author Jorge Martins e Rodrigo Duro
 */
public class InsertCategory extends JFrame implements ActionListener {
    private Container cont;
    private JPanel panelTop, panelMenu, panelInsertCat;
    private JTextField classField, desField;
    private JButton insertCat;

    /**
     * Construtor da classe que impulsiona a construção da interface grafica
     */
    public InsertCategory(){
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        cont = getContentPane();
        cont.setLayout(new BorderLayout());

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER)); //para preencher e ficar no meio
        panelTop.add(new JLabel("Inserir Categoria"));

        JPanel tagDes = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagDes.add(new JLabel("Designacao"));

        JPanel tagClass = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagClass.add(new JLabel("Classificacao"));

        JPanel desBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        desField = new JTextField(15);
        desField.setToolTipText("Insira Designacao da Categoria");
        desBox.add(desField);

        JPanel classBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        classField = new JTextField(15);
        classField.setToolTipText("Insira a Classe da Categoria");
        classBox.add(classField);

        panelMenu = new JPanel(new GridLayout(2,2));
        panelMenu.add(tagDes);
        panelMenu.add(desBox);

        panelMenu.add(tagClass);
        panelMenu.add(classBox);

        panelInsertCat = new JPanel(new FlowLayout(FlowLayout.CENTER));
        insertCat = new JButton(" Inserir Categoria");
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
        this.setTitle("Categoria");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * Metodo de gestao de eventos da classe
     * @param e evento gerado pela class
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        switch (op){
            case "inserir": if(desField.getText().equals("") || classField.getText().equals("")){
                Aviso.showMessage("Campo Vazio", "Aviso", "error");
            }else{
                if(DadosProdutos.pesquisaCategoria(desField.getText()).getDesignacao().equals(desField.getText())){
                    Aviso.showMessage("Designacao Existente", "Aviso", "error");
                }else {
                    DadosProdutos.adicionarCategoria(new Categoria(desField.getText(), classField.getText()));
                    Aviso.showMessage("Categoria Adicionada com Sucesso", "Aviso", "info");
                    cleanerFields();
                }
            }
        }

    }

    /**
     * Metodo usado para limpeza de campos
     */
    public void cleanerFields() {
        desField.setText("");
        classField.setText("");
    }
}
