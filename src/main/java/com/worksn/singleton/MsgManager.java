package com.worksn.singleton;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import com.worksn.R;
import com.worksn.adapters.MsgChainAdapter;
import com.worksn.classes.BroadCastMsg;
import com.worksn.classes.ConfirmDeliverMsg;
import com.worksn.classes.ConvertMsgData;
import com.worksn.classes.MyImg;
import com.worksn.classes.SubMenu;
import com.worksn.objects.C_;
import com.worksn.objects.MyContext;
import com.worksn.objects.MyScreen;
import com.worksn.objects.MyStorageConst;
import com.worksn.objects.PostSubData;
import com.worksn.objects.SaveImgData;
import com.worksn.objects.StructMsg;
import com.worksn.objects.TmpImg;
import com.worksn.objects.User;
import com.worksn.static_class.MyLog;
import com.worksn.static_class.Post;
import com.worksn.view.FrameReplyToMsg;
import com.worksn.view.PrintMsgProcess;
import com.worksn.view.MyView;
import com.worksn.view.Render;
import com.worksn.websocket.WsBroadcastReceiver;

public class MsgManager {
    private LinearLayoutManager mLayoutManagerMsgChain;
    private MsgChainAdapter mMsgChainAdapter;
    private RecyclerView mRcVwMsgChain;
    private MyContext mMyContext = new MyContext();
    private boolean startTm = false;
    private static MsgManager i;
    private static List<StructMsg> sMessages = new ArrayList<>();
    private AtomicBoolean msgIsShown;
    private EditText vSendMsgTxt;

    public static class ReplyData{
        public static Long replyMsgId = null;
        public static Integer replySenderId = null;
        public static String  replySenderLogin = null;
        public static String  replyContent = null;
        public static String  replyImg = null;

        public static void clear(){
            ReplyData.replyMsgId = null;
            ReplyData.replySenderId = null;
            ReplyData.replySenderLogin = null;
            ReplyData.replyContent = null;
            ReplyData.replyImg = null;
        }
    }


    public static MsgManager i(){
        if (i == null) {
            i = new MsgManager();
        }
        return i;
    }
    public MyContext getMsgContext() {
        return mMyContext;
    }
    public void scrollRcView(int position){
        mRcVwMsgChain.scrollToPosition(position);
    }
    public void setMsgContext(MyContext context) {
        this.mMyContext = context;
    }
    public void clearRcVw(Context context){
        List<StructMsg> tmp = new ArrayList<>();
        createMsgChainRecyclerView(context, tmp,0);
    }

    public void createMsgChainRecyclerView(Context context, @NotNull List<StructMsg> msgs, Integer pos){
        ((Activity)context).runOnUiThread(() -> {
            mRcVwMsgChain = (RecyclerView)((Activity)context).findViewById(R.id.rcVwMsgChain);
            sMessages = msgs;
            mMsgChainAdapter = null;
            mLayoutManagerMsgChain = new LinearLayoutManager(context);
            mLayoutManagerMsgChain.setReverseLayout(true);
            mMsgChainAdapter = new MsgChainAdapter(context, msgs, R.layout.frm_msg_chain_reply,
                    (eCode, data1, data2)-> {
                        if (eCode == C_.CODE_SHOW_REPLY_MSG){
                            scrollRcView((Integer)data1);
                        }else {
                            StructMsg msg = (StructMsg) data1;
                            if (msg.getImg() != null){
                                String img = C_.URL_BASE + msg.getImg();
                                new MyImg((Activity)context).setWebView(img);
                            }
                            else {
                                if (msg.getImgGallery() != null){
                                    new MyImg((Activity)context).setImageView(msg.getImgGallery());
                                }
                            }
                            SubMenu.i().hide((Activity)context);
                        }
                    });
            mRcVwMsgChain.setLayoutManager(mLayoutManagerMsgChain);
            mRcVwMsgChain.setHasFixedSize(true);
            mRcVwMsgChain.setAdapter(mMsgChainAdapter);
            if(pos!=null) {
                mRcVwMsgChain.scrollToPosition(pos);
                if (pos < 5)new Render((Activity)context).showBtScrollDown(false);
            }
        });
    }
    public void removeMsg(Context context, StructMsg rmvMsg){
        remove(context, rmvMsg.getId(), (msg, result) -> {
            StructMsg rmvMsg1 = (StructMsg) SubMenu.i().getTargetObject();
            sMessages.remove(rmvMsg1);
            int pos   = ((LinearLayoutManager) mLayoutManagerMsgChain).findFirstVisibleItemPosition();
            createMsgChainRecyclerView(context, sMessages, pos);
        });
    }
    public void wsSendPrintMsgProcess(Context context){
        long discusId;
        int consumerId;
        try{
            discusId = mMyContext.getDiscus().getId();
            consumerId = mMyContext.getSpeaker().getId();
        }catch (Exception e){
            return;
        }

        HashMap<String, Object>map = new HashMap<>();
        if (!startTm){
            startTm = true;
            map.put(C_.STR_ACT, C_.ACT_PRINT_MSG_PROCESS);
            map.put(C_.STR_DISCUS_ID, discusId);
            map.put(C_.STR_CONSUMER_ID, consumerId);
            new BroadCastMsg(context, map, WsBroadcastReceiver.BROADCAST_FILTER);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    startTm = false;
                }
            }, 5000);
        }
    }
    private String checkSendMsgContent(Context context){
        vSendMsgTxt = ((Activity)context).findViewById(R.id.sendMsgTxt);
        String msgTxt = vSendMsgTxt.getText().toString();
        if((msgTxt.equals(""))&&(!TmpImg.wasSendImg)){
            PUWindow.i().show(R.string.enterMsgTxt);
            return null;
        }
        new MyImg((Activity)context).setWebView(null);
        if(msgTxt.equals(""))msgTxt = " ";
        return msgTxt;
    }
    private PostSubData createSubData(String content){
        PostSubData subData = new PostSubData();
        try {
            subData.setSenderId(Usr.i().getUser().getId());
            subData.setConsumerId(MsgManager.i().getMsgContext().getSpeaker().getId());
            subData.setAds_id(MsgManager.i().getMsgContext().getDiscus().getAds_id());
            subData.setContent(content);

            subData.setReplyContent(ReplyData.replyContent);
            subData.setReplyMsgId(ReplyData.replyMsgId);
            subData.setReplySenderId(ReplyData.replySenderId);
            subData.setReplySenderLogin(ReplyData.replySenderLogin);
            subData.setReplyImg(ReplyData.replyImg);
            return subData;
        }catch (Exception e){
            new MyLog().sendException(e);
            return null;
        }
    }
    public void getMsgGroup(Context context, String act, ReturnMsgArray cb){
        Post.sendRequest(context,act, null, (data, result, stringData) -> {
            boolean dataError;
            List<StructMsg> msgs = new ArrayList<>();
            if(result > 0){
                setMsgContext(data);
                if (data.getMessages() == null) return;
                for (StructMsg msg : data.getMessages()){
                    dataError = false;
                    if (msg.getSpeakerId() == null)dataError = true;
                    if (msg.getSpeakerLogin() == null)dataError = true;
                    if (msg.getAds_id() == null)dataError = true;

                    if (!dataError){
                        User user = Usr.i().getUserDataFromObject(msg);
                        if (user != null){
                            if ((user.getImgIcon() == null) || (user.getImgIcon().length()<10)){
                                user.setImgIcon(C_.URL_NO_AVATAR);
                            }
                            Usr.i().addUserToTable(user);
                            msgs.add(msg);
                        }
                    }
                }
            }
            result = msgs.size();
            cb.callback(msgs, result, stringData);
        });
    }
    private void clearTmpImgData(){
        TmpImg.wasSendImg = false;
        TmpImg.img        = null;
        TmpImg.icon       = null;
        TmpImg.createId   = null;
        TmpImg.photoPath  = null;
    }
    private void showPrevImg(Context context, StructMsg msg, PostSubData subData){
        if (TmpImg.img == null)return;
        msg.setCreateId(TmpImg.createId);
        msg.setImgGallery(TmpImg.img.getAbsolutePath());
        msg.setImgIconGallery(TmpImg.icon.getAbsolutePath());
        subData.setCreateId(TmpImg.createId);
        sMessages.add(0,msg);
        createMsgChainRecyclerView(context, sMessages, 0);
        msgIsShown.set(true);
        TmpImg.wasSendPostImg = true;
    }
    public void sendMsg(Context context){
        String content = checkSendMsgContent(context);
        if (content == null)return;

        msgIsShown = new AtomicBoolean(false);
        new MyImg((Activity)context).setImageView(null);
        PostSubData subData = createSubData(content);
        StructMsg msg = createMsgForAddToRcVw(content);

        if ((subData == null)||(msg == null)){
            new PUWindow().show(R.string.errorSendMsg);
            return;
        }
        if( TmpImg.imgSend !=null){
            /*the image was delivered to the server*/
            subData.setImg(TmpImg.imgSend);
            TmpImg.imgSend = null;
            TmpImg.imgIconSend = null;

        }else {
            if (TmpImg.icon != null){
                /*the image was send but not delivered to server.
                * Need  to show previously image*/
                subData.setImg("was_send_post_data");
                subData.setImgIcon("was_send_post_data");
                showPrevImg(context, msg, subData);
            }
        }
        clearTmpImgData();

        Post.sendRequest(context,C_.ACT_ADD_MSG, subData, (data, result, stringData)->{
            StructMsg showMsg;
            if (result == 0)
                PUWindow.i().show(stringData);
            if (result == 1){
                if(!msgIsShown.get()){
                    Log.i("MyImg", "img1 -> "+ data.getTargetMsg().getImgIcon());
                    showMsg = addResponseMsgToRcVw(context,data.getTargetMsg());
                }else {
                    if (TmpImg.postImgWasLoad){
                        TmpImg.postImgWasLoad = false;
                    }else {

                        msg.setImg(C_.STR_WAS_SEND_POST_DATA);
                        msg.setImgIcon(C_.STR_WAS_SEND_POST_DATA);
                    }
                    if (data.getTargetMsg() != null){
                        msg.setId(data.getTargetMsg().getId());
                        msg.setImg(data.getTargetMsg().getImg());
                        msg.setImgIcon(data.getTargetMsg().getImgIcon());
                        showMsg = msg;
                    }else showMsg = null;
                }
                new FrameReplyToMsg((Activity)context).hide();
                ReplyData.clear();
                vSendMsgTxt.setText("");
                vSendMsgTxt.setHint(R.string.msgTxt);

                if (showMsg != null){
                    HashMap<String, Object>map = new ConvertMsgData().sendMsgToMap(showMsg);
                    new BroadCastMsg(context, map, WsBroadcastReceiver.BROADCAST_FILTER);
                }
            }
            msgIsShown.set(false);
            if (result == -1) PUWindow.i().show(stringData);
        });
    }
    private StructMsg addResponseMsgToRcVw(Context context, StructMsg msg){
        try{
            msg.setSender_login(Usr.i().getUser().getLogin());
            msg.setSenderImg(Usr.i().getUser().getImgIcon());
            msg.setView(0);
            sMessages.add(0,msg);
            createMsgChainRecyclerView(context, sMessages, 0);
            TmpImg.imgSend = null;
            TmpImg.imgIconSend = null;
            return msg;
        }catch (Exception e){
            new MyLog().sendException(e);
            return null;
        }
    }
    public void sendBindImgToMsg(Context context, SaveImgData saveImgData){
        TmpImg.createId     = saveImgData.getCreateId();
        TmpImg.imgSend     = null;
        TmpImg.imgIconSend = null;
        TmpImg.wasSendPostImg = false;
        sendBindImgDataToWs(context, saveImgData);
        for (StructMsg msg : sMessages){
            if (msg.getCreateId() != null){
                if (msg.getCreateId().equals(saveImgData.getCreateId())){
                    msg.setImg(saveImgData.getImg());
                    msg.setImgIcon(saveImgData.getImgIcon());
                    break;
                }
            }
        }
    }
    public void wsRcvNewMsg(Context context, StructMsg msg, boolean enRingtone){
        if (msg.getSysNotify() != null){
            int notifySign = Integer.parseInt(msg.getSysNotify().toString());
            MyView.setNotifySign( (Activity) context, notifySign == 1);
        }
        if (msg.getDiscus_id() == null)return;
        if(MyScreen.screenMode == C_.SCREEN_MODE_MSG_CHAIN) {
            if (mMyContext.getDiscus().getId().equals(msg.getDiscus_id())) {
                try{
                    if (msg.getImgIcon().equals(C_.STR_WAS_SEND_POST_DATA)){
                        msg.setImg(C_.FILE_GALLERY);
                        msg.setImgIcon(C_.FILE_GALLERY);
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
                sMessages.add(0, msg);
                createMsgChainRecyclerView(context, sMessages, 0);
                ConfirmDeliverMsg c =  new ConfirmDeliverMsg(context, msg.getSender_id(), msg.getDiscus_id(), C_.CODE_CONFIRM_VIEWED);
                return;
            }
        }
        new ConfirmDeliverMsg(context, msg.getSender_id(), msg.getDiscus_id(), C_.CODE_CONFIRM_DELIVER);
        if (enRingtone)playMsgRingtone(context);
        MyStorage.i().putData(MyStorageConst.NEW_MSG_SIGN, true);
        MyView.setBell((Activity) context);
    }
    public void renderMsgViewedStatus(Context context, long discusId, int statusMsg){
        MyImg myImg = new MyImg((Activity) context);
        try{
            if (discusId != mMyContext.getDiscus().getId())return;
        }catch (Exception e){
            return;
        }
        for (StructMsg msg : sMessages){
            if (msg.getSender_id().equals(Usr.i().getUser().getId())){
                try{
                    if (msg.getView() < 2)
                        msg.setView(statusMsg);
                }catch (NullPointerException ignored){}
            }
        }
        ((Activity)context).runOnUiThread(() -> {

            int fst, lst;
            View v = null;
            fst = mLayoutManagerMsgChain.findFirstVisibleItemPosition();
            lst = mLayoutManagerMsgChain.findLastVisibleItemPosition();

            try{
                for (int i = fst; i <= lst; i++){
                    StructMsg msg = sMessages.get(i);
                    if (msg.getSender_id().equals(Usr.i().getUser().getId())){
                        v = mLayoutManagerMsgChain.findViewByPosition(i);
                        ImageView status =  Objects.requireNonNull(v).findViewById(R.id.msgChainSenderStatus);
                        try{
                            switch (msg.getView()){
                                case 0: myImg.setImgSrc(status, R.drawable.birdie_1); break;
                                case C_.CODE_CONFIRM_DELIVER: myImg.setImgSrc(status, R.drawable.birdie_2); break;
                                case C_.CODE_CONFIRM_VIEWED: myImg.setImgSrc(status, R.drawable.birdie_3); break;
                            }
                        }catch (NullPointerException e){
                            Log.i("MyConfirm", "exception");
                            e.printStackTrace();
                            myImg.setImgSrc(status, R.drawable.birdie_1);
                        }
                    }
                }
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        });
    }
    public void showPrintMsgProcess(Context context, long discusId){
        try{
            if (discusId != mMyContext.getDiscus().getId())return;
        }catch (NullPointerException ignored){};
        new PrintMsgProcess(context);
    }
    public void wsRcvBindImageToMessage(Context context, SaveImgData data){
        if (MyScreen.screenMode == C_.SCREEN_MODE_MSG_CHAIN){
            for (StructMsg msg : sMessages){
                if (msg.getId().equals(data.getMsgId())){
                    msg.setImg(data.getImg());
                    msg.setImgIcon(data.getImgIcon());
                    updateImageOfMessage(context, msg.getId());
                }
            }
        }
    }
    private void sendBindImgDataToWs(Context context, SaveImgData saveImgData){
        HashMap<String, Object> map = new HashMap<>();
        map.put(C_.STR_ACT, C_.ACT_BIND_IMG_TO_MSG);
        map.put(C_.STR_CREATE_ID, saveImgData.getCreateId());
        map.put(C_.STR_IMG, saveImgData.getImg());
        map.put(C_.STR_IMG_ICON, saveImgData.getImgIcon());
        map.put(C_.STR_MSG_ID, saveImgData.getMsgId());
        map.put(C_.STR_CONSUMER_ID, saveImgData.getDestinationConsumerId());
        new BroadCastMsg(context, map, WsBroadcastReceiver.BROADCAST_FILTER);
    }
    @SuppressLint("SimpleDateFormat")
    private StructMsg createMsgForAddToRcVw(String content){
        StructMsg msg = new StructMsg();
        try{
            msg.setDiscus_id(MsgManager.i().getMsgContext().getDiscus().getId());
            msg.setSender_id(Usr.i().getUser().getId());
            msg.setConsumerId(mMyContext.getSpeaker().getId());
            msg.setAds_id(mMyContext.getTargetAds().getId());
            msg.setContent(content);
            msg.setCreateDate(new SimpleDateFormat().format(new Date()));
            msg.setView(0);
            return msg;
        }catch (Exception e){
            new MyLog().sendException(e);
            return null;
        }
    }
    private void updateImageOfMessage(Context context, Long msgId){
        int fst, lst;
        View v;
        fst = mLayoutManagerMsgChain.findFirstVisibleItemPosition();
        lst = mLayoutManagerMsgChain.findLastVisibleItemPosition();
        try{
            for (int i = fst; i <= lst; i++){
                StructMsg msg = sMessages.get(i);
                if (msgId==null || msg.getId()==null)continue;
                if (msg.getId().equals(msgId)){
                    v = mLayoutManagerMsgChain.findViewByPosition(i);
                    if (v != null){
                        ImageView img =  (ImageView) v.findViewById(R.id.msgChainReceiverImg);
                        ((Activity)context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                img.setImageResource(0);
                            }
                        });
                        new MyImg((Activity)context).loadImg(img, C_.URL_BASE+msg.getImgIcon(), 5, null);
                    }
                }
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
    private void remove(Context context, long id, ReturnMsg returnMsg){
        PostSubData subData = new PostSubData();
        subData.setId(id);
        Post.sendRequest(context, C_.ACT_REMOVE_MSG, subData, (data, result, stringData) -> {
            if(result == 0)
                PUWindow.i().show(stringData);
            if (result == 1)
                returnMsg.callback(null, 1);
            if (result == -1)
                PUWindow.i().show(stringData);
        });
    }
    private void playMsgRingtone(Context context){
        new Thread(() -> {
            MediaPlayer mp = MediaPlayer.create(context, R.raw.snd1);
            mp.start();
        }).start();
    }
    private interface ReturnMsg {
        public void callback(StructMsg msg, Integer result);
    }
    public interface ReturnMsgArray {
        void callback(List<StructMsg> msgs, Integer result, String stringData);
    }
}