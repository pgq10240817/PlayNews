package android.yhpl.util.log;
//package com.taobao.appcenter.utils.log;
//
//import android.os.Environment;
//import android.taobao.common.SDKConfig;
//
//import com.taobao.appcenter.utils.com.taobao.appcenter.module.app.Constants;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * 没小时一个文件记录log
// * Created by junxian.bian on 13-11-15.
// */
//public class FileLog2 {
//
//    private static boolean isPrintLog = false;
//
//    private static final int MAX_FILE_SIZE = 3 * 1024 * 1024;//文件最大3M
//
//    // 用于格式化日期,作为日志文件名的一部分
//    private DateFormat fileFormatter = new SimpleDateFormat("yyyy_MM_dd_HH");
//    private DateFormat logFormatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
//    private String logFileName;
//    private int fileCount = 0;
//
//    private static FileLog2 instance;
//
//    private FileLog2() {
//        initLog();
//    }
//
//    // 返回Filelog的实例
//    public synchronized static FileLog2 getIntance() {
//        if (instance == null) {
//            instance = new FileLog2();
//        }
//        return instance;
//    }
//
//    private void initLog() {
//        String time = fileFormatter.format(new Date());
//        logFileName = "taoappLog_" + time + ".log";
//    }
//
//    /**
//     * @param tag
//     * @param message
//     * @param append  本地写入追加
//     */
//    public void write(String tag, String message, boolean append) {
//
//        BufferedWriter out = null;
//        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/"
//                + Constants.SAVE_FILE_ROOT_DIR + "/log2", logFileName);
//
//        if (!file.getParentFile().exists()) {
//            file.getParentFile().mkdirs();
//        }
//        if (!file.exists()) {
//            try {
//                fileCount = 0;
//                file.createNewFile();
//            } catch (IOException e) {
//            }
//        }
//
//        if (file.length() >= MAX_FILE_SIZE) {
//            String time = fileFormatter.format(new Date());
//            String fileName = "taoappLog_" + time + ".log";
//            if (fileName.equals(logFileName)) {
//                fileCount++;
//                logFileName = logFileName + "(" + fileCount + ")";
//            } else {
//                logFileName = fileName;
//            }
//
//            write(tag, message, append);
//
//            return;
//        }
//
//        try {
//            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append)));
//            StringBuffer logBuffer = new StringBuffer();
//            logBuffer.append(logFormatter.format(new Date()));
//            logBuffer.append("              ");
//            logBuffer.append(tag);
//            logBuffer.append("              ");
//            logBuffer.append(message);
//            logBuffer.append("\n");
//            out.write(logBuffer.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (out != null) {
//                    out.flush();
//                    out.close();
//                    out = null;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
