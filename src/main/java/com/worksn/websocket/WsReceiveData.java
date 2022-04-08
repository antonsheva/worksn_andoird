package com.worksn.websocket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.worksn.objects.C_;
import com.worksn.objects.StructMsg;

public class WsReceiveData {
    @SerializedName("id_list")
    @Expose
    private String idList = null;
    @SerializedName("unread_msg")
    @Expose
    private Integer unreadMsg = null;
    @SerializedName("msg")
    @Expose
    private StructMsg msg;
    @SerializedName("data")
    @Expose
    private Object data = null;
    @SerializedName("result")
    @Expose
    private Integer result = null;
    @SerializedName("error")
    @Expose
    private String error = null;
    @SerializedName("type")
    @Expose
    private String type = null;
    @SerializedName("date_time")
    @Expose
    private String date_time = null;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("discus_id")
    @Expose
    private Long discusId = null;
    @SerializedName("ads_id")
    @Expose
    private Long adsId;
    @SerializedName("sender_id")
    @Expose
    private Integer senderId;
    @SerializedName("sender_login")
    @Expose
    private String senderLogin;
    @SerializedName("sender_avatar")
    @Expose
    private String senderAvatar;
    @SerializedName("status_msg")
    @Expose
    private Integer statusMsg = 0;
    @SerializedName("consumer_id")
    @Expose
    private Integer consumerId;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("img_icon")
    @Expose
    private String imgIcon;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("user_login")
    @Expose
    private String userLogin;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("msg_id")
    @Expose
    private Long msgId;
    @SerializedName("create_id")
    @Expose
    private String createId;

    @SerializedName(C_.VAR_NEW_TOKEN)
    @Expose
    private String newToken = null;

    public String getNewToken() {
        return newToken;
    }

    public void setNewToken(String newToken) {
        this.newToken = newToken;
    }

    @SerializedName(C_.VAR_REPLY_MSG_ID)
    @Expose
    private Long replyMsgId = null;

    @SerializedName(C_.VAR_REPLY_CONTENT)
    @Expose
    private String replyContent = null;

    @SerializedName(C_.VAR_REPLY_SENDER_ID)
    @Expose
    private Integer replySenderId = null;

    @SerializedName(C_.VAR_REPLY_SENDER_LOGIN)
    @Expose
    private String replySenderLogin = null;

    @SerializedName(C_.VAR_REPLY_IMG)
    @Expose
    private String replyImg = null;

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
    public String getSenderAvatar() {
        return senderAvatar;
    }
    public void setSenderAvatar(String senderAvatar) {
        this.senderAvatar = senderAvatar;
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
    public String getDate_time() {
        return date_time;
    }
    public void setDate_time(String date_time) {
        this.date_time = date_time;
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
