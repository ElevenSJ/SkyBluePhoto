package com.sj.skyblue.entity.post;

import com.sj.skyblue.entity.order.OrderProduct;

import java.util.List;

/**
 * Created by Sunj on 2018/11/17.
 */

public class UpdateOrderBean {


    /**
     * token : 92233704946627518139a67cbca0b074b798e57e5baa92b315e
     * orderInfo : {"id":"DD1542185466956mtaf","discount":"110"}
     * services : [{"orderServiceId":"OS1542185466956ukAR","serviceId":"922337049473718265352d2fdf575df4bcd80a281f06165bbb7","basePrint":{"id":"DD1542185466956mtaf","color":"红色","size":"40","price":"320","addPrint":[{"id":"02","color":"红色","size":"2","price":"20"},{"id":"03","color":"黑色","size":"3","price":"30"}]}}]
     */

    private String token;
    private OrderInfoBean orderInfo;
    private List<ServicesBean> services;
    private List<OrderProduct> products;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<ServicesBean> getServices() {
        return services;
    }

    public void setServices(List<ServicesBean> services) {
        this.services = services;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }

    public static class OrderInfoBean {
        /**
         * id : DD1542185466956mtaf
         * discount : 110
         */

        private String id;
        private String discount;
        private String remark;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    public static class ServicesBean {
        /**
         * orderServiceId : OS1542185466956ukAR
         * serviceId : 922337049473718265352d2fdf575df4bcd80a281f06165bbb7
         * basePrint : {"id":"DD1542185466956mtaf","color":"红色","size":"40","price":"320","addPrint":[{"id":"02","color":"红色","size":"2","price":"20"},{"id":"03","color":"黑色","size":"3","price":"30"}]}
         */

        private String orderServiceId;
        private String serviceId;
        private BasePrintBean basePrint;
        private List<BasePrintBean> extPrint;

        public String getOrderServiceId() {
            return orderServiceId;
        }

        public void setOrderServiceId(String orderServiceId) {
            this.orderServiceId = orderServiceId;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public BasePrintBean getBasePrint() {
            return basePrint;
        }

        public void setBasePrint(BasePrintBean basePrint) {
            this.basePrint = basePrint;
        }

        public List<BasePrintBean> getExtPrint() {
            return extPrint;
        }

        public void setExtPrint(List<BasePrintBean> extPrint) {
            this.extPrint = extPrint;
        }

        public static class BasePrintBean {
            /**
             * id : DD1542185466956mtaf
             * color : 红色
             * size : 40
             * price : 320
             * addPrint : [{"id":"02","color":"红色","size":"2","price":"20"},{"id":"03","color":"黑色","size":"3","price":"30"}]
             */

            private int count;
            private int peopleCount;
            private String id;
            private String color;
            private String size;
            private String price;
            private List<AddPrintBean> addPrint;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public List<AddPrintBean> getAddPrint() {
                return addPrint;
            }

            public void setAddPrint(List<AddPrintBean> addPrint) {
                this.addPrint = addPrint;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getPeopleCount() {
                return peopleCount;
            }

            public void setPeopleCount(int peopleCount) {
                this.peopleCount = peopleCount;
            }

            public static class AddPrintBean {
                /**
                 * id : 02
                 * color : 红色
                 * size : 2
                 * price : 20
                 */

                private String id;
                private String color;
                private String size;
                private String price;
                private int count;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
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

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }
            }
        }
    }
}
