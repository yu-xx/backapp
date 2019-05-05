package com.knowworld.backapp.model.dao;

import com.knowworld.backapp.model.domain.KwArticle;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KwArticleDao extends PagingAndSortingRepository<KwArticle, Long>, JpaSpecificationExecutor {
}
