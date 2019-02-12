package com.sj.skyblue.manager;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.module_lib.utils.Utils;
//import com.sunmi.peripheral.printer.InnerPrinterCallback;
//import com.sunmi.peripheral.printer.InnerPrinterException;
//import com.sunmi.peripheral.printer.InnerPrinterManager;
//import com.sunmi.peripheral.printer.InnerResultCallbcak;
//import com.sunmi.peripheral.printer.SunmiPrinterService;

/**
 * Created by Sunj on 2018/11/5.
 */

public class PrintManager {

//    PrintCallBack printCallBack;
//    String printText;
//    SunmiPrinterService sunmiPrinterService;
//
//
//    InnerPrinterCallback innerPrinterCallback = new InnerPrinterCallback() {
//        @Override
//        protected void onConnected(SunmiPrinterService service) {
//            Logger.d("打印机连接成功");
//            ToastUtils.showShortToast("打印机连接成功");
//            sunmiPrinterService = service;
//            try {
//                sunmiPrinterService.printerInit(null);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        protected void onDisconnected() { //当服务异常断开后，会回调此⽅法
//            Logger.d("打印机断开连接");
//            ToastUtils.showShortToast("打印机断开连接");
//            sunmiPrinterService = null;
//        }
//    };
//
//    private static final PrintManager ourInstance = new PrintManager();
//
//    public static PrintManager getInstance() {
//        return ourInstance;
//    }
//
//    private PrintManager() {
//    }
//
//    public void bindService() {
//        Logger.d("打印机连接开始");
//        try {
//            InnerPrinterManager.getInstance().bindService(Utils.getContext(), innerPrinterCallback);
//        } catch (InnerPrinterException e) {
//            e.printStackTrace();
//            Logger.d("打印机连接异常");
//        }
//
//    }
//
//    public synchronized void printText(String text, PrintCallBack callBack) {
//        this.printText = text;
//        this.printCallBack = callBack;
//        printText();
//
//    }
//
//    private void printText() {
//        try {
//            sunmiPrinterService.printOriginalText(printText, new InnerResultCallbcak() {
//
//                @Override
//                public void onRunResult(boolean b) throws RemoteException {
//                    Logger.d("打印机打印：onRunResult");
//                    if (printCallBack != null) {
//                        if (b) {
//                            printCallBack.printSuccess();
//                        } else {
//                            printCallBack.printFailed(-2);
//                        }
//                    }
//
//                }
//
//                @Override
//                public void onReturnString(String s) throws RemoteException {
//                    Logger.d("打印机打印：onReturnString：" + s);
//                }
//
//                @Override
//                public void onRaiseException(int code, String s) throws RemoteException {
//                    Logger.d("打印机打印：onRaiseException：" + code + "-" + s);
//                }
//
//                @Override
//                public void onPrintResult(int code, String s) throws RemoteException {
//                    Logger.d("打印机打印：onPrintResult：" + code + "-" + s);
//                }
//            });
//        } catch (RemoteException e) {
//            Logger.d("打印机打印：RemoteException");
//        }
//    }
//
//    public interface PrintCallBack {
//        void printSuccess();
//
//        //code -1 打印机连接异常 -2打印机打印失败
//        void printFailed(int code);
//    }

}
