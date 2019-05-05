package com.knowworld.backapp.service;

import com.knowworld.backapp.common.DataGrid;
import com.knowworld.backapp.common.DictConstants;
import com.knowworld.backapp.common.MySpecification;
import com.knowworld.backapp.dto.ArticlePageDto;
import com.knowworld.backapp.model.dao.KwArticleDao;
import com.knowworld.backapp.model.domain.KwArticle;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class KwArticleService {

    /**
     * 字典DAO
     */
    @Autowired
    private KwArticleDao articleDao;

    @Autowired
    private SDictItemService dictItemService;

    /**
     * 分页查询
     * @param page   分页参数
     * @param rows   分页参数
     * @param type  类型
     * @param title  标题
     */
    public DataGrid<ArticlePageDto> page(Integer page, Integer rows, Integer type, String title){
        PageRequest pr = new PageRequest(page - 1, rows);
        Page pageData = articleDao.findAll(new MySpecification<KwArticle>().and(
                MySpecification.Cnd.eq("type", type),
                MySpecification.Cnd.like("title", title)
        ).desc("operTime"), pr);
        return this.transKwArticle(new DataGrid<KwArticle>(pageData));
    }

    private DataGrid<ArticlePageDto> transKwArticle(DataGrid<KwArticle> articleDataGrid){
        List<ArticlePageDto> dtoList = new ArrayList<>();
        Map<Integer, String> typeDictMap = dictItemService.getDictItemMap(DictConstants.ARTICLE_TYPE);
        for (KwArticle article : articleDataGrid.getRows()) {
            ArticlePageDto dto = new ArticlePageDto();
            dtoList.add(dto);
            BeanUtils.copyProperties(article,dto);
            dto.setTypeName(typeDictMap.get(article.getType()));
        }
        return new DataGrid<>(dtoList);
    }
}
