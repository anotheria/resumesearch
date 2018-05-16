package com.bootcamp.profilegenerator.main;

import com.bootcamp.profilegenerator.generator.NameGenerator;
import com.bootcamp.profilegenerator.generator.SkillSetGenerator;
import com.bootcamp.profilegenerator.generator.TypeProbabilityGenerator;
import com.bootcamp.profilegenerator.model.Backend;
import com.bootcamp.profilegenerator.model.Frontend;
import com.bootcamp.profilegenerator.model.Profile;
import com.bootcamp.profilegenerator.model.Skill;

import java.util.*;

public class Main {
    public static void main(String... args) {
        TypeProbabilityGenerator t = TypeProbabilityGenerator.getInstance();
        Map<Class, Integer> m = new HashMap<>();
        m.put(Backend.class, 75);
        m.put(Frontend.class, 25);

        int profilesNumber = 50;
        if (args.length > 0 && Integer.parseInt(args[0]) > 0) {
            profilesNumber = Integer.parseInt(args[0]);
        }

        List<Profile> profiles = new LinkedList<>();
        for (int i = 0; i < profilesNumber; i++) {

            Class c = t.getRandomClass(m);

            SkillSetGenerator<Skill> sg = SkillSetGenerator.getSkillSetGenerator();
            NameGenerator n = NameGenerator.getInstance();

            Profile p = new Profile(n.getRandomName(), n.getRandomSurname(), sg.generateSkillSet(c));
            System.out.println("===============");
            System.out.println("Type : " + c);
            System.out.println("Name : " + p.getFirstName() + " " + p.getLastName());
            System.out.println("Skill number : " + p.getSkillSet().size());
            System.out.println("Skills : " + p.getSkillSet());
            System.out.println("===============");
            profiles.add(p);
        }

        //Now somehow we need to store profiles on our Solr Server.
        for(Profile p : profiles){
            //System.out.println(p.toString());
        }

    }
}
