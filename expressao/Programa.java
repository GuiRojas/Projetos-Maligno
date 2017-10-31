import fila.*;
import pilha.*;
import java.*;

public class Programa{	
	public static void main(String[] args){
		try{
			BufferedReader teclado = new BufferedReader( new InputStreamReader(System.in));

			System.out.print("Digite a expressão a ser resolvida.");

     		String exp=teclado.readLine();

			StringTokenixer quebrado = new StringTokenizer (exp , "+-*/^()" , true);

			Fila<String> fila = new Fila<String>(exp.lenght);
			Pilha<String> pilha = new Pilha<String>(exp.lenght);

			String ops = "+-*/^()";


			///////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////
			while(quebrado.hasMoreTokens()){
				//primeira etapa
				String str = quebrado.nextToken();

				try{
					double num = Double.parseDouble(str);
					//vê se o token é um número
					//Se não for um número, e sim um operador,
					//da erro e vai pro catch
					fila.enfileire(str);
				}catch{
					//Caso o token não seja um número					
					if(!(ops.contains(str))){
						//caso não seja um operador válido
						throw new Exception("Operador inválido: "+str);
					}	

					boolean bol = true;
					//bol é um boolean de controle de repetição.
					//sei val

					while(bol){

						//verifica cada possibilidade e trata baseado na matrix fornecida
						switch(str){
							case "(": //f f f f f f f
								pilha.empilhe(str);
								bol = false;
								break;
							case "^": //f f f f f f f
								pilha.empilhe(str);
								bol = false;
								break;
							case "*": //f t t t f f f
								if( pilha.getElemento()=="^"|| //t
									pilha.getElemento()=="*"||
									pilha.getElemento()=="/"){

									fila.enfileire(pilha.getElemento());
									pilha.desempilhe();

								}else{
									pilha.empilhe(str); 
									bol=false;
								}
								break;
							case "/": //f t t t f f f
								if( pilha.getElemento()=="^"|| //t
									pilha.getElemento()=="*"||
									pilha.getElemento()=="/"){

									fila.enfileire(pilha.getElemento());
									pilha.desempilhe();

								}else{
									pilha.empilhe(str); 
									bol=false;
								}
								break;
							case "+": //f t t t t t f
								if( pilha.getElemento()=="("|| //f
									pilha.getElemento()==")"||){

									pilha.empilhe(str); 
									bol=false;
								}else{
									fila.enfileire(pilha.getElemento());
									pilha.desempilhe();
								}
								break;
							case "-": //f t t t t t f
								if( pilha.getElemento()=="("|| //f
									pilha.getElemento()==")"||){

									pilha.empilhe(str); 
									bol=false;
								}else{
									fila.enfileire(pilha.getElemento());
									pilha.desempilhe();
								}
								break;
							case ")": //t f f f f f f
								if(pilha.getElemento()=="("){ //desempilha até achar um (
									pilha.desempilhe();
									bol=false;
								}else{
									if(pilha.vazio()){
										throw new Exception("Correspondência de parenteses incorrta!");
									}else{
										fila.enfileire(pilha.getElemento());
										pilha.desempilhe();	
									}									
								}
								break;

						}//switch

						//teste////////////////////////////////////////////
						System.out.println("Teste begin");
						System.out.println(quebrado.toString());
						System.out.println(str);
						System.out.println("fila:"+fila.toString());
						System.out.println("pilha:"+pilha.toString());
						if(pilha.vazio()){
							System.out.println("Pilha está vazia");				
						}
						if(fila.vazio()){
							System.out.println("fila está vazia");				
						}
						System.out.println("Teste end");
						///////////////////////////////////////////////////

					}//while dos operadores		

				}//catch

			}//while da primeira etapa

			///////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////

			//teste////////////////////////////////////////////
			System.out.println("Teste begin");
			System.out.println(quebrado.toString());
			System.out.println("fila:"+fila.toString());
			System.out.println("pilha:"+pilha.toString());
			if(pilha.vazio()){
				System.out.println("Pilha está vazia");				
			}
			if(fila.vazio()){
				System.out.println("fila está vazia");				
			}
			System.out.println("Teste end");
			///////////////////////////////////////////////////

			///////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////
			
			double op1 = 0;
			double op2 = 0;
			String opr = "";

			while(!(fila.vazio())){

				//lê o valor (numerico ou não) e associa no opr
				opr = fila.getElemento();
				fila.desenfilheire();

				try{
					//novamente, verifica se o valor lido é número
					op1 = Double.parseDouble(opr);
					pilha.empilhe(opr);
				}catch{
					//caso seja um operador
					op1 = Double.parseDouble(pilha.getElemento());
					pilha.desempilhe();
					op2 = Double.parseDouble(pilha.getElemento());
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

				}//catch

				//teste////////////////////////////////////////////
				System.out.println("Teste begin");
				System.out.println(quebrado.toString());
				System.out.println("op1"+String.valueOf(op1));
				System.out.println("op2"+String.valueOf(op1));
				System.out.println("opr"+opr);
				System.out.println("fila:"+fila.toString());
				System.out.println("pilha:"+pilha.toString());
				if(pilha.vazio()){
					System.out.println("Pilha está vazia");				
				}
				if(fila.vazio()){
					System.out.println("fila está vazia");				
				}
				System.out.println("Teste end");
				///////////////////////////////////////////////////

			}//while da segunda etapa

			//teste////////////////////////////////////////////
			System.out.println("Teste begin");
			System.out.println(quebrado.toString());
			System.out.println("op1 "+String.valueOf(op1));
			System.out.println("op2 "+String.valueOf(op1));
			System.out.println("opr "+opr);
			System.out.println("fila: "+fila.toString());
			System.out.println("pilha: "+pilha.toString());
			if(pilha.vazio()){
				System.out.println("Pilha está vazia");				
			}
			if(fila.vazio()){
				System.out.println("fila está vazia");				
			}
			System.out.println("Teste end");
			///////////////////////////////////////////////////

			/////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////////

			System.out.println("Resultado: "+pilha.getElemento());

		}catch(Exception err){}
	}
}