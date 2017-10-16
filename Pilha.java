public class Pilha<X> implements Cloneable{
	protected Object[] vetor;
	protected float txDC;
	
	protected int topo;

	public Pilha(){
		this.inic();
	}

	public Pilha(int tam){
		this.inic(tam);
	}

	public Pilha(int tam,float tc){
		try{
			this.inic(tam,tc);
		}catch(Exception erro){}
	}

	protected void inic(){
		this.inic(10);
	}

	protected void inic(int tam){
		try{
			this.inic(tam,50);
		}catch(Exception erro){}
	}

	protected void inic(int tam, float tx) throws Exception{
		if(tam<=0){
			throw new Exception("Tamanho Inválido");
		}
		if(tx<=0){
			throw new Exception("Taxa Inválida");
		}

		this.vetor = new Object[tam];
		this.topo = -1;
		this.txDC = tx;
	}

	public int getTopo(){
		return this.topo;
	}

	public void empilhe(X x) throws Exception{
		if (x==null) {
			throw new Exception("Valor nulo não pode ser adicionado");
		}

		if(this.topo+1==this.vetor.length)
			this.crescer();

		this.topo++;

		this.vetor[topo]=x;
	}

	public void desempilhe(X x) throws Exception{
		if(this.vazio())
			throw new Exception("Não há o que desempilhar!");

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

	public Object getElemento() throws Exception{
		if(this.vazio())
			throw new Exception("Pilha está vazia!");

		return this.vetor[topo];
	}

	public boolean vazio(){
		if(topo==-1)
			return true;
		return false;
	}

	public int hashCode(){
		int ret = 2;

		ret = ret * 13 + new Integer(this.topo).hashCode();
		ret = ret * 13 + new Float(this.txDC).hashCode();

		for(int i=0;i<=this.vetor.length;i++)
			ret = ret * 13 + this.vetor[i].hashCode();

		return ret;
	}

	public String toString(){
		String ret = "{ ";

		for(int i=0;i<=this.topo;i++)
			ret+=this.vetor[i].toString()+(i!=this.topo?",":"");

		return ret + " }";
	}

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

	public Pilha (Pilha modelo) throws Exception{
		if(modelo==null)
			throw new Exception("Modelu nulo");

		this.vetor = new Object[modelo.vetor.length];
		for(int i=0;i<=this.vetor.length;i++){

        this.vetor[i] = modelo.vetor[i];
		}
		this.topo=modelo.topo;
		this.txDC=modelo.txDC;
	}

	public Object clone(){
		Pilha ret=null;

		try{
			ret= new Pilha(this);
		}catch(Exception erro){}

		return ret;
	}

}