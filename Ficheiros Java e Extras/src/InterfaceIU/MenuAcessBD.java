package InterfaceIU;

import AcessoBD.FicheiroProperties;
import DadosEstaticos.DadosStatic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Classe responsavel pela configuração do acesso a base de dados, esta edita os dados do ficheiro Properties
 * @author Jorge Martins e Rodrigo Duro
 *
 */
public class MenuAcessBD extends JFrame implements ActionListener {
	private Container cont;
	private JPanel panel1, panelData;
	private JButton atualizationBottom;
	private JTextField ipField, portField, NameDBField, loginField, passwordField;

	/**
	 * Construtor da classe com os elementos graficos para a invocacao da interface grafica
	 */
	public MenuAcessBD() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

		cont = getContentPane();
		cont.setLayout(new BorderLayout());
		panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel1.add(new JLabel("Acesso Base de Dados"));
		
		GridLayout gl = new GridLayout(6,2);
		gl.setHgap(2);
		gl.setVgap(2);
		panelData = new JPanel(gl);
		//textos de etiquetas para por antes dos campos de preenchimento
		JPanel panelTextIp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelTextIp.add(new JLabel("IP da Base dados"));
		
		JPanel panelTextPort = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelTextPort.add(new JLabel("Porto de comunicacão"));
		
		JPanel panelTextNameDB = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelTextNameDB.add(new JLabel("Nome da Base Dados"));
		
		JPanel panelTextLogin = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelTextLogin.add(new JLabel("Login BD"));
		
		JPanel painelTextPassword = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		painelTextPassword.add(new JLabel("Password BD"));
		// fim dos textos à direita

		JPanel panelFieldIp = new JPanel(new FlowLayout(FlowLayout.LEFT)); //campo de preenchimento do IP
		ipField =  new JTextField(10);
		ipField.setToolTipText("Insira Ip da Base de Dados");
		panelFieldIp.add(ipField);
		
		JPanel panelFieldPorto = new JPanel(new FlowLayout(FlowLayout.LEFT));
		portField =  new JTextField(10);
		portField.setToolTipText("Insira o Porto da Base de Dados");
		panelFieldPorto.add(portField);
		
		JPanel panelFieldNomeDB = new JPanel(new FlowLayout(FlowLayout.LEFT));
		NameDBField =  new JTextField(10);
		NameDBField.setToolTipText("Insira o nome da Base de Dados");
		panelFieldNomeDB.add(NameDBField);
		
		JPanel panelCaixaLogin = new JPanel(new FlowLayout(FlowLayout.LEFT));
		loginField =  new JTextField(10);
		loginField.setToolTipText("Insira o login a base de Dados");
		panelCaixaLogin.add(loginField);
		
		JPanel panelFieldPassword = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passwordField = new JPasswordField(10);
		passwordField.setToolTipText("Insira a password de acesso a base de Dados");
		panelFieldPassword.add(passwordField);
		//final dos paneis já com os campos...
		//inserção do botao de actualizar dentro do seu painel especifico
		JPanel panelatualizationBottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		atualizationBottom = new JButton(" Atualizar ");
		atualizationBottom.setActionCommand("actualizarBD");
		atualizationBottom.addActionListener(this);
		atualizationBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelatualizationBottom.add(atualizationBottom);
		//inserção dos paineis anteriores ao painel pai
		panelData.add(panelTextIp);
		panelData.add(panelFieldIp);
		
		panelData.add(panelTextPort);
		panelData.add(panelFieldPorto);
		
		panelData.add(panelTextNameDB);
		panelData.add(panelFieldNomeDB);
		
		panelData.add(panelTextLogin);
		panelData.add(panelCaixaLogin);
		
		panelData.add(painelTextPassword);
		panelData.add(panelFieldPassword);
		
		panelData.add(new JPanel());
		panelData.add(panelatualizationBottom);
		
		cont.add(panel1, BorderLayout.NORTH);
		cont.add(panelData, BorderLayout.CENTER);
		cont.add(new JPanel(), BorderLayout.SOUTH);
		
		this.setSize(330,300);
		this.setLocationRelativeTo(null);
		this.setTitle("Acesso Base de Dados");
		this.setResizable(true);
		this.setVisible(true);
	}
	
	/**
	 * Funciona como um get numa classe mas em grupo de variaveis
	 * @return Array de objetos tipo String
	 */
	public String[] takeDataFields() {
		String [] configuration = new String[5];
		configuration[0] = ipField.getText();
		configuration[1] = portField.getText();
		configuration[2] = NameDBField.getText();
		configuration[3] = loginField.getText();
		configuration[4] = passwordField.getText();
		return configuration;
	}

	/**
	 *Metodos para escrever os dados nos campos oriundos do ficheiro properties
	 */
	public void loadAcessData() {
		ipField.setText(DadosStatic.ip);
		portField.setText(DadosStatic.porto);
		NameDBField.setText(DadosStatic.baseDados);
		loginField.setText(DadosStatic.utilizador);
		passwordField.setText(DadosStatic.pass);
	}

	/**
	 * Metodo de gestao de eventos da classe
	 * @param e evento gerado pela classe
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("actualizarBD")){
			String[] conf = takeDataFields();
			try {
				FicheiroProperties.escritaDadosProp(conf[0], conf[1], conf[2], conf[3], conf[4]);
				this.dispose();
				LoginMenu loginMenu = new LoginMenu();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}
