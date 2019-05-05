package com.knowworld.backapp.controller.kw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowworld.backapp.common.AjaxResult;
import com.knowworld.backapp.common.DataGrid;
import com.knowworld.backapp.common.DictConstants;
import com.knowworld.backapp.dto.ArticlePageDto;
import com.knowworld.backapp.model.dao.KwArticleDao;
import com.knowworld.backapp.model.domain.KwArticle;
import com.knowworld.backapp.model.domain.SDictItem;
import com.knowworld.backapp.service.KwArticleService;
import com.knowworld.backapp.service.SDictItemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/kw/article")
public class KwArticleController {

    Logger logger = Logger.getLogger(KwArticleController.class);

    @Autowired
    private KwArticleDao articleDao;

    @Autowired
    private KwArticleService articleService;
    @Autowired
    private SDictItemService dictItemService;

    @RequestMapping
    public void index() {
    }

    @RequestMapping("/page")
    @ResponseBody
    public DataGrid<ArticlePageDto> page(Integer page, Integer rows, Integer type, String title) {
        return articleService.page(page,rows,type,title);
    }


    @RequestMapping("/form")
    public void form(Long articleId, Model model){
        ObjectMapper mapper = new ObjectMapper();
        if(null != articleId){
            KwArticle article = articleDao.findOne(articleId);
            try{
                model.addAttribute("article",mapper.writeValueAsString(article));
            }catch (JsonProcessingException e){
                logger.error("json转换错误",e);
            }
        }
    }

    @RequestMapping({"/save", "/update"})
    @Transactional
    @ResponseBody
    public Object save(@Valid KwArticle article, BindingResult br){
        if (br.hasErrors()) {
            logger.error("对象校验失败：" + br.getAllErrors());
            return new AjaxResult(false).setData(br.getAllErrors());
        }
        if(null != article.getArticleId()){
            KwArticle oldArticle = articleDao.findOne(article.getArticleId());
            if(null == oldArticle){
                return new AjaxResult(false,"内容不存在！");
            }
        }
        return articleDao.save(article);
    }

    @RequestMapping("/delete")
    @Transactional
    @ResponseBody
    public AjaxResult delete(Long articleId){
        try{
            articleDao.delete(articleId);
        }catch (Exception e){
            return new AjaxResult().setMessage(e.getMessage());
        }
        return new AjaxResult();
    }

    @RequestMapping("/type/list")
    @ResponseBody
    public Iterable<SDictItem> listTypes(){
        return dictItemService.getDictItemList(DictConstants.ARTICLE_TYPE);
    }
}
