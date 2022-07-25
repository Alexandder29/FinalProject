package service;

public class Location {

    private Long id;
    private String destination;
    private String description;
    private String season;
    private int cost;
    private boolean isVisited;
    private String seatClass;

    public Location(String destination, String description, String season, String seatClass, int cost, boolean isVisited) {
        this.destination = destination;
        this.description = description;
        this.season = season;
        this.cost = cost;
        this.isVisited = isVisited;
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
        return isVisited;
    }

    public String getSeatClass() {
        return seatClass;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", description='" + description + '\'' +
                ", season='" + season + '\'' +
                ", cost=" + cost +
                ", isVisited=" + isVisited +
                ", seatClass='" + seatClass + '\'' +
                '}';
    }
}