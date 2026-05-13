package rvt.ShowAndTicket;

public class Ticket {
    private int seat;
    private int code;
    private Show show;

    public Ticket(int seat, int code, Show show) {
        this.seat = seat;
        this.code = code;
        this.show = show;
    }

    public int getSeat() {
        return this.seat;
    }

    public int getCode() {
        return this.code;
    }

    public Show getShow() {
        return this.show;
    }
}
