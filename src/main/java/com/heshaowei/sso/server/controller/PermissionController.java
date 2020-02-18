package com.heshaowei.sso.server.controller;

import com.heshaowei.sso.server.entity.SysPermission;
import com.heshaowei.sso.server.repository.SysPermissionRepository;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RequestMapping("/permission")
@Controller
public class PermissionController {

    @Autowired
    private SysPermissionRepository permissionRepository;

    @GetMapping("/manage")
    public ModelAndView manage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        ModelAndView mv = new ModelAndView("system/permission/manage");
        Page<SysPermission> pageResult = this.permissionRepository.findAll(PageRequest.of(page, size, Sort.by("id")));
        mv.addObject("page", pageResult);
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(@RequestParam String name, @RequestParam String remark){
        SysPermission permission = new SysPermission();
        permission.setName(name);
        permission.setRemark(remark);
        this.permissionRepository.saveAndFlush(permission);

        ModelAndView mv = this.manage(0, 5);
        mv.setView(new RedirectView("/permission/manage"));
        return mv;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        this.permissionRepository.deleteById(id);

        return ResponseEntity.ok("success");
    }

    @GetMapping("/choose")
    public ModelAndView choose(){
        List<SysPermission> permissions = this.permissionRepository.findAll();
        ModelAndView mv = new ModelAndView("system/permission/choose");
        mv.addObject("permissions", permissions);
        return mv;
    }
}
