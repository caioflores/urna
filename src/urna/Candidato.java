package urna;

public class Candidato {
	private int codigo_votacao;
	private String nome_candidato;
	private String partido;
	private int num_votos;
	
	public Candidato(int codigo_votacao, String nome_cadidato, String partido, int num_votos){
		this.codigo_votacao = codigo_votacao;
		this.nome_candidato = nome_cadidato;
		this.partido = partido;
		this.num_votos = num_votos;
	}
	
	public int getCodigo_votacao() {
		return codigo_votacao;
	}
	public void setCodigo_votacao(int codigo_votacao) {
		this.codigo_votacao = codigo_votacao;
	}
	public String getNome_candidato() {
		return nome_candidato;
	}
	public void setNome_candidato(String nome_candidato) {
		this.nome_candidato = nome_candidato;
	}
	public String getPartido() {
		return partido;
	}
	public void setPartido(String partido) {
		this.partido = partido;
	}
	public int getNum_votos() {
		return num_votos;
	}
	public void setNum_votos(int num_votos) {
		this.num_votos = num_votos;
	}
	
}
