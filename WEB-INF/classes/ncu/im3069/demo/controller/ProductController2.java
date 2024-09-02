package ncu.im3069.demo.controller;

import java.awt.List;
import java.io.IOException;
//import java.security.Timestamp;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
//import org.apache.tomcat.util.buf.StringUtils;
//import org.apache.tomcat.util.codec.binary.StringUtils;
import org.json.JSONObject;

//import com.mysql.cj.util.StringUtils;
//import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;

import ncu.im3069.demo.app.Product2;
import ncu.im3069.demo.app.ProductHelper;
import ncu.im3069.demo.app.ProductHelper2;
import ncu.im3069.tools.JsonReader;
/**
 * Servlet implementation class ProductController2
 */
@WebServlet("/api/product2.do")
public class ProductController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductHelper2 ph =  ProductHelper2.getHelper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 JsonReader jsr = new JsonReader(request);
	        /** 若直接透過前端AJAX之data以key=value之字串方式進行傳遞參數，可以直接由此方法取回資料 */
		 	JSONObject jso = jsr.getObject();
		 	int fun = jso.getInt("function");
	        System.out.printf("\naaa%s",fun);
	        JSONObject resp = new JSONObject();
	        /** 判斷該字串是否存在，若存在代表要取回購物車內產品之資料，否則代表要取回全部資料庫內產品之資料 */
	        if(fun==3) {//add new product
	        	String id = jso.getString("id");
	        	int seller = jso.getInt("seller");
	        	String name = jso.getString("name");
	        	int price = jso.getInt("price");
	        	String describe = jso.getString("describe");
	        	String image = jso.getString("image");
	        	int category = jso.getInt("category");
	        	String deadline2 = jso.getString("deadline");
	        	deadline2 +=":00";
	        	Timestamp deadline = Timestamp.valueOf(deadline2.replace("T", " "));
	        	int temp = Integer.parseInt(id)+1;
	        	id = String.valueOf(temp);
	        	
	        	Product2 p = new Product2(id,name,price,deadline,category,describe,image,seller);
	        	
	        	JSONObject data = ph.create(p);
                
                /** 新建一個JSONObject用於將回傳之資料進行封裝 */
	        	resp = new JSONObject();
	        	resp.put("status", "200");
	        	resp.put("message", "成功! 新增商品...");
	        	resp.put("response", data);
                
                /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
	        	//jsr.response(resp, response);
	        }
	        else if (fun==2) {//get one product
		      String product_id = jso.getString("product_id");
	          JSONObject query = ph.getById(product_id);
	          resp.put("status", "200");
	          resp.put("message", "所有購物車之商品資料取得成功");
	          resp.put("response", query);
	        }
	        else if(fun ==6) {//update isPaid
	        	String product_id = jso.getString("product_id");
	        	ph.updateisPaid(product_id);
	        	resp.put("status", "200");
		        resp.put("message", "update isPaid success");
		        //resp.put("response", query);
	        }
	        else if(fun == 8) {//get category
	        	int category = jso.getInt("category");
	        	JSONObject query = ph.getCategory(category);
	        	resp.put("status", "200");
		        resp.put("message", "get my market success");
		        resp.put("response", query);
	        }
	        else if(fun ==7) {//seller get market product
	        	int member_id = jso.getInt("member_id");
	        	JSONObject query = ph.getMyMarket(member_id);
	        	resp.put("status", "200");
		        resp.put("message", "get my market success");
		        resp.put("response", query);
	        }
	        else if(fun ==4) {//get follow
	        	int member_id = jso.getInt("member_id");
	        	JSONObject query = ph.getFollow(member_id);

		         resp.put("status", "200");
		         resp.put("message", "所有Follow資料取得成功");
		         resp.put("response", query);
	        }
	        else if(fun==5) {
	        	int member_id = jso.getInt("member_id");
	        	JSONObject query = ph.getOwn(member_id);

		         resp.put("status", "200");
		         resp.put("message", "所有Follow資料取得成功");
		         resp.put("response", query);
	        }
	        else{//fun=1 getallproduct
	          JSONObject query = ph.getAll();

	          resp.put("status", "200");
	          resp.put("message", "所有商品資料取得成功");
	          resp.put("response", query);
	        }

	        jsr.response(resp, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject resp = new JSONObject();
		JsonReader jsr = new JsonReader(request);
	 	JSONObject jso = jsr.getObject();
	 	int fun = jso.getInt("fun");
	 	if(fun==1) {//update price
	 		String product_id = jso.getString("product_id");
		 	int price = jso.getInt("price");
		 	int member_id = jso.getInt("member_id");

		 	ph.update(member_id,product_id,price);
		 	resp.put("status", "200");
		 	jsr.response(resp, response);
	 	}
	 	else if(fun==2) {//update follow
	 		int member_id = jso.getInt("member_id");
	 		String product_id = jso.getString("product_id");
	 		
	 		ph.updateFollow(member_id,product_id);
	 		resp.put("status", "200");
		 	jsr.response(resp, response);
	 	}

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
