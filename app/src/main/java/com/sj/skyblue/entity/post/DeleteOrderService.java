package com.sj.skyblue.entity.post;

/**
 * Created by Sunj on 2018/11/27.
 */

public class DeleteOrderService {


    /**
     * token : 9223370493902909103126a4486fafb40579e171e82f8582b69
     * id : DD1542944174048UaUn
     * type : 1
     */

    private String token;
    private String id;
    private int type;

    public DeleteOrderService(String tokenId, String id, int type) {
        this.token = tokenId;
        this.id = id;
        this.type = type;
    }

}
