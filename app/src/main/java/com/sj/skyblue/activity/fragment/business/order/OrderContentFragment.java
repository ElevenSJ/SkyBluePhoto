package com.sj.skyblue.activity.fragment.business.order;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.makeramen.roundedimageview.RoundedImageView;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.http.BaseResponse;
import com.sj.module_lib.http.CommonCallback;
import com.sj.module_lib.http.ServerResultBack;
import com.sj.module_lib.utils.CalculateUtils;
import com.sj.module_lib.utils.DateUtils;
import com.sj.module_lib.utils.StringUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.module_lib.widgets.AmountView;
import com.sj.skyblue.R;
import com.sj.skyblue.activity.adapter.OrderServiceRyvAdapter;
import com.sj.skyblue.activity.base.BaseFragment;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.EmployeeBase;
import com.sj.skyblue.entity.EmployeesEntity;
import com.sj.skyblue.entity.GoodEntity;
import com.sj.skyblue.entity.GoodServiceEntity;
import com.sj.skyblue.entity.OrderCombo;
import com.sj.skyblue.entity.OrderDetailEntity;
import com.sj.skyblue.entity.OrderInfoCombo;
import com.sj.skyblue.entity.PayMethod;
import com.sj.skyblue.entity.ServiceEntity;
import com.sj.skyblue.entity.TransationId;
import com.sj.skyblue.entity.order.OrderInfo;
import com.sj.skyblue.entity.order.OrderPhoto;
import com.sj.skyblue.entity.order.OrderPhotoService;
import com.sj.skyblue.entity.order.OrderProduct;
import com.sj.skyblue.entity.order.OrderProductService;
import com.sj.skyblue.entity.order.PhotoServiceItem;
import com.sj.skyblue.entity.order.Vip;
import com.sj.skyblue.entity.order.base.OrderBase;
import com.sj.skyblue.entity.order.base.OrderServiceBase;
import com.sj.skyblue.entity.payment.request.ReqPay;
import com.sj.skyblue.entity.post.AddOrderServiceBean;
import com.sj.skyblue.entity.post.AddProductBean;
import com.sj.skyblue.entity.post.GetTransationIdBean;
import com.sj.skyblue.entity.post.PayBackBean;
import com.sj.skyblue.entity.post.ShoPwdBean;
import com.sj.skyblue.entity.post.UpdateOrderBean;
import com.sj.skyblue.event.OrderChangeEvent;
import com.sj.skyblue.event.OrderDeleteEvent;
import com.sj.skyblue.event.OrderPayRefundEvent;
import com.sj.skyblue.event.OrderServicePriceEvent;
import com.sj.skyblue.event.OrderShowEvent;
import com.sj.skyblue.event.PresentationChangeEvent;
import com.sj.skyblue.http.API;
import com.sj.skyblue.manager.SPManager;
import com.sj.skyblue.manager.ThreadPoolManager;
import com.sj.skyblue.manager.handler.OrderDetailHandler;
import com.sj.skyblue.task.PayMethodGetTask;
import com.sj.skyblue.utils.DialogUtil;
import com.sj.skyblue.utils.sunmi.AidlUtil;
import com.sj.skyblue.view.AddComboDialogView;
import com.sj.skyblue.view.AddServiceDialogView;
import com.sj.skyblue.view.BindEmployeeDialogView;
import com.sj.skyblue.view.SqbPayDialogView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;

/**
 * 订单详情页面
 * Created by Sunj on 2018/11/4.
 */

public class OrderContentFragment extends BaseFragment {

    String appointmentEntityId;
    @BindView(R.id.tv_orderId)
    TextView tvOrderId;
    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.tv_appointment_time)
    TextView tvAppointmentTime;
    @BindView(R.id.tv_employee_name)
    TextView tvEmployeeName;
    @BindView(R.id.tv_user_info)
    TextView tvUserInfo;
    @BindView(R.id.tv_pre_pay)
    TextView tvPrePay;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_combo)
    TextView tvCombo;
    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;
    @BindView(R.id.img_head)
    RoundedImageView imgHead;
    @BindView(R.id.tv_vip_name)
    TextView tvVipName;
    @BindView(R.id.tv_vip_info)
    TextView tvVipInfo;
    @BindView(R.id.tv_add_combo)
    TextView tvAddCombo;

    OrderServiceRyvAdapter mAdapter;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_paid_price)
    TextView tvPaidPrice;
    @BindView(R.id.tv_combo_price)
    TextView tvComboPrice;
    @BindView(R.id.tv_discount_price)
    TextView tvDiscountPrice;
    @BindView(R.id.tv_need_pay)
    TextView tvNeedPay;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.radio_group)
    RadioGroup rdPayGroup;

    List<PayMethod> payMethodList;
    OrderDetailEntity orderDetailEntity;
    String payType;
//    Map<String, OrderServicePriceEvent> priceMap = new HashMap<>();

    double totalPrice = 0d;
    double paidPrice = 0d;
    double needPayPrice = 0d;
    double discount = 0d;
    double comboPrice = 0d;
    double prePrice = 0d;

    String employeeBaseName;

    public static OrderContentFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString("data", id);
        OrderContentFragment fragment = new OrderContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        appointmentEntityId = getArguments().getString("data");
    }

    @Override
    public void onLazyInitView(Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        EventBus.getDefault().register(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(layoutManager);
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.transparent), 20, 0, 0);
        dividerDecoration.setDrawLastItem(true);
        rylView.addItemDecoration(dividerDecoration);
        rylView.showEmpty();
        if (!StringUtils.isEmpty(appointmentEntityId)) {
            post(new Runnable() {
                @Override
                public void run() {
                    getPayMethod();
                    getOrderDetail();
                }
            });
        }
    }

    /**
     * 获取支付方式
     */
    private void getPayMethod() {
        new PayMethodGetTask() {
            @Override
            protected void onPostExecute(List<PayMethod> payMethods) {
                Logger.i(payMethods == null ? "读取本地序列化支付方式失败" : "读取本地序列化支付方式成功");
                payMethodList = payMethods;
                if (payMethodList != null && !payMethodList.isEmpty()) {
                    for (PayMethod payMethod : payMethodList) {
                        rdPayGroup.findViewWithTag(payMethod.getCode()).setVisibility(View.VISIBLE);
                    }
                }
            }
        }.execute();
    }

    private void getOrderDetail(ServerResultBack serverResultBack) {
        promptDialog.showLoading("正在加载");
        API.queryOrderDetailById(appointmentEntityId, serverResultBack);
    }

    private void getOrderDetail() {
        promptDialog.showLoading("正在加载");
        API.queryOrderDetailById(appointmentEntityId, new ServerResultBack() {
            @Override
            public void onSuccess(Object data) {
                orderDetailEntity = (OrderDetailEntity) data;
                initView();
                OrderDetailHandler.getInstance().handler(orderDetailEntity);
                updateListView();
            }

            @Override
            public Type getType() {
                return new TypeReference<BaseResponse<OrderDetailEntity>>() {
                }.getType();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                promptDialog.dismiss();
            }
        });
    }


    private void initView() {
//        priceMap.clear();
        if (orderDetailEntity == null) {
            return;
        }
        OrderInfo orderInfo = orderDetailEntity.getOrderInfo();
        if (orderInfo != null) {
            employeeBaseName = orderInfo.getEmployeeName();
            tvOrderId.setText("订单编号：" + orderInfo.getOrderId());
            tvOrderStatus.setText(Constants.orderPayStatus.get(orderInfo.getStatus()));
            if (Constants.orderPayStatus.containsKey(orderInfo.getStatus())) {
                tvOrderStatus.setBackground(getContext().getResources().getDrawable(Constants.orderPayStatusBg.get(orderInfo.getStatus())));
            }
            tvEmployeeName.setText("收银员：" + orderInfo.getEmployeeName());
            if (StringUtils.isEmpty(orderInfo.getAppointmentTime())) {
                tvAppointmentTime.setVisibility(View.GONE);
            } else {
                tvAppointmentTime.setVisibility(View.VISIBLE);
                tvAppointmentTime.setText("预约时间：" + orderInfo.getAppointmentTime());
            }
            tvUserInfo.setVisibility(!StringUtils.isEmpty(orderInfo.getCusName()) || !StringUtils.isEmpty(orderInfo.getPhone()) ? View.VISIBLE : View.GONE);
            tvUserInfo.setText((!StringUtils.isEmpty(orderInfo.getCusName()) ? "称呼：" + orderInfo.getCusName() : "") + (!StringUtils.isEmpty(orderInfo.getPhone()) ? "  电话：" + orderInfo.getPhone() : ""));
            if (StringUtils.isEmpty(orderInfo.getPaid())) {
                tvPrePay.setVisibility(View.GONE);
            } else {
                tvPrePay.setVisibility(View.VISIBLE);
                tvPrePay.setText("预付定金：¥" + orderInfo.getPaid());
            }
            if (StringUtils.isEmpty(orderInfo.getReceiveAddress())) {
                tvAddress.setVisibility(View.GONE);
            } else {
                tvAddress.setVisibility(View.VISIBLE);
                tvAddress.setText("物流地址：" + orderInfo.getReceiveAddress());
            }
            try {
                if (!StringUtils.isEmpty(orderInfo.getDiscount())) {
                    discount = Double.parseDouble(orderInfo.getDiscount());
                }
            } catch (Exception e) {
                discount = 0f;
                e.printStackTrace();
                Logger.e("折扣价格格式化异常");
            }
            OrderInfoCombo orderCombo = orderInfo.getCombo();
            tvCombo.setVisibility(null != orderCombo && !StringUtils.isEmpty(orderCombo.getName()) ? View.VISIBLE : View.GONE);
            tvAddCombo.setVisibility(null != orderCombo && null != orderCombo.getServiceList() ? View.VISIBLE : View.GONE);
            if (null != orderCombo && !StringUtils.isEmpty(orderCombo.getName())) {
                try {
                    if (!StringUtils.isEmpty(orderCombo.getPrice())) {
                        comboPrice = Double.parseDouble(orderCombo.getPrice());
                    }
                } catch (Exception e) {
                    comboPrice = 0f;
                    e.printStackTrace();
                    Logger.e("套餐优惠价格格式化异常");
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("当前套餐信息：");
                stringBuilder.append(orderCombo.getName());
                if (orderCombo.getServiceList() != null) {
                    stringBuilder.append(" (");
                    for (int i = 0; i < orderCombo.getServiceList().size(); i++) {
                        stringBuilder.append(orderCombo.getServiceList().get(i).getName());
                        if (i < orderCombo.getServiceList().size() - 1) {
                            stringBuilder.append(" + ");
                        }
                    }
                    stringBuilder.append(" )");
                }
                tvCombo.setText(stringBuilder.toString());
            }
            tvComboPrice.setText("优惠：- ¥" + comboPrice);
            tvDiscountPrice.setText("折扣：- ¥" + discount);
            etRemark.setText(StringUtils.isNullString(orderInfo.getRemark()) ? "" : orderInfo.getRemark());
        }
        Vip vip = orderDetailEntity.getVip();
        if (vip != null) {
            tvVipInfo.setText("姓名：" + vip.getName());
        }
        List<OrderCombo> orderComboList = orderDetailEntity.getCombo();
        tvAddCombo.setVisibility(null != orderComboList && !orderComboList.isEmpty() ? View.VISIBLE : View.GONE);

    }

    private void updateListView() {
        mAdapter = new OrderServiceRyvAdapter(_mActivity);
        mAdapter.addAll(OrderDetailHandler.getInstance().getOrderServiceList());
        rylView.setAdapter(mAdapter);
    }


    @Subscribe
    public void deleteOrder(final OrderDeleteEvent event) {
        if (!StringUtils.isEmpty(event.id)) {
            API.deleteOrderService(event.id, event.type, new CommonCallback() {
                @Override
                public void onSuccess(String message) {
                    Logger.i(message);
                    updateOrderPrice(null);
                }
            });
        }
        if (Constants.TYPE_PAYBACK_SERVICE == event.type) {
            OrderDetailHandler.getInstance().getOrderServiceList().remove(event.positon);
            updateListView();
        }
    }

    @Subscribe
    public void updateOrderPrice(OrderServicePriceEvent event) {
//        if (event != null) {
//            priceMap.put(event.getId(), event);
//        }
        totalPrice = 0d;
        paidPrice = 0d;
        needPayPrice = 0d;
        prePrice = 0d;
//        for (Map.Entry<String, OrderServicePriceEvent> entry : priceMap.entrySet()) {
//            OrderServicePriceEvent priceEvent = entry.getValue();
//            totalPrice = CalculateUtils.add(totalPrice + "", Double.toString(CalculateUtils.mul(priceEvent.getPrice(), priceEvent.getCount())));
//            if (!Constants.PAY_STATUS_NOT.equals(event.getStatus())) {
//                paidPrice = CalculateUtils.add(paidPrice + "", Double.toString(CalculateUtils.mul(priceEvent.getPrice(), priceEvent.getCount())));
//            } else {
//                needPayPrice = CalculateUtils.add(needPayPrice + "", Double.toString(CalculateUtils.mul(priceEvent.getPrice(), priceEvent.getCount())));
//            }
//        }
        List<OrderBase> orderBaseList = new ArrayList<>();
        try {
            for (OrderServiceBase orderServiceBase : OrderDetailHandler.getInstance().getOrderServiceList()) {
                if (orderServiceBase instanceof OrderPhotoService) {
                    prePrice = CalculateUtils.add(prePrice + "", ((OrderPhotoService) orderServiceBase).getPrePrice());
                    for (int i = 0; i < ((OrderPhotoService) orderServiceBase).getPhotoServiceItems().size(); i++) {
                        PhotoServiceItem photoServiceItem = ((OrderPhotoService) orderServiceBase).getPhotoServiceItems().get(i);
                        OrderPhoto baseOrderPhoto = photoServiceItem.getBasePhotoItem();
                        totalPrice = CalculateUtils.add(totalPrice + "", Double.toString(CalculateUtils.mul(baseOrderPhoto.getPrice(), Integer.toString(baseOrderPhoto.getCount()))));
                        if (Constants.PAY_STATUS_NOT.equals(baseOrderPhoto.getStatus())) {
                            needPayPrice = CalculateUtils.add(needPayPrice + "", Double.toString(CalculateUtils.mul(baseOrderPhoto.getPrice(), Integer.toString(baseOrderPhoto.getCount()))));
                        } else {
                            paidPrice = CalculateUtils.add(paidPrice + "", Double.toString(CalculateUtils.mul(baseOrderPhoto.getPrice(), Integer.toString(baseOrderPhoto.getCount()))));
                        }
                        for (OrderPhoto orderPhoto : photoServiceItem.getPhotoItems()) {
                            totalPrice = CalculateUtils.add(totalPrice + "", Double.toString(CalculateUtils.mul(orderPhoto.getPrice(), Integer.toString(orderPhoto.getCount()))));
                            if (Constants.PAY_STATUS_NOT.equals(orderPhoto.getStatus())) {
                                needPayPrice = CalculateUtils.add(needPayPrice + "", Double.toString(CalculateUtils.mul(orderPhoto.getPrice(), Integer.toString(orderPhoto.getCount()))));
                            } else {
                                paidPrice = CalculateUtils.add(paidPrice + "", Double.toString(CalculateUtils.mul(orderPhoto.getPrice(), Integer.toString(orderPhoto.getCount()))));
                            }
                        }
                        orderBaseList.add(baseOrderPhoto);
                        orderBaseList.addAll(photoServiceItem.getPhotoItems());
                    }
                } else if (orderServiceBase instanceof OrderProductService) {
                    for (OrderProduct orderProduct : ((OrderProductService) orderServiceBase).getOrderProductList()) {
                        totalPrice = CalculateUtils.add(totalPrice + "", Double.toString(CalculateUtils.mul(orderProduct.getPrice(), Integer.toString(orderProduct.getCount()))));
                        if (Constants.PAY_STATUS_NOT.equals(orderProduct.getStatus())) {
                            needPayPrice = CalculateUtils.add(needPayPrice + "", Double.toString(CalculateUtils.mul(orderProduct.getPrice(), Integer.toString(orderProduct.getCount()))));
                        } else {
                            paidPrice = CalculateUtils.add(paidPrice + "", Double.toString(CalculateUtils.mul(orderProduct.getPrice(), Integer.toString(orderProduct.getCount()))));
                        }
                    }
                    orderBaseList.addAll(((OrderProductService) orderServiceBase).getOrderProductList());
                }
            }
        } catch (Exception e) {
            ToastUtils.showShortToast("价格异常，请检查服务");
        }
        double mulPrice = CalculateUtils.add(Double.toString(comboPrice), Double.toString(prePrice));
        needPayPrice = CalculateUtils.sub(Double.toString(CalculateUtils.sub(Double.toString(needPayPrice), Double.toString(discount))), Double.toString(mulPrice));
        tvTotalPrice.setText("总额：¥" + totalPrice);
        tvPaidPrice.setText("已付：- ¥" + paidPrice);
        tvComboPrice.setText("优惠：- ¥" + mulPrice);
        tvNeedPay.setText("应收：¥" + (needPayPrice <= 0d ? 0d : needPayPrice));

        OrderShowEvent orderShowEvent = new OrderShowEvent.Builder()
                .orderBaseList(orderBaseList)
                .totalPrice(totalPrice)
                .paidPrice(paidPrice)
                .comboPrice(mulPrice)
                .discount(discount)
                .needPayPrice((needPayPrice <= 0d ? 0d : needPayPrice))
                .build();
        EventBus.getDefault().post(orderShowEvent);
    }


    @Override
    public int getContentView() {
        return R.layout.fragment_order_content;
    }

    /**
     * 选择套餐
     */
    public void showAddComboDialog() {
        DialogUtil.getInstance().showBigViewDialog(_mActivity, R.layout.dialog_add_combo, new DialogUtil.ViewInterface() {
            @Override
            public void getChildView(AlertDialog dialog, View view, int layoutResId) {
                AddComboDialogView addComboDialogView = new AddComboDialogView(_mActivity, dialog, view);
                addComboDialogView.initModule(orderDetailEntity.getCombo());
                addComboDialogView.setIItemSelectLisenter(new AddComboDialogView.IItemSelectLisenter() {
                    @Override
                    public void callback(final OrderCombo orderCombo) {
                        promptDialog.showLoading("正在创建套餐");
                        API.saveComboForApp(orderDetailEntity.getOrderInfo().getOrderId(), orderCombo.getId(), new CommonCallback() {
                            @Override
                            public void onSuccess(String message) {
                                getOrderDetail();
                            }

                            @Override
                            public void onFinish() {
                                super.onFinish();
                                promptDialog.dismiss();
                            }
                        });
                    }
                });
            }
        });
    }

    /**
     * 绑定收银员dialog
     */
    public void showEmployeeDialog() {
        promptDialog.showLoading("正在加载");
        API.queryStageAndShopList(new ServerResultBack() {
            @Override
            public void onSuccess(Object data) {
                final EmployeesEntity employeesEntity = (EmployeesEntity) data;
                DialogUtil.getInstance().showBigViewDialog(_mActivity, R.layout.dialog_bind_employee, new DialogUtil.ViewInterface() {
                    @Override
                    public void getChildView(AlertDialog dialog, View view, int layoutResId) {
                        BindEmployeeDialogView bindEmployeeDialogView = new BindEmployeeDialogView(_mActivity, dialog, view);
                        bindEmployeeDialogView.initModule(employeesEntity.getStages(), employeesEntity.getShops());
                        bindEmployeeDialogView.setIItemSelectLisenter(new BindEmployeeDialogView.IItemSelectLisenter() {
                            @Override
                            public void callback(final EmployeeBase employeeBase) {
                                promptDialog.showLoading("正在绑定");
                                API.orderBindEmployee(orderDetailEntity.getOrderInfo().getOrderId(), employeeBase.getId(), new CommonCallback() {
                                    @Override
                                    public void onSuccess(String message) {
                                        promptDialog.showSuccessDelay(message, 500);
                                        employeeBaseName = employeeBase.getName();
                                        orderDetailEntity.getOrderInfo().setEmployeeName(employeeBaseName);
                                        tvEmployeeName.setText("收银员：" + employeeBaseName);
                                    }

                                    @Override
                                    public void onFinish() {
                                        super.onFinish();
                                        promptDialog.dismiss();
                                    }
                                });
                            }
                        });
                    }
                });
            }

            @Override
            public Type getType() {
                return new TypeReference<BaseResponse<EmployeesEntity>>() {
                }.getType();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                promptDialog.dismiss();
            }
        });
    }

    public void showAddServiceDialog(final int type, final String orderServiceId) {
        promptDialog.showLoading("正在加载");
        API.queryServiceList(new ServerResultBack() {
            @Override
            public void onSuccess(Object data) {
                final GoodServiceEntity goodServiceEntity = (GoodServiceEntity) data;
                DialogUtil.getInstance().showBigViewDialog(_mActivity, R.layout.dialog_add_service, new DialogUtil.ViewInterface() {
                    @Override
                    public void getChildView(AlertDialog dialog, View view, int layoutResId) {
                        AddServiceDialogView addServiceDialogView = new AddServiceDialogView(_mActivity, dialog, view, type);

                        addServiceDialogView.initModule(goodServiceEntity.getServices(), goodServiceEntity.getProducts());
                        addServiceDialogView.setIServiceSelectLisenter(new AddServiceDialogView.IServiceSelectLisenter() {
                            @Override
                            public void callback(ServiceEntity serviceEntity, List<GoodEntity> goodEntityList) {
                                Logger.d("has Service = " + (serviceEntity != null ? serviceEntity.getId() : "null") + ",goodEntityList size = " + goodEntityList.size());
                                if (type == 1) {
                                    AddOrderServiceBean addOrderServiceBean = new AddOrderServiceBean();
                                    addOrderServiceBean.setToken(SPManager.getInstance().getTokenId());
                                    addOrderServiceBean.setOrderId(orderDetailEntity.getOrderInfo().getOrderId());
                                    if (serviceEntity != null) {
                                        addOrderServiceBean.setServiceId(serviceEntity.getId());
                                    }
                                    List<AddProductBean> addProductBeanList = new ArrayList<>();
                                    for (GoodEntity goodEntity : goodEntityList) {
                                        AddProductBean addProductBean = new AddProductBean();
                                        addProductBean.setCount("1");
                                        addProductBean.setId(goodEntity.getId());
                                        addProductBean.setName(goodEntity.getName());
                                        addProductBean.setPrice(goodEntity.getGoodPrice());
                                        addProductBean.setType(goodEntity.getType());
                                        addProductBeanList.add(addProductBean);
                                    }
                                    addOrderServiceBean.setProducts(addProductBeanList);
                                    promptDialog.showLoading("正在添加");
                                    API.addOrderService(addOrderServiceBean, new CommonCallback() {
                                        @Override
                                        public void onSuccess(String message) {
                                            promptDialog.showSuccessDelay(message, 500);
                                            getOrderDetail();
                                        }

                                        @Override
                                        public void onFinish() {
                                            super.onFinish();
                                            promptDialog.dismiss();
                                        }
                                    });
                                } else {
                                    promptDialog.showLoading("正在更换");
                                    API.changeOrderService(orderDetailEntity.getOrderInfo().getOrderId(), orderServiceId, serviceEntity.getId(), new CommonCallback() {
                                        @Override
                                        public void onSuccess(String message) {
                                            promptDialog.showSuccessDelay(message, 500);
                                            getOrderDetail();
                                        }

                                        @Override
                                        public void onFinish() {
                                            super.onFinish();
                                            promptDialog.dismiss();
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
            }

            @Override
            public Type getType() {
                return new TypeReference<BaseResponse<GoodServiceEntity>>() {
                }.getType();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                promptDialog.dismiss();
            }
        });
    }


    @OnClick({R.id.layout_main, R.id.tv_add_combo, R.id.tv_bind, R.id.tv_add_service, R.id.bt_add_discount, R.id.rt_cash, R.id.rt_card, R.id.rt_ailipay, R.id.rt_wechat, R.id.bt_reback_money, R.id.bt_pay, R.id.bt_print_order})
    public void onViewClicked(View view) {
        if (orderDetailEntity == null) {
            promptDialog.showError("未找到该订单");
            return;
        }
        EventBus.getDefault().post(new PresentationChangeEvent(Constants.CUSTOMER_DISPLAY_ORDER));
        updateOrderPrice(null);
        switch (view.getId()) {
            case R.id.tv_bind:
                showEmployeeDialog();
                break;
            case R.id.tv_add_combo:
                showAddComboDialog();
                break;
            case R.id.tv_add_service:
                updateOrderInfo(2);
                break;
            case R.id.bt_add_discount:
                showAddDiscounteDialog();
                break;
            case R.id.rt_cash:
                payType = Constants.PAYCHANNEL_CASH;
                break;
            case R.id.rt_card:
                payType = Constants.PAYCHANNEL_CARD;
                break;
            case R.id.rt_ailipay:
                payType = Constants.PAYCHANNEL_ALIPAY;
                break;
            case R.id.rt_wechat:
                payType = Constants.PAYCHANNEL_WXPAY;
                break;
            case R.id.bt_reback_money:
                showPayBackDialog();
                break;
            case R.id.bt_pay:
                if (needPayPrice <= 0f) {
                    ToastUtils.showShortToast("当前订单无需付款");
                    return;
                }
                if (StringUtils.isEmpty(payType)) {
                    ToastUtils.showShortToast("请选择一种支付方式");
                    return;
                }
                if (StringUtils.isEmpty(employeeBaseName)) {
                    ToastUtils.showShortToast("请绑定收银人员");
                    return;
                }
                updateOrderInfo(1);
                break;
            case R.id.bt_print_order:
                printOrder();
                break;
        }
    }

    private void updateOrderInfo(final int type) {
        promptDialog.showLoading("正在更新订单");
        UpdateOrderBean updateOrderBean = new UpdateOrderBean();
        updateOrderBean.setToken(SPManager.getInstance().getTokenId());
        UpdateOrderBean.OrderInfoBean orderInfoBean = new UpdateOrderBean.OrderInfoBean();
        orderInfoBean.setDiscount(discount + "");
        orderInfoBean.setId(orderDetailEntity.getOrderInfo().getOrderId());
        orderInfoBean.setRemark(etRemark.getText().toString());
        updateOrderBean.setOrderInfo(orderInfoBean);
        List<UpdateOrderBean.ServicesBean> servicesBeanList = new ArrayList<>();
        for (OrderServiceBase orderServiceBase : OrderDetailHandler.getInstance().getOrderServiceList()) {
            if (orderServiceBase instanceof OrderPhotoService) {
                UpdateOrderBean.ServicesBean servicesBean = new UpdateOrderBean.ServicesBean();
                servicesBean.setOrderServiceId(((OrderPhotoService) orderServiceBase).getServiceOrderId());
                servicesBean.setServiceId(((OrderPhotoService) orderServiceBase).getServiceId());
                List<UpdateOrderBean.ServicesBean.BasePrintBean> extPrintList = new ArrayList<>();
                for (int i = 0; i < ((OrderPhotoService) orderServiceBase).getPhotoServiceItems().size(); i++) {
                    PhotoServiceItem photoServiceItem = ((OrderPhotoService) orderServiceBase).getPhotoServiceItems().get(i);
                    UpdateOrderBean.ServicesBean.BasePrintBean basePrintBean = new UpdateOrderBean.ServicesBean.BasePrintBean();
                    if (!StringUtils.isEmpty(photoServiceItem.getId()) && !photoServiceItem.getId().startsWith("empty_")) {
                        basePrintBean.setId(photoServiceItem.getId());
                    }
                    basePrintBean.setColor(photoServiceItem.getBasePhotoItem().getColor());
                    basePrintBean.setPrice(photoServiceItem.getBasePhotoItem().getPrice());
                    basePrintBean.setSize(photoServiceItem.getBasePhotoItem().getSize());
                    basePrintBean.setCount(photoServiceItem.getBasePhotoItem().getCount());
                    basePrintBean.setPeopleCount(photoServiceItem.getBasePhotoItem().getPeopleCount());
                    List<UpdateOrderBean.ServicesBean.BasePrintBean.AddPrintBean> addPrintBeanList = new ArrayList<>();
                    for (OrderPhoto orderPhoto : photoServiceItem.getPhotoItems()) {
                        UpdateOrderBean.ServicesBean.BasePrintBean.AddPrintBean addPrintBean = new UpdateOrderBean.ServicesBean.BasePrintBean.AddPrintBean();
                        if (!StringUtils.isEmpty(orderPhoto.getId()) && !orderPhoto.getId().startsWith("empty_")) {
                            addPrintBean.setId(orderPhoto.getId());
                        }
                        addPrintBean.setColor(orderPhoto.getColor());
                        addPrintBean.setSize(orderPhoto.getSize());
                        addPrintBean.setPrice(orderPhoto.getPrice());
                        addPrintBean.setCount(orderPhoto.getCount());
                        addPrintBeanList.add(addPrintBean);
                    }
                    basePrintBean.setAddPrint(addPrintBeanList);
                    if (photoServiceItem.getIsExtra().equals("0")) {
                        servicesBean.setBasePrint(basePrintBean);
                    } else {
                        extPrintList.add(basePrintBean);
                    }
                }
                servicesBean.setExtPrint(extPrintList);
                servicesBeanList.add(servicesBean);
            } else if (orderServiceBase instanceof OrderProductService) {
                updateOrderBean.setProducts(((OrderProductService) orderServiceBase).getOrderProductList());
            }
        }
        updateOrderBean.setServices(servicesBeanList);
        API.updateOrderInfo(updateOrderBean, new CommonCallback() {
            @Override
            public void onSuccess(String message) {
                getOrderDetail(new ServerResultBack() {
                    @Override
                    public void onSuccess(Object data) {
                        orderDetailEntity = (OrderDetailEntity) data;
                        initView();
                        OrderDetailHandler.getInstance().handler(orderDetailEntity);
                        updateListView();
                        if (type == 1) {
                            getTransation();
                        } else {
                            showAddServiceDialog(1, null);
                        }
                    }

                    @Override
                    public Type getType() {
                        return new TypeReference<BaseResponse<OrderDetailEntity>>() {
                        }.getType();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        promptDialog.dismiss();
                    }
                });

            }

            @Override
            public void onFailed(String error_code, String error_message) {
                super.onFailed(error_code, error_message);
                promptDialog.dismiss();
            }
        });
    }

    private void showPayBackDialog() {

        DialogUtil.getInstance().showSureViewDialog(_mActivity, R.layout.dialog_pay_back, new DialogUtil.ViewInterface() {

            @Override
            public void getChildView(AlertDialog dialog, View view, int layoutResId) {
                List<String> prices = getOrderPrice();
                TextView tvPrices = view.findViewById(R.id.tv_price);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("现金：¥" + prices.get(0));
                stringBuilder.append("\n");
                stringBuilder.append("银联卡：¥" + prices.get(1));
                stringBuilder.append("\n");
                stringBuilder.append("支付宝：¥" + prices.get(2));
                stringBuilder.append("\n");
                stringBuilder.append("微信：¥" + prices.get(3));
                tvPrices.setText(stringBuilder.toString());
            }
        }, new DialogUtil.OnSureListener() {
            @Override
            public void callBack(View view, final DialogInterface dialog) {
                dialog.dismiss();
                API.refundOrder(orderDetailEntity.getOrderInfo().getOrderId(), Constants.TYPE_PAYBACK_ORDER, 1, new CommonCallback() {
                    @Override
                    public void onSuccess(String message) {
                        getOrderDetail();
                    }
                });

            }
        });
    }

    @Subscribe
    public void orderChange(final OrderChangeEvent event) {
        showAddServiceDialog(2, event.id);
    }

    @Subscribe
    public void orderPayRefund(final OrderPayRefundEvent event) {
        Logger.i(event.toString());
        DialogUtil.getInstance().showSureViewDialog(_mActivity, R.layout.dialog_pay_refund, new DialogUtil.ViewInterface() {
            @Override
            public void getChildView(AlertDialog dialog, View view, int layoutResId) {
                double price = CalculateUtils.mul(event.getPrice(), Integer.toString(event.getCount()));
                final TextView tvPrices = view.findViewById(R.id.tv_price);
                StringBuilder stringBuilder = new StringBuilder();
                if (Constants.PAYCHANNEL_CASH.equals(event.getPayChannel())) {
                    stringBuilder.append("现金：¥" + price);
                } else if (Constants.PAYCHANNEL_CARD.equals(event.getPayChannel())) {
                    stringBuilder.append("银联卡：¥" + price);
                } else if (Constants.PAYCHANNEL_ALIPAY.equals(event.getPayChannel())) {
                    stringBuilder.append("支付宝：¥" + price);
                } else if (Constants.PAYCHANNEL_WXPAY.equals(event.getPayChannel())) {
                    stringBuilder.append("微信：¥" + price);
                }
                tvPrices.setText(stringBuilder.toString());

                AmountView tvCount = view.findViewById(R.id.tv_num);
                tvCount.setAmount(event.getCount());
                tvCount.setGoods_storage(event.getCount());
                tvCount.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
                    @Override
                    public void onAmountChange(View view, int amount) {
                        event.setCount(amount);
                        double price = CalculateUtils.mul(event.getPrice(), Integer.toString(event.getCount()));
                        StringBuilder stringBuilder = new StringBuilder();
                        if (Constants.PAYCHANNEL_CASH.equals(event.getPayChannel())) {
                            stringBuilder.append("现金：¥" + price);
                        } else if (Constants.PAYCHANNEL_CARD.equals(event.getPayChannel())) {
                            stringBuilder.append("银联卡：¥" + price);
                        } else if (Constants.PAYCHANNEL_ALIPAY.equals(event.getPayChannel())) {
                            stringBuilder.append("支付宝：¥" + price);
                        } else if (Constants.PAYCHANNEL_WXPAY.equals(event.getPayChannel())) {
                            stringBuilder.append("微信：¥" + price);
                        }
                        tvPrices.setText(stringBuilder.toString());
                    }
                });
            }
        }, new DialogUtil.OnSureListener() {
            @Override
            public void callBack(View view, final DialogInterface dialog) {
                dialog.dismiss();
                API.refundOrder(event.getId(), event.getType(), event.getCount(), new CommonCallback() {
                    @Override
                    public void onSuccess(String message) {
                        getOrderDetail();
                    }
                });

            }
        });
    }

    private List<String> getOrderPrice() {
        List<String> prices = new ArrayList<>();
        double cashPrice = 0d;
        double cardPrice = 0d;
        double alipayPrice = 0d;
        double wechatPrice = 0d;
        for (OrderServiceBase orderServiceBase : OrderDetailHandler.getInstance().getOrderServiceList()) {
            if (orderServiceBase instanceof OrderPhotoService) {
                for (int i = 0; i < ((OrderPhotoService) orderServiceBase).getPhotoServiceItems().size(); i++) {
                    PhotoServiceItem photoServiceItem = ((OrderPhotoService) orderServiceBase).getPhotoServiceItems().get(i);
                    OrderPhoto baseOrderPhoto = photoServiceItem.getBasePhotoItem();
                    if (Constants.PAY_STATUS_PAID.equals(baseOrderPhoto.getStatus())) {
                        if (Constants.PAYCHANNEL_CASH.equals(baseOrderPhoto.getPayChannel())) {
                            cashPrice = CalculateUtils.add(cashPrice + "", Double.toString(CalculateUtils.mul(baseOrderPhoto.getPrice(), Integer.toString(baseOrderPhoto.getCount()))));
                        } else if (Constants.PAYCHANNEL_CARD.equals(baseOrderPhoto.getPayChannel())) {
                            cardPrice = CalculateUtils.add(cardPrice + "", Double.toString(CalculateUtils.mul(baseOrderPhoto.getPrice(), Integer.toString(baseOrderPhoto.getCount()))));
                        } else if (Constants.PAYCHANNEL_ALIPAY.equals(baseOrderPhoto.getPayChannel())) {
                            alipayPrice = CalculateUtils.add(alipayPrice + "", Double.toString(CalculateUtils.mul(baseOrderPhoto.getPrice(), Integer.toString(baseOrderPhoto.getCount()))));
                        } else if (Constants.PAYCHANNEL_WXPAY.equals(baseOrderPhoto.getPayChannel())) {
                            wechatPrice = CalculateUtils.add(wechatPrice + "", Double.toString(CalculateUtils.mul(baseOrderPhoto.getPrice(), Integer.toString(baseOrderPhoto.getCount()))));
                        }
                    }
                    for (OrderPhoto orderPhoto : photoServiceItem.getPhotoItems()) {
                        if (Constants.PAY_STATUS_PAID.equals(orderPhoto.getStatus())) {
                            if (Constants.PAYCHANNEL_CASH.equals(orderPhoto.getPayChannel())) {
                                cashPrice = CalculateUtils.add(cashPrice + "", Double.toString(CalculateUtils.mul(orderPhoto.getPrice(), Integer.toString(orderPhoto.getCount()))));
                            } else if (Constants.PAYCHANNEL_CARD.equals(orderPhoto.getPayChannel())) {
                                cardPrice = CalculateUtils.add(cardPrice + "", Double.toString(CalculateUtils.mul(orderPhoto.getPrice(), Integer.toString(orderPhoto.getCount()))));
                            } else if (Constants.PAYCHANNEL_ALIPAY.equals(orderPhoto.getPayChannel())) {
                                alipayPrice = CalculateUtils.add(alipayPrice + "", Double.toString(CalculateUtils.mul(orderPhoto.getPrice(), Integer.toString(orderPhoto.getCount()))));
                            } else if (Constants.PAYCHANNEL_WXPAY.equals(orderPhoto.getPayChannel())) {
                                wechatPrice = CalculateUtils.add(wechatPrice + "", Double.toString(CalculateUtils.mul(orderPhoto.getPrice(), Integer.toString(orderPhoto.getCount()))));
                            }
                        }
                    }
                }
            } else if (orderServiceBase instanceof OrderProductService) {
                for (OrderProduct orderProduct : ((OrderProductService) orderServiceBase).getOrderProductList()) {
                    if (Constants.PAY_STATUS_PAID.equals(orderProduct.getStatus())) {
                        if (Constants.PAYCHANNEL_CASH.equals(orderProduct.getPayChannel())) {
                            cashPrice = CalculateUtils.add(cashPrice + "", Double.toString(CalculateUtils.mul(orderProduct.getPrice(), Integer.toString(orderProduct.getCount()))));
                        } else if (Constants.PAYCHANNEL_CARD.equals(orderProduct.getPayChannel())) {
                            cardPrice = CalculateUtils.add(cardPrice + "", Double.toString(CalculateUtils.mul(orderProduct.getPrice(), Integer.toString(orderProduct.getCount()))));
                        } else if (Constants.PAYCHANNEL_ALIPAY.equals(orderProduct.getPayChannel())) {
                            alipayPrice = CalculateUtils.add(alipayPrice + "", Double.toString(CalculateUtils.mul(orderProduct.getPrice(), Integer.toString(orderProduct.getCount()))));
                        } else if (Constants.PAYCHANNEL_WXPAY.equals(orderProduct.getPayChannel())) {
                            wechatPrice = CalculateUtils.add(wechatPrice + "", Double.toString(CalculateUtils.mul(orderProduct.getPrice(), Integer.toString(orderProduct.getCount()))));
                        }
                    }
                }
            }
        }
        prices.add(Double.toString(cashPrice));
        prices.add(Double.toString(cardPrice));
        prices.add(Double.toString(alipayPrice));
        prices.add(Double.toString(wechatPrice));
        return prices;
    }


    private void getTransation() {
        GetTransationIdBean getTransationIdBean = new GetTransationIdBean();
        getTransationIdBean.setOrderId(orderDetailEntity.getOrderInfo().getOrderId());
        getTransationIdBean.setToken(SPManager.getInstance().getTokenId());
        API.getTransactionId(getTransationIdBean, new ServerResultBack() {
            @Override
            public void onSuccess(Object data) {
                TransationId transationId = (TransationId) data;
                switch (payType) {
                    case Constants.PAYCHANNEL_CASH:
//                        showCashDialog(transationId.getTotalPrice(), transationId.getOrderNum());
                        showCashDialog(needPayPrice + "", transationId.getOrderNum());
                        break;
                    case Constants.PAYCHANNEL_CARD:
//                        showCardDialog(transationId.getTotalPrice(), transationId.getOrderNum());
                        showCardDialog(needPayPrice + "", transationId.getOrderNum());
                        break;
                    case Constants.PAYCHANNEL_ALIPAY:
                    case Constants.PAYCHANNEL_WXPAY:
//                        showShouqianbaDialog(transationId.getTotalPrice(), transationId.getOrderNum());
                        showShouqianbaDialog("0.01", transationId.getOrderNum(), "测试商品", employeeBaseName, null);
                        break;
                }
            }

            @Override
            public Type getType() {
                return new TypeReference<BaseResponse<TransationId>>() {
                }.getType();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                promptDialog.dismiss();
            }
        });
    }

    private void showShouqianbaDialog(final String totalPrice, final String orderNum, final String subject, final String operator, final ReqPay.GoodDetail goods_details) {
        DialogUtil.getInstance().showViewDialog(_mActivity, R.layout.dialog_shouqianba, new DialogUtil.ViewInterface() {
            @Override
            public void getChildView(AlertDialog dialog, View view, int layoutResId) {
                SqbPayDialogView sqbPayDialogView = new SqbPayDialogView(_mActivity, dialog, view);
                sqbPayDialogView.initModule(orderNum, totalPrice, subject, operator, goods_details);
            }
        });
    }

    private void showCardDialog(final String totalPrice, final String orderNum) {
        DialogUtil.getInstance().showSureViewDialog(_mActivity, R.layout.dialog_card, new DialogUtil.ViewInterface() {
            @Override
            public void getChildView(AlertDialog dialog, View view, int layoutResId) {
                TextView tvPrice = view.findViewById(R.id.tv_price);
                tvPrice.setText("应收：¥" + totalPrice);
            }
        }, new DialogUtil.OnSureListener() {
            @Override
            public void callBack(View view, final DialogInterface dialog) {
                Logger.d("sureView onClick callBack");
                dialog.dismiss();
                List<PayBackBean.OrderNumsBean> orderNumsBeanList = new ArrayList<>();
                PayBackBean.OrderNumsBean orderNumsBean = new PayBackBean.OrderNumsBean();
                orderNumsBean.setOrderNum(orderNum);
                orderNumsBean.setPayChannel(Constants.PAYCHANNEL_CARD);
                orderNumsBean.setPayChannelName(Constants.orderPayChannels.get(Constants.PAYCHANNEL_CARD));
                orderNumsBean.setPayStatus(Constants.PAY_STATUS_PAID);
                orderNumsBeanList.add(orderNumsBean);
                updateOrderPay(orderNumsBeanList);
            }
        });
    }

    private void updateOrderPay(List<PayBackBean.OrderNumsBean> orderNumsBeanList) {

        PayBackBean payBackBean = new PayBackBean();
        payBackBean.setToken(SPManager.getInstance().getTokenId());
        payBackBean.setOrderId(orderDetailEntity.getOrderInfo().getOrderId());
        payBackBean.setOrderNums(orderNumsBeanList);
        doPaidUpdate(payBackBean);

    }

    private void doPaidUpdate(final PayBackBean payBackBean) {
        promptDialog.showLoading("正在更新订单支付状态");
        API.paidUpdate(payBackBean, new CommonCallback() {
            @Override
            public void onSuccess(String message) {
                ToastUtils.showShortToast("更新支付状态成功");
                getOrderDetail();
            }

            @Override
            public void onFailed(String error_code, String error_message) {
                super.onFailed(error_code, error_message);
                promptDialog.showWarnAlert(error_message, new PromptButton("重试", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                        promptDialog.dismiss();
                        doPaidUpdate(payBackBean);
                    }
                }));
            }

            @Override
            public void onFinish() {
                super.onFinish();
                promptDialog.dismiss();
            }
        });
    }

    private void showCashDialog(final String totalPrice, final String orderNum) {
        final EditText[] etCash = new EditText[1];
        DialogUtil.getInstance().showSureViewDialog(_mActivity, R.layout.dialog_cash, new DialogUtil.ViewInterface() {
            @Override
            public void getChildView(AlertDialog dialog, View view, int layoutResId) {
                etCash[0] = view.findViewById(R.id.et_cash);
                final TextView tvPrice = view.findViewById(R.id.tv_price);
                etCash[0].addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        tvPrice.setText("应收：¥" + totalPrice + "\n" + "找零：¥" + (Float.parseFloat(StringUtils.isEmpty(s.toString()) ? "0" : s.toString()) - Float.parseFloat(totalPrice)));
                    }
                });

                view.findViewById(R.id.bt_open_drawer).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (StringUtils.isEmpty(etCash[0].getText().toString())) {
                            ToastUtils.showShortToast("请输入已收现金");
                            return;
                        }
                        AidlUtil.getInstance().openDrawer();
                    }
                });
                tvPrice.setText("应收：¥" + totalPrice + "\n" + "找零：¥0");
            }
        }, new DialogUtil.OnSureListener() {
            @Override
            public void callBack(View view, final DialogInterface dialog) {
                Logger.d("sureView onClick callBack");
                if (StringUtils.isEmpty(etCash[0].getText().toString())) {
                    ToastUtils.showShortToast("请输入已收现金额");
                    return;
                }
                if ((Float.parseFloat(StringUtils.isEmpty(etCash[0].getText().toString()) ? "0" :etCash[0].getText().toString()) - Float.parseFloat(totalPrice))<0){
                    ToastUtils.showShortToast("还不够付款哦");
                    return;
                }
                dialog.dismiss();
                List<PayBackBean.OrderNumsBean> orderNumsBeanList = new ArrayList<>();
                PayBackBean.OrderNumsBean orderNumsBean = new PayBackBean.OrderNumsBean();
                orderNumsBean.setOrderNum(orderNum);
                orderNumsBean.setPayChannel(Constants.PAYCHANNEL_CASH);
                orderNumsBean.setPayChannelName(Constants.orderPayChannels.get(Constants.PAYCHANNEL_CASH));
                orderNumsBean.setPayStatus(Constants.PAY_STATUS_PAID);
                orderNumsBeanList.add(orderNumsBean);
                updateOrderPay(orderNumsBeanList);
                AidlUtil.getInstance().openDrawer();
                orderDetailEntity.getOrderInfo().setStatus(Constants.PAY_STATUS_PAID);
                printOrder();
            }

        });
    }

    private void printOrder() {
        if (orderDetailEntity == null) {
            return;
        }
        ThreadPoolManager.getInstance().executeTask(new Runnable() {
            @Override
            public void run() {
                StringBuffer printBuffer = new StringBuffer();
                printBuffer.append("\n");
                printBuffer.append("\n");
                AidlUtil.getInstance().printText("         "+SPManager.getInstance().getPrintTitle()+"             ", 36, true, false);
                printBuffer.append("\n");
                printBuffer.append("\n");
                printBuffer.append("打印时间：" + DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
                printBuffer.append("\n");
                if (null != orderDetailEntity.getVip()) {
                    printBuffer.append("会员昵称：" + orderDetailEntity.getVip().getName());
                    printBuffer.append("\n");
                }
                if (null != orderDetailEntity.getOrderInfo()) {
                    printBuffer.append("订单编号：" + orderDetailEntity.getOrderInfo().getOrderId());
                    printBuffer.append("\n");
                    printBuffer.append("预付金额：¥" + orderDetailEntity.getOrderInfo().getPaid());
                    printBuffer.append("\n");
                    printBuffer.append("支付状态：" + Constants.orderPayStatus.get(orderDetailEntity.getOrderInfo().getStatus()));
                    printBuffer.append("\n");
                    printBuffer.append("收银人员：" +orderDetailEntity.getOrderInfo().getEmployeeName());
                    printBuffer.append("\n");
                }
                printBuffer.append("----------------订单详情----------------");
                printBuffer.append("\n");
                for (OrderServiceBase orderServiceBase : OrderDetailHandler.getInstance().getOrderServiceList()) {
                    if (orderServiceBase instanceof OrderPhotoService) {
                        List<PhotoServiceItem> photoServiceItemList = ((OrderPhotoService) orderServiceBase).getPhotoServiceItems();
                        for (PhotoServiceItem photoServiceItem : photoServiceItemList) {
                            OrderPhoto orderPhoto = photoServiceItem.getBasePhotoItem();
                            printBuffer.append("-------------" + photoServiceItem.getServiceName() + "---------------");
                            printBuffer.append("\n");
                            printBuffer.append("(基础)" + orderPhoto.getSize() + "   " + "底色：" + orderPhoto.getColor() + "      " + "价格：¥" + orderPhoto.getPrice());
                            printBuffer.append("\n");
                            if (photoServiceItem.getPhotoItems() != null) {
                                for (OrderPhoto orderPhoto1 : photoServiceItem.getPhotoItems()) {
                                    printBuffer.append("(加印)" + orderPhoto1.getSize() + "      " + orderPhoto1.getColor() + "      " + orderPhoto1.getPrice());
                                    printBuffer.append("\n");
                                }
                            }
                        }
                    } else if (orderServiceBase instanceof OrderProductService) {
                        List<OrderProduct> orderProductList = ((OrderProductService) orderServiceBase).getOrderProductList();
                        if (orderProductList != null) {
                            printBuffer.append("--------------" + "商品" + "----------------");
                            printBuffer.append("\n");
                            for (OrderProduct product : orderProductList) {
                                printBuffer.append(product.getServiceName() + "      " + "数量：" + product.getCount() + "      " + "价格：¥" + product.getPrice());
                            }
                        }
                    }
                }
                printBuffer.append("\n");
                printBuffer.append("\n");
                printBuffer.append("订单总额：¥" + totalPrice);
                AidlUtil.getInstance().printText(printBuffer.toString(), 26, false, false);
                AidlUtil.getInstance().printText("--------------谢谢惠顾----------------", 30, false, false);
                AidlUtil.getInstance().printLine(1);
                AidlUtil.getInstance().cutPaper();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {

                }
            }
        });
    }

    private void showAddDiscounteDialog() {
        final EditText[] etDiscount = new EditText[1];
        final EditText[] etPwd = new EditText[1];
        DialogUtil.getInstance().showSureViewDialog(_mActivity, R.layout.dialog_discount, new DialogUtil.ViewInterface() {
            @Override
            public void getChildView(AlertDialog dialog, View view, int layoutResId) {
                etDiscount[0] = view.findViewById(R.id.et_discount);
                etPwd[0] = view.findViewById(R.id.et_pwd);
            }
        }, new DialogUtil.OnSureListener() {
            @Override
            public void callBack(View view, final DialogInterface dialog) {
                if (StringUtils.isEmpty(etDiscount[0].getText().toString()) || StringUtils.isEmpty(etPwd[0].getText().toString())) {
                    ToastUtils.showShortToast("请输入折扣和密码");
                    return;
                }
                ShoPwdBean shoPwdBean = new ShoPwdBean();
                shoPwdBean.setToken(SPManager.getInstance().getTokenId());
                shoPwdBean.setPassword(etPwd[0].getText().toString());
                promptDialog.showLoading("正在验证");
                API.checkShopPwd(shoPwdBean, new CommonCallback() {
                    @Override
                    public void onSuccess(String message) {
                        dialog.dismiss();
                        promptDialog.showSuccessDelay(message, 500);
                        try {
                            discount += Float.parseFloat(etDiscount[0].getText().toString());
                            tvDiscountPrice.setText("折扣：¥" + discount);
//                            onUpdateOrderContent(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Logger.e("折扣价格格式化异常");
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        promptDialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        EventBus.getDefault().post(new PresentationChangeEvent(Constants.CUSTOMER_DISPLAY_DEFAULT));
    }
}
