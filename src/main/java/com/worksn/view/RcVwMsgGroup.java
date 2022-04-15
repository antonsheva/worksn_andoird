package com.worksn.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import com.worksn.R;
import com.worksn.adapters.MsgGroupAdapter;
import com.worksn.objects.C_;
import com.worksn.singleton.PUWindow;
import com.worksn.interfaces.AdapterListener;
import com.worksn.interfaces.NetCallback;
import com.worksn.objects.MyContext;
import com.worksn.objects.PostSubData;
import com.worksn.objects.StructMsg;
import com.worksn.singleton.Usr;
import com.worksn.classes.MyNet;

public class RcVwMsgGroup {
    MsgGroupAdapter msgGrpAdapter;
    LinearLayoutManager layoutManagerMsgGroup;
    RecyclerView rcVwMsgGroup;
    AdapterListener adapterListener;
    Context context;
    private List<StructMsg>messages = null;
    public RcVwMsgGroup(Activity activity, AdapterListener adapterLstnr){
        context = activity;
        adapterListener = adapterLstnr;
        rcVwMsgGroup = (RecyclerView) ((Activity)context).findViewById(R.id.rcVwMsgGroup);
    }
    public void clearRcVw(){
        ArrayList<StructMsg> tmp = new ArrayList<>();
        createMsgGroupRecyclerView(tmp, 0);
    }
    public void createMsgGroupRecyclerView( @NotNull List<StructMsg> msgs, Integer pos){
        ((Activity)context).runOnUiThread(() -> {
            messages = msgs;
            msgGrpAdapter = null;
            layoutManagerMsgGroup = new LinearLayoutManager(context);
            int qt = messages.size();

            msgGrpAdapter = new MsgGroupAdapter(qt, messages, R.layout.frm_msg_group,
                    (eCode, data1, data2) -> adapterListener.event(eCode,data1,data2));
            rcVwMsgGroup.setLayoutManager(layoutManagerMsgGroup);
            rcVwMsgGroup.setHasFixedSize(true);
            rcVwMsgGroup.setAdapter(msgGrpAdapter);
            if(pos!=null) rcVwMsgGroup.scrollToPosition(pos);
            Usr.i().requestUsersStatus((Context) context);
        });
    }
    public void setOnlineStatus(){
        renderOnlineStatus(Usr.i().onlineList);
        for (StructMsg msg : messages){
            if (Usr.i().onlineList.contains(msg.getSpeakerId()))msg.setSpeakerOnline(true);
        }
    }
    private void renderOnlineStatus(List<Integer> idList){
        ((Activity)context).runOnUiThread(() -> {
            int fst, lst;
            View v;
            fst = layoutManagerMsgGroup.findFirstVisibleItemPosition();
            lst = layoutManagerMsgGroup.findLastVisibleItemPosition();

            try{
                for (int i = fst; i <= lst; i++){
                    StructMsg msg = messages.get(i);
                    v = layoutManagerMsgGroup.findViewByPosition(i);
                    if(v == null) return;
                    ImageView online =  (ImageView)v.findViewById(R.id.uProfileOnline);
                    if (idList.contains(msg.getSpeakerId())){
                        online.setVisibility(View.VISIBLE);
                    }else {
                        online.setVisibility(View.GONE);
                    }
                }
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        });
    }
    public void removeMsgGroup(StructMsg rmvMsg){
        removeMsgGroup(context, rmvMsg.getDiscus_id(), (msg, result) -> {
            messages.remove(rmvMsg);
            int pos   = ((LinearLayoutManager) layoutManagerMsgGroup).findFirstVisibleItemPosition();
            createMsgGroupRecyclerView(messages, pos);
        });
    }
    private void removeMsgGroup(Context context, long id, CB cb){
        PostSubData subData = new PostSubData();
        subData.setId(id);
        MyNet.sendRequest(context, C_.ACT_REMOVE_DISCUS, subData, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                if (result == 0)
                    PUWindow.i().show(stringData);
                if (result == 1)
                    cb.callback(null, 1);
                if (result == -1)
                    PUWindow.i().show(stringData);
            }
        });
    }
    private interface CB {
        public void callback(StructMsg msg, Integer result);
    }
}
