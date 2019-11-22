package com.cqut.wangyu.crm.service.impl;

import com.cqut.wangyu.crm.dao.CustomerDao;
import com.cqut.wangyu.crm.dto.PageQueryDTO;
import com.cqut.wangyu.crm.dto.ResponseDTO;
import com.cqut.wangyu.crm.entity.Customer;
import com.cqut.wangyu.crm.service.CustomerService;
import com.cqut.wangyu.crm.utils.MyFileUtil;
import com.cqut.wangyu.crm.utils.POIUtil;
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
 * @ClassName CustomerServiceImpl
 * @Description
 * @Author ChongqingWangYu
 * @DateTime 2019/6/17 8:36
 * @GitHub https://github.com/ChongqingWangYu
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public ResponseDTO addCustomer(Customer customer) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Customer> cName = customerDao.selectCustomerByName(customer.getCustomerName());
        if (cName.isEmpty()) {
            Integer rows = customerDao.insertCustomer(customer);
            responseDTO.setMessage(rows == 1 ? "添加成功" : "添加失败");
            responseDTO.setData("succeed");
        } else {
            responseDTO.setMessage("客户名称已存在");
            responseDTO.setData("error");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO findPageCustomer(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getLimit());
        ResponseDTO responseDTO = new ResponseDTO();
        List<Customer> customerList = customerDao.selectPageCustomer(pageQueryDTO);
        PageInfo<Customer> pageInfo = new PageInfo(customerList);

        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("items", customerList);
        responseDTO.setData(map);
        return responseDTO;
    }

    /**
     * 删除客户
     *
     * @param cusId 客户Id
     * @return
     */
    @Override
    public ResponseDTO deleteCustomer(Integer cusId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Integer rows = customerDao.deleteCustomer(cusId);
        responseDTO.setMessage(rows == 1 ? "删除成功" : "删除失败");
        return responseDTO;
    }

    /**
     * 修改客户信息
     *
     * @param customer
     * @return
     */
    @Override
    public ResponseDTO updateCustomer(Customer customer) {
        ResponseDTO responseDTO = new ResponseDTO();
        Integer rows = customerDao.updateCustomer(customer);
        responseDTO.setMessage(rows == 1 ? "修改成功" : "修改失败");
        return responseDTO;
    }

    /**
     * 根据客户名查询客户
     *
     * @param cusName
     * @return
     */
    @Override
    public ResponseDTO findCustomerByName(String cusName) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Customer> customerList = customerDao.selectCustomerByName(cusName);
        responseDTO.setData(customerList);
        return responseDTO;
    }

    /**
     * 根据客户ID查询客户
     *
     * @param cusId
     * @return
     */
    @Override
    public ResponseDTO findCustomerById(Integer cusId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Customer customer = customerDao.selectCustomerById(cusId);
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
    public ResponseDTO importCustomerFromExcel(MultipartFile file, HttpServletRequest request) {
        ResponseDTO responseDTO = new ResponseDTO();
        int inserted = 0, updated = 0, notChanged = 0, error = 0;
        if (!file.isEmpty()) {
            String filePath = file.getOriginalFilename();
            //windows
            String savePath = request.getSession().getServletContext().getRealPath(MyFileUtil.excelPath + filePath);
            //linux
            //String savePath = "/home/odcuser/webapps/file";
            List<Customer> customerList = null;
            try {
                File targetFile = new File(savePath);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                file.transferTo(targetFile);
                customerList = POIUtil.readExcel(targetFile);
                inserted = customerDao.insertForeach(customerList);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inserted == 0) {
                    for (int i = 0; i < customerList.size(); i++) {
                        Customer customer = customerList.get(i);
                        List<Customer> customerListDB = customerDao.selectCustomerByName(customer.getCustomerName());
                        if (customerListDB.isEmpty()) {
                            //不存在，插入数据
                            customerDao.insertCustomer(customer);
                            inserted++;
                        } else {
                            //已存在，更新数据
                            if (!customer.equals(customerListDB.get(0))) {
                                Integer updateRows = customerDao.updateCustomer(customer);
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
                    error = customerList.size() - inserted;
                }
            }
            responseDTO.setMessage("新增：" + inserted + "条," + "更新：" + updated + "条" + ",未改：" + notChanged + "条," + "失败：" + error + "条");
        } else {
            responseDTO.setMessage("导入失败");
        }
        if (inserted + updated + notChanged == 0) {
            responseDTO.setData("error");
        } else if (inserted + updated + notChanged <= error) {
            responseDTO.setData("warn");
        } else if (error == 0) {
            responseDTO.setData("succeed");
        } else {
            responseDTO.setData("info");
        }
        return responseDTO;
    }

    /**
     * 不分页的情况下查询所有客户
     *
     * @return
     */
    @Override
    public ResponseDTO getAllCustomer() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Customer> customerList = customerDao.selectAllCustomer();
        responseDTO.setData(customerList);
        responseDTO.setMessage("共" + customerList.size() + "条数据");
        return responseDTO;
    }


}
