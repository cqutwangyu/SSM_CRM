package com.cqut.wangyu.crm.system.contact;

import com.cqut.wangyu.crm.framework.AbstractController;
import com.cqut.wangyu.crm.system.contact.entity.Contact;
import com.cqut.wangyu.crm.system.contact.service.ContactService;
import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
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
public class ContactController extends AbstractController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    @ResponseBody
    public Object addContact(Contact contact) {
        try {
            return succeed(contactService.addContact(contact));
        } catch (Exception e) {
            return failure(e);
        }
    }

    @RequestMapping(value = "/deleteContact", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteContact(Integer contactID) {
        try {
            return succeed(contactService.deleteContact(contactID));
        } catch (Exception e) {
            return failure(e);
        }
    }

    @RequestMapping(value = "/updateContact", method = RequestMethod.POST)
    @ResponseBody
    public Object updateContact(Contact contact) {
        return succeed(contactService.updateContact(contact));
    }

    @RequestMapping(value = "/findContactByName", method = RequestMethod.POST)
    @ResponseBody
    public Object findContactByName(String cusName) {
        return succeed(contactService.findContactByName(cusName));
    }

    @RequestMapping(value = "/findContactByConID", method = RequestMethod.POST)
    @ResponseBody
    public Object findContactByConID(Integer conID) {
        return succeed(contactService.findContactByConID(conID));
    }


    @RequestMapping(value = "/findContactByCusID", method = RequestMethod.POST)
    @ResponseBody
    public Object findContactByCusID(Integer cusID) {
        return succeed(contactService.findContactByCusID(cusID));
    }

    @RequestMapping(value = "/findPageContact", method = RequestMethod.POST)
    @ResponseBody
    public Object findPageContact(PageQueryDTO pageQueryDTO) {
        return succeed(contactService.findPageContact(pageQueryDTO));
    }

    @RequestMapping(value = "/getAllContact", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllContact() {
        return succeed(contactService.getAllContact());
    }

    @RequestMapping(value = "/importContactFromExcel", method = RequestMethod.POST)
    @ResponseBody
    public Object importContactFromExcel(MultipartFile file) {
        return succeed(contactService.importContactFromExcel(file, request));
    }
}
