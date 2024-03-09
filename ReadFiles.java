import java.io.*;
import java.util.*;

public class ReadFiles {
   private String path;

    public ReadFiles() {
    }

    public ReadFiles(String path) {
        this.path = path;
    }

    public String[][] gettingArray(){
        String path = this.path;
        String line = "";
        String [][] myArr = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
    
            ArrayList<String[]> myArrList = new ArrayList<>();
    
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                myArrList.add(values);
            }
            br.close();
            myArr = myArrList.toArray(new String[myArrList.size()][]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            } catch (IOException s) {
            s.printStackTrace();
            }
        return myArr;
    }
}
