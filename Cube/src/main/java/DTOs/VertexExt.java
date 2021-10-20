package DTOs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder

public class VertexExt extends Vertex {
    ArrayList<String> name;
    ArrayList<ArrayList<Move>> setup;
}
