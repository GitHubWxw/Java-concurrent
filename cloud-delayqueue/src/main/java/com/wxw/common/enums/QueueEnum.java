package com.wxw.common.enums;


import lombok.Getter;

/**
 * 消息队列枚举配置
 */
@Getter
public enum QueueEnum {
    /**
     * 消息通知队列
     */
    QUEUE_ORDER_CANCEL("wxw.order.direct", "wxw.order.cancel", "wxw.order.cancel"),
    /**
     * 消息通知 ttl 队列
     */
    QUEUE_TTL_ORDER_CANCEL("wxw.order.direct.ttl", "wxw.order.cancel.ttl", "wxw.order.cancel.ttl");

    /**
     * 交换名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
