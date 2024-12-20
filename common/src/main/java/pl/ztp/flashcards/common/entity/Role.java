package pl.ztp.flashcards.common.entity;

public enum Role {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private final String name;

    Role(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
