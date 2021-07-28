package Utilizadores;
import AcessoBD.DadosLogs;
import AcessoBD.DadosUtilizadores;
import DadosEstaticos.DadosStatic;

import java.util.*;

/**
 * Classe dos Logs de Sistema
 * @author Jorge Martins e Rodrigo Duro
 *
 */
public class Logs {
	
	private int idUtilizador;
	private String acaoLog;
	private Date data;
	
	/**
	 * Construtor para a objecto Logs
	 * @param aIdUtilizador id utilizador
	 * @param aAcaoLog acao a gravas
	 * @param aData data
	 * Construtor para a objecto Logs
	 */
	public Logs(int aIdUtilizador, String aAcaoLog, Date aData) {
		idUtilizador = aIdUtilizador;
		acaoLog = aAcaoLog;
		data = aData;
	}



	/**
	 * Retorna o id do Utilizador
	 * @return idUtilizador
	 */
	public int getIdUtilizador() {
		return idUtilizador;
	}

	/**
	 * Retorna a acao
	 * @return acaoLog
	 */
	public String getAcaoLog() {
		return acaoLog;
	}

	/**
	 * retorna a data
	 * @return data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * retorna o login
	 * @return login
	 */
	public String getLogin(){
		return DadosUtilizadores.getLogin(idUtilizador);
	}

	/**
	 * Metodo Generico toString
	 */
	@Override
	public String toString() {
		return "Logs [idUtilizador=" + idUtilizador + ", Ação=" + acaoLog + ", Data=" + data + "] \n";
	}

	/**
	 * Metodo que adiciona o movimento realizado pelo utilizador mais seu login
	 * e a data do movimento
	 * @param movimento movimentos
	 * Metodo que adiciona o movimento realizado pelo utilizador mais seu login
	 * e a data do movimento
	 */
	public static void addMov(String movimento) {
		DadosLogs.adicionaLog(new Logs(Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login)), movimento, new Date()));
	}
	
	



}
