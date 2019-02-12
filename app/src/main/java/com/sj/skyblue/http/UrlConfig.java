package com.sj.skyblue.http;

import android.support.annotation.Keep;

/**
 * 创建时间: on 2018/3/31.
 * 创建人: 孙杰
 * 功能描述:请求地址配置
 */
@Keep
public class UrlConfig {

    //baseUrl
    public static final String BASE_URL = "https://naiveblue.app-service-node.com";
    //员工登录
    public static final String LOGIN_URL = "/appEmployeeLogin";
    //设备绑定
    public static final String QUERY_SHOP_INFO = "/queryShopInfoByDeviceId";
    //查询设备绑定门店
    public static final String DEVICE_BIND_SHOP = "/deviceBindToShop";
    //开班/下班/交班
    public static final String UPDATE_WORKSTATUS_URL = "/updateEmployeeWorkStatus";
    //查询服务列表
    public static final String QUERY_SERVICELIST_URL = "/findAllServiceByShopId";
    //查询预约单列表
    public static final String QUERY_APPOINTMENT_LIST_URL = "/queryAppointmentList";
    //查询订单列表
    public static final String QUERY_APPOINT_GRADE = "/queryAppointGrade";
    //查询订单详情
    public static final String QUERY_ORDERDETAIL_BY_ID_URL = "/findOrderDetailByIdNew";
    //新建订单
    public static final String APP_SAVE_ORDER_URL = "/appSaveOrder";
    //修改订单
    public static final String UPDATE_ORDER_INFO_URL = "/updateOrderInfoByApp";
    //获取流水号
    public static final String GET_TRANSACTION_ID_URL = "/getTransactionId";
    //更新订单支付状态
    public static final String PAID_UPDATE_URL = "/paidBackApp";
    //退单，退款
    public static final String PAID_REFUND_URL = "/refundOrder";
    //折扣密码验证
    public static final String CHECK_SHOP_URL = "/checkShopPwd";
    //订单添加服务
    public static final String SAVA_ORDER_SERVICE_URL = "/saveOrderService";
    //店长和前台列表
    public static final String QUERY_STAGE_AND_SHOP_LIST = "/queryStageAndShopList";
    //订单绑定人员
    public static final String ORDER_BIND_EMPLOYEE = "/orderBindEmployee";
    //订单服务修改服务
    public static final String CHANGE_ORDER_SERVICE = "/changeOrderService";
    //删除订单
    public static final String DELETE_ORDER_SERVICE = "/deleteOrderById";
    //退单
    public static final String REFUND_ORDER = "/refundOrder";
    //查询商品列表
    public static final String QUERY_GOODLIST_URL = "/queryGoodsList";
    //查询化妆师忙碌数量
    public static final String QUERY_DRESS_COUNT = "/queryDressCountForApp";
    //门店支付方式
    public static final String QUERY_SHOP_PAY_METHOD = "/queryShopPayMethod";
    //新增套餐服务
    public static final String SAVE_COMBO_URL = "/saveComboForApp";
    //App升级
    public static final String GET_APK_FOR_APP = "/getApkForAPP";
}