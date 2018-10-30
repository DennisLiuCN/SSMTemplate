package org.bluesky.ssm.freemarker;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 文本省略显示自定义指令
 * 
 * @author: liuyuefeng
 * @date: 2016年3月25日 下午5:25:26
 * @version: V1.0
 *
 */
public class EllipsisDirective implements TemplateDirectiveModel {

	@Override
	public void execute(Environment env,
			@SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String text = "";
		int length = 0;
		if (params.get("text") != null) {
			text = ((SimpleScalar) params.get("text")).getAsString();
		}
		if (params.get("length") != null) {
			length = Integer.valueOf(((SimpleScalar) params.get("length"))
					.getAsString());
		}
		if (length < text.length()) {
			text = "..." + text.substring(text.length() - length);
		}
		env.getOut().write(text);
	}
}
