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
	
	public Candidato(int codigo_votacao, String nome_cadidato, String partido, int num_votos){
		this.codigo_votacao = codigo_votacao;
		this.nome_candidato = nome_cadidato;
		this.partido = partido;
	}
	
	public String toString(){
		String data = new String();
		data = "Candidato: " + this.nome_candidato + "\n";
		data += "Partido: " + this.partido + "\n";
		data += "Votos: " + this.num_votos + "\n\n";
		return data;
	}
	
	
}
