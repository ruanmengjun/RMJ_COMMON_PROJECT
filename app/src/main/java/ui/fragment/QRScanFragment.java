package ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.activity.CaptureActivity;

import base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rmj.example.com.rmj_common_project.R;

/**
 * Created by admin on 2017/11/17.
 */

public class QRScanFragment extends BaseFragment {

    @BindView(R.id.btn1)
    TextView btn1;
    @BindView(R.id.result)
    TextView result;
    Unbinder unbinder;
    private final static int REQ_CODE = 1028;
    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected int initLayoutId() {
        return R.layout.qr_scan_fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.btn1)
    public void click(View v) {
        Intent intent = new Intent(getContext(), CaptureActivity.class);
        startActivityForResult(intent, REQ_CODE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            String resultStr = data.getStringExtra(CaptureActivity.SCAN_QRCODE_RESULT);
            Bitmap bitmap = data.getParcelableExtra(CaptureActivity.SCAN_QRCODE_BITMAP);
            result.setText(resultStr);
            if (bitmap!=null)
            imageView.setImageBitmap(bitmap);
        }
    }
}
