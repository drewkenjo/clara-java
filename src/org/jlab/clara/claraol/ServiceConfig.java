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

package org.jlab.clara.claraol;

import org.protege.owl.codegeneration.WrappedIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import java.util.Collection;

/**
 * 
 * <p>
 * Generated by Protege (http://protege.stanford.edu). <br>
 * Source Class: ServiceConfig <br>
 * @version generated on Sat Jan 30 17:43:59 EST 2016 by gurjyan
 */

public interface ServiceConfig extends WrappedIndividual {

    /* ***************************************************
     * Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#broadcastDone
     */
     
    /**
     * Gets all property values for the broadcastDone property.<p>
     * 
     * @return a collection of values for the broadcastDone property.
     */
    Collection<? extends Boolean> getBroadcastDone();

    /**
     * Checks if the class has a broadcastDone property value.<p>
     * 
     * @return true if there is a broadcastDone property value.
     */
    boolean hasBroadcastDone();

    /**
     * Adds a broadcastDone property value.<p>
     * 
     * @param newBroadcastDone the broadcastDone property value to be added
     */
    void addBroadcastDone(Boolean newBroadcastDone);

    /**
     * Removes a broadcastDone property value.<p>
     * 
     * @param oldBroadcastDone the broadcastDone property value to be removed.
     */
    void removeBroadcastDone(Boolean oldBroadcastDone);



    /* ***************************************************
     * Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#broadcastError
     */
     
    /**
     * Gets all property values for the broadcastError property.<p>
     * 
     * @return a collection of values for the broadcastError property.
     */
    Collection<? extends Boolean> getBroadcastError();

    /**
     * Checks if the class has a broadcastError property value.<p>
     * 
     * @return true if there is a broadcastError property value.
     */
    boolean hasBroadcastError();

    /**
     * Adds a broadcastError property value.<p>
     * 
     * @param newBroadcastError the broadcastError property value to be added
     */
    void addBroadcastError(Boolean newBroadcastError);

    /**
     * Removes a broadcastError property value.<p>
     * 
     * @param oldBroadcastError the broadcastError property value to be removed.
     */
    void removeBroadcastError(Boolean oldBroadcastError);



    /* ***************************************************
     * Property http://claraweb.jlab.org/ontology/2015/11/ClaraOL#broadcastWarning
     */
     
    /**
     * Gets all property values for the broadcastWarning property.<p>
     * 
     * @return a collection of values for the broadcastWarning property.
     */
    Collection<? extends Boolean> getBroadcastWarning();

    /**
     * Checks if the class has a broadcastWarning property value.<p>
     * 
     * @return true if there is a broadcastWarning property value.
     */
    boolean hasBroadcastWarning();

    /**
     * Adds a broadcastWarning property value.<p>
     * 
     * @param newBroadcastWarning the broadcastWarning property value to be added
     */
    void addBroadcastWarning(Boolean newBroadcastWarning);

    /**
     * Removes a broadcastWarning property value.<p>
     * 
     * @param oldBroadcastWarning the broadcastWarning property value to be removed.
     */
    void removeBroadcastWarning(Boolean oldBroadcastWarning);



    /* ***************************************************
     * Common interfaces
     */

    OWLNamedIndividual getOwlIndividual();

    OWLOntology getOwlOntology();

    void delete();

}