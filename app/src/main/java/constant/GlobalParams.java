package constant;

public class GlobalParams {
    /**
     * 微信appid
     */
    public static final String JUHESHUJU_KEY = "3054072c3bfc4b10d0faa986ac15c96b";

    // 整型常量
    public static final int CHANNEL_ID = 2006;
    public static final int IMG_WIDTH = 216;
    public static final int IMG_HEIGHT = 172;

    public static final int SHOP_TYPE_PROPORTION = 6;//商家类型图标占总屏幕宽度为1/6；
    public static final int SHOP_ITEM_IMG_PROPORTION = 5;//商家列表中logo占比
    public static final int CONSUMPTION_IMG_PROPORTION = 6;//付款记录中logo占比
    public static final int BILL_IMG_PROPORTION = 6;//账单中商家logo的占比
    public static final int PAY_IMG_PROPORTION = 9;//买单页面商家logo的占比
    public static final int WX_PAY_SUCCESS = 0;//微信支付成功
    public static final int WX_PAY_FAIL = -1;//微信支付失败
    public static final int WX_PAY_CANCEL = -2;//用户取消支付

    public static final String NOT_QUALIFIED = "0";//上传身份证信息，不合格
    public static final String QUALIFIED = "1";//上传身份证信息，合格
    public static final String REFUSE_BY_MECHINE_FROM_KEY = "refuse_by_mechine_from_key";//决策拒绝来源
    public static final String REFUSE_BY_MECHINE_FROM_SAVE_ID = "refuse_by_mechine_from_save_id";//保存身份证信息决策拒绝
    public static final int STAGES_TYPE_ITEM_WIDTH = 3;//分期类型中的item占屏幕总款的1/3
    public static final int AUTHENTICATION_SUCCESS = 1;//认证成功
    public static final int AUTHENTICATION_FAIL = 2;//认证失败
    public static final int VERIFY_FINISHED = 1; // 分期或提额审核完成
    public static final int WITHDRAWALS_VERIFY_FINISHED = 2; // 提现审核完成
    public static final int UPDATE_LOG_STATUS = 3;//上传log
    public static final int A_LOT_OF_VERIFY = 5;//各种审核状态
    public static final int ADD_BORROW_TERM = 6;//延长借款期限（各种审核状态）
    public static final int PRO_REGISTE = 1;//用户注册协议
    public static final int PRO_SERVER = 2;//用户服务协议
    public static final int PRO_RENEW = 3;//用户续借申请协议
    public static final int REPAY_BY_BANK_CARD = 0;//银行卡还款
    public static final int REPAY_BY_WECHAT = 1;//微信还款
    public static final int STAGE_TYPE_REFRESH = 3;//刷新分期类型
    public static final int DIALOG_SHOW = 4;//加载数据时显示dialog
    public static final int DIALOG_DISSMISS = 5;//隐藏加载数据的dialog
    public static final int REFRESH_CLEAR_VIEW = 6;//清空数据
    public static final int APPLY_TYPE_INSTALLMENT = 2; //分期消费
    public static final int APPLY_TYPE_WITHDRAWALS_APPLY = 3; //提现申请
    public static final int APPLY_TYPE_GET_CASH = 4; //提取现金（转入用户银行卡）
    public static final String BILL_DETAIL_FROM_KEY = "bill_detail_from_key";//详情来源
    public static final int BILL_DETAIL_FROM_PAY = 1;//消费账单-"我的" 进入
    public static final int BILL_DETAIL_FROM_PAY_BILL = 2;//消费账单 "消费账单" 进入
    public static final int BILL_DETAIL_FROM_WITHDRAWLS = 3;//提现账单
    public static final String NOT_MUST_AUTHENTICATION = "0";//无需认证
    public static final String ONE_MUST_AUTHENTICATION = "1";//必须认证一项
    public static final String TWO_MUST_AUTHENTICATION = "2";//必须认证两项
    public static final String AUTHENTICATION_OPEN = "1";//列表中显示
    public static final String AUTHENTICATION_CLOSE = "0";//列表中不显示
    public static final String NOT_AUTHENTICATION = "0";//未认证
    public static final String ALREADY_AUTHENTICATION = "1";//已认证
    public static final int AUTHENTICATION_REQUEST = 1;//认证请求
    public static final String CHANGE_LOGIN_PASSWORD = "2";//修改登录密码
    public static final String CHANGE_PAY_PASSWORD = "1";//修改支付密码
    public static final String INIT_PAGE = "1";//初始化页面
    public static final String NOT_INIT_PAGE = "0";//不初始化页面
    public static final String AUTHENTICATION_CHINA_MOBILE = "china_mobile";//运营商认证
    public static final String AUTHENTICATION_TAOBAO = "taobao_pass";//淘宝认证
    public static final String AUTHENTICATION_JINGDONG = "jd_pass";//京东认证
    public static final String AUTHENTICATION_ZHIFUBAO = "alipay_pass";//支付宝认证
    public static final String AUTHENTICATION_ZHIMA = "zhima_pass";//芝麻认证
    public static final String DOWN_TYPE_DEFAT = "1";//默认首付
    public static final String DOWN_TYPE_CHANGED = "2";//用户自定义修改首付

    public static final String USER_STATUS_NEW = "0";//新用户，未认证
    public static final String HAVE_UPLOAD_IDCARD_INFO = "1";//已上传身份证信息
    public static final String HAVE_SCAN_FACE = "2";//已扫脸通过
    public static final String HAVE_BIND_BANK_CARD = "3";//已绑定银行卡
    public static final String HAVE_UPLOAD_CONTACTS_INFO = "4";//已经上传过联系人信息



    public static final String USER_STATUS_HAD_PASS = "1";//用户有支付密码
    public static final String USER_STATUS_NOT_PASS = "0";//用户没有支付密码
    public static final String IS_CAN_CHANGE = "1";//可修改个人信息
    public static final String CAN_NOT_CHANGE = "0";//不可修改个人信息
    // 相机相关
    public static final int INTO_IDCARDSCAN_FRONT_PAGE = 50; // 身份证正面
    public static final int INTO_IDCARDSCAN_BACK_PAGE = 49; // 身份证反面
    public static final int PAGE_INTO_LIVENESS = 51; //活体检测
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100; // 照相的tag值
    public static final int GET_PICTURE_FROM_XIANGCE_CODE = 101; // 从相册取相片的tag值
    public static final int GET_CUT_PICTURE_CODE = 102; // 取得裁剪后的照片
    public static final int EMPLOYEE_CARD = 110;         // 工作证
    public static final int CREDIT = 111;         // 信用卡
    public static final int HUKOU = 112;         // 户口本
    public static final int MARRIAGE_CERTIFICATE = 113;      // 结婚证
    public static final int HOUSE_CERTIFICATE = 114;     // 房产证
    public static final int DERIVE_CARD = 115;         // 汽车行驶证
    public static final int SHOP_PHOTO = 116;//商家合影
    //界面需要刷新
    public static final String IS_MY_PAGE_NEED_REFRESH = "is_my_page_need_refresh";
    public static final String IMMEDIATELY_REPAY = "whole";
    public static final String INSTALMENTS_REPAY = "parts";
    public static final String IS_CAMERA_INITED = "is_camera_inited"; // 相机是否初始化了
    public static final String WX_APP_ID = "wx8f81903169fe60ea";//微信Key
    public static final String GOTO_LOGIN_ACTIVITY = "goto_login_activity";
    //	public static final String IS_CALL_RECORDS_SAVED_KEY = "is_call_records_saved_key";
    public static final String IS_CALL_CONTACTS_SAVED_KEY = "is_call_contacts_saved_key";
    public static final String IS_SCAN_ID_CARD_KEY = "is_scan_id_card_key";
    public static final String LAST_LOCATION_POSITION_KEY = "last_location_position_key";
    public static final String LAST_LOCATION_NAME_KEY = "last_location_name_key";
    public static final int MONEY_LEVER_1ST = 0; // 单位：分
    public static final int MONEY_LEVER_2ND = 20000; // 单位：分
    public static final int MONEY_LEVER_3RD = 50000; // 单位：分
    public static final int AMOUNT_TYPE_0 = 0; // 当前信用额度为0
    public static final int AMOUNT_TYPE_200 = 1; // 当前信用额度为200
    public static final int AMOUNT_TYPE_500 = 2; // 当前信用额度为500
    public static final int AMOUNT_TYPE_500_PLUS = 5; // 当前信用额度为>500
    //	public static final int MONEY_LEVER_4TH = 200000; // 单位：分
    public static final int CONSUME_TYPE_0 = 0; // 原消费额度+现消费额度-优惠额度=0
    public static final int CONSUME_TYPE_LESS_200 = 1; // 原消费额度+现消费额度-优惠额度<=200
    public static final int CONSUME_TYPE_LESS_500 = 2; // 原消费额度+现消费额度-优惠额度<=500
    public static final int CONSUME_TYPE_500_PLUS = 16; // 原消费额度+现消费额度-优惠额度>500
    public static final int UPLOADCALLCONTACTS = 1;//上传通讯录
    public static final int UPLOADCALLRECORD = 2;//上传通话记录
    public static final int UPLOADMESSAGE = 3;//上传短信记录
    public static final String WX_PAY_EXTDATA_KEY = "_wxapi_payresp_extdata";//微信回调传递的key，不可修改
    // 广播相关的key值
    // 通话记录没有获取到时，放行广播
    public static final String PASS_CALL_RECORD_ACTION = "qtq_pass_call_record_action";
    // 通讯录没有获取到时，放行广播
    public static final String PASS_CONTACT_ACTION = "qtq_pass_contact_action";

    public static final String VERIFY_FINISHED_ACTION = "qtq_verify_finished_action";  // 接收到审核完成的push时，放行广播
    //通知首页刷新
    public static final String REFRESH_HOME_PAGE_ACTION = "qtq_order_success_success_action";
    public static final String VERIFY_SUCCESS_ACTION = "qtq_verify_success_actioin";//审核成功，用于提高额度
    public static final String REMOVEBIND_SUCCESS_ACTION = "qtq_removebind_success_action";//解除绑定银行卡成功
    public static final String WECHAT_PAY_SUCCESS_ACTION = "qtq_wx_pay_success";//微信付款成功
    public static final String ACTIVE_SUCCESS_ACTION = "qtq_active_success";//激活成功
    public static final String BIND_CARD_SUCCESS_ACTION = "qtq_bind_card_success_action";//绑定银行卡成功
    public static final String LOGIN_SUCCESS_ACTION = "qtq_login_success_action";//登陆成功
    public static final String WITHDRAWALS_VERIFY_FINISHED_ACTION = "qtq_withdrawals_verify_finished_action";//提现审核完成广播
    public static final String WITHDRAWALS_ORDER_SUCCESS_ACTION = "qtq_withdrals_order_success_action";//提现下单完成
    public static final String REPAY_WITHDRAWAL_SUCCESS_ACTION = "qtq_repay_withdrawal_success_action";//现金贷还款
    public static final String LOT_OF_VERIFY_STATUE_ACTION = "qtq_lot_of_verify_status_action";//各种审核状态完成的推送
    public static final String ADD_BORROW_TERM_KEY_ACTION = "qtq_add_borrow_term_action";//增大借款期限的推送
    public static final String LOGOUT_ACTION = "qtq_logout_action";//退出账号
    public static final String IF_NEED_SCAN_FACE_KEY = "if_need_scan_face_key";
    public static final int SCAN_ID_CARD_TYPE = 200;
    public static final int SCAN_FACE_TYPE = 201;
    public static final int RETURN_FROM_ACTIVITY_ERROR = 202;   // 该数值不要轻易修改，有关联
    public static final int RETURN_FROM_ACTIVITY_BACK_KEY = 203;   // 该数值不要轻易修改，有关联
    public static final String REFUSE_TYPE_KEY = "refuse_type_key";
    public static final int REFUSE_BY_PERSON_TYPE = 1;
    public static final int REFUSE_BY_MACHINE_TYPE = 2;
    // 订单状态
    public static final String HAVE_BEEN_VERIFY = "1";//订单已完成
    public static final String WAIT_PAY_SHOUFU = "2";//待缴首付
    public static final String REPAY_MONEY = "3";//已还款
    public static final String NEXT_MONTH_TRANSFORM_STAGE = "4";//次月转分期
    public static final String WAIT_VERIFY = "5";//订单待审核
    public static final String REFUSE_BY_PERSON = "6";//审核未通过
    public static final String REFUSE_BY_MACHINE = "7";//决策执行失败
    public static final String WAIT_FOR_PAY = "8";//订单待付款
    // 现金贷审核状态
    public static final String CASH_APPLY_WAIT_VERIFY = "5"; // 现金贷申请待审核
    public static final String CASH_APPLY_REFUSE_BY_PERSON = "6"; // 现金贷申请人工审核未通过
    public static final String CASH_APPLY_REFUSE_BY_MACHINE = "7"; // 现金贷申请决策执行失败
    public static final String CASH_APPLY_HAVE_BEEN_VERIFY = "12"; // 现金贷申请已经完成
    public static final String BANK_PEY_TYPE = "1";//银行卡支付
    public static final String WECHAT_PAY_TYPE = "2";//微信支付
    public static final String NEXT_MONTH_REPAY = "1";
    public static final String FENQI_REPAY = "2";
    public static final String ENTER_DOWN_PAYMENT_PAGE_TYPE_KEY = "enter_down_payment_page_type_key"; // 进入付首付页面的方式
    public static final int ENTER_DOWN_PAYMENT_PAGE_FROM_SIGNIN = 1;  // 登录后进入
    public static final int ENTER_DOWN_PAYMENT_PAGE_FROM_PLACE_AN_ORDER = 2;  // 下单申请之后进入
    public static final int ENTER_DOWN_PAYMENT_PAGE_FROM_REFRESH_OR_PUSH = 3;  // 带审核页面刷新或者jpush
    public static final int ENTER_DOWN_PAYMENT_PAGE_FROM_CONSUM = 4;//付款订单界面进入
    public static final String SHOUFU_BROAD_ACTION = "qtq_wx_shoufu_success";//首付成功广播内容
    public static final String JPUSH_ID_KEY = "jpush_id_key"; // 存到sp中的jpushId的键值
    public static final String WX_REPAY_CONSUMPTION_SUCCESS_ACTION = "qtq_wx_repay_success";//微信还消费成功
    public static final String WX_REPAY_BORROW_SUCCESS_ACTION = "qtq_wx_repay_borrow_success_action";//微信还现金贷成功
    public static final String WX_REPAY_BORROW_DETAIL_SUCCESS_ACTION = "qtq_wx_repay_borrow_detail_success_action";//微信还现金贷详情成功
    public static final String WX_SHOUFU_SUCCESS = "wx_shoufu_success";//微信支付首付成功
    // bundle相关的key值
    public static final String CONSUME_ID_KEY = "consume_id_key"; // 消费id的key
    public static final String ORDER_BEAN_KEY = "order_bean_key"; // 订单bean的key
    public static final String ORDER_REFRESH_BEAN_KEY = "order_refresh_bean_key"; // 带审核订单刷新接口bean的key
    public static final String ID_CARD_BEAN_KEY = "id_card_bean_key"; // 身份证信息bean的key
    public static final String CONSUMEITEMBEAN_KEY = "consumeItemBean_key";//订单列表bean;
    public static final String IS_BIND_BANK_KEY = "is_bind_bank_key";//是否绑定银行卡
    public static final String CONSUMELISTBEAN_KEY = "consumelistBean_key";//消费记录的key
    public static final String VERIFY_FINISHED_KEY = "verify_finished_key";//审核完成的key
    public static final String SCAN_ID_CARD_KEY = "scan_id_card_key";
    public static final String SCAN_FACE_KEY = "scan_face_key";
    public static final String WITHDRAWALS_BEAN_KEY = "withdrawals_bean_key"; // 提现申请javabean的key
    // 提交申请的类型，APPLY_TYPE_IMPROVE:提额,APPLY_TYPE_INSTALLMENT:分期,
    // APPLY_TYPE_WITHDRAWALS:提现申请,APPLY_TYPE_GET_CASH:提取现金
    public static final String APPLY_TYPE_KEY = "apply_type_key";
    // 提现申请订单待审核时的id
    public static final String WITHDRAWALS_VERIFY_ID_KEY = "withdrawals_verify_id_key";

    public static final String WITHDRAWALS_VERIFY_FINISH_KEY = "withdrawals_verify_FINISH_key"; // 体现申请订单待审核完成传bean时的key
    public static final String WITHDRAWALS_BANK_NAME_KEY = "withdrawals_bank_name_key"; // 跳转到提现确认页传入的银行名称
    public static final String WITHDRAWALS_CARD_NUM_KEY = "withdrawals_card_num_key"; // 跳转到提现确认页传入的银行卡号
    public static final String WITHDRAWALS_CARD_GATE_KEY = "withdrawals_card_gate_key"; // 跳转到提现确认页传入的银行卡标识id
    public static final String WITHDRAWALS_REPAY_ID_KEY = "withdrawals_repay_id_key"; // 跳转到提现确认页传入的分期id
    public static final String WITHDRAWALS_REPAY_TIMES_KEY = "withdrawals_repay_times_key"; // 跳转到提现确认页传入的分期期数
    public static final String LOT_OF_VERIFY_STATUE_KEY = "lot_of_verify_status_key"; // 各种审核状态的推送的key
    public static final String ADD_BORROW_TERM_KEY = "add_borrow_term_key"; // 增大可借款期限的key
    public static final String WITHDRAWALS_REPAY_UNIT = "repay_unit";//分期类型，月 日
    public static final String CHANGE_INFO_TYPE_KEY = "change_info_type_key";//修改个人信息的key
    public static final String LOG_STATUS_NEED_UPLOAD = "log_status_need_upload";//需要上传log
    public static final String LOG_STATUS_IS_UPLOADING = "log_status_is_uploading";//正在上传log
    public static final String LOG_STATUS_IS_UPLOAD_fail = "log_status_upload_fail";//上传失败
    public static final String LOG_STATUS_NOT_NEED_UPLOAD = "log_status_not_need_upload";//不需要上传log
    public static final int CHANGE_TYPE_MOBILE = 1;//修改登录手机号码
    public static final int CHANGE_TYPE_USER_NAME = 2;//修改用户姓名
    public static final int CHANGE_TYPE_USER_ID_NUM = 3;//修改用户身份证号
    public static final int CHANGE_TYPE_WECHAT = 4;//修改用户微信号
    public static final int CHANGE_TYPE_ADDRESS = 5;//修改用户常住地址
    public static final int CHANGE_TYPE_COMPANY_NAME = 6;//修改用户单位名称
    public static final int CHANGE_TYPE_COMPANY_PHONE = 7;//修改用户单位电话
    public static final int CHANGE_TYPE_PARENT_NAME = 8;//修改用户父母姓名
    public static final int CHANGE_TYPE_PARENT_MOBILE = 9;//修改用户父母电话
    public static final int CHANGE_TYPE_PARENT_ADDRESS = 10;//修改用户父母住址
    public static final int CHANGE_TYPE_BROTHER_NAME = 11;//修改用户亲友姓名
    public static final int CHANGE_TYPE_BROTHER_MOBILE = 12;//修改用户亲友电话
    public static final int CHANGE_TYPE_COMPANY_ADDRESS = 13;//修改单位地址
    public static final int CHANGE_TYPE_QQ = 14;//修改qq号码
    public static final int CHANGE_TYPE_FRIEND_NAME = 15;//修改朋友姓名
    public static final int CHANGE_TYPE_FRIEND_MOBILE = 16;//修改朋友电话
    public static final int CHANGE_TYPE_COLLEAGUE_NAME = 17;//修改同事姓名
    public static final int CHANGE_TYPE_COLLEAGUE_MOBILE = 18;//修改同事电话
    public static final int CHANGE_RESULT_OK = 1;//已修改
    public static final String CHANGE_INFO_KEY = "change_type_info_key";//修改信息返回的key值
    public static final String CHANGE_INTO_INFO_KEY = "change_into_info_key";//修改信息传入的值
    public static final String CARD_NUM_KEY = "card_num_key";//银行卡号的key
    public static final String CARD_USER_NAME = "card_user_name";//持卡人姓名的key
    public static final String REPAY_FROM_KEY = "repay_from_key";//还款界面来源
    //消费还款
    public static final String REPAY_FROM_CONSUMPTION = "repay_from_consumption";
    public static final String REPAY_FROM_BORROW = "repay_from_borrow";//现金贷还款
    public static final String REPAY_FAILED = "1111";//现金贷还款失败

    public static final String REPAY_FROM_SHOUFU = "repay_from_shoufu";//首付付款
    public static final String REPAY_BEAN_KEY = "repay_bean_key";//还款界面进入的类
    //提现详情页进入还款页面时带入提现详情的所有数据的key
    public static final String REPAY_BEAN_TOTAL_DATA_KEY = "repay_bean_total_data_key";
    //从提现账单详情进入
    public static final String REPAY_FROM_BORROW_DETAIL = "repay_from_borrow_detail";
    //用户申请续借
    public static final String RENEW_FROM_BORROW_DETAIL = "renew_from_borrow_detail";

    public static final String REPAY_FROM_CONSUME_DETAIL = "repay_from_consume_detail";//从消费账单详情进入
    public static boolean isNotice = true;//是否提醒用户
    // 字符串常量
    public static String BILL_LOAD_LENGTH = "10";
    public static String CONSUMPTIONRECORD_LOAD_LENGTH = "10";
    public static String LOG_FILE = "waterDriver/log.txt";//log路径

    public static String getSlot() {
        return "secret@huoyan.com";
    }

}
