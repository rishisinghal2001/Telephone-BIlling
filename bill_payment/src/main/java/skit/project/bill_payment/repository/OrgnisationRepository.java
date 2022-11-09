package skit.project.bill_payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import skit.project.bill_payment.entity.OrgnisationEntity;

@Repository("orgnisationrepository")
public interface OrgnisationRepository extends JpaRepository<OrgnisationEntity, Integer> {

}
