package arvores;

public class TesteArvore {
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();

        arvore.inserir(30);
        arvore.inserir(25);
        arvore.inserir(20);
        arvore.inserir(22);
        arvore.inserir(40);
        arvore.inserir(27);
        arvore.inserir(45);
        
        //arvore.mostrarEmOrdem();
        /*
        arvore.remover(22);
        arvore.remover(40);

        System.out.println("Após remoção");

        arvore.mostrarEmOrdem();

        System.out.println("Decrescente");

        arvore.mostrarEmOrdemDecrescente();
        */

        //System.out.println("Por nível");
        //arvore.mostrarPorNivel();

        arvore.encontrarMaior();

        arvore.encontrarMenor();

        arvore.encontrarFolhas();

        arvore.encontrarAncestrais(22);

        arvore.encontrarDescendentes(25);

        arvore.mostrarSubarvoreDireita(30);

        arvore.mostrarSubarvoreEsquerda(30);

        arvore.mostrarPares();

        arvore.mostrarNivel(45);

        arvore.mostrarAltura();

        arvore.mostrarTamanho();

        arvore.inserirSemRecursao(50);

        arvore.transformarEmLista();
    }
}
