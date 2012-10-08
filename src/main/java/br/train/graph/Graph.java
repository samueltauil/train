package br.train.graph;

import java.util.*;
import java.util.concurrent.DelayQueue;

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

    //not working
    public String getTripsEqual(int stops, Vertex ... route) throws CloneNotSupportedException {

        Vertex start = route[0];
        Vertex target = route[1];

        int white = 0;
        int grey = 1;
        int black = 2;
        int counter = 0;

        Map<Vertex, Vertex[]> adjList = getAdjList();

        Stack<Vertex> stack = new Stack<Vertex>();
        stack.push(start);

        LinkedList<Vertex> later = new LinkedList<Vertex>();    //backtracking before last popped stack vertex

        LinkedList<Vertex> routeGuess = new LinkedList<Vertex>();
        Map<Integer, LinkedList<Vertex>> result = new HashMap<Integer, LinkedList<Vertex>>();

        int i = 0;
        while (!stack.empty()) {
            Vertex popVertex = stack.pop();
            popVertex.setColor(grey);

            if (popVertex.equals(target)  ){
                routeGuess.addAll(0, later);
            }
            routeGuess.add(popVertex);
            counter++;

            if (popVertex.equals(target)){

                LinkedList<Vertex> clone = (LinkedList<Vertex>)routeGuess.clone(); //cloning to avoid reference color change
                later.add(routeGuess.get(i));

                for (Vertex guess : routeGuess) {
                    guess.setColor(white);
                }
                result.put(i++, clone);
                routeGuess.clear();
                continue;
            }

            for (Vertex v : adjList.get(popVertex)) {
                if (v.getColor() == white){
                    stack.push(v);
                }
            }
            popVertex.setColor(black);
        }

        int solution = 0;

        for (LinkedList<Vertex> vertexes : result.values()) {
            if (vertexes.size() == stops + 1 ) {
                solution++;
            }
        }
        return String.valueOf(solution);
    }


    public String getTripsMax(int stops, Vertex ... route) throws CloneNotSupportedException {

        Vertex start = route[0];
        Vertex target = route[1];

        int white = 0;
        int grey = 1;
        int black = 2;

        Map<Vertex, Vertex[]> adjList = getAdjList();

        Stack<Vertex> stack = new Stack<Vertex>();
        stack.push(start);

        LinkedList<Vertex> later = new LinkedList<Vertex>();    //backtracking before last popped stack vertex

        LinkedList<Vertex> routeGuess = new LinkedList<Vertex>();
        Map<Integer, LinkedList<Vertex>> result = new HashMap<Integer, LinkedList<Vertex>>();

        int i = 0;
        while (!stack.empty()) {
            Vertex popVertex = stack.pop();
            popVertex.setColor(grey);

            if (popVertex.equals(target) && start.getColor() != grey){
                    routeGuess.addAll(0, later);         //this not works for exercise seven cause doesn't support repeated vertexes
            }
            routeGuess.add(popVertex);


                if (popVertex.equals(target) && start.getColor() != grey){

                    LinkedList<Vertex> clone = (LinkedList<Vertex>)routeGuess.clone(); //cloning to avoid reference color change
                    later.add(routeGuess.get(i));

                    for (Vertex guess : routeGuess) {
                        guess.setColor(white);
                    }
                    result.put(i++, clone);
                    routeGuess.clear();
                    continue;
                }
            for (Vertex v : adjList.get(popVertex)) {
                if (v.getColor() == white){
                       stack.push(v);
                }
            }
            popVertex.setColor(black);
        }

        int solution = 0;

        for (LinkedList<Vertex> vertexes : result.values()) {
            if (vertexes.size() <= stops + 1) {
                solution++;
            }
        }
        return String.valueOf(solution);
    }



    //TODO to be analyzed and maybe used as new version of DFS

//    public void DFS() {
//        VertexState state[] = new VertexState[vertexCount];
//        for (int i = 0; i < vertexCount; i++)
//            state[i] = VertexState.White;
//        runDFS(0, state);
//    }

//    public void runDFS(int u, VertexState[] state) {
//        state[u] = VertexState.Gray;
//        for (int v = 0; v < vertexCount; v++)
//            if (isEdge(u, v) && state[v] == VertexState.White)
//                runDFS(v, state);
//        state[u] = VertexState.Black;
//    }

}