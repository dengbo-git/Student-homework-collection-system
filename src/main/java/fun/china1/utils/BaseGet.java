package fun.china1.utils;

import fun.img.path;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BaseGet {
    public static void main(String[] args) {
        //System.out.println(path.class.getResource("").getPath());
        //decodeBase64ToImage("", path.class.getResource("").getPath(), "o.png");
    }
    public static void decodeBase64ToImage(String base64,
                                           String imgName) {
        //System.out.println(base64);
        //System.out.println("测试");

        BASE64Decoder decoder = new BASE64Decoder();
        //System.out.println(base64);
        System.out.println(path.class.getResource("").getPath());
        //System.out.println(path.class.getResource("").getPath());
        try {
            FileOutputStream write = new FileOutputStream(new File(path.class.getResource("").getPath()
                    + imgName));
            byte[] decoderBytes = decoder.decodeBuffer(base64);
            write.write(decoderBytes);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(path.class.getResource("").getPath());
    }
}
