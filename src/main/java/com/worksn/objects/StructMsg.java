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

    public void setSender_rating(Float sender_rating) {
        this.sender_rating = sender_rating;
    }

    public void setSenderAvatar(String senderAvatar) {
        this.senderAvatar = senderAvatar;
    }

    public void setConsumer_id(Integer consumer_id) {
        this.consumer_id = consumer_id;
    }

    public void setConsumer_login(String consumer_login) {
        this.consumer_login = consumer_login;
    }

    public void setConsumer_rating(Float consumer_rating) {this.consumer_rating = consumer_rating;}

    public void setConsumerAvatar(String consumerAvatar) {
        this.consumerAvatar = consumerAvatar;
    }

    public Integer getSpeaker_id() {
        return speaker_id;
    }

    public void setSpeaker_id(Integer speaker_id) {
        this.speaker_id = speaker_id;
    }

    public String getSpeaker_login() {
        return speaker_login;
    }

    public void setSpeaker_login(String speaker_login) {
        this.speaker_login = speaker_login;
    }

    public Float getSpeaker_rating() {
        return speaker_rating;
    }

    public void setSpeaker_rating(Float speaker_rating) {
        this.speaker_rating = speaker_rating;
    }

    public String getSpeaker_img() {
        return speaker_img;
    }

    public void setSpeaker_img(String speaker_img) {
        this.speaker_img = speaker_img;
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

    public void setDeliver(Integer deliver) {
        this.deliver = deliver;
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

    public void setAds_type(Integer ads_type) {
        this.ads_type = ads_type;
    }

    public void setRmv_1(Integer rmv_1) {
        this.rmv_1 = rmv_1;
    }

    public void setRmv_2(Integer rmv_2) {
        this.rmv_2 = rmv_2;
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

    public Float getSender_rating() {
        return sender_rating;
    }

    public String getSenderAvatar() {
        return senderAvatar;
    }

    public Integer getConsumer_id() {
        return consumer_id;
    }

    public String getConsumer_login() {
        return consumer_login;
    }

    public Float getConsumer_rating() {
        return consumer_rating;
    }

    public String getConsumerAvatar() {
        return consumerAvatar;
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

    public Integer getDeliver() {
        return deliver;
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

    public Integer getAds_type() {
        return ads_type;
    }

    public Integer getRmv_1() {
        return rmv_1;
    }

    public Integer getRmv_2() {
        return rmv_2;
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

    @SerializedName("create_id")
    @Expose
    private String createId = null;

    @SerializedName("id")
    @Expose
    private Long id = null;

    @SerializedName("ads_category")
    @Expose
    private Integer adsCategory = null;

    @SerializedName("sender_id")
    @Expose
    private Integer sender_id = null;

    @SerializedName("sender_login")
    @Expose
    private String sender_login = null;

    @SerializedName("sender_rating")
    @Expose
    private Float sender_rating = null;

    @SerializedName("sender_avatar")
    @Expose
    private String senderAvatar = null;

    @SerializedName("consumer_id")
    @Expose
    private Integer consumer_id = null;

    @SerializedName("consumer_login")
    @Expose
    private String consumer_login = null;

    @SerializedName("consumer_rating")
    @Expose
    private Float consumer_rating = null;

    @SerializedName("consumer_img")
    @Expose
    private String consumerAvatar = null;

    @SerializedName("speaker_id")
    @Expose
    private Integer speaker_id = null;

    @SerializedName("speaker_login")
    @Expose
    private String speaker_login = null;

    @SerializedName("speaker_rating")
    @Expose
    private Float speaker_rating = null;

    @SerializedName("speaker_img")
    @Expose
    private String speaker_img = null;

    @SerializedName("speaker_online")
    @Expose
    Boolean speakerOnline = null;

    @SerializedName("content")
    @Expose
    private String content = null;

    @SerializedName("img")
    @Expose
    private String  img = null;

    @SerializedName("img_icon")
    @Expose
    private String imgIcon = null;

    @SerializedName("cost")
    @Expose
    private Integer cost = null;

    @SerializedName("view")
    @Expose
    private Integer view = null;

    @SerializedName("deliver")
    @Expose
    private Integer deliver = null;

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

    @SerializedName("ads_type")
    @Expose
    private Integer ads_type = null;

    @SerializedName("rmv_1")
    @Expose
    private Integer rmv_1 = null;

    @SerializedName("rmv_2")
    @Expose
    private Integer rmv_2 = null;

    @SerializedName("sys_notify")
    @Expose
    private Integer sysNotify = null;



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
}
