package com.worksn.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import com.worksn.R;
import com.worksn.interfaces.AdapterListener;
import com.worksn.objects.C_;
import com.worksn.objects.UserReview;
import com.worksn.singleton.Usr;
import com.worksn.static_class.Funcs;

public class UserReviewsAdapter extends RecyclerView.Adapter<UserReviewsAdapter.UserReviewVwHldr>{
    public Context context;
    private final List<UserReview> userReviewList;
    public  final AdapterListener adapterListener;
    public  final int vhId;
    private boolean highLight = false;
    public UserReviewsAdapter(List<UserReview> userReviews, AdapterListener adapterListener, int vhId){
        this.adapterListener = adapterListener;
        this.vhId    = vhId;
        this.userReviewList = userReviews;
    }
    @NonNull
    @Override
    public UserReviewVwHldr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(vhId,parent,false);
        return new UserReviewVwHldr(view);
    }
    @Override
    public void onBindViewHolder(@NonNull UserReviewVwHldr holder, int position) {
        highLight = ! highLight;
        UserReview userReview = userReviewList.get(position);
        renderUserReviewField(userReview, holder, highLight);
    }
    @Override
    public int getItemCount() {
        return userReviewList.size();
    }
    private void renderUserReviewField(UserReview review, UserReviewVwHldr holder, boolean hglt){
        if (review.getCreateDate() != null)
            holder.reviewFrmTime.setText(review.getCreateDate());
        if(review.getSenderId() != null){
            if (review.getSenderId().equals(Usr.i().getUser().getId())){
                holder.reviewFrmImg.setVisibility(View.VISIBLE);
                Funcs.loadImg(context, holder.reviewFrmImg, C_.URL_BASE+Usr.i().getUser().getImgIcon(), 5, null);
            }
        }

        if (review.getComment() != null)
            holder.reviewFrmComment.setText(review.getComment());
        if (hglt)holder.reviewFrm.setBackgroundResource(R.color.stt_1);
        else     holder.reviewFrm.setBackgroundResource(R.color.stt_2);
    }


    class UserReviewVwHldr extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView  reviewFrmTime;
        TextView  reviewFrmComment ;
        ImageView reviewFrmImg;
        FrameLayout reviewFrm;
        boolean expand = false;
        public UserReviewVwHldr(@NonNull View itemView) {
            super(itemView);
            initViewElements();
            itemView.setOnClickListener(this);
        }
        @SuppressLint("ClickableViewAccessibility")
        private void initViewElements(){
            reviewFrmTime    = (TextView)itemView.findViewById(R.id.reviewFrmTime);
            reviewFrmComment = (TextView)itemView.findViewById(R.id.reviewFrmComment);
            reviewFrmImg     = (ImageView)itemView.findViewById(R.id.reviewFrmImg);
            reviewFrm        = (FrameLayout) itemView.findViewById(R.id.reviewFrm);
        }

        @Override
        public void onClick(View v) {
            if (!expand){
                reviewFrmComment.setMaxLines(500);
                expand = true;
            }else {
                reviewFrmComment.setMaxLines(4);
                expand = false;
            }
        }
    }
}

