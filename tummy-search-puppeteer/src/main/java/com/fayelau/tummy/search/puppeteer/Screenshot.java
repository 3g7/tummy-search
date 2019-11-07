package com.fayelau.tummy.search.puppeteer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 截图
 * 
 * @author 3g7 2019-10-25 16:35:02
 * @version 0.0.1
 *
 */
public class Screenshot {

    private static final String SCREENSHOT_JS_PATH = "/Users/777/develop/code/com.fayelau/tummy/tummy-puppeteer/features/screenshot.js";

    public static void screenshot(String uri) {
        String command = "node " + SCREENSHOT_JS_PATH + " " + uri;
        try {
            System.out.println("command: " + command);
            Process process = Runtime.getRuntime().exec(command);
            ByteArrayOutputStream resultOutStream = new ByteArrayOutputStream();
            ByteArrayOutputStream resultErrorStream = new ByteArrayOutputStream();
            InputStream in = process.getInputStream();
            InputStream error = process.getErrorStream();
            int num = 0;
            byte[] bs = new byte[1024];
            while ((num = in.read(bs)) != -1) {
                resultOutStream.write(bs, 0, num);
            }
            String pngName = new String(resultOutStream.toByteArray());
            int errorNum = 0;
            byte[] errorBs = new byte[1024];
            while ((errorNum = error.read(errorBs)) != -1) {
                resultErrorStream.write(errorBs, 0, errorNum);
            }
            String errorStr = new String(resultErrorStream.toByteArray());
            System.out.println(pngName);
            System.out.println(errorStr);
            process.waitFor();
            in.close();
            process.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String uri = "https://douyu.com/265438";
        Screenshot.screenshot(uri);
    }

}
