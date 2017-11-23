package com.ascba.rebate.activities.score_shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ascba.rebate.R;
import com.ascba.rebate.activities.seller.SellerGiveCreateActivity;
import com.ascba.rebate.base.activity.BaseDefaultNetActivity;

public class ExchangeSuccessActivity extends BaseDefaultNetActivity {

    private TextView tvType;
    private TextView tvMoney;
    private TextView tvTitle;
    private Button btnComplete;
    private View wave;
    int type;

    @Override
    protected int bindLayout() {
        return R.layout.activity_gift_exchange_success;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        tvType = fv(R.id.tv_pay_type);
        tvMoney = fv(R.id.tv_pay_money);
        tvTitle = fv(R.id.tv_title);
        btnComplete = fv(R.id.btn_complete);
        wave = fv(R.id.wave_line);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) wave.getLayoutParams();
        params.height = getResources().getDisplayMetrics().widthPixels / 18;
        getParams();
        mMoneyBar.setCallBack(mMoneyBar.new CallbackImp() {
            @Override
            public void clickBack(View back) {
                backResults();
            }
        });
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 0)
                    startActivity(SellerGiveCreateActivity.class, null);
                else
                    GiftExchangeLogActivity.jumpIntent(ExchangeSuccessActivity.this, type);
                finish();
            }
        });
    }

    private void backResults() {
        finish();
    }

    private void getParams() {
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        if (type == 0) {
            mMoneyBar.setTextTitle("申请成功");
            tvTitle.setText("申请成功");
            btnComplete.setText("查看申请");
            tvMoney.setText("\u3000\u3000" +
                    "首次申请后15个工作日寄出，无需再次申请，后期产生的发票在账单日后15个工作日内寄出。"
                    + intent.getStringExtra("info"));
        } else if (type == 1 || type == 2) {
            mMoneyBar.setTextTitle("兑换成功");
            tvTitle.setText("兑换成功");
            btnComplete.setText("立即查看");
            tvMoney.setText("\u3000\u3000" + intent.getStringExtra("info"));
        }
    }

    @Override
    public void onBackPressed() {
        backResults();
    }
}