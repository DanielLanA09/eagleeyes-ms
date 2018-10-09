package com.eagleshing.sm.mappers;

import org.apache.ibatis.annotations.Select;

import com.eagleshing.sm.model.Cover;

public interface CoverMapper {
	
	@Select("select * from cover where id =#{id}")
	Cover findById(long id);
}
