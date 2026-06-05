package examenes;

	import java.util.ArrayList;
	import javax.swing.JTextArea;

	public class Sintactico {

	    private ArrayList<Lexema> lista;
	    private int pos;
	    private JTextArea salida;

	    // CONTADOR DE ERRORES
	    private int errores = 0;

	    public Sintactico(ArrayList<Lexema> lista,
	                      JTextArea salida) {

	        this.lista = lista;
	        this.salida = salida;
	        this.pos = 0;
	    }

	    // METODOS BASICOS

	    private Lexema actual() {

	        if (pos < lista.size()) {
	            return lista.get(pos);
	        }

	        return null;
	    }

	    private void avanzar() {

	        if (pos < lista.size()) {
	            pos++;
	        }
	    }

	    private boolean aceptar(int token) {

	        if (actual() != null &&
	            actual().getToken() == token) {

	            avanzar();
	            return true;
	        }

	        return false;
	    }

	    // ERROR

	    private void error(String mensaje) {

	        String encontrado = "FIN";

	        if (actual() != null) {
	            encontrado = actual().getDato();
	        }

	        salida.append(
	                "ERROR SINTÁCTICO\n" +
	                mensaje +
	                "\nToken encontrado: " +
	                encontrado +
	                "\n\n"
	        );

	        errores++;

	        // CONTINUAR ANALISIS
	        avanzar();
	    }

	    // ANALISIS PRINCIPAL

	    public void analizar() {

	        salida.setText("");

	        errores = 0;

	        try {

	            // DETECTAR SI ES GRAMATICA
	            if (esGramatica()) {

	                gramatica();

	            } else {

	                programa();
	            }

	        } catch (Exception e) {

	        }

	        if (errores == 0) {

	            salida.setText(
	                    "CÓDIGO CORRECTO\n\n" +
	                    "Análisis terminado sin errores."
	            );

	        } else {

	            salida.append(
	                    "TOTAL DE ERRORES: " + errores
	            );
	        }
	    }
	    private boolean esGramatica() {

	        // MINIMO 4 TOKENS
	        if (lista.size() < 4) {
	            return false;
	        }

	        // < ID > ->
	        return
	            lista.get(0).getToken() == A_Lexema.MENOR &&
	            lista.get(1).getToken() == A_Lexema.ID &&
	            lista.get(2).getToken() == A_Lexema.MAYOR &&
	            lista.get(3).getToken() == A_Lexema.FLECHA;
	    }
	    // ANALISIS DE GRAMATICA

	    private void gramatica() {

	        while (actual() != null) {

	            try {

	                regla();

	            } catch (Exception e) {

	                avanzar();
	            }
	        }
	    }

	    // REGLA

	    private void regla() {

	        // <
	        if (!aceptar(A_Lexema.MENOR)) {

	            error("Se esperaba '<'");
	        }

	        // ID
	        if (!aceptar(A_Lexema.ID)) {

	            error("Se esperaba identificador");
	        }

	        // >
	        if (!aceptar(A_Lexema.MAYOR)) {

	            error("Se esperaba '>'");
	        }

	        // ->
	        if (!aceptar(A_Lexema.FLECHA)) {

	            error("Se esperaba '->'");
	        }

	        produccion();
	    }

	    // PRODUCCION

	    private void produccion() {

	        while (actual() != null) {

	            // VERIFICAR SI REALMENTE ES OTRA REGLA
	            if (pos + 3 < lista.size()) {

	                if (
	                    lista.get(pos).getToken() == A_Lexema.MENOR &&
	                    lista.get(pos + 1).getToken() == A_Lexema.ID &&
	                    lista.get(pos + 2).getToken() == A_Lexema.MAYOR &&
	                    lista.get(pos + 3).getToken() == A_Lexema.FLECHA
	                ) {

	                    return;
	                }
	            }

	            avanzar();
	        }
	    }

	    // PROGRAMA

	    private void programa() {

	        bloque();

	        if (!aceptar(A_Lexema.PUNTO)) {

	            error("Se esperaba '.'");
	        }
	    }
	    
	    //Proposicion Examen Final
	    private void proposicion1() {
	    	aceptar(22);
	    	cicloProp();
	    	if (!aceptar(23)) {
	    		error("Se esperaba END");
	    }
    }
	    private void proposicion2() {

	        aceptar(28);

	        if (!aceptar(A_Lexema.ID)) {

	            error("Se esperaba ID");
	        }

	        if (!aceptar(A_Lexema.IGUAL)) {

	            error("Se esperaba '='");
	        }

	        expresion();
	    }
	    private void proposicion3() {

	        aceptar(32);

	        idNum();
	    }
	    private void proposicion4() {

	        aceptar(31);

	        if (!aceptar(A_Lexema.ID)) {

	            error("Se esperaba ID");
	        }
	    }
	    private void proposicion5() {

	        aceptar(33);

	        if (!aceptar(A_Lexema.ID)) {

	            error("Se esperaba ID");
	        }
	    }
	    private void proposicion6() {

	        aceptar(24);

	        condicion();

	        if (!aceptar(25)) {

	            error("Se esperaba THEN");
	        }

	        proposicion();
	    }
	    private void proposicion7() {

	        aceptar(26);

	        condicion();

	        if (!aceptar(27)) {

	            error("Se esperaba DO");
	        }

	        proposicion();
	    }
	    private void proposicion8() {

	        aceptar(28);

	        if (!aceptar(A_Lexema.ID)) {

	            error("Se esperaba ID");
	        }

	        if (!aceptar(A_Lexema.IGUAL)) {

	            error("Se esperaba '='");
	        }

	        expresion();

	        to_down();

	        expresion();

	        if (!aceptar(27)) {

	            error("Se esperaba DO");
	        }

	        proposicion();
	    }
	    private void proposicionFinal() {

	        aceptar(26);

	        condicion();

	        if (!aceptar(27)) {

	            error("Se esperaba DO");
	        }

	        proposicion();
	    }
	    

	    
	    
	    
	    
	    

	    // BLOQUE

	    private void bloque() {

	        declConst();

	        declVar();

	        declProc();

	        proposicion();
	    }

	    // DECL CONST

	    private void declConst() {

	        if (actual() != null &&
	            actual().getToken() == 19) {

	            avanzar();

	            listaConst();

	            if (!aceptar(A_Lexema.PUNTO_COMA)) {

	                error("Se esperaba ';'");
	            }
	        }
	    }

	    // LISTA CONST

	    private void listaConst() {

	        if (!aceptar(A_Lexema.ID)) {

	            error("Se esperaba ID");
	        }

	        if (!aceptar(A_Lexema.IGUAL)) {

	            error("Se esperaba '='");
	        }

	        if (!aceptar(A_Lexema.NUM)) {

	            error("Se esperaba NUM");
	        }

	        while (aceptar(A_Lexema.COMA)) {

	            if (!aceptar(A_Lexema.ID)) {

	                error("Se esperaba ID");
	            }

	            if (!aceptar(A_Lexema.IGUAL)) {

	                error("Se esperaba '='");
	            }

	            if (!aceptar(A_Lexema.NUM)) {

	                error("Se esperaba NUM");
	            }
	        }
	    }

	    // DECL VAR

	    private void declVar() {

	        if (actual() != null &&
	            actual().getToken() == 20) {

	            avanzar();

	            listaVar();

	            if (!aceptar(A_Lexema.PUNTO_COMA)) {

	                error("Se esperaba ';'");
	            }
	        }
	    }

	    // LISTA VAR

	    private void listaVar() {

	        if (!aceptar(A_Lexema.ID)) {

	            error("Se esperaba ID");
	        }

	        while (aceptar(A_Lexema.COMA)) {

	            if (!aceptar(A_Lexema.ID)) {

	                error("Se esperaba ID");
	            }
	        }
	    }

	    // DECL PROC

	    private void declProc() {

	        while (actual() != null &&
	               actual().getToken() == 21) {

	            avanzar();

	            if (!aceptar(A_Lexema.ID)) {

	                error("Se esperaba ID");
	            }

	            if (!aceptar(A_Lexema.PUNTO_COMA)) {

	                error("Se esperaba ';'");
	            }

	            bloque();

	            if (!aceptar(A_Lexema.PUNTO_COMA)) {

	                error("Se esperaba ';'");
	            }
	        }
	    }

	    // PROPOSICION

	    private void proposicion() {

	        if (actual() == null) {
	            return;
	        }

	        int token = actual().getToken();

	        // FIRST(bloqueBegin)
	        if (firstBloqueBegin(token)) {

	            bloqueBegin();
	            return;
	        }

	        // FIRST(asignacion)
	        if (firstAsignacion(token)) {

	            asignacion();
	            return;
	        }

	        // FIRST(escritura)
	        if (firstEscritura(token)) {

	            escritura();
	            return;
	        }

	        // FIRST(lectura)
	        if (firstLectura(token)) {

	            lectura();
	            return;
	        }

	        // FIRST(llamada)
	        if (firstLlamada(token)) {

	            llamada();
	            return;
	        }

	        // FIRST(si)
	        if (firstSi(token)) {

	            si();
	            return;
	        }

	        // FIRST(mientras)
	        if (firstMientras(token)) {

	            mientras();
	            return;
	        }

	        // FIRST(para)
	        if (firstPara(token)) {

	            para();
	            return;
	        }

	        error("Proposición inválida");
	    }
	    // BEGIN END

	    private void bloqueBegin() {

	        aceptar(22);

	        cicloProp();

	        if (!aceptar(23)) {

	            error("Se esperaba END");
	        }
	    }

	    // LISTA PROP

	    private void cicloProp() {

	        proposicion();

	        while (aceptar(A_Lexema.PUNTO_COMA)) {

	            proposicion();
	        }
	    }

	    // ASIGNACION

	    private void asignacion() {

	        if (!aceptar(A_Lexema.ID)) {

	            error("Se esperaba ID");
	        }

	        if (!aceptar(A_Lexema.IGUAL)) {

	            error("Se esperaba '='");
	        }

	        expresion();
	    }

	    // WRITE

	    private void escritura() {

	        aceptar(32);

	        idNum();
	    }

	    // VALOR

	    private void idNum() {

	        if (aceptar(A_Lexema.ID)) {
	            return;
	        }

	        if (aceptar(A_Lexema.NUM)) {
	            return;
	        }

	        error("Se esperaba ID o NUM");
	    }

	    // READ

	    private void lectura() {

	        aceptar(31);

	        if (!aceptar(A_Lexema.ID)) {

	            error("Se esperaba ID");
	        }
	    }

	    // CALL

	    private void llamada() {

	        aceptar(33);

	        if (!aceptar(A_Lexema.ID)) {

	            error("Se esperaba ID");
	        }
	    }

	    // IF

	    private void si() {

	        aceptar(24);

	        condicion();

	        if (!aceptar(25)) {

	            error("Se esperaba THEN");
	        }

	        proposicion();
	    }

	    // WHILE

	    private void mientras() {

	        aceptar(26);

	        condicion();

	        if (!aceptar(27)) {

	            error("Se esperaba DO");
	        }

	        proposicion();
	    }

	    // FOR

	    private void para() {

	        aceptar(28);

	        if (!aceptar(A_Lexema.ID)) {

	            error("Se esperaba ID");
	        }

	        if (!aceptar(A_Lexema.IGUAL)) {

	            error("Se esperaba '='");
	        }

	        expresion();

	        to_down();

	        expresion();

	        if (!aceptar(27)) {

	            error("Se esperaba DO");
	        }

	        proposicion();
	    }

	    // DIRECCION

	    private void to_down() {

	        if (actual() == null) {

	            error("Dirección inválida");
	            return;
	        }

	        int token = actual().getToken();

	        // FIRST(direccion)
	        if (!firstDireccion(token)) {

	            error("Se esperaba TO o DOWN");
	            return;
	        }

	        avanzar();
	    }

	    // CONDICION

	    private void condicion() {

	        expresion();

	        opRel();

	        expresion();
	    }

	    // OP REL
	    private void opRel() {

	        if (actual() == null) {

	            error("Operador relacional inválido");
	            return;
	        }

	        int token = actual().getToken();

	        // FIRST(opRel)
	        if (!firstOpRel(token)) {

	            error("Operador relacional inválido");
	            return;
	        }

	        avanzar();
	    }

	    // EXPRESION

	    private void expresion() {

	        termino();

	        while (actual() != null &&
	              (
	               actual().getToken() == A_Lexema.SUMA ||
	               actual().getToken() == A_Lexema.RESTA
	              )) {

	            avanzar();

	            termino();
	        }
	    }

	    // TERMINO

	    private void termino() {

	        factor();

	        while (actual() != null &&
	              (
	               actual().getToken() == A_Lexema.MULT ||
	               actual().getToken() == A_Lexema.DIV
	              )) {

	            avanzar();

	            factor();
	        }
	    }

	    // FACTOR
	    private void factor() {

	        if (actual() == null) {

	            error("Factor inválido");
	            return;
	        }

	        int token = actual().getToken();

	        // FIRST(factor)
	        if (!firstFactor(token)) {

	            error("Se esperaba NUM, ID o (");
	            return;
	        }

	        if (aceptar(A_Lexema.ID)) {
	            return;
	        }

	        if (aceptar(A_Lexema.NUM)) {
	            return;
	        }

	        if (aceptar(A_Lexema.PAR_IZQ)) {

	            expresion();

	            if (!aceptar(A_Lexema.PAR_DER)) {

	                error("Se esperaba ')'");
	            }
	        }
	    }
	 // =========================================
	 // FIRST
	 // =========================================

	 private boolean firstFactor(int token) {

	     return token == A_Lexema.NUM ||
	            token == A_Lexema.ID ||
	            token == A_Lexema.PAR_IZQ;
	 }

	 private boolean firstOpMult(int token) {

	     return token == A_Lexema.MULT ||
	            token == A_Lexema.DIV;
	 }

	 private boolean firstOpSumaRes(int token) {

	     return token == A_Lexema.SUMA ||
	            token == A_Lexema.RESTA;
	 }

	 private boolean firstDireccion(int token) {

	     return token == 29 || // to
	            token == 30;   // down
	 }

	 private boolean firstPara(int token) {

	     return token == 28; // for
	 }

	 private boolean firstMientras(int token) {

	     return token == 26; // while
	 }

	 private boolean firstSi(int token) {

	     return token == 24; // if
	 }

	 private boolean firstLlamada(int token) {

	     return token == 33; // call
	 }

	 private boolean firstLectura(int token) {

	     return token == 31; // read
	 }

	 private boolean firstValor(int token) {

	     return token == A_Lexema.NUM ||
	            token == A_Lexema.ID;
	 }

	 private boolean firstEscritura(int token) {

	     return token == 32; // write
	 }

	 private boolean firstAsignacion(int token) {

	     return token == A_Lexema.ID;
	 }

	 private boolean firstBloqueBegin(int token) {

	     return token == 26; // begin
	 }

	 private boolean firstProposicion(int token) {

	     return firstPara(token) ||
	            firstMientras(token) ||
	            firstSi(token) ||
	            firstLlamada(token) ||
	            firstLectura(token) ||
	            firstEscritura(token) ||
	            firstAsignacion(token) ||
	            firstBloqueBegin(token);
	 }

	 private boolean firstOpRel(int token) {

	     return token == A_Lexema.IGUAL_IGUAL ||
	            token == A_Lexema.DIFERENTE ||
	            token == A_Lexema.MENOR ||
	            token == A_Lexema.MAYOR ||
	            token == A_Lexema.MENOR_IGUAL ||
	            token == A_Lexema.MAYOR_IGUAL;
	 }

	 private boolean firstDeclProc(int token) {

	     return token == 25; // proced
	 }

	 private boolean firstDeclVar(int token) {

	     return token == 20; // var
	 }

	 private boolean firstListaVar(int token) {

	     return token == 24;
	 }

	 private boolean firstDeclConst(int token) {

	     return token == 23; // const
	 }

	 private boolean firstListaConst(int token) {

	     return token == A_Lexema.ID;
	 }

	 private boolean firstBloque(int token) {

	     return firstDeclConst(token) ||
	            firstDeclVar(token) ||
	            firstDeclProc(token) ||
	            firstProposicion(token);
	 }

	 private boolean firstPrograma(int token) {

	     return firstBloque(token);
	 }
	}
