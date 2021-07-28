package InterfaceIU;

import AcessoBD.DadosEncomendas;
import AcessoBD.DadosUtilizadores;
import AcessoBD.FicheiroProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Aviso.*;
import DadosEstaticos.DadosStatic;
import Utilizadores.Utilizadores;
import Utilizadores.*;

/**
 * Classe responsavel pelo login do utilizador ao software
 * @author Jorge Martins e Rodrigo Duro
 */
public class LoginMenu extends JFrame implements ActionListener {

	private Container cont; //o contentor que vai ter tudo
	private JPanel panel1, panelLogin; //paineis
	private JTextField textLogin;
	private JPasswordField textPassword;
	private JButton bottonAutentication, bottonRegister;
	private JMenuBar navBar; //bar da janela
	private JMenu menuConfig;
	private JMenuItem configDataBase;
	private MainMenu mainMenu;


	/**
	 * Construtor da classe com elementos graficos para a construcao da interface grafica
	 */
	public LoginMenu() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));
		FicheiroProperties.leituraDadosProp();//leitura dos dados do ficheiro, os dados por defeito das variaveis estaticas são modificados
		if(checkConnection()) { //basicamente ele só para baixo caso tenha as conexoes correctas para puder chamar o verificatabela etc
			 DadosStatic.execucoes = DadosUtilizadores.contaExecucoes();
			DadosStatic.UltimaData = DadosUtilizadores.ultimaData(); //tenho de inserir antes de começar os movimentos para retirar as informações correctas
			if(DadosStatic.UltimaData.equals("null")) {
				DadosStatic.UltimaData = "Sem Data a Demonstrar";
			}
			if (DadosUtilizadores.verificaTabela() == 0) {
				this.setVisible(false);
				Aviso.showMessage("Base de Dados sem Utilizadores inicie como Gestor", "Aviso Base de Dados", "info");
				RegisterFirstTime firstTime = new RegisterFirstTime();

			} else {
				//criação do contentor
				cont = getContentPane();
				cont.setLayout(new BorderLayout());
				//-------------------------------------
				//criacao do painel que vai ficar dentro do contentor
				panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
				panel1.add(new JLabel("Autenticacao")); //titulo do painel **este painel ocupa tudo
				//----------------------------------------------------
				//navBar como em web é o menu
				navBar = new JMenuBar();
				menuConfig = new JMenu("Ligacao BD"); //nome que vai aparecer no menu
				menuConfig.setMnemonic(KeyEvent.VK_C); //inserir um evento de clique nesta opcao
				menuConfig.setToolTipText("Configuracao da Ligacao a Base de Dados");


				configDataBase = new JMenuItem("Ligacao a Base de Dados"); //item do menu ou seja da navbar
				configDataBase.setMnemonic(KeyEvent.VK_B); //inserir um evento a esta accao
				configDataBase.addActionListener(this);

				menuConfig.add(configDataBase); //adicionar o item a este menu Drop
				navBar.add(menuConfig); //adionar o menu de configuração dentro da navbar ou JMenu
				setJMenuBar(navBar); //activar o menu com tudo inserido acima também para ele aparecer
				//------------------------------------------------------------------------------------------
				//grid com o conteudo de login e password ou criaçao do espaço
				GridLayout gridsL = new GridLayout(3, 2); //criação de uma gridlayout com 3 linha e 2 colunas
				gridsL.setHgap(3); //espaçamento horizontal entre as "linhas"
				gridsL.setVgap(3); //espaçamento vertical entre as "colunas"
				panelLogin = new JPanel(gridsL); //objecto painel com as grids configuradas acima

				// estes texto ficam encostados a direita no limite da linha vertical da sua grid
				JPanel panel_Login = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //pequeno painel apenas que vai ser para ter a etiqueta
				panel_Login.add(new JLabel("Login")); //inserir a informacao para que depois se perceba que é para inserir login

				JPanel panel_Password = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				panel_Password.add(new JLabel("Password"));
				//espaço para o utilizador inserir os dados
				JPanel panelBoxLogin = new JPanel(new FlowLayout(FlowLayout.LEFT)); //maximo à esquerda para ficar proxima da sua etiqueta
				textLogin = new JTextField(13); //tamanho da box de texto
				textLogin.setToolTipText("Insira o seu login");
				panelBoxLogin.add(textLogin); //adicionar a box no objecto JPanel panelBoxLogin

				JPanel panelBoxPassword = new JPanel(new FlowLayout(FlowLayout.LEFT));
				textPassword = new JPasswordField(13);
				textPassword.setToolTipText("Insira a sua Password");
				panelBoxPassword.add(textPassword);

				//inserção dos botoes na ultima linha da grid para parecer que estão ao meio usa-se o flow left para um e o rigth para outro
				JPanel painelBotaoLogin = new JPanel(new FlowLayout(FlowLayout.LEFT));
				bottonAutentication = new JButton(" Login ");
				bottonAutentication.setActionCommand("login"); //inserir uma acção para este botão e não ser um dead botão
				bottonAutentication.addActionListener(this);
				bottonAutentication.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ao passar o rato por cima ele demonstra estar pressionado
				painelBotaoLogin.add(bottonAutentication); //adicionar o botao ao painel

				JPanel painelBotaoRegisto = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				bottonRegister = new JButton(" Registo ");
				bottonRegister.setActionCommand("registo");
				bottonRegister.addActionListener(this);
				bottonRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
				painelBotaoRegisto.add(bottonRegister);

				//fase final após todos estarem configurados insererem-se nos paineis pais
				//inserir login e sua box
				panelLogin.add(panel_Login); //importante a ordem é sempre da esquerda para a direita :3
				panelLogin.add(panelBoxLogin);
				//inserir password e sua box
				panelLogin.add(panel_Password);
				panelLogin.add(panelBoxPassword);
				//inserir os botoes
				panelLogin.add(painelBotaoRegisto);
				panelLogin.add(painelBotaoLogin);

				cont.add(panel1, BorderLayout.NORTH); //adicionar este painel na zona mais acima
				cont.add(panelLogin, BorderLayout.CENTER); //adicionar este painel para que fique no centro

				//configurações gerais do Frame
				this.setSize(370, 250);
				this.setLocationRelativeTo(null);
				this.setTitle("Login no Sistema");
				this.setResizable(false); //para não deconfigurar ao aumentar xD
				this.setVisible(true);
			}
		}

	}

	/**
	 * Metodo que retorna o login inserido no seu campo
	 * @return string login
	 */
	public String getLogin() {
		return textLogin.getText();
	}

	/**
	 * metodo que retorna a password inserida no seu campo
	 * @return string com a password
	 */
	public String getPassword() {
		return String.valueOf(textPassword.getPassword());
	}

	/**
	 * Metodo que devolve configuracao de acesso a BD
	 * @return JMenuItem
	 */
	public JMenuItem getConfigBD() {
		return configDataBase;
	}

	/**
	 * Classe para limpar os campos
	 */
	public void resetTexto() {
		textLogin.setText("");
		textPassword.setText("");
	}

	/**
	 * Classe que verifica a conexao a base de dados
	 * @return booleano com resultado da verificacao
	 */
	private boolean checkConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://"+DadosStatic.ip+":"+DadosStatic.porto+"/"+DadosStatic.baseDados+"?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC", DadosStatic.utilizador, DadosStatic.pass);
		}catch (SQLException e) {
			Aviso.showMessage("Sem ligacao a base de dados", "Aviso", "error");
			MenuAcessBD menuAcessBD = new MenuAcessBD();
			menuAcessBD.loadAcessData();
			System.out.println("!! SQL Exception !!\n"+e);
			return false;

		} catch (ClassNotFoundException e) {
			Aviso.showMessage("Sem ligação a base de dados", "Aviso", "error");
			MenuAcessBD menuAcessBD = new MenuAcessBD();
			menuAcessBD.loadAcessData();
			return false;
		}
		return true;
	}

	/**
	 * Metodo que gere os eventos da classe
	 * @param e evento gerado pela classe
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(configDataBase)) {
			this.dispose();
			MenuAcessBD menuAcessBD = new MenuAcessBD();
			FicheiroProperties.leituraDadosProp();
			menuAcessBD.loadAcessData();
		}
		if (e.getActionCommand().equals("login")) {
			String login = this.getLogin();
			String pass = this.getPassword();
			if (login.trim().isEmpty() || pass.trim().isEmpty())
				Aviso.showMessage("Algum campo esta vazio!", "Aviso Erro", "error");
			else {
				Utilizadores utilizador = DadosUtilizadores.pesquisaLogin(login);
				if (utilizador.getLogin() != null) {
					if (utilizador.getEstado().equals("rejeitado")) {
						Aviso.showMessage("Conta nao tem acesso", "Aviso Sem acesso", "error");
					} else {
						if (utilizador.getLogin().equals(login) && utilizador.getPass().equals(pass)) {
							if (utilizador.getEstado().equalsIgnoreCase("activo") || utilizador.getEstado().equalsIgnoreCase("pedido")) {
								DadosStatic.UserON = utilizador.getNome();
								DadosStatic.Login = utilizador.getLogin();
								DadosStatic.Tipo = utilizador.getTipo();
								if (utilizador.getTipo().equals("Funcionario")) {
									Funcionarios funcionario = DadosUtilizadores.pesquisaFuncionarios(login);
									DadosStatic.especializacao = funcionario.getEspecializacao();
								}
								Aviso.showMessage("Bem-Vindo " + DadosStatic.Login + " !!", "Bem Vindo", "info");

								this.setVisible(false); //tornar a janela de login escondida pois já não é preciso

								switch(utilizador.getTipo()){
									case "Gestor":{
										DadosEncomendas.EncomendasForaDeTempo();
										MainMenu menu = new MainMenu("Gestor");
									} break;
									case "Funcionario":{
										DadosEncomendas.EncomendasForaDeTempo();
										MainMenu menu = new MainMenu("Funcionario");
									} break;
									case "Cliente":{
										DadosEncomendas.EncomendasForaDeTempo();
										MainMenu menu = new MainMenu("Cliente");
									} break;
								}

							} else {
								Aviso.showMessage("Conta nao tem acesso", "Aviso Sem acesso", "error");
							}
						} else {
							Aviso.showMessage("Utilizador ou Password nao existe!", "Aviso Sem acesso", "error");
						}
					}

				}else{
					Aviso.showMessage("Utilizador ou Password nao existe!", "Aviso Sem acesso", "error");
				}
			}
		}
		if(e.getActionCommand().equals("registo")){
			RegisterMenu registerMenu = new RegisterMenu();
		}
	}
}


