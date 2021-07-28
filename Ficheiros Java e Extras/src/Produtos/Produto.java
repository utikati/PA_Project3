package Produtos;
import java.util.*;

/**
 * Classe responsavel pelos produtos
 * @author Jorge Martins e Rodrigo Duro
 *
 */
public class Produto {
	
	private String designacao;
	private String fabricante;
	private int quantidade;
	private float preco;
	private int sku;
	private String lote;
	private Date dataProducao;
	private Date dataValidade;
	private float peso;
	private Categoria categoria;
	private String obs;
	
	/**
	 * Construtor do objecto Produto
	 * @param designacao designacao
	 * @param fabricante fabricante
	 * @param quantidade quantidade de produto
	 * @param preco preco por unidade
	 * @param sku sku unico do produto
	 * @param lote lote do produto
	 * @param dataProducao data de producao
	 * @param aCategoria categoria do produto
	 * Construtor do objecto Produto
	 */
	public Produto(String designacao, String fabricante, int quantidade, float preco, int sku, String lote,
			Date dataProducao, Categoria aCategoria) {
		
		this.designacao = designacao;
		this.fabricante = fabricante;
		this.quantidade = quantidade;
		this.preco = preco;
		this.sku = sku;
		this.lote = lote;
		this.dataProducao = dataProducao;
		categoria = aCategoria;
	}
	
	/**
	 * Construtor vazio do objecto Produto, apenas para iniciar o objecto sem inserir dados
	 */
	public Produto() {
		this("Sem designacao", "sem fabricante", 0, 0, 0, "alote233", new Date(), new Categoria());
	}
	
	/**
	 * Metodo generico toString
	 */
	@Override
	public String toString() {
		return "Designacao: "+designacao+" Quantidade: "+quantidade+" SKU: "+sku+" preco: "+preco+"\n";
	}

	/**
	 * Retorna observacoes
	 * @return String
	 */
	public String getObs(){
		return obs;
	}

	/**
	 * Usado para inserir as observacoes no objecto sem ser no construct
	 * @param aObs observacoes
	 */
	public void setObs(String aObs){
		this.obs = aObs;
	}
	
	/**
	 * retorna categoria
	 * @return categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * retorna designacao do produto
	 * @return designacao
	 */
	public String getDesignacao() {
		return designacao;
	}

	/**
	 * Modificar a sua designacao
	 * @param designacao designacao
	 * Modificar a sua designacao
	 */
	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	/** retorna o fabricante do produto
	 * @return fabricante
	 */
	public String getFabricante() {
		return fabricante;
	}

	/**
	 * Modificar o seu fabricante
	 * @param fabricante fabricante
	 * Modificar o seu fabricante
	 */
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	/**
	 * retorna a quantidade do produto
	 * @return quantidade de produto
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * Modificar a sua quantidade / stock
	 * @param quantidade quantidade
	 * Modificar a sua quantidade / stock
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * retorna o preco
	 * @return preco
	 */
	public float getPreco() {
		return preco;
	}

	/**
	 * Modificar o seu preco
	 * @param preco preco
	 * Modificar o seu preco
	 */
	public void setPreco(float preco) {
		this.preco = preco;
	}

	/**
	 * retorna o lote
	 * @return lote
	 */
	public String getLote() {
		return lote;
	}

	/**
	 * modificar o seu lote de produto
	 * @param lote lote
	 * modificar o seu lote de produto
	 */
	public void setLote(String lote) {
		this.lote = lote;
	}

	/**
	 * retorna a Data de Producao
	 * @return Data de produção
	 */
	public Date getDataProducao() {
		return dataProducao;
	}

	/**
	 * Modificar a sua data de produção
	 * @param dataProducao data de producao
	 * Modificar a sua data de produção
	 */
	public void setDataProducao(Date dataProducao) {
		this.dataProducao = dataProducao;
	}

	/**
	 * retorna a Data de Validade do Produto
	 * @return Data Validade do produto
	 */
	public Date getDataValidade() {
		return dataValidade;
	}

	/**
	 * Modificar a sua data de validade
	 * @param dataValidade data de validade
	 * Modificar a sua data de validade
	 */
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	/**
	 * retorna o peso do produto
	 * @return peso 
	 */
	public float getPeso() {
		return peso;
	}

	/**
	 * Modificar o peso do produto
	 * @param peso peso do produto
	 * Modificar o peso do produto
	 */
	public void setPeso(float peso) {
		this.peso = peso;
	}

	/**
	 * retorna o sku do produto
	 * @return SKU
	 */
	public int getSku() {
		return sku;
	}
	
	
}
