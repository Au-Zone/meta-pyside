require shiboken.inc

DEPENDS += "python qt4-x11-free libxslt apiextractor-native generatorrunner-native python-native"
RDEPENDS_${PN} = "python-core"
PR = "${INC_PR}.1"

inherit cmake pkgconfig python-dir

SRC_URI += " \
 file://MacroPushRequiredVars.cmake \
 file://FindQt4.cmake \
 file://rename-shiboken-pkg.patch \
"
# file://fix-shiboken-cmake-config.patch \
#

SRC_URI[md5sum] = "28f4a0ab81d353f7835d82a0c5cf87d0"
SRC_URI[sha256sum] = "a773ea87d806f16a19884a3622e1cda2cdd612279750130d8752944f29b4fd28"

OE_CMAKE_AR = "${STAGING_BINDIR_TOOLCHAIN}/${AR}"

EXTRA_OECMAKE += " \
  -DPYTHON_EXECUTABLE=${STAGING_BINDIR_NATIVE}/python-native/python \
  -DCMAKE_AR=${OE_CMAKE_AR} \
  -DBUILD_TESTS=FALSE \
  -DQT_LIBINFIX=${QT_LIBINFIX} \
  -DQT_DIR_NAME=${QT_DIR_NAME} \
  -DQT_INCLUDE_DIR=${STAGING_INCDIR}/${QT_DIR_NAME} \
  -DQT_QTCORE_INCLUDE_DIR=${STAGING_INCDIR}/${QT_DIR_NAME}/QtCore \
  -DQT_QTXML_INCLUDE_DIR=${STAGING_INCDIR}/${QT_DIR_NAME}/QtXml \
  -DQT_QTGUI_INCLUDE_DIR=${STAGING_INCDIR}/${QT_DIR_NAME}/QtGui \
  -DQT_LIBRARY_DIR=${STAGING_LIBDIR} \
  -DQT_HEADERS_DIR=${STAGING_INCDIR}/${QT_DIR_NAME} \
  -DQT_QMAKE_EXECUTABLE=${STAGING_BINDIR_NATIVE}/qmake2 \
  -DCMAKE_STAGING_DIR_NATIVE:PATH=${STAGING_DIR_NATIVE} \
  -Dshibokengenerator_BINARY_DIR=${STAGING_BINDIR_NATIVE} \
"
#  -DLIB_INSTALL_DIR=${STAGING_LIBDIR_NATIVE} \ 
#

do_configure_prepend() {
	cp ${WORKDIR}/MacroPushRequiredVars.cmake ${S}/cmake/Modules/MacroPushRequiredVars.cmake
	cp ${WORKDIR}/FindQt4.cmake ${S}/cmake/Modules/FindQt4.cmake
	
	# Fixup generated *.cmake and *.pc files for wrong paths
	for i in `find ${S}/data -name "*.in" -type f` ; do \
		sed -i -e 's:@LIB_INSTALL_DIR@:${STAGING_LIBDIR}:g' \
               -e 's:@CMAKE_INSTALL_PREFIX@/bin/@SHIBOKEN_GENERATOR@:@shibokengenerator_BINARY_DIR@/shiboken:g' \
               -e 's:@CMAKE_INSTALL_PREFIX@:${STAGING_DIR_HOST}/usr:g' \
    		$i
	done
}

STAGING_LIBDIR_NATIVE = "${STAGING_DIR}/${BUILD_SYS}${prefix}/lib"
STAGING_INCDIR_NATIVE = "${STAGING_DIR}/${BUILD_SYS}${prefix}/include"

# NOTE: This needs to be appended to do_configure as pkgconfig.bbclass uses
# do_install_prepend for it's fixups and we need to run before it!
do_configure_append() {
	# Fixup generated *.cmake and *.pc files for wrong paths
	#for i in `find ${S}/data -name "*.cmake" -type f` ; do \
	#	sed -i -e 's:libshiboken-.so:libshiboken${PYTHON_SUFFIX}.so:g' \
	#		$i
	#done

	# We need do this here a second time (pkgconfig.bbclass already replaces the -L.. and
	# -I .. ones) as there are additional variables for python in the pkgconfig file
#	for i in `find ${S}/data -name "*.pc" -type f` ; do \
#		sed -i -e 's:${STAGING_BINDIR_NATIVE}:${bindir}:g' \
#			-e 's:${STAGING_INCDIR}:${includedir}:g' \
#			-e 's:${STAGING_LIBDIR}:${libdir}:g' \
#			-e 's:${STAGING_INCDIR_NATIVE}:${includedir}:g' \
#			-e 's:${STAGING_LIBDIR_NATIVE}:${libdir}:g' \
#			-e 's:-lshiboken:-lshiboken-${PYTHON_DIR}:g' \
#			$i
#	done
}

FILES_${PN}-dev += "${libdir}/cmake/ ${libdir}/pkgconfig"

# The following exports are needed to let the cmake build configuration succeed without
# errors when detecting the correct python version
export HOST_SYS
export BUILD_SYS
export STAGING_LIBDIR
export STAGING_INCDIR
