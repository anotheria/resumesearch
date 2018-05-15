package com.bootcamp.profilegenerator.main;

import com.bootcamp.profilegenerator.generator.NameGenerator;
import com.bootcamp.profilegenerator.generator.SkillSetGenerator;
import com.bootcamp.profilegenerator.model.Backend;
import com.bootcamp.profilegenerator.model.Frontend;
import com.bootcamp.profilegenerator.model.Profile;
import com.bootcamp.profilegenerator.model.Skill;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String... args) {
        SkillSetGenerator<Backend> sg = SkillSetGenerator.getSkillSetGenerator();
        sg.generateSkillSet(Backend.class);
        NameGenerator n = NameGenerator.getInstance();
        System.exit(0);
        for (int i = 0; i < 10; i++) {
            System.out.println("Name: " + n.getRandomName() + " " + n.getRandomSurname());
        }

        Set<Backend> l1 = new HashSet<>();
        Set<Frontend> l2 = new HashSet<>();


        Backend b = new Backend("Backend", 100);
        Frontend f = new Frontend("Frontend", 100);

        l1.add(b);
        l2.add(f);

        Profile<Backend> p = new Profile<>("Name", "Lastname", l1);

    }
}
