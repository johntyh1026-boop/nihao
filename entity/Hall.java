package entity;

public class Hall {
    private int hallId;
    private String hallType;
    private int totalSeats;//for database query
    public Hall(int hallId, String hallType) {
        this.hallId = hallId;
        this.hallType = hallType;
    }
    //for database query
    public Hall(int hallId, String hallType, int totalSeats) {
        this.hallId = hallId;
        this.hallType = hallType;
        this.totalSeats = totalSeats;
    }

    public int getHallId() {
        return hallId;
    }
    public String getDisplayId(){
        return String.format("HALL-%03d", hallId);
    }

    public String getHallType() {
        return hallType;
    }


    public void setHallType(String hallType) {
        this.hallType = hallType;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

}
