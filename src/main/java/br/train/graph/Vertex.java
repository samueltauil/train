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

    public int color = 0;

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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Vertex");
        sb.append("{value='").append(value).append('\'');
        sb.append(", distance=").append(distance);
        sb.append(", explored=").append(explored);
        sb.append(", color=").append(color);
        sb.append('}');
        return sb.toString();
    }
}
