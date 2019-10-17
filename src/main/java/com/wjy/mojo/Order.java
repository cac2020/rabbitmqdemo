package com.wjy.mojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {
    private Integer id;
    private String orderId;
    private BigDecimal amount;
    private Date createTime;

    public Order() {
        super();
    }

    public Order(Integer id, String orderId, BigDecimal amount, Date createTime) {
        super();
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", orderId=" + orderId + ", amount="
                + amount + ", createTime=" + createTime + "]";
    }
}
