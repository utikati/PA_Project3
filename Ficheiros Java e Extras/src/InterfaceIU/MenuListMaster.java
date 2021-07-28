package InterfaceIU;

import Utilizadores.Utilizadores;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Metodo de listagem de utilizadores do sistema
 * @author Jorge Martins e Rodrigo Duro
 */
public class MenuListMaster extends JPanel{


	private JPanel panelTop, userPanelTable, filterPanel;
	private JTable userTable;
	private JScrollPane scroll;


	/**
	 * Construtor da classe Listar os utilizadores atraves de um arraylist, contem os elementos para criacao de uma interface grafica, constroi JPanel
	 * @param listUsers arraylist de utilizadores
	 */
	public MenuListMaster(ArrayList <Utilizadores> listUsers) {
		String [][] userlist = stringConverterUser(listUsers);
		setLayout(new BorderLayout());
		
		panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelTop.add(new JLabel("Lista de Utilizadores"));
		
		userPanelTable = new JPanel(new FlowLayout(FlowLayout.CENTER));
		String[] colname = {"Nome", "Login", "Email", "Estado", "Tipo de Conta"};
		userTable = new JTable(userlist, colname);
		userTable.setEnabled(false);
		userTable.setAutoCreateRowSorter(true);
		userTable.setPreferredScrollableViewportSize(new Dimension(900,250));
		scroll = new JScrollPane(userTable); //inserir dentro do scroll e não o contrario --'
		userPanelTable.add(scroll);

		add(panelTop, BorderLayout.NORTH);
		add(userPanelTable, BorderLayout.CENTER);

	}

	/**
	 * Conversor de arrayList para String bidimensional, para imitar o que foi usado no projecto 1 do Jorge como listador Master
	 * @param listUsers
	 * @return String [][]
	 */
	private static String[][] stringConverterUser(ArrayList <Utilizadores> listUsers){ // como a JTable só aceita objectos [][] para ficar parecido ao meu listador master do primeiro projecto converti em string bidimensional
		String [][] sendList = new String[listUsers.size()][5];
		for (int i = 0; i < listUsers.size(); ++i) {
			sendList[i][0] = " " + listUsers.get(i).getNome();
			sendList[i][1] = " " + listUsers.get(i).getLogin();
			sendList[i][2] = " " + listUsers.get(i).getEmail();
			sendList[i][3] = " " + listUsers.get(i).getEstado();
			sendList[i][4] = " " + listUsers.get(i).getTipo();
		}
		return sendList;
	}

	/**
	 * Metodo usado para criar um JFrame enquanto que a classe no construtor apenas cria JPanel
	 * @param users arraylist de utilizadores
	 * @return JFrame com o JPanel do construtor
	 */
	public static JFrame createFrame(ArrayList<Utilizadores> users){
		JFrame jframe = new JFrame();
		MenuListMaster menuListMaster = new MenuListMaster(users);

		jframe.setSize(950,400);
		jframe.setLocationRelativeTo(null);
		jframe.setTitle("Lista de Utilizadores do Sistema");
		jframe.setResizable(false);
		jframe.setVisible(true);

		jframe.add(menuListMaster);
		return jframe;
	}
}
