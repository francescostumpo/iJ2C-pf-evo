package org.ij2c.evolution.service;

import org.bson.types.ObjectId;
import org.ij2c.evolution.model.Application;
import org.ij2c.evolution.model.Client;
import org.ij2c.evolution.repository.ApplicationRepository;
import org.ij2c.evolution.repository.ClientRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ClientService {

    @Inject
    ClientRepository clientRepository;

    @Inject
    ApplicationService applicationService;

    public boolean insertClientOnMongo(Client client){

        boolean inserted = clientRepository.createClient(client);
        return inserted;
    }

    public Client getClientOnMongo(String id){
        Client client = clientRepository.findById(new ObjectId(id));
        return client;
    }

    public boolean updateClient(Client client){
        boolean updated = clientRepository.updateClient(client);
        return updated;
    }

    public boolean deleteClient(ObjectId clientId) {
        boolean success = applicationService.deleteApplicationsLinkedToClient(clientId);
        if(success){
            return clientRepository.deleteClient(clientId);
        }
        return false;
    }
}
