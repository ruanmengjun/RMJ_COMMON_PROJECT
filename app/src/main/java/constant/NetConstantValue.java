package constant;

/**
 * @date 2016/6/14
 */
public class NetConstantValue {
    private static String COMMONURI = "http://apitest.huoyanzichan.com/Home/";

    public static  String getZQH(){
        return "http://m.feibaodai.com.cn/fBExtend/?chlNo=zhaoqianhua&linkNo=10390#";
//        return "http://testapi.huoyanzichan.com/web/index.html";
        //return "https://tianji.rong360.com/tianjiwapreport/login?data=nD%2BRaNTUBXKJrM1QPpIetNJ5z854uzjscIdvYpkqIfsLoMlcSDNvOb6sJyqn4rJvn7bLWxKy2%2BoNsCCScAmgnHQOQlzMijv%2BfY1ISwrA%2FP7vv52GnsfrjdDAvDlePwOCRW%2BqaJaXPwKZJ%2FiI7e4Z0ReSjEEgP7UBX0oGHBaOvUlOY6gp8zTCUKiFAcm%2FO1ra%2BmXQWZ%2FlME9glwb7LKEbgeL8LinMPxHQjVq2Bb4ERQY%3D";
    }
    /**
     * 获取用户芝麻分认证
     */
    public static String getZhimaUrl() {
        return COMMONURI + "Zm/getUserData";
    }
    /**
     * 获取省市县三级联动数据
     */
    public static String getProvinceData() {
        return COMMONURI + "Customer/getProvinceData";
    }

    /**
     * 获取用户手机安装软件列表
     */
    public static String getUserAppList() {
        return COMMONURI + "Customer/getUserAppList";
    }

    /**
     * 获取易宝验证码
     */
    public static String getYbVerifySms() {
        return COMMONURI + "Pay/yibaoBindVerifySms";
    }

    /**
     * 获取银行卡名称
     */
    public static String getBankName() {
        return COMMONURI + "MerchantBankInfo/getBankInfo";
    }

    /**
     * 绑定易宝银行卡
     */
    public static String getYibaoSendBank() {
        return COMMONURI + "Pay/yibaoSendBank";
    }

    /**
     * 获取支付宝和微信付款h5
     */
    public static String getALiPayWeChat() {
        return "http://mp.weixin.qq.com/s/Y4jb90tM8HWarTvUMem8Mg";
    }
    /**
     * 查询用户信息是否过期
     */
    public static String chectUserInfo() {
        return COMMONURI + "Order/customerFewMsg";
    }

    /**
     * 用户登录
     */
    public static String getSignInUrl() {
        return COMMONURI + "UserCenter/signIn";
    }

    public static String getUserLoginPro() {
        //用户注册协议
        return COMMONURI + "Protocol/customerSignUp";
    }

    /**
     * 用户申请续借协议
     */
    public static String getRenewPro() {
        return COMMONURI + "Protocol/order";
    }

    /**
     * //用户服务协议的地址
     */
    public static String getServerURL() {
        return COMMONURI + "Protocol/borrowProtocol";
    }

    public static boolean getService() {
        if (COMMONURI.equals("http://api.huoyanzichan.com/Home/")) {
            return true;
        }
        if (COMMONURI.equals("http://dev.qtqapi.9maibei.com/Home/")) {
            return false;
        }
        return false;
    }

    public static String getRedPackageUrl() {
        return COMMONURI + "Webapp/activity";
    }

    /**
     * 获取申请续借
     */
    public static String getRenewUrl() {
        return COMMONURI + "Renew/RenewByUser";
    }

    /**
     * 续借支付
     */
    public static String getRenewPayUrl() {
        return COMMONURI + "Pay/NewpayConfirm";
    }

    /**
     * 获取验证码
     */
    public static String getVerifyCodeUrl() {
        return COMMONURI + "Customer/getVerifyCode";
    }

    /**
     * 设置支付密码
     */
    public static String getSetPayPassUrl() {
        return COMMONURI + "Customer/setPayPass";
    }

    /**
     * 验证支付密码
     */
    public static String getVerifyPayPassUrl() {
        return COMMONURI + "Customer/verifyPayPass";
    }

    /**
     * 保存用户身份证信息
     */
    public static String getSaveIdCardInformationUrl() {
        return COMMONURI + "Customer/saveIdCardInformation";
    }

    /**
     * 保存用户信息
     */
    public static String getSaveInformationUrl() {
        return COMMONURI + "Customer/saveInformation";
    }

    /**
     * 获取已经绑定的银行卡列表
     */
    public static String getBankListUrl() {
        return COMMONURI + "UserCenter/getBankList";
    }

    /**
     * 批量保存联系人
     */
    public static String getSaveContactsUrl() {
        return COMMONURI + "System/saveContacts";
    }

    /**
     * 批量保存通话记录 (支持增量，即只传上次上行数据之后的通话记录)
     */
    public static String getSaveCallRecordUrl() {
        return COMMONURI + "System/saveCallRecord";
    }

    /**
     * 批量保存短信
     */
    public static String getSaveSMS() {
        return COMMONURI + "System/saveSMS";
    }

    /**
     * 取上一次保存通话记录的时间，下次增量上传通话记录的开始时间
     */
    public static String getLastSaveCallRecordTimeUrl() {
        return COMMONURI + "System/getLastSaveCallRecordTime";
    }

    /**
     * 我的(我的主页)
     */
    public static String getMineUrl() {
        return COMMONURI + "UserCenter/mine";
    }

    /**
     * 还款，立即还款
     */
    public static String getRepaymentUrl() {
        return COMMONURI + "Pay/payConfirm";
    }

    /**
     * 检查版本更新
     */
    public static String GetCheckUpgradeUrl() {
        return COMMONURI + "System/checkUpgrade";
    }

    /**
     * 图片上传接口
     */
    public static String GetUploadImageUrl() {
        return COMMONURI + "UserCenter/uploadImage";
    }

    /**
     * 重新设置密码
     */
    public static String getResetPasswordUrl() {
        return COMMONURI + "Customer/resetPassword";
    }

    /**
     * 获取打开权限提示语
     */
    public static String getOpenPermissionHintUrl() {
        return COMMONURI + "Customer/getOpenPermissionHint";
    }

    /**
     * 退出登录
     */
    public static String getLogoutUrl() {
        return COMMONURI + "UserCenter/logout";
    }

    /**
     * 绑定银行卡时获取验证码
     */
    public static String getBindVerifySmsUrl() {
        return COMMONURI + "Pay/bindVerifySms";
    }

    /**
     * 绑定银行卡
     */
    public static String getBindConfirmUrl() {
        return COMMONURI + "Pay/bindConfirm";
    }

    /**
     * 更换银行卡主副卡
     */
    public static String getChangeCardUrl() {
        return COMMONURI + "Pay/changeMainCard";
    }

    /**
     * 黑名单
     */
    public static String getBlacklist() {
        return COMMONURI + "blacklist/getData";
    }

    /**
     * 上传点击导流图
     */
    public static String postPopularize() {
        return COMMONURI + "";
    }

    /**
     * 获取导流图数据
     */
    public static String getPopularize() {
        return COMMONURI + "";
    }

    /**
     * 获取用户订单数据
     */
    public static String getConsumeInfoById() {
        return COMMONURI + "Renew/GetConsumeInfoById";
    }

    /**
     * 获取可绑定卡的银行列表
     */
    public static String GetAllBankListUrl() {
        return COMMONURI + "UserCenter/getAllBankList";
    }

    /**
     * 解绑银行卡
     */
    public static String GetUnbindBankCardUrl() {
        return COMMONURI + "Pay/unbindBankCard";
    }

    /**
     * 用户注册
     */
    public static String getSignUpUrl() {
        return COMMONURI + "UserCenter/signUp";
    }

    /**
     * 活体检测成功通知
     */
    public static String getCreditFaceUrl() {
        return COMMONURI + "Credit/CreditFace";
    }

    /*
    * 微信支付下单
    * */
    public static String getWeChatOrderURl() {
        return COMMONURI + "Pay/wxpayConfirm";
    }

    /*
    * 获取用户当前认证信息
    * */
    public static String getCustomerAuthURl() {
        return COMMONURI + "Credit/getCustomerAuth";
    }

    /*
    * 获取提升额度界面显示列表状态
    * */
    public static String getAuthListURL() {
        return COMMONURI + "Credit/getAuthList";
    }


    /*
     * 上传联系人信息
    * */
    public static String getUploadContactsInfoURL() {
        return COMMONURI + "Credit/uploadContactsInfo";
    }

    /*
    * 获取联系人信息
    * */
    public static String getGetContactsInfoURL() {
        return COMMONURI + "Credit/getContactsInfo";
    }

    /*
    * 修改联系人信息
    * */
    public static String getChangeContactsInfoURL() {
        return COMMONURI + "Credit/changeContactsInfo";
    }

    /*
    * 获取提现账单
    * */
    public static String getGetWithdrawalsBillURL() {
        return COMMONURI + "UserCenter/getWithdrawalsBill";
    }

    /*
    * 提现账单还款
    * */
    public static String getRepayWithdrawalsURL() {
        return COMMONURI + "UserCenter/repayWithdrawals";
    }

    /*
    * 提现产品选择
    * */
    public static String getSelWithdrawalsURL() {
        return COMMONURI + "WithdrawalsOrder/selWithdrawals";
    }

    /*
    * 申请额度
    * */
    public static String getWithdrawalsApplyURL() {
        return COMMONURI + "WithdrawalsOrder/withdrawalsApply";
    }

    /*
    * 提现待审核刷新
    * */
    public static String getWithdrawalsRefreshURL() {
        return COMMONURI + "WithdrawalsOrder/withdrawalsRefresh";
    }

    /*
    * 现金贷提现下单（申请提现）
    * */
    public static String getWithdrawalsOrderURL() {
        return COMMONURI + "WithdrawalsOrder/withdrawalsOrder";
    }

    /*
    * 获取提现账单详情
    * */
    public static String getWithdrawalsBillInfoURL() {
        return COMMONURI + "UserCenter/GetWithdrawalsBillInfo";
    }

    /*
    * 获取取现记录
    * */
    public static String getWithdrawalsRecordURL() {
        return COMMONURI + "UserCenter/getWithdrawalsRecord";
    }

    /*
    * 现金贷审核通过的确认
    * */
    public static String getCashVerifyConfirmURL() {
        return COMMONURI + "UserCenter/cashVerifyConfirm";
    }

    /*
    * 日志上传接口
    * */
    public static String getUploadLogUrl() {
        return COMMONURI + "UserCenter/uploadUserLog";
    }

    /*
    * 运营商已重复认证
    * 分期消费等待审核界面使用
    *
    * */
    public static String getAgainUrl() {
        return COMMONURI + "Credit/creditMobile";
    }

    public static String getStatisticsRollUrl() {
        return COMMONURI + "UserCenter/getDemographic";
    }

    /**
     * 决策拒绝通知后台
     */
    public static String getRecordPopupTimeUrl() {
        return COMMONURI + "Customer/RecordPopupTimes";
    }
}
