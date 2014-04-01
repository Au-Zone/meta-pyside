QT_DIR_NAME = "qtopia"
QT_LIBINFIX = "E"

require shiboken.inc

DEPENDS += "qt4-embedded"
RCONFLICTS_${PN} += "shiboken-x11"

