package com.kkb.xzk.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @Author: HanDing
 * @Description:
 * @Date Created in 2021-07-24 1:56
 * @Modified By:
 */
public class SessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("session属性值增加：name=" + httpSessionBindingEvent.getName() + ",value=" + httpSessionBindingEvent.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("session属性值删除：name=" + httpSessionBindingEvent.getName() + ",value=" + httpSessionBindingEvent.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("session属性值修改：name=" + httpSessionBindingEvent.getName() + ",value=" + httpSessionBindingEvent.getValue());
    }
}
