package com.githup.zzwloves.dto;

import java.io.Serializable;

/**
 * 开联通请求
 *
 * @author zhuzw
 * @version <b>1.0.0</b>
 */
public class KltRequest<T> implements Serializable {

    private static final long serialVersionUID = 5957422194916207450L;

    private Head head;

    private T content;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KltRequest<?> that = (KltRequest<?>) o;

        if (head != null ? !head.equals(that.head) : that.head != null) return false;
        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "KltRequest{" +
                "head=" + head +
                ", content=" + content +
                '}';
    }
}
