package Produtos;

import java.util.*;

/**
 * Classe encomenda, relativa à tabela encomenda na base de dados
 * @author Jorge Martins
 * Classe encomenda, relativa à tabela encomenda na base de dados
 */
public class Encomenda {
	private int idEncomenda;
	private int idUtilizador;
	private int idCliente;
	private int idArmazenista;
	private int idEstafeta;
	private String identificadorEncomenda;
	private float custo;
	private Date dataCriacao;
	private Date dataAceitacao;
	private Date dataEntrega;
	private String estado;
	private String obs;
	
	
	/**
	 * Construtor com a instanciação das suas variaveis da classe
	 * @param aIdentificadorEncomenda identificador de encomenda
	 * @param aCusto custo de encomenda
	 * @param aDataCria data de criacao
	 * @param aEstado estado da encomenda
	 * @param aIdCliente id do cliente
	 * Construtor com a instanciação das suas variaveis da classe
	 */
	public Encomenda(String aIdentificadorEncomenda, float aCusto, Date aDataCria, String aEstado, int aIdCliente) {
		identificadorEncomenda = aIdentificadorEncomenda;
		custo = aCusto;
		dataCriacao = aDataCria;
		estado = aEstado;
		idCliente = aIdCliente;
	}
	
	/**
	 * Construtor vazio para iniciar um objecto sem instanciação de variaveis
	 */
	public Encomenda() {
		
	}

	/**
	 * Retorna as observacoes da encomenda
	 * @return observacoes
	 */
	public String getObs(){
		return this.obs;
	}

	/**
	 * Modifica as observacoes do objecto
	 * @param obs observacoes
	 */
	public void setObs(String obs){
		this.obs = obs;
	}

	/**
	 * Metodo generico toString
	 */
	public String toString() {
		return "Identificador Encomenda: "+identificadorEncomenda+" custo: "+custo+" datacriacao: "+dataCriacao+" estado: "+estado+"\n";
	}


	/**
	 * @return idEncomenda
	 */
	public int getIdEncomenda() {
		return idEncomenda;
	}


	/**
	 * Modificar o id da encomenda
	 * @param idEncomenda id de encomenda
	 * Modificar o id da encomenda
	 */
	public void setIdEncomenda(int idEncomenda) {
		this.idEncomenda = idEncomenda;
	}


	/**
	 * retorna id utilizadores
	 * @return idUtilizadores
	 */
	public int getIdUtilizador() {
		return idUtilizador;
	}


	/**
	 * A encomenda é iniciada com um cliente mas sem Gestor este serve para num objecto com uma encomenda
	 * existente assim inserir um gestor
	 * @param idUtilizador id de utilizador
	 * A encomenda é iniciada com um cliente mas sem Gestor este serve para num objecto com uma encomenda
	 * existente assim inserir um gestor
	 */
	public void setIdUtilizador(int idUtilizador) {
		this.idUtilizador = idUtilizador;
	}


	/**
	 * retorna id de armazenista
	 * @return idArmazenista
	 */
	public int getIdArmazenista() {
		return idArmazenista;
	}


	/**
	 * Inserir um armazenista pois a encomenda inicia apenas com cliente
	 * @param idArmazenista id de armazenista
	 * Inserir um armazenista pois a encomenda inicia apenas com cliente
	 */
	public void setIdArmazenista(int idArmazenista) {
		this.idArmazenista = idArmazenista;
	}


	/**
	 * Retorna id do estafeta
	 * @return idEstafeta
	 */
	public int getIdEstafeta() {
		return idEstafeta;
	}


	/**
	 * Inserir um estafeita pois encomenda apenas inicia com cliente
	 * @param idEstafeta id de estafeta
	 * Inserir um estafeita pois encomenda apenas inicia com cliente
	 */
	public void setIdEstafeta(int idEstafeta) {
		this.idEstafeta = idEstafeta;
	}


	/**
	 * inserir custo de encomenda
	 * @return inserir custo
	 */
	public float getCusto() {
		return custo;
	}


	/**
	 * Modificando o custo no decorrer da conta das tabelas
	 * @param custo custo da encomenda
	 * Modificando o custo no decorrer da conta das tabelas
	 */
	public void setCusto(float custo) {
		this.custo = custo;
	}


	/**
	 * retorna data de criacao
	 * @return DataCriação
	 */
	public Date getDataCriacao() {
		return dataCriacao;
	}



	/**
	 * retorna data de entrega
	 * @return DataEntrega
	 */
	public Date getDataEntrega() {
		return dataEntrega;
	}


	/**
	 * Encomenda iniciar com dataEntrega a null, aqui modifica quando esta é entregue
	 * @param dataEntrega data de entrega da encomenda
	 * Encomenda iniciar com dataEntrega a null, aqui modifica quando esta é entregue
	 */
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}


	/**
	 * retorna estado da encomenda
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}


	/**
	 * modifica o estado da encomenda
	 * @param estado estado da encomenda
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}


	/**
	 * retorna o id do cliente
	 * @return idCliente
	 */
	public int getIdCliente() {
		return idCliente;
	}


	/**
	 * retorna o identificador da encomenda
	 * @return Identificador de encomenda
	 */
	public String getIdentificadorEncomenda() {
		return identificadorEncomenda;
	}


	/**
	 * retorna data de aceitação
	 * @return Data de Aceitação
	 */
	public Date getDataAceitacao() {
		return dataAceitacao;
	}
	
	/**
	 * Iniciar com data de aceitação a null, serve para modificar sua data quando for a
	 * encomenda aceite
	 * @param aData data de aceitacao
	 * Iniciar com data de aceitação a null, serve para modificar sua data quando for a 
	 * encomenda aceite
	 */
	public void setDataAceitacao(Date aData) {
		dataAceitacao = aData;
	}
	
	
	
	
}
