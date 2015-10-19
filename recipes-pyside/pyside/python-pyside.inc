QTV = "4.7"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=15a1ca44f90f3ab457d6a4fe7c0f3a19"

DEPENDS = "generatorrunner-native shiboken-native"

INC_PR = "r2"
QT_DIR_NAME ?= "qt4"

SRC_URI = "git://github.com/PySide/PySide.git;protocol=git;tag=d2a47ab8f27af7e74d34797464da85c128c17c37 \
           file://MacroPushRequiredVars.cmake \
           file://FindQt4.cmake \
           file://no-accessibility-support.patch \
"

SRC_URI[md5sum] = "946e8988e5f4c4bd62e774407fa80fee"
SRC_URI[sha256sum] = "82c6c24dc55458ed047eba9fe700894a3347cd53462b21a97b7b5f9180b2a896"

inherit cmake

PYTHON_DIR="python2.7"

OE_CMAKE_AR = "${STAGING_BINDIR_TOOLCHAIN}/${AR}"
EXTRA_OECMAKE += " \
  -DBUILD_TESTS=FALSE \
  -DCMAKE_AR=${OE_CMAKE_AR} \
  -DQT_LIBINFIX=${QT_LIBINFIX} \
  -DQT_MKSPECS_DIR=${STAGING_DATADIR}/${QT_DIR_NAME}/mkspecs/qws/linux-arm-gnueabi-g++/ \
  -DQT_INCLUDE_DIR:PATH=${STAGING_INCDIR}/${QT_DIR_NAME}/ \
  -DQT_HEADERS_DIR:PATH=${STAGING_INCDIR}/${QT_DIR_NAME} \
  -DQT_QTCORE_INCLUDE_DIR:PATH=${STAGING_INCDIR}/${QT_DIR_NAME}/QtCore \
  -DQT_QTCORE_LIBRARY:FILE=${STAGING_LIBDIR}/libQtCore${QT_LIBINFIX}.so \
  -DQT_QTCORE_LIBRARY_RELEASE:FILE=${STAGING_LIBDIR}/libQtCore${QT_LIBINFIX}.so \
  -DQT_QTGUI_LIBRARY:FILE=${STAGING_LIBDIR}/libQtGui${QT_LIBINFIX}.so \
  -DQT_QTGUI_LIBRARY_RELEASE:FILE=${STAGING_LIBDIR}/libQtGui${QT_LIBINFIX}.so \
  -DQT_QTXML_LIBRARY:FILE=${STAGING_LIBDIR}/libQtXml${QT_LIBINFIX}.so \
  -DQT_QTXML_LIBRARY_RELEASE:FILE=${STAGING_LIBDIR}/libQtXml${QT_LIBINFIX}.so \
  -DSITE_PACKAGE:PATH=${STAGING_LIBDIR}/${PYTHON_DIR}/site-packages \
  -DPYTHON_INCLUDE_DIR:PATH=${STAGING_INCDIR}/${PYTHON_DIR} \
  -DPYTHON_LIBRARIES:PATH=${STAGING_LIBDIR}/${PYTHON_DIR} \
  -DQT_HEADERS_DIR=${STAGING_INCDIR}/${QT_DIR_NAME} \
  -DSHIBOKEN_INCLUDE_DIR:PATH=${STAGING_INCDIR}/shiboken \
  -DSHIBOKEN_PYTHON_INCLUDE_DIR:PATH=${STAGING_INCDIR}/${PYTHON_DIR} \
  -DCMAKE_LIBRARY_PATH=${STAGING_LIBDIR} \
"

do_configure_append() {
	find ${S}/PySide -name cmake_install.cmake | xargs sed -i "s:${STAGING_LIBDIR}/${PYTHON_DIR}/site-packages/PySide:${libdir}/${PYTHON_DIR}/site-packages/PySide:g"
}

FILES_${PN} =+ " \
   ${libdir}/${PYTHON_DIR}/site-packages/PySide/*.so \
   ${libdir}/${PYTHON_DIR}/site-packages/PySide/*.py \
"
FILES_${PN}-dbg =+ "${libdir}/${PYTHON_DIR}/site-packages/PySide/.debug"

FILES_${PN}-dev =+ " \
   ${datadir}/PySide/typesystems \
   ${libdir}/cmake \
"

S = "${WORKDIR}/git"

# The following exports are needed to let the cmake build configuration succeed without
# errors when detecting the correct python version
export HOST_SYS
export BUILD_SYS
export STAGING_LIBDIR
export STAGING_INCDIR

