package com.bootcamp.profilegenerator.generator;

import com.bootcamp.profilegenerator.constants.Skills;
import com.bootcamp.profilegenerator.model.Backend;
import com.bootcamp.profilegenerator.model.Frontend;
import com.bootcamp.profilegenerator.model.Skill;

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
     * TODO generation for Frontend skills
     * @param clazz
     * @return
     */
    public Set<T> generateSkillSet(Class<T> clazz) {
        List<T> skills = new LinkedList<>((Set<T>) Skills.getSet(clazz));
        setSkillSetProbability(skills);

        if (clazz.getClass().isInstance(Backend.class)) {
            int number = r.nextInt(MAX_BACKEND - MIN_BACKEND) + MIN_BACKEND;
            for (int i = 0; i < number; i++) {
                //int[] skillArr = skillSetProbability;
                int rand = r.nextInt(skillSetProbability.length);

                System.out.println(skills.get(skillSetProbability[rand]).toString());
                deleteSkillSetProbabilitySkill(skills, rand);
            }
        } else {
            if (clazz.getClass().isInstance(Frontend.class)) {

            }
        }

        return null;
    }

    private void deleteSkillSetProbabilitySkill(List<T> skills, int rand) {
        T toRemove = skills.get(skillSetProbability[rand]);
        int toRemoveIndex = skillSetProbability[rand];
        int[] newProb = new int[skillSetProbability.length - toRemove.getProbability()];
        //System.out.println("Delete - " + toRemoveIndex + " N= " + toRemove.getProbability());
        //System.out.println(Arrays.toString(skillSetProbability) + " L: " + skillSetProbability.length);
        int index = 0;
        for(int i : skillSetProbability){
            if(i != toRemoveIndex){
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
