package com.cqut.wangyu.crm.controller;

import com.cqut.wangyu.crm.dto.PageQueryDTO;
import com.cqut.wangyu.crm.dto.ResponseDTO;
import com.cqut.wangyu.crm.entity.Contact;
import com.cqut.wangyu.crm.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ContactController
 * @Description 联系人控制类
 * @Author ChongqingWangYu
 * @DateTime 2019/11/26 8:35
 * @GitHub https://github.com/ChongqingWangYu
 */
@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addContact(Contact contact) {
        return contactService.addContact(contact);
    }
    @RequestMapping(value = "/deleteContact", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deleteContact(Integer contactID) {
        return contactService.deleteContact(contactID);
    }

    @RequestMapping(value = "/updateContact", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO updateContact(Contact contact) {
        return contactService.updateContact(contact);
    }

    @RequestMapping(value = "/findContactByName", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO findContactByName(String cusName) {
        return contactService.findContactByName(cusName);
    }

    @RequestMapping(value = "/findContactByConID", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO findContactByConID(Integer conID) {
        return contactService.findContactByConID(conID);
    }


    @RequestMapping(value = "/findContactByCusID", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO findContactByCusID(Integer cusID) {
        return contactService.findContactByCusID(cusID);
    }

    @RequestMapping(value = "/findPageContact", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO findPageContact(PageQueryDTO pageQueryDTO) {
        return contactService.findPageContact(pageQueryDTO);
    }
    @RequestMapping(value = "/getAllContact", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO getAllContact() {
        return contactService.getAllContact();
    }

    @RequestMapping(value = "/importContactFromExcel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO importContactFromExcel(MultipartFile file) {
        return contactService.importContactFromExcel(file, request);
    }
}
