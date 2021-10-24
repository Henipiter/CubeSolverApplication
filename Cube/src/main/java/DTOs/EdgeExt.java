package DTOs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class EdgeExt extends Edge {
    ArrayList<String> name;
    ArrayList<String> algorithm;
    ArrayList<String> setup;
}
