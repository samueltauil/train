package br.train.main;

import br.train.graph.Graph;
import br.train.graph.Vertex;

/**
 * User: samuel (Samuel Tauil)
 * Date: 10/4/12
 * Time: 7:56 PM
 */
public class Main {


    public static void main(String[] args) throws CloneNotSupportedException {

        Graph g = new Graph();

        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("E");

        g.getAdjList().put(A, new Vertex[] { new Vertex("B", 5), new Vertex("D",5), new Vertex("E",7) });
        g.getAdjList().put(B, new Vertex[] { new Vertex("C", 4) });
        g.getAdjList().put(C, new Vertex[] { new Vertex("D", 8), new Vertex("E",2) });
        g.getAdjList().put(D, new Vertex[] { new Vertex("C", 8), new Vertex("E",6) });
        g.getAdjList().put(E, new Vertex[] { new Vertex("B", 3) });

        Vertex[] exerciseOne = new Vertex[] { A, B, C };
        System.out.println( g.getDistance(exerciseOne) );

        Vertex[] exerciseTwo = new Vertex[] { A, D };
        System.out.println( g.getDistance(exerciseTwo) );

        Vertex[] exerciseThree = new Vertex[] { A, D, C };
        System.out.println( g.getDistance(exerciseThree) );

        Vertex[] exerciseFour = new Vertex[] { A, E, B, C, D };
        System.out.println( g.getDistance(exerciseFour) );

        Vertex[] exerciseFive = new Vertex[] { A, E, D };
        System.out.println( g.getDistance(exerciseFive) );

        Vertex[] exerciseSix = new Vertex[] { C, C };
        System.out.println( g.getTrips(exerciseSix));
    }
}
