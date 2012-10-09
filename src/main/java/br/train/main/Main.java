package br.train.main;

import br.train.graph.Graph;
import br.train.graph.Vertex;

import java.util.LinkedList;

/**
 * User: samuel (Samuel Tauil)
 * Date: 10/4/12
 * Time: 7:56 PM
 */
public class Main {


    public static void main(String[] args) throws CloneNotSupportedException {

        Graph g = new Graph();

        //create base nodes
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("E");

        //create adjacent nodes and define those weight
        g.getAdjList().put(A, new Vertex[] { new Vertex("B", 5), new Vertex("D",5), new Vertex("E",7) });
        g.getAdjList().put(B, new Vertex[] { new Vertex("C", 4) });
        g.getAdjList().put(C, new Vertex[] { new Vertex("D", 8), new Vertex("E",2) });
        g.getAdjList().put(D, new Vertex[] { new Vertex("C", 8), new Vertex("E",6) });
        g.getAdjList().put(E, new Vertex[] { new Vertex("B", 3) });

        //define routes to exercises
        Vertex[] exerciseOne = new Vertex[] { A, B, C };
        System.out.println( "Output #1: " + g.getDistance(exerciseOne) );

        Vertex[] exerciseTwo = new Vertex[] { A, D };
        System.out.println( "Output #2: " + g.getDistance(exerciseTwo) );

        Vertex[] exerciseThree = new Vertex[] { A, D, C };
        System.out.println( "Output #3: " + g.getDistance(exerciseThree) );

        Vertex[] exerciseFour = new Vertex[] { A, E, B, C, D };
        System.out.println( "Output #4: " + g.getDistance(exerciseFour) );

        Vertex[] exerciseFive = new Vertex[] { A, E, D };
        System.out.println( "Output #5: " + g.getDistance(exerciseFive) );

        Vertex[] exerciseSix = new Vertex[] { C, C };
        System.out.println( "Output #6: " + g.getTripsMax(3, exerciseSix));

        Vertex[] exerciseSeven = new Vertex[] { A, C };
//        System.out.println( g.getTripsEqual(4, exerciseSeven));
        System.out.println("Output #7: " + "Not Completed");

        Vertex[] exerciseEight = new Vertex[] { A, C };
        System.out.println("Output #8: " + g.findBestRoute(exerciseEight) );

        //TODO Exercises 7, 9 and 10
    }
}
