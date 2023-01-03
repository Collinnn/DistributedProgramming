package dk.dtu;

import java.io.IOException;

import org.jspace.ActualField;
import org.jspace.RemoteSpace;
import org.jspace.Space;

public class philosopher {
    public static void main(String[] args) {    
        int philosophers = 8;
        int port = 31145; 
        int me = 0;
        String host = "localhost";
        String URI = "tcp://" + host + ":" + port + "/board?conn";
        try {
            Space board = new RemoteSpace(URI);
            for(int i=0; i<philosophers;i++){
                philosopher(board,i);
            }
        } catch (IOException e) {
            
        }
    }

    public static void philosopher(Space board, int me){
        while (true) {
            try {
                board.get(new ActualField("ticket"));
                board.get(new ActualField("fork"),new ActualField(me));
                board.get(new ActualField("fork"),new ActualField(me+1));

                System.out.println("nom nom");

                board.put("fork",me);
                board.put("fork",me+1);
                board.put(ticket);
                System.out.println("both put back");

    
            } catch (InterruptedException e) {
                System.out.println("Bruh it crashed");
            }
        }

    }
}
