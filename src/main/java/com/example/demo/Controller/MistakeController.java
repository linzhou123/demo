package com.example.demo.Controller;

import com.example.demo.Model.Mistake;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.MistakeService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/mistake")
public class MistakeController {
    @Resource
    private MistakeService mistakeService;


    @PostMapping("/add")
    public ResponseInfo insertMistake(@RequestBody @Valid Mistake mistake, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseInfo.errorInfo(result.getFieldError().getDefaultMessage());
        }
        List<Mistake> mistakes = mistakeService.findByDescriptionOrNot(mistake.getDescription());
        if (mistakes.size() > 0) {
            return ResponseInfo.errorInfo(mistake.getDescription() + "伤害描述已存在,请详细描述");
        }
        mistakeService.insertMistake(mistake);

        return ResponseInfo.successInfo("");
    }

    @GetMapping("/page")
    public ResponseInfo getMistakePage(@RequestParam(value = "pageNum", required = true) int pageNum, @RequestParam(value = "pageSize", required = true) int pageSize) {
        return ResponseInfo.successInfo(mistakeService.finAllWithPage(pageNum, pageSize));
    }
}
