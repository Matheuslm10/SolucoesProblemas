import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//@Author
//Matheus Lima Machado

public class RunLength {

	public static void main(String[] args)  throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String seq = "";
		Scanner leitor = new Scanner(System.in);

		int atual, anterior, contador;
		ArrayList<Integer> qtd = new ArrayList<Integer>();
		ArrayList<Character> assoc = new ArrayList<Character>();
		ArrayList<String> preresult = new ArrayList<String>();
		
		
		while((seq = br.readLine()) != null) {
			
			if(seq.equals(" ")) {
				System.out.println("1 1");
			}else if(seq.length()==0) {
				System.out.println();
			}else if(seq.length()<2) {
				if(seq.equals("1")){
					System.out.println("1"+seq+"11");
				}else{
					System.out.println("1"+seq+"1");
				}
			}else {
				contador = 1;
				assoc.add(seq.charAt(0));
				
				for(int i=1; i<seq.length(); i++) {
					
					atual = seq.charAt(i) - 'a';
					anterior = seq.charAt(i-1) - 'a';
					
					if((atual-anterior)==0) {
						contador++;
					}else {
						qtd.add(contador);
						contador = 1;
						assoc.add(seq.charAt(i));
					}
				}
				qtd.add(contador);		
				//------------------------------------
				
				for(int i=0; i<qtd.size(); i++) {
					if(qtd.get(i)==1) {
						preresult.add("1");
						String unicos = "";
						for(int j=i; j<qtd.size(); j++, i++) {
							if(qtd.get(i)==1) {
								int x = assoc.get(i) - 'a';
								if(x==-48) {//se for 1, duplica ele
									unicos += assoc.get(i).toString();
									unicos += assoc.get(i).toString();
									
								}else {
									unicos += assoc.get(i).toString();
								}
								
							}else {
								break;
							}
						}
						preresult.add(unicos);
						preresult.add("1");
						if(i!=qtd.size()) {
							if(qtd.get(i)>9) {
								i--;
							}else {
								preresult.add(Integer.toString(qtd.get(i)));
								preresult.add((assoc.get(i).toString()));
							}
							
							
						}
						
					}else if(qtd.get(i)>9){
						int repeticoes = qtd.get(i)/9;
						int resto = qtd.get(i)%9;
						while(repeticoes>0) {
							preresult.add("9");
							preresult.add((assoc.get(i).toString()));
							repeticoes--;
						}

						if(resto == 1){
							preresult.add(Integer.toString(resto));
							if(assoc.get(i).toString().equals("1")){
								preresult.add((assoc.get(i).toString())+"1");
								preresult.add("1");
							}else{
								preresult.add((assoc.get(i).toString())+"1");
							}
							
						}else if(resto == 0) {
							//faz nada
						}else{						
							preresult.add(Integer.toString(resto));
							preresult.add(assoc.get(i).toString());
						}
									
						
					}else {
						preresult.add(Integer.toString(qtd.get(i)));
						preresult.add((assoc.get(i).toString()));
					}
					
				}
				for(String s: preresult) {
					System.out.print(s);
				}
				System.out.println();
				
				qtd.clear();
				assoc.clear();
				preresult.clear();
			}
			
			
		}
			
	}

}
