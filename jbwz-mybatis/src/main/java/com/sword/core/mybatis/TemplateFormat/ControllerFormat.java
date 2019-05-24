package com.jbwz.core.mybatis.TemplateFormat;

import com.jbwz.core.common.base.BaseController;
import com.jbwz.core.common.base.ResponseJson;
import com.jbwz.core.mybatis.base.BaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class ControllerFormat extends BaseController {
    BaseService baseService;

    @PostMapping("/save")
    public ResponseJson saveOrUpdate() {
        baseService.saveOrUpdate(null);
        return success();
    }

    @PostMapping("/delete")
    public ResponseJson delete(String id) {
        baseService.removeById(id);
        return success();
    }
}
