package net.anotheria.resumesearch.model;


public class Skill {
    private String name;
    private int probability;

    protected Skill() {
    }

    protected Skill(String name, int probability) {
        this.name = name;
        this.probability = probability;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        if (probability != skill.probability) return false;
        return name.equals(skill.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + probability;
        return result;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", probability=" + probability +
                '}';
    }
}
