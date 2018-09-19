package cn.projectivity.repository;

import cn.projectivity.moji.MojiCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MojiCityRepository extends JpaRepository<MojiCity,Long> {
//    @Query("select m from MojiCity m where m.name=:name")
//    public MojiCity findByName(@Param("name")String name);
    public MojiCity findByName(String name);
}