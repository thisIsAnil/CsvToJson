import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    static class Query{
        private String[] row;
        public Query() {
        }

        public Query(String[] row) {
            this.row = row;
        }


        public String[] getRow() {
            return row;
        }

        public void setRow(String[] row) {
            this.row = row;
        }
    }
    public static void main(String[] args) throws Exception{
        File f=new File("yourcsv file");//path to csv file
            CSVReader reader = new CSVReader(new FileReader(f.getAbsolutePath()));
            List<Query> q=new ArrayList<>();
            String[] row;
            while ((row = reader.readNext()) != null) {
               Query qd=new Query();
               qd.setRow(row);
               System.out.println(qd.toString());
               System.out.println("Row size is "+row.length);
               q.add(qd);
            }

            Gson gson=new Gson();
            Type type=new TypeToken<List<Query>>(){}.getType();
            String json=gson.toJson(q,type);
            FileOutputStream fos=new FileOutputStream("assets/output.json");
            fos.write(json.getBytes(),0,json.getBytes().length);
            fos.close();

            System.out.println(json);
    }
}
