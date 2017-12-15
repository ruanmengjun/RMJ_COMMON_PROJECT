package utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import base.BaseActivity;
import rmj.example.com.rmj_common_project.R;

/**
 * 公共标题栏
 */

public class CommonTitleBar implements View.OnClickListener{

    private Context mContext;
    private ImageView imgLeft;
    private ImageView imgRight;
    private TextView title;

    public CommonTitleBar(BaseActivity context) {
        mContext=context;
        imgLeft=(ImageView) context.findViewById(R.id.img_left);
        imgRight=(ImageView) context.findViewById(R.id.img_right);
        title=(TextView)context.findViewById(R.id.title);
        imgLeft.setOnClickListener(this);
        imgRight.setOnClickListener(this);
    }

    public void setTitle(String titleName){
        title.setText(titleName);
    }

    public void hideLeft(boolean flag){
        imgLeft.setVisibility(View.GONE);
    }

    public void hideRight(boolean flag){
        imgRight.setVisibility(View.GONE);
    }

    public void hideBoth(boolean flag){
        imgLeft.setVisibility(View.GONE);
        imgRight.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_left:
                //Toast.makeText(mContext,"返回",Toast.LENGTH_LONG).show();
                Activity activity = (Activity) mContext;
                activity.finish();
                activity.overridePendingTransition(R.anim.not_exit_push_left_in, R.anim.push_right_out);
                break;
            case R.id.img_right:

                break;
        }
    }

}
