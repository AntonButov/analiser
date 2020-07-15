package pro.butovanton.analizer;

import android.content.Context;
import android.view.Display;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    public Context context;

    @Before
    public void setContex() {
    context =  InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("pro.butovanton.analizer", appContext.getPackageName());
    }


    @Test
    public void check() throws FileNotFoundException {
        InputStream inputStream = context.getResources().openRawResource(R.raw.chance);
        CSVReader csvReader = new CSVReader(inputStream);
        List<DataKart> read = csvReader.read();
        assertTrue(read.size() > 0);
        Model model = new Model(read);
        String[] result;
        FindConfig findConfig = new FindConfig();
        result = model.find("K", findConfig);
        assertTrue(result[0].equals(""));
        assertTrue(result[1].equals("7"));
        result = model.find("1", findConfig);
        assertTrue(result[0].equals("7"));
        assertTrue(result[1].equals("J"));
        result = model.find("797", findConfig);
        assertTrue(result[0].equals("KA9"));
        assertTrue(result[1].equals("9K9"));
        result = model.find("JAQ1", findConfig);
        assertTrue(result[0].equals("7977"));
        assertTrue(result[1].equals("17A7"));
        result = model.findVert("K", findConfig);
        assertTrue(result[1].equals("A"));
        result = model.findVert("79", findConfig);
        assertTrue(result[0].equals(""));
        assertTrue(result[1].equals("9K"));
        result = model.findVert("A9", findConfig);
        assertTrue(result[0].equals("K7"));
        assertTrue(result[1].equals("97"));
        result = model.findVert("9K9A", findConfig);
        assertTrue(result[0].equals("797J"));
        assertTrue(result[1].equals("797Q"));
        result = model.findVert("71J7", findConfig);
        assertTrue(result[0].equals("9797"));
        assertTrue(result[1].equals(""));

        result = model.findSelector("7K77", SEARCHTYPE.RIGHTUP, findConfig);
        assertTrue(result[0].equals("999 "));
        assertTrue(result[1].equals("J991"));
        result = model.findSelector("1QA1", SEARCHTYPE.RIGHTUP, findConfig);
        assertTrue(result[0].equals("A7Q7"));
        assertTrue(result[1].equals("91K7"));
        result = model.findSelector("77K7", SEARCHTYPE.LEFTDOWN, findConfig);
        assertTrue(result[0].equals(" 999"));
        assertTrue(result[1].equals("199J"));
        result = model.findSelector("7Q7A", SEARCHTYPE.LEFTDOWN, findConfig);
        assertTrue(result[0].equals("J7A1"));
        assertTrue(result[1].equals("1AQ1"));
        result = model.findSelector("799K", SEARCHTYPE.LEFTUP, findConfig);
        assertTrue(result[0].equals("J7A "));
        assertTrue(result[1].equals("17K7"));
        result = model.findSelector("7Q99", SEARCHTYPE.LEFTUP, findConfig);
        assertTrue(result[0].equals("17K7"));
        assertTrue(result[1].equals("1AA7"));
        result = model.findSelector("7K71", SEARCHTYPE.RIGTDOWN, findConfig);
        assertTrue(result[0].equals("K997"));
        assertTrue(result[1].equals("99Q7"));
        findConfig.to = true;
        findConfig.year = 2020;
        findConfig.monf = 06;
        findConfig.day = 01;
        result = model.findSelector("7", SEARCHTYPE.RIGT, findConfig);
        assertTrue(result[0].equals("K"));
        assertTrue(result[1].equals("9"));
    }
}