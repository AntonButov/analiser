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
    public void read() throws FileNotFoundException {
        InputStream inputStream = context.getResources().openRawResource(R.raw.chance);
        CSVReader csvReader = new CSVReader(inputStream);
        List<String> read = csvReader.read();
        assertTrue(read.size() > 0);
        Model model = new Model(read);
        String[] result;
        result = model.find("K");
        assertTrue(result[0].equals(""));
        assertTrue(result[1].equals("7"));
        result = model.find("1");
        assertTrue(result[0].equals("7"));
        assertTrue(result[1].equals("J"));
        result = model.find("797");
        assertTrue(result[0].equals("KA9"));
        assertTrue(result[1].equals("9K9"));
        result = model.find("JAQ1");
        assertTrue(result[0].equals("7977"));
        assertTrue(result[1].equals("17A7"));
    }
}