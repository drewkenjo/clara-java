/*
 * Copyright (C) 2015. Jefferson Lab, CLARA framework (JLAB). All Rights Reserved.
 * Permission to use, copy, modify, and distribute this software and its
 * documentation for educational, research, and not-for-profit purposes,
 * without fee and without a signed licensing agreement.
 *
 * Contact Vardan Gyurjyan
 * Department of Experimental Nuclear Physics, Jefferson Lab.
 *
 * IN NO EVENT SHALL JLAB BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT, SPECIAL,
 * INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS, ARISING OUT OF
 * THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF JLAB HAS BEEN ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * JLAB SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE. THE CLARA SOFTWARE AND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED
 * HEREUNDER IS PROVIDED "AS IS". JLAB HAS NO OBLIGATION TO PROVIDE MAINTENANCE,
 * SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 */

package org.jlab.clara.claraol.impl;


import java.util.Collection;

import org.jlab.clara.claraol.Condition;
import org.jlab.clara.claraol.ConditionalRouting;
import org.jlab.clara.claraol.Service;
import org.jlab.clara.claraol.Vocabulary;
import org.protege.owl.codegeneration.impl.WrappedIndividualImpl;

import org.protege.owl.codegeneration.inference.CodeGenerationInference;

import org.semanticweb.owlapi.model.IRI;


/**
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: DefaultConditionalRouting <br>
 * @version generated on Tue Dec 22 14:51:01 EST 2015 by gurjyan
 */
public class DefaultConditionalRouting extends WrappedIndividualImpl implements ConditionalRouting {

    public DefaultConditionalRouting(CodeGenerationInference inference, IRI iri) {
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
     * Object Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#ifFalseSend
     */
     
    public Collection<? extends Service> getIfFalseSend() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_IFFALSESEND,
                                               DefaultService.class);
    }

    public boolean hasIfFalseSend() {
	   return !getIfFalseSend().isEmpty();
    }

    public void addIfFalseSend(Service newIfFalseSend) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_IFFALSESEND,
                                       newIfFalseSend);
    }

    public void removeIfFalseSend(Service oldIfFalseSend) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_IFFALSESEND,
                                          oldIfFalseSend);
    }


    /* ***************************************************
     * Object Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#ifTrueSend
     */
     
    public Collection<? extends Service> getIfTrueSend() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_IFTRUESEND,
                                               DefaultService.class);
    }

    public boolean hasIfTrueSend() {
	   return !getIfTrueSend().isEmpty();
    }

    public void addIfTrueSend(Service newIfTrueSend) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_IFTRUESEND,
                                       newIfTrueSend);
    }

    public void removeIfTrueSend(Service oldIfTrueSend) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_IFTRUESEND,
                                          oldIfTrueSend);
    }


}