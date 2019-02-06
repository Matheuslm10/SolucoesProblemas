import java.io.IOException;
import java.util.Scanner;

//@Author
//Matheus Lima Machado

public class ArrayHash {
 
    public static void main(String[] args) throws IOException {
 
        Scanner leitor = new Scanner(System.in);	
		String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		int qtdTestes = leitor.nextInt();
		int vetorValoresGerados[] = new int[qtdTestes];

		
		for(int i=0; i < qtdTestes; i++) {			
			int qtdLinhas = leitor.nextInt();
			int valorHash = 0;
			
			//percorre as linhas dos casos de teste
			for(int j=0; j<qtdLinhas; j++) {
				String stringDaVez = leitor.next();
				// o j determina em qual elemento esta.
				int tam = stringDaVez.length();
				
				//percorre a stringDaVez
				for(int t=0; t<tam; t++) {
					char letra = stringDaVez.charAt(t);
					int posicao = 0;
					
					//procura e diz a posicao da letra no alfabeto
					for (int p = 0; p < alfabeto.length(); p++) {
						if (alfabeto.charAt(p) == letra) {
							
							posicao = p;//pega a posicao em que achou a letra
							
							//atualizacao do valor Hash:
							valorHash += posicao + j + t;
							break;
						}
					}
				}
			}
			
			System.out.println(valorHash);
		}
 
    }
 
}