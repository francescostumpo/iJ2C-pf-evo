package org.ij2c.evolution.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.bson.types.ObjectId;
import org.ij2c.evolution.model.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ApplicationRepository implements PanacheMongoRepository<Application> {

    private Logger logger = LoggerFactory.getLogger(ApplicationRepository.class);

    public ObjectId createApplication(Application application){
        ObjectId applicationId = null;
        try {
            persist(application);
            applicationId = application.getId();
        } catch (Exception e) {
            logger.error("Exception in creating application with ID " + applicationId.toString() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return applicationId;
    }

    public boolean updateApplication(ObjectId applicationId, ObjectId questionnaireId){
        boolean success = false;
        try{
            Application application = findById(applicationId);
            application.setQuestionnaireId(questionnaireId);
            update(application);
            success = true;
        }catch (Exception e){
            logger.error("Exception in updating application with ID " + applicationId.toString() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }

    public boolean deleteApplication(ObjectId applicationId){
        boolean success = false;
        try{
            deleteById(applicationId);
            success = true;
        }catch (Exception e){
            logger.error("Exception in deleting application with ID " + applicationId.toString() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }

    public Application getApplicationOnMongo(ObjectId applicationId){
        Application application = findById(applicationId);
        return application;
    }

    public List<Application> getApplicationsByClientId(ObjectId clientId){
        List<Application> applicationList = list("clientId", clientId);
        return applicationList;
    }
}
