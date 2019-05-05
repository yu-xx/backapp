package com.knowworld.backapp.model.mapper;

import com.knowworld.backapp.model.domain.SDict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SDictMapper {
    List<SDict> list(SDict dict);
}
