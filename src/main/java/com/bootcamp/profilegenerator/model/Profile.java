package com.bootcamp.profilegenerator.model;

import java.util.Objects;
import java.util.Set;

public class Profile<T extends Skill> {

    private String firstName;
    private String lastName;
    private Set<T> skillSet;

    public Profile(String firstName, String lastName, Set<T> skillSet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.skillSet = skillSet;
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

    public Set<T> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(Set<T> skillSet) {
        this.skillSet = skillSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (!firstName.equals(profile.firstName)) return false;
        if (!lastName.equals(profile.lastName)) return false;
        return skillSet.equals(profile.skillSet);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + skillSet.hashCode();
        return result;
    }
}
