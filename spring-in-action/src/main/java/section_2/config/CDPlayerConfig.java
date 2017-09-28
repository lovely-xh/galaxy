package section_2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ComponentScan能够在Spring中启用组件扫描，默认扫描与配置类相同的包以及这个包下的子包，查找带有@Component注解的类
 */
@Configuration
@ComponentScan(basePackages="section_2")
public class CDPlayerConfig {

}
