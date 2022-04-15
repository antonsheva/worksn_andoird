package com.worksn.view;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.worksn.R;
import com.worksn.classes.MyImg;
import com.worksn.objects.C_;
import com.worksn.objects.StructMsg;
import com.worksn.singleton.MsgManager;
import com.worksn.singleton.Usr;

public class FrameReplyToMsg {
    LinearLayout frmReply   ;
    TextView frmReplySpeaker;
    TextView frmReplyContent;
    ImageView frmReplyClose ;
    ImageView frmReplyImg   ;

    Activity activity;

    public FrameReplyToMsg(Activity activity){
        this.activity = activity;
        frmReply    = (LinearLayout) activity.findViewById(R.id.frmReply);
        frmReplySpeaker = (TextView) activity.findViewById(R.id.frmReplySpeaker);
        frmReplyContent = (TextView) activity.findViewById(R.id.frmReplyContent);
        frmReplyClose  = (ImageView)    activity.findViewById(R.id.frmReplyClose);
        frmReplyImg    = (ImageView) activity.findViewById(R.id.frmReplyImg);
    }
    public void show(StructMsg msg){
        String speaker = activity.getString(R.string.you);

        try{
            if (!msg.getSender_id().equals(Usr.i().getUser().getId())){
                speaker = MsgManager.i().getMsgContext().getSpeaker().getLogin();
            }
        }catch (Exception e){
            speaker = activity.getString(R.string.speaker);
        }

        frmReplySpeaker.setText(speaker);
        frmReplyContent.setText(msg.getContent());

        try{
            if (msg.getImgIcon().length()>5){
                frmReplyImg.setVisibility(View.VISIBLE);
                new MyImg(activity).loadImg(frmReplyImg, C_.URL_BASE+msg.getImgIcon(), 10, null);
            }else {
                frmReplyImg.setImageResource(0);
                frmReplyImg.setVisibility(View.GONE);
            }
        }catch (NullPointerException e){
            frmReplyImg.setImageResource(0);
            frmReplyImg.setVisibility(View.GONE);
        }

        frmReply.setVisibility(View.VISIBLE);

        MsgManager.ReplyData.replyMsgId = msg.getId();
        MsgManager.ReplyData.replySenderId = msg.getSender_id();
        MsgManager.ReplyData.replySenderLogin = speaker;
        MsgManager.ReplyData.replyContent = msg.getContent();
        MsgManager.ReplyData.replyImg = msg.getImgIcon();

        frmReplyClose.setOnClickListener(v -> {
            hide();
            MsgManager.ReplyData.clear();
        });
    }
    public void hide(){
        frmReplySpeaker.setText("");
        frmReplyImg.setImageResource(0);
        frmReplyContent.setText("");
        frmReplyImg.setVisibility(View.GONE);
        frmReply.setVisibility(View.GONE);
    }
}
