package MovieTheater;

import java.util.ArrayList;
import java.util.List;

public class Response {
    class DS {
        int start,end;
        char rowName;
        DS (int start, int end, char rowName) {
            this.start = start;
            this.end = end;
            this.rowName = rowName;
        }
    }
    String request;
    List<DS> allocated;
    public Response(String request,char rowName,int start,int end) {
        DS l = new DS(start, end, rowName);
        addRow(rowName,start,end);
        this.request=request;

    }

    public void addRow(char rowName,int start,int end) {
        if (allocated==null){
            allocated = new ArrayList<>();
        }
        DS l = new DS(start, end, rowName);
        allocated.add(l);
        //System.out.println(l.rowName+""+l.start+" to "+l.rowName+""+l.end+" the list id is "+allocated);
    }
public String toString(){
     String res="";
     res=res+this.request+" ";
     for(int i=0;i<allocated.size();i++)
     {
         for(int j=allocated.get(i).start;j<=allocated.get(i).end;j++){
             if(j==allocated.get(i).end)
                 res=res+allocated.get(i).rowName+j+" ";
             else
                 res=res+allocated.get(i).rowName+j+",";

         }
     }
    return res;

}
}

