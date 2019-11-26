package com.cqut.wangyu.crm.dao;

import com.cqut.wangyu.crm.dto.PageQueryDTO;
import com.cqut.wangyu.crm.entity.Contact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContactDao {

    Integer insertContact(Contact contact);

    List<Contact> selectPageContact(PageQueryDTO pageQueryDTO);

    Integer deleteContact(Integer conId);

    Integer updateContact(Contact contact);

    List<Contact> selectContactByName(String conName);

    Contact selectContactById(Integer conId);

    Integer insertForeach(@Param("list") List<Contact> contactList);

    List<Contact> selectAllContact();

}
