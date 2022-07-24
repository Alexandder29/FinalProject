package service;

public class Location {

    private Long id;
    private String destination; //numele destinatiei
    private String description; //scurta descriere a locului de vizitat
    private String season; //sezonul/perioada de vizitat
    private int cost; //costul aproximativ necesar indeplinirii sejurului in euro, pentru 2 persoane
    private boolean visited; //daca s-a indeplinit sejurul
    private String seatClass; //categoria clasei de zbor

    public Location(String destination, String description, String season, String seatClass, int cost, boolean visited) {
        this.destination = destination;
        this.description = description;
        this.season = season;
        this.cost = cost;
        this.visited = visited;
        this.seatClass = seatClass;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public String getDescription() {
        return description;
    }

    public String getSeason() {
        return season;
    }

    public int getCost() {
        return cost;
    }

    public boolean isVisited() {
        return visited;
    }

    public String getSeatClass() {
        return seatClass;
    }

    @Override
    public String toString() {
        return "Location{" +
                "destination='" + destination + '\'' +
                ", description='" + description + '\'' +
                ", season=" + season +
                ", cost=" + cost +
                ", visited=" + visited +
                ", seatClass=" + seatClass +
                '}';
    }
}