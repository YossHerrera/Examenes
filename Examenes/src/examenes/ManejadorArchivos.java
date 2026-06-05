package examenes;

	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileReader;
	import javax.swing.JFileChooser;
	import javax.swing.JOptionPane;
	import javax.swing.JTextArea;

	public class ManejadorArchivos {
	    
	    private JTextArea areaSuperior;
	    private JTextArea areaInferior;
	    
	    public ManejadorArchivos(JTextArea areaSuperior, JTextArea areaInferior) {
	        this.areaSuperior = areaSuperior;
	        this.areaInferior = areaInferior;
	    }
	    
	    public void buscarYcargarArchivo() {
	        JFileChooser selector = new JFileChooser();
	        //File carpeta = new File(System.getProperty("user.home")+"\\OneDrive\\Test");
	        selector.setCurrentDirectory(new File("Test"));
	        //if(!carpeta.exists()){
	            //carpeta.mkdirs();
	        //}
	        //selector. setCurrentDirectory(carpeta);
	        
	        int resultado = selector.showOpenDialog(null);

	        if (resultado == JFileChooser.APPROVE_OPTION) {
	            File archivo = selector.getSelectedFile();
	            cargarArchivo(archivo);
	        }
	    }
	    
	    public void cargarArchivo(File archivo) {
	        try {
	            BufferedReader lector = new BufferedReader(new FileReader(archivo));
	            String linea;
	            areaSuperior.setText("");
	            areaInferior.setText("");

	            while ((linea = lector.readLine()) != null) {
	                areaSuperior.append(linea + "\n");
	            }

	            lector.close();

	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null,
	                 "Error al leer el archivo: " + ex.getMessage());
	        }
	    }
	}
