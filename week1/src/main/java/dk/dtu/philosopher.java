package dk.dtu;

import org.jspace.ActualField;
import org.jspace.RemoteSpace;
import org.jspace.Space;

public class Philosopher {
    
    public static void main(String[] args){
        int port = 31145;
        int philosophers = 8;
        String host = "localhost";
        System.out.println("Start");
        try {
            String URL =  "tcp://" + host + ":" + port + "/board?conn";
            Space board = new RemoteSpace(URL);
            System.out.println("Remoted in");
            for(int i=0; i<philosophers;i++){
                philosopher(board, i);
            }
            

        } catch (Exception e) {
        }
    }
    public static void philosopher(Space board, int me){
        while(true){
            try{
                System.out.println("Tried to get ticket");
                board.get(new ActualField("ticket"));
                System.out.println("got ticked");
                board.get(new ActualField("fork"),new ActualField(me));
                board.get(new ActualField("fork"),new ActualField(me+1));
                System.out.println("nom nom nom");
                board.put("fork",me);
                board.put("fork",me+1);
                board.put("ticket");

            }catch(InterruptedException e){

            }
        }

    }
}
