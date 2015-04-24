package socialnetwork.tweetterm.model;

import java.util.HashSet;
import java.util.Set;

public class User {

    private String username;
    private Set<User> following = new HashSet<>();

    public User(String username) {
        this.username = username;
    }

    public void addFollowingUser(User user) {
        following.add(user);
    }

    public Set<User> getFollowedUsers() {
        return following;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return !(username != null ? !username.equals(user.username) : user.username != null);

    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}
