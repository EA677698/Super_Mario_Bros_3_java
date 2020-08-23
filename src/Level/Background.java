package Level;

public enum Background {


    AQUA_BACKGROUND(1),
    BLUE_BACKGROUND(3),
    PINK_BACKGROUND(2),
    BLACK_BACKGROUND(5),
    ICE_BACKGROUND(6),
    CARAMEL_BACKGROUND(4),
    PORTAGE_BACKGROUND(0);

    private int INDEX;
    Background(int URL){
        this.INDEX = URL;
    }

    public int getIndex() {
        return INDEX;
    }
}
