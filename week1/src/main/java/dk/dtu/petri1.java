package dk.dtu;

import org.jspace.ActualField;
import org.jspace.SequentialSpace;
import org.jspace.Space;

public class petri1 {
    public static Space lock = new SequentialSpace();
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Startup");
        activity.init();
        new Thread(new activityA()).start();
        System.out.println("Thread1");
        new Thread(new activityB()).start();
        System.out.println("Thread2");
        new Thread(new activityC()).start();
        System.out.println("Thread3");
        new Thread(new activityD()).start();
        System.out.println("Thread4");
        
        lock.get(new ActualField("wait"));

    }
}

class activity{
    public static Space lock = new SequentialSpace();
    public static Space q0 = new SequentialSpace();
    public static Space q1 = new SequentialSpace();
    public static Space q2 = new SequentialSpace();
    public activity() throws InterruptedException{
    }
    public static void init() throws InterruptedException{
        lock.put("lock");
        q2.put("coconut");
    }
}

class activityA extends activity implements Runnable{
    
    public activityA() throws InterruptedException{
        super();
    }
    
    public void run(){
        while(true){
            try {
                lock.get(new ActualField("lock"));
                //System.out.println("We lock at A today boyz");
                if(q0.queryp(new ActualField("coconut"))!=null){
                    q0.get(new ActualField("coconut"));
                    System.out.println("We dine at A today boyz");
                    q0.put(("coconut"));
                    System.out.println("We starve at A boyz :(");                    
                }
                lock.put("lock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
} 
class activityB extends activity implements Runnable{
    
    public activityB() throws InterruptedException{
        super();
    }
    
    public void run(){
        while(true){
            try {
                lock.get(new ActualField("lock"));
                //System.out.println("We lock at B today boyz");
                if(q0.queryp(new ActualField("coconut")) !=null && q1.queryp(new ActualField("coconut")) !=null){
                    q0.get(new ActualField("coconut"));
                    q1.get(new ActualField("coconut"));
                    System.out.println("We dine at B today boyz");
                    q2.put(("coconut"));
                    System.out.println("We starve at B boyz :(");
                }
                lock.put("lock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
} 

class activityC extends activity implements Runnable{
    
    public activityC() throws InterruptedException{
        super();
    }
    
    public void run(){
        while(true){
            try {
                lock.get(new ActualField("lock"));
                //System.out.println("We lock at C today boyz");
                if(q1.queryp(new ActualField("coconut")) !=null){
                    q1.get(new ActualField("coconut"));
                    System.out.println("We dine at C today boyz");
                    q1.put(("coconut"));
                    System.out.println("We starve at C boyz :(");
                }
                lock.put("lock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
} 
class activityD extends activity implements Runnable{
    
    public activityD() throws InterruptedException{
        super();
    }
    
    public void run(){
        while(true){
            try {
                lock.get(new ActualField("lock"));
                if(q2.queryp(new ActualField("coconut")) !=null){
                    q2.get(new ActualField("coconut"));
                    System.out.println("We dine at D today boyz");
                    q0.put(("coconut"));
                    q1.put(("coconut"));
                    System.out.println("We starve at D boyz :(");
                    
                } 
                lock.put("lock");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
} 