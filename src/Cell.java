public class Cell {
    private static final String freeCell = "⬜";
    private static final String shipCell = "\uD83D\uDEE5";
    private static final String haloCell = "\uD83D\uDFE6";
    private static final String damagedCell = "\uD83D\uDFE5";
    private static final String errorCell = "\uD83D\uDFE8";

    public static String getFreeCell() {
        return freeCell;
    }

    public static String getShipCell() {
        return shipCell;
    }

    public static String getHaloCell() {
        return haloCell;
    }

    public static String getDamagedCell() {
        return damagedCell;
    }

    public static String getErrorCell() {
        return errorCell;
    }
}
