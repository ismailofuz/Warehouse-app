package uz.pdp.warehouseapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.warehouseapp.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client,Integer> {

}
