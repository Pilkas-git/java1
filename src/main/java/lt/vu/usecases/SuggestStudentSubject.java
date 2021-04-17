package lt.vu.usecases;

import lt.vu.entities.Student;
import lt.vu.entities.Subject;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.StudentsDAO;
import lt.vu.persistence.SubjectsDAO;
import lt.vu.services.SubjectSuggester;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RequestScoped
@Named
public class SuggestStudentSubject implements Serializable {

    @Inject
    SubjectsDAO subjectsDAO;

    @Inject
    StudentsDAO studentsDAO;

    @Inject
    SubjectSuggester subjectSuggester;

    private CompletableFuture<String> suggestionGenerationTask = null;

    @LoggedInvocation
    public String suggestSubject() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer studentId = Integer.parseInt(requestParameters.get("studentId"));
        Student student = studentsDAO.findOne(studentId);
        List<Subject> subjects = subjectsDAO.loadAll();
        subjects.removeAll(student.getSubjects());

        suggestionGenerationTask = CompletableFuture.supplyAsync(() ->
                subjectSuggester.suggestSubject(subjects));

        return  "/studentDetails.xhtml?faces-redirect=false&studentId=" + studentId;
    }

    public String getGenerationStatus() throws ExecutionException, InterruptedException {
        if (suggestionGenerationTask == null) {
            return null;
        }

        return "Suggested position: " + suggestionGenerationTask.get();
    }
}
