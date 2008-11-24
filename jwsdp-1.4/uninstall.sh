#!/bin/sh

#
# Copyright 2004 Sun Microsystems, Inc. All rights reserved.
# SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
#

# -----------------------------------------------------------------------------
# Uninstall this installation
#
# $Id: uninstall.sh,v 1.5 2004/03/30 23:28:01 ofung Exp $
# -----------------------------------------------------------------------------

# resolve links - $0 may be a softlink
PRG="$0"

while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '.*/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

# Get standard environment variables
PRGDIR=`dirname "$PRG"`
if [ -r "$PRGDIR"/jwsdp-shared/bin/setenv.sh ]; then
  . "$PRGDIR"/jwsdp-shared/bin/setenv.sh
fi

# Make sure prerequisite environment variables are set
if [ -z "$JAVA_HOME" ]; then
  echo "The JAVA_HOME environment variable is not defined"
  echo "This environment variable is needed to run this program"
  exit 1
fi
if [ -z "$UNINSTALL_JAR_FILE" ]; then
  echo "The UNINSTALL_JAR_FILE environment variable is not defined"
  echo "This environment variable is needed to run this program"
  exit 1
fi

# Check that the uninstaller's jar file exists.
if [ ! -r "$UNINSTALL_JAR_FILE" ]; then
  echo "Could not find the $UNINSTALL_JAR_FILE file. This file is needed to"
  echo "run this program. To correct this problem, reinstall the product in the"
  echo "following directory and then rerun this program:"
  echo "    $BASEDIR"
  exit 1
fi

# Usage function
usage() {
    echo
    if [ -n "$1" ]; then
        echo "Error: $1"
    fi
    echo "Usage: $0 [install-options] [-console|-silent]"
    echo
    echo "where install-options are:"
    echo "    -is:log <log file>"
    echo "    -is:debug"
    echo
    echo "and -console|-silent are defined as:"
    echo "    -console    The uninstaller is run from the command line"
    echo "    -silent     The uninstallation is done with no user interaction"
    cleanup 1
}

DEBUG_PROP=
LOG_FILE=/dev/null


# Process arguments
while [ $# != 0 ]; do
    case "$1" in
        -is:log)
            shift
            if [ -n "$1" ]; then
                LOG_FILE="$1"
                shift
            else
                usage
            fi;;
        -is:debug)
            shift
            DEBUG_PROP="-Dis.debug=1" ;;
        -help)
            shift
            usage;;
        -h)
            shift
            usage;;
        *)
            break;;
    esac
done


# Execute InstallShield uninstaller
exec 2> $LOG_FILE
"$JAVA_HOME"/bin/java -Djava.ext.dirs= -Djava.endorsed.dirs= -classpath "$UNINSTALL_JAR_FILE" $DEBUG_PROP JWSDP "$@"
