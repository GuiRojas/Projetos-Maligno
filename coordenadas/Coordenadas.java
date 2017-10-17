package coordenadas;

public class Coordenadas<X> {
	protected int absissas,
				  ordenadas;


	public Coordenadas(int x, y){

		this.absissas = x;
		this.ordenadas = y;
	}

	public int getX(){
		return this.absissas;
	}

	public int getY(){
		return this.ordenadas;
	}

	public int hashCode(){
		int ret = 44;

		ret = 7*ret + new Integer(absissas).hashCode();
		ret = 7*ret + new Integer(ordenadas).hashCode();

		return ret;
	}

	public String toString(){
		String ret = "(";

		ret += new Integer (this.absissas).toString;
		ret += "," + new Integer (this.ordenadas).toString;
		ret += ")";

		return ret;
	}

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