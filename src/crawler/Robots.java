package crawler;

import java.util.ArrayList;

public class Robots {
	private static  String [] disallow;
	private static  String [] allow;
	private String siteUrl;
	
	private static void  extractAllowDisallow(String robotos) {
		ArrayList <String> al = new ArrayList<String>();
		ArrayList <String> dl = new ArrayList<String>();
		String temp[] = robotos.split("\n") ;
		int cont = 0;
		while(cont<temp.length){
			if((temp[cont].charAt(0)!='#' || temp[cont].charAt(0)!='\n') && temp[cont].substring(0, 11).equalsIgnoreCase("User-agent:") && temp[cont].substring(12).equalsIgnoreCase("*")) {
				cont +=1;
				while(cont<temp.length){
					if((temp[cont].charAt(0)!='#' || temp[cont].charAt(0)!='\n') && !(temp[cont].substring(0, 11).equalsIgnoreCase("User-agent:")) && temp[cont].charAt(0)=='D') {
						dl.add(temp[cont].substring(13));
					}
					else if ((temp[cont].charAt(0)!='#' || temp[cont].charAt(0)!='\n') && !(temp[cont].substring(0, 11).equalsIgnoreCase("User-agent:")) && temp[cont].charAt(0)=='A') {
						al.add(temp[cont].substring(13));
					}
					cont+=1;
				}
			}
			cont +=1;
		}
			
		disallow = new String[dl.size()];
		dl.toArray(disallow);
		allow = new String[al.size()];
		al.toArray(allow);
		
	}
	
	public Robots(String url, String robotsTXT) {
		this.siteUrl = url;
		extractAllowDisallow(robotsTXT);
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
