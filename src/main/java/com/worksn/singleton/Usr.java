package com.worksn.singleton;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.worksn.classes.BroadCastMsg;
import com.worksn.interfaces.NetCallback;
import com.worksn.objects.Ads;
import com.worksn.objects.C_;
import com.worksn.objects.MyContext;
import com.worksn.objects.MyScreen;
import com.worksn.objects.PostSubData;
import com.worksn.objects.StructMsg;
import com.worksn.objects.User;
import com.worksn.classes.MyNet;
import com.worksn.view.FrameUserProfile;
import com.worksn.view.Render;
import com.worksn.websocket.WsBroadcastReceiver;


public class Usr {
    private static Usr i;
    public static Usr i(){
        if (i == null) i = new Usr();
        return i;
    }
    private User mUser = new User();
    private boolean mAuth = false;

    public List<Integer>onlineList = new ArrayList<>();
    private final ArrayList<User> mTargetUsers = new ArrayList<>();
    private final List<Integer> mUsersIdTable = new ArrayList<>();
    private final ArrayList<FrameUserProfile> mUserProfiles = new ArrayList<>();

    public int targetUserId = 0;
    public ArrayList<Integer> getBanList(){
        String strBanList  = MyStorage.i().getString( C_.STR_BAN_LIST);
        String[] strBanListArray = null;
        ArrayList<Integer>banList  = new ArrayList<>();
        if (strBanList != null){
            strBanListArray = strBanList.split("_");
            for (String d : strBanListArray){
                if (d.length() > 0)banList.add(Integer.parseInt(d));
            }
        }
        return banList;
    }
    public ArrayList<Integer> getLikeList(){
        String strLikeList = MyStorage.i().getString(C_.STR_LIKE_LIST);
        String[] strLikeListArray;
        ArrayList<Integer>likeList = new ArrayList<>();
        if (strLikeList != null){
            strLikeListArray = strLikeList.split("_");
            for (String d : strLikeListArray){
                if (d.length() > 0)likeList.add(Integer.parseInt(d));
            }
        }
        return likeList;
    }

    public ArrayList<User> getTargetUsers() {
        return mTargetUsers;
    }
    public ArrayList<FrameUserProfile> getUserProfiles() {
        return mUserProfiles;
    }

    public void addViewUserToList(FrameUserProfile userProfile){
        mUserProfiles.add(userProfile);
    }
    public void addUserToTable(User user){
        if (!mUsersIdTable.contains(user.getId())){
            mUsersIdTable.add(user.getId());
            mTargetUsers.add(user);
        }
    }
    public void addIdToOnlineList(int id){
        if (!onlineList.contains(id))onlineList.add(id);
    }
    public void addUsersList(ArrayList<User>users){
        clearUsersList();
        mTargetUsers.addAll(users);
        for (User user : mTargetUsers){
            mUsersIdTable.add(user.getId());
        }
    }
    public void clearUsersList(){
        if (MyScreen.activeMode != C_.ACTIVE_SCREEN_USERS)
            mUserProfiles.clear();

        mTargetUsers.clear();
        mUsersIdTable.clear();

    }
    public void initUser(Context context, CB cb){
        MyNet.sendRequest(context, C_.ACT_GET_USER_DATA, null, (data, result, stringData) -> {
            if (result == -1)  {
                setUser(null);
                cb.callback(0, null);
                return;
            };
            if (data.getUser() != null){
                if (data.getUser().getId() != null){
                    setUser(data.getUser());
                    cb.callback(1, null);
                }
            }else {
                setUser(null);
                cb.callback(0, null);
            }
        });
    }

    public void setNewToken(String newToken){
        mUser.setWsToken(newToken);
    }
    public void setUser(User usr) {
        if (usr == null){
            clearUser();
            return;
        }
        try{
            if ((usr.getId() == 0)||
                (usr.getLogin().length()==0)||
                (usr.getWsToken().length()<4)){
                    mUser = null;
                    mAuth = false;
                    return;
            }
        }catch (Exception e){
            mUser = null;
            mAuth = false;
            return;
        }

        mUser = usr;
        mAuth = true;
        MyStorage.i().putData(C_.STR_USER_ID,       mUser.getId())     ;
        MyStorage.i().putData(C_.STR_USER_LOGIN,    mUser.getLogin())  ;
        MyStorage.i().putData(C_.STR_USER_NAME,     mUser.getName())   ;
        MyStorage.i().putData(C_.STR_USER_S_NAME,   mUser.getSName())  ;
        MyStorage.i().putData(C_.STR_USER_PHONE,    mUser.getPhone())  ;
        MyStorage.i().putData(C_.STR_USER_EMAIL,    mUser.getEmail())  ;
        MyStorage.i().putData(C_.STR_USER_IMG,      mUser.getImg())    ;
        MyStorage.i().putData(C_.STR_USER_IMG_ICON, mUser.getImgIcon());
        MyStorage.i().putData(C_.STR_USER_RATING,   mUser.getRating()) ;
        MyStorage.i().putData(C_.STR_USER_VOTE_QT,  mUser.getVoteQt()) ;
        MyStorage.i().putData(C_.STR_USER_RIGHTS,   mUser.getVoteQt()) ;
        MyStorage.i().putData(C_.STR_USER_WS_TOKEN, mUser.getWsToken());
        MyStorage.i().putData(C_.STR_BAN_LIST,      mUser.getBanList());
        MyStorage.i().putData(C_.STR_LIKE_LIST,     mUser.getLikeList());
    }
    public User getUser() {
        if (mUser != null)
            if ((mUser.getId() != 0)&&
                (mUser.getLogin().length()>0)&&
                (mUser.getWsToken().length()>3))
                return mUser;

        mUser = new User();
        mUser.setId(MyStorage.i()        .getInt     (C_.STR_USER_ID       ));
        mUser.setLogin(MyStorage.i()     .getString  (C_.STR_USER_LOGIN    ));
        mUser.setName(MyStorage.i()      .getString  (C_.STR_USER_NAME     ));
        mUser.setSName(MyStorage.i()     .getString  (C_.STR_USER_S_NAME   ));
        mUser.setPhone(MyStorage.i()     .getString  (C_.STR_USER_PHONE    ));
        mUser.setEmail(MyStorage.i()     .getString  (C_.STR_USER_EMAIL    ));
        mUser.setImg(MyStorage.i()       .getString  (C_.STR_USER_IMG      ));
        mUser.setImgIcon(MyStorage.i()   .getString  (C_.STR_USER_IMG_ICON ));
        mUser.setRating(MyStorage.i()    .getFloat   (C_.STR_USER_RATING   ));
        mUser.setVoteQt(MyStorage.i()    .getInt     (C_.STR_USER_VOTE_QT  ));
        mUser.setRights(MyStorage.i()    .getInt     (C_.STR_USER_RIGHTS   ));
        mUser.setWsToken(MyStorage.i()   .getString  (C_.STR_USER_WS_TOKEN ));
        mUser.setBanList(MyStorage.i()   .getString  (C_.STR_BAN_LIST      ));
        mUser.setLikeList(MyStorage.i()  .getString  (C_.STR_LIKE_LIST     ));
        

        if ((mUser.getId() != 0)&&
            (mUser.getLogin().length()>0)&&
            (mUser.getWsToken().length()>3))
            return mUser;
        else
            return null;
    }
    public User getUserDataFromObject(Object o){
        if (o == null)return null;
        String name = o.getClass().getSimpleName();

        if (name.equals("Ads")){
            Ads ads = (Ads)o;
            User user = new User();
            user.setId(ads.getUserId());
            user.setLogin(ads.getUserLogin());
            user.setImgIcon(ads.getUserImgIcon());
            user.setRating(ads.getUserRating());
            return user;
        }
        if (name.equals("StructMsg")){
            StructMsg msg = (StructMsg)o;
            User user = new User();
            user.setId(msg.getSpeakerId());
            user.setLogin(msg.getSpeakerLogin());
            user.setImgIcon(msg.getSpeakerImg());
            user.setRating(msg.getSpeakerRating());
            return user;
        }
        return null;
    }
    public boolean auth(){
        if (!mAuth){
            User mUser = getUser();
            if (mUser != null) mAuth = true;
        }
        return mAuth;
    }
    public void checkNewNotify(Context context){
        MyNet.sendRequest(context,C_.ACT_CHECK_NEW_NOTIFY, null,(data, result, stringData)->{
            new Render((Activity) context).notifySign(result == 1);
        });
    }

    public void clearUser(){
        mUser = null;
        mAuth = false;
        MyStorage.i().clearField(C_.STR_USER_ID       );
        MyStorage.i().clearField(C_.STR_USER_LOGIN    );
        MyStorage.i().clearField(C_.STR_USER_NAME     );
        MyStorage.i().clearField(C_.STR_USER_S_NAME   );
        MyStorage.i().clearField(C_.STR_USER_PHONE    );
        MyStorage.i().clearField(C_.STR_USER_EMAIL    );
        MyStorage.i().clearField(C_.STR_USER_IMG      );
        MyStorage.i().clearField(C_.STR_USER_IMG_ICON );
        MyStorage.i().clearField(C_.STR_USER_RATING   );
        MyStorage.i().clearField(C_.STR_USER_VOTE_QT  );
        MyStorage.i().clearField(C_.STR_USER_RIGHTS   );
        MyStorage.i().clearField(C_.STR_USER_WS_TOKEN );
        MyStorage.i().clearField(C_.STR_BAN_LIST      );
        MyStorage.i().clearField(C_.STR_LIKE_LIST     );
    }
    public void loginUser(Context context, String login, String password, CB cb){
        PostSubData data = new PostSubData();
        data.setLogin(login);
        data.setPassword(password);
        MyNet.sendRequest(context, C_.ACT_LOGIN, data, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                if(result == 0)
                    PUWindow.i().show(stringData);
                if(result == 1){
                    setUser(data.getUser());
                    cb.callback(1, getUser());
                }
                if (result == -1)
                    PUWindow.i().show(stringData);
            }
        });
    }
    public void anonymousLogin(Context context, CB cb){
        PostSubData userData = new PostSubData();
        MyNet.sendRequest(context,C_.ACT_ANONYMOUS_LOGIN, userData, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                if(result == 0)
                    PUWindow.i().show(stringData);
                if(result == 1){
                    setUser(data.getUser());
                    cb.callback(1, data.getUser());
                }
                if (result == -1)
                    PUWindow.i().show(stringData);
            }
        });
    }
    public void exit(Context context, CB cb){
        MyNet.sendRequest(context,C_.ACT_EXIT, null, (data, result, stringData) -> {
            setUser(null);
            if (cb != null)
                cb.callback(0, null);
        });
    }
    public void requestUsersStatus(Context context){
        String listId = "";
        listId = getTargetUsersList();
        if (listId.length()>0){
            listId = listId.substring(0, listId.length()-1);
            HashMap<String, Object>map = new HashMap<>();
            map.put(C_.STR_ACT, C_.ACT_GET_ONLINE_STATUS);
            map.put(C_.STR_ID_LIST, listId);
            new BroadCastMsg(context, map, WsBroadcastReceiver.BROADCAST_FILTER);
        }
    }
    public void requestUserStatus(Context context, Integer userId){
        if (userId == null) return;
        String id = userId.toString();
        HashMap<String, Object>map = new HashMap<>();
        map.put(C_.STR_ACT, C_.ACT_GET_ONLINE_STATUS);
        map.put(C_.STR_ID_LIST, id);
        new BroadCastMsg(context, map, WsBroadcastReceiver.BROADCAST_FILTER);
    }
    private String getTargetUsersList(){
        String listId = "";
        for (Integer userId : mUsersIdTable){
            listId = String.format("%s%s_", listId, userId.toString());
        }
        return listId;
    }

    public interface CB{
       void callback(int code, Object data);
    }
}
