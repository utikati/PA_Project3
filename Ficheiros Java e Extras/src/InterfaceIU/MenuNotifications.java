package InterfaceIU;

import AcessoBD.DadosNotificacao;
import AcessoBD.DadosUtilizadores;
import DadosEstaticos.DadosStatic;
import PedidosNotificacoes.Notificacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe responsavel pelas notificacoes do utilizadores
 * @author Jorge Martins e Rodrigo Duro
 */
public class MenuNotifications extends JFrame implements ActionListener {

    private Container cont;
    private JPanel mainPanel, panelTop, panelBot;
    private JButton nextNot;
    private JLabel label;
    private ArrayList<Notificacao> not;
    private int index = 0;

    /**
     * Construtor da classe com os elementos graficos para construcao da interface grafica
     * @param notification arraylist de notificacoes
     * @param index numero de notificacoes
     */
    public MenuNotifications(ArrayList<Notificacao> notification, int index){
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        this.index = index;
        this.not = notification;
        cont = getContentPane();
        cont.setLayout(new BorderLayout());

        mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTop.add(new JLabel("Notificacao"));

        label = new JLabel(not.get(index).getDescricao());
        mainPanel.add(label);

        panelBot = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nextNot = new JButton("Proxima notificacao");
        nextNot.setPreferredSize(new Dimension(180, 30));
        nextNot.setActionCommand("nextNot");
        nextNot.addActionListener(this);
        nextNot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelBot.add(nextNot);

        cont.add(panelTop, BorderLayout.NORTH);
        cont.add(mainPanel, BorderLayout.CENTER);
        cont.add(panelBot, BorderLayout.SOUTH);

        this.setSize(550, 190);
        this.setLocationRelativeTo(null);
        this.setTitle("Notificacoes");
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * Metodo de gestao de eventos da classe
     * @param e evento gerado pela classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("nextNot")){
            DadosNotificacao.deleteNot(not.get(index), DadosUtilizadores.getIdUser(DadosStatic.Login));
            index++;
            if (index < not.size()) {
                MenuNotifications menu = new MenuNotifications(not, index);
            }else{
                MainMenu menu = new MainMenu(DadosStatic.Tipo);
            }
            this.dispose();
        }
    }
}
