/*
 *   Copyright (c) 2016.  Jefferson Lab (JLab). All rights reserved. Permission
 *   to use, copy, modify, and distribute  this software and its documentation for
 *   educational, research, and not-for-profit purposes, without fee and without a
 *   signed licensing agreement.
 *
 *   IN NO EVENT SHALL JLAB BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT, SPECIAL
 *   INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS, ARISING
 *   OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF JLAB HAS
 *   BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *   JLAB SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 *   THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *   PURPOSE. THE CLARA SOFTWARE AND ACCOMPANYING DOCUMENTATION, IF ANY,
 *   PROVIDED HEREUNDER IS PROVIDED "AS IS". JLAB HAS NO OBLIGATION TO PROVIDE
 *   MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 *   This software was developed under the United States Government license.
 *   For more information contact author at gurjyan@jlab.org
 *   Department of Experimental Nuclear Physics, Jefferson Lab.
 */

package org.jlab.clara.claraol.impl;


import org.jlab.clara.claraol.*;
import org.protege.owl.codegeneration.impl.WrappedIndividualImpl;
import org.protege.owl.codegeneration.inference.CodeGenerationInference;
import org.semanticweb.owlapi.model.IRI;

import java.util.Collection;


/**
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: DefaultService <br>
 * @version generated on Sat Jan 30 17:43:59 EST 2016 by gurjyan
 */
public class DefaultService extends WrappedIndividualImpl implements Service {

    public DefaultService(CodeGenerationInference inference, IRI iri) {
        super(inference, iri);
    }





    /* ***************************************************
     * Object Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#hasCondition
     */
     
    public Collection<? extends Condition> getHasCondition() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASCONDITION,
                                               DefaultCondition.class);
    }

    public boolean hasHasCondition() {
	   return !getHasCondition().isEmpty();
    }

    public void addHasCondition(Condition newHasCondition) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASCONDITION,
                                       newHasCondition);
    }

    public void removeHasCondition(Condition oldHasCondition) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASCONDITION,
                                          oldHasCondition);
    }


    /* ***************************************************
     * Object Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#hasConfig
     */
     
    public Collection<? extends ServiceConfig> getHasConfig() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASCONFIG,
                                               DefaultServiceConfig.class);
    }

    public boolean hasHasConfig() {
	   return !getHasConfig().isEmpty();
    }

    public void addHasConfig(ServiceConfig newHasConfig) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASCONFIG,
                                       newHasConfig);
    }

    public void removeHasConfig(ServiceConfig oldHasConfig) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASCONFIG,
                                          oldHasConfig);
    }


    /* ***************************************************
     * Object Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#hasEngine
     */
     
    public Collection<? extends Engine> getHasEngine() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASENGINE,
                                               DefaultEngine.class);
    }

    public boolean hasHasEngine() {
	   return !getHasEngine().isEmpty();
    }

    public void addHasEngine(Engine newHasEngine) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASENGINE,
                                       newHasEngine);
    }

    public void removeHasEngine(Engine oldHasEngine) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASENGINE,
                                          oldHasEngine);
    }


    /* ***************************************************
     * Object Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#hasIdentity
     */
     
    public Collection<? extends Identity> getHasIdentity() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASIDENTITY,
                                               DefaultIdentity.class);
    }

    public boolean hasHasIdentity() {
	   return !getHasIdentity().isEmpty();
    }

    public void addHasIdentity(Identity newHasIdentity) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASIDENTITY,
                                       newHasIdentity);
    }

    public void removeHasIdentity(Identity oldHasIdentity) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASIDENTITY,
                                          oldHasIdentity);
    }


    /* ***************************************************
     * Object Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#hasLink
     */
     
    public Collection<? extends Service> getHasLink() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASLINK,
                                               DefaultService.class);
    }

    public boolean hasHasLink() {
	   return !getHasLink().isEmpty();
    }

    public void addHasLink(Service newHasLink) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASLINK,
                                       newHasLink);
    }

    public void removeHasLink(Service oldHasLink) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASLINK,
                                          oldHasLink);
    }


    /* ***************************************************
     * Object Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#hasState
     */
     
    public Collection<? extends State> getHasState() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASSTATE,
                                               DefaultState.class);
    }

    public boolean hasHasState() {
	   return !getHasState().isEmpty();
    }

    public void addHasState(State newHasState) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASSTATE,
                                       newHasState);
    }

    public void removeHasState(State oldHasState) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASSTATE,
                                          oldHasState);
    }


    /* ***************************************************
     * Data Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#hasContainer
     */
     
    public Collection<? extends String> getHasContainer() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASCONTAINER, String.class);
    }

    public boolean hasHasContainer() {
		return !getHasContainer().isEmpty();
    }

    public void addHasContainer(String newHasContainer) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASCONTAINER, newHasContainer);
    }

    public void removeHasContainer(String oldHasContainer) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASCONTAINER, oldHasContainer);
    }


    /* ***************************************************
     * Data Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#hasDpe
     */
     
    public Collection<? extends String> getHasDpe() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASDPE, String.class);
    }

    public boolean hasHasDpe() {
		return !getHasDpe().isEmpty();
    }

    public void addHasDpe(String newHasDpe) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASDPE, newHasDpe);
    }

    public void removeHasDpe(String oldHasDpe) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASDPE, oldHasDpe);
    }


    /* ***************************************************
     * Data Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#hasPoolSize
     */
     
    public Collection<? extends Integer> getHasPoolSize() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASPOOLSIZE, Integer.class);
    }

    public boolean hasHasPoolSize() {
		return !getHasPoolSize().isEmpty();
    }

    public void addHasPoolSize(Integer newHasPoolSize) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASPOOLSIZE, newHasPoolSize);
    }

    public void removeHasPoolSize(Integer oldHasPoolSize) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASPOOLSIZE, oldHasPoolSize);
    }


}