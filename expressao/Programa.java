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

			System.out.print("Digite a expressao a ser resolvida.");

     		String exp =teclado.readLine();

			StringTokenizer quebrado = new StringTokenizer (exp , "+-*/^()" , true);

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

				if(isNumber(str)){
					double num = Double.parseDouble(str);
					//vê se o token é um número

					fila.enfileire(str);
				}else{
					//Caso o token não seja um número
					if(!(ops.contains(str))){
						//caso não seja um operador válido
						throw new Exception("Operador invalido");
					}

					boolean bol = true;
					//bol é um boolean de controle de repetição.

					while(bol){

						if(str.contains(")")){

							while(str.contains(")")){
								if (!(pilha.vazio())) {
									if(pilha.getElemento().contains("(")){
										pilha.desempilhe();
										str=" ";
										bol = false;
									}else{
										fila.enfileire(pilha.getElemento());
										pilha.desempilhe();
									}
								}else
									throw new Exception("E necessario abrir e fechar todos os parenteses!");
							}
						}else{
							///////////////////////////////////////////
							if(!(pilha.vazio())){
								aux = pilha.getElemento();
							}else{
								aux = " ";
							}

							if(mat.verificar(str,aux)){
								if(pilha.vazio()){
									fila.enfileire(str);
									bol = false;
								}else{
									fila.enfileire(pilha.getElemento());
									pilha.desempilhe();
								}
							}else{//false empilha
								pilha.empilhe(str);
								bol = false;
							}
							//fim da matriz////////////

						} //else do  )

					}//while dos operadores

				}//else

			}//while da primeira etapa

			//passar TUDO da pilha para a fila.
			while(!(pilha.vazio())){
				if (!(pilha.getElemento().contains("("))) {
					fila.enfileire(pilha.getElemento());
					pilha.desempilhe();
				}else
					throw new Exception("E necessario abrir e fechar todos os parenteses!");
			}

			///////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////

			double op1 = 0;
			double op2 = 0;
			String opr = "";

			while(!(fila.vazio())){

				//lê o valor (numerico ou não) e associa no opr
				opr = fila.getElemento();
				fila.desenfileire();

				if(isNumber(opr)){
					//novamente, verifica se o valor lido é número
					op1 = Double.parseDouble(opr);
					pilha.empilhe(opr);
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

			}//while da segunda etapa

			/////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////////

			System.out.println(" ");
			System.out.println("Resultado: "+pilha.getElemento());

		}catch(Exception err){
			System.err.println(err);
		}
	}
}