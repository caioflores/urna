package urna;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadMessage implements Runnable{
    private ObjectInputStream input;

    public ReadMessage(ObjectInputStream input) {
        this.input = input;
    }

    @Override
    public void run() {
        while(true) {
            try {
                String msg = (String) input.readObject();
                System.out.println(msg);
            } catch (IOException | ClassNotFoundException ex) {
                break;
            }
        }
    }
}
