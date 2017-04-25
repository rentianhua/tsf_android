package com.dumu.housego.presenter;

import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IPaySuccessModel;
import com.dumu.housego.model.PaySuccessModel;
import com.dumu.housego.view.IPaySuccessView;

public class PaySuccessPresenter implements IPaySuccessPresenter{

	private IPaySuccessModel model;
	private IPaySuccessView view;
	
	public PaySuccessPresenter(IPaySuccessView view) {
		super();
		this.view = view;
		model=new PaySuccessModel();
	}

	@Override
	public void PayInfo(String resultStatus, String jine, String order_no,String trade_no) {
		model.PayInfo(resultStatus, jine, order_no, trade_no,new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.PayInfo(info);
				
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
