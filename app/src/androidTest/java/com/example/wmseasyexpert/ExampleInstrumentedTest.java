package com.example.wmseasyexpert;

import android.content.Context;
import android.util.Log;


import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.wmseasyexpert.Parser.XMLParser;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String TAG = ExampleInstrumentedTest.class.getName();

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.wmseasyexpert", appContext.getPackageName());
        Log.e("Testt", " ");
        long startTime = System.currentTimeMillis();
        XMLParser.parseDoc(TestXMLs.optionsXML);
        Log.e(TAG, "exec time: " + (System.currentTimeMillis() - startTime) + " ms");
    }
}
