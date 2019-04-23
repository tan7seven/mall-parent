package com.mall.malladmin.service.Impl;

import com.mall.malladmin.vo.UserVo;
import com.mall.malladmin.entity.UserEntity;
import com.mall.malladmin.repository.UserRepository;
import com.mall.malladmin.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity add(UserEntity entity) {
        entity.setAddDate(new Date());
        entity.setIsUsable(UserEntity.IS_USABLE);
        return userRepository.save(entity);
    }

    @Override
    public List<UserEntity> getList(UserVo dto) {
        List<UserEntity>entitys = userRepository.findAll((Specification<UserEntity>) (root, query, criteriaBuilder)->{
            List<Predicate> list = new ArrayList<>();
            if(StringUtils.isNotBlank(dto.getRole())){
                list.add(criteriaBuilder.like(root.get("role").as(String.class), "%"+dto.getRole()+"%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        });
        System.out.println("sss");
        return entitys;
    }

    @Override
    public Optional<UserEntity> findById(String userId) {
        return userRepository.findById(userId);
    }
}
