/*
 * Copyright (C) 2015. Jefferson Lab, CLARA framework (JLAB). All Rights Reserved.
 * Permission to use, copy, modify, and distribute this software and its
 * documentation for educational, research, and not-for-profit purposes,
 * without fee and without a signed licensing agreement.
 *
 * Author Vardan Gyurjyan
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

package org.jlab.clara.base;

import org.jlab.clara.base.ClaraRequests.DeployContainerRequest;
import org.jlab.clara.base.ClaraRequests.DeployServiceRequest;
import org.jlab.clara.base.ClaraRequests.ExitRequest;
import org.jlab.clara.base.ClaraRequests.ServiceConfigRequestBuilder;
import org.jlab.clara.base.ClaraRequests.ServiceExecuteRequestBuilder;
import org.jlab.clara.base.error.ClaraException;
import org.jlab.clara.engine.EngineData;
import org.jlab.clara.engine.EngineDataType;
import org.jlab.clara.engine.EngineStatus;
import org.jlab.clara.util.CConstants;
import org.jlab.clara.util.ClaraUtil;
import org.jlab.clara.util.report.CReportTypes;
import org.jlab.coda.xmsg.core.*;
import org.jlab.coda.xmsg.data.xMsgM.xMsgMeta;
import org.jlab.coda.xmsg.data.xMsgR.xMsgRegistration;
import org.jlab.coda.xmsg.excp.xMsgException;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;


/**
 * Base class for orchestration of applications.
 */
@ParametersAreNonnullByDefault
public class BaseOrchestrator {

    //Set of user defined data types, that provide data specific serialization routines.
    private final Set<EngineDataType> dataTypes = new HashSet<>();

    // Map of subscription objects. Key = Clara_component_canonical_name # topic_of_subscription
    private final Map<String, xMsgSubscription> subscriptions = new HashMap<>();

    // ClaraBase reference
    private ClaraBase base = null;


    /**
     * Creates a new orchestrator.
     * Uses a random name and the local node as front-end.
     *
     * @throws IOException if localhost could not be obtained
     * @throws ClaraException if the orchestrator could not be created
     */
    public BaseOrchestrator() throws ClaraException, IOException {
        this(xMsgConstants.DEFAULT_POOL_SIZE);
    }

    /**
     * Creates a new orchestrator.
     * Uses a random name and receives the location of the front-end.
     *
     * @param subPoolSize set the size of the pool for processing subscriptions on background
     * @throws IOException if localhost could not be obtained
     * @throws ClaraException if the orchestrator could not be created
     */
    public BaseOrchestrator(int subPoolSize) throws ClaraException, IOException {
        this(ClaraUtil.getUniqueName(),
             new DpeName(xMsgUtil.localhost(), ClaraLang.JAVA),
             subPoolSize);
    }

    /**
     * Creates a new orchestrator.
     * Uses a random name and receives the location of the front-end.
     *
     * @param frontEnd use this front-end for communication with the Clara cloud
     * @throws IOException if localhost could not be obtained
     * @throws ClaraException if the orchestrator could not be created
     */
    public BaseOrchestrator(DpeName frontEnd) throws ClaraException {
        this(ClaraUtil.getUniqueName(),
             frontEnd,
             xMsgConstants.DEFAULT_POOL_SIZE);
    }

    /**
     * Creates a new orchestrator.
     * Uses a random name and receives the location of the front-end.
     *
     * @param frontEnd use this front-end for communication with the Clara cloud
     * @param subPoolSize set the size of the pool for processing subscriptions on background
     * @throws ClaraException if the orchestrator could not be created
     */
    public BaseOrchestrator(DpeName frontEnd, int subPoolSize) throws ClaraException {
        this(ClaraUtil.getUniqueName(),
             frontEnd,
             subPoolSize);
    }

    /**
     * Creates a new orchestrator.
     *
     * @param name the identification of this orchestrator
     * @param frontEnd use this front-end for communication with the Clara cloud
     * @param subPoolSize set the size of the pool for processing subscriptions on background
     * @throws ClaraException if the orchestrator could not be created
     */
    public BaseOrchestrator(String name, DpeName frontEnd, int subPoolSize)
            throws ClaraException {
        try {
            base = getClaraBase(name, frontEnd, subPoolSize);
        } catch (IOException e) {
            throw new ClaraException("Clara-Error: Could not start orchestrator", e);
        }
    }

    /**
     * Creates the internal base object.
     * It can be overridden to return a mock for testing purposes.
     *
     * @throws ClaraException
     * @throws IOException
     */
    ClaraBase getClaraBase(String name, DpeName frontEnd, int poolSize)
            throws IOException, ClaraException {
        ClaraComponent o = ClaraComponent.orchestrator(name, xMsgUtil.localhost(), poolSize, "");
        ClaraBase b = new ClaraBase(o) {
            @Override
            public void start(ClaraComponent component) {
                // Nothing
            }

            @Override
            public void end() {
                // Nothing
            }
        };
        b.setFrontEnd(ClaraComponent.dpe(frontEnd.canonicalName()));
        return b;
    }

    /**
     * Returns the map of subscriptions for testing purposes.
     *
     * @return {@link org.jlab.coda.xmsg.core.xMsgSubscription} objects
     *          mapped by the key = Key = Clara_component_canonical_name # topic_of_subscription
     */
    Map<String, xMsgSubscription> getSubscriptions() {
        return subscriptions;
    }


    /**
     * Registers the necessary data-types to communicate data to services.
     * {@link org.jlab.clara.engine.EngineDataType} object contains user
     * provided data serialization routine
     *
     * @param dataTypes service engine data types
     */
    public void registerDataTypes(EngineDataType... dataTypes) {
        Collections.addAll(this.dataTypes, dataTypes);
    }

    /**
     * Registers the necessary data-types to communicate data to services.
     * {@link org.jlab.clara.engine.EngineData} object contains user
     * provided data serialization routine
     *
     * @param dataTypes set of {@link org.jlab.clara.engine.EngineDataType} objects
     */
    public void registerDataTypes(Set<EngineDataType> dataTypes) {
        this.dataTypes.addAll(dataTypes);
    }


    /**
     * Tells a Clara DPE component to consider the passed Clara component as a front end.
     * This method is used at run-time to define/redefine front end DPE.
     *
     * @param dpe receiver DPE
     * @param frontEnd info about the front end DPE
     */
    public void setFrontEnd(ClaraComponent dpe, ClaraComponent frontEnd)
            throws IOException, xMsgException {

        xMsgTopic topic = ClaraUtil.buildTopic(CConstants.DPE, base.getFrontEnd().getCanonicalName());

        String data = ClaraUtil.buildData(CConstants.SET_FRONT_END_REMOTE, dpe.getDpeHost(),
                dpe.getDpePort(), dpe.getDpeLang(), frontEnd.getDpeHost(),
                frontEnd.getDpePort(), frontEnd.getDpeLang());
        base.send(base.getFrontEnd(), new xMsgMessage(topic, data));
    }

    /**
     * Sends a message to the front-end DPE asking to start a DPE. The new DPE info,
     * such as where DPE should start, on what port, language, pool size, etc, is defined
     * in ClaraComponent object. Note that front end is set/defined by the user from one
     * of the cloud DPEs.
     *
     * @param comp    DPE that must be started as a {@link org.jlab.clara.base.ClaraComponent} object
     * @param regHost registration service host that future DPE will use to register it's components
     * @param regPort registration service port number
     * @throws ClaraException
     * @throws xMsgException
     * @throws IOException
     * @throws TimeoutException
     */
    public void deployDpe(ClaraComponent comp, String regHost, int regPort)
            throws ClaraException, xMsgException, IOException, TimeoutException {
        if (comp.isDpe()) {
            xMsgTopic topic = ClaraUtil.buildTopic(CConstants.DPE, base.getFrontEnd().getCanonicalName());


            String data = ClaraUtil.buildData(CConstants.START_DPE,
                    comp.getDpeHost(),
                    comp.getDpePort(),
                    comp.getDpeLang(),
                    comp.getSubscriptionPoolSize(),
                    regHost, regPort,
                    comp.getDescription());
            base.send(base.getFrontEnd(), new xMsgMessage(topic, data));
        }
    }

    /**
     * Requests a DPE to exit.
     * The request is sent to a running DPE of the given language.
     * If no DPE is running in the node, the message is lost.
     *
     * @throws ClaraException if the request could not be sent
     */
    public void exitFrontEnd()
            throws ClaraException, xMsgException, IOException, TimeoutException {
        xMsgTopic topic = ClaraUtil.buildTopic(CConstants.DPE, base.getFrontEnd().getCanonicalName());

        String data = CConstants.STOP_DPE;
        base.send(base.getFrontEnd(), new xMsgMessage(topic, data));
    }

    public DeployContainerRequest deploy(ContainerName container) throws ClaraException {
        String dpeName = ClaraUtil.getDpeName(container.canonicalName());
        ClaraComponent targetDpe = ClaraComponent.dpe(dpeName);
        return new DeployContainerRequest(base, targetDpe, container);
    }

    public DeployServiceRequest deploy(ServiceName service, String classPath) throws ClaraException {
        String dpeName = ClaraUtil.getDpeName(service.canonicalName());
        ClaraComponent targetDpe = ClaraComponent.dpe(dpeName);
        return new DeployServiceRequest(base, targetDpe, service, classPath);
    }


    public ExitRequest exit(DpeName dpe) throws ClaraException {
        ClaraComponent targetDpe = ClaraComponent.dpe(dpe.canonicalName());
        return new ExitRequest(base, targetDpe, dpe);
    }

    public ExitRequest exit(ContainerName container) throws ClaraException {
        String dpeName = ClaraUtil.getDpeName(container.canonicalName());
        ClaraComponent targetDpe = ClaraComponent.dpe(dpeName);
        return new ExitRequest(base, targetDpe, container);
    }

    public ExitRequest exit(ServiceName service) throws ClaraException {
        String dpeName = ClaraUtil.getDpeName(service.canonicalName());
        ClaraComponent targetDpe = ClaraComponent.dpe(dpeName);
        return new ExitRequest(base, targetDpe, service);
    }


    /**
     * Pings a DPE. This is a sync request.
     *
     * @param dpe{@link org.jlab.clara.base.ClaraComponent} DPE
     * @param timeout sync request timeout
     * @return message {@link org.jlab.coda.xmsg.core.xMsgMessage}
     *         indicating the status of the sync operation.
     * @throws ClaraException
     * @throws xMsgException
     * @throws IOException
     * @throws TimeoutException
     */
    public xMsgMessage pingDpe(ClaraComponent dpe, int timeout)
            throws ClaraException, xMsgException, IOException, TimeoutException {
        return base.pingDpe(dpe, timeout);
    }

    /**
     * Returns a request builder to configure the given service.
     *
     * @param service the Clara service to be configured
     * @returns a builder to choose how to configure the service
     *          (with data, with report frequency, etc)
     */
    public ServiceConfigRequestBuilder configure(ServiceName service) throws ClaraException {
        String dpeName = ClaraUtil.getDpeName(service.canonicalName());
        ClaraComponent targetDpe = ClaraComponent.dpe(dpeName);
        return new ServiceConfigRequestBuilder(base, targetDpe, service, dataTypes);
    }


    /**
     * Returns a request builder to execute the given service.
     *
     * @param service the Clara service to be executed
     * @returns a builder to setup the execution request
     *          (with data, data types, etc)
     */
    public ServiceExecuteRequestBuilder execute(ServiceName service) throws ClaraException {
        String dpeName = ClaraUtil.getDpeName(service.canonicalName());
        ClaraComponent targetDpe = ClaraComponent.dpe(dpeName);
        return new ServiceExecuteRequestBuilder(base, targetDpe, service, dataTypes);
    }

    /**
     * Returns a request builder to execute the given composition.
     *
     * @param service the Clara composition to be executed
     * @returns a builder to to configure the execute request
     *          (with data, data types, etc)
     */
    public ServiceExecuteRequestBuilder execute(Composition composition) throws ClaraException {
        String dpeName = ClaraUtil.getDpeName(composition.firstService());
        ClaraComponent targetDpe = ClaraComponent.dpe(dpeName);
        return new ServiceExecuteRequestBuilder(base, targetDpe, composition, dataTypes);
    }



    /**
     * Sends a request to start reporting "done" on service executions.
     * Configures the service to repeatedly publish "done" messages after
     * a number of <code>eventCount</code> executions have been completed.
     * If the service does not exist, the message is lost.
     *
     * @param serviceCanonicalName the canonical name of the service
     * @param eventCount the interval of executions to be completed to publish the report
     * @throws ClaraException if the request could not be sent
     */
    public void startReportingDone(String serviceCanonicalName, int eventCount)
            throws ClaraException, IOException, xMsgException {

        base.startReporting(ClaraComponent.service(serviceCanonicalName), CReportTypes.DONE, eventCount);
    }


    /**
     * Sends a request to stop reporting "done" on service executions.
     * Configures the service to stop publishing "done" messages.
     * If the service does not exist, the message is lost.
     *
     * @param serviceCanonicalName the canonical name of the service
     * @throws ClaraException if the request could not be sent
     */
    public void stopReportingDone(String serviceCanonicalName)
            throws ClaraException, IOException, xMsgException {
        base.stopReporting(ClaraComponent.service(serviceCanonicalName), CReportTypes.DONE);
    }


    /**
     * Sends a request to start reporting the output data on service executions.
     * Configures the service to repeatedly publish the resulting output data after
     * a number of <code>eventCount</code> executions have been completed.
     * If the service does not exist, the message is lost.
     *
     * @param serviceCanonicalName the canonical name of the service
     * @param eventCount the interval of executions to be completed to publish the report
     * @throws ClaraException if the request could not be sent
     */
    public void startReportingData(String serviceCanonicalName, int eventCount)
            throws ClaraException, IOException, xMsgException {

        base.startReporting(ClaraComponent.service(serviceCanonicalName), CReportTypes.DATA, eventCount);

    }

    /**
     * Sends a request to stop reporting the output data on service executions.
     * Configures the service to stop publishing the resulting output data.
     * If the service does not exist, the message is lost.
     *
     * @param serviceCanonicalName the canonical name of the service
     * @throws ClaraException if the request could not be sent
     */
    public void stopReportingData(String serviceCanonicalName)
            throws ClaraException, IOException, xMsgException {
        base.stopReporting(ClaraComponent.service(serviceCanonicalName), CReportTypes.DATA);
    }


    /**
     * Subscribes to the specified status reports of the selected service.
     * <p>
     * A background thread is started to receive messages from the service.
     * Every time a report is received, the provided callback will be executed.
     * The messages are received sequentially, but the callback may run
     * in extra background threads, so it must be thread-safe.
     * <p>
     * Services will publish status reports after every execution that results
     * on error or warning.
     *
     * @param serviceCanonicalName the service to be listened
     * @param status the status to be listened
     * @param callback the action to be run when a message is received
     * @throws ClaraException if there was an error starting the subscription
     */
    public void listenServiceStatus(String serviceCanonicalName,
                                    EngineStatus status,
                                    EngineCallback callback) throws ClaraException, xMsgException {

        xMsgTopic topic = ClaraUtil.buildTopic(ClaraUtil.getStatusText(status), serviceCanonicalName);
        String key = ClaraUtil.getDpeHost(serviceCanonicalName) + CConstants.MAPKEY_SEP + topic;
        if (subscriptions.containsKey(key)) {
            throw new IllegalStateException("Clara-Error: Duplicated subscription to: " + serviceCanonicalName);
        }
        xMsgCallBack wrapperCallback = wrapEngineCallback(callback, status);
        xMsgSubscription handler = base.listen(ClaraComponent.service(serviceCanonicalName), topic, wrapperCallback);
        subscriptions.put(key, handler);
    }


    /**
     * Un-subscribes from the specified status reports of the selected service.
     *
     * @param serviceCanonicalName the service being listened
     * @param status the status being listened
     * @throws ClaraException if there was an error stopping the subscription
     */
    public void unListenServiceStatus(String serviceCanonicalName, EngineStatus status)
            throws ClaraException, xMsgException {
        xMsgTopic topic = ClaraUtil.buildTopic(ClaraUtil.getStatusText(status), serviceCanonicalName);
        String key = ClaraUtil.getDpeHost(serviceCanonicalName) + CConstants.MAPKEY_SEP + topic;
        xMsgSubscription handler = subscriptions.remove(key);
        if (handler != null) {
            base.unsubscribe(handler);
        }
    }


    /**
     * Subscribes to the data reports of the selected service.
     * <p>
     * A background thread is started to receive messages from the service.
     * Every time a report is received, the provided callback will be executed.
     * The messages are received sequentially, but the callback may run
     * in extra background threads, so it must be thread-safe.
     * <p>
     * Services will publish "data" reports if they are configured to do so
     * with {@link #startReportingData}. The messages will contain the full
     * output result of the service.
     *
     * @param serviceCanonicalName the service to be listened
     * @param callback the action to be run when a message is received
     * @throws ClaraException if there was an error starting the subscription
     */
    public void listenServiceData(String serviceCanonicalName, EngineCallback callback)
            throws ClaraException, xMsgException {

        xMsgTopic topic = ClaraUtil.buildTopic(xMsgConstants.DATA, serviceCanonicalName);
        String key = ClaraUtil.getDpeHost(serviceCanonicalName) + CConstants.MAPKEY_SEP + topic;
        if (subscriptions.containsKey(key)) {
            throw new IllegalStateException("Clara-Error: Duplicated subscription to: " + serviceCanonicalName);
        }
        xMsgCallBack wrapperCallback = wrapEngineCallback(callback, null);
        xMsgSubscription handler = base.listen(ClaraComponent.service(serviceCanonicalName), topic, wrapperCallback);
        subscriptions.put(key, handler);
    }

    /**
     * Un-subscribes from the data reports of the selected service.
     *
     * @param serviceCanonicalName the service being listened
     * @throws ClaraException if there was an error stopping the subscription
     */
    public void unListenServiceData(String serviceCanonicalName)
            throws ClaraException, xMsgException {

        xMsgTopic topic = ClaraUtil.buildTopic(xMsgConstants.DATA, serviceCanonicalName);
        String key = ClaraUtil.getDpeHost(serviceCanonicalName) + CConstants.MAPKEY_SEP + topic;

        xMsgSubscription handler = subscriptions.remove(key);
        if (handler != null) {
            base.unsubscribe(handler);
        }
    }

    /**
     * Subscribes to the "done" reports of the selected service.
     * <p>
     * A background thread is started to receive messages from the service.
     * Every time a report is received, the provided callback will be executed.
     * The messages are received sequentially, but the callback may run
     * in extra background threads, so it must be thread-safe.
     * <p>
     * Services will publish "done" reports if they are configured to do so
     * with {@link #startReportingDone}. The messages will not contain the full
     * output result of the service, but just a few stats about the execution.
     *
     * @param serviceCanonicalName the service to be listened
     * @param callback the action to be run when a message is received
     * @throws ClaraException if there was an error starting the subscription
     */
    public void listenServiceDone(String serviceCanonicalName, EngineCallback callback)
            throws ClaraException, xMsgException {
        xMsgTopic topic = ClaraUtil.buildTopic(xMsgConstants.DONE, serviceCanonicalName);
        String key = ClaraUtil.getDpeHost(serviceCanonicalName) + CConstants.MAPKEY_SEP + topic;
        if (subscriptions.containsKey(key)) {
            throw new IllegalStateException("Clara-Error: Duplicated subscription to: " + serviceCanonicalName);
        }
        xMsgCallBack wrapperCallback = wrapEngineCallback(callback, null);
        xMsgSubscription handler = base.listen(ClaraComponent.service(serviceCanonicalName), topic, wrapperCallback);
        subscriptions.put(key, handler);
    }

    /**
     * Un-subscribes from the "done" reports of the selected service.
     *
     * @param serviceCanonicalName the service being listened
     * @throws ClaraException if there was an error stopping the subscription
     */
    public void unListenServiceDone(String serviceCanonicalName)
            throws ClaraException, xMsgException {

        xMsgTopic topic = ClaraUtil.buildTopic(xMsgConstants.DONE, serviceCanonicalName);
        String key = ClaraUtil.getDpeHost(serviceCanonicalName) + CConstants.MAPKEY_SEP + topic;

        xMsgSubscription handler = subscriptions.remove(key);
        if (handler != null) {
            base.unsubscribe(handler);
        }
    }

    /**
     * Subscribes to the periodic alive message reported by the running DPEs.
     *
     * @param callback the action to be run when a report is received
     * @throws ClaraException if there was an error starting the subscription
     */
    public void listenDpe(String FrontEndDpeCanonicalName, GenericCallback callback)
            throws ClaraException, xMsgException {
        ClaraComponent dpe = ClaraComponent.dpe(FrontEndDpeCanonicalName);
        xMsgTopic topic = ClaraUtil.buildTopic(CConstants.DPE_ALIVE);
        String key = dpe.getDpeHost() + CConstants.MAPKEY_SEP + topic;
        if (subscriptions.containsKey(key)) {
            throw new IllegalStateException("Clara-Error: Duplicated subscription to: " + topic);
        }
        xMsgCallBack wrapperCallback = wrapGenericCallback(callback);
        xMsgSubscription handler = base.listen(dpe, topic, wrapperCallback);
        subscriptions.put(key, handler);
    }


    /**
     * Un-subscribes from the alive reports of the running DPEs.
     *
     * @throws ClaraException if there was an error stopping the subscription
     */
    public void unListenDpe(String FrontEndDpeCanonicalName)
            throws ClaraException, xMsgException {

        ClaraComponent dpe = ClaraComponent.dpe(FrontEndDpeCanonicalName);
        xMsgTopic topic = ClaraUtil.buildTopic(CConstants.DPE_ALIVE);
        String key = dpe.getDpeHost() + CConstants.MAPKEY_SEP + topic;

            xMsgSubscription handler = subscriptions.remove(key);
            if (handler != null) {
                base.unsubscribe(handler);
            }
    }

    /**
     * Uses a default registrar service address (defined at the
     * constructor) to ask the Set of registered DPEs.
     *
     * @return Set of DPE names: String
     * @throws ClaraException
     * @throws xMsgException
     */
    public Set<String> getDpeNames() throws ClaraException, xMsgException {
        xMsgTopic topic = xMsgTopic.build("xyz)");
        String rs = base.findSubscriberDomainNames(topic);
        StringTokenizer st = new StringTokenizer(rs);
        HashSet<String> result = new HashSet<>();
        while (st.hasMoreTokens()) {
            result.add(st.nextToken());
        }
        return result;
    }

    /**
     * Returns the names of all service containers of a particular DPE.
     * Request goes to the default registrar service, defined at the constructor.
     *
     * @param dpeName canonical name of a DPE
     * @return Set of container names of a DPE
     * @throws ClaraException
     * @throws xMsgException
     */
    public Set<String> getContainerNames(String dpeName) throws ClaraException, xMsgException {
        xMsgTopic topic = xMsgTopic.build(dpeName);
        String rs = base.findSubscriberSubjectNames(topic);
        StringTokenizer st = new StringTokenizer(rs);
        HashSet<String> result = new HashSet<>();
        while (st.hasMoreTokens()) {
            result.add(st.nextToken());
        }
        return result;
    }

    /**
     * Returns service engine names of a particular container of a particular DPE.
     * Request goes to the default registrar service, defined at the constructor.
     *
     * @param dpeName  canonical name of a DPE
     * @param containerName canonical name of a container
     * @return Set of service engine names
     * @throws ClaraException
     * @throws xMsgException
     */
    public Set<String> getEngineNames(String dpeName, String containerName) throws ClaraException, xMsgException {
        xMsgTopic topic = xMsgTopic.build(dpeName, containerName);
        String rs = base.findSubscriberTypeNames(topic);
        StringTokenizer st = new StringTokenizer(rs);
        HashSet<String> result = new HashSet<>();
        while (st.hasMoreTokens()) {
            result.add(st.nextToken());
        }
        return result;
    }

    /**
     * Returns the registration information of a selected Clara actor.
     * The actor can be a DPE, a container or a service.
     *
     * @param canonicalName the name of the actor
     * @return Set of {@link org.jlab.coda.xmsg.data.xMsgR.xMsgRegistration} objects
     */
    public Set<xMsgRegistration> getRegistrationInfo(String canonicalName)
            throws ClaraException, xMsgException {
        xMsgTopic topic = xMsgTopic.wrap(canonicalName);
        return base.findSubscribers(topic);
    }


    public String meta2Json(xMsgMeta meta) throws ClaraException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String reg2Json(xMsgRegistration regData) throws ClaraException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns this orchestrator name.
     */
    public String getName() {
        return base.getName();
    }
}
