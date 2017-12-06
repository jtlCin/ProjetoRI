package busca;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class ArquivoInvertido {
	private static Hashtable arquivo = new Hashtable();

	public ArquivoInvertido(boolean ehArquivo, String arquivoOuDiretorio) {
		// tenta ler o arquivo de lista invertida se o boolean for true, se nao le os arquivo do diretorio passado 
		try {
			if (!ehArquivo) {
				throw new FileNotFoundException();
			} //se passar eh arquivo
			File arquivoInvertido = new File(arquivoOuDiretorio);
			Scanner sc = new Scanner(arquivoInvertido);
			// ler enquanto tiver linha (cada linha representa uma lista de
			// documentos/frequancia separados por espaco que contem o termo
			// que
			// aparece como 1 elemento da linha)
			while (sc.hasNext()) {
				// vai salvar o ultimo node inserido para facilitar a
				// insercao
				// de novos nodes
				Node nodeTemp;
				// ler a linha
				String temp = sc.nextLine();
				// splita nos espacos
				String[] arrayTemp = temp.split(" ");
				// cria o head da lista, assume que se o termo esta no
				// documento
				// ele aparece em pelo menos uma pagina com uma frequancia,
				// entao podemos pegar 3 elementos
				// no arrayTemp: arrayTemp[0] = String do termo,
				// arrayTemp[1] =
				// primeiro documento em que o termo aparece, arrayTemp[2] =
				// frequencia do termo nesse documento
				nodeTemp = new Node(arrayTemp[0], (arrayTemp[1]), Integer.parseInt(arrayTemp[2]));
				// insere o head na hastable
				arquivo.put(arrayTemp[0], nodeTemp);
				// para cada um dos outros pares documento/frequencia,
				// insere
				// eles no next do ultimo node inserido e seta o novo
				// nodeTemp
				for (int i = 3; i < arrayTemp.length; i += 2) {
					nodeTemp.setNext(new Node((arrayTemp[i]), Integer.parseInt(arrayTemp[i + 1])));
					nodeTemp = nodeTemp.getNext();
				}
			}
			// caso o arquivo nao exista busca criar o arquivo
		} catch (FileNotFoundException e) {
			File[] listaArquivos;
			//chegando aqui eh diretorio
			listaArquivos = new File(arquivoOuDiretorio).listFiles();
			try {
				bancoPalavras(listaArquivos );
				salvar();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public ArquivoInvertido(boolean select) throws IOException {
		if (!select) {
			// tenta ler o arquivo de lista invertida
			try {
				File arquivoInvertido = new File("ai");
				Scanner sc = new Scanner(arquivoInvertido);
				// ler enquanto tiver linha (cada linha representa uma lista de
				// documentos/frequancia separados por espaco que contem o termo
				// que
				// aparece como 1 elemento da linha)
				while (sc.hasNext()) {
					// vai salvar o ultimo node inserido para facilitar a
					// insercao
					// de novos nodes
					Node nodeTemp;
					// ler a linha
					String temp = sc.nextLine();
					// splita nos espacos
					String[] arrayTemp = temp.split(" ");
					// cria o head da lista, assume que se o termo esta no
					// documento
					// ele aparece em pelo menos uma pagina com uma frequancia,
					// entao podemos pegar 3 elementos
					// no arrayTemp: arrayTemp[0] = String do termo,
					// arrayTemp[1] =
					// primeiro documento em que o termo aparece, arrayTemp[2] =
					// frequencia do termo nesse documento
					nodeTemp = new Node(arrayTemp[0], (arrayTemp[1]), Integer.parseInt(arrayTemp[2]));
					// insere o head na hastable
					arquivo.put(arrayTemp[0], nodeTemp);
					// para cada um dos outros pares documento/frequencia,
					// insere
					// eles no next do ultimo node inserido e seta o novo
					// nodeTemp
					for (int i = 3; i < arrayTemp.length; i += 2) {
						nodeTemp.setNext(new Node((arrayTemp[i]), Integer.parseInt(arrayTemp[i + 1])));
						nodeTemp = nodeTemp.getNext();
					}
				}
				// caso o arquivo nao exista busca criar o arquivo
			} catch (FileNotFoundException e) {
				File listaArquivos[];
				// substituir a pasta paginas
				listaArquivos = new File("Teste").listFiles();
				try {
					bancoPalavras(listaArquivos);
					salvar();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else {
			try {
				//abre o arquivo com os bytes de dados
				File arquivoInvertido = new File("aic");
				//abre o arquivo com o head da lista
				File arquivoInvertidoHead = new File("haic");
				// FileInputStream sc = new FileInputStream("aic");
				Scanner sc = new Scanner(new FileInputStream("aic"));
				Scanner hsc = new Scanner(arquivoInvertidoHead);
				//enquanto existirem keys no head
				while(hsc.hasNext()){
					String temp[] = hsc.nextLine().split(" ");
					//le a key do head
					String key = temp[0];
					//ler a quantidade de interacoes que vao ser lidas de dados dessa key
					int qt = Integer.parseInt(temp[1]);
					//le as infromacoes do head pra essa key no 
					String tempNumDoc = sc.next();
					int tempFreq = sc.nextInt();
					Node nodeTemp = new Node(tempNumDoc, tempFreq);
					arquivo.put(key, nodeTemp);
					while (qt > 1) {
						// vai salvar o ultimo node inserido para facilitar a
						// insercao
						// de novos nodes
						// ler a linha
						tempNumDoc = sc.next();
						// splita nos espacos
						tempFreq = sc.nextInt();
						// cria o head da lista, assume que se o termo esta no
						// documento
						// ele aparece em pelo menos uma pagina com uma frequancia,
						// entao podemos pegar 3 elementos
						// no arrayTemp: arrayTemp[0] = String do termo,
						// arrayTemp[1] =
						// primeiro documento em que o termo aparece, arrayTemp[2] =
						// frequencia do termo nesse documento
						nodeTemp.setNext(new Node(tempNumDoc, tempFreq));
						nodeTemp = nodeTemp.getNext();
						qt--;
					}
				}
				// caso o arquivo nao exista busca criar o arquivo
			} catch (IOException e) {
				File[] listaArquivos;
				// substituir a pasta paginas
				listaArquivos = new File("Teste").listFiles();
				try {
					bancoPalavras(listaArquivos);
					FileWriter fw = new FileWriter("aic");
					BufferedWriter bw = new BufferedWriter(fw);
					//File arquivoInvertido = new File("aic");
					FileWriter fhw = new FileWriter("haic");
					BufferedWriter bhw = new BufferedWriter(fhw);
					int s = 0;
					//pega todas as keys do hashtable temporario
					Enumeration names = arquivo.keys();
					while(s<arquivo.size()){
						int cont = 1;
						String key = (String) names.nextElement();
						Node tempNode = (Node) arquivo.get(key);
						bw.write(tempNode.getNumDoc()+"");
						bw.write(tempNode.getFreq()+"");
						bw.flush();
						while(tempNode.hasNext()){
							tempNode=tempNode.getNext();
							bw.write(tempNode.getNumDoc()+"");
							bw.write(tempNode.getFreq()+"");
							bw.flush();
							cont++;
						}
						bhw.write(key + " " + cont + "\n");
						bhw.flush();
						s++;
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	

	public static void bancoPalavras(File[] listaArquivos) throws FileNotFoundException {
		for (File temp : listaArquivos) {
			String i = temp.getName();
			Hashtable tempHash = new Hashtable();
			String st = "";
			String arrayTemp[];
			Scanner tempScan = new Scanner(temp);
			while (tempScan.hasNext()) {
				st += tempScan.nextLine() + " ";
			}
			arrayTemp = st.split(" ");
			for (int ii = 0; ii < arrayTemp.length; ii++) {
				if (tempHash.containsKey(arrayTemp[ii])) {
					int aux = (int) tempHash.get(arrayTemp[ii]);
					tempHash.put(arrayTemp[ii], aux + 1);
				} else
					tempHash.put(arrayTemp[ii], 1);
			}
			int s = 0;
			Enumeration names = tempHash.keys();
			while (s < tempHash.size()) {
				String key = (String) names.nextElement();
				int k = (int) tempHash.get(key);
				if (arquivo.containsKey(key)) {
					Node tempNode = (Node) arquivo.get(key);
					tempNode.setNext(new Node(i, k));
					arquivo.put(key, tempNode);
				} else
					arquivo.put(key, new Node(key, i, k));
				s++;
			}
		}
	}

	public Hashtable getIndiceInvertido() {
		return this.arquivo;
	}
	
	private void salvar() {
		FileWriter fw;
		BufferedWriter bw;
		try {
			fw = new FileWriter("ai");
			bw = new BufferedWriter(fw);
		
			int s = 0;
			Enumeration names = this.arquivo.keys();
			while(s<this.arquivo.size()){
				String key = (String) names.nextElement();
				String temp = "";
				Node tempNode = (Node) arquivo.get(key);
				temp += key + " " + tempNode.getNumDoc() + " " + tempNode.getFreq();
				while(tempNode.hasNext()){
					tempNode = tempNode.getNext();
					temp += " " + tempNode.getNumDoc() + " " + tempNode.getFreq();
				}
				temp += "\n";
				bw.write(temp);
				s++;
			}
		} catch (IOException e) {
			// System.out.println(e);
			e.printStackTrace();
		}
		
	}

}