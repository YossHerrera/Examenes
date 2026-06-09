package examenes;

import examenes.A_Lexema;

public class Lexema {
	    private String dato;
	    private String tipo;
	    private int token;

	    public Lexema(String dato, String tipo) {
	        this.dato = dato;
	        this.tipo = tipo;
	        if (tipo.equals("ID")) {
	            this.token =  A_Lexema.esReservada(dato);
	            // Si el token es 14 → es ID normal, si es otro → es Palabra Reservada (PR)
	            this.tipo = (this.token == A_Lexema.ID) ? "ID" : "PR";
	        } else if (tipo.equals("Num")) {
	            this.token =  A_Lexema.NUM;
	        } else if (tipo.equals("Error")) {
	            this.token = 0;
	        } else {
	            // Cualquier otro tipo se marca como símbolo
	            this.tipo = "Simb";
	            this.token =  A_Lexema.obtenerTokenSimbolo(dato);
	        }
	    }
	    public String getDato() {
	        return dato;
	    }

	    public String getTipo() {
	        return tipo;
	    }

	    public int getToken() {
	        return token;
	    }

	    @Override
	    public String toString() {

	        return "[" +
	                "\t" + dato +
	                "\t" + tipo +
	                "\t" + token +
	                "]";
	    }
	}


