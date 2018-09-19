package cn.projectivity.service;

import cn.projectivity.moji.MojiCity;

import java.util.Optional;

public interface MojiCityService {
    public Optional<MojiCity> findById(long id);

    public MojiCity findById(String id);

    /**
     * 通过城市中文名称查找
     * @param name
     * @return
     */
    public MojiCity findByName(String name);
}