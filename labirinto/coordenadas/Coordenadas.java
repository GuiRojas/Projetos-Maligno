package coordenadas;

/**
*	Classe para guardar coodernadas de um plano cartesiano
*
*	Possui construtor, get das variaveis e os metodos obrigatorios
*
*	@since 2017
*
*	@author Francisco 17178 e Guilherme 17182
*/
public class Coordenadas<X> {
	protected int absissas,
				  ordenadas;

	/**
*	Construtor da classe Coordenadas
*
*	Atribui parametros as variaveis globais
*
*	@paramx variavel que define a absissa desejada
*	@paramy variavel que define a ordenada desejada
*
*	@throw se nao passar os valores x e y
	*/
	public Coordenadas(int x,int y){
		this.absissas = x;
		this.ordenadas = y;
	}

	/**
*	Retorna o valor da absissa da coordenada
*
*	@return retorna o valor da absissa da coodernada guardada
	*/
	public int getX(){
		return this.absissas;
	}

	/**
*	Retorna o valor da ordenada da coordenada
*
*	@return retorna o valor da ordenada da coodernada guardada
	*/
	public int getY(){
		return this.ordenadas;
	}

	/**
*	Metodo obrigatorio
*
*	@return retorna o hash code para o usuario
	*/
	public int hashCode(){
		int ret = 44;

		ret = 7*ret + new Integer(absissas).hashCode();
		ret = 7*ret + new Integer(ordenadas).hashCode();

		return ret;
	}

	/**
*	Metodo obrigatorio
*
*	@return retorna uma string com as coordenadas para o usuario
	*/
	public String toString(){
		String ret = "(";

		ret += new Integer (this.absissas).toString();
		ret += "," + new Integer (this.ordenadas).toString();
		ret += ")";

		return ret;
	}

	/**
*	Metodo obrigatorio
*
*	@param obj objeto que sera comparado com this
*
*	@return compara dois Objects e retorna se sao iguais ou nao
	*/
	public boolean equals(Object obj){
		if(this==obj)
			return true;

		if(this==null)
			return false;

		if(!(obj instanceof Coordenadas))
			return false;

		Coordenadas coo = (Coordenadas)obj;

		if (this.absissas != coo.absissas)
			return false;

		if(this.ordenadas != coo.ordenadas)
			return false;

		return true;
	}

}