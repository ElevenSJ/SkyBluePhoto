package com.sj.skyblue.entity.post;

/**
 * Created by Sunj on 2018/11/27.
 */

public class OrderComboAdd {


    /**
     * token : 9223370493902909103126a4486fafb40579e171e82f8582b69
     * id : DD1542944174048UaUn
     * type : 1
     * count : 1
     */

    private String token;
    private String orderId;
    private String comboId;

    public OrderComboAdd(String tokenId, String orderId, String comboId) {
        this.token = tokenId;
        this.orderId = orderId;
        this.comboId = comboId;
    }

}
