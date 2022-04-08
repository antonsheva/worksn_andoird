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
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.worksn.R;
import com.worksn.classes.SubMenu;
import com.worksn.objects.C_;
import com.worksn.objects.G_;
import com.worksn.objects.StructMsg;
import com.worksn.singleton.UiClick;
import com.worksn.static_class.Funcs;


public class MsgGroupAdapter extends RecyclerView.Adapter<MsgGroupAdapter.MsgVwHolder>{
    private final int msgQt;
    private static int vhCnt = 0;
    private Context context;
    private final List<StructMsg> msgList;
    public  final int vhId;
    private final Cb cb;
    public MsgGroupAdapter(int msgQt, List<StructMsg> msgList, int vhId, Cb callback){
        cb = callback;
        this.vhId    = vhId;
        this.msgQt   = msgQt;
        this.msgList = msgList;
    }
    @NonNull
    @Override
    public MsgVwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("MyVh", "vhCnt -> "+((Integer)vhCnt));
        vhCnt++;
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(vhId,parent,false);
        return new MsgVwHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MsgVwHolder holder, int position) {
        StructMsg msg = msgList.get(position);
        renderMsgGroupField(msg, holder);
    }
    @Override
    public int getItemCount() {
        return msgQt;
    }
    @SuppressLint("DefaultLocale")
    private void renderMsgGroupField(StructMsg msg, MsgVwHolder holder){
        String outTime = msg.getCreateDate();
        String outDescription;
        String outContent;
        String outCategory;
        String outCost;
        String outLogin =  msg.getSpeaker_login();
        int len;

        if (outLogin == null)outLogin = "Кто-то";
        if(outTime == null) outTime = "--:--";


        if (msg.getContent() != null){
            len = msg.getContent().length();
            outContent = (len>30) ? msg.getContent().substring(0,26)+"..." : msg.getContent();
        }else {
            outContent = "...";
        }
        if(msg.getAds_description()!=null) {
            len = msg.getAds_description().length();
            outDescription = (len>50) ?  msg.getAds_description().substring(0,46)+"..." : msg.getAds_description();
        }else outDescription = "Что-то очень полезное";

        outCategory = "Услуга";
        if (msg.getSpeaker_id() != null){
            if (msg.getSpeaker_id() == 1)outCategory = "Переписка с администратором";
            else if (msg.getAdsCategory() == C_.ADS_CATEGORY_FOR_DIRECT_DISCUS){
                outCategory = "Переписка с "+outLogin;
            }else {
                try{
                    outCategory =  G_.catList.get(msg.getAdsCategory()).getName();
                }catch (Exception e){
                    Log.e("MyEx", e.fillInStackTrace().toString());
                }
            }
        }
        if(msg.getCost() != null) {
            if (msg.getCost()>0){
                if (msg.getCost() > 999999){
                    float tmp = msg.getCost().floatValue()/1000000;
                    outCost = (String.format("%.2f", tmp)).concat("млн.р.");
                }else {
                    outCost = msg.getCost().toString()+"р.";
                }
                outDescription = outDescription + " ("+outCost+")";
            }
        }
        holder.msgGroupContent.setText(null);
        holder.msgGroupTime.setText(null);
        holder.msgGroupDescription.setText(null);
        holder.msgLogin.setText(null);
        holder.msgGroupCategory.setText(null);
        holder.msgImg.setImageResource(0);
        holder.msgAvatar.setImageResource(R.drawable.no_avatar_r50);
        holder.msgGroupCategory.setText(outCategory);
        holder.msgGroupContent.setText(outContent);
        holder.msgGroupTime.setText(outTime);
        holder.msgGroupDescription.setText(outDescription);
        holder.msgLogin.setText(outLogin);

        if (msg.getSpeakerOnline())holder.uProfileOnline.setVisibility(View.VISIBLE);
        else                       holder.uProfileOnline.setVisibility(View.GONE);

        if (msg.getImgIcon() != null)
            Funcs.loadImg(context, holder.msgImg, C_.URL_BASE+msg.getImgIcon(), 5, null);

        Funcs.loadImgNecessarily(context, holder.msgAvatar, C_.URL_BASE+msg.getSpeaker_img(), 20);
        holder.msgAvatar.setOnClickListener(v -> UiClick.i().showUserPage(context, msg.getSpeaker_id()));
    }

    class MsgVwHolder extends RecyclerView.ViewHolder {

        TextView msgGroupContent;
        TextView msgGroupTime;
        TextView msgGroupDescription;
        TextView msgLogin;
        TextView msgGroupCategory;
        ImageView msgAvatar;
        ImageView msgImg;
        ImageView uProfileOnline;

        MotionEvent motionEvent;
        StructMsg msg;
        @SuppressLint("ClickableViewAccessibility")
        public MsgVwHolder(@NonNull View itemView) {
            super(itemView);
            initViewElements();

            itemView.setOnClickListener(v -> {
                if(G_.noClick){G_.noClick = false; return;}
                int pos = getAdapterPosition();
                StructMsg msg = msgList.get(pos);
                Log.i("MyAdapter", "pos -> "+pos);
                cb.cb(C_.CODE_SHOW_DISCUS, msg, null);
            });

            itemView.setOnLongClickListener(v -> {
                G_.noClick = true;
                int pos = getAdapterPosition();
                msg = msgList.get(pos);
                int x = (int)motionEvent.getX();
                int y = (int)motionEvent.getY();
                msg.setViewPosition(pos);
                SubMenu.i().show((Activity)context, msg, C_.SUBMENU_OBJECT_TYPE_MSG_GROUP, x, y);
                return false;
            });
            itemView.setOnTouchListener((v, event) -> {
                if(event.getAction() == MotionEvent.ACTION_DOWN)motionEvent = event;
                return false;
            });

        }
        private void initViewElements(){
            msgGroupContent     = itemView.findViewById(R.id.msgContent);
            msgGroupTime        = itemView.findViewById(R.id.msgGroupTime);
            msgGroupCategory    = itemView.findViewById(R.id.msgGroupCategory);
            msgGroupDescription = itemView.findViewById(R.id.msgDescription);
            msgLogin            = itemView.findViewById(R.id.msgLogin);
            msgAvatar           = itemView.findViewById(R.id.msgAvatar);
            msgImg              = itemView.findViewById(R.id.msgImg);
            uProfileOnline      = itemView.findViewById(R.id.uProfileOnline);
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    public void remove(int pos, int size){
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos, size);
        notifyDataSetChanged();
    }
    public interface Cb{
        void cb(int eCode, Object data1, Object data2);
    }
 }

