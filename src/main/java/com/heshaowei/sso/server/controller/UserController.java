package com.heshaowei.sso.server.controller;

import com.heshaowei.sso.server.entity.SysUser;
import com.heshaowei.sso.server.entity.SysUserPermission;
import com.heshaowei.sso.server.repository.SysUserPermissionRepository;
import com.heshaowei.sso.server.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private SysUserRepository userRepository;
    @Autowired
    private SysUserPermissionRepository userPermissionRepository;

    @GetMapping("/manage")
    public ModelAndView manage(Map<String, Object> model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        ModelAndView mv = new ModelAndView("system/user/manage");
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<SysUser> pageData = userRepository.findAll(pageRequest);
        mv.addObject("page", pageData);
        return mv;
    }

    @GetMapping("/permissions/{username}")
    public ResponseEntity<List<SysUserPermission>> permissions(@PathVariable("username") String username){
        List<SysUserPermission> userPermissions = this.userPermissionRepository.findAllByUsername(username).orElse(new ArrayList<>());
        return ResponseEntity.ok(userPermissions);
    }

    @PostMapping("/assignPermissions/{username}")
    @Transactional
    public ModelAndView assignPermissions(@PathVariable("username") String username, @RequestParam(required = false) List<String> permissionNames) {
        this.userPermissionRepository.deleteAllByUsername(username);

        List<SysUserPermission> userPermissions = new ArrayList<>();
        if(null != permissionNames) {
            for (String permission : permissionNames) {
                SysUserPermission userPermission = new SysUserPermission();
                userPermission.setUsername(username);
                userPermission.setPermission(permission);
                userPermissions.add(userPermission);
            }
            this.userPermissionRepository.saveAll(userPermissions);
        }

        return new ModelAndView(new RedirectView("/user/manage"));
    }
}
