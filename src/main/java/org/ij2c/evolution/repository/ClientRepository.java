package org.ij2c.evolution.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.ij2c.evolution.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientRepository implements PanacheMongoRepository<Client> {

    private Logger logger = LoggerFactory.getLogger(ClientRepository.class);

    public boolean createClient(Client client){
        boolean success = false;
        try{
            persist(client);
            success = true;
        }catch(Exception e){
            e.printStackTrace();
            success = false;
        }
        return success;
    }
}
