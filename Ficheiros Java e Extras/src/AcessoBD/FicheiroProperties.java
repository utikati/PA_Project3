
package AcessoBD;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.*;

import DadosEstaticos.*;

/**
 * Acesso ao ficheiro properties
 * @author Jorge Martins e Rodrigo Duro
 *
 */
public class FicheiroProperties {
	
	 
	public static File ficheiroLeitura;
	  
	 
	public static FileInputStream fis; // substitui FileReader

	  
	  /**
	   * Leitura de Dados Do ficheiro Properties
	 * @param aCaminho path do ficheiro
	 * @return boolean booleano com resultado da operacao
	 * Leitura de Dados Do ficheiro Properties
	 */
	public static boolean leituraFicheiroProp(String aCaminho) {

	    if (aCaminho != null && aCaminho.length() > 0) {
	      ficheiroLeitura = new File(aCaminho);
	      if (ficheiroLeitura.exists()) {
	    	  
	        try {
	          fis = new FileInputStream(ficheiroLeitura);
	          return true;
	        } catch (IOException ioe) {
	          ioe.printStackTrace();
	        }
	      }else {
	    	  return false;
	      }
	    }
	    return false;
	  }
	  
	  /**
	 * Inserir os dados do ficheiro Properties nas variaveis Estaticas para ligação
	 */

	public static void leituraDadosProp() {
		  if(!leituraFicheiroProp("./Properties/dadosAcesso.properties")) {
			  if(new File("./Properties").exists()) { //caso a pasta não exista ele cria uma e chama o metodo

			  }else {
				  new File("./Properties").mkdirs(); //comando para criar uma pasta no sistema operativo

			  }
			  
		  }else {
		  
			  Properties properties = new Properties();
			  try {
				  properties.load(fis);
			  }catch(Exception e) {
				  e.printStackTrace();
			  }
			  
			  DadosStatic.ip = properties.getProperty("ip");
			  DadosStatic.porto = properties.getProperty("porto");
			  DadosStatic.baseDados = properties.getProperty("baseDados");
			  DadosStatic.utilizador = properties.getProperty("utilizador");
			  DadosStatic.pass = properties.getProperty("pass");
			  try {
				  fis.close();
			  }catch(Exception e) {
				  e.printStackTrace();
			  }
		  }
	  }

	  //Modificação de dados no ficheiro properties
	  
	  
	 /**
	  * Escrita no ficheiro Properties dados que sejam para ser alterados
	 * @param aIp ip da base de dados
	 * @param aPorto porto da base de dados
	 * @param aBaseDados nome da base de dados
	 * @param aUtilizador login da base de dados
	 * @param aPass password da base de dados
	 * @throws Exception exepcao que pode devolver na leitura
	 * Escrita no ficheiro Properties dados que sejam para ser alterados
	 */
	public static void escritaDadosProp(String aIp, String aPorto, String aBaseDados, String aUtilizador, String aPass) throws Exception { //ao fazer try catch estava a ter problemas no store
		 
		 
		 OutputStream os = new FileOutputStream("./Properties/dadosAcesso.properties");
		 
		 Properties properties = new Properties();
		 
		 properties.setProperty("ip", aIp);
		 properties.setProperty("porto", aPorto);
		 properties.setProperty("baseDados", aBaseDados);
		 properties.setProperty("utilizador", aUtilizador);
		 properties.setProperty("pass", aPass);
		 
		 properties.store(os, null);
		 
		 os.close();
		 
		 
	 }
	
	
	
}
