package Models;

public class GrafMatPeso {
	public static int INFINITO = 0xFFFF;
	private double [][] matPeso;
	private Vertice [] verts;
	private int numVerts;
	
	public GrafMatPeso() {
		
	}
	
	public GrafMatPeso(int mx) {
		matPeso= new double [mx][mx];
		verts = new Vertice[mx];		
		for(int i=0;i<mx;i++) {
			for(int j=0;j<mx;j++)
				matPeso[i][j] = INFINITO;
		}
		numVerts = 0;
	}
	
	public void nuevoVertice (String nom) {
		
		boolean esta = numVertice(nom)>=0;
		if(!esta) {
			Vertice v= new Vertice(nom);
			v.asigVert(numVerts);
			verts[numVerts++]=v;
		}
	}
	
	public double pesoArco(String a,String b) {
		int va,vb;
		va = numVertice(a);
		vb = numVertice(b);
		return matPeso[va][vb];
	}
	
	public int numeroDeVertices() {
		return numVerts;
	}
	
	public Vertice[] vertices() {
		return verts;
	}
	
	public void nuevoArco(String a,String b,double peso) {
		int va,vb;
		va=numVertice(a);
		vb=numVertice(b);
		
		matPeso[va][vb] = peso;
		matPeso[vb][va] = peso;

	}
	
	public int numVertice(String vs) {
		Vertice v= new Vertice (vs);
		boolean encontrado=false;
		int i=0;
		for(;(i<numVerts) && !encontrado;) {
			encontrado=verts[i].equals(v);
			if(!encontrado)
				i++;
		}
		return(i<numVerts)?i:-1;
	}
	
	public double[][] getMatPeso(){
		return matPeso;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  ");
		for(int k=0;k<verts.length;k++) {
			sb.append(verts[k].nombre+" ");
		}
		sb.append("\n");
		for(int i=0;i<matPeso.length;i++) {
			sb.append(verts[i].nombre+" ");
			for(int j=0;j<matPeso[0].length;j++) {
				if(i!=j && matPeso[i][j]<999) {
					sb.append(String.format("%.0f", matPeso[i][j])+" ");
				}else
					sb.append(0+" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
