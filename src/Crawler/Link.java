package Crawler;

public class Link {
	private int peso;
	private String url;
	public Link(int peso, String url){
		this.peso=peso;
		this.url=url;
	}
	public int getPeso() {
		return peso;
	}
	public String getUrl() {
		return url;
	}
	
}
