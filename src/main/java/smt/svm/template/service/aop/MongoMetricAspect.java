package smt.svm.template.service.aop;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import smt.svm.template.dto.BaseDTO;
import smt.svm.template.service.mongo.entity.BaseEntity;
import smt.svm.template.service.util.EntityDTOConverter;

@Aspect
@Component
@ConditionalOnProperty(name = {"service.aspect.enabled"}, havingValue = "true", matchIfMissing = true)
public class MongoMetricAspect {

    private static final Logger logger = LoggerFactory.getLogger(MongoMetricAspect.class);

    @Autowired
    private MeterRegistry meterRegistry;

    EntityDTOConverter entityDTOConverter = new EntityDTOConverter();

    @Around(
            value = "within(smt.svm.template.service.mongo..*)"
    )
    @Timed
    public Object aroundServiceMethod(ProceedingJoinPoint pjp) throws Throwable {

        io.micrometer.core.instrument.Timer timer;
        Timer.Sample sample;
        Object result = null;
        Object[] args = pjp.getArgs();
        Object[] convertedArgs = new Object[args.length];
        for(int i = 0 ; i < args.length ; i++) {
            if(args[i] instanceof BaseDTO) {
                convertedArgs[i] = entityDTOConverter.getEntityOfDTO(args[i]);
            }
            else{
                convertedArgs[i] = args[i];
            }
        }
        if(meterRegistry == null)
        {
            return pjp.proceed(convertedArgs);
        }
        timer = Timer.builder(pjp.getSignature().getDeclaringType() + "").tag("method", pjp.getSignature().getName()).register(meterRegistry);
        sample = Timer.start(meterRegistry);
        long start = System.currentTimeMillis();
        try {
            result = pjp.proceed(convertedArgs);

        } catch (Throwable throwable) {


            throw throwable;

        } finally {
            //Do Something useful, If you have
        }

        sample.stop(timer);
        if(result instanceof BaseEntity)
            return entityDTOConverter.getDTOOfEntity(result);
        return result;
    }


}
