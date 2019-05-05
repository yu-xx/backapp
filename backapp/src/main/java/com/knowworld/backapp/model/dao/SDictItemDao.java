package com.knowworld.backapp.model.dao;

import com.knowworld.backapp.model.domain.SDictItem;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SDictItemDao extends PagingAndSortingRepository<SDictItem, Long>, JpaSpecificationExecutor {

    SDictItem findByDictItemIdAndDictId(Long dictItemId,Long dictId);
}
