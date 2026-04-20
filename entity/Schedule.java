package entity;

import java.time.LocalDateTime;

public class Schedule {
    private int scheduleId;
    private Movie movie;
    private Hall hall;        
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double Price;

    public Schedule(int scheduleId, Movie movie, Hall hall, LocalDateTime startTime, double Price) {
        this.scheduleId = scheduleId;
        this.movie = movie;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(movie.getDurationInMin()).plusMinutes(15);
        this.Price = Price;
    }
    public Schedule(int scheduleId, Movie movie, Hall hall, LocalDateTime startTime, LocalDateTime endTime, double Price) {
        this.scheduleId = scheduleId;
        this.movie = movie;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.Price = Price;
    }
    //getter
    public int getScheduleId() {
        return scheduleId;
    }

    public String getDisplayId() {
        return String.format("SCH-%04d", scheduleId);
    }

    public Movie getMovie() {
        return movie;
    }

    public Hall getHall() {
        return hall;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public double getPrice() {
        return Price;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
}