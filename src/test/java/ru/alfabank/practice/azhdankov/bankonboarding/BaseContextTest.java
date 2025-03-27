package ru.alfabank.practice.azhdankov.bankonboarding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import ru.alfabank.practice.azhdankov.bankonboarding.in.ShopController;
import ru.alfabank.practice.azhdankov.bankonboarding.in.mapper.BaseMapper;
import ru.alfabank.practice.azhdankov.bankonboarding.repository.DBStub;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseContextTest {

    @Autowired protected ShopController controller;
    @Autowired protected TestRestTemplate restTemplate;
    @Autowired protected DBStub dbStub;
    @Autowired protected BaseMapper mapper;
}
