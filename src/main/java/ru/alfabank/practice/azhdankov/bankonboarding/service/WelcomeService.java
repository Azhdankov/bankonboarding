package ru.alfabank.practice.azhdankov.bankonboarding.service;

import org.springframework.stereotype.Service;
import ru.alfabank.practice.azhdankov.bankonboarding.model.WelcomeModel;

@Service
public class WelcomeService {

    public WelcomeModel getWelcome() {
        WelcomeModel welcomeModel = new WelcomeModel();
        welcomeModel.setMessage("Добро пожаловать в наш чудестный магазин");
        return welcomeModel;
    }
}
