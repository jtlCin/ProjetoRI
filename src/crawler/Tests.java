package crawler;

import java.util.ArrayList;

public class Tests {

	public static void main(String[] args) {
		String robotos = "#siteId: MLB\n" + 
				"#country: brasil\n" + 
				"User-agent: *\n" + 
				"Disallow: /HOME/\n" + 
				"Disallow: /gz/merch/\n" + 
				"Disallow: /gz/menu\n" + 
				"Disallow: /gz/webdevice/config\n" + 
				"\n" + 
				" ##Block - Referidos\n" + 
				" Disallow: /gz/referidos\n" + 
				"";
		ArrayList <String> al = new ArrayList<String>();
		ArrayList <String> dl = new ArrayList<String>();
		String temp[] = robotos.split("\n") ;
		int cont = 0;
		while(cont<temp.length){
			if((temp[cont].charAt(0)!='#' || temp[cont].charAt(0)!='\n' || !(temp[cont].equalsIgnoreCase(""))) && temp[cont].substring(0, 11).equalsIgnoreCase("User-agent:") && temp[cont].substring(12).equalsIgnoreCase("*")) {
				cont +=1;
				while(cont<temp.length){
					//string "" dando erro nessa parte                               olhar aqui \/
					if((temp[cont].charAt(0)!='#' || temp[cont].charAt(0)!='\n' || !(temp[cont].equalsIgnoreCase(""))) && !(temp[cont].substring(0, 11).equalsIgnoreCase("User-agent:")) && temp[cont].charAt(0)=='D') {
						dl.add(temp[cont].substring(10));
					}
					else if ((temp[cont].charAt(0)!='#' || temp[cont].charAt(0)!='\n') && !(temp[cont].substring(0, 11).equalsIgnoreCase("User-agent:")) && temp[cont].charAt(0)=='A') {
						al.add(temp[cont].substring(10));
					}
					cont+=1;
				}
			}
			cont +=1;
		}
		System.out.println("Imprimir Disallows");
		temp = (String[]) dl.toArray();
		for(int i = 0; i<temp.length; i++) System.out.println(temp[i]);
		System.out.println("\n\nImprimir Allows");
		temp = (String[]) dl.toArray();
		for(int i = 0; i<temp.length; i++) System.out.println(temp[i]);

	}

}
