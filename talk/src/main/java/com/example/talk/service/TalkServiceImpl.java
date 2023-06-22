package com.example.talk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.talk.dao.TalkDao;
import com.example.talk.dto.Talk;

@Service
public class TalkServiceImpl implements TalkService{

	@Autowired //의존성 주입
	private TalkDao boardMapper; 
	
	@Override
	public int maxNum() throws Exception {
		return boardMapper.maxNum();
	}

	@Override
	public void insertData(Talk talk) throws Exception {
		boardMapper.insertData(talk);
	}

	@Override
	public int getDataCount(String searchKey, String searchValue) throws Exception {
		
		return boardMapper.getDataCount(searchKey, searchValue);
	}

	@Override
	public List<Talk> getLists(String searchKey, String searchValue, int start, int end) throws Exception {
		
		return boardMapper.getLists(searchKey, searchValue, start, end);
	}

	@Override
	public void updateHitCount(int num) throws Exception {
		boardMapper.updateHitCount(num);
		
	}

	@Override
	public Talk getReadData(int num) throws Exception {
		
		return boardMapper.getReadData(num);
	}

	@Override
	public void updateData(Talk talk) throws Exception {
		boardMapper.updateData(talk);
		
	}

	@Override
	public void deleteData(int num) throws Exception {
		boardMapper.deleteData(num);
		
	}

	
}
