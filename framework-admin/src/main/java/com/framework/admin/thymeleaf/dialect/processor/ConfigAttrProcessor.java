package com.framework.admin.thymeleaf.dialect.processor;

import java.util.Set;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.postprocessor.IPostProcessor;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

import com.framework.admin.util.SysConfigRedis;


public class ConfigAttrProcessor extends AbstractAttributeTagProcessor {

	public static final int PRECEDENCE = 1400;
	public static final String ATTR_NAME = "value";

	public ConfigAttrProcessor(final TemplateMode templateMode, final String dialectPrefix) {
		super(templateMode, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
	}

	public ConfigAttrProcessor(final TemplateMode templateMode, final String dialectPrefix, String attr_name,
			int precedence) {
		super(templateMode, dialectPrefix, null, false, attr_name, true, precedence, true);
	}

	@Override
	protected void doProcess(final ITemplateContext context, final IProcessableElementTag tag,
			final AttributeName attributeName, final String attributeValue,
			final IElementTagStructureHandler structureHandler) {
		final IEngineConfiguration configuration = context.getConfiguration();
		 /*
         * Obtain the Thymeleaf Standard Expression parser
         * 获取Thymeleaf的表达式转换器
         */
        final IStandardExpressionParser parser =
                StandardExpressions.getExpressionParser(configuration);
        final IStandardExpression expression = parser.parseExpression(context, attributeValue);
        
        /*
         * Execute the expression just parsed
         * 使用得到的表达式，处理上下文内容，得到具体传入的参数值
         * Demo中传入的是一个数字
         */
        final String value = (String) expression.execute(context);
		String key = tag.getAttributeValue("key");
		String attr = tag.getAttributeValue("attr");
		if (null != key) {
			String prefix = SysConfigRedis.getConfigByKey(key);
		
			// 返回新的HTML节点
			final Set<IPostProcessor> postProcessors = configuration.getPostProcessors(getTemplateMode());
			if (postProcessors.isEmpty()) {
				  StringBuilder src = new StringBuilder();
				  src.append(prefix);
				  src.append(value);
//				structureHandler.setBody(src.toString(), false);
				structureHandler.setAttribute(attr, src.toString());
			}
		}

	}

}
