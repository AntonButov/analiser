package pro.butovanton.analizer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 100;
    final String[] carts = {"", "A", "K", "Q", "J", "1", "9", "8", "7"};

    private int search_type = SEARCHTYPE.LEFT;
    private RadioGroup radioGroupFindType;

    private Spinner spinner1, spinner2, spinner3, spinner4;
    private Button buttonFind, buttonClear;
    private TextView textView1up, textView2up, textView3up, textView4up;
    private TextView textView1Down, textView2Down, textView3Down, textView4Down;
    private String findStr1 = "", findStr2 = "", findStr3 = "", findStr4 = "";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1up = findViewById(R.id.textView1up);
        textView2up = findViewById(R.id.textView2up);
        textView3up = findViewById(R.id.textView3up);
        textView4up = findViewById(R.id.textView4up);
        textView1Down = findViewById(R.id.textView1down);
        textView2Down = findViewById(R.id.textView2down);
        textView3Down = findViewById(R.id.textView3down);
        textView4Down = findViewById(R.id.textView4down);

        spinner1 = findViewById(R.id.spinner);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spiner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                findStr1 = carts[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner1.setAdapter(adapter);
        spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.spiner_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                findStr2 = carts[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3 = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.spiner_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                findStr3 = carts[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner4 = findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.spiner_array, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                findStr4 = carts[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        InputStream inputStream = getResources().openRawResource(R.raw.chance);
//        InputStream inputStream = null;
//        try {
//            FileInputStream fis = new FileInputStream(getDataDir() + "/Chance.csv" );
//            Log.d("DEBUG", fis.toString());
//           inputStream = fis;
//       } catch (FileNotFoundException e) {
//           e.printStackTrace();
//       }

        CSVReader csvReader = new CSVReader(inputStream);
        final Model model = new Model(csvReader.read());
        buttonFind = findViewById(R.id.buttonFind);
        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] result;
                String findStr = findStr1 + findStr2 + findStr3 + findStr4;
                if (!findStr.equals("")) {
                    result = model.findSelector(findStr, search_type);
                    for (int i = 0; i < findStr.length(); i++) {
                        switch (i) {
                            case (0):
                            textView1up.setText(parseResult(result[0], i));
                            textView1Down.setText(parseResult(result[1], i));
                            break;
                            case (1):
                            textView2up.setText(parseResult(result[0], 1));
                            textView2Down.setText(parseResult(result[1], 1));
                            break;
                            case (2):
                            textView3up.setText(parseResult(result[0], 2));
                            textView3Down.setText(parseResult(result[1], 2));
                            break;
                            case (3):
                            textView4up.setText(parseResult(result[0], 3));
                            textView4Down.setText(parseResult(result[1], 3));
                            break;
                        }
                    }
                }
            }
        });
        buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner1.setSelection(0);
                spinner2.setSelection(0);
                spinner3.setSelection(0);
                spinner4.setSelection(0);
                textView1up.setText("");
                textView2up.setText("");
                textView3up.setText("");
                textView4up.setText("");
                textView1Down.setText("");
                textView2Down.setText("");
                textView3Down.setText("");
                textView4Down.setText("");
            }
        });

        radioGroupFindType = findViewById(R.id.radioGroupFindType);
        radioGroupFindType.check(R.id.radioButtonLeft);
        radioGroupFindType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonLeft:
                        search_type = SEARCHTYPE.LEFT;
                        break;
                    case R.id.radioButtonRight:
                        search_type = SEARCHTYPE.RIGT;
                        break;
                    case R.id.radioButtonUp:
                        search_type = SEARCHTYPE.UP;
                        break;
                    case R.id.radioButtonDoun:
                        search_type = SEARCHTYPE.DOWN;
                        break;
                }
            }
        });
    }

    private String parseResult(String result, int n) {
        String resultParse = "";
        if (!result.equals("")) {
            resultParse = String.valueOf(result.charAt(n));
            if (resultParse.equals("1")) resultParse = "10";
        }
        return resultParse;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // Explain to the user why we need to read the contacts

                }

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                // app-defined int constant that should be quite unique

                return;
            }
        }
    }
}