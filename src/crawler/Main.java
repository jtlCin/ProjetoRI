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
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Main {
	private static String [] positivos = {"notebook", "intel", "core", "4gb", "8gb", "500", "windos", "informatica", "i5", "i7", "dell", "lenovo", " samsung", "acer", "hp", "hdmi", "1tb"};
	private static String [] negativos = {"mochila", "bateria", "motorola", "snapdragon", "smartphone", "tv", "playstation", "console", "categoria", "Eletrodomesticos", "Eletroportateis",
										  "Lavadora", "Roupas", "Electrolux", "Fritadeira", "oleo", "Britania", "Android", "galaxy", "bebes", "Cadeira", "Conforto", "Multifuncional",
										  "Cartucho", "Tinta", "box", "busca", "Geladeira", "Portas", "Freezer", "Pneu", "Pirelli", "aro", "Travel", "Street", "Suporte", "Maleta", "dicas",
										  "institucional", "caneca", "case", "tablet", "volante", "ps3", "ps4", "WindowsPhone", "MOUSE", "Calculadora"};
	
	//REMOVER cea
	//array que salva as urls iniciais
	private static String [] sites = {"http://www.extra.com.br", "https://www.casasbahia.com.br", "https://www.carrefour.com.br", "https://www.submarino.com.br", "https://www.pontofrio.com.br",
									  "https://www.magazineluiza.com.br", "https://www.kabum.com.br", "http://www.dell.com/br/", "http://www.lojahp.com.br"};
	private static String [] dominio = {"extra.com", "casasbahia.com", "carrefour.com", "submarino.com", "pontofrio.com", "kabum.com", "dell.com/br/", "lojahp.com"};
	static Calendar cal = new GregorianCalendar();
	//criacao da fronteira, uma lista prioritaria baseada na variavel peso
	private static PriorityQueue fronteira = new PriorityQueue(50, new Comparator<Link>(){
		public int compare(Link a, Link b){
			if(a.getPeso()>b.getPeso()) return -1;
			if(a.getPeso()<b.getPeso()) return 1;
			return 0;
		}
	});
	static int cont = 0;
	private static Robots [] robots;
	private static Hashtable ctrlFronteira = new Hashtable();
	public static void salvarTXT(String texto, String nome) throws IOException{
		//essa parte remove o http:// https:// da url (tava dando erro na interpretacao do //)
		if(nome.charAt(4)=='s'){
			nome = nome.substring(8);
		}
		else{
			nome = nome.substring(7);
		}
		//define o endereco aonde o arquivo txt vai ser salvo - na pasta Paginas\nome(String).txt
		String endereco = "Paginas\\" + cont + ".txt";
		//ESSE BLOCO DE CODIGO SALVA O TEXTO DO ARTIGO EM UM TXT
		FileWriter fw = new FileWriter(endereco);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(texto);
		bw.flush();
		fw.close();
		bw.close();
		cont++;
	}

	public static void getRobots(){
		//variavel global
		robots = new Robots[sites.length];
		URLConnection connection = null;
		String content = null;
		for(int i=0; i<robots.length; i++){
			try{
				connection = new URL(sites[i] + "/robots.txt").openConnection();
				Scanner scanner = new Scanner(connection.getInputStream());
				//le a pagina ate o final (delimitador \\Z)
				scanner.useDelimiter("\\Z");
				content = scanner.next();
				robots[i] = new Robots(sites[i], content);				
			}catch (MalformedURLException e){
				e.printStackTrace();
			}catch (IOException e){
				robots[i] = new Robots(sites[i], "");
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
	public static boolean verificarRobots(String a){
		for(int i = 0; i< robots.length; i++){
			if(robots[i].checkDisallow(a)) return true;
		}
		return false;
	}
	public static boolean verificarDominio(String a){
		for(int i = 0; i<dominio.length; i++){
			if(a.contains(dominio[i])) return true;
		}
		return false;
	}
	public static String [] filtrarUrls(String[] a){
		ArrayList <String> temp = new ArrayList <String>();
		for(int i = 0; i< a.length; i++){
			if(a[i].length()>0)temp.add(a[i]);
		}
		String [] b = new String[temp.size()];
		temp.toArray(b);
		return b;
		
	}
	public static void adicionarUrlFronteira(String html) {
		try{
		String titleTemp, urlTemp;
		String [] urls = parse(html);
		urls = filtrarUrls(urls);
		for(int i=0; i<urls.length ; i++) {
			int peso = 0;
			URL url = new URL(urls[i]);
			URLConnection connection = (URLConnection)  url.openConnection();
			connection.connect();
			//pega o tipo de conteudo da url
			String contentType = connection.getContentType();
			int lastModified = cal.get(Calendar.DAY_OF_WEEK);
			//bloco que pega o title
			InputStream inp = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inp, StandardCharsets.UTF_8));
			Scanner scanner = new Scanner(br);
			scanner.useDelimiter("</title>");
			if(scanner.hasNext()){
				String title = scanner.next();
				String [] aux = title.split("<title>");
				if(aux.length>=2)title = aux[1];
				else title = " ";
				titleTemp = title.toUpperCase();
				urlTemp = urls[i].toUpperCase();
				//verificar dominio, verificar robots
				if(verificarDominio(urls[i]) /*&& !verificarRobots(urlTemp)*/ && (contentType.equalsIgnoreCase("text/html") || contentType.equalsIgnoreCase("text/html; charset=utf-8"))){	
					if(!ctrlFronteira.containsKey(urls[i])){
						for(int ii = 0; ii<positivos.length; ii++){
							if(urlTemp.contains(positivos[ii].toUpperCase()))peso++;
							if(titleTemp.contains(positivos[ii].toUpperCase()))peso+=2;
						}
						for(int ii = 0; ii<negativos.length; ii++){
							if(urlTemp.contains(negativos[ii].toUpperCase()))peso--;
							if(titleTemp.contains(negativos[ii].toUpperCase()))peso-=5;
						}
					}

					else{
						int temp = (int) ctrlFronteira.get(urls[i]);
						if(!(temp==lastModified)){
							for(int ii = 0; ii<positivos.length; ii++){
								if(urlTemp.contains(positivos[ii].toUpperCase()))peso++;
								if(titleTemp.contains(positivos[ii].toUpperCase()))peso+=2;
							}
							for(int ii = 0; ii<negativos.length; ii++){
								if(urlTemp.contains(negativos[ii].toUpperCase()))peso--;
								if(titleTemp.contains(negativos[ii].toUpperCase()))peso-=5;
							}
						}
					}
					if(peso>=0){
						fronteira.add(new Link(peso, urls[i]));
						ctrlFronteira.put(urls[i], lastModified);
					
					}
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static void main(String [] args) throws IOException{
		//getRobots();
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
	
