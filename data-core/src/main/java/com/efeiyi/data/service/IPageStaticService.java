package com.efeiyi.data.service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public interface IPageStaticService {

    //默认生成
    public void generatePage();


    public void generagePage(List lsit,String path);

    /**
     * @param ls 数据对象
     * @param templatePath 模板所在路径
     * @param filePath 生成文件路径
     */
    public void generagePage(List ls,String templatePath,String filePath);

    /**
     *
     * @param list 　数据列表对象
     * @param path　生成页面所放路径地址
     * class 数据对象类型           　
     */
    public void generatePage(List<Object> list,String path,Class c);

}
