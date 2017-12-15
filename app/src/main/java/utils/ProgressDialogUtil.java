package utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import rmj.example.com.rmj_common_project.R;

/**
 * Created by admin on 2017/11/3.
 */

public class ProgressDialogUtil {
    private static Dialog dialog;

    public static void cancelDialog(){
        if (dialog!=null&&dialog.isShowing()){
            dialog.cancel();
        }
    }

    public static void showDialog(Activity act,String msg) {
        dialog = new Dialog(act, R.style.Translucent_NoTitle);
        final LayoutInflater inflater = LayoutInflater.from(act);
        View v = inflater.inflate(R.layout.progress_dialog, null);
        dialog.setContentView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);

        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                act, R.anim.loading_animation);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);

        dialog.setCancelable(false);


        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.onWindowAttributesChanged(wl);

        dialog.show();
    }
}
