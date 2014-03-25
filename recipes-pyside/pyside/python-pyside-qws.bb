QT_LIBINFIX="E"

require python-pyside.inc

DESCRIPTION = "Python Bindings for Qt ${QTV} QWS"

RDEPENDS_${PN} = " \
 python-lang \
 qt4-embedded \
"


SRC_URI += "file://support-qws.patch \
"

OECMAKE_CXX_FLAGS_append=" -DQ_WS_QWS "
CXX_DEFINES+=" -DQ_WS_QWS "

export CXX_DEFINES

