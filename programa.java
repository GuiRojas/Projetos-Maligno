import pilha.*;
import fila.*;
import coordenadas.*;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class Programa{
	public static void main(String[] args){
		try{
			BufferedReader teclado = new BufferedReader( new InputStreamReader(System.in));

			System.out.print("Digite o nome do arquivo");

     		String caminhoParaOArquivo = teclado.readLine();

			FileReader arquivo = new FileReader(caminhoParaOArquivo);
			BufferedReader lerArq = new BufferedReader(arquivo);

        	int li = Integer.parseInt(lerArq.readLine());
        	int co = Integer.parseInt(lerArq.readLine());

        	Character[][] labirinto = new Character[li][co];


        	for(int i=0;i<=li;i++){
        		String str = lerArq.readLine();
        		char[] vetorChar = str.toCharArray();

				for(int j=0;j<=co;j++)
					labirinto[i][j] = vetorChar[j];
        	}

        	Pilha<Coordenadas> caminho = new Pilha<Coordenadas>(co*li);

        	Pilha<Fila<Coordenadas>> possibilidades = new Pilha<Fila<Coordenadas>>(co*li);

        	for(int i=0;i<=li;i++){
        		if((i==0)||(i==li)){
        			for(int j=0;j<=co;j++){
        				if(labirinto[i][j]=='E'){
        					Coordenadas atual = new Coordenadas(i,j);
        					break;
        				}
        			}
        		}else{
        			if(labirinto[i][0]){ ///parei aqui
        				Coordenadas atual = new Coordenadas(i,0);
        				break;
        			}else if(labirinto[i][co]){
        				Coordenadas atual = new Coordenadas(i,co);
        				break;
        			}
        		}
        		throw new Exception("Entrada não encontrada");
        	}

        	//boolean de modo
        	//true  == prosseguir
        	//false == regredir
        	boolean prog = false;

        	//fila criada antes para fazer a primeira verificação do if de instanciar filas
        	Fila<Coordenadas> fila = new Fila<Coordenadas>(3);

        	while(labirinto[atual.getX][atual.getY]!='S'){
        		//roda enquanto não achar a saída

        		if(labirinto[atual.getX()][atual.getY()]=='S'){
        			//caso atual seja a saída

        			for(int i=0;i<=co;i++){
        				String show = "";
        				for(int j=0;j<=li;j++)
        					show+=labirinto[i][j].toString;
        				System.out.println(show);
        			}

        			Pilha<Coordenadas> inverso[] = new Pilha<Coordenadas>[co*li];

        			inverso.empilhe(caminho.getElemento());
        			caminho.desempilhe();

        			inverso.toString();

        			while(!(inverso.vazio())){
        				inverso.desempilhe();
        			}

        			break;

        		}


        		if(fila.vazio()&&prog)
					Fila<Coordenadas> fila = new Fila<Coordenadas>(3);

        		/*
				prog permanece false até achar um espaço vazio,assim virando true
        		*/
				if (fila.vazio()){
	        		if(labirinto[atual.getX()][atual.getY()-1]==' '||
	        		   labirinto[atual.getX()][atual.getY()-1]=='S'){

	        			//achar em cima
	        			fila.enfileire(labirinto[atual.getX()][atual.getY()-1]);
	        			prog = true;

	        		}
	        		if(labirinto[atual.getX()-1][atual.getY()]==' '||
	        			     labirinto[atual.getX()-1][atual.getY()]=='S'){

	        			//achar na esquerda
	        			fila.enfileire(labirinto[atual.getX()][atual.getY()-1]);
	        			prog = true;

	        		}
	        		if(labirinto[atual.getX()+1][atual.getY()]==' '||
	        				 labirinto[atual.getX()+1][atual.getY()]=='S'){

	        			//achar na direita
						fila.enfileire(labirinto[atual.getX()][atual.getY()-1]);
						prog = true;

	        		}
	        		if(labirinto[atual.getX()][atual.getY()+1]==' '||
	        				 labirinto[atual.getX()][atual.getY()+1]=='S'){

	        			//achar em baixo
	        			fila.enfileire(labirinto[atual.getX()][atual.getY()-1]);
	        			prog = true;

	        		}
        		}

    			if( !prog && fila.vazio()){
    				//caso alguem adjacente seja a entrada,
    				//não há solução

    				throw new Exception("Não há como completar o labirinto!");

    			}
        		//prosseguir caso encontre algo



        		if(prog){
        			//rodar modo de progressão

        			//não precisar verificar se é null,
	        		//pois se chegou aqui É porque tem algo
	    			atual = fila.getElemento();
	    			fila.desenfilere();

	        		labirinto[atual.getX()][atual.getY()]="*";

	        		caminho.empilhe(atual);

	        		possibilidades.empilhe(fila);

	        		prog=true;
        		}else{

        			atual = caminho.getElemento();

        			caminho.desempilhe();

        			labirinto[getX()][getY()]=' ';

        			fila = possibilidades.getElemento();

        			possibilidades.desempilhe();

        			prog=false;
        		}
			}

		}catch(Exception err){

		}
	}
}