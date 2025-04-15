package com.suisseg.app_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {

    //anotaciones, atributos, constructor ->public "entity"(entityDTO), static void mapDTO(entityDTO, entity), public entityDTO toDTO, public String toString

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String idTechnique;

    private String name;

    private String email;

    private String phone;

    private String address;

    private String city;

    private String state;

    private String zip;

    private Timestamp createdAt;

    private Timestamp updatedAt;


    public Client(ClientDTO clientDTO) {
        this.idTechnique = clientDTO.getId();
        this.name = clientDTO.getName();
        this.email = clientDTO.getEmail();
        this.phone = clientDTO.getPhone();
        this.address = clientDTO.getAddress();
        this.city = clientDTO.getCity();
        this.state = clientDTO.getState();
        this.zip = clientDTO.getZip();

    }


    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    //mapDTO

    static void mapDTO(ClientDTO clientDTO, Client client) {
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setAddress(clientDTO.getAddress());
        client.setCity(clientDTO.getCity());
        client.setState(clientDTO.getState());
        client.setZip(clientDTO.getZip());
    }

    //toDTO

    public ClientDTO toDTO() {

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(this.idTechnique);
        clientDTO.setName(name);
        clientDTO.setEmail(email);
        clientDTO.setPhone(phone);
        clientDTO.setAddress(address);
        clientDTO.setCity(city);
        clientDTO.setState(state);
        clientDTO.setZip(zip);
        return clientDTO;

    }
}
