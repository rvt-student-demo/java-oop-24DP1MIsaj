package rvt.ShowAndTicket;

public class Show {
    private String movie;
    private String time;

    public Show(String movie, String time) {
        this.movie = movie;
        this.time = time;
    }

    public String getMovie() {
        return this.movie;
    }

    public String getTime() {
        return this.time;
    }
}

