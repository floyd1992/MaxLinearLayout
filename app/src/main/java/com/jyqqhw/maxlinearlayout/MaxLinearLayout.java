package com.jyqqhw.maxlinearlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by wj on 16-12-16.
 */
public class MaxLinearLayout extends LinearLayout {

	private int maxHeight;
	private boolean relayout;

	public MaxLinearLayout(Context context) {
		super(context);
	}

	public MaxLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MaxLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measureH = getMeasuredHeight();

		if (0 == maxHeight || 0 == measureH ) {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			return;
		}

		if(relayout){
			int m = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE/3,MeasureSpec.AT_MOST);
			super.onMeasure(m, m);
			measureH = getMeasuredHeight();
			relayout = false;
		}
		int hMS;
		if (measureH >= maxHeight) {
			hMS = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.EXACTLY);
		} else {
			hMS = MeasureSpec.makeMeasureSpec(measureH, MeasureSpec.EXACTLY);
		}
		super.onMeasure(widthMeasureSpec, hMS);
	}

	public void setMaxHeight(int mH) {
		if(0 >= mH){
			return;
		}
		maxHeight = mH;
		relayout = true;
		requestLayout();
	}

	public int getMaxHeight() {
		return maxHeight;
	}
}
