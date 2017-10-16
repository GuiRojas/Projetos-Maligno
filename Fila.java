public class Fila<X> implements Cloneable{
	protected Object[] vetor;
	protected float txDC;

	protected int inicio,
				  fim,
				  qtd;

	public Fila(){
		this.inic();
	}

	public Fila(int tam){
		try{
			this.inic(tam);
		}catch(Exception erro){}
	}

	protected void inic(){
		try{
			this.inic(10);
		}catch(Exception erro){}
	}

	protected void inic(int tam) throws Exception{
		if(tam<=0)
			throw new Exception("Tamanho invalido");

		this.vetor = new Object[tam];
		this.inicio = 0;
		this.fim = -1;
		this.qtd = 0;
	}

	public int getQtd(){
		return this.qtd;
	}

	public int getFim(){
		return this.fim;
	}

	public int getInicio(){
		return this.inicio;
	}

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

	public void desenfilere()throws Exception{
		if(this.vazio())
			throw new Exception("Não há o que ser removido");

		this.vetor[this.inicio]=null;
		if(this.inicio+1==this.vetor.length)
			this.inicio=0;
		else
			this.inicio++;

		this.qtd--;
	}

	public Object getElemento()throws Exception{
		if(this.vazio())
			throw new Exception("Não há elemento");

		return this.vetor[this.inicio];
	}

	public boolean vazio(){
		if(this.qtd==0)
			return true;
		return false;
	}

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

	public String toString(){
		String ret = "{ ";

		for(int i=0;i<=this.vetor.length;i++)
			ret+=this.vetor[i].toString()+(i!=this.vetor.length ?",":"");

		return ret + " }";
	}

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

	public Object clone(){
		Fila ret=null;

		try{
			ret= new Fila(this);
		}catch(Exception erro){}

		return ret;
	}

}