package cn.projectivity.repository;

import cn.projectivity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * 通过username查找用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 通过openid查找用户
     * @param openid
     * @return
     */
    public User findByOpenid(String openid);
}