package claraol.impl;

import claraol.*;

import java.util.Collection;

import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.WrappedIndividualImpl;

import org.protege.owl.codegeneration.inference.CodeGenerationInference;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;


/**
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: DefaultAction <br>
 * @version generated on Sat Jan 30 17:43:59 EST 2016 by gurjyan
 */
public class DefaultAction extends WrappedIndividualImpl implements Action {

    public DefaultAction(CodeGenerationInference inference, IRI iri) {
        super(inference, iri);
    }





    /* ***************************************************
     * Object Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#sendsData
     */
     
    public Collection<? extends DataSource> getSendsData() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_SENDSDATA,
                                               DefaultDataSource.class);
    }

    public boolean hasSendsData() {
	   return !getSendsData().isEmpty();
    }

    public void addSendsData(DataSource newSendsData) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_SENDSDATA,
                                       newSendsData);
    }

    public void removeSendsData(DataSource oldSendsData) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_SENDSDATA,
                                          oldSendsData);
    }


    /* ***************************************************
     * Object Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#sendsTo
     */
     
    public Collection<? extends Service> getSendsTo() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_SENDSTO,
                                               DefaultService.class);
    }

    public boolean hasSendsTo() {
	   return !getSendsTo().isEmpty();
    }

    public void addSendsTo(Service newSendsTo) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_SENDSTO,
                                       newSendsTo);
    }

    public void removeSendsTo(Service oldSendsTo) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_SENDSTO,
                                          oldSendsTo);
    }


}
