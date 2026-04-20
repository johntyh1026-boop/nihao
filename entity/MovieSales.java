package entity;
public class MovieSales {
    private String movieTitle;
    private int ticketsSold;

    public MovieSales(String movieTitle, int ticketsSold) {
        this.movieTitle = movieTitle;
        this.ticketsSold = ticketsSold;
    }

    public String getMovieTitle() { return movieTitle; }
    public int getTicketsSold() { return ticketsSold; }
}