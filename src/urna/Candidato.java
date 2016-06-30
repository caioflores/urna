package urna;

import java.io.Serializable;

public class Candidato implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo_votacao;
	private String nome_candidato;
	private String partido;
	private int num_votos;
	
	public void setNum_votos(int num_votos) {
		this.num_votos = num_votos;
	}

	public int getNum_votos() {
		return num_votos;
	}

	public void incVotos() {
		this.num_votos++;
	}

	public Candidato(int codigo_votacao, String nome_candidato, String partido, int num_votos){
		this.codigo_votacao = codigo_votacao;
		this.nome_candidato = nome_candidato;
		this.partido = partido;
		this.num_votos = num_votos;
	}
	
	public String toString(){
		String data = new String();
		data = "Candidato: " + this.nome_candidato + "\n";
		data += "Codigo: " + this.codigo_votacao + "\n";
		data += "Partido: " + this.partido + "\n";
		data += "Votos: " + this.num_votos + "\n\n";
		return data;
	}
	
	
}
