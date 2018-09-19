package cn.projectivity.service.impl;

import cn.projectivity.entity.SysRole;
import cn.projectivity.repository.SysRoleRepository;
import cn.projectivity.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Override
    public Optional<SysRole> findById(long id) {
        return sysRoleRepository.findById(id);
    }

    @Override
    public SysRole save(SysRole sysRole) {
        return sysRoleRepository.save(sysRole);
    }

    @Override
    public void delete(SysRole sysRole) {
        sysRoleRepository.delete(sysRole);
    }

    @Override
    public SysRole findByRole(String role) {
        return sysRoleRepository.findByRole(role);
    }

    @Override
    public Page<SysRole> findAll(Pageable pageable) {
        return sysRoleRepository.findAll(pageable);
    }
}