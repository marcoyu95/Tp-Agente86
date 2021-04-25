package Models;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Agente {
	public String nombreClave;
	public Coordinate ubicacion;

	public Agente(String nombre, Coordinate ubicacion) {
		this.nombreClave = nombre;
		this.ubicacion = ubicacion;
	}
	
	public double distanciaEntreAgente(Agente otroAgente) {
	
		 double radioTierra = 6371;//en kilómetros  
	     double dLat = Math.toRadians(this.ubicacion.getLat() - otroAgente.ubicacion.getLat());  
	     double dLng = Math.toRadians(this.ubicacion.getLon() - otroAgente.ubicacion.getLon()); 
	     double sindLat = Math.sin(dLat / 2);  
	     double sindLng = Math.sin(dLng / 2);  
	     double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(this.ubicacion.getLat())) * Math.cos(Math.toRadians(otroAgente.ubicacion.getLat()));  
	     double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
	     double distancia = radioTierra * va2;
	     
		return distancia;
	}
	
	@Override
	public String toString() {
		return "Agente [nombre: " + nombreClave + ", ubicacion: " + ubicacion + "]";
	}
	
}