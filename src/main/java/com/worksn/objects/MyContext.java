package com.worksn.objects;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyContext {

    @SerializedName(C_.STR_BAN_LIST)
    @Expose
    private String banList;
    @SerializedName(C_.STR_LIKE_LIST)
    @Expose
    private String likeList;


    @SerializedName(C_.STR_USERS_LIST)
    @Expose
    private ArrayList<User>usersList = null;

    @SerializedName(C_.STR_IMG_LIST)
    @Expose
    private String imgList;

    @SerializedName(C_.STR_LIFETIME)
    @Expose
    private ArrayList<AssocData> lifetime = new ArrayList<>();
    @SerializedName(C_.STR_CATEGORIES)
    @Expose
    private ArrayList<AssocData> categories = new ArrayList<>();

    @SerializedName(C_.STR_FLOAT_DATA)
    @Expose
    private Float floatData = null;

    @SerializedName(C_.STR_INTEGER_DATA)
    @Expose
    private Integer integerData = null;
    @SerializedName(C_.STR_USER)
    @Expose
    private User user = null;

    @SerializedName(C_.STR_OWNER)
    @Expose
    private User owner = new User();

    public User getSpeaker() {
        return speaker;
    }

    public void setSpeaker(User speaker) {
        this.speaker = speaker;
    }

    @SerializedName(C_.STR_SPEAKER)
    @Expose
    private User speaker = new User();



    @SerializedName(C_.STR_NOTIFIES)
    @Expose
    private ArrayList<SysNotify> notifies = new ArrayList<>();

    public ArrayList<SysNotify> getNotifies() {
        return notifies;
    }

    public void setNotifies(ArrayList<SysNotify> notifies) {
        this.notifies = notifies;
    }

    @SerializedName(C_.STR_SETTING_PAGE_DATA)
    @Expose
    private ArrayList<StructTxtData>settingPageContent = new ArrayList<>();

    public ArrayList<StructTxtData> getSettingPageContent() {
        return settingPageContent;
    }

    public void setSettingPageContent(ArrayList<StructTxtData> settingPageContent) {
        this.settingPageContent = settingPageContent;
    }

    @SerializedName(C_.STR_DISCUS)
    @Expose
    private Discus discus = new Discus();

    @SerializedName(C_.STR_ADS_COLLECTION)
    @Expose
    private final ArrayList<Ads> adsCollection = new ArrayList<Ads>();

    @SerializedName(C_.STR_REVIEW)
    @Expose
    private UserReview review;

    @SerializedName(C_.STR_USER_REVIEWS)
    @Expose
    private final List<UserReview> userReviews = new ArrayList<UserReview>();

    @SerializedName(C_.STR_TARGET_ADS)
    @Expose
    private Ads targetAds;

    @SerializedName(C_.STR_TARGET_MSG)
    @Expose
    private StructMsg targetMsg;

    @SerializedName(C_.STR_MESSAGES)
    @Expose
    private ArrayList<StructMsg> messages = new ArrayList<StructMsg>();

    @SerializedName(C_.STR_TMP_IMG)
    @Expose
    private String tmpImg = null;

    @SerializedName(C_.STR_TMP_IMG_ICON)
    @Expose
    private String tmpImgIcon = null;

    @SerializedName(C_.STR_SAVE_IMG_DATA)
    @Expose
    private SaveImgData saveImgData = null;



    public void setBanList(String banList) {
        this.banList = banList;
    }

    public void setLikeList(String likeList) {
        this.likeList = likeList;
    }

    public void setUsersList(ArrayList<User> usersList) {
        this.usersList = usersList;
    }

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    public String getBanList() {
        return banList;
    }

    public String getLikeList() {
        return likeList;
    }

    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public ArrayList<AssocData> getLifetime() {
        return lifetime;
    }

    public void setLifetime(ArrayList<AssocData> lifetime) {
        this.lifetime = lifetime;
    }

    public ArrayList<AssocData> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<AssocData> categories) {
        this.categories = categories;
    }

    public SaveImgData getSaveImgData() {
        return saveImgData;
    }

    public void setSaveImgData(SaveImgData saveImgData) {
        this.saveImgData = saveImgData;
    }

    public Integer getIntegerData() {return integerData;}

    public void setIntegerData(Integer integerData) {this.integerData = integerData;}

    public Float getFloatData() {return floatData;}

    public void setFloatData(Float floatData) {this.floatData = floatData;}

    public String getTmpImg() {
        return tmpImg;
    }

    public void setTmpImg(String tmpImg) {
        this.tmpImg = tmpImg;
    }

    public String getTmpImgIcon() {
        return tmpImgIcon;
    }

    public void setTmpImgIcon(String tmpImgIcon) {
        this.tmpImgIcon = tmpImgIcon;
    }

    public void setMessages(ArrayList<StructMsg> data){
        messages = new ArrayList<StructMsg>();
        messages.addAll(data);
    }

    public StructMsg getTargetMsg() {
        return targetMsg;
    }

    public void setTargetMsg(StructMsg targetMsg) {
        this.targetMsg = targetMsg;
    }

    public List<StructMsg> getMessages() {
        return messages;
    }

    public User getUser() {
        return user;
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<Ads> getAdsCollection() {
        return adsCollection;
    }

    public Ads getTargetAds() {
        return targetAds;
    }

    public UserReview getReview() {return review;}

    public List<UserReview> getUserReviews() {
        return userReviews;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setDiscus(Discus discus) {
        this.discus = discus;
    }

    public void setReview(UserReview review) {
        this.review = review;
    }

    public void setTargetAds(Ads targetAds) {
        this.targetAds = targetAds;
    }

    public Discus getDiscus() {
        return discus;
    }
}