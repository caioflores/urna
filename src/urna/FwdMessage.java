package urna;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class FwdMessage implements Runnable {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Vector<FwdMessage> clients;

    public FwdMessage(ObjectOutputStream output, ObjectInputStream input, Vector<FwdMessage> clients) {
        this.output = output;
        this.input = input;
        this.clients = clients;
    }

    public void sendMessage(String msg) throws IOException {
        output.writeObject(msg);
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                String msg = (String)input.readObject();
                this.sendMessage(msg);
                
            } catch (IOException ex) {
                clients.remove(this);
                
                try {
                    input.close();
                    output.close();
                } catch (IOException ex1) {
                    
                }
                break;
            } catch (ClassNotFoundException ex) {
                break;
            }
        }
    }
}
