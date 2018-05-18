package net.anotheria.resumesearch.generator;


import java.util.*;

public final class SkillSetGenerator<T extends Skill> {

    private static SkillSetGenerator skillSetGenerator;
    private Random r;
    private static final int MAX_BACKEND = 10;
    private static final int MIN_BACKEND = 5;
    private static final int MAX_FRONTEND = 6;
    private static final int MIN_FRONTEND = 4;
    private static int[] skillSetProbability;

    private SkillSetGenerator() {
        r = new Random();
    }

    public static SkillSetGenerator getSkillSetGenerator() {
        if (skillSetGenerator == null) {
            skillSetGenerator = new SkillSetGenerator();
        }
        return skillSetGenerator;
    }


    /**
     *
     * @param clazz
     * @return Set of skills randomly filled with possible skills.
     */
    public Set<T> generateSkillSet(Class<T> clazz) {
        List<T> skills = new LinkedList<>((Set<T>) Skills.getSet(clazz));
        List<T> mustHave = mustHaveSave(skills);
        skills.removeAll(mustHave);
        setSkillSetProbability(skills);
        Set<T> finalSkillSet = new HashSet<>();
        if (clazz.equals(Backend.class)) {
            finalSkillSet.addAll(mustHave);
            int number = r.nextInt(MAX_BACKEND+1 - MIN_BACKEND) + MIN_BACKEND;
            for (int i = 0; i < number; i++) {
                int rand = r.nextInt(skillSetProbability.length);
                finalSkillSet.add(skills.get(skillSetProbability[rand]));
                deleteSkillSetProbabilitySkill(skills, rand);

            }
        } else {
            if (clazz.equals(Frontend.class)) {
                finalSkillSet.addAll(mustHave);
                int number = r.nextInt(MAX_FRONTEND+1 - MIN_FRONTEND) + MIN_FRONTEND - mustHave.size();
                for (int i = 0; i < number; i++) {
                    int rand = r.nextInt(skillSetProbability.length);
                    finalSkillSet.add(skills.get(skillSetProbability[rand]));
                    deleteSkillSetProbabilitySkill(skills, rand);
                }
            }
        }

        return finalSkillSet;
    }

    private List<T> mustHaveSave(List<T> skills) {
        List<T> mustHave = new LinkedList<>();
        for (T s : skills) {
            if (s.getProbability() < 0) {
                mustHave.add(s);
            }
        }
        return mustHave;
    }

    private void deleteSkillSetProbabilitySkill(List<T> skills, int rand) {
        T toRemove = skills.get(skillSetProbability[rand]);
        int toRemoveIndex = skillSetProbability[rand];
        int[] newProb = new int[skillSetProbability.length - toRemove.getProbability()];
        //System.out.println("Delete - " + toRemoveIndex + " N= " + toRemove.getProbability());
        //System.out.println(Arrays.toString(skillSetProbability) + " L: " + skillSetProbability.length);
        int index = 0;
        for (int i : skillSetProbability) {
            if (i != toRemoveIndex) {
                newProb[index] = i;
                index++;
            }
        }
        //skills.remove(toRemove);
        skillSetProbability = Arrays.copyOf(newProb, newProb.length);
    }

    private void setSkillSetProbability(List<T> skills) {
        List<int[]> arrays = new LinkedList<>();
        int total = 0;
        for (int i = 0; i < skills.size(); i++) {
            int[] temp = new int[skills.get(i).getProbability()];
            Arrays.fill(temp, i);
            arrays.add(temp);
            total += temp.length;
        }
        int[] concat = new int[total];
        total = 0;
        for (int[] a : arrays) {
            for (int v : a) {
                concat[total] = v;
                total++;
            }
        }
        skillSetProbability = concat;
    }
}
