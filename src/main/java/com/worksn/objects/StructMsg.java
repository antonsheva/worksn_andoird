package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class StructMsg {
    public void setId(Long id) {
        this.id = id;
    }

    public void setSender_id(Integer sender_id) {
        this.sender_id = sender_id;
    }

    public void setSender_login(String sender_login) {
        this.sender_login = sender_login;
    }

    public void setSenderImg(String senderImg) {
        this.senderImg = senderImg;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public Integer getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(Integer speakerId) {
        this.speakerId = speakerId;
    }

    public String getSpeakerLogin() {
        return speakerLogin;
    }

    public void setSpeakerLogin(String speakerLogin) {
        this.speakerLogin = speakerLogin;
    }

    public Float getSpeakerRating() {
        return speakerRating;
    }

    public void setSpeakerRating(Float speakerRating) {
        this.speakerRating = speakerRating;
    }

    public String getSpeakerImg() {
        return speakerImg;
    }

    public void setSpeakerImg(String speakerImg) {
        this.speakerImg = speakerImg;
    }

    public Boolean getSpeakerOnline() {
        if (speakerOnline == null) speakerOnline = false;
        return speakerOnline;
    }

    public void setSpeakerOnline(Boolean speakerOnline) {
        this.speakerOnline = speakerOnline;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public void setDiscus_id(Long discus_id) {
        this.discus_id = discus_id;
    }

    public void setAds_id(Long ads_id) {
        this.ads_id = ads_id;
    }

    public void setAds_description(String ads_description) {
        this.ads_description = ads_description;
    }

    public Long getId() {
        return id;
    }

    public Integer getSender_id() {
        return sender_id;
    }

    public String getSender_login() {
        return sender_login;
    }

    public String getSenderImg() {
        return senderImg;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public String getContent() {
        return content;
    }

    public String getImg() {
        return img;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getView() {
        return view;
    }

    public Long getDiscus_id() {
        return discus_id;
    }

    public Long getAds_id() {
        return ads_id;
    }

    public String getAds_description() {
        return ads_description;
    }

    public String getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(String imgIcon) {
        this.imgIcon = imgIcon;
    }

    public Integer getAdsCategory() {
        return adsCategory;
    }

    public void setAdsCategory(Integer adsCategory) {
        this.adsCategory = adsCategory;
    }

    public String getImgGallery() {
        return imgGallery;
    }

    public void setImgGallery(String imgGallery) {
        this.imgGallery = imgGallery;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getImgIconGallery() {
        return imgIconGallery;
    }

    public void setImgIconGallery(String imgIconGallery) {
        this.imgIconGallery = imgIconGallery;
    }

    public Integer getSysNotify() {
        return sysNotify;
    }

    public void setSysNotify(Integer sysNotify) {
        this.sysNotify = sysNotify;
    }

    private Integer viewPosition;

    public Integer getViewPosition() {
        return viewPosition;
    }

    public void setViewPosition(Integer viewPosition) {
        this.viewPosition = viewPosition;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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

    public String getReplyImg() {
        return replyImg;
    }

    public void setReplyImg(String replyImg) {
        this.replyImg = replyImg;
    }

    public String getReplySenderLogin() {
        return replySenderLogin;
    }

    public void setReplySenderLogin(String replySenderLogin) {
        this.replySenderLogin = replySenderLogin;
    }

    private String imgGallery     = null;
    private String imgIconGallery = null;



    @SerializedName(C_.STR_CREATE_ID)
    @Expose
    private String createId = null;

    @SerializedName(C_.STR_ID)
    @Expose
    private Long id = null;

    @SerializedName(C_.STR_ADS_CATEGORY)
    @Expose
    private Integer adsCategory = null;

    @SerializedName(C_.STR_SENDER_ID)
    @Expose
    private Integer sender_id = null;

    @SerializedName(C_.STR_SENDER_LOGIN)
    @Expose
    private String sender_login = null;

    @SerializedName(C_.STR_SENDER_IMG)
    @Expose
    private String senderImg = null;

    @SerializedName(C_.STR_CONSUMER_ID)
    @Expose
    private Integer consumerId = null;

    @SerializedName(C_.STR_SPEAKER_ID)
    @Expose
    private Integer speakerId = null;

    @SerializedName(C_.STR_SPEAKER_LOGIN)
    @Expose
    private String speakerLogin = null;

    @SerializedName(C_.STR_SPEAKER_RATING)
    @Expose
    private Float speakerRating = null;

    @SerializedName(C_.STR_SPEAKER_IMG)
    @Expose
    private String speakerImg = null;

    @SerializedName(C_.STR_SPEAKER_ONLINE)
    @Expose
    Boolean speakerOnline = null;

    @SerializedName(C_.STR_CONTENT)
    @Expose
    private String content = null;

    @SerializedName(C_.STR_IMG)
    @Expose
    private String  img = null;

    @SerializedName(C_.STR_IMG_ICON)
    @Expose
    private String imgIcon = null;

    @SerializedName(C_.STR_COST)
    @Expose
    private Integer cost = null;

    @SerializedName(C_.STR_VIEW)
    @Expose
    private Integer view = null;

    @SerializedName("create_date")
    @Expose
    private String createDate = null;

    @SerializedName("discus_id")
    @Expose
    private Long discus_id = null;

    @SerializedName("ads_id")
    @Expose
    private Long ads_id = null;

    @SerializedName("ads_description")
    @Expose
    private String ads_description = null;

    @SerializedName("sys_notify")
    @Expose
    private Integer sysNotify = null;

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
}
