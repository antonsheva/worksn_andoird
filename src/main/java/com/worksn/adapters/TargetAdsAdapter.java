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
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.worksn.classes.MyImg;
import com.yandex.mapkit.geometry.Point;
import com.worksn.R;
import com.worksn.classes.SubMenu;
import com.worksn.objects.Ads;
import com.worksn.objects.C_;
import com.worksn.objects.G_;
import com.worksn.objects.User;
import com.worksn.singleton.MyMap;
import com.worksn.singleton.UiClick;
import com.worksn.singleton.Usr;
import com.worksn.view.FrameUserProfile;


public class TargetAdsAdapter extends RecyclerView.Adapter<TargetAdsAdapter.AdsVwHldr> {
    private final int adsQt;
    private static int vhCnt = 0;
    private Context context;
    private final List<Ads> adsList;
    private final Cb cb;
    public  final int vhId;


    public TargetAdsAdapter(int adsQt, List<Ads> adsList, int vhId, Cb callback) {
        cb = callback;
        this.vhId = vhId;
        this.adsList = adsList;
        this.adsQt = adsQt;

    }

    @NonNull
    @Override
    public AdsVwHldr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        vhCnt++;
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(vhId,parent,false);
        return new AdsVwHldr(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AdsVwHldr holder, int position) {
        Ads ads = this.adsList.get(position);
        renderAdsField(ads, holder, position);
    }
    @Override
    public int getItemCount() {
        return adsQt;
    }

    @SuppressLint("DefaultLocale")
    private void renderAdsField(Ads ads, AdsVwHldr holder, int position) {
        int len;
        String outDescription;
        String outTime;
        if(ads.getDescription()!=null) {
            len = ads.getDescription().length();
            outDescription = (len>120) ? ads.getDescription().substring(0,116)+"..." : ads.getDescription();
        }else outDescription = context.getString(R.string.someUseful);
        if(ads.getCreateDate() != null) {
            outTime = ads.getCreateDate();
        } else outTime = "--:--";
        String outCategory   = context.getString(R.string.service);
        String outLogin      = (ads.getUserLogin() != null) ? ads.getUserLogin()       : context.getString(R.string.login);
        String outCost       = " ";

        if(ads.getCost() != null) {
            if (ads.getCost()>0){
                if (ads.getCost() > 999999){
                    float tmp = ads.getCost().floatValue()/1000000f;
                    outCost = (String.format("%.2f", tmp)).concat(context.getString(R.string.mlnR));
                }else {
                    outCost = ads.getCost().toString()+"р.";
                }
            }
        }
        if (ads.getCategory() != null){
            try{
                outCategory = G_.catList.get(ads.getCategory()).getName();
            }catch (Exception ignored){}
        }
        clearViewHolder(holder);

        switch (ads.getVisibleMode()){
            case C_.ADS_VISIBLE_NORMAL:

                break;
            case C_.ADS_VISIBLE_HIDDEN_FOR_TIME:
                holder.frmAdsCard.setBackgroundColor(context.getResources().getColor(R.color.ads_visible_hidden_for_time, null));
                outDescription = context.getString(R.string.msgHiddenForTime);
                break;
            case C_.ADS_VISIBLE_HIDDEN_MANUAL:
                holder.frmAdsCard.setBackgroundColor(context.getResources().getColor(R.color.ads_visible_hidden_manual, null));
                outDescription = context.getString(R.string.msgHidden);
                break;
            case C_.ADS_VISIBLE_HIDDEN_REMOVE:
                holder.frmAdsCard.setBackgroundColor(context.getResources().getColor(R.color.ads_visible_hidden_remove, null));
                outDescription = context.getString(R.string.msgWasRemove);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + ads.getVisibleMode());
        }

        holder.frmAdsCardCategory.setText(outCategory);
        holder.frmAdsCardCost.setText(outCost);
        holder.frmAdsCardTime.setText(outTime);
        holder.frmAdsCardDescription.setText(outDescription);

        if (ads.getImgIcon() != null){
            String[] imgList = ads.getImgIcon().split(",", 2);
            if (imgList[0].length()>1){
                holder.frmAdsCardImgFrame.setVisibility(View.VISIBLE);
                new MyImg((Activity)context).loadImg(holder.frmAdsCardImg, C_.URL_BASE+imgList[0],3, null);
            }
        }

        User user = new User();
        user.setLogin(outLogin);
        user.setImgIcon(ads.getUserImgIcon());
        user.setRating(ads.getUserRating());
        user.setId(ads.getUserId());
        user.setOnline(ads.isUserOnline());
        FrameUserProfile vUser = new FrameUserProfile(context, holder.frmAdsCardProfile, user, (user1, result) -> {
            UiClick.i().showUserPage(context, user1.getId());
        });
    }
    private void clearViewHolder(AdsVwHldr holder){
        holder.frmAdsCard.setBackgroundResource(0);
        holder.frmAdsCardCategory.setText(null);
        holder.frmAdsCardCost.setText(null);
        holder.frmAdsCardTime.setText(null);
        holder.frmAdsCardDescription.setText(null);
        holder.frmAdsCardImg.setImageResource(0);
        holder.frmAdsCardImgFrame.setVisibility(View.GONE);
    }
    class AdsVwHldr extends RecyclerView.ViewHolder {
        LinearLayout frmAdsCard;
        LinearLayout frmAdsCardImgFrame;
        ImageView    frmAdsCardImg;
        TextView     frmAdsCardCategory;
        TextView     frmAdsCardTime;
        TextView     frmAdsCardCost;
        TextView     frmAdsCardDescription;
        LinearLayout frmAdsCardProfile;
        MotionEvent  motionEvent;

        public AdsVwHldr(@NonNull View itemView) {
            super(itemView);
            initViewElements();
            itemView.setOnClickListener(v -> {
                if(G_.noClick){G_.noClick = false; return;}
                int pos = getAdapterPosition();
                Ads ads = adsList.get(pos);
                try{
                    cb.cb(C_.CODE_SHOW_TARGET_ADS, ads, null);
                }catch (Exception e){
                    Log.e("MyEx", e.fillInStackTrace().toString());
                }
            });
            itemView.setOnLongClickListener(v -> {
                G_.noClick = true;
                int pos = getAdapterPosition();
                Ads ads = adsList.get(pos);
                Point pt = new Point(ads.getCoordX(), ads.getCoordY());
                MyMap.i().cleanMapObjectCollections(C_.MAP_PM_PURPLE);
                MyMap.i().cleanMapObjectCollections(C_.MAP_PM_RED);
                MyMap.i().setCameraPosition(pt);

                MyMap.i().addPm(context,  C_.MAP_PM_PURPLE,pt , null);
                if(Usr.i().getUser() == null)return false;
                if(!ads.getUserId().equals(Usr.i().getUser().getId()))return false;

                int x = (int)motionEvent.getX();
                int y = (int)motionEvent.getY();
                ads.setViewPosition(pos);
                SubMenu.i().show((Activity)context, ads, C_.SUBMENU_OBJECT_TYPE_ADS, x, y);
                return false;
            });
            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN)motionEvent = event;
                    return false;
                }
            });
        }
        private void initViewElements() {
            frmAdsCardImgFrame    = itemView.findViewById(R.id.frmAdsCardImgFrame);
            frmAdsCardImg         = itemView.findViewById(R.id.frmAdsCardImg);
            frmAdsCard            = itemView.findViewById(R.id.frmAdsCard);
            frmAdsCardCategory    = itemView.findViewById(R.id.frmAdsCardCategory);
            frmAdsCardCost        = itemView.findViewById(R.id.frmAdsCardCost);
            frmAdsCardTime        = itemView.findViewById(R.id.frmAdsCardTime);
            frmAdsCardDescription = itemView.findViewById(R.id.frmAdsCardDescription);
            frmAdsCardProfile     = itemView.findViewById(R.id.frmAdsCardProfile);
        }
    }

    public interface Cb{
        void cb(int eCode, Object data1, Object data2);
    }
}
