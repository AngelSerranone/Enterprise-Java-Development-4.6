package com.ironhack.Lab4_6.controller.dto;

import com.ironhack.Lab4_6.enums.Status;

public class StatusDto {
    private Status status;

    public StatusDto() {
    }

    public StatusDto(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
