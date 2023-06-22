package com.example.talk.service;

import java.util.List;

import com.example.talk.dto.Talk;

public interface TalkService {
	public int maxNum() throws Exception;

	public void insertData(Talk talk) throws Exception;

	public int getDataCount(String searchKey, String searchValue) throws Exception;

	public List<Talk> getLists(String searchKey, String searchValue, int start, int end) throws Exception;
	
	public void updateHitCount(int num) throws Exception;
	
	public Talk getReadData(int num) throws Exception;
	
	public void updateData(Talk talk) throws Exception;
	
	public void deleteData(int num) throws Exception;
}
