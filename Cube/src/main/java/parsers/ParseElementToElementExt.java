package parsers;

import DTOs.Edge;
import DTOs.EdgeExt;
import DTOs.Vertex;
import DTOs.VertexExt;

import java.util.ArrayList;

public class ParseElementToElementExt {

    public static VertexExt convertToVertexExt(VertexExt vertexExt, Vertex vertex){
        if(vertexExt == null){
            vertexExt = VertexExt.builder().build();
        }
        vertexExt.setColor(vertex.getColor());
        vertexExt.setField(vertex.getField());
        vertexExt.setWall(vertex.getWall());
        return vertexExt;
    }

    public static ArrayList<VertexExt> convertToVertexExtList(ArrayList<VertexExt> vertexExtArrayList ,ArrayList<Vertex> vertexList){
        ArrayList<VertexExt> vertexExts = new ArrayList<>();
        for (int i = 0; i < vertexList.size(); i++) {
            vertexExts.add(convertToVertexExt(vertexExtArrayList.get(i), vertexList.get(i)));
        }
        return vertexExts;
    }

    public static EdgeExt convertToEdgeExt(EdgeExt edgeExt, Edge edge){
        if(edgeExt == null){
            edgeExt = EdgeExt.builder().build();
        }
        edgeExt.setColor(edge.getColor());
        edgeExt.setField(edge.getField());
        edgeExt.setWall(edge.getWall());
        return edgeExt;
    }

    public static ArrayList<EdgeExt> convertToEdgeExtList(ArrayList<EdgeExt> edgeExtArrayList , ArrayList<Edge> edgesList){
        ArrayList<EdgeExt> vertexExts = new ArrayList<>();
        for (int i = 0; i < edgesList.size(); i++) {
            vertexExts.add(convertToEdgeExt(edgeExtArrayList.get(i), edgesList.get(i)));
        }
        return vertexExts;
    }
}

