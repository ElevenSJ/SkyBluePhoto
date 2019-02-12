package com.sj.skyblue.entity.order;

import com.sj.module_lib.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunj on 2018/11/15.
 */

public class OrderService {
    /**
     * size : [{"code":"60","createTime":"2018-10-18 19:15:25","name":"60","id":"9223370496993450595e445029fbc314c66a7fa65ddf9763253","type":"尺寸"},{"code":"40","createTime":"2018-10-18 19:15:09","name":"40","id":"922337049699346618835d5fa371fc543d1a902f2420d0379d8","type":"尺寸"},{"code":"20","createTime":"2018-10-18 19:14:55","name":"20","id":"922337049699348048983c121c5593a4ebeae8dd515b18aacb8","type":"尺寸"}]
     * color : [{"code":"02","name":"天蓝","id":"9223370497369532020fc4f69e9d142446797bd2782eb4567fc","type":"底色"}]
     * tierAddPrice : 550
     * isExtra : 1
     * orderServiceId : 92233704953802660669645068beaed43a49f706c40c84e6ddc
     * name : 全家福
     * basePrint : {"color":"红色","orderId":"DD1539826907301AJAX","remark":"666","orderExtra":[{"color":"红色","size":"60","orderId":"92233704949375191697129b5ab89984c39b53d421bce451b6a","price":"100","id":"92233704946491730778896b44cb3224ab8b3de78133a6c4949","status":"0"},{"color":"红色","size":"60","orderId":"92233704949375191697129b5ab89984c39b53d421bce451b6a","price":"100","id":"9223370494656017712d18c4441df0a414eb06dec9eaf3c982a","status":"0"},{"color":"红色","size":"40","orderId":"92233704949375191697129b5ab89984c39b53d421bce451b6a","price":"100","id":"9223370494657565550197d972ccde644d783865d844320fe11","status":"0"},{"color":"红色","size":"60","orderId":"92233704949375191697129b5ab89984c39b53d421bce451b6a","price":"100","id":"92233704946579032663ea14741472f466c8f1659619d97d49f","status":"0"}],"picUrl1":"https://public.app-storage-node.com/FslzoRRW0A7ohv-KUjJIDMIOQkdu?attname=3.jpg","picUrl2":"https://public.app-storage-node.com/Fv6PZA2FQNIvL8xqtIyQF7WzGaHX?attname=2.jpg","picUrl":"https://public.app-storage-node.com/FuPmdFWkLRXtEkrPQKrGqa-XaAm3?attname=1.jpg","size":"60","isExtra":"0","price":"","orderServiceId":"92233704953802660669645068beaed43a49f706c40c84e6ddc","id":"92233704949375191697129b5ab89984c39b53d421bce451b6a","status":"1"}
     * serviceId : 9223370494746645919677fbc2b72bd4751a2136af11c1fbdd8
     * printPrizes : [{"price":"100","name":"加印6寸（非排版）","id":"01"},{"price":"200","name":"加印6寸（排版）","id":"02"},{"price":"200","name":"加印A4","id":"03"}]
     * extPrint : []
     * status :
     * tierPrice : 600
     */

    private String tierAddPrice;//加选价格
    private String isExtra;//是否新增服务
    private String orderServiceId;//订单服务id
    private String name;//服务名称
    private BasePrintBean basePrint;
    private List<BasePrintBean> extPrint;
    private String serviceId;
    private String status;
    private String type;
    private String prePrice;//优惠价格
    private String tierPrice;//服务价格
    private List<SizeColor> size;
    private List<SizeColor> color;
    private List<PrintPrizesBean> printPrizes;

    public String getPrePrice() {
        return StringUtils.isEmpty(prePrice)?"0":prePrice;
    }

    public void setPrePrice(String prePrice) {
        this.prePrice = prePrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTierAddPrice() {
        return StringUtils.isEmpty(tierAddPrice)?"0":tierAddPrice;
    }

    public void setTierAddPrice(String tierAddPrice) {
        this.tierAddPrice = tierAddPrice;
    }

    public String getIsExtra() {
        return isExtra;
    }

    public void setIsExtra(String isExtra) {
        this.isExtra = isExtra;
    }

    public String getOrderServiceId() {
        return orderServiceId;
    }

    public void setOrderServiceId(String orderServiceId) {
        this.orderServiceId = orderServiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BasePrintBean getBasePrint() {
        return basePrint;
    }

    public void setBasePrint(BasePrintBean basePrint) {
        this.basePrint = basePrint;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTierPrice() {
        return StringUtils.isEmpty(tierPrice)?"0":tierPrice;
    }

    public void setTierPrice(String tierPrice) {
        this.tierPrice = tierPrice;
    }

    public List<SizeColor> getSize() {
        return size;
    }

    public void setSize(List<SizeColor> size) {
        this.size = size;
    }

    public List<SizeColor> getColor() {
        return color;
    }

    public void setColor(List<SizeColor> color) {
        this.color = color;
    }

    public List<PrintPrizesBean> getPrintPrizes() {
        return printPrizes;
    }

    public void setPrintPrizes(List<PrintPrizesBean> printPrizes) {
        this.printPrizes = printPrizes;
    }

    public List<BasePrintBean> getExtPrint() {
        return extPrint;
    }

    public void setExtPrint(List<BasePrintBean> extPrint) {
        this.extPrint = extPrint;
    }

    public static class BasePrintBean {
        /**
         * color : 红色
         * orderId : DD1539826907301AJAX
         * remark : 666
         * orderExtra : [{"color":"红色","size":"60","orderId":"92233704949375191697129b5ab89984c39b53d421bce451b6a","price":"100","id":"92233704946491730778896b44cb3224ab8b3de78133a6c4949","status":"0"},{"color":"红色","size":"60","orderId":"92233704949375191697129b5ab89984c39b53d421bce451b6a","price":"100","id":"9223370494656017712d18c4441df0a414eb06dec9eaf3c982a","status":"0"},{"color":"红色","size":"40","orderId":"92233704949375191697129b5ab89984c39b53d421bce451b6a","price":"100","id":"9223370494657565550197d972ccde644d783865d844320fe11","status":"0"},{"color":"红色","size":"60","orderId":"92233704949375191697129b5ab89984c39b53d421bce451b6a","price":"100","id":"92233704946579032663ea14741472f466c8f1659619d97d49f","status":"0"}]
         * picUrl1 : https://public.app-storage-node.com/FslzoRRW0A7ohv-KUjJIDMIOQkdu?attname=3.jpg
         * picUrl2 : https://public.app-storage-node.com/Fv6PZA2FQNIvL8xqtIyQF7WzGaHX?attname=2.jpg
         * picUrl : https://public.app-storage-node.com/FuPmdFWkLRXtEkrPQKrGqa-XaAm3?attname=1.jpg
         * size : 60
         * isExtra : 0
         * price :
         * orderServiceId : 92233704953802660669645068beaed43a49f706c40c84e6ddc
         * id : 92233704949375191697129b5ab89984c39b53d421bce451b6a
         * status : 1
         */

        private int count;//数量
        private int paidCount;
        private int refundCount;
        private int peopleCount;//人数
        private String color;//颜色
        private String orderId;//订单id
        private String remark;//服务备注
        private String picUrl1;
        private String picUrl2;
        private String picUrl;
        private String size;
        private String isExtra;//是否加选
        private String orderServiceId;//订单服务id
        private String orderNum;//交易流水号
        private String id;//服务id
        private String price;//服务价格
        private String prePrice;//优惠价格
        private String status;//支付状态
        private String payChannel;//支付渠道
        private String payChannelName;//支付渠道名称
        private List<OrderExtraBean> orderExtra;//加印服务

        public String getPrePrice() {
            return StringUtils.isEmpty(prePrice)?"0":prePrice;
        }

        public void setPrePrice(String prePrice) {
            this.prePrice = prePrice;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getPayChannel() {
            return payChannel;
        }

        public void setPayChannel(String payChannel) {
            this.payChannel = payChannel;
        }

        public String getPayChannelName() {
            return payChannelName;
        }

        public void setPayChannelName(String payChannelName) {
            this.payChannelName = payChannelName;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPaidCount() {
            return paidCount;
        }

        public void setPaidCount(int paidCount) {
            this.paidCount = paidCount;
        }

        public int getRefundCount() {
            return refundCount;
        }

        public void setRefundCount(int refundCount) {
            this.refundCount = refundCount;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPicUrl1() {
            return picUrl1;
        }

        public void setPicUrl1(String picUrl1) {
            this.picUrl1 = picUrl1;
        }

        public String getPicUrl2() {
            return picUrl2;
        }

        public void setPicUrl2(String picUrl2) {
            this.picUrl2 = picUrl2;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getIsExtra() {
            return isExtra;
        }

        public void setIsExtra(String isExtra) {
            this.isExtra = isExtra;
        }

        public String getOrderServiceId() {
            return orderServiceId;
        }

        public void setOrderServiceId(String orderServiceId) {
            this.orderServiceId = orderServiceId;
        }

        public int getCount() {
            return count;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<OrderExtraBean> getOrderExtra() {
            return orderExtra;
        }

        public void setOrderExtra(List<OrderExtraBean> orderExtra) {
            this.orderExtra = orderExtra;
        }

        public String getPrice() {
            return StringUtils.isEmpty(price)?"0":price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getPeopleCount() {
            return peopleCount;
        }

        public void setPeopleCount(int peopleCount) {
            this.peopleCount = peopleCount;
        }


        public static class OrderExtraBean {
            /**
             * color : 红色
             * size : 60
             * orderId : 92233704949375191697129b5ab89984c39b53d421bce451b6a
             * price : 100
             * id : 92233704946491730778896b44cb3224ab8b3de78133a6c4949
             * status : 0
             */


            private String color;
            private String size;
            private String orderId;
            private String price;
            private String prePrice;//优惠价格
            private String id;
            private String orderNum;//交易流水号
            private String status;//支付状态
            private String payChannel;//支付渠道
            private String payChannelName;//支付渠道名称
            private int count =1;//数量
            private int paidCount;
            private int refundCount;

            public String getPrePrice() {
                return StringUtils.isEmpty(prePrice)?"0":prePrice;
            }

            public void setPrePrice(String prePrice) {
                this.prePrice = prePrice;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getPrice() {
                return StringUtils.isEmpty(price)?"0":price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public String getPayChannel() {
                return payChannel;
            }

            public void setPayChannel(String payChannel) {
                this.payChannel = payChannel;
            }

            public String getPayChannelName() {
                return payChannelName;
            }

            public void setPayChannelName(String payChannelName) {
                this.payChannelName = payChannelName;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getPaidCount() {
                return paidCount;
            }

            public void setPaidCount(int paidCount) {
                this.paidCount = paidCount;
            }

            public int getRefundCount() {
                return refundCount;
            }

            public void setRefundCount(int refundCount) {
                this.refundCount = refundCount;
            }
        }
    }

    public static class SizeColor {

        private String code;//尺寸大小
        private String createTime;//创建时间
        private String name;//名称
        private String id;//id
        private String type;//尺寸

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class PrintPrizesBean {
        /**
         * price : 100
         * name : 加印6寸（非排版）
         * id : 01
         */

        private List<String> size;
        private String price;
        private String name;
        private String id;

        public String getPrice() {
            return StringUtils.isEmpty(price)?"0":price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List getSizes() {
            return size;
        }

        public void setSize(List size) {
            this.size = size;
        }
    }
}
