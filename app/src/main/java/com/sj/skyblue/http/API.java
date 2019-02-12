package com.sj.skyblue.http;

import android.util.ArrayMap;

import com.jady.retrofitclient.HttpManager;
import com.jady.retrofitclient.callback.HttpCallback;
import com.sj.module_lib.utils.Utils;
import com.sj.skyblue.entity.post.AddOrderServiceBean;
import com.sj.skyblue.entity.post.BindDevice;
import com.sj.skyblue.entity.post.BindEmployee;
import com.sj.skyblue.entity.post.ChangeOrderService;
import com.sj.skyblue.entity.post.DeleteOrderService;
import com.sj.skyblue.entity.post.GetTransationIdBean;
import com.sj.skyblue.entity.post.OrderComboAdd;
import com.sj.skyblue.entity.post.OrderServiceRefund;
import com.sj.skyblue.entity.post.PayBackBean;
import com.sj.skyblue.entity.post.PayRefundBean;
import com.sj.skyblue.entity.post.QueryShop;
import com.sj.skyblue.entity.post.ShoPwdBean;
import com.sj.skyblue.entity.post.TokenBean;
import com.sj.skyblue.entity.post.UpdateOrderBean;
import com.sj.skyblue.manager.SPManager;
import com.sj.skyblue.utils.SystemUtil;

import java.util.Map;

/**
 * Created by Sunj on 2018/7/7.
 */

public class API {

    /**
     * 设备绑定
     *
     * @param terminalCode
     * @param callback
     */
    public static void deviceBindToShop(String terminalCode, HttpCallback callback) {
        BindDevice bindDevice = new BindDevice.Builder().deviceId(SystemUtil.getDeviceSN()).terminalCode(terminalCode).build();
        HttpManager.postByBody(UrlConfig.DEVICE_BIND_SHOP, bindDevice, callback);
    }

    /**
     * 查询绑定设备门店信息
     *
     * @param callback
     */
    public static void queryShopInfo(HttpCallback callback) {
        QueryShop queryShop = new QueryShop(SystemUtil.getDeviceSN());
        HttpManager.postByBody(UrlConfig.QUERY_SHOP_INFO, queryShop, callback);
    }

    /**
     * 密码登录
     *
     * @param phoneNum
     * @param pwd
     * @param callback
     */
    public static void appEmployeeLogin(String phoneNum, String pwd, HttpCallback callback) {
        Map<String, Object> parameters = new ArrayMap<>();
        parameters.put("name", phoneNum);
        parameters.put("password", pwd);
        parameters.put("deviceId", SystemUtil.getDeviceSN());
        HttpManager.get(UrlConfig.LOGIN_URL, parameters, callback);
    }

    /**
     * 开班/下班/交班
     *
     * @param status
     * @param callback
     */
    public static void updateEmployeeWorkStatus(String status, HttpCallback callback) {
        Map<String, Object> parameters = new ArrayMap<>();
        parameters.put("status", status);
        parameters.put("shopId", SPManager.getInstance().getShopId());
        parameters.put("token", SPManager.getInstance().getTokenId());
        HttpManager.get(UrlConfig.UPDATE_WORKSTATUS_URL, parameters, callback);
    }

    /**
     * 查询预约单列表
     *
     * @param callback
     */
    @Deprecated
    public static void queryAppointmentList(String firstIndex, String phone, HttpCallback callback) {
        Map<String, Object> parameters = new ArrayMap<>();
        parameters.put("firstIndex", firstIndex == null ? "" : firstIndex);
        parameters.put("pageNum", 10);
        parameters.put("phone", phone == null ? "" : phone);
        parameters.put("token", SPManager.getInstance().getTokenId());
        parameters.put("shopId", SPManager.getInstance().getShopId());
        HttpManager.get(UrlConfig.QUERY_APPOINTMENT_LIST_URL, parameters, callback);
    }

    /**
     * 查询订单列表
     *
     * @param callback
     */
    public static void queryOrderList(HttpCallback callback) {
        TokenBean tokenBean = new TokenBean(SPManager.getInstance().getTokenId());
        HttpManager.postByBody(UrlConfig.QUERY_APPOINT_GRADE, tokenBean, callback);
    }

    /**
     * 查询商品列表
     *
     * @param callback
     */
    public static void queryGoodList(HttpCallback callback) {
        TokenBean tokenBean = new TokenBean(SPManager.getInstance().getTokenId());
        HttpManager.postByBody(UrlConfig.QUERY_GOODLIST_URL, tokenBean, callback);
    }

    /**
     * 查询化妆师忙碌数量
     *
     * @param callback
     */
    public static void queryDressCount(HttpCallback callback) {
        TokenBean tokenBean = new TokenBean(SPManager.getInstance().getTokenId());
        HttpManager.postByBody(UrlConfig.QUERY_DRESS_COUNT, tokenBean, callback);
    }

    /**
     * 查询门店支付方式
     *
     * @param callback
     */
    public static void queryShopPayMethod(HttpCallback callback) {
        TokenBean tokenBean = new TokenBean(SPManager.getInstance().getTokenId());
        HttpManager.postByBody(UrlConfig.QUERY_SHOP_PAY_METHOD, tokenBean, callback);
    }

    /**
     * 查询服务列表
     *
     * @param callback
     */
    public static void queryServiceList(HttpCallback callback) {
        Map<String, Object> parameters = new ArrayMap<>();
        parameters.put("shopId", SPManager.getInstance().getShopId());
        parameters.put("token", SPManager.getInstance().getTokenId());
        HttpManager.get(UrlConfig.QUERY_SERVICELIST_URL, parameters, callback);
    }

    /**
     * 查询订单详情
     *
     * @param appointmentId
     * @param callback
     */
    public static void queryOrderDetailById(String appointmentId, HttpCallback callback) {
        Map<String, Object> parameters = new ArrayMap<>();
        parameters.put("shopId", SPManager.getInstance().getShopId());
        parameters.put("orderId", appointmentId);
        parameters.put("token", SPManager.getInstance().getTokenId());
        HttpManager.get(UrlConfig.QUERY_ORDERDETAIL_BY_ID_URL, parameters, callback);
    }

    /**
     * 新建订单
     *
     * @param phone
     * @param serviceId
     * @param callback
     */
    public static void appSaveOrder(String time, String phone, String serviceId, HttpCallback callback) {
        Map<String, Object> parameters = new ArrayMap<>();
        parameters.put("shopId", SPManager.getInstance().getShopId());
        parameters.put("serviceId", serviceId);
        parameters.put("token", SPManager.getInstance().getTokenId());
        parameters.put(Utils.isMobile(phone) ? "phone" : "cusName", phone);
        parameters.put("time", time);
        HttpManager.get(UrlConfig.APP_SAVE_ORDER_URL, parameters, callback);
    }

    /**
     * 更新订单信息
     *
     * @param updateOrderBean
     * @param callback
     */
    public static void updateOrderInfo(UpdateOrderBean updateOrderBean, HttpCallback callback) {
        HttpManager.postByBody(UrlConfig.UPDATE_ORDER_INFO_URL, updateOrderBean, callback);
    }

    /**
     * 更新订单支付状态信息
     *
     * @param payBackBean
     * @param callback
     */
    @Deprecated
    public static void paidUpdate(PayBackBean payBackBean, HttpCallback callback) {
        HttpManager.postByBody(UrlConfig.PAID_UPDATE_URL, payBackBean, callback);
    }

    /**
     * 退款退单
     *
     * @param payRefundBean
     * @param callback
     */
    public static void paidRefund(PayRefundBean payRefundBean, HttpCallback callback) {
        HttpManager.postByBody(UrlConfig.PAID_REFUND_URL, payRefundBean, callback);
    }


    /**
     * 获取交易流水号接口
     *
     * @param getTransationIdBean
     * @param callback
     */
    public static void getTransactionId(GetTransationIdBean getTransationIdBean, HttpCallback callback) {
        HttpManager.postByBody(UrlConfig.GET_TRANSACTION_ID_URL, getTransationIdBean, callback);
    }

    /**
     * 折扣验证密码接口
     *
     * @param shoPwdBean
     * @param callback
     */
    public static void checkShopPwd(ShoPwdBean shoPwdBean, HttpCallback callback) {
        HttpManager.postByBody(UrlConfig.CHECK_SHOP_URL, shoPwdBean, callback);
    }

    /**
     * 订单添加服务
     *
     * @param addOrderServiceBean
     * @param callback
     */
    public static void addOrderService(AddOrderServiceBean addOrderServiceBean, HttpCallback callback) {
        HttpManager.postByBody(UrlConfig.SAVA_ORDER_SERVICE_URL, addOrderServiceBean, callback);
    }

    /**
     * 店长和前台列表
     *
     * @param callback
     */
    public static void queryStageAndShopList(HttpCallback callback) {
        Map<String, Object> parameters = new ArrayMap<>();
        parameters.put("token", SPManager.getInstance().getTokenId());
        parameters.put("shopId", SPManager.getInstance().getShopId());
        HttpManager.get(UrlConfig.QUERY_STAGE_AND_SHOP_LIST, parameters, callback);
    }

    /**
     * 订单绑定人员
     *
     * @param callback
     */
    public static void orderBindEmployee(String orderId, String employeeId, HttpCallback callback) {
        BindEmployee bindEmployee = new BindEmployee(SPManager.getInstance().getTokenId(), orderId, employeeId);
        HttpManager.postByBody(UrlConfig.ORDER_BIND_EMPLOYEE, bindEmployee, callback);
    }

    /**
     * 订单服务修改服务
     *
     * @param callback
     */
    public static void changeOrderService(String orderId, String orderServiceId, String serviceId, HttpCallback callback) {
        ChangeOrderService changeOrderService = new ChangeOrderService.Builder()
                .orderId(orderId).serviceId(serviceId).token(SPManager.getInstance().getTokenId()).orderserviceid(orderServiceId).build();
        HttpManager.postByBody(UrlConfig.CHANGE_ORDER_SERVICE, changeOrderService, callback);
    }

    /**
     * 订单删除
     *
     * @param callback
     */
    public static void deleteOrderService(String id, int type, HttpCallback callback) {
        DeleteOrderService deleteOrderService = new DeleteOrderService(SPManager.getInstance().getTokenId(), id, type);
        HttpManager.postByBody(UrlConfig.DELETE_ORDER_SERVICE, deleteOrderService, callback);
    }

    /**
     * 订单退单
     *
     * @param callback
     */
    public static void refundOrder(String id, int type, int count, HttpCallback callback) {
        OrderServiceRefund orderServiceRefund = new OrderServiceRefund(SPManager.getInstance().getTokenId(), id, type, count);
        HttpManager.postByBody(UrlConfig.REFUND_ORDER, orderServiceRefund, callback);
    }

    /**
     * 新增套餐服务
     *
     * @param callback
     */
    public static void saveComboForApp(String orderId, String comboId, HttpCallback callback) {
        OrderComboAdd orderComboAdd = new OrderComboAdd(SPManager.getInstance().getTokenId(), orderId, comboId);
        HttpManager.postByBody(UrlConfig.SAVE_COMBO_URL, orderComboAdd, callback);
    }

    /**
     * 查询预约单列表
     *
     * @param versionCode
     * @param callback
     */
    public static void queryApp(int versionCode, HttpCallback callback) {
        Map<String, Object> parameters = new ArrayMap<>();
        parameters.put("versionCode", versionCode);
        HttpManager.get(UrlConfig.GET_APK_FOR_APP, parameters, callback);
    }
}
