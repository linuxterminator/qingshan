package com.hu.qingshan.modules.Upload;

import com.hu.qingshan.core.Untils.OssUntil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    private OssUntil oss;

    public UploadController(OssUntil oss) {
        this.oss = oss;
    }

    /**
     * 文件上传restful
     * @param files
     * @return
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/file")
    public String fileUpload(@RequestParam("files") MultipartFile files){
        return oss.fileUpLoad(files);
    }

    /**
     * 删除文件
     * @param fileName
     */
    @DeleteMapping("/file/{fileName}")
    public void delFile(@PathVariable("fileName") String fileName){

    }

}
