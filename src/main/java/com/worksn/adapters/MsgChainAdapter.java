package com.worksn.adapters;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.worksn.R;
import com.worksn.classes.MyImg;
import com.worksn.classes.SubMenu;
import com.worksn.objects.C_;
import com.worksn.objects.G_;
import com.worksn.objects.StructMsg;
import com.worksn.objects.TmpImg;
import com.worksn.singleton.MsgManager;
import com.worksn.singleton.Usr;
import com.worksn.view.Render;

public class MsgChainAdapter extends RecyclerView.Adapter<MsgChainAdapter.MsgVwHldr>{
    private Context context;
    private Activity activity;
    private final List<StructMsg> msgList;

    public  final int vhId;
    private final Cb cb;
    private static int lastPosition = 0;
    private ImageView rcVwMsgChainBtDown;
    MyImg myImg;
    Render vw;
    public MsgChainAdapter(Context context, List<StructMsg> msgList, int vhId, Cb callback){
        this.context  = context;
        this.activity = (Activity)context;
        lastPosition = 0;
        cb = callback;
        this.vhId    = vhId;
        this.msgList = msgList;
        myImg = new MyImg((Activity) context);
        rcVwMsgChainBtDown  = activity.findViewById(R.id.rcVwMsgChainBtDown);
        vw = new Render((Activity)context);
        initOnClickListener();
    }
    @NonNull
    @Override
    public MsgVwHldr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(vhId,parent,false);
        return new MsgVwHldr(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MsgVwHldr holder, int position) {
        int pos = holder.getAdapterPosition();
        StructMsg msg = msgList.get(pos);
        renderMsgField(msg, holder);

        if ((lastPosition < 12)&&(pos >= 12)){
            vw.viewElement(rcVwMsgChainBtDown, true);
        }
        if ((lastPosition >= 12)&&(pos < 12)){
            vw.viewElement(rcVwMsgChainBtDown, false);
        }
        lastPosition = pos;
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }
    private void initOnClickListener(){
        rcVwMsgChainBtDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MsgManager.i().scrollRcView(0);
                vw.viewElement(rcVwMsgChainBtDown, false);
            }
        });
    }
    private void renderMsgField(StructMsg msg, MsgVwHldr holder){
        if(msg.getCreateDate() == null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD hh:mm:ss");
            msg.setCreateDate(dateFormat.toString());
        }
        clearMsgFrame(holder);
        clearReplyMsgFrame(holder);

        if(Usr.i().getUser().getId().equals(msg.getSender_id())){
            renderSenderMsg(msg, holder);
            renderSenderReplyMsg(msg, holder);
        }else{
            renderReceiverMsg(msg, holder);
            renderReceiverReplyMsg(msg, holder);
        }
    }
    private void clearMsgFrame(MsgVwHldr holder){
        holder.msg_chain_sndr_txt.setText(null);
        holder.msg_chain_sndr_tm .setText(null);
        holder.msg_chain_sndr_layout.setBackgroundResource(R.color.msg_color_bcgrnd);
        holder.msg_chain_sndr_img.setImageResource(0);
        holder.msg_chain_sndr_img.setVisibility(View.GONE);
        holder.msg_chain_sndr_status.setImageResource(R.drawable.empty_100_100);


        holder.msg_chain_rcvr_txt.setText(null);
        holder.msg_chain_rcvr_tm .setText(null);
        holder.msg_chain_rcvr_layout.setBackgroundResource(R.color.msg_color_bcgrnd);
        holder.msg_chain_rcvr_img.setImageResource(0);
        holder.msg_chain_rcvr_img.setVisibility(View.GONE);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        holder.msg_chain_rcvr_layout.setLayoutParams(params);
        holder.msg_chain_sndr_layout.setLayoutParams(params);
        holder.msg_chain_sndr_layout.setVisibility(View.GONE);
        holder.msg_chain_rcvr_layout.setVisibility(View.GONE);
    }
    private void clearReplyMsgFrame(MsgVwHldr holder){
        holder.msgChainSenderReplyContent.setText(null);
        holder.msgChainSenderReplySpeaker.setText(null);
        holder.msgChainSenderReplyImg.setImageResource(0);
        holder.msgChainSenderReplyImg.setVisibility(View.GONE);

        holder.msgChainReceiverReplyContent.setText(null);
        holder.msgChainReceiverReplySpeaker.setText(null);
        holder.msgChainReceiverReplyImg.setImageResource(0);
        holder.msgChainReceiverReplyImg.setVisibility(View.GONE);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        holder.msgChainSenderReplyLayout.setLayoutParams(params);
        holder.msgChainReceiverReplyLayout.setLayoutParams(params);

        holder.msgChainReceiverReplyLayout.setVisibility(View.GONE);
        holder.msgChainSenderReplyLayout.setVisibility(View.GONE);
    }    
    private void renderSenderMsg(StructMsg msg, MsgVwHldr holder){
        String timeTxt = msg.getCreateDate();
        String img = msg.getImgIcon();
        String txt = msg.getContent();
        String imgIconGallery = msg.getImgIconGallery();

        if (timeTxt == null) timeTxt = "--:--";
        if (txt == null) txt = "...";

        holder.msg_chain_sndr_layout.setVisibility(View.VISIBLE);
        if(img !=null){
            if(img.length()>3){
                holder.msg_chain_sndr_img.setVisibility(View.VISIBLE);
                myImg.loadImg(holder.msg_chain_sndr_img, C_.URL_BASE +img, 20, null);
            }
        }else {
            if (imgIconGallery != null){
                holder.msg_chain_sndr_img.setVisibility(View.VISIBLE);
                holder.msg_chain_sndr_img.setImageDrawable(TmpImg.bitMapIconSend);
                TmpImg.bitMapIconSend = null;
            }
        }
        try{
            switch (msg.getView()){
                case 0: myImg.setImgSrc(holder.msg_chain_sndr_status, R.drawable.birdie_1); break;
                case 1: myImg.setImgSrc(holder.msg_chain_sndr_status, R.drawable.birdie_2); break;
                case 2: myImg.setImgSrc(holder.msg_chain_sndr_status, R.drawable.birdie_3); break;
            }
        }catch (NullPointerException e){
            myImg.setImgSrc(holder.msg_chain_sndr_status, R.drawable.birdie_1);
        }

        holder.msg_chain_sndr_txt.setText(txt);
        holder.msg_chain_sndr_tm .setText(timeTxt);
        holder.msg_chain_sndr_layout.setBackgroundResource(R.drawable.msg_sender);
    }
    private void renderReceiverMsg(StructMsg msg, MsgVwHldr holder){
        String timeTxt = msg.getCreateDate();
        String img = msg.getImgIcon();
        String txt = msg.getContent();

        if (timeTxt == null) timeTxt = "--:--";
        if (txt == null) txt = "...";

        holder.msg_chain_rcvr_layout.setVisibility(View.VISIBLE);
        if(img != null)
            if (img.length()>3){
                holder.msg_chain_rcvr_img.setVisibility(View.VISIBLE);
                myImg.loadImg(holder.msg_chain_rcvr_img, C_.URL_BASE +img, 20, null);
            }
        holder.msg_chain_rcvr_txt.setText(txt);
        holder.msg_chain_rcvr_tm .setText(timeTxt);
        holder.msg_chain_rcvr_layout.setBackgroundResource(R.drawable.msg_consumer);
    }
    private void renderSenderReplyMsg(StructMsg msg, MsgVwHldr holder){
        if ((msg.getReplyMsgId() == null) || msg.getReplyMsgId().equals(0L) )return;
        holder.replyMsgId = msg.getReplyMsgId();
        holder.msgChainSenderReplyLayout.setVisibility(View.VISIBLE);
        holder.msgChainSenderReplyContent.setText(msg.getReplyContent());
        holder.msgChainSenderReplySpeaker.setText(msg.getReplySenderLogin());
        if (msg.getReplyImg() != null){
            if (msg.getReplyImg().length()>3){
                holder.msgChainSenderReplyImg.setVisibility(View.VISIBLE);
                myImg.loadImg(holder.msgChainSenderReplyImg, C_.URL_BASE+msg.getReplyImg(), 20, null);
            }
        }
    }
    private void renderReceiverReplyMsg(StructMsg msg, MsgVwHldr holder){
        if ((msg.getReplyMsgId() == null) || msg.getReplyMsgId().equals(0L) ) return;
        holder.replyMsgId = msg.getReplyMsgId();
        holder.msgChainReceiverReplyLayout.setVisibility(View.VISIBLE);
        holder.msgChainReceiverReplyContent.setText(msg.getReplyContent());
        holder.msgChainReceiverReplySpeaker.setText(msg.getReplySenderLogin());
        if (msg.getReplyImg() != null){
            if (msg.getReplyImg().length()>3){
                holder.msgChainReceiverReplyImg.setVisibility(View.VISIBLE);
                myImg.loadImg(holder.msgChainReceiverReplyImg, C_.URL_BASE+msg.getReplyImg(), 20, null);
            }
        }
    }

    class MsgVwHldr extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {
        TextView  msg_chain_sndr_txt;
        TextView  msg_chain_sndr_tm ;
        ImageView msg_chain_sndr_img;
        ImageView msg_chain_sndr_status;
        TextView  msg_chain_rcvr_txt;
        TextView  msg_chain_rcvr_tm ;
        ImageView msg_chain_rcvr_img;
        LinearLayout msg_chain_rcvr_layout;
        LinearLayout msg_chain_sndr_layout;

        LinearLayout msgChainReceiverReplyLayout;
        ImageView    msgChainReceiverReplyImg;
        TextView     msgChainReceiverReplySpeaker;
        TextView     msgChainReceiverReplyContent;

        LinearLayout msgChainSenderReplyLayout;
        ImageView    msgChainSenderReplyImg;
        TextView     msgChainSenderReplySpeaker;
        TextView     msgChainSenderReplyContent;        

        MotionEvent motionEvent;

        public Long replyMsgId = 0L;

        public MsgVwHldr(@NonNull View itemView) {
            super(itemView);
            initMsgViewElements();
            initReplyViewElements();
            itemView.setOnClickListener(this);
        }
        @SuppressLint("ClickableViewAccessibility")
        private void initMsgViewElements(){
            msg_chain_sndr_txt      = (TextView) itemView.findViewById(R.id.msgChainSenderContent);
            msg_chain_sndr_tm       = (TextView) itemView.findViewById(R.id.msgChainSenderTm);
            msg_chain_sndr_img      = (ImageView)itemView.findViewById(R.id.msgChainSenderImg);
            msg_chain_sndr_status   = (ImageView)itemView.findViewById(R.id.msgChainSenderStatus);
            msg_chain_rcvr_txt      = (TextView) itemView.findViewById(R.id.msgChainReceiverContent);
            msg_chain_rcvr_tm       = (TextView) itemView.findViewById(R.id.msgChainReceiverTm);
            msg_chain_rcvr_img      = (ImageView)itemView.findViewById(R.id.msgChainReceiverImg);
            msg_chain_rcvr_layout   = (LinearLayout) itemView.findViewById(R.id.msgChainReceiverLayout);
            msg_chain_sndr_layout   = (LinearLayout) itemView.findViewById(R.id.msgChainSenderLayout);

            msg_chain_sndr_img.setOnClickListener(this);
            msg_chain_rcvr_img.setOnClickListener(this);
            msg_chain_sndr_layout.setOnClickListener(this);
            msg_chain_rcvr_layout.setOnClickListener(this);

            msg_chain_sndr_img.setOnLongClickListener(this);
            msg_chain_rcvr_img.setOnLongClickListener(this);
            msg_chain_sndr_layout.setOnLongClickListener(this);
            msg_chain_rcvr_layout.setOnLongClickListener(this);

            msg_chain_sndr_img.setOnTouchListener(this);
            msg_chain_rcvr_img.setOnTouchListener(this);
            msg_chain_sndr_layout.setOnTouchListener(this);
            msg_chain_rcvr_layout.setOnTouchListener(this);
        }
        private void initReplyViewElements(){
            msgChainReceiverReplyLayout  = (LinearLayout)itemView.findViewById(R.id.msgChainReceiverReplyLayout);
            msgChainReceiverReplyImg     = (ImageView) itemView.findViewById(R.id.msgChainReceiverReplyImg);
            msgChainReceiverReplySpeaker = (TextView) itemView.findViewById(R.id.msgChainReceiverReplySpeaker);
            msgChainReceiverReplyContent = (TextView) itemView.findViewById(R.id.msgChainReceiverReplyContent);

            msgChainSenderReplyLayout  = (LinearLayout)itemView.findViewById(R.id.msgChainSenderReplyLayout);
            msgChainSenderReplyImg     = (ImageView) itemView.findViewById(R.id.msgChainSenderReplyImg);
            msgChainSenderReplySpeaker = (TextView) itemView.findViewById(R.id.msgChainSenderReplySpeaker);
            msgChainSenderReplyContent = (TextView) itemView.findViewById(R.id.msgChainSenderReplyContent);

            msgChainSenderReplyLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = -1;
                    if (replyMsgId != 0){
                        for (StructMsg msg : msgList){
                            if (msg.getId().equals(replyMsgId)){
                                position = msgList.indexOf(msg);
                                break;
                            }
                        }
                    }
                    if (position != -1){
                        cb.cb(C_.CODE_SHOW_REPLY_MSG, position, null);
                    }else {
                        Log.i("MyReply", "not load message");
                    }
                }
            });
            msgChainReceiverReplyLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = -1;
                    if (replyMsgId != 0){
                        for (StructMsg msg : msgList){
                            if (msg.getId().equals(replyMsgId)){
                                position = msgList.indexOf(msg);
                                break;
                            }
                        }
                    }
                    if (position != -1){
                        cb.cb(C_.CODE_SHOW_REPLY_MSG, position, null);
                    }else {
                        Log.i("MyReply", "not load message");
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            if(G_.noClick){G_.noClick = false; return;}
            int id = v.getId();
            int pos = getAdapterPosition();
            StructMsg msg = msgList.get(pos);
            if((id==R.id.msgChainReceiverImg)||(id==R.id.msgChainSenderImg)){
                cb.cb(C_.CODE_SHOW_IMG, msg, null);
            }

        }

        @Override
        public boolean onLongClick(View v) {
            G_.noClick = true;
            int pos = getAdapterPosition();
            StructMsg msg = msgList.get(pos);
            int x = (int)motionEvent.getX();
            int y = (int)motionEvent.getY();
            msg.setViewPosition(pos);

            SubMenu.i().show((Activity)context,msg, C_.SUBMENU_OBJECT_TYPE_MSG,x,y);
            return false;
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN)motionEvent = event;
            return false;
        }
    }

    public interface Cb{
        void cb(int eCode, Object data1, Object data2);
    }
}

