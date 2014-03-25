DESCRIPTION = "Python Bindings for Qt ${QTV} X11"
QT_LIBINFIX=""
PR = "${INC_PR}.1"

RDEPENDS_${PN} = " \
 python-lang \
 qt4-x11-free \
"

require python-pyside.inc

OECMAKE_CXX_FLAGS_append=" -DQ_WS_X11 "
CXX_DEFINES+=" -DQ_WS_X11 "

