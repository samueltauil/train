package br.train.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * User: samuel (Samuel Tauil)
 * Date: 10/4/12
 * Time: 7:57 PM
 */
public class Graph {

   private Map<Vertex, Vertex[]> adjList = new HashMap<Vertex, Vertex[]>();

   public Map<Vertex, Vertex[]> getAdjList(){
    return this.adjList;
   }

   public String getDistance(Vertex ... route) {

       Map<Vertex, Vertex[]> adjList = getAdjList();

       int total = 0;
       int counter = 0;
       for (int i = 0; i < route.length; i++) {
           Vertex[] vertexes = adjList.get(route[i]);

           for (Vertex vertexAdj : vertexes) {

               if(i+1 != route.length){
                    if(vertexAdj.equals(route[i + 1])){
                         total += vertexAdj.distance;
                         counter++;
                         break;
                    }
               }
           }

       }
      if(counter + 1 != route.length) {
          return "NO SUCH ROUTE";
      }
       return String.valueOf(total);
   }

    public String getTrips(Vertex ... route) throws CloneNotSupportedException {

        Vertex start = route[0];
        Vertex target = route[1];

        int white = 0;
        int grey = 1;
        int black = 2;
        
        Map<Vertex, Vertex[]> adjList = getAdjList();

        Stack<Vertex> stack = new Stack<Vertex>();
        stack.push(start);

        LinkedList<Vertex> routeGuess = new LinkedList<Vertex>();
        Map<Integer, LinkedList<Vertex>> result = new HashMap<Integer, LinkedList<Vertex>>();

        int i = 0;
        while (!stack.empty()) {
            Vertex popVertex = stack.pop();
            routeGuess.add(popVertex);
            popVertex.setColor(grey);

                if (popVertex.equals(target) && start.getColor() != grey){
                    LinkedList<Vertex> clone = (LinkedList<Vertex>)routeGuess.clone();

                    result.put(i++, clone);
                    for (Vertex guess : routeGuess) {
                        guess.setColor(white);
                    }
                    routeGuess.clear();
                    continue;
                }
            for (Vertex v : adjList.get(popVertex)) {
                if (v.getColor() == white){
                       stack.push(v);
                }
            }
            popVertex.setColor(black);
            start = stack.get(0);
        }
        return result.toString();
    }

}