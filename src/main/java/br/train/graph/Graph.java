package br.train.graph;

import java.util.*;

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

       //get adjacent list
       Map<Vertex, Vertex[]> adjList = getAdjList();

       int total = 0;
       int counter = 0;

       for (int i = 0; i < route.length; i++) {

           Vertex[] vertexes = adjList.get(route[i]); //return routes for a given node

           for (Vertex vertexAdj : vertexes) {

               if(i + 1 != route.length){  //verify if there is a vertex defined in route as adjacent
                    if(vertexAdj.equals(route[i + 1])){
                         total += vertexAdj.distance;
                         counter++;
                         break;
                    }
               }
           }

       }
      if(counter + 1 != route.length) {
          return "NO SUCH ROUTE"; //verify if don't exists any vertex defined on route as adjacent
      }
       return String.valueOf(total);
   }

    //not working, supposed to be 7
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
        stack.push(start);  //add start as root node to start searching

        LinkedList<Vertex> later = new LinkedList<Vertex>();    //backtracking before last popped stack vertex

        LinkedList<Vertex> routeGuess = new LinkedList<Vertex>();  // possible routes

        Map<Integer, LinkedList<Vertex>> result = new HashMap<Integer, LinkedList<Vertex>>();

        int i = 0;
        while (!stack.empty()) {
            Vertex popVertex = stack.pop();
            popVertex.setColor(grey); //mark this vertex as visited

            if (popVertex.equals(target) && start.getColor() != grey){ //add backtracked vertexes to possible routes
                    routeGuess.addAll(0, later);         //this not works for exercise seven cause doesn't support repeated vertexes
            }
            routeGuess.add(popVertex);


                if (popVertex.equals(target) && start.getColor() != grey){

                    LinkedList<Vertex> clone = (LinkedList<Vertex>)routeGuess.clone(); //cloning to avoid reference color change
                    later.add(routeGuess.get(i));  //save backtrack route

                    for (Vertex guess : routeGuess) {
                        guess.setColor(white);   //clean all visited vertex for this route
                    }
                    result.put(i++, clone);
                    routeGuess.clear();
                    continue;
                }
            for (Vertex v : adjList.get(popVertex)) {
                if (v.getColor() == white){   //add vertexes not visited yet to be visited
                       stack.push(v);
                }
            }
            popVertex.setColor(black); //define vertex to not be visited again
        }

        int solution = 0;

        for (LinkedList<Vertex> vertexes : result.values()) {
            if (vertexes.size() <= stops + 1) { //counts how many valid routes
                solution++;
            }
        }
        return String.valueOf(solution);
    }

    public String findBestRoute(Vertex ... route){

        Vertex start = route[0];
        Vertex end = route[1];
        LinkedList<Vertex> visited = new LinkedList<Vertex>();

        //define start vertex as root
        visited.add(start);
        Map<Integer, LinkedList<Vertex>> result = new HashMap<Integer, LinkedList<Vertex>>();

        //searchPaths decorates result to add all available routes found
        searchPaths(end, visited, result);

        return getLessDistance(result);
    }

    public String getLessDistance(Map<Integer, LinkedList<Vertex>> result){

        Stack<Integer> value = new Stack<Integer>();

        for (LinkedList<Vertex> vertexes : result.values()) {
            Integer distance = Integer.decode(getDistance(vertexes.toArray(new Vertex[vertexes.size()]))); //find distance between this route found
            value.push(distance);  //adds distance to stack to be verified
        }

        Integer pivot = value.pop();  //define a distance as pivot to be compared with all others
        for (Integer number : value) {
           if(pivot <= number) {
              value.pop();
           } else {              //verify distance
               pivot = number;
           }
        }
        return String.valueOf(pivot);
    }

    public void searchPaths(Vertex end, LinkedList<Vertex> visited, Map<Integer, LinkedList<Vertex>> result) {

        Map<Vertex, Vertex[]> adjList = getAdjList();

        Vertex[] vertexes = adjList.get(visited.getLast());  //get all adjacent vertex to given start one

        for (Vertex vertex : vertexes) {
            if (visited.contains(vertex)) {  //verify if it's already visited
                continue;
            }
            if (vertex.equals(end)) {  //if the end vertex is found this path is added to result list
                visited.add(vertex);
                addPath(visited, result);  //save path
                visited.removeLast();
                break;
            }
        }

        for (Vertex vertex : vertexes) { // recursion after visit all vertexes
            if (visited.contains(vertex) || vertex.equals(end)) {
                continue;
            }
            visited.addLast(vertex);  //add this vertex as visited and carry on the search
            searchPaths(end, visited, result);
            visited.removeLast();
        }
    }

    private void addPath(LinkedList<Vertex> visited, Map<Integer, LinkedList<Vertex>> result) {
        LinkedList<Vertex> clone = (LinkedList<Vertex>)visited.clone();  //clone to prevent remove from the result by reference
        result.put(result.size() + 1, clone);
    }









}