package Visuals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VentanaDeCarga extends JDialog {
	
	public JFileChooser seleccionar;
	public JLabel lblNombre;
	public JTextField textFieldNombreIngresado;
	public JButton btnNewButton;
	public JPanel panel;
	public File archivo;
	public byte[] imagen;
	public JLabel lblFoto;
	public JButton btnCargarAgente;
	public boolean fotoCargada;
	public boolean agenteValido;

	public VentanaDeCarga(java.awt.Frame parent, boolean modal, ArrayList<String> _listaNombreAgentes) {
        super(parent, modal);
        seleccionar = new JFileChooser();
		fotoCargada = false;
		agenteValido = false;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Cargar nuevo agente");
		setBounds(300, 100, 652, 509);
		getContentPane().setLayout(null);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(10, 21, 66, 25);
		getContentPane().add(lblNombre);
		
		textFieldNombreIngresado = new JTextField();
		textFieldNombreIngresado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldNombreIngresado.setBounds(72, 23, 171, 25);
		getContentPane().add(textFieldNombreIngresado);
		textFieldNombreIngresado.setColumns(10);
		
		panel = new JPanel();
		panel.setBounds(10, 57, 616, 402);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblFoto = new JLabel("");
		lblFoto.setBounds(0, 0, 616, 402);
		panel.add(lblFoto);
				
		btnNewButton = new JButton("Seleccionar foto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fotoCargada=false;
				if(seleccionar.showDialog(null,null)==JFileChooser.APPROVE_OPTION) {
					archivo=seleccionar.getSelectedFile();
						if(archivo.getName().endsWith("JPG")||archivo.getName().endsWith("jpg")) {
							imagen = AbrirArchivo(archivo);
							lblFoto.setIcon(new ImageIcon(imagen));
							lblFoto.setVisible(true);
							fotoCargada=true;
							
						}else {
							JOptionPane.showMessageDialog(null, "Archivo no compatible");
						}
				}else {
					JOptionPane.showMessageDialog(null, "Foto no seleccionada");
				}
			}
		});
		
		btnNewButton.setBounds(295, 23, 144, 25);
		getContentPane().add(btnNewButton);
		
		btnCargarAgente = new JButton("Cargar agente");
		btnCargarAgente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				agenteValido=false;
				if(fotoCargada==true && textFieldNombreIngresado.getText().length()!=0) {
					
					if(_listaNombreAgentes.size()==0 || !_listaNombreAgentes.contains(textFieldNombreIngresado.getText())) {
						agenteValido=true;
						dispose();
						archivo=new File(new File ("").getAbsolutePath ()+"\\FotoAgentes\\"+textFieldNombreIngresado.getText()+".JPG");
						try {
							FileOutputStream salida = new FileOutputStream(archivo);
							salida.write(imagen);
							salida.close();
						}catch(Exception e0) {
							System.out.println("error");
						}
					}else {
						JOptionPane.showMessageDialog(null,"Ya existe el agente");
					}
				}	
			}
		});
		btnCargarAgente.setBounds(503, 23, 123, 25);
		getContentPane().add(btnCargarAgente);
        
    }
	
	protected byte[] AbrirArchivo(File rutaArchivo) {
		FileInputStream entrada;
		byte[] imagen = new byte[1024*500];
		
		try {
			entrada= new FileInputStream(rutaArchivo);
			entrada.read(imagen);
			
		}catch(Exception e) {
		}
		return imagen;
	}

}
