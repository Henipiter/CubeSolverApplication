package DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileElement {
    int element;
    int field;
    String data;
}
