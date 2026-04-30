package examenes;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalislLexicoExamen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String entrada = "El lenguaje Java es excelente. HTML y CSS son tecnologías-web\n. " + 
				"El valor de PI es=3.1416.Los números 42 y 0.5 son ejemplos.\n " + 
				"HOLA,MUNDO. Una palabra como eStUdIo es válida.67-5.4.3\n " + 
				"Esto es un texto de prueba. 3. .5 12a 51-50 holaMundo. A B C \n" + 
				"MINUSCULAS minusculas MIXto mIxto 123 45.67 .78 99.";
		
		String regex ="\\b//d+\\.//d+"
				 Pattern pattern = Pattern.compile(regex);
				 Matcher matcher = pattern.matcher(entrada);
				
			     ArrayList<String> numeros = new ArrayList<>();
			     ArrayList<String> mixto = new ArrayList<>();
			     ArrayList<String> soloMayusculas = new ArrayList<>();
			     ArrayList<String> soloMinusculas = new ArrayList<>();
			   
			     while(matcher.)


	}

}
