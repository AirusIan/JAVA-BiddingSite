package ncu.im3069.demo.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.*;

import ncu.im3069.demo.app.Member;
import ncu.im3069.demo.app.Member2;
import ncu.im3069.demo.app.MemberHelper;
import ncu.im3069.demo.app.MemberHelper2;
import ncu.im3069.tools.JsonReader;
@WebServlet("/api/Member2.do")
public class MemberController2 extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private MemberHelper2 mh =  MemberHelper2.getHelper();
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
    	 JsonReader jsr = new JsonReader(request);
         JSONObject jso = jsr.getObject();
         
         /** 取出經解析到JSONObject之Request參數 */
         int fun = jso.getInt("function");
         
         if(fun ==1) {//register
        	 String email = jso.getString("email");
             String password = jso.getString("password");
             String name = jso.getString("name");
             String address = jso.getString("address");
     		 int account = jso.getInt("account");
     		 String follow = jso.getString("follow");
     		 String bid = jso.getString("bid");
             
             /** 建立一個新的會員物件 */
     		 Member2 m = new Member2(email, password, name, account, address,follow,bid);
                
                /** 後端檢查是否有欄位為空值，若有則回傳錯誤訊息 */
             if(email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                    /** 以字串組出JSON格式之資料 */
            	 String resp = "{\"status\": \'400\', \"message\": \'欄位不能有空值\', \'response\': \'\'}";
                    /** 透過JsonReader物件回傳到前端（以字串方式） */
            	 jsr.response(resp, response);
             }
                /** 透過MemberHelper物件的checkDuplicate()檢查該會員電子郵件信箱是否有重複 */
             else if (!mh.checkDuplicate(m)) {
                    /** 透過MemberHelper物件的create()方法新建一個會員至資料庫 */
            	 JSONObject data = mh.create(m);
                    
                    /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            	 JSONObject resp = new JSONObject();
                 resp.put("status", "200");
                 resp.put("message", "成功! 註冊會員資料...");
                 resp.put("response", data);
                    
                    /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
                 jsr.response(resp, response);
              }
              else {
                    /** 以字串組出JSON格式之資料 */
            	  String resp = "{\"status\": \'400\', \"message\": \'新增帳號失敗，此E-Mail帳號重複！\', \'response\': \'\'}";
                    /** 透過JsonReader物件回傳到前端（以字串方式） */
                  jsr.response(resp, response);
                }
         }
         else {//login
        	String email = jso.getString("email");//login in email
            String password = jso.getString("password");//login in password
        	 
			JSONObject query = mh.getByEmail(email);
			String email2 = query.getString("email");//db email
			String password2 = query.getString("password");//db password
			int id = query.getInt("id");
			String name = query.getString("name");
			String address = query.getString("address");
			String bid = query.getString("bid");
			String follow = query.getString("follow");
			int account = query.getInt("account");
			
			JSONObject resp = new JSONObject();
			
			
			if(email.equals(email2) && password.equals(password2)) {//login success
			     resp.put("status", "200");//i wrote it  
			     resp.put("message", "成功登入");
			     resp.put("id", id);
			     resp.put("name", name);
			     resp.put("address", address);
			     resp.put("account", account);
			     resp.put("follow", follow);
			     resp.put("bid", bid);
			     resp.put("email", email);
			     resp.put("password", password);
			     jsr.response(resp, response);
			} else {
				resp.put("message", "密碼錯誤或無此會員!");
				jsr.response(resp, response);
			}
         	
         }
         
    }


    /**
     * 處理Http Method請求GET方法（取得資料）*/
     
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        /** 若直接透過前端AJAX之data以key=value之字串方式進行傳遞參數，可以直接由此方法取回資料 */
        String id = jsr.getParameter("id");
        
      
        if (id.isEmpty()) {
            /** 透過MemberHelper物件之getAll()方法取回所有會員之資料，回傳之資料為JSONObject物件 */
            JSONObject query = mh.getAll();
            
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "所有會員資料取得成功");
            resp.put("response", query);
    
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        }
        else {
            /** 透過MemberHelper物件的getByID()方法自資料庫取回該名會員之資料，回傳之資料為JSONObject物件 */
            JSONObject query = mh.getByID(id);
            
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "會員資料取得成功");
            resp.put("response", query);
    
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        }
    }

    /**
      處理Http Method請求DELETE方法（刪除）!!!不刪除了*/ 
  
     
    
    /**
     * 更新會員資料  */
   
    public void doPut(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        int id = jso.getInt("id");
        String email = jso.getString("email");
        String password = jso.getString("password");
        String name = jso.getString("name");
		String address = jso.getString("address");
		int account = jso.getInt("account");
		//String follow = jso.getString("follow");
		//String bid = jso.getString("bid");

        /** 透過傳入之參數，新建一個以這些參數之會員Member物件 */
        Member2 m = new Member2(id, email, password, name, account, address);
        
        /** 透過Member物件的update()方法至資料庫更新該名會員資料，回傳之資料為JSONObject物件 */
        JSONObject data = m.update();
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功! 更新會員資料...");
        resp.put("response", data);
        
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }
}