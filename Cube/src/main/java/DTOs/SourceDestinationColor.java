package DTOs;

public class SourceDestinationColor {
    int sourceSide;
    int destinationSide;
    char fieldColor;

    public SourceDestinationColor(int sourceSide, char fieldColor){
        this.sourceSide = sourceSide;
        this.fieldColor = fieldColor;
    }

    public SourceDestinationColor(int sourceSide, int destinationSide,char fieldColor){
        this.sourceSide = sourceSide;
        this.destinationSide = destinationSide;
        this.fieldColor = fieldColor;
    }

    public int getDestinationSide() {
        return destinationSide;
    }

    public char getFieldColor() {
        return fieldColor;
    }

    public int getSourceSide() {
        return sourceSide;
    }

    public void setDestinationSide(int destinationSide) {
        this.destinationSide = destinationSide;
    }

    public void setFieldColor(char fieldColor) {
        this.fieldColor = fieldColor;
    }

    public void setSourceSide(int sourceSide) {
        this.sourceSide = sourceSide;
    }
}
