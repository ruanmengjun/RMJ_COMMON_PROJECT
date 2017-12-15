package service.entity;

import java.io.Serializable;

/**
 * @date 16/6/28
 */

public class SignInBean extends ResponseBean {
    private SignInDataBean data; // 商户id

    public SignInBean() {
        data = new SignInDataBean();
    }

    public SignInDataBean getData() {
        return data;
    }

    public void setData(SignInDataBean data) {
        this.data = data;
    }

    public class SignInDataBean implements Serializable {
        private String engerce_msg;//信息
        private String max_num;//控制7天产品 1显示0隐藏
        private Customer customer; // 用户数据
        private Credit credit; // 信审数据
        private CashCredit cash_credit; //现金贷审核数据

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }
        public String getMax_num() {
            return max_num;
        }

        public void setMax_num(String max_num) {
            this.max_num = max_num;
        }

        public String getEngerce_msg() {
            return engerce_msg;
        }

        public void setEngerce_msg(String engerce_msg) {
            this.engerce_msg = engerce_msg;
        }

        public Credit getCredit() {
            return credit;
        }

        public void setCredit(Credit credit) {
            this.credit = credit;
        }

        public CashCredit getCash_credit() {
            return cash_credit;
        }

        public void setCash_credit(CashCredit cash_credit) {
            this.cash_credit = cash_credit;
        }

        public class Customer implements Serializable {
            private static final long serialVersionUID = 1L;
            private String id;  // 注册成功后产生的用户ID
            private String device_id;  // 设备ID
            private String real_name;  // 姓名
            private String mobile;   // 手机号
            private String id_num;   // 身份证号
            private String isset_pay_pass;   // 是否设置密码0:未设置,1:已设置
            private String amount;   // 当前总的信用额度，单位分
            private String balance_amount;   // 当前剩余信用额度, 单位分
            private String max_amount;   // 最高可提升的额度, 单位分
            private String credit_step; // 认证阶梯：0-未认证 1-已身份认证 2-已face++认证
            private String create_time;   // 创建时间 Unix 时间戳
            private String update_time;   // 更新时间 Unix 时间戳
            private String cash_amount; // 当前总的现金贷信用额度，单位分
            private String balance_cash_amount; // 当前剩余现金贷信用额度, 单位分
            private String face_pass_score; // face++通过的分数
            private String withdraw; // 当前用户有几笔未还借款

            public String getWithdraw() {
                return withdraw;
            }

            public void setWithdraw(String withdraw) {
                this.withdraw = withdraw;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDevice_id() {
                return device_id;
            }

            public void setDevice_id(String device_id) {
                this.device_id = device_id;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getId_num() {
                return id_num;
            }

            public void setId_num(String id_num) {
                this.id_num = id_num;
            }

            public String getIsset_pay_pass() {
                return isset_pay_pass;
            }

            public void setIsset_pay_pass(String isset_pay_pass) {
                this.isset_pay_pass = isset_pay_pass;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getBalance_amount() {
                return balance_amount;
            }

            public void setBalance_amount(String balance_amount) {
                this.balance_amount = balance_amount;
            }

            public String getMax_amount() {
                return max_amount;
            }

            public void setMax_amount(String max_amount) {
                this.max_amount = max_amount;
            }

            public String getCredit_step() {
                return credit_step;
            }

            public void setCredit_step(String credit_step) {
                this.credit_step = credit_step;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getCash_amount() {
                return cash_amount;
            }

            public void setCash_amount(String cash_amount) {
                this.cash_amount = cash_amount;
            }

            public String getBalance_cash_amount() {
                return balance_cash_amount;
            }

            public void setBalance_cash_amount(String balance_cash_amount) {
                this.balance_cash_amount = balance_cash_amount;
            }

            public String getFace_pass_score() {
                return face_pass_score;
            }

            public void setFace_pass_score(String face_pass_score) {
                this.face_pass_score = face_pass_score;
            }
        }

        public class Credit implements Serializable {
            private static final long serialVersionUID = 1L;
            // 当前信审状态 1：已审核，2：待审核，3：人工拒绝，4：决策拒绝",
            private String status;
            private String notice_status;//"通知状态 0-不需要通知 1-未通知 2-已通知"
            //(如果status状态为 3-人工拒绝 或 4-决策拒绝，则有下面的待审核详情信息)
            private String reason;//拒绝原因
            private String valuation;//估值
            //  (如果status状态为2-待审核，则有下面的待审核详情信息),
            private String amount; // 实付金额/申请提升额度",
            private String add_time; // 消费时间/申请日期 年-月-日 时:分:秒",
            private String type; // 消费类型 1：次月还款 2：分期还款 3：提升额度",

            // (如果type类型不为3-提升额度，则有如下的信息)
            private String merchant_id; // 商户id",
            private String merchant_name; // 商户名称",
            private String consume_amount; // 消费金额",
            private String reduce_amount; // 优惠金额（商家优惠金额+平台优惠金额）",
            private String overdue_amount; // 逾期金额",
            private String repayment_time; //还款时间 年-月-日"

            private String down_payment; // 首付款
            private String bind_card; // 是否绑定银行卡：0-未绑定 1-已绑定
            private String card_num; // 银行卡号
            private String bank_name; // 银行名称
            private String consume_id; // 消费id

            public String getNotice_status() {
                return notice_status;
            }

            public void setNotice_status(String notice_status) {
                this.notice_status = notice_status;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getValuation() {
                return valuation;
            }

            public void setValuation(String valuation) {
                this.valuation = valuation;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getMerchant_id() {
                return merchant_id;
            }

            public void setMerchant_id(String merchant_id) {
                this.merchant_id = merchant_id;
            }

            public String getMerchant_name() {
                return merchant_name;
            }

            public void setMerchant_name(String merchant_name) {
                this.merchant_name = merchant_name;
            }

            public String getConsume_amount() {
                return consume_amount;
            }

            public void setConsume_amount(String consume_amount) {
                this.consume_amount = consume_amount;
            }

            public String getReduce_amount() {
                return reduce_amount;
            }

            public void setReduce_amount(String reduce_amount) {
                this.reduce_amount = reduce_amount;
            }

            public String getOverdue_amount() {
                return overdue_amount;
            }

            public void setOverdue_amount(String overdue_amount) {
                this.overdue_amount = overdue_amount;
            }

            public String getRepayment_time() {
                return repayment_time;
            }

            public void setRepayment_time(String repayment_time) {
                this.repayment_time = repayment_time;
            }

            public String getDown_payment() {
                return down_payment;
            }

            public void setDown_payment(String down_payment) {
                this.down_payment = down_payment;
            }

            public String getBind_card() {
                return bind_card;
            }

            public void setBind_card(String bind_card) {
                this.bind_card = bind_card;
            }

            public String getCard_num() {
                return card_num;
            }

            public void setCard_num(String card_num) {
                this.card_num = card_num;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getConsume_id() {
                return consume_id;
            }

            public void setConsume_id(String consume_id) {
                this.consume_id = consume_id;
            }
        }

        public class CashCredit implements Serializable {
            private static final long serialVersionUID = 1L;
            // <如果 status=12, 正常处理>
            String need_pop; // 需要弹框：0-不需要 1-需要
            String already_nums;
            String hope_nums;

            // 1:订单已完成，2:待缴首付, 3:已还款，4:次月转分期，5:市场待审核，6:审核未通过，
            // 7:决策执行失败，8:订单待付款，9:订单已取消，10:信审待审核,11自动拒绝,
            // 12提现人工审核通过 13提现失败
            private String status;
            // <如果 status=5>
            private String consume_id; // 提现申请订单id
            // <如果 status=6 或 status=7,>
            private String reason; // 失败原因

            private String day_remaining;//未逾期，剩余还款天数
            private String overdue;//0未逾期1已逾期
            private String final_day;//天数

            private String withdrawal_amount; // 提现金额单位分
            private String repay_total; // 每期还款数，单位分
            private String transfer_amount;//到账金额,单位分
            private String fee_amount;//手续费
            private String timer;//天
            private String repay_id;//
            private String repay_unit;//天
            private String repay_times;//
            private String allow_renew;

            public String getAllow_renew() {
                return allow_renew;
            }

            public void setAllow_renew(String allow_renew) {
                this.allow_renew = allow_renew;
            }

            public String getRepay_unit() {
                return repay_unit;
            }

            public void setRepay_unit(String repay_unit) {
                this.repay_unit = repay_unit;
            }

            public String getRepay_times() {
                return repay_times;
            }

            public void setRepay_times(String repay_times) {
                this.repay_times = repay_times;
            }

            public String getRepay_id() {
                return repay_id;
            }

            public void setRepay_id(String repay_id) {
                this.repay_id = repay_id;
            }

            public String getWithdrawal_amount() {
                return withdrawal_amount;
            }

            public void setWithdrawal_amount(String withdrawal_amount) {
                this.withdrawal_amount = withdrawal_amount;
            }

            public String getRepay_total() {
                return repay_total;
            }

            public void setRepay_total(String repay_total) {
                this.repay_total = repay_total;
            }

            public String getTransfer_amount() {
                return transfer_amount;
            }

            public void setTransfer_amount(String transfer_amount) {
                this.transfer_amount = transfer_amount;
            }

            public String getFee_amount() {
                return fee_amount;
            }

            public void setFee_amount(String fee_amount) {
                this.fee_amount = fee_amount;
            }

            public String getTimer() {
                return timer;
            }

            public void setTimer(String timer) {
                this.timer = timer;
            }

            public String getDay_remaining() {
                return day_remaining;
            }

            public void setDay_remaining(String day_remaining) {
                this.day_remaining = day_remaining;
            }

            public String getOverdue() {
                return overdue;
            }

            public void setOverdue(String overdue) {
                this.overdue = overdue;
            }

            public String getFinal_day() {
                return final_day;
            }

            public void setFinal_day(String final_day) {
                this.final_day = final_day;
            }

            public String getAlready_nums() {
                return already_nums;
            }

            public void setAlready_nums(String already_nums) {
                this.already_nums = already_nums;
            }

            public String getHope_nums() {
                return hope_nums;
            }

            public void setHope_nums(String hope_nums) {
                this.hope_nums = hope_nums;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getNeed_pop() {
                return need_pop;
            }

            public void setNeed_pop(String need_pop) {
                this.need_pop = need_pop;
            }

            public String getConsume_id() {
                return consume_id;
            }

            public void setConsume_id(String consume_id) {
                this.consume_id = consume_id;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

        }
    }
}
