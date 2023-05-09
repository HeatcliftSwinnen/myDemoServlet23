package technifutur.be.technifutur.domain.entities;

import lombok.*;
@Builder @AllArgsConstructor @NoArgsConstructor @ToString @Getter @Setter @EqualsAndHashCode
public class User {
    private Integer id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

