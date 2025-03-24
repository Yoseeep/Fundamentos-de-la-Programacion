package fp.tipos;

import java.util.Objects;

import utiles.Checkers;

public class Electrodoméstico implements Comparable<Electrodoméstico>{
	private String númeroSerie;
	private String denominación;
	private Integer mesesGarantía;
	private Float importe;
	private Float iva;

	public Electrodoméstico(String númeroSerie, String denominación, Integer mesesGarantía, Float importe, Float iva) {
		this.númeroSerie = númeroSerie;
		this.denominación = denominación;
		this.mesesGarantía = mesesGarantía;
		this.importe = importe;
		this.iva = iva;
	}

	public Electrodoméstico(String númeroSerie, String denominación) {
		this.númeroSerie = númeroSerie;
		this.denominación = denominación;
		this.mesesGarantía = 0;
		this.importe = 0f;
		this.iva = 0f;
	}
	
	public Electrodoméstico(String s) {
		String[] tr = s.split("#");
		Checkers.check("La cadena no está bien formada" + s, tr.length == 5);	
		this.númeroSerie  = tr[0].trim();
		this.denominación  = tr[1].trim();
		this.mesesGarantía = Integer.valueOf(tr[2].trim());
		this.importe  = Float.valueOf(tr[3].trim());
		this.iva  = Float.valueOf(tr[4].trim());
	}

	public String getNúmeroSerie() {
		return númeroSerie;
	}

	public String getDenominación() {
		return denominación;
	}

	public Integer getMesesGarantía() {
		return mesesGarantía;
	}

	public Float getImporte() {
		return importe;
	}

	public Float getIva() {
		return iva;
	}

	public void setImporte(Float importe) {
		this.importe = importe;
	}

	public void setIva(Float iva) {
		this.iva = iva;
	}

	@Override
	public String toString() {
		return "Electrodoméstico [númeroSerie=" + númeroSerie + ", denominación=" + denominación + ", mesesGarantía="
				+ mesesGarantía + ", importe=" + importe + ", iva=" + iva + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(denominación, importe, iva, mesesGarantía, númeroSerie);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Electrodoméstico))
			return false;
		Electrodoméstico other = (Electrodoméstico) obj;
		return Objects.equals(denominación, other.denominación) && Objects.equals(importe, other.importe)
				&& Objects.equals(iva, other.iva) && Objects.equals(mesesGarantía, other.mesesGarantía)
				&& Objects.equals(númeroSerie, other.númeroSerie);
	}
	
	
	public Float importeTotal() {
		return this.importe + this.importe * (this.iva / 100); 
	}
	
	public int compareTo(Electrodoméstico e) {
		int res =  this.getNúmeroSerie().compareTo(e.getNúmeroSerie());
		
		if (res == 0) {
			res = this.getImporte().compareTo(e.getImporte());
		}
		
		return res;
	}
	
	
	

}
