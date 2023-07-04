package dev.oxoo2a.sim4da.termination;

import dev.oxoo2a.sim4da.Message;
import dev.oxoo2a.sim4da.Network;
import dev.oxoo2a.sim4da.Node;
import dev.oxoo2a.sim4da.termination.E2_Main;

import java.util.Random;

public class TerminatingNode extends Node {

    private boolean active = false;
    Random rand = new Random();
    private int counter = 0;
    public TerminatingNode(int my_id) {
        super(my_id);
    }

    @Override
    protected void main() {

        Message m = new Message();

        m.add("counter", 0);
            sendUnicast(generateRandomNumber(E2_Main.NUM_OF_ACTORS, myId), m);

        while (true){
            ++counter;
            System.out.println("Node " + myId +  " Loop Count: " + counter);



            if(active){
                double r = rand.nextDouble();
                if(r < E2_Main.PROBABILITY) {

                    m = new Message();
                    m.add("test", "test");
                    int receiverId = generateRandomNumber(E2_Main.NUM_OF_ACTORS, myId);
                    sendUnicast(receiverId, m);
                    System.out.println("Node " + myId+ " sending Message to " + receiverId);
                }else{
                    System.err.println("Node " + myId+ " missed the Chance");
                }
                active= false;
                System.out.println("Node " + myId+ " turned INACTIVE");
            }

            Network.Message m_raw = receive();
            if(m_raw == null) {
                System.out.println("node " + myId+ " dies.");

            }else{
                System.out.println("Node "+ myId+ ": ACTIVE");
                active = true;
            }
        }
/*
        m.add("counter", ++counter);
        int receiverId = generateRandomNumber(E2_Main.NUM_OF_ACTORS, myId);
        System.out.println("Node " + myId + " sending initial Message to " + receiverId);
        sendUnicast(receiverId, m);



        while(true){
            System.out.println("lopp for node " + myId);


            if(active) {
                double r = rand.nextDouble();
                if(r <= E2_Main.PROBABILITY){


                    m.add("counter", ++counter);
                    receiverId = generateRandomNumber(E2_Main.NUM_OF_ACTORS, myId);


                    System.out.println("Node " + myId + " is active, probability matched and sending to " + receiverId);

                    sendUnicast(receiverId, m);
                }else{
                    System.out.println("Node " + myId + " did NOT send next message");
                }
                active = false;
            }else{
                Network.Message m_raw = receive();
                if(m_raw==null){
                    System.out.println("Node "+ myId+ " is not receiving");
                }else{
                    active = true;
                    System.out.println("Node " + myId +" has received something!");
                    m = Message.fromJson(m_raw.payload);
                    System.out.print("Node " + myId + " printing message: ");
                    m.getMap().forEach((k,v) -> {
                        System.out.print(k+": " + v);
                    });
                    System.out.println();
                }
            }
        }

 */
    }


    public int generateRandomNumber(int n, int exclude){
        int randomNumber;
        do{
            randomNumber = rand.nextInt(n);
        }while(randomNumber == exclude);

        return randomNumber;
    }
}
