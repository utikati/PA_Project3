package InterfaceIU;

import AcessoBD.DadosEncomendas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Aviso.*;

/**
 * Classe responsavel para demonstrar ao utilizador as encomendas disponiveis para gerar uma fatura
 * @author Jorge Martins e Rodrigo Duro
 * Classe responsavel para demonstrar ao utilizador as encomendas disponiveis para gerar uma fatura
 */
public class InvoiceOrder extends JFrame implements ActionListener {

    private Container cont;
    private JPanel mainPanel, panelTop, bottonSerchPanel, southPanel;
    private JTextField orderField, storeField;
    private JButton searchOrd;

    /**
     * Metodo construtor da interface grafica da lista com encomendas confirmadas, prontas para imprimir fatura
     */
    public InvoiceOrder(){
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        cont = getContentPane();
        cont.setLayout(new BorderLayout());
        mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop.add(new JLabel("Lista de Encomendas Confirmadas/Finalizadas"));

        JPanel tagOrder = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagOrder.add(new JLabel("Identificador de Encomenda"));


        JPanel orderBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        orderField = new JTextField(15);
        orderField.setToolTipText("Insira o Identificador da Encomenda para criar fatura");
        orderBox.add(orderField);


        bottonSerchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchOrd = new JButton("Criar Fatura");
        searchOrd.setActionCommand("pesquisarEncomenda");
        searchOrd.addActionListener(this);
        searchOrd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonSerchPanel.add(searchOrd);


        mainPanel.add(new ListMasterOrder(DadosEncomendas.listarEncomendasCondicao("WHERE ESTADO_ENCOMENDA = 'confirmada'")));

        southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.add(tagOrder);
        southPanel.add(orderBox);
        southPanel.add(bottonSerchPanel);

        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(mainPanel, BorderLayout.CENTER);
        cont.add(southPanel, BorderLayout.SOUTH);

        this.setSize(950, 490);
        this.setLocationRelativeTo(null);
        this.setTitle("Encomendas Confirmadas/Finalizadas");
        this.setResizable(true);
        this.setVisible(true);

    }

    /**
     * Metodo responsavel pela gestao de eventos da classe
     * @param e evento gerado pela classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("pesquisarEncomenda")){
            if(orderField.getText().equals("")){
                Aviso.showMessage("Campo Vazio", "Aviso", "error");
            }else{
                if(DadosEncomendas.idEncomenda(orderField.getText()) > 0){
                    ListInvoiceOrder.createFrame(DadosEncomendas.listarEncomendasCondicao("WHERE ESTADO_ENCOMENDA = 'confirmada' AND IDENTIFICADOR_ENCOMENDA = "+orderField.getText()+" ;"));
                }else{
                    Aviso.showMessage("Identificador não existe", "Aviso", "error");
                }
            }
        }

    }
}
