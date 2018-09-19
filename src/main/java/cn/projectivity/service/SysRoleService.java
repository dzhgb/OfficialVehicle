package cn.projectivity.service;

import cn.projectivity.entity.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SysRoleService {
    public Optional<SysRole> findById(long id);

    public SysRole save(SysRole sysRole);

    public void delete(SysRole sysRole);

    public SysRole findByRole(String role);

    public Page<SysRole> findAll(Pageable pageable);
}