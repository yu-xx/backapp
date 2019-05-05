package com.knowworld.backapp.controller.system;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowworld.backapp.common.AjaxResult;
import com.knowworld.backapp.common.DataGrid;
import com.knowworld.backapp.common.MySpecification;
import com.knowworld.backapp.dto.DictItemPageDto;
import com.knowworld.backapp.model.dao.SDictDao;
import com.knowworld.backapp.model.dao.SDictItemDao;
import com.knowworld.backapp.model.domain.SDict;
import com.knowworld.backapp.model.domain.SDictItem;
import com.knowworld.backapp.service.SDictItemService;
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
@RequestMapping("/system/dictItem")
public class SDictItemController {

    Logger logger = Logger.getLogger(SDictItemController.class);

    @Autowired
    SDictItemService dictItemService;
    @Autowired
    SDictDao sDictDao;

    @Autowired
    SDictItemDao dictItemDao;

    @RequestMapping
    public void index() {
    }

    @RequestMapping("/page")
    @ResponseBody
    public DataGrid<DictItemPageDto> page(Integer page, Integer rows, Long dictId, String itemCode, String itemName) {
        return dictItemService.page(page,rows,dictId,itemCode,itemName);
    }

    @RequestMapping("/form")
    public void form(Long dictItemId,Long dictId,Model model){
        ObjectMapper mapper = new ObjectMapper();
        if(null != dictItemId){
            SDictItem dictItem = dictItemDao.findByDictItemIdAndDictId(dictItemId,dictId);
            try{
                model.addAttribute("dictItem",mapper.writeValueAsString(dictItem));
            }catch (JsonProcessingException e){
                logger.error("json转换错误",e);
            }
        }else{
            model.addAttribute("dictId",dictId);
        }
    }

    @RequestMapping({"/save", "/update"})
    @Transactional
    @ResponseBody
    public Object save(@Valid SDictItem dictItem, BindingResult br){
        if (br.hasErrors()) {
            logger.error("对象校验失败：" + br.getAllErrors());
            return new AjaxResult(false).setData(br.getAllErrors());
        }
        if(null != dictItem.getDictItemId()){
            SDictItem oldDictItem = dictItemDao.findOne(dictItem.getDictItemId());
            if(null == oldDictItem){
                return new AjaxResult(false,"数据字典项不存在！");
            }
        }
        return dictItemDao.save(dictItem);
    }

    @RequestMapping("/delete")
    @Transactional
    @ResponseBody
    public AjaxResult delete(Long dictItemId){
        try{
            dictItemDao.delete(dictItemId);
        }catch (Exception e){
            return new AjaxResult().setMessage(e.getMessage());
        }
        return new AjaxResult();
    }

    @RequestMapping("/list")
    @ResponseBody
    public Iterable<SDictItem> list(String dictCode){
        return dictItemService.getDictItemList(dictCode);
    }
}
