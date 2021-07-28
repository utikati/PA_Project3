package Produtos;

/**
 * Classe relativa �s quantidades de produtos dentro de uma encomenda (varios produtos e cada um
 * com sua quantidade)
 * @author Jorge Martins e Rodrigo Duro
 * Classe relativa �s quantidades de produtos dentro de uma encomenda (varios produtos e cada um
 * com sua quantidade)
 */
public class Encomenda_Produto {
	private int idEncomenda;
	private int idProduto;
	private int quantidadeProduto;
	
	/**
	 * Construtor da classe com o ID do produto e a sua quantidade
	 * @param aIdProduto id do produto
	 * @param aQuantidade quantidade
	 * Construtor da classe com o ID do produto e a sua quantidade
	 */
	public Encomenda_Produto(int aIdProduto, int aQuantidade) {
		idProduto = aIdProduto;
		quantidadeProduto = aQuantidade;
	}

	/**retorna o id da encomenda
	 * @return IDEncomenda
	 */
	public int getIdEncomenda() {
		return idEncomenda;
	}

	/**
	 * Modifica o id de encomenda
	 * @param idEncomenda id de encomenda
	 * Modifica o id de encomenda
	 * Usado quando se vai buscar informa��o na base de dados ao iniciar o objecto
	 * � usado o construtor e em seguida realizado um set no objecto para lhe inserir o id da sua
	 * encomenda
	 */
	public void setIdEncomenda(int idEncomenda) {
		this.idEncomenda = idEncomenda;
	}

	/**
	 * retorna o id do produto
	 * @return idProduto
	 */
	public int getIdProduto() {
		return idProduto;
	}

	/**
	 * retorna a quantidade do produto
	 * @return quantidade de Produto
	 */
	public int getQuantidadeProduto() {
		return quantidadeProduto;
	}

	/**
	 * Metodo generico toString
	 */
	@Override
	public String toString() {
		return "Encomenda_Produto [idEncomenda=" + idEncomenda + ", idProduto=" + idProduto + ", quantidadeProduto="
				+ quantidadeProduto + "]";
	}
	
	
}
