package com.hsshy.beam.modular.business.controller;
import com.hsshy.beam.common.enumeration.RetEnum;
import com.hsshy.beam.common.exception.BeamException;
import com.hsshy.beam.common.utils.ToolUtil;
import com.hsshy.beam.config.properties.BeamRestProperties;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.util.OSSFactory;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.UUID;

/*
* swagger 注解参考： https://blog.csdn.net/u014231523/article/details/76522486
* swagger 注解示例
*/
@Api(value="FileController",tags={"文件上传接口"})
@RestController
@RequestMapping("/file")
public class FileController {


    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public Object uploadFile(@RequestPart("file") MultipartFile file) {

        String voiceName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(file.getOriginalFilename());
        try {
            String url = OSSFactory.build().upload(file.getBytes(),voiceName);
            return R.ok(url);

        } catch (Exception e) {
            e.printStackTrace();
            throw new BeamException(RetEnum.UPLOAD_ERROR);
        }

    }




}