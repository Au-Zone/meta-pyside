DESCRIPTION = "Python Bindings for Qt ${QTV} embedded (DirectFB) with QWS"

QT_LIBINFIX = "E"

QT_DIR_NAME = "qtopia"
PR = "${INC_PR}.1"

require python-pyside.inc

DEPENDS += "qt4-embedded"
RCONFLICTS_${PN} += "python-pyside-x11"

PKG_${PN} = "pyside-${PYTHON_DIR}-embedded"

RDEPENDS_${PN} = " \
 python-core \
 python-lang \
 qt4-embedded \
"

SRC_URI += "file://support-qws.patch \
"

OECMAKE_CXX_FLAGS_append=" -DQ_WS_QWS "
CXX_DEFINES+=" -DQ_WS_QWS "

export CXX_DEFINES

