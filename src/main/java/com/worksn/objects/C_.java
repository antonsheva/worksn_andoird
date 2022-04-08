package com.worksn.objects;

public class C_ {
    public final static String NOTIFY_CHANNEL_MAIN       = "notify_channel_main";
    public final static String NOTIFY_CHANNEL_FOREGROUND = "notify_channel_foreground";
    public final static boolean CONN_OFF = false;
    public final static boolean CONN_ON = true;

//    --- Messages  ---------------------------------------------------------
    public final static int MSG_TYPE_ALL    = 0;
    public final static int MSG_TYPE_NEW    = 1;

//    --- Screen    ----------------------------------------------------------
    public final static int ACTIVE_SCREEN_NO_CHANGE  = 0;
    public final static int ACTIVE_SCREEN_MSG_GROUP  = 1;
    public final static int ACTIVE_SCREEN_CATEGORY   = 2;
    public final static int ACTIVE_SCREEN_LIFETIME   = 3;
    public final static int ACTIVE_SCREEN_USERS      = 4;
    public final static int ACTIVE_SCREEN_ADD        = 5;
    public final static int ACTIVE_SCREEN_MSG_CHAIN  = 6;
    public final static int ACTIVE_SCREEN_TARGET_ADS = 7;
    public final static int ACTIVE_SCREEN_EMPTY      = 8;

    public final static int SCREEN_MODE_MAIN             = 0;
    public final static int SCREEN_MODE_MSG_CHAIN        = 1;
    public final static int SCREEN_MODE_ADS_DETAIL       = 2;
    public final static int SCREEN_MODE_ADD_ADS          = 3;
//------------------------------------------------------------------------------

    public final static int APP_MODE_MAIN                = 0;
    public final static int APP_MODE_ADD_ADS             = 1;
    public final static int APP_MODE_SHOW_BW_LIST        = 2;
    public final static int APP_MODE_DISCUS_WITH_USER    = 3;

    public final static int APP_PAGE_MAIN                 = 0;
    public final static int APP_PAGE_MY_PROFILE           = 1;
    public final static int APP_PAGE_ANONYMOUS_USER_PAGE  = 2;
    public final static int APP_PAGE_USER_PAGE            = 3;
    public final static int APP_PAGE_REGISTRATION         = 4;
    public final static int APP_PAGE_RECOVERY_PASSWORD    = 5;
    public final static int APP_PAGE_SETTING              = 6;
    public final static int APP_PAGE_MAKE_PHOTO           = 7;
    public final static int APP_PAGE_CHOOSE_IMG           = 8;


    public final static int CODE_SHOW_IMG                  = 0;
    public final static int CODE_REMOVE_MSG                = 1;
    public final static int CODE_REMOVE_DISCUS             = 2;
    public final static int CODE_EMPTY                     = 3;
    public final static int CODE_SHOW_SUB_MENU             = 4;
    public final static int CODE_NEW_MSG                   = 5;
    public final static int CODE_CONNECTION_ERROR          = 6;
    public final static int CODE_ON_CONNECT                = 7;
    public final static int CODE_USER_STATUS               = 8;
    public final static int CODE_SHOW_DISCUS               = 9;
    public final static int CODE_SHOW_TARGET_ADS           = 10;
    public final static int CODE_EXIT                      = 11;
    public final static int CODE_WS_HAVE_NEW_MSG           = 12;
    public final static int CODE_ON_CLOSE                  = 13;
    public final static int CODE_ON_FAILURE                = 14;
    public final static int CODE_WS_AUTH_USER              = 15;
    public final static int CODE_WS_NEW_AUTH_DATA          = 16;
    public final static int CODE_WS_BAD_AUTH_DATA          = 17;
    public final static int CODE_WS_NEW_TOKEN              = 18;
    public final static int CODE_CONFIRM_DELIVER           = 1;
    public final static int CODE_CONFIRM_VIEWED            = 2;
    public final static int CODE_WS_ONLINE_LIST            = 20;
    public final static int CODE_WS_PRINT_TXT              = 21;
    public final static int CODE_WS_SEND_ERROR             = 22;
    public final static int CODE_WS_BIND_IMG_TO_MSG        = 23;
    public final static int CODE_WS_PING                   = 24;
    public final static int CODE_WS_PONG_SERVER            = 30;
    public final static int CODE_WAKEUP                    = 31;
    public final static int CODE_SHOW_REPLY_MSG            = 32;
    public final static int CODE_RC_VIEW_POSITION          = 33;


    //------------- ADS VISIBLE MODE ---------------------------------------------
    public final static int ADS_VISIBLE_HIDDEN           = 0;
    public final static int ADS_VISIBLE_NORMAL           = 1;
    public final static int ADS_VISIBLE_HIDDEN_FOR_TIME  = 2;
    public final static int ADS_VISIBLE_HIDDEN_MANUAL    = 3;
    public final static int ADS_VISIBLE_HIDDEN_REMOVE    = 4;



    //------------- ACT WS -------------------------------------------------------
    public final static String ACT_CONFIRM_DELIVER_MSG   = "deliver_msg";
    public final static String ACT_CHECK_NEW_MSG         = "check_new_msg";
    public final static String ACT_GET_ONLINE_STATUS     = "get_online_status";
    public final static String ACT_EXIT                  = "exit";
    public final static String ACT_CLOSE_ACTIVITY        = "enable_notify";
    public final static String ACT_OPEN_ACTIVITY         = "disable_notify";
    public final static String ACT_WAKEUP                = "wakeup";
    public final static String ACT_PRINT_MSG_PROCESS     = "print_msg_process";
    public final static String ACT_BIND_IMG_TO_MSG       = "bind_img_to_msg";
    public final static String ACT_PING                  = "ping";
    public final static String ACT_PONG                  = "pong";
    public final static String ACT_PING_SERVER           = "ping_server";
    public final static String ACT_PONG_SERVER           = "pong_server";
    public final static String ACT_WS_IS_START           = "ws_start";
    public final static String ACT_AUTH_USER             = "auth_user";
    public final static String ACT_ONLINE_LIST           = "online_list";
    public final static String ACT_NEW_MSG               = "send_msg";
    public final static String ACT_NEW_TOKEN             = "new_token";
    public final static String ACT_NEW_AUTH_DATA         = "new_auth_data";
    public final static String ACT_BAD_AUTH_DATA         = "bad_auth_data";
    public final static String ACT_CONNECTION_OK         = "connection_ok";
    public final static String ACT_CONNECTION_ERROR      = "connection_error";



    //---------------- ACT APACHE --------------------------------------------------
    public final static String ACT_ENABLE_SHOW_STATUS    = "enable_show_status";
    public final static String ACT_DISABLE_SHOW_STATUS   = "disable_show_status";

    public final static String ACT_GET_ENVIRONMENT_DATA  = "get_env_data";
    public final static String ACT_GET_USER_REVIEWS      = "get_user_reviews";

    public final static String ACT_GET_LIKE_USERS        = "get_like_users";
    public final static String ACT_GET_BAN_USERS         = "get_ban_users";
    public final static String ACT_SET_BW_STATUS         = "set_bw_status";

    public final static String ACT_GET_USER_MSG          = "get_user_msg";
    public final static String ACT_GET_ALL_MSG           = "get_all_msg";
    public final static String ACT_GET_NEW_MSG           = "get_new_msg";
    public final static String ACT_GET_DISCUS_FOR_ADS    = "get_discus_for_ads";
    public final static String ACT_GET_CHAIN_MSG         = "get_chain_msg";
    public final static String ACT_UPDATE_USER_DATA      = "updt_user_data";
    public final static String ACT_UPDATE_AUTO_AUTH_DATA = "updt_auto_auth_data";
    public final static String ACT_GET_ADS_COLLECTION    = "get_ads_collection";
    public final static String ACT_REG_NEW_USER          = "reg_new_user";


    public final static String ACT_ADS_REMOVE   = "rmv_ads";
    public final static String ACT_ADS_RECOVERY = "recovery_ads";
    public final static String ACT_ADS_HIDDEN   = "hidden_ads";
    public final static String ACT_ADS_SHOW     = "show_ads";
    public final static String ACT_ADS_EDIT     = "edit_ads";
    public final static String ACT_ADS_ADD      = "add_ads";

    public final static String ACT_REMOVE_TMP_FILE      = "rm_tmp_file";
    public final static String ACT_ANONYMOUS_LOGIN      = "anonym_login";

    public final static String ACT_CHECK_NEW_NOTIFY  = "check_new_notify";
    public final static String ACT_GET_NEW_NOTIFY    = "get_new_notify"  ;
    public final static String ACT_GET_ALL_NOTIFY    = "get_all_notify"  ;
    public final static String ACT_REFRESH_SESSION    = "refresh_session"  ;

//---------------------------------------------------------------------------

//--------------- NAMES OF STRING VARIABLES  ------------------------------------

    public static final String VAR_STATUS                   = "status"                ;
    public static final String VAR_BAN_LIST                 = "ban_list"              ;
    public static final String VAR_LIKE_LIST                = "like_list"             ;
    public static final String VAR_USERS_LIST               = "users_list"            ;
    public static final String VAR_APP_ID                   = "app_id"                ;
    public static final String VAR_S_TOKEN                  = "s_token"               ;
    public static final String VAR_NEW_TOKEN                = "new_token"             ;
    public static final String VAR_USER_ID                  = "user_id"               ;
    public static final String VAR_USER_LOGIN               = "user_login"            ;
    public static final String VAR_USER_RATING              = "user_rating"           ;
    public static final String VAR_USER_AVATAR              = "user_avatar"           ;
    public static final String VAR_USER_AVATAR_ICON         = "user_avatar_icon"      ;

    public static final String VAR_OWNER_ID                 = "owner_id"              ;
    public static final String VAR_OWNER_LOGIN              = "owner_login"           ;
    public static final String VAR_OWNER_RATING             = "owner_rating"          ;
    public static final String VAR_OWNER_AVATAR             = "owner_avatar"          ;
    public static final String VAR_OWNER_AVATAR_ICON        = "owner_avatar_icon"     ;

    public static final String VAR_MSG_ID                   = "msg_id"                ;
    public static final String VAR_DISCUS_ID                = "discus_id"             ;
    public static final String VAR_NOTIFY_DISCUS_ID         = "notify_discus_id"      ;
    public static final String VAR_ADS_ID                   = "ads_id"                ;
    public static final String VAR_SENDER_ID                = "sender_id"             ;
    public static final String VAR_SENDER_LOGIN             = "sender_login"          ;
    public static final String VAR_SENDER_AVATAR            = "sender_avatar"         ;
    public static final String VAR_SENDER_AVATAR_ICON       = "sender_avatar_icon"    ;
    public static final String VAR_SENDER_RATING            = "sender_rating"         ;
    public static final String VAR_SENDER_VOTE_QT           = "sender_vote_qt"        ;
    public static final String VAR_CONSUMER_ID              = "consumer_id"           ;
    public static final String VAR_CONSUMER_LOGIN           = "consumer_login"        ;
    public static final String VAR_CONSUMER_AVATAR          = "consumer_avatar"       ;
    public static final String VAR_CONSUMER_AVATAR_ICON     = "consumer_avatar_icon"  ;
    public static final String VAR_CONSUMER_RATING          = "consumer_rating"       ;
    public static final String VAR_CONSUMER_VOTE_QT         = "consumer_vote_qt"      ;
    public static final String VAR_SPEAKER_ID               = "speaker_id"            ;
    public static final String VAR_SPEAKER_LOGIN            = "speaker_login"         ;
    public static final String VAR_SPEAKER_AVATAR           = "speaker_avatar"        ;
    public static final String VAR_SPEAKER_AVATAR_ICON      = "speaker_avatar_icon"   ;
    public static final String VAR_SPEAKER_RATING           = "speaker_rating"        ;
    public static final String VAR_SPEAKER_VOTE_QT          = "speaker_vote_qt"       ;

    public static final String VAR_SUBJECT_ID               = "subject_id"            ;
    public static final String VAR_SUBJECT_LOGIN            = "subject_login"         ;
    public static final String VAR_SUBJECT_AVATAR           = "subject_avatar"        ;
    public static final String VAR_SUBJECT_AVATAR_ICON      = "subject_avatar_icon"   ;
    public static final String VAR_SUBJECT_RATING           = "subject_rating"        ;
    public static final String VAR_SUBJECT_VOTE_QT          = "subject_vote_qt"       ;

    public static final String VAR_CONTENT                  = "content"               ;
    public static final String VAR_IMG                      = "img"                   ;
    public static final String VAR_IMG_ICON                 = "img_icon"              ;
    public static final String VAR_ID_LIST                  = "id_list"               ;
    public static final String VAR_STATUS_MSG               = "status_msg"            ;
    public static final String VAR_CREATE_ID                = "create_id"             ;
    public static final String VAR_CREATE_DATE              = "create_date"           ;
    public static final String VAR_DATA_GROUP               = "data_group"            ;

    public static final String VAR_REPLY_MSG_ID             = "reply_msg_id"          ;
    public static final String VAR_REPLY_CONTENT            = "reply_content"         ;
    public static final String VAR_REPLY_SENDER_ID          = "reply_sender_id"       ;
    public static final String VAR_REPLY_SENDER_LOGIN       = "reply_sender_login"    ;
    public static final String VAR_REPLY_IMG                = "reply_img"             ;

    public static final String VAR_SHOW_STATUS              = "show_status"           ;

    public static final String VAR_LAST_PING_TIME           = "last_ping_time"        ;
    public static final String VAR_SWITCH_SHOW_STATUS       = "switch_show_status"    ;
    public static final String VAR_SWITCH_CONFIRM_DELIVER   = "switch_confirm_deliver";
    public static final String VAR_SWITCH_CONFIRM_VIEWED    = "switch_confirm_viewed" ;
    public static final String VAR_SWITCH_SEND_PRINT_TEXT   = "switch_send_print_text";

    public static final String VAR_SHOW_STATUS_CHANGE       = "show_status_change"    ;
    public static final String VAR_CONFIRM_DELIVER_CHANGE   = "confirm_deliver_change";
    public static final String VAR_CONFIRM_VIEWED_CHANGE    = "confirm_viewed_change" ;
    public static final String VAR_SEND_PRINT_TEXT_CHANGE   = "send_print_text_change";

    public static final String VAR_ACTIVE                   = "active";
    public static final String VAR_VIEW                     = "view";

    public static final String VAR_HOUR_START               = "hour_start";
    public static final String VAR_HOUR_STOP                = "hour_stop";
    public static final String VAR_MIN_START                = "min_start";
    public static final String VAR_MIN_STOP                 = "min_stop";

    public static final String VAR_VISIBLE_MODE             = "visible_mode";
    public static final String VAR_EDIT                     = "edit";
    public static final String VAR_IMG_LIST                 = "img_list";


//------------------------------------------------------------------------------------------------



    public final static long ADS_ID_FOR_ADMIN         = 1;
    public final static long ADS_ID_FOR_DIRECT_DISCUS = 2;
    public final static long ADS_CATEGORY_FOR_ADMIN         = 100;
    public final static long ADS_CATEGORY_FOR_DIRECT_DISCUS = 101;


//------------- User black/white list status -----------------------------
    public final static int BW_STATUS_EMPTY = 0;
    public final static int BW_STATUS_LIKE  = 1;
    public final static int BW_STATUS_BAN   = 2;

//------------- URLs ------------------------------------------------------
    public final static String URL_BASE          = "https://worksn.ru/";
    public final static String URL_USERS_IMG     = "https://worksn.ru/wksn_users_img/";
    public final static String URL_TMP_IMG       = "https://worksn.ru/wksn_users_img/tmp_img/";
    public final static String URL_TMP_IMG_ICON  = "https://worksn.ru/wksn_users_img/tmp_img/icon/";
    public final static String URL_SERVICE_IMG   = "https://worksn.ru/service_img/";
    public final static String URL_NO_AVATAR     = "https://worksn.ru/service_img/avatars/no-avatar.jpg";


//   --------   Map  ---------------------------------------------
    public final static int MAP_PM_YELLOW        = 0;
    public final static int MAP_PM_RED           = 1;
    public final static int MAP_PM_BLUE          = 2;
    public final static int MAP_PM_GREEN         = 3;
    public final static int MAP_PM_PURPLE        = 4;

    public final static int CODE_MAP_MOVE        = 1;
    public final static int CODE_MAP_CLICK       = 2;
    public final static int CODE_MAP_INIT        = 3;
    public final static int CODE_MAP_ADS_DETAILS = 4;

//    ------   Ads  ------------------------------------------------
    public final static int ADS_TYPE_ANY       = 0;
    public final static int ADS_TYPE_EMPLOYER  = 1;
    public final static int ADS_TYPE_WORKER    = 2;

    public final static int ADS_PARAM_ANY      = 0;
    public final static int ADS_PARAM_CATEGORY = 1;
    public final static int ADS_PARAM_USER     = 2;
    public final static int ADS_PARAM_ADD      = 3;
    public final static int ADS_PARAM_LIFETIME = 3;


//    ------   Upload file  -----------------------------------------
    public final static int PICK_IMAGE_REQUEST = 1;
    public final static int IMG_FOR_MSG = 1;
    public final static int IMG_FOR_ADS = 2;


//    -----    Sub menu ----------------------------------------------
    public static final int SUBMENU_OBJECT_TYPE_MSG       = 1;
    public static final int SUBMENU_OBJECT_TYPE_MSG_GROUP = 2;
    public static final int SUBMENU_OBJECT_TYPE_ADS       = 3;
    public static final int SUBMENU_OBJECT_SEND_MSG       = 4;

    public static final int SUBMENU_CODE_REMOVE_MSG       = 1;
    public static final int SUBMENU_CODE_REMOVE_MSG_GROUP = 2;
    public static final int SUBMENU_CODE_COPY_TXT         = 3;
    public static final int SUBMENU_CODE_REPLY_TO_MSG     = 4;
    public static final int SUBMENU_CODE_ADS_REMOVE       = 5;
    public static final int SUBMENU_CODE_ADS_RECOVERY     = 6;
    public static final int SUBMENU_CODE_ADS_EDIT         = 7;
    public static final int SUBMENU_CODE_ADS_SHOW         = 8;
    public static final int SUBMENU_CODE_ADS_HIDDEN       = 9;














}

