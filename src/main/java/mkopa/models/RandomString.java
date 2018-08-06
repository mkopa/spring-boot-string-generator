package mkopa.models;

import javax.persistence.*;

@Entity(name="randoms")
@Table(name="randoms")
public class RandomString {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String randomString;

    public RandomString() { }

    public RandomString(String randomString) {
        this.randomString = randomString;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getRandomString() {
        return randomString;
    }

    public void setRandomString(String randomString) { this.randomString = randomString; }
}
