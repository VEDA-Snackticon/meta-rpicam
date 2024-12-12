# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE.txt
#   glslang/LICENSE.txt
#   glslang/license-checker.cfg
#   python/pybind11/LICENSE
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=8c8b5b19ef89ee9cfa47e768aa904935 \
                    file://glslang/LICENSE.txt;md5=2a2b5acd7bc4844964cfda45fe807dc3 \
                    file://glslang/license-checker.cfg;md5=e4bdec5a82fe0bec8d18a5639dab1a4c \
                    file://python/pybind11/LICENSE;md5=774f65abd8a7fe3124be2cdf766cd06f"

SRC_URI = "gitsm://github.com/Tencent/ncnn.git;protocol=https;branch=master"

# Modify these as desired
PV = "1.0+git"
SRCREV = "a9553fcc153d52da0e1a69e812b83934e9a65050"

S = "${WORKDIR}/git"

inherit python_setuptools_build_meta cmake

DEPENDS += "zlib jpeg openmp"

EXTRA_OECMAKE = " \
    -DNCNN_VULKAN=ON \
    -DCMAKE_INSTALL_LIBDIR=/usr/lib \
"
do_install:append() {
    # 정적 파일을 /usr/lib에 복사
    install -d ${D}/usr/lib
    cp -r ${S}/../build/src/*.a ${D}/usr/lib/
}
FILES_${PN}  += "${bindir}/* ${libdir}/* ${includedir}/*"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    cmake
#    importlib-metadata
#    ninja;
