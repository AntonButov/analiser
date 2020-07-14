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
                result[0] = flip(result[0]);
                result[1] = flip(result[1]);
                break;
            case SEARCHTYPE.RIGHTUP:
                result = findRUp(findedStr);
                break;
            case SEARCHTYPE.LEFTDOWN:
                result = findRUp(flip(findedStr));
                result[0] = flip(result[0]);
                result[1] = flip(result[1]);
                break;
            case SEARCHTYPE.LEFTUP:

                break;
            case SEARCHTYPE.RIGTDOWN:

                break;

        }
    Log.d("DEBUG", "find-" + findedStr + ": " + result[0] + " " + result[1]);
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

    public String[] findVert(String findStr) {
        findStr = flip(findStr);
        String[] result = new String[2];
        result[0] = "";
        result[1] = "";
        for (int i = 0; i < data.size(); i ++)
           for (int x = 0; x < 4; x ++) {
                if (i >= findStr.length() - 1) {
                    int l;
                    for ( l = 0; l < findStr.length(); l++)
                        if (data.get(i - l).charAt(x) != findStr.charAt(l))
                            break;
                    if (l == findStr.length()) {
                        if (x > 0)
                            for (l = 0; l < findStr.length(); l++ )
                            result[0] = result[0] + data.get(i - l).charAt(x -1 );
                        if (x < 3)
                            for (l = 0; l < findStr.length(); l++ )
                                result[1] = result[1] + data.get(i - l).charAt(x + 1);
                    findStr = flip(findStr);
                    result[0] = flip(result[0]);
                    result[1] = flip(result[1]);
                    Log.d("DEBUG", "find-" + findStr + ": " + result[0] + " " + result[1]);
                    return result;
                    }

                }
            }
        return result;
    }

    public String[] findRUp(String findStr) {
        String[] result = new String[2];
        result[0] = "";
        result[1] = "";
        for (int i = 0; i < data.size(); i ++)
            for (int x = 0; x < 4; x ++) {
                if (i >= findStr.length() - 1 && x + findStr.length() <= 4 ) {
                    int l;
                    for ( l = 0; l < findStr.length(); l++)
                        if (data.get(i - l).charAt(x + l) != findStr.charAt(l))
                            break;
                    if (l == findStr.length()) {
                           for (l = 0; l < findStr.length(); l++ )
                               if (i - l -1 >= 0)
                                  result[0] = result[0] + data.get(i - l - 1).charAt(x + l);
                               else result[0] = result[0] + " ";
                            for (l = 0; l < findStr.length(); l++ )
                                if (i + 1 < data.size())
                                  result[1] = result[1] + data.get(i - l + 1).charAt(x + l);
                        return result;
                    }
                }
            }
        return result;
    }

    public String[] findLeftUp(String findStr) {
        String[] result = new String[2];
        result[0] = "";
        result[1] = "";
        for (int i = 0; i < data.size(); i ++)
            for (int x = 0; x < 4; x ++) {
                if (i >= findStr.length() - 1 && x + findStr.length() <= 4 ) {
                    int l;
                    for ( l = 0; l < findStr.length(); l++)
                        if (data.get(i - l).charAt(x + l) != findStr.charAt(l))
                            break;
                    if (l == findStr.length()) {
                        for (l = 0; l < findStr.length(); l++ )
                            if (i - l -1 >= 0)
                                result[0] = result[0] + data.get(i - l - 1).charAt(x + l);
                            else result[0] = result[0] + " ";
                        for (l = 0; l < findStr.length(); l++ )
                            if (i + 1 < data.size())
                                result[1] = result[1] + data.get(i - l + 1).charAt(x + l);
                        return result;
                    }
                }
            }
        return result;
    }
}
