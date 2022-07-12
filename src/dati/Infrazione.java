package dati;

import java.sql.Date;

public class Infrazione {
	
	private int id;
	private Date data;
	private String tipo;
	private double importo;
	private int id_auto;
	
	public Infrazione() {}

	public Infrazione(int id, Date data, String tipo, double importo, int id_auto) {
		this.id = id;
		this.data = data;
		this.tipo = tipo;
		this.importo = importo;
		this.id_auto = id_auto;
	}
	
	public Infrazione(Date data, String tipo, double importo, int id_auto) {
		this.data = data;
		this.tipo = tipo;
		this.importo = importo;
		this.id_auto = id_auto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public int getId_auto() {
		return id_auto;
	}

	public void setId_auto(int id_auto) {
		this.id_auto = id_auto;
	}
	
	@Override
	public String toString() {
		return data + " | " + tipo + " | " + importo + " | ";
	}

}
