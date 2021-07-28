package InterfaceIU;

import AcessoBD.DadosUtilizadores;
import DadosEstaticos.DadosStatic;
import Utilizadores.Utilizadores;
import Aviso.*;
import Utilizadores.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * Classe responsavel pela edicao dos dados do proprio utilizador
 * @author Jorge Martins e Rodrigo Duro
 */
public class MenuMyData extends JFrame implements ActionListener{
    private Utilizadores user;
    private Clientes client;
    private Container container;
    private JPanel geralPanel, bottomPanelUpPic,bottomPanelObs, bottomPanelUpName, requestPanel, bottomPanelUpEmail, bottomPanelUpContact, bottomPanelUpFiscal, bottomPanelUpAddress, bottomPanelUpPass, filePanel, subNorthPanel1, subNorthPanel2, northPanel; //cada painel vai conter as informações para cada especificidade
    private JButton upPicBottom, requestBottom, upNameBottom, upEmailBottom, upContactBottom, upFiscalBottom, upAddressBottom, upPasswordBottom, upObsBotton, fileBotton;
    private JTextField nameField, emailField, contactField, fiscalField, addressField, obsField;
    private JPasswordField passwordField;
    private JLabel imgLabel;

    private Image image = Toolkit.getDefaultToolkit().getImage(DadosUtilizadores.pesquisaLogin(DadosStatic.Login).getFoto());
    private Image scale = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
    private String path = DadosUtilizadores.pesquisaLogin(DadosStatic.Login).getFoto();

    /**
     * Construtor da classe que chama varios metodos de construcao grafica dependendo do utlizador
     */
    public MenuMyData() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/box.png")));

        if (DadosStatic.Tipo.equals("Gestor")) {
            chAdmin();
        } else {
            chRest();
        }
    }

    /**
     * metodo de construcao de interface grafica com elementos respectivos a clientes e funcionarios com as informacoes editaveis
     */
    private void chRest() {
        client = DadosUtilizadores.pesquisaClientes(DadosStatic.Login);
        user = DadosUtilizadores.pesquisaLogin(DadosStatic.Login); //para nos updates apenas usar um switch

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

        bottomPanelUpPic = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upPicBottom = new JButton("Update Foto");
        upPicBottom.setActionCommand("alterarFoto");
        upPicBottom.addActionListener(this);
        upPicBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanelUpPic.add(upPicBottom);

        imgLabel = new JLabel(new ImageIcon(scale));

        subNorthPanel1.add(imgLabel);
        subNorthPanel2.add(filePanel);
        subNorthPanel2.add(bottomPanelUpPic);
        northPanel.add(subNorthPanel1);
        northPanel.add(subNorthPanel2);

        //grids que vao ser usadas
        GridLayout grids = new GridLayout(7, 3);
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

        JPanel tagObs = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagObs.add(new JLabel("Observacoes"));



        //CAMPOS A PREENCHER

        JPanel nameBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        nameField = new JTextField(15);
        nameField.setToolTipText("Insira novo nome");
        nameBox.add(nameField);

        JPanel passBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordField = new JPasswordField(15);
        passwordField.setToolTipText("Insira nova password");
        passBox.add(passwordField);

        JPanel emailBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailField = new JTextField(15);
        emailField.setToolTipText("Insira novo email");
        emailBox.add(emailField);

        JPanel contactBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contactField = new JTextField(15);
        contactField.setToolTipText("Insira novo contacto");
        contactBox.add(contactField);

        JPanel fiscalBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fiscalField = new JTextField(15);
        fiscalField.setToolTipText("Insira novo numero fiscal");
        fiscalBox.add(fiscalField);

        JPanel addressBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addressField = new JTextField(15);
        addressField.setToolTipText("Insira nova morada");
        addressBox.add(addressField);

        JPanel obsBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        obsField = new JTextField(15);
        obsField.setToolTipText("Insira novas observacoes");
        obsBox.add(obsField);

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

        bottomPanelObs = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upObsBotton = new JButton("Update Observacoes");
        upObsBotton.setActionCommand("alterarObs");
        upObsBotton.addActionListener(this);
        upObsBotton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanelObs.add(upObsBotton);


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
        geralPanel.add(bottomPanelObs);

        geralPanel.add(tagContact);
        geralPanel.add(contactBox);
        geralPanel.add(bottomPanelUpContact);

        geralPanel.add(tagFiscal);
        geralPanel.add(fiscalBox);
        geralPanel.add(bottomPanelUpFiscal);

        geralPanel.add(tagAddress);
        geralPanel.add(addressBox);
        geralPanel.add(bottomPanelUpAddress);


        requestPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        requestBottom = new JButton("Pedido Desactivacao Conta");
        requestBottom.setActionCommand("desactivarConta");
        requestBottom.addActionListener(this);
        requestBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        requestPanel.add(requestBottom);

        container.add(northPanel, BorderLayout.NORTH);
        container.add(geralPanel, BorderLayout.CENTER);
        container.add(requestPanel, BorderLayout.SOUTH);


        this.setSize(590, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("Alteracao Meus Dados");
        this.setResizable(true);
        this.setVisible(true);
        writeFields();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Metodos com a construcao de uma interface grafico com os elementos respectivos a Administradores/gestores
     */
    private void chAdmin() {
        user = DadosUtilizadores.pesquisaLogin(DadosStatic.Login);

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

        bottomPanelUpPic = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upPicBottom = new JButton("Update Foto");
        upPicBottom.setActionCommand("alterarFoto");
        upPicBottom.addActionListener(this);
        upPicBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanelUpPic.add(upPicBottom);

        imgLabel = new JLabel(new ImageIcon(scale));

        subNorthPanel1.add(imgLabel);
        subNorthPanel2.add(filePanel);
        subNorthPanel2.add(bottomPanelUpPic);
        northPanel.add(subNorthPanel1);
        northPanel.add(subNorthPanel2);

        //grids que vao ser usadas
        GridLayout grids = new GridLayout(4, 3);
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

        JPanel tagObs = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //como no login no janela de LoginMenu
        tagObs.add(new JLabel("Observacoes"));


        //CAMPOS A PREENCHER

        JPanel nameBox = new JPanel(new FlowLayout(FlowLayout.LEFT)); //para ficar encostado a sua etiqueta criada acima
        nameField = new JTextField(15);
        nameField.setToolTipText("Insira novo nome");
        nameBox.add(nameField);

        JPanel passBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordField = new JPasswordField(15);
        passwordField.setToolTipText("Insira nova password");
        passBox.add(passwordField);

        JPanel emailBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailField = new JTextField(15);
        emailField.setToolTipText("Insira novo email");
        emailBox.add(emailField);

        JPanel obsBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        obsField = new JTextField(15);
        obsField.setToolTipText("Insira novas observacoes");
        obsBox.add(obsField);

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

        bottomPanelObs = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upObsBotton = new JButton("Update Observacoes");
        upObsBotton.setActionCommand("alterarObs");
        upObsBotton.addActionListener(this);
        upObsBotton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanelObs.add(upObsBotton);


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
        geralPanel.add(bottomPanelObs);


        requestPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        requestBottom = new JButton("Pedido Desactivacao Conta");
        requestBottom.setActionCommand("desactivarConta");
        requestBottom.addActionListener(this);
        requestBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        requestPanel.add(requestBottom);




        container.add(northPanel, BorderLayout.NORTH);
        container.add(geralPanel, BorderLayout.CENTER);
        container.add(requestPanel, BorderLayout.SOUTH);


        this.setSize(590, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("Alteracao Meus Dados");
        this.setResizable(true);
        this.setVisible(true);
        writeFields();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Metodo de escrita dos dados retirados da base de dados para a interface grafica
     */
    private void writeFields() {
        if (DadosStatic.Tipo.equals("Gestor")) {
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
     * Metodo de gestao de eventos
     * @param e evento gerado pela classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("desactivarConta")) {
            user.setEstado("pedido");
            DadosUtilizadores.updateUtilizador(user);
            Aviso.showMessage("Actualização Realizada com Sucesso", "Aviso", "info");
        } else {
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
                                        Aviso.showMessage("Campo Observacoes vazio", "Aviso", "error");
                                    }else{
                                        user.setObs(obsField.getText());
                                        DadosUtilizadores.updateUtilizador(user);
                                        Aviso.showMessage("Update realizado com sucesso", "Aviso", "info");
                                    } break;

                case"fotoPerfil":   JFileChooser fileChooser = new JFileChooser();
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
                                    break;
                case "alterarFoto": user.setFoto(path);
                                    DadosUtilizadores.updateUtilizador(user);
                                    Aviso.showMessage("Update da Foto realizado com sucesso", "Aviso", "info");
                                    break;

            }

        }
    }

    /**
     * @param aEmail
     * @return boolean
     * Verifica se email está dentro da expressao regular
     */
    private static boolean checkEmail(String aEmail) {

        boolean send = aEmail.matches("^[a-zA-Z0-9.]+@[a-z0-9]+\\.[a-z]+");//procura de a-z em pequeno e grande, numero e pode ter ponto, seguido de arroba com numero ou letra seguido de ponto e depois caracteres..


        return send;
    }


}
