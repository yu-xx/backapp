package com.knowworld.backapp.model.mapper;

import java.util.List;

import com.knowworld.backapp.model.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

	List<Member> findAll();
}
