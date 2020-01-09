package com.framework.admin.util;


import java.util.LinkedHashMap;
import java.util.Map;

import com.framework.admin.entity.FwDicEntity;
import com.framework.admin.service.FwDicService;
import com.framework.common.util.SpringContextUtils;

/**
 * 
* @ClassName: DictUtil  
* @Description: TODO(字典工具类)  
* @author hanyl
* @date 2019年7月1日 下午1:49:22  
*
 */
public class DictUtil {


    /**
     * 获取字典值集合
     * @param label 字典标识
     */
    public static Map<String, String> value(String label){
    	System.out.println("根据字典标识查询："+label);
        Map<String, String> value = null;
        FwDicService fwDicService=SpringContextUtils.getBean("fwDicService", FwDicService.class);
        FwDicEntity dict=fwDicService.getModelByName(label);
        if(dict != null){
            String dictValue = dict.getValue();
            String[] outerSplit = dictValue.split(",");
            value = new LinkedHashMap<>();
            for (String osp : outerSplit) {
                String[] split = osp.split(":");
                if(split.length > 1){
                    value.put(split[0], split[1]);
                }
            }
        }
        return value;
    }

    /**
     * 根据选项编码获取选项值
     * @param label 字典标识
     * @param code 选项编码
     */
    public static String keyValue(String label, String code){
        Map<String, String> list = DictUtil.value(label);
        if(list != null){
            return list.get(code);
        }else{
            return "";
        }
    }

    /**
     * 封装数据状态字典
     * @param status 状态
     */
    public static String dataStatus(Byte status){
        String label = "DATA_STATUS";
        return DictUtil.keyValue(label, String.valueOf(status));
    }

    /**
     * 清除缓存中指定的数据
     * @param label 字典标识
     */
//    public static void clearCache(String label){
//        Element dictEle = dictCache.get(label);
//        if (dictEle != null){
//            dictCache.remove(label);
//        }
//    }
}
