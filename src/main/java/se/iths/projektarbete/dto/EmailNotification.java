package se.iths.projektarbete.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class EmailNotification implements Serializable {
    private String username;
    private String email;
}
