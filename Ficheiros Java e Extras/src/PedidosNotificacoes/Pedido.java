package PedidosNotificacoes;

/**
 * Classe do Pedido (neste software usado para Pedido de Entrega de encomenda)
 * @author Jorge Martins e Rodrigo
 * Classe do Pedido (neste software usado para Pedido de Entrega de encomenda)
 * Construído para que possa ser adaptado para mais tipos de pedido
 */
public class Pedido {
	private int idPedido = 0;
	private int idUtilizador;
	private int idEncomenda;
	private int idCliente;
	private int idFuncionario;
	private String tipoPedido;
	private String estado;
	
	/**
	 * Construtor para um Pedido vindo da base de dados para a memoria da maquina
	 * @param aIdPedido id do pedido
	 * @param aIdUtilizador id do utilizador
	 * @param aIdEncomenda id de encomenda
	 * @param aIdCliente id do cliente
	 * @param aIdFuncionario id do funcionario
	 * @param aTipoPedido tipo de pedido
	 * @param aEstado estado do pedido
	 * Construtor para um Pedido vindo da base de dados para a memoria da maquina 
	 * 
	 */
	public Pedido(int aIdPedido, int aIdUtilizador, int aIdEncomenda, int aIdCliente, int aIdFuncionario, String aTipoPedido,
	String aEstado) {
		idPedido = aIdPedido;
		idUtilizador = aIdUtilizador;
		idEncomenda = aIdEncomenda;
		idCliente = aIdCliente;
		idFuncionario = aIdFuncionario;
		tipoPedido = aTipoPedido;
		estado = aEstado;
	}
	
	/**
	 * Construtor idealizado para um pedido que seja formado no software para em seguida ser guardado na
	 * base de dados relacional (o seu ID é gerado na base de dados)
	 * @param aIdUtilizador id do utilizador
	 * @param aIdEncomenda id da encomenda
	 * @param aIdCliente id do cliente
	 * @param aIdFuncionario id do funcionario
	 * @param aTipoPedido tipo de pedido
	 * @param aEstado estado do pedido
	 * Construtor idealizado para um pedido que seja formado no software para em seguida ser guardado na
	 * base de dados relacional (o seu ID é gerado na base de dados)
	 */
	public Pedido(int aIdUtilizador, int aIdEncomenda, int aIdCliente, int aIdFuncionario, String aTipoPedido,
			String aEstado) {
				idUtilizador = aIdUtilizador;
				idEncomenda = aIdEncomenda;
				idCliente = aIdCliente;
				idFuncionario = aIdFuncionario;
				tipoPedido = aTipoPedido;
				estado = aEstado;
	}
	
	/**
	 * Construtor vazio apenas para iniciar o objecto sem que este instancie as suas variaveis
	 */
	public Pedido() {
		
	}
	
	
	/**
	 * retorna id do pedido
	 * @return idPedido
	 */
	public int getIdPedido() {
		return idPedido;
	}
	
	/**
	 * retorna o id do utilizador
	 * @return IdUtilizador
	 */
	public int getIdUtilizador() {
		return idUtilizador;
	}
	
	/**
	 * retorna o id da Encomenda
	 * @return idEncomenda
	 */
	public int getIdEncomenda() {
		return idEncomenda;
	}
	
	/**
	 * retorna o id do cliente
	 * @return idCliente
	 */
	public int getIdCliente() {
		return idCliente;
	}
	
	/**
	 * retorna o id do funcionario
	 * @return idFuncionario
	 */
	public int getIdFuncionario() {
		return idFuncionario;
	}
	
	/**
	 * retorna String com o tipo de pedido (normalmente uma descrição)
	 * @return String com o tipo de pedido (normalmente uma descrição)
	 */
	public String getTipoPedido() {
		return tipoPedido;
	}
	
	/**
	 * retorna o estado do pedido
	 * @return String com estado do pedido
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * Modificar o estado do pedido
	 * @param estado estado do pedido
	 * Modificar o estado do pedido
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * String com informações acerca do pedido, metodo generico toString
	 */
	@Override
	public String toString() {
		return tipoPedido+" Com ID: "+idPedido+ " Do cliente: "+idCliente+ " Do utilizador: "+idUtilizador+" Id da encomenda: "+idEncomenda+" Estado: "+estado+"\n" ;
	}
	
	
	
}
