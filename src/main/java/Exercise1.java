import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.apache.jena.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;

public class Exercise1 {
    public static void main(String[] args) {

        String rootUri = "https://www.uni-trier.de/";
        String siteURI = "index.php?id=1890";
        String personURI = "RalphBergmann";

        Model model = ModelFactory.createDefaultModel();

        Property hasCreated = model.createProperty(rootUri + "hasCreated");
        Property hasEMail = model.createProperty(rootUri+"hasEMail");
        Property hasFullName = model.createProperty(rootUri+"hasFullName");

        Resource bergmann = model.createResource(rootUri+personURI);
        Resource site = model.createResource(rootUri+siteURI);

        bergmann.addProperty(hasCreated, site);
        bergmann.addProperty(hasEMail,"bergmann@uni-trier.de");
        bergmann.addProperty(hasFullName, "Ralph Bergmann");

        String stUri = "SemanticTechnologies";

        Resource lectureST = model.createResource(rootUri + stUri);
        Property givesLecture = model.createProperty(rootUri+"givesLecture");
        bergmann.addProperty(givesLecture,lectureST);

        Resource exercise = model.createResource(rootUri + "exercise");
        Property has = model.createProperty(rootUri + "has");
        lectureST.addProperty(has,exercise);

        Resource assignment1 = model.createResource(rootUri+"assignment1");
        Resource assignment2 = model.createResource(rootUri+"assignment2");
        Resource assignment3 = model.createResource(rootUri+"assignment3");

        RDFList assignmentList = model.createList(assignment1,assignment2,assignment3);

        exercise.addProperty(has, assignmentList);

        Property givesExercise = model.createProperty(rootUri+"givesExercise");
        Resource hoffmann = model.createResource(rootUri + "Hoffmann");
        hoffmann.addProperty(hasEMail, "hoffmann@uni-trier.de");
        hoffmann.addProperty(givesExercise, exercise);

        model.write(System.out);

    }
}
