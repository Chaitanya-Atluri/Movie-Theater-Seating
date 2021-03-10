package MovieTheater;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Runner {
    public static void main(String[] args) throws IOException {
        int numOfRows = 10;
        RowSeats[] rows = new RowSeats[numOfRows];
        // Initialize an array of RowSeats to store info about the rows from a to j
            for (int i = numOfRows - 1; i >= 0; i--)
                rows[numOfRows - i - 1] = new RowSeats(20, (char) (65 + i));
            Theater theater = new Theater(200, numOfRows, rows);
            // Read the input file and store each requests in array of strings
            LinkedHashMap<String, Response> lhm = new LinkedHashMap<>();
            String path = args[0];
            Scanner sc = new Scanner(new File(path));
            List<String> lines = new ArrayList<String>();
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
            String[] requests = lines.toArray(new String[0]);


            //Open file writer to write outputs to the output file
            OutputWriter.initializeFileWriter();

            // Handle requests and allocate the seats
            for (String request : requests) {
                //Get Request id
                String requestId = request.substring(0, request.indexOf(" "));
                //Get number of seats and handle NumberFormatException
                int numberOfSeats = 0;
                try {
                    numberOfSeats = Integer.parseInt(request.substring(request.indexOf(" ") + 1, request.length()));
                } catch (NumberFormatException n) {
                    System.out.println("cannot book tickets for the request " + requestId + " as the request contain number of seats in a invalid format \n");
                }
                if (!theater.validRequest(requestId, numberOfSeats)) {
                    System.out.println("cannot book tickets for the request " + requestId + " as the requested number of seats is not valid \n");
                    lhm.put(requestId, null);
                    continue;
                }

                if (!theater.isEmpty(requestId, numberOfSeats)) {
                    if (theater.canAccommodate(requestId, numberOfSeats)) {
                        int ind = theater.searchIndex(numberOfSeats);
                        if (ind >= 0)
                            lhm.put(requestId, theater.allocateSeatsInSingleRow(requestId, numberOfSeats, ind));
                        else
                            lhm.put(requestId, theater.allocateSeatsInMultipleRows(requestId, numberOfSeats));


                    } else {
                        System.out.println("ERROR: Cannot book tickets for the request " + requestId + " as we cant accomidate the number of seats \n");
                        lhm.put(requestId, null);
                    }
                } else {
                    System.out.println("ERROR: Cannot book tickets for the request " + requestId + " as the seats are full \n");
                    lhm.put(requestId, null);
                }
            }


            //Send the lhm values to the OutPutWriter
            for (Map.Entry<String, Response> entry : lhm.entrySet()) {
                Response re = entry.getValue();
                if (entry.getValue() != null) {
                    OutputWriter.outputFileWriter(re.toString() + "\n");
                } else {
                    OutputWriter.outputFileWriter(entry.getKey() + "\n");
                }

            }
            //print the Theater Seating
            theater.printTheaterSeating();
			//print The output file location
			System.out.println("C:/Users/chait/Desktop/MovieTheater/src/output.txt");
            //Close file writer
            OutputWriter.closeFileWriter();
        }
    }

