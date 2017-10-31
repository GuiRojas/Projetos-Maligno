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

			System.out.print("Digite o nome do arquivo:");

     		String caminhoParaOArquivo = teclado.readLine();

			FileReader arquivo = new FileReader(caminhoParaOArquivo);
			BufferedReader lerArq = new BufferedReader(arquivo);

        	int li = Integer.parseInt(lerArq.readLine());
        	int co = Integer.parseInt(lerArq.readLine());

        	Character[][] labirinto = new Character[li][co];


        	for(int i=0;i<li;i++){
        		String str = lerArq.readLine();
        		char[] vetorChar = str.toCharArray();

				for(int j=0;j<vetorChar.length;j++)
					labirinto[i][j] = vetorChar[j];
        	}

        	lerArq.close();

        	Pilha<Coordenadas> caminho = new Pilha<Coordenadas>(co*li);

        	Pilha<Fila<Coordenadas>> possibilidades = new Pilha<Fila<Coordenadas>>(co*li);

        	int x = 0;
        	int y = 0;

        	for(int i=0;i<li;i++){
        		if((i==0)||(i==li)){
        			for(int j=0;j<co;j++){
        				if(labirinto[i][j]=='E'){
        					x = i;
        					y = j;
        				}
        			}
        		}else{
					for(int j=0;j<co;j++){
						if((j==0)||(j==co)){
        					if(labirinto[i][j]=='E'){
								x = i;
								y = j;
        					}
						}
					}
        		}
        	}
        	Coordenadas atual = new Coordenadas(x,y);

        	//boolean de modo
        	//true  == prosseguir
        	//false == regredir
        	boolean prog = false;
        	boolean terminou = false;

        	while(!(terminou)){
        		//roda enquanto não achar a saída

				if (atual.getY()+1 != co)
					if (labirinto[atual.getX()][atual.getY()+1]=='S')
						terminou = true;

				if (atual.getY()-1 != -1)
					if (labirinto[atual.getX()][atual.getY()-1]=='S')
						terminou = true;

				if (atual.getX()+1 != li)
					if (labirinto[atual.getX()+1][atual.getY()]=='S')
						terminou = true;

				if (atual.getX()-1 != -1)
					if (labirinto[atual.getX()-1][atual.getY()]=='S')
						terminou = true;

        		if (terminou) {
					for(int i=0;i<li;i++){
	           			String show = "";
	           		    for(int j=0;j<co;j++)
	           	        	show += labirinto[i][j].toString();
	           	    System.out.println(show);
            		}

					System.out.println(caminho.toString());
				}




				Fila<Coordenadas> fila = new Fila<Coordenadas>(3);
				//prog permanece false até achar um espaço vazio,assim virando true

				if (atual.getY()-1 != -1)
	        		if(labirinto[atual.getX()][atual.getY()-1]== ' '){
	        			//achar em cima

	        			fila.enfileire(new Coordenadas(atual.getX(),atual.getY()-1));
	        			prog = true;
	        		}

	        	if (atual.getX()-1 != -1)
	        		if(labirinto[atual.getX()-1][atual.getY()]== ' '){
	        			//achar na esquerda

	        			fila.enfileire(new Coordenadas(atual.getX()-1,atual.getY()));
	        			prog = true;
	        		}
	        	if (atual.getX()+1 != li)
	        		if(labirinto[atual.getX()+1][atual.getY()]== ' '){
	        			//achar na direita

						fila.enfileire(new Coordenadas(atual.getX()+1,atual.getY()));
						prog = true;
	        		}
	        	if (atual.getY()+1 != co)
	        		if(labirinto[atual.getX()][atual.getY()+1]== ' '){
	        			//achar em baixo

	        			fila.enfileire(new Coordenadas(atual.getX(),atual.getY()+1));
	        			prog = true;
	        		}

    			if( !prog && possibilidades.vazio()){
    				//caso alguem adjacente seja a entrada,
    				//não há solução

    				throw new Exception("Nao ha como completar o labirinto!");

    			}
        		//prosseguir caso encontre algo



        		if(prog){
        			//rodar modo de progressão

        			//não precisar verificar se é null,
	        		//pois se chegou aqui É porque tem algo

	        		if (!(fila.vazio())){
	    				atual = (Coordenadas)fila.getElemento();
	    				fila.desenfilere();
					}

	        		labirinto[atual.getX()][atual.getY()]='*';

	        		caminho.empilhe(atual);

	        		possibilidades.empilhe(fila);

	        		prog = false;
        		}else{
					if (!(possibilidades.vazio())) {
        				fila = (Fila<Coordenadas>)possibilidades.getElemento();
        				possibilidades.desempilhe();

        				if (!(fila.vazio())) {
							atual = (Coordenadas)fila.getElemento();
	    					fila.desenfilere();
	    					prog = true;
						}else {
							labirinto[atual.getX()][atual.getY()] = ' ';
						}
        			}
        		}
			}
		}catch(Exception err){
			System.err.println(err);

		}
	}
}