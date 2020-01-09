package com.framework.admin.thymeleaf.dialect.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import com.framework.admin.util.SysConfigRedis;
public class SysConfigProcessor extends AbstractElementTagProcessor {
	
//	@Autowired
//	private RedisUtils redisUtils;
	
	private static final String TAG_NAME = "config";// 标签名
	
	public SysConfigProcessor(String dialectPrefix) {
		super(TemplateMode.HTML,// 此处理器将仅应用于HTML模式
				dialectPrefix,// 要应用于名称的匹配前缀
				TAG_NAME,// 标签名称：匹配此名称的特定标签
				true,// 应用于标签名的前缀
				null,// 无属性名称：将通过标签名称匹配
				false,// 没有要应用于属性名称的前缀
				10000// 优先级
		);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		// TODO Auto-generated method stub
		  String key = tag.getAttributeValue("key");
		  String id= tag.getAttributeValue("id");
		  if(null!=key) {
			 String value=SysConfigRedis.getConfigByKey(key);
			 if(null!=value) {
				 StringBuilder str=new StringBuilder();
				 str.append("<input type='hidden'");
				 str.append(" id='");
				 str.append(null!=id?id:"configAttr");
				 str.append("'");
				 str.append(" attr='");
				 str.append(value);
				str.append("'");
				str.append("/>");
		        IModelFactory modelFactory = context.getModelFactory();
		        IModel model = modelFactory.createModel();
		        model.add(modelFactory.createText(str.toString()));
		        structureHandler.replaceWith(model, false);
			 }

		  }

	}

}
