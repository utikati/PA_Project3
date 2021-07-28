package DadosEstaticos;
import java.util.Scanner;

/**
 * Classe com os dados estaticos que ficam na memoria no decorrer do software
 * @author Jorge Martins e Rodrigo Duro
 * Classe com os dados estaticos que ficam na memoria no decorrer do software
 * 
 */
public class DadosStatic {
	// Dados relativos ao acesso a base de dados
	public static String ip = "localhost";
	public static String porto = "3306";
	public static String baseDados = "projecto1"; 
	public static String utilizador = "root"; 
	public static String pass = "jorgepass";

	//--------------------------------------------------------
	
	public static Scanner teclado = new Scanner(System.in);
	
	
	// Dados do Utilizador Online-----------------------
	
	public static String UserON = ""; //nome do utilizador
	public static String Tipo = ""; //tipo de conta
	public static String Login = ""; //login do utilizador
	public static String especializacao = ""; //especializacao do funcionario

	//Dados de Contabilizacoes do software

	public static int execucoes = 0;
	public static String UltimaData = "";
}
