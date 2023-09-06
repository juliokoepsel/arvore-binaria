package arvores;

import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria {
    private class Nodo {
        private int chave;
        private Nodo dir, esq;

        public Nodo(int item) {
            this.chave = item;
            dir = esq = null;
        }
    }

    Nodo raiz = null;

    public void inserir(int chave) {
        raiz = inserirDado(raiz, chave);
    }

    private Nodo inserirDado(Nodo raiz, int chave) {
        if (raiz == null) {
            raiz = new Nodo(chave);
        } else if (chave < raiz.chave) {
            raiz.esq = inserirDado(raiz.esq, chave);
        } else if (chave > raiz.chave) {
            raiz.dir = inserirDado(raiz.dir, chave);
        }
        return raiz;
    }

    public void mostrarEmOrdem() {
        mostrarOrdenado(raiz);
    }

    private void mostrarOrdenado(Nodo raiz) {
        if (raiz != null) {
            mostrarOrdenado(raiz.esq);
            System.out.println(raiz.chave);
            mostrarOrdenado(raiz.dir);
        }
    }

    public void mostrarEmOrdemDecrescente() {
        mostrarOrdenadoDecrescente(raiz);
    }

    private void mostrarOrdenadoDecrescente(Nodo raiz) {
        if (raiz != null) {
            mostrarOrdenadoDecrescente(raiz.dir);
            System.out.println(raiz.chave);
            mostrarOrdenadoDecrescente(raiz.esq);
        }
    }

    public void mostrarPorNivel() {
        if (raiz == null) {
            System.out.println("Árvore vazia!");
            return;
        }
        Queue<Nodo> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            int tamanhoNivel = fila.size();
            for (int i = 0; i < tamanhoNivel; i++) {
                Nodo nodoAtual = fila.poll();
                if (nodoAtual != null) {
                    System.out.print(nodoAtual.chave + " ");
                    fila.add(nodoAtual.esq);
                    fila.add(nodoAtual.dir);
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println(); // Nova linha para separar os níveis
        }
    }

    public void remover(int chave) {
        raiz = removerItem(raiz, chave);
    }

    private Nodo removerItem(Nodo raiz, int chave) {
        if (raiz == null) {
            // Nó não encontrado, não faz nada
            return null;
        } else if (chave < raiz.chave) {
            // Chave a ser removida está à esquerda
            raiz.esq = removerItem(raiz.esq, chave);
        } else if (chave > raiz.chave) {
            // Chave a ser removida está à direita
            raiz.dir = removerItem(raiz.dir, chave);
        } else {
            // Encontramos o nó a ser removido
            if (raiz.esq == null) {
                // Caso em que o nó não possui filho à esquerda
                return raiz.dir;
            } else if (raiz.dir == null) {
                // Caso em que o nó não possui filho à direita
                return raiz.esq;
            } else {
                // Caso em que o nó possui ambos os filhos
                // Nó sucessor será o menor da subárvore da direita
                Nodo sucessor = encontrarSucessor(raiz.dir);
                // Substituímos o valor do nó a ser removido pelo valor do sucessor
                raiz.chave = sucessor.chave;
                raiz.dir = removerItem(raiz.dir, sucessor.chave);
            }
        }
        return raiz;
    }

    private Nodo encontrarSucessor(Nodo nodo) {
        while (nodo.esq != null) {
            nodo = nodo.esq;
        }
        return nodo;
    }

    // Atividade:
    // a) Mostrar o maior número:
    public void encontrarMaior() {
        Nodo nodo = raiz;
        while (nodo.dir != null) {
            nodo = nodo.dir;
        }
        System.out.println("O maior número da árvore é: " + nodo.chave);
    }
    // b) Mostrar o menor número:
    public void encontrarMenor() {
        Nodo nodo = raiz;
        while (nodo.esq != null) {
            nodo = nodo.esq;
        }
        System.out.println("O Menor número da árvore é: " + nodo.chave);
    }
    // c) Mostrar os nós folhas:
    public void encontrarFolhas() {
        System.out.print("Os nós folha da árvore são: ");
        encontrarFolhasItem(raiz);
        System.out.println();
    }
    private void encontrarFolhasItem(Nodo raiz) {
        if (raiz.esq == null && raiz.dir == null) {
            System.out.print(raiz.chave + " ");
        }
        if (raiz.esq != null) {
            encontrarFolhasItem(raiz.esq);
        }
        if (raiz.dir != null) {
            encontrarFolhasItem(raiz.dir);
        }
    }
    // d) Mostrar os nós ancestrais de um nó:
    public void encontrarAncestrais(int chave) {
        encontrarAncestraisItem(raiz, chave);
        System.out.println();
    }
    private boolean encontrarAncestraisItem(Nodo raiz, int chave) {
        if (raiz == null) {
            System.out.print("Erro: o nó informado não foi encontrado!");
            return false;
        } else if (chave < raiz.chave) {
            if (encontrarAncestraisItem(raiz.esq, chave)) {
                System.out.print(raiz.chave + " ");
                return true;
            }
        } else if (chave > raiz.chave) {
            if (encontrarAncestraisItem(raiz.dir, chave)) {
                System.out.print(raiz.chave + " ");
                return true;
            }
        } else if (raiz.chave == chave) {
            System.out.print("Os nós ancestrais do nó informado são: ");
            return true;
        }
        return false;
    }
    // e) Mostrar os nós descendentes de um nó:
    public void encontrarDescendentes(int chave) {
        encontrarDescendentesItem(raiz, chave);
        System.out.println();
    }
    private void encontrarDescendentesItem(Nodo raiz, int chave) {
        if (raiz == null) {
            System.out.print("Erro: o nó informado não foi encontrado!");
        } else if (chave < raiz.chave) {
            encontrarDescendentesItem(raiz.esq, chave);
        } else if (chave > raiz.chave) {
            encontrarDescendentesItem(raiz.dir, chave);
        } else if (raiz.chave == chave) {
            if (raiz.esq == null && raiz.dir == null) {
                System.out.print("O nó informado é um nó folha");
            } else {
                System.out.print("Os nós descendentes do nó informado são: ");
                if (raiz.dir != null) {
                    mostrarDescendentes(raiz.dir);
                }
                if (raiz.esq != null) {
                    mostrarDescendentes(raiz.esq);
                }
            }
        }
    }
    private void mostrarDescendentes(Nodo raiz) {
        if (raiz != null) {
            mostrarDescendentes(raiz.dir);
            System.out.print(raiz.chave + " ");
            mostrarDescendentes(raiz.esq);
        }
    }
    // f) Mostrar a subárvore da direita de um nó:
    public void mostrarSubarvoreDireita(int chave) {
        encontrarSubarvoreDireitaItem(raiz, chave);
        System.out.println();
    }
    private void encontrarSubarvoreDireitaItem(Nodo raiz, int chave) {
        if (raiz == null) {
            System.out.print("Erro: o nó informado não foi encontrado!");
        } else if (chave < raiz.chave) {
            encontrarSubarvoreDireitaItem(raiz.esq, chave);
        } else if (chave > raiz.chave) {
            encontrarSubarvoreDireitaItem(raiz.dir, chave);
        } else if (raiz.chave == chave) {
            if (raiz.dir == null) {
                System.out.print("O nó informado não possui subárvore à direita");
            } else {
                System.out.print("Os nós da subárvore da direita do nó informado são: ");
                mostrarDireita(raiz.dir);
            }
        }
    }
    private void mostrarDireita(Nodo raiz) {
        if (raiz != null) {
            mostrarDireita(raiz.dir);
            System.out.print(raiz.chave + " ");
            mostrarDireita(raiz.esq);
        }
    }
    // g) Mostrar a subárvore da esquerda de um nó:
    public void mostrarSubarvoreEsquerda(int chave) {
        encontrarSubarvoreEsquerdaItem(raiz, chave);
        System.out.println();
    }
    private void encontrarSubarvoreEsquerdaItem(Nodo raiz, int chave) {
        if (raiz == null) {
            System.out.print("Erro: o nó informado não foi encontrado!");
        } else if (chave < raiz.chave) {
            encontrarSubarvoreEsquerdaItem(raiz.esq, chave);
        } else if (chave > raiz.chave) {
            encontrarSubarvoreEsquerdaItem(raiz.dir, chave);
        } else if (raiz.chave == chave) {
            if (raiz.esq == null) {
                System.out.print("O nó informado não possui subárvore à esquerda");
            } else {
                System.out.print("Os nós da subárvore da esquerda do nó informado são: ");
                mostrarEsquerda(raiz.esq);
            }
        }
    }
    private void mostrarEsquerda(Nodo raiz) {
        if (raiz != null) {
            mostrarEsquerda(raiz.dir);
            System.out.print(raiz.chave + " ");
            mostrarEsquerda(raiz.esq);
        }
    }
    // h) Transformar a árvore numa lista encadeada:
    public void transformarEmLista() {
        transformarEmListaItem(raiz);
        System.out.print("A árvore binária transformada em lista encadeada: ");
        mostrarLista(raiz);
        System.out.println();
    }
    private void transformarEmListaItem(Nodo raiz)
    {
        if (raiz == null || (raiz.esq == null && raiz.dir == null))
            return;
        if (raiz.esq != null) {
            transformarEmListaItem(raiz.esq);
            Nodo temp = raiz.dir;
            raiz.dir = raiz.esq;
            raiz.esq = null;
            Nodo atual = raiz.dir;
            while (atual.dir != null)
                atual = atual.dir;
            atual.dir = temp;
        }
        transformarEmListaItem(raiz.dir);
    }
    private void mostrarLista(Nodo raiz) {
        if (raiz != null) {
            System.out.print(raiz.chave + " ");
            mostrarLista(raiz.dir);
        }
    }
    // i) Mostrar somente os números pares:
    public void mostrarPares() {
        System.out.print("Os nós pares da árvore são: ");
        mostrarParesItem(raiz);
        System.out.println();
    }
    private void mostrarParesItem(Nodo raiz) {
        if (raiz != null) {
            mostrarParesItem(raiz.esq);
            if ((raiz.chave % 2) == 0)
                System.out.print(raiz.chave + " ");
            mostrarParesItem(raiz.dir);
        }
    }
    // j) Mostrar o nível de um nodo:
    public void mostrarNivel(int chave) {
        if (encontrarNivelItem(raiz, chave)) {
            System.out.println("O nível do nó informado é: " + mostrarNivelItem(raiz, chave));
        }
    }
    private boolean encontrarNivelItem(Nodo raiz, int chave) {
        if (raiz == null) {
            System.out.print("Erro: o nó informado não foi encontrado!");
            return false;
        } else if (chave < raiz.chave) {
            return encontrarNivelItem(raiz.esq, chave);
        } else if (chave > raiz.chave) {
            return encontrarNivelItem(raiz.dir, chave);
        } else {
            return true;
        }
    }
    private int mostrarNivelItem(Nodo raiz, int chave) {
        if (chave < raiz.chave) {
            return mostrarNivelItem(raiz.esq, chave) + 1;
        } else if (chave > raiz.chave) {
            return mostrarNivelItem(raiz.dir, chave) + 1;
        } else {
            return 0;
        }
    }
    // k) Mostrar a altura da árvore:
    public void mostrarAltura() {
        System.out.println("A altura da árvore é: " + mostrarAlturaItem(raiz));
    }
    private int mostrarAlturaItem(Nodo raiz) {
        if (raiz == null) {
            return 0;
        } else {
            int esq = mostrarAlturaItem(raiz.esq);
            int dir = mostrarAlturaItem(raiz.dir);
            if (esq > dir) {
                return esq + 1;
            } else {
                return dir + 1;
            }
        }
    }
    // l) Mostrar o tamanho da árvore:
    public void mostrarTamanho() {
        System.out.println("O tamanho da árvore é: " + mostrarTamanhoItem(raiz));
    }
    private int mostrarTamanhoItem(Nodo raiz) {
        if (raiz.esq == null && raiz.dir == null) {
            return 1;
        } else {
            int aux = 1;
            if (raiz.esq != null) {
                aux = aux + mostrarTamanhoItem(raiz.esq);
            }
            if (raiz.dir != null) {
                aux = aux + mostrarTamanhoItem(raiz.dir);
            }
            return aux;
        }
    }
    // m) Inserir um novo item na árvore, mas de forma não recursiva:
    public void inserirSemRecursao(int chave) {
        Nodo aux = raiz;
        boolean proximo = true;
        while (proximo) {
            if (chave < aux.chave) {
                if (aux.esq == null) {
                    proximo = false;
                    aux.esq = new Nodo(chave);
                    System.out.println("Nó inserido!");
                } else {
                    aux = aux.esq;
                }
            } else if (chave > aux.chave) {
                if (aux.dir == null) {
                    proximo = false;
                    aux.dir = new Nodo(chave);
                    System.out.println("Nó inserido!");
                } else {
                    aux = aux.dir;
                }
            } else {
                proximo = false;
                System.out.println("Nó já existe na árvore!");
            }
        }
    }
}