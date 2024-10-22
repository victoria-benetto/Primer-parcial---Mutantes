package com.example.PrimerParcial.controller;

import com.example.PrimerParcial.entities.Base;
import com.example.PrimerParcial.service.BaseServiceImpl;

public class TestBaseController extends BaseControllerImpl<Base, BaseServiceImpl<Base, Long>> {
    public TestBaseController(BaseServiceImpl<Base, Long> service) {
        super(service);
    }
}