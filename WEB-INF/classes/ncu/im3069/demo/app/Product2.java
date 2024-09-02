package ncu.im3069.demo.app;

import org.json.*;
import java.sql.Timestamp;

public class Product2 {


    private String id;
    private String name;
    private int price;
    private Timestamp deadline;
    private int category;
	private String describe;
	private String image;
	private int buyer;
	private int seller;
	private boolean isPaid;

    /**
     * 實例化（Instantiates）一個新的（new）Product 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增產品時
     *
     * @param id 產品編號
     */
	public Product2(String id) {
		this.id = id;
	}

    /**
     * 實例化（Instantiates）一個新的（new）Product 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增產品時
     *
     * @param name 產品名稱
     * @param price 產品價格
     * @param image 產品圖片
     */
	/*public Product2(String name, double price, String image) {
		this.name = name;
		this.price = price;
		this.image = image;
	}*/

    /**
     * 實例化（Instantiates）一個新的（new）Product 物件<br>
     * 採用多載（overload）方法進行，此建構子用於修改產品時
     *
     * @param id 產品編號
     * @param name 產品名稱
     * @param price 產品價格
     * @param image 產品圖片
     * @param describe 產品敘述
     */
	public Product2(String id,String name, int price,Timestamp deadline,int category, String describe, String image,int seller,boolean isPaid) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.deadline = deadline;
		this.category = category;
		this.image = image;
		this.describe = describe;
		this.seller = seller;
		this.isPaid = isPaid;
	}
	
	public Product2(String id,String name, int price,Timestamp deadline,int category, String describe, String image,int seller) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.deadline = deadline;
		this.category = category;
		this.image = image;
		this.describe = describe;
		this.seller = seller;
	}
	public Product2(String id, String name, int price,Timestamp deadline,int category, String describe, String image) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.deadline = deadline;
		this.category = category;
		this.image = image;
		this.describe = describe;
		//this.seller = seller;
	}
	public Product2(String id, String name, int price,Timestamp deadline,int category, String describe, String image,boolean isPaid,int buyer) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.deadline = deadline;
		this.category = category;
		this.image = image;
		this.describe = describe;
		this.isPaid = isPaid;
		this.buyer = buyer;
	}
	public Product2(String id, String name, int price,Timestamp deadline,int category, String describe, String image,boolean isPaid) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.deadline = deadline;
		this.category = category;
		this.image = image;
		this.describe = describe;
		this.isPaid = isPaid;
	}
	public Product2(String id, String name, int price,Timestamp deadline,int category, String describe, String image,int buyer,int seller) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.deadline = deadline;
		this.category = category;
		this.image = image;
		this.describe = describe;
		this.buyer=buyer;
		this.seller=seller;
	}
    /**
     * 取得產品編號
     *
     * @return  回傳產品編號
     */
	public String  getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	public int getPrice() {
		return this.price;
	}
	public Timestamp getDeadline() {
		return this.deadline;
	}
	public int getCategory() {
		return this.category;
	}
	public String getImage() {
		return this.image;
	}
	public String getDescribe() {
		return this.describe;
	}
	public int getBuyer() {
		return this.buyer;
	}
	public int getSeller() {
		return this.seller;
	}
	public boolean getisPaid() {
		return this.isPaid;
	}
    /**
     * 取得產品資訊
     *
     * @return JSONObject 回傳產品資訊
     */
	public JSONObject getData() {
        /** 透過JSONObject將該項產品所需之資料全部進行封裝*/
        JSONObject jso = new JSONObject();
        jso.put("id", getID());
        jso.put("name", getName());
        jso.put("price", getPrice());
        jso.put("deadline", getDeadline());
        jso.put("category", getCategory());
        jso.put("image", getImage());
        jso.put("describe", getDescribe());
        jso.put("buyer",getBuyer());
        jso.put("seller", getSeller());
        jso.put("isPaid", isPaid);
        return jso;
    }
}
