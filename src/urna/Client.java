package urna;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private boolean candidato_flag = false;
		
    public static void main(String[] args) throws ClassNotFoundException, IOException {
    	new Client();
    }
    
    public Client() throws IOException, ClassNotFoundException {
        
        System.out.println("Escolha sua opcao:");
        System.out.println("1 - Votar");
        System.out.println("2 - Votar branco");
        System.out.println("3 - Votar nulo");
        System.out.println("4 - Carregar candidatos");
        System.out.println("5 - Finalizar as votacoes da urna e enviar ao servidor");
        
        Scanner scanner = new Scanner(System.in);
        String line = "";
        while(!(line = scanner.nextLine()).equals("5")) {
            if(line.equals("1")) { 
            	if(this.candidato_flag == true){
                	System.out.println("Qual o seu candidato?"); 
                	line = scanner.nextLine();
            	} else {
            		System.out.println("Primeiro carregue os candidatos digitando 4!");
            	}
            } else if(line.equals("4")){
            	
            	Socket socket = new Socket("127.0.0.1", 3333);
                
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            	
                output.writeObject("999");

                while(true) {
                	System.out.println("TESTE");
                    try {
                        String msg = (String) input.readObject();
                        System.out.println(msg);
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    	break;
                    }
                }
                
                //output.close();
                //input.close();
                
                //socket.close();
                
            } else if(line.equals("5")){
            	//output.writeObject("888");
            }
            
        	
        }
        scanner.close();

    }
    
}
