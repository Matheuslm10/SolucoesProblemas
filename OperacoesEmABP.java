import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
//@Author
//Matheus Lima Machado
//The algs4 library was used.


public class OperacoesEmABP {

	static int qtd = 0;
	static String sequencia = "";

	public static class Elemento {

		private int valor;

		public Elemento() {

		}

		public Elemento(int valor) {
			this.valor = valor;
		}

		public int getValor() {
			return valor;
		}

		public void setValor(int valor) {
			this.valor = valor;
		}
	}

	public static class Arvore {

		private Elemento ele;
		private Arvore dir;
		private Arvore esq;

		public Arvore(){

		}
		public Arvore(Elemento elem) {
			this.ele = elem;
			this.dir = null;
			this.esq = null;
		}

		public boolean isEmpty() {
			return (qtd == 0);
		}

		public void imprimirPreOrdem() {
			if (!isEmpty()) {
				sequencia += Integer.toString(this.ele.getValor()) + " ";

				if (this.esq != null) {
					this.esq.imprimirPreOrdem();
				}
				if (this.dir != null) {
					this.dir.imprimirPreOrdem();
				}
			}

		}

		public void imprimirInOrdem() {
			if (!isEmpty()) {

				if (this.esq != null) {
					this.esq.imprimirInOrdem();

				}
				sequencia += Integer.toString(this.ele.getValor()) + " ";
				if (this.dir != null) {
					this.dir.imprimirInOrdem();
				}
			} else {
				System.out.println("entrou aqui");;
			}
		}

		public void imprimirPosOrdem() {
			if (!isEmpty()) {
				if (this.esq != null) {
					this.esq.imprimirPosOrdem();
				}
				if (this.dir != null) {
					this.dir.imprimirPosOrdem();
				}
				sequencia += Integer.toString(this.ele.getValor()) + " ";
			}
		}

		public void inserir(Elemento novo) {
			if (isEmpty()) {
				this.ele = novo;
				qtd++;
			} else {
				Arvore novaArvore = new Arvore(novo);
				if (novo.getValor() < this.ele.getValor()) {//vou inserir na descendencia esquerda
					if (this.esq == null) {//sou um nÃ³ folha?
						this.esq = novaArvore;
						qtd++;
					} else {
						this.esq.inserir(novo);// recursÃ£o! repassei a responsabilidade pra sub-arvore esquerda
					}
				} else if (novo.getValor() > this.ele.getValor()) {// vou inserir na descendencia direita
					if (this.dir == null) {
						this.dir = novaArvore;
						qtd++;
					} else {
						this.dir.inserir(novo);
					}

				}
			}
		}

		public Arvore remover(Elemento elem) {
			//primeiro caso: achei o elemento
			if (this.ele.getValor() == elem.getValor()) {
				//caso mais simples: o elemento estÃ¡ em um nÃ³ folha
				if (this.dir == null && this.esq == null) {
					return null;
				} else {
					if (this.esq != null && this.dir == null) {
						//caso 2: eu tenho filhos a esquerda, porem nao tenho a direita
						return this.esq;
					} else if (this.dir != null && this.esq == null) {
						//caso 3: eu tenho filhos a direita, porem nao tenho a esquerda
						return this.dir;
					} else {
						//caso 4: tenho filhos dos dois lados
						//vamos adotar a estrategia do maior dentre os menores?
						Arvore aux = this.esq;
						while (aux.dir != null) {
							aux = aux.dir;
						}
						//troco os elementos da arvore
						this.ele = aux.getEle();//o no atual recebe o elemento do aux
						// o maior dentre os menores
						aux.setEle(elem);  // insiro no nÃ³ folha(la embaixao) o elemento a ser eliminado. 
						this.esq = esq.remover(elem);
					}
				}
			} else if (elem.getValor() < this.ele.getValor()) {
				//delegar a responsabilidade a subarvore da esquerda
				this.esq = this.esq.remover(elem);
			} else if (elem.getValor() > this.ele.getValor()) {
				//delegar a responsabilidade a subarvore da direita
				this.dir = this.dir.remover(elem);

			}
			return this;

		}

		public boolean busca(int valor) {
			if (isEmpty()) {
				return false;
			}
			if (this.ele.getValor() == valor) {
				return true;
			} else {
				if (valor < this.ele.getValor()) {
					if (this.esq == null) {
						return false;
					} else {
						return this.esq.busca(valor);//repassei a responsabilidade para a subarvore esquerda.
					}
				} else if (valor > this.ele.getValor()) {
					if (this.dir == null) {
						return false;
					} else {
						return this.dir.busca(valor);
					}
				}
			}
			return false;
		}

		public Elemento getEle() {
			return ele;
		}

		public void setEle(Elemento ele) {
			this.ele = ele;
		}

		public Arvore getDir() {
			return dir;
		}

		public void setDir(Arvore dir) {
			this.dir = dir;
		}

		public Arvore getEsq() {
			return esq;
		}

		public void setEsq(Arvore esq) {
			this.esq = esq;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Arvore arvore = new Arvore();
		Elemento elem = new Elemento();
		Scanner scan = new Scanner(System.in);

		String line = null;
		boolean primeiraVez = true;

		String op, entrada, valorEmString, linha, comandoString;
		char space, comando;
		int valor, tamanhoEntrada, valorEmInt;

		while ((linha = br.readLine()) != null) {
			linha = linha.trim();

			entrada = linha;
			tamanhoEntrada = entrada.length();

			space = entrada.charAt(1);

			if (space == ' ') {
				comando = entrada.charAt(0);
				valorEmString = entrada.substring(2, tamanhoEntrada);
				valorEmInt = Integer.parseInt(valorEmString);

				if ((comando == 'I') || (comando == 'R') || (comando == 'P')) {
					switch (comando) {
						case 'I':
							if (primeiraVez) {
								arvore = new Arvore(new Elemento(valorEmInt));
								qtd++;
								primeiraVez = false;
							} else {
								arvore.inserir(new Elemento(valorEmInt));
							}
							break;
						case 'P':
							if (qtd == 0) {
								break;
							} else {
								System.out.println(valorEmInt + (arvore.busca(valorEmInt) ? " existe" : " nao existe"));
								break;
							}
						case 'R':
							if (qtd == 0) {
								break;
								//System.out.println("A árvore está vazia");
							} else {
								elem.setValor(valorEmInt);
								if (arvore.busca(valorEmInt)) {
									arvore = arvore.remover(elem);
									qtd--;
								}
								if (qtd == 0) {
									primeiraVez = true;
								}
								break;
							}
	
						default:
					}
				}

			} else {
				comandoString = entrada.substring(0, entrada.length());
				switch (comandoString) {
					case "INFIXA":
						if (qtd == 0) {
							break;
						} else {
							arvore.imprimirInOrdem();
							System.out.println(sequencia.trim());
							sequencia = "";
							break;
						}
	
					case "POSFIXA":
						if (qtd == 0) {
							break;
						} else {
							arvore.imprimirPosOrdem();
							System.out.println(sequencia.trim());
							sequencia = "";
							break;
						}
					case "PREFIXA":
						if (qtd == 0) {
							break;
						} else {
							arvore.imprimirPreOrdem();
							System.out.println(sequencia.trim());
							sequencia = "";
							break;
						}
					default:
				}
			}
		}
	}
}
