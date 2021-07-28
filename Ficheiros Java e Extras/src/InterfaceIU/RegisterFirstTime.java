package InterfaceIU;

import AcessoBD.DadosUtilizadores;
import Aviso.Aviso;
import Utilizadores.Utilizadores;
import Sistema.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Metodo responsavel pela criação do primeiro utilizador do sistema sendo este obrigatoriamente um gestor de sistema
 * @author Jorge Martins e Rodrigo Duro
 */
public class RegisterFirstTime extends JFrame implements ActionListener{

	private Container container;
	private JPanel geralPanel, titlePanel, bottomPanel, filePanel, subNorthPanel1, subNorthPanel2, northPanel; //cada painel vai conter as informações para cada especificidade
	private JButton registerBottom, fileBotton;
	private JTextField nameField, loginField, emailField, obsField;
	private JPasswordField passwordField;
	private JLabel imgLabel;


	private Image image = Toolkit.getDefaultToolkit().getImage("./Img/placeholderFoto.jpg");
	private Image scale = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	private String path = "./Img/placeholderFoto.jpg";


	/**
	 * Construtor da classe com os elementos graficos necessarios para a criacao de um interface grafico
	 */
	public RegisterFirstTime() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

		container = getContentPane();
		container.setLayout(new BorderLayout());
		subNorthPanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		subNorthPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		northPanel = new JPanel(new GridLayout(2, 1));

		filePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		fileBotton = new JButton("Selecionar Foto de Perfil");
		fileBotton.setActionCommand("fotoPerfil");
		fileBotton.addActionListener(this);
		fileBotton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		filePanel.add(fileBotton);

		imgLabel = new JLabel(new ImageIcon(scale));

		subNorthPanel1.add(imgLabel);
		subNorthPanel2.add(filePanel);
		northPanel.add(subNorthPanel1);
		northPanel.add(subNorthPanel2);

		//grids que vao ser usadas
		GridLayout grids = new GridLayout(8, 2);
		grids.setHgap(2); //espaçamento horizontal entre as "linhas"
		grids.setVgap(2);

		geralPanel = new JPanel(grids);

		//FINAL DA CONSTRUÇÃO DAS GRIDS QUE DEPOIS VAO SER PREENCHIDAS COM OS PAINEIS


		//criar labels e fields
		//ETIQUETAS PARA OS CAMPOS
		JPanel tagName = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
		tagName.add(new JLabel("Nome"));

		JPanel tagLogin = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
		tagLogin.add(new JLabel("Login"));

		JPanel tagPassword = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
		tagPassword.add(new JLabel("Password"));

		JPanel tagEmail = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
		tagEmail.add(new JLabel("Email"));

		JPanel tagObs = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
		tagObs.add(new JLabel("Observacoes"));

		//CAMPOS A PREENCHER

		JPanel nameBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
		nameField = new JTextField(15);
		nameField.setToolTipText("Insira o Nome");
		nameBox.add(nameField);

		JPanel logBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
		loginField = new JTextField(15);
		loginField.setToolTipText("Insira o seu Login(tem de ser Unico)");
		logBox.add(loginField);

		JPanel passBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passwordField = new JPasswordField(15);
		passwordField.setToolTipText("Insira a sua password");
		passBox.add(passwordField);

		JPanel emailBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		emailField = new JTextField(15);
		emailField.setToolTipText("Insira o seu email xxxx@xxxx.com");
		emailBox.add(emailField);

		JPanel obsBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		obsField = new JTextField(15);
		obsField.setToolTipText("Insira as suas observacoes");
		obsBox.add(obsField);


		//adicionar ao painel com a info geral
		geralPanel.add(tagName);
		geralPanel.add(nameBox);

		geralPanel.add(tagLogin);
		geralPanel.add(logBox);

		geralPanel.add(tagPassword);
		geralPanel.add(passBox);

		geralPanel.add(tagEmail);
		geralPanel.add(emailBox);

		geralPanel.add(tagObs);
		geralPanel.add(obsBox);

		bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		registerBottom = new JButton("Submeter Registo");
		registerBottom.setActionCommand("registar");
		registerBottom.addActionListener(this);
		registerBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bottomPanel.add(registerBottom);



		container.add(northPanel, BorderLayout.NORTH);
		container.add(geralPanel, BorderLayout.CENTER);
		container.add(bottomPanel, BorderLayout.SOUTH);


		this.setSize(390,300);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setTitle("Registo Primeiro Utilizador");
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Metodo de gestao de eventos da classe
	 * @param e evento gerado pela classe
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("registar")) {
			String[] data = takeDataFields();
			if (data[0].equals("") || data[1].equals("") || data[2].equals("") || data[3].equals("") || obsField.getText().equals("")) {
				Aviso.showMessage("Algum campo vazio", "Aviso Erro Registo", "error");
			} else {
				if (DadosUtilizadores.verificaLogin(data[1]) == null || DadosUtilizadores.verificaLogin(data[1]).length() > 0) {
					Aviso.showMessage("Login já existe", "Aviso Erro Registo", "error");
				} else {
					if (DadosUtilizadores.verificaEmail(data[3]).equals(data[3]) || !checkEmail(data[3])) {
						Aviso.showMessage("Email ja existe ou mal inserido", "Aviso Erro Registo", "error");
					} else {
						if (data[4].equals("Gestor")) {
								Utilizadores user = new Utilizadores(data[0], data[1], data[2], "activo", data[3], data[4]);
								user.setObs(obsField.getText());
								user.setFoto(saveImgFile());
								DadosUtilizadores.adicionaUtilizador(user);
								cleanerFields();
								Aviso.showMessage("Utilizador adicionado com sucesso", "Utilizador Adicionado", "info");
								JavaMail.sendMail(data[1]);
								this.setVisible(false);
								LoginMenu loginMenu = new LoginMenu();
							}
						}
					}
				}
			}
		if(e.getActionCommand().equals("fotoPerfil")){
			JFileChooser fileChooser = new JFileChooser();
			int as = fileChooser.showOpenDialog(null);
			if(as == JFileChooser.APPROVE_OPTION){
				File file = fileChooser.getSelectedFile();

				try{
					path = file.getAbsolutePath();
					image = Toolkit.getDefaultToolkit().getImage(path);
					scale = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
					imgLabel.setIcon(new ImageIcon(scale));
				}catch (Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}

	/**
	 * Metodo de verificacao para salvar o caminho para a imagem na base de dados
	 * @return sting com o caminho da imagem para guardar base de dados
	 */
	private String saveImgFile() {
		if (path.equals("./Img/placeholderFoto.jpg")) {
			return "./Img/placeholderFoto.jpg";
		} else {
			return path;
		}
	}


	/**
	 * Metodo responsavel por retirar os elementos inseridos nos campos de registo
	 * @return array de string com os dados dos campos
	 */
	public String[] takeDataFields() {
		String [] dataInfo = new String[11];
		dataInfo[0] = nameField.getText();
		dataInfo[1] = loginField.getText();
		dataInfo[2] = String.valueOf(passwordField.getPassword());
		dataInfo[3] = emailField.getText();
		dataInfo[4] = "Gestor";

		return dataInfo;
	}

	/**
	 * Metodo de limpeza dos campos inseridos
	 */
	public void cleanerFields() {
		loginField.setText("");
		nameField.setText("");
		loginField.setText("");
		emailField.setText("");
		passwordField.setText("");
	}

	/**
	 * Verifica se email está dentro da expressao regular
	 * @param aEmail string do email inserido
	 * @return boolean resultado da verificacao
	 * Verifica se email está dentro da expressao regular
	 */
	private static boolean checkEmail(String aEmail) {

		boolean send = aEmail.matches("^[a-zA-Z0-9.]+@[a-z0-9]+\\.[a-z]+");//procura de a-z em pequeno e grande, numero e pode ter ponto, seguido de arroba com numero ou letra seguido de ponto e depois caracteres..


		return send;
	}

}
