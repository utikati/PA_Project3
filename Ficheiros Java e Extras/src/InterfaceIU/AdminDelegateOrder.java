package InterfaceIU;

import AcessoBD.DadosEncomendas;
import AcessoBD.DadosNotificacao;
import AcessoBD.DadosUtilizadores;
import Aviso.Aviso;
import Produtos.Encomenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Zona onde o administrador vai delegar a encomenda para um armazenista
 * @author Jorge Martins e Rodrigo Duro
 */
public class AdminDelegateOrder extends JFrame implements ActionListener {
    private Container cont;
    private JPanel mainPanel, panelTop, bottonSerchPanel, southPanel;
    private JTextField orderField, storeField;
    private JButton searchOrd;


    /**
     * Construtor da classe responsavel por dar o trigger na construcao grafica
     */
    public AdminDelegateOrder(){
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        cont = getContentPane();
            cont.setLayout(new BorderLayout());
            mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panelTop.add(new JLabel("Lista de Encomendas para delegar"));

            JPanel tagOrder = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
            tagOrder.add(new JLabel("Identificador de Encomenda"));

            JPanel tagStore = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
            tagStore.add(new JLabel("Login Armazenista"));

            JPanel orderBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
            orderField = new JTextField(15);
            orderField.setToolTipText("Insira o Identificador da Encomenda");
            orderBox.add(orderField);

            JPanel storeBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
            storeField = new JTextField(15);
            storeField.setToolTipText("Insira o Login do Armazenista");
            storeBox.add(storeField);

            bottonSerchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            searchOrd = new JButton("Delegar Encomenda");
            searchOrd.setActionCommand("delegarEncomenda");
            searchOrd.addActionListener(this);
            searchOrd.setCursor(new Cursor(Cursor.HAND_CURSOR));
            bottonSerchPanel.add(searchOrd);


            mainPanel.add(new ListMasterOrder(DadosEncomendas.listarEncomendasCondicao("WHERE ESTADO_ENCOMENDA = 'aceite'")));

            southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            southPanel.add(tagOrder);
            southPanel.add(orderBox);
            southPanel.add(tagStore);
            southPanel.add(storeBox);
            southPanel.add(bottonSerchPanel);



            cont.add(panelTop, BorderLayout.NORTH);
            cont.add(mainPanel, BorderLayout.CENTER);
            cont.add(southPanel, BorderLayout.SOUTH);

            this.setSize(950, 490);
            this.setLocationRelativeTo(null);
            this.setTitle("Encomendas aceites");
            this.setResizable(true);
            this.setVisible(true);

        }


    /**
     * Metodo responsavel por receber os eventos da classe
     * @param e evento gerado na classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        switch (op) {
            case "delegarEncomenda":
                Encomenda eOrder = DadosEncomendas.objEncomenda(orderField.getText());
                if(DadosEncomendas.idEncomenda(orderField.getText()) > 0) {
                    if(eOrder.getEstado().equals("aceite")) {
                        String login = storeField.getText();
                        if(!DadosUtilizadores.verificaLogin(login).equals("")) {
                            if(DadosUtilizadores.pesquisaLogin(login).getTipo().equals("Funcionario") && DadosUtilizadores.pesquisaFuncionarios(login).getEspecializacao().equals("Armazenista")) {
                                eOrder.setIdArmazenista(Integer.parseInt(DadosUtilizadores.verificaLogin(login)));
                                eOrder.setEstado("preparada");
                                DadosEncomendas.updateEncomenda(eOrder);
                                Aviso.showMessage("Encomenda Delegada com sucesso", "Aviso", "info");
                                DadosNotificacao.addNotification("A encomenda com o identificador " +eOrder.getIdentificadorEncomenda() + " foi lhe  delegada!!!", DadosUtilizadores.listarUtilizadoresCondicao(" ID_UTILIZADOR = '" + eOrder.getIdArmazenista() + "' "));

                                this.dispose();
                                AdminDelegateOrder adminDelegateOrder = new AdminDelegateOrder();
                            }else {
                                Aviso.showMessage("Login de armazenista não existe", "Aviso", "error");
                            }
                        }else {
                            Aviso.showMessage("Login não existe", "Aviso", "error");
                        }
                    }else {
                        Aviso.showMessage("Encomenda para aceitar existe", "Aviso", "error");
                    }
                }else {
                    Aviso.showMessage("Encomenda não existe", "Aviso", "error");
                } break;
        }

    }
}
