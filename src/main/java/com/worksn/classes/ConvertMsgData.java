package com.worksn.classes;

import android.content.Intent;

import java.util.HashMap;

import com.worksn.objects.C_;
import com.worksn.objects.StructMsg;
import com.worksn.singleton.Usr;

public class ConvertMsgData {
    public StructMsg mapToMsg(HashMap<String, Object> map){
        StructMsg msg = new StructMsg();
        msg.setId           ((Long)    map.get(C_.STR_ID));
        msg.setDiscus_id    ((Long)    map.get(C_.STR_DISCUS_ID));
        msg.setAds_id       ((Long)    map.get(C_.STR_ADS_ID));
        msg.setSender_id    ((Integer) map.get(C_.STR_SENDER_ID));
        msg.setSender_login ((String)  map.get(C_.STR_SENDER_LOGIN));
        msg.setSenderImg    ((String)  map.get(C_.STR_SENDER_IMG));
        msg.setConsumerId((Integer) map.get(C_.STR_CONSUMER_ID));
        msg.setContent      ((String)  map.get(C_.STR_CONTENT));
        msg.setImg          ((String)  map.get(C_.STR_IMG));
        msg.setImgIcon      ((String)  map.get(C_.STR_IMG_ICON));
        msg.setCreateDate   ((String)  map.get(C_.STR_CREATE_DATE));
        return msg;
    }
    public HashMap<String, Object> sendMsgToMap(StructMsg msg){
        HashMap<String, Object>hashMap = new HashMap<>();
        hashMap.put(C_.STR_ACT, C_.ACT_NEW_MSG);
        hashMap.put(C_.STR_ID, msg.getId());
        hashMap.put(C_.STR_DISCUS_ID, msg.getDiscus_id());
        hashMap.put(C_.STR_ADS_ID, msg.getAds_id());
        hashMap.put(C_.STR_SENDER_ID, Usr.i().getUser().getId());
        hashMap.put(C_.STR_SENDER_LOGIN, Usr.i().getUser().getLogin());
        hashMap.put(C_.STR_SENDER_IMG, Usr.i().getUser().getImgIcon());
        hashMap.put(C_.STR_CONSUMER_ID, msg.getConsumerId());
        hashMap.put(C_.STR_CONTENT, msg.getContent());
        hashMap.put(C_.STR_IMG, msg.getImg());
        hashMap.put(C_.STR_IMG_ICON, msg.getImgIcon());
        hashMap.put(C_.STR_CREATE_DATE, msg.getCreateDate());


        if (msg.getReplyMsgId() != null){
            hashMap.put(C_.STR_REPLY_MSG_ID, msg.getReplyMsgId());
            hashMap.put(C_.STR_REPLY_SENDER_ID, msg.getReplySenderId());
            hashMap.put(C_.STR_REPLY_SENDER_LOGIN, msg.getReplySenderLogin());
            hashMap.put(C_.STR_REPLY_CONTENT, msg.getReplyContent());
            hashMap.put(C_.STR_REPLY_IMG, msg.getReplyImg());
        }
        return hashMap;
    }
    public HashMap<String, Object> rcvMsgToMap(StructMsg msg){
        HashMap<String, Object>hashMap = new HashMap<>();
        hashMap.put(C_.STR_ACT, C_.ACT_NEW_MSG);
        hashMap.put(C_.STR_ID, msg.getId());
        hashMap.put(C_.STR_DISCUS_ID, msg.getDiscus_id());
        hashMap.put(C_.STR_ADS_ID, msg.getAds_id());
        hashMap.put(C_.STR_SENDER_ID, msg.getSender_id());
        hashMap.put(C_.STR_SENDER_LOGIN, msg.getSender_login());
        hashMap.put(C_.STR_SENDER_IMG, msg.getSenderImg());
        hashMap.put(C_.STR_CONSUMER_ID, msg.getConsumerId());
        hashMap.put(C_.STR_CONTENT, msg.getContent());
        hashMap.put(C_.STR_IMG, msg.getImg());
        hashMap.put(C_.STR_IMG_ICON, msg.getImgIcon());
        hashMap.put(C_.STR_CREATE_DATE, msg.getCreateDate());

        if (msg.getReplyMsgId() != null){
            hashMap.put(C_.STR_REPLY_MSG_ID, msg.getReplyMsgId());
            hashMap.put(C_.STR_REPLY_SENDER_ID, msg.getReplySenderId());
            hashMap.put(C_.STR_REPLY_SENDER_LOGIN, msg.getReplySenderLogin());
            hashMap.put(C_.STR_REPLY_CONTENT, msg.getReplyContent());
            hashMap.put(C_.STR_REPLY_IMG, msg.getReplyImg());
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

        msg.setId               (intent.getLongExtra  (C_.STR_ID,0));
        msg.setDiscus_id        (intent.getLongExtra  (C_.STR_DISCUS_ID, 0));
        msg.setAds_id           (intent.getLongExtra  (C_.STR_ADS_ID,0));
        msg.setSender_id        (intent.getIntExtra   (C_.STR_SENDER_ID,0));
        msg.setSender_login     (intent.getStringExtra(C_.STR_SENDER_LOGIN));
        msg.setSenderImg        (intent.getStringExtra(C_.STR_SENDER_IMG));
        msg.setConsumerId       (intent.getIntExtra   (C_.STR_CONSUMER_ID,0));
        msg.setContent          (intent.getStringExtra(C_.STR_CONTENT));
        msg.setImg              (intent.getStringExtra(C_.STR_IMG));
        msg.setImgIcon          (intent.getStringExtra(C_.STR_IMG_ICON));
        msg.setCreateDate       (intent.getStringExtra(C_.STR_CREATE_DATE));

        replyMsgId       = intent.getLongExtra  (C_.STR_REPLY_MSG_ID,0);
        replySenderId    = intent.getIntExtra   (C_.STR_REPLY_SENDER_ID,0);
        replySenderLogin = intent.getStringExtra(C_.STR_REPLY_SENDER_LOGIN);
        replyContent     = intent.getStringExtra(C_.STR_REPLY_CONTENT);
        replyImg         = intent.getStringExtra(C_.STR_REPLY_IMG);

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
