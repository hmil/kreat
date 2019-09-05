#!/bin/sh
DYLD_FRAMEWORK_PATH=/Users/hmil/Desktop/qt-everywhere-src-5.13.0/qtbase/lib${DYLD_FRAMEWORK_PATH:+:$DYLD_FRAMEWORK_PATH}
export DYLD_FRAMEWORK_PATH
QT_PLUGIN_PATH=/Users/hmil/Desktop/qt-everywhere-src-5.13.0/qtbase/plugins${QT_PLUGIN_PATH:+:$QT_PLUGIN_PATH}
export QT_PLUGIN_PATH
exec /Users/hmil/Desktop/qt-everywhere-src-5.13.0/qtbase/bin/uic "$@"
