package InterfacePrincipal;


import InterfaceIU.LoginMenu;
import InterfaceIU.MainMenu;
import InterfaceIU.MenuAcessBD;
import Sistema.TempoExecucao;

import Aviso.*;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.sql.Time;

/**
 * Classe onde se encontra o main e os menus principais do software
 * @author Jorge Martins e Rodrigo Duro
 * Classe onde se encontra o main e os menus principais do software
 */
public class Principal {
	private static MainMenu mainMenu;
	private static LoginMenu loginMenu;
	private static MenuAcessBD menuAcessBD;
	public static TempoExecucao time;

	/**
	 * Main do software
	 * @param args argumentos externos
	 * Main do software
	 */
	public static void main(String[] args) {
		//inicio do programa
		FlatDarculaLaf.setup();
		time = new TempoExecucao();
		loginMenu = new LoginMenu();
	}

	/**
	 * Classe responsavel por definir o fim do tempo de utilização do software
	 */
	public static void endTime(){
		time.setFim();
		Aviso.showMessage(time.toString(), "Tempo de Execucao", "info");
	}

}


