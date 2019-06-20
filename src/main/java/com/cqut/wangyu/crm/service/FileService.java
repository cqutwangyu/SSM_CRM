package com.cqut.wangyu.crm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileService {
    void ioReadImage(String path, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
