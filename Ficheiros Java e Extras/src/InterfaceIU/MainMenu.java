package InterfaceIU;

import AcessoBD.DadosNotificacao;
import AcessoBD.DadosUtilizadores;
import DadosEstaticos.DadosStatic;
import Utilizadores.Funcionarios;
import Aviso.*;
import InterfacePrincipal.*;
import Utilizadores.Logs;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Classe responsavel pelo menu principal do software
 * @author Jorge Martins e Rodrigo Duro
 */
public class MainMenu extends JFrame implements ActionListener, MouseListener {


	private Container cont;
	private JPanel panelTop, panelMenu, panelUserBottom,mainTopPanel,showNotPanel, panelProductBottom, panelOrderBottom, panelInvoiceBottom, panelLogOut, panelMyDataBottom, infoEx;
	private JButton UserBottom, productBottom, orderBottom, invoiceBottom, exitBottom, myDataBottom;
	private Border border;
	private JLabel labelEx;

	/**
	 * Construtor da classe que na medida do tipo de utilizador a ter feito o login serão gerados JPanel diferentes de menus
	 * @param tipo string com o tipo de utilizador recebido
	 */
	public MainMenu(String tipo) {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

		labelEx = new JLabel("Ultima Data de Execucao: "+DadosStatic.UltimaData+" Numero execucoes: "+DadosStatic.execucoes+" ");
		infoEx = new JPanel(new FlowLayout(FlowLayout.CENTER));
		infoEx.add(labelEx);
		Logs.addMov("Iniciou Programa");
		if(tipo.equals("Gestor")) {
			menuAdmin();
			Logs.addMov("Entrou Menu Principal");
		}
		else {
			if (tipo.equals("Cliente")) {
				menuClient();
				Logs.addMov("Entrou Menu Cliente");
			} else { //se nao é funcionario
				menuEmp(); // este menu tem apenas a opção de pedidos ao estatefa o que muda é dentro do seu submenu que sera uma opção para armazenista e outra para estafeta
				Logs.addMov("Entrou Menu Funcionario");
			}
		}
	}

	/**
	 * Metodo com o menu do Administrador / Gestor do programa
	 */
	private void menuAdmin(){
		cont = getContentPane();
		cont.setLayout(new BorderLayout());
		if(DadosNotificacao.getNotTotal(DadosStatic.Login) == 0){
			mainTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			showNotPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			border = BorderFactory.createLineBorder(Color.RED, 2);
			showNotPanel.setBorder(border);
			showNotPanel.add(new JLabel("Nao tem notificacoes"));
			mainTopPanel.add(showNotPanel);

		}else {
			mainTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			showNotPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			border = BorderFactory.createLineBorder(Color.RED, 2);
			showNotPanel.setBorder(border);
			showNotPanel.add(new JLabel("Tem "+DadosNotificacao.getNotTotal(DadosStatic.Login)+" notificacoes"));
			mainTopPanel.add(showNotPanel);
			showNotPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			showNotPanel.addMouseListener(this);

		}

		panelMenu = new JPanel(new GridLayout(7, 1));
		panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER)); //para preencher e ficar no meio
		panelTop.add(new JLabel("Menu Principal"));

		panelMyDataBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		myDataBottom = new JButton(" Meus Dados ");
		myDataBottom.setPreferredSize(new Dimension(180,30));
		myDataBottom.setActionCommand("menuMeusDados");
		myDataBottom.addActionListener(this); //vai ficar à escuta de evento ou seja clique
		myDataBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelMyDataBottom.add(myDataBottom); //adiciona o botao ao painel

		panelUserBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		UserBottom = new JButton(" Utilizadores ");
		UserBottom.setPreferredSize(new Dimension(180,30));
		UserBottom.setActionCommand("menuUtilizadores");
		UserBottom.addActionListener(this); //vai ficar à escuta de evento ou seja clique
		UserBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelUserBottom.add(UserBottom); //adiciona o botao ao painel

		panelProductBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		productBottom = new JButton("Produtos");
		productBottom.setPreferredSize(new Dimension(180, 30));
		productBottom.setActionCommand("menuProdutos"); //id da acção deste comando
		productBottom.addActionListener(this);
		productBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelProductBottom.add(productBottom);

		panelOrderBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		orderBottom = new JButton("Encomendas");
		orderBottom.setPreferredSize(new Dimension(180, 30));
		orderBottom.setActionCommand("menuEncomendas"); //id da acção deste comando
		orderBottom.addActionListener(this);
		orderBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelOrderBottom.add(orderBottom);


		panelInvoiceBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		invoiceBottom = new JButton("Faturas");
		invoiceBottom.setPreferredSize(new Dimension(180, 30));
		invoiceBottom.setActionCommand("menuFaturas");
		invoiceBottom.addActionListener(this);
		invoiceBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelInvoiceBottom.add(invoiceBottom);

		panelLogOut = new JPanel(new FlowLayout(FlowLayout.CENTER));
		exitBottom = new JButton("Log Out");
		exitBottom.setPreferredSize(new Dimension(180, 30));
		exitBottom.setActionCommand("logOut");
		exitBottom.addActionListener(this);
		exitBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelLogOut.add(exitBottom);


		//adicionar os paineis ao painel principal
		panelMenu.add(panelTop);
		panelMenu.add(panelMyDataBottom);
		panelMenu.add(panelUserBottom);
		panelMenu.add(panelProductBottom);
		panelMenu.add(panelOrderBottom);
		panelMenu.add(panelInvoiceBottom);
		panelMenu.add(panelLogOut);

		//adicionar os paineis principais ao contentor
		cont.add(mainTopPanel, BorderLayout.NORTH);
		cont.add(panelMenu, BorderLayout.CENTER);
		cont.add(infoEx, BorderLayout.SOUTH);

		this.setSize(450,390);
		this.setLocationRelativeTo(null);
		this.setTitle("Principal");
		this.setResizable(true);
		this.setVisible(true);
	}

	/**
	 * Metodo com o Menu principal de um utilizador Cliente
	 */
	private void menuClient(){
		cont = getContentPane();
		cont.setLayout(new BorderLayout());

		if(DadosNotificacao.getNotTotal(DadosStatic.Login) == 0){
			mainTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			showNotPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			border = BorderFactory.createLineBorder(Color.RED, 2);
			showNotPanel.setBorder(border);
			showNotPanel.add(new JLabel("Nao tem notificacoes"));
			mainTopPanel.add(showNotPanel);

		}else {
			mainTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			showNotPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			border = BorderFactory.createLineBorder(Color.RED, 2);
			showNotPanel.setBorder(border);
			showNotPanel.add(new JLabel("Tem "+DadosNotificacao.getNotTotal(DadosStatic.Login)+" notificacoes"));
			mainTopPanel.add(showNotPanel);
			showNotPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			showNotPanel.addMouseListener(this);

		}

		panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER)); //para preencher e ficar no meio
		panelTop.add(new JLabel("Menu Principal"));

		panelMenu = new JPanel(new GridLayout(5,1));

		panelMyDataBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		myDataBottom = new JButton(" Meus Dados ");
		myDataBottom.setPreferredSize(new Dimension(180,30));
		myDataBottom.setActionCommand("menuMeusDados");
		myDataBottom.addActionListener(this); //vai ficar à escuta de evento ou seja clique
		myDataBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelMyDataBottom.add(myDataBottom); //adiciona o botao ao painel

		panelProductBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		productBottom = new JButton("Produtos");
		productBottom.setPreferredSize(new Dimension(180, 30));
		productBottom.setActionCommand("menuProdutos"); //id da acção deste comando
		productBottom.addActionListener(this);
		productBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelProductBottom.add(productBottom);

		panelOrderBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		orderBottom = new JButton("Encomendas");
		orderBottom.setPreferredSize(new Dimension(180, 30));
		orderBottom.setActionCommand("menuEncomendas"); //id da acção deste comando
		orderBottom.addActionListener(this);
		orderBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelOrderBottom.add(orderBottom);

		panelLogOut = new JPanel(new FlowLayout(FlowLayout.CENTER));
		exitBottom = new JButton("Log Out");
		exitBottom.setPreferredSize(new Dimension(180, 30));
		exitBottom.setActionCommand("logOut");
		exitBottom.addActionListener(this);
		exitBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelLogOut.add(exitBottom);


		//adicionar os paineis ao painel principal
		panelMenu.add(panelMyDataBottom);
		panelMenu.add(panelProductBottom);
		panelMenu.add(panelOrderBottom);

		panelMenu.add(panelLogOut);

		//adicionar os paineis principais ao contentor
		cont.add(mainTopPanel, BorderLayout.NORTH);
		cont.add(panelMenu, BorderLayout.CENTER);
		cont.add(infoEx, BorderLayout.SOUTH);

		this.setSize(350,300);
		this.setLocationRelativeTo(null);
		this.setTitle("Principal");
		this.setResizable(true);
		this.setVisible(true);
	}

	/**
	 * Metodo com o Menu Principal de um Funcionario
	 */
	public void menuEmp() {
		Funcionarios emp = DadosUtilizadores.pesquisaFuncionarios(DadosStatic.Login);
		if(emp.getEspecializacao() != null) {
			cont = getContentPane();
			cont.setLayout(new BorderLayout());

			if(DadosNotificacao.getNotTotal(DadosStatic.Login) == 0){
				mainTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				showNotPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				border = BorderFactory.createLineBorder(Color.RED, 2);
				showNotPanel.setBorder(border);
				showNotPanel.add(new JLabel("Nao tem notificacoes"));
				mainTopPanel.add(showNotPanel);

			}else {
				mainTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				showNotPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				border = BorderFactory.createLineBorder(Color.RED, 2);
				showNotPanel.setBorder(border);
				showNotPanel.add(new JLabel("Tem "+DadosNotificacao.getNotTotal(DadosStatic.Login)+" notificacoes"));
				mainTopPanel.add(showNotPanel);
				showNotPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				showNotPanel.addMouseListener(this);

			}

			panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER)); //para preencher e ficar no meio
			panelTop.add(new JLabel("Menu Principal"));

			panelMenu = new JPanel(new GridLayout(5, 1));

			panelMenu.add(panelTop);

			panelMyDataBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
			myDataBottom = new JButton(" Meus Dados ");
			myDataBottom.setPreferredSize(new Dimension(180,30));
			myDataBottom.setActionCommand("menuMeusDados");
			myDataBottom.addActionListener(this); //vai ficar à escuta de evento ou seja clique
			myDataBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
			panelMyDataBottom.add(myDataBottom); //adiciona o botao ao painel

			panelProductBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
			productBottom = new JButton("Produtos");
			productBottom.setPreferredSize(new Dimension(180, 30));
			productBottom.setActionCommand("menuProdutos"); //id da acção deste comando
			productBottom.addActionListener(this);
			productBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
			panelProductBottom.add(productBottom);

			panelOrderBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
			orderBottom = new JButton("Encomendas");
			orderBottom.setPreferredSize(new Dimension(180, 30));
			orderBottom.setActionCommand("menuEncomendas"); //id da acção deste comando
			orderBottom.addActionListener(this);
			orderBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
			panelOrderBottom.add(orderBottom);

			panelLogOut = new JPanel(new FlowLayout(FlowLayout.CENTER));
			exitBottom = new JButton("Log Out");
			exitBottom.setPreferredSize(new Dimension(180, 30));
			exitBottom.setActionCommand("logOut");
			exitBottom.addActionListener(this);
			exitBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
			panelLogOut.add(exitBottom);


			//adicionar os paineis ao painel principal
			panelMenu.add(panelMyDataBottom);
			panelMenu.add(panelProductBottom);
			panelMenu.add(panelOrderBottom);


			panelMenu.add(panelLogOut);

			//adicionar os paineis principais ao contentor
			cont.add(mainTopPanel, BorderLayout.NORTH);
			cont.add(panelMenu, BorderLayout.CENTER);
			cont.add(infoEx, BorderLayout.SOUTH);

			this.setSize(350, 390);
			this.setLocationRelativeTo(null);
			this.setTitle("Principal");
			this.setResizable(true);
			this.setVisible(true);
		}
	}

	/**
	 * Metodo de gestao dos eventos da classe
	 * @param e evento gerado pela classe
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String option = e.getActionCommand();
		switch (option){
			case "menuFaturas": InvoiceOrder invoiceOrder = new InvoiceOrder(); Logs.addMov("Entrou na opca Faturas"); break;
			case "menuEncomendas": MenuOrders menuOrders = new MenuOrders(); this.dispose(); Logs.addMov("Entrou Menu Encomendas"); break;
			case "menuProdutos": this.dispose(); MenuProducts menuProducts = new MenuProducts(); Logs.addMov("Entrou Menu Produtos"); break;
			case "menuMeusDados": MenuMyData menuMyData = new MenuMyData(); Logs.addMov("Entrou Menu Meus Dados"); break;
			case "menuUtilizadores": MenuUsers menuUsers = new MenuUsers(); this.dispose(); Logs.addMov("Entrou Menu Utilizadores"); break;
			case "logOut": Principal.endTime(); Aviso.showMessage("Adeus "+DadosStatic.Login+" !!", "Aviso", "info"); Logs.addMov("Terminou Programa"); this.setVisible(false); break;
			default: Aviso.showMessage("Nao implementado", "Aviso", "error");
		}

	}

	/**
	 * Metodo de gestao dos eventos de clique do rato
	 * @param e evento gerado pela classe
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(showNotPanel)){
			MenuNotifications menuNotifications = new MenuNotifications(DadosNotificacao.listarNotificacoesCondicao("WHERE ID_UTILIZADOR = '"+DadosUtilizadores.getIdUser(DadosStatic.Login)+"'"),0);
			this.setVisible(false);
			this.dispose();
		}
	}
	/**
	 * Metodo de gestao dos eventos de clique do rato
	 * @param e evento gerado pela classe
	 */
	@Override
	public void mousePressed(MouseEvent e) {

	}
	/**
	 * Metodo de gestao dos eventos de clique do rato
	 * @param e evento gerado pela classe
	 */
	@Override
	public void mouseReleased(MouseEvent e) {

	}
	/**
	 * Metodo de gestao dos eventos de clique do rato
	 * @param e evento gerado pela classe
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource().equals(showNotPanel)){
			showNotPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		}
	}
	/**
	 * Metodo de gestao dos eventos de clique do rato
	 * @param e evento gerado pela classe
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource().equals(showNotPanel)){
			showNotPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2));
		}
	}
}
