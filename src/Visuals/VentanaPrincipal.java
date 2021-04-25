package Visuals;

import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import Main.RedDeAgentes;
import Models.Agente;
import Models.AlgoritmoPrim;
import Models.Arista;
import Models.GrafMatPeso;

@SuppressWarnings({ "unused", "unchecked", "serial", "rawtypes"})
public class VentanaPrincipal extends JFrame{
	
	private ArrayList<Arista>_listaVerticesDelArbolGeneradorMinimo;
	private JPanel panelControles;
	private ArrayList<MapPolygonImpl> _losCaminos;
	private ArrayList<Agente> _losAgentes;
	private ArrayList<String> _listaNombreAgentes;
	private GrafMatPeso grafo;
	private MapPolygonImpl camino;
	private JPanel panelMapa;
	private JMapViewer mapa;
	private JButton btnGuardarAgentes;
	private JButton btnArbGeneradMin;
	private JLabel lblPesoTotal;
	private JLabel lblAristaPesoMinimo;
	private JLabel lblAristaPesoMaximo;
	private JLabel lblPromedioDeAristas;
	private JLabel lblDesviacionEstandar;
	private JButton btnEliminarAgente;
	private JButton btnMostrarAgente;
	private VentanaDeCarga ventanaDeCarga;
	private JComboBox comboBoxDeAgentes;
	private VentanaMostrar ventanaMostrar;
	private JComboBox comboBoxEliminar;
	private JLabel lblEstadisticas;
	
public VentanaPrincipal() {
	_listaVerticesDelArbolGeneradorMinimo = new ArrayList<Arista>();
	_losAgentes = new ArrayList<Agente>();
	_listaNombreAgentes = new ArrayList<String>();
	_losCaminos = new ArrayList<MapPolygonImpl>();
	grafo= new GrafMatPeso();
	ventanaDeCarga = new VentanaDeCarga(this,true,_listaNombreAgentes);
	ventanaMostrar = new VentanaMostrar(this,true);
	
	setBounds(100, 100, 800, 550);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	getContentPane().setLayout(null);
	setTitle("Red de agentes");
	
	panelMapa = new JPanel();
	panelMapa.setBounds(10, 11, 437, 489);
	
	panelControles = new JPanel();
	panelControles.setBounds(450, 11, 324, 489);
	panelControles.setLayout(null);
	
	getContentPane().add(panelMapa);
	getContentPane().add(panelControles);
	
	mapa = new JMapViewer();
	mapa.setZoomControlsVisible(false);
	mapa.setDisplayPosition(new Coordinate(-34.550, -58.7000), 10);
	mapa.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			
		if (e.getButton() == MouseEvent.BUTTON1){
			ventanaDeCarga.setVisible(true);
			if(ventanaDeCarga.agenteValido==true && ventanaDeCarga.fotoCargada==true) {//si no es un agente repetido y cargo la foto
				Coordinate markeradd = (Coordinate)mapa.getPosition(e.getPoint());
				mapa.addMapMarker(new MapMarkerDot(ventanaDeCarga.textFieldNombreIngresado.getText(), markeradd));
			    _losAgentes.add(new Agente(ventanaDeCarga.textFieldNombreIngresado.getText(),markeradd));
			    _listaNombreAgentes.add(ventanaDeCarga.textFieldNombreIngresado.getText());
			    comboBoxDeAgentes.setModel(new DefaultComboBoxModel(_listaNombreAgentes.toArray()));
			    comboBoxEliminar.setModel(new DefaultComboBoxModel(_listaNombreAgentes.toArray()));
			}
			ventanaDeCarga.textFieldNombreIngresado.setText("");
		    ventanaDeCarga.lblFoto.setVisible(false);
		    ventanaDeCarga.agenteValido=false;
		    ventanaDeCarga.fotoCargada=false;
		}
		}
	});
	panelMapa.add(mapa);
	
	lblPesoTotal = new JLabel();
	lblPesoTotal.setBounds(9, 76, 305, 14);
	panelControles.add(lblPesoTotal);
	
	lblAristaPesoMinimo = new JLabel();
	lblAristaPesoMinimo.setBounds(9, 101, 305, 14);
	panelControles.add(lblAristaPesoMinimo);
	
	lblAristaPesoMaximo = new JLabel();
	lblAristaPesoMaximo.setBounds(9, 126, 304, 14);
	panelControles.add(lblAristaPesoMaximo);
	
	btnArbGeneradMin = new JButton("Arbol Generador Minimo");
	btnArbGeneradMin.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		if(_losAgentes.size()>1) {
			for (MapPolygonImpl c: _losCaminos)//elimino el agm dibujado antes
				mapa.removeMapPolygon(c);
						
			grafo = new GrafMatPeso(_losAgentes.size());
			
			for(int i=0;i<_losAgentes.size();i++) {//creo los vertices
				grafo.nuevoVertice(_losAgentes.get(i).nombreClave);
			}
			
			for(int i=0;i<_losAgentes.size();i++) {//creo los arcos
				for(int j=0;j<_losAgentes.size();j++) {
					for(int k=0;k<_losAgentes.size();k++) {
						if(!_losAgentes.get(j).nombreClave.equals(_losAgentes.get(k).nombreClave))
							grafo.nuevoArco(_losAgentes.get(j).nombreClave, _losAgentes.get(k).nombreClave,
									_losAgentes.get(j).distanciaEntreAgente(_losAgentes.get(k)));
					}
				}
			}
			AlgoritmoPrim _arbol = new AlgoritmoPrim(grafo,grafo.vertices());
			_listaVerticesDelArbolGeneradorMinimo=_arbol.arbolGeneradorMinimoPrim();
			dibujarArbolGeneradorMinimo(_listaVerticesDelArbolGeneradorMinimo);
			asignarPesoTotal(_listaVerticesDelArbolGeneradorMinimo);
			asignarAristaMinima(_listaVerticesDelArbolGeneradorMinimo);
			asignarAristaMaxima(_listaVerticesDelArbolGeneradorMinimo);
			asignarAristaPromedio(_listaVerticesDelArbolGeneradorMinimo);
			asignarDesviacionEstandar(_listaVerticesDelArbolGeneradorMinimo);
			lblEstadisticas.setText("Estadisticas del arbol generador minimo:");
		}
	}
	});
	btnArbGeneradMin.setBounds(10, 11, 195, 23);
	panelControles.add(btnArbGeneradMin);
	
	btnEliminarAgente = new JButton("Eliminar agente");
	btnEliminarAgente.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if ((String) comboBoxEliminar.getSelectedItem() != null) {
				int j = buscarPosicionDeVertice((String) comboBoxEliminar.getSelectedItem());
				String nombre = _losAgentes.get(j).nombreClave;
				Coordinate markeradd;
				mapa.removeAllMapMarkers();
				mapa.removeMapMarker(new MapMarkerDot(_losAgentes.get(j).ubicacion));
				_losAgentes.remove(j);
				_listaNombreAgentes.remove(j);
				comboBoxDeAgentes.setModel(new DefaultComboBoxModel(_listaNombreAgentes.toArray()));
				comboBoxEliminar.setModel(new DefaultComboBoxModel(_listaNombreAgentes.toArray()));

				for (MapPolygonImpl c : _losCaminos) // elimino el agm dibujado antes
					mapa.removeMapPolygon(c);
				
				for (int i = 0; i < _losAgentes.size(); i++) {//dibujo los agentes nuevamente
					markeradd = _losAgentes.get(i).ubicacion;
					mapa.addMapMarker(new MapMarkerDot(_losAgentes.get(i).nombreClave, _losAgentes.get(i).ubicacion));
				}
				JOptionPane.showMessageDialog(null, "Agente "+nombre+" eliminado");
			}
		}
	});
	btnEliminarAgente.setBounds(175, 260, 127, 23);
	panelControles.add(btnEliminarAgente);
	
	lblPromedioDeAristas = new JLabel("");
	lblPromedioDeAristas.setBounds(9, 151, 304, 14);
	panelControles.add(lblPromedioDeAristas);
	
	btnMostrarAgente = new JButton("Mostrar agente");
	btnMostrarAgente.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if((String)comboBoxDeAgentes.getSelectedItem()!=null) {
				ventanaMostrar.mostrarAgente(_losAgentes.get(buscarPosicionDeVertice((String)comboBoxDeAgentes.getSelectedItem())));
				ventanaMostrar.setVisible(true);
			}
		}
	});
	btnMostrarAgente.setBounds(175, 339, 127, 23);
	panelControles.add(btnMostrarAgente);
	
	btnGuardarAgentes = new JButton("Actualizar lista agentes");
	btnGuardarAgentes.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			RedDeAgentes.guardarAgentes(_losAgentes);
			JOptionPane.showMessageDialog(null, "Lista de agentes actualizada");
		}
	});
	btnGuardarAgentes.setBounds(9, 305, 293, 23);
	panelControles.add(btnGuardarAgentes);
	
	comboBoxDeAgentes = new JComboBox();
	comboBoxDeAgentes.setFont(new Font("Tahoma", Font.PLAIN, 15));
	comboBoxDeAgentes.setBounds(9, 340, 139, 23);
	panelControles.add(comboBoxDeAgentes);
	
	comboBoxEliminar = new JComboBox();
	comboBoxEliminar.setFont(new Font("Tahoma", Font.PLAIN, 15));
	comboBoxEliminar.setBounds(9, 259, 139, 23);
	panelControles.add(comboBoxEliminar);
	
	lblDesviacionEstandar = new JLabel("");
	lblDesviacionEstandar.setBounds(9, 176, 293, 14);
	panelControles.add(lblDesviacionEstandar);
	
	lblEstadisticas = new JLabel("");
	lblEstadisticas.setBounds(9, 45, 293, 14);
	panelControles.add(lblEstadisticas);

}

	protected void asignarDesviacionEstandar(ArrayList<Arista> _lista) {
		if(_lista.size()>1) {
			double promedio = 0;
			double desviacion = 0;
			for(int i=0;i<_lista.size();i++) {
				promedio+=_lista.get(i).peso;
			}
			promedio=promedio/_lista.size();
			
			for(int i=0;i<_lista.size();i++) {
				desviacion+=Math.pow(_lista.get(i).peso-promedio,2);
			}
			
			lblDesviacionEstandar.setText("Desviacion estandar: "+String.format("%.2f",Math.sqrt(desviacion/(_lista.size()-1))));
		}
		else {
			lblDesviacionEstandar.setText("");
		}
	}
	
	protected boolean agenteRepetido(String nombre) {
		for (Agente agente : _losAgentes) {
			if(agente.nombreClave.equals(nombre)) {
				return true;
			}
		}
		return false;
	}	

	protected void asignarAristaPromedio(ArrayList<Arista> _lista) {
		double promedio = 0;
		for(int i=0;i<_lista.size();i++) {
			promedio+=_lista.get(i).peso;
		}
		promedio=promedio/_lista.size();
		lblPromedioDeAristas.setText("Costo de la arista promedio: "+String.format("%.2f", promedio));
	}
	
	protected void asignarAristaMaxima(ArrayList<Arista> _lista) {
		double maximo = _lista.get(0).peso;
		int pos=0;
		for(int i=0;i<_lista.size();i++) {
			if(_lista.get(i).peso>maximo) {
				maximo=_lista.get(i).peso;
				pos=i;
			}
		}
		lblAristaPesoMaximo.setText("Arista peso maximo: "+_lista.get(pos));
	}

	protected void asignarAristaMinima(ArrayList<Arista> _lista) {
		double minimo=_lista.get(0).peso;
		int pos=0;
		for(int i=0;i<_lista.size();i++) {
			if(_lista.get(i).peso<minimo) {
				minimo=_lista.get(i).peso;
				pos=i;
			}
		}
		lblAristaPesoMinimo.setText("Arista peso minimo: "+_lista.get(pos));
	}

	protected void asignarPesoTotal(ArrayList<Arista> _lista) {
		double acum=0;
		for(int i=0;i<_lista.size();i++) {
			acum+=_lista.get(i).peso;
		}
		lblPesoTotal.setText("Peso total: "+String.format("%.2f", acum));
	}

	protected void dibujarArbolGeneradorMinimo(ArrayList<Arista> _lista) {//dibujo en el mapa el agm
		int j,k;
		for(int i=0;i<_lista.size();i++) {
			j=buscarPosicionDeVertice(_lista.get(i).inicio);
			k=buscarPosicionDeVertice(_lista.get(i).llegada);
			camino= new MapPolygonImpl(_losAgentes.get(j).ubicacion, _losAgentes.get(k).ubicacion, _losAgentes.get(j).ubicacion);
			_losCaminos.add(camino);
			mapa.addMapPolygon(camino);
		}
	}

	protected int buscarPosicionDeVertice(String nombreVertice) {
		for(int i=0;i<_losAgentes.size();i++){
			if(_losAgentes.get(i).nombreClave.equals(nombreVertice))
				return i;
		}
		return -1;
	}

	public void cargarAgentes(ArrayList<Agente> cargarAgentes) {//cargo los agentes ya guardados
		for (Agente agente : cargarAgentes) {
			mapa.addMapMarker(new MapMarkerDot(agente.nombreClave, agente.ubicacion));
		    _losAgentes.add(new Agente(agente.nombreClave,agente.ubicacion));
		    _listaNombreAgentes.add(agente.nombreClave);
		}
		 comboBoxDeAgentes.setModel(new DefaultComboBoxModel(_listaNombreAgentes.toArray()));
		 comboBoxEliminar.setModel(new DefaultComboBoxModel(_listaNombreAgentes.toArray()));
	}
}