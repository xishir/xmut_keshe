package com.xmut.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmut.dao.CommentMapper;
import com.xmut.pojo.CommentInfo;

@Service
public class CommentService {
	@Autowired
	private CommentMapper commentmapper;
	public void  createcomment(Map<String, Object> reqMap) {
		commentmapper.createcomment(reqMap);
		
	}
	public ArrayList<CommentInfo> getComments(String article_id){
		return commentmapper.getComments(article_id);
		
	}
	public void addStar(String id) {
		commentmapper.addStar(id);
	}
	public void addDiss(String id) {
		commentmapper.addDiss(id);
	}
	public void deleteCommentById(CommentInfo commentinfo) {
		commentmapper.deleteCommentById(commentinfo);
	}

}
