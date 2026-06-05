package examenes;
//Clase Principal
	import java.awt.BorderLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import javax.swing.JFrame;
	import javax.swing.JMenu;
	import javax.swing.JMenuBar;
	import javax.swing.JMenuItem;
	import javax.swing.JScrollPane;
	import javax.swing.JSplitPane;
	import javax.swing.JTextArea;
	import javax.swing.SwingUtilities;

	public class VentanaPrincipalP extends JFrame {
	    private ManejadorArchivos manejadorArchivos;
	    private AnalizadorTexto analizadorTexto;
	    private JTextArea areaSuperior;
	    private JTextArea areaInferior;
	    private JMenuItem itemSintactico;
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(VentanaPrincipalP::new);
	    }

	    public VentanaPrincipalP() {

	        setTitle("Administrador de Procesos");
	        setSize(700, 600);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        areaSuperior = new JTextArea();
	        areaInferior = new JTextArea();

	        JScrollPane scrollSuperior = new JScrollPane(areaSuperior);
	        JScrollPane scrollInferior = new JScrollPane(areaInferior);

	        JSplitPane splitPane = new JSplitPane(
	                JSplitPane.VERTICAL_SPLIT,
	                scrollSuperior,
	                scrollInferior
	        );

	        splitPane.setDividerLocation(250);
	        add(splitPane, BorderLayout.CENTER);
	    inicializarManejadores();
	    configurarMenu();

	    setVisible(true); 
	    }
	       private void inicializarManejadores() {
	        manejadorArchivos = new ManejadorArchivos(areaSuperior, areaInferior);
	        analizadorTexto = new AnalizadorTexto(areaSuperior, areaInferior);
	    }
	    
	    private void configurarMenu() {
	        JMenuBar barraMenu = new JMenuBar();

	        JMenu menuArchivo = new JMenu("Archivo");
	        JMenuItem itemBuscarArchivo = new JMenuItem("Abrir");
	        menuArchivo.add(itemBuscarArchivo);
	        itemBuscarArchivo.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                manejadorArchivos.buscarYcargarArchivo();
	                itemSintactico.setEnabled(false);
	            }
	        });

	        JMenu menuProcesos = new JMenu("Compilar");
	      JMenuItem itemAnalizarTexto = new JMenuItem( "Léxicos");
	        menuProcesos.add(itemAnalizarTexto);
	        itemAnalizarTexto.addActionListener(new ActionListener() {
	           @Override
	            public void actionPerformed(ActionEvent e) {
	                analizadorTexto.analizarTexto();
	                itemSintactico.setEnabled(true);
	            }
	        });
	        
	        //JMenuItem itemSintactico = new JMenuItem("Sintáctico");
	        itemSintactico = new JMenuItem("Sintáctico");
	        itemSintactico.setEnabled(false);
	        menuProcesos.add(itemSintactico);
	        
	// ANALISIS SINTACTICO
	        itemSintactico.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {

	             // Primero hacer análisis léxico
	             analizadorTexto.analizarTexto();

	             // Crear sintáctico
	             Sintactico s = new Sintactico(
	                     analizadorTexto.getListaLexemas(),
	                     areaInferior
	             );

	             // Analizar gramática
	             s.analizar();
	         }
	     });
	     
	        barraMenu.add(menuArchivo);
	        barraMenu.add(menuProcesos);
	        setJMenuBar(barraMenu);
	    }
	}

