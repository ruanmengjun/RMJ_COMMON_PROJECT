package ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.megvii.idcardlib.IDCardScanActivity;
import com.megvii.idcardlib.util.Util;
import com.megvii.idcardquality.IDCardQualityLicenseManager;
import com.megvii.licensemanager.Manager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;
import rmj.example.com.rmj_common_project.R;
import service.entity.IDCardBean;
import service.presenter.IDCardInfoPresenter;
import service.view.IDCardInfoView;
import utils.CommonTitleBar;
import utils.RetrofitManager;

import static android.os.Build.VERSION_CODES.M;
import static utils.RetrofitManager.parseImageRequestBody;
import static utils.RetrofitManager.parseRequestBody;

public class IdentificationActivity extends BaseActivity {
    @BindView(R.id.imgFont)
    ImageView imgFont;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    private CommonTitleBar titleBar;
    private int mSide = 0;
    private static final int INTO_IDCARDSCAN_PAGE = 100;
    private static final int EXTERNAL_STORAGE_REQ_CAMERA_CODE = 10;
    private String[] mImageFullPath = new String[7];//存储本地图片路径
    private final int IMAGE_TYPE_ID_CARD_FRONT = 20;
    private final int IMAGE_TYPE_ID_CARD_BACK = 21;

    private IDCardInfoPresenter idCardInfoPresenter=new IDCardInfoPresenter(this);

    @Override
    protected int initLayoutId() {
        return R.layout.activity_identification;
    }

    @Override
    protected void initView() {
        //初始化控件布局等等
        titleBar = new CommonTitleBar(this);
        titleBar.setTitle("身份证识别");
        titleBar.hideRight(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idCardInfoPresenter.onCreate();
        idCardInfoPresenter.attachView(idCardInfoView);

    }

    private IDCardInfoView idCardInfoView=new IDCardInfoView() {
        @Override
        public void onSuccess(IDCardBean idCardBean) {
            Toast.makeText(mContext,"请求成功"+idCardBean.getId_card_number(),Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(String result) {
            Toast.makeText(mContext,"请求失败"+result,Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        idCardInfoPresenter.onStop();
    }

    @OnClick({R.id.imgFont, R.id.imgBack})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.imgFont:
                network();
                break;
            case R.id.imgBack:
                requestCameraPerm(1);
                break;
        }

    }

    private void network() {
        //ProgressDialogUtil.showDialog(IdentificationActivity.this, "正在联网授权中...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Manager manager = new Manager(IdentificationActivity.this);
                IDCardQualityLicenseManager idCardLicenseManager = new IDCardQualityLicenseManager(
                        IdentificationActivity.this);
                manager.registerLicenseManager(idCardLicenseManager);
                manager.takeLicenseFromNetwork(Util.getUUIDString(IdentificationActivity.this));
                Log.d("ret", idCardLicenseManager.checkCachedLicense() + "checkCachedLicense");
                if (idCardLicenseManager.checkCachedLicense() > 0) {
                    UIAuthState(true);
                } else {
                    UIAuthState(false);
                }
            }
        }).start();
    }

    private void UIAuthState(final boolean isSuccess) {
        runOnUiThread(new Runnable() {
            public void run() {
                authState(isSuccess);
            }
        });
    }

    private void authState(boolean isSuccess) {
        if (isSuccess) {
            //开始拍摄
            requestCameraPerm(0); //0代表正面，1代表反面
        } else {
            Toast.makeText(mContext, "联网授权失败！请检查网络或找服务商", Toast.LENGTH_SHORT).show();
        }
    }

    private void requestCameraPerm(int side) {
        mSide = side;
        if (Build.VERSION.SDK_INT >= M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                //进行权限请求
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        EXTERNAL_STORAGE_REQ_CAMERA_CODE);
            } else {
                enterNextPage(side);
            }
        } else {
            enterNextPage(side);
        }
    }

    private void enterNextPage(int side) {
        Intent intent = new Intent(this, IDCardScanActivity.class);
        intent.putExtra("side", side);
        intent.putExtra("isvertical", true);//是横屏拍还是竖屏
        startActivityForResult(intent, INTO_IDCARDSCAN_PAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == EXTERNAL_STORAGE_REQ_CAMERA_CODE) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {// Permission Granted
                Util.showToast(this, "获取相机权限失败");
            } else
                enterNextPage(mSide);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTO_IDCARDSCAN_PAGE && resultCode == RESULT_OK) {
            switch (mSide) {
                case 0:
                    setImageSource(data.getByteArrayExtra("idcardImg"),mSide);
                    mImageFullPath[0] = saveJPGFile(mContext, data.getByteArrayExtra("idcardImg"), IMAGE_TYPE_ID_CARD_FRONT);

                    ArrayList<String> pathList = new ArrayList<>();
                    pathList.add(mImageFullPath[0]);
                    Map<String, RequestBody> bodyMap = new HashMap<>();
                    bodyMap.put("api_key", parseRequestBody("0azhB3p2GR3kmnoIq8uqAJJ4SGWFPcqG"));
                    bodyMap.put("api_secret", parseRequestBody("jYdc1g-snWxWs_Jvwk49k094GINJ59Y5"));
                    if(pathList.size() > 0) {
                        for (int i = 0; i < pathList.size(); i++) {
                            File file = new File(pathList.get(i));
                            bodyMap.put(RetrofitManager.parseImageMapKey("image",file.getName()),parseImageRequestBody(file));
                        }
                    }
                    idCardInfoPresenter.getIDCardInfo(bodyMap);

                    break;
                case 1:
                    setImageSource(data.getByteArrayExtra("idcardImg"),mSide);
                    mImageFullPath[1] = saveJPGFile(mContext, data.getByteArrayExtra("idcardImg"), IMAGE_TYPE_ID_CARD_BACK);
                    break;
            }
        }
    }

    //请求face++获取身份证信息
    public void getIdCardInfo(byte[] data){

    }


    private void setImageSource(byte[] imageSource, int side) {
        Bitmap idcardBmp = BitmapFactory.decodeByteArray(imageSource, 0,
                imageSource.length);
        if (side == 0) {
            imgFont.setImageBitmap(idcardBmp);
        }else{
            imgBack.setImageBitmap(idcardBmp);
        }
    }


    private static String saveJPGFile(Context mContext, byte[] data, int type) {
        if (data == null)
            return null;
        File mediaStorageDir = mContext.getExternalFilesDir("idCardAndLiveness");
        if (null == mediaStorageDir) {
            return null;
        }
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            String jpgFileName = type + ".jpg";
            fos = new FileOutputStream(mediaStorageDir + "/" + jpgFileName);
            bos = new BufferedOutputStream(fos);
            bos.write(data);
            return mediaStorageDir.getAbsolutePath() + "/" + jpgFileName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }
}
