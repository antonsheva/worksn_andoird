package com.worksn.objects;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;

import java.io.File;

public class TmpImg {
    public static String createId;
    public static String photoPath;
    public static File img;
    public static File icon;
    public static String imgSend;
    public static String imgIconSend;
    public static boolean wasSendImg = false;
    public static boolean sendImgIsRun = false;

    public static RoundedBitmapDrawable bitMapIconSend = null;
   /* boolean wasSendPostImg, boolean postImgWasLoad;
    * отлов ситуации, когда большой файл прибыл на сервер раньше пакета с текстовым сообщением
    * при том, что сообщение было отправлено раньше загрузки файла.
    * */
    public static boolean wasSendPostImg = false;
    public static boolean postImgWasLoad = false;

    public static void clear(){
        bitMapIconSend = null;
        createId           = null;
        img                = null;
        icon               = null;
        imgSend            = null;
        imgIconSend        = null;
        wasSendImg         = false;
        sendImgIsRun       = false;
        wasSendPostImg     = false;
        postImgWasLoad     = false;
    }
}
