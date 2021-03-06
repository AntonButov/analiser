package pro.butovanton.analizer;

import android.util.Log;

import java.util.List;

public class Model {

    private List<DataKart> data;


    public Model(List<DataKart> data) {
        this.data = data;
    }
    
    public String[] findSelector(String findedStr, int findType, FindConfig findConfig) {
        String[] result = new String[2];
        switch (findType) {
            case SEARCHTYPE.LEFT:
                result = find(findedStr, findConfig);
                break;
            case SEARCHTYPE.RIGT:
                result = find(flip(findedStr), findConfig);
                result[0] = flip(result[0]);
                result[1] = flip(result[1]);
                break;
            case SEARCHTYPE.RIGHTUP:
                result = findRUp(findedStr, findConfig);
                break;
            case SEARCHTYPE.LEFTDOWN:
                result = findRUp(flip(findedStr), findConfig);
                result[0] = flip(result[0]);
                result[1] = flip(result[1]);
                break;
            case SEARCHTYPE.LEFTUP:
                result = findLeftUp(findedStr, findConfig);
                break;
            case SEARCHTYPE.RIGTDOWN:
                result = findLeftUp(flip(findedStr), findConfig);
                result[0] = flip(result[0]);
                result[1] = flip(result[1]);
                break;

        }
    Log.d("DEBUG", "find-" + findedStr + ": " + result[0] + " " + result[1]);
    return result;    
    }

    private String flip(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    public String[] find(String findedStr, FindConfig findConfig) {
        String[] result = new String[2];
        result[0] = "";
        result[1] = "";
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isValid(findConfig)) {
            int fint = data.get(i).data.indexOf(findedStr);
            if (fint != -1) {
                if (i > 0)
                    result[0] = data.get(i - 1).data.substring(fint, fint + findedStr.length());
                if (i < data.size() - 1)
                    result[1] = data.get(i + 1).data.substring(fint, fint + findedStr.length());
                return result;
            }
            }
        }
        return result;
    }

    public String[] findVert(String findStr, FindConfig findConfig) {
        findStr = flip(findStr);
        String[] result = new String[2];
        result[0] = "";
        result[1] = "";
        for (int i = 0; i < data.size(); i ++)
           for (int x = 0; x < 4; x ++) {
                if (i >= findStr.length() - 1) {
                    if (data.get(i).isValid(findConfig)) {
                        int l;
                        for (l = 0; l < findStr.length(); l++)
                            if (data.get(i - l).data.charAt(x) != findStr.charAt(l))
                                break;
                        if (l == findStr.length()) {
                            if (x > 0)
                                for (l = 0; l < findStr.length(); l++)
                                    result[0] = result[0] + data.get(i - l).data.charAt(x - 1);
                            if (x < 3)
                                for (l = 0; l < findStr.length(); l++)
                                    result[1] = result[1] + data.get(i - l).data.charAt(x + 1);
                            findStr = flip(findStr);
                            result[0] = flip(result[0]);
                            result[1] = flip(result[1]);
                            Log.d("DEBUG", "find-" + findStr + ": " + result[0] + " " + result[1]);
                            return result;
                        }
                    }
                }
            }
        return result;
    }

    public String[] findRUp(String findStr, FindConfig findConfig) {
        String[] result = new String[2];
        result[0] = "";
        result[1] = "";
        for (int i = 0; i < data.size(); i ++)
            if (data.get(i).isValid(findConfig)) {
                for (int x = 0; x < 4; x++) {
                    if (i >= findStr.length() - 1 && x + findStr.length() <= 4) {
                        int l;
                        for (l = 0; l < findStr.length(); l++)
                            if (data.get(i - l).data.charAt(x + l) != findStr.charAt(l))
                                break;
                        if (l == findStr.length()) {
                            for (l = 0; l < findStr.length(); l++)
                                if (i - l - 1 >= 0)
                                    result[0] = result[0] + data.get(i - l - 1).data.charAt(x + l);
                                else result[0] = result[0] + " ";
                            for (l = 0; l < findStr.length(); l++)
                                if (i + 1 < data.size())
                                    result[1] = result[1] + data.get(i - l + 1).data.charAt(x + l);
                            return result;
                        }
                    }
                }
            }
        return result;
    }

    public String[] findLeftUp(String findStr, FindConfig findConfig) {
        String[] result = new String[2];
        result[0] = "";
        result[1] = "";
        for (int i = 0; i < data.size(); i ++)
            for (int x = 0; x < 4; x ++)
                if (data.get(i).isValid(findConfig)) {
                if (i >= findStr.length() - 1 && x - findStr.length() + 1 >= 0 ) {
                    int l;
                    for ( l = 0; l < findStr.length(); l++)
                        if (data.get(i - l).data.charAt(x - l) != findStr.charAt(l))
                            break;
                    if (l == findStr.length()) {
                        for (l = 0; l < findStr.length(); l++ )
                            if (i - l -1 >= 0)
                                result[0] = result[0] + data.get(i - l - 1).data.charAt(x - l);
                            else result[0] = result[0] + " ";
                        for (l = 0; l < findStr.length(); l++ )
                            if (i + 1 < data.size())
                                result[1] = result[1] + data.get(i - l + 1).data.charAt(x - l);
                        return result;
                    }
                }
            }
        return result;
    }


}
