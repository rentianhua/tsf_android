package com.dumu.housego.entity;

public class FourDataPrograma {
	private String catid; // ѡ���������ͣ�����
	private String page;// ҳ������һҳ20������
	private String ct;// ������ 7=������
	private String ar;// ������15=ˮ��
	private String zj;// �ܼ�50-100W
	private String shi;// ��
	private String zx; // װ������
	private String yt; // ����
	private String wy; // ����
	private String xq; // �Ƿ�С��
	private String order; // ����
	private String kwds; // �ؼ���
	private String mj; // ���
	private String qs; // ��ҵ
	private String ly; // ��Դ
	private String dt; // ����һ����
	private String cx; // ����
	private String lc; // ¥��
	private String zl; // ��������
	private String kf; // �Ƿ���ʱ����
	private String fs; // ת�÷�ʽ
	private String lx; // ����
	private String sx; // ����

	public FourDataPrograma(String catid, String page, String ct, String ar, String zj, String shi, String zx,
			String yt, String wy, String xq, String order, String kwds, String mj, String qs, String ly, String dt,
			String cx, String lc, String zl, String kf, String fs, String lx, String sx) {
		super();
		this.catid = catid;
		this.page = page;
		this.ct = ct;
		this.ar = ar;
		this.zj = zj;
		this.shi = shi;
		this.zx = zx;
		this.yt = yt;
		this.wy = wy;
		this.xq = xq;
		this.order = order;
		this.kwds = kwds;
		this.mj = mj;
		this.qs = qs;
		this.ly = ly;
		this.dt = dt;
		this.cx = cx;
		this.lc = lc;
		this.zl = zl;
		this.kf = kf;
		this.fs = fs;
		this.lx = lx;
		this.sx = sx;
	}

	public FourDataPrograma() {
		super();
	}

	public String getCatid() {
		return catid;
	}

	public void setCatid(String catid) {
		this.catid = catid;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getAr() {
		return ar;
	}

	public void setAr(String ar) {
		this.ar = ar;
	}

	public String getZj() {
		return zj;
	}

	public void setZj(String zj) {
		this.zj = zj;
	}

	public String getShi() {
		return shi;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}

	public String getZx() {
		return zx;
	}

	public void setZx(String zx) {
		this.zx = zx;
	}

	public String getYt() {
		return yt;
	}

	public void setYt(String yt) {
		this.yt = yt;
	}

	public String getWy() {
		return wy;
	}

	public void setWy(String wy) {
		this.wy = wy;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getKwds() {
		return kwds;
	}

	public void setKwds(String kwds) {
		this.kwds = kwds;
	}

	public String getMj() {
		return mj;
	}

	public void setMj(String mj) {
		this.mj = mj;
	}

	public String getQs() {
		return qs;
	}

	public void setQs(String qs) {
		this.qs = qs;
	}

	public String getLy() {
		return ly;
	}

	public void setLy(String ly) {
		this.ly = ly;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getCx() {
		return cx;
	}

	public void setCx(String cx) {
		this.cx = cx;
	}

	public String getLc() {
		return lc;
	}

	public void setLc(String lc) {
		this.lc = lc;
	}

	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	public String getKf() {
		return kf;
	}

	public void setKf(String kf) {
		this.kf = kf;
	}

	public String getFs() {
		return fs;
	}

	public void setFs(String fs) {
		this.fs = fs;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getSx() {
		return sx;
	}

	public void setSx(String sx) {
		this.sx = sx;
	}

	@Override
	public String toString() {
		return "FourDataPrograma [catid=" + catid + ", page=" + page + ", ct=" + ct + ", ar=" + ar + ", zj=" + zj
				+ ", shi=" + shi + ", zx=" + zx + ", yt=" + yt + ", wy=" + wy + ", xq=" + xq + ", order=" + order
				+ ", kwds=" + kwds + ", mj=" + mj + ", qs=" + qs + ", ly=" + ly + ", dt=" + dt + ", cx=" + cx + ", lc="
				+ lc + ", zl=" + zl + ", kf=" + kf + ", fs=" + fs + ", lx=" + lx + ", sx=" + sx + "]";
	}

}
