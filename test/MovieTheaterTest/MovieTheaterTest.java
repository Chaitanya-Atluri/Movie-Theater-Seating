package MovieTheaterTest;
import java.io.IOException;

import MovieTheater.*;

public class MovieTheaterTest {

    public static Theater getEmptyTheater(){
        RowSeats [] rows=new RowSeats[10];
        // Initialize an array of RowSeats to store info about the rows from a to j
        for(int i=9; i >= 0; i--)
            rows[10-i-1]=new RowSeats(20,(char)(65+i));
        Theater theater=new Theater(200,10,rows);
    return theater;
    }

    public static void main(String args[]) throws IOException {

        System.out.println("***** TESTING ******");
        testValidRequest1();
        testValidRequest2();
        testCanAccommodate1();
        testCanAccommodate2();
        testIsEmpty1();
        testIsEmpty2();
        testAllocatedSeatsInSingleRow1();
        testAllocatedSeatsInMultipleRows1();
        testSearchIndex1();
        testSearchIndex2();
        testSearchIndex3();
        testSearchIndex4();
        testSearchIndex5();
        testAllocatedSpaces1();
        testAllocatedSpaces2();

    }
// Test if the number of seats is less than or equal to 0 i.e invalid
    private static void testValidRequest1() {
        Boolean expectedResult=false;
        Theater theater=getEmptyTheater();
        Boolean actualResult=theater.validRequest("R001",-1);
        if(actualResult==expectedResult){
            System.out.println("Test 1 Passed , Name: TestValidRequest");
        }else{
            System.out.println("Test 1 Failed , Name: TestValidRequest "+ "Actual result: "+actualResult.toString()+" Expected Result "+expectedResult.toString());
        }
    }
// Test if the number of seats is less than or equal to 0 i.e valid
    private static void testValidRequest2() {
        Boolean expectedResult=true;
        Theater theater=getEmptyTheater();
        Boolean actualResult=theater.validRequest("R001",12);
        if(actualResult==expectedResult){
            System.out.println("Test 2 Passed , Name: TestValidRequest");
        }else{
            System.out.println("Test 2 Failed , Name: TestValidRequest "+ "Actual result: "+actualResult.toString()+" Expected Result "+expectedResult.toString());
        }
    }
// Test if the number of seats requested can be accommodated in the theater
    private static void testCanAccommodate1(){
        Boolean expectedResult=false;
        Theater theater=getEmptyTheater();
        Boolean actualResult=theater.canAccommodate("R001",240);
        if(actualResult==expectedResult){
            System.out.println("Test 3 Passed , Name: TestCanAccommodate");
        }else{
            System.out.println("Test 3 Failed , Name: TestCanAccommodate "+ "Actual result: "+actualResult.toString()+" Expected Result "+expectedResult.toString());
        }
    }

// Test if the number of seats requested can be accommodated in the theater
    private static void testCanAccommodate2(){
        Boolean expectedResult=true;
        Theater theater=getEmptyTheater();
        Boolean actualResult=theater.canAccommodate("R001",20);
        if(actualResult==expectedResult){
            System.out.println("Test 4 Passed , Name: TestCanAccommodate");
        }else{
            System.out.println("Test 4 Failed , Name: TestCanAccommodate "+ "Actual result: "+actualResult.toString()+" Expected Result "+expectedResult.toString());
        }
    }

// Test if the theater is full returns true if full
    private static void testIsEmpty1(){
        Boolean expectedResult=true;
        RowSeats [] rows=new RowSeats[10];
        // Initialize an array of RowSeats to store info about the rows from a to j
        for(int i=9; i >= 0; i--)
            rows[10-i-1]=new RowSeats(20,(char)(65+i));
        Theater theater=new Theater(0,10,rows);
        Boolean actualResult=theater.isEmpty("R001",2);
        if(actualResult==expectedResult){
            System.out.println("Test 5 Passed , Name: TestIsEmpty");
        }else{
            System.out.println("Test 5 Failed , Name: TestIsEmpty "+ "Actual result: "+actualResult.toString()+" Expected Result "+expectedResult.toString());
        }
    }

    // Test if the theater is full returns false if not full
    private static void testIsEmpty2(){
        Boolean expectedResult=false;
        Theater theater=getEmptyTheater();
        Boolean actualResult=theater.isEmpty("R001",2);
        if(actualResult==expectedResult){
            System.out.println("Test 6 Passed , Name: TestIsEmpty");
        }else{
            System.out.println("Test 6 Failed , Name: TestIsEmpty "+ "Actual result: "+actualResult.toString()+" Expected Result "+expectedResult.toString());
        }
    }

    //Allocate requested number of seats in a single Row , returns arrangement
    private static void testAllocatedSeatsInSingleRow1(){
        String expectedResult="R001 J1,J2 ";
        Theater theater=getEmptyTheater();

        Response actualResult=theater.allocateSeatsInSingleRow("R001",2,0);
        if(actualResult.toString().equals(expectedResult)){
            System.out.println("Test 7 Passed , Name: TestAllocatedSeatsInSingleRow");
        }else{
            System.out.println("Test 7 Failed , Name: TestAllocatedSeatsInSingleRow "+ "Actual result: "+actualResult.toString()+" Expected Result "+expectedResult);
        }
    }

    //Allocate requested number of seats in a Multiple Rows as Requested seats are not available in a single row , returns arrangement
    private static void testAllocatedSeatsInMultipleRows1(){
        String expectedResult="R001 J1,J2,J3,J4,J5,J6,J7,J8,J9,J10,J11,J12,J13,J14,J15,J16,J17,J18,J19,J20 I1,I2 ";
        Theater theater=getEmptyTheater();

        Response actualResult=theater.allocateSeatsInMultipleRows("R001",22);
        if(actualResult.toString().equals(expectedResult)){
            System.out.println("Test 8 Passed , Name: TestAllocatedSeatsInMultipleRows");
        }else{
            System.out.println("Test 8 Failed , Name: TestAllocatedSeatsInMultipleRows "+ "Actual result: "+actualResult.toString()+" Expected Result "+expectedResult);
        }
    }
//Search for the index where the available seats are greater than or equal to the requested seats,returns index if possible else returns -1
    private static void testSearchIndex1(){
        int expectedResult=7;
        RowSeats [] rows=new RowSeats[10];
        // Initialize an array of RowSeats to store info about the rows from a to j
        rows[9]=new RowSeats(2,'A');
        rows[8]=new RowSeats(4,'B');
        rows[7]=new RowSeats(15,'C');
        rows[6]=new RowSeats(3,'D');
        rows[5]=new RowSeats(6,'E');
        rows[4]=new RowSeats(8,'F');
        rows[3]=new RowSeats(1,'G');
        rows[2]=new RowSeats(9,'H');
        rows[1]=new RowSeats(2,'I');
        rows[0]=new RowSeats(1,'J');
        Theater theater=new Theater(200,10,rows);


        int actualResult=theater.searchIndex(10);
        if(actualResult==expectedResult){
            System.out.println("Test 9 Passed , Name: TestSearchIndex");
        }else{
            System.out.println("Test 9 Failed , Name: TestSearchIndex "+ "Actual result: "+actualResult+" Expected Result "+expectedResult);
        }
    }

//Search for the index where the available seats are greater than or equal to the requested seats in an empty theater,returns index if possible else returns -1
    private static void testSearchIndex2(){
        int expectedResult=0;
        Theater theater=getEmptyTheater();
        int actualResult=theater.searchIndex(10);
        if(actualResult==expectedResult){
            System.out.println("Test 10 Passed , Name: TestSearchIndex");
        }else{
            System.out.println("Test 10 Failed , Name: TestSearchIndex "+ "Actual result: "+actualResult+" Expected Result "+expectedResult);
        }
    }
//Search for the index where the available seats are greater than or equal to the requested seats in an empty Theater,returns index if possible else returns -1
    private static void testSearchIndex3(){
        int expectedResult=-1;
        Theater theater=getEmptyTheater();
        int actualResult=theater.searchIndex(22);
        if(actualResult==expectedResult){
            System.out.println("Test 11 Passed , Name: TestSearchIndex");
        }else{
            System.out.println("Test 11 Failed , Name: TestSearchIndex "+ "Actual result: "+actualResult+" Expected Result "+expectedResult);
        }
    }
//Search for the index where the available seats are greater than or equal to the requested seats in a non-empty theater,returns index if possible else returns -1
    private static void testSearchIndex4(){
        int expectedResult=1;
        RowSeats [] rows=new RowSeats[10];
        // Initialize an array of RowSeats to store info about the rows from a to j
        rows[9]=new RowSeats(2,'A');
        rows[8]=new RowSeats(4,'B');
        rows[7]=new RowSeats(15,'C');
        rows[6]=new RowSeats(3,'D');
        rows[5]=new RowSeats(6,'E');
        rows[4]=new RowSeats(8,'F');
        rows[3]=new RowSeats(1,'G');
        rows[2]=new RowSeats(9,'H');
        rows[1]=new RowSeats(2,'I');
        rows[0]=new RowSeats(1,'J');
        Theater theater=new Theater(200,10,rows);


        int actualResult=theater.searchIndex(2);
        if(actualResult==expectedResult){
            System.out.println("Test 12 Passed , Name: TestSearchIndex");
        }else{
            System.out.println("Test 12 Failed , Name: TestSearchIndex "+ "Actual result: "+actualResult+" Expected Result "+expectedResult);
        }
    }
//Search for the top most row where the available seats are greater than or equal to the requested seats,returns index if possible else returns -1
    private static void testSearchIndex5(){
        int expectedResult=2;
        RowSeats [] rows=new RowSeats[10];
        // Initialize an array of RowSeats to store info about the rows from a to j
        rows[9]=new RowSeats(2,'A');
        rows[8]=new RowSeats(4,'B');
        rows[7]=new RowSeats(9,'C');
        rows[6]=new RowSeats(3,'D');
        rows[5]=new RowSeats(6,'E');
        rows[4]=new RowSeats(8,'F');
        rows[3]=new RowSeats(1,'G');
        rows[2]=new RowSeats(9,'H');
        rows[1]=new RowSeats(2,'I');
        rows[0]=new RowSeats(1,'J');
        Theater theater=new Theater(200,2,rows);


        int actualResult=theater.searchIndex(9);
        if(actualResult==expectedResult){
            System.out.println("Test 13 Passed , Name: TestSearchIndex");
        }else{
            System.out.println("Test 13 Failed , Name: TestSearchIndex "+ "Actual result: "+actualResult+" Expected Result "+expectedResult);
        }
    }
    //Test to check after assigning a request proper spaces are left to maintain social distancing
    private static void testAllocatedSpaces1(){
        int expectedResult=180;
        Theater theater=getEmptyTheater();
        theater.allocateSeatsInSingleRow("R001",17,0);
        int actualResult=theater.total_seats;
        if(actualResult==expectedResult){
            System.out.println("Test 14 Passed , Name: TestAllocatedSeatsInSingleRow");
        }else{
            System.out.println("Test 14 Failed , Name: TestAllocatedSeatsInSingleRow "+ "Actual result: "+actualResult+" Expected Result "+expectedResult);
        }
    }

    //Test to check after assigning a request proper spaces are left to maintain social distancing and to check that only remaining seats at the end are left instead of leaving 3 seats
    private static void testAllocatedSpaces2(){
        int expectedResult=180;
        Theater theater=getEmptyTheater();
        theater.allocateSeatsInSingleRow("R001",18,0);
        int actualResult=theater.total_seats;
        if(actualResult==expectedResult){
            System.out.println("Test 15 Passed , Name: TestAllocatedSeatsInSingleRow");
        }else{
            System.out.println("Test 15 Failed , Name: TestAllocatedSeatsInSingleRow "+ "Actual result: "+actualResult+" Expected Result "+expectedResult);
        }
    }



//    private void checkFirstCustomerSeat() throws IOException {
//        // testTheater.printLayout();
//        testTheater.main("test1.txt");
//        List<String> list = new ArrayList<>();
//        list.add("E1");
//        list.add("E2");
//        list.add("E3");
//        list.add("E4");
//        list.add("E5");
//        if (testTheater.getResults().get("R002").equals(list)) {
//            System.out
//                    .println("Test 2 Passed : 5 Seats successfully reserved for first customer at the middle row.");
//        } else {
//            System.out
//                    .println("Test 2 Failed: Failed to reserve seats for first customer at the middle row.");
//        }
//    }
//
//    public void checkConsecutiveSeats() throws IOException {
//        List<String> list = new ArrayList<>();
//        list.add("R002");
//        list.add("R002");
//        list.add("R002");
//        list.add("R002");
//        list.add("R002");
//        if (testTheater.main("test1.txt")) {
//            System.out
//                    .println("Test 3 Passed : 5 Consecutive seats successfully reserved for first customer row E.");
//
//        } else {
//            System.out
//                    .println("Test 3 Failed : Failed to reserve consecutive seats.");
//        }
//
//    }
//
//    public void checkInsufficientSeats() throws IOException {
//        testTheater.main("test1.txt");
//        if (testTheater.getNumberOfSeats() > 0) {
//            System.out
//                    .println("Test 4 Passed : Failed to allocate seats when the request was greater than the available seats.");
//        } else {
//            System.out
//                    .println(" Test 4 Failed : Allocated as many seta as possible.");
//        }
//    }
//
//    public void checkGroupUnableToAccomodateInRow() {
//        int result = testTheater.bookSeat("R004 24");
//        if (result == 0) {
//            System.out
//                    .println("Test 5 Passed : Successfully allocated seats to a large group that could not be accomodated in a row.");
//        } else {
//            System.out
//                    .println("Test 5 Failed : Failed to allocate seats to a large group.");
//        }
//    }
//
//    private void checkGroupAccomodationOfSizeLargerSize(){
//        List<String> list = new ArrayList<>();
//        list.add("F1");
//        list.add("F2");
//        list.add("F3");
//        list.add("F4");
//        list.add("F5");
//        list.add("F6");
//        list.add("F7");
//        list.add("F8");
//        list.add("F9");
//        list.add("F10");
//        list.add("F11");
//        list.add("F12");
//        list.add("F13");
//        list.add("F14");
//        list.add("F15");
//        list.add("F16");
//        list.add("F17");
//        list.add("F18");
//        list.add("F19");
//        list.add("F20");
//        list.add("E6");
//        list.add("E7");
//        list.add("E8");
//        list.add("E9");
//        if(testTheater.getResults().get("R004").equals(list)){
//            System.out.println("Test 6 Passed : Successfully accomodated a group that could not be accomodated in a single row.");
//        }else{
//            System.out.println(" Test 6 Failed : Failed to accomodate the group that could not be accomodated in a single row.");
//        }
//    }


}


