package entity;

public class Movie {
    private int id;
    private String title;
    private int durationInMin;
    
    //constructer
    public Movie(int id,String title, int durationInMin){
        this.id = id;
        this.title = title;
        this.durationInMin = durationInMin;
    }

    //getter
    public int getid(){
        return this.id;
    }
    public String getDisplayid(){
        return String.format("MV%03d",this.id);
    }
    public String getMovieTitle(){
        return this.title;
    }

    public int getDurationInMin(){
        return this.durationInMin;
    }


    //setter
    public void setMovieTitle(String title){
        this.title = title;
    }

    public void setDurationInMin(int timeInMin){
        this.durationInMin = timeInMin;
    }

    public String toString(){
        return String.format("ID: %s\nMovie: %s\nDuration: %d Min\n",getDisplayid(),getMovieTitle(),getDurationInMin());
    }

}
