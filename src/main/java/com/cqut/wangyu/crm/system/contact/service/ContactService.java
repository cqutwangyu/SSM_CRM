package com.cqut.wangyu.crm.system.contact.service;

import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
import com.cqut.wangyu.crm.system.dto.ResponseDTO;
import com.cqut.wangyu.crm.system.contact.entity.Contact;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ContactService {

    ResponseDTO addContact(Contact contact);

    ResponseDTO findPageContact(PageQueryDTO pageQueryDTO);

    /**
     * 删除联系人
     * @param contactID 联系人Id
     * @return
     */
    ResponseDTO deleteContact(Integer contactID);

    /**
     * 修改联系人信息
     * @param contact
     * @return
     */
    ResponseDTO updateContact(Contact contact);

    /**
     * 根据联系人姓名查询联系人
     * @param conName
     * @return
     */
    ResponseDTO findContactByName(String conName);

    /**
     * 根据ID查询联系人
     * @param conID
     * @return
     */
    ResponseDTO findContactByConID(Integer conID);


    /**
     * 根据客户ID查询联系人
     * @param cusID
     * @return
     */
    ResponseDTO findContactByCusID(Integer cusID);

    /**
     * 上传excel文件并导入数据到数据库
     * @param file
     * @param request
     * @return
     */
    ResponseDTO importContactFromExcel(MultipartFile file, HttpServletRequest request);

    /**
     * 不分页的情况下查询所有联系人
     * @return
     */
    ResponseDTO getAllContact();

}
