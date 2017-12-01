package busca;

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class Ranking {
    
    private ArquivoInvertido a;
    Hashtable indiceInvertido;

    public Ranking(ArquivoInvertido a) {
        this.a = a;
        indiceInvertido = a.getIndiceInvertido();
    }

    public List search(String query) {
        /** Dado um query retorna as paginas que devem aparecer ordenadas pelos seus rankings */
        List<String> r = new ArrayList<String>();
        String[] words = query.split(" ");
        for (String word : words) {
            if (indiceInvertido.containsKey(word)) {
                Node no = (Node) indiceInvertido.get(word);
                do {
                    String num = no.getNumDoc();
                    int freq = no.getFreq();
                    //em construcao
                    r.add(num + " " + freq);
                    no = no.getNext();
                } while (no != null);
            }
        }
        return r;
    }

    public static void main(String[] args) {
        ArquivoInvertido a = new ArquivoInvertido(false, "../Paginas/");
        Ranking r = new Ranking(a);
        String query = "Notebooks usados";
        print(r.search(query));

    }


    public static void print(Object[] a) {
        System.out.println(Arrays.toString(a));
    }

    public static void print(Object a) {
        System.out.println(a);
    }

    public static void print(List a) {
        System.out.println(Arrays.toString(a.toArray(new String[a.size()])));
    }

}