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
     */
    public static void main(String[] args) throws IOException {
        Vector<FwdMessage> clients = new Vector();
        
        ServerSocket server = new ServerSocket(1234, 10);

        while (true) {
            System.out.println("Aguardando conexoes...");
            Socket socket = server.accept();
            System.out.println("Conectado!");

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            FwdMessage client = new FwdMessage(output, input, clients);
            clients.add(client);
            
            Thread t = new Thread(client);
            t.start();

        }
    }
}
