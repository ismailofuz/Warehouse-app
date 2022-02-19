package uz.pdp.warehouseapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.warehouseapp.entity.Attachment;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
