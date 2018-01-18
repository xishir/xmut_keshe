package com.xmut.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmut.dao.ArticleMapper;
import com.xmut.dao.TagMapper;
import com.xmut.pojo.ArticleInfo;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagMapper tagmapper;

    public ArrayList<ArticleInfo> getList(Integer page) {
    	System.out.println("调用getArticles");
        return articleMapper.getArticles();
    }
    
    public ArrayList<ArticleInfo> getList_Hot(Integer page) {
    	System.out.println("调用getArticles");
        return articleMapper.getArticles_Hot();
    }
    
    public ArticleInfo getInfo(String id) {
    	System.out.println("aaa");
    	articleMapper.addVisit(id);
        return articleMapper.getArticle(id);
    }
    
    public String createArticle(Map<String, Object> reqMap)
    {
    	String tag=(String) reqMap.get("sort");
    	String[] strArray = null;   
        strArray = tag.split(","); //拆分字符为"," ,然后把结果交给数组strArray 
    	articleMapper.createArticle(reqMap);
    	ArrayList<String> tagsort=tagmapper.getSort();
    	for(int i=0;i<strArray.length;i++) {
    		for(int j=0;j<tagsort.size();j++) {
    			if(strArray[i].equals(tagsort.get(j)))
    				break;
    			else 
    				tagmapper.createtag(strArray[i]);
    				    		}
    	}   	
    	return "success";
    }
    
    //增加浏览数
    public void addVisit(String id)
    {
    	articleMapper.addVisit(id);
    }
    
    //增加点赞数
    public void addStar(String id)
    {
    	articleMapper.addStar(id);;
    }
    
    
    
    
}