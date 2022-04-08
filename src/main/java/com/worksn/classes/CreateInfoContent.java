package com.worksn.classes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.worksn.R;
import com.worksn.objects.HtmlSS;
import com.worksn.objects.StructTxtData;
import com.worksn.objects.T_;
import com.worksn.singleton.MyStorage;

import java.util.ArrayList;

public class CreateInfoContent {
    Activity activity;
    LinearLayout frmContent;
    public CreateInfoContent(Activity activity){
        this.activity = activity;
        frmContent = (LinearLayout)activity.findViewById(R.id.settingFrmContent);

        String fieldList = MyStorage.i().getString(T_.ENVIRONMENT_CONTENT_LIST);
        if (fieldList == null) return;
        String[] list = fieldList.split("___");
        ArrayList<StructTxtData> pageContents = new ArrayList<>();
        for (String key : list){
            if (MyStorage.i().getString(key) == null)continue;
            String description = new HtmlSS().strDecode(MyStorage.i().getString(key));
            StructTxtData data = new StructTxtData();
            data.setName(key);
            data.setDescription(description);
            addNote(data);
        }
    }
    private void addNote(StructTxtData content){
        LayoutInflater inflater = (activity.getLayoutInflater());
        View v = inflater.inflate(R.layout.shell_layout_wc, (ViewGroup) frmContent, false);
        new MyHref(activity, v, content.getName(), content.getDescription());
        frmContent.addView(v);
    }
}
