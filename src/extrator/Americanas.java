package extrator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Americanas {
	
	public static void main(String[] args) {
		String a = ""; 
		 Pattern patModelo = Pattern.compile(".*Modelo</td><td data-reactid=\"\\d\\d\\d\">([a-zA-z0-9\\-\\s]+).*");
		 Pattern patCor = Pattern.compile(".*Cor</td><td data-reactid=\"\\d\\d\\d\">([a-zA-z0-9\\-\\s]+).*");
		 Pattern patMarca = Pattern.compile(".*Marca</td><td data-reactid=\"\\d\\d\\d\">([a-zA-z0-9\\-\\s]+).*");
		 Pattern patSisOp = Pattern.compile(".*Sistema Operacional</td><td data-reactid=\"\\d\\d\\d\">([a-zA-z0-9\\-\\s]+).*");
		 //Pattern patSSD = Pattern.compile(".*SSD</td><td data-reactid=\"\\d\\d\\d\">([a-zA-z0-9\\-\\s]+).*");
		 Pattern patHD = Pattern.compile(".*HD</td><td data-reactid=\"\\d\\d\\d\">([a-zA-z0-9\\-\\s]+).*");
		 Pattern patMemRAM = Pattern.compile(".*Memória RAM</td><td data-reactid=\"\\d\\d\\d\">([a-zA-z0-9\\-\\s]+).*");
		 Pattern patProc = Pattern.compile(".*Processador</td><td data-reactid=\"\\d\\d\\d\">([a-zA-z0-9\\-\\s]+).*");
		 //Pattern patFabricante = Pattern.compile(".*Fabricante</td><td data-reactid=\"\\d\\d\\d\">([a-zA-z0-9\\-\\s]+).*");
		 Pattern patPolTela = Pattern.compile(".*Polegadas da Tela</td><td data-reactid=\"\\d\\d\\d\">([a-zA-z0-9\\-\\s\"]+).*");
		 //Pattern patDimensao = Pattern.compile(".*Dimensões aproximadas do produto (cm) - AxLxP</td><td data-reactid=\"\\d\\d\\d\">([a-zA-z0-9\\-\\s]+).*");
		 Pattern patPeso = Pattern.compile(".*Peso líq. aproximado do produto (kg)</td><td data-reactid=\"\\d\\d\\d\">([a-zA-z0-9\\-\\s]+).*");
		 Matcher matcher;
		 matcher = patModelo.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Modelo: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Modelo: Não encontrado.");
		 matcher = patCor.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Cor: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Cor: Não encontrado."); 
		 matcher = patMarca.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Marca: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Marca: Não encontrado.");
		 matcher = patSisOp.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Sistema Operacional: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Sistema Operacional: Não encontrado.");
		 /*matcher = patSSD.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("SSD: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("SSD: Não encontrado.");*/
		 matcher = patHD.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("HD: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("HD: Não encontrado.");
		 matcher = patMemRAM.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Memória RAM: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Memória RAM: Não encontrado.");
		 matcher = patProc.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Processador: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Processador: Não encontrado.");
		 /*matcher = patFabricante.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Fabricante: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Fabricante: Não encontrado.");*/
		 matcher = patPolTela.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Polegadas da Tela: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Polegadas da Tela: Não encontrado.");
		 /*matcher = patDimensao.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Dimensões: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Dimensões: Não encontrado.");*/
		 matcher = patPeso.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Peso: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Peso: Não encontrado.");
		 
		 
		 //Peso líq. aproximado do produto (kg)
	}
}
