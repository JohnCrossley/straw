package com.jccworld.strawtestapp.di.module.service;

import com.jccworld.strawtestapp.di.module.DIModule;
import com.jccworld.strawtestapp.service.LoginStrawService;
import com.jccworld.strawtestapp.service.NumberDomainService;
import com.jccworld.strawtestapp.service.NumberPojoService;
import com.jccworld.strawtestapp.service.SentenceDomainService;
import com.jccworld.strawtestapp.service.SentencePojoService;

import dagger.Module;

/**
 * Created by johncrossley on 27/11/15.
 */
@Module(
    injects = {
            LoginStrawService.class,
            NumberDomainService.class,
            SentenceDomainService.class,
            NumberPojoService.class,
            SentencePojoService.class
        },
    complete = false
)
public class ServiceModule implements DIModule {

}