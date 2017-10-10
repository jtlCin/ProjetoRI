package extrator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PontoFrio {

	public static void main(String[] args) {
		String a = "lixo lixo lixo class=\"Processador\">\n<dt>\nProcessador\n</dt>\n<dd>\n6� Gera��o Intel� Core� i3-6006U 2.0 GHz\n</dd> lixo lixo"; 
		Pattern patProc = Pattern.compile(".*class=\"Processador\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s\\�\\�\\.\\,\\�\\�\\�]+).*");
		Pattern patModelo = Pattern.compile(".*class=\"Modelo\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s\\.\\,]+).*");
		Pattern patCor = Pattern.compile(".*class=\"Cor\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s\\.\\,]+).*");
		Pattern patMarca = Pattern.compile(".*class=\"contatoFornecedor\">\\s*<h3 class=\"tit\">Contato ([a-zA-z0-9\\-\\s\\.\\,]+).*");
		Pattern patSisOp = Pattern.compile(".*class=\"Sistema operacional\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s\\.\\,]+).*");
		Pattern patHD = Pattern.compile(".*class=\"Disco r�gido (HD)\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s\\.\\,]+).*");
		Pattern patMemRAM = Pattern.compile(".*class=\"Mem�ria RAM\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s\\.\\,]+).*");
		Pattern patPolTela = Pattern.compile(".*class=\"Tamanho da tela\">\\s*<dt>\\s*Processador\\s*</dt>\\s*<dd>([a-zA-z0-9\\-\\s\"\\.\\,]+).*");
		Pattern patPeso = Pattern.compile(".*Peso</dt><dd>\\s*([a-zA-z0-9\\-\\s\\.\\,]+).*");
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
