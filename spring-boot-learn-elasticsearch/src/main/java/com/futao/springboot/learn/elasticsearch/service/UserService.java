package com.futao.springboot.learn.elasticsearch.service;

import com.futao.springboot.learn.elasticsearch.dao.UserRepository;
import com.futao.springboot.learn.elasticsearch.entity.UserEntity;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.function.Function;

/**
 * @author ft
 * @date 2021/1/15
 */
@Service
public class UserService {
    private UserRepository userRepository;
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    private ElasticsearchOperations elasticsearchOperations;

    public UserService(UserRepository userRepository, ElasticsearchRestTemplate elasticsearchRestTemplate, ElasticsearchOperations elasticsearchOperations) {
        this.userRepository = userRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public void save() {
        Function<UserEntity, String> getUsername = UserEntity::getUsername;

        UserEntity userEntity = new UserEntity();
        UserEntity save = userRepository.save(userEntity);
        UserEntity userEntity1 = elasticsearchRestTemplate.get("1", UserEntity.class);
        elasticsearchRestTemplate.search(CriteriaQuery.fromQuery(new CriteriaQuery(new Criteria().and(new Field))), UserEntity.class);
        IndexQueryBuilder indexQueryBuilder = new IndexQueryBuilder();
        IndexQuery indexQuery = indexQueryBuilder.build();
        elasticsearchOperations.index(indexQuery, IndexCoordinates.of())
    }

    public void getColName(Function<Object, String> getUsername) {
        getUsername.apply()
    }

}
