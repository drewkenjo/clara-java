#!/usr/bin/env bash


if [ -z "${CLARA_HOME}" ]; then
    echo "CLARA_HOME is not defined. Exiting..."
    exit
fi

if [ -z "${CLASSPATH}" ]; then
    # Find plugins directory
    CLASSPATH="${CLARA_HOME}/lib/*"
    if [ -n "${CLARA_PLUGINS}" ]; then
        if [ ! -d "${CLARA_PLUGINS}" ]; then
            echo "Error: \$CLARA_PLUGINS is not a directory."
            exit 1
        fi
        plugins_dir="${CLARA_PLUGINS}"
    else
        plugins_dir="${CLARA_HOME}/plugins"
    fi

    # Add every plugin
    for plugin in "${plugins_dir}"/*; do
        if [ -d "${plugin}" ]; then
            CLASSPATH+=":${plugin}/services/*:${plugin}/lib/*"

            # Support subdirectories inside lib/
            for libdir in "${plugin}"/lib/*; do
                if [ -d "${libdir}" ]; then
                    CLASSPATH+=":${libdir}/*"
                fi
            done
        fi
    done

    CLASSPATH+=":${CLARA_HOME}/services/*"
    export CLASSPATH
fi


if [ -n "${JAVA_OPTS}" ]; then
    jvm_options=(${JAVA_OPTS})
else
    jvm_options=(-Xms512m -Xmx512m -XX:+UseNUMA -XX:+UseBiasedLocking)
fi

if [ -n "${JAVA_HOME}" ]; then
    java_bin="${JAVA_HOME}/bin/java"
else
    java_bin=java
fi

MAIN=org.jlab.clas.std.orchestrators.CloudOrchestrator

"${java_bin}" "${jvm_options[@]}" -cp $CLASSPATH $MAIN "$@"

# Cleanup after we done
#remove-dpe

exit ${PIPESTATUS[0]}