package com.xmut.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xmut.dao.ArticleMapper;
import com.xmut.dao.TagMapper;
import com.xmut.pojo.ArticleInfo;
import com.xmut.util.XssUtil;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagMapper tagmapper;

    public ArrayList<ArticleInfo> getList(Integer page) {
    	PageHelper.startPage(page,10);
        return articleMapper.getArticles();
    }
    
    public ArrayList<ArticleInfo> getList_Hot(Integer page) {
    	PageHelper.startPage(page,10);
        return articleMapper.getArticles_Hot();
    }
    
    public ArticleInfo getInfo(String id) {
    	articleMapper.addVisit(id);
    	ArticleInfo article=articleMapper.getArticle(id);
    	String frontid=articleMapper.getFrontID(id);
    	String behindid=articleMapper.getBehindID(id);
    	if(frontid==null)
    		frontid=id;
    	if(behindid==null)
    		behindid=id;
    	article.setFrontid(frontid);
    	article.setBehindid(behindid);
        return article;
    }
    
    public String createArticle(Map<String, Object> reqMap)
    {
    	String sort=XssUtil.cleanXSS(reqMap.get("sort").toString());
		String content=XssUtil.cleanXSS2(reqMap.get("content").toString());
		String title=XssUtil.cleanXSS(reqMap.get("title").toString());
		String author=XssUtil.cleanXSS(reqMap.get("author").toString());
		String time=XssUtil.cleanXSS(reqMap.get("time").toString());
		reqMap.put("sort", sort);
		reqMap.put("title", title);
		reqMap.put("author", author);
		reqMap.put("author", author);
		reqMap.put("time", time);

    	String tag=(String) reqMap.get("sort");
    	String[] strArray = null;   
        strArray = tag.split(","); //拆分字符为"," ,然后把结果交给数组strArray 
    	articleMapper.createArticle(reqMap);
    	ArrayList<String> tagsort=tagmapper.getSort();
    	int k=0;
    	for(int i=0;i<strArray.length;i++)
    	{
    		k=0;
    		if(tagsort.size()==0)
    		{
    			tagmapper.createtag(strArray[i]);
    			tagsort=tagmapper.getSort();
    		}
    		else
    		{
    			for(int j=0;j<tagsort.size();j++) 
        		{
        			if(strArray[i].equals(tagsort.get(j)))
        			{
        				tagmapper.addNum(tagsort.get(j));
        				k=1;
        				break;
        			}
        			
        		}
    			if(k==0)
    			{
    				tagmapper.createtag(strArray[i]);
    			}
    		}
    	}   	
    	return "success";	
    }
    
    public String editArticle(Map<String, Object> reqMap)
    {
    	String tag=(String) reqMap.get("sort");
    	String[] strArray = null;   
        strArray = tag.split(","); //拆分字符为"," ,然后把结果交给数组strArray 
    	articleMapper.UpdataArticle(reqMap);
    	ArrayList<String> tagsort=tagmapper.getSort();
    	int k=0;
    	for(int i=0;i<strArray.length;i++)
    	{
    		k=0;
    		if(tagsort.size()==0)
    		{
    			tagmapper.createtag(strArray[i]);
    			tagsort=tagmapper.getSort();
    		}
    		else
    		{
    			for(int j=0;j<tagsort.size();j++) 
        		{
        			if(strArray[i].equals(tagsort.get(j)))
        			{
        				tagmapper.addNum(tagsort.get(j));
        				k=1;
        				break;
        			}
        			
        		}
    			if(k==0)
    			{
    				tagmapper.createtag(strArray[i]);
    			}
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
    
    public void deleteArticleById(String id)
    {
    	articleMapper.deleteArticleById(id);
    }
    
    public int getPageNum()
    {
    	String num=articleMapper.getPageNum();
    	return (Integer.parseInt(num)/10)+1;
    }
    
    public int getArticleNum()
    {
    	String num=articleMapper.getPageNum();
    	return Integer.parseInt(num);
    }
    //增加评论数
    public void addComment(String id)
    {
    	articleMapper.addComment(id);
    }
    
    
}
