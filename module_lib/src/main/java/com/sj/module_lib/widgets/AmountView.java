package com.sj.module_lib.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.sj.module_lib.R;
import com.sj.module_lib.utils.ToastUtils;


/**
 * 创建时间: on 2018/4/9.
 * 创建人: 孙杰
 * 功能描述:
 */
public class AmountView extends LinearLayout implements View.OnClickListener, TextWatcher {

    private static final String TAG = "AmountView";
    private int amount = 1; //购买数量
    private final int min_amount = 1; //最少购买数量
    private int goods_storage = Integer.MAX_VALUE; //商品库存

    private OnAmountChangeListener mListener;

    private EditText etAmount;
    private Button btnDecrease;
    private Button btnIncrease;

    private boolean editable = true;

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_amount, this);
        etAmount = (EditText) findViewById(R.id.etAmount);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);

        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.AmountView);
        int btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnWidth, LayoutParams.WRAP_CONTENT);
        int tvWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvWidth, 80);
        int tvTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvTextSize, 0);
        int btnTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnTextSize, 0);
        obtainStyledAttributes.recycle();

        LayoutParams btnParams = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
        btnDecrease.setLayoutParams(btnParams);
        btnIncrease.setLayoutParams(btnParams);
        if (btnTextSize != 0) {
            btnDecrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
            btnIncrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
        }

        LayoutParams textParams = new LayoutParams(tvWidth, LayoutParams.MATCH_PARENT);
        etAmount.setLayoutParams(textParams);
        if (tvTextSize != 0) {
            etAmount.setTextSize(tvTextSize);
        }
        etAmount.setEnabled(false);
        etAmount.setTextColor(context.getResources().getColor(R.color.black_alpha80));
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public void setGoods_storage(int goods_storage) {
        this.goods_storage = goods_storage;
    }

    public void setAmount(int amount) {
        if (amount<min_amount){
            amount = min_amount;
        }
        this.amount = amount;
        if ( etAmount!=null){
            etAmount.setText(amount + "");
        }
    }
    public int getAmount(){
        return amount;
    }

    @Override
    public void onClick(View v) {
        if (!editable){
            ToastUtils.showShortToast("当前为不可编辑状态");
            return;
        }
        etAmount.clearFocus();
        int i = v.getId();
        if (i == R.id.btnDecrease) {
            if (amount > min_amount) {
                amount--;
                etAmount.setText(amount + "");
            }else{
                return;
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < goods_storage) {
                amount++;
                etAmount.setText(amount + "");
            }else{
                return;
            }
        }

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty())
            return;
        amount = Integer.valueOf(s.toString());
        if (amount > goods_storage) {
            etAmount.setText(goods_storage + "");
            return;
        }
//
//        if (mListener != null) {
//            mListener.onAmountChange(this, amount);
//        }
    }

    public void setEditable(boolean b) {
//        btnDecrease.setVisibility(b?View.VISIBLE:View.INVISIBLE);
//        btnIncrease.setVisibility(b?View.VISIBLE:View.INVISIBLE);
        this.editable = b;
    }


    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }

}

