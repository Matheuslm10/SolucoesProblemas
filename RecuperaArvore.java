import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

//@Author
//Matheus Lima Machado
//The algs4 library was used.

public class RecuperaArvore {

    private Elemento raiz;
    
    private class Elemento{
        private Elemento direita;
        private Elemento esquerda;
        private int chave;
        private String valor;
        private int tamanho;
        
        public Elemento(int chave, String valor, int tamanho) {
            this.chave = chave;
            this.valor = valor;
            this.tamanho = tamanho;
        }
        
    }
    
    //public BST(){
    //}
    
    public boolean estaVazio() {
        return tamanho() == 0;
    }
    
    public int tamanho() {
        return tamanho(raiz);
    }
    
    private int tamanho(Elemento x) {
        if (x == null) {
            return 0;
        }else{
            return x.tamanho;
        }
    }
    
    public boolean contem(int chave) {
        if (chave<0) throw new IllegalArgumentException("argumento para o contem() esta negativo");
        return get(chave) != null;
    }
    
    public String get(int chave) {
        return get(raiz, chave);
    }

    private String get(Elemento x, int chave) {
        if (x == null) {
            return null;
        }           
        if(chave > x.chave) {
            return get(x.direita, chave);
        }else if(chave < x.chave) {
            return get(x.esquerda, chave);
        }else {
            return x.valor;
        }
    }
    
    public void inserir(int chave, String valor) {
        if (chave<0) throw new IllegalArgumentException("chamada de inserir() com chave invalida(negativa)");
        if (valor == null) {
            deletar(chave);
            return;
        }
        raiz = inserir(raiz, chave, valor);
        //assert check();
    }
    
    private Elemento inserir(Elemento x, int chave, String valor) {
        if (x == null) return new Elemento(chave, valor, 1);
        if(chave > x.chave){
            x.direita = inserir(x.direita, chave, valor);
        }else if(chave < x.chave) {
            x.esquerda = inserir(x.esquerda, chave, valor);
        }else{
            x.valor = valor;                
        }
        x.tamanho = 1 + tamanho(x.esquerda) + tamanho(x.direita);
        return x;
    }
    
    public void deletarMin() {
        if (estaVazio()) throw new NoSuchElementException("Symbol table underflow");
        raiz = deletarMin(raiz);
        //assert check();
    }

    private Elemento deletarMin(Elemento x) {
        if (x.esquerda == null) return x.direita;
        x.esquerda = deletarMin(x.esquerda);
        x.tamanho = tamanho(x.esquerda) + tamanho(x.direita) + 1;
        return x;
    }
    
    public void deletar(int chave) {
        if (chave<0) throw new IllegalArgumentException("calls delete() with a null key");
        raiz = deletar(raiz, chave);
        //assert check();
    }

    private Elemento deletar(Elemento x, int chave) {
        if (x == null) return null;
        
        //
        if(chave > x.chave) {
            x.direita = deletar(x.direita, chave);
        }else if(chave < x.chave) {
            x.esquerda = deletar(x.esquerda, chave);
        }else {
            if(x.direita == null) return x.esquerda;
            if(x.esquerda == null) return x.direita;
            Elemento t = x;
            x = min(t.direita);
            x.direita = deletarMin(t.direita);
            x.esquerda = t.esquerda;
        }
        x.tamanho = tamanho(x.esquerda) + tamanho(x.direita) + 1;
        return x;
    }
    
    public int min() {
        if (estaVazio()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(raiz).chave;
    } 

    private Elemento min(Elemento x) { 
        if (x.esquerda == null) {
            return x; 
        }else {
            return min(x.esquerda); 
        }
    } 
    
    public void posorder(){
        posorder(raiz);
    }
    
    private void posorder(Elemento node) {
        if(node != null) {
            if(node.esquerda != null) posorder(node.esquerda);
            if(node.direita != null) posorder(node.direita);
            visit(node);
        }else{
            System.out.println("Arvore vazia");
        }
    }
    
    private void visit(Elemento node){
        posfixa += node.valor;
    }

    static String posfixa = "";
    
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        String pre, inf;
        int tam;
        boolean primeiraVez = true;
        ArrayList<String> saidas = new ArrayList<String>();
        int contadorLinhas = 0;
        
        

        while (leitor.hasNext()) {
            contadorLinhas++;
            //System.out.println(contadorLinhas);
            pre = leitor.next();
            pre = pre.toUpperCase();
            inf = leitor.next();
            inf = inf.toUpperCase();
            
            tam = pre.length();
            
            String vetorPREvalores[] = new String[tam];
            //int vetorPREchaves[] = new int[tam];
            
            String vetorINFvalores[] = new String[tam];
            //int vetorINFchaves[] = new int[tam];
            
            for (int i = 0; i < vetorPREvalores.length; i++) {
                vetorPREvalores[i] = String.valueOf(pre.charAt(i));
            }
            
            for (int i = 0; i < vetorINFvalores.length; i++) {
                vetorINFvalores[i] = String.valueOf(inf.charAt(i));
            }           
            
            //criando sequencia numerica (isto e, a sequencia das chaves dos valores) que traduzira a entrada em prefixo:
            int sequencia[] = new int[tam];
            String alvo;
            for (int i = 0; i < vetorPREvalores.length; i++) {
                alvo = vetorPREvalores[i];
                for(int j = 0; j < vetorINFvalores.length; j++) {
                    if(vetorINFvalores[j].equals(alvo)) {
                        sequencia[i] = j;
                    }
                }
            }

            
            RecuperaArvore arvore = new RecuperaArvore();
            for (int i = 0; i < sequencia.length; i++) {
                arvore.inserir(sequencia[i], vetorPREvalores[i]);
            }
            arvore.posorder();
            
            posfixa = posfixa.trim();
            posfixa = posfixa.toUpperCase();
            System.out.println(posfixa); 
            posfixa="";
                
        }
        /*
        while(!saidas.isEmpty()){
            if(contadorLinhas==1) {
                System.out.print(saidas.remove(0));

            }else {
                System.out.println(saidas.remove(0));
            }
            contadorLinhas--;
        }
        */
            

    }

}
