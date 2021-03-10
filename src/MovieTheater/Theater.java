package MovieTheater;
import java.io.File;
import java.io.IOException;
import java.util.*;
public class Theater {
    public int total_seats;
    public int numOfRows;
    public RowSeats [] rows;
    public Theater(int total_seats, int numOfRows, RowSeats [] rows)
    {
        this.total_seats=total_seats;
        this.numOfRows=numOfRows;
        this.rows=rows;

    }
    public Boolean isEmpty(String requestId,int numberOfSeats){
        //If total seats available in theater is zero print we can't handle all the next requests coming in as the theater is full
        if(total_seats==0)
        {
            return true;
        }
        return false;
    }

    public  Boolean canAccommodate(String requestId,int numberOfSeats){
        //If total seats available in theater is less than the requested seats print that we can't handle all the request as we can't allocate the requested number of seats
        if(numberOfSeats>total_seats)
        {
           return false;
        }
        return true;
    }

    public Boolean validRequest(String requestId,int numberOfSeats)
    {
        // if the requested number of seats is less than or equal to zero print we can't handle that request as invalid number of seats requested.
    if(numberOfSeats<=0)
    {
        return false;

    }
        return true;
    }


    public Response allocateSeatsInSingleRow(String requestId,int numberOfSeats,int ind) {
        Response response = null;
        //Find the best row that can accommodate requested number of seats

            response = new Response(requestId, rows[ind].name, rows[ind].max_seats - rows[ind].available_seats + 1, rows[ind].max_seats - rows[ind].available_seats + numberOfSeats);
            for(int i=rows[ind].max_seats - rows[ind].available_seats + 1;i<=rows[ind].max_seats - rows[ind].available_seats + numberOfSeats;i++)
            {
                rows[ind].rows[i-1]=requestId;
            }
        for(int i=rows[ind].max_seats - rows[ind].available_seats + numberOfSeats + 1;i<=(rows[ind].max_seats - rows[ind].available_seats + numberOfSeats+3)&&i<=20;i++)
        {
            rows[ind].rows[i-1]="COVID-19";
        }
        rows[ind].available_seats -= (numberOfSeats + 3);
        if (rows[ind].available_seats >= 0)
                total_seats -= (numberOfSeats + 3);
            else
                total_seats -= (numberOfSeats + rows[ind].available_seats + 3);
    return response;
    }

    public Response allocateSeatsInMultipleRows(String requestId,int numberOfSeats){

        //Handle the test cases where the request cannot be accommodated in single row (ind value will be -1)
        Response response=null;
            int i=0;
            while(numberOfSeats!=0&&total_seats>0)
            {

                if(rows[i].available_seats>0)
                {

                    int available;
                    //This if else logic is required to decide if all the available seats in the row should be allocated or the remaining number of seats are less than the available and is enough to allocate the remaining number of seats
                    if(rows[i].available_seats<numberOfSeats)
                        available = rows[i].available_seats;
                    else
                        available=numberOfSeats;
                    if(response==null) {
                        response = new Response(requestId, rows[i].name, rows[i].max_seats - rows[i].available_seats + 1, rows[i].max_seats - rows[i].available_seats + available);
                        for(int j=rows[i].max_seats - rows[i].available_seats + 1;j<=20;j++)
                        {
                            rows[i].rows[j-1]=requestId;
                        }
                    }
                    else {
                        response.addRow(rows[i].name, rows[i].max_seats - rows[i].available_seats + 1, rows[i].max_seats - rows[i].available_seats + available);
                        for(int j=rows[i].max_seats - rows[i].available_seats + 1;j<=rows[i].max_seats - rows[i].available_seats + numberOfSeats;j++)
                        {
                            rows[i].rows[j-1]=requestId;
                        }
                        for(int j=rows[i].max_seats - rows[i].available_seats + numberOfSeats + 1;j<=(rows[i].max_seats - rows[i].available_seats + numberOfSeats+3)&&j<=20;j++)
                        {
                            rows[i].rows[j-1]="COVID-19";
                        }
                    }
                    rows[i].available_seats-=(available+3);
                    if(available==numberOfSeats)
                        total_seats-=(available+3);
                    else
                        total_seats-=available;
                    numberOfSeats-=available;
                }
                i++;

            }

        return response;
    }

//Function to return the best row to accommodate the seats
public int searchIndex(int seatsRequested){
        for(int j=0;j<10;j++){
            if(rows[j].available_seats>=seatsRequested)
                return j;
        }
        return -1;
}

public void printTheaterSeating(){
        for(int i=0;i<10;i++)
        {
            System.out.print(rows[i].name+"  ");
            for(int j=0;j<20;j++)
            {
                System.out.print(rows[i].rows[j]+" ");
            }
            System.out.println("");
        }
}

}