package urna;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Client {
	
	private Vector<Candidato> candidatos;
	private Integer nulos = 0;
	private Integer brancos = 0;
	
	public Client(Vector<Candidato> candidatos, Integer nulos, Integer brancos){
		this.candidatos = candidatos;
		this.nulos = nulos;
		this.brancos = brancos;
	}
	
	public void nuloIcrement() {
		this.nulos++;
	}
	
	public void brancoIncrement() {
		this.brancos++;
	}
		
    public void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 1234);
        
        // Vector<Candidato> candidatos = new Vector<Candidato>();
        
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        
        System.out.println("Escolha sua opcao:");
        System.out.println("1 - Votar");
        System.out.println("2 - Votar branco");
        System.out.println("3 - Votar nulo");
        System.out.println("4 - Carregar candidatos");
        System.out.println("5 - Finalizar as votações da urna e enviar ao servidor");
        
        ReadMessage reader = new ReadMessage(input);
        Thread t = new Thread(reader);
        t.start();
        
        Scanner scanner = new Scanner(System.in);
        String line = "";
        while(!(line = scanner.nextLine()).equals("5")) {
            if(line.equals("1")) { 
            	System.out.println("Qual o seu candidato?"); 
            	line = scanner.nextLine();
            }
            
            else if(line.equals("2")) {
            	this.brancoIncrement();
            }
            
            else if(line.equals("3")) {
            	this.nuloIcrement();
            }
            
            else if(line.equals("4")) {
            	this.candidatos = (Vector<Candidato>)input.readObject();
            	for(Candidato c: this.candidatos) {
            		System.out.println(c.getCodigo_votacao() + " - " + c.getNome_candidato() + " - " + c.getPartido());
            	}
            }
            
        	output.writeObject(line);
        }
        scanner.close();
        input.close();
        output.close();
        socket.close();
    }
    
}
