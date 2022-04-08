package com.worksn.classes;

import android.content.Intent;

import java.util.HashMap;

import com.worksn.objects.C_;
import com.worksn.objects.StructMsg;
import com.worksn.singleton.Usr;
import com.worksn.websocket.WsBroadcastReceiver;

public class ConvertMsgData {
    public StructMsg mapToMsg(HashMap<String, Object> map){
        StructMsg msg = new StructMsg();
        msg.setId           ((Long) map.get("id"));
        msg.setDiscus_id    ((Long) map.get("discus_id"    ));
        msg.setAds_id       ((Long) map.get("ads_id"       ));
        msg.setSender_id    ((Integer) map.get("sender_id"    ));
        msg.setSender_login ((String) map.get("sender_login"  ));
        msg.setSenderAvatar ((String) map.get("sender_avatar" ));
        msg.setConsumer_id  ((Integer) map.get("consumer_id"  ));
        msg.setContent      ((String) map.get("content"       ));
        msg.setImg          ((String) map.get("img"           ));
        msg.setImgIcon      ((String) map.get("img_icon"      ));
        msg.setCreateDate   ((String) map.get("create_date"   ));
        return msg;
    }
    public HashMap<String, Object> sendMsgToMap(StructMsg msg){
        HashMap<String, Object>hashMap = new HashMap<>();
        hashMap.put("act"           , C_.ACT_NEW_MSG);
        hashMap.put("id"            , msg.getId());
        hashMap.put("discus_id"     , msg.getDiscus_id());
        hashMap.put("ads_id"        , msg.getAds_id());
        hashMap.put("sender_id"     , Usr.i().getUser().getId());
        hashMap.put("sender_login"  , Usr.i().getUser().getLogin());
        hashMap.put("sender_avatar" , Usr.i().getUser().getImgIcon());
        hashMap.put("consumer_id"   , msg.getConsumer_id());
        hashMap.put("content"       , msg.getContent());
        hashMap.put("img"           , msg.getImg());
        hashMap.put("img_icon"      , msg.getImgIcon());
        hashMap.put("create_date"   , msg.getCreateDate());


        if (msg.getReplyMsgId() != null){
            hashMap.put(C_.VAR_REPLY_MSG_ID      , msg.getReplyMsgId());
            hashMap.put(C_.VAR_REPLY_SENDER_ID   , msg.getReplySenderId());
            hashMap.put(C_.VAR_REPLY_SENDER_LOGIN, msg.getReplySenderLogin());
            hashMap.put(C_.VAR_REPLY_CONTENT     , msg.getReplyContent());
            hashMap.put(C_.VAR_REPLY_IMG         , msg.getReplyImg());
        }
        return hashMap;
    }
    public HashMap<String, Object> rcvMsgToMap(StructMsg msg){
        HashMap<String, Object>hashMap = new HashMap<>();
        hashMap.put("act"           , C_.ACT_NEW_MSG);
        hashMap.put("id"            , msg.getId());
        hashMap.put("discus_id"     , msg.getDiscus_id());
        hashMap.put("ads_id"        , msg.getAds_id());
        hashMap.put("sender_id"     , msg.getSender_id());
        hashMap.put("sender_login"  , msg.getSender_login());
        hashMap.put("sender_avatar" , msg.getSenderAvatar());
        hashMap.put("consumer_id"   , msg.getConsumer_id());
        hashMap.put("content"       , msg.getContent());
        hashMap.put("img"           , msg.getImg());
        hashMap.put("img_icon"      , msg.getImgIcon());
        hashMap.put("create_date"   , msg.getCreateDate());

        if (msg.getReplyMsgId() != null){
            hashMap.put(C_.VAR_REPLY_MSG_ID      , msg.getReplyMsgId());
            hashMap.put(C_.VAR_REPLY_SENDER_ID   , msg.getReplySenderId());
            hashMap.put(C_.VAR_REPLY_SENDER_LOGIN, msg.getReplySenderLogin());
            hashMap.put(C_.VAR_REPLY_CONTENT     , msg.getReplyContent());
            hashMap.put(C_.VAR_REPLY_IMG         , msg.getReplyImg());
        }

        return hashMap;
    }
    public StructMsg intentExtrasToMsg(Intent intent){
        StructMsg msg = new StructMsg();
        long   replyMsgId      ;
        int    replySenderId   ;
        String replySenderLogin;
        String replyContent    ;
        String replyImg        ;

        msg.setId               (intent.getLongExtra  ("id",0));
        msg.setDiscus_id        (intent.getLongExtra  (C_.VAR_DISCUS_ID, 0));
        msg.setAds_id           (intent.getLongExtra  (C_.VAR_ADS_ID,0));
        msg.setSender_id        (intent.getIntExtra   (C_.VAR_SENDER_ID,0));
        msg.setSender_login     (intent.getStringExtra(C_.VAR_SENDER_LOGIN));
        msg.setSenderAvatar     (intent.getStringExtra(C_.VAR_SENDER_AVATAR));
        msg.setConsumer_id      (intent.getIntExtra   (C_.VAR_CONSUMER_ID,0));
        msg.setContent          (intent.getStringExtra(C_.VAR_CONTENT));
        msg.setImg              (intent.getStringExtra(C_.VAR_IMG));
        msg.setImgIcon          (intent.getStringExtra(C_.VAR_IMG_ICON));
        msg.setCreateDate       (intent.getStringExtra(C_.VAR_CREATE_DATE));

        replyMsgId       = intent.getLongExtra  (C_.VAR_REPLY_MSG_ID      ,0);
        replySenderId    = intent.getIntExtra   (C_.VAR_REPLY_SENDER_ID   ,0);
        replySenderLogin = intent.getStringExtra(C_.VAR_REPLY_SENDER_LOGIN            );
        replyContent     = intent.getStringExtra(C_.VAR_REPLY_CONTENT                 );
        replyImg         = intent.getStringExtra(C_.VAR_REPLY_IMG                     );

        if (replyMsgId != 0){
            msg.setReplyMsgId       (replyMsgId      );
            msg.setReplySenderId    (replySenderId   );
            msg.setReplySenderLogin (replySenderLogin);
            msg.setReplyContent     (replyContent    );
            msg.setReplyImg         (replyImg        );
        }

        return msg;
    }
}
