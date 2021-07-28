package AcessoBD;
import PedidosNotificacoes.*;
import Utilizadores.Utilizadores;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Dados Relacionados com a tabela notificaçõs da base de dados
 * @author Jorge Martins, Rodrigo Duro
 */
public class DadosNotificacao {

	/**
	 * Metodo de identificacao da totatilidade de notificacoes
	 * @param login string com o login
	 * @return inteiro com numero de notificacoes
	 */
	public static int getNotTotal(String login) {
		int total = 0;
		DadosConnect.conecta();
		try {

			StringBuffer sqlQuery = new StringBuffer();

			// prepared statement for select
			sqlQuery.append(" SELECT * FROM notificacao ");
			sqlQuery.append(" WHERE ID_UTILIZADOR = (SELECT ID_UTILIZADOR FROM utilizadores WHERE LOGIN_UTILIZADOR = ? );");
			DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			DadosConnect.ps.clearParameters();


			DadosConnect.ps.setString(1, login);

			DadosConnect.rs = DadosConnect.ps.executeQuery();

			while (DadosConnect.rs.next()) {
				total++;

			} }catch(SQLException e){
				System.out.println("!! SQL Exception !!\n" + e);
				e.printStackTrace();
				return 0;
			}

			DadosConnect.desliga();
			return total;
		}


	/**
	 * Metodo de adicionar Notificação
	 * @param notificacao objecto notificacao
	 * @return boolean retorna booleano com resultado
	 */
	public static boolean adicionaNotifica(Notificacao notificacao) {
		DadosConnect.conecta();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" INSERT INTO notificacao  (ID_ENCOMENDA, ID_UTILIZADOR, ID_PEDIDO, ESTADO_NOTIFICACAO, DESCRICAO_NOTIFICACAO) "
			  				+ "	VALUES( ?, ?, ?, ?, ?); "); 
			  
			  
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setInt(1, notificacao.getIdEncomenda());
			  DadosConnect.ps.setInt(2, notificacao.getIdUtilizador());
			  if(notificacao.getIdPedido() != 0) {
				  DadosConnect.ps.setInt(3, notificacao.getIdPedido());
			  }else {
				  DadosConnect.ps.setNull(3, Types.INTEGER);
			  }
			  
			  DadosConnect.ps.setString(4, notificacao.getEstado());
			  DadosConnect.ps.setString(5,  notificacao.getDescricao());
			  
			  DadosConnect.ps.executeUpdate();
			  
			  
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
           	return false;
		}
        DadosConnect.desliga();
		return true;
	}

	/**
	 * Metodo de adicionar uma notificacao
	 * @param description string com descricao da notificacao
	 * @param user arraylist de utilizadores
	 * @return booleano com resultado
	 */
	public static boolean addNotification(String description, ArrayList<Utilizadores> user) {
		DadosConnect.conecta();
		try {

			StringBuffer sqlQuery = new StringBuffer();

			// prepared statement for select
			sqlQuery.append(" INSERT INTO notificacao  (ID_UTILIZADOR, ESTADO_NOTIFICACAO, DESCRICAO_NOTIFICACAO) "
					+ "	VALUES( ?, ?, ?); ");

			for (Utilizadores u:user) {
				DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
				DadosConnect.ps.clearParameters();
				DadosConnect.ps.setInt(1, DadosUtilizadores.getIdUser(u.getLogin()));
				DadosConnect.ps.setString(2, "ativa");
				DadosConnect.ps.setString(3, description);

				DadosConnect.ps.executeUpdate();

			}
		} catch (SQLException e) {
			System.out.println("!! SQL Exception !!\n"+e);
			e.printStackTrace();
			return false;
		}

		DadosConnect.desliga();
		return true;
	}

	/**
	 * Metodo de realizar um Update à notificação, neste caso apenas é mudado o estado da mesma
	 * @param notificacao objecto notificacao
	 */
	public static void updateEstadoNotifica(Notificacao notificacao) {
		DadosConnect.conecta2();
        try {
        	  
              
			  StringBuffer sqlQuery = new StringBuffer();
			  sqlQuery.append(" UPDATE notificacao ");
			  sqlQuery.append(" SET ESTADO_NOTIFICACAO = ? ");
			  sqlQuery.append(" WHERE ID_NOTIFICACAO = ? ");

			  DadosConnect.ps2 = DadosConnect.conn2.prepareStatement(sqlQuery.toString());

			  DadosConnect.ps2.clearParameters();
			  DadosConnect.ps2.setString(1, notificacao.getEstado());
			  DadosConnect.ps2.setInt(2, notificacao.getId()); 
			  
			  DadosConnect.ps2.executeUpdate();
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        
        DadosConnect.desliga2();
	}

	/**
	 * Metodo de apagar as notificacoes da base de dados
	 * @param description descricao para ser apagada
	 */
	public static void deleteNotByDescription(String description) {
		DadosConnect.conecta2();
		try {


			StringBuffer sqlQuery = new StringBuffer();
			sqlQuery.append(" DELETE FROM notificacao ");
			sqlQuery.append(" WHERE DESCRICAO_NOTIFICACAO = ?");

			DadosConnect.ps2 = DadosConnect.conn2.prepareStatement(sqlQuery.toString());

			DadosConnect.ps2.clearParameters();
			DadosConnect.ps2.setString(1, description);

			DadosConnect.ps2.executeUpdate();

		} catch (SQLException e) {
			System.out.println("!! SQL Exception !!\n"+e);
			e.printStackTrace();
		}

		DadosConnect.desliga2();
	}

	/**
	 * Metodo para apagar as notificacoes pelo id
	 * @param notificacao objecto notificacao
	 * @param id id da notificacao
	 */
	public static void deleteNot(Notificacao notificacao,int id) {
		DadosConnect.conecta2();
		try {


			StringBuffer sqlQuery = new StringBuffer();
			sqlQuery.append(" DELETE FROM notificacao ");
			sqlQuery.append(" WHERE DESCRICAO_NOTIFICACAO = ? AND ID_UTILIZADOR = ?");

			DadosConnect.ps2 = DadosConnect.conn2.prepareStatement(sqlQuery.toString());

			DadosConnect.ps2.clearParameters();
			DadosConnect.ps2.setString(1, notificacao.getDescricao());
			DadosConnect.ps2.setInt(2, id);

			DadosConnect.ps2.executeUpdate();

		} catch (SQLException e) {
			System.out.println("!! SQL Exception !!\n"+e);
			e.printStackTrace();
		}

		DadosConnect.desliga2();
	}
	//mudar para listagem de notificações
	/**
	 * Metodo de ligação à base de dados para listar notificações através de condicao ex(ID = 1 AND Estado = activa)
	 * @param aCondicao condicao da notificacao para listagem
	 * @return ArrayList de objectos de Notificacao para ser depois listado
	 * Metodo de ligação à base de dados para listar notificações através de condicao ex(ID = 1 AND Estado = activa)
	 * 
	 */
	public static ArrayList <Notificacao> listarNotificacoesCondicao(String aCondicao){
		ArrayList <Notificacao> lista = new ArrayList <Notificacao> ();
        DadosConnect.conecta();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM notificacao "); 
			  sqlQuery.append(" "+aCondicao+" ; ");
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  lista = new ArrayList <Notificacao> (); //garantir que a lista fica vazia..
				  while (DadosConnect.rs.next()) {
					  lista.add(new Notificacao(DadosConnect.rs.getInt("ID_NOTIFICACAO"), DadosConnect.rs.getInt("ID_ENCOMENDA"), DadosConnect.rs.getInt("ID_UTILIZADOR"), DadosConnect.rs.getString("ESTADO_NOTIFICACAO"), DadosConnect.rs.getString("DESCRICAO_NOTIFICACAO")));    
					  Notificacao noti = new Notificacao(DadosConnect.rs.getInt("ID_NOTIFICACAO"), DadosConnect.rs.getInt("ID_ENCOMENDA"), DadosConnect.rs.getInt("ID_UTILIZADOR"), DadosConnect.rs.getString("ESTADO_NOTIFICACAO"), DadosConnect.rs.getString("DESCRICAO_NOTIFICACAO"));
					  noti.setEstado("inactiva");
					  updateEstadoNotifica(noti); //assim demonstra só uma unica vez as notificações
				  }
			  }
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
        	
           	e.printStackTrace();
           	return lista;
		} 
        DadosConnect.desliga();
        
        return lista;
	}
	
	/**
	 * Todas as encomendas com o estado 'iniciada' são consideradas encomendas novas.
	 * @return int com numero de novas encomendas na base de dados
	 * Todas as encomendas com o estado 'iniciada' são consideradas encomendas novas.
	 * Usado para notificar gestores
	 */
	public static int verificaNovasEncomendas() {
		int conta = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        	  
	    StringBuffer sqlQuery = new StringBuffer();
		
	    DadosConnect.conecta();
			  
	    sqlQuery.append(" SELECT COUNT(*) AS \"Contador\" FROM encomenda "); 
	    sqlQuery.append(" WHERE ESTADO_ENCOMENDA = 'iniciada' ; ");
	    
	    try {
	    	ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
	    	ps.clearParameters();   
			
	    	rs = ps.executeQuery();
			  
	    	if (rs == null) {
		    System.out.println("!! No Record on table !!");
	    	} else
	    		while (rs.next()) {
	    			conta += rs.getInt("Contador");  	  
				}
			  
	    	ps.close();
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    		DadosConnect.desliga();
	    		return 0;
	    	}
		DadosConnect.desliga();
        
        return conta;
	}
	
	
}
