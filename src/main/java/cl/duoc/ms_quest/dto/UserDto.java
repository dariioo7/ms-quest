package cl.duoc.ms_quest.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String accountStatus;
}
