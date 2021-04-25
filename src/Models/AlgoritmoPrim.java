package Models;

import java.util.ArrayList;

public class AlgoritmoPrim {
	private double[][] Pesos;
	private int n;
	private Vertice[] vertices;
	int cont;
	
	public AlgoritmoPrim(GrafMatPeso gp, Vertice[] verts) {
		n = gp.numeroDeVertices();
		Pesos = gp.getMatPeso();
		vertices = verts;
		cont=0;
	}
	
	public ArrayList<Arista> arbolGeneradorMinimoPrim() {
		ArrayList<Arista> aristasCaminoMinimo = new ArrayList<Arista>();
		double menor;
		int z;
		double[] coste = new double [n];
		int [] masCerca=new int [n];
		boolean [] w= new boolean[n];
		
		for (int i=0;i<n;i++)
			w[i]=false;
		
		w[0]=true;
		
		for(int i=1;i<n;i++) {
			coste[i]=Pesos[0][i];
			masCerca[i]=0;
		}
		
		for(int i=1;i<n;i++) {
			menor=coste[1];
			z=1;
			
			for(int j=2;j<n;j++) {
				if(coste[j]<menor){
					menor=coste[j];
					z=j;
				}
			}
//			System.out.println((++cont)+"º Pasada: Vertice: "+vertices[masCerca[z]].nomVertice()+" -->> "+"Vertice: "+vertices[z].nomVertice()+" Peso:"+coste[z]);
			aristasCaminoMinimo.add(new Arista(vertices[masCerca[z]].nomVertice(),vertices[z].nomVertice(),coste[z]));
			w[z]= true;
			coste[z]=GrafMatPeso.INFINITO;
			
			for(int j=1;j<n;j++) {
				if((Pesos[z][j]<coste[j])&& !w[j]) {
					coste[j]=Pesos[z][j];
					masCerca[j]=z;
				}				
			}			
		}
		
		return aristasCaminoMinimo;
	}
	
}
