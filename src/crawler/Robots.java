package crawler;

public class Robots {
	private String [] disallow;
	private String [] allow;
	private String siteUrl;
	private static String [] extractDisallow(String robtos) {
		
		return null;
	}
	private static String [] extractAllow(String robtos) {
		
		return null;
	}
	public Robots(String url, String robotsTXT) {
		this.siteUrl = url;
		this.disallow = extractDisallow(robotsTXT);
		this.allow = extractAllow(robotsTXT);
	}
	public String getSiteUrl() {
		return this.siteUrl;
	}
	public boolean checkDisallow(String url) {
		return false;
	}
	public boolean checkAllow(String url) {
		return true;
	}
}
