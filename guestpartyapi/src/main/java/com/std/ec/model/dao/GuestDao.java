package com.std.ec.model.dao;

import com.std.ec.model.entity.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestDao extends CrudRepository<Guest, Integer> {
}
