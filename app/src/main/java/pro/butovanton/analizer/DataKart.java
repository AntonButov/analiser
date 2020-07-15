package pro.butovanton.analizer;

import java.util.List;

public class DataKart {

    int day;
    int monf;
    int yaer;
    String data;
    String gift;

    public DataKart() {

    }

    public String getData() {
        return data;
    }

    public boolean isValid(FindConfig findConfig) {
        boolean result = true;
        if (findConfig.to)
            if (yaer >= findConfig.year && monf >= findConfig.monf && day > findConfig.day)
                result = false;

        return result;
    }

}
