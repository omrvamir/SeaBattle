public class Player extends Sea {
    private String name;
    private Sea sea;

    public Player(String name) {
        this.name = name;
        Sea sea = new Sea();
    }

    public String getName() {
        return name;
    }


}
