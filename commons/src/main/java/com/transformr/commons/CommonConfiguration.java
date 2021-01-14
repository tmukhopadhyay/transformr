package com.transformr.commons;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.transformr.commons.aspects.LoggingAspect;

@Configuration
@Import({ LoggingAspect.class })
public class CommonConfiguration {

}
