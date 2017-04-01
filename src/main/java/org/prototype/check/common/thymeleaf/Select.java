package org.prototype.check.common.thymeleaf;

import java.util.Map;

/**
 * Created by root on 2017/4/1.
 */
public class Select {

    private String id;

    private String initOption;

    private String initValue;

    private String clazz;

    private String style;

    private Map<String, String> options;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInitOption() {
        return initOption;
    }

    public void setInitOption(String initOption) {
        this.initOption = initOption;
    }

    public String getInitValue() {
        return initValue;
    }

    public void setInitValue(String initValue) {
        this.initValue = initValue;
    }

    public String getClazz() {
        return clazz;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public static Select init(Map<String, String> params){
        if(params == null || params.size() == 0){
            return new Select();
        }
        Select select = new Select();
        select.setClazz(params.get("class"));
        select.setStyle(params.get("style"));
        select.setInitOption(params.get("initOption"));
        select.setInitValue(params.get("initValue"));
        return select;
    }
}
