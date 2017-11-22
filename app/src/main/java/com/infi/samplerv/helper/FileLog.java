package com.infi.samplerv.helper;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

/**
 * Created by INFIi on 11/22/2017.
 */

public class FileLog {

    private static final boolean DEBUG=false;
    public static void write(String msg) {
        if (DEBUG) {
            try {
                File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/sampleLog.txt");
                if (!f.exists()) f.createNewFile();
                FileInputStream fis = new FileInputStream(f);
                String old;
                byte[] bytes = new byte[(int) f.length()];
                if (bytes.length > 0) fis.read(bytes, 0, bytes.length);
                old = new String(bytes, Charset.defaultCharset());
                msg = old + "\n" + msg;
                fis.close();
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(msg.getBytes(), 0, msg.length());
                fos.close();


            } catch (Exception ffe) {
            }
        }
    }
    public static void e(String tag,Exception e){
        write(e.getMessage());

    }
    public static void e(Throwable e){
        write(e.getMessage());

    }
    public static void e(String tag,Throwable e){
        write(e.getMessage());

    }

    public static void w(String tag,String msg) {
        write(tag + msg);
    }

}
