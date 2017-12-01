package busca;

import java.util.Hashtable;

public class Ranking {
    public static void main(String[] args) {
        ArquivoInvertido a = new ArquivoInvertido(false, "../Paginas/");
        Hashtable indiceInvertido = a.getArquivoInvertido();
        Node test = (Node) indiceInvertido.get("Expressa");
        System.out.println(test.getFreq());
    }
}