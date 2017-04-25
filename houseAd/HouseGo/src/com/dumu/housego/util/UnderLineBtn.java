package com.dumu.housego.util;

import com.dumu.housego.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Kevin on 2016/5/13.
 */
public class UnderLineBtn extends RelativeLayout {
	/**
	 * ��Ӧ����
	 */
	private float lineHeight, lineWeight, textSize;
	private int unCheckedColor, checkedColor;
	private boolean isChecked;
	private String text;

	/**
	 * �ؼ�
	 */
	private TextView textView;// ����
	private View view;// �»���
	private LayoutParams textViewParams, viewParams;

	public UnderLineBtn(Context context) {
		super(context);
	}

	public UnderLineBtn(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public UnderLineBtn(Context context, AttributeSet attrs) {
		super(context, attrs);
		/**
		 * ��ȡ�Զ�������
		 */
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.underLineBtn);
		lineHeight = array.getDimension(R.styleable.underLineBtn_lineHeight, 1);
		lineWeight = array.getDimension(R.styleable.underLineBtn_lineWight, LayoutParams.MATCH_PARENT);
		textSize = array.getDimensionPixelSize(R.styleable.underLineBtn_textSize, 14);
		unCheckedColor = array.getColor(R.styleable.underLineBtn_unCheckedColor, Color.WHITE);
		checkedColor = array.getColor(R.styleable.underLineBtn_checkedColor, Color.BLUE);
		isChecked = array.getBoolean(R.styleable.underLineBtn_isChecked, false);
		text = array.getString(R.styleable.underLineBtn_text);
		array.recycle();// ���Ի�ȡ��֮��ʱ����

		// ���ؼ���ֵ
		textView = new TextView(context);
		// textView.setId(StringUtil.generateViewId());
		view = new View(context);
		if (isChecked) {
			textView.setTextColor(checkedColor);
			view.setBackgroundColor(checkedColor);
		} else {
			textView.setTextColor(unCheckedColor);
			view.setBackgroundColor(unCheckedColor);
		}
		textView.setText(text);
		// textView.setTextSize(textSize);
		textView.getPaint().setTextSize(textSize);
		// �ؼ�λ��
		textViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		textViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		textView.setGravity(Gravity.CENTER_HORIZONTAL);
		addView(textView, textViewParams);

		viewParams = new LayoutParams((int) lineWeight, (int) lineHeight);
		viewParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		addView(view, viewParams);
	}

	public void setChecked(boolean checked) {
		if (isChecked != checked) {
			isChecked = checked;
			/**
			 * �����ı�����ʾ��ִ��drawableStateChanged()�еı仯
			 * ��ִ�б�������drawableStateChanged()�����õĸı佫����ԭ
			 */
			refreshDrawableState();
		}
	}

	@Override
	protected void drawableStateChanged() {
		super.drawableStateChanged();
		// �ı�ʱ���л��߼�
		if (isChecked) {
			textView.setTextColor(checkedColor);
			view.setBackgroundColor(checkedColor);
		} else {
			textView.setTextColor(unCheckedColor);
			view.setBackgroundColor(unCheckedColor);
		}
	}
}
