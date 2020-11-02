package org.ij2c.evolution.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.bson.types.ObjectId;
import org.ij2c.evolution.model.Application;
import org.ij2c.evolution.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;

@ApplicationScoped
public class ClientRepository implements PanacheMongoRepository<Client> {

    private Logger logger = LoggerFactory.getLogger(ClientRepository.class);

    public boolean createClient(Client client){
        boolean success = false;
        try{
            persist(client);
            success = true;
        }catch(Exception e){
            logger.error("Exception in creating client: " + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }

    public boolean updateClient(Client client){
        boolean success = false;
        try{
            update(client);
            success = true;
        }catch (Exception e){
            logger.error("Exception in updating client: " + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }

    public boolean deleteClient(ObjectId clientId){
        boolean success = false;
        try{
            deleteById(clientId);
            success = true;
        }catch (Exception e){
            logger.error("Exception in deleting application with ID " + clientId.toString() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }
}
