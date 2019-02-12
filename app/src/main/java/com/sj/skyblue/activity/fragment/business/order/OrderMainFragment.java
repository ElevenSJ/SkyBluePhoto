package com.sj.skyblue.activity.fragment.business.order;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sj.module_lib.utils.DateUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.activity.base.BaseFragment;
import com.sj.skyblue.event.OrderCreateEvent;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 预约订单主页面
 * Created by Sunj on 2018/11/3.
 */

public class OrderMainFragment extends BaseFragment{

    public static OrderMainFragment newInstance() {
        OrderMainFragment fragment = new OrderMainFragment();
        return fragment;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_order_list;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        if (findChildFragment(OrderListFragment.class) == null) {
            OrderListFragment appointmentListFragment = OrderListFragment.newInstance();
            loadRootFragment(R.id.fl_list_container, appointmentListFragment,true,true);
        }
    }


    public void createOrder(){
        OrderListFragment appointmentListFragment = findChildFragment(OrderListFragment.class);
        if (appointmentListFragment!=null){
            appointmentListFragment.showCreateOrderDialog(new OrderCreateEvent(DateUtils.getCurrentTime()));
        }
    }
    /**
     * 替换加载 内容Fragment
     *
     * @param fragment
     */
    public void switchContentFragment(OrderContentFragment fragment) {
        SupportFragment contentFragment = findChildFragment(OrderContentFragment.class);
        if (contentFragment != null) {
            contentFragment.replaceFragment(fragment, false);
        }else{
            // false:  不加入回退栈;  false: 不显示动画
            loadRootFragment(R.id.fl_content_container, fragment, false, true);
        }
    }
}
