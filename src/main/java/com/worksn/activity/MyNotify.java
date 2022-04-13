package com.worksn.activity;

import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import org.jetbrains.annotations.Nullable;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import java.util.Timer;
import java.util.TimerTask;

import com.worksn.R;
import com.worksn.classes.ConfirmDeliverMsg;
import com.worksn.objects.C_;
import com.worksn.objects.G_;
import com.worksn.objects.StructMsg;
import com.worksn.singleton.MyStorage;

public class MyNotify {
    Timer mLoadImgTimer = null;
    boolean notifyIsWorked = false;
    public void newNotify(Context context, StructMsg msg, String prepTxt, boolean enSound){
        Bitmap img = null;
        Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        long discusId = 0;
        int senderId = 0;
        String senderLogin = null;
        String senderImg   = null;
        String titleTxt = null;
        String msgContent = null;
        long adsId    = 0;
        long msgId    = 0;
        String createDate = null;

        if (msg.getDiscus_id()  != null)  discusId    = msg.getDiscus_id();
        if (msg.getSender_id()  != null)  senderId    = msg.getSender_id();
        if (msg.getSenderImg() != null)senderImg  = msg.getSenderImg();
        if (msg.getAds_id()     != null) adsId      = msg.getAds_id();
        if (msg.getId()         != null) msgId      = msg.getId();
        if (msg.getCreateDate() != null) createDate = msg.getCreateDate();

        if (msg.getSender_login() != null){
            senderLogin = msg.getSender_login();
            titleTxt = prepTxt + msg.getSender_login();
        }else{
            titleTxt = context.getString(R.string.thereAreUnreadMsg);
        }

        msgContent = context.getString(R.string.newMsg);
        if ((msg.getContent().equals(" "))||(msg.getContent().equals(""))||(msg.getContent() == null)){
            if (msg.getImgIcon() != null){
                if(senderLogin != null)msgContent = context.getString(R.string.imgFrom_)+senderLogin;
                else msgContent = context.getString(R.string.newImg);
            }
        }else {
            msgContent = msg.getContent();
        }


        G_.notifyDiscusId = discusId;
        MyStorage.i().putData(C_.STR_NOTIFY_DISCUS_ID, discusId);
        Intent intent = new Intent(context, MainActivity.class);

        Intent intentQuickReply = new Intent(context, QuickReply.class);
        intentQuickReply.putExtra(C_.STR_DISCUS_ID, discusId);
        intentQuickReply.putExtra(C_.STR_SENDER_ID, senderId);
        intentQuickReply.putExtra(C_.STR_ADS_ID, adsId);
        intentQuickReply.putExtra(C_.STR_MSG_ID, msgId);
        intentQuickReply.putExtra(C_.STR_CREATE_DATE, createDate);

        Intent intentSetAsViewed = new Intent (context, NotifyBroadcastReceiver.class);
        intentSetAsViewed.putExtra("act", C_.ACT_CONFIRM_DELIVER_MSG);
        intentSetAsViewed.putExtra(C_.STR_DISCUS_ID, discusId);
        intentSetAsViewed.putExtra(C_.STR_CONSUMER_ID, senderId);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(context, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentQuickReply =
                PendingIntent.getActivity(context, 0, intentQuickReply,PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentSetAsViewed =
                PendingIntent.getBroadcast(context, 0, intentSetAsViewed,PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteInput remoteInput = new RemoteInput.Builder(C_.STR_NOTIFY_FIELD)
                .setLabel(context.getString(R.string.msgTxt))
                .build();


        NotificationCompat.Action action = new NotificationCompat.Action.Builder(android.R.drawable.ic_menu_send,
                context.getString(R.string.notifyReply), pendingIntentQuickReply)
                        .setAllowGeneratedReplies(true)
                        .addRemoteInput(remoteInput)
                        .build();

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, C_.NOTIFY_CHANNEL_MAIN)
                        .setSmallIcon(R.drawable.notification)
                        .setContentTitle(titleTxt)
                        .setContentText(msgContent)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(resultPendingIntent)
                        .addAction(action)
                        .addAction(0, context.getString(R.string.notifySetAsViewed), pendingIntentSetAsViewed)
                            ;
        if (enSound){
            builder.setSound(ringtoneUri);
            builder.setVibrate(new long[]{0, 500, 1000});
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        builder.setContentIntent(resultPendingIntent);
        if(senderImg  !=null){
            senderImg   = C_.URL_BASE +senderImg  ;
            if (mLoadImgTimer != null){
                mLoadImgTimer.cancel();
                mLoadImgTimer = null;
            }
            mLoadImgTimer = new Timer();
            mLoadImgTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(!notifyIsWorked){
                        notifyIsWorked = true;
                        notificationManager.notify(1, builder.build());
                    }
                }
            }, 3000);
            getBitmap(context, senderImg  , new CB() {
                @Override
                public void callback(Bitmap bm) {
                    if(!notifyIsWorked){
                        notifyIsWorked = true;
                        builder.setLargeIcon(bm);
                        notificationManager.notify(1, builder.build());
                    }
                }
            });
        }else notificationManager.notify(1, builder.build());


        new ConfirmDeliverMsg(context, senderId, discusId, C_.CODE_CONFIRM_DELIVER);
    }
    public void removeNotify(Context context){
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(context);
        notificationManager.cancel(1);
    }
    private void getBitmap(Context context, String url, CB cb) {
        final Bitmap[] bitmap = {null};
        final Bitmap[] res = {null};
        Glide.with(context)
                .asBitmap()
                .load(url)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        bitmap[0] = resource;
                        cb.callback(bitmap[0]);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    interface CB{
        public void callback(Bitmap bm);
    }
}
