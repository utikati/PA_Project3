package InterfaceIU;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import AcessoBD.*;
import DadosEstaticos.*;
import Sistema.JavaMail;
import Utilizadores.*;

import Aviso.*;
import Utilizadores.Utilizadores;

import javax.swing.*;

/**
 * Classe responsavel pelo registo de utilizadores no sistema (apos registo do primeiro de todos)
 * @author Jorge Martins e Rodrigo Duro
 */
public class RegisterMenu extends JFrame implements ItemListener, ActionListener{

	private Container container;
	private JPanel geralPanel, employePanel, clientPanel, bottomPanel, fatherPanel, obsPanel, filePanel, subNorthPanel1, subNorthPanel2, northPanel; //cada painel vai conter as informações para cada especificidade
	private JButton registerBottom, fileBotton;
	private JTextField nameField, loginField, emailField, fiscalNumberField, contactField, addressField, employeBegDate, obsField;
	private JPasswordField passwordField;
	private JComboBox <String> typeUser, employeSpecialization;
	private JLabel imgLabel;


	private Image image = Toolkit.getDefaultToolkit().getImage("./Img/placeholderFoto.jpg");
	private Image scale = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	private String path = "./Img/placeholderFoto.jpg";

	/**
	 * Construtor da classe com os elementos graficos necessarios para a construcao da interface grafica
	 */
	public RegisterMenu() {
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
		GridLayout grids = new GridLayout(6, 2);
		grids.setHgap(2); //espaçamento horizontal entre as "linhas"
		grids.setVgap(2);

		geralPanel = new JPanel(grids);

		GridLayout grid2nd = new GridLayout(3, 2);
		grids.setHgap(1); //espaçamento horizontal entre as "linhas"
		grids.setVgap(1);
		clientPanel = new JPanel(grid2nd);

		GridLayout grid3nd = new GridLayout(2, 2);
		grids.setHgap(3); //espaçamento horizontal entre as "linhas"
		grids.setVgap(3);
		employePanel = new JPanel(grid3nd);

		GridLayout fatherGrid = new GridLayout(3, 0); //para que todas as grids fiquem no centro dentro de um unico contentor
		grids.setHgap(1);
		fatherPanel = new JPanel(fatherGrid);

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

		JPanel tagType = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
		tagType.add(new JLabel("Tipo de Utilizador"));

		JPanel tagObs = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		tagObs.add(new JLabel("Observacoes"));

		//CAMPOS A PREENCHER

		JPanel nameBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
		nameField = new JTextField(15);
		nameField.setToolTipText("Insira o nome");
		nameBox.add(nameField);

		JPanel logBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
		loginField = new JTextField(15);
		loginField.setToolTipText("Insira o Login");
		logBox.add(loginField);

		JPanel passBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passwordField = new JPasswordField(15);
		passwordField.setToolTipText("Insira a Password");
		passBox.add(passwordField);

		JPanel emailBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		emailField = new JTextField(15);
		emailField.setToolTipText("Insira o email xxxx@xxx.com");
		emailBox.add(emailField);

		JPanel typeBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String [] varTypes = {"Funcionario", "Gestor", "Cliente"};
		typeUser = new JComboBox<String>(varTypes);
		typeUser.setToolTipText("Escolha o Tipo de Utilizador");
		typeUser.addItemListener(this);
		typeBox.add(typeUser);

		JPanel obsBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		obsField = new JTextField(15);
		obsField.setToolTipText("Insira Observacoes");
		obsBox.add(obsField);


		//informações cliente

		//primeiro novamente criar as tags para ter em texto

		JPanel tagFiscal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		tagFiscal.add(new JLabel("Numero Contribuinte"));

		JPanel tagContact = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		tagContact.add(new JLabel("Contacto Utilizador"));

		JPanel tagAddress = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		tagAddress.add(new JLabel("Morada"));

		//criar as boxes para estes elementos

		JPanel fiscalBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		fiscalNumberField = new JTextField(15);
		fiscalNumberField.setToolTipText("Numero fiscal");
		fiscalBox.add(fiscalNumberField);

		JPanel contactBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		contactField = new JTextField(15);
		contactField.setToolTipText("Contacto de Utilizador");
		contactBox.add(contactField);

		JPanel addressBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addressField = new JTextField(15);
		addressField.setToolTipText("Morada de Utilizador");
		addressBox.add(addressField);

		//INICIO DE ADICIONAR OS CAMPOS DE FUNCIONARIO

		JPanel tagBegDate = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		tagBegDate.add(new JLabel("Data de Inicio dd/mm/yyyy"));

		JPanel tagEmployeSpec = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		tagEmployeSpec.add(new JLabel("Especializacao"));

		//criar os fields
		JPanel begDateBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		employeBegDate = new JTextField(15);
		employeBegDate.setToolTipText("Data de inicio do Trabalhador");
		begDateBox.add(employeBegDate);


		JPanel employeSpecBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String []varSpecs = {"Estafeta", "Armazenista"};
		employeSpecialization = new JComboBox<String>(varSpecs);
		employeSpecialization.setToolTipText("Escolha especializacao");
		employeSpecBox.add(employeSpecialization);
		//FIM DE ADICIONAR AS INFO DE FUNCIONARIO

		//adicionar ao painel com a info geral
		geralPanel.add(tagName);
		geralPanel.add(nameBox);

		geralPanel.add(tagLogin);
		geralPanel.add(logBox);

		geralPanel.add(tagPassword);
		geralPanel.add(passBox);

		geralPanel.add(tagEmail);
		geralPanel.add(emailBox);

		geralPanel.add(tagType);
		geralPanel.add(typeBox);

		geralPanel.add(tagObs);
		geralPanel.add(obsBox);

		//adicionar os paineis de cliente

		clientPanel.add(tagFiscal);
		clientPanel.add(fiscalBox);

		clientPanel.add(tagContact);
		clientPanel.add(contactBox);

		clientPanel.add(tagAddress);
		clientPanel.add(addressBox);

		//FIM DE ADICIONAR paineis DE CLIENTE

		employePanel.add(tagBegDate);
		employePanel.add(begDateBox);

		employePanel.add(tagEmployeSpec);
		employePanel.add(employeSpecBox);
		//fim dos paineis de cliente

		fatherPanel.add(geralPanel);
		fatherPanel.add(clientPanel);
		fatherPanel.add(employePanel);

		bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		registerBottom = new JButton("Submeter Registo");
		registerBottom.setActionCommand("registar");
		registerBottom.addActionListener(this);
		registerBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bottomPanel.add(registerBottom);



		container.add(northPanel, BorderLayout.NORTH);
		container.add(fatherPanel, BorderLayout.CENTER);
		container.add(bottomPanel, BorderLayout.SOUTH);


		this.setSize(450,890);
		this.setLocationRelativeTo(null);
		this.setTitle("Registo de Conta");
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Metodo responsavel pela gestao de eventos da classe
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
							if (DadosStatic.Tipo.equals("Gestor")) {
								Utilizadores envio = new Utilizadores(data[0], data[1], data[2], "activo", data[3], data[4]);
								envio.setObs(obsField.getText());
								envio.setFoto(saveImgFile());
								DadosNotificacao.addNotification("O Gestor "+data[1]+" foi criado!!!",DadosUtilizadores.listarUtilizadoresCondicao("TIPO_UTILIZADOR = 'Gestor'"));
								DadosUtilizadores.adicionaUtilizador(envio);
								cleanerFields();
								JavaMail.sendMail(data[1]);

								Aviso.showMessage("Utilizador adicionado com sucesso", "Utilizador Adicionado", "info");
								this.setVisible(false);

							} else {
								Utilizadores envio = new Utilizadores(data[0], data[1], data[2], "espera", data[3], data[4]);
								envio.setObs(obsField.getText());
								envio.setFoto(saveImgFile());
								DadosNotificacao.addNotification("O Gestor "+data[1]+" foi criado e aguarda ser activo!!!",DadosUtilizadores.listarUtilizadoresCondicao("TIPO_UTILIZADOR = 'Gestor'"));
								DadosUtilizadores.adicionaUtilizador(envio);
								cleanerFields();
								JavaMail.sendMail(data[1]);

								Aviso.showMessage("Utilizador adicionado com sucesso", "Utilizador Adicionado", "info");
								this.setVisible(false);
							}
						} else if (data[4].equals("Cliente") || data[4].equals("Funcionario")) {
							if (DadosUtilizadores.verificaContribuinte(Integer.parseInt(data[5])) == 0) {
								String auxFiscal = data[5];
								if (auxFiscal.length() < 9 || Integer.parseInt(data[5]) / 100000000 < 0 || Integer.parseInt(data[5]) / 100000000 > 3) { //divisao inteira para que tenha 1 2 3
									Aviso.showMessage("Contribuinte tem de ter 9 digitos e começa por 1, 2 ou 3", "Erro Contribuinte", "error");
								} else { //aqui continua para adicionar tanto cliente como depois funcionario
									if (checkContact(data[6]).length() > 0) {
										Aviso.showMessage(checkContact(data[6]), "Erro", "error");
									}else{ //quer dizer que o contacto esta nos conformes pode-se adicionar os campos
										if(data[7].equals("")){
											Aviso.showMessage("Campo de morada vazio", "Aviso", "error");
										}else{
											if(data[4].equals("Cliente")){ //todos os campos de cliente estão vistos então pode-se adicionar como cliente se for o caso
												if(DadosStatic.Tipo.equals("Gestor")){
													Clientes client = new Clientes(data[0], data[1], data[2], "activo", data[3], data[4], Integer.parseInt(data[5]), Integer.parseInt(data[6]), data[7]);
													client.setObs(obsField.getText());
													client.setFoto(saveImgFile());
													DadosUtilizadores.adicionaCliente(client);
													cleanerFields();
													JavaMail.sendMail(data[1]);
													Aviso.showMessage("Utilizador adicionado com sucesso", "Utilizador Adicionado", "info");
													this.setVisible(false);
												}else{
													Clientes client = new Clientes(data[0], data[1], data[2], "espera", data[3], data[4], Integer.parseInt(data[5]), Integer.parseInt(data[6]), data[7]);
													client.setObs(obsField.getText());
													client.setFoto(saveImgFile());
													DadosUtilizadores.adicionaCliente(client);
													cleanerFields();
													JavaMail.sendMail(data[1]);
													Aviso.showMessage("Utilizador adicionado com sucesso", "Utilizador Adicionado", "info");
													this.setVisible(false);
												}
											}else{

												if(data[8].equals("") || data[9].equals("")){
													Aviso.showMessage("Algum campo vazio", "Aviso", "error");
												}else {
													if (!checkData(data[8])) {
														Aviso.showMessage("Data nao aceite", "Data nao aceite", "error");
													} else {
														Date envio = new Date();
														SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
														try {
															envio = dateFormat.parse(data[8]);
														} catch (ParseException parseException) {
															parseException.printStackTrace();
														}
														if (DadosStatic.Tipo.equals("Gestor")) {
															Funcionarios employe = new Funcionarios(data[0], data[1], data[2], "activo", data[3], data[4], Integer.parseInt(data[5]), Integer.parseInt(data[6]), data[7], envio, data[9]);
															employe.setObs(obsField.getText());
															employe.setFoto(saveImgFile());
															DadosUtilizadores.adicionaFuncionario(employe);
															JavaMail.sendMail(data[1]);
															Aviso.showMessage("Utilizador adicionado com sucesso", "Utilizador Adicionado", "info");
															this.setVisible(false);
														} else {
															Funcionarios employe = new Funcionarios(data[0], data[1], data[2], "espera", data[3], data[4], Integer.parseInt(data[5]), Integer.parseInt(data[6]), data[7], envio, data[9]);
															employe.setObs(obsField.getText());
															employe.setFoto(saveImgFile());
															DadosUtilizadores.adicionaFuncionario(employe);
															JavaMail.sendMail(data[1]);
															Aviso.showMessage("Utilizador adicionado com sucesso", "Utilizador Adicionado", "info");
															this.setVisible(false);
														}
													}
												}
											}
										}
									}
								}
								} else{
									Aviso.showMessage("Contribuinte ja existe", "Erro - Aviso", "error");
								}
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
	 * Classe responsavel para verificar se a foto escolhida não é a mesma já como placeholder
	 * @return string com o caminho da imagem a guardar na base de dados
	 */
		private String saveImgFile(){
		if(path.equals("./Img/placeholderFoto.jpg")){
			return "./Img/placeholderFoto.jpg";
		}else {
			return path;
		}
	}

	/**
	 * Metodo para que o registo fique com os campos de cliente, remove os campos oriundos do construtor
	 */
	private void windowClient() {
		container.remove(fatherPanel);
		fatherPanel.removeAll();
		fatherPanel.add(geralPanel);
		fatherPanel.add(clientPanel);
		container.add(fatherPanel, BorderLayout.CENTER);
		container.repaint(); //basicamente ele resedenha o contentor
	}
	/**
	 * Metodo para que o registo fique com os campos de gestor, remove os campos oriundos do construtor
	 */
	private void windowManager() {
		container.remove(fatherPanel);
		fatherPanel.removeAll();
		fatherPanel.add(geralPanel);
		container.add(fatherPanel, BorderLayout.CENTER);
		container.repaint(); //basicamente ele resedenha o contentor
	}
	/**
	 * Metodo para que o registo fique com os campos de funcionario, remove os campos oriundos do construtor
	 */
	private void windowEmploye() {
		container.remove(fatherPanel);
		fatherPanel.removeAll();
		fatherPanel.add(geralPanel);
		fatherPanel.add(clientPanel);
		fatherPanel.add(employePanel);
		container.add(fatherPanel, BorderLayout.CENTER);
		container.repaint(); //basicamente ele resedenha o contentor
	}


	/**
	 * Metodo responsavel por ler todos os dados inseridos nos campos de regist
	 * @return array de string com os dados lidos
	 */
	public String[] takeDataFields() {
		String [] dataInfo = new String[11];
		dataInfo[0] = nameField.getText();
		dataInfo[1] = loginField.getText();
		dataInfo[2] = String.valueOf(passwordField.getPassword());
		dataInfo[3] = emailField.getText();
		if(typeUser.getSelectedIndex() == 0) {
			dataInfo[4] = "Funcionario";
			dataInfo[5] = fiscalNumberField.getText();
			dataInfo[6] = contactField.getText();
			dataInfo[7] = addressField.getText();
			dataInfo[8] = employeBegDate.getText();
			dataInfo[9] = employeSpecialization.getItemAt(employeSpecialization.getSelectedIndex());
		}
		else
			if(typeUser.getSelectedIndex() == 1) {
			dataInfo[4] = "Gestor";
		}
		else {
			dataInfo[4] = "Cliente";
			dataInfo[5] = fiscalNumberField.getText();
			dataInfo[6] = contactField.getText();
			dataInfo[7] = addressField.getText();

		}

		return dataInfo;
	}


	//GESTOR de eventos para no registo mudar a janela

	/**
	 * Evento que na medida que a comboBox muda o seu estado os metodos de reenscrita da interface grafica são chamados
	 * @param e evento da classe
	 */
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			if(typeUser.getSelectedIndex() == 0) {
				windowEmploye();
			}else if(typeUser.getSelectedIndex() == 1){
				windowManager();
			}else {
				windowClient();
			}
		}
	}


	/**
	 * Metodo que verifica o formato da data escrito esta em conformidade com o pedido no programa
	 * @param dataInicioString data de inicio
	 * @return booleano com a verificacao do formado da data
	 */
	private boolean checkData(String dataInicioString){
		Date dataInicio = new Date();
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		formatoData.setLenient(false); //para verificar se o formato da data é valido tipo 30/02/2021
		try {
			dataInicio = formatoData.parse(dataInicioString);
		}catch(ParseException e) {
			try {
				dataInicio = formatoData.parse("01/01/1970");
			}catch(ParseException e2) {
				e.printStackTrace();
			}
			return false; //data nao aceite
		}
		try {
			Date antes = formatoData.parse("01/01/1970");
			Date agora = new Date();
			if(dataInicio.after(antes) && dataInicio.before(agora)) {
				return true; //unica forma de ser aceite
			}else {
				return false; //nao aceite
			}
		}catch(ParseException e3) {
			e3.printStackTrace();
		}
		return false;
	}

	/**
	 * Metodo responsavel por verificar se o contato esta correcto ou existe na base de dados
	 * @param contact string com o contato inserido
	 * @return string com aviso da verificacao
	 */
	private String checkContact(String contact){
		if (DadosUtilizadores.verificaContacto(Integer.parseInt(contact)) == 0) {
			String auxContact = contact;
			if (auxContact.length() < 9) {
				return "Erro na inserção do contacto, tem de ter 9 digitos";
			} else {
				if (auxContact.startsWith("9") || auxContact.startsWith("2") || auxContact.startsWith("3")) {
					return "";
				} else {
					return "Erro na inserção do contacto, só pode começar por 9, 2 ou 3";
				}
			}
		}else{
			return "Contacto já existe na base de dados";
		}

	}

	/**
	 * Metodo de limpeza dos campos preenchidos no formulario
	 */
	public void cleanerFields() {
		loginField.setText("");
		nameField.setText("");
		loginField.setText("");
		emailField.setText("");
		fiscalNumberField.setText("");
		contactField.setText("");
		addressField.setText("");
		employeBegDate.setText("");
		passwordField.setText("");
	}

	/**
	 * Verifica se email está dentro da expressao regular
	 * @param aEmail string email inserida
	 * @return boolean booleano com resultado da verificacao
	 * Verifica se email está dentro da expressao regular
	 */
	private static boolean checkEmail(String aEmail) {

		boolean send = aEmail.matches("^[a-zA-Z0-9.]+@[a-z0-9]+\\.[a-z]+");//procura de a-z em pequeno e grande, numero e pode ter ponto, seguido de arroba com numero ou letra seguido de ponto e depois caracteres..


		return send;
	}




}
