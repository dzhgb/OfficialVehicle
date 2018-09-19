package cn.projectivity.service.impl;

import cn.projectivity.moji.MojiCity;
import cn.projectivity.repository.MojiCityRepository;
import cn.projectivity.service.MojiCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MojiCityServiceImpl implements MojiCityService {
    @Autowired
    private MojiCityRepository mojiCityRepository;

    @Override
    public MojiCity findById(String id) {
        if(id != null && !"".equals(id)){
            Optional<MojiCity> aqi = mojiCityRepository.findById(Long.valueOf(id));
            if(aqi.isPresent()){
                return aqi.get();
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public Optional<MojiCity> findById(long id) {
        return mojiCityRepository.findById(id);
    }

    @Override
    public MojiCity findByName(String name) {
        return mojiCityRepository.findByName(name);
    }

}