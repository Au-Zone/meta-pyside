DESCRIPTION = "Shiboken is a plugin (front-end) for Generator Runner and a runtime library. It generates \
bindings for C++ libraries using CPython source code."
HOMEPAGE = "http://www.pyside.org"
INC_PR = "r1"
LICENSE = "LGPLv2.1 | GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=34337af480a8c452bfafe22a78fa20cb \
                    file://COPYING.libsample;md5=15a1ca44f90f3ab457d6a4fe7c0f3a19 \
                    file://COPYING.libshiboken;md5=15a1ca44f90f3ab457d6a4fe7c0f3a19"

DEPENDS = "apiextractor-native generatorrunner-native"

SRC_URI = "git://gitorious.org/pyside/shiboken.git;protocol=git;tag=a527dd51e69b80c2d5be3a1d8cd60ab2b2616fa5"
S = "${WORKDIR}/git"

QT_DIR_NAME ?= "qt4"

addtask do_fix_generator_names after do_patch before do_configure

do_fix_generator_names() {
    mv ${S}/generator/shiboken ${S}/generator/shiboken-src
}

