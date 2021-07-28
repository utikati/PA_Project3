package InterfaceIU;

import AcessoBD.DadosUtilizadores;
import Aviso.Aviso;
import Utilizadores.Clientes;
import Utilizadores.Utilizadores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Classe responsavel pelo formulario de modificacao de dados de um utilizador
 * @author Jorge Martins e Rodrigo Duro
 */
public class FormChUser extends JFrame implements ActionListener, ItemListener {
    private Utilizadores user;
    private Clientes client;
    private Container container;
    private JPanel geralPanel,bottonStatePanel, titlePanel, bottomPanelUpName, requestPanel, bottomPanelUpEmail, bottomPanelUpContact, bottomPanelUpFiscal, bottomPanelUpAddress, bottomPanelUpPass, bottonPanelObs; //cada painel vai conter as informações para cada especificidade
    private JButton requestBottom, upNameBottom, upEmailBottom, upContactBottom, upFiscalBottom, upAddressBottom, upPasswordBottom, upObsBotton, searchState;
    private JTextField nameField, emailField, contactField, fiscalField, addressField, obsField;
    private JPasswordField passwordField;
    private String login, state;
    private JComboBox stateField;


    /**
     * Construtor que chama a funcao correcta dependendendo do utilizador online
     * @param aLogin
     */
    public FormChUser(String aLogin) {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        login = aLogin;
        Utilizadores us = DadosUtilizadores.pesquisaLogin(login);
        if (us.getTipo().equals("Gestor")) {
            chAdmin();
        } else {
            chRest();
        }
    }

    /**
     * Metodo de construcao grafica de clientes e funcionarios
     */
    private void chRest() {
        client = DadosUtilizadores.pesquisaClientes(login);
        user = DadosUtilizadores.pesquisaLogin(login); //para nos updates apenas usar um switch

        container = getContentPane();
        container.setLayout(new BorderLayout());

        titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(new JLabel("Alterar Dados de Utilizador"));
        //grids que vao ser usadas
        GridLayout grids = new GridLayout(8, 3);
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

        JPanel tagContact = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tagContact.add(new JLabel("Contacto"));

        JPanel tagFiscal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tagFiscal.add(new JLabel("Contribuinte"));

        JPanel tagAddress = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tagAddress.add(new JLabel("Morada"));


        //observacoes fields

        JPanel tagObs = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagObs.add(new JLabel("Observacoes"));

        JPanel obsBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        obsField = new JTextField(15);
        obsField.setToolTipText("Insira Observacoes");
        obsBox.add(obsField);

        bottonPanelObs = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upObsBotton = new JButton("Update Observacoes");
        upObsBotton.setActionCommand("alterarObs");
        upObsBotton.addActionListener(this);
        upObsBotton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonPanelObs.add(upObsBotton);


        //----------


        //CAMPOS A PREENCHER

        JPanel nameBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        nameField = new JTextField(15);
        nameField.setToolTipText("Insira o nome");
        nameBox.add(nameField);

        JPanel passBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordField = new JPasswordField(15);
        passwordField.setToolTipText("Insira Password");
        passBox.add(passwordField);

        JPanel emailBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailField = new JTextField(15);
        emailField.setToolTipText("Insira email");
        emailBox.add(emailField);

        JPanel contactBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contactField = new JTextField(15);
        contactField.setToolTipText("Insira contacto");
        contactBox.add(contactField);

        JPanel fiscalBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fiscalField = new JTextField(15);
        fiscalField.setToolTipText("Insira numero fiscal");
        fiscalBox.add(fiscalField);

        JPanel addressBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addressField = new JTextField(15);
        addressField.setToolTipText("Insira morada");
        addressBox.add(addressField);

        //BOTOES DE UPDATE DOS DADOS
        bottomPanelUpName = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upNameBottom = new JButton("Update Nome");
        upNameBottom.setActionCommand("alterarNome");
        upNameBottom.addActionListener(this);
        upNameBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanelUpName.add(upNameBottom);

        bottomPanelUpEmail = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upEmailBottom = new JButton("Update Email");
        upEmailBottom.setActionCommand("alterarEmail");
        upEmailBottom.addActionListener(this);
        upEmailBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanelUpEmail.add(upEmailBottom);

        bottomPanelUpContact = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upContactBottom = new JButton("Update Contacto");
        upContactBottom.setActionCommand("alterarContacto");
        upContactBottom.addActionListener(this);
        upContactBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanelUpContact.add(upContactBottom);

        bottomPanelUpFiscal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upFiscalBottom = new JButton("Update Contribuinte");
        upFiscalBottom.setActionCommand("alterarContribuinte");
        upFiscalBottom.addActionListener(this);
        upFiscalBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanelUpFiscal.add(upFiscalBottom);

        bottomPanelUpAddress = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upAddressBottom = new JButton("Update Morada");
        upAddressBottom.setActionCommand("alterarMorada");
        upAddressBottom.addActionListener(this);
        upAddressBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanelUpAddress.add(upAddressBottom);

        bottomPanelUpPass = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upPasswordBottom = new JButton("Update Password");
        upPasswordBottom.setActionCommand("alterarPass");
        upPasswordBottom.addActionListener(this);
        upPasswordBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanelUpPass.add(upPasswordBottom);


        //adicionar ao painel com a info geral
        geralPanel.add(tagName);
        geralPanel.add(nameBox);
        geralPanel.add(bottomPanelUpName);

        geralPanel.add(tagPassword);
        geralPanel.add(passBox);
        geralPanel.add(bottomPanelUpPass);

        geralPanel.add(tagEmail);
        geralPanel.add(emailBox);
        geralPanel.add(bottomPanelUpEmail);

        geralPanel.add(tagObs);
        geralPanel.add(obsBox);
        geralPanel.add(bottonPanelObs);

        geralPanel.add(tagContact);
        geralPanel.add(contactBox);
        geralPanel.add(bottomPanelUpContact);

        geralPanel.add(tagFiscal);
        geralPanel.add(fiscalBox);
        geralPanel.add(bottomPanelUpFiscal);

        geralPanel.add(tagAddress);
        geralPanel.add(addressBox);
        geralPanel.add(bottomPanelUpAddress);

        JPanel tagState = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagState.add(new JLabel("Estado"));

        JPanel stateBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        String[] varTypes = {"activo", "inactivo"};
        stateField = new JComboBox<String>(varTypes);
        stateField.setToolTipText("Escolha o estado para dar Update");
        stateField.addItemListener(this);
        stateBox.add(stateField);

        bottonStatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchState = new JButton("Update Estado");
        searchState.setActionCommand("mudaEstado");
        searchState.addActionListener(this);
        searchState.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonStatePanel.add(searchState);

        geralPanel.add(tagState);
        geralPanel.add(stateBox);
        geralPanel.add(bottonStatePanel);

        container.add(titlePanel, BorderLayout.NORTH);
        container.add(geralPanel, BorderLayout.CENTER);
        container.add(new JPanel(), BorderLayout.SOUTH);


        this.setSize(590, 390);
        this.setLocationRelativeTo(null);
        this.setTitle("Alteracao Dados");
        this.setResizable(true);
        this.setVisible(true);
        writeFields();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    /**
     * Metodo responsavel por verificar se a ComboBox muda o seu estado e assim vai mudando o valor da variavel state
     * @param e evento da de mudanca do estado da comboBox
     */
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            state = e.getItem().toString();
        }
    }

    /**
     * Metodo de construcao grafica de gestores
     */
    private void chAdmin() {
        user = DadosUtilizadores.pesquisaLogin(login);


        container = getContentPane();
        container.setLayout(new BorderLayout());

        titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(new JLabel("Alterar Dados Utilizador"));
        //grids que vao ser usadas
        GridLayout grids = new GridLayout(6, 3);
        grids.setHgap(2); //espaçamento horizontal entre as "linhas"
        grids.setVgap(2);

        geralPanel = new JPanel(grids);

        //FINAL DA CONSTRUÇÃO DAS GRIDS QUE DEPOIS VAO SER PREENCHIDAS COM OS PAINEIS

        //criar labels e fields
        //ETIQUETAS PARA OS CAMPOS
        JPanel tagName = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagName.add(new JLabel("Nome"));

        JPanel tagPassword = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagPassword.add(new JLabel("Password"));

        JPanel tagEmail = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagEmail.add(new JLabel("Email"));



        //campos de OBS
        JPanel tagObs = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagObs.add(new JLabel("Observacoes"));

        JPanel obsBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        obsField = new JTextField(15);
        obsBox.add(obsField);

        bottonPanelObs = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upObsBotton = new JButton("Update Observacoes");
        upObsBotton.setActionCommand("alterarObs");
        upObsBotton.addActionListener(this);
        upObsBotton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonPanelObs.add(upObsBotton);




        //CAMPOS A PREENCHER

        JPanel nameBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        nameField = new JTextField(15);
        nameBox.add(nameField);

        JPanel passBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordField = new JPasswordField(15);
        passBox.add(passwordField);

        JPanel emailBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailField = new JTextField(15);
        emailBox.add(emailField);

        //BOTOES DE UPDATE DOS DADOS
        bottomPanelUpName = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upNameBottom = new JButton("Update Nome");
        upNameBottom.setActionCommand("alterarNome");
        upNameBottom.addActionListener(this);
        upNameBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanelUpName.add(upNameBottom);

        bottomPanelUpEmail = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upEmailBottom = new JButton("Update Email");
        upEmailBottom.setActionCommand("alterarEmail");
        upEmailBottom.addActionListener(this);
        upEmailBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanelUpEmail.add(upEmailBottom);

        bottomPanelUpPass = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upPasswordBottom = new JButton("Update Password");
        upPasswordBottom.setActionCommand("alterarPass");
        upPasswordBottom.addActionListener(this);
        upPasswordBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanelUpPass.add(upPasswordBottom);


        //adicionar ao painel com a info geral
        geralPanel.add(tagName);
        geralPanel.add(nameBox);
        geralPanel.add(bottomPanelUpName);

        geralPanel.add(tagPassword);
        geralPanel.add(passBox);
        geralPanel.add(bottomPanelUpPass);

        geralPanel.add(tagEmail);
        geralPanel.add(emailBox);
        geralPanel.add(bottomPanelUpEmail);

        geralPanel.add(tagObs);
        geralPanel.add(obsBox);
        geralPanel.add(bottonPanelObs);

        JPanel tagState = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagState.add(new JLabel("Estado"));

        JPanel stateBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        String[] varTypes = {"activo", "inactivo"};
        stateField = new JComboBox<String>(varTypes);
        stateField.setToolTipText("Escolha o estado para dar Update");
        stateField.addItemListener(this);
        stateBox.add(stateField);

        bottonStatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchState = new JButton("Update Estado");
        searchState.setActionCommand("mudaEstado");
        searchState.addActionListener(this);
        searchState.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottonStatePanel.add(searchState);

        geralPanel.add(tagState);
        geralPanel.add(stateBox);
        geralPanel.add(bottonStatePanel);

        container.add(titlePanel, BorderLayout.NORTH);
        container.add(geralPanel, BorderLayout.CENTER);
        container.add(new JPanel(), BorderLayout.SOUTH);


        this.setSize(590, 300);
        this.setLocationRelativeTo(null);
        this.setTitle("Alteracao Dados");
        this.setResizable(true);
        this.setVisible(true);
        writeFields();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void writeFields() {
        if (DadosUtilizadores.pesquisaLogin(login).getTipo().equals("Gestor")) {
            nameField.setText(user.getNome());
            passwordField.setText(user.getPass());
            emailField.setText(user.getEmail());
            obsField.setText(user.getObs());
        } else {
            nameField.setText(client.getNome());
            passwordField.setText(client.getPass());
            emailField.setText(client.getEmail());
            obsField.setText(client.getObs());
            contactField.setText(String.valueOf(client.getContacto()));
            fiscalField.setText(String.valueOf(client.getContribuinte()));
            addressField.setText(String.valueOf(client.getMorada()));
        }

    }

    /**
     * Classe que gere os eventos invocados pelo utilizador
     * @param e evento gerado pelo utilizador
     */
    @Override
    public void actionPerformed(ActionEvent e) {
            String op = e.getActionCommand();

            switch (op){
                case "alterarNome": if(nameField.getText().equals("")){
                                        Aviso.showMessage("Campo Nome Vazio", "Aviso", "error");
                                    }else{
                                        user.setNome(nameField.getText());
                                        DadosUtilizadores.updateUtilizador(user);
                                        Aviso.showMessage("Update realizado com sucesso", "Aviso", "info");
                                    } break;
                case "alterarEmail": if(emailField.getText().equals("")){
                                        Aviso.showMessage("Campo Email vazio", "Aviso", "error");
                                    }else{
                                        if(DadosUtilizadores.verificaEmail(emailField.getText()).equals(emailField.getText()) || !checkEmail(emailField.getText())){
                                            Aviso.showMessage("Email já existe em base de dados ou mal inserido", "Aviso", "error");
                                        }else{
                                            user.setEmail(emailField.getText());
                                            DadosUtilizadores.updateUtilizador(user);
                                            Aviso.showMessage("Update realizado com sucesso", "Aviso", "info");
                                        }
                                    }  break;

                case "alterarContacto": if(contactField.getText().equals("")){
                                            Aviso.showMessage("Campo Contacto vazio", "Aviso", "error");
                                        }else{
                                            if(DadosUtilizadores.verificaContacto(Integer.parseInt(contactField.getText())) > 0){
                                                Aviso.showMessage("Contacto já existe em base de dados", "Aviso", "error");
                                            }else{
                                                client.setContacto(Integer.parseInt(contactField.getText()));
                                                DadosUtilizadores.updateCliente(client);
                                                Aviso.showMessage("Update realizado com sucesso", "Aviso", "info");
                                            }
                                        } break;

                case "alterarContribuinte": if(fiscalField.getText().equals("")){
                                                Aviso.showMessage("Campo Contribuinte vazio", "Aviso", "error");
                                                }else{
                                                    if(DadosUtilizadores.verificaContribuinte(Integer.parseInt(fiscalField.getText())) > 0){
                                                        Aviso.showMessage("Contribuinte já existe em base de dados", "Aviso", "error");
                                                    }else{
                                                        client.setContribuinte(Integer.parseInt(fiscalField.getText()));
                                                        DadosUtilizadores.updateCliente(client);
                                                        Aviso.showMessage("Update realizado com sucesso", "Aviso", "info");

                                                    }
                                                } break;

                case "alterarMorada": if(addressField.getText().equals("")){
                                            Aviso.showMessage("Campo Morada vazio", "Aviso", "error");
                                        }else{
                                            client.setMorada(addressField.getText());
                                            DadosUtilizadores.updateCliente(client);
                                            Aviso.showMessage("Update realizado com sucesso", "Aviso", "info");
                                        } break;

                case "alterarPass": if(String.valueOf(passwordField.getPassword()).equals("")){
                                            Aviso.showMessage("Campo Password vazio", "Aviso", "error");
                                        }else{
                                            user.setPass(String.valueOf(passwordField.getPassword()));
                                            DadosUtilizadores.updateUtilizador(user);
                                            Aviso.showMessage("Update realizado com sucesso", "Aviso", "info");
                                        } break;
                case "alterarObs": if(obsField.getText().equals("")){
                                        Aviso.showMessage("Campo Vazio", "Aviso", "error");
                                    }else{
                                        user.setObs(obsField.getText());
                                        DadosUtilizadores.updateUtilizador(user);
                                        Aviso.showMessage("Update realizado com sucesso", "Aviso", "info");
                                    } break;
                case "mudaEstado": user.setEstado(state);
                                    DadosUtilizadores.updateUtilizador(user);
                                    Aviso.showMessage("Update realizado com sucesso", "Aviso", "info");
                                    break;
            }


    }
    /**
     * Verifica se email está dentro da expressao regular
     * @param aEmail
     * @return boolean
     * Verifica se email está dentro da expressao regular
     */
    private static boolean checkEmail(String aEmail) {

        boolean send = aEmail.matches("^[a-zA-Z0-9.]+@[a-z0-9]+\\.[a-z]+");//procura de a-z em pequeno e grande, numero e pode ter ponto, seguido de arroba com numero ou letra seguido de ponto e depois caracteres..


        return send;
    }

}
