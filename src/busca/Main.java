package busca;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;


public class Main {
	private static Hashtable arquivo = new Hashtable();

	public static void bancoPalavras(String [] listaArquivos) throws FileNotFoundException{
		// for(int i=1; i<=listaArquivos.length; i++){	
		// 	Hashtable tempHash =  new Hashtable();
		// 	String st = "";
		// 	String arrayTemp  [];
		// 	File temp = new File("Teste\\" + i + ".txt");
		// 	Scanner tempScan = new Scanner(temp);
		// 	while(tempScan.hasNext()){
		// 		st+=tempScan.nextLine() + " ";
		// 	}
		// 	arrayTemp = st.split(" ");
		// 	for(int ii=0; ii<arrayTemp.length; ii++){
		// 		if (tempHash.containsKey(arrayTemp[ii])){
		// 			int aux = (int) tempHash.get(arrayTemp[ii]);
		// 			tempHash.put(arrayTemp[ii], aux+1);
		// 		}
		// 		else tempHash.put(arrayTemp[ii], 1);
		// 	}
		// 	int s = 0;
		// 	Enumeration names = tempHash.keys();
		// 	while(s<tempHash.size()) {
		// 		String  key = (String) names.nextElement();
		// 		int k = (int) tempHash.get(key);
		// 		if(arquivo.containsKey(key)){
		// 			Node tempNode = (Node) arquivo.get(key);
		// 			tempNode.setNext(new Node (i+"", k));
		// 			arquivo.put(key, tempNode);
		// 		}
		// 		else arquivo.put(key, new Node (key, i+"", k));
		// 		s++;
		// 	 }
		// }
	}
	public static void main(String[] args) throws IOException {
		ArquivoInvertido a =  new ArquivoInvertido(true);
		
		
	}

}
