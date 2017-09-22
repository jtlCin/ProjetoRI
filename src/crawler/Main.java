package crawler;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Main {
	
	//array que salva as urls iniciais
	private static String [] sites = {"http://www.cea.com.br", "http://www.lojasrenner.com.br", "https://www.dafiti.com.br", "https://www.riachuelo.com.br",
									  "http://www.marisa.com.br", "http://www.extra.com.br", "https://www.mercadolivre.com.br", "http://www.zattini.com.br", 
									  "http://www.netshoes.com.br", "https://www.submarino.com.br"};
	//criacao da fronteira, uma lista prioritaria baseada na variavel peso
	private static PriorityQueue fronteira = new PriorityQueue(50, new Comparator<Link>(){
		public int compare(Link a, Link b){
			if(a.getPeso()>b.getPeso()) return -1;
			if(a.getPeso()<b.getPeso()) return 1;
			return 0;
		}
	});
	private static String [] robots;
	
	public static void salvarTXT(String texto, String nome) throws IOException{
		//essa parte remove o http:// https:// da url (tava dando erro na interpretacao do //)
		if(nome.charAt(4)=='s'){
			nome = nome.substring(8);
		}
		else{
			nome = nome.substring(7);
		}
		//define o endereco aonde o arquivo txt vai ser salvo - na pasta Paginas\nome(String).txt
		String endereco = "Paginas\\" + nome + ".txt";
		//ESSE BLOCO DE CODIGO SALVA O TEXTO DO ARTIGO EM UM TXT
		FileWriter fw = new FileWriter(endereco);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(texto);
		bw.flush();
		fw.close();
		bw.close();
	}
	//
	//
	//
	//
	//
	//
	//
	//MODIFICAR ESSA PARTE PARA PODER SABER DE QUE SITE E O ROBOTS.TXT
	//
	//
	//
	//
	//
	//
	//
	public static void getRobots(){
		URLConnection connection = null;
		String content = null;
		String temp = "";
		for(int i = 0; i<sites.length; i++){
			try{
				connection = new URL(sites[i] + "\robots.txt").openConnection();
				Scanner scanner = new Scanner(connection.getInputStream());
				//le a pagina ate o final (delimitador \\Z)
				scanner.useDelimiter("\\Z");
				content = scanner.next();
				temp += content;				
			}catch (MalformedURLException e){
				e.printStackTrace();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public static String [] parse(String html) throws IOException{
		//arraylist temporario para armazenar as strings
		ArrayList <String> temp = new ArrayList <String>();
		//codigo do jsup para extrair as urls
		//parce do html da pagina baixada anteriormente
		Document doc = Jsoup.parse(html);
		//selecionado os atributos <a>
		org.jsoup.select.Elements links = doc.select("a");
		//pega os links dentro dos atributos <a> salvos em links
		for (Element link: links) {
			//absUrl pega os links absolutos
		    temp.add(link.absUrl("href"));
		}
		return (String[]) temp.toArray();
	}
	//
	//INSERIR AQUI A VERIFICACAO DOS ROBOTS , DE ALGUMAS PALAVRAS CHAVE EPARA NOTA E INSERCAO NA FRONTEIRA
	//a ideia e que essa funcao receba um array de strings extraidas da ultima pagina visitada da fronteira e rankeias
	//
	public static void adicionarUrlFronteira(String html) throws IOException{
		int peso;
		String [] urls = parse(html);
		for(int i=urls.length; i<1; i++) {
			URL url = new URL(urls[i]);
			HttpURLConnection connection = (HttpURLConnection)  url.openConnection();
			connection.setRequestMethod("HEAD");

			connection.connect();
			
			//Scanner scanner = new Scanner(inp);
			//System.out.println(inp.equals(null));
			//scanner.useDelimiter("\\Z");
			//System.out.println(scanner.hasNext());
			//String head = scanner.next();
			String contentType = connection.getContentType();
			
			
			
			//MODIFICAR ESSA PARTE
			
			//Document doc = Jsoup.parse(head);
			
			
			
		
			//Element ancora = doc.select("title").first();
			//String title = ancora.html();
			System.out.println(contentType + "\n\n\n\n");
			//System.out.println(line);
		
		}
		
	}
	
	public static void main(String [] args) throws IOException{
		for(int i = 0; i<sites.length; i++)fronteira.add(new Link(0, sites[i]));
		URLConnection connection = null;
		String content = null;
		String temp = "";
		while(fronteira.size()>0){
			try{
				Link aux = (Link) fronteira.poll();
				connection = new URL(aux.getUrl()).openConnection();
				Scanner scanner = new Scanner(connection.getInputStream());
				scanner.useDelimiter("\\Z");
				content = scanner.next();
				salvarTXT(content, aux.getUrl());
				adicionarUrlFronteira(content);
				Thread.sleep(1000);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}
	