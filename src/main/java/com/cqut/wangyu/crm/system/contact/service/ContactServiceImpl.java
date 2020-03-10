package com.cqut.wangyu.crm.system.contact.service;

import com.cqut.wangyu.crm.system.contact.dao.ContactDao;
import com.cqut.wangyu.crm.system.dto.ContactDTO;
import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
import com.cqut.wangyu.crm.system.dto.ResponseDTO;
import com.cqut.wangyu.crm.system.contact.entity.Contact;
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
    public ResponseDTO addContact(Contact contact) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Contact> cName = contactDao.selectContactByName(contact.getContactName());
        if (cName.isEmpty()) {
            Integer rows = contactDao.insertContact(contact);
            responseDTO.setMessage(rows == 1 ? Constant.INSERT_SUCCEED : Constant.INSERT_FAILURE);
            responseDTO.setData("succeed");
        } else {
            responseDTO.setMessage("客户名称已存在");
            responseDTO.setData("error");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO findPageContact(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getLimit());
        ResponseDTO responseDTO = new ResponseDTO();
        List<ContactDTO> contactList = contactDao.selectPageContact(pageQueryDTO);
        PageInfo<ContactDTO> pageInfo = new PageInfo(contactList);

        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("items", contactList);
        responseDTO.setData(map);
        return responseDTO;
    }

    /**
     * 删除联系人
     *
     * @param contactID 联系人Id
     * @return
     */
    @Override
    public ResponseDTO deleteContact(Integer contactID) {
        ResponseDTO responseDTO = new ResponseDTO();
        Integer rows = -1;
        try {
            rows = contactDao.deleteContact(contactID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            switch (rows) {
                case -1:
                    responseDTO.setMessage(Constant.DELETE_FAILURE_FOREIGN_KEY);
                    break;
                case 0:
                    responseDTO.setMessage(Constant.DELETE_FAILURE);
                    break;
                case 1:
                    responseDTO.setMessage(Constant.DELETE_SUCCEED);
                    break;
            }
        }

        return responseDTO;
    }

    /**
     * 修改联系人信息
     *
     * @param contact
     * @return
     */
    @Override
    public ResponseDTO updateContact(Contact contact) {
        ResponseDTO responseDTO = new ResponseDTO();
        Integer rows = contactDao.updateContact(contact);
        responseDTO.setMessage(rows == 1 ? Constant.UPDATE_SUCCEED : Constant.UPDATE_FAILURE);
        return responseDTO;
    }

    /**
     * 根据联系人姓名查询联系人
     *
     * @param conName
     * @return
     */
    @Override
    public ResponseDTO findContactByName(String conName) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Contact> customerList = contactDao.selectContactByName(conName);
        responseDTO.setData(customerList);
        return responseDTO;
    }

    /**
     * 根据客户ID查询联系人
     *
     * @param conId
     * @return
     */
    @Override
    public ResponseDTO findContactByConID(Integer conId) {
        ResponseDTO responseDTO = new ResponseDTO();
        ContactDTO customer = contactDao.selectContactByConID(conId);
        responseDTO.setData(customer);
        return responseDTO;
    }

    /**
     * 上传excel文件并导入数据到数据库
     *
     * @param file
     * @param request
     * @return
     */
    @Override
    public ResponseDTO importContactFromExcel(MultipartFile file, HttpServletRequest request) {
        ResponseDTO responseDTO = new ResponseDTO();
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
            responseDTO.setMessage("新增：" + inserted + "条," + "更新：" + updated + "条" + ",未改：" + notChanged + "条," + "失败：" + error + "条");
        } else {
            responseDTO.setMessage(Constant.IMPORT_FAILURE);
        }
        POIUtil.returnImportResult(responseDTO, inserted, updated, notChanged, error);
        return responseDTO;
    }

    /**
     * 不分页的情况下查询所有联系人
     *
     * @return
     */
    @Override
    public ResponseDTO getAllContact() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<ContactDTO> contactList = contactDao.selectAllContact();
        responseDTO.setData(contactList);
        responseDTO.setMessage("共" + contactList.size() + "条数据");
        return responseDTO;
    }

    @Override
    public ResponseDTO findContactByCusID(Integer cusID) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<ContactDTO> customer = contactDao.selectContactByCusID(cusID);
        responseDTO.setData(customer);
        return responseDTO;
    }
}
