package ui.fragment;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.BitmapUtils;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rmj.example.com.rmj_common_project.R;
import utils.ImageUtil;

/**
 * Created by admin on 2017/11/17.
 */

public class AddQRFragment extends BaseFragment {
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.img1)
    ImageView img1;
    Unbinder unbinder;
    private Bitmap bitmap = null;
    private Dialog dialog;

    @Override
    protected int initLayoutId() {
        return R.layout.add_qr_fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        dialog = new Dialog(getContext(), R.style.Translucent_NoTitle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        img1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog();
                return false;
            }
        });

        return rootView;


    }

    @OnClick(R.id.btn1)
    public void click(View v) {
        //img1.setImageBitmap(QRCodeUtil.generateBitmap(et1.getText().toString().trim(), img1));//第一种方法
        try {
            bitmap = BitmapUtils.create2DCode(et1.getText().toString().trim());//根据内容生成二维码
            img1.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showDialog() {
        final LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.qr_dialog, null);
        dialog.setContentView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);

        TextView save=(TextView)v.findViewById(R.id.save);
        TextView verify=(TextView)v.findViewById(R.id.verify);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageUtil.saveImageToPhotos(getContext(), bitmap);
            }
        });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyQRCode(img1);
            }
        });


        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.onWindowAttributesChanged(wl);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                return keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0;
//            }
//        });
        dialog.show();
        //dialog.setCancelable(false);
    }

    public void verifyQRCode(ImageView imageView){
        Bitmap obmp = ((BitmapDrawable) (imageView).getDrawable()).getBitmap();
        int width = obmp.getWidth();
        int height = obmp.getHeight();
        int[] data = new int[width * height];
        obmp.getPixels(data, 0, width, 0, 0, width, height);
        RGBLuminanceSource source = new RGBLuminanceSource(width, height, data);
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result re = null;
        try {
            re = reader.decode(bitmap1);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (ChecksumException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
        if (re == null) {
            Toast.makeText(getContext(),"未发现二维码",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(),re.getText(),Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
