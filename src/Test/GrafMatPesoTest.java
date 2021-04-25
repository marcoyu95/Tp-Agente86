package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import Models.GrafMatPeso;
import Models.Vertice;

public class GrafMatPesoTest {
	private GrafMatPeso grafoPrueba;
	
	@Test
	public void nuevoVerticeTest() {
		grafoPrueba= new GrafMatPeso(2);
		grafoPrueba.nuevoVertice("a");
		grafoPrueba.nuevoVertice("b");
		grafoPrueba.nuevoArco("a", "b", 1);
		assertTrue(grafoPrueba.numeroDeVertices()==2);
	}
	
	@Test
	public void verticesTest() {
		grafoPrueba= new GrafMatPeso(10);
		grafoPrueba.nuevoVertice("a");
		grafoPrueba.nuevoVertice("b");
		grafoPrueba.nuevoVertice("c");
		grafoPrueba.nuevoVertice("d");
		grafoPrueba.nuevoVertice("e");
		grafoPrueba.nuevoVertice("f");
		grafoPrueba.nuevoVertice("g");
		grafoPrueba.nuevoVertice("h");
		grafoPrueba.nuevoVertice("i");
		grafoPrueba.nuevoVertice("j");
		
		Vertice[] esperado= new Vertice[10];
		esperado[0]= new Vertice("a");
		esperado[1]= new Vertice("b");
		esperado[2]= new Vertice("c");
		esperado[3]= new Vertice("d");
		esperado[4]= new Vertice("e");
		esperado[5]= new Vertice("f");
		esperado[6]= new Vertice("g");
		esperado[7]= new Vertice("h");
		esperado[8]= new Vertice("i");
		esperado[9]= new Vertice("j");

		boolean res= true;
		res = res && grafoPrueba.numeroDeVertices()==esperado.length;
		for(int i=0;i<grafoPrueba.numeroDeVertices();i++) {
			res = res && (grafoPrueba.vertices()[i].equals(esperado[i]));
		}
		assertTrue(res);
	}
	
	
	@Test
	public void nuevoArcoTest() {
		grafoPrueba= new GrafMatPeso(2);
		grafoPrueba.nuevoVertice("b");
		grafoPrueba.nuevoVertice("a");
		grafoPrueba.nuevoArco("b", "a", 1);
		assertTrue(grafoPrueba.pesoArco("a","b")==1.0);
	}
	
	@Test
	public void agregarVerticeDuplicadoTest() {
		grafoPrueba= new GrafMatPeso(2);
		grafoPrueba.nuevoVertice("b");
		grafoPrueba.nuevoVertice("b");
		assertTrue(grafoPrueba.numeroDeVertices()==1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void agregarArcoConVerticeInexistenteTest() {
		grafoPrueba= new GrafMatPeso(2);
		String a="A";
		String b="B";
		String c="C";
		
		grafoPrueba.nuevoVertice(a);
		grafoPrueba.nuevoVertice(b);
		
		grafoPrueba.nuevoArco(a, c, 3);
	}
	
}
