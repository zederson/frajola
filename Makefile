package_name := frajola

package_files := README.md lib config

CP := cp -apf

all: build

configuration:

bundle_deployment:

install:
	mkdir -p $(DESTDIR)/var/www/$(package_name)
	mkdir -p $(DESTDIR)/var/log/$(package_name)
	mkdir -p $(DESTDIR)/var/run/$(package_name)
	$(CP) $(package_files) $(DESTDIR)/var/www/$(package_name)/
	$(CP) etc $(DESTDIR)/
	cp target/frajola-0.1.0-standalone.jar $(DESTDIR)/var/www/$(package_name)/frajola.jar

build: configuration
	$(MAKE) configuration

