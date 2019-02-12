package com.sj.skyblue.constant;

import com.sj.skyblue.R;
import com.sj.skyblue.entity.PayMethod;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunj on 2018/11/1.
 */

public class Constants {

    public static final String SDCARD_ROOT_PATH = "/SkyBluePhoto";

    public static final String USER_INFO_FILE_NAME = "user_info.obj";
    public static final String PAY_METHOD_FILE_NAME = "pay_method.obj";

    public static final String STATUS_WORK_CLOSE = "0";
    public static final String STATUS_WORK_OPRN = "1";
    public static final String STATUS_WORK_SHIFT = "2";
    public static final HashMap<String, String> orderStatus = new HashMap<>();
    public static final HashMap<String, Integer> orderStatusBg = new HashMap<>();
    public static final HashMap<String, String> orderPayStatus = new HashMap<>();
    public static final HashMap<String, Integer> orderPayStatusBg = new HashMap<>();
    public static final HashMap<String, String> orderPayChannels = new HashMap<>();

    //    0: 现金1: 银联2: 支付宝3: 微信
    public static final String PAYCHANNEL_CASH = "0";
    public static final String PAYCHANNEL_CARD = "1";
    public static final String PAYCHANNEL_ALIPAY = "2";
    public static final String PAYCHANNEL_WXPAY = "3";

    public static final String PAY_STATUS_NOT = "0";
    public static final String PAY_STATUS_PAID = "1";
    public static final String PAY_STATUS_BACK = "3";

    //    1是普通类型，2是加印类型，3是合照类型
    public static final String TYPE_OTHER_SERVICE = "1";
    public static final String TYPE_ADD_SERVICE = "2";
    public static final String TYPE_GROUP_SERVICE = "3";

    //    1是订单，2是服务订单，3是服务基础子订单 4，服务基础加印订单 5，商品订单
    public static final int TYPE_PAYBACK_ORDER = 1;
    public static final int TYPE_PAYBACK_SERVICE = 2;
    public static final int TYPE_PAYBACK_SERVICE_BASE = 3;
    public static final int TYPE_PAYBACK_SERVICE_ADD = 4;
    public static final int TYPE_PAYBACK_PRODUCT = 5;

    static {
        orderStatus.put("0", "门店订单");
        orderStatus.put("1", "预约成功");
        orderStatus.put("2", "未到店");
        orderStatus.put("3", "已到店");
        orderStatus.put("4", "等待化妆");
        orderStatus.put("5", "化妆中");
        orderStatus.put("6", "等待化妆时间长");
        orderStatus.put("7", "等待摄影");
        orderStatus.put("8", "摄影中");
        orderStatus.put("9", "等待摄影时间长");
        orderStatus.put("10", "等待看片");
        orderStatus.put("11", "订单完成");
    }

    static {
        orderStatusBg.put("0", R.drawable.shape_circle_button_green);
        orderStatusBg.put("1", R.drawable.shape_circle_button_green);
        orderStatusBg.put("2", R.drawable.shape_circle_button_gray);
        orderStatusBg.put("3", R.drawable.shape_circle_button_blue);
        orderStatusBg.put("4", R.drawable.shape_circle_button_yellow);
        orderStatusBg.put("5", R.drawable.shape_circle_button_blue);
        orderStatusBg.put("6", R.drawable.shape_circle_button_orange);
        orderStatusBg.put("7", R.drawable.shape_circle_button_yellow);
        orderStatusBg.put("8", R.drawable.shape_circle_button_blue);
        orderStatusBg.put("9", R.drawable.shape_circle_button_orange);
        orderStatusBg.put("10", R.drawable.shape_circle_button_yellow);
        orderStatusBg.put("11", R.drawable.shape_circle_button_green);
    }

    //    0 未支付 1已支付 3已退款
    static {
        orderPayStatus.put(PAY_STATUS_NOT, "未支付");
        orderPayStatus.put(PAY_STATUS_PAID, "已支付");
        orderPayStatus.put(PAY_STATUS_BACK, "已退款");
    }

    static {
        orderPayStatusBg.put("0", R.drawable.shape_circle_button_gray);
        orderPayStatusBg.put("1", R.drawable.shape_circle_button_green);
        orderPayStatusBg.put("3", R.drawable.shape_circle_button_red);
    }

    static {
        orderPayChannels.put(PAYCHANNEL_CASH, "现金");
        orderPayChannels.put(PAYCHANNEL_CARD, "刷卡");
        orderPayChannels.put(PAYCHANNEL_ALIPAY, "支付宝");
        orderPayChannels.put(PAYCHANNEL_WXPAY, "微信");
    }

    public static final int CUSTOMER_DISPLAY_DEFAULT = 0;
    public static final int CUSTOMER_DISPLAY_REGISTER = 1;
    public static final int CUSTOMER_DISPLAY_ORDER = 2;

}
