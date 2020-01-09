package com.framework.admin.thymeleaf.dialect;


import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import com.framework.admin.thymeleaf.dialect.processor.ConfigAttrProcessor;
import com.framework.admin.thymeleaf.dialect.processor.SelectDictAttrProcessor;
import com.framework.admin.thymeleaf.dialect.processor.SysConfigProcessor;

public class ThymeleafDialect extends AbstractProcessorDialect {
    //定义方言的名字
    private static final String DIALECT_NAME = "framework";
//    定义方言的前缀
    private static final String DIALECT_PREFIX= "fw";
//定义方言的优先级，推荐使用标准的优先级1000
    private static final int PROCESSOR_PRECEDENCE = StandardDialect.PROCESSOR_PRECEDENCE;


    public ThymeleafDialect() {
        super(DIALECT_NAME, DIALECT_PREFIX, PROCESSOR_PRECEDENCE);
    }

    /**
     * 配置标签处理器
     * @param dialectPrefix
     * @return
     */
    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new ConfigAttrProcessor(TemplateMode.HTML, dialectPrefix));
        //字典下拉框
        processors.add(new SelectDictAttrProcessor(TemplateMode.HTML, dialectPrefix));
        processors.add(new SysConfigProcessor(dialectPrefix));
        processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
        return processors;
    }
}
