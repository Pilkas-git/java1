package lt.vu.usecases;

import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.NumberGenerator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateStudentNumber implements Serializable {
    @Inject
    NumberGenerator numberGenerator;

    private CompletableFuture<Integer> numberGenerationTask = null;

    @PostConstruct
    private void printOnCreation() {
        System.out.println("NEW BEAN FOR GENERATING STUDENT NUMBER CREATED");
    }

    @LoggedInvocation
    public String generateNewNumber() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        numberGenerationTask = CompletableFuture.supplyAsync(() -> numberGenerator.generateNumber());

        return  "/studentDetails.xhtml?faces-redirect=true&studentId=" + requestParameters.get("studentId");
    }

    public void startWork() {
        numberGenerationTask = CompletableFuture.supplyAsync(() -> numberGenerator.generateNumber());
    }

    public String getGenerationStatus() throws ExecutionException, InterruptedException {
        if (numberGenerationTask == null) {
            return null;
        } else if (isGenerationRunning()) {
            return "Number generation in progress";
        }
        return "Suggested number: " + numberGenerationTask.get();
    }

    public boolean isGenerationRunning() {
        return numberGenerationTask != null && !numberGenerationTask.isDone();
    }
}
