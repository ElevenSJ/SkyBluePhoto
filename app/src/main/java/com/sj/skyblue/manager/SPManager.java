package com.sj.skyblue.manager;

import com.sj.module_lib.utils.FileToolUtils;
import com.sj.module_lib.utils.SPUtils;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.constant.PaymentConstants;
import com.sj.skyblue.constant.SPNameSpace;
import com.sj.skyblue.utils.SystemUtil;

import java.io.File;

/**
 * Created by Sunj on 2018/11/3.
 */

public class SPManager {
    private static final SPManager ourInstance = new SPManager();

    public static SPManager getInstance() {
        return ourInstance;
    }

    private SPManager() {
    }

    public void exit() {
//        SPUtils.getInstance().clear();
        SPUtils.getInstance().remove(new String[]{SPNameSpace.IS_LOGIN, SPNameSpace.USER_ACCOUNT, SPNameSpace.TOKEN_ID,SPNameSpace.SHOP_ID,SPNameSpace.OPERATOR});
    }

    public boolean isBind() {
        return (Boolean) SPUtils.getInstance().getSharedPreference(SPNameSpace.IS_BIND, false);
    }

    public boolean isActivated() {
                return (Boolean) SPUtils.getInstance().getSharedPreference(SPNameSpace.IS_ACTIVATE, false)||FileToolUtils.fileExists(FileToolUtils.getRootPath() + Constants.SDCARD_ROOT_PATH + File.separator + SystemUtil.getDeviceSN() + PaymentConstants.activateFileExt);
    }

    public void setActivated(boolean activated) {
        SPUtils.getInstance().apply(SPNameSpace.IS_ACTIVATE, activated);
    }
    public boolean isWorkOpen() {
        return (boolean) SPUtils.getInstance().getSharedPreference(SPNameSpace.WORK_OPEN, false);
    }

    public boolean isWorkFilter() {
        return (boolean) SPUtils.getInstance().getSharedPreference(SPNameSpace.WORK_FILTER, false);
    }

    public void savePayTerminal(String terminal_sn, String terminal_key) {
        SPUtils.getInstance().apply(new String[]{SPNameSpace.TERMINAL_SN, SPNameSpace.TERMINAL_KEY}, new Object[]{terminal_sn, terminal_key});
    }

    public String getOperatorName() {
        return (String) SPUtils.getInstance().getSharedPreference(SPNameSpace.OPERATOR, "");
    }

    public String getTerminalSn() {
        return (String) SPUtils.getInstance().getSharedPreference(SPNameSpace.TERMINAL_SN, "");
    }

    public String getTerminalKey() {
        return (String) SPUtils.getInstance().getSharedPreference(SPNameSpace.TERMINAL_KEY, "");
    }

    public void saveLogined(boolean b, String phoneNum, String token,String shopId,String operator) {
        SPUtils.getInstance().apply(new String[]{SPNameSpace.IS_LOGIN, SPNameSpace.USER_ACCOUNT, SPNameSpace.TOKEN_ID,SPNameSpace.SHOP_ID,SPNameSpace.OPERATOR}, new Object[]{true, phoneNum, token,shopId,operator});
    }

    public boolean isLogined() {
        return (Boolean) SPUtils.getInstance().getSharedPreference(SPNameSpace.IS_LOGIN, false);
    }

    public String getTokenId() {
        return (String) SPUtils.getInstance().getSharedPreference(SPNameSpace.TOKEN_ID, "");
    }
    public String getShopId() {
        return (String) SPUtils.getInstance().getSharedPreference(SPNameSpace.SHOP_ID, "");
    }
    public String getShopName() {
        return (String) SPUtils.getInstance().getSharedPreference(SPNameSpace.SHOP_NAME, "");
    }
    public void setShopName(String shopName) {
       SPUtils.getInstance().commit(SPNameSpace.SHOP_NAME, shopName);
    }
    public String getWorkOrder() {
        return (String) SPUtils.getInstance().getSharedPreference(SPNameSpace.WORK_ORDER, "");
    }

    public void exitWork() {
        SPUtils.getInstance().remove(new String[]{SPNameSpace.WORK_ORDER,SPNameSpace.WORK_OPEN});
    }

    public void setWorkOpen(String workDate) {
        SPUtils.getInstance().apply(new String[]{SPNameSpace.WORK_ORDER, SPNameSpace.WORK_OPEN}, new Object[]{workDate, true});
    }

    public void setIsBind(boolean isBind) {
        SPUtils.getInstance().apply(SPNameSpace.IS_BIND, isBind);
    }

    public String getPrintTitle() {
        return (String) SPUtils.getInstance().getSharedPreference(SPNameSpace.PRINT_TITLE, "欢迎光临天真蓝");
    }
    public void setPrintTitle(String printTitle) {
        SPUtils.getInstance().apply(SPNameSpace.PRINT_TITLE,printTitle);
    }
}