package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestUtils {


    public static String getProperty(String key) {

        Properties config = new Properties();
        String user_dir=(System.getProperty("user.dir"));
        try (FileInputStream fileInputStream = new FileInputStream(user_dir+"\\src\\test\\resources\\SwagLabsTestData.properties")) {
            config.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config.getProperty(key);
    }
}
