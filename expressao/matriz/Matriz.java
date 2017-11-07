package matriz;

/**
*	Classe para Consultar uma matriz de operadores em relação a outros operadores
*
*	Possui construtor, a matriz dos operadores e os metodos obrigatorios
*
*	@since 2017
*
*	@author Francisco 17178 e Guilherme 17182
*/
public class Matriz{
	protected int oprAtv,
				  oprRef;

	protected boolean[][] matriz;

	public Matriz(){
		matriz = new boolean[][]{
		//     0 0   0 1   0 2   0 3   0 4   0 5   0 6
		//      (     ^     *     /     +     -     )
		/*(*/{false,false,false,false,false,false,true},
		/*^*/{false,false,true ,true ,true ,true ,true},
		/***/{false,false,true ,true ,true ,true ,true},
		/*/*/{false,false,true ,true ,true ,true ,true},
		/*+*/{false,false,false,false,true ,true ,true},
		/*-*/{false,false,false,false,true ,true ,true},
		/*)*/{false,false,false,false,false,false,true}
		};

		this.oprAtv = 0;
		this.oprRef = 0;
	}

	public boolean verificar(String atv, String ref){

		switch(atv){
			case "(":
				this.oprAtv = 0;
				break;
			case "^":
				this.oprAtv = 1;
				break;
			case "*":
				this.oprAtv = 2;
				break;
			case "/":
				this.oprAtv = 3;
				break;
			case "+":
				this.oprAtv = 4;
				break;
			case "-":
				this.oprAtv = 5;
				break;
			case ")":
				this.oprAtv = 6;
				break;
			default:
				this.oprAtv = 0;
				break;
		}
		switch(ref){
			case "(":
				this.oprRef = 0;
				break;
			case "^":
				this.oprRef = 1;
				break;
			case "*":
				this.oprRef = 2;
				break;
			case "/":
				this.oprRef = 3;
				break;
			case "+":
				this.oprRef = 4;
				break;
			case "-":
				this.oprRef = 5;
				break;
			case ")":
				this.oprRef = 6;
				break;
			default:
				this.oprRef = 0;
				break;
		}

		return this.matriz[oprRef][oprAtv];
	}

	/*
		Embora mude os atributos dentro da classe,
		as 2 variaveis são apenas para facilitar
		o uso da matriz, logo não terá clone
	*/

	public int hashCode(){
		int ret = 2;

		for(int i=0;i<7;i++){
			for(int j=0;j<7;j++){
				if(this.matriz[i][j]){
					ret = ret * 13 + 1; //+1 pois é true
				}else{
					ret = ret * 13 + 0; //+0 pois é false
				}
			}
		}

		return ret;

	}

	public String toString(){
		String ret="{";

		for(int i=0;i<7;i++){
			ret = ret + "{";
			for(int j=0;j<7;j++){
				if(this.matriz[i][j]){
					ret = ret + "true  ";
				}else{
					ret = ret + "false ";
				}
			}
			ret = ret + "}";
		}

		return ret+"}";
	}

	public boolean equals(Object obj){
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof Matriz))
			return false;

		Matriz mtz = (Matriz)obj;

		if (this.oprAtv != mtz.oprAtv)
			return false;

		if (this.oprRef != mtz.oprRef)
			return false;

		return true;
	}

	public Matriz (Matriz modelo) throws Exception{
		if(modelo==null)
			throw new Exception("Modelo nulo");

		this.matriz = modelo.matriz;
		this.oprRef = modelo.oprRef;
		this.oprAtv = modelo.oprAtv;
	}

	/**
	*Clone
	*
	*@return Object outra Matriz igual a essa.
	*/

	public Object clone(){
		Matriz ret=null;

		try{
			ret= new Matriz(this);
		}catch(Exception erro){}

		return ret;
	}

}