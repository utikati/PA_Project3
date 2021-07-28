package Utilizadores;
import java.util.*;

/**
 * Classe dos Funcionarios herda da classe clientes
 * @author Jorge Martins e Rodrigo Duro
 *
 */
public class Funcionarios extends Clientes {
	
	private Date dataInicio;
	private String especializacao;
	
	/**
	 * Construtor do objecto Funcionarios
	 * @param aNome nome utilizador
	 * @param aLogin login do utilizador
	 * @param aPass pass
	 * @param aEstado estado da conta
	 * @param aEmail email da conta
	 * @param aTipo tipo de conta
	 * @param aContribuinte contribuinte
	 * @param aContacto contacto
	 * @param aMorada morada
	 * @param aDataInicio data de inicio
	 * @param aEspecializacao especializacao
	 * Construtor do objecto Funcionarios
	 */
	public Funcionarios(String aNome, String aLogin, String aPass, String aEstado, String aEmail, String aTipo, int aContribuinte, int aContacto, String aMorada, Date aDataInicio, String aEspecializacao) {
		super(aNome, aLogin, aPass, aEstado, aEmail, aTipo, aContribuinte, aContacto, aMorada);
		dataInicio = aDataInicio;
		especializacao = aEspecializacao;
	}
	
	/**
	 * Construtor vazio do Objecto Funcionarios
	 */
	public Funcionarios() {
		super();
		dataInicio = null;
		especializacao = null;
	}

	/**
	 * Retorna Data de inicio
	 * @return Data de Inicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * retorna especializacao
	 * @return Especializacao
	 */
	public String getEspecializacao() {
		return especializacao;
	}

	/**
	 * Modificar a especializacao (não implementado neste software)
	 * @param especializacao especializacao
	 * Modificar a especializacao (não implementado neste software)
	 */
	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}

	/**
	 * Metodo to string
	 * Metodo generico toString
	 */
	@Override
	public String toString() {
		return "Nome: " + super.getNome() + " Login: " + super.getLogin() + " Tipo: " + super.getTipo() + " Especializacao: "+especializacao+"\n";
	}
	
	
	

}
