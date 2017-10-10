package extrator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Carrefour {

	public static void main(String[] args) {
		
		String a = "lixo lixo lixo Processador</p></td>\n<td>\n<p>Intel Core i5 <lixo lixo"; 
		Pattern patProc = Pattern.compile(".*Processador</p>\\s*</td>\\s*<td>\\s*<p>\\s*([a-zA-z0-9\\-\\s\\�\\�]+).*");
		Pattern patModelo = Pattern.compile(".*Modelo</p>\\s*</td>\\s*<td>\\s*<p>\\s*([a-zA-z0-9\\-\\s]+).*");
		Pattern patCor = Pattern.compile(".*Cor</p>\\s*</td>\\s*<td>\\s*<p>\\s*([a-zA-z0-9\\-\\s]+).*");
		Pattern patMarca = Pattern.compile(".*Marca</p>\\s*</td>\\s*<td>\\s*<p>\\s*([a-zA-z0-9\\-\\s]+).*");
		Pattern patSisOp = Pattern.compile(".*Sistema operacional</p>\\s*</td>\\s*<td>\\s*<p>\\s*([a-zA-z0-9\\-\\s]+).*");
		Pattern patHD = Pattern.compile(".*HD</p>\\s*</td>\\s*<td>\\s*<p>\\s*([a-zA-z0-9\\-\\s]+).*");
		Pattern patMemRAM = Pattern.compile(".*Mem�ria RAM</p>\\s*</td>\\s*<td>\\s*<p>\\s*([a-zA-z0-9\\-\\s]+).*");
		Pattern patPolTela = Pattern.compile(".*Tamanho da Tela</p>\\s*</td>\\s*<td>\\s*<p>\\s*([a-zA-z0-9\\-\\s\"\\.\\,]+).*");
		Pattern patPeso = Pattern.compile(".*Peso</p>\\s*</td>\\s*<td>\\s*<p>\\s*([a-zA-z0-9\\-\\s\\.\\,]+).*");
		 Matcher matcher;
		 matcher = patModelo.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Modelo: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Modelo: N�o encontrado.");
		 matcher = patCor.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Cor: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Cor: N�o encontrado."); 
		 matcher = patMarca.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Marca: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Marca: N�o encontrado.");
		 matcher = patSisOp.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Sistema Operacional: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Sistema Operacional: N�o encontrado.");
		 /*matcher = patSSD.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("SSD: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("SSD: N�o encontrado.");*/
		 matcher = patHD.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("HD: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("HD: N�o encontrado.");
		 matcher = patMemRAM.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Mem�ria RAM: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Mem�ria RAM: N�o encontrado.");
		 matcher = patProc.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Processador: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Processador: N�o encontrado.");
		 /*matcher = patFabricante.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Fabricante: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Fabricante: N�o encontrado.");*/
		 matcher = patPolTela.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Polegadas da Tela: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Polegadas da Tela: N�o encontrado.");
		 /*matcher = patDimensao.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Dimens�es: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Dimens�es: N�o encontrado.");*/
		 matcher = patPeso.matcher(a);
		 if(matcher.matches() && matcher.groupCount() == 1){
	    	 String codigoDoUsuario = matcher.group(1);
	    	 System.out.println("Peso: " + codigoDoUsuario);
	     } else	
	    	 System.out.println("Peso: N�o encontrado.");
	}

}
