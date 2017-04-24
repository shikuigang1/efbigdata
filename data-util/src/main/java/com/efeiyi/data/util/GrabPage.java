package com.efeiyi.data.util;

import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * Created by Administrator on 2017/4/11 0011.
 */
public class GrabPage {

    public static void main(String[] args) throws IOException, NoSuchFunctionException, XpathSyntaxErrorException, NoSuchAxisException{

        String x1 = "//h1[@id=\'title-page\']//text()";//获取标题名称
        String x2 = "//html/body/main/div/section/p[1]/a/text()";//国家名称
        String x3 ="//html/body/main/div/section/p[2]/text()";//副标题 需要处理出 申报年代
        String x4 ="//html/body/main/div/section/text()";//content
        String x5 ="//div[@id=\'slideshow\']/dl/dt/a/img/@src";//获取图片地址
        String updateSql = "";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String databaseName = "datatest";// 已经在MySQL数据库中创建好的数据库。
            String userName = "root";// MySQL默认的root账户名
            String password = "root";// 默认的root账户密码为空
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, userName, password);

            Statement stmt = conn.createStatement();
            Statement stmt1 = conn.createStatement();
            String sql = "SELECT * FROM ichpro";
            // 创建数据库中的表，
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String url = rs.getString("url");
                int id = rs.getInt("id");
                //System.out.println(url);
                Document doc = Jsoup.connect(url).timeout(50000).get();
                JXDocument jxDocument = new JXDocument(doc);

                String title = getString(jxDocument,x1);
                String country = getString(jxDocument,x2);
                String subtitle = getString(jxDocument,x3);

                subtitle = subtitle.replace("(","").replace(")","");
                String year="";
                if(subtitle.length()>17){
                     year = subtitle.substring(12,17);
                }else{
                    year="xxxx";
                }

                String content = getString(jxDocument,x4);

                List<String> ls  = getImgs(jxDocument,x5);

                updateSql ="UPDATE ichpro set title='"+StringEscapeUtils.escapeSql(title)+"', ";
                updateSql+="country='"+StringEscapeUtils.escapeSql(country)+"' ,";
                updateSql+="year='"+StringEscapeUtils.escapeSql(year)+"', ";
                updateSql+="subtitle='"+StringEscapeUtils.escapeSql(subtitle)+"', ";
                updateSql+="content='"+StringEscapeUtils.escapeSql(content)+"', ";
                String picture="";
                if(ls !=null && ls.size()>0) {
                    for(int i=0;i<ls.size();i++){
                        picture += ls.get(i)+";";
                    }
                }
                updateSql+="picture='"+picture+"' ";

                updateSql += " where id ="+id;

                stmt1.execute(updateSql);
            }


            conn.close();

        } catch (Exception e) {

            System.out.println(updateSql);
            e.printStackTrace();
        }




    }

    public  static String getString(JXDocument jxDocument,String xpath)throws IOException, NoSuchFunctionException, XpathSyntaxErrorException, NoSuchAxisException{

        List rs = jxDocument.sel(xpath);
        Object o=null;
        Iterator it = rs.iterator();
        while(it.hasNext()){
            o=it.next();
            //System.out.println(o.toString());
        }

        if(null != o ){
            return o.toString();
        }else{
            return "";
        }

    }

    public static List<String> getImgs(JXDocument jxDocument,String xpath)throws IOException, NoSuchFunctionException, XpathSyntaxErrorException, NoSuchAxisException{

        List rs = jxDocument.sel(xpath);

        List<String> imgs = new ArrayList<String>();
        Object o=null;
        Iterator it = rs.iterator();
        while(it.hasNext()){
            o=it.next();
            imgs.add(o.toString());
            //System.out.println(o.toString());
        }

        if(null != o ){
            return imgs;
        }else{
            return null;
        }

    }

    public static String httpRequest(String requestUrl){

        StringBuffer buffer = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        InputStream inputStream = null;
        HttpURLConnection httpUrlConn = null;
               try {
                        // 建立get请求
                        URL url = new URL(requestUrl);
                        httpUrlConn = (HttpURLConnection) url.openConnection();
                        httpUrlConn.setDoInput(true);
                        httpUrlConn.setRequestMethod("GET");

                        // 获取输入流
                        inputStream = httpUrlConn.getInputStream();
                        inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                        bufferedReader = new BufferedReader(inputStreamReader);

                        // 从输入流读取结果
                        buffer = new StringBuffer();
                        String str = null;
                        while ((str = bufferedReader.readLine()) != null) {
                             buffer.append(str);
                        }

               } catch (Exception e) {
                        e.printStackTrace();
               }  finally {
                       // 释放资源
                       if(bufferedReader != null) {
                              try {
                                       bufferedReader.close();
                                   } catch (IOException e) {
                                        e.printStackTrace();
                                   }
                           }
                        if(inputStreamReader != null){
                                try {
                                       inputStreamReader.close();
                                   } catch (IOException e) {
                                        e.printStackTrace();
                                   }
                            }
                       if(inputStream != null){
                                try {
                                        inputStream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                   }
                           }
                      if(httpUrlConn != null){
                             httpUrlConn.disconnect();
                            }
               }
               return buffer.toString();

    }


    private static HashMap<String,String> htmlFiter(String html) {

        HashMap<String,String> datamap = new HashMap<String, String>();

        String xpath="//h1/text()";
        //String doc = "...";
        JXDocument jxDocument = new JXDocument(html);
        List<Object> rs = null;
        try {
            rs = jxDocument.sel(xpath);
        } catch (NoSuchAxisException e) {
            e.printStackTrace();
        } catch (NoSuchFunctionException e) {
            e.printStackTrace();
        } catch (XpathSyntaxErrorException e) {
            e.printStackTrace();
        }
        for (Object o:rs){
            if (o instanceof Element){
                int index = ((Element) o).siblingIndex();
                System.out.println(index);
            }
            System.out.println(o.toString());
        }

        return null;
    }
}
