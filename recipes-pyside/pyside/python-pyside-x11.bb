DESCRIPTION = "Python Bindings for Qt ${QTV} X11"

QT_LIBINFIX=""

QT_DIR_NAME = "qt4"
PR = "${INC_PR}.1"

require python-pyside.inc

DEPENDS += "qt4-x11-free"
RCONFLICTS_${PN} += "python-pyside-qws"

PKG_${PN} = "pyside-${PYTHON_DIR}-x11"

RDEPENDS_${PN} = " \
 python-core \
 python-lang \
 qt4-x11-free \
"

OECMAKE_CXX_FLAGS_append=" -DQ_WS_X11 "
CXX_DEFINES+=" -DQ_WS_X11 "

