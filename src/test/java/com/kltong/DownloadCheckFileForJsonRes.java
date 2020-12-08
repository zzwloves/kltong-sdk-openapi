package com.kltong;

import com.githup.zzwloves.dto.KltBaseResponse;

import java.util.List;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2020/11/30 15:23
 */
public class DownloadCheckFileForJsonRes extends KltBaseResponse {
    private static final long serialVersionUID = -4063139629275669375L;

    private String batchNo;
    private Long consumeTotal;
    private Long consumeAmount;
    private Long refundTotal;
    private Long refundAmount;
    private Long consumeReversalTotal;
    private Long consumeReversalAmount;
    private Long fee;
    private List<CheckFileRecord> content;

    @Override
    public String toString() {
        return "DownloadCheckFileForJsonRes{" +
                "batchNo='" + batchNo + '\'' +
                ", consumeTotal=" + consumeTotal +
                ", consumeAmount=" + consumeAmount +
                ", refundTotal=" + refundTotal +
                ", refundAmount=" + refundAmount +
                ", consumeReversalTotal=" + consumeReversalTotal +
                ", consumeReversalAmount=" + consumeReversalAmount +
                ", fee=" + fee +
                ", content=" + content +
                "} " + super.toString();
    }

    public String getBatchNo() {
        return batchNo;
    }

    public DownloadCheckFileForJsonRes setBatchNo(String batchNo) {
        this.batchNo = batchNo;
        return this;
    }

    public Long getConsumeTotal() {
        return consumeTotal;
    }

    public DownloadCheckFileForJsonRes setConsumeTotal(Long consumeTotal) {
        this.consumeTotal = consumeTotal;
        return this;
    }

    public Long getConsumeAmount() {
        return consumeAmount;
    }

    public DownloadCheckFileForJsonRes setConsumeAmount(Long consumeAmount) {
        this.consumeAmount = consumeAmount;
        return this;
    }

    public Long getRefundTotal() {
        return refundTotal;
    }

    public DownloadCheckFileForJsonRes setRefundTotal(Long refundTotal) {
        this.refundTotal = refundTotal;
        return this;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public DownloadCheckFileForJsonRes setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
        return this;
    }

    public Long getConsumeReversalTotal() {
        return consumeReversalTotal;
    }

    public DownloadCheckFileForJsonRes setConsumeReversalTotal(Long consumeReversalTotal) {
        this.consumeReversalTotal = consumeReversalTotal;
        return this;
    }

    public Long getConsumeReversalAmount() {
        return consumeReversalAmount;
    }

    public DownloadCheckFileForJsonRes setConsumeReversalAmount(Long consumeReversalAmount) {
        this.consumeReversalAmount = consumeReversalAmount;
        return this;
    }

    public Long getFee() {
        return fee;
    }

    public DownloadCheckFileForJsonRes setFee(Long fee) {
        this.fee = fee;
        return this;
    }

    public List<CheckFileRecord> getContent() {
        return content;
    }

    public DownloadCheckFileForJsonRes setContent(List<CheckFileRecord> content) {
        this.content = content;
        return this;
    }

    public static class CheckFileRecord{
        private String mchtNo;
        private String reqId;
        private String cardNo;
        private String type;
        private Long amount;
        private Long fee;
        private String status;
        private String transTime;
        private String extra1;
        private String extra2;
        private String extra3;

        @Override
        public String toString() {
            return "CheckFileRecord{" +
                    "mchtNo='" + mchtNo + '\'' +
                    ", reqId='" + reqId + '\'' +
                    ", cardNo='" + cardNo + '\'' +
                    ", type='" + type + '\'' +
                    ", amount=" + amount +
                    ", fee=" + fee +
                    ", status='" + status + '\'' +
                    ", transTime='" + transTime + '\'' +
                    ", extra1='" + extra1 + '\'' +
                    ", extra2='" + extra2 + '\'' +
                    ", extra3='" + extra3 + '\'' +
                    '}';
        }

        public String getMchtNo() {
            return mchtNo;
        }

        public CheckFileRecord setMchtNo(String mchtNo) {
            this.mchtNo = mchtNo;
            return this;
        }

        public String getReqId() {
            return reqId;
        }

        public CheckFileRecord setReqId(String reqId) {
            this.reqId = reqId;
            return this;
        }

        public String getCardNo() {
            return cardNo;
        }

        public CheckFileRecord setCardNo(String cardNo) {
            this.cardNo = cardNo;
            return this;
        }

        public String getType() {
            return type;
        }

        public CheckFileRecord setType(String type) {
            this.type = type;
            return this;
        }

        public Long getAmount() {
            return amount;
        }

        public CheckFileRecord setAmount(Long amount) {
            this.amount = amount;
            return this;
        }

        public Long getFee() {
            return fee;
        }

        public CheckFileRecord setFee(Long fee) {
            this.fee = fee;
            return this;
        }

        public String getStatus() {
            return status;
        }

        public CheckFileRecord setStatus(String status) {
            this.status = status;
            return this;
        }

        public String getTransTime() {
            return transTime;
        }

        public CheckFileRecord setTransTime(String transTime) {
            this.transTime = transTime;
            return this;
        }

        public String getExtra1() {
            return extra1;
        }

        public CheckFileRecord setExtra1(String extra1) {
            this.extra1 = extra1;
            return this;
        }

        public String getExtra2() {
            return extra2;
        }

        public CheckFileRecord setExtra2(String extra2) {
            this.extra2 = extra2;
            return this;
        }

        public String getExtra3() {
            return extra3;
        }

        public CheckFileRecord setExtra3(String extra3) {
            this.extra3 = extra3;
            return this;
        }
    }

}
