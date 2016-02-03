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


import org.jlab.clara.claraol.DataSource;
import org.jlab.clara.claraol.Identity;
import org.jlab.clara.claraol.Vocabulary;
import org.protege.owl.codegeneration.impl.WrappedIndividualImpl;
import org.protege.owl.codegeneration.inference.CodeGenerationInference;
import org.semanticweb.owlapi.model.IRI;

import java.util.Collection;


/**
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: DefaultDataSource <br>
 * @version generated on Sat Jan 30 17:43:59 EST 2016 by gurjyan
 */
public class DefaultDataSource extends WrappedIndividualImpl implements DataSource {

    public DefaultDataSource(CodeGenerationInference inference, IRI iri) {
        super(inference, iri);
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
     * Data Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#hasType
     */
     
    public Collection<? extends String> getHasType() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASTYPE, String.class);
    }

    public boolean hasHasType() {
		return !getHasType().isEmpty();
    }

    public void addHasType(String newHasType) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASTYPE, newHasType);
    }

    public void removeHasType(String oldHasType) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASTYPE, oldHasType);
    }


    /* ***************************************************
     * Data Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#hasValue
     */
     
    public Collection<? extends String> getHasValue() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASVALUE, String.class);
    }

    public boolean hasHasValue() {
		return !getHasValue().isEmpty();
    }

    public void addHasValue(String newHasValue) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASVALUE, newHasValue);
    }

    public void removeHasValue(String oldHasValue) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASVALUE, oldHasValue);
    }


    /* ***************************************************
     * Data Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#isDirectory
     */
     
    public Collection<? extends Boolean> getIsDirectory() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_ISDIRECTORY, Boolean.class);
    }

    public boolean hasIsDirectory() {
		return !getIsDirectory().isEmpty();
    }

    public void addIsDirectory(Boolean newIsDirectory) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_ISDIRECTORY, newIsDirectory);
    }

    public void removeIsDirectory(Boolean oldIsDirectory) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_ISDIRECTORY, oldIsDirectory);
    }


    /* ***************************************************
     * Data Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#isFile
     */
     
    public Collection<? extends Boolean> getIsFile() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_ISFILE, Boolean.class);
    }

    public boolean hasIsFile() {
		return !getIsFile().isEmpty();
    }

    public void addIsFile(Boolean newIsFile) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_ISFILE, newIsFile);
    }

    public void removeIsFile(Boolean oldIsFile) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_ISFILE, oldIsFile);
    }


}
