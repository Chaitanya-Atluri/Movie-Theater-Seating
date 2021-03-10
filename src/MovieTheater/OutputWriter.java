package MovieTheater;
import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter {
    public static FileWriter fw;

    public static void initializeFileWriter() throws IOException {
        fw = new FileWriter("output.txt");
    }

    public static void closeFileWriter() throws IOException {
        fw.close();
    }
    public static void outputFileWriter(String errorData)
    {
        try {
            fw.write(errorData);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void outputFileWriter(String requestId,Character name,int low,int high)
    {
        try {
            fw.write(requestId+" ");
            for(int i=low;i<high;i++)
                fw.write(name+""+i+",");
            fw.write(name+""+high+"\n");
        } catch (IOException ex) {
            //System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }

}
    
    