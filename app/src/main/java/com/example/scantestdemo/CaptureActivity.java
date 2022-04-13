package com.example.scantestdemo;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 *
 */
public class CaptureActivity extends Activity {
    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        barcodeScannerView = initializeContent();

        ImageView iv_scan_zxing_back = findViewById(R.id.iv_scan_zxing_back);
        TextView tv_scan_zxing_album = findViewById(R.id.tv_scan_zxing_album);




        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();

        iv_scan_zxing_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /**
         //打开相册 打开相册  选择相册 并且扫描 这个扫描怎么弄啊   选择本地相册扫描
            现在的当务之急  就是处理相册的问题 必须能从相册选择图片  并且识别二维码
         先考虑最理想的请求   其他的乱七八糟的先不考虑  先把整个流程跑通
         */



    }

    /**
     * Override to use a different layout.
     *
     * @return the DecoratedBarcodeView
     */
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.zxing_capture);
        return (DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
    }

    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    /**
     这个地方应该直接调用相册  扫描 问题的关键在于  怎么调用  然后怎么扫描  这是一个问题啊
     问题在于  我走了另外的一条路  这是一条另外的道路  应该怎么扫码呢  看源码

     */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        capture.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}
