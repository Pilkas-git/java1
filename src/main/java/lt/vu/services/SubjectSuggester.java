package lt.vu.services;

import lt.vu.entities.Subject;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class SubjectSuggester implements Serializable {

    public String suggestSubject(List<Subject> subjects) {
        Random rand = new Random();
        if(subjects.isEmpty()) {
            return "No subjects available";
        }
        return subjects.get(rand.nextInt(subjects.size())).getName();
    }
}