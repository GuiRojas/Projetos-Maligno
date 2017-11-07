package pilha;

/**
*Classe Pilha, que armazena qualquer Classe.
*
*@param X Classe a ser armazenada.
*
*@since 2017
*
*@author Francisco 17178 e Guilherme 17182
*/

public class Pilha<X> implements Cloneable{
	protected Object[] vetor;
	protected float txDC;

	protected int topo;

	public Pilha() throws Exception{
		this(10);
	}

	public Pilha(int tam) throws Exception{
		this(tam,50);
	}

	/**
	*Construtor, declara a Pilha e seu tamanho, alem da taxa de Crescimento.
	*
	*@param tam Tamanho da Pilha.
	*@param tc Taxa de crescimento para quando nao houver espaço na Pilha.
	*
	*@throw Exception caso os valores sejam invalidos (0 ou negativo).
	*/

	public Pilha(int tam,float tc)throws Exception{
		try{
			if(tam<=0){
				throw new Exception("Tamanho Invalido");
			}
			if(tc<=0){
				throw new Exception("Taxa Invalida");
			}

			this.vetor = new Object[tam];
			this.topo = -1;
			this.txDC = tc;
		}catch(Exception erro){}
	}

	public int getTopo(){
		return this.topo;
	}

	/**
	*Empilha Instancias no vetor da Pilha. Cresce o vetor caso ele esteja cheio.
	*
	*@param x Objeto da instancia a ser empilhada.
	*
	*@throw Exception caso a instancia seja null.
	*/

	public void empilhe(X x) throws Exception{
		if (x==null) {
			throw new Exception("Valor nulo nao pode ser adicionado");
		}

		if(this.topo+1==this.vetor.length)
			this.crescer();

		this.topo++;

		this.vetor[topo]=x;
	}

	/**
	*Desempilha a instancia na maior ultima posicao preenchida do vetor.
	*
	*@throw Exception caso a Pilha esteja vazia.
	*/

	public void desempilhe() throws Exception{
		if(this.vazio())
			throw new Exception("Nao ha o que desempilhar!");

		this.vetor[topo]=null;
		topo--;
	}

	protected void crescer(){
		float mult = this.txDC/100+1;
        int tamNovo = (int)Math.ceil(this.vetor.length*mult);

		Object[] novo = new Object[tamNovo];

		for(int  i=0;i<=this.topo;i++)
			novo[i]=this.vetor[i];

		this.vetor = novo;
	}

	/**
	*Retorna o valor da instancia na ultima posicao preenchida do vetor.
	*
	*@throw Exception caso a Pilha esteja vazia.
	*
	*@return Object Instancia na ultima posicao preenchida do vetor.
	*/

	public X getElemento() throws Exception{
		if(this.vazio())
			throw new Exception("Pilha esta vazia!");

		return (X)this.vetor[topo];
	}

	/**
	*Verifica se a Pilha está vazia
	*/

	public boolean vazio(){
		if(topo==-1)
			return true;
		return false;
	}

	/**
	*hashCode
	*
	*@return int hashCode da Pilha e suas Instancia armazenadas.
	*/

	public int hashCode(){
		int ret = 2;

		ret = ret * 13 + new Integer(this.topo).hashCode();
		ret = ret * 13 + new Float(this.txDC).hashCode();

		for(int i=0;i<=this.vetor.length;i++)
			ret = ret * 13 + this.vetor[i].hashCode();

		return ret;
	}

	/**
	*toString
	*
	*@return String Versão em texto de todas as instancias armazenadas.
	*/

	public String toString(){
		String ret = "{ ";

		for(int i=0;i<=this.topo;i++)
			ret+=this.vetor[i].toString()+(i!=this.topo?",":"");

		return ret + " }";
	}

	/**
	*equals
	*
	*@return boolean Verifica se outra classe é identica (Em classe e conteudo) a essa.
	*/

	public boolean equals(Object obj){
		if(this==obj)
			return true;
		if(this==null)
			return false;
		if(!(obj instanceof Pilha))
			return false;

		Pilha pil = (Pilha)obj;

		if(this.topo!=pil.topo)
			return false;
		if(this.txDC!=pil.txDC)
			return false;
		for(int i=0;i<=this.vetor.length;i++){
			if(!(this.vetor[i].equals(pil.vetor[i])))
				return false;
		}

		return true;
	}

	/**
	*Construtor de Copia
	*
	*@return Pilha outra Pilha <i>identica</i> a essa.
	*/

	public Pilha (Pilha modelo) throws Exception{
		if(modelo==null)
			throw new Exception("Modelo nulo");

		this.vetor = new Object[modelo.vetor.length];
		for(int i=0;i<=this.vetor.length;i++){

        this.vetor[i] = modelo.vetor[i];
		}
		this.topo=modelo.topo;
		this.txDC=modelo.txDC;
	}

	/**
	*Clone
	*
	*@return Object Chama o construtor de copia e retorna a nova Pilha.
	*/

	public Object clone(){
		Pilha ret=null;

		try{
			ret= new Pilha(this);
		}catch(Exception erro){}

		return ret;
	}

}