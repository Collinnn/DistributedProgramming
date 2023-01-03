package dk.dtu;

import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.Space;

public class Helloworld {
    public static void main(String[] args) throws InterruptedException {
        Space inbox = new SequentialSpace();
        inbox.put("Hello World!");
        Object[] Tuple = inbox.get(new FormalField(String.class));
        System.out.println(Tuple[0]); 
    }
}
