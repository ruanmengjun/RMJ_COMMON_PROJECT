package utils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by admin on 2017/11/22.
 */

public class RetrofitManager {
    public static RequestBody parseRequestBody(String value) {
        return RequestBody.create(MediaType. parse("text/plain"), value);
    }

    public static RequestBody parseImageRequestBody(File file) {
        return RequestBody.create(MediaType. parse("image/*"), file);
    }

    public static String parseImageMapKey(String key, String fileName) {
        return key + "\"; filename=\"" + fileName;
    }
}
