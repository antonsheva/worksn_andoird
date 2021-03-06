package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostSubData {
    @SerializedName(C_.STR_USER_ID)
    @Expose
    private Integer userId = null;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @SerializedName(C_.STR_SPEAKER_ID)
    @Expose
    private Integer speakerId = null;

    @SerializedName(C_.STR_OWNER_ID)
    @Expose
    Integer ownerId = null;

    @SerializedName(C_.STR_ID)
    @Expose
    private Long id = null;

    @SerializedName(C_.STR_CONTENT)
    @Expose
    private String content = null;

    @SerializedName(C_.STR_ADS_ID)
    @Expose
    private Long ads_id = null;

    @SerializedName(C_.STR_CONSUMER_ID)
    @Expose
    private Integer consumerId = null;

    @SerializedName(C_.STR_SENDER_ID)
    @Expose
    Integer senderId = null;

    @SerializedName(C_.STR_DISCUS_ID)
    @Expose
    Long discusId = null;

    @SerializedName(C_.STR_IMG)
    @Expose
    String img=null;

    @SerializedName(C_.STR_IMG_ICON)
    @Expose
    String imgIcon=null;

    @SerializedName(C_.STR_LOGIN)
    @Expose
    String login=null;

    @SerializedName(C_.STR_PASSWORD)
    @Expose
    String password=null;

    @SerializedName(C_.STR_NEW_PASS)
    @Expose
    String new_pass=null;

    @SerializedName(C_.STR_NAME)
    @Expose
    String name=null;

    @SerializedName(C_.STR_S_NAME)
    @Expose
    String s_name=null;

    @SerializedName(C_.STR_EMAIL)
    @Expose
    String email=null;

    @SerializedName(C_.STR_PHONE)
    @Expose
    String phone=null;

    @SerializedName(C_.STR_ABOUT_USER)
    @Expose
    String about_user=null;

    @SerializedName(C_.STR_FILE_NAME)
    @Expose
    String fileName = null;

    @SerializedName(C_.STR_TXT_REVIEW)
    @Expose
    String txtReview = null;

    @SerializedName(C_.STR_STAR_QT)
    @Expose
    Integer starQt = 0;

    @SerializedName(C_.STR_CREATE_ID)
    @Expose
    String createId = null;

    @SerializedName(C_.STR_REPLY_MSG_ID)
    @Expose
    private Long replyMsgId = null;

    @SerializedName(C_.STR_REPLY_SENDER_ID)
    @Expose
    private Integer replySenderId = null;

    @SerializedName(C_.STR_REPLY_SENDER_LOGIN)
    @Expose
    private String replySenderLogin = null;

    @SerializedName(C_.STR_REPLY_CONTENT)
    @Expose
    private String replyContent = null;

    @SerializedName(C_.STR_REPLY_IMG)
    @Expose
    private String replyImg;

    @SerializedName(C_.STR_STATUS)
    @Expose
    private Integer status = null;

    @SerializedName( C_.STR_SUBJECT_ID)
    @Expose
    private Integer subjectId = null;




    public String getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(String imgIcon) {
        this.imgIcon = imgIcon;
    }

    public Integer getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(Integer speakerId) {
        this.speakerId = speakerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Long getReplyMsgId() {
        return replyMsgId;
    }

    public void setReplyMsgId(Long replyMsgId) {
        this.replyMsgId = replyMsgId;
    }

    public Integer getReplySenderId() {
        return replySenderId;
    }

    public void setReplySenderId(Integer replySenderId) {
        this.replySenderId = replySenderId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
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

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }


    public PostSubData(){

    }
    public Long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public Long getAds_id() {
        return ads_id;
    }
    public Integer getConsumerId() {
        return consumerId;
    }
    public Integer getSenderId() {
        return senderId;
    }
    public Long getDiscusId() {
        return discusId;
    }
    public String getImg() {
        return img;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getNewPass() {
        return new_pass;
    }
    public String getName() {
        return name;
    }
    public String getS_name() {
        return s_name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getAbout_user() {
        return about_user;
    }
    public Integer getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getTxtReview() {
        return txtReview;
    }
    public void setTxtReview(String txtReview) {
        this.txtReview = txtReview;
    }
    public Integer getStarQt() {
        return starQt;
    }
    public void setStarQt(Integer starQt) {
        this.starQt = starQt;
    }
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }
    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }
    public void setAds_id(Long ads_id) {
        this.ads_id = ads_id;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setDiscusId(Long discusId) {
        this.discusId = discusId;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setS_name(String s_name) {
        this.s_name = s_name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setAboutUser(String about_user) {
        this.about_user = about_user;
    }

    public void setNewPass(String new_pass) {
        this.new_pass = new_pass;
    }
}
