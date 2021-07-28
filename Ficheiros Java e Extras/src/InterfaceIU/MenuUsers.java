package InterfaceIU;

import AcessoBD.DadosUtilizadores;
import Aviso.Aviso;
import DadosEstaticos.DadosStatic;
import Utilizadores.Logs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * Menu de Administrador para tratar dados relativos a utilizadores
 * @author Jorge Martins e Rodrigo Duro
 */
public class MenuUsers extends JFrame implements ActionListener {

    private Container cont;
    private JPanel panelTop, panelMenu, userCrePanel, userChangePanel, userListAllPanel, userListByNamePanel, userRequestDeletePanel, userNewAccPanel, userOutPanel, userLogPanel;
    private JButton userCreBottom, userChangeBottom, userListAllBottom, userListByNameBottom, userRequestBottom, userNewAccBottom, userOutBottom, userLogBottom;

    /**
     * Construtor dos elementos graficos para a interface grafica do Menu de Administracao de Utilizadores
     */
    public MenuUsers(){
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

            cont = getContentPane();
            cont.setLayout(new BorderLayout());

            panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER)); //para preencher e ficar no meio
            panelTop.add(new JLabel("Administrar Utilizadores"));

            panelMenu = new JPanel(new GridLayout(7, 1));

            userLogPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            userLogBottom = new JButton("Logs de Utilizadores");
            userLogBottom.setPreferredSize(new Dimension(180, 30));
            userLogBottom.setActionCommand("logUtilizador");
            userLogBottom.addActionListener(this);
            userLogBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
            userLogPanel.add(userLogBottom);


            userCrePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            userCreBottom = new JButton("Criar Utilizador");
            userCreBottom.setPreferredSize(new Dimension(180, 30));
            userCreBottom.setActionCommand("criarUtilizador");
            userCreBottom.addActionListener(this);
            userCreBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
            userCrePanel.add(userCreBottom);

            userChangePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            userChangeBottom = new JButton("Alterar Dados Utilizador");
            userChangeBottom.setPreferredSize(new Dimension(180, 30));
            userChangeBottom.setActionCommand("alterarUtilizador");
            userChangeBottom.addActionListener(this);
            userChangeBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
            userChangePanel.add(userChangeBottom);

            userListAllPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            userListAllBottom = new JButton("Listar todos Utilizadores");
            userListAllBottom.setPreferredSize(new Dimension(180, 30));
            userListAllBottom.setActionCommand("listarTodos");
            userListAllBottom.addActionListener(this);
            userListAllBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
            userListAllPanel.add(userListAllBottom);

            userListByNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            userListByNameBottom = new JButton("Listagens Condicionadas");
            userListByNameBottom.setPreferredSize(new Dimension(180, 30));
            userListByNameBottom.setActionCommand("listarCondicao");
            userListByNameBottom.addActionListener(this);
            userListByNameBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
            userListByNamePanel.add(userListByNameBottom);

            userRequestDeletePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            userRequestBottom = new JButton("Pedidos de Inactivacao");
            userRequestBottom.setPreferredSize(new Dimension(180, 30));
            userRequestBottom.setActionCommand("inactivacao");
            userRequestBottom.addActionListener(this);
            userRequestBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
            userRequestDeletePanel.add(userRequestBottom);

            userNewAccPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            userNewAccBottom = new JButton("Novas Contas");
            userNewAccBottom.setPreferredSize(new Dimension(180, 30));
            userNewAccBottom.setActionCommand("novasContas");
            userNewAccBottom.addActionListener(this);
            userNewAccBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
            userNewAccPanel.add(userNewAccBottom);

            userOutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            userOutBottom = new JButton("Voltar");
            userOutBottom.setPreferredSize(new Dimension(180, 30));
            userOutBottom.setActionCommand("voltar");
            userOutBottom.addActionListener(this);
            userOutBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
            userOutPanel.add(userOutBottom);

            //adicionar os paineis ao painel principal
            panelMenu.add(userCrePanel);
            panelMenu.add(userChangePanel);
            panelMenu.add(userListAllPanel);
            panelMenu.add(userListByNamePanel);
            panelMenu.add(userRequestDeletePanel);
            panelMenu.add(userNewAccPanel);
            panelMenu.add(userLogPanel);

            //adicionar os paineis principais ao contentor
            cont.add(panelTop, BorderLayout.NORTH);
            cont.add(panelMenu, BorderLayout.CENTER);
            cont.add(userOutPanel, BorderLayout.SOUTH);

            this.setSize(350, 490);
            this.setLocationRelativeTo(null);
            this.setTitle("Utilizadores");
            this.setResizable(true);
            this.setVisible(true);
    }

    /**
     * Metodo de gestao de eventos da classe
     * @param e evento gerado pela classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String option = e.getActionCommand();
        switch (option){
            case "voltar": MainMenu mainMenu = new MainMenu(DadosStatic.Tipo); this.dispose(); Logs.addMov("Voltou atras"); break;
            case "criarUtilizador": RegisterMenu registerMenu = new RegisterMenu(); Logs.addMov("Criar Utilizador"); break;
            case "listarTodos": MenuListMaster.createFrame(DadosUtilizadores.listarTodosUtilizadores()); Logs.addMov("Listar todos Utilizadores"); break;
            case "listarCondicao": MenuListSearchUser menuListSearchUser = new MenuListSearchUser(); Logs.addMov("Listar e Pesquisar"); break;
            case "alterarUtilizador": MenuChUser menuChUser = new MenuChUser(); Logs.addMov("Alterar Dados de Utilizadores"); break;
            case "novasContas": MenuNewAcc menuNewAcc = new MenuNewAcc(); Logs.addMov("Novas Contas"); break;
            case "inactivacao": MenuRequestDelete menuRequestDelete = new MenuRequestDelete(); Logs.addMov("Inactivação de Contas"); break;
            case "logUtilizador": MenuLogUsers menuLogUsers = new MenuLogUsers(); Logs.addMov("Verificar Logs"); break;
            default:
                Aviso.showMessage("Ainda nao implementado", "Aviso", "error"); break;
        }

    }
}
