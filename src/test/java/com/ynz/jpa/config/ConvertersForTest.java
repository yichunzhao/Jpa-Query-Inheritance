package com.ynz.jpa.config;

import com.ynz.jpa.converter.ApplicationConverter;
import com.ynz.jpa.converter.BugConverter;
import com.ynz.jpa.converter.EnhancementConverter;
import com.ynz.jpa.converter.ReleaseConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ApplicationConverter.class, BugConverter.class, EnhancementConverter.class, ReleaseConverter.class})
public class ConvertersForTest {
}
