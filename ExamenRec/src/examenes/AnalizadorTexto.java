package examenes;

	// Clase AnalizadorTexto.java
	import java.util.ArrayList;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	import javax.swing.JOptionPane;
	import javax.swing.JTextArea;

	public class AnalizadorTexto {
	    
	    private JTextArea areaSuperior;
	    private JTextArea areaInferior;
	    private ArrayList<Lexema> lexemas;
	    
	    public AnalizadorTexto(JTextArea areaSuperior, JTextArea areaInferior) {
	        this.areaSuperior = areaSuperior;
	        this.areaInferior = areaInferior;

	         this.areaInferior.setEditable(true);
	         lexemas = new ArrayList<>();
	    }

	    // GETTER
	    public ArrayList<Lexema> getListaLexemas() {
	        return lexemas;
	    }
	    
	    public void analizarTexto() {
	        String texto = areaSuperior.getText();
	        
	        if (texto.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, 
	                "No hay texto para analizar");
	            return;
	        }
	        lexemas.clear();
	        
	        String regex = 
	            "([A-Za-z]\\w*)|" +                     // Grupo 1 → Identificadores
	            "(0|[1-9]\\d*)|" +                      // Grupo 2 → Números
	           "(->|==|!=|<=|>=|<|>|=|\\+|-|\\*|/)|" +   // Grupo 3 → Operadores
	            "(\\.|,|;|\\(|\\))|" +                // Grupo 4 → Símbolos
	            "(\\s+)|" +                             // Grupo 5 → Espacios (ignorar)
	            "(.)";                                   // Grupo 6 → Errores

	        Pattern patron = Pattern.compile(regex);
	        Matcher matcher = patron.matcher(texto);

	        while (matcher.find()) {
	            String hallazgo = matcher.group();

	            if (matcher.group(1) != null) {
	                lexemas.add(new Lexema(hallazgo, "ID"));
	                continue;
	            }
	            if (matcher.group(2) != null) {
	                lexemas.add(new Lexema(hallazgo, "Num"));
	                continue;
	            }
	            if (matcher.group(3) != null) {
	                lexemas.add(new Lexema(hallazgo, "OP"));
	                continue;
	            }
	            if (matcher.group(4) != null) {
	                lexemas.add(new Lexema(hallazgo, "Simb"));
	                continue;
	            }
	            if (matcher.group(5) != null) {
	                continue; // Omitir espacios
	            }
	            if (matcher.group(6) != null) {
	                lexemas.add(new Lexema(hallazgo, "Error"));
	            }
	        }

	        // MOSTRAR
	        areaInferior.setText("");
	        for (Lexema l : lexemas) {
	            areaInferior.append(l.toString() + "\n");
	        }
	    }
	}
	        /*System.out.println("=== TOKENS VALIDOS ===");
	        for (String t : tokensValidos) {
	            System.out.println(t);*/
	       // }

	        /*System.out.println("=== TOKENS ERROR ===");
	        for (String e : tokensError) {
	            System.out.println(e);*/
	     //}
	//PARA MOSTRAR EN CONSOLA}
	//}

