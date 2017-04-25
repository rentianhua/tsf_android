package com.dumu.housego.util;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * @Author: pyz
 * @Package: com.pyz.viewpagerdemo.ui
 * @Description: TODO
 * @Project: ViewPagerDemo
 * @Date: 2016/8/19 10:38
 */
public class CarouselViewPager extends ViewPager {

	private int displayTime = 3000;// ͼƬչʾ��ʱ�䣬Ĭ��Ϊ3��
	private CarouselDirection direction = CarouselDirection.LEFT;// ͼƬ�Զ������ķ�������

	public CarouselViewPager(Context context) {
		super(context);
	}

	public CarouselViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * ����ͼƬչʾʱ��
	 * 
	 * @param time
	 */
	public void setDisplayTime(int time) {
		displayTime = time;
	}

	/**
	 * ����ͼƬ�Զ������ķ���
	 * 
	 * @param direction
	 */
	public void setDirection(CarouselDirection direction) {
		this.direction = direction;
	}

	/**
	 * ��ʼ�Զ��ֲ�
	 */
	public void start() {
		stop();
		postDelayed(automaticDisplay, displayTime);
	}

	/**
	 * ֹͣ�Զ��ֲ�
	 */
	public void stop() {
		removeCallbacks(automaticDisplay);
	}

	/**
	 * ͼƬ�ֲ�����ö����
	 */
	public enum CarouselDirection {
		LEFT, RIGHT
	}

	private Runnable automaticDisplay = new Runnable() {
		@Override
		public void run() {
			display(direction);
		}
	};

	/**
	 * ͼƬ�ֲ�
	 * 
	 * @param direction
	 */
	private synchronized void display(CarouselDirection direction) {
		PagerAdapter pagerAdapter = getAdapter();
		if (pagerAdapter != null) {
			int count = pagerAdapter.getCount();// ͼƬ������
			int currentItem = getCurrentItem();// ��ǰչʾ���ڼ���

			switch (direction) {
			case LEFT:
				currentItem++;
				// ��ǰչʾ��ͼƬΪ���һ��ʱ���򷵻ص�һ��
				if (currentItem >= count) {
					currentItem = 0;
				}
				break;
			case RIGHT:
				currentItem--;
				// ��ǰչʾ��ͼƬΪ���һ��ʱ���򷵻ص�һ��
				if (currentItem < 0) {
					currentItem = count - 1;
				}
				break;
			}
			setCurrentItem(currentItem);
		}
		start();
	}

	@Override
	protected void onFinishInflate() {
		addOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				if (state == SCROLL_STATE_IDLE) {
					start();
				} else if (state == SCROLL_STATE_DRAGGING) {
					stop();
				}
			}
		});
	}
}
