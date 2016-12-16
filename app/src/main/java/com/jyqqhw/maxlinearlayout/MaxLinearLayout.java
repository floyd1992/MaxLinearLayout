package com.jyqqhw.maxlinearlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by wj on 16-12-16.
 */
public class MaxLinearLayout extends LinearLayout {

	private static final int MAX_HEIGHT = 460;

	private int viewHeight, viewWidth;
	private int maxHeight;

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
		viewHeight = h;
		viewWidth = w;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measureH = getMeasuredHeight();

		if (0 == maxHeight || 0 == measureH ) {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			return;
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
		requestLayout();
	}

	public int getMaxHeight() {
		return maxHeight;
	}
}
