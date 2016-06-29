package urna;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    /**
     * @param args the command line arguments
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Vector<FwdMessage> clients = new Vector<FwdMessage>();
        Vector<Candidato> candidatos = new Vector<Candidato>();
        
        ServerSocket server = new ServerSocket(1234, 10);
        
        candidatos.add(new Candidato(10, "Jos� Augusto", "PT", 0));
        candidatos.add(new Candidato(20, "Matheus da Silva", "PSDB", 0));
        candidatos.add(new Candidato(30, "Nelson Santos", "PSol", 0));
        candidatos.add(new Candidato(40, "Lurdez Menezes", "PMDB", 0));    

        while (true) {
            System.out.println("Aguardando conexoes...");
            Socket socket = server.accept();
            System.out.println("Conectado!");

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            String msg = (String)input.readObject();
             
            if(msg.equals("4")){ // Candidatos
            	output.writeObject(candidatos);
            } else if(msg.equals("5")){ // Finalizar
            	//Candidato teste = candidatos.get(0);
            	output.writeObject(candidatos);
            }
            
            FwdMessage client = new FwdMessage(output, input, clients);
            clients.add(client);
            
            Thread t = new Thread(client);
            t.start();

        }
    }
}
