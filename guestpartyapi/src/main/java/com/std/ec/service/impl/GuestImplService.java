package com.std.ec.service.impl;

import com.std.ec.model.dao.GuestDao;
import com.std.ec.model.dto.GuestDto;
import com.std.ec.model.entity.Guest;
import com.std.ec.service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GuestImplService implements IGuestService {

    @Autowired
    private GuestDao guestDao;

    @Override
    public List<Guest> listAll() {
        return (List) guestDao.findAll();
    }

    @Transactional
    @Override
    public Guest save(GuestDto guestDto) {
        Guest guest = Guest.builder()
                .idGuest(guestDto.getIdGuest())
                .name(guestDto.getName())
                .invited(guestDto.getInvited())
                .build();
        return guestDao.save(guest);
    }

    @Transactional(readOnly = true)
    @Override
    public Guest findById(Integer id) {
        return guestDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Guest guest) {
        guestDao.delete(guest);
    }

    @Override
    public boolean existsById(Integer id) {
        return guestDao.existsById(id);
    }
}
