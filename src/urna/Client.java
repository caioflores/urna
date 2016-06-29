package urna;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Client {
		
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 3333);
        
        // Vector<Candidato> candidatos = new Vector<Candidato>();
        
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        
        System.out.println("Escolha sua opcao:");
        System.out.println("1 - Votar");
        System.out.println("2 - Votar branco");
        System.out.println("3 - Votar nulo");
        System.out.println("4 - Carregar candidatos");
        System.out.println("5 - Finalizar as votacoes da urna e enviar ao servidor");
        
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
            
        	output.writeObject(line);
        }
        scanner.close();
        input.close();
        output.close();
        socket.close();
    }
    
}
