package fila;

/**
*Fila de instancias da classe X.
*
*@param X Tipo de classe a ser armazenada.
*
*@since 2017.
*
*@author Francisco 17178 e Gulherme 17182.
*/
public class Fila<X> implements Cloneable{
	protected Object[] vetor;
	protected float txDC;

	protected int inicio,
				  fim,
				  qtd;

	public Fila()throws Exception{
		this(10);
	}

	/**
	*Construtor.
	*
	*@param tam Tamanho do vetor.
	*/
	public Fila(int tam)throws Exception{
		if(tam<=0)
			throw new Exception("Tamanho invalido");

		this.vetor = new Object[tam];
		this.inicio = 0;
		this.txDC = 50;
		this.fim = -1;
		this.qtd = 0;
	}

	/**
	*Retorna quantidade de Instancias na Fila.
	*
	*@return int Quantidade de Instancias na Fila.
	*/

	public int getQtd(){
		return this.qtd;
	}

	/**
	*Retorna a ultima posicao preenchida da Fila.
	*
	*@return int Retorna a ultima posicao preenchida da Fila.
	*/

	public int getFim(){
		return this.fim;
	}

	/**
	*Retorna a primeira posicao preenchida da Fila.
	*
	*@return int Retorna a primeira posicao preenchida da Fila.
	*/

	public int getInicio(){
		return this.inicio;
	}

	/**
	*Adiciona uma instancia a fila. Cresce caso esteja cheia.
	*
	*@param x Objeto da instancia a adicionar
	*
	*@throw Exception Caso tente adicionar algo null
	*/

	public void enfileire(X x)throws Exception{
		if(x==null)
			throw new Exception("Inserção de valor nulo");

		if(this.fim+1==this.vetor.length)
			if(this.qtd==this.vetor.length)
				this.crescer();
			else
				this.fim=0;
		else
			this.fim++;

		this.vetor[this.fim]=x;
		this.qtd++;
	}

	protected void crescer(){
		float mult = this.txDC/100+1;
        int   tamNovo = (int)Math.ceil(this.vetor.length*mult);

		Object[] novo = new Object[tamNovo];

		for(int i=0;i<=this.vetor.length;i++)
			novo[i]=this.vetor[i];

		this.vetor = novo;
	}

	/**
	*Retira o primeiro valor do vetor.
	*
	*@throw Exception Caso o vetor esteja vazio.
	*/

	public void desenfileire()throws Exception{
		if(this.vazio())
			throw new Exception("Não há o que ser removido");

		this.vetor[this.inicio]=null;
		if(this.inicio+1==this.vetor.length)
			this.inicio=0;
		else
			this.inicio++;

		this.qtd--;
	}

	/**
	*Retorna o primeiro valor preenchido do vetor.
	*
	*@return Object Retorna a primeira instancia do vetor.
	*/

	public X getElemento()throws Exception{
		if(this.vazio())
			throw new Exception("Não há elemento");

		return (X)this.vetor[this.inicio];
	}

	/**
	*Verifica se o vetor esta vazio.
	*
	*@return boolean Verifica se o vetor esta vazio.
	*/

	public boolean vazio(){
		if(this.qtd==0)
			return true;
		return false;
	}

	/**
	*hashCode
	*
	*@return int hashCode
	*/

	public int hashCode(){
		int ret = 2;

		ret = ret * 13 + new Integer(this.inicio).hashCode();
		ret = ret * 13 + new Integer(this.fim).hashCode();
		ret = ret * 13 + new Integer(this.qtd).hashCode();

		for(int i=0;i<=this.vetor.length;i++)
			if(!(this.vetor[i]==null)){
				ret = ret * 13 + this.vetor[i].hashCode();
			}

		return ret;
	}

	/**
	*toString
	*
	*@return String toString
	*/

	public String toString(){
		String ret = "{ ";

		//---######---
		if(this.fim>this.inicio){
			for(int i=this.inicio;i<=this.fim;i++){
				if(this.vetor[i]!=null){
					ret+=this.vetor[i].toString();
				}
			}
		}else
		//###------###
		if(this.inicio>this.fim){
			for(int i=this.inicio;i<=this.vetor.length;i++){
				if(this.vetor[i]!=null){
					ret+=this.vetor[i].toString();
				}
			}
			for(int i=0;i<=this.fim;i++){
				if(this.vetor[i]!=null){
					ret+=this.vetor[i].toString();
				}
			}
		}else{
			//caso fim==inicio
			ret = ret + "empty";
		}

		return ret + " }";
	}

	/**
	*equals
	*
	*@param obj Objeto da instancia a ser comparada
	*
	*@return boolean Verifica se o parametro é igual ao this
	*/

	public boolean equals(Object obj){
		if(this==obj)
			return true;
		if(this==null)
			return false;
		if(!(obj instanceof Fila))
			return false;

		Fila fil = (Fila)obj;

		if(this.inicio!=fil.inicio)
			return false;
		if(this.fim!=fil.fim)
			return false;
		if(this.qtd!=fil.qtd)
			return false;
		for(int i=0;i<=this.vetor.length;i++){
    			if(!(this.vetor[i].equals(fil.vetor[i])))
				return false;
		}

		return true;
	}

	public Fila (Fila modelo) throws Exception{
		if(modelo==null)
			throw new Exception("Modelu nulo");

		this.vetor = new Object[modelo.vetor.length];
		for(int i=0;i<=this.vetor.length;i++){
			if(!(modelo.vetor[i]==null)){
                this.vetor[i] = modelo.vetor[i];
			}
		}
		this.inicio=modelo.inicio;
		this.fim=modelo.fim;
		this.qtd=modelo.qtd;
	}

	/**
	*Clone
	*
	*@return Object outra Fila igual a essa.
	*/

	public Object clone(){
		Fila ret=null;

		try{
			ret= new Fila(this);
		}catch(Exception erro){}

		return ret;
	}

}