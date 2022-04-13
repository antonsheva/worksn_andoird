package com.worksn.singleton;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

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
import com.worksn.static_class.Post;
import com.worksn.view.FrameUserProfile;
import com.worksn.view.MyView;
import com.worksn.websocket.WsBroadcastReceiver;


public class Usr {
    private static Usr i;
    public static Usr i(){
        if (i == null) i = new Usr();
        return i;
    }
    private static User user = new User();
    private boolean auth = false;

    public List<Integer>onlineList = new ArrayList<>();
    private static final ArrayList<User> targetUsers = new ArrayList<User>();
    private static final List<Integer> usersIdTable = new ArrayList<Integer>();
    private final ArrayList<FrameUserProfile>userProfiles = new ArrayList< >();

    public int targetUserId = 0;
    public ArrayList<Integer> getBanList(){
        String strBanList  = MyStorage.i().getString( C_.STR_BAN_LIST);
        Log.i("MyBw", "banList -> "+strBanList);
        String[] strBanListArray = null;
        ArrayList<Integer>banList  = new ArrayList<>();
        if (strBanList != null){
            strBanListArray = strBanList.split("_");
            for (String d : strBanListArray){
                if (d.length() > 0)banList.add(Integer.parseInt(d));
            }
        }
        for (Integer d : banList){
            Log.i("MyBw", "banList -> "+d.toString());
        }
        return banList;
    }
    public ArrayList<Integer> getLikeList(){
        String strLikeList = MyStorage.i().getString(C_.STR_LIKE_LIST);
        Log.i("MyBw", "likeList -> "+strLikeList);

        String[] strLikeListArray = null;
        ArrayList<Integer>likeList = new ArrayList<>();
        if (strLikeList != null){
            strLikeListArray = strLikeList.split("_");
            for (String d : strLikeListArray){
                if (d.length() > 0)likeList.add(Integer.parseInt(d));
            }
        }
        for (Integer d : likeList){
            Log.i("MyBw", "likeList -> "+d.toString());
        }
        return likeList;
    }

    public ArrayList<User> getTargetUsers() {
        return targetUsers;
    }
    public ArrayList<FrameUserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void addViewUserToList(FrameUserProfile userProfile){
        userProfiles.add(userProfile);
    }
    public void addUserToTable(User user){
        if (!usersIdTable.contains(user.getId())){
            usersIdTable.add(user.getId());
            targetUsers.add(user);
        }
    }
    public void addIdToOnlineList(int id){
        if (!onlineList.contains(id))onlineList.add(id);
    }
    public void addUsersList(ArrayList<User>users){
        clearUsersList();
        targetUsers.addAll(users);
        for (User user : targetUsers){
            usersIdTable.add(user.getId());
        }
    }
    public void clearUsersList(){
        if (MyScreen.activeMode != C_.ACTIVE_SCREEN_USERS)
            userProfiles.clear();

        targetUsers.clear();
        usersIdTable.clear();

    }
    public void initUser(Context context, CB cb){
        Post.sendRequest(context, "get_user_data", null, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
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
            }
        });
    }

    public void setNewToken(String newToken){
        user.setWsToken(newToken);
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
                    user = null;
                    auth = false;
                    return;
            }
        }catch (Exception e){
            user = null;
            auth = false;
            return;
        }

        user = usr;
        auth = true;
        MyStorage.i().putData(C_.STR_USER_ID,       user.getId())     ;
        MyStorage.i().putData("user_login",    user.getLogin())  ;
        MyStorage.i().putData("user_name",     user.getName())   ;
        MyStorage.i().putData("user_s_name",   user.getSName())  ;
        MyStorage.i().putData("user_phone",    user.getPhone())  ;
        MyStorage.i().putData("user_email",    user.getEmail())  ;
        MyStorage.i().putData("user_img",      user.getImg())    ;
        MyStorage.i().putData("user_img_icon", user.getImgIcon());
        MyStorage.i().putData("user_rating",   user.getRating()) ;
        MyStorage.i().putData("user_vote_qt",  user.getVoteQt()) ;
        MyStorage.i().putData("user_rights",   user.getVoteQt()) ;
        MyStorage.i().putData("user_ws_token", user.getWsToken());
        MyStorage.i().putData(C_.STR_BAN_LIST,      user.getBanList());
        MyStorage.i().putData(C_.STR_LIKE_LIST,     user.getLikeList());
    }
    public User getUser() {
        if (user != null)
            if ((user.getId() != 0)&&
                (user.getLogin().length()>0)&&
                (user.getWsToken().length()>3))
                return user;

        user = new User();
        user.setId(MyStorage.i()        .getInt     ("user_id"       ));
        user.setLogin(MyStorage.i()     .getString  ("user_login"    ));
        user.setName(MyStorage.i()      .getString  ("user_name"     ));
        user.setSName(MyStorage.i()     .getString  ("user_s_name"   ));
        user.setPhone(MyStorage.i()     .getString  ("user_phone"    ));
        user.setEmail(MyStorage.i()     .getString  ("user_email"    ));
        user.setImg(MyStorage.i()       .getString  ("user_img"      ));
        user.setImgIcon(MyStorage.i()   .getString  ("user_img_icon" ));
        user.setRating(MyStorage.i()    .getFloat   ("user_rating"   ));
        user.setRights(MyStorage.i()    .getInt     ("user_rights"   ));
        user.setWsToken(MyStorage.i()   .getString  ("user_ws_token" ));

        Log.i("MyUser", "user_id       -> " + user.getId());
        Log.i("MyUser", "user_login    -> " + user.getLogin());
        Log.i("MyUser", "user_name     -> " + user.getName());
        Log.i("MyUser", "user_s_name   -> " + user.getSName());
        Log.i("MyUser", "user_phone    -> " + user.getPhone());
        Log.i("MyUser", "user_email    -> " + user.getEmail());
        Log.i("MyUser", "user_img      -> " + user.getImg());
        Log.i("MyUser", "user_img_icon -> " + user.getImgIcon());
        Log.i("MyUser", "user_rating   -> " + user.getRating());
        Log.i("MyUser", "user_rights   -> " + user.getRights());
        Log.i("MyUser", "user_ws_token -> " + user.getWsToken());

        if ((user.getId() != 0)&&
            (user.getLogin().length()>0)&&
            (user.getWsToken().length()>3))
            return user;
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
        if (!auth){
            User mUser = getUser();
            if (mUser != null) auth = true;
        }
        return auth;
    }
    public void checkNewNotify(Context context){
        Post.sendRequest(context,C_.ACT_CHECK_NEW_NOTIFY, null,(data, result, stringData)->{
            MyView.setNotifySign((Activity)context,  result == 1);
        });
    }

    public void clearUser(){
        user = null;
        auth = false;
        MyStorage.i().clearField("user_id"       );
        MyStorage.i().clearField("user_login"    );
        MyStorage.i().clearField("user_name"     );
        MyStorage.i().clearField("user_s_name"   );
        MyStorage.i().clearField("user_phone"    );
        MyStorage.i().clearField("user_email"    );
        MyStorage.i().clearField("user_img"      );
        MyStorage.i().clearField("user_img_icon" );
        MyStorage.i().clearField("user_rating"   );
        MyStorage.i().clearField("user_vote_qt"  );
        MyStorage.i().clearField("user_rights"   );
        MyStorage.i().clearField("user_ws_token" );
    }
    public void loginUser(Context context, String login, String password, CB cb){
        String act = "login";
        PostSubData data = new PostSubData();
        data.setLogin(login);
        data.setPassword(password);
        Post.sendRequest(context, act, data, new NetCallback() {
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
        Post.sendRequest(context,C_.ACT_ANONYMOUS_LOGIN, userData, new NetCallback() {
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

        Post.sendRequest(context,"exit", null, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                setUser(null);
                if (cb != null)
                    cb.callback(0, null);
            }
        });
    }
    public void requestUsersStatus(Context context, boolean timerScr){
        String listId = "";
        listId = getTargetUsersList();
        if (listId.length()>0){
            listId = listId.substring(0, listId.length()-1);
            HashMap<String, Object>map = new HashMap<>();
            map.put("act", C_.ACT_GET_ONLINE_STATUS);
            map.put("list_id", listId);
            new BroadCastMsg(context, map, WsBroadcastReceiver.BROADCAST_FILTER);
        }
    }
    public void requestUserStatus(Context context, Integer userId){
        if (userId == null) return;
        String id = userId.toString();
        HashMap<String, Object>map = new HashMap<>();
        map.put("act", C_.ACT_GET_ONLINE_STATUS);
        map.put("list_id", id);
        new BroadCastMsg(context, map, WsBroadcastReceiver.BROADCAST_FILTER);
    }
    private String getTargetUsersList(){
        String listId = "";
        for (Integer userId : usersIdTable){
            listId = String.format("%s%s_", listId, userId.toString());
        }
        return listId;
    }

    public interface CB{
       void callback(int code, Object data);
    }
    public interface CbInitUser{
        void cb(boolean result);
    }
}
