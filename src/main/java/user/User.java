package user;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String email;
    private String password;
    private String name;

    public static User getGeneratedUser() {
        Faker faker = new Faker();
        final String name = faker.name().firstName();
        final String email = faker.internet().emailAddress();
        final String password = faker.internet().password(6, 10);
        return new User(email, password, name);
    }
}
