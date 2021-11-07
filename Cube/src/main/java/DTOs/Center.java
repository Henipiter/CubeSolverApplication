package DTOs;

import lombok.Data;

@Data
public class Center {
    char[] color;

    public Center(char[] color) {
        this.color = color;
    }
}
