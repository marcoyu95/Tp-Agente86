package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;
import Models.AlgoritmoPrim;
import Models.Arista;
import Models.GrafMatPeso;

public class AlgoritmoPrimTest {
	private GrafMatPeso grafoPrueba;
	
	@Test
	public void arbolExpansionPrim1Test(){
		
		grafoPrueba= new GrafMatPeso(2);
		grafoPrueba.nuevoVertice("a");
		grafoPrueba.nuevoVertice("b");
		grafoPrueba.nuevoArco("a", "b", 1);
		
		AlgoritmoPrim aristasPrim = new AlgoritmoPrim(grafoPrueba, grafoPrueba.vertices());
		ArrayList<Arista> listaVerticesDelAGM = aristasPrim.arbolGeneradorMinimoPrim();
		ArrayList<Arista> aristasPrueba = new ArrayList<Arista>();
		aristasPrueba.add(new Arista("a","b",1));
		boolean res= true;
		
		for(int i=0;i<listaVerticesDelAGM.size();i++) {
			res = res && (listaVerticesDelAGM.get(i).equals(aristasPrueba.get(i)));
		}
		assertTrue(res);
	}
	
	@Test
	public void arbolExpansionPrim2Test(){
		grafoPrueba= new GrafMatPeso(5);
		
		grafoPrueba.nuevoVertice("a");
		grafoPrueba.nuevoVertice("b");
		grafoPrueba.nuevoVertice("c");
		grafoPrueba.nuevoVertice("d");
		grafoPrueba.nuevoVertice("e");
		grafoPrueba.nuevoArco("a", "b", 8);
		grafoPrueba.nuevoArco("a", "c", 4);
		grafoPrueba.nuevoArco("a", "d", 2);
		grafoPrueba.nuevoArco("a", "e", 9);
		grafoPrueba.nuevoArco("b", "c", 6);
		grafoPrueba.nuevoArco("b", "d", 6);
		grafoPrueba.nuevoArco("b", "e", 5);
		grafoPrueba.nuevoArco("c", "d", 4);
		grafoPrueba.nuevoArco("c", "e", 1);
		grafoPrueba.nuevoArco("d", "e", 3);
		
		AlgoritmoPrim aristasPrim = new AlgoritmoPrim(grafoPrueba, grafoPrueba.vertices());
		ArrayList<Arista> listaVerticesDelAGM = aristasPrim.arbolGeneradorMinimoPrim();
		ArrayList<Arista> aristasPrueba = new ArrayList<Arista>();
		aristasPrueba.add(new Arista("a","d",2));
		aristasPrueba.add(new Arista("d","e",3));
		aristasPrueba.add(new Arista("e","c",1));
		aristasPrueba.add(new Arista("e","b",5));
		
		boolean res= true;
		
		for(int i=0;i<listaVerticesDelAGM.size();i++) {
			res = res && (listaVerticesDelAGM.get(i).equals(aristasPrueba.get(i)));
		}
		assertTrue(res);
		
	}
	
	@Test
	public void arbolExpansionPrim3Test(){
		grafoPrueba= new GrafMatPeso(7);
		
		grafoPrueba.nuevoVertice("a");
		grafoPrueba.nuevoVertice("b");
		grafoPrueba.nuevoVertice("c");
		grafoPrueba.nuevoVertice("d");
		grafoPrueba.nuevoVertice("e");
		grafoPrueba.nuevoVertice("f");
		grafoPrueba.nuevoVertice("g");
		grafoPrueba.nuevoArco("a", "b", 10);
		grafoPrueba.nuevoArco("a", "c", 2);
		grafoPrueba.nuevoArco("a", "d", 10);
		grafoPrueba.nuevoArco("a", "e", 13);
		grafoPrueba.nuevoArco("a", "f", 3);
		grafoPrueba.nuevoArco("a", "g", 17);
		grafoPrueba.nuevoArco("b", "c", 10);
		grafoPrueba.nuevoArco("b", "d", 10);
		grafoPrueba.nuevoArco("b", "e", 10);
		grafoPrueba.nuevoArco("b", "f", 10);
		grafoPrueba.nuevoArco("b", "g", 1);
		grafoPrueba.nuevoArco("c", "d", 10);
		grafoPrueba.nuevoArco("c", "e", 2);
		grafoPrueba.nuevoArco("c", "f", 6);
		grafoPrueba.nuevoArco("c", "g", 16);
		grafoPrueba.nuevoArco("d", "e", 4);
		grafoPrueba.nuevoArco("d", "f", 19);
		grafoPrueba.nuevoArco("d", "g", 8);
		grafoPrueba.nuevoArco("e", "f", 2);
		grafoPrueba.nuevoArco("e", "g", 13);
		grafoPrueba.nuevoArco("f", "g", 15);
				
		AlgoritmoPrim aristasPrim = new AlgoritmoPrim(grafoPrueba, grafoPrueba.vertices());
		ArrayList<Arista> listaVerticesDelAGM = aristasPrim.arbolGeneradorMinimoPrim();
		ArrayList<Arista> aristasPrueba = new ArrayList<Arista>();
		aristasPrueba.add(new Arista("a","c",2));
		aristasPrueba.add(new Arista("c","e",2));
		aristasPrueba.add(new Arista("e","f",2));
		aristasPrueba.add(new Arista("e","d",4));
		aristasPrueba.add(new Arista("d","g",8));
		aristasPrueba.add(new Arista("g","b",1));
		
		boolean res= true;
		
		for(int i=0;i<listaVerticesDelAGM.size();i++) {
			res = res && (listaVerticesDelAGM.get(i).equals(aristasPrueba.get(i)));
		}
		assertTrue(res);	
	}
	
}
