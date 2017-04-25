package com.dumu.housego.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Street implements Serializable {
	private String pid;
	private String id;
	private double latitude;
	private double longitude;
	private String name;

	public Street(String pid, String id, double latitude, double longitude,
			String name) {
		super();
		this.pid = pid;
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;

	}

	public Street() {
		super();
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Street [id=" + id + ", latitude=" + latitude + ", longitude="
				+ longitude + ", name=" + name + "]";
	}

	/*
	 * locations.add(new shenZhenLocation("2",114.137543, 22.555234, "罗湖区"));
	 * locations.add(new shenZhenLocation("4",114.061087, 22.528578, "福田区"));
	 * locations.add(new shenZhenLocation("5",113.93695, 22.539017, "南山区"));
	 * locations.add(new shenZhenLocation("6",114.243911, 22.564199, "盐田区"));
	 * locations.add(new shenZhenLocation("7",113.889506, 22.559626, "宝安区"));
	 * locations.add(new shenZhenLocation("8",114.349555, 22.687449, "龙岗新区"));
	 * locations.add(new shenZhenLocation("9",114.039945, 22.661746, "龙华新区"));
	 * locations.add(new shenZhenLocation("10",113.923662, 22.779082, "光明新区"));
	 * locations.add(new shenZhenLocation("11",114.349555, 22.687449, "坪山新区"));
	 * locations.add(new shenZhenLocation("12",114.493599, 22.578712, "大鹏新区"));
	 * locations.add(new shenZhenLocation("13",113.758231, 23.026997, "东莞"));
	 * locations.add(new shenZhenLocation("14",114.423348, 23.116409, "惠州"));
	 */
	public static List<Street> streets = new ArrayList<Street>();
	static {
		// "罗湖区" - pid=2
		streets.add(new Street("2", "15", 22.577933, 114.14636, "水库"));
		streets.add(new Street("2", "16", 22.568807, 114.179631, "莲塘"));
		streets.add(new Street("2", "17", 22.571392, 114.139723, "翠竹"));
		streets.add(new Street("2", "18", 22.55442, 114.128569, "东门"));
		streets.add(new Street("2", "19", 22.559741, 114.145866, "黄贝岭"));
		streets.add(new Street("2", "20", 22.559741, 114.145866, "蔡屋围"));
		streets.add(new Street("2", "21", 22.571544, 114.128059, "洪湖"));
		streets.add(new Street("2", "22", 22.571083, 114.118472, "笋岗"));
		streets.add(new Street("2", "23", 22.588163, 114.131581, "布心"));
		streets.add(new Street("2", "24", 23.08247756958008, 113.875617980957,
				"人民南"));
		streets.add(new Street("2", "25", 23.08247756958008, 113.875617980957,
				"泥岗"));
		streets.add(new Street("2", "26", 23.08247756958008, 113.875617980957,
				"宝安南"));
		streets.add(new Street("2", "27", 22.584577207172, 114.09261483899,
				"银湖"));

		// "福田区" - pid=4
		streets.add(new Street("4", "28", 22.567395, 114.105686, "八卦岭"));
		streets.add(new Street("4", "29", 22.529288, 114.042469, "上下沙"));
		streets.add(new Street("4", "30", 22.575051, 114.047817, "梅林"));
		streets.add(new Street("4", "31", 22.530106, 114.050679, "新洲"));
		streets.add(new Street("4", "32", 22.51244900364, 114.05740789396,
				"保税区"));
		streets.add(new Street("4", "33", 22.54433, 114.068631, "福田中心区"));
		streets.add(new Street("4", "34", 22.550560856132, 114.09093837029,
				"华强"));
		streets.add(new Street("4", "35", 22.553766, 114.033166, "香蜜湖"));
		streets.add(new Street("4", "36", 22.527719, 114.074628, "皇岗"));
		streets.add(new Street("4", "37", 22.539264, 114.034639, "车公庙"));
		streets.add(new Street("4", "38", 22.561803, 114.068676, "莲花"));
		streets.add(new Street("4", "39", 23.08247756958008, 113.875617980957,
				"上步"));
		streets.add(new Street("4", "40", 22.55784, 114.050261, "景田"));
		streets.add(new Street("4", "41", 23.08247756958008, 113.875617980957,
				"石厦"));
		streets.add(new Street("4", "42", 23.08247756958008, 113.875617980957,
				"农科中心"));
		streets.add(new Street("4", "43", 22.549996, 114.01132, "竹子林"));
		streets.add(new Street("4", "44", 22.568307, 114.087294, "笔架山"));

		// "南山区" - pid=5
		streets.add(new Street("5", "45", 22.547214, 113.985865, "华侨城"));
		streets.add(new Street("5", "46", 22.516238, 113.939118, "后海"));
		streets.add(new Street("5", "47", 22.549232, 113.954273, "科技园"));
		streets.add(new Street("5", "48", 22.552238, 113.931872, "南头"));
		streets.add(new Street("5", "49", 23.08247756958008, 113.875617980957,
				"南油"));
		streets.add(new Street("5", "50", 22.520152, 113.92576, "南山中心区"));
		streets.add(new Street("5", "51", 22.522585, 113.902236, "前海"));
		streets.add(new Street("5", "52", 22.489944, 113.919881, "蛇口"));
		streets.add(new Street("5", "53", 22.57735, 113.956204, "西丽"));

		// "盐田区" - pid=6
		streets.add(new Street("6", "54", 22.56503410211, 114.23296227279,
				"沙头角"));
		streets.add(new Street("6", "55", 22.597845, 114.270382, "盐田港"));
		streets.add(new Street("6", "56", 22.603342, 114.31291, "梅沙"));

		// "宝安区" - pid=7
		streets.add(new Street("7", "57", 22.562266, 113.89565, "宝安中心区"));
		streets.add(new Street("7", "58", 22.686748, 113.819329, "福永"));
		streets.add(new Street("7", "59", 23.08247756958008, 113.875617980957,
				"石岩"));
		streets.add(new Street("7", "60", 22.791961262185, 113.84573771383,
				"松岗"));
		streets.add(new Street("7", "61", 22.736955, 113.813831, "沙井"));
		streets.add(new Street("7", "62", 22.600191, 113.858245, "西乡"));
		streets.add(new Street("7", "63", 22.580537, 113.914223, "新安"));

		// "龙岗新区" - pid=8
		streets.add(new Street("8", "64", 22.629117454088, 114.07449072316,
				"坂田"));
		streets.add(new Street("8", "65", 22.635423, 114.133659, "布吉"));
		streets.add(new Street("8", "66", 22.656642, 114.206821, "横岗"));
		streets.add(new Street("8", "67", 22.689232, 114.132299, "平湖"));
		streets.add(new Street("8", "68", 22.765347, 114.311051, "坪地"));
		streets.add(new Street("8", "69", 22.719931, 114.241984, "龙岗中心城"));

		// "龙华新区" - pid=9
		streets.add(new Street("9", "70", 22.657455, 114.032227, "龙华"));
		streets.add(new Street("9", "71", 22.685307, 114.012792, "大浪"));
		streets.add(new Street("9", "72", 22.622584720476, 114.04786064158,
				"民治"));
		streets.add(new Street("9", "73", 22.711183, 114.062906, "观澜"));

		// "光明新区" - pid=10
		streets.add(new Street("10", "74", 22.757803, 113.923689, "公明"));
		streets.add(new Street("10", "75", 23.08247756958008, 113.875617980957,
				"光明"));

		// "坪山新区" - pid=11
		streets.add(new Street("11", "76", 22.70512, 114.355493, "坪山"));
		streets.add(new Street("11", "77", 23.08247756958008, 113.875617980957,
				"坑梓"));

		// "大鹏新区" - pid=12
		streets.add(new Street("12", "78", 22.631135, 114.479045, "大鹏"));
		streets.add(new Street("12", "79", 23.08247756958008, 113.875617980957,
				"南澳"));
		streets.add(new Street("12", "80", 23.08247756958008, 113.875617980957,
				"葵涌"));

		// "东莞" - pid=13
		streets.add(new Street("13", "81", 23.05971717834473,
				113.7574920654297, "莞城"));
		streets.add(new Street("13", "82", 23.02432060241699,
				113.7506408691406, "南城"));
		streets.add(new Street("13", "83", 23.03434562683105,
				113.7896881103516, "东城"));
		streets.add(new Street("13", "84", 23.05331611633301,
				113.7437362670898, "万江"));
		streets.add(new Street("13", "85", 23.11161422729492, 113.880729675293,
				"石龙镇"));
		streets.add(new Street("13", "86", 23.09485244750977,
				113.9465484619141, "石排镇"));
		streets.add(new Street("13", "87", 23.08247756958008, 113.875617980957,
				"茶山镇"));
		streets.add(new Street("13", "88", 23.07903671264648,
				114.0285339355469, "企石镇"));
		streets.add(new Street("13", "89", 23.03007888793945,
				114.1096038818359, "桥头镇"));
		streets.add(new Street("13", "90", 22.99509429931641,
				113.9546508789062, "东坑镇"));
		streets.add(new Street("13", "91", 23.02480888366699,
				113.9729919433594, "横沥镇"));
		streets.add(new Street("13", "92", 22.98104667663574, 113.99951171875,
				"常平镇"));
		streets.add(new Street("13", "93", 22.82064819335938,
				113.6793212890625, "虎门镇"));
		streets.add(new Street("13", "94", 22.8210391998291, 113.8090362548828,
				"长安镇"));
		streets.add(new Street("13", "95", 22.92527008056641,
				113.6240844726562, "沙田镇"));
		streets.add(new Street("13", "96", 22.94132232666016,
				113.6767959594727, "厚街镇"));
		streets.add(new Street("13", "97", 23.00370979309082,
				113.8812713623047, "寮步镇"));
		streets.add(new Street("13", "98", 22.86803245544434,
				113.8093566894531, "大岭山镇"));
		streets.add(new Street("13", "99", 22.94565773010254,
				113.9505844116211, "大朗镇"));
		streets.add(new Street("13", "100", 22.92128562927246,
				114.0084075927734, "黄江镇"));
		streets.add(new Street("13", "101", 22.92082786560059,
				114.0897827148438, "樟木头镇"));
		streets.add(new Street("13", "102", 23.08247756958008,
				113.875617980957, "谢岗镇"));
		streets.add(new Street("13", "103", 23.08247756958008,
				113.875617980957, "塘厦镇"));
		streets.add(new Street("13", "104", 22.85030746459961,
				114.1708908081055, "清溪镇"));
		streets.add(new Street("13", "105", 22.74981689453125,
				114.1547164916992, "凤岗镇"));
		streets.add(new Street("13", "106", 23.05708312988281, 113.58837890625,
				"麻涌镇"));
		streets.add(new Street("13", "107", 23.09864234924316,
				113.663932800293, "中堂镇"));
		streets.add(new Street("13", "108", 23.09723854064941,
				113.7523574829102, "高埗镇"));
		streets.add(new Street("13", "109", 23.10500717163086,
				113.8198165893555, "石碣镇"));
		streets.add(new Street("13", "110", 23.06159782409668,
				113.6626358032227, "望牛墩镇"));
		streets.add(new Street("13", "111", 23.00064277648926,
				113.6154403686523, "洪梅镇"));
		streets.add(new Street("13", "112", 23.01024627685547,
				113.6817169189453, "道滘镇"));
		streets.add(new Street("13", "113", 22.89933013916016,
				113.8717041015625, "松山湖"));

		// "惠州" - pid=14
		streets.add(new Street("14", "114", 23.08990478515625,
				114.3892974853516, "惠城区"));
		streets.add(new Street("14", "115", 22.7946834564209,
				114.4630279541016, "惠阳区"));
		streets.add(new Street("14", "116", 22.99128150939941,
				114.7264862060547, "惠东县"));
		streets.add(new Street("14", "117", 23.17886924743652,
				114.296272277832, "博罗县"));
		streets.add(new Street("14", "118", 23.08247756958008,
				113.875617980957, "龙门县"));
		streets.add(new Street("14", "119", 22.78518676757812,
				114.6927947998047, "大亚湾"));
		streets.add(new Street("14", "120", 23.08209609985352,
				114.3903427124023, "仲恺"));
		streets.add(new Street("14", "121", 23.08247756958008,
				113.875617980957, "河源"));
	}

	static public List<String> getSubArea(String pid) {
		List<String> subs = new ArrayList<String>();
		for (Street s : streets) {
			if (s.getPid().equals(pid)) {
				subs.add(s.getName());
			}
		}
		return subs;
	}
}
