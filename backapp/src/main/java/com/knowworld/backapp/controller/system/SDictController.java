package com.knowworld.backapp.controller.system;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowworld.backapp.common.AjaxResult;
import com.knowworld.backapp.common.DataGrid;
import com.knowworld.backapp.model.dao.SDictDao;
import com.knowworld.backapp.model.domain.SDict;
import com.knowworld.backapp.service.SDictService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 数据字典控制器
 */
@Controller
@RequestMapping("/system/dict")
public class SDictController {

    Logger logger = Logger.getLogger(SDictController.class);

    @Autowired
    SDictService sDictService;
    @Autowired
    SDictDao sDictDao;

    @RequestMapping
    public void index() {
    }

    @RequestMapping("/page")
    @ResponseBody
    public DataGrid<SDict> page(Integer page,Integer rows,String dictCode,String dictName) {
        return sDictService.page(page,rows,dictCode,dictName);
    }

    @RequestMapping("/form")
    public void form(Long dictId,Model model){
        if(null != dictId){
            ObjectMapper mapper = new ObjectMapper();
            SDict dict = sDictDao.findOne(dictId);
            try{
                model.addAttribute("dict",mapper.writeValueAsString(dict));
            }catch (JsonProcessingException e){
                logger.error("json转换错误",e);
            }
        }
    }

    @RequestMapping({"/save", "/update"})
    @Transactional
    @ResponseBody
    public Object save(@Valid SDict dict, BindingResult br){
        if (br.hasErrors()) {
            logger.error("对象校验失败：" + br.getAllErrors());
            return new AjaxResult(false).setData(br.getAllErrors());
        }
        if(null != dict.getDictId()){
            SDict oldDict = sDictDao.findOne(dict.getDictId());
            if(null == oldDict){
                return new AjaxResult(false,"数据字典不存在！");
            }
        }
        return sDictDao.save(dict);
    }

    @RequestMapping("/delete")
    @Transactional
    @ResponseBody
    public AjaxResult delete(Long dictId){
        try{
            sDictDao.delete(dictId);
        }catch (Exception e){
            return new AjaxResult().setMessage(e.getMessage());
        }
        return new AjaxResult();
    }

    @RequestMapping("/list")
    @Transactional
    @ResponseBody
    public Iterable<SDict> list(){
        return sDictDao.findAll();
    }
}
