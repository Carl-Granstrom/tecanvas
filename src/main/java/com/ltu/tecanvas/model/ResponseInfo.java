package com.ltu.tecanvas.model;

import lombok.*;

import javax.persistence.Entity;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInfo {

    int reservationlimit;
    int reservationcount;
}
