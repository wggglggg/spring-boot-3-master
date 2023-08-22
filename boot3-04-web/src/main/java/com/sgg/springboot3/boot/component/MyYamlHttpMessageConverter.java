package com.sgg.springboot3.boot.component;//package com.example.demo.component;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
//import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
//import org.springframework.http.HttpInputMessage;
//import org.springframework.http.HttpOutputMessage;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.http.converter.HttpMessageNotWritableException;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * ClassName: MyYamlHttpMessageConverter
// * Description:
// *
// * @Author wggglggg
// * @Create 2023-08-16 18:53
// * @Version 1.0
// */
//public class MyYamlHttpMessageConverter implements HttpMessageConverter<Object> {
//
//
//    private ObjectMapper objectMapper = null;
//
//    public MyYamlHttpMessageConverter(){
//        getSupportedMediaTypes();
//        YAMLFactory yamlFactory = new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
//        objectMapper = new ObjectMapper(yamlFactory);
//    }
//
//    @Override
//    public boolean canRead(Class<?> clazz, MediaType mediaType) {
//        return false;
//    }
//
//    @Override
//    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
//        return true;
//    }
//
//    @Override
//    public List<MediaType> getSupportedMediaTypes() {
//        List<MediaType> mediaTypeList = new ArrayList<>();
//        mediaTypeList.add(new MediaType("text", "yaml", Charset.forName("UTF-8")));
//        return mediaTypeList;
//    }
//
//    @Override
//    public Object read(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
//        return null;
//    }
//
//    @Override   //@ResponseBody 把对象怎么写出去
//    public void write(Object methodReturnValue, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
//        //try-with写法，自动关流
//        try (OutputStream os = outputMessage.getBody()) {
//            objectMapper.writeValue(os, methodReturnValue);
//        }
//    }
//}
