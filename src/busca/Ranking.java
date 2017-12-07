package busca;

import java.util.Hashtable;
import java.util.List;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ranking {
    
    private ArquivoInvertido a;
    private Hashtable indiceInvertido;

    public Ranking(ArquivoInvertido a) {
        this.a = a;
        this.indiceInvertido = a.getIndiceInvertido();
    }
    public List search(String query) {
        return search(query, true);
    }

    public List search(String query, boolean tfidf) {
        /** Dado um query retorna as paginas que devem aparecer ordenadas pelos seus rankings */
        List r = new ArrayList<Pair>();
        String[] words = query.split(" ");
        Hashtable scoreByDocs = new Hashtable<String, Hashtable>();
        Hashtable docs = new Hashtable(); //todos numeros de docs envolvidos
        for (String word : words) { //organizando palavras e vetores 
            if (indiceInvertido.containsKey(word)) { //se alguem tem a palavra
                Node no = (Node) indiceInvertido.get(word);
                int N = indiceInvertido.size();
                int Ni = 0;
                do { //passando uma vez apenas para contar qtde de documentos
                    Ni++;
                    no = no.getNext();
                } while (no!= null);

                no = (Node) indiceInvertido.get(word);
                do {
                    String doc = no.getNumDoc();
                    docs.put(doc, ""); //salvando nome de doc
                    double tf = no.getFreq();
                    Hashtable v;
                    if (scoreByDocs.containsKey(doc)) {
                        v = (Hashtable) scoreByDocs.get(doc);
                    } else {
                        v = new Hashtable<String, Double>();
                    }
                    double score = tf;
                    if (tfidf) {
                        score *= Math.log(N/Ni);
                    }
                    v.put(word, score); //o documento doc tem determinado score para essa word 
                    scoreByDocs.put(doc, v); //guarda hashtable de doc
                    no = no.getNext();
                } while (no != null);
            }
        }
        Set<String> keys = docs.keySet();
        for (String doc : keys) { //calculando similaridade
            //dado um 'doc', fica com um hashtable que dado  uma 'word' (Se existir) diz um score que eh Wij
            Hashtable v = (Hashtable) scoreByDocs.get(doc);
            double somaD = 0;
            double somaN1 = 0;
            double somaN2 = 0;
            for (String word : words) {
                double wiq = 1;
                double score = 0;
                if (v.containsKey(word)) {
                    score = (double) v.get(word);
                }
                somaD += score * wiq;
                somaN1 += score * score;
                somaN2 += wiq * wiq;
            }
            double sim = somaD / (Math.sqrt(somaN1) * Math.sqrt(somaN2));
            r.add(new Pair(doc, sim));
        }

        Collections.sort(r, new Comparator<Pair>() {
            public int compare(Pair one, Pair two) {
                if (one.value == two.value) { //deixando por ordem de numeracao dos documentos
                    // String o = ext(one.doc, "(.*?)[.]");
                    // String t = ext(two.doc, "(.*?)[.]");
                    // return Integer.parseInt(o) > Integer.parseInt(t) ? 1 : -1;
                    return 0;
                }
                if (one.value > two.value) return -1;
                return 1;
            }
        });

        return r;
    }

    public static double kendal(List<Pair> l1, List<Pair> l2) {
    	print(l1.size() + " " + l2.size());
        List <Pair2> list = new ArrayList<Pair2>();
        int pares = 0;
        for (int i=0; i<l1.size()-1; i++) {
            for (int j=i+1;j<l1.size(); j++) {
                list.add(new Pair2(l1.get(i), l2.get(j)));
                pares++;
                System.out.println(l1.get(i) + ", " + l2.get(j));
            }
        }
        int concordantes = 0, discordantes = 0;
        for (int i=0; i<l2.size()-1; i++) {
            for (int j=i+1;j<l2.size(); j++) {
                pares++;
                if (Pair2.find(list, new Pair2(l2.get(i), l2.get(j)))) {
                    concordantes++;
                } else {
                    discordantes++;
                }
            }
        }
        return 1.0 - (double)((2.0*discordantes)/pares);

    }

    public String searchToString(String search, boolean tdidf) {
        String r = "";
        List<Pair> l = search(search, tdidf);
        for (Pair i : l) {
            r += i + "\n";
        }
        return r;
    }

    public String kendallToString(String query) {
    	print(kendal(search(query, false), search(query, true)));
        return kendal(search(query, false), search(query, true)) + "";
    }


    public static void main(String[] args) throws IOException {
        ArquivoInvertido a = new ArquivoInvertido();
        Ranking r = new Ranking(a);
        String query = "notebook processador";
        String query2 = query + " usados";
        print(r.searchToString(query2, false));
        print(r.kendal(r.search(query, true), r.search(query, true)) + "");
    }


    public static String ext(String page, String pat) {
        Pattern padrao = Pattern.compile(pat);
        Matcher matcher = padrao.matcher(page);
        if (matcher.find()) {
            String content = matcher.group(1);
            return content;
        } else {
            return "";
        }
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

class Pair {
    public double value;
    public String doc;

    public Pair(String doc, double value) {
        this.doc = doc;
        this.value = value;
    }

    public String toString() {
        return (this.doc + " " + this.value);
    }
}

class Pair2 {
    public Pair p1;
    public Pair p2;

    public Pair2(Pair p1, Pair p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public static boolean find(List<Pair2> l, Pair2 r) {
        /** Dado um Pair2 e uma Lista, diz se ele existe nela */
        for (int i=0; i<l.size(); i++) {
            Pair2 t = l.get(i);
            if ((t.p1.doc.equals(r.p1.doc)) && (t.p2.doc.equals(r.p2.doc))) {
                return true;
            }
        }
        return false;
    }

}