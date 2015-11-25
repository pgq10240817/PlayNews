package android.yhpl.util.log;
//package com.taobao.appcenter.utils.log;
//
//import android.util.Log;
//
///**
// * Created by junxian.bian on 13-11-15.
// */
//public class TaoAppLog2 {
//
//    private static boolean isPrintLog = false;
//    private static boolean isSaveLogToLocal = false;
//
//    public static boolean getLogStatus() {
//        return isPrintLog;
//    }
//
//    public static boolean getLocalLogStatus() {
//        return isSaveLogToLocal;
//    }
//
//    public static void setLogSwitcher(boolean open) {
//        isPrintLog = open;
//    }
//
//    public static void setLogToLocalSwitcher(boolean open) {
//        isSaveLogToLocal = open;
//    }
//
//    public static void printStackTrace(Throwable e) {
//        if (isPrintLog && e != null) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void Logd(String tag, String msg) {
//        if (tag != null && msg != null) {
//            if (isPrintLog) {
//                Log.d(tag, msg);
//            }
//            if (isSaveLogToLocal) {
//                FileLog2.getIntance().write(tag + "(d)", msg, true);
//            }
//        }
//
//        return;
//    }
//
//    public static void Loge(String tag, String msg) {
//
//        if (tag != null && msg != null) {
//            if (isPrintLog) {
//                Log.e(tag, msg);
//            }
//            if (isSaveLogToLocal) {
//                FileLog2.getIntance().write(tag + "(e)", msg, true);
//            }
//        }
//
//        return;
//    }
//
//    public static void Logi(String tag, String msg) {
//        if (tag != null && msg != null) {
//            if (isPrintLog) {
//                Log.i(tag, msg);
//            }
//            if (isSaveLogToLocal) {
//                FileLog2.getIntance().write(tag + "(i)", msg, true);
//            }
//        }
//
//        return;
//    }
//
//    public static void Logv(String tag, String msg) {
//        if (tag != null && msg != null) {
//            if (isPrintLog) {
//                Log.v(tag, msg);
//            }
//            if (isSaveLogToLocal) {
//                FileLog2.getIntance().write(tag + "(v)", msg, true);
//            }
//        }
//
//        return;
//    }
//
//    public static void Logw(String tag, String msg) {
//        if (tag != null && msg != null) {
//            if (isPrintLog) {
//                Log.w(tag, msg);
//            }
//
//            if (isSaveLogToLocal) {
//                FileLog2.getIntance().write(tag + "(w)", msg, true);
//            }
//        }
//
//        return;
//    }
//
//    public static void Log2File(String tag, String msg) {
//
//        if (isPrintLog) {
//            FileLog2.getIntance().write(tag, msg, true);
//        }
//
//    }
//
//}
