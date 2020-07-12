package pro.butovanton.analizer;

import android.util.Log;

import java.util.List;

public class Model {

    private List<String> data;


    public Model(List<String> data) {
        this.data = data;
    }
    
    public String[] findSelector(String findedStr, int findType) {
        String[] result = new String[2];
        switch (findType) {
            case SEARCHTYPE.LEFT:
                result = find(findedStr);
                break;
            case SEARCHTYPE.RIGT:
                result = find(flip(findedStr));
                break;

        }
    return result;    
    }

    private String flip(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    public String[] find(String findedStr) {
        String[] result = new String[2];
        result[0] = "";
        result[1] = "";
        for (int i = 0; i < data.size(); i++) {
            int fint = data.get(i).indexOf(findedStr);
            if (fint != -1) {
                if (i > 0)
                    result[0] = data.get(i - 1).substring(fint, fint + findedStr.length());
                if (i < data.size()-1)
                    result[1] = data.get(i + 1).substring(fint, fint + findedStr.length());
                Log.d("DEBUG", "find-" + findedStr + ": " + result[0] + " " + result[1]);
                return result;
            }
        }
        return result;
    }

    private int findedStrGetLench(String findedStr) {
        int i = 0;
        if (findedStr.equals("10")) return 1;
        else return findedStr.length();
    }
}
