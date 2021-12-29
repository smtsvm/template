package smt.svm.template.service.util;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EntityDTOConverter {

    private final Map<String, Method> methodMap;
    private static final String DTO_TO_ENTITY_SUFFIX="DTOToEntity";
    private static final String ENTITY_TO_DTO_SUFFIX="ToDTO";

    public EntityDTOConverter() {
        methodMap = new HashMap<>();
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(DtoToEntityMapper.class);
        for(int i = 0 ; i <methods.length ; i++) {
            methodMap.put(methods[i].getName(), methods[i]);
        }

    }

    public String getEntityOfDTOClass(Object object) {
        return object.getClass().getSimpleName().replace("DTO","");
    }

    public Object getEntityOfDTO(Object object) {

        String className = getEntityOfDTOClass(object);
        String methodName = Character.toLowerCase(className.charAt(0)) + className.substring(1) + DTO_TO_ENTITY_SUFFIX;
        return invokeMethod(methodName, object);
    }

    public Object getDTOOfEntity(Object object) {

        String className = getEntityOfDTOClass(object);
        String methodName = Character.toLowerCase(className.charAt(0)) + className.substring(1) + ENTITY_TO_DTO_SUFFIX;
        return invokeMethod(methodName, object);
    }

    private Object invokeMethod(String methodName, Object object) {
        DtoToEntityMapper dtoToEntityMapper = DtoToEntityMapper.INSTANCE;
        Method method =  methodMap.get(methodName);
        try {
            return method.invoke(dtoToEntityMapper, object);
        } catch (IllegalAccessException e) {
//            e.printStackTrace();
        } catch (InvocationTargetException e) {
//            e.printStackTrace();
        }
        return object;
    }


}
