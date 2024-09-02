package ncu.im3069.demo.app;

import org.json.*;
import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class Member
 * Member類別（class）具有會員所需要之屬性與方法，並且儲存與會員相關之商業判斷邏輯<br>
 * </p>
 * 
 * @author IPLab
 * @version 1.0.0
 * @since 1.0.0
 */

public class Member2 {
    
    /** id，會員編號 */
    private int id;
    
    /** email，會員電子郵件信箱 */
    private String email;
    
    /** name，會員姓名 */
    private String name;
    
    /** password，會員密碼 */
    private String password;
    
    private String address;
    
    private int account;
    
    private String follow;
    
    private String bid;
    
    
    
   
    
    /** mh，MemberHelper之物件與Member相關之資料庫方法（Sigleton） */
    private MemberHelper2 mh =  MemberHelper2.getHelper();
    
    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於建立會員資料時，產生一名新的會員
     *
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     */
    public Member2(String email, String password, String name,int account,String address ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.account = account;
       
        //update();
    }
    public Member2(int id,String email, String password, String name,int account,String address ) {
        this.id = id;
    	this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.account = account;
       
        update();
    }
    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於更新會員資料時，產生一名會員同時需要去資料庫檢索原有更新時間分鐘數與會員組別
     * 
     * @param id 會員編號
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     */
    public Member2(int id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        /** 取回原有資料庫內該名會員之更新時間分鐘數與組別 */
        
        /** 計算會員之組別 */
    
    }
    
    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於查詢會員資料時，將每一筆資料新增為一個會員物件
     *
     * @param id 會員編號
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名

     */
    public Member2( String email, String password, String name, int account, String address ,String follow, String bid) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.account = account;
        this.follow = follow;
        this.bid = bid;

    }
    public Member2(int id, String email, String password, String name, int account, String address ,String follow, String bid) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.account = account;
        this.follow = follow;
        this.bid = bid;

    }
    
    /**
     * 取得會員之編號
     *
     * @return the id 回傳會員編號
     */
    public int getID() {
        return this.id;
    }
    public String getEmail() {
        return this.email;
    }
    public String getName() {
        return this.name;
    }
    public String getPassword() {
        return this.password;
    }
    public String getAddress() {
        return this.address;
    }
    
    public int getAccount() {
        return this.account;
    }
    
    public String getFollow() {
        return this.follow;
    }
    
    public String getBid() {
        return this.bid;
    }
    


    
    /**
     * 更新會員資料
     *
     * @return the JSON object 回傳SQL更新之結果與相關封裝之資料
     */
    public JSONObject update() {
        /** 新建一個JSONObject用以儲存更新後之資料 */
        JSONObject data = new JSONObject();
        /** 取得更新資料時間（即現在之時間）之分鐘數 */
        //Calendar calendar = Calendar.getInstance();
        /** 檢查該名會員是否已經在資料庫 */
        if(this.id != 0) {
            /** 若有則將目前更新後之資料更新至資料庫中 */
            //mh.updateLoginTimes(this);
            /** 透過MemberHelper物件，更新目前之會員資料置資料庫中 */
            data = mh.update(this);
        }
        
        return data;
    }
    
    /**
     * 取得該名會員所有資料
     *
     * @return the data 取得該名會員之所有資料並封裝於JSONObject物件內
     */
    public JSONObject getData() {
        /** 透過JSONObject將該名會員所需之資料全部進行封裝*/ 
        JSONObject jso = new JSONObject();
        jso.put("id", getID());
        jso.put("name", getName());
        jso.put("email", getEmail());
        jso.put("password", getPassword());
        jso.put("account", getAccount());
        jso.put("address", getAddress());
        jso.put("follow", getFollow());
        jso.put("bid", getBid());
        
        return jso;
    }


}