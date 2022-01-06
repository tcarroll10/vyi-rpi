package com.tcarroll10.vyi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Main application spring java configuration class
 *
 * @author Tom Carroll [f355430]
 * @since 12/29/2021 2:35 PM
 *
 */

@Configuration
@ImportResource({ "classpath:config/vyi-sql-config.xml" })
public class AppConfig {

}
