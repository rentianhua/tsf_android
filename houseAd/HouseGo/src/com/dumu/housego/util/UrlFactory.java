package com.dumu.housego.util;

public class UrlFactory {

	public static String TSFURL = "https://www.taoshenfang.com";
	
	//身份证-优惠券活动
	public static String PostYHQDelete() {
		String path = TSFURL + "/index.php?g=api&m=sms&a=getyzm_hd";
		return path;
	}
	
	
	//委托管理
		public static String PostWeiTuoGuanLi() {
			String path = TSFURL + "/index.php?g=api&m=user&a=weituo";
			return path;
		}
	
	//删除留言
	public static String PostDeleteLiuYan() {
		String path = TSFURL + "/index.php?g=api&m=user&a=liuyan_del";
		return path;
	}
	
	//留言设置已读
	public static String PostLiuyanYidu(){
		String path = TSFURL + "/index.php?g=api&m=user&a=liuyan_yidu";
		return path;
	}
	
	
	//获取最新的二手市场行情
	public static String GetFangJiaXingQing(){
		String path = TSFURL + "/index.php?g=api&m=house&a=tracy";
		return path;
	}
	
	
	//首页标题
	public static String PostSureYuYue() {
		String path = TSFURL + "/index.php?g=api&m=user&a=yuyue_confirm";
		return path;
	}
	
	//首页标题
		public static String GetShouYeBiaoTi() {
			String path = TSFURL + "/index.php?g=api&m=house&a=webconfig";
			return path;
		}
		//留言详情
		public static String PostLiuYanDetail() {
			String path = TSFURL + "/index.php?g=api&m=user&a=liuyan_detail";
			return path;
		}
	
	//添加留言
	public static String PostAddLiuYan() {
		String path = TSFURL + "/index.php?g=api&m=user&a=liuyan_add";
		return path;
	}
	
	//留言列表
	public static String PostLiuYanList() {
		String path = TSFURL + "/index.php?g=api&m=user&a=liuyan_list";
		return path;
	}
	
	
	//历史记录
	public static String PostHistroyDate() {
		String path = TSFURL + "/index.php?g=api&m=user&a=history";
		return path;
	}
	
	
	//获取购房须知
		public static String GetGouFangxuzhi() {
			String path = TSFURL + "/index.php?g=api&m=house&a=house_xuzhi";
			return path;
		}
	
	
	//出租房编辑
	public static String PostChuzuEdit() {
		String path = TSFURL + "/index.php?g=api&m=user&a=edit_chuzu";
		return path;
	}
	
	
	//二手房编辑
		public static String PostErShouEdit() {
			String path = TSFURL + "/index.php?g=api&m=user&a=edit_ershou";
			return path;
		}
	
	
	//申请已售出
	public static String PostApplyShouChu() {
		String path = TSFURL + "/index.php?g=api&m=house&a=apply_shouchu";
		return path;
	}
	
	
	
	//判断是否勾地
	public static String PostHasGoudi() {
		String path = TSFURL + "/index.php?g=api&m=house&a=has_goudi";
		return path;
	}
	
	//推荐位列表数据获取接口
	public static String GetTuiJianWeiList(String posid) {
		String path = TSFURL + "/index.php?g=api&m=house&a=position&posid="+posid;
		return path;
	}	
	
	
	//
	//经纪人求租求购管理
		public static String PostQiuZuQiuGou() {
			String path = TSFURL + "/index.php?g=api&m=user&a=qiu_list";
			return path;
		}	
		
		
	//单张图片上传接口
	public static String PostupLoadPic() {
		String path = TSFURL + "/index.php?g=api&m=user&a=uploadimg";
		return path;
	}	
	
	
	
//	删除优惠券列表接口
	public static String PostYHQListDelete() {
		String path = TSFURL + "/index.php?g=api&m=house&a=coupon_del";
		return path;
	}		
	
//	获取优惠券列表接口
	public static String GetYHQList(String userid) {
		String path = TSFURL + "/index.php?g=api&m=house&a=coupon_orderlist&userid="+userid;
		return path;
	}	
	
	
//	购买优惠券接口
	public static String PostAddYHQ() {
		String path = TSFURL + "/index.php?g=api&m=house&a=coupon_add";
		return path;
	}	
	
	
//	获取省份验证吗信息
	public static String PostYZMforYHQ() {
		String path = TSFURL + "/index.php?g=api&m=sms&a=getyzm_hd";
		return path;
	}
	
	
//	获取新房优惠券
	public static String PostNewHouseYHQ() {
		String path = TSFURL + "/index.php?g=Api&m=house&a=getyhq";
		return path;
	}
	
	
	//	支付成功回调地址
	public static String PostPayStatusSuccess() {
		String path = TSFURL + "/index.php?g=Api&m=Api&a=app_return_url";
		return path;
	}
	
	
	//删除勾地订单
		public static String PostGouDiDelete() {
			String path = TSFURL + "/index.php?g=api&m=house&a=goudi_del";
			return path;
		}
	
	//勾地订单列表
	public static String GetGouDiList(String userid) {
		String path = TSFURL + "/index.php?g=api&m=house&a=goudi_orderlist&userid="+userid;
		return path;
	}
	
	//勾地订单添加接口
	public static String PostGouDiAdd() {
		String path = TSFURL + "/index.php?g=api&m=house&a=goudi_add";
		return path;
	}
	
	
	//指定小区下的所有房源
	public static String PostXiaoQuAllHosue() {
		String path = TSFURL + "/index.php?g=api&m=map&a=house";
		return path;
	}
	
	//指定区域下所有小区
	public static String PostXiaoQuMapHouse() {
		String path = TSFURL + "/index.php?g=api&m=map&a=xiaoqu";
		return path;
	}
	
	
	//指定行政区下所有区域的房源套数
	
	public static String PostAreaMapHouse() {
		String path = TSFURL + "/index.php?g=api&m=map&a=area_house";
		return path;
	}
	
	//添加评论
	public static String PostAddAgentComment() {
		String path = TSFURL + "/index.php?g=Api&m=user&a=jjr_comm_add";
		return path;
	}
	
	
	
	//经纪人评论
	
	public static String GetAgentComment(String id) {
		String path = TSFURL + "/index.php?g=Api&m=user&a=jjr_comment&id="+id;
		return path;
	}
	
	//经纪人详情
	public static String GetAgentDetail(String id) {
		String path = TSFURL + "/index.php?g=Api&m=user&a=jjrshow&id="+id;
		return path;
	}
	
	//二手房发布
		public static String PostATershouSubmit() {
			String path = TSFURL + "/index.php?g=api&m=user&a=add_ershou";
			return path;
		}
	
	//二手房/出租房删除
	public static String PostDeleteErShouChuZuList() {
		String path = TSFURL + "/index.php?g=api&m=user&a=house_del";
		return path;
	}

	//出租房发布
	public static String PostATrentingSubmit() {
		String path = TSFURL + "/index.php?g=api&m=user&a=add_chuzu";
		return path;
	}

	
	
	//小区搜索
	public static String PostSearchXiaoQu() {
		String path = TSFURL + "/index.php?g=api&m=user&a=xiaoqu_search";
		return path;
	}

	// 删除求购
	public static String PostBuyHousedelete() {
		String path = TSFURL + "/index.php?g=api&m=user&a=qiugou_del";
		return path;
	}

	// 求购列表/求租列表/求购管理/求租管理
	public static String PostQiuBuylist() {
		String path = TSFURL + "/index.php?g=api&m=user&a=qiu_list";
		return path;
	}

	// 发布求购
	public static String PostBuyHouseSubmit() {
		String path = TSFURL + "/index.php?g=api&m=user&a=qiugou_add";
		return path;
	}

	// 删除求租
	public static String PostQiuZuHouseListDelete() {
		String path = TSFURL + "/index.php?g=api&m=user&a=qiuzu_del";
		return path;
	}

	// 发布求租

	public static String PostQiuZuHouseSubmit() {
		String path = TSFURL + "/index.php?g=api&m=user&a=qiuzu_add";
		return path;
	}

	// 获取地区列表接口
	public static String PostAddressList() {
		String path = TSFURL + "/index.php?g=api&m=house&a=diqu";
		return path;
	}

	// 成交房源
	public static String PostChengJiaoHouseList() {
		String path = TSFURL + "/index.php?g=api&m=user&a=chengjiao";
		return path;
	}

	// 二手房/出租房列表
	public static String PostSubmitESorCZList() {
		String path = TSFURL + "/index.php?g=api&m=user&a=house_list";
		return path;
	}

	// 直接修改密码
	public static String PostChangePassword() {
		String path = TSFURL + "/index.php?g=member&m=public&a=mod_pwd2";
		return path;
	}

	// 申请400电话
	public static String GetShenQing400Url(String tel, String userid) {

		String path = TSFURL + "/index.php?g=api&m=api&a=add_vtel&tel=" + tel + "&userid=" + userid;
		return path;
	}

	// 申请400电话
	public static String GetJieBang400Url(String tel, String userid) {
		String path = TSFURL + "/index.php?g=api&m=api&a=remove_vtel&tel=" + tel + "&userid=" + userid;
		return path;
	}

	// ע��--��ȡ������֤��
	public static String GetPhoneCodeUrl(String mob, String verify) {

		String PhoneCode = TSFURL +"/index.php?g=api&m=sms&a=reg&mob=" + mob + "&verify=" + verify;
		return PhoneCode;
	}

	// ע��--�ӿ�
	public static String PostRegistUrl() {

		String path = TSFURL +"/index.php?g=Member&m=Public&a=api_register";
		return path;

	}

	// ��¼--�ӿ�
	public static String PostLoginUrl() {

		String path = TSFURL +"/index.php?g=Member&m=Public&a=api_dologin";
		return path;

	}

	// ��֤���½

	public static String PostYZMLoginUrl() {

		String path = TSFURL +"/index.php?g=api&m=sms&a=mob_login_yzm";
		return path;
	}

	public static String PostYZMLogincodeUrl() {

		String path = TSFURL +"/index.php?g=api&m=sms&a=getyzm";
		return path;
	}

	// ��ȡ��¼�û�����Ϣ
	public static String PostLoginUserInfoUrl() {

		String path = TSFURL +"/index.php?g=Member&m=Public&a=api_getuserinfo";
		return path;
	}

	// ���ĸ�����Ϣ
	public static String PostChangeUserInfoUrl() {
		String path = TSFURL +"/index.php?g=api&m=user&a=api_doprofile";
		return path;
	}

	// ���ĸ���ͷ��
	public static String PostChangeHeadPhotoUrl() {
		String path = TSFURL +"/index.php?g=api&m=user&a=api_uploadavatar";
		return path;
	}

	// ��ע���û���������-��ȡ��֤��
	public static String PostFindPasswordCodeUrl() {
		String path = TSFURL +"/index.php?g=api&m=sms&a=lost_getyzm";
		return path;
	}

	// ע���û� �޸�����
	public static String PostFindPasswordUrl() {
		String path = TSFURL +"/index.php?g=member&m=public&a=mod_pwd";
		return path;
	}

	// ����ֻ������Ƿ����ע���Ա
	public static String PostCheckPhoneRegistUrl() {
		String path = TSFURL +"/index.php?g=api&m=sms&a=check_mob";
		return path;
	}

	// ������ģ���б�api
	public static String PostAgentmodelUrl() {
		String path = TSFURL +"/index.php?g=Api&m=user&a=jjrlist";
		return path;
	}

	// �����Ƽ�λ�б��ȡ��Ӧ����
	public static String GetRecommendListToDetailUrlString(String catid, String id) {
		String path = TSFURL +"/index.php?g=api&m=house&a=api_shows&catid=" + catid + "&id=" + id;
		return path;
	}

	// �����Ƽ�λ�б��ȡ��Ӧ����
	public static String GetRecommendListToDetailUrl(int catid, int id) {
		String path = TSFURL +"/index.php?g=api&m=house&a=api_shows&catid=" + catid + "&id=" + id;
		return path;
	}

	// �����Ƽ�λ�б��ȡ��Ӧ����
	public static String PostRecommendListToDetailUrl() {
		String path = TSFURL +"/index.php?g=api&m=house&a=api_shows";
		return path;
	}

	// ��ȡָ����Ŀ�����б�ӿ�
	// ������ �·� ���ַ� �ⷿ ���ڽ��� �ĸ���Ŀ
	public static String PostFourDataProgramaUrl() {
		String path = TSFURL +"/index.php?g=api&m=house&a=lists";
		return path;
	}

	// wap����ҳ�Ƽ�
	public static String GetRecommendHouseUrl() {

		String path = TSFURL +"/index.php?g=api&m=house&a=position&posid=12";
		return path;
	}

	// ���ַ��б�ҳ�Ƽ�
	public static String GetErShouFangRecommendUrl() {
		String path = TSFURL +"/index.php?g=api&m=house&a=position&posid=8";
		return path;
	}

	// wap���·��Ƽ���Դ
	public static String GetWapNewHouseHotUrl() {
		String path = TSFURL +"/index.php?g=api&m=house&a=position&posid=13";
		return path;
	}

	// wap���·���ѯ��Դ
	public static String GetWapNewHouseRecommendUrl() {
		String path = TSFURL +"/index.php?g=api&m=house&a=position&posid=14";
		return path;
	}

	// ���ⷿ�б�ҳ�Ƽ�
	public static String GetRentingUrl() {
		String path = TSFURL +"/index.php?g=api&m=house&a=position&posid=9";
		return path;
	}
	// ���ⷿ�б�ҳ�Ƽ�
	public static String GetRentingTuijianUrl() {
		String path = TSFURL +"/index.php?g=api&m=house&a=position&posid=4";
		return path;
	}

	// ���ⷿ�б�ҳ�Ƽ�
	public static String GetBlockTradeListUrl() {
		String path = TSFURL +"/index.php?g=api&m=house&a=position&posid=10";
		return path;
	}

	// ��ȡָ����Ŀ�����б�ӿ�
	public static String GetAssignContentListUrl() {
		String path = TSFURL +"/index.php?g=api&m=house&a=lists";
		return path;
	}

	// ��ע��Դ�ӿ�
	public static String PostGuanZhuHouseUrl() {
		String path = TSFURL + "/index.php?g=api&m=house&a=guanzhu_add";
		return path;
	}

	// �ҵĹ�ע
	public static String PostGuanZhuErShouUrl() {
		String path = TSFURL + "/index.php?g=api&m=user&a=guanzhu";
		return path;
	}

	// ȡ����ע
	public static String PostGuanZhuDeleteUrl() {
		String path = TSFURL + "/index.php?g=api&m=user&a=guanzhu_del";
		return path;
	}

	// 预约看房接口
	public static String PostYuYueHouseUrl() {
		String path = TSFURL + "/index.php?g=api&m=house&a=yuyue_add";
		return path;
	}

	// 我的预约看房
	public static String PostMyYuYueHouseUrl() {
		String path = TSFURL + "/index.php?g=api&m=user&a=yuyue";
		return path;
	}

	// 删除预约
	public static String PostDeleteMyYuYueHouseUrl() {
		String path = TSFURL + "/index.php?g=api&m=user&a=yuyue_del";
		return path;
	}

	// 获取行政区房源套数
	public static String PostXingzhengHouseUrl() {
		String path = TSFURL + "/index.php?g=api&m=map&a=city_house";
		return path;
	}

	// 指定行政区下所以区域的房源套数
	public static String PostzhidingXingzhengHouseUrl() {
		String path = TSFURL + "/index.php?g=api&m=map&a=area_house";
		return path;
	}

	// 指定小区下所有的房源套数
	public static String PostzhidingxiaoquHouseUrl() {
		String path = TSFURL + "/index.php?g=api&m=map&a=house";
		return path;
	}

}
