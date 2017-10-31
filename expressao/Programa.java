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
					double num = parseDouble(str);
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
			

		}catch(Exception err){}
	}
}