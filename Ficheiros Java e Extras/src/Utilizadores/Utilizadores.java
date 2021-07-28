/**
 * Package dos utilizadores
 */
package Utilizadores;

/**
 * Classe Pai dos utilizadores, usada para os Gestores
 * @author Jorge Martins e Rodrigo Duro
 *
 */
public class Utilizadores {
	
	private String nome;
	private String login;
	private String pass;
	private String estado;
	private String email;
	private String tipo;
	private String obs = "";
	private String foto = "./Img/placeholderFoto.jpg";
	
	/**
	 * Construtor do objecto Utilizadores, classe Pai de Clientes e "Avo" de Funcionarios
	 * @param aNome nome do utilizador
	 * @param aLogin login do utilizador
	 * @param aPass pass do utilizador
	 * @param aEstado estado da conta
	 * @param aEmail email do utilizador
	 * @param aTipo tipo de conta
	 * Construtor do objecto Utilizadores, classe Pai de Clientes e "Avo" de Funcionarios
	 */
	public Utilizadores(String aNome, String aLogin, String aPass, String aEstado, String aEmail, String aTipo) {
		nome = aNome;
		login = aLogin;
		pass = aPass;
		estado = aEstado;
		email = aEmail;
		tipo = aTipo;
	}

	//usarei em caso de erro o null para verificar.
	/**
	 * Construtor vazio da classe
	 */
	public Utilizadores() {
		this("Sem nome", null, null, null, null, null);
	}
	/**
	 * Para que esta classe possa atualizar o caminho da fotografia de perfil
	 * @param foto caminho da foto
	 */
	public void setFoto(String foto){
		this.foto = foto;
	}

	/**
	 * esta classe para receber a foto vinda da base de dados
	 * @return string caminho foto
	 */
	public String getFoto(){
		return this.foto;
	}

	/**
	 * Metodo de obter as observa��es
	 * @return string
	 */
	public String getObs(){
		return obs;
	}

	/**
	 * Metodo para inserir as observa��es nos Utilizadores
	 * @param obs observacoes
	 */
	public void setObs(String obs){
		this.obs = obs;
	}


	/**
	 * Metodo Generico toString
	 */
	public String toString() {
		return "Nome: " + nome + " Login: " + login + " Tipo: " + tipo + "\n";
	}

	//Get e Setter
	/**
	 * retorna nome
	 * @return Nome nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Mudar o nome, mudan�a de dados
	 * @param aNome nome de user
	 * Mudar o nome, mudan�a de dados
	 */
	public void setNome(String aNome) {
		nome = aNome;
	}

	/**
	 * retorn login
	 * @return Login do user
	 */
	public String getLogin() {
		return login;
	}


	/**
	 * retorna password
	 * @return Password
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * Mudan�a da sua password
	 * @param pass password
	 * Mudan�a da sua password
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * retorna estado de conta
	 * @return estado de conta
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Mudan�a do estado de conta
	 * @param estado estado
	 * Mudan�a do estado de conta
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * returna email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * mudan�a do seu contacto de email
	 * @param email email
	 * mudan�a do seu contacto de email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * retorna o tipo de conta (gestor, funcionario, cliente)
	 * @return tipo tipo de conta
	 * retorna o tipo de conta (gestor, funcionario, cliente)
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Mudar o seu tipo de conta, n�o implementado apenas para evolu��o futura do software
	 * gestor puder dar upgrade nas contas
	 * @param tipo tipo de conta
	 * Mudar o seu tipo de conta, n�o implementado apenas para evolu��o futura do software
	 * gestor puder dar upgrade nas contas
	 * 
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
