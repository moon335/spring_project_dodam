package com.dodam.hotel.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReplyRepository {

	public int insert(@Param("content") String content,
			@Param("questionId") Integer questionId, @Param("managerId") Integer managerId);	
	public int deleteReply(Integer questionId);
}
