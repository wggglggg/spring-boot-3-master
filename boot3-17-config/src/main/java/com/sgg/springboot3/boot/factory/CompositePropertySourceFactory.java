package com.sgg.springboot3.boot.factory;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/**
 * ClassName: CompositePropertySourceFactory
 * Description:
 *      自定义其它目录下的yaml yml（springboot默认只认自定义其它目录下只认properties）
 * @Author wggglggg
 * @Create 2023-09-02 10:35
 * @Version 1.0
 */
public class CompositePropertySourceFactory extends DefaultPropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource)
            throws IOException {
        String sourceName = Optional.ofNullable(name).orElse(resource.getResource().getFilename());
        System.out.println("sourceName = " + Optional.ofNullable(name));

        // sourceName不能为空
        assert sourceName != null;

        // 源文件不存在
        if (!resource.getResource().exists()) {
            // 返回一个空的属性
            return new PropertiesPropertySource(sourceName, new Properties());
        }
        // 源文件存在
        else {
            // yaml文件格式解析
            if (sourceName.endsWith(".yml") || sourceName.endsWith(".yaml")) {
                Properties propertiesFromYaml = loadYaml(resource);
                return new PropertiesPropertySource(sourceName, propertiesFromYaml);
            }
            // 其他文件格式解析
            // 委托给父类
            else {
                return super.createPropertySource(name, resource);
            }
        }
    }

    private Properties loadYaml(EncodedResource resource) throws IOException {
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource.getResource());
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}