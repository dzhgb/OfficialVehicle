package cn.projectivity.service.impl;

import cn.projectivity.entity.Home;
import cn.projectivity.repository.HomeRepository;
import cn.projectivity.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeRepository homeRepository;

    @Override
    public Home findById(String id) {
        if(id != null && !"".equals(id)){
            Optional<Home> home = homeRepository.findById(Long.valueOf(id));
            if(home.isPresent()){
                return home.get();
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public Optional<Home> findById(long id) {
        return homeRepository.findById(id);
    }

    @Override
    public Home save(Home home) {
        return homeRepository.save(home);
    }

    @Override
    public void delete(Home home) {
        homeRepository.delete(home);
    }
}