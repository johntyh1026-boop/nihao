package entity;

public class Seat {
    private int seatId;
    private int hallId;
    private String seatRow;
    private int seatNumber;
    private String seatType;

    public Seat(int seatId, int hallId, char rowLetter, int seatNumber, String seatType) {
        this.seatId = seatId;
        this.hallId = hallId;
        this.seatRow = String.valueOf(rowLetter);
        this.seatNumber = seatNumber;
        this.seatType = seatType;
    }

    public int getSeatId() {
        return seatId;
    }

    public String getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(String seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    public int getHallId() {
        return hallId;
    }
    public void setHallId(int hallId) {
        this.hallId = hallId;
    }
    public String getSeatType() {
        return seatType;
    }
    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }
}
