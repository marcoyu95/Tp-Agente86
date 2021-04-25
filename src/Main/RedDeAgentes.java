package Main;

import java.util.ArrayList;
import Datos.Json;
import Models.Agente;
import Visuals.VentanaPrincipal;

public class RedDeAgentes {
	public static VentanaPrincipal window;
	public static Json json;
	
	public static void main(String[] args) {
		json = new Json("agentes.json");
		window = new VentanaPrincipal();
		window.cargarAgentes(json.cargarAgentes());
		window.setVisible(true);
	}

	public static void guardarAgentes(ArrayList<Agente> lista) {
		json.guardarAgentes(lista);
	}

}
