import pilha.*;
import fila.*;
import coordenadas.*;

public class Programa(){
	public static void main(String[] args){
		try{
			BufferedReader teclado = new BufferedReader( new InputStreamReader(System.in));

			System.out.print("Digite o nome do arquivo");

     		String caminhoParaOArquivo=teclado.readLine();

     		Scanner arquivo=new Scanner(caminhoParaOArquivo);

     		int li,co;

        	li=arquivo.readLine();
        	co=arquivo.readLine();

        	char labirinto = new Character[li][co];


        	for(int i=0;i<=li;i++){
        		String str = "";

        		str = arquivo.nextLine();

        		while(arquivo.hasNextLine()){
					str = str + "\n" + arquivo.nextLine();
				} 

				labirinto[i][] = str.toCharArray();
        	}

        	Pilha<Coordenadas> caminho = new Pilha<Coordenadas>(co*li);

        	Pilha<Fila<Coordenadas>> possibilidades = new Pilha<Fila<Coordenadas>>(co*li);

        	for(int i=0;i<=li;i++){
        		if((i==0)||(i==li){
        			for(int j=0;j<=co;j++){
        				if(labirinto[i][j]=='E'){
        					Coordenadas atual = new Coordenadas(i,j);
        					break;
        				}        				
        			}
        		}else{
        			if(labirinto[i][0]){
        				Coordenadas atual = new Coordenadas(i,0);
        				break;
        			}else if(labirinto[i][co]){
        				Coordenadas atual = new Coordenadas(i,co);
        				break;
        			}
        		}
        		throw new Exception("Entrada não encontrada")
        	}

		}catch(Exception err){

		}
	}
}