package com.efeiyi.data.page;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.efeiyi.data.service.*;

import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

@Service("pageService")
public class PageGenserviceImpl implements IPageStaticService{


    public void generatePage(){
        Logger.getLogger(PageGenserviceImpl.class).info("generate static page!");
    }

    public void generagePage(List ls, String path)  {

        //创建一个freemarker.template.Configuration实例，它是存储 FreeMarker 应用级设置的核心部分
        //指定版本号
        Configuration cfg=new Configuration(Configuration.VERSION_2_3_22);
        //设置模板目录
        try {
            cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
            //设置默认编码格式
            cfg.setDefaultEncoding("UTF-8");
            //从设置的目录中获得模板
            Template temp = cfg.getTemplate("product.ftl");

            //合并模板和数据模型
            //Writer out = new OutputStreamWriter(new FileOutputStream(path+"/filename"));
            Writer out = new OutputStreamWriter(new FileOutputStream(path+"/filename"));

            for (Object obj:ls) {
                temp.process(obj, out);
            }

            //关闭
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    public void generagePage(List ls, String templatePath, String filePath) {

    }

    public void generatePage(List<Object> list, String path, Class c) {

    }

    public static void main(String[] args){
        IPageStaticService pageStaticService = new PageGenserviceImpl();

        List ls = new ArrayList();

        //数据
        Map<String, Object> product = new HashMap<String, Object>();
        product.put("name", "Huwei P8");
        product.put("price", "3985.7");
        product.put("users", new String[]{"Tom","Jack","Rose"});
        product.put("book","struts2,freemarker");
        product.put("id",1);

        ls.add(product);

        pageStaticService.generagePage(ls,"/web/page");
    }
}
