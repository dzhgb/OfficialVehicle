package cn.projectivity.service;

import cn.projectivity.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    public Optional<User> findById(long id);

    /**通过username查找用户信息;*/
    public User findByUsername(String username);

    public Page<User> findAll(Pageable pageable);

    public User findByOpenid(String openid);

    public User save(User user);

    public void delete(User user);

    public void deleteById(long id);
}