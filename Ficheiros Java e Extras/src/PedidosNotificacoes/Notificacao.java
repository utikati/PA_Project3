package PedidosNotificacoes;

/**
 * Class de Notificação, usadas para notificações mais especificas (ex a um ID em especifico)
 * @author Jorge Martins e Rodrigo Duro
 * Class de Notificação, usadas para notificações mais especificas (ex a um ID em especifico)
 */
public class Notificacao {

	private int id;
	private int idEncomenda;
	private int idUtilizador;
	private int idPedido = 0;
	private String estado;
	private String descricao;
	
	
	/**
	 * Construtor para uma notificação que é inicializada vinda da Base de Dados
	 * @param aId id da notificacao
	 * @param aIdEncomenda id de encomenda
	 * @param aIdUtilizador id de utilizador
	 * @param aEstado estado da notificacao
	 * @param aDescricao descricao da notificacao
	 * Construtor para uma notificação que é inicializada vinda da Base de Dados
	 */
	public Notificacao(int aId, int aIdEncomenda, int aIdUtilizador, String aEstado, String aDescricao) {
		id = aId;
		idEncomenda = aIdEncomenda;
		idUtilizador = aIdUtilizador;
		
		estado = aEstado;
		descricao = aDescricao;
		
	}
	
	/**
	 * Construtor para uma notificação gerada pelo codigo no decorrer do software para em seguida ser guardada
	 * em base de dados
	 * @param aIdEncomenda id encomenda
	 * @param aIdUtilizador id utilizador
	 * @param aEstado estado da notificaoca
	 * @param aDescricao descricao da notificacao
	 * Construtor para uma notificação gerada pelo codigo no decorrer do software para em seguida ser guardada
	 * em base de dados
	 */
	public Notificacao(int aIdEncomenda, int aIdUtilizador, String aEstado, String aDescricao) {
		idEncomenda = aIdEncomenda;
		idUtilizador = aIdUtilizador;
		
		estado = aEstado;
		descricao = aDescricao;
		
	}
	
	
	

	/**
	 * Modifica o id do pedido. Outra tecnica de notificacoes.
	 * @param idPedido id do pedido
	 */
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	/**
	 * retorna o id do pedido
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * retorna o id da encomenda do pedido
	 * @return idencomenda
	 */
	public int getIdEncomenda() {
		return idEncomenda;
	}


	/**
	 * retorna o id de utilizador do pedido
	 * @return idUtilizador
	 */
	public int getIdUtilizador() {
		return idUtilizador;
	}


	/**
	 * retorna o id do pedido
	 * @return idPedido
	 */
	public int getIdPedido() {
		return idPedido;
	}

	/**
	 * retorna o estado do pedido
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Muda o estado do pedido
	 * @param estado estado da notificacao
	 * Muda o estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * retorna a descricao do pedido
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	
	/**
	 *Metodo toString da Classe
	 */
	public String toString() {
		return descricao;
	}
	
	
	
}
