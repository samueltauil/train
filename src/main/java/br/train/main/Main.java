package br.train.main;

import br.train.graph.Graph;
import br.train.graph.Vertex;

/**
 * User: samuel (Samuel Tauil)
 * Date: 10/4/12
 * Time: 7:56 PM
 */
public class Main {


    public static void main(String[] args) {

        Graph g = new Graph();

        g.getAdjList().put(new Vertex("A"), new Vertex[] { new Vertex("B", 5), new Vertex("D",5), new Vertex("E",7) });
        g.getAdjList().put(new Vertex("B"), new Vertex[] { new Vertex("C", 4) });
        g.getAdjList().put(new Vertex("C"), new Vertex[] { new Vertex("D", 8), new Vertex("E",2) });
        g.getAdjList().put(new Vertex("D"), new Vertex[] { new Vertex("C", 8), new Vertex("E",6) });
        g.getAdjList().put(new Vertex("E"), new Vertex[] { new Vertex("B", 3) });


        Vertex[] exerciseOne = new Vertex[] { new Vertex("A"), new Vertex("B"), new Vertex("C") };
        System.out.println( g.getDistance(exerciseOne) );

        Vertex[] exerciseTwo = new Vertex[] { new Vertex("A"), new Vertex("D")};
        System.out.println( g.getDistance(exerciseTwo) );

        Vertex[] exerciseThree = new Vertex[] { new Vertex("A"), new Vertex("D"), new Vertex("C")};
        System.out.println( g.getDistance(exerciseThree) );

        Vertex[] exerciseFour = new Vertex[] { new Vertex("A"), new Vertex("E"), new Vertex("B"), new Vertex("C"), new Vertex("D")};
        System.out.println( g.getDistance(exerciseFour) );

        Vertex[] exerciseFive = new Vertex[] { new Vertex("A"), new Vertex("E"), new Vertex("D")};
        System.out.println( g.getDistance(exerciseFive) );

        Vertex[] exerciseSix = new Vertex[] { new Vertex("C"), new Vertex("C")};
        System.out.println( g.getTrips(exerciseSix));
    }
}
