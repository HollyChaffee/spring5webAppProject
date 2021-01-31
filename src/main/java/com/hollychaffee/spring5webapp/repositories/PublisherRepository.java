package com.hollychaffee.spring5webapp.repositories;

import com.hollychaffee.spring5webapp.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {  // Extends out the Spring data Crud Repository
}
