package com.worksn.websocket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.worksn.objects.C_;
import com.worksn.objects.StructMsg;

public class WsReceiveData {
    @SerializedName(C_.STR_ID_LIST)
    @Expose
    private String idList = null;
    @SerializedName(C_.STR_UNREAD_MSG)
    @Expose
    private Integer unreadMsg = null;
    @SerializedName(C_.STR_MSG)
    @Expose
    private StructMsg msg;
    @SerializedName(C_.STR_DATA)
    @Expose
    private Object data = null;
    @SerializedName(C_.STR_RESULT)
    @Expose
    private Integer result = null;
    @SerializedName(C_.STR_ERROR)
    @Expose
    private String error = null;
    @SerializedName(C_.STR_TYPE)
    @Expose
    private String type = null;
    @SerializedName(C_.STR_ID)
    @Expose
    private Long id;
    @SerializedName(C_.STR_DISCUS_ID)
    @Expose
    private Long discusId = null;
    @SerializedName(C_.STR_ADS_ID)
    @Expose
    private Long adsId;
    @SerializedName(C_.STR_SENDER_ID)
    @Expose
    private Integer senderId;
    @SerializedName(C_.STR_SENDER_LOGIN)
    @Expose
    private String senderLogin;
    @SerializedName(C_.STR_SENDER_IMG)
    @Expose
    private String senderImg;
    @SerializedName(C_.STR_STATUS_MSG)
    @Expose
    private Integer statusMsg = 0;
    @SerializedName(C_.STR_CONSUMER_ID)
    @Expose
    private Integer consumerId;
    @SerializedName(C_.STR_CONTENT)
    @Expose
    private String content;
    @SerializedName(C_.STR_IMG)
    @Expose
    private String img;
    @SerializedName(C_.STR_IMG_ICON)
    @Expose
    private String imgIcon;
    @SerializedName(C_.STR_USER_ID)
    @Expose
    private Integer userId;
    @SerializedName(C_.STR_USER_LOGIN)
    @Expose
    private String userLogin;
    @SerializedName(C_.STR_STATUS)
    @Expose
    private Integer status;
    @SerializedName(C_.STR_CREATE_DATE)
    @Expose
    private String createDate;
    @SerializedName(C_.STR_MSG_ID)
    @Expose
    private Long msgId;
    @SerializedName(C_.STR_CREATE_ID)
    @Expose
    private String createId;
    @SerializedName(C_.STR_NEW_TOKEN)
    @Expose
    private String newToken = null;
    @SerializedName(C_.STR_REPLY_MSG_ID)
    @Expose
    private Long replyMsgId = null;
    @SerializedName(C_.STR_REPLY_CONTENT)
    @Expose
    private String replyContent = null;
    @SerializedName(C_.STR_REPLY_SENDER_ID)
    @Expose
    private Integer replySenderId = null;
    @SerializedName(C_.STR_REPLY_SENDER_LOGIN)
    @Expose
    private String replySenderLogin = null;
    @SerializedName(C_.STR_REPLY_IMG)
    @Expose
    private String replyImg = null;

    public String getNewToken() {
        return newToken;
    }
    public void setNewToken(String newToken) {
        this.newToken = newToken;
    }
    public Long getReplyMsgId() {
        return replyMsgId;
    }
    public void setReplyMsgId(Long replyMsgId) {
        this.replyMsgId = replyMsgId;
    }
    public String getReplyContent() {
        return replyContent;
    }
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
    public Integer getReplySenderId() {
        return replySenderId;
    }
    public void setReplySenderId(Integer replySenderId) {
        this.replySenderId = replySenderId;
    }
    public String getReplySenderLogin() {
        return replySenderLogin;
    }
    public void setReplySenderLogin(String replySenderLogin) {
        this.replySenderLogin = replySenderLogin;
    }
    public String getReplyImg() {
        return replyImg;
    }
    public void setReplyImg(String replyImg) {
        this.replyImg = replyImg;
    }
    public String getUserLogin() {
        return userLogin;
    }
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
    public Long getMsgId() {
        return msgId;
    }
    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }
    public String getCreateId() {
        return createId;
    }
    public void setCreateId(String createId) {
        this.createId = createId;
    }
    public String getIdList() {
        return idList;
    }
    public void setIdList(String idList) {
        this.idList = idList;
    }
    public Integer getUnreadMsg() {
        return unreadMsg;
    }
    public void setUnreadMsg(Integer unreadMsg) {
        this.unreadMsg = unreadMsg;
    }
    public Integer getStatusMsg() {
        return statusMsg;
    }
    public void setStatusMsg(Integer statusMsg) {
        this.statusMsg = statusMsg;
    }
    public String getSenderLogin() {
        return senderLogin;
    }
    public void setSenderLogin(String senderLogin) {
        this.senderLogin = senderLogin;
    }
    public String getSenderImg() {
        return senderImg;
    }
    public void setSenderImg(String senderImg) {
        this.senderImg = senderImg;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getDiscusId() {
        return discusId;
    }
    public void setDiscusId(Long discusId) {
        this.discusId = discusId;
    }
    public Long getAdsId() {
        return adsId;
    }
    public void setAdsId(Long adsId) {
        this.adsId = adsId;
    }
    public Integer getSenderId() {
        return senderId;
    }
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }
    public Integer getConsumerId() {
        return consumerId;
    }
    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getImgIcon() {
        return imgIcon;
    }
    public void setImgIcon(String imgIcon) {
        this.imgIcon = imgIcon;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public StructMsg getMsg() {
        return msg;
    }
    public void setMsg(StructMsg msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public Integer getResult() {
        return result;
    }
    public void setResult(Integer result) {
        this.result = result;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
