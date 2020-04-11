package com.cqut.wangyu.crm.system.contact.service;

import com.cqut.wangyu.crm.exception.CRMException;
import com.cqut.wangyu.crm.system.contact.dao.ContactDao;
import com.cqut.wangyu.crm.system.contact.entity.Contact;
import com.cqut.wangyu.crm.system.dto.ContactDTO;
import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
import com.cqut.wangyu.crm.utils.Constant;
import com.cqut.wangyu.crm.utils.MyFileUtil;
import com.cqut.wangyu.crm.utils.POIUtil;
import com.cqut.wangyu.crm.utils.Tools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ContactServiceImpl
 * @Description 联系人服务实现
 * @Author ChongqingWangYu
 * @DateTime 2019/11/26 9:15
 * @GitHub https://github.com/ChongqingWangYu
 */
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDao contactDao;

    @Override
    public String addContact(Contact contact) throws Exception {
        List<Contact> cName = contactDao.selectContactByName(contact.getContactName());
        if (cName.isEmpty()) {
            return contactDao.insertContact(contact) == 1 ? Constant.INSERT_SUCCEED : Constant.INSERT_FAILURE;
        } else {
            throw new Exception(CRMException.INPUT_CUSTOMER_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public Map<String, Object> findPageContact(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getLimit());
        List<ContactDTO> contactList = contactDao.selectPageContact(pageQueryDTO);
        PageInfo<ContactDTO> pageInfo = new PageInfo(contactList);

        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("items", contactList);
        return map;
    }

    /**
     * 删除联系人
     *
     * @param contactID 联系人Id
     * @return
     */
    @Override
    public String deleteContact(Integer contactID) throws Exception {
        // TODO: 2020/4/11 删除联系人需解决关联关系
        Integer rows = -1;
        try {
            rows = contactDao.deleteContact(contactID);
        } finally {
            switch (rows) {
                case -1:
                    throw new Exception(Constant.DELETE_FAILURE_FOREIGN_KEY);
                case 0:
                    throw new Exception(Constant.DELETE_FAILURE);
            }
        }
        return Constant.DELETE_SUCCEED;
    }

    /**
     * 修改联系人信息
     *
     * @param contact
     * @return
     */
    @Override
    public String updateContact(Contact contact) {
        return contactDao.updateContact(contact) == 1 ? Constant.UPDATE_SUCCEED : Constant.UPDATE_FAILURE;
    }

    /**
     * 根据联系人姓名查询联系人
     *
     * @param conName
     * @return
     */
    @Override
    public List<Contact> findContactByName(String conName) {
        return contactDao.selectContactByName(conName);
    }

    /**
     * 根据客户ID查询联系人
     *
     * @param conId
     * @return
     */
    @Override
    public ContactDTO findContactByConID(Integer conId) {
        return contactDao.selectContactByConID(conId);
    }

    /**
     * 上传excel文件并导入数据到数据库
     *
     * @param file
     * @param request
     * @return
     */
    @Override
    public String importContactFromExcel(MultipartFile file, HttpServletRequest request) {
        int inserted = 0, updated = 0, notChanged = 0, error = 0;
        if (Tools.isNotNull(file)) {
            String filePath = file.getOriginalFilename();
            String osName = Tools.getSystemStr();
            String savePath;
            if (Tools.isNotNull(osName) && "windows".equals(osName)) {
                //windows
                savePath = request.getSession().getServletContext().getRealPath(MyFileUtil.EXCEL_PATH + filePath);
            } else {
                //linux
                savePath = "/home/odcuser/webapps/file";
            }
            List<Contact> contactList = null;
            try {
                File targetFile = new File(savePath);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                file.transferTo(targetFile);
                contactList = POIUtil.readContactExcel(targetFile);
                inserted = contactDao.insertForeach(contactList);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inserted == 0) {
                    for (int i = 0; i < contactList.size(); i++) {
                        Contact contact = contactList.get(i);
                        List<Contact> customerListDB = contactDao.selectContactByName(contact.getContactName());
                        if (customerListDB.isEmpty()) {
                            //不存在，插入数据
                            contactDao.insertContact(contact);
                            inserted++;
                        } else {
                            //已存在，更新数据
                            if (!contact.equals(customerListDB.get(0))) {
                                Integer updateRows = contactDao.updateContact(contact);
                                if (updateRows == 1) {
                                    updated++;
                                } else {
                                    error++;
                                }
                            } else {
                                notChanged++;
                            }
                        }
                    }
                } else {
                    error = contactList.size() - inserted;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("新增：" + inserted + "条");
        sb.append(",更新" + updated + "条");
        sb.append(",未改" + notChanged + "条");
        sb.append(",失败" + error + "条");
        return sb.toString();
    }

    /**
     * 不分页的情况下查询所有联系人
     *
     * @return
     */
    @Override
    public List<ContactDTO> getAllContact() {
        return contactDao.selectAllContact();
    }

    @Override
    public List<ContactDTO> findContactByCusID(Integer cusID) {
        return contactDao.selectContactByCusID(cusID);
    }
}
