package com.example.TACS2021UTN.models;

import com.example.TACS2021UTN.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DuelResult {
    private User winner;
    private EResult result;
}
