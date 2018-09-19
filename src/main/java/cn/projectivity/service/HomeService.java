package cn.projectivity.service;

import cn.projectivity.entity.Home;

import java.util.Optional;

public interface HomeService {
    public Optional<Home> findById(long id);

    public Home findById(String id);

    public Home save(Home home);

    public void delete(Home home);
}