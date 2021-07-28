package InterfaceIU;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Utilizadores.*;

/**
 * Classe responsavel pelos Logs de utilizador
 * @author Jorge Martins e Rodrigo Duro
 * Classe responsavel pelos Logs de utilizador
 */
public class ListLogs extends JPanel{


	private JPanel panelTop, userPanelTable;
	private JTable userTable;
	private JScrollPane scroll;


	/**
	 * Construtor da classe com os metodos graficos para gerar o interface
	 * @param listLogs arralist com os logs de utilizadores
	 */
	public ListLogs(ArrayList <Logs> listLogs) {
		String [][] userlist = stringConverterUser(listLogs);
		setLayout(new BorderLayout());
		
		panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelTop.add(new JLabel("Logs"));
		
		userPanelTable = new JPanel(new FlowLayout(FlowLayout.CENTER));
		String[] colname = {"Login", "Accao", "Data"};
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
	 * @param listLogs
	 * @return String [][]
	 */
	private static String[][] stringConverterUser(ArrayList <Logs> listLogs){ // como a JTable só aceita objectos [][] para ficar parecido ao meu listador master do primeiro projecto converti em string bidimensional
		String [][] sendList = new String[listLogs.size()][5];
		for (int i = 0; i < listLogs.size(); ++i) {
			sendList[i][0] = " " + listLogs.get(i).getLogin();
			sendList[i][1] = " " + listLogs.get(i).getAcaoLog();
			sendList[i][2] = " " + listLogs.get(i).getData();
		}
		return sendList;
	}

	/**
	 * Metodo de criacao de JFrame pois o construtor cria um JPanel
	 * @param users arraylist com os logs para escrever no monitor
	 * @return JFrame criado neste metodo com o JPanel do construtor
	 */
	public static JFrame createFrame(ArrayList<Logs> users){
		JFrame jframe = new JFrame();
		ListLogs menuListMaster = new ListLogs(users);

		jframe.setSize(950,400);
		jframe.setLocationRelativeTo(null);
		jframe.setTitle("Listagem Logs");
		jframe.setResizable(false);
		jframe.setVisible(true);

		jframe.add(menuListMaster);
		return jframe;
	}
}
