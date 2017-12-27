#!/bin/bash
set -e

dpkg-buildpackage -b -uc -us

cp ../frajola_0.1_amd64.deb /data/
