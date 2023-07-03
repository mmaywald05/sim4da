package dev.oxoo2a.sim4da.example;

import dev.oxoo2a.sim4da.Node;
import dev.oxoo2a.sim4da.Simulator;

public class E2_Main {


    public static final int NUM_OF_ACTORS = 10;
    public static final double PROBABILITY = 0.95f;


    public static void main(String[] args) {


        Simulator s = Simulator.createDefaultSimulator(NUM_OF_ACTORS);


        for (int id = 0; id< NUM_OF_ACTORS; id++) {
            Node n = new TerminatingNode(id);
            s.attachNode(id,n);
        }

        try{
            s.runSimulation(1);
        }catch (InstantiationException e){
            e.printStackTrace();
            System.err.println("Instantiation failed. Time to investigate.");
        }




    }
}
