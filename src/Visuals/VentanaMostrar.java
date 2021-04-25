package Visuals;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Models.Agente;

@SuppressWarnings("serial")
public class VentanaMostrar extends JDialog {
	
	private JLabel lblNombre;
	private	JPanel panel;
	private JLabel lblMostrarFoto;
	private byte[] imagen;
	private JLabel lblUbicacion;
	
	public VentanaMostrar(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		setBounds(300, 100, 652, 509);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Agente");
		getContentPane().setLayout(null);
		
		lblNombre = new JLabel("Nombre: ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(10, 11, 375, 19);
		getContentPane().add(lblNombre);
		
		panel = new JPanel();
		panel.setBounds(10, 86, 616, 373);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblMostrarFoto = new JLabel("");
		lblMostrarFoto.setBounds(0, 0, 616, 373);
		panel.add(lblMostrarFoto);
		
		lblUbicacion = new JLabel("Ubicacion: ");
		lblUbicacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUbicacion.setBounds(10, 42, 472, 18);
		getContentPane().add(lblUbicacion);

	}
	
	protected byte[] AbrirArchivo(File rutaArchivo) {
		FileInputStream entrada;
		byte[] imagen = new byte[1024*100];
		
		try {
			entrada= new FileInputStream(rutaArchivo);
			entrada.read(imagen);
			
		}catch(Exception e) {
		}
		return imagen;
	}

	public void mostrarAgente(Agente agente) {
		lblNombre.setText("Nombre: "+agente.nombreClave);
		lblUbicacion.setText("Ubicacion: ["+agente.ubicacion.getLat()+","+agente.ubicacion.getLon()+"]");
		imagen = AbrirArchivo(new File(new File ("").getAbsolutePath ()+"\\FotoAgentes\\"+agente.nombreClave+".JPG"));//rutaImagen
		lblMostrarFoto.setIcon(new ImageIcon(imagen));
	}

}
