package Datos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import Models.Agente;

public class Json {
	String archivoJson;

	public Json(String ruta) {
		archivoJson = ruta;
	}

	public void setRuta(String archivo) {
		archivoJson = archivo;
	}

	public String nombreDeArchivo() {
		return archivoJson;
	}
	
	@SuppressWarnings("unchecked")
	public void guardarAgentes ( ArrayList <Agente> _listaAgentes) {
		JSONArray listaJsonAgente = new JSONArray();
		
		for (int i = 0; i < _listaAgentes.size(); i++) {
			
			JSONObject objetoAgente = new JSONObject();
			
			objetoAgente.put("nombre:", _listaAgentes.get(i).nombreClave);
			objetoAgente.put("latitud:", _listaAgentes.get(i).ubicacion.getLat());
			objetoAgente.put("longitud:", _listaAgentes.get(i).ubicacion.getLon());
			
			JSONArray listaJsonOf = new JSONArray();
			listaJsonOf.add(objetoAgente);
			listaJsonAgente.add(listaJsonOf);
		}

		try {
			FileWriter file = new FileWriter(archivoJson);
			file.write(listaJsonAgente.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			
		}
	}
			
	public ArrayList <Agente> cargarAgentes () {
		JSONParser parser = new JSONParser();
		ArrayList<Agente> _listaAgentes = new ArrayList<Agente>();

		try {
			Object obj = parser.parse(new FileReader(archivoJson));
			JSONArray listaJson = (JSONArray) obj;
			for (int i = 0; i < listaJson.size(); i++) {
				JSONArray listaJson1 = (JSONArray) listaJson.get(i);
				JSONObject Usuario = (JSONObject) listaJson1.get(0);
				String nombre = (String) Usuario.get("nombre:");
				Agente agente = new Agente (nombre, new Coordinate((double)Usuario.get("latitud:"), (double)Usuario.get("longitud:")));
				_listaAgentes.add(agente);
			}

		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		} catch (ParseException e) {
	
		}
		return _listaAgentes;
	}
}
