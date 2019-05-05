package com.knowworld.backapp.service;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.knowworld.backapp.common.DataGrid;
import com.knowworld.backapp.common.MySpecification;
import com.knowworld.backapp.dto.DictItemPageDto;
import com.knowworld.backapp.model.dao.SDictDao;
import com.knowworld.backapp.model.dao.SDictItemDao;
import com.knowworld.backapp.model.domain.SDict;
import com.knowworld.backapp.model.domain.SDictItem;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SDictItemService {

    /**
     * 字典DAO
     */
    @Autowired
    private SDictItemDao dictItemDao;
    @Autowired
    private SDictDao dictDao;

    /**
     * 分页查询
     * @param page   分页参数
     * @param rows   分页参数
     * @param dictId    字典ID
     * @param itemCode  字典项名称
     * @param itemName  字典项编码
     */
    public DataGrid<DictItemPageDto> page(Integer page, Integer rows,Long dictId, String itemCode, String itemName){
        PageRequest pr = new PageRequest(page - 1, rows);
        Page pageData = dictItemDao.findAll(new MySpecification<SDictItem>().and(
                MySpecification.Cnd.eq("dictId", dictId),
                MySpecification.Cnd.like("itemName", itemName),
                MySpecification.Cnd.like("itemCode", itemCode)
        ).asc("dictId"), pr);
        return this.transDictItem(new DataGrid<SDictItem>(pageData));
    }

    /**
     * 翻译字典项
     * @param dataGrid
     * @return
     */
    private DataGrid<DictItemPageDto> transDictItem(DataGrid<SDictItem> dataGrid){
        List<DictItemPageDto> pageDtoList = new ArrayList<>();
        for (SDictItem dictItem : dataGrid.getRows()) {
            DictItemPageDto pageDto = new DictItemPageDto();
            BeanUtils.copyProperties(dictItem,pageDto);
            SDict dict = dictDao.findOne(dictItem.getDictId());
            pageDto.setDictName(dict.getDictName());
            pageDtoList.add(pageDto);
        }
        return new DataGrid<>(pageDtoList);
    }

    /**
     * 获取字典Map
     * @param dictCode  字典编码
     * @return
     */
    public Map<Integer,String> getDictItemMap(String dictCode){
        List<SDictItem> dictItemList = this.getDictItemList(dictCode);
        return dictItemList.stream().collect(Collectors.toMap(SDictItem::getItemCode,SDictItem::getItemName));
    }

    /**
     * 获取字典项集合
     * @param dictCode  字典编码
     * @return
     */
    public List<SDictItem> getDictItemList(String dictCode){
        SDict dict = (SDict) dictDao.findOne(new MySpecification<SDict>().and(
                MySpecification.Cnd.eq("dictCode", dictCode)));
        return dictItemDao.findAll(new MySpecification<SDictItem>().and(
                MySpecification.Cnd.eq("dictId", dict.getDictId())));
    }
}
