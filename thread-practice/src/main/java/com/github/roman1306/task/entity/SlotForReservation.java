package com.github.roman1306.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotForReservation {
    private String ad;
    private LocalDate date;
    private String hotel;
}
