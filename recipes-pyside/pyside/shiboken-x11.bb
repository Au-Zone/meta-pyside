QT_DIR_NAME = "qt4"
QT_LIBINFIX = ""

require shiboken.inc

DEPENDS += "qt4-x11-free"
RCONFLICTS_${PN} += "shiboken-embedded"
