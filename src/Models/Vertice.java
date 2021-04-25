package Models;

public class Vertice {
	String nombre;
	int numVertice;
	
	public Vertice(String x) {
		nombre=x;
		numVertice=-1;
	}
	
	public String nomVertice() {
		return nombre;
	}
	
	public boolean equals(Vertice n) {
		return nombre.equals(n.nombre);
	}
	
	public void asigVert(int n) {
		numVertice=n;
	}
	@Override
	public String toString() {
		return nombre;
	}
	
}
