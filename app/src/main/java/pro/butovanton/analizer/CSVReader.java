package pro.butovanton.analizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    InputStream inputStream;

    public CSVReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List<DataKart> read() {
        List<DataKart> resultList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            reader.readLine();
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                csvLine = "";
                for (int i = 2; i <= 5; i++) {
                    if (row[i].equals("10")) row[i] = "1";
                    csvLine = csvLine + row[i];
                }
                DataKart dataKart = new DataKart();
                dataKart.dayMonfYr = row[0];
                dataKart.gift = row[1];
                dataKart.data = csvLine;
                resultList.add(dataKart);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: " + ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
        return resultList;

    }
}