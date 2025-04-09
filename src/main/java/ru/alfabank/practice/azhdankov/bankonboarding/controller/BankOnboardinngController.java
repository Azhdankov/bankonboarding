package ru.alfabank.practice.azhdankov.bankonboarding.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.alfabank.practice.azhdankov.bankonboarding.dto.ProductDto;
import ru.alfabank.practice.azhdankov.bankonboarding.dto.resp.CalculatedRespDto;
import ru.alfabank.practice.azhdankov.bankonboarding.dto.resp.WelcomeRespDto;
import ru.alfabank.practice.azhdankov.bankonboarding.mapper.BaseMapper;
import ru.alfabank.practice.azhdankov.bankonboarding.model.ProductModel;
import ru.alfabank.practice.azhdankov.bankonboarding.model.WelcomeModel;
import ru.alfabank.practice.azhdankov.bankonboarding.service.CalculationService;
import ru.alfabank.practice.azhdankov.bankonboarding.service.ProductService;
import ru.alfabank.practice.azhdankov.bankonboarding.service.WelcomeService;
import ru.alfabank.practice.azhdankov.bankonboarding.validator.ProductDtoConstraint;

@RestController
@RequestMapping("/shop")
@Validated
public class BankOnboardinngController {

    /* Наверное, лучше всё так котроллеры разделить на каждое взаимодействие и в каждом из них инжектить свой сервис
     * но в требованиях был один контроллер ShopController */

    @Autowired private BaseMapper baseMapper;
    @Autowired private WelcomeService welcomeService;
    @Autowired private ProductService productService;
    @Autowired private CalculationService calculationService;

    @GetMapping("/welcome")
    public ResponseEntity<WelcomeRespDto> getWelcome() {
        WelcomeModel welcomeModel = welcomeService.getWelcome();
        return new ResponseEntity<>(baseMapper.toWelcomeDto(welcomeModel), HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getProduct() {
        List<ProductModel> productInfoDtoList = productService.getProducts();
        return new ResponseEntity<>(
                baseMapper.toListProductInfoDtoWithoutCount(productInfoDtoList), HttpStatus.OK);
    }

    @PostMapping("/calc")
    public ResponseEntity<CalculatedRespDto> calculateSum(
            @RequestBody @ProductDtoConstraint List<ProductDto> products) {
        List<ProductModel> productModelList = baseMapper.toListProductModel(products);
        CalculatedRespDto calculatedRespDto =
                baseMapper.toCalculatedRespDto(calculationService.getProducts(productModelList));
        return new ResponseEntity<>(calculatedRespDto, HttpStatus.OK);
    }
}
