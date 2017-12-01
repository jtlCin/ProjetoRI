package busca;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;


public class Main {
	private static Hashtable arquivo = new Hashtable();

	public static void bancoPalavras(String [] listaArquivos) throws FileNotFoundException{
		for(int i=1; i<=listaArquivos.length; i++){	
			Hashtable tempHash =  new Hashtable();
			String st = "";
			String arrayTemp  [];
			File temp = new File("Teste\\" + i + ".txt");
			Scanner tempScan = new Scanner(temp);
			while(tempScan.hasNext()){
				st+=tempScan.nextLine() + " ";
			}
			arrayTemp = st.split(" ");
			for(int ii=0; ii<arrayTemp.length; ii++){
				if (tempHash.containsKey(arrayTemp[ii])){
					int aux = (int) tempHash.get(arrayTemp[ii]);
					tempHash.put(arrayTemp[ii], aux+1);
				}
				else tempHash.put(arrayTemp[ii], 1);
			}
			int s = 0;
			Enumeration names = tempHash.keys();
			while(s<tempHash.size()) {
				String  key = (String) names.nextElement();
				int k = (int) tempHash.get(key);
				if(arquivo.containsKey(key)){
					Node tempNode = (Node) arquivo.get(key);
					tempNode.setNext(new Node (i, k));
					arquivo.put(key, tempNode);
				}
				else arquivo.put(key, new Node (key, i, k));
				s++;
			 }
		}
	}
	public static void main(String[] args) throws FileNotFoundException {
		//hashtable aonde vao ficar as listas de documentos/quantidade
		//tenta ler o arquivo de lista invertida
		try{
			File arquivoInvertido = new File("ai");
			Scanner sc =  new Scanner(arquivoInvertido);
			//ler enquanto tiver linha (cada linha representa uma lista de documentos/frequancia separados por espaco que contem o termo que aparece como 1 elemento da linha)
			while(sc.hasNext()){
				//vai salvar o ultimo node inserido para facilitar a insercao de novos nodes
 				Node nodeTemp;
 				//ler  a linha
				String temp = sc.nextLine();
				//splita nos espacos
				String [] arrayTemp = temp.split(" ");
				//cria o head da lista, assume que se o termo esta no documento ele aparece em pelo menos uma pagina com uma frequancia, entao podemos pegar 3 elementos
				//no arrayTemp: arrayTemp[0] = String do termo, arrayTemp[1] = primeiro documento em que o termo aparece, arrayTemp[2] = frequencia do termo nesse documento
				nodeTemp = new Node (arrayTemp[0], Integer.parseInt(arrayTemp[1]), Integer.parseInt(arrayTemp[2]));
				//insere o head na hastable
				arquivo.put(arrayTemp[0], nodeTemp);
				//para cada um dos outros pares documento/frequencia, insere eles no next do ultimo node inserido e seta o novo nodeTemp
				for(int i = 3; i<arrayTemp.length; i+=2){
					nodeTemp.setNext(new Node (Integer.parseInt(arrayTemp[i]), Integer.parseInt(arrayTemp[i+1])));
					nodeTemp = nodeTemp.getNext();
				}
			}
		//caso o arquivo nao exista busca criar o arquivo	
		}catch(FileNotFoundException e){
			String listaArquivos [];
			//substituir a pasta paginas
			listaArquivos = new File("Teste").list();
			bancoPalavras(listaArquivos);
			for(int i=0; i<listaArquivos.length; i++);
		}
	}

}
