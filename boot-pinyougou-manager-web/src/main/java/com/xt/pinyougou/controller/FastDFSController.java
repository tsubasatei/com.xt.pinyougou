package com.xt.pinyougou.controller;

import com.xt.entity.Result;
import com.xt.pinyougou.util.FastDFSUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件上传、下载、删除
 */
@RestController
@Api(description = " fastdfs 接口 API")
@RequestMapping("/fdfs")
public class FastDFSController {

    @Value("${file.file_server.url}")
    private String FILE_SERVER_URL;//文件服务器地址

    @Autowired
    FastDFSUtils fastDFSUtils;

    /**
     * 文件上传
     *
     * storePath
     * "group": "group1",
     * "path": "M00/00/01/wKj4RFvuZlOALpzMAAJZc0IRPSY16.docx",
     * "fullPath": "group1/M00/00/01/wKj4RFvuZlOALpzMAAJZc0IRPSY16.docx"
     *
     * @param file
     * @return StorePath
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file) throws IOException {

        try {
            String url = fastDFSUtils.uploadFile(file, FILE_SERVER_URL);
            return new Result(true, url);
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    /**
     * 文件删除
     *
     * @param path group1/M00/00/00/wKj4RVvuZQWAAS5RAAJZc0IRPSY48.docx
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestBody String path) {

        try {
           fastDFSUtils.delete(path, FILE_SERVER_URL);
            return new Result(true, "删除成功！");
        } catch (Exception e) {
            return new Result(false, "删除失败！");
        }

    }

    /**
     * 文件下载
     *
     * @param group group1
     * @param path M00/00/01/wKj4RFvuZlOALpzMAAJZc0IRPSY16.docx
     * @param fileName wKj4RFvuZlOALpzMAAJZc0IRPSY16.docx
     * @param response
     * @throws IOException
     */
    @GetMapping("/download")
    public void downLoad(@RequestParam String group, @RequestParam String path, @RequestParam String fileName,
                         HttpServletResponse response) throws IOException {

        fastDFSUtils.downLoad(group, path, fileName, response);
    }
}


