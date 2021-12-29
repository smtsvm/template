package smt.svm.template.service.spring;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class ServiceHolderListener implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private ListableBeanFactory listableBeanFactory;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        fillRepositoryMap(listableBeanFactory);
    }

    public void fillRepositoryMap(ListableBeanFactory listableBeanFactory) {
        Repositories repositories = new Repositories(listableBeanFactory);


        Iterator<Class<?>> it = repositories.iterator();
        while (it.hasNext()) {
            Class<?> domainClass = it.next();
            RepositoryInformation repository = repositories.getRequiredRepositoryInformation(domainClass);
            MongoRepositoryHolder.get().addEntityToMap(domainClass, repository.getRepositoryInterface());

        }
    }
}
