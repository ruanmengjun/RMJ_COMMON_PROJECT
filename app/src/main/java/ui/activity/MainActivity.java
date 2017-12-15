package ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import base.BaseActivity;
import butterknife.OnClick;
import rmj.example.com.rmj_common_project.R;
import service.entity.Book;
import service.entity.SignInBean;
import service.presenter.BookPresenter;
import service.presenter.LoginPresenter;
import service.view.BookView;
import service.view.SignInView;
import utils.CommonTitleBar;
import utils.KeyboardUtil;

public class MainActivity extends BaseActivity {

    private CommonTitleBar titleBar;
    private BookPresenter mBookPresenter;
    private LoginPresenter loginPresenter ;
    private Dialog dialog;
    private KeyboardUtil key;
    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //初始化控件布局等等
        titleBar = new CommonTitleBar(this);
        titleBar.setTitle("首页");
        titleBar.hideRight(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBookPresenter = new BookPresenter(getApplicationContext());
        loginPresenter = new LoginPresenter(getApplicationContext());
        mBookPresenter.onCreate();
        mBookPresenter.attachView(mBookView);

        loginPresenter.onCreate();
        loginPresenter.attachView(signInView);
        dialog = new Dialog(this, R.style.Translucent_NoTitle);
    }

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9})
    public void click(View v) {
        switch (v.getId()){
            case R.id.btn1:
                startActivity(new Intent(MainActivity.this,QRCodeActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(MainActivity.this,IdentificationActivity.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(MainActivity.this,GlidePicActivity.class));
                break;
            case R.id.btn5:
                startActivity(new Intent(MainActivity.this,NewsActivity.class));
                break;
            case R.id.btn6:
                startActivity(new Intent(MainActivity.this,SetActivity.class));
                break;
            case R.id.btn7:
//                SnackbarUtil.LongSnackbar(v,"尊敬的找钱花客户您好，近期网络上存在关于找钱花平台及所在行业的不实言论。找钱花平台郑重承诺，我们对新老用户都正常提供服务，认真对待每一位客户，提供优质的服务。生活偶有不时之需，我们助您一臂之力，我们始终在这里！!",SnackbarUtil.Warning).setAction("查看", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(mContext, "傻缺，你还真点啊", Toast.LENGTH_SHORT).show();
//                    }
//                }).show();
                Intent intent=new Intent(MainActivity.this,MusicActivity.class);
                //startActivity(intent);
                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this);
                ActivityCompat.startActivity(MainActivity.this, intent, activityOptions.toBundle());
                //this.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
               // startActivity(new Intent(MainActivity.this,MusicActivity.class));
                break;
            case R.id.btn8:
                startActivity(new Intent(MainActivity.this,MyVideoActivity.class));
                break;
            case R.id.btn9:
                startActivity(new Intent(MainActivity.this,TestServiceActivity.class));
                break;
        }

    }

    private BookView mBookView = new BookView() {
        @Override
        public void onSuccess(Book mBook) {
            //text2.setText(mBook.toString());
        }

        @Override
        public void onError(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    };


    private SignInView signInView = new SignInView() {
        @Override
        public void onSuccess(SignInBean mBook) {
            //text2.setText(mBook.getData().getCustomer().getReal_name());
        }

        @Override
        public void onError(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    };

    //测试自定义键盘
    private void showDialog() {
        final LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View v = inflater.inflate(R.layout.input_pay_pwd, null);
        dialog.setContentView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        KeyboardView keyboard_view = (KeyboardView) v.findViewById(R.id.keyboard_view);
        TextView back = (TextView) v.findViewById(R.id.back);
        keyboard_view.setFocusable(true);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.layout_input);
        RelativeLayout forgetLayout = (RelativeLayout) v.findViewById(R.id.forget_pwd);
        forgetLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        key = new KeyboardUtil(keyboard_view, MainActivity.this, layout, new KeyboardUtil.InputFinishListener() {
            @Override
            public void inputHasOver(String text) {
                if (text.length() == 6) {
                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_LONG).show();
                    sub();
                }
            }
        });
        key.showKeyboard();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.onWindowAttributesChanged(wl);
        dialog.show();
    }

    public void sub(){
        key.clear();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBookPresenter.onStop();
        loginPresenter.onStop();
    }
}
