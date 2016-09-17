package com.xieziming.stap.data.repository;

import com.xieziming.stap.data.model.execution.Execution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * A repository to manage {@link Execution} instances.
 *
 * @author Suny Xie
 */
@RepositoryRestResource(collectionResourceRel = "execution", path = "execution")
public interface ExecutionRepository extends PagingAndSortingRepository<Execution, Integer>, CrudRepository<Execution, Integer> {

}
