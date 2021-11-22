package com.devsuperior.tarefacrud.services;

import com.devsuperior.tarefacrud.dto.ClientDTO;
import com.devsuperior.tarefacrud.entities.Client;
import com.devsuperior.tarefacrud.repositories.ClientRepository;
import com.devsuperior.tarefacrud.services.exceptions.DataBaseException;
import com.devsuperior.tarefacrud.services.exceptions.ResourceEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Transactional(readOnly = false)
    public Page<ClientDTO> findAllPaged(Pageable pageable){
        Page<Client> ClientList = clientRepository.findAll(pageable);
        return ClientList.map(cat -> new ClientDTO(cat ));
    }

    @Transactional(readOnly = false)
    public ClientDTO findOne(Long id){
        Client Client = clientRepository.findById(id).orElseThrow(() -> new ResourceEntityNotFoundException("Entity not found"));
        return new ClientDTO(Client);
    }

    @Transactional
    public ClientDTO save(ClientDTO dto){
        Client client = new Client();
        copyDtoToEntity(dto, client);
        client = clientRepository.save(client);

        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO ClientDTO){

        try {
            Client client = clientRepository.getOne(id);
            copyDtoToEntity(ClientDTO, client);
            client = clientRepository.save(client);
            return new ClientDTO(client);
        }catch (EntityNotFoundException e){
            throw new ResourceEntityNotFoundException("Entity not found");
        }
    }

    public void delete(Long id){
        try {
            clientRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceEntityNotFoundException("Entity not found");
        }
        catch (DataIntegrityViolationException e){
            throw new DataBaseException("Integrity violation ");
        }
    }

    private void copyDtoToEntity(ClientDTO dto, Client client){
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }
}
