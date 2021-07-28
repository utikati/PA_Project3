package Produtos;

/**
 * Classe referente à categoria do produto
 * @author Jorge Martins e Rodrigo Duro
 * Classe referente à categoria do produto
 */
public class Categoria {
	
	private String designacao;
	private String classificacao;
	
	/**
	 * Construtor da classe
	 * @param descricao descricao categoria
	 * @param classificacao classificacao da categoria
	 * Construtor da classe 
	 */
	public Categoria(String descricao, String classificacao) {
		
		this.designacao = descricao;
		this.classificacao = classificacao;
	}
	/**
	 * Construtor vazio para iniciar o objecto sem instanciar as variaveis
	 */
	public Categoria() {
		this("sem descricao", "sem classificacao");
	}

	/**
	 * retorna designacao da categoria
	 * @return String Designacao da categoria
	 */
	public String getDesignacao() {
		return designacao;
	}

	/**
	 * retorna a classificacao da categoria
	 * @return String Classificação da Categoria
	 */
	public String getClassificacao() {
		return classificacao;
	}

	/**
	 * Metodo toString da classe Categoria
	 */
	@Override
	public String toString() {
		return "Categoria [descricao=" + designacao + ", classificacao=" + classificacao + "]";
	}

	
	
	
	

}
