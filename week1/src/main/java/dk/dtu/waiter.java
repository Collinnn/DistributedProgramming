package dk.dtu;

import org.jspace.ActualField;
import org.jspace.SequentialSpace;
import org.jspace.Space;
import org.jspace.SpaceRepository;

public class Waiter {
    public static void main(String[] args) {
        int port = 31145;
        int philosopher = 8;
        String URI = "tcp://localhost:" + port + "/?conn";

        SpaceRepository repository = new SpaceRepository();
        
        repository.addGate(URI);
        Space board = new SequentialSpace();
        repository.add("board",board);


        for (int i = 0; i < philosopher; i ++) {
            try {
                board.put("fork", i);
                System.out.println("Waiter put fork " + i + " on the table.");
            } catch (InterruptedException e) {}
        }
        for(int i = 0; i <philosopher-1;i++){
            try {
                board.put("ticket");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Waiter done.");
        // keep the tuple space open
        try {
            board.get(new ActualField("done"));
        } catch (InterruptedException e) {}
        

    }

}
