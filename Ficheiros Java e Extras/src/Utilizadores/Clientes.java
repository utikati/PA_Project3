package Utilizadores;

/**
 * Objecto Cliente herda do objecto Utilizadores
 * @author Jorge Martins e Rodrigo Duro
 *Objecto Cliente herda do objecto Utilizadores
 */
public class Clientes extends Utilizadores {
	
	private int contribuinte;
	private int contacto;
	private String morada;
	
	/**
	 * Construtor do Objecto Cliente
	 * @param aNome nome de utilizador
	 * @param aLogin login de utilizador
	 * @param aPass pass de user
	 * @param aEstado estado de conta
	 * @param aEmail email de utilizador
	 * @param aTipo tipo de conta
	 * @param aContribuinte contribuinte de user
	 * @param aContacto contacto de utilizador
	 * @param aMorada morada de utilizador
	 * Construtor do Objecto Cliente
	 */
	public Clientes(String aNome, String aLogin, String aPass, String aEstado, String aEmail, String aTipo, int aContribuinte, int aContacto, String aMorada) {
		super(aNome, aLogin, aPass, aEstado, aEmail, aTipo);
		contribuinte = aContribuinte;
		contacto = aContacto;
		morada = aMorada;
	}
	
	/**
	 * Construtor vazio
	 */
	public Clientes() {
		super();
		contribuinte = 0;
		contacto = 0;
		morada = "";
	}

	/**
	 * @return contribuinte
	 */
	public int getContribuinte() {
		return contribuinte;
	}
	
	/**
	 * Alterar seus dados de contribuinte
	 * @param aContribuinte contribuinte
	 * Alterar seus dados de contribuinte
	 */
	public void setContribuinte(int aContribuinte) {
		contribuinte = aContribuinte;
	}


	/**
	 * Retorna contacto
	 * @return contacto
	 */
	public int getContacto() {
		return contacto;
	}

	/**
	 * alterar seus dados de contacto
	 * @param aContacto contacto
	 * alterar seus dados de contacto
	 */
	public void setContacto(int aContacto) {
		contacto = aContacto;
	}

	/**
	 * Retorna morada
	 * @return String morada
	 */
	public String getMorada() {
		return morada;
	}

	/**
	 * Alterar a sua morada
	 * @param aMorada morada
	 * Alterar a sua morada
	 */
	public void setMorada(String aMorada) {
		morada = aMorada;
	}

	/**
	 * To string metodo
	 * Metodo Generico toString
	 */
	@Override
	public String toString() {
		return "Nome: " + super.getNome() + " Login: " + super.getLogin() + " Tipo: " + super.getTipo() + "\n";
	}
	
	

}
