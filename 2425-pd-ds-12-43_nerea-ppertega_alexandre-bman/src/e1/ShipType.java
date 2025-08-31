package e1;

public enum ShipType {
    DE ("Destroyer Escorts"),
    DD ("Destroyers"),
    CL ("Light Cruise Ships"),
    AV ("Hydroplane Carriers"),
    CA ("Heavy Cruise Ships"),
    CV ("Aircraft Carriers"),
    BB ("Battleships");

    private final String name;

    ShipType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
