package javadbfundamenals.user_system.entities;

import javadbfundamenals.user_system.annotations.Password;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @Password(containsDigit = true,
        containsLowercase = true,
        containsSpecialSymbol = true,
        containsUppercase = true,
        message = "Invalid password")
    private String password;

    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] profilePicture;

    private Date registeredOn;

    private Date lastTimeLoggedIn;

    private Integer age;

    private Boolean isDeleted;

    @ManyToOne
    private Town birthTown;

    @ManyToOne
    private Town currentTown;

    @OneToMany(mappedBy = "owner")
    private Set<Album> albums;

    @ManyToMany
    @JoinTable(name = "friends",
    joinColumns = @JoinColumn(name = "friends_to_user", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "user_is_friend_of", referencedColumnName = "id"))
    private Set<User> PeopleThatUserIsFriendOf;

    @ManyToMany(mappedBy = "PeopleThatUserIsFriendOf")
    private Set<User> PeopleThatAreFriendsToUser;

    public User() {
        albums = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Size(min = 4, max = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validationEmail(email);
        this.email = email;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        validationPicture(profilePicture);
        this.profilePicture = profilePicture;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Date getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(Date lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        if(age < 1 || age > 120) {
            throw new IllegalArgumentException("Invalid age.");
        }
        this.age = age;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Town getBirthTown() {
        return birthTown;
    }

    public void setBirthTown(Town birthTown) {
        this.birthTown = birthTown;
    }

    public Town getCurrentTown() {
        return currentTown;
    }

    public void setCurrentTown(Town currentTown) {
        this.currentTown = currentTown;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    private void validationPicture(byte[] picture) {
        if (picture.length > (1024 * 1024)) {
            throw new IllegalArgumentException("Picture is too big!");
        }
    }

    private void validationEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9]+[\\w.-]+[a-zA-Z0-9]+@[a-zA-Z]+[a-zA-Z.]+[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Invalid email.");
        }
    }
}