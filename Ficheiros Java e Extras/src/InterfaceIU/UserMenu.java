package InterfaceIU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe responsavel pela administracao dos utilizadores do sistema, apresenta o menu com as suas opcoes
 * @author Jorge Martins e Rodrigo Duro
 */
public class UserMenu extends JFrame implements ActionListener {

    private Container cont;
    private JPanel panelTop, panelMenu, panelCreateUserBottom, panelChangeCurData, panelChangeDataUser;
    private JButton UserBottom, ChangeCurDataBottom, ChangeDataUserBottom;

    /**
     * Construtor da classe que chama o metodo de construcao grafica da interface
     * @param tipo tipo de utilizador
     */
    public UserMenu(String tipo) {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        if(tipo.equals("Gestor")) {
            menuAdmin();
        }
        else {

        }
    }

    /**
     * Metodo de construcao grafica da interface, contem os elementos necessarios para o efeito
     */
    private void menuAdmin(){
        cont = getContentPane();
        cont.setLayout(new BorderLayout());

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER)); //para preencher e ficar no meio
        panelTop.add(new JLabel("Menu Utilizadores"));

        panelMenu = new JPanel(new GridLayout(9,1));

        panelCreateUserBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        UserBottom = new JButton(" Criar Utilizadores ");
        UserBottom.setPreferredSize(new Dimension(180,30));
        UserBottom.setActionCommand("criarUtilizadores");
        UserBottom.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        UserBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelCreateUserBottom.add(UserBottom); //adiciona o botao ao painel

        panelChangeCurData = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ChangeCurDataBottom = new JButton(" Alterar Dados Conta Corrente ");
        ChangeCurDataBottom.setPreferredSize(new Dimension(180,30));
        ChangeCurDataBottom.setActionCommand("alterarMeusDados");
        ChangeCurDataBottom.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        ChangeCurDataBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelChangeCurData.add(ChangeCurDataBottom); //adiciona o botao ao painel

        panelChangeDataUser = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ChangeDataUserBottom = new JButton(" Alterar Dados de Utilizador ");
        ChangeDataUserBottom.setPreferredSize(new Dimension(180,30));
        ChangeDataUserBottom.setActionCommand("alterarDadosUtilizador");
        ChangeDataUserBottom.addActionListener(this); //vai ficar à escuta de evento ou seja clique
        ChangeDataUserBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelChangeDataUser.add(ChangeDataUserBottom); //adiciona o botao ao painel

        //adicionar os paineis ao painel principal
        panelMenu.add(panelCreateUserBottom);
        panelMenu.add(panelChangeCurData);


        //adicionar os paineis principais ao contentor
        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(panelMenu, BorderLayout.CENTER);
        cont.add(new JPanel(), BorderLayout.SOUTH);

        this.setSize(350,300);
        this.setLocationRelativeTo(null);
        this.setTitle("Utilizadores");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * Metodo responsavel pela gestao dos eventos da classe
     * @param e evento gerado pela classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
