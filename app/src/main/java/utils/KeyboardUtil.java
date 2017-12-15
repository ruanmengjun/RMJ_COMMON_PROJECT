package utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rmj.example.com.rmj_common_project.R;

import static android.inputmethodservice.Keyboard.KEYCODE_MODE_CHANGE;

public class KeyboardUtil {
    private Context ctx;
    private KeyboardView keyboardView;
    private Keyboard k2;// 数字键盘
    protected Typeface iconfont;
    private List<String> pwdList = new ArrayList<String>();

    InputFinishListener inputOver;
    TextView[] textViews = new TextView[6];
    LinearLayout layout_parent;

    public KeyboardUtil(KeyboardView keyboardView, Context ctx, LinearLayout layout_parent, InputFinishListener inputOver) {
        this.ctx = ctx;
        this.layout_parent = layout_parent;
        initTextViews();
        iconfont = Typeface.createFromAsset(ctx.getAssets(), "iconfont/iconfont.ttf");
        k2 = new Keyboard(ctx, R.xml.number);
        //keyboardView = (KeyboardView) ctx.findViewById(R.id.keyboard_view);
        this.keyboardView = keyboardView;
        keyboardView.setEnabled(true);
        keyboardView.setKeyboard(k2);
        keyboardView.setPreviewEnabled(true);
        keyboardView.setOnKeyboardActionListener(listener);

        this.inputOver = inputOver;

        List<Key> keys = keyboardView.getKeyboard().getKeys();
        for (Key key : keys) {
            if (key.codes[0] == KEYCODE_MODE_CHANGE || key.codes[0] == Keyboard.KEYCODE_DELETE) {
                key.onPressed();
            }
        }
    }

    /**
     * @description:初始化输入框
     * @user wzl email:wzl200711402@163.com
     * @date 2014-3-19
     */
    private void initTextViews() {
        for (int i = 0; i < textViews.length; i++) {
            textViews[i] = new TextView(ctx);
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            params.weight = 1;
            textViews[i].setLayoutParams(params);
            layout_parent.addView(textViews[i]);
            //textViews[i].setTransformationMethod(PasswordTransformationMethod.getInstance());
            textViews[i].setGravity(Gravity.CENTER);
            textViews[i].setTextSize(30);
            textViews[i].setTextColor(Color.BLACK);

            if (i < textViews.length - 1) {
                View view = new View(ctx);
                LayoutParams viewParams = new LayoutParams((int) ctx.getResources().getDimension(R.dimen.width_input_tv_cutline), LayoutParams.FILL_PARENT);
                view.setLayoutParams(viewParams);
                view.setBackgroundColor(Color.parseColor("#DCE4EC"));
                layout_parent.addView(view);
            }

        }
    }

    private OnKeyboardActionListener listener = new OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            if (primaryCode == Keyboard.KEYCODE_DELETE) {
                deleteTextView();
                //inputOver.inputHasOver(getInputText());
            } else if (primaryCode != KEYCODE_MODE_CHANGE) {
                pwdList.add(Character.toString((char) primaryCode));
                inputTextView(Character.toString((char) primaryCode));
            }
        }
    };

    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            keyboardView.setVisibility(View.INVISIBLE);
        }
    }

    public void clear(){
        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setText("");
            pwdList.clear();
        }
    }

    //获取数据
    private void inputTextView(String code) {
        for (int i = 0; i < textViews.length; i++) {
            TextView tv = textViews[i];
            String pwd = tv.getText().toString();
            if (pwd.equals("")) {
                //textViews[i].setText(R.string.circle);
                textViews[i].setTypeface(iconfont);
                if (i == textViews.length - 1) {
                    inputOver.inputHasOver(pwdList.get(0) + pwdList.get(1) + pwdList.get(2) + pwdList.get(3) + pwdList.get(4) + pwdList.get(5));
                }
                return;
            }
        }
    }

    private void deleteTextView() {
        for (int i = textViews.length - 1; i >= 0; i--) {
            TextView tv = textViews[i];
            if (!tv.getText().toString().equals("")) {
                tv.setText("");
                pwdList.remove(i);
                return;
            }
        }
    }

    private String getInputText() {
        StringBuffer sb = new StringBuffer();
        for (TextView tv : textViews) {
            sb.append(tv.getText().toString());
        }
        return sb.toString();
    }

    /**
     * @description:TODO 输入监听
     * @FileName KeyboardUtil.java
     * @user wzl email:wzl200711402@163.com
     * @date 2014-3-19
     */
    public interface InputFinishListener {
        void inputHasOver(String text);
    }


}