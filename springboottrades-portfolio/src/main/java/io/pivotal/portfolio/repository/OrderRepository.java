package io.pivotal.portfolio.repository;


import java.util.List;

import io.pivotal.portfolio.domain.Order;

import org.springframework.data.repository.CrudRepository;
/**
 * 
 * @author David Ferreira Pinto
 *
 */
public interface OrderRepository extends CrudRepository<Order,Integer> {

	List<Order> findByAccountId(String accountId);
}
