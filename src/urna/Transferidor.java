package urna;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class Transferidor implements Runnable {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Servidor server;

    public Transferidor(ObjectOutputStream output, ObjectInputStream input, Servidor server) {
        this.output = output;
        this.input = input;
        this.server = server;
    }

    public void sendMessage(Serializable msg) throws IOException {
        output.writeObject(msg);
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                String msg = (String)input.readObject();
                int num;

                if(msg.equals("999")){	
                	this.sendMessage(this.server.getCandidatos());
                } else if(msg.equals("888")){
                	@SuppressWarnings("unchecked")
					Vector<Candidato> candidatos_aux = (Vector<Candidato>)input.readObject();
                	for(int i = 0; i < candidatos_aux.size(); i++){
                		this.server.getCandidato(i).setNum_votos(this.server.getCandidato(i).getNum_votos() + candidatos_aux.get(i).getNum_votos());
                	}
                	num = (int)input.readObject();
                	this.server.setBrancos(num);
                	
                	num = (int)input.readObject();
                	this.server.setNulos(num);
                	
                }

                
            } catch (IOException ex) {
                //ex.printStackTrace();
                try {
                    input.close();
                    output.close();
                } catch (IOException ex1) {
                    //ex1.printStackTrace();
                }
                break;
            } catch (ClassNotFoundException ex) {
                //ex.printStackTrace();
            	break;
            }
        }
    }
}
