package ru.alfabank.practice.azhdankov.bankonboarding;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;
import ru.alfabank.practice.azhdankov.bankonboarding.controller.BankOnboardinngController;
import ru.alfabank.practice.azhdankov.bankonboarding.mapper.BaseMapper;
import ru.alfabank.practice.azhdankov.bankonboarding.repository.ProductMongoDB;
import ru.alfabank.practice.azhdankov.bankonboarding.repository.ProductRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public abstract class BaseContextTest {

    @Autowired protected BankOnboardinngController controller;
    @Autowired protected TestRestTemplate restTemplate;
    @Autowired protected ProductRepository productRepository;
    @Autowired protected ProductMongoDB productMongoDB;
    @Autowired protected BaseMapper mapper;

    static MongoDBContainer mongoDBContainer =
            new MongoDBContainer("mongo:8.0.6")
                    .withExposedPorts(27017)
                    .waitingFor(Wait.forLogMessage(".*Waiting for connections.*", 1));

    static {
        mongoDBContainer.start();
    }

    @DynamicPropertySource
    static void setMongoDBContainer(DynamicPropertyRegistry registry)
            throws IOException, InterruptedException {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
        mongoDBContainer.copyFileToContainer(
                MountableFile.forClasspathResource("scripts/import_test_data.js"),
                "/tmp/init_data.js");
        mongoDBContainer.execInContainer("mongosh", "--eval", "load('/tmp/init_data.js')");
    }
}
