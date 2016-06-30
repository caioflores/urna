package urna;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Servidor {

    private Vector<Candidato> candidatos = new Vector<Candidato>();
    private int brancos = 0;
    private int nulos = 0;
    public int getBrancos() {
		return brancos;
	}

	public void setBrancos(int brancos) {
		this.brancos = brancos;
	}

	public int getNulos() {
		return nulos;
	}

	public void setNulos(int nulos) {
		this.nulos = nulos;
	}

	/**
     * @param args the command line arguments
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Servidor();
    	
    }
    
    public Servidor() throws IOException, ClassNotFoundException{
    	
        // Porta 40004, 10 conexoes
        ServerSocket server = new ServerSocket(40004, 10);
        
        candidatos.add(new Candidato(0, "Jose Augusto", "PT", 0));
        candidatos.add(new Candidato(1, "Matheus da Silva", "PSDB", 0));
        candidatos.add(new Candidato(2, "Nelson Santos", "PSol", 0));
        candidatos.add(new Candidato(3, "Lurdez Menezes", "PMDB", 0));    

        while (true) {
            System.out.println("Aguardando conexoes...");
            Socket socket = server.accept();
            System.out.println("Conectado!");

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            Transferidor client = new Transferidor(output, input, this);
            
            Thread t = new Thread(client);
            t.start();

        }
  
    }
    
    public Candidato getCandidato(int i) {
    	return this.candidatos.get(i);
    }
    
    public int getCandidatosSize() {
    	return this.candidatos.size();
    }
    
    public Vector<Candidato> getCandidatos(){
    	return this.candidatos;
    }
}
