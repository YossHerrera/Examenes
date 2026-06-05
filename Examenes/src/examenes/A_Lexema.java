package examenes;

public class A_Lexema {
	    public static final int ID = 1;
	   public static final int NUM = 2;

	   public static final int IGUAL_IGUAL = 3;
	   public static final int DIFERENTE = 4;
	   public static final int MENOR = 5;
	   public static final int MAYOR = 6;
	   public static final int MENOR_IGUAL = 7;
	   public static final int MAYOR_IGUAL = 8;
	   public static final int IGUAL = 9;

	   public static final int SUMA = 10;
	   public static final int RESTA = 11;
	   public static final int MULT = 12;
	   public static final int DIV = 13;

	   public static final int COMA = 14;
	   public static final int PUNTO_COMA = 15;
	   public static final int PUNTO = 16;
	   public static final int PAR_IZQ = 17;
	   public static final int PAR_DER = 18;

	   public static final int MENOR_G = 19;
	   public static final int MAYOR_G = 20;
	   public static final int FLECHA = 21;
	   public static final int EPSILON = 22;
	   

	    public static int esReservada(String palabra) {
	       int res = ID; // Por defecto es ID normal
	       
	       String[] reservadas = {"const","var","proced","begin","end","if","then","while","do","for","to","down","read","write","call"};
	       int[] valores = {23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37};
	       
	       palabra = palabra.toLowerCase();
	       
	       for (int i = 0 ; i < reservadas.length ; i++){
	           if (palabra.equals(reservadas[i])) {
	               return valores[i];
	           }
	       }
	       return ID;
	   }

	   public static int obtenerTokenSimbolo(String simbolo) {
	       switch (simbolo) {
	           case "==": return IGUAL_IGUAL;
	           case "!=": return DIFERENTE;
	           case "<":  return MENOR;
	           case ">":  return MAYOR;
	           case "<=": return MENOR_IGUAL;
	           case ">=": return MAYOR_IGUAL;
	           case "=":  return IGUAL;
	           case "+":  return SUMA;
	           case "-":  return RESTA;
	           case "*":  return MULT;
	           case "/":  return DIV;
	           case ".":  return PUNTO;
	           case ",":  return COMA;
	           case ";":  return PUNTO_COMA;
	           case "(":  return PAR_IZQ;
	           case ")":  return PAR_DER;
	           case "->": return FLECHA;
	           case "ε":  return EPSILON;
	           default:   return 0;
	       }
	   }

	}
