package urna;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Urna {
	private boolean candidato_flag = false;
	private boolean voto_flag = false;
	private Vector<Candidato> candidatos = new Vector<Candidato>();
	private int brancos = 0;
	private int nulos = 0;
	
    public static void main(String[] args) throws ClassNotFoundException, IOException {
    	new Urna();
    }
    
    public void printMenu(){
        System.out.println("Escolha sua opcao:");
        System.out.println("1 - Votar");
        System.out.println("2 - Votar branco");
        System.out.println("3 - Votar nulo");
        System.out.println("4 - Carregar candidatos");
        System.out.println("5 - Finalizar as votacoes da urna e enviar ao servidor");
        System.out.println("6 - Sair");
    }
    
    public void sendMessage(Serializable msg, ObjectOutputStream output) throws IOException {
        output.writeObject(msg);
    }
    
    @SuppressWarnings("unchecked")
	public Urna() throws IOException, ClassNotFoundException {
    	this.printMenu();
    	Scanner scanner = new Scanner(System.in);
        String line = "";
        while(!(line = scanner.nextLine()).equals("6")) {
            
        	// Votar
        	if(line.equals("1")) { 
            	if(this.candidato_flag == true){
            		System.out.println("Digite o codigo do candidato"); 
                	line = scanner.nextLine();
                	this.candidatos.get(Integer.parseInt(line)).incVotos();
                	
                	// agora tem pelo menos um voto
                	this.voto_flag = true;
            	} 
            	// Se nao tiver carregado os candidatos ainda
            	else {
            		System.out.println("Primeiro carregue os candidatos digitando 4!\n");
            	}
            
            	System.out.println("Voto realizado com sucesso!");
        	} 
        	// Votar branco
        	else if(line.equals("2")){
            	this.brancos++;
            	System.out.println("Voto realizado com sucesso!");
            	
            } 
        	// Votar nulo
        	else if(line.equals("3")){
            	this.nulos++;
            	System.out.println("Voto realizado com sucesso!");
            } 
        	// Pegar candidatos do servidor
        	else if(line.equals("4")){
            	
            	Socket socket = new Socket("localhost", 3333);
                
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            	
                output.writeObject("999");
                
                while(true) {
                    try {
                        this.candidatos = (Vector<Candidato>)input.readObject();
                        for(int i = 0; i < this.candidatos.size(); i++){
                        	
                        	// Imprime os dados e as parciais do candidato
                        	System.out.println(this.candidatos.get(i).toString());
                        	
                        	// Zera o numero de votos para contabilizar os votos soh dessa urna
                        	this.candidatos.get(i).setNum_votos(0);
                        }
                        break;
                    } catch (IOException | ClassNotFoundException ex) {
                        break;
                    }
                }
                
                this.candidato_flag = true;
                
                output.close();
                input.close();
                socket.close();
                
                System.out.println("Cadidatos recuperados com sucesso!");
                 
            } 
        	// Atualizar servidor com votos dessa urna
        	else if(line.equals("5")){
        		if(this.candidato_flag == true){
	            	Socket socket = new Socket("localhost", 3333);
	                
	                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
	                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
	            	
	            	output.writeObject("888");
	            	this.sendMessage(candidatos, output);
	            	
	            	output.writeObject(this.brancos);
	            	output.writeObject(this.nulos);
	           
	            	output.close();
	                input.close();
	                socket.close();
	                
	                // Zera os votos dos candidatos
	                for(int i = 0; i < this.candidatos.size(); i++){
	                	this.candidatos.get(i).setNum_votos(0);
	                }

	                this.brancos = 0;
	                this.nulos = 0;
        		} else {
        			System.out.println("Vote em pelo menos um candidato!\n");
        		}
        		
        		System.out.println("Urna contabilizada com sucesso!");
            }
            this.printMenu();
        	
        }
        scanner.close();
    }
    
}
