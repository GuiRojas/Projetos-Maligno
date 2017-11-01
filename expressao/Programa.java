import fila.*;
import pilha.*;
import matriz.*;
import java.io.*;
import java.util.*;

public class Programa{
	//verifica se a string é válido como número
	public static boolean isNumber(String str) {
	    try {
	        double v = Double.parseDouble(str);
	        return true;
	    } catch (NumberFormatException nfe) {
	    }
	    return false;
	}

	public static void main(String[] args){
		try{

			BufferedReader teclado = new BufferedReader( new InputStreamReader(System.in));

			System.out.print("Digite a expressão a ser resolvida.");

     		String exp =teclado.readLine();

			//teste
     		System.out.println(exp);

			StringTokenizer quebrado = new StringTokenizer (exp , "+-*/^()" , true);

			//teste
     		System.out.println(quebrado.toString());

			Fila<String> fila = new Fila<String>(exp.length());
			Pilha<String> pilha = new Pilha<String>(exp.length());

			Matriz mat = new Matriz();

			String ops = "+-*/^()";


			///////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////
			//string que auxilia na verificação da matriz
			String aux = "";
			while(quebrado.hasMoreTokens()){
				//primeira etapa
				String str = quebrado.nextToken();

				//teste
				System.out.println(str);

				if(isNumber(str)){
					double num = Double.parseDouble(str);
					//vê se o token é um número

					fila.enfileire(str);
				}else{
					//Caso o token não seja um número
					if(!(ops.contains(str))){
						//caso não seja um operador válido
						throw new Exception("Operador inválido: "+str);
					}

					boolean bol = true;
					//bol é um boolean de controle de repetição.

					while(bol){

						//teste
						System.out.println("Entrou no while dentro do else");


						if(str!=")"){

							///////////////////////////
							if(!(pilha.vazio())){
								aux = pilha.getElemento();
							}else{
								aux = " ";
							}

							if(mat.verificar(str,aux)){
								if(pilha.vazio()){
									fila.enfileire(str);
								}else{
									fila.enfileire(pilha.getElemento());
									pilha.desempilhe();
								}
							}else{
								fila.enfileire(str);
							}
							//fim da matriz////////////

						}else{
							while(str==")"){
								if(pilha.getElemento()=="("){
									pilha.desempilhe();
								}else{
									fila.enfileire(pilha.getElemento());
									pilha.desempilhe();
								}
							}
						} //else do  )

					}//while dos operadores

				}//else

				//teste
				System.out.println("Saiu do catch");

				//teste////////////////////////////////////////////
				System.out.println("Teste begin");
				System.out.println(quebrado.toString());
				System.out.println("str: "+str);

				if(pilha.vazio()){
					System.out.println("Pilha está vazia");
				}else{
					System.out.println("pilha: "+pilha.toString());
				}
				if(fila.vazio()){
					System.out.println("fila está vazia");
				}else{
					System.out.println("fila: "+fila.toString());
				}
				System.out.println("Teste end");
				///////////////////////////////////////////////////

			}//while da primeira etapa

			//passar TUDO da pilha para a fila.
			while(!(pilha.vazio())){
				fila.enfileire(pilha.getElemento());
				pilha.desempilhe();
			}


			///////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////

			//teste////////////////////////////////////////////
			System.out.println("Teste begin");
			System.out.println(quebrado.toString());

			if(pilha.vazio()){
				System.out.println("Pilha está vazia");
			}else{
				System.out.println("pilha:"+pilha.toString());
			}
			if(fila.vazio()){
				System.out.println("fila está vazia");
			}else{
				System.out.println("fila:"+fila.toString());
			}
			System.out.println("Teste end");
			///////////////////////////////////////////////////

			///////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////

			System.out.println("Segunda etapa begin");


			double op1 = 0;
			double op2 = 0;
			String opr = "";

			while(!(fila.vazio())){

				//lê o valor (numerico ou não) e associa no opr
				opr = fila.getElemento();
				fila.desenfilere();

				if(isNumber(opr)){
					//novamente, verifica se o valor lido é número
					op1 = Double.parseDouble(opr);
					pilha.empilhe(opr);
					System.out.println("Verificou numero!");
				}else{
					//caso seja um operador
					op2 = Double.parseDouble(pilha.getElemento());
					pilha.desempilhe();
					op1 = Double.parseDouble(pilha.getElemento());
					pilha.desempilhe();

					switch(opr){ // +-*/^
						case "+":
							opr = String.valueOf(op1 + op2);
							break;
						case "-":
							opr = String.valueOf(op1 - op2);
							break;
						case "*":
							opr = String.valueOf(op1 * op2);
							break;
						case "/":
							opr = String.valueOf(op1 / op2);
							break;
						case "^":
							opr = String.valueOf(Math.pow(op1,op2));
							break;
					}//switch da operação

					pilha.empilhe(opr);

				}//else

				///teste////////////////////////////////////////////
				System.out.println("Teste begin");
				System.out.println(quebrado.toString());

				if(pilha.vazio()){
					System.out.println("Pilha está vazia");
				}else{
					System.out.println("pilha:"+pilha.toString());
				}
				if(fila.vazio()){
					System.out.println("fila está vazia");
				}else{
					System.out.println("fila:"+fila.toString());
				}
				System.out.println("Teste end");
				///////////////////////////////////////////////////

			}//while da segunda etapa

			//teste////////////////////////////////////////////
			System.out.println("Teste begin");
			System.out.println(quebrado.toString());

			if(pilha.vazio()){
				System.out.println("Pilha está vazia");
			}else{
				System.out.println("pilha:"+pilha.toString());
			}
			if(fila.vazio()){
				System.out.println("fila está vazia");
			}else{
				System.out.println("fila:"+fila.toString());
			}
			System.out.println("Teste end");
			///////////////////////////////////////////////////

			/////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////////

			System.out.println("Resultado: "+pilha.getElemento());

		}catch(Exception err){
			System.err.println(err);
		}
	}
}