package br.train.graph;

import java.util.HashMap;
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

    public String getTrips(Vertex ... route) {

        Vertex start = route[0];
        Vertex target = route[1];
        
        Map<Vertex, Vertex[]> adjList = getAdjList();


        return null;
    }

    public Vertex[] visit(Vertex root) {
        Vertex[] vertexes = getAdjList().get(root);
        return vertexes;
    }


}