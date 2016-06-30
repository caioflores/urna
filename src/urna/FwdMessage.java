package urna;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class FwdMessage implements Runnable {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Server server;
	private String msg_out;

    public FwdMessage(ObjectOutputStream output, ObjectInputStream input, Server server) {
        this.output = output;
        this.input = input;
        this.server = server;
    }

    public void sendMessage(Serializable msg) throws IOException {
        output.writeObject(msg);
    }
    
    @Override
    public void run() {
    	System.out.println("OLAR");
        while(true) {
            try {
                String msg = (String)input.readObject();
                System.out.println("OLAR2");
                
                msg_out = new String();
                
                if(msg.equals("999")){
                	
                	for(int i = 0; i < this.server.getCandidatosSize(); i++){
                		msg_out += this.server.getCandidato(i).toString();
                	}
                } else if(msg.equals("888")){
                	
                }
                this.sendMessage(msg_out);
                
            } catch (IOException ex) {
                ex.printStackTrace();
                try {
                    input.close();
                    output.close();
                } catch (IOException ex1) {
                    ex1.printStackTrace();
                }
                break;
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            	break;
            }
        }
    }
}
