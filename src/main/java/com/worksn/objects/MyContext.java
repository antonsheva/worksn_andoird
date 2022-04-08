package com.worksn.objects;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyContext {

    @SerializedName(C_.VAR_BAN_LIST)
    @Expose
    private String banList;
    @SerializedName(C_.VAR_LIKE_LIST)
    @Expose
    private String likeList;


    @SerializedName(C_.VAR_USERS_LIST)
    @Expose
    private ArrayList<User>usersList = null;

    @SerializedName(C_.VAR_IMG_LIST)
    @Expose
    private String imgList;

    @SerializedName("lifetime")
    @Expose
    private ArrayList<AssocData> lifetime = new ArrayList<>();
    @SerializedName("categories")
    @Expose
    private ArrayList<AssocData> categories = new ArrayList<>();

    @SerializedName("session_id")
    @Expose
    private String session_id;

    @SerializedName("float_data")
    @Expose
    private Float floatData = null;

    @SerializedName("integer_data")
    @Expose
    private Integer integerData = null;
    @SerializedName("user")
    @Expose
    private User user = null;

    @SerializedName("owner")
    @Expose
    private User owner = new User();

    public User getSpeaker() {
        return speaker;
    }

    public void setSpeaker(User speaker) {
        this.speaker = speaker;
    }

    @SerializedName("speaker")
    @Expose
    private User speaker = new User();



    @SerializedName("notifies")
    @Expose
    private ArrayList<SysNotify> notifies = new ArrayList<>();

    public ArrayList<SysNotify> getNotifies() {
        return notifies;
    }

    public void setNotifies(ArrayList<SysNotify> notifies) {
        this.notifies = notifies;
    }

    @SerializedName("setting_page_data")
    @Expose
    private ArrayList<StructTxtData>settingPageContent = new ArrayList<>();

    public ArrayList<StructTxtData> getSettingPageContent() {
        return settingPageContent;
    }

    public void setSettingPageContent(ArrayList<StructTxtData> settingPageContent) {
        this.settingPageContent = settingPageContent;
    }

    @SerializedName("discus")
    @Expose
    private Discus discus = new Discus();

    @SerializedName("ads_collection")
    @Expose
    private final ArrayList<Ads> adsCollection = new ArrayList<Ads>();

    @SerializedName("review")
    @Expose
    private UserReview review;

    @SerializedName("user_reviews")
    @Expose
    private final List<UserReview> userReviews = new ArrayList<UserReview>();

    @SerializedName("target_ads")
    @Expose
    private Ads targetAds;

    @SerializedName("target_msg")
    @Expose
    private StructMsg targetMsg;

    @SerializedName("messages")
    @Expose
    private ArrayList<StructMsg> messages = new ArrayList<StructMsg>();

    @SerializedName("cat_list_w")
    @Expose
    private ArrayList<String> catListWorker = new ArrayList<String>();

    @SerializedName("cat_list_e")
    @Expose
    private ArrayList<String> catListEmployer;

    @SerializedName("lifetime_list")
    @Expose
    private ArrayList<Double> lifetimeList;

    @SerializedName("lifetime_names")
    @Expose
    private ArrayList<String> lifetimeNames;


    @SerializedName("ads_owner_id")
    @Expose
    private Integer adsOwnerId;

    @SerializedName("s_token")
    @Expose
    private String sToken;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("page_name")
    @Expose
    private String pageName;

    @SerializedName("ads_type")
    @Expose
    private Integer adsType;

    @SerializedName("ads_category")
    @Expose
    private Integer adsCategory;

    @SerializedName("tmp_img")
    @Expose
    private String tmpImg = null;

    @SerializedName("tmp_img_icon")
    @Expose
    private String tmpImgIcon = null;

    @SerializedName("save_img_data")
    @Expose
    private SaveImgData saveImgData = null;

    @SerializedName("cat_list")
    @Expose
    private ArrayList<String> catList;


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

    public ArrayList<String> getLifetimeNames() {
        return lifetimeNames;
    }

    public void setLifetimeNames(ArrayList<String> lifetimeNames) {
        this.lifetimeNames = lifetimeNames;
    }

    public ArrayList<String> getCatList() {
        return catList;
    }

    public void setCatList(ArrayList<String> catList) {
        this.catList = catList;
    }

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

    public ArrayList<String> getCatListWorker() {
        return catListWorker;
    }

    public void setCatListWorker(ArrayList<String> catListWorker) {
        this.catListWorker = catListWorker;
    }

    public ArrayList<String> getCatListEmployer() {
        return catListEmployer;
    }

    public void setCatListEmployer(ArrayList<String> catListEmployer) {
        this.catListEmployer = catListEmployer;
    }

    public ArrayList<Double> getLifetimeList() {
        return lifetimeList;
    }

    public void setLifetimeList(ArrayList<Double> lifetimeList) {
        this.lifetimeList = lifetimeList;
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

    public Integer getAdsOwnerId() {
        return adsOwnerId;
    }

    public String getsToken() {
        return sToken;
    }

    public String getDate() {
        return date;
    }

    public String getPageName() {
        return pageName;
    }

    public Integer getAdsType() {
        return adsType;
    }

    public Integer getAdsCategory() {
        return adsCategory;
    }

    public Ads getTargetAds() {
        return targetAds;
    }

    public UserReview getReview() {return review;}

    public List<UserReview> getUserReviews() {
        return userReviews;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
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

    public void setAdsOwnerId(Integer adsOwnerId) {
        this.adsOwnerId = adsOwnerId;
    }

    public void setsToken(String sToken) {
        this.sToken = sToken;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public void setAdsType(Integer adsType) {
        this.adsType = adsType;
    }

    public void setAdsCategory(Integer adsCategory) {
        this.adsCategory = adsCategory;
    }

    public Discus getDiscus() {
        return discus;
    }
}