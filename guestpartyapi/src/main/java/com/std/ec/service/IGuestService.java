package com.std.ec.service;

import com.std.ec.model.dto.GuestDto;
import com.std.ec.model.entity.Guest;

import java.util.List;

public interface IGuestService {

    List<Guest> listAll();

    Guest save(GuestDto guest);

    Guest findById(Integer id);

    void delete(Guest guest);

    boolean existsById(Integer id);

}
