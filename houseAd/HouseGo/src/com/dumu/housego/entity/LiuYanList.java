package com.dumu.housego.entity;

public class LiuYanList {
    private String id;
    private String from_uid;
    private String to_uid;
    private String from_status;
    private String to_status;
    private String inputtime;
    private String status;
    private String content;
    private String userpic;
    private String realname;
    private int ids;
    private int yidu;
    private String weidu_sum;


    public LiuYanList(String weidu_sum, String id, String from_uid, String to_uid, String from_status, String to_status, String inputtime,
                      String status, String content, String userpic, String realname, int ids, int yidu) {
        super();
        this.weidu_sum = weidu_sum;
        this.id = id;
        this.from_uid = from_uid;
        this.to_uid = to_uid;
        this.from_status = from_status;
        this.to_status = to_status;
        this.inputtime = inputtime;
        this.status = status;
        this.content = content;
        this.userpic = userpic;
        this.realname = realname;
        this.ids = ids;
        this.yidu = yidu;
    }

    public LiuYanList() {
        super();
    }


    public String getWeidu_sum() {
        return weidu_sum;
    }

    public void setWeidu_sum(String weidu_sum) {
        this.weidu_sum = weidu_sum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom_uid() {
        return from_uid;
    }

    public void setFrom_uid(String from_uid) {
        this.from_uid = from_uid;
    }

    public String getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(String to_uid) {
        this.to_uid = to_uid;
    }

    public String getFrom_status() {
        return from_status;
    }

    public void setFrom_status(String from_status) {
        this.from_status = from_status;
    }

    public String getTo_status() {
        return to_status;
    }

    public void setTo_status(String to_status) {
        this.to_status = to_status;
    }

    public String getInputtime() {
        return inputtime;
    }

    public void setInputtime(String inputtime) {
        this.inputtime = inputtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserpic() {
        if (userpic != null && userpic.equals("null"))
            return "";
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public String getRealname() {
        if (realname != null && realname.equals("null"))
            return null;
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public int getYidu() {
        return yidu;
    }

    public void setYidu(int yidu) {
        this.yidu = yidu;
    }

    @Override
    public String toString() {
        return "LiuYanList [id=" + id + ", from_uid=" + from_uid + ", to_uid=" + to_uid + ", from_status=" + from_status
                + ", to_status=" + to_status + ", inputtime=" + inputtime + ", status=" + status + ", content="
                + content + ", userpic=" + userpic + ", realname=" + realname + ", ids=" + ids + ", yidu=" + yidu
                + ", weidu_sum=" + weidu_sum + "]";
    }


}
