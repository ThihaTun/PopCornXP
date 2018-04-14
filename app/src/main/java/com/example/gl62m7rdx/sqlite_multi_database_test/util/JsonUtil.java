package com.example.gl62m7rdx.sqlite_multi_database_test.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.gl62m7rdx.sqlite_multi_database_test.MyApplication;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by GL62M 7RDX on 19-Dec-17.
 */

public class JsonUtil {

    private static final String PATH_DUMMY_DATA = "dummy_data";

    private static JsonUtil objInstace;

    private Context context;

    public static JsonUtil getInstance() {
        if (objInstace == null) {
            objInstace = new JsonUtil();
        }

        return objInstace;
    }

    private JsonUtil() {
        context = MyApplication.getContext();
    }

    /**
     * Read text from assets folder.
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    private byte[] readJsonFile(String fileName) throws IOException {
        InputStream inStream = context.getAssets().open(fileName);
        int size = inStream.available();
        byte[] buffer = new byte[size];
        inStream.read(buffer);
        inStream.close();
        return buffer;
    }

    /**
     * @param fileName - name of Json File.
     * @return JSONObject from loaded file.
     * @throws IOException
     * @throws JSONException
     */
    public String loadDummyData(String fileName) throws IOException, JSONException {
        byte[] buffer = readJsonFile(PATH_DUMMY_DATA + "/" + fileName);
        return new String(buffer, "UTF-8").toString();
    }
}
