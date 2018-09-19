package cn.projectivity.repository;

import cn.projectivity.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRoleRepository extends JpaRepository<SysRole,Long> {
    /**
     * 通过role字段查找
     * @param role
     * @return
     */
    public SysRole findByRole(String role);
}