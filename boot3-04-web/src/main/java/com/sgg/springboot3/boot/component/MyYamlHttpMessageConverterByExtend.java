package com.sgg.springboot3.boot.component;//package com.example.demo.component;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
//import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
//import org.springframework.http.HttpInputMessage;
//import org.springframework.http.HttpOutputMessage;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.AbstractHttpMessageConverter;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.http.converter.HttpMessageNotWritableException;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.nio.charset.Charset;
//
///**
// * ClassName: MyYamlHttpMessageConverterByExtend
// * Description:
// *
// * @Author wggglggg
// * @Create 2023-08-17 9:06
// * @Version 1.0
// */
//public class MyYamlHttpMessageConverterByExtend extends AbstractHttpMessageConverter<Object> {
//
//    private ObjectMapper objectMapper = null;
//
//    public MyYamlHttpMessageConverterByExtend(){
//        //告诉SpringBoot这个MessageConverter支持哪种媒体类型  父类有一个有参构造器，设置编码与支持哪个类型
//        super(new MediaType("text", "yaml", Charset.forName("UTF-8")));
//        this.objectMapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
//    }
//
//
//
//
//    @Override
//    protected boolean supports(Class<?> clazz) {
//        return true;
//    }
//
//    @Override   //@RequestBody 读进来
//    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
//        return null;
//    }
//
//    @Override //@ResponseBody 把对象怎么写出去
//    protected void writeInternal(Object methodReturnValue, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
//
//        //try-with写法，自动关流
//        try(OutputStream os = outputMessage.getBody()) {
//            objectMapper.writeValue(os, methodReturnValue);
//        }
//    }
//}
