QT_LIBINFIX="E"

QT_DIR_NAME = "qtopia"
QT_LIBINFIX = "E"

require python-pyside.inc

DESCRIPTION = "Python Bindings for Qt ${QTV} embedded (DirectFB) with QWS"

DEPENDS += "qt4-embedded"

RDEPENDS_${PN} = " \
 python-lang \
 qt4-embedded \
"

SRC_URI += "file://support-qws.patch \
"

OECMAKE_CXX_FLAGS_append=" -DQ_WS_QWS "
CXX_DEFINES+=" -DQ_WS_QWS "

export CXX_DEFINES

