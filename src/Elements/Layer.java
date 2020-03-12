package Elements;

public enum Layer {

    FRONT_LAYER,
    BACK_LAYER,
    MIDDLE_LAYER,
    NONE;

    public static Layer valuesOf(String string){
        switch (string){
            case "FRONT_LAYER":
                return FRONT_LAYER;
            case "BACK_LAYER":
                return BACK_LAYER;
            case "MIDDLE_LAYER":
                return MIDDLE_LAYER;
            default: return NONE;
        }
    }

}
