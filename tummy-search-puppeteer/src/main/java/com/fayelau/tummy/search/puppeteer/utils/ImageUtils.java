package com.fayelau.tummy.search.puppeteer.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;

import com.fayelau.tummy.base.core.exception.TummyExCode;
import com.fayelau.tummy.base.core.exception.TummyException;

public class ImageUtils {

    public static void base64ToImage(String imgStr) {
        if (imgStr == null) {
            throw TummyException.getException(TummyExCode.PARAMETER_NULL);
        }
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            // Base64解码
            byte[] b = decoder.decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成png图片
            String imgFilePath = "/Users/777/a.png";// 新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
