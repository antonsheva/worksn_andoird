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

    public final static String ACT_CHNG_PASSWORD         = "chng_password";
    public final static String ACT_RECOVERY_PASSWORD     = "recovery_password";

    //---------------- ACT --------------------------------------------------
    public final static String ACT_ENABLE_SHOW_STATUS    = "enable_show_status";
    public final static String ACT_DISABLE_SHOW_STATUS   = "disable_show_status";

    public final static String ACT_GET_ENVIRONMENT_DATA  = "get_env_data";
    public final static String ACT_GET_USER_REVIEWS      = "get_user_reviews";
    public final static String ACT_GET_USER_DATA         = "get_user_data";

    public final static String ACT_GET_LIKE_USERS        = "get_like_users";
    public final static String ACT_GET_BAN_USERS         = "get_ban_users";
    public final static String ACT_SET_BW_STATUS         = "set_bw_status";

    public final static String ACT_GET_USER_MSG          = "get_user_msg";
    public final static String ACT_GET_ALL_MSG           = "get_all_msg";
    public final static String ACT_GET_NEW_MSG           = "get_new_msg";
    public final static String ACT_GET_DISCUS_FOR_ADS    = "get_discus_for_ads";
    public final static String ACT_GET_CHAIN_MSG         = "get_chain_msg";


    public final static String ACT_LOGIN                 = "login";
    public final static String ACT_ANONYMOUS_LOGIN       = "anonym_login";
    public final static String ACT_EXIT                  = "exit";

    public final static String ACT_UPDATE_USER_DATA      = "updt_user_data";
    public final static String ACT_UPDATE_AUTO_AUTH_DATA = "updt_auto_auth_data";
    public final static String ACT_GET_ADS_COLLECTION    = "get_ads_collection";
    public final static String ACT_REG_NEW_USER          = "reg_new_user";
    public final static String ACT_ADD_USER_REVIEW       = "add_user_review";

    public final static String ACT_ADS_REMOVE            = "rmv_ads";
    public final static String ACT_ADS_RECOVERY          = "recovery_ads";
    public final static String ACT_ADS_HIDDEN            = "hidden_ads";
    public final static String ACT_ADS_SHOW              = "show_ads";
    public final static String ACT_ADS_EDIT              = "edit_ads";
    public final static String ACT_ADS_ADD               = "add_ads";

    public final static String ACT_ADD_IMG               = "add_img";
    public final static String ACT_REMOVE_TMP_FILE       = "rm_tmp_file";

    public final static String ACT_CHECK_NEW_NOTIFY      = "check_new_notify";
    public final static String ACT_GET_NEW_NOTIFY        = "get_new_notify"  ;
    public final static String ACT_GET_ALL_NOTIFY        = "get_all_notify"  ;
    public final static String ACT_REFRESH_SESSION       = "refresh_session" ;

    public final static String ACT_ADD_MSG               = "add_msg"    ;
    public final static String ACT_REMOVE_MSG            = "rmv_msg"    ;
    public final static String ACT_REMOVE_DISCUS         = "rmv_discus" ;

    public final static String ACT_SEND_LOG_SERVICE      = "send_log_service" ;
    public final static String ACT_GET_TOKEN             = "get_token" ;

//---------------------------------------------------------------------------

//--------------- NAMES OF STRING VARIABLES  ------------------------------------

    public static final String STR_ID                       = "id"                    ;
    public static final String STR_ACT                      = "act"                   ;
    public static final String STR_CONTENT                  = "content"               ;
    public static final String STR_DESCRIPTION              = "description"           ;
    public static final String STR_COST                     = "cost"                  ;
    public static final String STR_SEARCH_PHRASE            = "search_phrase"         ;
    public static final String STR_ERROR                    = "error"                 ;
    public static final String STR_VAL                      = "val"                   ;
    public static final String STR_RESPONSE                 = "response"              ;
    public static final String STR_RESULT                   = "result"                ;
    public static final String STR_DATA                     = "data"                  ;
    public static final String STR_CONTEXT                  = "context"               ;
    public static final String STR_TYPE                     = "type"                  ;
    public static final String STR_DELIVER                  = "deliver"               ;
    public static final String STR_WS_TOKEN                 = "ws_token"              ;
    public static final String STR_FAVORITE                 = "favorite"              ;
    public static final String STR_COMMENT                  = "comment"               ;

//-------------- USER ----------------------------------------------
    public static final String STR_LOGIN                    = "login"                 ;
    public static final String STR_PASSWORD                 = "password"              ;
    public static final String STR_NEW_PASS                 = "new_pass"              ;
    public static final String STR_NAME                     = "name"                  ;
    public static final String STR_S_NAME                   = "s_name"                ;
    public static final String STR_EMAIL                    = "email"                 ;
    public static final String STR_PHONE                    = "phone"                 ;
    public static final String STR_WEB_SITE                 = "web_site"              ;
    public static final String STR_ABOUT_USER               = "about_user"            ;
    public static final String STR_AUTO_AUTH                = "auto_auth"             ;
    public static final String STR_LAST_TIME                = "last_time"             ;
    public static final String STR_RATING                   = "rating"                ;
    public static final String STR_RIGHTS                   = "rights"                ;
    public static final String STR_TXT_REVIEW               = "txt_review"            ;
    public static final String STR_STAR_QT                  = "star_qt"               ;
    public static final String STR_VOTE_QT                  = "vote_qt"               ;

//-------------- FILE ------------------------------------------------------
    public static final String STR_IMG                      = "img"                   ;
    public static final String STR_IMG_ICON                 = "img_icon"              ;
    public static final String STR_TMP_IMG                  = "tmp_img"               ;
    public static final String STR_TMP_IMG_ICON             = "tmp_img_icon"          ;
    public static final String STR_FILE_NAME                = "file_name"             ;
    public static final String STR_SAVE_IMG_DATA            = "save_img_data"         ;


    public static final String STR_SESSION_ID               = "session_id"            ;
    public static final String STR_NOTIFIES                 = "notifies"              ;
    public static final String STR_SETTING_PAGE_DATA        = "setting_page_data"     ;

    public static final String STR_INTEGER_DATA             = "integer_data"          ;
    public static final String STR_FLOAT_DATA               = "float_data"            ;

    public static final String STR_REMOVE                   = "remove"                ;
    public static final String STR_COPY                     = "copy"                  ;
    public static final String STR_EDIT                     = "edit"                  ;

    public static final String STR_STATUS                   = "status"                ;
    public static final String STR_BAN_LIST                 = "ban_list"              ;
    public static final String STR_LIKE_LIST                = "like_list"             ;
    public static final String STR_USERS_LIST               = "users_list"            ;
    public static final String STR_APP_ID                   = "app_id"                ;
    public static final String STR_S_TOKEN                  = "s_token"               ;
    public static final String STR_NEW_TOKEN                = "new_token"             ;

    public static final String STR_USER                     = "user"                  ;
    public static final String STR_USER_ID                  = "user_id"               ;
    public static final String STR_USER_LOGIN               = "user_login"            ;
    public static final String STR_USER_NAME                = "user_name"             ;
    public static final String STR_USER_S_NAME              = "user_s_name"           ;
    public static final String STR_USER_PHONE               = "user_phone"            ;
    public static final String STR_USER_EMAIL               = "user_email"            ;
    public static final String STR_USER_RATING              = "user_rating"           ;
    public static final String STR_USER_IMG                 = "user_img"              ;
    public static final String STR_USER_IMG_ICON            = "user_img_icon"         ;
    public static final String STR_USER_VOTE_QT             = "user_vote_qt"          ;
    public static final String STR_USER_RIGHTS              = "user_rights"           ;
    public static final String STR_USER_ONLINE              = "user_online"           ;
    public static final String STR_REVIEW                   = "review"                ;
    public static final String STR_USER_REVIEWS             = "user_reviews"          ;
    public static final String STR_USER_WS_TOKEN            = "user_ws_token"         ;

    public static final String STR_OWNER                    = "owner"                 ;
    public static final String STR_OWNER_ID                 = "owner_id"              ;
    public static final String STR_OWNER_LOGIN              = "owner_login"           ;
    public static final String STR_OWNER_RATING             = "owner_rating"          ;
    public static final String STR_OWNER_IMG                = "owner_img"             ;
    public static final String STR_OWNER_IMG_ICON           = "owner_img_icon"        ;

    public static final String STR_ADS_ID                   = "ads_id"                ;
    public static final String STR_ADS_TYPE                 = "ads_type"              ;
    public static final String STR_ADS_COLLECTION           = "ads_collection"        ;
    public static final String STR_TARGET_ADS               = "target_ads"            ;
    public static final String STR_ADS_CATEGORY             = "ads_category"          ;

    public static final String STR_MSG                      = "msg"                   ;
    public static final String STR_MESSAGES                 = "messages"              ;
    public static final String STR_DISCUS                   = "discus"                ;
    public static final String STR_MSG_ID                   = "msg_id"                ;
    public static final String STR_DISCUS_ID                = "discus_id"             ;
    public static final String STR_NOTIFY_DISCUS_ID         = "notify_discus_id"      ;
    public static final String STR_TARGET_MSG               = "target_msg"            ;
    public static final String STR_UNREAD_MSG               = "unread_msg"            ;

    public static final String STR_SENDER_ID                = "sender_id"             ;
    public static final String STR_SENDER_LOGIN             = "sender_login"          ;
    public static final String STR_SENDER_IMG               = "sender_img"            ;
    public static final String STR_SENDER_IMG_ICON          = "sender_img_icon"       ;
    public static final String STR_SENDER_RATING            = "sender_rating"         ;
    public static final String STR_SENDER_VOTE_QT           = "sender_vote_qt"        ;

    public static final String STR_CONSUMER_ID              = "consumer_id"           ;
    public static final String STR_CONSUMER_LOGIN           = "consumer_login"        ;
    public static final String STR_CONSUMER_IMG             = "consumer_img"          ;
    public static final String STR_CONSUMER_IMG_ICON        = "consumer_img_icon"     ;
    public static final String STR_CONSUMER_RATING          = "consumer_rating"       ;
    public static final String STR_CONSUMER_VOTE_QT         = "consumer_vote_qt"      ;

    public static final String STR_SPEAKER                  = "speaker"               ;
    public static final String STR_SPEAKER_ID               = "speaker_id"            ;
    public static final String STR_SPEAKER_LOGIN            = "speaker_login"         ;
    public static final String STR_SPEAKER_IMG              = "speaker_img"           ;
    public static final String STR_SPEAKER_IMG_ICON         = "speaker_img_icon"      ;
    public static final String STR_SPEAKER_RATING           = "speaker_rating"        ;
    public static final String STR_SPEAKER_VOTE_QT          = "speaker_vote_qt"       ;
    public static final String STR_SPEAKER_1                = "speaker_1"             ;
    public static final String STR_SPEAKER_2                = "speaker_2"             ;
    public static final String STR_SPEAKER_ONLINE           = "speaker_online"        ;

    public static final String STR_SUBJECT_ID               = "subject_id"            ;
    public static final String STR_SUBJECT_LOGIN            = "subject_login"         ;
    public static final String STR_SUBJECT_AVATAR           = "subject_avatar"        ;
    public static final String STR_SUBJECT_AVATAR_ICON      = "subject_avatar_icon"   ;
    public static final String STR_SUBJECT_RATING           = "subject_rating"        ;
    public static final String STR_SUBJECT_VOTE_QT          = "subject_vote_qt"       ;

    public static final String STR_ID_LIST                  = "id_list"               ;
    public static final String STR_STATUS_MSG               = "status_msg"            ;
    public static final String STR_CREATE_ID                = "create_id"             ;
    public static final String STR_CREATE_DATE              = "create_date"           ;
    public static final String STR_CREATE_TIME              = "create_time"           ;
    public static final String STR_LIFETIME                 = "lifetime"              ;
    public static final String STR_DATA_GROUP               = "data_group"            ;

    public static final String STR_REPLY_MSG_ID             = "reply_msg_id"          ;
    public static final String STR_REPLY_CONTENT            = "reply_content"         ;
    public static final String STR_REPLY_SENDER_ID          = "reply_sender_id"       ;
    public static final String STR_REPLY_SENDER_LOGIN       = "reply_sender_login"    ;
    public static final String STR_REPLY_IMG                = "reply_img"             ;

    public static final String STR_SHOW_STATUS              = "show_status"           ;

    public static final String STR_LAST_PING_TIME           = "last_ping_time"        ;
    public static final String STR_SWITCH_SHOW_STATUS       = "switch_show_status"    ;
    public static final String STR_SWITCH_CONFIRM_DELIVER   = "switch_confirm_deliver";
    public static final String STR_SWITCH_CONFIRM_VIEWED    = "switch_confirm_viewed" ;
    public static final String STR_SWITCH_SEND_PRINT_TEXT   = "switch_send_print_text";

    public static final String STR_SHOW_STATUS_CHANGE       = "show_status_change"    ;
    public static final String STR_CONFIRM_DELIVER_CHANGE   = "confirm_deliver_change";
    public static final String STR_CONFIRM_VIEWED_CHANGE    = "confirm_viewed_change" ;
    public static final String STR_SEND_PRINT_TEXT_CHANGE   = "send_print_text_change";

    public static final String STR_CATEGORY                 = "category";
    public static final String STR_CATEGORIES               = "categories";
    public static final String STR_ACTIVE                   = "active";
    public static final String STR_VIEW                     = "view";

    public static final String STR_HOUR_START               = "hour_start";
    public static final String STR_HOUR_STOP                = "hour_stop";
    public static final String STR_MIN_START                = "min_start";
    public static final String STR_MIN_STOP                 = "min_stop";

    public static final String STR_VISIBLE_MODE             = "visible_mode";
    public static final String STR_IMG_LIST                 = "img_list";

    public static final String STR_COORD_X                  = "coord_x";
    public static final String STR_COORD_Y                  = "coord_y";

    public static final String STR_MIN_X                    = "min_x";
    public static final String STR_MIN_Y                    = "min_y";
    public static final String STR_MAX_X                    = "max_x";
    public static final String STR_MAX_Y                    = "max_y";


    public static final String STR_ACTION_TYPE              = "action_type";
    public static final String STR_RESP_TYPE                = "resp_type";
    public static final String STR_NOTIFY_FIELD             = "notify_field";

    public static final String STR_WAS_SEND_POST_DATA       = "was_send_post_data";

    public static final String STR_MAX_FILE_SIZE            = "MAX_FILE_SIZE";
//--------------------------------------------------------------------------------------------------


//----------------------------- VALUES -------------------------------------------------------------
    public final static boolean VAL_SHOW = true;
    public final static boolean VAL_HIDE = false;




    public final static long ADS_ID_FOR_ADMIN               = 1;
    public final static long ADS_ID_FOR_DIRECT_DISCUS       = 2;
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
    public final static String URL_WS            = "wss://worksn.ru:8000";

//------------- File name --------------------------------------------------
    public final static String FILE_NAME_TMP_PHOTO = "tmp_photo.jpg";
    public final static String FILE_GALLERY = "service_img/design/gallery.gif";





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

