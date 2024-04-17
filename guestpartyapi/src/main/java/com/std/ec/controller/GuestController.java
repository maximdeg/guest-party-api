package com.std.ec.controller;

import com.std.ec.model.dto.GuestDto;
import com.std.ec.model.entity.Guest;
import com.std.ec.model.payload.MessageResponse;
import com.std.ec.service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GuestController {

    @Autowired
    private IGuestService guestService;

    @GetMapping("guest")
    public ResponseEntity<?> showAll() {
        List<Guest> getList = guestService.listAll();
        if (getList == null) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("There is no registry")
                            .object(null)
                            .build()
                    , HttpStatus.OK);
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(getList)
                        .build()
                , HttpStatus.OK);
    }


    @PostMapping("guest")
    public ResponseEntity<?> create(@RequestBody GuestDto guestDto) {
        Guest guestSave = null;
        try {
            guestSave = guestService.save(guestDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("success")
                    .object(GuestDto.builder()
                            .idGuest(guestSave.getIdGuest())
                            .name(guestSave.getName() )
                            .invited(guestSave.getInvited())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("guest/{id}")
    public ResponseEntity<?> update(@RequestBody GuestDto guestDto, @PathVariable Integer id) {
        Guest guestUpdate = null;
        try {
            if (guestService.existsById(id)) {
                guestDto.setIdGuest(id);
                guestUpdate = guestService.save(guestDto);
                return new ResponseEntity<>(MessageResponse.builder()
                        .message("success")
                        .object(GuestDto.builder()
                                .idGuest(guestUpdate.getIdGuest())
                                .name(guestUpdate.getName())
                                .invited(guestUpdate.getInvited())
                                .build())
                        .build()
                        , HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        MessageResponse.builder()
                                .message("The registry you want to update is not on the database")
                                .object(null)
                                .build()
                        , HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("guest/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Guest guestDelete = guestService.findById(id);
            guestService.delete(guestDelete);
            return new ResponseEntity<>(guestDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("guest/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Guest guest = guestService.findById(id);

        if (guest == null) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("That registry does not exist. Try another one.")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(GuestDto.builder()
                                .idGuest(guest.getIdGuest())
                                .name(guest.getName())
                                .invited(guest.getInvited())
                                .build())
                        .build()
                , HttpStatus.OK);
    }

}
