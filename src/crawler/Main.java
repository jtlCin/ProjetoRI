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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Main {
	//REMOVER cea
	//array que salva as urls iniciais
	private static String [] sites = {"http://www.lojasrenner.com.br", "https://www.dafiti.com.br", "https://www.riachuelo.com.br",
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
	private static Robots [] robots;
	
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

	public static void getRobots(){
		robots = new Robots[sites.length];
		URLConnection connection = null;
		String content = null;
		for(int i=0; i<robots.length; i++){
			try{
				connection = new URL(sites[i] + "\robots.txt").openConnection();
				Scanner scanner = new Scanner(connection.getInputStream());
				//le a pagina ate o final (delimitador \\Z)
				scanner.useDelimiter("\\Z");
				content = scanner.next();
				robots[i] = new Robots(sites[i], content);				
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
		//parse do html da pagina baixada anteriormente
		Document doc = Jsoup.parse(html);
		//selecionado os atributos <a>
		org.jsoup.select.Elements links = doc.select("a");
		//pega os links dentro dos atributos <a> salvos em links
		for (Element link: links) {
			//absUrl pega os links absolutos
		    temp.add(link.absUrl("href"));
		}
		String [] a = new String[temp.size()];
		temp.toArray(a);
		return a;
	}
	
	public static void adicionarUrlFronteira(String html) throws IOException{
		int peso;
		String [] urls = parse(html);
		for(int i=0; i<urls.length ; i++) {
			URL url = new URL(urls[i]);
			URLConnection connection = (URLConnection)  url.openConnection();
			connection.connect();
			//pega o tipo de conteudo da url
			String contentType = connection.getContentType();
			
			//bloco que pega o title
			InputStream inp = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inp, StandardCharsets.UTF_8));
			Scanner scanner = new Scanner(br);
			scanner.useDelimiter("</title>");
			String title = scanner.next();
			String [] aux = title.split("<title>");
			title = aux[1];
			
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
	