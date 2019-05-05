package com.knowworld.backapp.service;

import com.knowworld.backapp.common.DataGrid;
import com.knowworld.backapp.common.MySpecification;
import com.knowworld.backapp.common.PageArg;
import com.knowworld.backapp.model.dao.SDictDao;
import com.knowworld.backapp.model.domain.Member;
import com.knowworld.backapp.model.domain.SDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class SDictService {

    /**
     * 字典DAO
     */
    @Autowired
    private SDictDao sDictDao;

    /**
     * 分页查询
     * @param page   分页参数
     * @param rows   分页参数
     * @param dictName  字典名称
     * @param dictCode  字典编码
     */
    public DataGrid<SDict> page(Integer page,Integer rows,String dictCode,String dictName){
        PageRequest pr = new PageRequest(page - 1, rows);
        Page pageData = sDictDao.findAll(new MySpecification<SDict>().and(
                MySpecification.Cnd.like("dictName", dictName),
                MySpecification.Cnd.like("dictCode", dictCode)
        ).asc("orderNum"), pr);
        return new DataGrid<SDict>(pageData);
    }
}
