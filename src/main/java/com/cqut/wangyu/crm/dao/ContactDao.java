package com.cqut.wangyu.crm.dao;

import com.cqut.wangyu.crm.dto.ContactDTO;
import com.cqut.wangyu.crm.dto.PageQueryDTO;
import com.cqut.wangyu.crm.entity.Contact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContactDao {

    Integer insertContact(Contact contact);

    List<ContactDTO> selectPageContact(PageQueryDTO pageQueryDTO);

    Integer deleteContact(Integer conId);

    Integer updateContact(Contact contact);

    List<Contact> selectContactByName(String conName);

    ContactDTO selectContactByConID(Integer conID);

    List<ContactDTO> selectContactByCusID(Integer cusID);

    Integer insertForeach(@Param("list") List<Contact> contactList);

    List<ContactDTO> selectAllContact();

    List<Integer> selectMonthlyStatistics();
}
