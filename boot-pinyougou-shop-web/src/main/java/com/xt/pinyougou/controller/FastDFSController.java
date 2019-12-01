package com.xt.pinyougou.controller;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.xt.entity.Result;
import io.swagger.annotations.Api;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

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
    FastFileStorageClient fastFileStorageClient;

    private Logger logger = LoggerFactory.getLogger(getClass());
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
            /**
             * 设置文件信息
             * 文件信息可以为空 直接传null即可
             */
            Set<MetaData> mataDatas = new HashSet<>();
            mataDatas.add(new MetaData("author", "xt"));
            mataDatas.add(new MetaData("description", "附件上传"));


            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(),
                    FilenameUtils.getExtension(file.getOriginalFilename()), mataDatas);
            String url = FILE_SERVER_URL + storePath.getFullPath();
            logger.info("上传图片的地址：" + url);
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
            String[] strings = path.split(FILE_SERVER_URL);
            logger.info("删除的图片地址：" + strings[1]);
            // 第一种删除：参数：完整地址
            fastFileStorageClient.deleteFile(strings[1]);

            // 第二种删除：参数：组名加文件路径
            // fastFileStorageClient.deleteFile(group, path);

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

        // 获取文件
        byte[] bytes = fastFileStorageClient.downloadFile(group, path, new DownloadByteArray());

        //设置相应类型application/octet-stream （注：applicatoin/octet-stream 为通用，一些其它的类型苹果浏览器下载内容可能为空）
        response.reset();
        response.setContentType("application/octet-stream");
        //设置头信息                 Content-Disposition为属性名  附件形式打开下载文件   指定名称  为 设定的fileName
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        // 写入到流
        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.close();
    }
}


