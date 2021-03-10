package MovieTheater;
public class RowSeats {
    String [] rows;
    int available_seats;
    int max_seats;
    Character name;
    public RowSeats(int max_seats, Character name){
        rows=new String[max_seats];
        this.max_seats=max_seats;
        available_seats =max_seats;
        this.name=name;
    }
}
