package busca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArquivoInvertido {
	private static Hashtable arquivo = new Hashtable();

	public ArquivoInvertido()  {
		// tenta ler o arquivo de lista invertida
		try {
			File arquivoInvertido = new File("ai");
			Scanner sc = new Scanner(arquivoInvertido);
			// ler enquanto tiver linha (cada linha representa uma lista de
			// documentos/frequancia separados por espa�o que contem o termo
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
				nodeTemp = new Node(arrayTemp[0], Integer.parseInt(arrayTemp[1]), Integer.parseInt(arrayTemp[2]));
				// insere o head na hastable
				arquivo.put(arrayTemp[0], nodeTemp);
				// para cada um dos outros pares documento/frequencia,
				// insere
				// eles no next do ultimo node inserido e seta o novo
				// nodeTemp
				for (int i = 3; i < arrayTemp.length; i += 2) {
					nodeTemp.setNext(new Node(Integer.parseInt(arrayTemp[i]), Integer.parseInt(arrayTemp[i + 1])));
					nodeTemp = nodeTemp.getNext();
				}
			}
			// caso o arquivo nao exista busca criar o arquivo
		} catch (FileNotFoundException e) {
			String listaArquivos[];
			// substituir a pasta Pagclean
			listaArquivos = new File("Pagclean").list();
			try {
				bancoPalavras(listaArquivos);
				gerarAttrEsp(listaArquivos);
				salvar();
			} catch (Exception e1) {
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
				// documentos/frequancia separados por espa�o que contem o termo
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
					nodeTemp = new Node(arrayTemp[0], Integer.parseInt(arrayTemp[1]), Integer.parseInt(arrayTemp[2]));
					// insere o head na hastable
					arquivo.put(arrayTemp[0], nodeTemp);
					// para cada um dos outros pares documento/frequencia,
					// insere
					// eles no next do ultimo node inserido e seta o novo
					// nodeTemp
					for (int i = 3; i < arrayTemp.length; i += 2) {
						nodeTemp.setNext(new Node(Integer.parseInt(arrayTemp[i]), Integer.parseInt(arrayTemp[i + 1])));
						nodeTemp = nodeTemp.getNext();
					}
				}
				// caso o arquivo nao exista busca criar o arquivo
			} catch (FileNotFoundException e) {
				String listaArquivos[];
				// substituir a pasta Pagclean
				listaArquivos = new File("Pagclean").list();
				try {
					bancoPalavras(listaArquivos);
					//gerarAttrEsp(listaArquivos);
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
				FileInputStream sc = new FileInputStream("aic");
				Scanner hsc = new Scanner(arquivoInvertidoHead);
				//enquanto existirem keys no head
				while(hsc.hasNext()){
					String temp[] = hsc.nextLine().split(" ");
					//le a key do head
					String key = temp[0];
					//ler a quantidade de interacoes que vao ser lidas de dados dessa key
					int qt = Integer.parseInt(temp[1]);
					//le as infromacoes do head pra essa key no 
					byte tempNumDoc = (byte) sc.read();
					byte tempFreq = (byte) sc.read();
					Node nodeTemp = new Node(tempNumDoc, tempFreq);
					arquivo.put(key, nodeTemp);
					while (qt > 1) {
						// vai salvar o ultimo node inserido para facilitar a
						// insercao
						// de novos nodes
						// ler a linha
						tempNumDoc = (byte) sc.read();
						// splita nos espacos
						tempFreq = (byte) sc.read();
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
				String listaArquivos[];
				// substituir a pasta Pagclean
				listaArquivos = new File("Pagclean").list();
				try {
					bancoPalavrasByte(listaArquivos);//
					//
					//
					//
					//
					//
					//
					///
					//
					//
					//--------------------------------------------ADICIONAR A FUNC DE BYTES
					//
					//
					//
					///
					//
					//
					gerarAttrEspByte(listaArquivos);
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
						bw.write(tempNode.getNumDocByte());
						bw.write(tempNode.getFreqByte());
						bw.flush();
						while(tempNode.hasNext()){
							tempNode=tempNode.getNext();
							bw.write(tempNode.getNumDocByte());
							bw.write(tempNode.getFreqByte());
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
	
	public static void bancoPalavras(String[] listaArquivos) throws FileNotFoundException {
		for (int i = 0; i < listaArquivos.length; i++) {
			Hashtable tempHash = new Hashtable();
			String st = "";
			String arrayTemp[];
			File temp = new File("Pagclean/" + listaArquivos[i]);
			Scanner tempScan = new Scanner(temp, "UTF-8");
			while (tempScan.hasNext()) {
				st += tempScan.nextLine() + " ";
			}
			//System.out.println(st);
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
				int tpp = Integer.parseInt(listaArquivos[i].substring(0, listaArquivos[i].length()-10));
				if (arquivo.containsKey(key)) {
					Node tempNode = (Node) arquivo.get(key);
					tempNode.setNext(new Node(tpp, k));
					arquivo.put(key, tempNode);
				} else
					arquivo.put(key, new Node(key, tpp, k));
				s++;
			}
		}
	}

	public static void bancoPalavrasByte(String [] listaArquivos) throws FileNotFoundException{
		//varre a lista de arquivos, ie todas as Pagclean
		for (int i = 0; i < listaArquivos.length; i++) {
			//hastable temporario para salvar as palavras em uma pagina
			Hashtable tempHash = new Hashtable();
			String st = "";
			String arrayTemp[];
			//apaonta para a pagina salva no arquivo i.txt
			
			
			
			
			File temp = new File("Pagclean/" + listaArquivos[i]);
			Scanner tempScan = new Scanner(temp, "UTF-8");
			//Le toda a pagina salvano as linhas com espa�os
			while (tempScan.hasNext()) {
				st += tempScan.nextLine() + " ";
			}
			arrayTemp = st.split(" ");
			//modifica o hashtable temporario de acordo com as palavras do documento
			for (int ii = 0; ii < arrayTemp.length; ii++) {
				//se a palavra ja foi inserida no hashtable adiciona de 1 o valor salvo pela key
				 if (tempHash.containsKey(arrayTemp[ii])) {
					int aux = (int) tempHash.get(arrayTemp[ii]);
					tempHash.put(arrayTemp[ii], aux + 1);
				//caso o hashtable nao possua a palavra, a insere como key com valor inicial 1
				 } else
					tempHash.put(arrayTemp[ii], 1);
			}
			//vai pegar todos os valores do hashtable temporario e passar para o hashtable principal respeitando o seu formato
			int s = 0;
			//pega todas as keys do hashtable temporario
			Enumeration names = tempHash.keys();
			//enquanto aind tiverem keys
			while (s < tempHash.size()) {
				//usa a key para pegar o valor no hashtable temporario
				String key = (String) names.nextElement();
				int tempFreqa = (int) tempHash.get(key);
				byte tempFreq = (byte) tempFreqa;
				//se o hashtable principal j� contem a key, pega o head da lista salva por essa key e chama o setNext com um novo node
				//com as informacoes do hashtable temporario, ie insere no final da lista
				if (arquivo.containsKey(key)) {
					
					
					
					
					
					
					
					int nnd = Integer.parseInt(listaArquivos[i].substring(0, listaArquivos[i].length()-10));
					
					
					
					
					
					
					
					Node tempNode = (Node) arquivo.get(key);
					Node tempLast = tempNode.getLast();
					byte tempDistancia;
					if(tempNode!=tempLast) tempDistancia = (byte) (nnd-(tempNode.getNumDocByte()+tempLast.getNumDocByte()));
					else tempDistancia = (byte) (nnd - tempNode.getNumDocByte());
					tempNode.setNext(new Node(tempDistancia, tempFreq));
					arquivo.put(key, tempNode);
				//caso o hashtable principal nao possua a key a insere como head 
				} else{
					byte iii = (byte) i;
					arquivo.put(key, new Node(key, iii, tempFreq));
				}
				s++;
			}
		}
		
	}
	
	public Hashtable getIndiceInvertido() {
		return this.arquivo;
	}
	
	private void salvarHash(String key, int numDoc, int freq){
		if (arquivo.containsKey(key)) {
			//pega o head da lista
			Node tempNode = (Node) arquivo.get(key);
			//seta o next do head coom o numero do documento e a frequancia 1
			tempNode.setNext(new Node(numDoc, freq));
			//salva de volta na hashtable
			arquivo.put(key, tempNode);
		//caso n�o exista na hashtable cria um head novo
		} else
			arquivo.put(key, new Node(key, numDoc, freq));
	}
	
	private void salvarHashByte(String key, int numDoc, int freq){
		byte tempFreq = (byte) freq;
		//se o hashtable principal j� contem a key, pega o head da lista salva por essa key e chama o setNext com um novo node
		//com as informacoes do hashtable temporario, ie insere no final da lista
		if (arquivo.containsKey(key)) {
			Node tempNode = (Node) arquivo.get(key);
			Node tempLast = tempNode.getLast();
			byte tempDistancia;
			if(tempNode!=tempLast) tempDistancia = (byte) (numDoc-(tempNode.getNumDocByte()+tempLast.getNumDocByte()));
			else tempDistancia = (byte) (numDoc - tempNode.getNumDocByte());
			tempNode.setNext(new Node(tempDistancia, tempFreq));
			arquivo.put(key, tempNode);
		//caso o hashtable principal nao possua a key a insere como head 
		} else{
			byte iii = (byte) numDoc;
			arquivo.put(key, new Node(key, iii, tempFreq));
		}
	}
	
	private void salvar() throws IOException{
		FileWriter fw = new FileWriter("ai");
		BufferedWriter bw = new BufferedWriter(fw);
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
			bw.flush();
			s++;
		}
		
	}
	
	private void gerarAttrEsp(String [] listaArquivos) throws IOException{
		Pattern marca;
		Pattern sisOP;
		Pattern proc;
		Pattern hd;
		Pattern polTela;
		for (int i = 0; i < listaArquivos.length; i++) {
			String st = "";
			System.out.println(listaArquivos[i]);
			String temp;
			//System.out.println(Integer.parseInt(listaArquivos[i].substring(0, listaArquivos[i].length()-10)));
		
			InputStreamReader fr = new InputStreamReader(new FileInputStream("Pag/" + listaArquivos[i].substring(0, listaArquivos[i].length()-6)), "UTF-8");
			BufferedReader br = new BufferedReader(fr);
			while ((temp = br.readLine()) != null) {
				st += ("\n" + temp);
			}
			//System.out.println(st + "\n\n");

			if (st.contains("carrefour")) {
				System.out.println("entrou carrefour");
				marca = Pattern.compile("Marca</p>\\s*</td>\\s*<td>\\s*<p>\\s*([a-zA-z0-9\\-]+)\\s*&nbsp;<");
				sisOP = Pattern.compile("Sistema operacional</p>\\s*</td>\\s*<td>\\s*<p>\\s*(.*?)\\s*&nbsp;<");
				proc = Pattern.compile("Processador</p>\\s*</td>\\s*<td>\\s*<p>\\s*(.*?)\\s*&nbsp;<");
				hd = Pattern.compile("HD</p>\\s*</td>\\s*<td>\\s*<p>\\s*([a-zA-z0-9\\-]+)\\s*&nbsp;<");
				polTela = Pattern.compile("Tamanho da Tela</p>\\s*</td>\\s*<td>\\s*<p>\\s*(.*?)\\s*&nbsp;<");
			} else if (st.contains("americanas")) {
				System.out.println("entrou americanas");
				marca = Pattern.compile("Marca</td>\\s*<td>([a-zA-z0-9\\-\\s]+)<");
				sisOP = Pattern.compile("Sistema Operacional</td>\\s*<td>([a-zA-z0-9\\-\\s]+)<");
				proc = Pattern.compile("Processador</td>\\s*<td>([a-zA-z0-9\\-\\s]+)<");
				hd = Pattern.compile("HD</td>\\s*<td>([a-zA-z0-9\\-\\s]+)<");
				polTela = Pattern.compile("Polegadas da Tela</td>\\s*<td>(.*?)<");
			} else if (st.contains("casasbahia")) {
				System.out.println("entrou casasbahia");
				proc = Pattern.compile("class=\"Processador\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s\\�\\�]+)\\s<");
				marca = Pattern.compile("class=\"contatoFornecedor\">\\s*<h3 class=\"tit\">Contato ([a-zA-z0-9\\-\\s]+)\\s<");
				sisOP = Pattern.compile("class=\"Sistema operacional\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s]+)\\s<");
				hd = Pattern.compile("class=\"Disco r�gido (HD)\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s]+)\\s<");
				polTela = Pattern.compile("class=\"Tamanho da tela\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s\"\\.\\,]+)\\s<");
			
			
			
			
			
			} else if (st.contains("pontofrio")) {
				
				
				
				//System.out.println("entrou pontofrio");
				proc = Pattern.compile("Processador\\s* *</dt>\\s* *<dd>\\s* *(.*?)\\s* *<");
				marca = Pattern.compile("<h3 class=\"tit\">Contato (.*?)<");
				sisOP = Pattern.compile("Sistema operacional\\s* *</dt>\\s* *<dd>\\s *(.*?)\\s* *<");
				hd = Pattern.compile("Disco r.gido [(]HD[)]\\s* *</dt>\\s* *<dd>\\s* *(.*?)\\s *<");
				polTela = Pattern.compile("Tamanho da tela\\s* *</dt>\\s* *<dd>\\s* *(.*?)\\s *<");
				
				
			
			
			
			
			
			
			
			
			
			}else if(st.contains("extra.com")){
				
				System.out.println("entrou extra");
				
				hd = Pattern.compile("sebt4df5vfd54bv5df4b54df54b5d4b864sd4sd86v4s4as64f4sd4c84sac4cx4bdf4b4b4d4bv4f4v8v4v4v4sv44vs");
				
				marca = Pattern.compile("Detalhes do produto: (.*?):");
	            polTela = Pattern.compile("<dt>\\s*Tamanho da tela\\s*</dt>\\s*<dd>\\s*(.*?)\\s*</dd>");
	            sisOP = Pattern.compile("<dt>\\s*Sistema operacional\\s*</dt>\\s*<dd>\\s*(.*?)\\s*</dd>");
	            proc = Pattern.compile("<dt>\\s*Processador\\s*</dt>\\s*<dd>\\s*(.*?)\\s*</dd>");
			}else if(st.contains("kabum")){
				
				System.out.println("entrou kabum");
				
				polTela = Pattern.compile("sebt4df5vfd54bv5df4b54df54b5d4b864sd4sd86v4s4as64f4sd4c84sac4cx4bdf4b4b4d4bv4f4v8v4v4v4sv44vs");
	            sisOP = Pattern.compile("sebt4df5vfd54bv5df4b54df54b5d4b864sd4sd86v4s4as64f4sd4c84sac4cx4bdf4b4b4d4bv4f4v8v4v4v4sv44vs");
	            proc = Pattern.compile("sebt4df5vfd54bv5df4b54df54b5d4b864sd4sd86v4s4as64f4sd4c84sac4cx4bdf4b4b4d4bv4f4v8v4v4v4sv44vs");
	            hd = Pattern.compile("sebt4df5vfd54bv5df4b54df54b5d4b864sd4sd86v4s4as64f4sd4c84sac4cx4bdf4b4b4d4bv4f4v8v4v4v4sv44vs");
	            
	            
				marca = Pattern.compile("<title>KaBuM! - .*?(?: Gamer|) (.*?) ");
			}else if(st.contains("dell.com/br/")){
			
				System.out.println("entrou dell");
				
				hd = Pattern.compile("sebt4df5vfd54bv5df4b54df54b5d4b864sd4sd86v4s4as64f4sd4c84sac4cx4bdf4b4b4d4bv4f4v8v4v4v4sv44vs");
				
				marca = Pattern.compile("Dell");
				polTela = Pattern.compile("Tela<.*?>Tela .*? de (.*?) polegadas .*?</span>");
				sisOP = Pattern.compile("Sistema operacional<.*?<span.*?>(.*?)</span>");
				proc = Pattern.compile("Processador<.*?<span.*?>(.*?)</span>");
			}else if(st.contains("domain = 'lojahp.com.br'")){
			
				System.out.println("entrou hp");
				
				hd = Pattern.compile("sebt4df5vfd54bv5df4b54df54b5d4b864sd4sd86v4s4as64f4sd4c84sac4cx4bdf4b4b4d4bv4f4v8v4v4v4sv44vs");
				
				marca = Pattern.compile("Notebook ([^\\s]*?) (.*?) com Processado");
				polTela = Pattern.compile("<dt>\\s*Tamanho da tela\\s*</dt>\\s*<dd>\\s*(.*?)\\s*</dd>");
				sisOP = Pattern.compile("<dt>\\s*Sistema operacional\\s*</dt>\\s*<dd>\\s*(.*?)\\s*</dd>");
				proc = Pattern.compile("<dt>\\s*Processador\\s*</dt>\\s*<dd>\\s*(.*?)\\s*</dd>");
			}
			
			
			 else {
				
				 System.out.println("entrou else");
				 
				proc = Pattern.compile("Processador</td>\\s*<td>([a-zA-z0-9\\-\\s]+)<");
				marca = Pattern.compile("Marca</td>\\s*<td>([a-zA-z0-9\\-\\s]+)<");
				sisOP = Pattern.compile("Sistema Operacional</td>\\s*<td>([a-zA-z0-9\\-\\s]+)<");
				hd = Pattern.compile("HD</td>\\s*<td>([a-zA-z0-9\\-\\s]+)<");
				polTela = Pattern.compile("Polegadas da Tela</td>\\s*<td>([0-9\\.\"]+).*");
			}
			
			
			Matcher matcher;
			matcher = proc.matcher(st);
			if (matcher.find() && matcher.groupCount() == 1) {
				//salva o valor do campo
				String codigoDoUsuario = matcher.group(1);
				//pega o numero do arquivo (ainda em string) na posi��o 0 do array
				System.out.println(codigoDoUsuario);
				String aaaaaaaaaaaaaa [] = codigoDoUsuario.split(" ");
				String b = "";
				for(int k =0; k<aaaaaaaaaaaaaa.length; k++) b+=aaaaaaaaaaaaaa[k];
				//se ja existir no hastable
				salvarHash("processador." + b, Integer.parseInt(listaArquivos[i].substring(0, listaArquivos[i].length()-10)), 1);
			}
			
			
			matcher = sisOP.matcher(st);
			if (matcher.find() && matcher.groupCount() == 1) {
				//salva o valor do campo
				String codigoDoUsuario = matcher.group(1);
				//pega o numero do arquivo (ainda em string) na posi��o 0 do array
				System.out.println(codigoDoUsuario);
				String aaaaaaaaaaaaaa [] = codigoDoUsuario.split(" ");
				salvarHash("sistemaoperacional." + aaaaaaaaaaaaaa[0], Integer.parseInt(listaArquivos[i].substring(0, listaArquivos[i].length()-10)), 1);
			}
			
			
			matcher = marca.matcher(st);
			if (matcher.find() && matcher.groupCount() == 1) {
				//salva o valor do campo
				String codigoDoUsuario = matcher.group(1);
				//pega o numero do arquivo (ainda em string) na posi��o 0 do array
				System.out.println(codigoDoUsuario);

				salvarHash("marca." + codigoDoUsuario, Integer.parseInt(listaArquivos[i].substring(0, listaArquivos[i].length()-10)), 1);
			}
			
			
			matcher = hd.matcher(st);
			if (matcher.find() && matcher.groupCount() == 1) {
				//salva o valor do campo
				String codigoDoUsuario = matcher.group(1);
				//pega o numero do arquivo (ainda em string) na posi��o 0 do array
				System.out.println(codigoDoUsuario);
				String aa [] = codigoDoUsuario.split(" ");
				if(aa[0].charAt(0)==53) {
					if(aa[0].charAt(aa[0].length()-1)<58) aa[0] += "GB"; 
				}else {
					if(aa[0].charAt(aa[0].length()-1)<58) aa[0] += "TB";
				}
				salvarHash("hd." + aa[0], Integer.parseInt(listaArquivos[i].substring(0, listaArquivos[i].length()-10)), 1);
			}
			
			
			matcher = polTela.matcher(st);
			if (matcher.find() && matcher.groupCount() == 1) {
				//salva o valor do campo
				String codigoDoUsuario = matcher.group(1);
				//pega o numero do arquivo (ainda em string) na posi��o 0 do array
				System.out.println(codigoDoUsuario);
				String aaaaaaaaaaaaaa [] = codigoDoUsuario.split(" ");
				salvarHash("polegadatela." + aaaaaaaaaaaaaa[0], Integer.parseInt(listaArquivos[i].substring(0, listaArquivos[i].length()-10)), 1);
			}

		}
		
	}
	
	private void gerarAttrEspByte(String [] listaArquivos) throws IOException{
		Pattern marca;
		Pattern sisOP;
		Pattern proc;
		Pattern hd;
		Pattern polTela;
		for (int i = 0; i < listaArquivos.length; i++) {
			String st = "";
			String temp;
			System.out.println(listaArquivos[i]);
			System.out.println(Integer.parseInt(listaArquivos[i].substring(0, listaArquivos[i].length()-10)));
			FileReader fr = new FileReader("Pag/" + listaArquivos[i].substring(0, listaArquivos[i].length()-6));
			BufferedReader br = new BufferedReader(fr);
			while ((temp = br.readLine()) != null) {
				st += ("\n" + temp);
			}
			//System.out.println(st + "\n\n");

			if (st.contains("carrefour")) {
				
				marca = Pattern.compile("Marca</p>\\s*</td>\\s*<td>\\s*<p>\\s*([a-zA-z0-9\\-]+)\\s*&nbsp;<");
				sisOP = Pattern.compile("Sistema operacional</p>\\s*</td>\\s*<td>\\s*<p>\\s*(.*?)\\s*&nbsp;<");
				proc = Pattern.compile("Processador</p>\\s*</td>\\s*<td>\\s*<p>\\s*(.*?)\\s*&nbsp;<");
				hd = Pattern.compile("HD</p>\\s*</td>\\s*<td>\\s*<p>\\s*([a-zA-z0-9\\-]+)\\s*&nbsp;<");
				polTela = Pattern.compile("Tamanho da Tela</p>\\s*</td>\\s*<td>\\s*<p>\\s*(.*?)\\s*&nbsp;<");
			} else if (st.contains("americanas")) {

				marca = Pattern.compile("Marca</td>\\s*<td>([a-zA-z0-9\\-\\s]+)<");
				sisOP = Pattern.compile("Sistema Operacional</td>\\s*<td>([a-zA-z0-9\\-\\s]+)<");
				proc = Pattern.compile("Processador</td>\\s*<td>([a-zA-z0-9\\-\\s]+)<");
				hd = Pattern.compile("HD</td>\\s*<td>([a-zA-z0-9\\-\\s]+)<");
				polTela = Pattern.compile("Polegadas da Tela</td>\\s*<td>(.*?)<");
			} else if (st.contains("casasbahia")) {

				proc = Pattern.compile("class=\"Processador\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s\\�\\�]+)\\s<");
				marca = Pattern.compile("class=\"contatoFornecedor\">\\s*<h3 class=\"tit\">Contato ([a-zA-z0-9\\-\\s]+)\\s<");
				sisOP = Pattern.compile("class=\"Sistema operacional\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s]+)\\s<");
				hd = Pattern.compile("class=\"Disco r�gido (HD)\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s]+)\\s<");
				polTela = Pattern.compile("class=\"Tamanho da tela\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s\"\\.\\,]+)\\s<");
			} else if (st.contains("pontofrio")) {

				proc = Pattern.compile("class=\"Processador\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s\\�\\�]+)\\s<");
				marca = Pattern.compile("class=\"contatoFornecedor\">\\s*<h3 class=\"tit\">Contato ([a-zA-z0-9\\-\\s]+)\\s<");
				sisOP = Pattern.compile("class=\"Sistema operacional\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s]+)\\s<");
				hd = Pattern.compile("class=\"Disco r�gido (HD)\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s]+)\\s<");
				polTela = Pattern.compile("class=\"Tamanho da tela\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s\"\\.\\,]+)\\s<");
			
			
			
			
			
			
			
			
			
			
			
			}else if(st.contains("extra.com")){
				
				hd = Pattern.compile("sebt4df5vfd54bv5df4b54df54b5d4b864sd4sd86v4s4as64f4sd4c84sac4cx4bdf4b4b4d4bv4f4v8v4v4v4sv44vs");
				
				marca = Pattern.compile("Detalhes do produto: (.*?):");
	            polTela = Pattern.compile("<dt>\\s*Tamanho da tela\\s*</dt>\\s*<dd>\\s*(.*?)\\s*</dd>");
	            sisOP = Pattern.compile("<dt>\\s*Sistema operacional\\s*</dt>\\s*<dd>\\s*(.*?)\\s*</dd>");
	            proc = Pattern.compile("<dt>\\s*Processador\\s*</dt>\\s*<dd>\\s*(.*?)\\s*</dd>");
			}else if(st.contains("kabum")){
				
				polTela = Pattern.compile("sebt4df5vfd54bv5df4b54df54b5d4b864sd4sd86v4s4as64f4sd4c84sac4cx4bdf4b4b4d4bv4f4v8v4v4v4sv44vs");
	            sisOP = Pattern.compile("sebt4df5vfd54bv5df4b54df54b5d4b864sd4sd86v4s4as64f4sd4c84sac4cx4bdf4b4b4d4bv4f4v8v4v4v4sv44vs");
	            proc = Pattern.compile("sebt4df5vfd54bv5df4b54df54b5d4b864sd4sd86v4s4as64f4sd4c84sac4cx4bdf4b4b4d4bv4f4v8v4v4v4sv44vs");
	            hd = Pattern.compile("sebt4df5vfd54bv5df4b54df54b5d4b864sd4sd86v4s4as64f4sd4c84sac4cx4bdf4b4b4d4bv4f4v8v4v4v4sv44vs");
	            
	            
				marca = Pattern.compile("<title>KaBuM! - .*?(?: Gamer|) (.*?) ");
			}else if(st.contains("dell.com/br/")){
			
				hd = Pattern.compile("sebt4df5vfd54bv5df4b54df54b5d4b864sd4sd86v4s4as64f4sd4c84sac4cx4bdf4b4b4d4bv4f4v8v4v4v4sv44vs");
				
				marca = Pattern.compile("Dell");
				polTela = Pattern.compile("Tela<.*?>Tela .*? de (.*?) polegadas .*?</span>");
				sisOP = Pattern.compile("Sistema operacional<.*?<span.*?>(.*?)</span>");
				proc = Pattern.compile("Processador<.*?<span.*?>(.*?)</span>");
			}else if(st.contains("domain = 'lojahp.com.br'")){
			
				hd = Pattern.compile("sebt4df5vfd54bv5df4b54df54b5d4b864sd4sd86v4s4as64f4sd4c84sac4cx4bdf4b4b4d4bv4f4v8v4v4v4sv44vs");
				
				marca = Pattern.compile("Notebook ([^\\s]*?) (.*?) com Processado");
				polTela = Pattern.compile("<dt>\\s*Tamanho da tela\\s*</dt>\\s*<dd>\\s*(.*?)\\s*</dd>");
				sisOP = Pattern.compile("<dt>\\s*Sistema operacional\\s*</dt>\\s*<dd>\\s*(.*?)\\s*</dd>");
				proc = Pattern.compile("<dt>\\s*Processador\\s*</dt>\\s*<dd>\\s*(.*?)\\s*</dd>");
			}
			
			
			 else {

				proc = Pattern.compile("Processador</td>\\s*<td>([a-zA-z0-9\\-\\s]+)<");
				marca = Pattern.compile("Marca</td>\\s*<td>([a-zA-z0-9\\-\\s]+)<");
				sisOP = Pattern.compile("Sistema Operacional</td>\\s*<td>([a-zA-z0-9\\-\\s]+)<");
				hd = Pattern.compile("HD</td>\\s*<td>([a-zA-z0-9\\-\\s]+)<");
				polTela = Pattern.compile("Polegadas da Tela</td>\\s*<td>([0-9\\.\"]+).*");
			}
			
			
			Matcher matcher;
			matcher = proc.matcher(st);
			if (matcher.find() && matcher.groupCount() == 1) {
				//salva o valor do campo
				String codigoDoUsuario = matcher.group(1);
				//pega o numero do arquivo (ainda em string) na posi��o 0 do array
				
				//se ja existir no hastable
				salvarHashByte("processador." + codigoDoUsuario, Integer.parseInt(listaArquivos[i].substring(0, listaArquivos[i].length()-10)), 1);
			}
			
			
			matcher = sisOP.matcher(st);
			if (matcher.find() && matcher.groupCount() == 1) {
				//salva o valor do campo
				String codigoDoUsuario = matcher.group(1);
				//pega o numero do arquivo (ainda em string) na posi��o 0 do array
				
				salvarHashByte("sistemaoperacional." + codigoDoUsuario, Integer.parseInt(listaArquivos[i].substring(0, listaArquivos[i].length()-10)), 1);
			}
			
			
			matcher = marca.matcher(st);
			if (matcher.find() && matcher.groupCount() == 1) {
				//salva o valor do campo
				String codigoDoUsuario = matcher.group(1);
				//pega o numero do arquivo (ainda em string) na posi��o 0 do array
				
				salvarHashByte("marca." + codigoDoUsuario, Integer.parseInt(listaArquivos[i].substring(0, listaArquivos[i].length()-10)), 1);
			}
			
			
			matcher = hd.matcher(st);
			if (matcher.find() && matcher.groupCount() == 1) {
				//salva o valor do campo
				String codigoDoUsuario = matcher.group(1);
				//pega o numero do arquivo (ainda em string) na posi��o 0 do array
			
				salvarHashByte("hd." + codigoDoUsuario, Integer.parseInt(listaArquivos[i].substring(0, listaArquivos[i].length()-10)), 1);
			}
			
			
			matcher = polTela.matcher(st);
			if (matcher.find() && matcher.groupCount() == 1) {
				//salva o valor do campo
				String codigoDoUsuario = matcher.group(1);
				//pega o numero do arquivo (ainda em string) na posi��o 0 do array
				
				salvarHashByte("polegadatela." + codigoDoUsuario, Integer.parseInt(listaArquivos[i].substring(0, listaArquivos[i].length()-10)), 1);
			}

		}
	}
}
