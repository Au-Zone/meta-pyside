require shiboken.inc

DEPENDS = "apiextractor-native generatorrunner-native python-native"
PR = "${INC_PR}.0"

SRC_URI[md5sum] = "b515ebc1deb877d9db453c2a330ab6f8"
SRC_URI[sha256sum] = "b40d43d58ca4d9fd4ee9a958ee8f139009e76f5f7e1baff788bb61e925818c65"

SRC_URI += " \
 file://generator-rename-shiboken-dir.patch \
 file://fix-shiboken-cmake-config.patch \
"

inherit cmake native
OE_CMAKE_AR = "/usr/bin/ar"
EXTRA_OECMAKE += " \
  -DBUILD_TESTS=FALSE \
  -DPYTHON_EXECUTABLE=${STAGING_BINDIR_NATIVE}/python-native/python \
  -DCMAKE_AR=${OE_CMAKE_AR} \
"

# The following exports are needed to let the cmake build configuration succeed without
# errors when detecting the correct python version
export HOST_SYS
export BUILD_SYS
export STAGING_LIBDIR
export STAGING_INCDIR

