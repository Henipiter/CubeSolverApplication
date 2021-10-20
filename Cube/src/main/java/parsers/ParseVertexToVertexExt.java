package parsers;

import DTOs.Vertex;
import DTOs.VertexExt;

import java.util.ArrayList;

public class ParseVertexToVertexExt {

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


}

