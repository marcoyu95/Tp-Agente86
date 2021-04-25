package Models;

public class Arista {
	public String inicio;
	public String llegada;
	public double peso;
	
	public Arista(String a,String b,double c){
		inicio=a;
		llegada=b;
		peso=c;
	}

	@Override
	public String toString() {
		return inicio + "->" + llegada + " con peso: " + String.format("%.2f", peso);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
            return false;
        }

        if (!(obj instanceof Arista)) {
            return false;
        }
		
		Arista arista=(Arista)obj;
		
		if(arista.inicio.equals(inicio)&&(arista.llegada.equals(llegada))&&(arista.peso==peso)){
			return true;
		}
		if(arista.inicio.equals(llegada)&&(arista.llegada.equals(inicio))&&(arista.peso==peso))
			return true;
		return false;
	}
}
