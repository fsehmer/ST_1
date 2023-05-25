import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.apache.jena.*;
import org.apache.jena.ext.xerces.xs.datatypes.XSDateTime;
import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;

import javax.xml.crypto.Data;

public class Exercise2 {
    public static void main(String[] args) {
        String baseUri = "https://company.com/myOntology#";
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

        /**
         * Create factory class
         */
        OntClass factory = model.createClass(baseUri + "Factory");

        DatatypeProperty locatedIn = model.createDatatypeProperty(baseUri + "locatedIn");
        locatedIn.addDomain(factory);
        locatedIn.addRange(XSD.xstring);

        DatatypeProperty numberOfEmployees = model.createDatatypeProperty(baseUri + "numberOfEmployees");
        numberOfEmployees.addDomain(factory);
        numberOfEmployees.addRange(XSD.xint);

        DatatypeProperty yearBuilt = model.createDatatypeProperty(baseUri+ "yearBuilt");
        yearBuilt.addDomain(factory);
        yearBuilt.addRange(XSD.gYear);

        DatatypeProperty ledByManager = model.createDatatypeProperty(baseUri+ "ledByManager");
        ledByManager.addDomain(factory);
        ledByManager.addRange(XSD.xstring);

        /**
         * Create production class
         */

        OntClass productionStep = model.createClass(baseUri + "productionStep");

        DatatypeProperty costInDollars = model.createDatatypeProperty(baseUri + "costInDollars");
        costInDollars.addDomain(productionStep);
        costInDollars.addRange(XSD.decimal);

        DatatypeProperty durationInDays = model.createDatatypeProperty(baseUri + "durationInDays");
        durationInDays.addDomain(productionStep);
        durationInDays.addRange(XSD.xint);

        DatatypeProperty productionExecutedIn = model.createDatatypeProperty(baseUri + "productionExecutedIn");
        productionExecutedIn.addDomain(productionStep);
        productionExecutedIn.addRange(factory);


        OntClass productionPlanning = model.createClass(baseUri + "productionPlanning");
        OntClass materialPreparation = model.createClass(baseUri + "materialPreparation");
        OntClass intermediateProduction = model.createClass(baseUri + "intermediateProduction");
        OntClass assembly = model.createClass(baseUri + "assembly");

        productionStep.addSubClass(productionPlanning);
        productionStep.addSubClass(materialPreparation);
        productionStep.addSubClass(intermediateProduction);
        productionStep.addSubClass(assembly);

        OntClass productionProcess = model.createClass(baseUri + "productionProcess");

        DatatypeProperty startTime = model.createDatatypeProperty(baseUri+ "startTime");
        startTime.addDomain(productionProcess);
        startTime.addDomain(productionStep);
        startTime.addRange(XSD.dateTime);

        DatatypeProperty endTime = model.createDatatypeProperty(baseUri+ "endTime");
        endTime.addDomain(productionProcess);
        endTime.addDomain(productionStep);
        endTime.addRange(XSD.dateTime);

        /**
        DatatypeProperty referenceProduct = model.createDatatypeProperty(baseUri + "referenceProduct");
        referenceProduct.addDomain(productionProcess);
        referenceProduct.addRange(XSD.xstring); // add product class later
        **/

        OntClass productReference = model.createClass(baseUri + "productReference");

        DatatypeProperty productDescription = model.createDatatypeProperty(baseUri+ "productDescription");
        productDescription.addDomain(productReference);
        productDescription.addRange(XSD.xstring);

        DatatypeProperty orderDate = model.createDatatypeProperty(baseUri + "orderDate");
        orderDate.addDomain(productReference);
        orderDate.addRange(XSD.dateTime);



        Resource factory_1 = factory.createIndividual(baseUri + "factory1")
                .addProperty(locatedIn, "Hungary")
                .addProperty(numberOfEmployees, "100")
                .addProperty(yearBuilt, "2001")
                .addProperty(ledByManager, "Max Mustermann");

        Resource factory_2 = factory.createIndividual(baseUri + "factory2")
                .addProperty(locatedIn, "Italy")
                .addProperty(numberOfEmployees, "200")
                .addProperty(yearBuilt, "2001")
                .addProperty(ledByManager, "Max Mustermann");

        Resource factory_3 = factory.createIndividual(baseUri + "factory3")
                .addProperty(locatedIn, "Saudi Arabia")
                .addProperty(numberOfEmployees, "300");






















        model.write(System.out, "RDF/XML-ABBREV");
    }
}
