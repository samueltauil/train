package br.train.graph;

/**
 * User: samuel (Samuel Tauil)
 * Date: 10/4/12
 * Time: 7:58 PM
 */
public class Vertex {

    public String value;

    public int distance = 0;

    public boolean explored = false;

    public Vertex(String value, int distance){
    this.value = value;
    this.distance = distance;
    }

    public Vertex(String value){
    this.value = value;
    }

    @Override
    public boolean equals(Object v){
    return ((Vertex) v).value.equals(this.value);
    }

    @Override
    public int hashCode() {
    return value != null ? value.hashCode() : 0;
    }
}
