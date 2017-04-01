package org.prototype.check.common.thymeleaf;

import org.prototype.check.base.service.DictService;
import org.prototype.check.common.ParameterExpression;
import org.prototype.check.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.AbstractStandardExpressionAttributeTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;
import static com.google.common.base.Preconditions.*;
import static org.thymeleaf.util.StringUtils.trim;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by root on 2017/4/1.
 */

@Component
public class CommonDialect extends AbstractProcessorDialect {

    private static final String NAME = "Common";
    private static final String PREFIX = "common";

    @Autowired
    private DictService dictService;

    public CommonDialect(){
        super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String s) {
        LinkedHashSet<IProcessor> processors = new LinkedHashSet<IProcessor>();
        processors.add(new SelectProcessor(s));
        processors.add(new FromDictProcessor(s));
        return processors;
    }

    class FromDictProcessor extends AbstractStandardExpressionAttributeTagProcessor {
        private static final String ATTRIBUTE_NAME = "fromDict";
        private static final int PRECEDENCE = 300;


        FromDictProcessor( String dialectPrefix) {
            super(TemplateMode.HTML, dialectPrefix, ATTRIBUTE_NAME, PRECEDENCE, true); // Remove the matched attribute afterwards
        }

        @Override
        protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue, Object expressionResult, IElementTagStructureHandler structureHandler) {
            final String rawValue = expressionResult == null ? "" : expressionResult.toString(); //获取标签内容表达式
            if(!rawValue.contains(",")){
                structureHandler.setBody("", false);
                return;
            }
            String dictType = rawValue.split(",")[0];
            String value = rawValue.split(",")[1];
            Map<String, String> options = dictService.options(dictType);
            String text = options.get(value) == null ? "" : options.get(value);
            structureHandler.setBody(text, false);
        }
    }

    class SelectProcessor extends AbstractAttributeTagProcessor{


        private static final String ATTRIBUTE_NAME = "select";
        private static final int PRECEDENCE = 300;


        SelectProcessor( String dialectPrefix) {
            super(
                    TemplateMode.HTML, // 处理thymeleaf 的模型
                    dialectPrefix, // 标签前缀名
                    null, // No tag name: match any tag name
                    false, // No prefix to be applied to tag name
                    ATTRIBUTE_NAME, // 标签前缀的 属性 例如：< risk:sansiEncrypt="">
                    true, // Apply dialect prefix to attribute name
                    PRECEDENCE, // Precedence (inside dialect's precedence)
                    true); // Remove the matched attribute afterwards
        }



        @Override
        protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
                                 AttributeName attributeName, String s,
                                 IElementTagStructureHandler structureHandler) {

            final String rawValue = getRawValue(tag, attributeName); //获取标签内容表达式
            String tagName = tag.getElementCompleteName();

            if(!isAllow(tagName)){
                return;
            }

            //创建模型
            final IModelFactory modelFactory = context.getModelFactory();
            final IModel model = modelFactory.createModel();
            ParameterExpression expression = new ParameterExpression(rawValue);
            Select select = Select.init(expression);
            if(select.getInitOption() != null){
                addOption(modelFactory, model, select.getInitValue(), select.getInitOption());
            }

            Map<String, String> options = dictService.options(expression.get("value"));

            if(options != null){
                for (Map.Entry<String, String> entry : options.entrySet()) {
                    addOption(modelFactory, model, entry.getKey(), entry.getValue());
                }

            }

            structureHandler.insertImmediatelyAfter(model, false);

        }

        private void addOption( IModelFactory modelFactory , IModel model ,String value, String text){
            model.add(modelFactory.createOpenElementTag("option", "value", value));
            model.add(modelFactory.createText(text));
            model.add(modelFactory.createCloseElementTag("option"));
        }

        private boolean isAllow(String tagName){
            return  StringUtils.isNotEmpty(tagName) && ATTRIBUTE_NAME.toUpperCase().equals(tagName.toUpperCase());
        }
    }

    private String getRawValue(final IProcessableElementTag element, final AttributeName attributeName) {
        checkNotNull(element, "element must not be null");
        checkNotNull(attributeName, "attributeName must not be empty");
        final String rawValue = trim(element.getAttributeValue(attributeName));
        checkNotNull(rawValue, "value of '" + attributeName + "' must not be empty");
        return rawValue;
    }
}
