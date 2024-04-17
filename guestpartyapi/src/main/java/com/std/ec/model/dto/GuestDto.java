package com.std.ec.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class GuestDto implements Serializable {

    private Integer idGuest;
    private String name;
    private String invited;

}
